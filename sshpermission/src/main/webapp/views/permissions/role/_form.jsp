<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div style="padding:20px">
	<table cellpadding="0" cellspacing="0" class="form-table">
		<tr>
			<td>角色名称：</td>
			<td><input id="name" name="role.name" class="easyui-validatebox" style="width:200px" required="true"></input>
			<input id="id" name="role.id" type="hidden"></input>
			</td>
		</tr>
		<tr>
			<td>英文名称：</td>
			<td><input id="enname" name="role.enname" class="easyui-validatebox" style="width:200px" ></input></td>
		</tr>
		<tr>
			<td>角色状态：</td>
			<td><input id="status" class="easyui-combobox" name="role.status" style="width:200px" required="true" readonly="true"
					url="<c:url value='/permissions/role/outDicJsonByNicknameRole.tg?nickName=status'/>"
					valueField="value" textField="name"></input></td>
		</tr>
		<tr>
			<td>排序：</td>
			<td><input id="orderid" name="role.orderid" class="easyui-validatebox" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>备注：</td>
			<td><textarea id="memo" name="role.memo" class="e-input" style="height:200px;width:200px"></textarea></td>
		</tr>
		
	</table>
</div>