
$(document).ready(function () {
	var id = $("#topid").val();
	if(id=="new20"){
		$("#new").attr("class","a_click");
	}else if(id=="hot20"){
		$("#popularity").attr("class","a_click");
	}else if(id=="reply20"){
		$("#comment").attr("class","a_click");
	}

	var $container = $('.goods_share_list');  
    $container.imagesLoaded( function(){
      $container.masonry({
        itemSelector : '.share_list_content'
      });  
    });  
	
	var C = null;
	$("a.smile_click").hover(function () {
		$(this).find("em").html("&nbsp;+1&nbsp;");
	}, function () {
		$(this).find("em").html("\u7fa1\u6155");
	}).bind("click", function () {
		C = $(this).attr("id");
		var I = $(this).parent().parent();
		var II = $(this).parent().parent().parent().parent();
			var J = II.find(".smile > em");
			var K = II.find(".smile > em").html();
			var E = getCookie("UP_"+C);
			if(E!="1"){
				J.html(parseInt(K) + 1);
				var L = $("<div class='ts'>\u4eb2\uff0c\u7fa1\u6155\u6210\u529f\u54e6\uff01</div>");
				I.append(L);
				SetCookieByExpires("UP_"+C,"1",1);
				L.fadeTo(3000, 0, function () {
					L.remove();
				});
				$.ajax({
					url:"/shareShow/upShareInfo.action?shareId="+C,
					type:"post",
					data:"string"
				});
			}else{
				var J = $("<div class='tsed'>\u60a8\u5df2\u7ecf\u7fa1\u6155\u8fc7\u4e86\uff01</div>");
				I.append(J);
				J.fadeTo(3000, 0, function () {
					J.remove();
				});
			}
	});
	
	function SetCookieByExpires(name,value,date) //存cookie 有过期时限
	{
	    var Days = date;
	    var exp  = new Date();
	    exp.setTime(exp.getTime() + Days*24*60*60*1000);
	    document.cookie = name + "="+ escape (value) + ";id=1ypg;path=/;expires="+exp.toGMTString()+";domain="+$domain;
	}
	function getCookie(name)//取cookies函数        
	{
	    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	     if(arr != null) return unescape(arr[2]); return null;
	}
	$(".scrollLoading").scrollLoading();
});
