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
	    	var url = "/user/PostSingleListAjaxPage.action?pageNo="+pageNo+"&selectTime="+R.val()+"&userId="+userId+"&startDate="+startDate+"&endDate="+endDate;
	    	$(".pageUL").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\""+$img+"/Images/loding.gif\" /></li>");
	        $.ajax({
	            url: url,
	            type: "post",
	            data: "json",
	            beforeSend:loading,
	            success: function (data) {
	            	$("#goods_lotter").empty();
	            	if(resultCount>0){
	            		$(".pageUL").show();
	            		$(".pageUL").prepend("<li class=\"total_page\">页次<i>"+(pageNo+1)+"/"+Math.ceil(resultCount/5)+"</i>&nbsp;&nbsp;共<i>"+resultCount+"</i>条记录</li>");
		            	for(var i=0;i<data.length;i++){
		            		var X = "<ul class=\"goods_lotter_list\" >";
							X += "<li class=\"name\"><a href=\""+$www+"/products/" + data[i].productId + ".html\" class=\"pic\" target=\"_blank\" title=\"" + data[i].productTitle + "\"><img src=\""+$img+data[i].productImg+"\" /></a><div><p class=\"title\"><a href=\""+$www+"/products/"+data[i].productId+".html\" class=\"name\" target=\"_blank\" title=\"" + data[i].productTitle + "\"><font color='#ff6600'>(\u7b2c" + data[i].productPeriod + "\u671f)</font>" + data[i].productName + " </a></p><p>\u5e78\u8fd0\u7f16\u53f7:<span>" + data[i].winId + "</span> \u63ed\u6653\u65f6\u95f4:<span>" + data[i].winDate + "</span></p></div></li>";
							if (data[i].buyStatus == -1) {
								X += "<li class=\"share_status\"><span class=\"nothing\">\u6682\u672a\u6652\u5355</span></li>";
								X += "<li class=\"do\"><a href=\""+$post+"/PostSingleAdd/" + data[i].historyId + ".html\" class=\"go_share\">\u7acb\u5373\u6652\u5355</a></li>";
							} else {
								if (data[i].buyStatus == 0) {
									X += "<li class=\"share_status\"><span class=\"wrong\">\u7b49\u5f85\u5ba1\u6838</span></li>";
									X += "<li class=\"do\"><a href=\""+$post+"/PostSingleDetail/" + data[i].winUserId + ".html\">\u6652\u5355\u8be6\u60c5</a></li>";
								} else {
									if (data[i].buyStatus == 1) {
										X += "<li class=\"share_status\"><span class=\"wrong\">\u672a\u5ba1\u6838\u901a\u8fc7\uff0c\u8bf7\u91cd\u65b0\u4fee\u6539\u6652\u5355\u4fe1\u606f</span></li>";
										X += "<li class=\"do\"><a href=\""+$post+"/PostSingleEdit/" + data[i].winUserId + ".html\" >\u4fee\u6539\u6652\u5355\u4fe1\u606f</a></li>";
									} else {
										if (data[i].buyStatus == 2) {
											X += "<li class=\"share_status\"><span class=\"ok\">\u5ba1\u6838\u901a\u8fc7\uff0c\u5956\u52b110\u5143</span></li>";
											X += "<li class=\"do\"><a target=\"_blank\" href=\""+$post+"/PostSingleDetail/" + data[i].winUserId + ".html\" >\u6652\u5355\u8be6\u60c5</a></li>";
										}
									}
								}
							}
							X += "</ul>";
							$(X).appendTo("#goods_lotter");
						}
		            	$("#pageLoading").hide();
	            	}else{
	            		$("<ul><li class=\"notFound\">\u67e5\u65e0\u6652\u5355\u8bb0\u5f55\uff0c\u8bf7\u66f4\u6539\u67e5\u8be2\u533a\u95f4\uff01</li></ul>").appendTo("#goods_lotter");
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
				url:"/user/PostSingleListAjaxPageResultCount.action?pageNo="+pageNo+"&selectTime="+R.val()+"&userId="+userId+"&startDate="+startDate+"&endDate="+endDate,
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
					url:"/user/PostSingleListAjaxPageResultCount.action?pageNo="+pageNo+"&selectTime="+R.val()+"&userId="+userId+"&startDate="+d+"&endDate="+e,
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

