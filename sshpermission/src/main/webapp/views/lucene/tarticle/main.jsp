<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://com.tgyt.com.cn/tag/easyui" prefix="tgEasyui" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>搜索</title>
	 <style type="text/css">
		.searchTableTr { background-color : gray;}
		.divSearchInput{
			display: none;
			position: absolute;
			border-width: 1px;
			overflow:hidden; 
			background:none repeat scroll 0 0 #FFFFFF; 
			border:#c5dadb 1px solid;
		}
	</style> 
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
            	$('#dt-actions').datagrid({onDblClickRow:function dblClickRow(rowIndex,rowData){
    				//alert(rowIndex+"-"+JSON.stringify(rowData));
    				//$('#detailform').form('load',rowData);
    				//$('#detail').dialog('setTitle','更新角色信息').dialog('open');
    				$('#myform').formid('loadit',rowData);
    				$.each($('#myform input'),function(i){
    					$(this).attr("readonly","true");
    				});
    				$("#dlg-buttons a:first-child").hide();
    				$('#dlg').dialog('setTitle','查看操作信息').dialog('open');
    			}});
        	});
        </script>
    <script>
    	//鼠标停留改变搜索显示的背景颜色
	  	function changeColor(v,i) {
	  		if ($("#searchIndex").val() != '') {
	  			var index = $("#searchIndex").val();
	  				if (index == 10) {
	  					index = 1;
	  				}
	        	 	 $("#searhTable tr").each(function(i,v) {
		            	if (i == (index-1)) {
		            		$(this).removeClass();
		            	}
		            })
	        	 }
	  		$(v).addClass("searchTableTr");
	  		$(v).css("cursor","default");
	  		$("#searchIndex").val(i+1);
	  		 
	  	}
	  	//鼠标离开移除搜索显示的背景颜色
	  	function moverColor(v) {
	  		$(v).removeClass();
	  		$(v).css("cursor","");
	  		$("#searchIndex").val(0);
	  	}
	  	
	  //点击搜索显示信息
	  	function searchValue(v) {
	  		$("#search").val($(v).text());
	  		$("#searhInput").hide();
	  		
	  	}
	  	
		$(function(){
				$("#divWidthAuto").width(533);
				$("#search").width(530);
				$("#searhInput").width(533);
				$("#searhTable").width(533);
				
				var disp = true;
		 		//点击空白处隐藏
				 $(document).click(function(){
				  	//点击搜索处显示
					 $("#divSearchPage").click(function(){ 
					 	  disp = false;
					 	  if ($('#searhInput').css('display') == 'block') {
			       		  	$('#searhInput').show();
			       		  }
			  		});
			  		if (disp) {
			  			$('#searhInput').hide();
			  		}
			  		disp = true;
				 });
				 //右键点击搜索处显示
				 $("#divSearchPage").bind("contextmenu",function(e){  
				 		$('#searhInput').show();
		  		});  		
		  		
			  	
			  	 //输入框键盘触发事件
			  	$("#search").keyup(function(e){
			        var key =  e.which;
			         //下键
			        if(key == 40){
			        	$('#searhInput').show();
			        	 var index = $("#searchIndex").val();
			        	 var maxIndex = $("#maxIndex").val();
			        	 	if (index == new Number(maxIndex)) {
				            		index = 0;
				            	}
				            if (index == (new Number(maxIndex)+1)) {
				            		index = 1;
				            	}	 
				            $("#searhTable tr").each(function(i,v) {
				            	if (i == index) {
				            		$("#search").val($(this).text());
				            		$(this).addClass("searchTableTr");
				            	} else {
				            		$(this).removeClass();
				            	}		            	
				            })
				            index++;
				            $("#searchIndex").val(index);
			        }
			        //上键
			         if(key == 38){
			         	$('#searhInput').show();
			         	var index = $("#searchIndex").val();
			         	var maxIndex = $("#maxIndex").val();
			         	if (index == 0) {
			         		index = (new Number(maxIndex)+1);
			         	}
			         	if (index == 1) {
			         		index = (new Number(maxIndex)+1);
			         	} 
			         	index = index - 2;
			         	$("#searhTable tr").each(function(i,v) {	            	
			            	if (i == index) {
			            		$("#search").val($(this).text());
			            		$(this).addClass("searchTableTr");
			            	} else {
			            		$(this).removeClass();
			            	}
			            })
			           
			           index = index+1;
			           $("#searchIndex").val(index);
			            
			         }
			         if (key != 40 && key != 38) {
				         if(this.value != '') {
				        	 querySearchInfo(this);
				        	 } else {
				            	$('#searhTable').empty();
						  		$('#searhInput').hide();
					  	}
			        }
			        
		    	});
		    var keyword
		    	//搜索框输入
		   		function querySearchInfo(v) {
		    		
		    	    keyword= $("#search").val();
		    	    var url="/tgOA/lucene/tarticle/searchIndexTArticle.tg";
					$.ajax({
						type: "post",
						dataType:"json",
						url: url,
						data: "keyword="+keyword,
						success: function(json){
							$("#searhTable").empty();
							$("#searhInput").hide();
							 $("#searchIndex").val(0);
							for (i = 0;i < json.length;i++) {
								if (i == 10) {
									break;
								}
								$("#searhTable").append("<tr onmousemove='changeColor(this,"+i+")' onmouseout='moverColor(this)' id='searchTr"+i+"'><td onclick='searchValue(this)' name='title'>"+json[i]["title"]+"</td></tr>")
							 
							}
							if (json.length != 0) {
								var y = ($(v).offset().top+30);
		                   		var x = ($(v).offset().left-1);
		                   		$("#searhInput").css("top",y);
								$("#searhInput").css("left", x);
								var maxIndex = json.length;
								if (json.length > 10) {
									maxIndex = 10;
								}
								$("#maxIndex").val(maxIndex);
								$("#searhInput").show();
							}
							
						}
						});
					//alert(1234);
				}
		});
	 </script>  
	 <script type="text/javascript">
	 function createIndex(){
		 //alert(keyword);
    	    var url="/tgOA/lucene/tarticle/createIndexTArticle.tg";
			$.ajax({
				type: "post",
				dataType:"json",
				url: url,
				success: function(json){
					alert("建立成功");
				}
				});   
	 }
		function searchinfo(){
			 var keyword1= $("#search").val();
			 //alert(keyword);
			  var keyword = encodeURI(encodeURI(keyword1));
			  var url = '<c:url value="/lucene/tarticle/getItemsTArticle.tg"/>?keyword='+keyword;
			 $('#serachResult').datagrid({ url:url });   

			 
	    	    /* //var url="/tgOA/lucene/tarticle/getItemsTArticle.tg";
				$.ajax({
					type: "post",
					dataType:"json",
					url: url,
					success: function(json){
						alert(JSON.stringify(json));
						$('#serachResult').datagrid({ url:url });
						
					}
					});    */
		}
	</script>
	  <script>
		var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
			renderRow: function(target, fields, frozen, rowIndex, rowData){
				var cc = [];
				cc.push('<td colspan=' + fields.length + ' style="padding:10px 5px;border:0;">');
				if (!frozen){
					cc.push('<div style="float:left;margin-left:20px;">');
					for(var i=0; i<fields.length; i++){
						var copts = $(target).datagrid('getColumnOption', fields[i]);
						cc.push('<p><span class="c-label">' + copts.title + ':</span> ' + rowData[fields[i]] + '</p>');
					}
					cc.push('</div>');
				}
				cc.push('</td>');
				return cc.join('');
			}
		});
		$(function(){
			$('#serachResult').datagrid({
				view: cardview
			});
		});
	</script>
  </head>
  
  <body style="margin:0;padding:0;height:100%;overflow:hidden;background:#F2FBFF">
			<div region="north" border="false">
  	<div id="divSearchPage">
  	
	   	<div id="divWidthAuto" style="border:#c5dadb 1px solid;border-width: 1px;height:30px;float: left;">
	   		<input type="text" name="search" id="search" style="border: 0px;height:30px;font-size: 16px;line-height: 30px;color: #000000;">
	   	</div>
	   	<tr>
	   		<td style="text-align: left"><input type="button" onclick="searchinfo();" value="搜索" style="height:30;width:100"></td>
	   	    <td style="text-align: right"><input type="button"  onclick="createIndex();" value="建立索引" style="height:30;width:100"></td>
	   	</tr>
	   	<input type="hidden" name="searchIndex" id="searchIndex" value="0">
	   	<input type="hidden" name="maxIndex" id="maxIndex" value="0">
	   	<div id="searhInput" class="divSearchInput" >
	   		 <table id="searhTable">
	   		 	<tbody>
	   		 	<tr>
	   		 	<th field="title" width="100" sortable="true"></th>
	   		 	</tr>
	   		 	</tbody>
	   		 </table>
	   	</div>
   	 </div> 
   	 </div>
   <div region="center" border="false" style="height:95%;width:100%" display="none">
				<table id="serachResult"  title="搜索结果" singleSelect="true" fitColumns="true" class="easyui-datagrid"
				remoteSort="false" pagination="true"   border="false" fit="true" align="left" >
					
					<thead>
						<tr>
							<th field="title" width="80" sortable="true">title</th>
							<th field="content" width="120" sortable="true" >content</th>
						</tr>
					</thead>
				</table>	
   	</div>
  </body>
</html>
