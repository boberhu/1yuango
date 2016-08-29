<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<title><d:title default="<%=ApplicationListenerImpl.sysConfigureJson.getSaitTitle() %>"/></title>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="shortcut icon" href="/favicon.ico" />
	<meta name="description" content="<%=ApplicationListenerImpl.sysConfigureJson.getDescription() %>" />
	<meta name="keywords" content="<%=ApplicationListenerImpl.sysConfigureJson.getKeyword() %>" />
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/comm.css" />
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/header.css" />
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery181.js"></script>
    <d:head/>
  </head>
  <body<d:getProperty property="body.id" writeEntireProperty="true" /><d:getProperty property="body.class" writeEntireProperty="true" />>
         <!--顶部-->
<!--头部-->
    <d:body/>
    <!-- 尾部开始 -->
<div class="footer">
	<div class="footer_links">
		<a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">首页</a>
		<b></b>
  		<a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/index.html">关于<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %></a>
		<b></b>
  		<a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/privacy.html">隐私声明</a>
		<b></b>
  		<a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/jobs.html">加入<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %></a>
		<b></b>
		<a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/about/Links.html">友情链接</a>
  	</div>
	<div class="copyright"> <%=ApplicationListenerImpl.sysConfigureJson.getIcp()%> </div>
</div>
<!-- 尾部结束 -->
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
                            <a href="/user/CollectList.html" target="_blank">
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
                            <a href="#" target="_blank">
                                <img src="/Images/qrcode_591jx.jpg" width="75"/>
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
                            <a href="#" target="_blank">
                                <img src="/Images/qrcode_591jx.jpg" width="75"/>
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
                <cite class="fr"><a href="/user/CollectList.html" target="_blank" title="全屏查看">全屏查看</a></cite>
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
                    <a href="/user/CollectList.html" target="_blank">
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
                            <img src="/Images/qrcode_591jx.jpg" width="75"/>
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
                            <img src="/Images/qrcode_591jx.jpg" width="75"/>
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


<div style="display: none;">
<script type="text/javascript">
var $img = "<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>";
var $skin = "<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>";
var $www = "<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>";
var $domain = "<%=ApplicationListenerImpl.sysConfigureJson.getDomain()%>";
var $saitName = "<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>";
var $shortName = "<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>";
</script>
<script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/bottom.js?data=20131121"></script>
</div>
  </body>
</html>
