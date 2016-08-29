<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>帐户充值_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></title>
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutframe.css?data=20131121" />
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutrecharge.css?data=20131121" />
  </head>
  
<body>
<div class="main-content clearfix">
        
<div class="left">
	<div class="head">
	    <a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${user.userId }.html">
	    <img width="160" height="160" border="0" runat="server" alt="" src="${user.faceImg }" id="imgUserPhoto" /></a>
	</div>
	<div class="head-but">
		<a class="blue" href="/user/UserPhoto.html">修改头像</a>
		<a class="blue fr" href="/user/MemberModify.html">编辑资料</a>
	</div>
	<div class="sidebar-nav">
<p class="sid-line"></p>
<h2 class=""><a href="index.html"><b></b>我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></a></h2><p class="sid-line"></p>
<h3 url="/user/UserBuyList.html" haschild="1" class="sid-icon02 "><a href="javascript:;"><b></b>我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <s title="收起"></s></a></h3>
<ul><li class=""><a href="/user/UserBuyList.html"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</a></li>
<li class=""><a href="/user/OrderList.html">获得的商品</a></li>
<li class=""><a href="/user/PostSingleList.html">晒单</a></li></ul>
<!--<p class="sid-line"></p><h3 url="/user/JoinGroup.html" haschild="1" class="sid-icon03 "><a href="javascript:;"><b></b>圈子管理 <s title="收起"></s></a></h3>-->
<!--<ul><li class=""><a href="/user/JoinGroup.html">加入的圈子</a></li>-->
<!--<li class=""><a href="/user/Topic.html">圈子话题</a></li></ul>-->
<p class="sid-line"></p><h3 url="/user/InvitedList.html" haschild="1" class="sid-icon04 "><a href="javascript:;"><b></b>邀请管理 <s title="收起"></s></a></h3>
<ul><li class=""><a href="/user/InvitedList.html">邀请好友</a></li>
<li class=""><a href="/user/CommissionQuery.html">佣金明细</a></li>
<li class=""><a href="/user/ApplyMention.html">提现申请</a></li>
<li class=""><a href="/user/MentionList.html">提现记录</a></li></ul>
<p class="sid-line"></p><h3 url="/user/UserBalance.html" haschild="1" class="sid-icon05 "><a href="javascript:;"><b></b>账户管理 <s title="收起"></s></a></h3>
<ul><li class=""><a href="/user/UserBalance.html">账户明细</a></li>
<li class="sid-cur"><a href="/user/UserRecharge.html">账户充值</a></li>
<li class=""><a href="/user/userCardRecharge.html">实卡充值</a></li>
</ul>
<!--<p class="sid-line"></p><h3 url="/user/MyFriends.html" haschild="1" class="sid-icon06 "><a href="javascript:;"><b></b>我的好友 <s title="收起"></s></a></h3>-->
<!--<ul><li class=""><a href="/user/MyFriends.html">我的好友</a></li>-->
<!--<li class=""><a href="/user/SearchFriends.html">查找好友</a></li>-->
<!--<li class=""><a href="/user/FriendsApply.html">好友请求</a></li></ul>-->
<p class="sid-line"></p><h3 url="" haschild="0" class="sid-icon07 "><a href="/user/MemberPoints.html"><b></b>我的积分</a></h3>

<!--<p class="sid-line"></p><h3 url="" haschild="0" class="sid-icon08 "><a href="/user/UserMessage.html"><b></b>消息管理</a></h3>-->

<p class="sid-line"></p><h3 url="" haschild="0" class="sid-icon09 "><a href="/user/MemberModify.html"><b></b>个人设置</a></h3>


		<p class="sid-line"></p>
	</div>
	<div class="sid-service">
		<p><a class="service-btn" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq() %>&site=qq&menu=yes" id="btnLeftQQ"><s></s>在线客服</a></p>
		<span>客服热线(免长途费)</span>
		<b class="tel"><%=ApplicationListenerImpl.sysConfigureJson.getServiceTel() %></b>
	</div>
</div>
<script type="text/javascript">

    var _NavState = [true, true, true, true, true];
    
    $("div.sidebar-nav").find("h3").each(function(i,v){
        var _This = $(this);
        var _HasClild = _This.attr("hasChild")=="1";
        
        var _SObj = _This.find("s");
        _This.click(function(e){
            if(_HasClild){
                var _State = _NavState[i];
                
                /* 一级栏目更改样式 */
                if(_State){
                    _This.addClass("sid-iconcur");
                    _SObj.attr("title","展开");
                }
                else {
                    _This.removeClass("sid-iconcur");
                    _SObj.attr("title","收起");
                }
                
                /* 二级栏目显示或隐藏 */
                _This.next("ul").children().each(function(){
                    if(_State){
                        $(this).hide(50);
                    }
                    else {
                        $(this).show(50);
                    }
                });
                _NavState[i] = !_State;
            }
        });
    });
    
</script>
<div class="R-content">
            <div class="subMenu">
                <a class="current" title="网银充值" href="/user/UserRecharge.html">网银充值</a>
                <a title="充值卡充值" href="/user/userCardRecharge.html">充值卡充值</a>
            </div>
            <div class="select">
        	    <b class="gray01">请您选择充值金额</b>
                <ul id="ulMoneyList">
            	    <li style="margin-left:0;" class="selected"><input type="radio" checked="checked" value="50" name="money" id="rd50" onfocus="this.blur()" /> <label for="rd50">充值 <strong>￥</strong><b>50</b></label></li>
                    <li><input type="radio" id="rd100" value="100" name="money" onfocus="this.blur()" /> <label for="rd100">充值 <strong>￥</strong><b>100</b></label></li>
                    <li class=""><input type="radio" id="rd200" value="200" name="money" onfocus="this.blur()" /> <label for="rd200">充值 <strong>￥</strong><b>200</b></label></li>
                    <li><input type="radio" id="rd500" value="500" name="money" onfocus="this.blur()" /> <label for="rd500">充值 <strong>￥</strong><b>500</b></label></li>
                    <li class="custom"><input type="radio" id="rdOther" name="money" value="0" onfocus="this.blur()" /> <label for="rdOther">其它金额</label><input type="text" onkeypress="if(event.keyCode&lt;48 || event.keyCode&gt;57)event.returnValue=false;" onpaste="return false;" maxlength="7" class="enter" id="txtOtherMoney"></li>
                </ul>
            </div>
            <div class="charge_money_list">
                <p class="title gray01">请选择支付方式</p>
                <%--
                <div class="tab_btn"><a href="javascript:;" class="tab_btn_hover" id="btnCXK">储蓄卡支付</a><a href="javascript:;" id="btnXYK">信用卡支付</a></div>
                --%>
                <div class="tab_btn_cz">
                    <ul class="payment">
                    <%--
                        <!-- 1. 招商、工商、建行、农行 -->
                        <li class="top">
                            <input type="radio" checked="checked" id="bankType3080" name="account" value="3080"><label for="bankType3080"><span class="zh_bank"></span></label>
                            <input type="radio" id="bankType1025" name="account" value="1025"><label for="bankType1025"><span class="gh_bank"></span></label>
                            <input type="radio" id="bankType105" name="account" value="105"><label for="bankType105"><span class="jh_bank"></span></label>
                            <input type="radio" id="bankType103" name="account" value="103"><label for="bankType103"><span class="nh_bank"></span></label>
                        </li>

                        <!-- 2. 中行、交通、华夏、兴业 -->
                        <li class="top">
                            <input type="radio" id="bankType104" name="account" value="104"><label for="bankType104"><span class="zg_bank"></span></label>
                            <input type="radio" id="bankType301" name="account" value="301"><label for="bankType301"><span class="jt_bank"></span></label>
                            <input type="radio" id="bankType311" name="account" value="311"><label for="bankType311"><span class="hx_bank"></span></label>
                            <input type="radio" id="bankType309" name="account" value="309"><label for="bankType309"><span class="xy_bank"></span></label>
                        </li>

                        <!-- 3. 上海、民生、广发、平安 -->
                        <li class="top">
                            <input type="radio" id="bankType326" name="account" value="326"><label for="bankType326"><span class="sh_bank"></span></label>
                            <input type="radio" id="bankType305" name="account" value="305"><label for="bankType305"><span class="ms_bank"></span></label>
                            <input type="radio" id="bankType3061" name="account" value="3061"><label for="bankType3061"><span class="gf_bank"></span></label>
                            <input type="radio" id="bankType307" name="account" value="307"><label for="bankType307"><span class="pa_bank"></span></label>
                        </li>

                        <!-- 4. 浦发、中信、光大、南京  -->
                        <li class="top">
                            <input type="radio" id="bankType314" name="account" value="314"><label for="bankType314"><span class="pf_bank"></span></label>
                            <input type="radio" id="bankType313" name="account" value="313"><label for="bankType313"><span class="zx_bank"></span></label>
                            <input type="radio" id="bankType312" name="account" value="312"><label for="bankType312"><span class="gd_bank"></span></label>
                            <input type="radio" id="bankType316" name="account" value="316"><label for="bankType316"><span class="nj_bank"></span></label>
                        </li>

                        <!-- 5. 邮政、杭州、宁波、北京  -->
                        <li class="top">
                            <input type="radio" id="bankType3230" name="account" value="3230"><label for="bankType3230"><span class="yz_bank"></span></label>
                            <input type="radio" id="bankType324" name="account" value="324"><label for="bankType324"><span class="hz_bank"></span></label>
                            <input type="radio" id="bankType302" name="account" value="302"><label for="bankType302"><span class="lb_bank"></span></label>
                            <input type="radio" id="bankType310" name="account" value="310"><label for="bankType310"><span class="bj_bank"></span></label>
                        </li>

                        <!-- 6. 重庆农商、北京农商、青岛、成都  -->
                        <li class="top">
                            <input type="radio" id="bankType342" name="account" value="342"><label for="bankType342"><span class="cqls_bank"></span></label>
                            <input type="radio" id="bankType335" name="account" value="335"><label for="bankType335"><span class="bj2_bank"></span></label>
                            <input type="radio" id="bankType3341" name="account" value="3341"><label for="bankType3341"><span class="qd_bank"></span></label>
                            <input type="radio" id="bankType336" name="account" value="336"><label for="bankType336"><span class="cd_bank"></span></label>
                        </li>
                    </ul>
                    <ul style="display: none;" class="payment">
                        <!-- 1. 招商、工商、建行、中行 -->
                        <li class="top">
                            <input type="radio" id="bankType308" name="account" value="308"><label for="bankType308"><span class="zh_bank"></span></label>
                            <input type="radio" id="bankType1027" name="account" value="1027"><label for="bankType1027"><span class="gh_bank"></span></label>
                            <input type="radio" id="bankType1054" name="account" value="1054"><label for="bankType1054"><span class="jh_bank"></span></label>
                            <input type="radio" id="bankType106" name="account" value="106"><label for="bankType106"><span class="zg_bank"></span></label>
                        </li>
                        <!-- 2. 华夏、民生、光大、邮政 -->
                        <li class="top">
                            <input type="radio" id="bankType3112" name="account" value="3112"><label for="bankType3112"><span class="hx_bank"></span></label>
                            <input type="radio" id="bankType3051" name="account" value="3051"><label for="bankType3051"><span class="ms_bank"></span></label>
                            <input type="radio" id="bankType3121" name="account" value="3121"><label for="bankType3121"><span class="gd_bank"></span></label>
                            <input type="radio" id="bankType3231" name="account" value="3231"><label for="bankType3231"><span class="yz_bank"></span></label>
                        </li>
                        <!-- 3. 杭州、宁波、上海、兴业 -->
                        <li class="top">
                            <input type="radio" id="bankType3241" name="account" value="3241"><label for="bankType3241"><span class="hz_bank"></span></label>
                            <input type="radio" id="bankType303" name="account" value="303"><label for="bankType303"><span class="lb_bank"></span></label>
                            <input type="radio" id="bankType3261" name="account" value="3261"><label for="bankType3261"><span class="sh_bank"></span></label>
                            <input type="radio" id="bankType3091" name="account" value="3091"><label for="bankType3091"><span class="xy_bank"></span></label>
                        </li>
                        <!-- 4. 广发、平安、浦发、青岛 -->
                        <li class="top">
                            <input type="radio" id="bankType306" name="account" value="306"><label for="bankType306"><span class="gf_bank"></span></label>
                            <input type="radio" id="bankType3071" name="account" value="3071"><label for="bankType3071"><span class="pa_bank"></span></label>
                            <input type="radio" id="bankType3141" name="account" value="3141"><label for="bankType3141"><span class="pf_bank"></span></label>
                            <input type="radio" id="bankType334" name="account" value="334"><label for="bankType334"><span class="qd_bank"></span></label>
                        </li>
                        --%>
                    </ul>
                    <%--
                    <p class="leix">支付平台支付：</p>
                    --%>
                    <ul class="payment">
                        <li class="top">
                        	<%
								if(ApplicationListenerImpl.sysConfigureJson.getAlipayStatus()==0){
							%>
                        	<input type="radio" id="Alipay" name="account" value="Alipay" />
                        	<label for="Alipay"><span style="background: url('/Images/bank_logo.png') repeat scroll 8px 65px; rgba(0, 0, 0, 0);text-indent: -9999px;background-color: #fff;">支付宝</span></label>
                        	<%
							}
							%>
							<%
								if(ApplicationListenerImpl.sysConfigureJson.getTenpayStatus()==0){
							%>
			                <input type="radio"  id="Tenpay" name="account" value="Tenpay" /><label for="Tenpay"><span class="cft_bank"></span></label>
			                <%
							}
							%>
							 <%
								if(ApplicationListenerImpl.sysConfigureJson.getChinabankStatus()==0){
							%>
                            <input type="radio" id="Chinabank" name="account" value="Chinabank" />
                            <label for="Chinabank">
                                <span class="wy">网银在线</span>
                            </label>
                            <%
							}
							%>
							<%
								if(ApplicationListenerImpl.sysConfigureJson.getWeixinStatus()==0){
							%>
                            <input type="radio" id="Swift" name="account" value="Swift" />
                            <label for="Swift">
                                <span class="weixin">微信支付</span>
                            </label>
                            <%
							}
							%>
							<%
								if(ApplicationListenerImpl.sysConfigureJson.getYunPayStatus()==0){
							%>
                            <input type="radio" id="YunPay" name="account" value="YunPay" />
                            <label for="YunPay">
                                <span class="yunpay">云支付</span>
                            </label>
                            <%
							}
							%>
							<%
								if(ApplicationListenerImpl.sysConfigureJson.getIapppayStatus()==0){
							%>
                            <input type="radio" id="IAppPay" name="account" value="IAppPay" />
                            <label for="IAppPay">
                                <span class="iapppay">爱贝支付</span>
                            </label>
                            <%
							}
							%>
                        </li>
                       	<li class="top">
                       	<%
							if(ApplicationListenerImpl.sysConfigureJson.getJdPayStatus()==0){
						%>
                           <input type="radio" id="jdpay" name="account" value="jdpay" />
                           <label for="jdpay">
                               <span class="jd_bank">京东支付</span>
                           </label>
                           <%
						}
						%>
                       	<%
							if(ApplicationListenerImpl.sysConfigureJson.getYeepayStatus()==0){
						%>
						<input type="radio" id="Yeepay" name="account" value="Yeepay" /><label for="Yeepay"><span style="background: url('/Images/yeepay.gif') repeat scroll 8px 0px; rgba(0, 0, 0, 0);text-indent: -9999px;background-color: #fff;"></span></label>
						<%
						}
						%>
						<%
							if(ApplicationListenerImpl.sysConfigureJson.getAliPayUserStatus()==0){
						%>
							<input type="radio" id="aliPayUser" name="account" value="aliPayUser" /><label for="aliPayUser"><span style="background: url('/Images/aliPayUser.png') repeat scroll 6px 0px; rgba(0, 0, 0, 0);text-indent: -9999px;background-color: #fff;">支付宝免签</span></label>
						 <%
							}
						%>
						<%
							if(ApplicationListenerImpl.sysConfigureJson.getTenPayUserStatus()==0){
						%>
							<input type="radio" id="tenPayUser" name="account" value="tenPayUser" /><label for="tenPayUser"><span style="background: url('/Images/tenPayUser.png') repeat scroll 10px 0px rgba(0, 0, 0, 0);text-indent: -9999px;background-color: #fff;">财付通免签</span></label>
						 <%
							}
						%>
						<%
								if(ApplicationListenerImpl.sysConfigureJson.getJubaoPayStatus()==0){
							%>
                            <input type="radio" id="JubaoPay" name="account" value="JubaoPay" />
                            <label for="JubaoPay">
                                <span class="jubaopay">聚宝云计费</span>
                            </label>
                            <%
							}
							%>
						</li>
                        <li class="top">
                        	<%
								if(ApplicationListenerImpl.sysConfigureJson.getUnionpayStatus()==0){
							%>
                            <input type="radio" id="Unionpay" name="account" value="Unionpay" />
                            <label for="Unionpay">
                                <span class="online">在线支付</span>
                            </label>
                            <%
							}
							%>
							<%
								if(ApplicationListenerImpl.sysConfigureJson.getQqPayStatus()==0){
							%>
                            <input type="radio" id="SwiftQ" name="account" value="SwiftQ" />
                            <label for="SwiftQ">
                                <span class="qqmobile">手Q支付</span>
                            </label>
                            <%
							}
							%>
							<%
								if(ApplicationListenerImpl.sysConfigureJson.getBillStatus()==0){
							%>
                             <input type="radio" id="QuickMoney" name="account" value="QuickMoney" />
                            <label for="QuickMoney">
                                <span class="kq">快钱支付</span>
                            </label>
                            <%
							}
							%>
                       </li>
                       
                    </ul>
                </div>
                <p class="much">应付金额：<span class="yf"><strong>￥</strong><span id="Money">50</span></span></p>
                <h6>
                    <form target="_blank" method="post" action="/balance/goBalance.html" name="toPayForm" id="toPayForm">
                        <input type="hidden" value="" name="payName" id="hidPayName" />
                        <input type="hidden" value="0" name="payBank" id="hidPayBank" />
                        <input type="hidden" value="50" name="money" id="hidMoney" />
                        <input type="hidden" value="" name="moneyCount" id="moneyCount"/>
                        <input type="hidden" id="hidIntegral" name="integral" value="0" />
                        <input type="hidden" id="bankMoney" name="bankMoney" value="0" />
                        <input type="submit" title="立即充值" value="立即充值" name="submit" class="bluebut imm" id="submit_ok" />
                    </form>
                </h6>
                <div style="display:none;" id="payAltBox">
                    <div class="prompt_box">
			            <p class="pic"><em></em>请您在新开的页面上完成支付！</p>
			            <p class="ts">付款完成之前，请不要关闭本窗口！<br />完成付款后跟据您的个人情况完成此操作！</p>
			            <ul>
				            <li><a title="查看充值记录" class="look" href="/user/UserBalance.html">查看充值记录 </a></li>
				            <li><a title="重选支付方式" id="btnReSelect" class="look" href="javascript:;">重选支付方式</a></li>
			            </ul>
                    </div>
                </div>
            </div>  
        </div>
 </div>
    <link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css?data=20131121" />
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js?data=20131121"></script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/userrecharge.js?data=20131121"></script>
    <div class="clear_process"></div>
    <div class="pageDialogBG" id="pageDialogBG"></div>
	<div class="pageDialogBorder" id="pageDialogBorder"></div>
	<div class="pageDialog" id="pageDialog">
	<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
	<div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
	</div>
	<iframe id="iframeLayer" frameborder="0" name="iframeLayer" class="iframeLayer" allowtransparency="true" style="display: none; position: absolute; z-index: 999; width: 100%; height: 100%; top: 0px; left: 0px; right: 0; bottom: 0; background: url('/Images/jdloading.gif') center center no-repeat;" src="about:blank"></iframe>
</body>
</html>
