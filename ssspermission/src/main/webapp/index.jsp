<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>权限管理系统</title>
    <link href="<c:url value="/css/default.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/index.css"/>" rel="stylesheet" type="text/css" />
    
    <link rel="stylesheet" href="<c:url value="/tg/commons/easyui/themes/default/easyui.css"/>" type="text/css" />
    <link rel="stylesheet" href="<c:url value="/tg/commons/easyui/themes/icon.css"/>" type="text/css" />
    <script src="<c:url value="/tg/commons/easyui/jquery-1.6.min.js"/>"></script>
    <script src="<c:url value="/tg/commons/easyui/jquery.easyui.min.js"/>"></script>
    <script src="<c:url value="/tg/commons/easyui/locale/easyui-lang-zh_CN.js"/>"></script>
    <script type="text/javascript" src='<c:url value="/js/outlook2.js"/>'> </script>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎    <shiro:principal />   <a href="logoutLogin.tg" id="loginOut">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; "><img src="images/blocks.gif" width="20" height="20" align="absmiddle" /> 权限管理系统</span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">权限管理系统</div>
    </div>
    <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
	<div id="nav" class="accordion accordion-noborder" fit="true" border="false">
		<%=request.getAttribute("treescript") %>
	</div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<!-- <div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; " >
				<br/>
				<br/>
				<h1 style="font-size:24px;">* * 欢迎使用权限管理系统！</h1>
			</div> -->
		</div>
    </div>
    
    
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>


</body>
</html>