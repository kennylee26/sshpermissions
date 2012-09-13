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
	<style type="text/css">
		.e-input{
			width:198px;
			border:1px solid #A4BED4;
			height:18px;
			line-height:18px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$('#t-dictionarys').datagrid({onDblClickRow:function dblClickRow(rowIndex,rowData){
				//alert(rowIndex+"-"+JSON.stringify(rowData));
				//$('#detailform').form('load',rowData);
				//$('#detail').dialog('setTitle','更新角色信息').dialog('open');
				$('#myform').formid('loadit',rowData);
				$.each($('#myform input'),function(i){
					$(this).attr("readonly","true");
				});
				$("#dlg-buttons a::first-child").hide();
				$('#dlg').dialog('setTitle','查看农友信息').dialog('open');
			}});
			
		});
	
	
		var actionUrl;
		function newItem(){
			$('#dlg').dialog('setTitle', '填写系统常量').dialog('open');
			$('#myform').form('clear');
			$.each($('#myform input'),function(i){
				$(this).removeAttr("readonly");
			});
			$("#dlg-buttons a::first-child").show();
			actionUrl = '<c:url value="/dictionary/saveDictionary.tg"/>';
		}
		function saveItem(){
			$('#myform').form('submit', {
				url:actionUrl,
				onSubmit: function(){
						return $('#myform').form('validate');
				},
				success: function(data){
					$('#dlg').dialog('close');
					$('#t-dictionarys').datagrid('reload');
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
			var row = $('#t-dictionarys').datagrid('getSelected');
			if (row){
				$.each($('#myform input'),function(i){
					$(this).removeAttr("readonly");
				});
				$("#dlg-buttons a::first-child").show();
				$('#dicId').val(row.id);
				actionUrl = '<c:url value="/dictionary/findDictionary.tg"/>';
				$('#dicform').formid('loadit',row);
				$('#dlg').dialog('setTitle', '修改常量信息').dialog('open');
				actionUrl = '<c:url value="/dictionary/updateDictionary.tg"/>';
			}else{
				alert('请选则一常量修改');
			}
		}
		
		function delItem(){
			var row = $('#t-dictionarys').datagrid('getSelected');
			if(row){
				$('#dicId').val(row.id);
				actionUrl = '<c:url value="/dictionary/delDictionary.tg"/>';
				$('#dicform').form('submit', {
					url:actionUrl,
					success: function(data){
						$('#dlg').dialog('close');
						$('#t-dictionarys').datagrid('reload');
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
				alert('请选择要删除的常量！谢谢'); 
			}
		}
		
		function advanceQuery(){
			showQueryDialog({
				width:350,
				height:230,
				form:'<c:url value="/views/permissions/dictionary/query.jsp"/>',
				callback:function(data){
					$('#t-dictionarys').datagrid('load',data);
				}
			});
		}
	</script>