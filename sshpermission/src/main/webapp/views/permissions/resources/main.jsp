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
        	});
        </script>
	<script type="text/javascript">
		//----------------------------------------------用来格式化显示状态，是否打开和是否叶子
		var status;
		var open;
		var leaf;
		$.getJSON("<c:url value='/permissions/resources/outDicJsonByNicknameResources.tg?nickName=status'/>", function(json){
			status=json;
		});
		$.getJSON("<c:url value='/permissions/resources/outDicJsonByNicknameResources.tg?nickName=open'/>", function(json){
			open=json;
		});
		$.getJSON("<c:url value='/permissions/resources/outDicJsonByNicknameResources.tg?nickName=leaf'/>", function(json){
			leaf=json;
		});
		function statusFormatter(value){
			for(var i=0; i<status.length; i++){
				if (status[i].value == value) return status[i].name;
			}
			return value;
		}
		function openFormatter(value){
			for(var i=0; i<open.length; i++){
				if (open[i].value == value) return open[i].name;
			}
			return value;
		}
		function leafFormatter(value){
			for(var i=0; i<leaf.length; i++){
				if (leaf[i].value == value) return leaf[i].name;
			}
			return value;
		}
		//---------------------------------------------------------------------
		$(function(){
			init();
			/* $('#dt-resources').datagrid({onDblClickRow:function dblClickRow(rowIndex,rowData){
				//alert(rowIndex+"-"+JSON.stringify(rowData));
				//$('#detailform').form('load',rowData);
				//$('#detail').dialog('setTitle','更新角色信息').dialog('open');
				$('#myform').formid('loadit',rowData);
				$.each($('#myform input'),function(i){
    					$(this).attr("readonly","true");
    				});
    				$("#dlg-buttons a:first-child").hide();
				$('#dlg').dialog('setTitle','查看资源信息').dialog('open');
			}}); */
		});
		function init(){
			$('#dlg').dialog({
				onOpen:function(){
					$('#dt-resources').datagrid('resize');
				}
			});
			$('#tree').tree({
				onClick: function(node){
					//$('#t-resources').datagrid('reload', {"id":node.id});
					var queryParams = $('#dt-resources').datagrid('options').queryParams;
					queryParams.treeId=node.id;
					
					$('#dt-resources').datagrid('loadData', {total:0,rows:[]});
					$('#dt-resources').datagrid("reload");
				}
			});
			$('input[id="systemId"]').combobox({
				onSelect:function(node){
					loadTree();
				}
			});
			
		}
		function loadTree(){
			var systemid = $('input[id="systemId"]').combobox("getValue");
			$('input[id="parentId"]').combotree({
				url:"<c:url value='/permissions/resources/getTreeResources.tg'/>?systemId="+systemid
			});
			$('input[id="parentId"]').combotree('reload');
		}
		function advanceQuery(){
			showQueryDialog({
				width:400,
				height:430,
				form:'<c:url value="/views/permissions/resources/_query.jsp"/>',
				callback:function(data){
					$('#dt-resources').datagrid('loadData', {total:0,rows:[]});
					$('#dt-resources').datagrid('load',data);
				}
			});
		}
		function back(){
			$('#dt-resources').datagrid('loadData', {total:0,rows:[]});
			$('#dt-resources').datagrid('load',{});
		}
		var url;
		function newItem(){
			url = '<c:url value="/permissions/resources/saveResources.tg"/>';
			$('input[id="parentId"]').combotree({
				url:"<c:url value='/permissions/resources/getTreeResources.tg'/>?systemId="
			});
			$('input[id="parentId"]').combotree('loadData', {total:0,rows:[]});
			$('#myform').form('clear');
			$.each($('#myform input'),function(i){
				$(this).removeAttr("readonly");
			});
			$("#dlg-buttons a:first-child").show();
			$('#dlg').dialog('setTitle','新增系统资源').dialog('open');
		}
		function editItem(){
			var row = $('#dt-resources').datagrid('getSelected');
			if (row){
				//编辑之前加载上级资源列表
				$('input[id="parentId"]').combotree({
					url:"<c:url value='/permissions/resources/getTreeResources.tg'/>?id="+row["id"]+"&systemId="+row["systemId"]
				});
				//设置提交的url
				url = '<c:url value="/permissions/resources/updateResources.tg"/>?';
				//因为row中的.为特殊字符，需要转义才能让easyui自动赋值，该函数返回类型是字符串
				//row = JSON.stringify(row).replace(/\./g,"\\\\.");
				//$('#myform').form('load',eval('('+row+')'));
				$('#myform').formid('loadit',row);
				
				$('#dlg').dialog('setTitle','修改系统资源').dialog('open');
			} else {
				$.messager.show({
					title:'提示',
					msg:'请先选择系统资源，再进行修改。'
				});
			}
		}
		function loadAllTrees(){
			url = '<c:url value="/permissions/resources/getAllHTMLTreesResources.tg"/>?';
			 $('#myform').form('submit',{
					url:url,
					onSubmit:function(){return true;},
					success:function(data){
						$("#allTrees").html("");
						$("#allTrees").append($(data));
						$("#tree").tree();
						$('#tree').tree({
							onClick: function(node){
								//$('#t-resources').datagrid('reload', {"id":node.id});
								var queryParams = $('#dt-resources').datagrid('options').queryParams;
								queryParams.treeId=node.id;
								
								$('#dt-resources').datagrid('loadData', {total:0,rows:[]});
								$('#dt-resources').datagrid("reload");
							}
						});
					}
				});
		}
		function removeItem(){
			var row = $('#dt-resources').datagrid('getSelected');
			if (row){
				$.messager.confirm('确认','确定要删除该记录?',function(result){
					if(result){
						url = '<c:url value="/permissions/resources/deleteResources.tg"/>?id='+row["id"];
						 $('#myform').form('submit',{
								url:url,
								onSubmit:function(){return true;},
								success:function(data){
									$('#dt-resources').datagrid('reload');
									$("#tree").tree('reload');
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
				});
			} else {
				$.messager.show({
					title:'提示',
					msg:'请先选择资源记录，再进行修改。'
				});
			}
		}
					
		function saveItem(){
			var data = $('#myform').form('submit',{
				url:url,
				onSubmit:function(){return $(this).form('validate');},
				success:function(data){
					$('#dlg').dialog('close');
					$('#dt-resources').datagrid('reload');
					$("#tree").tree('reload');
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
	</script>
	
    </head>
	<body style="margin:0;padding:0;height:100%;overflow:hidden;background:#F2FBFF">
  			<div id="mainlayout" class="easyui-layout" fit="true">
			<div region="north" border="false" >
				<div class="toolbar">
					<table cellpadding="0" cellspacing="0" style="width:95%;height:50px;" fit="true">
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
			<div region="west" border="false" style="border-right:1px solid #92B7D0;width:150px;padding:5px;">
				<%-- <ul id="tree" url="<c:url value='/permissions/resources/getAllTreesResources.tg'/>"></ul> --%>
				<div id="allTrees">
					<%=request.getAttribute("allTrees") %>
				</div>
			</div>
			<div region="center" border="false">
				<table id="dt-resources" class="easyui-datagrid"
						url="<c:url value='/permissions/resources/getItemsResources.tg'/>"
						fit="true" border="false" 
						pagination="true" 
						singleSelect="true" rownumbers="true" disabled="true" >
					<thead>
						<tr>
							<th field="name" width="100"  sortable="true">资源名称</th>
							<th field="enname" width="100" sortable="true">英文名称</th>
							<th field="parentName" width="100" >上级资源</th>
							<th field="systemName" width="100" sortable="true">系统名称</th>
							<th field="resourcetype" width="100" sortable="true">资源类型</th>
							<th field="link" width="100" sortable="true">链接</th>
							<th field="icon" width="100" sortable="true">图标</th>
							<th field="iconopen" width="70" sortable="true" formatter="openFormatter">打开图标</th>
							<th field="isopen" width="70" sortable="true" formatter="openFormatter">是否打开</th>
							<th field="isleaf" width="70" sortable="true" formatter="leafFormatter">是否节点</th>
							<th field="status" width="50" sortable="true" formatter="statusFormatter">状态</th>
							<th field="orderid" width="50" sortable="true">排序</th>
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
					<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveItem()">保存</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
				</div>
			</div>
		</div>
</body>