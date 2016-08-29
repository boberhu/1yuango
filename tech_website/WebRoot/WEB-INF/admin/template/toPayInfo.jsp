<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.pojo.Admin"%>
<%@page import="com.tech.util.Struts2Utils"%>
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
<div class="header-title lr10"><b>支付方式配置</b></div>
<div class="bk10"></div>
<div class="table-list lr10">
<!--start-->
<form method="post" action="/admin_back/doSeoSet.action" name="myform">
<c:if test="${id=='jdpay'}">
 <table width="100%" cellspacing="0">	
		<tbody>
		<tr>
			<td width="220" align="right">支付名称：</td>
			<td><input type="text" class="input-text" value="京东支付" name="pay_name" disabled="disabled"/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">支付方式：</td>
				<td>
				<input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>即时到账
			</td>
		</tr>
		<tr>
			<td width="220"  align="right">图片：</td>
			<td>
			<div  style="background-image:url('/Images/bank-logo.gif'); background-position: 8px -1017px;background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank-logo.gif') repeat scroll 8px -1017px rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">京东支付</div>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">是否开启：</td>
			<td>
			<c:if test="${sysConfigure.jdPayStatus==0}">
				<input type="radio" checked="checked" value="0" name="sysConfigure.jdPayStatus" />开启
				<input type="radio" value="1" name="sysConfigure.jdPayStatus" />关闭
			</c:if>
			<c:if test="${sysConfigure.jdPayStatus==1}">
				<input type="radio" value="0" name="sysConfigure.jdPayStatus" />开启
				<input type="radio" checked="checked" value="1" name="sysConfigure.jdPayStatus" />关闭
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">京东商户号:</td>
			<td><input name="sysConfigure.jdPayPartner" value="${sysConfigure.jdPayPartner }"  class="input-text wid300" /></td>
		</tr>
				<tr>
			<td width="220" align="right" style="padding-right:5px;">京东商户密钥:</td>
			<td>
			<%
			if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
			%>
			<input name="sysConfigure.jdPayKey" value="${sysConfigure.jdPayKey }" type="password" class="input-text wid300" />
			<%
			}else{
			%>
			<input name="sysConfigure.jdPayKey" value="密钥已加密" type="password" class="input-text wid300" />
			<%
			}
			%>
			</td>
		</tr>
        <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " name="dosubmit" class="button" />
            <input type="button" value="返回" class="button" onclick="window.location.href='/admin_back/toPaySet.action';" />
            </td>
		</tr>
	</tbody>
</table>
</c:if>
<c:if test="${id=='qqpay'}">
 <table width="100%" cellspacing="0">	
		<tbody>
		<tr>
			<td width="220" align="right">支付名称：</td>
			<td><input type="text" class="input-text" value="手Q支付" name="pay_name" disabled="disabled"/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">支付方式：</td>
				<td>
				<input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>即时到账
			</td>
		</tr>
		<tr>
			<td width="220"  align="right">图片：</td>
			<td>
			<div  style="background-image:url('/Images/bank-logo.gif'); background-position: 3px -1055px;background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank-logo.gif') repeat scroll 3px -1055px rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">手Q支付</div>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">是否开启：</td>
			<td>
			<c:if test="${sysConfigure.qqPayStatus==0}">
				<input type="radio" checked="checked" value="0" name="sysConfigure.qqPayStatus" />开启
				<input type="radio" value="1" name="sysConfigure.qqPayStatus" />关闭
			</c:if>
			<c:if test="${sysConfigure.qqPayStatus==1}">
				<input type="radio" value="0" name="sysConfigure.qqPayStatus" />开启
				<input type="radio" checked="checked" value="1" name="sysConfigure.qqPayStatus" />关闭
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">手Q支付户号:</td>
			<td><input name="sysConfigure.qqPayPartner" value="${sysConfigure.qqPayPartner }"  class="input-text wid300" /></td>
		</tr>
				<tr>
			<td width="220" align="right" style="padding-right:5px;">手Q支付密钥:</td>
			<td>
			<%
			if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
			%>
			<input name="sysConfigure.qqPayKey" value="${sysConfigure.qqPayKey }" type="password" class="input-text wid300" />
			<%
			}else{
			%>
			<input name="sysConfigure.qqPayKey" value="密钥已加密" type="password" class="input-text wid300" />
			<%
			}
			%>
			</td>
		</tr>
        <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " name="dosubmit" class="button" />
            <input type="button" value="返回" class="button" onclick="window.location.href='/admin_back/toPaySet.action';" />
            </td>
		</tr>
	</tbody>
</table>
</c:if>
<c:if test="${id=='tenpay'}">
 <table width="100%" cellspacing="0">	
		<tbody>
		<tr>
			<td width="220" align="right">支付名称：</td>
			<td><input type="text" class="input-text" value="财付通" name="pay_name" disabled="disabled"/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">支付方式：</td>
				<td>
				<input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>即时到账
			</td>
		</tr>
		<tr>
			<td width="220"  align="right">图片：</td>
			<td>
			<div  style="background-image:url('/Images/bank_logo.png'); background-position: 8px -500px;background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank_logo.png') repeat scroll 8px -500px rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">财付通</div>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">是否开启：</td>
			<td>
			<c:if test="${sysConfigure.tenpayStatus==0}">
				<input type="radio" checked="checked" value="0" name="sysConfigure.tenpayStatus" />开启
				<input type="radio" value="1" name="sysConfigure.tenpayStatus" />关闭
			</c:if>
			<c:if test="${sysConfigure.tenpayStatus==1}">
				<input type="radio" value="0" name="sysConfigure.tenpayStatus" />开启
				<input type="radio" checked="checked" value="1" name="sysConfigure.tenpayStatus" />关闭
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">财付通商户号:</td>
			<td><input name="sysConfigure.tenpayPartner" value="${sysConfigure.tenpayPartner }"  class="input-text wid300" /></td>
		</tr>
				<tr>
			<td width="220" align="right" style="padding-right:5px;">财付通商户密钥:</td>
			<td>
			<%
			if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
			%>
			<input name="sysConfigure.tenpayKey" value="${sysConfigure.tenpayKey }" type="password" class="input-text wid300" />
			<%
			}else{
			%>
			<input name="sysConfigure.tenpayKey" value="密钥已加密" type="password" class="input-text wid300" />
			<%
			}
			%>
			</td>
		</tr>
        <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " name="dosubmit" class="button" />
            <input type="button" value="返回" class="button" onclick="window.location.href='/admin_back/toPaySet.action';" />
            </td>
		</tr>
	</tbody>
</table>
</c:if>
<c:if test="${id=='alipay'}">
 <table width="100%" cellspacing="0">	
		<tbody>
		<tr>
			<td width="220" align="right">支付名称：</td>
			<td><input type="text" class="input-text" value="支付宝" name="pay_name" disabled="disabled"/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">支付方式：</td>
				<td>
				<input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>即时到账
			</td>
		</tr>
		<tr>
			<td width="220"  align="right">图片：</td>
			<td>
			<div  style="background-image:url('/Images/bank_logo.png'); background-position: 8px -613px;background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank_logo.png') repeat scroll 8px -613px rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">支付宝</div>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">是否开启：</td>
			<td>
			<c:if test="${sysConfigure.alipayStatus==0}">
				<input type="radio" checked="checked" value="0" name="sysConfigure.alipayStatus" />开启
				<input type="radio" value="1" name="sysConfigure.alipayStatus" />关闭
			</c:if>
			<c:if test="${sysConfigure.alipayStatus==1}">
				<input type="radio" value="0" name="sysConfigure.alipayStatus" />开启
				<input type="radio" checked="checked" value="1" name="sysConfigure.alipayStatus" />关闭
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">支付宝商户号:</td>
			<td><input name="sysConfigure.alipayPartner" value="${sysConfigure.alipayPartner }"  class="input-text wid300" /></td>
		</tr>
				<tr>
			<td width="220" align="right" style="padding-right:5px;">支付宝商户密钥:</td>
			<td>
			<%
			if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
			%>
			<input name="sysConfigure.alipayKey" value="${sysConfigure.alipayKey }" type="password" class="input-text wid300" />
			<%
			}else{
			%>
			<input name="sysConfigure.alipayKey" value="密钥已加密" type="password" class="input-text wid300" />
			<%
			}
			%>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">支付宝账号:</td>
			<td><input name="sysConfigure.alipayMail" value="${sysConfigure.alipayMail }"  class="input-text wid300" /></td>
		</tr>
        <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " name="dosubmit" class="button" />
            <input type="button" value="返回" class="button" onclick="window.location.href='/admin_back/toPaySet.action';" />
            </td>
		</tr>
	</tbody>
</table>
</c:if>
<c:if test="${id=='yeepay'}">
 <table width="100%" cellspacing="0">	
		<tbody>
		<tr>
			<td width="220" align="right">支付名称：</td>
			<td><input type="text" class="input-text" value="易宝支付" name="pay_name" disabled="disabled"/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">支付方式：</td>
				<td>
				<input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>即时到账
			</td>
		</tr>
		<tr>
			<td width="220"  align="right">图片：</td>
			<td>
			<div  style="background-image:url('/Images/yeepay.gif');background-repeat:repeat;background-attachment:scroll;background: url('/Images/yeepay.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">易宝支付</div>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">是否开启：</td>
			<td>
			<c:if test="${sysConfigure.yeepayStatus==0}">
				<input type="radio" checked="checked" value="0" name="sysConfigure.yeepayStatus" />开启
				<input type="radio" value="1" name="sysConfigure.yeepayStatus" />关闭
			</c:if>
			<c:if test="${sysConfigure.yeepayStatus==1}">
				<input type="radio" value="0" name="sysConfigure.yeepayStatus" />开启
				<input type="radio" checked="checked" value="1" name="sysConfigure.yeepayStatus" />关闭
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">易宝支付商户号:</td>
			<td><input name="sysConfigure.yeepayPartner" value="${sysConfigure.yeepayPartner }" class="input-text wid300" /></td>
		</tr>
				<tr>
			<td width="220" align="right" style="padding-right:5px;">易宝支付商户密钥:</td>
			<td>
			<%
			if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
			%>
			<input name="sysConfigure.yeepayKey" value="${sysConfigure.yeepayKey }" type="password" class="input-text wid300" />
			<%
			}else{
			%>
			<input name="sysConfigure.yeepayKey" value="密钥已加密" type="password" class="input-text wid300" />
			<%
			}
			%>
			</td>
		</tr>
        <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " name="dosubmit" class="button" />
            <input type="button" value="返回" class="button" onclick="window.location.href='/admin_back/toPaySet.action';" />
            </td>
		</tr>
	</tbody>
</table>
</c:if>

<c:if test="${id=='chinabank'}">
 <table width="100%" cellspacing="0">	
		<tbody>
		<tr>
			<td width="220" align="right">支付名称：</td>
			<td><input type="text" class="input-text" value="网银在线" name="pay_name" disabled="disabled"/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">支付方式：</td>
				<td>
				<input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>即时到账
			</td>
		</tr>
		<tr>
			<td width="220"  align="right">图片：</td>
			<td>
			<div  style="background-image:url('/Images/bank-logo.gif'); background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank-logo.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;background-position: 8px -840px;">网银在线</div>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">是否开启：</td>
			<td>
			<c:if test="${sysConfigure.chinabankStatus==0}">
				<input type="radio" checked="checked" value="0" name="sysConfigure.chinabankStatus" />开启
				<input type="radio" value="1" name="sysConfigure.chinabankStatus" />关闭
			</c:if>
			<c:if test="${sysConfigure.chinabankStatus==1}">
				<input type="radio" value="0" name="sysConfigure.chinabankStatus" />开启
				<input type="radio" checked="checked" value="1" name="sysConfigure.chinabankStatus" />关闭
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">网银在线商户号:</td>
			<td><input name="sysConfigure.chinabankPartner" value="${sysConfigure.chinabankPartner }" class="input-text wid300" /></td>
		</tr>
				<tr>
			<td width="220" align="right" style="padding-right:5px;">网银在线商户密钥:</td>
			<td>
			<%
			if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
			%>
			<input name="sysConfigure.chinabankKey" value="${sysConfigure.chinabankKey }" type="password" class="input-text wid300" />
			<%
			}else{
			%>
			<input name="sysConfigure.chinabankKey" value="密钥已加密" type="password" class="input-text wid300" />
			<%
			}
			%>
			</td>
		</tr>
        <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " name="dosubmit" class="button" />
            <input type="button" value="返回" class="button" onclick="window.location.href='/admin_back/toPaySet.action';" />
            </td>
		</tr>
	</tbody>
</table>
</c:if>

<c:if test="${id=='bill'}">
 <table width="100%" cellspacing="0">	
		<tbody>
		<tr>
			<td width="220" align="right">支付名称：</td>
			<td><input type="text" class="input-text" value="快钱支付" name="pay_name" disabled="disabled"/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">支付方式：</td>
				<td>
				<input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>即时到账
			</td>
		</tr>
		<tr>
			<td width="220"  align="right">图片：</td>
			<td>
			<div  style="background-image:url('/Images/bank-logo.gif'); background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank-logo.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;background-position: 8px -875px;">快钱支付</div>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">是否开启：</td>
			<td>
			<c:if test="${sysConfigure.billStatus==0}">
				<input type="radio" checked="checked" value="0" name="sysConfigure.billStatus" />开启
				<input type="radio" value="1" name="sysConfigure.billStatus" />关闭
			</c:if>
			<c:if test="${sysConfigure.billStatus==1}">
				<input type="radio" value="0" name="sysConfigure.billStatus" />开启
				<input type="radio" checked="checked" value="1" name="sysConfigure.billStatus" />关闭
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">快钱支付商户号:</td>
			<td><input name="sysConfigure.billPartner" value="${sysConfigure.billPartner }"  class="input-text wid300" /></td>
		</tr>
				<tr>
			<td width="220" align="right" style="padding-right:5px;">快钱支付商户密钥:</td>
			<td>
			<%
			if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
			%>
			<input name="sysConfigure.billKey" value="${sysConfigure.billKey }" type="password" class="input-text wid300" />
			<%
			}else{
			%>
			<input name="sysConfigure.billKey" value="密钥已加密" type="password" class="input-text wid300" />
			<%
			}
			%>
			</td>
		</tr>
        <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " name="dosubmit" class="button" />
            <input type="button" value="返回" class="button" onclick="window.location.href='/admin_back/toPaySet.action';" />
            </td>
		</tr>
	</tbody>
</table>
</c:if>

<c:if test="${id=='unionpay'}">
 <table width="100%" cellspacing="0">	
		<tbody>
		<tr>
			<td width="220" align="right">支付名称：</td>
			<td><input type="text" class="input-text" value="银联支付" name="pay_name" disabled="disabled"/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">支付方式：</td>
				<td>
				<input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>即时到账
			</td>
		</tr>
		<tr>
			<td width="220"  align="right">图片：</td>
			<td>
			<div  style="background-image:url('/Images/bank-logo.gif'); background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank-logo.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;background-position: 8px -910px;">银联支付</div>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">是否开启：</td>
			<td>
			<c:if test="${sysConfigure.unionpayStatus==0}">
				<input type="radio" checked="checked" value="0" name="sysConfigure.unionpayStatus" />开启
				<input type="radio" value="1" name="sysConfigure.unionpayStatus" />关闭
			</c:if>
			<c:if test="${sysConfigure.unionpayStatus==1}">
				<input type="radio" value="0" name="sysConfigure.unionpayStatus" />开启
				<input type="radio" checked="checked" value="1" name="sysConfigure.unionpayStatus" />关闭
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">银联支付商户号:</td>
			<td><input name="sysConfigure.unionpayPartner" value="${sysConfigure.unionpayPartner }" class="input-text wid300" /></td>
		</tr>
				<tr>
			<td width="220" align="right" style="padding-right:5px;">银联支付商户密钥:</td>
			<td>
			<%
			if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
			%>
			<input name="sysConfigure.unionpayKey" value="${sysConfigure.unionpayKey }" type="password" class="input-text wid300" />
			<%
			}else{
			%>
			<input name="sysConfigure.unionpayKey" value="密钥已加密" type="password" class="input-text wid300" />
			<%
			}
			%>
			</td>
		</tr>
        <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " name="dosubmit" class="button" />
            <input type="button" value="返回" class="button" onclick="window.location.href='/admin_back/toPaySet.action';" />
            </td>
		</tr>
	</tbody>
</table>
</c:if>

<c:if test="${id=='weixin'}">
 <table width="100%" cellspacing="0">	
		<tbody>
		<tr>
			<td width="220" align="right">支付名称：</td>
			<td><input type="text" class="input-text" value="微信支付" name="pay_name" disabled="disabled"/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">支付方式：</td>
				<td>
				<input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>即时到账
			</td>
		</tr>
		<tr>
			<td width="220"  align="right">图片：</td>
			<td>
			<div  style="background-image:url('/Images/bank-logo.gif'); background-repeat:repeat;background-attachment:scroll;background: url('/Images/bank-logo.gif') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;background-position: 8px -945px;">微信支付</div>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">是否开启：</td>
			<td>
			<c:if test="${sysConfigure.weixinStatus==0}">
				<input type="radio" checked="checked" value="0" name="sysConfigure.weixinStatus" />开启
				<input type="radio" value="1" name="sysConfigure.weixinStatus" />关闭
			</c:if>
			<c:if test="${sysConfigure.weixinStatus==1}">
				<input type="radio" value="0" name="sysConfigure.weixinStatus" />开启
				<input type="radio" checked="checked" value="1" name="sysConfigure.weixinStatus" />关闭
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">微信支付商户号:</td>
			<td><input name="sysConfigure.weixinPayPartner" value="${sysConfigure.weixinPayPartner }"  class="input-text wid300" /></td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">微信支付密钥:</td>
			<td>
			<%
			if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
			%>
			<input name="sysConfigure.weixinPayKey" value="${sysConfigure.weixinPayKey }" type="password" class="input-text wid300" />
			<%
			}else{
			%>
			<input name="sysConfigure.weixinPayKey" value="密钥已加密" type="password" class="input-text wid300" />
			<%
			}
			%>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">微信公众号appid:</td>
			<td><input name="sysConfigure.weixinAppId" value="${sysConfigure.weixinAppId }"  class="input-text wid300" /></td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">微信公众号密钥:</td>
			<td><input name="sysConfigure.weixinAppSecret" value="${sysConfigure.weixinAppSecret }"  class="input-text wid300" /></td>
		</tr>
		<%-- <tr>
			<td width="220" align="right" style="padding-right:5px;">微信支付APPKEY:</td>
			<td><input name="sysConfigure.weixinAppKey" value="${sysConfigure.weixinAppKey }"  class="input-text wid300" /></td>
		</tr> --%>
        <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " name="dosubmit" class="button" />
            <input type="button" value="返回" class="button" onclick="window.location.href='/admin_back/toPaySet.action';" />
            </td>
		</tr>
	</tbody>
</table>
</c:if>

<c:if test="${id=='aliPayUser'}">
 <table width="100%" cellspacing="0">	
		<tbody>
		<tr>
			<td width="220" align="right">支付名称：</td>
			<td><input type="text" class="input-text" value="支付宝免签" name="pay_name" disabled="disabled"/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">支付方式：</td>
				<td>
				<input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>即时到账
			</td>
		</tr>
		<tr>
			<td width="220"  align="right">图片：</td>
			<td>
			<div  style="background-image:url('/Images/aliPayUser.png'); background-repeat:repeat;background-attachment:scroll;background: url('/Images/aliPayUser.png') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">支付宝免签</div>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">是否开启：</td>
			<td>
			<c:if test="${sysConfigure.aliPayUserStatus==0}">
				<input type="radio" checked="checked" value="0" name="sysConfigure.aliPayUserStatus" />开启
				<input type="radio" value="1" name="sysConfigure.aliPayUserStatus" />关闭
			</c:if>
			<c:if test="${sysConfigure.aliPayUserStatus==1}">
				<input type="radio" value="0" name="sysConfigure.aliPayUserStatus" />开启
				<input type="radio" checked="checked" value="1" name="sysConfigure.aliPayUserStatus" />关闭
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">支付宝免签商户ID:</td>
			<td><input name="sysConfigure.aliPaySignId" value="${sysConfigure.aliPaySignId }"  class="input-text wid300" /></td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">支付宝免签商户密钥:</td>
			<%
			if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
			%>
			<td><input name="sysConfigure.aliPaySignKey" value="${sysConfigure.aliPaySignKey }" type="password" class="input-text wid300" /></td>
			<%
			}else{
			%>
			<td><input name="sysConfigure.aliPaySignKey" value="密钥已加密" type="password" class="input-text wid300" /></td>
			<%
			}
			%>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">支付宝收款帐号:</td>
			<td><input name="sysConfigure.aliPayUser" value="${sysConfigure.aliPayUser }"  class="input-text wid300" /></td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">软件通知地址为:</td>
			<td>${sysConfigure.wwwUrl }/alipay/noKeyRecharge.html</td>
		</tr>
        <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " name="dosubmit" class="button" />
            <input type="button" value="返回" class="button" onclick="window.location.href='/admin_back/toPaySet.action';" />
            </td>
		</tr>
	</tbody>
</table>
</c:if>

<c:if test="${id=='tenPayUser'}">
 <table width="100%" cellspacing="0">	
		<tbody>
		<tr>
			<td width="220" align="right">支付名称：</td>
			<td><input type="text" class="input-text" value="财付通免签" name="pay_name" disabled="disabled"/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">支付方式：</td>
				<td>
				<input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>即时到账
			</td>
		</tr>
		<tr>
			<td width="220"  align="right">图片：</td>
			<td>
			<div  style="background-image:url('/Images/tenPayUser.png'); background-repeat:repeat;background-attachment:scroll;background: url('/Images/tenPayUser.png') repeat scroll 3px 0 rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">财付通免签</div>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">是否开启：</td>
			<td>
			<c:if test="${sysConfigure.tenPayUserStatus==0}">
				<input type="radio" checked="checked" value="0" name="sysConfigure.tenPayUserStatus" />开启
				<input type="radio" value="1" name="sysConfigure.tenPayUserStatus" />关闭
			</c:if>
			<c:if test="${sysConfigure.tenPayUserStatus==1}">
				<input type="radio" value="0" name="sysConfigure.tenPayUserStatus" />开启
				<input type="radio" checked="checked" value="1" name="sysConfigure.tenPayUserStatus" />关闭
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">财付通免签商户ID:</td>
			<td><input name="sysConfigure.tenPaySignId" value="${sysConfigure.tenPaySignId }"  class="input-text wid300" /></td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">财付通免签商户密钥:</td>
			<%
			if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
			%>
			<td><input name="sysConfigure.tenPaySignKye" value="${sysConfigure.tenPaySignKye }" type="password" class="input-text wid300" /></td>
			<%
			}else{
			%>
			<td><input name="sysConfigure.tenPaySignKye" value="密钥已加密" type="password"  class="input-text wid300" /></td>
			<%
			}
			%>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">财付通收款帐号:</td>
			<td><input name="sysConfigure.tenPayUser" value="${sysConfigure.tenPayUser }" class="input-text wid300" /></td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">软件通知地址为:</td>
			<td>${sysConfigure.wwwUrl }/alipay/noKeyRecharge.html</td>
		</tr>
        <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " name="dosubmit" class="button" />
            <input type="button" value="返回" class="button" onclick="window.location.href='/admin_back/toPaySet.action';" />
            </td>
		</tr>
	</tbody>
</table>
</c:if>

<c:if test="${id=='yunPay'}">
 <table width="100%" cellspacing="0">	
		<tbody>
		<tr>
			<td width="220" align="right">支付名称：</td>
			<td><input type="text" class="input-text" value="云支付" name="pay_name" disabled="disabled"/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">支付方式：</td>
				<td>
				<input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>即时到账
			</td>
		</tr>
		<tr>
			<td width="220"  align="right">图片：</td>
			<td>
			<div  style="background-image:url('/Images/yunPay.png');background-repeat:repeat;background-attachment:scroll;background: url('/Images/yunPay.png') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">云支付</div>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">是否开启：</td>
			<td>
			<c:if test="${sysConfigure.yunPayStatus==0}">
				<input type="radio" checked="checked" value="0" name="sysConfigure.yunPayStatus" />开启
				<input type="radio" value="1" name="sysConfigure.yunPayStatus" />关闭
			</c:if>
			<c:if test="${sysConfigure.yunPayStatus==1}">
				<input type="radio" value="0" name="sysConfigure.yunPayStatus" />开启
				<input type="radio" checked="checked" value="1" name="sysConfigure.yunPayStatus" />关闭
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">云支付合作者ID:</td>
			<td><input name="sysConfigure.yunPayPartner" value="${sysConfigure.yunPayPartner }" class="input-text wid300" /></td>
		</tr>
				<tr>
			<td width="220" align="right" style="padding-right:5px;">云支付密钥:</td>
			<td>
			<%
			if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
			%>
			<input name="sysConfigure.yunPayKey" value="${sysConfigure.yunPayKey }" type="password" class="input-text wid300" />
			<%
			}else{
			%>
			<input name="sysConfigure.yunPayKey" value="密钥已加密" type="password" class="input-text wid300" />
			<%
			}
			%>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">云会员账户(邮箱):</td>
			<td><input name="sysConfigure.yunPayMail" value="${sysConfigure.yunPayMail }"  class="input-text wid300" /></td>
		</tr>
        <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " name="dosubmit" class="button" />
            <input type="button" value="返回" class="button" onclick="window.location.href='/admin_back/toPaySet.action';" />
            </td>
		</tr>
	</tbody>
</table>
</c:if>

<c:if test="${id=='iAppPay'}">
 <table width="100%" cellspacing="0">	
		<tbody>
		<tr>
			<td width="220" align="right">支付名称：</td>
			<td><input type="text" class="input-text" value="爱贝云计费" name="pay_name" disabled="disabled"/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">支付方式：</td>
				<td>
				<input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>即时到账
			</td>
		</tr>
		<tr>
			<td width="220"  align="right">图片：</td>
			<td>
			<div  style="background-image:url('/Images/iAppPay.png');background-repeat:repeat;background-attachment:scroll;background: url('/Images/iAppPay.png') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">爱贝云计费</div>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">是否开启：</td>
			<td>
			<c:if test="${sysConfigure.iapppayStatus==0}">
				<input type="radio" checked="checked" value="0" name="sysConfigure.iapppayStatus" />开启
				<input type="radio" value="1" name="sysConfigure.iapppayStatus" />关闭
			</c:if>
			<c:if test="${sysConfigure.iapppayStatus==1}">
				<input type="radio" value="0" name="sysConfigure.iapppayStatus" />开启
				<input type="radio" checked="checked" value="1" name="sysConfigure.iapppayStatus" />关闭
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">iAppPay应用编号:</td>
			<td><input name="sysConfigure.iapppayAppId" value="${sysConfigure.iapppayAppId }" class="input-text wid300" /></td>
		</tr>
				<tr>
			<td width="220" align="right" style="padding-right:5px;">iAppPay应用私钥:</td>
			<td>
			<textarea name="sysConfigure.iapppayAppKey" class="input-text wid300" style="line-height:16px; height:35px; width:663px;">${sysConfigure.iapppayAppKey }</textarea>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">iAppPay平台公钥:</td>
			<td><textarea name="sysConfigure.iapppayPlatKey" class="input-text wid300" style="line-height:16px; height:35px; width:663px;">${sysConfigure.iapppayPlatKey }</textarea></td>
		</tr>
        <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " name="dosubmit" class="button" />
            <input type="button" value="返回" class="button" onclick="window.location.href='/admin_back/toPaySet.action';" />
            </td>
		</tr>
	</tbody>
</table>
</c:if>

<c:if test="${id=='jubaoPay'}">
 <table width="100%" cellspacing="0">	
		<tbody>
		<tr>
			<td width="220" align="right">支付名称：</td>
			<td><input type="text" class="input-text" value="聚宝云计费" name="pay_name" disabled="disabled"/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">支付方式：</td>
				<td>
				<input type="radio" checked="checked" value="1" name="pay_type" disabled="disabled"/>即时到账
			</td>
		</tr>
		<tr>
			<td width="220"  align="right">图片：</td>
			<td>
			<div  style="background-image:url('/Images/jubaoPay.png');background-repeat:repeat;background-attachment:scroll;background: url('/Images/jubaoPay.png') repeat scroll rgba(0, 0, 0, 0);height: 36px;width: 120px;border: 1px solid #ddd;text-indent: -9999px;background-color: #fff;">聚宝云计费</div>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">是否开启：</td>
			<td>
			<c:if test="${sysConfigure.jubaoPayStatus==0}">
				<input type="radio" checked="checked" value="0" name="sysConfigure.jubaoPayStatus" />开启
				<input type="radio" value="1" name="sysConfigure.jubaoPayStatus" />关闭
			</c:if>
			<c:if test="${sysConfigure.jubaoPayStatus==1}">
				<input type="radio" value="0" name="sysConfigure.jubaoPayStatus" />开启
				<input type="radio" checked="checked" value="1" name="sysConfigure.jubaoPayStatus" />关闭
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="220" align="right" style="padding-right:5px;">jubaoPay应用编号:</td>
			<td><input name="sysConfigure.jubaoPayPartner" value="${sysConfigure.jubaoPayPartner }" class="input-text wid300" /></td>
		</tr>
        <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " name="dosubmit" class="button" />
            <input type="button" value="返回" class="button" onclick="window.location.href='/admin_back/toPaySet.action';" />
            </td>
		</tr>
	</tbody>
</table>
</c:if>



</form>

</div>
 </body>
</html>
