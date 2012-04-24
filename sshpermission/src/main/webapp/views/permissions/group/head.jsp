<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>权限管理系统</title>
    <jsp:include page="/views/include.jsp"></jsp:include>
    <script type="text/javascript">
    	var contextPath = '<%=request.getContextPath()%>';
    	$.parser.onComplete = function(){
        	$('body').css('visibility','visible');
        	setTimeout(function(){
         	$('#loading-mask').remove();
        	},50);
    	};
    	$(function(){
        	$(window).resize(function(){
            	$('#mainlayout').layout('resize');
        	});
    	});
    </script>
	<script type="text/javascript">
	//----------------------------------------------用来格式化显示状态，是否打开和是否叶子
	var status;
	$.getJSON("<c:url value='/permissions/resources/outDicJsonByNicknameResources.tg?nickName=status'/>", function(json){
		status=json;
	});
	function statusFormatter(value){
		for(var i=0; i<status.length; i++){
			if (status[i].value == value) return status[i].name;
		}
		return value;
	}
	var education;
	$.getJSON("<c:url value='/permissions/actions/outDicJsonByNicknameActions.tg?nickName=education'/>", function(json){
		education=json;
	});
	function educationFormatter(value){
		for(var i=0; i<education.length; i++){
			if (education[i].value == value) return education[i].name;
		}
		return value;
	}


	var sex;
	$.getJSON("<c:url value='/permissions/actions/outDicJsonByNicknameActions.tg?nickName=sex'/>", function(json){
		sex=json;
	});
	function sexFormatter(value){
		for(var i=0; i<sex.length; i++){
			if (sex[i].value == value) return sex[i].name;
		}
		return value;
	}
	//---------------------------------------------------------------------
	
		var url;	
	
		$(function(){
			$('#tree').tree({
				onClick : function(node) {
					var id =node.id.substr(4);
					if('ual' == id){
						url = '<c:url value='/user/getItemsUser.tg'/>';
					}else{
						url = '<c:url value='/user/getItemsUser.tg'/>?id=' + id;
					}
					$('#dt-resources').datagrid('options').url = url;
					$('#dt-resources').datagrid("reload");
				}
			});
		});
		
		function newItem(){
			$('#myform').form('clear');
			var obj = $('#tree').tree('getSelected');
			if(obj){
				var id =obj.id.substr(4);
				if('ual' == id){
					$('#parentId').combotree({
						url:'<c:url value="/group/getTreeGroup.tg"/>',
						valueField:'id',
						textField:'text'
					});
					url = '<c:url value="/group/saveGroup.tg"/>';
					$('#dlg').dialog('setTitle', '填写组资料').dialog('open');
					return;
				}else{
					url = '<c:url value="/group/findGroup.tg"/>?id=' + id;
				}
				$('#myform').form('submit', {
					url:url,
					onSubmit: function(){
						return true;
					},
					success: function(data){
						data=eval('('+data+')');
						var tempObj = data.rows[0];
						$('#parentId').combotree({
							url:'<c:url value="/group/getTreeGroup.tg"/>',
							valueField:'id',
							textField:'text'
						});
						$('#parentId').combotree('setText',data.parentName);
						$('#parentId').combotree('setValue',tempObj.id);
					}
				});
			}else{
				$('#parentId').combotree({
					url:'<c:url value="/group/getTreeGroup.tg"/>',
					valueField:'id',
					textField:'text'
				});
			}
			
			url = '<c:url value="/group/saveGroup.tg"/>';
			$('#dlg').dialog('setTitle', '填写组资料').dialog('open');
			
		}
		
		function loadAllTree(){
			url = '<c:url value="/group/indexGroup.tg"/>';
			$('groupForm').form('submit',{
				url:url,
				onSubmit: function(){
					return $('#myform').form('validate');
				},
				success: function(data){
					$("#allTrees").html('');
					$("#allTrees").append(data);
					$("#tree").tree();
				}
			});
		}
		
		function saveItem(){
			$('#myform').form('submit', {
				url:url,
				onSubmit: function(){
					return $('#myform').form('validate');
				},
				success: function(data){
					$('#dlg').dialog('close');
					data=eval('('+data+')');
					if(data.success){
						$.messager.show(
							{
								title:'提示',
								msg:'操作成功！',
								showType:'slide'
							}
						);
						loadAllTrees();
					}
					if(data.error){
						$.messager.alert('提示','操作失败！','error');
					}
				}
			});
		}
		
		//==========================================
		function loadAllTrees(){
			url = '<c:url value="/group/listGroup.tg"/>?';
			 $('#myform').form('submit',{
					url:url,
					onSubmit:function(){return true;},
					success:function(data){
						$("#allTrees").html("");
						$("#allTrees").append($(data));
						$("#tree").tree();
						$('#tree').tree({
							onClick : function(node) {
								var id =node.id.substr(4);
								if('ual' == id){
									url = '<c:url value='/user/getItemsUser.tg'/>';
								}else{
									url = '<c:url value='/user/getItemsUser.tg'/>?id=' + id;
								}
								$('#dt-resources').datagrid('options').url = url;
								$('#dt-resources').datagrid("reload");
							}
						});
						url = '<c:url value='/user/getItemsUser.tg'/>';
						$('#dt-resources').datagrid('options').url = url;
						$('#dt-resources').datagrid("reload");
					}
				});
		}
		//==========================================
		
		
		
		function delItem(){
			var obj = $('#tree').tree('getSelected');
			if(obj){
				var id =obj.id.substr(4);
				if('ual' == id){
					$.messager.alert('提示','根元素不能删除','info');
					return;
				}else{
					url = '<c:url value="/group/delGroup.tg"/>?id=' + id;
				}
				if(confirm('确定要删除？')){
					$('#myform').form('submit', {
						url:url,
						onSubmit: function(){
							return true;
						},
						success: function(data){
							data=eval('('+data+')');
							if(data.success){
								$.messager.show(
									{
										title:'提示',
										msg:'操作成功！',
										showType:'slide'
									}
								);
								loadAllTrees();
							}
							if(data.error){
								$.messager.alert('提示','操作失败！','error');
							}
						}
					});
				}
			}else{
				$.messager.alert('提示','请选择要删除的组','info');
			}
		}
		function editItem(){
			$('#myform').form('clear');
			var obj = $('#tree').tree('getSelected');
			if(obj){
				var id =obj.id.substr(4);
				if('ual' == id){
					$.messager.alert('提示','根元素不能修改','info');
					return;
				}else{
					url = '<c:url value="/group/findGroup.tg"/>?id=' + id;
				}
				
				$('#myform').form('submit', {
					url:url,
					onSubmit: function(){
						return true;
					},
					success: function(data){
						data=eval('('+data+')');
						$('#name').val(data.rows[0].name);
						$('#enName').val(data.rows[0].enName);
						$('#groupType').val(data.rows[0].groupType);
						$('#orderId').val(data.rows[0].orderId);
						$('#memo').val(data.rows[0].memo);
						$('#myhomeid').val(data.rows[0].id);
						
						$('#parentId').combotree({
							url:'<c:url value="/group/getTreeGroup.tg"/>',
							valueField:'id',
							textField:'text'
						});
						
						
						$('#parentId').combotree('setValue',data.parentid);
						$('#parentId').combotree('setText',data.parentName);
						
						$('#status').combobox('setValue',data.rows[0].status);
					}
				});

				$('#dlg').dialog('setTitle', '修改组资料').dialog('open');
				url = '<c:url value="/group/updateGroup.tg"/>';
			}else{
				$.messager.alert('提示','请选择要修改的组','info');
			}
		}
		function lookItem(){
			var obj = $('#tree').tree('getSelected');
			if(obj){
				var id =obj.id.substr(4);
				if('ual' == id){
					$.messager.alert('提示','根元素不允许查看','info');
					return;
				}else{
					url = '<c:url value="/group/findGroup.tg"/>?id=' + id;
				}
				$('#myform').form('submit', {
					url:url,
					onSubmit: function(){
						return true;
					},
					success: function(data){
						data=eval('('+data+')');
						$('#lname').val(data.rows[0].name);
						$('#lenname').val(data.rows[0].enName);
						$('#lgrouptype').val(data.rows[0].groupType);
						$('#lorderid').val(data.rows[0].orderId);
						$('#lmemo').val(data.rows[0].memo);				
						if('1' == data.rows[0].status){
							$('#lstatus').val('启用');
						}else{
							$('#lstatus').val('禁用');
						}
					}
				});
				$('#look').dialog('setTitle', '查看组资料').dialog('open');
			}else{
				$.messager.alert('提示','请选择要查看的组','info');
			}
		}
		function addUserItem(){
			var obj = $('#tree').tree('getSelected');
			if(obj){
				var id =obj.id.substr(4);
				if('ual' == id){
					$.messager.alert('提示','根元素不允许添加','info');
					return;
				}else{
					$('#adduser form table tbody').html('');
					url = '<c:url value="/group/findGroup.tg"/>';
					$('#id').val(id);
				}
				$('#groupForm').form('submit', {
					url:url,
					onSubmit: function(){
						return true;
					},
					success: function(data){
						data=eval('('+data+')');
						$('#adduser').dialog('setTitle', '添加用户到' + data.rows[0].name + '组').dialog('open');
					}
				});
				
				url = '<c:url value="/user/getOtherUser.tg"/>';
				$('#groupForm').form('submit', {
					url:url,
					onSubmit: function(){
						return true;
					},
					success: function(data){
						data=eval('('+data+')');
						$("#dt-otheruser").datagrid('loadData',data);
					}
				});
				$('#gid').val(id);
			}else{
				$.messager.alert('提示','请先选择组','info');
			}
		}
		function addUsers(){
			var selected=$("#dt-otheruser").datagrid('getSelections');
			var temp = "";
			for ( var i = 0; i < selected.length; i++) {
					temp = temp + selected[i].id ;
					if(i != selected.length-1){
						temp += ",";
					}
			}
			url = '<c:url value="/group/addUserToGroup.tg?userIds="/>'+temp; 
			$('#self').form('submit', {
				url:url,
				onSubmit: function(){
					return true;
				},
				success: function(data){
					data=eval('('+data+')');
					$('#adduser form table tbody').html('');
					$('#adduser').dialog('close');
					url = '<c:url value="/user/getItemsUser.tg"/>?id=' + $('#gid').val();
					$('#dt-resources').datagrid('options').url = url;
					$('#dt-resources').datagrid("reload");
				}
			});
		}
		function selectAll(tem){
			if(tem.checked){
				$('#adduser form table tbody').find('input').each(function(i){
					$(this).attr('checked',true);
				});
			}else{
				$('#adduser form table tbody').find('input').each(function(i){
					$(this).removeAttr('checked');
				});
			}
		}
		
		function delUserItem(){
			var obj = $('#tree').tree('getSelected');
			if(obj){
				var id =obj.id.substr(4);
				if('ual' == id){
					$.messager.alert('提示','根元素内成员不能删除','info'); 
					return;
				}
				var row = $('#dt-resources').datagrid('getSelections');
				if(row.length != 0){
					$('#groupid').val(id);
					if(confirm('确实要删除这些用户吗？')){
						var temp = '';
						for(var i=0;i<row.length;i++){
							temp = temp + row[i].id + ',';
						}
						$('#id').val(temp);
						url = '<c:url value="/group/delUserFromGroup.tg"/>'; 
						$('#groupForm').form('submit', {
							url:url,
							onSubmit: function(){
								return true;
							},
							success: function(data){
								url = '<c:url value="/user/getItemsUser.tg"/>?id=' + $('#groupid').val();
								$('#dt-resources').datagrid('options').url = url;
								$('#dt-resources').datagrid("reload");
								data=eval('('+data+')');
								if(data.success){
									$.messager.show(
											{
												title:'提示',
												msg:'操作成功！',
												showType:'slide'
											}
									);
								}
								if(data.error){
									$.messager.alert('提示','操作失败！','error');
								}
							}
						});
					}
				}else{
					$.messager.alert('提示','请选择组内成员','info');
				}
			}else{
				$.messager.alert('提示','请选择组','info');
			}
		}
		function groupToRole(){
			//$('#rolGroup form table tbody').html('');
			
			var obj = $('#tree').tree('getSelected');
			if(obj){
				var id =obj.id.substr(4);
				if('ual' == id){
					$.messager.alert('提示','根元素内成员不授权','info'); 
					return;
				}
				$("#dlg-button a::first-child").show();
				$("#dlg-button a::last-child").hide();
				$("#dlg-button a:nth-child(2)").show();
				$('#dlgwindow').dialog('setTitle', '组授权角色').dialog('open');
				
				//$('#role-buttons a').first().show();
				//$('#role-buttons a').last().hide();
				
				$('#groupid').val(id);
				url = '<c:url value="/group/otherRoleFromGroup.tg"/>';  
				$('#groupForm').form('submit', {
					url:url,
					success: function(data){
						$('#gsid').val(id);
						data=eval('('+data+')');
						/*  for(var i=0;i<data.length;i++){
							var temp = '<tr><td><input type="checkbox" name="roleIds" value="' + data[i].id + '"/></td><td>' + data[i].name + '</td><td>&nbsp;</td><td>' + data[i].memo + '</td></tr>';
							$('#rolGroup form table tbody').append(temp);
						}  */
						$('#dt-group').datagrid("loadData", data);
					}
				});
			}else{
				$.messager.alert('提示','请选择组','info');
			}
		}
		function addRole(){
			
			var selected = $('#dt-group').datagrid('getSelections');
			//alert(JSON.stringify(selected));
			var temp = "";
			for ( var i = 0; i < selected.length; i++) {
					temp = temp + selected[i].id ;
					if(i != selected.length-1){
						temp += ",";
					}
			}
			//var data = select
			//alert(temp);
			url = '<c:url value="/group/addRoleToGroup.tg"/>?roleIds='+temp; 
			//var data = '{"roleIds":"' + temp + '"}';
			$('#addRole').form('submit', {
				url:url,
				success: function(data){
					data=eval('('+data+')');
					$('#dlgwindow').dialog('close')
					//$('#rolGroup').dialog('close');
				}
			});
		}
		function lookGroupHaveRole(){
			var obj = $('#tree').tree('getSelected');
			if(obj){
				var id =obj.id.substr(4);
				if('ual' == id){
					$.messager.alert('提示','根元素内成员不授权','info'); 
					return;
				}
				/* $('#rolGroup form table tbody').html('');
				$('#role-buttons a').first().show();
				$('#role-buttons a').last().hide();  */
				$("#dlg-button a::first-child").hide();
				$("#dlg-button a::last-child").hide();
				$("#dlg-button a:nth-child(2)").show();
				$('#dlgwindow').dialog('setTitle', '查看组拥有的角色').dialog('open'); 
				$('#groupid').val(id);
				url = '<c:url value="/group/groupHaveRole.tg"/>';  
				$('#groupForm').form('submit', {
					url:url,
					success: function(data){
						data=eval('('+data+')');
						/* for(var i=0;i<data.length;i++){
							var temp = '<tr><td></td><td>' + data[i].name + '</td><td>&nbsp;</td><td>' + data[i].memo + '</td></tr>';
							$('#rolGroup form table tbody').append(temp);
						} */
						$('#dt-group').datagrid("loadData", data);
					}
				});
			}else{
				$.messager.alert('提示','请选择组','info');
			}
		}
		function removeGroupHaveRole(){
			var obj = $('#tree').tree('getSelected');
			if(obj){
				var id =obj.id.substr(4);
				if('ual' == id){
					$.messager.alert('提示','根元素内没有角色','info'); 
					return;
				}
				/* $('#rolGroup form table tbody').html('');
				$('#role-buttons a').first().hide();
				$('#role-buttons a').last().show(); */
				$("#dlg-button a::first-child").hide();
				$("#dlg-button a::last-child").show();
				$("#dlg-button a:nth-child(2)").hide();
				$('#dlgwindow').dialog('setTitle', '移除组拥有的角色').dialog('open'); 
				$('#groupid').val(id);
				
				url = '<c:url value="/group/groupHaveRole.tg"/>';  
				$('#groupForm').form('submit', {
					url:url,
					success: function(data){
						$('#gsid').val(id);
						data=eval('('+data+')');
						/* for(var i=0;i<data.length;i++){
							var temp = '<tr><td><input type="checkbox" name="roleIds" value="' + data[i].id + '"/></td><td>' + data[i].name + '</td><td>&nbsp;</td><td>' + data[i].memo + '</td></tr>';
							$('#rolGroup form table tbody').append(temp);
						} */
						$('#dt-group').datagrid("loadData", data);
					}
				});
			}else{
				$.messager.alert('提示','请选择组','info');
			}
		}
		function removeRole(){
			//alert(1);
			var selected = $('#dt-group').datagrid('getSelections');
			//alert(JSON.stringify(selected));
			var temp = "";
			for ( var i = 0; i < selected.length; i++) {
					temp = temp + selected[i].id ;
					if(i != selected.length-1){
						temp += ",";
					}
			}
			//var data = select
			//alert(temp);
			actionUrl = '<c:url value="/group/removeGroupHaveRole.tg"/>?roleIds='+temp; 
			$('#addRole').form('submit', {
				url:actionUrl,
				success: function(data){
					data=eval('('+data+')');
					$('#dlgwindow').dialog('close')
				}
			});
		}
		/* function clickeAll(tem){
			if(tem.checked){
				$('#rolGroup form table tbody').find('input').each(function(i){
					$(this).attr('checked',true);
				});
			}else{
				$('#rolGroup form table tbody').find('input').each(function(i){
					$(this).removeAttr('checked');
				});
			}
		} */
	</script>
