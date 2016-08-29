<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="template_footer" />
    <title>支付_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/payment.css" />
  </head>
  
  <body>
        <div class="w1190 clrfix">
            <!--头部-->
            <div class="g-special-head">
                <a href="/" ><img style="width: 122px;height: 74px;" src="<%=ApplicationListenerImpl.sysConfigureJson.getSaitLogo() %>"/></a>
                <a href="/" title="继续<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>" class="f-carryOn fr">继续<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %></a>
            </div>

            <div class="g-main-con clrfix">
                <div class="m-cart-title gray3"><b>订单信息</b></div>

                <!--商品列表-->
                <div id="divCartList" class="m-pay-list gray9 clrfix">
                    <dl>
                        <dt>
                            <span class="f-pay-comm">商品</span>
                            <span class="f-pay-price">剩余人次</span>
                            <span class="f-pay-plusLess"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>人次</span>
                            <span class="f-pay-subtotal">小计</span>
                        </dt>
                        <s:iterator var="productCarts" value="productCartList">
                            <dd>
                                <ul>
                                    <li class="f-pay-comm">
                                        <cite class="u-pay-pic">
                                            <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${productCarts.productId }.html" target="_blank" title="${productCarts.productTitle }">
                                                <img alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${productCarts.headImage }" />
                                            </a>
                                        </cite>
                                        <c:choose>
                                        	<c:when test="${productCarts.productStyle=='3'}">
                                        		<cite class="u-pay-name limitbuy ">
		                                            <em class="limitbuy-icon"></em>
		                                            <a title="${productCarts.productTitle }" target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${productCarts.productId }.html">(第${productCarts.productPeriod }期) ${productCarts.productName }</a></cite>
                                        	</c:when>
                                        	<c:otherwise>
                                        		<cite class="u-pay-name  ">
                                            	<a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${productCarts.productId }.html" target="_blank" title="${productCarts.productTitle }">(第${productCarts.productPeriod }期) ${productCarts.productName }</a></cite>
                                        	</c:otherwise>
                                        </c:choose>
                                    </li>
                                    <li class="f-pay-price">${productCarts.productPrice - productCarts.currentBuyCount}</li>
                                    <li class="f-pay-plusLess">${productCarts.count }人次</li>
                                    <li class="f-pay-subtotal orange">￥${productCarts.count }.00</li>
                                </ul>
                            </dd>
                         </s:iterator>
                        
                    </dl>
                </div>

                <input name="hidTotalCount" type="hidden" id="hidTotalCount" value="${productCarts.productCount }" />
                <!--展开按钮-->
                <div id="divExpand" class="g-pay-unfold clrfix transparent-png">
                    <a id="btnExpand" href="javascript:;"></a>
                </div>
                

                <!--积分与余额-->
                <div id="divPayWayBox" class="m-pay-Total clrfix">
                    <dl>
                        <dt>
                            <span class="fl"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/mycart/index.html" title="返回修改">返回修改</a></span>
                            <span class="fr">支付总额：<i class="orange">￥${productCarts.moneyCount }.00</i></span>
                        </dt>

                        <dd id="ddPointBox" class="f-pay-grayBg">
                            <cite style="display:none;">
                                <span id="spPoint" class="orange">-￥0.00</span>
                                <span class="u-pay-bixu"><em><b></b></em>必须为整数倍</span>
                                <span><input id="txtUsePoint" type="text"/></span>
                            </cite>
                            <span>使用积分支付，您的积分<i>${user.commissionPoints }</i></span>
                            <span><a id="checkPoint" href="javascript:;" class="z-check"></a></span>
                        </dd>
                        <dd id="ddBalanceBox" class="f-pay-grayBg">
                            <span id="spBalance" class="orange" style="display: none;">-￥0.00</span>
                            <span>使用账户余额支付，您的账户余额 ￥${user.balance }</span>
                            <span><a id="checkBalance" href="javascript:;" class="z-check"></a></span>
                        </dd>
                    </dl>
                </div>

                <!--网银-->
                <div id="divBankBox" class="m-pay-manner" style="display: none;">
                    <div class="f-pay-select">
                        <span class="fl" id="spPayTitle">您可使用账户余额支付，也可选择以下方式完成支付</span>
                        <span class="fr">使用网银支付<i id="iBankPay" class="orange">￥0.00</i></span>
                    </div>
                    <%--
                    <div class="f-pay-defray clrfix">
                        <p class="fl gray9">网银支付</p>
                        <div id="divBankList" class="fr f-pay-bank">
                            <cite>
                                <span class="current"><a id="btnCXK" href="javascript:;" title="储蓄卡支付">储蓄卡支付</a></span>
                                <span><a id="btnXYK" href="javascript:;" title="信用卡支付">信用卡支付</a></span>
                            </cite>
                            <!-- 储蓄卡 -->
                            <dl id="dlCXK" class="clrfix">
                                <!-- 1. 招商、工商、建行、农行、 中行-->
                                <dd class="checked">
                                    <input type="radio" value="3080" name="account" id="bankType3080" checked="checked" /><label for="bankType3080"><span class="zh_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="1025" name="account" id="bankType1025" /><label for="bankType1025"><span class="gh_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="105" name="account" id="bankType105" /><label for="bankType105"><span class="jh_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="103" name="account" id="bankType103" /><label for="bankType103"><span class="nh_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="104" name="account" id="bankType104" /><label for="bankType104"><span class="zg_bank"></span></label>
                                </dd>

                                <!-- 2. 交通、华夏、兴业、上海、民生 -->
                                <dd>
                                    <input type="radio" value="301" name="account" id="bankType301" /><label for="bankType301"><span class="jt_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="311" name="account" id="bankType311" /><label for="bankType311"><span class="hx_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="309" name="account" id="bankType309" /><label for="bankType309"><span class="xy_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="326" name="account" id="bankType326" /><label for="bankType326"><span class="sh_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="305" name="account" id="bankType305" /><label for="bankType305"><span class="ms_bank"></span></label>
                                </dd>

                                <!-- 3. 广发、平安、浦发、中信、光大 -->
                                <dd>
                                    <input type="radio" value="3061" name="account" id="bankType3061" /><label for="bankType3061"><span class="gf_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="307" name="account" id="bankType307" /><label for="bankType307"><span class="pa_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="314" name="account" id="bankType314" /><label for="bankType314"><span class="pf_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="313" name="account" id="bankType313" /><label for="bankType313"><span class="zx_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="312" name="account" id="bankType312" /><label for="bankType312"><span class="gd_bank"></span></label>
                                </dd>

                                <!-- 4. 南京、邮政、杭州、宁波、北京  -->
                                <dd>
                                    <input type="radio" value="316" name="account" id="bankType316" /><label for="bankType316"><span class="nj_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="3230" name="account" id="bankType3230" /><label for="bankType3230"><span class="yz_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="324" name="account" id="bankType324" /><label for="bankType324"><span class="hz_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="302" name="account" id="bankType302" /><label for="bankType302"><span class="lb_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="310" name="account" id="bankType310" /><label for="bankType310"><span class="bj_bank"></span></label>
                                </dd>

                                <!-- 5. 重庆农商、北京农商、青岛、成都  -->
                                <dd>
                                    <input type="radio" value="342" name="account" id="bankType342" /><label for="bankType342"><span class="cqls_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="335" name="account" id="bankType335" /><label for="bankType335"><span class="bj2_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="3341" name="account" id="bankType3341" /><label for="bankType3341"><span class="qd_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="336" name="account" id="bankType336" /><label for="bankType336"><span class="cd_bank"></span></label>
                                </dd>
                            </dl>

                            <!-- 信用卡 -->
                            <dl id="dlXYK" class="clrfix" style="display: none;">
                                <!-- 1. 招商、工商、建行、中行、华夏 -->
                                <dd>
                                    <input type="radio" value="308" name="account" id="bankType308" /><label for="bankType308"><span class="zh_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="1027" name="account" id="bankType1027" /><label for="bankType1027"><span class="gh_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="1054" name="account" id="bankType1054" /><label for="bankType1054"><span class="jh_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="106" name="account" id="bankType106" /><label for="bankType106"><span class="zg_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="3112" name="account" id="bankType3112" /><label for="bankType3112"><span class="hx_bank"></span></label>
                                </dd>
                                <!-- 2. 民生、光大、邮政、杭州、宁波 -->
                                <dd>
                                    <input type="radio" value="3051" name="account" id="bankType3051" /><label for="bankType3051"><span class="ms_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="3121" name="account" id="bankType3121" /><label for="bankType3121"><span class="gd_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="3231" name="account" id="bankType3231" /><label for="bankType3231"><span class="yz_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="3241" name="account" id="bankType3241" /><label for="bankType3241"><span class="hz_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="303" name="account" id="bankType303" /><label for="bankType303"><span class="lb_bank"></span></label>
                                </dd>
                                <!-- 3. 上海、兴业、广发、平安、浦发 -->
                                <dd>
                                    <input type="radio" value="3261" name="account" id="bankType3261" /><label for="bankType3261"><span class="sh_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="3091" name="account" id="bankType3091" /><label for="bankType3091"><span class="xy_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="306" name="account" id="bankType306" /><label for="bankType306"><span class="gf_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="3071" name="account" id="bankType3071" /><label for="bankType3071"><span class="pa_bank"></span></label>
                                </dd>
                                <dd>
                                    <input type="radio" value="3141" name="account" id="bankType3141" /><label for="bankType3141"><span class="pf_bank"></span></label>
                                </dd>
                                <!-- 4. 青岛 -->
                                <dd>
                                    <input type="radio" value="334" name="account" id="bankType334" /><label for="bankType334"><span class="qd_bank"></span></label>
                                </dd>
                            </dl>
                        </div>
                    </div>
                    --%>
                    <div class="f-pay-defray f-pay-defray2 clrfix">
                        <p class="fl gray9">支付平台支付</p>
                        <div class="fr f-pay-bank">
                            <h3>支付平台支付：</h3>
                            <dl id="dlPayForm" class="clrfix">
                            	<%
									if(ApplicationListenerImpl.sysConfigureJson.getJdPayStatus()==0){
								%>
                                <dd>
                                    <input type="radio" name="account" value="jdpay" id="jdpay" /><label for="jdpay"><span class="jd_bank"></span></label>
                                </dd>
                                <%
									}
								%>
								<%
									if(ApplicationListenerImpl.sysConfigureJson.getAlipayStatus()==0){
								%>
				                <dd>
				                <input type="radio" id="Alipay" name="account" value="Alipay" /><label for="Alipay"><span style="background: url('/Images/bank_logo.png') repeat scroll 0 65px; rgba(0, 0, 0, 0);margin-left:15px;height: 36px;width: 120px;text-indent: -9999px;background-color: #fff;"></span></label>
				                </dd>
								<%
									}
								%>
                                <%
									if(ApplicationListenerImpl.sysConfigureJson.getTenpayStatus()==0){
								%>
				                <dd>
				                <input type="radio"  id="Tenpay" name="account" value="Tenpay" /><label for="Tenpay"><span class="cft_bank"></span></label>
				                </dd>
				                <%
									}
								%>
                                <%
									if(ApplicationListenerImpl.sysConfigureJson.getChinabankStatus()==0){
								%>
                                <dd>
                                    <input type="radio" name="account" value="Chinabank" id="Chinabank" /><label for="Chinabank"><span class="u-bank-wy"></span></label>
                                </dd>
                                <%
									}
								%>
								<%
									if(ApplicationListenerImpl.sysConfigureJson.getWeixinStatus()==0){
								%>
                                <dd>
                                    <input type="radio" name="account" value="Swift" id="Swift" /><label for="Swift"><span class="u-bank-weixin"></span></label>
                                </dd>
                                <%
									}
								%>
								<%
									if(ApplicationListenerImpl.sysConfigureJson.getQqPayStatus()==0){
								%>
                                <dd>
                                    <input type="radio" name="account" value="SwiftQ" id="SwiftQ" /><label for="SwiftQ"><span class="u-bank-qqmobile"></span></label>
                                </dd>
                                <%
									}
								%>
                                <%
									if(ApplicationListenerImpl.sysConfigureJson.getBillStatus()==0){
								%>
                                <dd>
                                    <input type="radio" name="account" value="QuickMoney" id="QuickMoney" /><label for="QuickMoney"><span class="u-bank-kq"></span></label>
                                </dd>
                                <%
									}
								%>
								<%
									if(ApplicationListenerImpl.sysConfigureJson.getUnionpayStatus()==0){
								%>
                                <dd>
                                    <input type="radio" name="account" value="Unionpay" id="Unionpay" /><label for="Unionpay"><span class="u-bank-online"></span></label>
                                </dd>
                                <%
									}
								%>
								<%
									if(ApplicationListenerImpl.sysConfigureJson.getAliPayUserStatus()==0){
								%>
								<dd>
									<input type="radio" id="aliPayUser" name="account" value="aliPayUser" /><label for="aliPayUser"><span style="background: url('/Images/aliPayUser.png') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;text-indent: -9999px;background-color: #fff;">支付宝免签</span></label>
								</dd>
								 <%
									}
								%>
								<%
									if(ApplicationListenerImpl.sysConfigureJson.getTenPayUserStatus()==0){
								%>
								<dd>
									<input type="radio" id="tenPayUser" name="account" value="tenPayUser" /><label for="tenPayUser"><span style="background: url('/Images/tenPayUser.png') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;text-indent: -9999px;background-color: #fff;">财付通免签</span></label>
								</dd>	
								 <%
									}
								%>
								<%
									if(ApplicationListenerImpl.sysConfigureJson.getYeepayStatus()==0){
								%>
								<dd>
								<input type="radio" id="Yeepay" name="account" value="Yeepay" /><label for="Yeepay"><span style="background: url('/Images/yeepay.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;text-indent: -9999px;background-color: #fff;"></span></label>
								</dd>
								<%
									}
								%>
								<%
									if(ApplicationListenerImpl.sysConfigureJson.getYunPayStatus()==0){
								%>
								<dd>
								<input type="radio" id="yunPay" name="account" value="YunPay" /><label for="yunPay"><span style="background: url('/Images/yunPay.png') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;text-indent: -9999px;background-color: #fff;"></span></label>
								</dd>
								<%
									}
								%>
								<%
									if(ApplicationListenerImpl.sysConfigureJson.getIapppayStatus()==0){
								%>
								<dd>
								<input type="radio" id="iAppPay" name="account" value="IAppPay" /><label for="iAppPay"><span style="background: url('/Images/iAppPay.png') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;text-indent: -9999px;background-color: #fff;"></span></label>
								</dd>
								<%
									}
								%>
								<%
									if(ApplicationListenerImpl.sysConfigureJson.getJubaoPayStatus()==0){
								%>
								<dd>
								<input type="radio" id="jubaoPay" name="account" value="JubaoPay" /><label for="jubaoPay"><span style="background: url('/Images/jubaoPay.png') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;text-indent: -9999px;background-color: #fff;"></span></label>
								</dd>
								<%
									}
								%>
                            </dl>
                        </div>
                    </div>

                </div>

                <input type="hidden" id="hidBalance" value="${user.balance }"/>
                <input type="hidden" id="hidCountMoney" value="${productCarts.moneyCount }"/>
                <input type="hidden" id="hidPoints" value="${user.commissionPoints }"/>
                <input type="hidden" id="hidAvailablePoints" value="${user.commissionPoints }"/>
                <input type="hidden" id="hidproductLimit" value="${productCarts.productLimit }"/>
                <input type="hidden" id="hidUsePaypwd" value="0"/>
                <input type="hidden" id="hidSmallMoney" value="0"/>

                <!--使用网银支付提交表单-->
                <form id="toPayForm" name="toPayForm" action="/mycart/goPay.html" method="post" target="_blank">
                    <input type="hidden" id="hidPayName" name="payName" value="" />
                    <input type="hidden" id="hidPayBank" name="payBank" value="0" />
                    <input type="hidden" value="" name="moneyCount" id="moneyCount"/>
                    <input type="hidden" id="hidUseBalance" name="hidUseBalance" value="${user.balance }" />
                    <!-- 不使用积分时，值为0；使用积分时，值为使用积分值 2012.4.28-->
                    <input type="hidden" id="hidIntegral" name="integral" value="0" />
                    <input type="hidden" id="bankMoney" name="bankMoney" value="0" />
                    <!-- 支付SN -->
                    <input type="hidden" id="hidCheckSN" name="checkSN" value="" />
                    <input type="hidden" value="${id }" name="id" id="ids"/>
                    <div class="m-pay-Total clrfix">
                        <div id="divPaypwd" class="f-payment-password clrfix" style="display:none;">
                            <p>
                                <input type="password" id="txtPaypwd" value="" maxlength="6" autocomplete="off"/>
                                <s style="display:none;"></s>
                                <em id="emTip">请输入6位支付密码</em>
                            </p>
                            <span><a href="/Security/UserAuth-10.do" target="_blank" class="gray6">忘记支付密码？</a></span>
                        </div>
                        <div class="f-list-accounts f-grayButton fr">
                            <input id="submitOK" type="submit" name="submit" value="确认支付" />
                        </div>
                    </div>
                    <div id="divGetPoint" class="f-cgzf"></div>
                </form>
            </div>
        </div>
    <div class="pageDialogBG" id="pageDialogBG"></div>
	<div class="pageDialogBorder" id="pageDialogBorder"></div>
	<div class="pageDialog" id="pageDialog">
	<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
	<div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
	</div>
	<div id="payAltBox" style="display: none;">
        <div class="payment_ts">
            <h3><s></s>请在新开窗口完成支付！</h3>
			<ul>
				<li class="payment_ts_con">如您的浏览器不支持，请复制以下链接到IE浏览器打开，完成付款后跟据您的情况进行以下操作！</li>
				<li class="payment_ts_links"><%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/mycart/payment.html</li>
				<li class="payment_ts_but"><a id="btnBuyOk" href="javascript:;" class="pay_tipsbut">完成支付</a><a id="btnReSelect" href="javascript:;" class="blue">支付遇到问题？返回重新选择</a></li>
			</ul>
        </div>
    </div>
    
        <iframe id="iframeLayer" frameborder="0" name="iframeLayer" class="iframeLayer" allowtransparency="true" style="display: none; position: absolute; z-index: 999; width: 100%; height: 100%; top: 0px; left: 0px; right: 0; bottom: 0; background: url('/Images/jdloading.gif') center center no-repeat;" src="about:blank"></iframe>
    
  	<script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/payment.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css" />
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js"></script>
</body>
</html>
