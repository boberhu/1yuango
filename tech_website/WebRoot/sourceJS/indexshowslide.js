$(document).ready(function () {
	var banner = $(".banner");
	var I = $("#hidBuyID").val();
	var number = 1;
	var SI = $("#slideImg");
	var imgShow = [
//		"<a target=\"_blank\" href=\"/products/1003.html\"><img width=\"742\" height=\"333\" alt=\"苹果（Apple）iphone 4S 32G 3G手机3.5寸主屏 A5双核CPU 像素800万 iOS5操作系统 32G版\" src=\"/Images/index_1.jpg\"/></a>",
//		"<a target=\"_blank\" href=\"/products/1004.html\"><img width=\"742\" height=\"333\" alt=\"苹果（Apple）MacBook Air MC968CH/A 11.6英寸宽屏笔记本电脑高分辨率显示屏，FaceTime 摄像头，立体声扬声器！\" src=\"/Images/index_2.jpg\"/></a>",
//		"<a target=\"_blank\" href=\"/products/1003.html\"><img width=\"742\" height=\"333\" alt=\"三星（Samsung）I9100 3G手机（黑色）WCDMA/GSM安卓2.3系统，4.3英寸魔丽屏，1.2GHZ双核处理器，800万像素摄像头！\" src=\"/Images/index_3.jpg\"/></a>",
//		"<a target=\"_blank\" href=\"/products/1003.html\"><img width=\"742\" height=\"333\" alt=\"尼康（Nikon） D3100 单反套机 黑色 AF-S DX 18-55mm f/3.5-5.6G VR尼克尔镜头 1420万像素 3英寸\" src=\"/Images/index_4.jpg\"/></a>",
		"<a target=\"_blank\" href=\""+$www+"/products/10071.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\""+$img+"/Images/index_6.jpg\"/></a>",
		"<a target=\"_blank\" href=\""+$www+"/products/10073.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\""+$img+"/Images/index_7.jpg\"/></a>",
		"<a target=\"_blank\" href=\""+$www+"/products/10072.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\""+$img+"/Images/index_8.jpg\"/></a>",
		"<a target=\"_blank\" href=\""+$www+"/products/10074.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\""+$img+"/Images/index_9.jpg\"/></a>",
		"<a target=\"_blank\" href=\""+$www+"/products/10075.html\"><img width=\"742\" height=\"333\" alt=\"\" src=\""+$img+"/Images/index_11.jpg\"/></a>"
//		"<a target=\"_blank\" href=\"/products/1003.html\"><img width=\"742\" height=\"333\" alt=\"周生生黄金首饰！\" src=\"/Images/index_5.jpg\"/></a>"
		];
	for(var i=1;i<imgShow.length+1;i++){
		banner.find("ul").append("<li class=\"\">"+i+"</li>");
	}
	SI.html(imgShow[0]);
	banner.find("li").eq(0).attr("class","on");
	var E = function (c) {
		SI.css({"opacity":"0"});
		SI.html(imgShow[c]);
		number ++;
		SI.animate({"opacity":"1"}, 500);
		banner.find("li").attr("class","");
		banner.find("li").eq(c).attr("class","on");
		if(number==5){
			number = 0;
		}
	};
	var P = setInterval(function(){E(number)}, 5000);
	SI.hover(
	  function () {
	    clearInterval(P);
		P = null;
	  },
	  function () {
	    P = setInterval(function(){E(number)}, 5000);
	  }
	);
	banner.find("li").each(function(){
		var i = $(this).html();
		$(this).hover(
			function(){
				clearInterval(P);
				P = null;
				banner.find("li").attr("class","");
				$(this).attr("class","on");
				E(i-1);
				number = i;
				if(number==5){
					number = 0;
				}
			},
			function(){
				P = setInterval(function(){E(number)}, 5000);
			}
		);
	});
	var J = $("#buyList");
	$.ajax({
		url:"/getNowBuyProduct.action?pageNo="+1+"&pageSize="+6,
		type:"GET",
		data:"JSON",
		success:function(data){
			var M = "";
			for (var L = 0; L < data.length; L++) {
				M += "<li><a href=\""+$www+"/products/" + data[L].productId + ".html\" class=\"pic\" title=\""+data[L].productName+"\" target=\"_blank\"><img src=\""+$img+data[L].headImage+"\" /></a><span class=\"who\"><p style=\"display: inline;\">" + data[L].buyer + "</p>\u521a\u521a\u62cd\u8d2d\u4e86</span><span><a href=\""+$www+"/products/" + data[L].productId + ".html\" class=\"name\" title=\""+data[L].productTitle+"\" target=\"_blank\">" + data[L].productName + "</a></span></li>";
			}
			J.find("li:gt(" + (J.find("li").length - data.length - 1) + ")").remove();
			J.append(M);
		}
	});
	
	var C = function () {
		J.prepend(J.find("li:last")).css("marginTop", "-85px");
		J.animate({"marginTop":"0px"}, 800);
	};
	var B = setInterval(C, 3000);
	J.hover(function () {
		clearInterval(B);
		B = null;
	}, function () {
		B = setInterval(C, 3000);
	});
//	$(".new_lottery,.hot,.goods_show").find("li").hover(function () {
//		$(this).addClass("hover");
//	}, function () {
//		$(this).removeClass("hover");
//	});

//    var loaded = false;
//     function show(){
//      var top = $(".show_list").offset().top;
//      if(!loaded && $(window).scrollTop() + $(window).height() > top ){
//       		$.ajax({
//				url:"/share/ajaxPage.action?pageNo=1&pageSize=8&id=new20",
//				type:"GET",
//				data:"JSON",
//				success:function(data){
//					for(var i=0;i<data.length;i++){
//						if(i>=0 && i<=1){
//							$(".show_list").before("<dl><dt><a target=\"_blank\" href=\""+$post+"/shareShow/"+data[i].uid+".html\">"+
//		                      "<img alt=\"\" src=\""+$img+data[i].shareImages+"\"></a></dt>"+
//		                   	  "<dd class=\"bg\"><ul><li class=\"name\"><a target=\"_blank\" href=\""+$post+"shareShow/"+data[i].uid+".html\">"+data[i].shareTitle+"</a> 获得者：<span>"+data[i].user+"</span></li><li class=\"content\">"+data[i].shareContent+"…"+"</li></ul></dd>"+
//		               		  "</dl>");
//						}else{
//							$("<ul><li class=\"pic\"><a href=\""+$post+"/shareShow/"+data[i].uid+".html\">"+
//		                      "<img alt=\"\" src=\""+$img+data[i].shareImages+"\"></a></li>"+
//		                      "<li><a target=\"_blank\" title=\""+data[i].shareTitle+"\" href=\""+$post+"/shareShow/"+data[i].uid+".html\">"+data[i].shareTitle+"</a></li><li>获得者："+data[i].user+"</li><li>揭晓时间："+data[i].announcedTime+"</li>"+
//		                  	  "</ul>").appendTo($(".show_list"));
//						}
//					}
//				}
//			});
//			loaded = true;
//      	}
//     };
//     $(window).scroll(show);
     $(".scrollLoading").scrollLoading();
});

