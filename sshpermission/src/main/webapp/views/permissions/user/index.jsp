<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://com.tgyt.com.cn/tag/easyui" prefix="tgEasyui" %>
<head>
	 <jsp:include page="head.jsp"></jsp:include>
</head>
<body style= "margin:0px;">
	<form id="userform" method="post">
		<input type="hidden" id="userId" name="id" value=""/>
	</form>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false">
			<div class="toolbar">
				<table cellpadding="0" cellspacing="0" style="width:95%;" fit="true">
							<tr>
								<td style="text-align:left">
				<tgEasyui:easyuiButton iconCls="icon-add" method="newItem()" permission="users:add" operationName="新增"/>
				<tgEasyui:easyuiButton iconCls="icon-edit" method="editItem()" permission="users:modify" operationName="修改"/>
				<tgEasyui:easyuiButton iconCls="icon-cancel" method="delItem()" permission="users:delete" operationName="删除"/>
 				<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newItem()">新增用户</a> 
 				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editItem()">修改用户</a> 
 				<a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="delItem()">删除用户</a> 
 				<a href="javascript:advanceQuery()" class="easyui-linkbutton"plain="true">查询用户</a>  -->
								</td>
								<td style="text-align:right;">
				<tgEasyui:easyuiButton iconCls="icon-search" method="advanceQuery()" permission="users:query" operationName="高级查询"/>
								</td>
							</tr>
					</table>
			</div>
		</div>
		
		<div region="center" border="false">
			<table id="t-users" class="easyui-datagrid"
					url="<c:url value='/user/getPageUser.tg'/>"
					singleSelect="true" pagination="true"  rownumbers="true"
					border="false" fit="true">
				<thead>
					<tr>
						<th field="name" width="100">用户名称</th>
						<th field="jobmember" width="100">工号</th>
						<th field="position" width="50">职位</th>
						
						<th field="age" width="50">年龄</th>
						<th field="sex" width="50" formatter="sexFormatter">性别</th>
						<th field="education" width="100" formatter="educationFormatter">教育情况</th>
						<!-- <th field="address" width=100>地址</th> -->
						<th field="phone" width="100">电话</th>
						<!-- <th field="registerdate" width="100" sortable="true">创建时间</th> -->
						<th field="email" width="150">邮箱</th>
						<th field="memo" width="300">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
	<div id="dlg" style="width:650px;height:420px;"
			class="easyui-dialog" closed="true" modal="true" buttons="#dlg-buttons">
		<form id="myform" method="post" >
			<jsp:include page="form.jsp"></jsp:include>
		</form>
		<div id="dlg-buttons" style="text-align:center;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveItem()">保存</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
		</div>
	</div>
</body>
