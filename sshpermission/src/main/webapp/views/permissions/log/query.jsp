<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div>
<div class="subtitle" style="border-bottom:1px solid #ccc">请输入查询参数：</div>
<div style="padding:10px 0 10px 30px">
		<table>
			<tr>
				<td style="width:80px">操作类型</td>
					<td><input type="text" class="query" name="logs.opertype"></input></td>
				</tr>
				<tr>
					<td>操作用户</td>
					<td><input type="text" class="query" name="logs.createuser"></input></td>
				</tr>
				
				<tr>
					<td>操作日期</td>
					<td><input type="text" class="easyui-datebox e-input query" name="logs.createdate"></input></td>
				</tr>
				
			</table>
	</div>
</div>

	