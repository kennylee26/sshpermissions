<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://com.tgyt.com.cn/tag/easyui" prefix="tgEasyui" %>

<head>
	<jsp:include page="head.jsp"></jsp:include>
</head>
<body style= "margin:0px;">
	<form id="dicform" method="post">
		<input type="hidden" id="dicId" name="id" value=""/>
	</form>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false">
			<div class="toolbar">
				<tgEasyui:easyuiButton iconCls="icon-add" method="newItem()" permission="dictionary:add" operationName="新增"/>
				<tgEasyui:easyuiButton iconCls="icon-edit" method="editItem()" permission="dictionary:modify" operationName="修改"/>
				<tgEasyui:easyuiButton iconCls="icon-cancel" method="delItem()" permission="dictionary:delete" operationName="删除"/>
				<tgEasyui:easyuiButton iconCls="icon-search" method="advanceQuery()" permission="dictionary:query" operationName="查询常量"/>
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newItem()">新增常量</a> -->
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editItem()">修改常量</a> -->
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="delItem()">删除常量</a> -->
<!-- 				<a href="javascript:advanceQuery()" class="easyui-linkbutton"plain="true">查询常量</a> -->
			</div>
		</div>
		
		<div region="center" border="false">
			<table id="t-dictionarys" class="easyui-datagrid"
					url="<c:url value='/dictionary/getItemsDictionary.tg'/>"
					singleSelect="true" pagination="true"
					border="false" fit="true">
				<thead>
					<tr>
						<th field="nickname" width="100" sortable="true">昵称</th>
						<th field="name" width="100" >常量显示名称</th>
						<th field="value" width="100">常量代码值</th>
						<th field="createtime" width="100" sortable="true">创建日期</th>
						<th field="type" width="100">常量排序值</th>
						<th field="updatable" width=100>是否可编辑</th>
						<th field="creator" width="100">创建人</th>
						<th field="status" width="100">状态</th>
						<th field="orderid" width="100">排序</th>
						<th field="memo" width="300">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
	<div id="dlg" style="width:600px;height:400px;"
			class="easyui-dialog" closed="true" modal="true" buttons="#dlg-buttons">
		<div style="padding-left:150px;padding-top:40px;">
			<div style="float:center">
				<form id="myform" method="post" style="margin:0;padding:0">
					<table>
						<tr>
							<td style="width:80px">昵称</td>
							<td><input type="text" id="nickname" class="easyui-validatebox e-input" name="dic.nickName" required="true"></input></td>
						</tr>
						<tr>
							<td>常量显示名称</td>
							<td>
								<input type="text" id="name" class="easyui-validatebox e-input" name="dic.name" required="true" />
							</td>
						</tr>
						<tr>
							<td></td>
							<td style="display: none;">
								<input type="text" id="createtime" name="dic.createTime" >
							</td>
						</tr>
						<tr>
							<td>常量排序值</td>
							<td><input type="text" id="type" class="e-input" name="dic.type"></input></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="hidden" id="value" name="dic.value" value=""></input></td>
						</tr>
						<tr>
							<td>是否可编辑</td>
							<td>
								<input id="updaTable" class="easyui-combobox"  name="dic.updaTable" style="width:200px" 
									required="true" readonly="true" 
									url="<c:url value='/permissions/actions/outDicJsonByNicknameActions.tg?nickName=icon'/>"
									valueField="value" textField="name">
						</tr>
						<tr>
							<td>创建人</td>
							<td><input type="text" id="creator" class="e-input" name="dic.creator"></input></td>
						</tr>
						<tr>
							<td>状态</td>
							<td>
								<input id="status" class="easyui-combobox"  name="dic.status" style="width:200px" 
									required="true" readonly="true" 
									url="<c:url value='/permissions/actions/outDicJsonByNicknameActions.tg?nickName=status'/>"
									valueField="value" textField="name">
								
							</td>
						</tr>
						<tr>
							<td>排序</td>
							<td>
								<input type="text" id="orderid" class="e-input" name="dic.orderid"></input>
							</td>
						</tr>
						<tr>
							<td>备注</td>
							<td><input type="text" id="memo" class="e-input" name="dic.memo"></input></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>
								<input type="hidden" id="id" name="dic.id" value="1"/>
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
	
</body>
