<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="template_footer" />
    <title><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>成功_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/cartlist.css" />
  </head>
  
  <body>
 <body id="loadingPicBlock">
       <div class="w1190 clrfix">
            <!--头部-->
            <div class="g-special-head">
                <a href="/" class="f-logo fl transparent-png"></a>
                <a href="/" title="继续<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>" class="f-carryOn fr">继续<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %></a>
            </div>

            <div id="divResult"  class="g-main-con g-results-bor clrfix">
            	<div id="proDiv" style="display: none;">
	            <c:choose>
		   			<c:when test="${fn:length(successCartList)>0}">
						<div class="g-results-success"><i class="z-arrows"></i><dl><dt><b>支付成功！请耐心等待揭晓结果！</b></dt><dd><a title="查看<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录" href="/user/UserBuyList.html">查看<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</a><a title="继续<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>" href="/">继续<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %></a></dd></dl></div>
						<div class="g-resulst-prompt"><span>${fn:length(successCartList)} 件商品<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>成功</span><b></b></div>
						<div class="g-results-info clrfix">
						<dl><dt><span class="u-results-name">商品名称</span><span class="u-results-time"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>时间</span><span class="u-results-visitors"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>人次</span><span class="u-results-code"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码</span></dt>
						<s:iterator value="successCartList" var="successCart">
						<dd><span class="u-results-name"><a target="_blank" title="(第${successCart.productPeriod }期) ${successCart.productName }" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${successCart.spellbuyProductId }.html">(第${successCart.productPeriod }期) ${successCart.productName }</a></span><span class="u-results-time">${successCart.buyDate}</span><span class="u-results-visitors">${successCart.buyCount}</span><span class="u-results-code"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/user/UserBuyDetail-${successCart.spellbuyProductId }.html" target="_blank">查看详情</a></span></dd>
						</s:iterator>
						</dl></div>
			           	
		   			</c:when>
		   			<c:otherwise>
						<div class="g-results-success"><i class="z-arrows"></i><dl><dt><b>支付成功！</b></dt><dd><a title="查看充值记录" href="/user/UserBalance.html">查看充值记录</a><a title="继续<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>" href="/">继续<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %></a></dd></dl></div>
		   			</c:otherwise>
		   		</c:choose>
		   		</div>
                <div class="g-results-loading">
                    <span>正在提交数据，请稍候……</span>
                    <b></b>
                </div>

                <div id="divWx" class="g-wx-banner" style="display: none;">
                    <img alt="" src="/Images/wx-banner.jpg"/>
                </div>

            </div>
        </div>
    <link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css" />
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/shopok.js"></script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js"></script>
    <div class="pageDialogBG" id="pageDialogBG"></div>
	<div class="pageDialogBorder" id="pageDialogBorder"></div>
	<div class="pageDialog" id="pageDialog">
		<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
		<div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
	</div>
</body>
</html>
