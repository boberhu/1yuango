$(document).ready(function () {
		var skinDomain = "";
		var CBLFun = null;
		function showDetailBox(D) {
			var E = null;
			var C = null;
			for (var B = 1; B <= 10; B++) {
				E = $("#id_boxmenu_" + String(B));
				C = $("#id_boxcontents_" + String(B));
				if ((E.length > 0) && (C.length > 0) && (B != D)) {
					E.attr("class", "");
					C.attr("class", "pro-mainH");
				} else {
					if (B == D) {
						E.attr("class", "menu-on");
						C.attr("class", "pro-main");
					}
				}
			}
			if (D == 2 && CBLFun != null) {
				var A = function () {
					if (!isLoaded) {
						setTimeout(A, 1000);
						return;
					}
					CBLFun.initData();
				};
				A();
			}
			return false;
		}
		$("#id_boxmenu_1").click(function(){
			showDetailBox(1);
		});
		$("#id_boxmenu_2").click(function(){
			showDetailBox(2);
			getParticipatePage();
		});
		$("#id_boxmenu_3").click(function(){
			showDetailBox(3);
		});
		
		var B = $("#hylinkGoShop");
		var A = function () {
			window.scrollTo(0, 0);
			return false;
		};
		var E = $("#historyBout");
		$("#LookHistoryBout").hover(function () {
			E.show();
		}, function () {
			E.hide();
		});
		$("#linkLookBuyInfo").click(function(){
			showDetailBox(2);
			window.location.href ="#con";
			getParticipatePage();
			return false;
		});
		B.click(function(D){
			var codeid = $("#productId").val();
			var json = index.getCookie("products");
			if(json==null || json==""){
				var val = "[{'pId':"+codeid+",'num':1}]";
				index.SetCookieByExpires("products",val,1);
			}else{
				var flag = false;
				json = eval('('+json+')');
				$.each(json,function(n,value) {
					if(codeid==value.pId){
						value.num = parseInt(value.num+1);
						flag = true;
					}
				});
				if(!flag){
					json.push({"pId":+codeid,"num":1});
				}
				json = JSON.stringify(json);
				index.SetCookieByExpires("products",json,1);
			}
			$.ajax({
				url:"/mycart/buyProductClick.html",
				data:"JSON",
				type:"GET",
				success:function(data){
					var content = "<div class=\"message\"><h3><span class=\"right_s\"></span></h3><ul><li class=\"title\">商品已成功添加到购物车</li><li>购物车内总共有<em>"+data[0].productCount+"</em>件商品，夺宝总金额：<em>￥"+data[0].moenyCount+".00</em></li><li><input type=\"button\" class=\"button_2\" id=\"dgSubmit\" value=\"查看购物车\"><input type=\"button\" class=\"button_3\" id=\"dgCancle\" value=\"继续夺宝\"></li></ul></div>";
					pageDialog.PageDialog(content,"",450,138,"true","false");
				}
			});
		});
		$("#dgCancle").live("click", function () {
			$("#pageDialog").hide();
			$("#pageDialogBG").hide();
			$("#pageDialogBorder").hide();
		});
		$("#dgSubmit").live("click", function () {
			location.href = $www+"/mycart/index.html";
		});
		isLoaded = true;
		if ($("#liLotteryTimeout").length > 0) {
			showDetailBox(2);
		}
		if ($("#liLotteryed").length > 0) {
			showDetailBox(2);
		}
		
		var upcomingAnnounced = false;
		function upcomingAnnouncedShow(){
			var top = $(".willstart-list").offset().top;
			if(!upcomingAnnounced && $(window).scrollTop() + $(window).height() > top ){
				$.ajax({
					url:"/lottery/upcomingAnnounced.action?pageNo="+1+"&pageSize="+3,
					type:"get",
					data:"JSON",
					success:function(data){
						for(var i=0;i<data.length;i++){
							$("<div class=\"item-wrap\">"+
								"<div class=\"willstart-list-pic\"><a title=\""+data[i].productName+"\" href=\""+$www+"/products/"+data[i].productId+".html\"><img alt=\""+data[i].productName+"\" src=\""+$img+data[i].headImage+"\"></a></div>"+
								"<div class=\"willstart-list-desc\">"+
								"<h4><a title=\""+data[i].productTitle+"\" href=\""+$www+"/products/"+data[i].productId+".html\">"+data[i].productName+"</a></h4>"+
								"<ul class=\"willstart-list-info\">"+
								"<li>市场参考价：<span class=\"rmb\">"+data[i].productPrice+".00</span></li>"+
								"<li>已有<span class=\"num\">"+data[i].currentBuyCount+"</span>人次参与</li>"+
								"<li>总需<em>"+data[i].productPrice+"</em>人次参与</li>"+
								"<li class=\"buybtn\"><span class=\"btn-skin-orange\"><a href=\""+$www+"/products/"+data[i].productId+".html\">立即1元夺宝</a></span></li></ul>"+
							"</div></div>").appendTo($(".willstart-list"));
						}
					}
				});
				upcomingAnnounced = true;
			}
		};
		
		var share = false;
		function shareShow(){
			var top = $(".share-list-wrap").offset().top;
			if(!share && $(window).scrollTop() + $(window).height() > top ){
				$.ajax({
					url:"/share/ajaxPage.action?pageNo=1&pageSize=6&id=new20",
					type:"GET",
					data:"JSON",
					success:function(data){
						for(var i=0;i<data.length;i++){
							$("<li>"+
							  "<dl><dt><a href=\""+$post+"/shareShow/"+data[i].uid+".html\"><img alt=\"\" src=\""+$img+data[i].shareImages+"\"></a></dt>"+
							  "<dd class=\"share-goods-name\"><a title=\""+data[i].shareTitle+"\" href=\""+$post+"/shareShow/"+data[i].uid+".html\">"+data[i].shareTitle+"</a></dd>"+
							  "<dd>获得者："+data[i].user+"</dd><dd>揭晓时间："+data[i].announcedTime+"</dd></dl>"+
							  "</li>").appendTo($(".share-list-wrap > ul"));
						}
					}
				});
				share = true;
			}
		};
		
		function getParticipatePage(){
			var resultCount = $("#resultCount").val();
		    var pageNo = 0;
		    var id = $("#productId").val();
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
		        items_per_page:20,
		        callback: pageselectCallback
		    });
		    function pageselectCallback(pageNo) {
		    	var url = "/products/ajaxPage.action?id="+id+"&pageNo="+pageNo;
		    	$(".pageUL").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\""+$img+"/Images/loding.gif\" /></li>");
		        $.ajax({
		            url: url,
		            type: "post",
		            data: "json",
		            beforeSend:loading,
		            success: function (data) {
		            	$("#ParticipateTbody").empty();
		            	$(".pageUL").prepend("<li class=\"total_page\">页次<i>"+(pageNo+1)+"/"+Math.ceil(resultCount/20)+"</i>&nbsp;&nbsp;共<i>"+resultCount+"</i>条记录</li>");
		            	for(var i=0;i<data.length;i++){
	                        $("<tr><td class=\"t-id\">"+data[i].userName+"</td>"+
							"<td class=\"t-ip\">"+data[i].ip_address+"<span>("+data[i].ip_location+")</span></td>"+
							"<td class=\"t-numb\">"+data[i].buyCount+"人次</td>"+
							"<td class=\"t-time\">"+data[i].buyDate+"</td>"+
							"</tr>").appendTo($("#ParticipateTbody"));
		            	}
		            	$("#pageLoading").hide();
		            }
		        });
		    }
		}
		function loading() {
			$("#pageLoading").show();
		}
		if($("#id_boxmenu_2").attr("class")=="menu-on"){
			getParticipatePage();
		}
		$(window).scroll(upcomingAnnouncedShow);
		$(window).scroll(shareShow);
		$(".jqzoom").imagezoom();
		$("#mycarousel li").eq(0).addClass("curr");
		$("#mycarousel li a").click(function () {
	      $(this).parents("li").addClass("curr").siblings().removeClass("curr");
		  $(".jqzoom").attr('src',$(this).find("img").attr("mid"));
		  $(".jqzoom").attr('rel',$(this).find("img").attr("big"));
	  });
	  
	  $.ajax({
	  	url:"/products/getProductNewList.action?id="+$("#productId").val(),
	  	data:"string",
	  	type:"get",
	  	success:function(data){
	  		var html = "<ul>";
	  		for(string in data){
	  			html += "<li><a href=\""+$www+"/products/"+string+".html\">第 "+data[string]+" 期</a></li>";
	  		}
            html += "</ul>";
            $(html).appendTo(E);
	  	}
	  });
	$.getScript($skin+"/js/goodsdetailpicfun.js",function(){mycarousel()});
});