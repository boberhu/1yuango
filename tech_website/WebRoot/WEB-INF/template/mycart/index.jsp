<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="template_footer" />
    <title>购物车_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/cartlist.css" />
  </head>
  
 <body id="loadingPicBlock">
 <form id="toPaymentForm" name="toPaymentForm" action="/mycart/payment.html" method="post">
 <input name="hidLogined" type="hidden" id="hidLogined" value="${userId }" />
 <input name="hidCartState" type="hidden" id="hidCartState" value="1" />
  <input name="userPayType" type="hidden" id="userPayType" value="${userPayType }" />
 <input name="hidTotalMoney" type="hidden" id="hidTotalMoney" value="${moneyCount }" />
 <input type="hidden" value="" name="id" id="ids"/>
 </form>
        <div class="w1190 clrfix">
            <!--头部-->
            <div class="g-special-head">
                <a href="/"><img style="width: 122px;height: 74px;" src="<%=ApplicationListenerImpl.sysConfigureJson.getSaitLogo() %>"/></a>
                <a href="/" title="继续<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>" class="f-carryOn fr">继续<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %></a>
            </div>

            <div class="g-main-con clrfix">

                <div class="m-cart-title gray3"><b>我的购物车</b></div>
				<c:if test="${fn:length(productCartList)>0}">
					 <div id="divCartBox" class="m-cart-list gray9  clrfix">
	                    <dl id="dlCartList">
	                        <dt>
	                            <span class="f-cart-comm">商品</span>
	                            <span class="f-cart-price">剩余人次</span>
	                            <span class="f-cart-plusLess"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>人次</span>
	                            <span class="f-cart-subtotal">小计</span>
	                            <span class="f-cart-operate">操作</span>
	                        </dt>
	                        <!--有效商品-->
	                        <s:iterator var="productCarts" value="productCartList">
		                        	<dd codeid="${productCarts.productId }" >
	                                <ul>
	                                    <li class="f-cart-comm">
	                                        <cite><a isvalid="1" type="check" href="javascript:;" class="z-check" codeid="${productCarts.productId }"><b class='z-comms'></b></a></cite>
	                                        <cite class="u-cart-pic"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/product/${productCarts.productId }.html" target="_blank" title="${productCarts.productTitle }">
	                                            <img alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${productCarts.headImage }" /></a>
	                                            
	                                        </cite>
	                                        <cite class="u-cart-name">
	                                            
	                                            <span>
	                                                <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/product/${productCarts.productId }.html" target="_blank" title="${productCarts.productTitle }">(第${productCarts.productPeriod }期) ${productCarts.productName }</a>
	                                            </span>
	                                            价值：￥${productCarts.productPrice}.00
	                                        </cite>
	                                    </li>
	                                    <li class="f-cart-price">${productCarts.productPrice - productCarts.currentBuyCount }</li>
	                                    <li class="f-cart-plusLess"  >
	                                        <span>
	                                            <a href="javascript:;" class="z-arrows z-less"></a>
	                                            <input  name=num type="text" maxlength="7" value='${productCarts.count }' onpaste="return false" surplus="${productCarts.productPrice - productCarts.currentBuyCount }" limitbuy="${productCarts.productLimit }" mylimitsales="${productCarts.myLimitSales }" />
	                                            <a href="javascript:;" class="z-arrows z-plus"></a>
	                                        </span>
	                                    </li>
	                                    <li class="f-cart-subtotal orange" >￥${productCarts.count }.00</li>
	                                    
	                                    <li class="f-cart-operate fr"><a href="javascript:;" type="delete" class="z-comms" title="删除"></a></li>
	                                </ul>
	                            </dd>
	                        </s:iterator> 
	                        <!--已失效商品-->
	                        
	                    </dl>
	                    <div class="m-list-operating clrfix">
	                        <p class="fl">
	                            <span><a id="sSelAll" class="z-check"><b class="z-comms"></b></a></span>
	                            <span><a id="btnSelAll" href="javascript:;" title="全选">全选</a></span>
	                            <span><a id="btnDelete" href="javascript:;" title="删除">删除</a></span>
	                        </p>
	                        <cite class="fr">金额总计：<i class="orange" id="iTotalMoney">￥${moneyCount }.00</i></cite>
	                        <div class="f-list-accounts">
	                            <a href="/list/hot20.html" title="返回继续<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>" class="f-list-return">返回继续<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %></a>
	                            <a id="btnGoPay" href="javascript:;" title="去结算" class="f-list-jiesuan">去结算</a>
	                        </div>
	                    </div>
	                </div>
				</c:if>

	            <c:if test="${fn:length(productCartList)==0}">
					<div id="divEmpty" class="z-cart-nothing"></div>
	            </c:if>
                
<div id="divRec" class="m-rqtj-con clrfix">
    <p>人气推荐</p>
    <div id="divRecList" class="m-recomm-con clrfix">
    </div>
</div>

    </div>
</div>
    <link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css" />
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/cartlistfun.js"></script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js"></script>
    <div class="pageDialogBG" id="pageDialogBG"></div>
	<div class="pageDialogBorder" id="pageDialogBorder"></div>
	<div class="pageDialog" id="pageDialog">
		<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
		<div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
	</div>
</body>
</html>
