<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@page import="com.tech.util.DateUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>支付结果</title>
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/mycart.css" />
	</head>

	<body>
	<div class="logo">
        <div class="float">
            <span class="logo_pic"><a title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>" class="a" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>"></a></span><span class="tel"></span>
        </div>
    </div>
    <div class="shop_payment">
        <ul class="payment">
            <li class="first_step">第一步：提交订单</li>
            <li class="arrow_2"></li>
            <li class="secend_step">第二步：网银支付</li>
            <li class="arrow_1"></li>
            <li class="third_step orange_Tech">第三步：支付成功</li>
            <li class="arrow_3"></li>
            <li class="fourth_step">第四步：获取授权系统</li>
            <li class="arrow_2"></li>
            <li class="fifth_step">第五步：交易成功</li>
        </ul>
	   	<div class="wait_list hidden" id="divResult" style="display: block;">
	   		<c:choose>
	   			<c:when test="${paymentStatus=='success'}">
   				 	<div class="wait_list_tips ">
				        <dl><dt><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/login_exp04.png" /></dt><dd>
				        <h2 class="orange Fb">恭喜您，订单已支付成功。</h2>
				        <p>订单信息如下：</p></dd></dl>
				    </div>
				     <div class="wait_listCon">
				        <ul>
				        <li class="wait_Ltit"><span class="wait_Cw01">订单时间</span><span class="wait_Cw02">商品名称</span><span class="wait_Cw03">商品价格</span></li>
				        <li class="wait_Lodity"><span class="wait_Cw01">${integral}</span><span class="wait_Cw02" style="color: #2af;">1元夺宝系统商业授权版</span><span class="wait_Cw03">${successCart.buyCount}</span><span class="wait_Cw04">￥5000元</span></li>
				        <li class="wait_Lodity"><span style="padding-left: 10px;width: 568px;color: #2af;">商业授权码：${userId }</span>请牢记，请联系客服获取软件。</li>
				        </ul>
		           </div>
	   			</c:when>
	   			<c:otherwise>
	   				<div class="wait_list_tips ">
				        <dl><dt><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/login_exp03.png" /></dt><dd>
				        <h2 class="orange Fb">哎呀！没有支付成功。</h2>
				        <p>您现在可以 <a class="blue" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/buySoft/index.html">查看订单</a> 或 <a class="blue" href="http://www.egouos.com">返回主页</a></p>
				        </dd></dl>
				    </div>
	   			</c:otherwise>
	   		</c:choose>
	   			<div align="center" class="wait_list_tips ">
					<p align="center"><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=52013594&site=qq&menu=yes" title="联系客服" style="padding: 8px 28px; font-size: 18px;-moz-border-bottom-colors: none;-moz-border-left-colors: none;-moz-border-right-colors: none;-moz-border-top-colors: none;background: none repeat scroll 0 0 #f60;border-color: #f60 #f60 #e85700;border-image: none;border-radius: 3px;border-style: solid;border-width: 1px;color: #fff;font-family: 微软雅黑,宋体;">联系客服</a></p>
				</div>
        </div>
    </div>
	</body>
</html>
