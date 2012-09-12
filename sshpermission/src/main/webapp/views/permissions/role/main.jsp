<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://com.tgyt.com.cn/tag/easyui" prefix="tgEasyui" %>
<head>
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
	//----------------------格式化显示状态名称
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
	//------------------------------------
		$(function(){
			init();
			$('#dt-role').datagrid({onDblClickRow:function dblClickRow(rowIndex,rowData){
				//alert(rowIndex+"-"+JSON.stringify(rowData));
				//$('#detailform').form('load',rowData);
				//$('#detail').dialog('setTitle','更新角色信息').dialog('open');
				$('#myform').formid('loadit',rowData);
				$.each($('#myform input'),function(i){
    					$(this).attr("readonly","true");
    				});
    				$("#dlg-buttons a:first-child").hide();
				$('#dlg').dialog('setTitle','查看角色信息').dialog('open');
			}});
		});
		function init(){
			$('#dlg').dialog({
				onOpen:function(){
					$('#dt-role').datagrid('resize');
				}
			});
			
		}
		function advanceQuery(){
			showQueryDialog({
				width:350,
				height:300,
				form:'<c:url value="/views/permissions/role/_query.jsp"/>',
				callback:function(data){
					$('#dt-role').datagrid('loadData', {total:0,rows:[]});
					$('#dt-role').datagrid('load',data);
				}
			});
		}
		function back(){
			$('#dt-role').datagrid('loadData', {total:0,rows:[]});
			$('#dt-role').datagrid('load',{});
		}
		
		var url;
		function initTree(row){
			$('#tt').tree({
				checkbox:true,
				url: '<c:url value="/permissions/role/getAllTreesRole.tg"/>?id='+row["id"],
				onClick:function(node){
					$(this).tree('toggle', node.target);
					//alert('you dbclick '+node.text);
				},
				onContextMenu: function(e, node){
					e.preventDefault();
					$('#tt').tree('select', node.target);
					$('#mm').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				},
				toolbar:[{
					text:'保存',
					iconCls:'icon-add',
					handler:function(){
						saveAuthorizate();
					}
				},'-',{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$('#auth').dialog('close');
					}
				}]
			});	
		}
		function checkbox(row,col){
			if(!row.children){
				if(row[col.dataField].indexOf("_")<0){
				//alert(row[col.dataField].indexOf("_"));
					return '<input type="checkbox" name="'+row[col.dataField]+'" />';
				}else{
					return '<input type="checkbox" name="'+row[col.dataField].substr(1)+'" checked=true/>';
				}
			}
			return '';
		}
		function customResName(row, col){
			var name = row[col.dataField] || "";
			if(!row.children){
				return name+'<input type="checkbox" >';
			}
			return row[col.dataField]+'';
			
		}
		function initTreeGrid(row){
			url = '<c:url value="/permissions/role/getActionsRole.tg"/>?id='+row["id"];
			 $('#actions').form('submit',{
					url:url,
					onSubmit:function(){return true;},
					success:function(data){
						var col=eval('('+data+')');
						var config = {
								id: "tgActions",
								width: "900",
								renderTo: "resActions",
								headerAlign: "left",
								headerHeight: "30",
								dataAlign: "left",
								indentation: "20",
								hoverRowBackground: "false",
								folderColumnIndex: "0",
								//folderOpenIcon:"../../../images/tree_folder_open.gif",
								//folderCloseIcon:"../../../images/tree_folder.gif",
								//defaultLeafIcon:"../../../images/tree_file.gif",
								folderOpenIcon:"../../images/tree_folder_open.gif",
								folderCloseIcon:"../../images/tree_folder.gif",
								defaultLeafIcon:"../../images/tree_file.gif",
								columns:[],
								data:[]
							};
						
						config.columns=col;
						 $.ajax({url: '<c:url value="/permissions/role/getRAMappingsRole.tg"/>?id='+row["id"],  
								 dataType : "json",  
								 type: "post",  
								 timeout: 5000000,  
								 success: function(data){
									 config.data=data;
									//创建一个组件对象
									var treeGrid = new TreeGrid(config);
									treeGrid.show();
									 //查找id=tgActions的表格，下面除了第一个tr，下面的第一个td，所有的复习框的点击事件。
							        $("#tgActions tr:not(:first) > td:nth-child(1)").find("input[type='checkbox']").bind("click", function () {
							            if (!!this.checked ) {
							                $(this).parent().nextAll().find("input[type='checkbox']").attr("checked", true);
							            }
							            else {
							                $(this).parent().nextAll().find("input[type='checkbox']").attr("checked", false);
							            }
							        });
									//查找id=tgActions的表格，下面第一个tr，下面除了第一个td下，所有的复习框的点击事件。
									 $("#tgActions tr:first > td:not(:first)").find("input[type='checkbox']").bind("click", function () {
										var index = $(this).parent().index()+1;
										if (!!this.checked ) {
							              $("#tgActions tr:not(:first) > td:nth-child("+index+")").find("input[type='checkbox']").attr("checked", true);
							            }
							            else {
							              $("#tgActions tr:not(:first) > td:nth-child("+index+")").find("input[type='checkbox']").attr("checked", false);
							            }
										
							        });
									//查找id=tgActions的表格，下面第一个tr，下面第一个td下，的复习框的点击事件。
									$("#tgActions tr:first>td:first").find("input[type='checkbox']").bind("click", function () {
							            if (!!this.checked ) {
							               $("#tgActions tr>td").find("input[type='checkbox']").attr("checked", true);
							            }
							            else {
							               $("#tgActions tr>td").find("input[type='checkbox']").attr("checked", false);
							            }
							        });
								 },  
								 error: function() { alert("error"); }  
								 });  
						
					}
				});
			
		}
		function resActions(){
			var row = $('#dt-role').datagrid('getSelected');
			if (row){
				$("#resActions").html("");
				initTreeGrid(row);
				url = '<c:url value="/permissions/role/saveResActionsRole.tg"/>?id='+row["id"];
				row = JSON.stringify(row).replace(/\./g,"\\\\.");
				$('#ac').dialog('setTitle','资源分配操作').dialog('open');
			} else {
				$.messager.show({
					title:'注意',
					msg:'请先选择角色，再进行修改。'
				});
			}
		}
		//=======================处理授权操作==================================================================
		//使用自己生成的资源操作列表给角色分配操作
		function resAct(){
			var row = $('#dt-role').datagrid('getSelected');
			if (row){
			 	$("#dt-resAct").datagrid({
					url:'<c:url value="/permissions/role/getResActMappingsRole.tg"/>?id='+row["id"]
				});
				url = '<c:url value="/permissions/role/saveResActionsRole.tg"/>?id='+row["id"];
				$('#resAct').dialog('setTitle','资源分配操作').dialog('open');
			} else {
				$.messager.show({
					title:'注意',
					msg:'请先选择角色，再进行修改。'
				});
			}
		}
		//当点击第二列的checkbox时，将当前行的所有checkbox全选或全不选
		function selectCurrentRow(select,id){
			//查找id=resActForm下的所有checkbox，并选中或取消选中
			$("#resActForm").find("input[type='checkbox']").each(function(index){
				if(this.name.indexOf(id+":")==0){
					this.checked = select;
				}
	        });
		}
		//系统列的formatter
		function systemFormatter(value,rowData,rowIndex){
			return value.name;
		}
		//资源列的formatter
		function resourceFormatter(value,rowData,rowIndex){
			return '<input type="checkbox" onchange="selectCurrentRow(this.checked,this.id);" id="'+value.id+'" name="'+value.ename+'"/>'+value.name;
		}
		//操作列的formatter
		function actionsFormatter(value,rowData,rowIndex){
// 			alert(JSON.stringify(value));
			var ownActions = value.ownActions;
			var allActions = value.allActions;
			if(!!allActions && allActions.length>0){
				var html = "";
				for(var i=0;i<allActions.length;i++){
					html+='<input type="checkbox" ';
					if(!!ownActions && ownActions.indexOf(allActions[i].ename)!=-1){
						html+=' checked="checked" ';
					}
					//在每个操作的name属性的格式为：资源id:操作ename
					html+=' name="'+rowData.res.id+':'+allActions[i].ename+'"/>'+allActions[i].name;
				}
				return html;
			}else{
				return '<div style="color:red">该资源暂无操作</div>';
			}
		}
		//保存授权操作
		function saveResAct(){
			var actions="";
			//存储所有选择的操作
			var allSelections = new Array();
			//存储所有的资源
			var allResources = new Array();
			
			$("#resActForm").find("input[type='checkbox']").each(function(index){
				//如果name属性里面没有冒号的话，表示是资源，则存入资源数组
				if(this.name.indexOf(":")==-1 ){
					allResources[allResources.length] = this.id;
				}else if(this.checked){
					//不是资源数组并且被选中了，就存入操作数组
					allSelections[allSelections.length] = this.name;
				}
	        });
			//循环遍历资源数组，从操作数组中找出对应的操作依次加入actions变量中
			for(var i=0;i<allResources.length;i++){
				var flag=false;
				for(var j=0;j<allSelections.length;j++){
					//如果操作中包含资源ID，表示当前操作输入该资源，就顺序加入actions中
					if(allSelections[j].indexOf(allResources[i]+":")==0){
						flag=true;
						actions+=allSelections[j]+",";
					}
				}
				if(flag){
					actions=actions.substring(0,actions.length-1)+";";
				}
			}
			$("#saveResActInput").val(actions);
			 $('#saveResActForm').form('submit',{
					url:url,
					onSubmit:function(){return true;},
					success:function(json){
						$('#resAct').dialog('close');
						if(json.success){
							$.messager.show(
								{
									title:'提示',
									msg:'操作成功！',
									showType:'slide'
								}
							);
						}
						if(json.error){
							$.messager.alert('警告','操作失败！','error');
						}
					}
			 });
// 			$.getJSON(url, { roleActions: actions }, function(json){
// 				$('#resAct').dialog('close');
// 				if(json.success){
// 					$.messager.show(
// 						{
// 							title:'提示',
// 							msg:'操作成功！',
// 							showType:'slide'
// 						}
// 					);
// 				}
// 				if(json.error){
// 					$.messager.alert('警告','操作失败！','error');
// 				}
// 			});
// 			var data = $('#resActForm').form('submit',{
// 				url:url,
// 				onSubmit:function(){return true;},
// 				success:function(data){
// 					$('#resAct').dialog('close');
// 					data=eval('('+data+')');
// 					if(data.success){
// 						$.messager.show(
// 							{
// 								title:'提示',
// 								msg:'操作成功！',
// 								showType:'slide'
// 							}
// 						);
// 					}
// 					if(data.error){
// 						$.messager.alert('警告','操作失败！','error');
// 					}
// 				}
// 			});
			
		}
		
		//=========================================================================================
		
		//原来用treegrid的时候的保存操作的方法
		function saveResActions(){
// 			alert(JSON.stringify($("#dt-resAct").datagrid("getRows")));
// 			alert($("#resActForm").html());
// 			return;
			var actions="";
			for(var i=1;i<$("#tgActions tr").length;i++){
				var flag=false;
				 $("#tgActions tr:nth-child("+i+") > td:not(:first)").find("input[type='checkbox']").each(function (i) {
					if (!!this.checked ) {
						if(!!this.name){
							flag=true;
							actions+=this.name+",";
						}
		            }
		        });
				 if(flag){
					 actions+=";";
				 }
			}
			url=url+"&roleActions="+actions;
			var data = $('#authorization').form('submit',{
				url:url,
				onSubmit:function(){return true;},
				success:function(data){
					$('#ac').dialog('close');
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
						$.messager.alert('警告','操作失败！','error');
					}
				}
			});
			
		}
		function authorizate(){
			var row = $('#dt-role').datagrid('getSelected');
			if (row){
				initTree(row);
				url = '<c:url value="/permissions/role/saveAuthorizateRole.tg"/>?id='+row["id"];
				row = JSON.stringify(row).replace(/\./g,"\\\\.");
				$('#auth').dialog('setTitle','角色分配资源').dialog('open');
			} else {
				$.messager.show({
					title:'注意',
					msg:'请先选择角色，再进行修改。'
				});
			}
		}
		function saveAuthorizate(){
			var nodes = $('#tt').tree('getChecked');
			var s = '';
			for(var i=0; i<nodes.length; i++){
				if (s != '') s += ',';
				s += nodes[i].id;
			}
			url = url+'&rids='+s;
			var data = $('#authorization').form('submit',{
				url:url,
				onSubmit:function(){return true;},
				success:function(data){
					$('#auth').dialog('close');
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
						$.messager.alert('警告','操作失败！','error');
					}
				}
			});
			
		}
		function newItem(){
			url = '<c:url value="/permissions/role/saveRole.tg"/>';
			$('#myform').form('clear');
			$.each($('#myform input'),function(i){
				$(this).removeAttr("readonly");
			});
			$("#dlg-buttons a:first-child").show();
			$('#dlg').dialog('setTitle','增加角色').dialog('open');
		}
		function editItem(){
			var row = $('#dt-role').datagrid('getSelected');
			if (row){
				url = '<c:url value="/permissions/role/updateRole.tg"/>?';
				//row = JSON.stringify(row).replace(/\./g,"\\\\.");
				//$('#myform').form('load',eval('('+row+')'));
				$('#myform').formid('loadit',row);
				$.each($('#myform input'),function(i){
					$(this).removeAttr("readonly");
				});
				$("#dlg-buttons a:first-child").show();
				$('#dlg').dialog('setTitle','更新角色信息').dialog('open');
			} else {
				$.messager.show({
					title:'注意',
					msg:'请先选择数据，再进行修改。'
				});
			}
		}
		function removeItem(){
			var row = $('#dt-role').datagrid('getSelected');
			if (row){
				if(confirm('确定要删除？')){
					url = '<c:url value="/permissions/role/deleteRole.tg"/>?id='+row["id"];
					 $('#myform').form('submit',{
							url:url,
							onSubmit:function(){return true;},
							success:function(data){
								$('#dt-role').datagrid('reload');
								data=eval('('+data+')');
								if(data.success){
									$.messager.show(
										{
											title:'提示',
											msg:'删除操作成功！',
											showType:'slide'
										}
									);
								}
								if(data.error){
									$.messager.alert('警告','删除操作失败！','error');
								}
							}
						});
				}
			} else {
				$.messager.show({
					title:'注意',
					msg:'请先选择角色信息，再进行删除。'
				});
			}
		}
		function saveItem(){
			var data = $('#myform').form('submit',{
				url:url,
				onSubmit:function(){return $(this).form('validate');},
				success:function(data){
					$('#dlg').dialog('close');
					$('#dt-role').datagrid('reload');
					data=eval('('+data+')');
					if(data.success){
						$.messager.show(
							{
								title:'提示',
								msg:'保存操作成功！',
								showType:'slide'
							}
						);
					}
					if(data.error){
						$.messager.alert('警告','操作失败！','error');
					}
				}
			});
			
		}
		
	</script>
    </head>
	<body style="margin:0;padding:0;height:100%;overflow:hidden;background:#F2FBFF">
  			<div id="mainlayout" class="easyui-layout" fit="true">
			<div region="north" border="false">
				<div class="toolbar">
					<table cellpadding="0" cellspacing="0" style="width:95%;height=30px;">
						<tr>
							<td>
								<tgEasyui:easyuiButton iconCls="icon-add" method="newItem()" permission="role:add" operationName="新增"/>
								<tgEasyui:easyuiButton iconCls="icon-edit" method="editItem()" permission="role:modify" operationName="修改"/>
								<tgEasyui:easyuiButton iconCls="icon-cancel" method="removeItem()" permission="role:delete" operationName="删除"/>
								<tgEasyui:easyuiButton iconCls="icon-reload" method="back()" permission="role:refresh" operationName="刷新"/>
								<tgEasyui:easyuiButton iconCls="icon-search" method="authorizate()" permission="role:assignResource" operationName="分配资源"/>
								<tgEasyui:easyuiButton iconCls="icon-search" method="resAct()" permission="role:assignAction" operationName="分配操作"/>
 							<!-- 	<a href="javascript:newItem()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a> 
 								<a href="javascript:editItem()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
 								<a href="javascript:removeItem()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a> 
 								<a href="javascript:back()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a> 
 								<a href="javascript:authorizate()" class="easyui-linkbutton" iconCls="icon-search" plain="true">分配资源</a> 
 								<a href="javascript:resAct()" class="easyui-linkbutton" iconCls="icon-search" plain="true">分配操作</a>   -->


<!-- 								<a href="javascript:resActions()" class="easyui-linkbutton" iconCls="icon-search" plain="true">分配操作</a> -->
							</td>
							<td style="text-align:right">
							<tgEasyui:easyuiButton iconCls="icon-search" method="advanceQuery()" permission="role:advanceQuery" operationName="高级查询"/>
<!-- 								<a href="javascript:advanceQuery()" class="easyui-linkbutton" plain="true">高级查询</a> -->
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div region="center" border="false">
				<table id="dt-role" class="easyui-datagrid"
						url="<c:url value='/permissions/role/getItemsRole.tg'/>"
						fit="true" border="false" 
						pagination="true" 						
						singleSelect="true" rownumbers="true">
					<thead>
						<tr>
							<th field="name" width="100" sortable="true">角色名称</th>
							<!-- <th field="enname" width="100" sortable="true">英文名称</th> 
							<th field="orderid" width="100" sortable="true">排序</th>-->
							<th field="status" width="100" sortable="true" formatter="statusFormatter">状态</th>
							<th field="memo" width="250">备注</th>
						</tr>
					</thead>
				</table>
			</div>
		<div id="dlg" class="easyui-dialog" style="width:350px;height:430px;"
				closed="true" modal="true" buttons="#dlg-buttons">
			<form id="myform" method="post">
				<jsp:include page="_form.jsp"></jsp:include>
			</form>
			<div id="dlg-buttons" style="text-align:center">
				<a  href="#" class="easyui-linkbutton"  iconCls="icon-save" onclick="saveItem()">保存</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
			</div>
		</div>
		<div id="auth" class="easyui-dialog" style="width:350px;height:250px;"
				closed="true" modal="true" buttons="#auth-buttons">
			<form id="authorization" method="post">
				<ul id="tt"></ul>
			</form>
			<div id="auth-buttons" style="text-align:center">
				<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveAuthorizate()">保存</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#auth').dialog('close')">关闭</a>
			</div>
		</div>
		<div id="ac" class="easyui-dialog" style="width:900px;height:500px;"
				closed="true" modal="true" buttons="#actions-buttons">
			<form id="actions" method="post">
				<div id="resActions"></div>
			</form>
			<div id="actions-buttons" style="text-align:center">
				<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveResActions()">保存</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#ac').dialog('close')">关闭</a>
			</div>
		</div>
		<div id="resAct" class="easyui-dialog" style="width:900px;height:500px;"
				closed="true" modal="true" buttons="#resAct-buttons">
			<form id="resActForm" method="post">
<!-- 				<div id="resActDiv"></div> -->
				<table id="dt-resAct"  border="false" singleSelect="true" 
						rownumbers="true"  checkbox="true">
					<thead>
						<tr>
							<th field="sys" width="100" formatter="systemFormatter">系统名称</th>
							<th field="res" width="100" formatter="resourceFormatter">资源名称</th>
							<th field="acts" width="630" formatter="actionsFormatter">操作</th>
						</tr>
					</thead>
				</table>
			</form>
			<div id="resAct-buttons" style="text-align:center">
				<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveResAct()">保存</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#resAct').dialog('close')">关闭</a>
			</div>
		</div>
		<div id="detail" class="easyui-dialog" style="width:350px;height:430px;"
				closed="true" modal="true" buttons="#detail-buttons">
			<form id="detailform" >
				<%-- <jsp:include page="_detail.jsp"></jsp:include> --%>
			</form>
			<div id="detail-buttons" style="text-align:center">
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#detail').dialog('close')">关闭</a>
			</div>
		</div>
		<form id="saveResActForm" method="post">
			<input type="text" name="roleActions" id="saveResActInput"/>
		</form>
	</div>
</body>