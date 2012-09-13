<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div style="padding:10px">
	<div class="subtitle" style="border-bottom:1px solid #ccc">请输入查询参数：</div>
	<div style="padding:10px 0 10px 30px">
		<table cellpadding="0" cellspacing="2">
			<tr>
				<td>参数名称：</td>
				<td><input name="argument.name" class="query" style="width:200px"></input></td>
			</tr>
			<tr>
				<td>参数值：</td>
				<td><input name="argument.value" class="query" style="width:200px"></input></td>
			</tr>
			<tr>
				<td>备注：</td>
				<td><input name="argument.memo" class="query" style="width:200px"></input></td>
			</tr>
		</table>
	</div>
</div>