<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>${keyword }_商品搜索_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
	<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/goodslist.css" />
  </head>
  
	<body rf="2">
		<input type="hidden" id="topid" value="${id }"/>
		<input type="hidden" id="resultCount" value="${resultCount }"/>
		<input type="hidden" id="keyword" value="${keyword }"/>
	<div class="g_list_Curtit w1190"><h1 class="fl">商品搜索－"${keyword }"</h1><span id="spTotalCount" class="po_p">(共找到<em class="ys">${resultCount }</em>件相关商品)</span></div>
    <div class="g-main-con clrfix">
       <div class="w1190">
           <!--排序-->
           <div class="m-filterForm clrfix">
               <div class="f-list-sorts fl">
                   <ul>
                       <li class="" id="about20"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/about20/<%= URLEncoder.encode(request.getParameter("keyword") ,"UTF-8") %>.html">即将揭晓</a></li>
                       <li class="" id="hot20"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/hot20/<%= URLEncoder.encode(request.getParameter("keyword") ,"UTF-8") %>.html">人气</a></li>
                       <li class="" id="surplus20"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/surplus20/<%= URLEncoder.encode(request.getParameter("keyword") ,"UTF-8") %>.html">剩余人次</a></li>
                       <li class="" id="date20"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/date20/<%= URLEncoder.encode(request.getParameter("keyword") ,"UTF-8") %>.html">最新</a></li>
                       <li class="m-value" id="price20"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/search/priceAsc20/<%= URLEncoder.encode(request.getParameter("keyword") ,"UTF-8") %>.html">价值<s></s></a></li>
                   </ul>
               </div>
               <div id="divTopPageInfo" class="f-list-sortsR fr"></div>
           </div>

           <!--列表-->
           <div id="ulGoodsList" class="announced-soon clrfix">
           	<c:choose>
					<c:when test="${resultCount==0}">
						<div class="g-noConMsg"><span>未找到有关“<em class="orange">${keyword }</em>”的商品</span></div>
					</c:when>
					<c:otherwise>
						<s:iterator value="ProductList" var="Products" status="i">
							<c:choose>
								<c:when test="${(i.index+1)%4==0}">
									<div goodsid="${Products.productId }" codeid="${Products.productId }" class="soon-list-con soon-list4" idx="${i.index+1}">
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${(i.index+1)==resultCount}">
											<div goodsid="${Products.productId }" codeid="${Products.productId }" class="soon-list-con f-add-borR" idx="${i.index+1}">
										</c:when>
										<c:otherwise>
											<div goodsid="${Products.productId }" codeid="${Products.productId }" class="soon-list-con" idx="${i.index+1}">
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
							<div class="soon-list"><ul><li class="g-soon-pic"><a title="${Products.productTitle }" target="_blank" href="/products/${Products.spellbuyProductId }.html">
							<img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${Products.headImage }" name="goodsImg" /></a></li>
							<li class="soon-list-name"><a title="${Products.productTitle }" target="_blank" href="/products/${Products.spellbuyProductId }.html">${Products.productName }</a></li>
							<li class="gray">价值：￥${Products.productPrice}.00</li><li class="g-progress"><dl class="m-progress"><dt title="已完成<fmt:formatNumber type="number" value="${(Products.currentBuyCount/Products.productPrice)*100 } " maxFractionDigits="2"/>%"><b style="width:<fmt:formatNumber type="number" value="${(Products.currentBuyCount/Products.productPrice)*100 } " maxFractionDigits="2"/>%"></b></dt><dd><span class="orange fl"><em>${Products.currentBuyCount }</em>已参与</span><span class="gray6 fl"><em>${Products.productPrice }</em>总需人次</span><span class="blue fr"><em>${Products.productPrice - Products.currentBuyCount }</em>剩余</span></dd></dl></li>
							<li name="buyBox"><a class="u-now" title="立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>" href="javascript:;">立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></a><a class="u-cart" href="javascript:;"><s></s></a></li></ul>
							<c:if test="${Products.productStyle=='goods_tj'}">
								<div class="f-callout"><span class="F_goods_tj"><i>推荐</i></span></div>
							</c:if>
							<c:if test="${Products.productStyle=='goods_xp'}">
								<div class="f-callout"><span class="F_goods_xp"><i>新品</i></span></div>
							</c:if>
							<c:if test="${Products.productStyle=='goods_rq'}">
								<div class="f-callout"><span class="F_goods_rq"><i>人气</i></span></div>
							</c:if>
							</div>
							<div class="u_buyCount" style="display: none;"></div></div>
						</s:iterator>
					</c:otherwise>
				</c:choose>
           </div>

       </div>
   </div>
${pageString }

		<!-- end -->
    <script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/searchlistfun.js"></script>
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
