<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>jquery</title>
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
	 
  </head>
  
  <body>
  	<div id="divSearchPage">
  	
	   	<div id="divWidthAuto" style="border:#c5dadb 1px solid;border-width: 1px;height:30px;float: left;">
	   		<input type="text" name="search" id="search" style="border: 0px;height:30px;font-size: 16px;line-height: 30px;color: #000000;">
	   	</div>
	   	&nbsp;<input type="button" value="搜索" style="height:30;width:100">
	   	
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
   	<script src="jquery.js"></script> 
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
  </body>
</html>
