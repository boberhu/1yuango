function lazyload(option){
	var settings={
	defObj:null,
	defHeight:0
	};
	settings=$.extend(settings,option||{});
	var defHeight=settings.defHeight,defObj=(typeof settings.defObj=="object")?settings.defObj.find("img"):$(settings.defObj).find("img");
	var pageTop=function(){
	return document.documentElement.clientHeight+Math.max(document.documentElement.scrollTop,document.body.scrollTop)-settings.defHeight;
	};
	var imgLoad=function(){
		defObj.each(function(){               
			if ($(this).offset().top<=pageTop()){
				var original=$(this).attr("src2");
				if (original){
					$(this).attr("src",original).removeAttr("src2");
				}
			}
		});
	};
//	imgLoad();
	$(window).bind("scroll",function(){
		imgLoad();
	});
}
$(function() {
	lazyload({defObj:"#allbody"});
});