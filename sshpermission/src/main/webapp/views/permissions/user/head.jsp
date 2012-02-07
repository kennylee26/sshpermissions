<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
            	/*
            	$('#t-users').datagrid({onDblClickRow:function dblClickRow(rowIndex,rowData){
    				$('#myform').formid('loadit',rowData);
    				$.each($('#myform input'),function(i){
    					$(this).attr("readonly","true");
    				});
    				$("#dlg-buttons a::first-child").hide();
    				$('#dlg').dialog('setTitle','查看操作信息').dialog('open');
    			}});
            	*/
        	});
        </script>
<style type="text/css">
.e-input {
	width: 198px;
	border: 1px solid #A4BED4;
	height: 18px;
	line-height: 18px;
}
</style>
<script type="text/javascript">
//----------------------------------------------用来格式化显示状态，是否打开和是否叶子
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

		var actionUrl;
		function newItem(){
			$('#myform').form('clear');
			$.each($('#myform input'),function(i){
				$(this).removeAttr("readonly");
			});
			$("#dlg-buttons a::first-child").show();
			
			$('#parentId').combotree({
				url:'<c:url value="/group/getTreeGroup.tg"/>',
				valueField:'id',
				textField:'text'
			});
			$('#dlg').dialog('setTitle', '填写用户资料').dialog('open');
			actionUrl = '<c:url value="/user/saveUser.tg"/>';
		}
		function saveItem(){
			$('#myform').form('submit', {
				url:actionUrl,
				onSubmit: function(){
					return $('#myform').form('validate');
				},
				success: function(data){
					$('#dlg').dialog('close');
					$('#t-users').datagrid('reload');
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
		
		function editItem(){
			$('#myform').form('clear');
			var row = $('#t-users').datagrid('getSelected');
			if (row){
				$('#userId').val(row.id);
				actionUrl = '<c:url value="/user/findUser.tg"/>'; 
				$('#userform').formid('loadit',row);
				
				$('#userform').form('submit', {
					url:actionUrl,
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
						$('#parentId').combotree('setValue',data.parentid);
						
						$('#employeddate').datebox('setValue',tempObj.employeddate);
						$('#birthday').datebox('setValue',tempObj.birthday);
						$('#lastlogondate').datebox('setValue',tempObj.lastlogondate);
						$('#lastlogoffdate').datebox('setValue',tempObj.lastlogoffdate);
					}
				});
				
				$('#dlg').dialog('setTitle', '修改系统资料').dialog('open');
				$.each($('#myform input'),function(i){
					$(this).removeAttr("readonly");
				});
				$("#dlg-buttons a::first-child").show();
				actionUrl = '<c:url value="/user/updateUser.tg"/>';
			}else{
				alert('请选则用户修改');
			}
		}
		function delItem(){
			var row = $('#t-users').datagrid('getSelected');
			if(row){
				$('#userId').val(row.id);
				actionUrl = '<c:url value="/user/delUser.tg"/>';
				$('#userform').form('submit', {
					url:actionUrl,
					success: function(data){
						$('#dlg').dialog('close');
						$('#t-users').datagrid('reload');
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
			}else{
				alert('请选择要删除的用户！谢谢'); 
			}
		}
		
		function advanceQuery(){
			showQueryDialog({
				width:350,
				height:230,
				form:'<c:url value="/views/permissions/user/query.jsp"/>',
				callback:function(data){
					$('#t-users').datagrid('load',data);
				}
			});
		}
	</script>