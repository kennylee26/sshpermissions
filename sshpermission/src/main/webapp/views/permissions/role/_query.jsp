<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div style="padding:10px">
	<div class="subtitle" style="border-bottom:1px solid #ccc">请输入查询参数：</div>
	<div style="padding:10px 0 10px 30px">
		<table cellpadding="0" cellspacing="2">
			<tr>
			<td>角色名称：</td>
			<td><input name="role.name" class="easyui-validatebox query" style="width:200px" required="true"></input>
			</td>
		</tr>
		<tr>
			<td>英文名称：</td>
			<td><input name="role.enname" class="easyui-validatebox query" style="width:200px" ></input></td>
		</tr>
		<tr>
			<td>角色状态：</td>
			<td><select class="easyui-combobox query" name="role.status" style="width:200px" required="true" readonly="true">
									<option value="0">不选</option>
									<option value="1">启用</option>
									<option value="2">禁用</option>
									<option value="3">冻结</option>
								</select></td>
		</tr>
		<tr>
			<td>排序：</td>
			<td><input name="role.orderid" class="easyui-validatebox query" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>备注：</td>
			<td><textarea name="role.memo" class="e-input query" style="height:40px;width:200px"></textarea></td>
		</tr>
		
		</table>
	</div>
</div>