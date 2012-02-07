<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<head>
  <jsp:include page="head.jsp"></jsp:include>
</head>
<body style= "margin:0px;">
	<form method="post" id="logform" >
		<input type="hidden" name="id" id="logid" value="1"/>
	</form>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false" class="toolbar">
				<table style="width:100%">
					<tr>
						<td style="text-align:center">
							&nbsp;&nbsp;日志信息列表&nbsp;&nbsp;
						</td>
						<td style="text-align:right">
							<a href="javascript:lookLog()" class="easyui-linkbutton" plain="true">查看日志</a>
							<a href="javascript:advanceQuery()" class="easyui-linkbutton" plain="true">高级查询</a>
						</td>
					</tr>
				</table>
		</div>
		
		<div region="center" border="false">
			<table id="dt-logs" class="easyui-datagrid"
					url="<c:url value='/log/getItemsLog.tg'/>"
					fit="true" border="false" 
					pagination="true"
					singleSelect="true" rownumbers="true">
				<thead>
					<tr>
						<th field="opertype" width="80" sortable="true">操作类型</th>
						<th field="content" width="100" sortable="true">操作内容</th>
						<th field="cost" width="80" sortable="true">所耗时间</th>
						<th field="createip" width="80">操作用户ip</th>
						<th field="createuser" width="80">操作用户</th>
						<th field="createdate" width="80">操作时间</th>
						<th field="memo" width="200">备注</th>
					</tr>
				</thead>
			</table>
		</div>
		
	</div>
	<div id="dlg" class="easyui-dialog" style="width:500px;height:350px;"closed="true" modal="true" buttons="#dlg-buttons">
		<div style="padding-left:150px;padding-top:40px;">
			<div style="float:center">
				<form id="myform" method="post" style="margin:0;padding:0">
					<table>
						<tr>
							<td style="width:80px">操作类型</td>
							<td><input readonly type="text" id="opertype" name="logs.opertype"></input></td>
						</tr>
						<tr>
							<td>操作内容</td>
							<td>
								<input readonly type="text" id="content" name="logs.content" />
							</td>
						</tr>
						<tr>
							<td>所耗时间</td>
							<td>
								<input readonly type="text" id="cost" name="logs.cost" >
							</td>
						</tr>
						<tr>
							<td>操作用户ip</td>
							<td><input readonly type="text" id="createip" name="logs.createip"></input></td>
						</tr>
						<tr>
							<td>操作用户</td>
							<td><input readonly type="text" id="createuser" name="logs.createuser"></input></td>
						</tr>
						<tr>
							<td>操作时间</td>
							<td><input readonly type="text" id="createdate" name="logs.createdate"></input></td>
						</tr>
						
						<tr>
							<td>备注</td>
							<td>
								<input readonly type="text" id="memo" name="logs.memo"></input>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div style="clear:both"></div>
		</div>
		<div id="dlg-buttons" style="text-align:center;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
		</div>
	</div>
</body>