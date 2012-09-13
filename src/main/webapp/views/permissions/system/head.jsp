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
            	$('#t-systems').datagrid({onDblClickRow:function dblClickRow(rowIndex,rowData){
    				$('#myform').formid('loadit',rowData);
    				$.each($('#myform input'),function(i){
    					$(this).attr("readonly","true");
    				});
    				$("#dlg-buttons a::first-child").hide();
    				$('#dlg').dialog('setTitle','查看操作信息').dialog('open');
    			}});
        	});
        </script>
	<style type="text/css">
		.e-input{
			width:198px;
			border:1px solid #A4BED4;
			height:18px;
			line-height:18px;
		}
	</style>
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
	
	//---------------------------------------------------------------------
		
		var actionUrl;
		function newItem(){
			$('#dlg').dialog('setTitle', '填写系统资料').dialog('open');
			$('#myform').form('clear');
			$.each($('#myform input'),function(i){
				$(this).removeAttr("readonly");
			});
			$("#dlg-buttons a::first-child").show();
			actionUrl = '<c:url value="/sys/saveMessage.tg"/>';
		}
		function editItem(){
			var t = $('#t-systems');
			var row = t.datagrid('getSelected');
			if (row){
				$('#sysId').val(row.id);
				actionUrl = '<c:url value="/sys/findMessage.tg"/>';
				$('#sysform').formid('loadit',row);
				$('#dlg').dialog('setTitle', '修改系统资料').dialog('open');
				$.each($('#myform input'),function(i){
					$(this).removeAttr("readonly");
				});
				$("#dlg-buttons a::first-child").show();
				actionUrl = '<c:url value="/sys/updateMessage.tg"/>';
			}else{
				alert('请选则一套系统修改');
			}
		}
		function delItem(){
			var t = $('#t-systems');
			var row = t.datagrid('getSelected');
			if(row){
				$('#sysId').val(row.id);
				actionUrl = '<c:url value="/sys/delMessage.tg"/>';
				$('#sysform').form('submit', {
					url:actionUrl,
					success: function(data){
						$('#dlg').dialog('close');
						$('#t-systems').datagrid('reload');
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
				alert('请选择要删除的系统！谢谢');
			}
		}
		function saveItem(){
			$('#myform').form('submit', {
				url:actionUrl,
				onSubmit: function(){
					return $('#myform').form('validate');
				},
				success: function(data){
					$('#dlg').dialog('close');
					$('#t-systems').datagrid('reload');
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
		function advanceQuery(){
			showQueryDialog({
				width:350,
				height:230,
				form:'<c:url value="/views/permissions/system/query.jsp"/>',
				callback:function(data){
					$('#t-systems').datagrid('load',data);
				}
			});
		}
	</script>