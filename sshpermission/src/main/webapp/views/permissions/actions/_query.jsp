<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div style="padding:10px">
	<div class="subtitle" style="border-bottom:1px solid #ccc">请输入查询参数：</div>
	<div style="padding:10px 0 10px 30px">
		<table cellpadding="0" cellspacing="2">
			<tr>
			<td>操作名称：</td>
			<td><input name="action.name" class="query" style="width:200px" ></input>
			</td>
		</tr>
		<tr>
			<td>英文名称：</td>
			<td><input name="action.enname" class="query" style="width:200px" ></input></td>
		</tr>
		<tr>
			<td>操作方法：</td>
			<td><input name="action.methodName" class="query" style="width:200px" ></input></td>
		</tr>
		<tr>
			<td>图标：</td>
			<td><input name="action.icon" class="query" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>状态：</td>
			<td><input class="easyui-combobox query" name="action.status" style="width:200px" 
			 readonly="true"
			 url="<c:url value='/permissions/actions/outDicJsonByNicknameActions.tg?nickName=status'/>"
					valueField="value" textField="name">
								</input></td>
		</tr>
		<tr>
			<td>排序：</td>
			<td><input name="action.orderid" class="query" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>备注：</td>
			<td><textarea name="action.memo" class="query" style="height:40px;width:200px"></textarea></td>
		</tr>
		</table>
	</div>
</div>