index={
		SetCookie:function(name,value) //存cookie
		{
		    document.cookie = name + "="+ escape (value) + ";id=1ypg;path=/;domain="+$domain;
		},
		SetCookieByExpires:function(name,value,date) //存cookie 有过期时限
		{
		    var Days = date;
		    var exp  = new Date();
		    exp.setTime(exp.getTime() + Days*24*60*60*1000);
		    document.cookie = name + "="+ escape (value) + ";id=1ypg;path=/;expires="+exp.toGMTString()+";domain="+$domain;
		},
		getCookie:function(name)//取cookies函数        
		{
		    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
		     if(arr != null) return unescape(arr[2]); return null;
		},
		delCookie:function(name)//删除cookie
		{
		    var exp = new Date();
		    exp.setTime(exp.getTime() - 1);
		    var cval=index.getCookie(name);
		    if(cval!=null) document.cookie= name + "="+cval+";id=1ypg;path=/;expires="+exp.toGMTString()+";domain="+$domain;
		},
		sCartlist:function(){
			$.ajax({
				url:"/mycart/getMyProductCart.html",
				data:"JSON",
				type:"GET",
				success:function(data){
					if(data=="[]"){
						$("#sCartlist").hide();
					}else{
						$("#sCartLoading").hide();
						$("#sCartlist > dl").remove();
						var c ="";
						for (var b = 0; b < data.length; b++) {
							c += "<dl><dt class=\"img\"><a href=\""+$www+"/products/" + data[b].productId + ".html\" title=\""+data[b].productName + data[b].productTitle +"\"><img src=\""+$img+data[b].headImage+"\" /></a></dt><dd class=\"title\"><a href=\""+$www+"/products/" + data[b].productId + ".html \" title=\""+data[b].productName + data[b].productTitle +"\">" + data[b].productName + "</a><span class='rmbred'>1.00 \xd7 " + data[b].count + "</span></dd><dd class=\"del\"><a href=\"javascript:;\" class=\"delGood\">\u5220\u9664</a></dd><input type=\"hidden\" value=" + data[b].productId + "></dl>";
						}
						$("#sCartlist").prepend(c);
						$("#sCartTotalM").html(data[0].moenyCount+".00");
						$("#sCartTotal").html(data[0].productCount);
						$("#sCartTotal2").html(data[0].productCount);
					}
				}
			});
		},
		getProductCartCount:function(){
			$.ajax({
				url:"/mycart/getProductCartCount.html",
				type:"GET",
				success:function(data){
				  var D = data;
					if(D > 0){
						$("#sCartlist").show();
						$("#sCartTotal").html(D);
						$("#sCartTotal2").html(D);
						index.sCartlist();
					}else{
						$("#sCartlist").hide();
						$("#sCartTotal").html(0);
						$("#sCartTotal2").html(0);
					}
				}
			});
		},
	loginload:function(){
		/*回顶部*/
		(function(){
			var n=0;
			var obj=document.getElementById("TopLayout");
			if(!obj){return false;}
			var x=0;
			var fe=$("#TopLayout");
			window.onscroll=function(){
				obj.style.top=(document.body.scrollTop||document.documentElement.scrollTop)+n+'px';
				x=(document.body.scrollTop||document.documentElement.scrollTop)+n;
				if(x==0){fe.fadeOut().hide()}else{fe.fadeIn().show()};
			};
			window.onresize=function(){obj.style.top=(document.body.scrollTop||document.documentElement.scrollTop)+n+'px'};
		})();
		(function () {
			var G = "http://www.1ypg.com";
			var A = $("#logininfo");
			var userId = index.getCookie("userId");
			var phone = index.getCookie("phone");
			var mail = index.getCookie("mail");
			if (A.length > 0) {
				if(userId!=null && userId!=""){
					if(phone!=null && phone!=""){
						A.html("\u60a8\u597d\uff0c<span class=\"name\">" + phone + "</span>\u6b22\u8fce\u6765\u52301\u5143\u62CD\u8d2d\uff01<a href=\""+$www+"/logout/index.html\">[\u9000\u51fa]</a>");
					}else if(mail!=null && mail!=""){
						A.html("\u60a8\u597d\uff0c<span class=\"name\">" + mail + "</span>\u6b22\u8fce\u6765\u52301\u5143\u62CD\u8d2d\uff01<a href=\""+$www+"/logout/index.html\">[\u9000\u51fa]</a>");
					}
				}else{
					A.html("\u60a8\u597d\uff0c\u6b22\u8fce\u5149\u4e341\u5143\u62CD\u8d2d\uff01<span>[<a href=\""+$passport+"/login/index.html?forward=rego\"  class=\"login\">\u767b\u5f55</a>]</span><span>[<a href=\""+$passport+"/register/index.html?forward=rego\"  class=\"regsiter\">\u6ce8\u518c</a>]</span>");
				}
				//$("#spanFundTotal").html("");
			}
			var I = $("#slideSort");
			if (I.length > 0) {
				var O = I.children("div");
				I.hover(function () {
					O.show().prev().addClass("ico");
				}, function () {
					O.hide().prev().removeClass("ico");
				});
			}
			var K = function (Z) {
				var X = "\u8bf7\u8f93\u5165\u641c\u7d22\u5173\u952e\u5b57";
				var E = "亲,关键字太短哦!请重新输入!";
				var W = X;
				var Y = $("#searchKey");
				if (Y.length > 0 && Y.val() != "") {
					W = Y.val();
				}
				var V = function () {
					Z.removeClass("text").unbind("blur").bind("focus", U);
					if (Z.val() == "") {
						Z.val(X);
					}
				};
				var U = function () {
					Z.addClass("text").unbind("focus").bind("blur", V);
					if (Z.val() == X || Z.val()==E) {
						Z.val("");
					}
				};
				var T = function () {
					var a = $.trim(Z.val());
					if (a == "" || a == X || a == E) {
						Z.removeClass("text").focus(U).val(X);
					} else {
						if(a.length<2){
							Z.focus(U).val(E);
						}else{
							location.href = $www+"/search/hot20/" + encodeURIComponent(a.replace(/[ ]/g,""))+".html";
						}
					}
				};
				Z.focus(U).keydown(function (a) {
					if (13 == ((window.event) ? event.keyCode : a.keyCode)) {
						T();
					}
				}).val(W);
				$("#butSearch").click(T);
			};
			var F = $("#txtSearch");
			if (F.length > 0) {
				K(F);
			}
			var R = $("#sCartNavi");
			if (R.length > 0) {
				var D = 0;
				var H = false;
				$.ajax({
					url:"/mycart/getProductCartCount.html",
					type:"GET",
					success:function(data){
						D = data;
						$("#sCartTotal").html(D);
						H = true;
					}
				});
				$("#sCart").hover(function () {
					if (D > 0) {
						$("#sCartlist").show();
						R.css("border-bottom", "none");
						index.sCartlist();
					} else {
						$("#sCartlist").hide();
						index.getProductCartCount();
					}
				}, function () {
					$("#sCartlist").hide();
					R.css("border", "0px solid #ccc");
				});
				var W = $("#sCartlist");
				$(".delGood").live("click", function(){
					var f = $(this).parent().parent();
					var e = f.find("input[type=hidden]").eq(0).val();
					var json = index.getCookie("products");
					if(json!=null || json!=""){
						json = eval('('+json+')');
						var i=0;
						$.each(json,function(n,value) {
							if(e==value.pId){
								i=n;
							}
						});
						json.splice(i,1);
						json = JSON.stringify(json);
						index.SetCookieByExpires("products",json,1);
						index.getProductCartCount();
					}
				});
			}
			
			var L = $("#addSiteFavorite");
			if (L.length > 0) {
				L.bind("click", function () {
					var U = "1\u5143\u62CD\u8D2D | 1\u5143\u62FC\u8D2D - \u4EAB\u53D7\u8D2D\u7269\u7684\u6109\u5FEB";
					var T = "http://www.1ypg.com";
					if (window.sidebar) {
						window.sidebar.addPanel(U, T, "");
					} else {
						if (document.all) {
							window.external.addToFavoritesBar(T, U);
						} else {
							return true;
						}
					}
				});
			}
			var B = $("#ulTrundle");
			if (B.length > 0) {
				$.ajax({
					url:"/lottery/indexWinningScroll.action",
					type:"GET",
					data:"JSON",
					success:function(data){
						if(data!=null && data!=""){
							for (var U = 0; U < data.length; U++) {
								B.append("<li><a rel=\"nofollow\" href=\""+$www+"/products/" + data[U].spellbuyProductId + ".html\">\u606d\u559c&nbsp;[<span>" + data[U].buyUser + "</span>]&nbsp;\u6210\u529f\u62cd\u8d2d\u5230\u4e86" + data[U].productName + "</a></li>");
							}
						}
						var W = function () {
							B.animate({"marginTop":"-26px"}, 800, function () {
								B.append(B.find("li:first")).css("marginTop", "0");
							});
						};
						var V = setInterval(W, 3000);
						B.hover(function () {
							clearInterval(V);
							V = null;
						}, function () {
							V = setInterval(W, 3000);
						});
					}
				});
			}
		})();
//		var Z = index.getCookie("referralHAD");
//		if (Z != "off") {
//			var V = "<div style=\"background:#5fd1ff;\"><div style=\"background:url('"+$img+"/Images/bannerAdTest.png') center no-repeat; width:980px; height:40px; margin:0 auto; position:relative; \"><a id=\"referADLink\" style=\" display:block; width:980px; height:40px;\" title=\"\u7acb\u5373\u9080\u8bf7\u597d\u53cb\" target=\"_blank\" href=\"http://news.1ypg.com/news/1000.html\"></a><a class=\"closeBtn\" style=\" display:block; width:12px; height:11px; position:absolute; right:8px; top:1px; z-index:5;\" title=\"\u5173\u95ed\" href=\"JavaScript:gotoClick();\"></a></div></div>";
//			var X = $(V);
//			var W = function () {
//				X.find("a.closeBtn").click(function () {
//					index.SetCookie("referralHAD","off");
//					X.remove();
//				});
//			};
//			$("body").prepend(X).ready(W);
//		}
	}
};

jQuery(function($) {
	index.loginload();
	$(".live800link").click(function(){
   		location.replace("tencent://message/?uin=52013594&Site=1元夺宝&Menu=yes")
   	});
});