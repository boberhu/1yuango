<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@page import="com.tech.util.DateUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>提交订单</title>
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/mycart.css" />
	</head>

	<body>
	<div class="logo">
        <div class="float">
            <span></span><span class="tel"></span>
        </div>
    </div>
    <div class="shop_payment">
        <ul class="payment">
            <li class="first_step orange_Tech">第一步：提交订单</li>
            <li class="arrow_3"></li>
            <li class="secend_step">第二步：网银支付</li>
            <li class="arrow_2"></li>
            <li class="third_step">第三步：支付成功</li>
            <li class="arrow_2"></li>
            <li class="fourth_step">第四步：获取授权系统</li>
            <li class="arrow_2"></li>
            <li class="fifth_step">第五步：交易成功</li>
        </ul>
	   	<div class="wait_list hidden" id="divResult" style="display: block;">
	   		<form action="/buySoft/goPay.html" method="post">
	   		<input type="hidden" name="integral" value="${integral }"/>
		 	<div class="wait_list_tips ">
		        <dl><dt><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/login_exp04.png" /></dt><dd>
		        <h2 class="orange Fb">订单创建成功！请尽快完成支付。</h2>
		        <p>订单信息如下：</p></dd></dl>
		    </div>
		     <div class="wait_listCon">
		        <ul>
		        <li class="wait_Ltit"><span class="wait_Cw01">订单时间</span><span class="wait_Cw02">商品名称</span><span class="wait_Cw03">商品价格</span></li>
		        <li class="wait_Lodity"><span class="wait_Cw01">${integral}</span><span class="wait_Cw02" style="color: #2af;">1元夺宝系统商业授权版</span><span class="wait_Cw03">${successCart.buyCount}</span><span class="wait_Cw04">￥5000元</span></li>
		        </ul>
           </div>
           
           	<div align="center" class="wait_list_tips ">
				<p align="center"><input type="submit" target="_blank" value="立即支付" style="padding: 8px 28px; font-size: 18px;-moz-border-bottom-colors: none;-moz-border-left-colors: none;-moz-border-right-colors: none;-moz-border-top-colors: none;background: none repeat scroll 0 0 #f60;border-color: #f60 #f60 #e85700;border-image: none;border-radius: 3px;border-style: solid;border-width: 1px;cursor: pointer;color: #fff;font-family: 微软雅黑,宋体;"/></p>
			</div>
			</form>
        </div>
    </div>
    <link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css" />
    <div class="pageDialogBG" id="pageDialogBG"></div>
	<div class="pageDialogBorder" id="pageDialogBorder"></div>
	<div class="pageDialog" id="pageDialog">
		<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
		<div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
	</div>
	</body>
</html>
