<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta name="decorator" content="index_template" />
		<title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>_商品列表页 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网,1元购物,1元购,一元购物</title>
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/limitbuy.css" />
	</head>

	<body class="limit" rf="2" id="loadingPicBlock">
    <div class="g-purchase">
        <div class="purchase-banner">
    	    <ul>
        	    <li class="pic01"></li>
                <li class="pic02"></li>
            </ul>
        </div>
        <div class="purchase-con clrfix">
    	    <div class="g-purchase-box clrfix">
               <!--限购商品中奖名单-->
               <div id="divLotteryList" class="m-roll" style="display:none;" >
                   <p><em>揭晓公告：</em></p>
                   <div class="congratulate">
                       <ul id="ulLotteryList" class="gray6"></ul>
                   </div>
                   <i><a href="/lottery/index.html" target="_blank">更多>></a></i>
               </div>
               <!--加载-->
               <div id="divLoading" class="g-loading"><b></b></div>
               <!--限购商品数据-->
               <div id="ulLimitGoodsList" class="m-purchase-list clrfix" style="display:none;">
              
               </div>
            </div>
	    </div>
    </div>
    
		<script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/limitbuy.js"></script>
		<script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/goodscomm.js"></script>
		<script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.scrollloading-min.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css" />
		<script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js"></script>
	    <div class="pageDialogBG" id="pageDialogBG"></div>
	    <div class="pageDialogBorder" id="pageDialogBorder"></div>
	    <div class="pageDialog" id="pageDialog">
	    	<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
	    	<div class="pageDialogMain" id="pageDialogMain"> </div>
	    </div>
	</body>
</html>
