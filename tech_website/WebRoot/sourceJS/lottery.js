$(document).ready(function () {
    	$("li.likai").hover(function () {
			$(this).addClass("hover");
		}, function () {
			$(this).removeClass("hover");
		});
		$("div.luck").hover(function () {
			var C = $(this).children("div");
			C.eq(0).hide();
			C.eq(1).show();
		}, function () {
			var C = $(this).children("div");
			C.eq(0).show();
			C.eq(1).hide();
		});
    	
    	$.ajax({
    		url:"/lottery/upcomingAnnouncedByTop.action?pageNo="+1+"&pageSize="+8,
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
    	
		var H = $("#buyList");
		$.ajax({
			url:"/lottery/getNowBuyProduct.action?pageNo="+1+"&pageSize="+12,
			type:"GET",
			data:"JSON",
			success:function(data){
				var M = "";
				for (var L = 0; L < data.length; L++) {
					M += "<li><a rel='nofollow' href='"+$www+"/products/" + data[L].productId + ".html' class='pic' target='_blank'>";
					M += "<img src='"+$img+data[L].headImage + "'/></a>";
					M += "<span class='Rtagou'><p>" + data[L].buyer + "</p>\u521a\u521a\u62cd\u8d2d\u4e86</span>";
					M += "<span class='Rintro'><a rel='nofollow' href='"+$www+"/products/" + data[L].productId + ".html' target='_blank'>" + data[L].productName + "</a></span></li>";
				}
				H.find("li:gt(" + (H.find("li").length - data.length - 1) + ")").remove();
				H.append(M);
			}
		});
			var F = function () {
				H.prepend(H.find("li:last")).css("marginTop", "-68px");
				H.animate({"marginTop":"0px"}, 800);
			};
			var G = setInterval(F, 3000);
			H.hover(function () {
				clearInterval(G);
				G = null;
			}, function () {
				G = setInterval(F, 3000);
			});
			$(".scrollLoading").scrollLoading();
	});