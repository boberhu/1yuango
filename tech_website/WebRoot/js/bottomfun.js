var mainHttp=$www;var IsCartChanged=true;(function(){var v=mainHttp;var P=$www;var i=$www;var E=$skin;var j=$www;var s=function(){var W=$("#divRighTool");var X=$("body").attr("rf");var Z=W.width();var ab=5;var Y=980;var U=(Z+ab)*2+Y;var aa=false;var ac=function(){if(aa||$(window).scrollTop()>0){var ad=$(window).width();var ae=ad>U?((ad-Y)/2-Z-ab):ab;W.css("right",ae+"px").fadeIn("slow")}else{if(!aa){W.fadeOut("slow")}}};var V=function(){W.find("dd").eq(0).show();ac()};if(X=="2"){aa=true;V()}else{if(X=="1"){V()}}$(window).scroll(ac).resize(ac);setTimeout(ac,1000)};s();var M=$("#logininfo");var n=M.next();var J=n.find("div.h_1ypg_eject");n.hover(function(){J.show()},function(){J.hide()});var b=n.next();var A=$("#logininfo");var userId=getCookie("userId");var userName=getCookie("userName");var phone=getCookie("phone");var mail=getCookie("mail");var face=getCookie("face");if(A.length>0){if(userId!=null&&userId!=""){if(userName!=null&&userName!=""){A.html('<a class="gray01" href="'+$www+"/u/"+userId+'.html"><img src="'+face+'">'+userName+'</a> <a href="'+$www+'/logout/index.html">[\u9000\u51fa]</a>')}else{if(mail!=null&&mail!=""){A.html('<a class="gray01" href="'+$www+"/u/"+userId+'.html"><img src="'+face+'">'+mail+'</a> <a href="'+$www+'/logout/index.html">[\u9000\u51fa]</a>')}else{if(phone!=null&&phone!=""){A.html('<a class="gray01" href="'+$www+"/u/"+userId+'.html"><img src="'+face+'">'+phone+'</a> <a href="'+$www+'/logout/index.html">[\u9000\u51fa]</a>')}}}}else{A.html('<i>\u60a8\u597d\uff0c\u6b22\u8fce\u5149\u4e34\uff01</i><a class="gray01" href="'+$www+'/login/index.html?forward=rego" rel="nofollow">\u767b\u5f55</a><span>|</span><a class="gray01" href="'+$www+'/register/index.html?forward=rego" rel="nofollow">\u6ce8\u518c</a>')}}var a=$("#spBuyCount");var A=0;var c=function(){$.ajax({url:"/getAllBuyCount.html",data:"JSON",type:"GET",success:function(data){if(A!=data){if(A==0){A=data;a.html(A)}else{A=data;a.css({color:"white",background:"red"}).html(A);a.animate({opacity:0.1},{queue:false,duration:1000,complete:function(){a.show().css({color:"#22AAFF",background:"#F5F5F5",opacity:1})}})}}}});setTimeout(c,5000)};c();var h=function(aa){var Y="iPhone";var X=Y;var Z=$("#searchKey");if(Z.length>0&&Z.val()!=""){X=Z.val()}var W=function(){aa.removeClass("text").unbind("blur").bind("focus",V);if(aa.val()==""){aa.val(Y)}};var V=function(){aa.addClass("text").unbind("focus").bind("blur",W);if(aa.val()==Y){aa.val("")}};var U=function(){var ab=$.trim(aa.val());ab=ab.replace(/(\/)/g,"");ab=ab.replace(/(\%)/g,"");ab=ab.replace(/(\&)/g,"");if(ab==""){aa.removeClass("text").focus(V).val(Y)}else{location.href=mainHttp+"/search/hot20/"+encodeURIComponent(ab)+".html"}};aa.focus(V).keydown(function(ab){if(13==((window.event)?event.keyCode:ab.keyCode)){U()}}).val(X);$("#butSearch").click(U)};var q=$("#txtSearch");if(q.length>0){h(q)}var C=$("#addSiteFavorite");var F=$("#btnFavorite");if(C.length>0){var u=function(){var V="1\u5143\u62cd\u8d2d \u4eab\u53d7\u8d2d\u7269\u7684\u4e50\u8da3";var U=$www;try{window.external.addFavorite(U,V)}catch(e){try{window.sidebar.addPanel(V,U,"")}catch(e){alert("\u62b1\u6b49\uff0c\u60a8\u6240\u4f7f\u7528\u7684\u6d4f\u89c8\u5668\u65e0\u6cd5\u5b8c\u6210\u6b64\u64cd\u4f5c\u3002\n\n\u52a0\u5165\u6536\u85cf\u5931\u8d25\uff0c\u8bf7\u4f7f\u7528Ctrl+D\u8fdb\u884c\u6dfb\u52a0")}}};C.bind("click",function(){u()});F.bind("click",function(){u()}).hover(function(){$(this).addClass("Current")},function(){$(this).removeClass("Current")})}var R=$("#sCartNavi");var e=R.children().eq(1);var p=0;var m=0;var I=false;var K=false;var g=false;var D=$("#btnMyCart");var T=D.parent();var t=D.next();var r=t.find("ul");var z=t.find("p");D.hover(function(){D.addClass("Current")},function(){D.removeClass("Current")});r.hover(function(){D.addClass("Current")},function(){D.removeClass("Current")});var Q=function(U){if(U>=0){e.html(U);if(U>0){D.html("<b>\u8d2d\u7269\u8f66</b><s></s><em>"+(U>99?"N+":U)+"</em>")}else{D.html("<b>\u8d2d\u7269\u8f66</b><s></s>")}}};if(T.length>0){var G=$("#rCartLoading");T.hover(function(){if(I){return}I=true;var V=parseInt(e.html());if(V==0){return}var U=function(W){t.show();if(W){t.children().hide();G.show()}$.ajax({url:"/mycart/getMyProductCart.html",type:"GET",success:function(data){if(data.length>0){var Y=function(af,ae){z.html('\u5171\u8ba1<span id="rCartTotal2">'+af+'</span> \u4ef6\u5546\u54c1 \u91d1\u989d\u603b\u8ba1\uff1a<span class="rmbgray" id="rCartTotalM">'+ae+".00</span>").show()};if(data.length>0){p=data.length;var ac=data;var aa="";var ad=data.length;var ab=ad>8?7:(ad==8?8:ad);for(var Z=0;Z<ab;Z++){aa+='<li><a href="javascript:;" title="\u5220\u9664" class="Close"></a><a href="'+mainHttp+"/products/"+ac[Z].productId+'.html"><img src="'+$img+ac[Z].headImage+'" title="'+ac[Z].productTitle+'" ></a><span class="orange">'+ac[Z].count+'</span>\u4eba\u6b21<input type="hidden" value='+ac[Z].productId+'><input type="hidden" value='+ac[Z].count+"></li>"}if(ad>8){aa+='<li class="Roll_CartMore"><a target="_blank" title="\u67e5\u770b\u66f4\u591a" href="/mycart/index.html">\u66f4\u591a<i>></i></a></li>'}p=data[ad-1].productCount;m=data[ad-1].moneyCount;Y(p,m);Q(p);r.show().html(aa);G.hide()}$(".Close",r).bind("click",function(){var af=$(this).parent();var ae=af.find("input[type=hidden]").eq(0).val();var ag=af.find("input[type=hidden]").eq(1).val();var json=getCookie("products");if(json!=null||json!=""){json=eval("("+json+")");var i=0;$.each(json,function(n,value){if(ae==value.pId){i=n}});json.splice(i,1);json=JSON.stringify(json);SetCookieByExpires("products",json,1);IsCartChanged=true;if(p>8){Q(p-1);U(false)}else{p-=1;m-=ag;Q(p);if(p==0){t.hide()}else{Y(p,m);af.remove()}}}else{alert("\u5220\u9664\u5931\u8d25!");location.reload()}});$("#rGotoCart").unbind("click").bind("click",function(){location.href=mainHttp+"/mycart/index.html"}).parent().show()}else{Q(0);t.hide()}}});IsCartChanged=false};if(IsCartChanged){K=true;U(true)}else{if(g){U(true);g=false}else{t.show()}}},function(){I=false;D.next().hide()})}if(R.length>0){$.ajax({url:"/mycart/getProductCartCount.html",type:"GET",success:function(data){Q(data);var X=$("#sCartlist");var V=$("#sCartLoading");var Y=$("#p1");var U=function(){var Z=function(ab,aa){Y.html('\u5171\u8ba1 <span id="sCartTotal2"> '+ab+'</span> \u4ef6\u5546\u54c1 \u91d1\u989d\u603b\u8ba1\uff1a<span id="sCartTotalM" class="rmbred">'+aa+".00</span>")};X.show().find("dl").remove();V.show();$.ajax({url:"/mycart/getMyProductCart.html",data:"JSON",type:"GET",success:function(data){if(data.length>0){if(data[0].count>0){var ad=data;var ac="";for(var ab=0;ab<ad.length;ab++){ac+='<dl><dt class="img"><a href="'+mainHttp+"/products/"+ad[ab].productId+'.html"><img src="'+$img+ad[ab].headImage+'" /></a></dt><dd class="title"><a href="'+mainHttp+"/products/"+ad[ab].productId+'.html">'+ad[ab].productName+'</a><span class="rmbred">1.00 \xd7 '+ad[ab].count+'</span></dd><dd class="del"><a class="delGood" href="javascript:;" >\u5220\u9664</a></dd><input type="hidden" value='+ad[ab].productId+'><input type="hidden" value='+ad[ab].productCount+"></dl>"}p=data[(ad.length-1)].productCount;m=data[(ad.length-1)].moneyCount;Z(p,m);Q(p);V.hide();X.prepend(ac).find("dl").hover(function(){$(this).addClass("mycartcur")},function(){$(this).removeClass("mycartcur")})}$(".delGood",X).bind("click",function(){var af=$(this).parent().parent();var ae=af.find("input[type=hidden]").eq(0).val();var ag=af.find("input[type=hidden]").eq(1).val();var json=getCookie("products");if(json!=null||json!=""){json=eval("("+json+")");var i=0;$.each(json,function(n,value){if(ae==value.pId){i=n}});json.splice(i,1);json=JSON.stringify(json);SetCookieByExpires("products",json,1);IsCartChanged=true;af.remove();p-=1;m-=ag;Z(p,m);Q(p);if(p==0){X.hide()}else{X.show()}}else{alert("\u5220\u9664\u5931\u8d25!");location.reload()}});$("#sGotoCart").bind("click",function(){location.href=mainHttp+"/mycart/index.html"})}else{Q(0);X.hide()}}});IsCartChanged=false};$("#sCart").hover(function(){if(I){return}I=true;var Z=parseInt(e.html());if(Z==0){return}if(IsCartChanged){g=true;U()}else{if(K){U();K=false}else{X.show()}}},function(){I=false;X.hide()})}})}$("#btnRigQQ").hover(function(){$(this).addClass("Current")},function(){$(this).removeClass("Current")});$("#btnGotoTop").click(function(){$("body,html").animate({scrollTop:0},0);return false}).hover(function(){$(this).addClass("Current")},function(){$(this).removeClass("Current")});$(".go_cart").click(function(){IsCartChanged=true});var ab=$("#divWechat");var aj=ab.width();var ah=5;var ac=980;var ad=ab.children("a");var af=getCookie("_wxTip")!="off";var W=function(){if(af){ad.click(function(){ab.fadeOut("slow");SetCookie("_wxTip","off")});var al=$(window).width();var ak=(aj+ah)*2+ac;var am=al>ak?((al-ac)/2-aj-ah)-2:ah;ab.css("right",am+"px").fadeIn("slow")}};W()})();function SetCookie(a,b){document.cookie=a+"="+escape(b)+";id=1ypg;path=/;domain="+$domain}function SetCookieByExpires(b,d,a){var c=a;var e=new Date();e.setTime(e.getTime()+c*24*60*60*1000);document.cookie=b+"="+escape(d)+";id=1ypg;path=/;expires="+e.toGMTString()+";domain="+$domain}function getCookie(b){var a=document.cookie.match(new RegExp("(^| )"+b+"=([^;]*)(;|$)"));if(a!=null){return unescape(a[2])}return null}function delCookie(a){var c=new Date();c.setTime(c.getTime()-1);var b=getCookie(a);if(b!=null){document.cookie=a+"="+b+";id=1ypg;path=/;expires="+c.toGMTString()+";domain="+$domain}};