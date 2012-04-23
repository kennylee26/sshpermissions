<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<head>
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
		$(function(){
			init();
		});
		function init(){
			$('#mytree').tree({
				onClick: function(node){
					//$('#t-resources').datagrid('reload', {"id":node.id});
					var queryParams = $('#dt-resources').datagrid('options').queryParams;
					queryParams.treeId=node.id;
					
					$('#dt-resources').datagrid('loadData', {total:0,rows:[]});
					$('#dt-resources').datagrid("reload");
				},
				onContextMenu: function(e, node){
					e.preventDefault();
					$('#mytree').tree('select', node.target);
					$('#mm').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				},
				onDrop: function(target, source, point){
					var targetId = $('#mytree').tree('getNode', target).id;
					$.ajax({
						url: '<c:url value="/workflow/flowtype/dndCFlowTypeTg.tg"/>?id=' + source.id,
						data: {
							targetId: targetId,
							point: point
						},
						dataType: 'json',
						method: 'post',
						success: function(data){
						}
					});
				}
			});
		}
		function expandAll(){
			var node = $('#mytree').tree('getSelected');
			$('#mytree').tree('expandAll', node ? node.target : null);
		}
		function collapseAll(){
			var node = $('#mytree').tree('getSelected');
			$('#mytree').tree('collapseAll', node ? node.target : null);
		}
		
		var actionUrl;
		function createItem(){
			$('#myform').form('clear');
			$('#dlg').dialog('setTitle', '新建').dialog('open');
			var node = $('#mytree').tree('getSelected');
			if (node){
				$('#pname').html(node.text);
				actionUrl = '<c:url value="/workflow/flowtype/saveTypeCFlowTypeTg.tg"/>' + '?parentid=' + node.id;
			} else {
				$('#pname').html('');
				actionUrl = '<c:url value="/workflow/flowtype/saveTypeCFlowTypeTg.tg"/>';
			}
		}
		function editItem(){
			var node = $('#mytree').tree('getSelected');
			if (node){
				var pnode = $('#mytree').tree('getParent', node.target);
				$('#pname').html(pnode ? pnode.text : '');
				$('#myform input[name=name]').val(node.text);
				$('#dlg').dialog('open').dialog('setTitle', '修改');
				actionUrl = '<c:url value="/workflow/flowtype/updateTypeCFlowTypeTg.tg"/>?id=' + node.id;
			}else{
				$.messager.show({
					title:'提示',
					msg:'请先选择问题类别，再进行修改。'
				});
			}
		}
		function saveItem(){
			$('#myform').form('submit', {
				url:actionUrl,
				onSubmit: function(){
					
					return $('#myform').form('validate');
				},
				success:function(data){
					var data = eval('(' + data + ')');
					var node = $('#mytree').tree('getSelected');
					if (node){
						if (data.action == 'append'){
							$('#mytree').tree('append', {
								parent: node.target,
								data: [data]
							});
						} else {
							node.text = data.text;
							$('#mytree').tree('update', node);
						}
					} else {
						$('#mytree').tree('reload');
					}
					$('#dlg').dialog('close');
				}
			});
		}

		function delItem(){
			var node = $('#mytree').tree('getSelected');
			if (node){
				if(confirm('确定要删除' + node.text + '？')){
					actionUrl = '<c:url value="/workflow/flowtype/delteTypeCFlowTypeTg.tg"/>?id=' + node.id;
					$('#myform').form('submit', {
						url:actionUrl,
						onSubmit: function(){return true;},
						success:function(data){
							var data = eval('(' + data + ')');
							if(data.success){
								$.messager.show(
									{
										title:'提示',
										msg:'操作成功！',
										showType:'slide'
									}
								);
								$('#mytree').tree('reload');
							}
							if(data.error){
								$.messager.alert('提示','操作失败！','error');
							}
						}
					});
				}
			}else{
				$.messager.show({
					title:'提示',
					msg:'请先选择问题类别，再进行修改。'
				});
			}
		}
		var i=0;
		function createFlow(){
			var node = $('#mytree').tree('getSelected');
			if(node){
				//只有当第二次新建流程时才调用初始化工作流函数
				if(i>0){
					var swf = window.document.getElementById("tgWorkFlow").contentWindow.findSWF("tgWorkFlow");
					swf.initFlow();
				}
				i++;
				//将当前选中类型ID传给流程设计器
				window.document.getElementById("tgWorkFlow").contentWindow.setParams(node.id);
				$('#dlgflow').dialog('open').dialog('setTitle', '新建流程');
			}else{
				alert("请选择类型");
			}
		}
		function formatTypeResult(value,rowData){
			var c = '<a href="#" onclick="showFlow('+rowData.flowid+','+rowData.typeid+')">查看</a>&nbsp;&nbsp;<a href="#" onclick="flag('+rowData.id+')">标识</a>';
// 			var c = '<img src=\"<c:url value="/icons/update.png"/>\" style="cursor:hand " width="20" height="20" onclick="showFlow('+rowData.flowid+','+rowData.typeid+')"/>&nbsp;&nbsp;<a href="#" onclick="flag('+rowData.id+')">标识</a>';
			return c;
		}
        function showFlow(flowid,typeid){
        	window.document.getElementById("tgWorkFlow").contentWindow.setParams(typeid);
        	//如果k>0，表示不是第一次调用此函数，也就意味着流程设计器已经加载完毕，那么就直接能调用流程设计器的初始化函数
        	if(i>0){
        		var swf = window.document.getElementById("tgWorkFlow").contentWindow.findSWF("tgWorkFlow");
        		swf.initFlow(flowid);
        	}else{
        		//因为流程设计器还未初始化完毕，只能先把当前的流程ID赋值到流程设计器页面
	        	window.document.getElementById("tgWorkFlow").contentWindow.setDeploymentId(flowid);
        	}
        	i++;
			$('#dlgflow').dialog('open').dialog('setTitle', '查看流程');
//         	url = '<c:url value="/tgbpm/businessprocess/getProcessXMLBusinessProcess.tg?deploymentId="/>'+flowid;
// 			 $('#upload').form('submit',{
// 					url:url,
// 					onSubmit:function(){return true;},
// 					success:function(data){
// 						alert(data);
// 					}
// 				});
        }
        function flag(id){
        	url = '<c:url value="/listflow/revertStatusTypeFlowTg.tg?id="/>'+id;
			 $('#upload').form('submit',{
					url:url,
					onSubmit:function(){return true;},
					success:function(data){
						data=eval('('+data+')');
						if(data.success){
							$('#dt-resources').datagrid("reload");
						}else{
							$.messager.alert('提示','修改状态失败');  
						}
					}
				});
        }
	</script>
</head>
<body style="margin:0;padding:0;height:100%;overflow:hidden;background:#F2FBFF">
	<div class="easyui-layout" fit="true">
		<div region="north" border="false">
			<div class="toolbar">
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="createItem()">新增</a>
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="editItem()">修改</a>
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-remove" onclick="delItem()">删除</a>
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="createFlow()">新增流程</a>
			</div>
		</div>
		<div region="west" border="false" style="border-right:1px solid #92B7D0;width:150px;padding:5px;">
			<ul id="mytree" dnd="true" url="<c:url value='/workflow/flowtype/getTreeCFlowTypeTg.tg'/>"></ul>
		</div>
		<div region="center" border="false">
				<table id="dt-resources" class="easyui-datagrid"
						fit="true" border="false"
						url="<c:url value='/listflow/getFlowItemsTypeFlowTg.tg'/>" 
						pagination="true" 
						singleSelect="true" rownumbers="true" disabled="true" >
					<thead>
						<tr>
							<th field="id" align="center"  width="50"  sorta ble="true">序号</th>
							<th field="flowname" align="center"  width="150" sortable="true">流程名称</th>
							<th field="flowdescribe" align="center"  width="250" >流程描述</th>
							<th field="version" align="center"  width="150" sortable="true">流程版本</th>
							<th field="flag"  align="center" width="50">状态</th>
							<th field="memo"  align="center" width="150" formatter="formatTypeResult">操作</th>
						</tr>
					</thead>
				</table> 
			</div>
	</div>
	
	<div id="dlg" style="width:290px;height:140px"
			class="easyui-dialog"
			title="添加" closed="true" modal="true">
		<form id="myform" method="post">
			<table border="0" cellpadding="3" cellspacing="1" bordercolor="#FFFFFF"  bgcolor="#ececec" style="font-size:12px;">
				<tr>
					<td align="right" bgcolor="#ececec">上级类别：</td>
					<td bgcolor="#FFFFFF"><span id="pname" style="width: 200px"></span></td>
				</tr>
				<tr>
					<td align="right" bgcolor="#ececec">类别名称：</td>
					<td bgcolor="#FFFFFF"><input id="typename" type="text" name="typename" class="easyui-validatebox" required="true" style="width: 200px"></input></td>
				</tr>
			</table>
		</form>
		<div style="text-align:center;margin-top:20px;">
			<a href="#" class="easyui-linkbutton" onclick="saveItem()">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="$('#dlg').dialog('close')">关闭</a>
		</div>
	</div>
	
   <div id="dlgflow" style="width:820px;height:540px"
			class="easyui-dialog"
			title="添加" closed="true" modal="true">
        <form id="myforms" method="post">
        <iframe id="tgWorkFlow" src="../designer/tgWorkFlow.jsp" frameBorder=0 style="width:100%;height:500px">
        </iframe>
		</form>
	</div>
	<div id="mm" class="easyui-menu" style="width:120px;">
		<div iconCls="icon-add" onclick="createItem()">新增类型</div>
		<div iconCls="icon-edit" onclick="editItem()">修改类型</div>
		<div iconCls="icon-remove" onclick="delItem()">删除类型</div>
		<div class="menu-sep"></div>
		<div onclick="expandAll()">全部展开</div>
		<div onclick="collapseAll()">全部收缩</div>
	</div>
	<form id="upload" method="post"></form>
</body>
