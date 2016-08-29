
(function($){
	$(function(){
		// QQ
		var qqItem = ["3175184851", "3175184851"];
		var itemList = "";
		for (var i = 0, len = qqItem.length; i < len; i++ ) {
			var value = qqItem[i];
			itemList += "<li><a target='_blank' href='tencent://message/?Menu=yes&Exe=在线客服&Uin="+ value +"'><img src='qq-service/images/qq.png' alt='一元夺宝OS客服' title='一元夺宝OS客服' /></a></li>";
		}
		itemList += "<li class='weixin'><img src='qq-service/images/weixin.jpg' width='96' alt='一元夺宝OS' /></li>";
		$(".keifu_con").html(itemList);
		
		var KF = $(".keifu");
		var wkbox = $(".keifu_box");
		var kf_close = $(".keifu .keifu_close");
		var icon_keifu = $(".icon_keifu");
		var kH = wkbox.height();
		var kW = wkbox.width();
		var wH = $(window).height();
		KF.css({height:kH});
		icon_keifu.css("top",parseInt((kH-200)/2));
		var KF_top = (wH-kH-200)/2;
		if(KF_top<0) KF_top=0;
		KF.css("top",KF_top);
		
		$(kf_close).click(function(){
			KF.animate({width:"0"},200,function(){
				wkbox.hide();
				icon_keifu.show();
				KF.animate({width:26},300);		
			});	
		});
		$(icon_keifu).click(function(){
				$(this).hide();
				wkbox.show();
				KF.animate({width:kW},200);
		});
	});
})( jQuery || window.jQuery);