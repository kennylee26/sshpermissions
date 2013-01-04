 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>权限管理系统</title>
    <jsp:include page="/views/include.jsp"></jsp:include>
    <script type="text/javascript">
    	var contextPath = '<%=request.getContextPath()%>';
    	$.parser.onComplete = function(){
        	$('body').css('visibility','visible');
        	setTimeout(function(){
         	$('#loading-mask').remove();
        	},50);
    	};
    	$(function(){
        	$(window).resize(function(){
            	$('#mainlayout').layout('resize');
        	});
    	});
    </script>

	<script type="text/javascript">
		function advanceQuery(){
			showQueryDialog({
				width:350,
				height:230,
				form:'<c:url value="/views/permissions/log/query.jsp"/>',
				callback:function(data){
					$('#dt-logs').datagrid('load',data);
				}
			});
		}
		
		var actionUrl;
		function lookLog(){
			var row = $('#dt-logs').datagrid('getSelected');
			if(row){
				actionUrl = '<c:url value="/log/lookLog.tg"/>';
				$('#logid').val(row.id);
				$('#myform').formid('loadit',row);
				$('#dlg').dialog('setTitle', '查看日志信息').dialog('open');
			}else{
				alert('请选择要查看的具体日志');
			}
			
		}
		
	</script>