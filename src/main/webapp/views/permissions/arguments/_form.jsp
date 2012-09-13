<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div style="padding:20px">
	<table cellpadding="0" cellspacing="0" class="form-table">
		<tr>
			<td>参数名称：</td>
			<td><input id="name" name="argument.name" class="easyui-validatebox" style="width:200px" required="true"></input>
			<input id="id" name="argument.id" type="hidden"></input>
			</td>
		</tr>
		<tr>
			<td>参数值：</td>
			<td><input id="value" name="argument.value" class="easyui-validatebox" style="width:200px" required="true"></input></td>
		</tr>
		<tr>
			<td>排序：</td>
			<td><input id="orderid" name="argument.orderid" class="easyui-validatebox" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>备注：</td>
			<td><input id="memo" name="argument.memo" style="width:200px"></input></td>
		</tr>
		
	</table>
</div>