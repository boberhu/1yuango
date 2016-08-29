<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<title>1元夺宝_管理中心</title>
	<link href="/admin_css/global.css"  rel="stylesheet" type="text/css"/>
	<link href="/admin_css/admin_style.css"  rel="stylesheet" type="text/css"/>
	<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
  </head>
  
  <body>
<div class="header-title lr10"><b>支付方式设置</b></div>
<div class="bk10"></div>
<div class="table-list lr10">
<!--start-->
  <table width="100%" cellspacing="0">
    <thead>
		<tr>
            <th width="100px" align="center">支付名称</th>
            <th width="100px" align="center">图片</th>
			<th width="100px" align="center">是否启用</th>
            <th width="100px" align="center">支付方式</th>
            <th width="100px" align="center">管理</th>
		</tr>
    </thead>
    <tbody>
    	 <%-- <tr>
			<td align="center">京东支付</td>	
			<td align="center">
			<div  style="background-image:url('/Images/bank-logo.gif'); background-position: 8px -1017px;background-repeat:repeat;background-attachment:scroll; background: url('/Images/bank-logo.gif') repeat scroll 8px -1017px rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">京东支付
			</div>
			</td>
			<td align="center">
				<%
					if(ApplicationListenerImpl.sysConfigureJson.getJdPayStatus()==0){
					%>
					<font style="color:#0c0">启用</font>
					<%
					}else{
					%>
					<font style="color:#f00">禁用</font>
					<%
					}
					%>
			</td>
			<td align="center">
				即时到账
			</td>	
			<td align="center"><a href="/admin_back/payInfo.action?id=jdpay">设置</a>  <a href="http://www.chinabank.com.cn/product/payment_plus.jsp" target="_blank"><font style="color:#0c0">申请接口</font></a></td>
		</tr>
    	<tr>
			<td align="center">手Q支付</td>	
			<td align="center">
			<div  style="background-image:url('/Images/bank-logo.gif'); background-position: 3px -1055px;background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank-logo.gif') repeat scroll 3px -1055px rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">手Q支付
			</div>
			</td>
			<td align="center">
				<%
					if(ApplicationListenerImpl.sysConfigureJson.getQqPayStatus()==0){
					%>
					<font style="color:#0c0">启用</font>
					<%
					}else{
					%>
					<font style="color:#f00">禁用</font>
					<%
					}
					%>
			</td>
			<td align="center">
				即时到账
			</td>	
			<td align="center"><a href="/admin_back/payInfo.action?id=qqpay">设置</a>  <a href="http://mch.tenpay.com/" target="_blank"><font style="color:#0c0">申请接口</font></a></td>
		</tr>  --%>
		<tr>
			<td align="center">财付通即时到账</td>	
			<td align="center">
			<div  style="background-image:url('/Images/bank_logo.png'); background-position: 8px -500px;background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank_logo.png') repeat scroll 8px -500px rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">财付通
			</div>
			</td>
			<td align="center">
				<%
					if(ApplicationListenerImpl.sysConfigureJson.getTenpayStatus()==0){
					%>
					<font style="color:#0c0">启用</font>
					<%
					}else{
					%>
					<font style="color:#f00">禁用</font>
					<%
					}
					%>
			</td>
			<td align="center">
				即时到账
			</td>	
			<td align="center"><a href="/admin_back/payInfo.action?id=tenpay">设置</a>  <a href="http://mch.tenpay.com/" target="_blank"><font style="color:#0c0">申请接口</font></a></td>
		</tr>
		<tr>
			<td align="center">支付宝即时到账</td>	
			<td align="center">
			<div  style="background-image:url('/Images/bank_logo.png'); background-position: 8px -613px;background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank_logo.png') repeat scroll 8px -613px rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">支付宝
			</div>
			</td>
			<td align="center">
						<%
						if(ApplicationListenerImpl.sysConfigureJson.getAlipayStatus()==0){
						%>
						<font style="color:#0c0">启用</font>
						<%
						}else{
						%>
						<font style="color:#f00">禁用</font>
						<%
						}
						%>
						
			</td>	
			<td align="center">
				即时到账
			</td>	
			<td align="center"><a href="/admin_back/payInfo.action?id=alipay">设置</a>  <a href="https://e.alipay.com/index.htm" target="_blank"><font style="color:#0c0">申请接口</font></a></td>
		</tr>
				<tr>
			<td align="center">易宝支付即时到账</td>	
			<td align="center">
			<div  style="background-image:url('/Images/yeepay.gif'); background-repeat:repeat;background-attachment:scroll;background: url('/Images/yeepay.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">易宝支付
			</div>
			</td>
			<td align="center">
				<%
					if(ApplicationListenerImpl.sysConfigureJson.getYeepayStatus()==0){
					%>
					<font style="color:#0c0">启用</font>
					<%
					}else{
					%>
					<font style="color:#f00">禁用</font>
					<%
					}
					%>
			</td>	
			<td align="center">
				即时到账
			</td>	
			<td align="center"><a href="/admin_back/payInfo.action?id=yeepay">设置</a>  <a href="http://www.yeepay.com/" target="_blank"><font style="color:#0c0">申请接口</font></a></td>
		</tr>
		
		<tr>
			<td align="center">网银在线即时到帐</td>	
			<td align="center">
			<div  style="background-image:url('/Images/bank-logo.gif'); background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank-logo.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;background-position: 8px -840px;">网银在线
			</div>
			</td>
			<td align="center">
				<%
					if(ApplicationListenerImpl.sysConfigureJson.getChinabankStatus()==0){
					%>
					<font style="color:#0c0">启用</font>
					<%
					}else{
					%>
					<font style="color:#f00">禁用</font>
					<%
					}
					%>
			</td>
			<td align="center">
				即时到账
			</td>	
			<td align="center"><a href="/admin_back/payInfo.action?id=chinabank">设置</a>  <a href="http://www.chinabank.com.cn/" target="_blank"><font style="color:#0c0">申请接口</font></a></td>
		</tr>
		
		<tr>
			<td align="center">快钱支付即时到帐</td>	
			<td align="center">
			<div  style="background-image:url('/Images/bank-logo.gif');background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank-logo.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;background-position: 8px -875px;">财付通
			</div>
			</td>
			<td align="center">
				<%
					if(ApplicationListenerImpl.sysConfigureJson.getBillStatus()==0){
					%>
					<font style="color:#0c0">启用</font>
					<%
					}else{
					%>
					<font style="color:#f00">禁用</font>
					<%
					}
					%>
			</td>
			<td align="center">
				即时到账
			</td>	
			<td align="center"><a href="/admin_back/payInfo.action?id=bill">设置</a>  <a href="https://www.99bill.com/" target="_blank"><font style="color:#0c0">申请接口</font></a></td>
		</tr>
		
		<tr>
			<td align="center">银联支付即时到帐</td>	
			<td align="center">
			<div  style="background-image:url('/Images/bank-logo.gif'); background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank-logo.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;background-position: 8px -910px;">财付通
			</div>
			</td>
			<td align="center">
				<%
					if(ApplicationListenerImpl.sysConfigureJson.getUnionpayStatus()==0){
					%>
					<font style="color:#0c0">启用</font>
					<%
					}else{
					%>
					<font style="color:#f00">禁用</font>
					<%
					}
					%>
			</td>
			<td align="center">
				即时到账
			</td>	
			<td align="center"><a href="/admin_back/payInfo.action?id=unionpay">设置</a>  <a href="http://www.chinapay.com/" target="_blank"><font style="color:#0c0">申请接口</font></a></td>
		</tr>
		
			<tr>
			<td align="center">微信支付</td>	
			<td align="center">
			<div  style="background-image:url('/Images/bank-logo.gif'); background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank-logo.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;background-position: 8px -945px;">微信支付
			</div>
			</td>
			<td align="center">
				<%
					if(ApplicationListenerImpl.sysConfigureJson.getWeixinStatus()==0){
					%>
					<font style="color:#0c0">启用</font>
					<%
					}else{
					%>
					<font style="color:#f00">禁用</font>
					<%
					}
					%>
			</td>	
			<td align="center">
				即时到账
			</td>	
			<td align="center"><a href="/admin_back/payInfo.action?id=weixin">设置</a>  <a href="https://pay.weixin.qq.com/" target="_blank"><font style="color:#0c0">申请接口</font></a></td>
		</tr>
		
		<tr>
			<td align="center">支付宝免签</td>	
			<td align="center">
			<div  style="background-image:url('/Images/aliPayUser.png');background-repeat:repeat;background-attachment:scroll;background: url('/Images/aliPayUser.png') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">支付宝
			</div>
			</td>
			<td align="center">
						<%
						if(ApplicationListenerImpl.sysConfigureJson.getAliPayUserStatus()==0){
						%>
						<font style="color:#0c0">启用</font>
						<%
						}else{
						%>
						<font style="color:#f00">禁用</font>
						<%
						}
						%>
						
			</td>	
			<td align="center">
				即时到账
			</td>	
			<td align="center"><a href="/admin_back/payInfo.action?id=aliPayUser">设置</a>  <a href="http://t.cn/RLSD18q" target="_blank"><font style="color:#0c0">申请接口</font></a></td>
		</tr>
		
		<tr>
			<td align="center">财付通免签</td>	
			<td align="center">
			<div  style="background-image:url('/Images/tenPayUser.png'); background-repeat:repeat;background-attachment:scroll;background: url('/Images/tenPayUser.png') repeat scroll 3px 0 rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">财付通
			</div>
			</td>
			<td align="center">
				<%
					if(ApplicationListenerImpl.sysConfigureJson.getTenPayUserStatus()==0){
					%>
					<font style="color:#0c0">启用</font>
					<%
					}else{
					%>
					<font style="color:#f00">禁用</font>
					<%
					}
					%>
			</td>
			<td align="center">
				即时到账
			</td>
			<td align="center"><a href="/admin_back/payInfo.action?id=tenPayUser">设置</a>  <a href="http://t.cn/RLSD18q" target="_blank"><font style="color:#0c0">申请接口</font></a></td>
		</tr>
		
		<tr>
			<td align="center">云支付</td>	
			<td align="center">
			<div  style="background-image:url('/Images/yunPay.png'); background-repeat:repeat;background-attachment:scroll;background: url('/Images/yunPay.png') repeat scroll 3px 0 rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">云支付
			</div>
			</td>
			<td align="center">
				<%
					if(ApplicationListenerImpl.sysConfigureJson.getYunPayStatus()==0){
					%>
					<font style="color:#0c0">启用</font>
					<%
					}else{
					%>
					<font style="color:#f00">禁用</font>
					<%
					}
					%>
			</td>
			<td align="center">
				即时到账
			</td>	
			<td align="center"><a href="/admin_back/payInfo.action?id=yunPay">设置</a>  <a href="http://www.yunpay.net.cn/" target="_blank"><font style="color:#0c0">申请接口</font></a></td>
		</tr>
		
		<%-- <tr>
			<td align="center">爱贝云计费</td>	
			<td align="center">
			<div  style="background-image:url('/Images/iAppPay.png'); background-repeat:repeat;background-attachment:scroll;background: url('/Images/iAppPay.png') repeat scroll 3px 0 rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">爱贝云计费
			</div>
			</td>
			<td align="center">
				<%
					if(ApplicationListenerImpl.sysConfigureJson.getIapppayStatus()==0){
					%>
					<font style="color:#0c0">启用</font>
					<%
					}else{
					%>
					<font style="color:#f00">禁用</font>
					<%
					}
					%>
			</td>
			<td align="center">
				即时到账
			</td>
			<td align="center"><a href="/admin_back/payInfo.action?id=iAppPay">设置</a>  <a href="http://www.iapppay.com/g-book.html" target="_blank"><font style="color:#0c0">申请接口</font></a></td>
		</tr> --%>
		
		<tr>
			<td align="center">聚宝云计费</td>	
			<td align="center">
			<div  style="background-image:url('/Images/jubaoPay.png'); background-repeat:repeat;background-attachment:scroll;background: url('/Images/jubaoPay.png') repeat scroll 3px 0 rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">聚宝云计费
			</div>
			</td>
			<td align="center">
				<%
					if(ApplicationListenerImpl.sysConfigureJson.getJubaoPayStatus()==0){
					%>
					<font style="color:#0c0">启用</font>
					<%
					}else{
					%>
					<font style="color:#f00">禁用</font>
					<%
					}
					%>
			</td>
			<td align="center">
				即时到账
			</td>
			<td align="center"><a href="/admin_back/payInfo.action?id=jubaoPay">设置</a>  <a href="https://www.jubaopay.com" target="_blank"><font style="color:#0c0">申请接口</font></a></td>
		</tr>
		  	</tbody>
</table>
</div>
 </body>
</html>
