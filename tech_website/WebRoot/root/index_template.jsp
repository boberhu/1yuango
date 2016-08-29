<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="d"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title><d:title default="egouos.com" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="shortcut icon" href="/favicon.ico" />
        <meta property="qc:admins" content="<%=ApplicationListenerImpl.sysConfigureJson.getQqVerify()%>" />
		<meta name="description" content="<%=ApplicationListenerImpl.sysConfigureJson.getDescription()%>" />
		<meta name="keywords" content="<%=ApplicationListenerImpl.sysConfigureJson.getKeyword()%>" />
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css2/header.css" />
		<script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery181.js"></script>
		<d:head />
		<script>
		var _hmt = _hmt || [];
		(function() {
		  var hm = document.createElement("script");
		  hm.src = "//hm.baidu.com/hm.js?8908e7d379072384d89956cb4c06d330";
		  var s = document.getElementsByTagName("script")[0]; 
		  s.parentNode.insertBefore(hm, s);
		})();
		</script>
	</head>

	<body <d:getProperty property="body.id" writeEntireProperty="true" />
		<d:getProperty property="body.class" writeEntireProperty="true" />
		<d:getProperty property="body.rf" writeEntireProperty="true" />>
		    <!-- 圣诞节效果 -->
<!--    <div id="divSnow" class="g-snow-con clrfix">-->
<!--        <div class="g-snow"></div>-->
<!--        <div class="g-snow2"></div>-->
<!--    </div>-->
<!--    <div class="g-christmas clrfix"></div>-->
<!--    <div class="g-christmas-con">-->
<!--        <div class="g-christmas-logo clrfix">-->
<!--            <a href="/"></a>-->
<!--        </div>-->
<!--    </div>-->
<!--    <canvas id="christmasSnow" style="position: fixed; top: 0px; left: 0px; z-index: 5000; pointer-events: none;"></canvas>-->
<div class="wrapper">
	<!--头部-->
	
	<!--顶部-->	
       <!--顶部-->
 <div class="g-toolbar">
     <div class="w1190">
         <ul class="fl">
             <li class="f-collect">
                 <div class="u-menu-hd">
                     <a id="btnTFavorite" href="javascript:;" title="收藏">收藏</a>
                 </div>
             </li>
             <li class="f-gap"><s></s></li>
             <li id="liMobile" class="u-arr">
                 <div class="u-menu-hd transparent-png">
                     <a href="#" target="_blank" title="手机夺宝">手机夺宝</a>
                     <div class="f-top-arrow"><cite>◆</cite><b>◆</b></div>
                 </div>
                 <div class="u-select">
                     <p>
                         <a href="#" target="_blank">
                             <img src="/Images/qrcode_wx.jpg" />
                             下载客户端
                         </a>
                     </p>
                 </div>
             </li>
             <li class="f-gap"><s></s></li>
             <li>
                 <div class="u-menu-hd">
                     <a href="/help/qqgroup.html" target="_blank" title="官方QQ群">官方QQ群</a>
                 </div>
             </li>
         </ul>
         <ul id="ulTopRight" class="fr">
             <li id="liMember" class="u-arr-1yyg">
                 <div class="u-menu-hd">
                     <a href="/user/index.html?forward=myUser" title="我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>">我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></a>
                     <div class="f-top-arrow"><cite>◆</cite><b>◆</b></div>
                 </div>
                 <div class="u-select">
                     <span><a href="/user/UserBuyList.html" title="<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>记录"><%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>记录</a></span>
                     <span><a href="/user/OrderList.html" title="获得的商品">获得的商品</a></span>
                     <span><a href="/user/MemberModify.html" title="个人设置">个人设置</a></span>
                 </div>
             </li>
             <li class="f-gap"><s></s></li>
             <li id="liTopUMsg" class="u-arr-news" style="display: none;">
                 <div class="u-menu-hd">
                     <a href="/user/userMessage.do" title="消息">消息</a>
                     <h3 style="display: none;"></h3>
                     <div class="f-top-arrow"><cite>◆</cite><b>◆</b></div>
                 </div>
                 <div class="u-select">
                     <span><a href="/user/FriendsApply.do" title="好友请求">好友请求</a></span>
                     <span><a href="/user/UserMessage.do" title="系统消息">系统消息</a></span>
                     <span><a href="/user/UserPrivMsg.do" title="私信" class="f-msg">私信</a></span>
                 </div>
             </li>
             <li class="f-gap" style="display: none;"><s></s></li>
             <li>
                 <div class="u-menu-hd">
                     <a href="/user/UserRecharge.html?forward=userRecharge" title="快速充值">快速充值</a>
                 </div>
             </li>
             <li class="f-gap"><s></s></li>
             <li>
                 <div class="u-menu-hd">
                     <a href="/help/whatPaigou.html" title="帮助">帮助</a>
                 </div>
             </li>
             <li class="f-gap"><s></s></li>
             <li class="f-zxkf">
                 <div class="u-menu-hd">
                     <a id="btnTopQQ" href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq()%>&site=qq&menu=yes" target="_blank" title="在线客服" class="u-service-off u-service"><i></i>在线客服</a>
                 </div>
             </li>
         </ul>
     </div>
 </div><!--头部-->
 <div class="g-header">
     <div class="w1190">
         <div id="topLogoAd" class="logo_1yyg fl">
             <a href="/" class="logo">
             <img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%><%=ApplicationListenerImpl.sysConfigureJson.getSaitLogo()%>" />
			</a>
         </div>
         <div class="search_cart_wrap fr">
             <div class="number">
                 <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/getNewRecord.html" target="_blank">
                     <ul id="ulHTotalBuy">

                     </ul>
                 </a>
             </div>
 
             <div class="search">
                 <div class="form">
                     <input id="txtHSearch" type="text" value="输入“手机”试试" />
                     <span></span>
                 </div>
                 <a id="btnHSearch" href="javascript:;" class="seaIcon"><i></i></a>
             </div>
         </div>
     </div>
 </div>
 
 <!--导航-->
 <div class="g-nav">
     <div class="w1190">
         <div id="divGoodsSort" class="m-menu fl">
             <div class="m-menu-all">
                 <h3><a href="${wwwUrl}/list/hot20.html">全部商品分类</a><em></em></h3>
             </div>
             <div id="divSortList" class="m-all-sort">
             	 <s:iterator var="protype" value="protypeList">
                 <dl>
                     <dt>
                     	<a href="<s:property value='wwwUrl'/>/list/hot20/<s:property value='#protype.typeId'/>.html">
                     		<s:property value='#protype.typeName'/>
                     	</a>
                     </dt>
                 </dl>
                 </s:iterator>
             </div>
             <%-- <div id="divSortList" class="m-all-sort">
                 <dl>
                     <dt><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1001.html">手机数码</a></dt>
                     <dd>
                         <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1020.html">手机</a>
                         <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1023.html">数码影像</a>
                         <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1022.html">单反相机</a>
                     </dd>
                 </dl>
                 <dl>
                     <dt><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1002.html">电脑办公</a></dt>
                     <dd>
                         <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1008.html">上网本</a>
                         <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1009.html">平板电脑</a>
                         <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1026.html">笔记本</a>
                     </dd>
                 </dl>
                 <dl>
                     <dt><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1003.html">家用电器</a></dt>
                     <dd>
                         <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1003.html">大家电</a>
                         <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1003.html">生活电器</a>
                         <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1003.html">个人护理</a>
                     </dd>
                 </dl>
                 <dl>
                     <dt><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1004.html">化妆个护</a></dt>
                     <dd>
                         <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1010.html">面部护理</a>
                         <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1019.html">香水</a>
                     </dd>
                 </dl>
                 <dl>
                     <dt><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1005.html">钟表首饰</a></dt>
                     <dd><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1005.html">珠宝首饰</a></dd>
                 </dl>
                 <dl>
                     <dt><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1007.html">其他商品</a></dt>
                     <dd>
                         <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1007.html">汽车</a>
                         <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/1007.html">旅游</a>
                     </dd>
                 </dl>
             </div> --%>
 
         </div>
         <div class="nav-main fl">
             <ul>
                 <li class="f-nav-home"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">首页</a></li>
                 <li class="f-nav-lottery"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lottery/index.html">最新揭晓</a></li>
                 <li class="f-nav-limit"><a href="/limitBuy/index.html">限购专区</a></li>
                 <li class="f-nav-share"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/share/new20.html">晒单分享</a></li>
                 <li class="f-nav-invite"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/referAuth.html">邀请</a></li>
                 <li class="f-nav-guide"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/index.html">新手指南</a></li>
             </ul>
         </div>
         <div id="divHCart" class="nav-cart fr">
             <div class="nav-cart-btn">
                 <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/mycart/index.html" target="_blank"><i class="f-cart-icon"></i>购物车<span id="spHCart">(0)</span></a>
             </div>
             <div class="nav-cart-con">
                 <div class="m-loading-2014"><em></em></div>
                 <div class="nav-car-cartEmpty"><i></i>您的购物车为空 !</div>
                 <div class="nav-cart-select"></div>
                 <div class="nav-cart-pay"></div>
             </div>
         </div>
     </div>
 </div>

		<d:body />

   <!--底部-->
 <div class="g-footer">
     <div class="w1190">
         <div class="g-guide clrfix">
             <dl>
                 <dt>新手指南</dt>
                 <dd><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/whatPaigou.html" title="了解<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>">了解<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></a></dd>
                 <dd><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/questionDetail.html" title="常见问题">常见问题</a></dd>
                 <dd><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/userExperience.html" title="会员积分经验值">会员积分经验值</a></dd>
             </dl>
             <dl>
                 <dt><%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>保障</dt>
                 <dd><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/genuinetwo.html" title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>保障体系"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>保障体系</a></dd>
                 <dd><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/securepayment.html" title="安全支付">安全支付</a></dd>
                 <dd><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/suggestion.html" title="投诉建议">投诉建议</a></dd>
             </dl>
             <dl>
                 <dt>商品配送</dt>
                 <dd><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/deliveryFees.html" title="配送费用">配送费用</a></dd>
                 <dd><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/prodCheck.html" title="商品验货与签收">商品验货与签收</a></dd>
                 <dd><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/shiptwo.html" title="长时间未收到商品">长时间未收到商品</a></dd>
             </dl>
             <dl>
                 <dt>关于<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></dt>
                 <dd><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/index.html" title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>介绍"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>介绍</a></dd>
                 <dd><a target="_blank" href="" title="微博"><%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>微博</a></dd>
             </dl>
             <dl>
                 <dt>携手<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></dt>
                 <dd><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/Links.html" title="友情链接">友情链接</a></dd>
                 <dd><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/qqgroup.html" title="官方QQ群交流">官方QQ群交流</a></dd>
             </dl>
         </div>
 
         <div class="g-service">
             <div class="m-ser u-ser1">
                 <ul>
                     <li><s class="u-icons"></s></li>
                     <li>
                         <dl>
                             <dt>下载安卓客户端</dt>
                             <dd><b class="u-icons"></b></dd>
                             <dd><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>#" title="立即下载">立即下载</a></dd>
                         </dl>
                     </li>
                 </ul>
             </div>
             <div class="m-ser u-ser2">
                 <ul>
                     <li><a href="javascript:;">
                         <img src="/Images/qrcode_wx.jpg" width="75"/></a></li>
                     <li>
                         <dl>
                             <dt><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>#"><i class="u-icons"></i>关注官方微信</a></dt>
                             <dd><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>#"><b class="u-icons"></b></a></dd>
                         </dl>
                     </li>
                 </ul>
             </div>
             <div class="m-ser u-ser3">
                 <ul>
                     <li><s class="u-icons"></s></li>
                     <li>
                         <dl>
                             <dt>服务器时间</dt>
                             <dd id="pServerTime">
                                 <span>00</span>
                                 <em>:</em>
                                 <span>00</span>
                                 <em>:</em>
                                 <span>00</span>
                             </dd>
                         </dl>
                     </li>
                 </ul>
             </div>
             <div class="m-ser u-ser4">
                 <ul>
                     <li><s class="u-icons"></s></li>
                     <li>
                         <dl>
                             <dt><%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>公益基金</dt>
                             <dd><a  href="javascript:;" id="spFundTotal">￥0.00</a></dd>
                         </dl>
                     </li>
                 </ul>
             </div>
             <div class="m-ser u-ser5">
                 <ul>
                     <li><s class="u-icons"></s></li>
                     <li>
                         <dl>
                             <dt>服务热线</dt>
                             <dd class="orange u-tel"><%=ApplicationListenerImpl.sysConfigureJson.getServiceTel()%></dd>
                             <dd><a id="btnBtmQQ" href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq()%>&site=qq&menu=yes" target="_blank" class="u-service-off u-service"><i></i>在线客服</a></dd>
                         </dl>
                     </li>
                 </ul>
             </div>
         </div>
 
         <div class="g-special clrfix">
             <ul>
                 <li>
                     <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/genuinetwo.html" target="_blank" title="100%公平公正">
                         <em class="u-spc-icon1"></em>
                         <span>100%公平公正</span>
                         参与过程公开透明，用户可随时查看
                     </a>
                 </li>
                 <li>
                     <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/genuine.html" target="_blank" title="100%正品保证">
                         <em class="u-spc-icon2"></em>
                         <span>100%正品保证</span>
                         精心挑选优质商家，100%品牌正品
                     </a>
                 </li>
                 <li>
                     <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/deliveryFees.html" target="_blank" title="全国免运费">
                         <em class="u-spc-icon3"></em>
                         <span>全国免运费</span>
                         全场商品全国包邮（港澳台地区除外）
                     </a>
                 </li>
             </ul>
         </div>
     </div>
 </div><!--版权-->
 <div class="g-copyrightCon">
     <div class="w1190">
         <div class="g-links">
             <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>" target="_blank" title="首页">首页</a><s></s>
             <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/index.html" target="_blank" title="关于<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>">关于<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%></a><s></s>
             <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/privacy.html" target="_blank" title="隐私声明">隐私声明</a><s></s>
             <%--
             <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/jobs.html" target="_blank" title="加入<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>">加入<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%></a><s></s>
             --%>
             <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/Links.html" target="_blank" title="友情链接">友情链接</a>
         </div>
         <div class="g-copyright"><%=ApplicationListenerImpl.sysConfigureJson.getIcp()%> </div>
         <div class="g-authentication">
             <a href="javascript:;"  class="fi_ectrustchina"></a>
             <a href="javascript:;"  class="fi_315online"></a>
             <%--
             <a href="javascript:;"  class="fi_qh"></a>
             --%>
             <a href="javascript:;"  class="fi_cnnic"></a>
             <a href="javascript:;"  class="fi_anxibao"></a>
             <%--
             <a href="javascript:;"  class="fi_pingan"></a>
             --%>
         </div>
     </div>
 </div>
 
<!--通栏购物车-->
<div id="rightTool" class="g-outer">
    <div class="g-status-standard">
        <div class="m-banner-list">
            <div class="u-sentence"><span></span></div>
            <!--工具项-->
            <div class="u-shortcut">
                <ul id="ulRToolList">
                    <li class="f-shopping-cart">
                        <!--购物车-->
                        <div class="u-float-list">
                            <a href="/mycart/index.html" target="_blank">
                                <i></i>
                                <em style="display: none;">0</em>
                                <span>购物车</span>
                            </a>
                            <b class="curr-arrow"></b>
                        </div>
                    </li>
                    <li class="f-attention">
                        <!--关注-->
                        <div class="u-float-list">
                            <a href="javascript:;" target="_blank">
                                <i></i>
                                <cite>我的关注</cite>
                            </a>
                            <b class="curr-arrow" style="display: none;"></b>
                        </div>
                    </li>
                    <li class="f-customer-service">
                        <!--客服-->
                        <div class="u-float-list">
                            <a id="btnRigQQ" href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq()%>&site=qq&menu=yes" target="_blank" class="z-customer-off"><i></i><cite>在线客服</cite></a>
                        </div>
                    </li>
                    <li class="f-client">
                        <!--客户端-->
                        <div class="u-float-list">
                            <a href="javascript:;"><i></i><cite>安卓APP</cite></a>
                            <b class="curr-arrow"></b>
                        </div>
                        <div class="u-activate">
                            <!-- <a href="#" target="_blank"> -->
                                <img src="/Images/qrcode_wx.jpg" />
                                下载客户端
                            </a>
                        </div>
                    </li>
                    <li class="f-weixin">
                        <!--微信-->
                        <div class="u-float-list">
                            <a href="javascript:;"><i></i><cite>官方微信</cite></a>
                            <b class="curr-arrow"></b>
                        </div>
                        <div class="u-activate">
                        <!-- <a href="#" target="_blank"> -->
                            
                                <img src="/Images/qrcode_wx.jpg" />
                                关注官方微信
                            </a>
                        </div>
                    </li>
                    <li class="f-feedback">
                        <!--意见反馈-->
                        <div class="u-float-list">
                            <a href="/help/suggestion.html" target="_blank"><i></i><cite>意见反馈</cite></a>
                        </div>
                    </li>
                    <li class="f-back-to">
                        <!--Top-->
                        <div class="u-float-list">
                            <a href="javascript:;"><i></i><cite>置顶</cite></a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <!--购物车列表-->
        <div id="divRTCartMain" class="m-unfold-cart clrfix">
            <!--头部-->
            <div class="f-unfold-title">
                <span class="fl">购物车</span>
                <cite class="fr"><a href="/mycart/index.html" target="_blank" title="全屏查看">全屏查看</a></cite>
            </div>
            <!--列表-->
            <div id="cart_container" class="f-unfold-con clrfix">
                <div id="cart_box" class="scrollcontent">
                    <div id="cart_shower" class="unfold-list"></div>
                </div>
            </div>
            <!--加载-->
            <div class="loading-2015 clrfix"><em></em></div>
            <!--购物车为空-->
            <div class="cartEmpty clrfix"><i></i>您的购物车为空 !</div>
            <!--支付-->
            <div class="f-unfold-pay clrfix"></div>
        </div>
        <!--登录-->
        <div id="divRTLogin" class="m-cart-login clrfix"></div>
        <!--关注-->
        <div id="divRTColect" class="m-unfold-cart clrfix" style="display: none; bottom: 0;">
            <!--头部-->
            <div class="f-unfold-title" style="display: block;">
                <span class="fl">我的关注</span>
                <cite class="fr"><a href="javascript:;" target="_blank" title="全屏查看">全屏查看</a></cite>
            </div>
            <!--列表-->
            <div id="colect_container" class="f-unfold-con clrfix">
                <div id="colect_box" class="scrollcontent" style="display: block;">
                    <div id="colect_shower" class="attentionCon"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--无购物车页面版-->
<div id="divRTool" class="g-narrow-con" style="display: none;">
    <div class="m-narrow-list">
        <ul>
            <li class="f-attention">
                <!--关注-->
                <div class="u-float-list">
                    <a href="javascript:;" target="_blank">
                        <i></i>
                        <cite>我的关注</cite>
                    </a>
                </div>
            </li>
            <li class="f-customer-service">
                <!--客服-->
                <div class="u-float-list">
                    <a id="btnRigQQ2" href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq()%>&site=qq&menu=yes" target="_blank" class="z-customer-on"><i></i><cite>在线客服</cite></a><!-- z-customer-off 客服不在线时 -->
                </div>
            </li>
            <li class="f-client">
                <!--客户端-->
                <div class="u-float-list">
                    <a href="#" target="_blank"><i></i><cite>安卓APP</cite></a>
                    <b class="curr-arrow"><s></s></b>
                </div>
                <div class="activateCon">
                    <div class="u-activate">
                        <a href="#" target="_blank">
                            <img src="/Images/qrcode_wx.jpg" />
                            下载客户端
                        </a>
                    </div>
                </div>
            </li>
            <li class="f-weixin">
                <!--微信-->
                <div class="u-float-list">
                    <a href="#" target="_blank"><i></i><cite>官方微信</cite></a>
                    <b class="curr-arrow"><s></s></b>
                </div>
                <div class="activateCon">
                    <div class="u-activate">
                        <a href="#" target="_blank">
                            <img src="/Images/qrcode_wx.jpg" />
                            关注官方微信
                        </a>
                    </div>
                </div>
            </li>
            <li class="f-feedback">
                <!--意见反馈-->
                <div class="u-float-list">
                    <a href="/help/suggestion.html" target="_blank"><i></i><cite>意见反馈</cite></a>
                </div>
            </li>
            <li class="f-back-to">
                <!--Top-->
                <div class="u-float-list">
                    <a href="javascript:;"><i></i><cite>置顶</cite></a>
                </div>
            </li>
        </ul>
    </div>
</div>
 
 </div>
<script type="text/javascript">
var $img = "<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>";
var $skin = "<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>";
var $www = "<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>";
var $domain = "<%=ApplicationListenerImpl.sysConfigureJson.getDomain()%>";
var $saitName = "<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>";
var $shortName = "<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>";
</script>
<script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/bottom.js?data=20150709"></script>
</body>
</html>
