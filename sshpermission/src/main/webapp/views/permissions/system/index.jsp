<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://com.tgyt.com.cn/tag/easyui" prefix="tagEasyui" %>
<head>
	 <jsp:include page="head.jsp"></jsp:include>
</head>
<body style= "margin:0px;">
	<form id="sysform" method="post">
		<input type="hidden" id="sysId" name="id" value=""/>
	</form>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false">
			<div class="subtitle">系统信息</div>
			<div class="toolbar">
				<tgEasyui:easyuiButton iconCls="icon-add" method="newItem()" permission="system:add" operationName="新增"/>
				<tgEasyui:easyuiButton iconCls="icon-edit" method="editItem()" permission="system:modify" operationName="修改"/>
				<tgEasyui:easyuiButton iconCls="icon-cancel" method="delItem()" permission="system:delete" operationName="删除"/>
				<tgEasyui:easyuiButton iconCls="icon-search" method="advanceQuery()" permission="system:query" operationName="查询常量"/>
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newItem()">新增系统</a> -->
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editItem()">修改系统</a> -->
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="delItem()">删除系统</a> -->
<!-- 				<a href="javascript:advanceQuery()" class="easyui-linkbutton"plain="true">查询系统</a> -->
			</div>
		</div>
		
		<div region="center" border="false">
			<table id="t-systems" class="easyui-datagrid"
					url="<c:url value='/sys/getItemsMessage.tg'/>"
					singleSelect="true" pagination="true"
					border="false" fit="true">
				<thead>
					<tr>
						<th field="name" width="100" sortable="true">系统名称</th>
						<th field="ename" width="100" sortable="true">系统英文名称</th>
						<th field="contextPath" width="100">上下文</th>
						<th field="tablePrefix" width="100">表名前缀</th>
						<th field="logo" width="100">系统标志</th>
						<th field="order" width=50>排序</th>
						<th field="status" width="50" formatter="statusFormatter">状态</th>
						<th field="version" width="50">版本</th>
						<th field="builddate" width="100" sortable="true">创建日期</th>
						<th field="memo" width="300">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
	<div id="dlg" style="width:600px;height:400px;"
			class="easyui-dialog" closed="true" modal="true" buttons="#dlg-buttons">
		<div style="padding-left:150px;padding-top:40px;">
			<div style="float:center">
				<form id="myform" method="post" style="margin:0;padding:0">
					<table>
						<tr>
							<td style="width:80px">系统名称</td>
							<td><input type="text" id="name" class="easyui-validatebox e-input" name="system.name" required="true"></input></td>
						</tr>
						<tr>
							<td>系统英文名称</td>
							<td>
								<input type="text" id="ename" class="e-input" name="system.ename" >
							</td>
						</tr>
						<tr>
							<td>上下文</td>
							<td>
								<input type="text" id="contextPath" class="e-input" name="system.contextPath" >
							</td>
						</tr>
						<tr>
							<td>表名前缀</td>
							<td><input type="text" id="tablePrefix" class="e-input" name="system.tablePrefix"></input></td>
						</tr>
						<tr>
							<td>系统标志</td>
							<td><input type="text" id="logo" class="e-input" name="system.logo"></input></td>
						</tr>
						<tr>
							<td>排序</td>
							<td><input type="text" id="order" class="e-input" name="system.order"></input></td>
						</tr>
						<tr>
							<td>状态</td>
							<td>
								<input id="status" class="easyui-combobox"  name="system.status" style="width:200px" 
									required="true" readonly="true" 
									url="<c:url value='/permissions/actions/outDicJsonByNicknameActions.tg?nickName=status'/>"
									valueField="value" textField="name">
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input type="hidden" id="builddate" name="system.builddate"></input>
							</td>
						</tr>
						<tr>
							<td>版本</td>
							<td>
								<input type="text" id="version" class="e-input" name="system.version"></input>
							</td>
						</tr>
						<tr>
							<td>备注</td>
							<td><input type="text" id="memo" class="e-input" name="system.memo"></input></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>
								<input type="hidden" id="icon" name="system.icon" value="/u/images"><input type="hidden" id="id" name="system.id" value="1">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div style="clear:both"></div>
		</div>
		<div id="dlg-buttons" style="text-align:center;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveItem()">保存</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
		</div>
	</div>
	
</body>
