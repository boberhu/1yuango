$(document).ready(function () {
		var resultCount = $("#resultCount").val();
	    var pageNo = 0;
	    var userId = $("#userId").val();
		var R = $("#selectTime");
		var J = $("#userSelTime");
		var S = $("#txtMisTime");
		var B = $("#txtMaxTime");
	 	var startDate = S.val();
	    var endDate = B.val();
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
	    	var url = "/user/OrderListAjaxPage.action?pageNo="+pageNo+"&selectTime="+R.val()+"&userId="+userId+"&startDate="+startDate+"&endDate="+endDate;
	    	$(".pageUL").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\""+$img+"/Images/loding.gif\" /></li>");
	        $.ajax({
	            url: url,
	            type: "post",
	            data: "json",
	            beforeSend:loading,
	            success: function (data) {
	            	$("#goods_lotter_list").empty();
	            	if(resultCount>0){
	            		$(".pageUL").show();
	            		$(".pageUL").prepend("<li class=\"total_page\">页次<i>"+(pageNo+1)+"/"+Math.ceil(resultCount/5)+"</i>&nbsp;&nbsp;共<i>"+resultCount+"</i>条记录</li>");
		            	for(var i=0;i<data.length;i++){
		            		var X = "<ul class=\"goods_lotter_list\" >";
							X += "<li class=\"name\"><a href=\""+$www+"/products/"+data[i].productId+".html\" class=\"pic\" target=\"_blank\" title=\""+data[i].productTitle+"\"><img src=\""+$img+data[i].productImg+"\" /></a>";
							X += "<div><p class=\"title\"><a href=\""+$www+"/products/"+data[i].productId+".html\" class=\"name\" target=\"_blank\" title=\""+data[i].productTitle+"\"><font color='#ff6600'>(\u7b2c" + data[i].productPeriod + "\u671f)</font>" + data[i].productName + "</a></p><p>\u5e78\u8fd0\u7f16\u53f7\uff1a<span>" + data[i].winId + "</span> \u63ed\u6653\u65f6\u95f4\uff1a<span>" + data[i].winDate + "</span></p></div></li>";
							X += "<li class=\"share_status\">";
							var Z = data[i].buyStatus;
							switch (Z) {
							  case 1:
								X += "\u672a\u63d0\u4ea4\u6536\u83b7\u5730\u5740";
								break;
							  case 2:
								X += "\u7b49\u5f85\u53d1\u8d27";
								break;
							  case 3:
								X += "\u7b49\u5f85\u6536\u8d27";
								break;
							  case 4:
								X += "\u5df2\u786e\u8ba4\u6536\u8d27";
								break;
							  case 10:
								X += "\u4ea4\u6613\u5b8c\u6210";
								break;
							  case 11:
								X += "\u4ea4\u6613\u53d6\u6d88";
								break;
							}
							X += "</li><li class=\"do\"><a target=\"_blank\" href=\""+$server+"/lotteryDetail/" + data[i].productId + ".html\">\u67e5\u770b\u8be6\u60c5</a>";
							X += "</br><a href=\""+$passport+"/user/PostSingleList.html\">\u7acb\u5373\u6652\u5355</a></li></ul>";
							$(X).appendTo("#goods_lotter_list");
		            	}
		            	$("#pageLoading").hide();
	            	}else{
	            		$("<ul><li class=\"notFound\">查无夺宝成功记录，请更改查询区间！ &nbsp;</li></ul>").appendTo("#goods_lotter_list");
	            		$(".pageUL").hide();
	            	}
	            }
	        });
	    }
	    
   	    function loading(){
    		$("#pageLoading").show();
    	}
    	function changeLoading(){
    		$("#changeLoading").show();
    	}
    	
 	$("#selectTime").change(function(){
   		var selectVal = R.val();
   		var a = new Date();
		var b = parseInt(R.val());
		if(selectVal=="0" || selectVal=="1" || selectVal=="2" || selectVal=="3" || selectVal=="4"){
			$.ajax({
				url:"/user/OrderListAjaxPageResultCount.action?pageNo="+pageNo+"&selectTime="+R.val()+"&userId="+userId+"&startDate="+startDate+"&endDate="+endDate,
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
			J.hide();
		}else if(selectVal=="5"){
			S.val(a.DateAdd("m", -1).Format("YYYY-MM-DD")).date_input();
			B.val(a.Format("YYYY-MM-DD")).date_input();
			J.show();
			var c = function () {
				var d = S.val();
				var e = B.val();
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
					url:"/user/OrderListAjaxPageResultCount.action?pageNo="+pageNo+"&selectTime="+R.val()+"&userId="+userId+"&startDate="+d+"&endDate="+e,
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
   			J.show();
   		}
   	});
   	$.getScript($skin+"/js/date.js");
   	$.getScript($skin+"/js/dateinput.js");
});

