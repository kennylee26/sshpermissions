<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%
	//组的id
	String id = "ual".equals(request.getParameter("id")) ? null:request.getParameter("id");
%>

<head>
	<%
		if(null == id){
	%>
	<jsp:include page="head.jsp"></jsp:include>
	<%
		}else{
	%>
	<jsp:include page="head.jsp">
		<jsp:param value="<%=id %>" name="id"/>
	</jsp:include>
	<%		
		}
	%>
</head>
<body style= "margin:0px;">
	<form id="groupForm">
		<input type="hidden" id="sid" name="id" value=""/>
		<input type="hidden" id="groupid" name="groupid" value=""/>
	</form>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false">
			<div class="toolbar">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newItem()">新增组</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editItem()">修改组</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="delItem()">删除组</a>
				<a href="#" class="easyui-linkbutton"plain="true" onclick="lookItem()">查看组信息</a>
				<%
					if(null != id){
				%>
				<a href="#" class="easyui-linkbutton"plain="true" onclick="addUserItem()">添加用户</a>
				<a href="#" class="easyui-linkbutton"plain="true" onclick="delUserItem()">删除组内用户</a>
				<a href="#" class="easyui-linkbutton"plain="true" onclick="groupToRole()">组授权角色</a>
				<a href="#" class="easyui-linkbutton"plain="true" onclick="lookGroupHaveRole()">查看组拥有的角色</a>
				<a href="#" class="easyui-linkbutton"plain="true" onclick="removeGroupHaveRole()">移除组拥有的角色</a>
				<%
					}
				%>
				
			</div>
			<div id="groupMemberMessage" class="subtitle" style="text-align: center;">组内成员信息</div>
		</div>
		<div region="center" border="false">
			<table id="t-groups" class="easyui-datagrid"
					url="<c:url value='/user/getItemsUser.tg'/>?id=<%= null != id ? id:"" %>"
					singleSelect="true" pagination="true"
					border="false" fit="true">
				<thead>
					<tr>
						<th field="user.name" width="100" >用户名称</th>
						<th field="user.position" width="50">职位</th>
						<th field="user.age" width="50">年龄</th>
						<th field="user.sex" width="50">性别</th>
						<th field="user.education" width="100">教育情况</th>
						<th field="user.address" width=100>地址</th>
						<th field="user.phone" width="100">电话</th>
						<th field="user.email" width="150">邮箱</th>
						<th field="user.memo" width="300">备注</th>
					</tr>
				</thead>
			</table>
		</div>
		
	</div>
	
	
	<div id="rolGroup" style="width:250px;height:600px;" class="easyui-dialog" closed="true" modal="true" buttons="#role-buttons">
		<div>
			<h4 style="text-align: center;">可选角色</h4>
			<form id="selectRole">
				<input type="hidden" value="" name="groupid" id="gsid"/>
				<table style="border-color: #8DB2E3 ;" >
					<thead>
						<tr>
							<td>
							<input type="checkbox" name="all" onclick="clickeAll(this);"/></td> 
							<td>角色名称</td><td>&nbsp;</td><td>备注</td>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</form>
		</div>
		<div id="role-buttons" style="text-align:center;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="">确定</a>
		</div>
	</div>
	
	<div id="adduser" style="width:400px;height:600px;" class="easyui-dialog" closed="true" modal="true" buttons="#dlg-buttons">
		<div style="padding-left:40px;">
		<h4 style="text-align: center;">可以添加的用户</h4>
			<form id="self">
				<input type="hidden" id="gid" name="groupid" value=""/>
				<table border="0.5">
					<thead>
						<tr>
							<td>
							<input type="checkbox" name="selectall" onclick="selectAll(this);"/>
								<!-- <input type="button" id="selectall" value="全选" onclick="selectAll();"/></td> -->
							<td>用户名称</td><td>电话</td><td>邮箱</td>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</form>
		</div>
		<div id="dlg-buttons" style="text-align:center;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="addUsers()">确定</a>
		</div>
	</div>
	
	<div id="dlg" style="width:600px;height:400px;"
			class="easyui-dialog" closed="true" modal="true" buttons="#dlg-buttons">
		<div style="padding-left:150px;padding-top:40px;">
			<div style="float:center">
				<form id="myform" method="post" style="margin:0;padding:0">
					<table>
						<tr>
							<td style="width:80px">组名称</td>
							<td><input type="text" id="name" class="easyui-validatebox e-input" name="group.name" required="true"></input></td>
						</tr>
						<tr>
							<td>组英文名称</td>
							<td>
								<input type="text" id="enname" class="e-input" name="group.enName" >
							</td>
						</tr>
						<tr>
							<td>组类别</td>
							<td>
								<input type="text" id="grouptype" class="e-input" name="group.groupType" >
							</td>
						</tr>
						
						<tr>
							<td>所属组</td>
							<td>
								<input id="parentId" name="group.parentId" class="easyui-combobox" style="width:200px" url="<c:url value="/group/allGroup.tg"/>" valueField="id" textField="name" ></input>
							</td>
						</tr>
						
						<tr>
							<td>状态</td>
							<td>
								<select class="easyui-combobox" id="status" name="group.status" panelHeight="60" style="width:60px">
									<option selected="selected" value="1">启用</option>
									<option value="0">禁用</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<td>排序</td>
							<td><input type="text" id="orderid" class="e-input" name="group.orderId"></input></td>
						</tr>
						<tr>
							<td>备注</td>
							<td><input type="text" id="memo" class="e-input" name="group.memo"></input></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>
								<input type="hidden" id="id" name="group.id" value="1">
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
	
	<div id="look" style="width:600px;height:400px;"
			class="easyui-dialog" closed="true" modal="true" buttons="#dlg-buttons">
		<div style="padding-left:150px;padding-top:40px;">
			<div style="float:center">
				<form id="myform" method="post" style="margin:0;padding:0">
					<table>
						<tr>
							<td style="width:80px">组名称</td>
							<td><input readonly type="text" id="lname" class="easyui-validatebox e-input" name="group.name" required="true"></input></td>
						</tr>
						<tr>
							<td>组英文名称</td>
							<td>
								<input readonly type="text" id="lenname" class="e-input" name="group.enName" >
							</td>
						</tr>
						<tr>
							<td>组类别</td>
							<td>
								<input readonly type="text" id="lgrouptype" class="e-input" name="group.groupType" >
							</td>
						</tr>
						
						<tr>
							<td>状态</td>
							<td>
							<input readonly type="text" id="lstatus" class="e-input" name="group.parentId" >
							</td>
						</tr>
						
						<tr>
							<td>排序</td>
							<td><input readonly type="text" id="lorderid" class="e-input" name="group.orderId"></input></td>
						</tr>
						<tr>
							<td>备注</td>
							<td><input readonly type="text" id="lmemo" class="e-input" name="group.memo"></input></td>
						</tr>
					</table>
				</form>
			</div>
			<div style="clear:both"></div>
		</div>
		<div id="dlg-buttons" style="text-align:center;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#look').dialog('close')">关闭</a>
		</div>
	</div>
	
</body>
