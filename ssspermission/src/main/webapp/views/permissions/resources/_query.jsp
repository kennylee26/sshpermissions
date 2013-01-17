<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<script type="text/javascript">
		$(function(){
			init();
		});
		function init(){
			$('input[id="resource.systemId"]').combobox({
				onSelect:function(node){
					$('input[id="resource.parentId"]').combotree({
						url:"<c:url value='/permissions/resources/getTreeResources.tg'/>?systemId="+node.id
					})
					$('input[id="resource.parentId"]').combotree('reload');
				}
			});
		}
</script>
<div style="padding:10px">
	<div class="subtitle" style="border-bottom:1px solid #ccc">请输入查询参数：</div>
	<div style="padding:10px 0 10px 30px">
		<table cellpadding="0" cellspacing="2">
		<tr>
			<td>资源名称：</td>
			<td><input name="resource.name" class="easyui-validatebox query" style="width:200px" ></input>
			</td>
		</tr>
		<tr>
			<td>英文名称：</td>
			<td><input name="resource.enname" class="easyui-validatebox query" style="width:200px" ></input></td>
		</tr>
		<tr>
			<td>所属系统：</td>
			<td><input id="resource.systemId" name="resource.systemId" class="easyui-combobox query" style="width:200px"
							url="<c:url value='/permissions/resources/getSystemsResources.tg'/>"
							valueField="id" textField="name"
							></input></td>
		</tr>
		<tr>
			<td>上级资源：</td>
			<td><input id="resource.parentId" name="resource.parentId"  type="text" class="easyui-combotree query"
										style="width:200px;"
										></td>
		</tr>
		<tr>
			<td>资源类型：</td>
			<td><input name="resource.resourcetype" class="easyui-validatebox query" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>链接：</td>
			<td><input name="resource.link" class="easyui-validatebox query" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>图标：</td>
			<td><input name="resource.icon" class="easyui-validatebox query" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>打开图标：</td>
			<td><input class="easyui-combobox query" name="resource.iconopen"  panelHeight="80" style="width:200px"  readonly="true"
					url="<c:url value='/permissions/resources/outDicJsonByNicknameResources.tg?nickName=open'/>"
					valueField="value" textField="name"></input></td>
		</tr>
		<tr>
			<td>是否节点：</td>
			<td><input class="easyui-combobox query" name="resource.isleaf"  panelHeight="80" style="width:200px"  readonly="true"
					url="<c:url value='/permissions/resources/outDicJsonByNicknameResources.tg?nickName=leaf'/>"
					valueField="value" textField="name"></input></td>
		</tr>
		<tr>
			<td>是否打开：</td>
			<td><input class="easyui-combobox query" name="resource.isopen" style="width:200px"  panelHeight="60"
					readonly="true"
					url="<c:url value='/permissions/resources/outDicJsonByNicknameResources.tg?nickName=open'/>"
					valueField="value" textField="name"></input></td>
		</tr>
		<tr>
			<td>状态：</td>
			<td><input class="easyui-combobox  query" name="resource.status"  panelHeight="80" style="width:200px"  readonly="true"
					url="<c:url value='/permissions/resources/outDicJsonByNicknameResources.tg?nickName=status'/>"
					valueField="value" textField="name"></input></td>
		</tr>
		<tr>
			<td>排序：</td>
			<td><input name="resource.orderid" class="easyui-validatebox query" style="width:200px"></input></td>
		</tr>
		<tr>
			<td>备注：</td>
			<td><textarea name="resource.memo" class="e-input query" style="height:40px;width:200px"></textarea></td>
		</tr>
		
	</table>
</div>
</div>