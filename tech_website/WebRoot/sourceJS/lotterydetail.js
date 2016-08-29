var LotteryRFun = null;
var CodeListFun = null;
var CBLFun = null;
var _IsShowCalc = 0;
function showDetailBox(d) {
	var e = null;
	var c = null;
	for (var b = 1; b <= 10; b++) {
		e = $("#id_boxmenu_" + String(b));
		c = $("#id_boxcontents_" + String(b));
		if ((e.length > 0) && (c.length > 0) && (b != d)) {
			e.attr("class", "");
			c.attr("class", "pro-mainH");
		} else {
			if (b == d) {
				e.attr("class", "menuOn");
				c.attr("class", "pro-main");
			}
		}
	}
	return false;
}
$(document).ready(function () {
	var a = $("#hidCodeId").val();
	var k = 0;
	var g = $(document);
	var e = 646;
	var resultCount = $("#resultCount").val();
    var pageNo = 0;
    var productId = $("#productId").val();
    var spellbuyRecordId = $("#spellbuyRecordId").val();
    var spellbuyProductId = $("#spellbuyProductId").val();
	
    //购买人详情分页事件
    $("#pagination").pagination(resultCount, {
    	current_page:pageNo,
        prev_text: "上一页",
        next_text: "下一页",
        num_display_entries: 5,
        num_edge_entries:1,
        link_to:"",
        prev_show_always:false,
        next_show_always:false,
        items_per_page:10,
        callback: pageselectCallback
    });
    function pageselectCallback(pageNo) {
    	var url = "/lotteryDetail/getLotteryDetailBybuyerListAjaxPage.action?pageNo="+pageNo+"&id="+spellbuyProductId;
    	$(".pageUL").prepend("<li class=\"total_page\" id=\"pageLoading\"><img src=\""+$img+"/Images/loding.gif\" /></li>");
        $.ajax({
            url: url,
            type: "post",
            data: "json",
            beforeSend:loading,
            success: function (data) {
            	$(".infoMain").empty();
            	$(".pageUL").prepend("<li class=\"total_page\">页次<i>"+(pageNo+1)+"/"+Math.ceil(resultCount/10)+"</i>&nbsp;&nbsp;共<i>"+resultCount+"</i>条记录</li>");
            	for(var i=0;i<data.length;i++){
            		$("<dl><dt><img src=\""+$img+data[i].faceImg+"\"></dt>"+
            		"<dd><p><span>"+data[i].userName+"</span>总共夺宝了<em class=\"orange\">"+data[i].buyCount+"</em>人次</p><div>"+data[i].randomNumber+"</div></dd></dl>").appendTo($(".infoMain"));
            	}
            	$("#pageLoading").hide();
            }
        });
    };
    
    function loading(){
   		$("#pageLoading").show();
   	};
   	function loading2(){
   		$("#pageLoading2").show();
   	};
   	
   	$.ajax({
   		url:"/lotteryDetail/getNewProductResult.action?id="+productId,
   		type:"get",
   		data:"string",
   		success:function(data){
   			$("<p>本商品第 <em>"+data.productPeriod+"</em> 期正在进行中……</p><dl><dt>"+
			"<span>已有<em>"+data.currentBuyCount+"</em>人次参与</span><span>总需<i>"+data.productPrice+"</i>人次参与</span></dt>"+
			"<dd><a target=\"_blank\" href=\""+$www+"/products/"+data.productId+".html\"></a></dd></dl>").appendTo($(".infoRTBg"));
   		}
   	});
   	
   	//所有购买记录分页事件
   	$("#id_boxmenu_3").click(function(){
		var buyResultCount = $("#buyResultCount").val();
	    $("#pagination2").pagination(buyResultCount, {
	    	current_page:pageNo,
	        prev_text: "上一页",
	        next_text: "下一页",
	        num_display_entries: 5,
	        num_edge_entries:1,
	        link_to:"",
	        prev_show_always:false,
	        next_show_always:false,
	        items_per_page:20,
	        callback: pageselectCallback2
	    });
	    function pageselectCallback2(pageNo) {
	    	var url = "/lotteryDetail/buyListAjaxPage.action?pageNo="+pageNo+"&id="+spellbuyProductId;
	    	$(".pageULEx").prepend("<li class=\"total_page\" id=\"pageLoading2\"><img src=\""+$img+"/Images/loding.gif\" /></li>");
	        $.ajax({
	            url: url,
	            type: "post",
	            data: "json",
	            beforeSend:loading2,
	            success: function (data) {
	            	$("#tbodyContent").empty();
	            	$(".pageULEx").prepend("<li class=\"total_page\">页次<i>"+(pageNo+1)+"/"+Math.ceil(buyResultCount/20)+"</i>&nbsp;&nbsp;共<i>"+buyResultCount+"</i>条记录</li>");
	            	for(var i=0;i<data.length;i++){
	            		$("<tr><td class=\"t-id\">"+data[i].userName+"</td>"+
						"<td class=\"t-ip\">"+data[i].ip_address+"<span>("+data[i].ip_location+")</span></td><td class=\"t-numb\">"+data[i].buyCount+"人次</td><td class=\"t-time\">"+data[i].buyDate+"</td>"+
						"</tr>").appendTo($("#tbodyContent"));
	            	}
	            	$("#pageLoading2").hide();
	            }
	        });
	    };
   	});
	
	var j = function () {
		var u = $("#userRnoId");
			var m = u.children("dl");
			var q = 0;
			m.each(function () {
				q += $(this).height();
			});
			if (q > 144) {
				var s = function () {
					var w = $("#userRnoId", $(this).prev());
					if ($(this).attr("class") == "MaOpen") {
						k = g.scrollTop();
						w.attr("class", "MaCenterC");
						$(this).attr("class", "MaOff").html("<span>\u6536\u8d77<s></s></span>");
					} else {
						$(this).attr("class", "MaOpen").html("<span>\u5c55\u5f00\u67e5\u770b\u5168\u90e8 <s></s></span>");
						w.attr("class", "MaCenterH");
						g.scrollTop(k);
					}
				};
				$("#divOpen").bind("click", s).show();
			}else{
				$("#divOpen").hide();
			}
		if (window.location.hash == "#calculate") {
			showDetailBox(2);
			var n = $("div.infoMainC").offset();
			e = n.top;
			g.scrollTop(e);
		} else {
			showDetailBox(1);
		}
		$("a[href='#calculate']").click(function () {
			$("#divOpen").attr("class", "MaOpen").html("<span>\u5c55\u5f00\u67e5\u770b\u5168\u90e8 <s></s></span>");
			$("#userRnoId").attr("class", "MaCenterH");
			var n = $("div.infoMainC").offset();
			e = n.top;
			g.scrollTop(e);
			return false;
		});
	};
	$.ajax({
   		url:"/lottery/upcomingAnnounced.action?pageNo="+1+"&pageSize="+8,
   		type:"get",
   		data:"json",
   		success:function(data){
   			for(var i=0;i<data.length;i++){
   				if(i>=0 && i<=2){
   					$("<li>"+
					  "<a class=\"pic\" target=\"_blank\" title=\""+data[i].productName+"\" href=\""+$www+"/products/"+data[i].productId+".html\" rel=\"nofollow\">"+
					  "<img alt=\""+data[i].productName+"\" src=\""+$img+data[i].headImage+"\"></a>"+
					  "<span class=\"who\"><a target=\"_blank\" title=\""+data[i].productTitle+"\" href=\""+$www+"/products/"+data[i].productId+".html\">"+data[i].productName+"</a></span>"+
					  "<span>已有<em>"+data[i].currentBuyCount+"</em>人次参与</span><span>总需<i>"+data[i].productPrice+"</i>人次参与</span><b>"+(i+1)+"</b>"+
					  "</li>").appendTo($(".RcenterH"));
   				}else{
   					$("<li>"+
					  "<a class=\"pic\" target=\"_blank\" title=\""+data[i].productName+"\" href=\""+$www+"/products/"+data[i].productId+".html\" rel=\"nofollow\">"+
					  "<img alt=\""+data[i].productName+"\" src=\""+$img+data[i].headImage+"\"></a>"+
					  "<span class=\"who\"><a target=\"_blank\" title=\""+data[i].productTitle+"\" href=\""+$www+"/products/"+data[i].productId+".html\">"+data[i].productName+"</a></span>"+
					  "<span>已有<em>"+data[i].currentBuyCount+"</em>人次参与</span><span>总需<i>"+data[i].productPrice+"</i>人次参与</span>"+
					  "</li>").appendTo($(".RcenterH"));
   				}
   			}
   		}
   	});
	j();
});
