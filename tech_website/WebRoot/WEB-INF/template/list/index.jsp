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
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/goodslist.css" />
	</head>

	<body class="sort" rf="2" id="loadingPicBlock">
		<input type="hidden" id="topid" value="${id }" />
		<input type="hidden" id="typeId" value="${typeId }" />
		<input type="hidden" id="typeName" value="${typeName }" />
		<input type="hidden" id="resultCount" value="${resultCount }" />
		
		<!-- start -->
<div class="g-main-con clrfix">
<div class="w1190">
<div class="m-position">当前位置：<a href="/" title="首页">首页</a><span class="f-tran f-tran-prev f-tran-next">&gt;</span>${typeName }</div>
	            <!--分类 品牌-->
            <div class="m-list-classification clrfix">
                <div class="m-classification">
                    <div class="m-classi">分类</div>
                    <div class="m-fication">
                        <ul>
       					<c:choose>
							<c:when test="${tId!=null && tId!=''}">
								<li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20.html">全部分类</a></li>
								<s:iterator value="producttyList" var="producttys">
									<c:choose>
									<c:when test="${tId==producttys.typeId}">
										<li class="current"><a  href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/${producttys.typeId }.html">${producttys.typeName }</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/${producttys.typeId }.html">${producttys.typeName }</a></li>
									</c:otherwise>
								</c:choose>
								</s:iterator>
							</c:when>
							<c:otherwise>
								<li class="current"><a  href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20.html">全部分类</a></li>
								<s:iterator value="producttyList" var="producttys">
									<c:choose>
									<c:when test="${tId==producttys.typeId}">
										<li class="current"><a  href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/${producttys.typeId }.html">${producttys.typeName }</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/${producttys.typeId }.html">${producttys.typeName }</a></li>
									</c:otherwise>
								</c:choose>
								</s:iterator>
							</c:otherwise>
						</c:choose>
                        </ul>
                        <a href="javascript:;" class="expansion"></a>
                    </div>
                </div>

                <div id="dlBrandBox" class="m-classification">
                    <div class="m-classi">品牌</div>
                    <div class="m-fication">
                        <ul id="ulBrandList"  style="height:84px;">
                        	<c:choose>
								<c:when test="${brandId!=null && brandId!=''}">
									<li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20.html">全部</a></li>
									<s:iterator value="brandList" var="brands">
										<c:choose>
											<c:when test="${brandId==brands.typeId}">
												<li class="current"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/${tId }b${brands.typeId }.html">${brands.typeName }</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/${tId }b${brands.typeId }.html">${brands.typeName }</a></li>
											</c:otherwise>
										</c:choose>
									</s:iterator>
								</c:when>
								<c:otherwise>
									<li class="current"><a  href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20.html">全部</a></li>
									<s:iterator value="brandList" var="brands">
										<c:choose>
											<c:when test="${brandId==brands.typeId}">
												<li class="current"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/${tId }b${brands.typeId }.html">${brands.typeName }</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/list/hot20/${tId }b${brands.typeId }.html">${brands.typeName }</a></li>
											</c:otherwise>
										</c:choose>
									</s:iterator>
								</c:otherwise>
							</c:choose>
                        </ul>
                        <div class="f-a-expansion">
                            <a href="javascript:;" id="btnExpend" title="展开">展开
                                <div class="f-top-arrow"><cite>◆</cite><b>◆</b></div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <!--排序-->
            <div class="m-filterForm clrfix">
                <div class="f-list-sorts fl">
                    <ul>
   					<c:choose>
						<c:when test="${typeId!=null}">
							<li class="" id="about20"><a href="/list/about20/${typeId }.html">即将揭晓</a></li>
	                        <li class="" id="hot20"><a href="/list/hot20/${typeId }.html">人气</a></li>
	                        <li class="" id="surplus20"><a href="/list/surplus20/${typeId }.html">剩余人次</a></li>
	                        <li class="" id="date20"><a href="/list/date20/${typeId }.html">最新</a></li>
	                        <li id="price20" class="m-value"><a href="/list/priceAsc20/${typeId }.html">价值<s></s></a></li>
	                        <li class="" id="limit20"><a href="/list/limit20/${typeId }.html">限购</a></li>
<!--	                        <li name="liPrice" class="m-value m-value-default" style="display:none;"><a href="/list/priceAsc20/${typeId }.html">价值<s></s></a></li>-->
						</c:when>
						<c:otherwise>
							<li class="" id="about20"><a href="/list/about20.html">即将揭晓</a></li>
	                        <li class="" id="hot20"><a href="/list/hot20.html">人气</a></li>
	                        <li class="" id="surplus20"><a href="/list/surplus20.html">剩余人次</a></li>
	                        <li class="" id="date20"><a href="/list/date20.html">最新</a></li>
	                        <li  id="price20" class="m-value"><a href="/list/priceAsc20.html">价值<s></s></a></li>
	                        <li class="" id="limit20"><a href="/list/limit20.html">限购</a></li>
<!--	                        <li name="liPrice" class="m-value m-value-default" style="display:none;"><a href="/list/priceAsc20.html">价值<s></s></a></li>-->
						</c:otherwise>
					</c:choose>
                        
                    </ul>
                </div>
                <div id="divTopPageInfo" class="f-list-sortsR fr"></div>
            </div>

            <!--加载-->
<!--            <div id="divLoadingLine" class="g-loading-2014 w1190"><i></i></div>-->

            <!--列表-->
            <div id="ulGoodsList" class="announced-soon clrfix">
            	<c:choose>
					<c:when test="${resultCount==0}">
						<div class="g-noConMsg"><span>未找到有关“<em class="orange">${typeName} - ${ brandName }</em>”的商品</span></div>
					</c:when>
					<c:otherwise>
						<s:iterator value="ProductList" var="Products" status="i">
							<c:choose>
								<c:when test="${(i.index+1)%4==0}">
									<div goodsid="${Products.productId }" codeid="${Products.spellbuyProductId }" class="soon-list-con soon-list4" idx="${i.index+1}">
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${fn:length(ProductList)-(i.index+1)==0}">
											<div goodsid="${Products.productId }" codeid="${Products.spellbuyProductId }" class="soon-list-con f-add-borR" idx="${i.index+1}">
										</c:when>
										<c:otherwise>
											<div goodsid="${Products.productId }" codeid="${Products.spellbuyProductId }" class="soon-list-con" idx="${i.index+1}">
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
							<div class="soon-list"><ul><li class="g-soon-pic"><a title="${Products.productTitle }" target="_blank" href="/products/${Products.spellbuyProductId }.html">
							<img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${Products.headImage }" name="goodsImg" /></a></li>
							<li class="soon-list-name"><a title="${Products.productTitle }" target="_blank" href="/products/${Products.spellbuyProductId }.html">${Products.productName }</a></li>
							<li class="gray6">价值：￥${Products.productPrice}.00</li><li class="g-progress"><dl class="m-progress"><dt title="已完成<fmt:formatNumber type="number" value="${(Products.currentBuyCount/Products.productPrice)*100 } " maxFractionDigits="2"/>%"><b style="width:<fmt:formatNumber type="number" value="${(Products.currentBuyCount/Products.productPrice)*100 } " maxFractionDigits="2"/>%"></b></dt><dd><span class="orange fl"><em>${Products.currentBuyCount }</em>已参与</span><span class="gray6 fl"><em>${Products.productPrice }</em>总需人次</span><span class="blue fr"><em>${Products.productPrice - Products.currentBuyCount }</em>剩余</span></dd></dl></li>
							<li name="buyBox" limitBuy="${Products.spellbuyLimit }"><a class="u-now" title="立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>" href="javascript:;">立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></a><a class="u-cart" href="javascript:;"><s></s></a></li></ul>
							<c:if test="${Products.productStyle=='goods_tj'}">
								<div class="f-callout"><span class="F_goods_tj transparent-png"><i></i></span></div>
							</c:if>
							<c:if test="${Products.productStyle=='goods_xp'}">
								<div class="f-callout"><span class="F_goods_xp transparent-png"><i></i></span></div>
							</c:if>
							<c:if test="${Products.productStyle=='goods_rq'}">
								<div class="f-callout"><span class="F_goods_rq transparent-png"><i></i></span></div>
							</c:if>
							<c:if test="${Products.spellbuyLimit>0}">
								<div class="f-callout"><span class="F_goods_xg transparent-png"><i></i></span></div>
							</c:if>
							<div class="f-add-attention" style="display: none;"></div>
							</div>
							<div class="u_buyCount" style="display: none;"></div></div>
						</s:iterator>
					</c:otherwise>
				</c:choose>
            </div>
</div>
${pageString }
<!--	<div class="list_goodsTj" id="divGoodsRec">-->
<!--		<div class="list_goodsTjTit">-->
<!--			人气推荐-->
<!--		</div>-->
<!--		<div class="list_goodsTjCon" id="divRecommend">-->
<!--			<div class="Roll_Left">-->
<!--				<a href="javascript:;" name="left" class="Roll_enabled">&lt;</a>-->
<!--			</div>-->
<!--			<div class="Roll_Con">-->
<!--				<ul id="ulRecommend">-->
<!--				</ul>-->
<!--			</div>-->
<!--			<div class="Roll_Right">-->
<!--				<a href="javascript:;" name="right" class="Roll_enabled">&gt;</a>-->
<!--			</div>-->
<!--		</div>-->
<!--	</div>-->
<!--</div>-->
		<!-- end -->
		<script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/goodslist.js"></script>
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
