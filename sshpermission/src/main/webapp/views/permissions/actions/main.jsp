<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
            	$('#dt-actions').datagrid({onDblClickRow:function dblClickRow(rowIndex,rowData){
    				//alert(rowIndex+"-"+JSON.stringify(rowData));
    				//$('#detailform').form('load',rowData);
    				//$('#detail').dialog('setTitle','更新角色信息').dialog('open');
    				$('#myform').formid('loadit',rowData);
    				$.each($('#myform input'),function(i){
    					$(this).attr("readonly","true");
    				});
    				$("#dlg-buttons a:first-child").hide();
    				$('#dlg').dialog('setTitle','查看操作信息').dialog('open');
    			}});
        	});
        </script>
	<script type="text/javascript">
		function advanceQuery(){
			
			showQueryDialog({
				width:350,
				height:30,
				form:'<c:url value="/views/permissions/actions/_query.jsp"/>',
				callback:function(data){
					//$('#q').val(data.name);
					$('#dt-actions').datagrid('loadData', {total:0,rows:[]});
					$('#dt-actions').datagrid('load',data);
				}
			});
		}
		function back(){
			$('#dt-actions').datagrid('loadData', {total:0,rows:[]});
			$('#dt-actions').datagrid('load',{});
		}
		//------------------------------用来格式化显示状态名称
		var status;
		$.getJSON("<c:url value='/permissions/actions/outDicJsonByNicknameActions.tg?nickName=status'/>", function(json){
			status=json;
		});
		function statusFormatter(value){
			for(var i=0; i<status.length; i++){
				if (status[i].value == value) return status[i].name;
			}
			return value;
		}
		//---------------------------------------------------------------------
		
		var url;
		function newItem(){
			url = '<c:url value="/permissions/actions/saveActions.tg"/>';
			$('#myform').form('clear');
			$.each($('#myform input'),function(i){
				$(this).removeAttr("readonly");
			});
			$("#dlg-buttons a:first-child").show();
			$('#dlg').dialog('setTitle','新增操作').dialog('open');
		}
		function editItem(){
			var row = $('#dt-actions').datagrid('getSelected');
			if (row){
				url = '<c:url value="/permissions/actions/updateActions.tg"/>?';
				//row = JSON.stringify(row).replace(/\./g,"\\\\.");
				//$('#myform').form('load',eval('('+row+')'));
				$('#myform').formid('loadit',row);
				$.each($('#myform input'),function(i){
					$(this).removeAttr("readonly");
				});
				$("#dlg-buttons a:first-child").show();
				$('#dlg').dialog('setTitle','修改操作').dialog('open');
			} else {
				$.messager.show({
					title:'提示',
					msg:'请先选择操作，再进行修改。'
				});
			}
		}
		function removeItem(){
			var row = $('#dt-actions').datagrid('getSelected');
			if (row){
				if(confirm('确定要删除？')){
					url = '<c:url value="/permissions/actions/deleteActions.tg"/>?id='+row["id"];
					 $('#myform').form('submit',{
							url:url,
							onSubmit:function(){return true;},
							success:function(data){
								$('#dt-actions').datagrid('reload');
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
			} else {
				$.messager.show({
					title:'提示',
					msg:'请先选择操作，再进行修改。'
				});
			}
		}
		function saveItem(){
			var data = $('#myform').form('submit',{
				url:url,
				onSubmit:function(){return $(this).form('validate');},
				success:function(data){
					$('#dlg').dialog('close');
					$('#dt-actions').datagrid('reload');
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
	</script>
    </head>
	<body style="margin:0;padding:0;height:100%;overflow:hidden;background:#F2FBFF">
		<div id="mainlayout" class="easyui-layout" fit="true">
			<div region="north" border="false">
				<div class="toolbar">
					<table cellpadding="0" cellspacing="0" style="width:95%" >
						<tr>
							<td>
								<a href="javascript:newItem()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
								<a href="javascript:editItem()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
								<a href="javascript:removeItem()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
								<a href="javascript:back()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
							</td>
							<td style="text-align:right">
								<a href="javascript:advanceQuery()" class="easyui-linkbutton" plain="true">高级查询</a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div region="center" border="false">
				<table id="dt-actions" class="easyui-datagrid"
						url="<c:url value='/permissions/actions/getItemsActions.tg'/>"
						fit="true" border="false" 
						pagination="true"
						singleSelect="true" rownumbers="true" fit="true">
					<thead>
						<tr>
							<th field="name" width="150" sortable="true">操作名称</th>
							<th field="enname" width="150" sortable="true">英文名称</th>
							<th field="methodName" width="150" sortable="true">操作方法</th>
							<th field="icon" width="150" >图标</th>
							<th field="status" width="150" sortable="true" formatter="statusFormatter">状态</th>
							<th field="orderid" width="50" sortable="true">排序</th>
							<th field="memo" width="350">备注</th>
						</tr>
					</thead>
				</table>
			</div>
		<div id="dlg" class="easyui-dialog" style="width:350px;height:300px;"
				closed="true" modal="true" buttons="#dlg-buttons">
			<form id="myform" method="post">
				<jsp:include page="_form.jsp"></jsp:include>
			</form>
			<div id="dlg-buttons" style="text-align:center">
				<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveItem()">保存</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
			</div>
		</div>
	</div>
</body>