<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>${sysConfigure.saitName}管理中心</title>
	<link href="/admin_css/global.css"  rel="stylesheet" />
	<link href="/admin_css/index.css"  rel="stylesheet" />
	<script language="javascript" type="text/javascript" src="/admin_js/jquery-1.8.3.min.js"></script>
	<script language="javascript" type="text/javascript" src="/admin_js/layer.min.js"></script>
	<script language="javascript" type="text/javascript" src="/admin_js/global.js"></script>
	<script type="text/javascript">
var ready=1;
var kj_width;
var kj_height;
var header_height=100;
var R_label;
var R_label_one = "当前位置: 系统设置 >";


function left(init){
	var left = document.getElementById("left");
	var leftlist = left.getElementsByTagName("ul");
	
	for (var k=0; k<leftlist.length; k++){
		leftlist[k].style.display="none";
	}
	document.getElementById(init).style.display="block";
}

$(function(){
	$("#nav li").each(function(obj){
		$(this).removeClass("current");
		if (!$(this).hasClass("normal")) {
			$(this).addClass("normal");
		}
	});
	$("#nav li:eq(0)").removeClass("normal").addClass("current");
});

function secBoard(elementID,n,init,r_lable) {
			$("#" + elementID + " li").each(function(){
				$(this).attr("class", "normal");
			});
			$("#" + elementID + " li:eq("+ n +")").attr("class", "current");
			R_label_one="当前位置: "+r_lable+" >";
			R_label.text(R_label_one);
			if ( n ==0 ) {
				$("#iframe_src").attr("src", init);
			} else {
				left(init);
			}
			
	/**var elem = document.getElementById(elementID);
	var elemlist = elem.getElementsByTagName("li");
	for (var i=0; i<elemlist.length; i++) {
		elemlist[i].className = "normal";		
	}
	elemlist[n].className = "current"; */
}


function set_div(){
		kj_width=$(window).width();
		kj_height=$(window).height();
		if(kj_width<1000){kj_width=1000;}
		if(kj_height<500){kj_height=500;}

		$("#header").css('width',kj_width); 
		// $("#header").css('height',header_height);
		$("#left").css('height',kj_height-header_height); 
	    //$("#right").css('height',kj_height-header_height); 
		//$("#left").css('top',header_height); 
		//$("#right").css('top',header_height);
		
		$("#left").css('width',180);		
		$("#right").css('width',kj_width-182); 
		$("#right").css('left',182);
		
		$("#right_iframe").css('width',kj_width-206); 
		$("#right_iframe").css('height',kj_height-148);
		 		
		$("#iframe_src").css('width',kj_width-208); 
		$("#iframe_src").css('height',kj_height-150); 	
		
		$("#off_on").css('height',kj_height-180);
		
		var nav=$("#nav");		
		nav.css("left",(kj_width-nav.get(0).offsetWidth)/2);
		//nav.css("top",61);
}


$(document).ready(function(){	
		set_div();		
		$("#off_on").click(function(){
				if($(this).attr('val')=='open'){
					$(this).attr('val','exit');
					$("#right").css('width',kj_width);
					$("#right").css('left',1);
					$("#right_iframe").css('width',kj_width-25); 
					$("iframe").css('width',kj_width-27);
					$("#left").hide();
				}else{
					$(this).attr('val','open');
					$("#right").css('width',kj_width-182);
					$("#right").css('left',182);
					$("#right_iframe").css('width',kj_width-206); 
					$("#left").show();
					$("iframe").css('width',kj_width-208);
				}
		});
		<#list modules as modu>
			left('${modu.url}');
			 <#break>
	  </#list>
		
		$(".left_date a").click(function(){
				$(".left_date li").removeClass("set");						  
				$(this).parent().addClass("set");
				R_label.text(R_label_one+' '+$(this).text()+' >');
				$("#iframe_src").attr("src",$(this).attr("src"));
		});
			
	     $("#iframe_src").attr("src","/admin_back/sysInfo.action");
		
		
		R_label=$("#R_label");
		$('body').bind('contextmenu',function(){return false;});
		$('body').bind("selectstart",function(){return false;});
				
});

function api_off_on_open(key){
	if(key=='open'){
			$("#off_on").attr('val','exit');
			$("#right").css('width',kj_width);
			//$("#right").css('left',1);
			$("#right_iframe").css('width',kj_width-25); 
			$("iframe").css('width',kj_width-27);
	}else{
			$("#off_on").attr('val','open');
			$("#right").css('width',kj_width-182);
			$("#right").css('left',182);
			$("#right_iframe").css('width',kj_width-206); 
			$("iframe").css('width',kj_width-208);
	}
}

</script>

<style>
.header_case{  position:absolute; right:10px; top:10px; color:#fff}
.header_case a{ padding-left:5px}
.header_case a:hover{ color:#fff; }
.left_date a{color:#444;}
.left_date{overflow:hidden;}
.left_date ul{ margin:0px; padding:0px;}
.left_date li a:hover{ background-color:#d3e8f2;text-decoration:none;border-radius:3px;}

</style>
  </head>
  <body  onResize="set_div();">
<div id="header">
	<div class="logo"  style="width: 500px;"><span>${sysConfigure.saitName}管理中心</span></div>
    <div class="header_case">
    欢迎您：<a title="test" href="javascript:;">${Session.admin.userName} [超级管理员]</a>
    <a title="退出" href="/admin_back/logOut.action">[退出]</a>
    <a target="_blank" title="网站首页" href="${sysConfigure.wwwUrl}">网站首页</a>
    <a target="_blank" title="官方网站" href="${sysConfigure.wwwUrl}">官方网站</a>
    </div>
    <div id="nav" class="nav" >
    	<ul>
    		<li class="normal"><a onclick="secBoard('nav', 0, '/admin_back/sysInfo.action', '后台首页')" " href="javascript:;">后台首页</a></li>
    		<#list modules as item>
    		<li <#if item_index=0>class="current"<#else>class="normal"</#if>><a onclick="secBoard('nav',${item_index+1},'${item.url}','${item.name}');" href="javascript:;">${item.name}</a></li>
    		</#list>
        </ul>
    </div>
</div>

<div id="left">
	
	<#list modules as modu>
	<#if modu.active?string="true" && Session._rights_key.contains(modu.url)>
	<ul id="${modu.url}" class="left_date" style="display: none;">
		<#list modu.child as cat>
		<#if cat.active?string="true" && Session._rights_key.contains(cat.url)>
		<li class="head">${cat.name}</li>
			<#list cat.child as menu>
			<#if menu.active?string="true" && Session._rights_key.contains(menu.url)>
			<li class=""><a src="${(menu.type=1)?string(('' + menu.url+'.action')!,menu.url!)}" href="javascript:;">${menu.name}</a></li>
			</#if> 
			</#list>
		</#if>
		</#list>
	</ul>
	</#if>
	</#list>
	
	 <!--<div style="padding:30px 10px; color:#ccc">
     	<p>
        	&copy; 2016 ${sysConfigure.domain}<br />
			All Rights Reserved.
        </p>
     </div> -->
</div>


<div id="right">
	<div class="right_top">
    	<ul id="R_label" class="R_label">当前位置: 系统设置 &gt; 后台首页 &gt;</ul>
<!--    	<ul class="R_btn">-->
<!--    	<a class="system_button" onclick="btn_iframef5();" href="javascript:;"><span>刷新框架</span></a>-->
<!--        <a class="system_button" onclick="btn_caches();" href="javascript:;"><span>清空缓存</span></a>    -->
<!--		<a class="system_button" target="_blank" href=""><span>提交工单</span></a>		-->
<!--        <a class="system_button" onclick="btn_map('');" href="javascript:;"><span>后台地图</span></a>-->
<!--        </ul>-->
    </div>
    <div class="right_left">
    	<a id="off_on" title="全屏" val="open" href="#">全屏</a>
    </div>
    <div id="right_iframe">
    
         <iframe frameborder="no" allowtransparency="yes" scrolling="auto" src="/admin_back/sysInfo.action" marginheight="0" marginwidth="0" border="1" class="iframe" name="iframe" id="iframe_src">
        hh </iframe>
        
    </div>
</div>
  </body>
</html>
