<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
<!--		<meta name="decorator" content="index_template" />-->
		<title>最新揭晓地图模式 - <%=ApplicationListenerImpl.sysConfigureJson.getSaitName() %></title>
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/record.css" />
		<link rel="stylesheet" type="text/css" href="/css/map.css" />
    	<script language="javascript" type="text/javascript" src="/js/jquery123.js"></script>
	</head>

	<body>
		<div id="mainBox" class="wrapper">
        <div class="header">
            <div class="m_search">
                <a class="Fl logo" href="/" rel="nofollow">
                    <img title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName() %>" alt="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName() %>" src="<%=ApplicationListenerImpl.sysConfigureJson.getSaitLogo()%>" />
                </a>
                <div class="Fl m_search_text">
                    <ul>
                        <li class="m_type"><a href="javascript:;" title="地区" class="grayBtn hover">地区</a><a href="javascript:;" title="商品" class="grayBtn">商品</a><a href="javascript:;" title="昵称" class="grayBtn">昵称</a></li>
                        <li>
                            <input id="keyword" type="text" />
                            <input id="keywords" type="text" maxlength="100" style="display: none" />
                            <a href="javascript:;" title="搜索" class="orangeBtn">搜索</a></li>
                        <li class="m_name" id="liNav">
                            <a href="javascript:;" title="北京">北京</a>
                            <a href="javascript:;" title="上海">上海</a>
                            <a href="javascript:;" title="广州">广州</a>
                            <a href="javascript:;" title="深圳">深圳</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="gray9 m_total">截至目前共揭晓商品<em class="orange">${lotteryCount }</em>个，已确认地址<em class="orange">0</em>个
                <div id="helpIcon" class="m_ico">
                    <span></span>
                    <div id="helpDesc" class="m_tips">图标所示位置为获得者填写的配送地址，非<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>时IP地址，所示位置系百度地图自动标识，可能与实际地址有细小偏差。
                        <i><s></s></i>
                    </div>
                </div>
            </div>
        </div>

        <div class="content" style="overflow: hidden;">
            <s class="shadowTop"></s>
            <div class="MapHolder">
                <div id="allmap" style="height: 615px;"></div>
                <!--搜索结果-->
                <div class="MapInfoCon">
                    <div class="searchContent">
                        <div class="m_sort">
                            <ul>
                                <li>
                                     <a href="javascript:void(0);" sortid=0 title="全部分类">全部分类</a>
                                     <a href="javascript:void(0);" sortid=100 title="数码手机">数码手机</a>
                                     <a href="javascript:void(0);" sortid=106 title="电脑办公">电脑办公</a><a href="javascript:void(0);" sortid=104 title="家用电器">家用电器</a><a href="javascript:void(0);" sortid=2 title="化妆个护">化妆个护</a><a href="javascript:void(0);" sortid=397 title="食品饮料">食品饮料</a><a href="javascript:void(0);" sortid=213 title="家居家纺">家居家纺</a><a href="javascript:void(0);" sortid=222 title="钟表首饰">钟表首饰</a><a href="javascript:void(0);" sortid=251 title="礼品箱包">礼品箱包</a><a href="javascript:void(0);" sortid=276 title="运动户外">运动户外</a><a href="javascript:void(0);" sortid=312 title="其他商品">其他商品</a>
                                </li>
                            </ul>
                        </div>
                        <div class="m_searchTo">
                            <span style="display: none;"></span>
                            <div class="m_loading" style="display: none;">正在努力加载中...</div>
                            <ul></ul>
                        </div>
                    </div>
                    <div class="shadowRight"><s></s></div>
                    <div id="MapInfoTab" class="expand"><a href="javascript:void(0);"></a></div>
                </div>
            </div>
            <s class="shadowBottom"></s>
        </div>

        <!--底部-->
        <div class="g-frame copyright">
            <div class="footer_links">
                <a target="_blank" href="/">首页</a>
                <span></span>
                <a target="_blank" href="/about/index.html">关于<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %></a>
                <span></span>
                <a target="_blank" href="/help/privacy.html">隐私声明</a>
                <span></span>
                <a target="_blank" href="/about/jobs.html">加入<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %></a>
            </div>
            <div class="copyright">Copyright  &copy; 2011 - 2014,  版权所有  <%=ApplicationListenerImpl.sysConfigureJson.getDomain()%>  <%=ApplicationListenerImpl.sysConfigureJson.getIcp()%></div>
        </div>

        <div id="pageDialogBG" class="pageDialogBG" style="position: absolute; background: none repeat scroll 0 0 #999; left: 0; top: 0; z-index: 101; opacity: 0.3; filter: alpha(opacity:30); zoom: 1; width: 100%; height: 100%; display: none;"></div>
        <!--多个-->
        <div id="divMore" class="m_bigBox" style="display: none;">
            <div class="m_title"><b class="gray6" id="mTitle"></b><span>此位置已确认地址<em class="orange" id="orderCount">0</em>个</span><a id="moreClose" href="javascript:void(0);"></a></div>
            <div id="loading" class="win_loading" style="display: none;">
                <img alt="" src="/Images/goods_loading2.gif" />正在努力加载...</div>
            <div class="m_info un_blockCon">
                <ul></ul>
            </div>
        </div>
    </div>
    <script language="javascript" type="text/javascript">
        var Base = { head: document.getElementsByTagName("head")[0] || document.documentElement, Myload: function (B, A) { this.done = false; B.onload = B.onreadystatechange = function () { if (!this.done && (!this.readyState || this.readyState === "loaded" || this.readyState === "complete")) { this.done = true; A(); B.onload = B.onreadystatechange = null; if (this.head && B.parentNode) { this.head.removeChild(B) } } } }, getScript: function (A, C) { var B = function () { }; if (C != undefined) { B = C } var D = document.createElement("script"); D.setAttribute("language", "javascript"); D.setAttribute("type", "text/javascript"); D.setAttribute("src", A); this.head.appendChild(D); this.Myload(D, B) }, getStyle: function (A, B) { var B = function () { }; if (callBack != undefined) { B = callBack } var C = document.createElement("link"); C.setAttribute("type", "text/css"); C.setAttribute("rel", "stylesheet"); C.setAttribute("href", A); this.head.appendChild(C); this.Myload(C, B) } }
        function GetVerNum() { var D = new Date(); return D.getFullYear().toString().substring(2, 4) + '.' + (D.getMonth() + 1) + '.' + D.getDate() + '.' + D.getHours() + '.' + (D.getMinutes() < 10 ? '0' : D.getMinutes().toString().substring(0, 1)) }
        Base.getScript('/js/map.js?v=' + GetVerNum());
    </script>
	</body>
</html>
