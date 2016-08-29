$(document).ready(function () {
	    var resultCount = $("#resultCount").val();
	    var pageNo = 0;
	    var userId = $("#userId").val();
	    var M = $("#GoodsList");
	    var O = $("<dd></dd>");
	    var X = $("#txtMisTime");
		var F = $("#txtMaxTime");
		var N = $("#userSelTime");
		var W = $("#selectTime");
	    var startDate = X.val();
	    var endDate = F.val();
	    //分页事件
	    $("#pagination").pagination(resultCount, {
	    	current_page:pageNo,
	        prev_text: "上一页",
	        next_text: "下一页",
	        num_display_entries: 5,
	        num_edge_entries:1,
	        link_to:"",
	        prev_show_always:false,
	        next_show_always:false,
	        items_per_page:5,
	        callback: pageselectCallback
	    });
	    function pageselectCallback(pageNo) {
	    	var url = "/user/userBuyListAjaxPage.action?pageNo="+pageNo+"&selectTime="+W.val()+"&userId="+userId+"&startDate="+startDate+"&endDate="+endDate;
	    	$(".pageUL").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\""+$img+"/Images/loding.gif\" /></li>");
	        $.ajax({
	            url: url,
	            type: "post",
	            data: "json",
	            beforeSend:loading,
	            success: function (data) {
	            	$("#goods_list").empty();
	            	if(resultCount>0){
	            		$(".pageUL").show();
	            		$(".pageUL").prepend("<li class=\"total_page\">页次<i>"+(pageNo+1)+"/"+Math.ceil(resultCount/5)+"</i>&nbsp;&nbsp;共<i>"+resultCount+"</i>条记录</li>");
		            	for(var i=0;i<data.length;i++){
		            		if(data[i].buyStatus=="0"){
		            			$("<ul class=\"goods_list\"><li><a title=\""+data[i].productTitle+"\" target=\"_blank\" class=\"pic\" href=\""+$www+"/products/"+data[i].productId+".html\"><img src=\""+$img+data[i].productImg+"\"></a></li>"+
		                        "<li class=\"gname\"><a title=\""+data[i].productTitle+"\" target=\"_blank\" href=\""+$www+"/products/"+data[i].productId+".html\"><font color=\"#ff6600\">(第"+data[i].productPeriod+"期)</font>"+data[i].productName+"</a></li>"+
		                        "<li class=\"yg_status\"><span class=\"status_ing\">等待揭晓</span></li><li><dl class=\"joinInfo\"><dt><em>"+data[i].buyCount+"</em>人次<input type=\"hidden\" value=\""+data[i].historyId+"\"></dt></dl></li>"+
		                        "<li class=\"do\"><a onclick=\"javascript:location.href='"+$member+"/user/UserBuyDetail.action?id="+data[i].historyId+"';return false;\" href=\"javascript:gotoClick();\">查看详情</a></li></ul>").appendTo("#goods_list");
		            		}else{
		            			$("<ul class=\"goods_list\"><li><a title=\""+data[i].productTitle+"\" target=\"_blank\" class=\"pic\" href=\""+$www+"/products/"+data[i].productId+".html\"><img src=\""+$img+data[i].productImg+"\"></a></li>"+
		                        "<li class=\"gname\"><a title=\""+data[i].productTitle+"\" target=\"_blank\" href=\""+$www+"/products/"+data[i].productId+".html\"><font color=\"#ff6600\">(第"+data[i].productPeriod+"期)</font>"+data[i].productName+"</a></li>"+
		                        "<li class=\"yg_status\"><span class=\"over\">已经揭晓</span><br><span>获得者<em>"+data[i].winUser+"</em></span></li><li><dl class=\"joinInfo\"><dt><em>"+data[i].buyCount+"</em>人次<input type=\"hidden\" value=\""+data[i].historyId+"\"></dt></dl></li>"+
		                        "<li class=\"do\"><a onclick=\"javascript:location.href='"+$member+"/user/UserBuyDetail.action?id="+data[i].historyId+"';return false;\" href=\"javascript:gotoClick();\">查看详情</a></li></ul>").appendTo("#goods_list");
		            		}
		            	}
		            	$("#pageLoading").hide();
						$(".joinInfo", M).each(function(){
							var that = $(this);
							that.hoverDelay({
				               hoverDuring: 500,
					            hoverEvent: function(){
					               that.children("dt").addClass("hover");
							       showList(that);
					            },
					            outEvent: function(){
					                that.children("dt").removeClass("hover");
									O.remove();
					            }
					        });
						});
	            	}else{
	            		$("<ul><li class=\"notFound\">查无夺宝记录，请更改查询区间！&nbsp;</li></ul>").appendTo("#goods_list");
	            		$(".pageUL").hide();
	            	}
	            }
	        });
	    }
	    function loading(){
    		$("#pageLoading").show();
    	}
    	function loading2(){
    		$("#pageLoading2").show();
    	}
    	function changeLoading(){
    		$("#changeLoading").show();
    	}
    	function showList(b){
    		O.html("<div id=\"pageLoading2\"><img src=\""+$img+"/Images/loding.gif\" /></div>");
			var id = b.children("dt").children("input").val();
			var buyCount = b.children("dt").children("em").html();
			var e = "<div class='title'>\u672c\u671f\u5546\u54c1\u60a8\u603b\u62e5\u6709<span>" + buyCount + "</span>\u4e2a\u62cd\u8d2d\u7f16\u53f7</div>";
			e += "<ul>";
			var f ="";
			$.ajax({
				url:"/user/getRandomNumberList.action?id="+id+"&pageNo="+0,
				data:"JSON",
				type:"GET",
				beforeSend:loading2,
				success:function(data){
					for (var d = 0; d < data.length; d++) {
						f += "<li>" + data[d] + "</li>";
					}
					e += f;
					O.html(e);
					isLoaded = false;
					$("#pageLoading2").hide();
				}
			});
			e += "</ul>";
			b.append(O);
    	}
   	$("#selectTime").change(function(){
   		var selectVal = W.val();
   		var a = new Date();
		var b = parseInt(W.val());
		if(selectVal=="0" || selectVal=="1" || selectVal=="2" || selectVal=="3" || selectVal=="4"){
			$.ajax({
				url:"/user/getuserBuyListAjaxPageResultCount.action?pageNo="+pageNo+"&selectTime="+W.val()+"&userId="+userId+"&startDate="+startDate+"&endDate="+endDate,
				type:"get",
				data:"String",
				beforeSend:changeLoading,
				success:function(data){
					resultCount = parseInt(data);
					 //分页事件
				    $("#pagination").pagination(resultCount, {
				    	current_page:pageNo,
				        prev_text: "上一页",
				        next_text: "下一页",
				        num_display_entries: 5,
				        num_edge_entries:1,
				        link_to:"",
				        prev_show_always:false,
				        next_show_always:false,
				        items_per_page:5,
				        callback: pageselectCallback
				    });
				    $("#changeLoading").hide();
				}
			});
			N.hide();
		}else if(selectVal=="5"){
			X.val(a.DateAdd("m", -1).Format("YYYY-MM-DD")).date_input();
			F.val(a.Format("YYYY-MM-DD")).date_input();
			N.show();
			var c = function () {
				var d = X.val();
				var e = F.val();
				if (!IsValidDate(d)) {
					alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u5f00\u59cb\u65e5\u671f!");
					return;
				}
				if (!IsValidDate(e)) {
					alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7ed3\u675f\u65e5\u671f!");
					return;
				}
				if (ConvertDate(e) < ConvertDate(d)) {
					alert("\u7ed3\u675f\u65e5\u671f\u4e0d\u80fd\u5c0f\u4e8e\u5f00\u59cb\u65e5\u671f!");
					return;
				}
				$.ajax({
					url:"/user/getuserBuyListAjaxPageResultCount.action?pageNo="+pageNo+"&selectTime="+W.val()+"&userId="+userId+"&startDate="+d+"&endDate="+e,
					type:"get",
					data:"String",
					beforeSend:changeLoading,
					success:function(data){
						resultCount = parseInt(data);
						startDate = d;
						endDate = e;
						 //分页事件
					    $("#pagination").pagination(resultCount, {
					    	current_page:pageNo,
					        prev_text: "上一页",
					        next_text: "下一页",
					        num_display_entries: 5,
					        num_edge_entries:1,
					        link_to:"",
					        prev_show_always:false,
					        next_show_always:false,
					        items_per_page:5,
					        callback: pageselectCallback
					    });
					    $("#changeLoading").hide();
					}
				});
			};
			$("#butTimeSelect").unbind("click").bind("click", c);
			return;
   			N.show();
   		}
   	});
   	$.getScript($skin+"/js/date.js");
   	$.getScript($skin+"/js/dateinput.js");
   	$.getScript($skin+"/js/jquery.hoverdelay.js");
});

