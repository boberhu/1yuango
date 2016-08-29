pageDialog = {
 PageDialog:function (content,title, wid,hei,bg,autoClose) {
			var H = $("#pageDialog");
			var L = $("#pageDialogBG");
			var N = $("#pageDialogBorder");
			var I = $("#pageDialogMain");
			var O = $("#pageDialogClose");
			O.click(function(){
				H.hide();
				L.hide();
				N.hide();
			});
			if(bg=="false"){
				L.hide();
			}else{
				L.show();
			}
			if(autoClose=="true"){
				window.setTimeout(function(){
				H.hide();
				L.hide();
				N.hide();
			},2000);
			}
			content = "<div class=\"content\">" + content + "</div>";
			if (title != "") {
				content = "<div class=\"title\">" + title + "</div>" + content;
			}
			I.html(content);
			var b_top = ((document.documentElement.clientHeight-hei)/2) + (document.documentElement.scrollTop)-hei/2;
			var b_left = ((document.documentElement.clientWidth-wid)/2);
			var c_top = Math.max(document.documentElement.scrollHeight, document.documentElement.clientHeight);
			var c_left = Math.max(document.documentElement.scrollWidth, document.documentElement.clientWidth);
			I.css({width:wid + "px", height:hei + "px"});
			H.css({"position":"absolute","top":b_top+"px","left":b_left+"px","border":1+"px solid","color":"#FF6600"});
			N.css({opacity:0.1, width:H.width()+16 + "px", height:H.height()+16 + "px","position":"absolute","top":b_top-6+"px","left":b_left-6 +"px"});
			L.css({"opacity":0.3,"position":"absolute","top":0,"left":0,"height":c_top+"px","width":c_left+"px"});
			H.show();
			N.show();
		}
};
jQuery(function ($) {
});

