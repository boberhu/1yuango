$(document).ready(function(){var b=$skin;$("li","#ProductList").hover(function(){$(this).addClass("Cursor")},function(){$(this).removeClass("Cursor")});var c=$("#buyList");$.ajax({url:"/getNowBuyProduct.action?pageNo="+1+"&pageSize="+12,type:"GET",data:"JSON",success:function(f){var d="";for(var a=0;a<f.length;a++){d+='<li><a href="'+$www+"/u/"+f[a].userId+'.html" title="'+f[a].productName+'" class="pic" target="_blank">';d+='<img src="'+f[a].headImage+'" /></a>';d+='<p class="Rtagou"><a class="blue" target="_blank" href="'+$www+"/u/"+f[a].userId+'.html">'+f[a].buyer+"</a>\u521a\u521a"+$shortName+"\u4e86</p>";d+='<p class="Rintro"><a class="gray01" href="'+$www+"/products/"+f[a].productId+'.html" target="_blank">'+f[a].productName+"</a></p></li>"}c.find("li:gt("+(c.find("li").length-f.length-1)+")").remove();c.append(d)}});var i=function(){c.prepend(c.find("li:last")).css("marginTop","-68px");c.animate({marginTop:"0px"},800)};var e=setInterval(i,3000);c.hover(function(){clearInterval(e);e=null},function(){e=setInterval(i,3000)});$.ajax({url:"/lottery/upcomingAnnouncedByTop.action?pageNo="+1+"&pageSize="+7,type:"get",data:"json",success:function(d){for(var a=0;a<d.length;a++){if(a>=0&&a<=2){if(a==0){$('<li class="list-block"><div name="detailsinfo" style=""><b>1</b><div class="pic"><a rel="nofollow" href="'+$www+"/products/"+d[a].productId+'.html" target="_blank" title="'+d[a].productTitle+'"><img alt="'+d[a].productName+'" src="'+$img+d[a].headImage+'"></a></div><p class="name"><a class="gray01" href="'+$www+"/products/"+d[a].productId+'.html" target="_blank" title="'+d[a].productTitle+'">'+d[a].productName+'</a></p><p class="money">\u4ef7\u503c\uff1a<span class="rmb">'+d[a].productPrice+'.00</span></p><div class="Progress-bar" style=""><p title="\u5df2\u5b8c\u6210'+((d[a].currentBuyCount/d[a].productPrice)*100).toFixed(2)+'%"><span style="width:'+((d[a].currentBuyCount/d[a].productPrice)*100).toFixed(2)+'%;"></span></p><ul class="Pro-bar-li"><li class="P-bar01 list-block"><em>'+d[a].currentBuyCount+'</em>\u5df2\u53c2\u4e0e\u4eba\u6b21</li><li class="P-bar02"><em>'+d[a].productPrice+'</em>\u603b\u9700\u4eba\u6b21</li><li class="P-bar03"><em>'+(d[a].productPrice-d[a].currentBuyCount)+'</em>\u5269\u4f59\u4eba\u6b21</li></ul></div><div class="shop_buttom"><a class="go_Shopping" title="\u7acb\u5373'+$saitName+'" target="_blank" href="'+$www+"/products/"+d[a].productId+'.html">\u7acb\u5373'+$saitName+'</a></div></div><div name="simpleinfo" style="display:none;"><b>1</b><span class="pic"><img alt="'+d[a].productName+'" src="'+$img+d[a].headImage+'"></span><p class="Rintro gray01">'+d[a].productName+"</p><p><i>\u5269\u4f59\u4eba\u6b21</i><em>"+(d[a].productPrice-d[a].currentBuyCount)+"</em></p></div></li>").appendTo("#RcenterH")}else{$('<li class=""><div style="display: none;" name="detailsinfo"><b>'+(a+1)+'</b><div class="pic"><a title="'+d[a].productTitle+'" target="_blank" href="'+$www+"/products/"+d[a].productId+'.html" rel="nofollow"><img alt="'+d[a].productName+'" src="'+$img+d[a].headImage+'"></a></div><p class="name"><a title="'+d[a].productTitle+'" target="_blank" href="'+$www+"/products/"+d[a].productId+'.html" class="gray01">'+d[a].productName+'</a></p><p class="money">\u4ef7\u503c\uff1a<span class="rmb">'+d[a].productPrice+'.00</span></p><div style="" class="Progress-bar"><p title="\u5df2\u5b8c\u6210'+((d[a].currentBuyCount/d[a].productPrice)*100).toFixed(2)+'%"><span style="width:'+((d[a].currentBuyCount/d[a].productPrice)*100).toFixed(2)+'%;"></span></p><ul class="Pro-bar-li"><li class="P-bar01"><em>'+d[a].currentBuyCount+'</em>\u5df2\u53c2\u4e0e\u4eba\u6b21</li><li class="P-bar02"><em>'+d[a].productPrice+'</em>\u603b\u9700\u4eba\u6b21</li><li class="P-bar03"><em>'+(d[a].productPrice-d[a].currentBuyCount)+'</em>\u5269\u4f59\u4eba\u6b21</li></ul></div><div class="shop_buttom"><a href="'+$www+"/products/"+d[a].productId+'.html" target="_blank" title="\u7acb\u5373'+$saitName+'" class="go_Shopping">\u7acb\u5373'+$saitName+'</a></div></div><div style="display: block;" name="simpleinfo"><b>'+(a+1)+'</b><span class="pic"><img alt="'+d[a].productName+'" src="'+$img+d[a].headImage+'"></span><p class="Rintro gray01">'+d[a].productName+"</p><p><i>\u5269\u4f59\u4eba\u6b21</i><em>"+(d[a].productPrice-d[a].currentBuyCount)+"</em></p></div></li>").appendTo("#RcenterH")}}else{$('<li class=""><div style="display: none;" name="detailsinfo"><div class="pic"><a title="'+d[a].productTitle+'" target="_blank" href="'+$www+"/products/"+d[a].productId+'.html" rel="nofollow"><img alt="'+d[a].productName+'" src="'+$img+d[a].headImage+'"></a></div><p class="name"><a title="'+d[a].productTitle+'" target="_blank" href="'+$www+"/products/"+d[a].productId+'.html" class="gray01">'+d[a].productName+'</a></p><p class="money">\u4ef7\u503c\uff1a<span class="rmb">'+d[a].productPrice+'.00</span></p><div style="" class="Progress-bar"><p title="\u5df2\u5b8c\u6210'+((d[a].currentBuyCount/d[a].productPrice)*100).toFixed(2)+'%"><span style="width:'+((d[a].currentBuyCount/d[a].productPrice)*100).toFixed(2)+'%;"></span></p><ul class="Pro-bar-li"><li class="P-bar01"><em>'+d[a].currentBuyCount+'</em>\u5df2\u53c2\u4e0e\u4eba\u6b21</li><li class="P-bar02"><em>'+d[a].productPrice+'</em>\u603b\u9700\u4eba\u6b21</li><li class="P-bar03"><em>'+(d[a].productPrice-d[a].currentBuyCount)+'</em>\u5269\u4f59\u4eba\u6b21</li></ul></div><div class="shop_buttom"><a href="'+$www+"/products/"+d[a].productId+'.html" target="_blank" title="\u7acb\u5373'+$saitName+'" class="go_Shopping">\u7acb\u5373'+$saitName+'</a></div></div><div style="display: block;" name="simpleinfo"><span class="pic"><img alt="'+d[a].productName+'" src="'+$img+d[a].headImage+'"></span><p class="Rintro gray01">'+d[a].productName+"</p><p><i>\u5269\u4f59\u4eba\u6b21</i><em>"+(d[a].productPrice-d[a].currentBuyCount)+"</em></p></div></li>").appendTo("#RcenterH")}}$("li","#RcenterH").hover(function(){$(this).addClass("list-block").siblings().removeClass("list-block");$("div[name='detailsinfo']",$(this)).show();$("div[name='simpleinfo']",$(this)).hide();$("div[name='detailsinfo']",$(this).siblings()).hide();$("div[name='simpleinfo']",$(this).siblings()).show()},function(){})}});var j=",";var k=function(){var d=false;var a=0;var l=(function(f){if(f!=null){var g=function(h){var o=$("#ProductList");var q=h.length>14?14:h.length;for(var p=h.length-1;p>=(h.length-q);p--){if(j.indexOf(","+h[p].lotteryProductId+",")<0){j+=h[p].lotteryProductId+",";var r="";r+='<li class="lottery">';r+='<s class="lottery-tips"></s>';r+='<a class="fl goodsimg" href="/products/'+h[p].lotteryProductId+'.html" target="_blank" title="(\u7b2c'+h[p].lotteryProductPeriod+"\u671f)"+h[p].lotteryProductName+'">';r+='<img src="'+$img+h[p].lotteryProductImg+'" border="0" alt="'+h[p].lotteryProductName+'" width="140" />';r+="</a>";r+='<div class="publishC-tit">';r+="<h3>";r+='<a href="/products/'+h[p].lotteryProductId+'.html" target="_blank" class="gray01">(\u7b2c'+h[p].lotteryProductPeriod+"\u671f)"+h[p].lotteryProductName+"</a>";r+="</h3>";r+='<p class="money">\u5546\u54c1\u4ef7\u503c\uff1a<span class="rmb">'+h[p].lotteryProductPrice+"</span></p>";r+="</div>";r+='<div id="jiexiaoTime">';r+='<div class="Pour-time jiexiaoTime">';r+="<b><em>\u63ed&nbsp;&nbsp;\u6653</em>\u5012\u8ba1\u65f6</b>";r+="<ol>";r+='<li class="Dig"><span class="minute">00</span></li>';r+="<li>:</li>";r+='<li class="Dig"><span class="second">00</span></li>';r+="<li>:</li>";r+='<li class="Dig"><span class="millisecond">0</span><span class="last">0</span></li>';r+="</ol>";r+="</div>";r+="</div>";r+='<div class="details">\u5373\u5c06\u63ed\u6653\uff0c\u656c\u8bf7\u671f\u5f85...</div>';r+="</li>";r=$(r);o.children("li:last").remove();o.prepend(r);r.StartTimeOut(h[p].lotteryProductId,parseInt(h[p].lotteryProductEndDate))}}};if(d){g(f)}else{$.getScript(b+"/js/lotterytime.js",function(){d=true;g(f)})}}setTimeout(m,5000)});var m=function(){return;$.ajax({url:"/lottery/lotteryproductutilList.html",type:"GET",data:"json="+new Date().getTime(),success:function(f){if(f!=null){l(f)}}})};m()};k();$(".scrollLoading").scrollLoading()});
