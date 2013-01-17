<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link rel="stylesheet" href="<c:url value="/tg/commons/easyui/themes/default/easyui.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/tg/commons/easyui/themes/icon.css"/>" type="text/css"/>
<script src="<c:url value="/tg/commons/easyui/jquery-1.6.min.js"/>"></script>
<script src="<c:url value="/tg/commons/easyui/jquery.easyui.min.js"/>"></script>
</HEAD>
<%=request.getAttribute("treeScript")%>
</HTML>
<script>
	$(function() {
		$('#tree').tree({
			onClick : function(node) {
				var id =node.id.substr(4);
				self.parent.frames['rightFrame'].location= "../views/permissions/group/Blank.jsp?id=" + id;
			}
		});
	});
</script>