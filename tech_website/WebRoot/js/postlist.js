$(function(){var a=$("#topid").val();if(a=="new20"){$("#new").attr("class","current")}else{if(a=="hot20"){$("#popularity").attr("class","current")}else{if(a=="reply20"){$("#comment").attr("class","current")}else{if(a=="elite20"){$("#elite").attr("class","current")}}}}var o=$("#hidOrderID").val();var f=$("#hidSortID").val();var c=$("#div_category");var l=c.children("p").children("a");var p=l.attr("href");var q=l.children("em").html();c.hover(function(){l.attr("href","/share/new20.html").children("em").html("\u5168\u90e8\u5206\u7c7b");$(this).addClass("category-hover").children("div").show()},function(){l.attr("href",p).children("em").html(q);$(this).removeClass("category-hover").children("div").hide()});var b="postHits";var h=r(b);if(h==null||h==""){h=","}$("div.m-single","#loadingSinglePic").hover(function(){var d=$(this).hasClass("m-single-special")?"single-hover single-hover2":"single-hover";$(this).addClass(d)},function(){var d=$(this).hasClass("m-single-special")?"single-hover single-hover2":"single-hover";$(this).removeClass(d)}).each(function(){var d=$(this).find("a.xianmu");var i=parseInt(d.attr("num"));var j=d.attr("postid");var g=function(){var k=$('<b class="gray9">\u5df2\u7fa1\u6155</b>');d.append(k);k.fadeTo(2000,0,function(){k.remove()})};var e=function(){if(j<=0){return}$.ajax({url:"/shareShow/upShareInfo.action?shareId="+j,type:"post",data:"string"});h=h+j+",";s(b,h,1);var k=function(){d.addClass("xianmu-click");var C=d.children("img");var A=C.width();var t=C.height();var m=C.offset().left;var B=C.offset().top;C.hide();var n=$('<img style="display: none" src='+C.attr("src")+">").prependTo("body");n.css({position:"absolute",left:m+"px",top:B+"px",width:A,height:t,"z-index":9999}).show();n.animate({width:A*2,height:t*2,left:m-A/2,top:B-t/2,opacity:0},700,function(){n.remove();d.children("em").html(i+1);d.removeClass("xianmu-click").addClass("xianmu-past")})};k()};if(h.indexOf(","+j+",")>=0){d.bind("click",function(){g()}).addClass("xianmu-past")}else{d.bind("click",function(){if(h.indexOf(","+j+",")>=0){g();return}e()})}});function s(e,i,d){var g=d;var j=new Date();j.setTime(j.getTime()+g*24*60*60*1000);document.cookie=e+"="+escape(i)+";id=1ypg;path=/;expires="+j.toGMTString()+";domain="+$domain}function r(e){var d=document.cookie.match(new RegExp("(^| )"+e+"=([^;]*)(;|$)"));if(d!=null){return unescape(d[2])}return null}$(".scrollLoading").scrollLoading()});
