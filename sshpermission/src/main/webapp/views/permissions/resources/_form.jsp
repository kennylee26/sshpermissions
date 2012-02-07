<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div style="padding:20px">
	<table cellpadding="0" cellspacing="0" class="form-table">
		<tr>
			<td>资源名称：</td>
			<td><input id="name" name="resource.name" class="easyui-validatebox" style="width:200px" required="true"></input>
			<input id="id" name="resource.id" type="hidden"></input>
			</td>
		</tr>
		<tr>
			<td>英文名称：</td>
			<td><input id="enname" name="resource.enname" class="easyui-validatebox" style="width:200px" required="true"></input></td>
		</tr>
		<tr>
			<td>所属系统：</td>
			<td><input id="systemId" name="resource.systemId" class="easyui-combobox" style="width:200px"
							url="<c:url value='/permissions/resources/getSystemsResources.tg'/>"
							valueField="id" textField="name"
							required="true" ></input></td>
		</tr>
		<tr>
			<td>上级资源：</td>
			<td><input id="parentId" name="resource.parentId"  type="text" class="easyui-combotree"
										style="width:200px;"
										required="true"></td>
		</tr>
		<tr>
			<td>资源类型：</td>
			<td><input id="resourcetype" name="resource.resourcetype" class="easyui-validatebox" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>链接：</td>
			<td><input id="link" name="resource.link" class="easyui-validatebox" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>图标：</td>
			<td><input id="icon" name="resource.icon" class="easyui-validatebox" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>打开图标：</td>
			<td><input id="iconopen" class="easyui-combobox" name="resource.iconopen" style="width:200px"  panelHeight="60" 
					required="true" readonly="true"
					url="<c:url value='/permissions/resources/outDicJsonByNicknameResources.tg?nickName=open'/>"
					valueField="value" textField="name"></input></td>
		</tr>
		<tr>
			<td>是否节点：</td>
			<td><input id="isleaf" class="easyui-combobox" name="resource.isleaf" style="width:200px"  panelHeight="60" 
					required="true" readonly="true"
					url="<c:url value='/permissions/resources/outDicJsonByNicknameResources.tg?nickName=leaf'/>"
					valueField="value" textField="name"></input></td>
		</tr>
		<tr>
			<td>是否打开：</td>
			<td><input id="isopen" class="easyui-combobox" name="resource.isopen" style="width:200px"  panelHeight="60"
					required="true" readonly="true"
					url="<c:url value='/permissions/resources/outDicJsonByNicknameResources.tg?nickName=open'/>"
					valueField="value" textField="name"></input></td>
		</tr>
		<tr>
			<td>状态：</td>
			<td><input id="status" class="easyui-combobox" name="resource.status" style="width:200px" panelHeight="60" 
					required="true" readonly="true"
					url="<c:url value='/permissions/resources/outDicJsonByNicknameResources.tg?nickName=status'/>"
					valueField="value" textField="name"></input></td>
		</tr>
		<tr>
			<td>排序：</td>
			<td><input id="orderid" name="resource.orderid" class="easyui-validatebox" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>备注：</td>
			<td><textarea id="memo" name="resource.memo" class="e-input" style="height:40px;width:200px"></textarea></td>
		</tr>
		
	</table>
</div>