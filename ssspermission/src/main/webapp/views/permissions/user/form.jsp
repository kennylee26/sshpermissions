<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div style="padding:20px">
	<table cellpadding="0" cellspacing="0" class="form-table">
	<tr>
		<td >用户登录名：</td>
		<td><input type="text" id="logonid" style="width:200px" 
			class="easyui-validatebox e-input" name="user.logonid"
			required="true"></input></td>
		<td style="padding-left:20px; ">密码：</td>
		<td><input type="text" id="password" style="width:200px" 
			class="easyui-validatebox e-input" name="user.password"
			required="true"></td>
	</tr>

	<tr>
		<td>请选择组：</td>
		<td><input id="parentId" name="groupId" class="easyui-combotree" style="width:200px" ></input></td>
		<td style="padding-left:20px; ">用户名称：</td>
		<td><input type="text" id="name" style="width:200px" 
			class="easyui-validatebox e-input" name="user.name" required="true">
		</td>
	</tr>


	<tr>
		<td>工号：</td>
		<td><input type="text" id="jobmember" style="width:200px" 
			class="e-input" name="user.jobmember">
		</td>
		<td style="padding-left:20px; ">英文名称：</td>
		<td><input type="text" id="enname" class="e-input" style="width:200px" 
			name="user.enname"></input></td>
	</tr>
	
	<tr>
		<td>职位：</td>
		<td><input type="text" id="position" class="e-input" style="width:200px" 
			name="user.position"></input></td>
		<td style="padding-left:20px; ">入职日期：</td>
		<td><input type="text" id="employeddate" style="width:200px" 
			class="easyui-datebox" name="user.employeddate"></input></td>
	</tr>
	<tr>
		<td>年龄：</td>
		<td><input type="text" id="age" class="e-input" name="user.age" style="width:200px" ></input></td>
		<td style="padding-left:20px; ">出生日期：</td>
		<td><input type="text" id="birthday"
			class="easyui-datebox" name="user.birthday" style="width:200px" ></input></td>
	</tr>
	<tr>
		<td>籍贯：</td>
		<td><input type="text" id="nativeplace" class="e-input" style="width:200px" 
			name="user.nativeplace"></input></td>
		<td style="padding-left:20px; ">地址：</td>
		<td><input type="text" id="address" class="e-input" style="width:200px" 
			name="user.address"></input></td>
	</tr>
	<tr>
		<td>电话：</td>
		<td><input type="text" id="phone" class="e-input" style="width:200px" 
			name="user.phone"></td>
		<td style="padding-left:20px; ">邮箱：</td>
		<td><input type="text" id="email" class="e-input" style="width:200px" 
			name="user.email"></td>
	</tr>

	<tr>
		<td>用户状态：</td>
		<td><select class="easyui-combobox" id="usertype" 
			name="user.usertype" panelHeight="60" style="width: 200px">
				<option selected="selected" value="1">系统创建</option>
		</select></td>
		
		<td style="padding-left:20px; ">上次登录时间：</td>
		<td><input type="text" id="lastlogondate" class="easyui-datebox" style="width:200px" 
			name="user.lastlogondate"></td>
	</tr>
	<tr>
		<td>上次登录IP：</td>
		<td><input type="text" id="lastlogonip" class="e-input" style="width:200px" 
			name="user.lastlogonip"></td>
		<td style="padding-left:20px; ">上次退出时间：</td>
		<td><input type="text" id="lastlogoffdate" style="width:200px" 
			class="easyui-datebox" name="user.lastlogoffdate"></td>
	</tr>
	<tr>
		<td>排序：</td>
		<td><input type="text" id="orderid" class="e-input" style="width:200px" 
			name="user.orderid"></td>
		<td style="padding-left:20px; ">备注：</td>
		<td><input type="text" id="memo" class="e-input" name="user.memo" style="width:200px" >
		</td>
	</tr>

	<tr>
		<td>教育情况：</td>
		<td>
		<input id="education" class="easyui-combobox"  name="user.education" style="width:200px" 
			required="true" readonly="true" 
			url="<c:url value='/permissions/actions/outDicJsonByNicknameActions.tg?nickName=education'/>"
			valueField="value" textField="name">
		</td>
		<td style="padding-left:20px; ">婚姻状况：</td>
		<td>
		<input id="marriage" class="easyui-combobox"  name="user.marriage" style="width:200px" 
			required="true" readonly="true" 
			url="<c:url value='/permissions/actions/outDicJsonByNicknameActions.tg?nickName=marriage'/>"
			valueField="value" textField="name">
		</td>
	</tr>

	<tr>
		<td>性别：</td>
		<td>
		<input id="sex" class="easyui-combobox"  name="user.sex" style="width:200px" 
			required="true" readonly="true" 
			url="<c:url value='/permissions/actions/outDicJsonByNicknameActions.tg?nickName=sex'/>"
			valueField="value" textField="name">
			</td>
		<td style="padding-left:20px; ">状态：</td>
		<td>
		<input id="status" class="easyui-combobox"  name="user.status" style="width:200px" 
			required="true" readonly="true" 
			url="<c:url value='/permissions/actions/outDicJsonByNicknameActions.tg?nickName=status'/>"
			valueField="value" textField="name">
		</td>
	</tr>
	<tr>
		<td></td>
		<td><input type="hidden" id="registerdate" name="user.registerdate" style="width:200px" ></td>
		<td></td>
		<td><input type="hidden" value="1" id="id" name="user.id" style="width:200px" >
		</td>
	</tr>

</table>
</div>