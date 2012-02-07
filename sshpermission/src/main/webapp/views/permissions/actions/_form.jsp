<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div style="padding:20px">
	<table cellpadding="0" cellspacing="0" class="form-table">
		<tr>
			<td>操作名称：</td>
			<td><input id="name" name="action.name" class="easyui-validatebox" style="width:200px" required="true"></input>
			<input id="id" name="action.id" type="hidden"></input>
			</td>
		</tr>
		<tr>
			<td>英文名称：</td>
			<td><input id="enname" name="action.enname" class="easyui-validatebox" style="width:200px" required="true"></input></td>
		</tr>
		<tr>
			<td>操作方法：</td>
			<td><input id="methodName" name="action.methodName" class="easyui-validatebox" style="width:200px" ></input></td>
		</tr>
		<tr>
			<td>图标：</td>
			<td><input id="icon" name="action.icon" class="easyui-validatebox" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>状态：</td>
			<td><input id="status" class="easyui-combobox"  name="action.status" style="width:200px" 
					required="true" readonly="true" 
					url="<c:url value='/permissions/actions/outDicJsonByNicknameActions.tg?nickName=status'/>"
					valueField="value" textField="name"></input>
			</td>
		</tr>
		<tr>
			<td>排序：</td>
			<td><input id="orderid" name="action.orderid" class="easyui-validatebox" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>备注：</td>
			<td><textarea id="memo" name="action.memo" class="e-input" style="height:40px;width:200px"></textarea></td>
		</tr>
	</table>
</div>