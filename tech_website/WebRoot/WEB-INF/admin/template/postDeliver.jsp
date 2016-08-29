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
	<link rel="stylesheet" type="text/css" href="/css/dateinput.css" />
	<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
  </head>
  
  <body>
<div class="header-title lr10"><b>订单发货管理</b></div>
<div class="bk10"></div>
<div class="table-list lr10">
<!--start-->
<form method="post" action="/admin_back/doPostDeliver.action" name="myform">
<input type="hidden" name="id" value="${id }"/>
<input type="hidden" name="orderdetailaddress.id" value="${orderdetailaddress.id }"/>
<input type="hidden" name="orderdetailaddress.orderDetailId" value="${orderdetailaddress.orderDetailId }"/>
<input type="hidden" name="orderdetailaddress.consignee" value="${orderdetailaddress.consignee }"/>
<input type="hidden" name="orderdetailaddress.phone" value="${orderdetailaddress.phone }"/>
<input type="hidden" name="orderdetailaddress.address" value="${orderdetailaddress.address }"/>
<input type="hidden" name="orderdetailaddress.postDate" value="${orderdetailaddress.postDate }"/>
<input type="hidden" name="orderdetailaddress.orderRemarks" value="${orderdetailaddress.orderRemarks }"/>
  <table width="100%" cellspacing="0">	
		<tbody><tr>
			<td width="220" align="right">商品信息：</td>
			<td>
			<table border="0" cellspacing="0" cellpadding="0" style="width: 600px;">
	         <tbody>
	         <tr class="infoTitle">
		            <td class="comm">商品</td>
	                <td class="bor">市场价</td>
	                <td>数量</td>
	            </tr>
	             <tr>
	              <td class="commodity">
	              <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${latestlottery.spellbuyProductId }.html" target="_blank">
	              <img width="70" height="70" border="0" align="left" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${latestlottery.productImg }" alt="${latestlottery.productTitle }"></a>
	              <em class="orange">(第${latestlottery.productPeriod }期)</em><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${latestlottery.spellbuyProductId }.html" target="_blank">${latestlottery.productName }</a>
	              	幸运编号：${latestlottery.randomNumber }<br />揭晓时间：${latestlottery.announcedTime }</td>
	                 <td>￥${latestlottery.productPrice }.00</td>
	                 <td>1</td>
	             </tr>
	        </tbody>
	        </table>
			</td>
		</tr>
		<tr>
			<td width="220" align="right">收货信息：</td>
			<td>
				收 货 人：${orderdetailaddress.consignee } <br/>
				联系电话：${orderdetailaddress.phone } <br/>
				收货地址：${orderdetailaddress.address } <br/>
				配送时间：${orderdetailaddress.postDate } <br/>
				订单备注：${orderdetailaddress.orderRemarks } <br/>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">快递单号：</td>
			<td><input type="text" class="input-text" value="${orderdetailaddress.expressNo }" name="orderdetailaddress.expressNo" /></td>
		</tr>	
		<tr>
			<td width="220" align="right">快递公司：</td>
			<td><input type="text" class="input-text" value="${orderdetailaddress.expressCompany }" name="orderdetailaddress.expressCompany" /></td>
		</tr>
		<tr>
			<td width="220" align="right">发货时间：</td>
			<td><input type="text" class="input-text" id="time" value="${orderdetailaddress.deliverTime }"  name="orderdetailaddress.deliverTime" /></td>
		</tr>		
		<tr>
			<td width="220" align="right">发货备注：</td>
			<td><input type="text" class="input-text  wid500" value="${orderdetailaddress.deliverRemarks }"  name="orderdetailaddress.deliverRemarks" /></td>
		</tr>	
        <tr>
        	<td width="220" align="right"></td>
            <td> 
            <input type="submit" value=" 提交 " id="submit" class="button" />
            <input type="button" value="返回" class="button"  onclick="history.go(-1)" /></td>
		</tr>
</tbody></table>
</form>
</div>
 	<script language="javascript" type="text/javascript" src="/js/date.js?data=20131121"></script>
    <script language="javascript" type="text/javascript" src="/js/dateinput.js?data=20131121"></script>
     	<script type="text/javascript">
 		$("#submit").click(function(){
 			if($("[name='orderdetailaddress.expressNo']").val()==""){
 				alert("请输入快递单号！");
 				return false;	
 			}
 			if($("[name='orderdetailaddress.expressCompany']").val()==""){
 				alert("请输入快递公司名称！");
 				return false;	
 			}
 			if($("[name='orderdetailaddress.deliverTime']").val()==""){
 				alert("请输入发货时间！");
 				return false;	
 			}
 			$("form:first").submit(); 
 		});
 		var a = new Date();
 		var X = $("#time");
 		X.val(a.Format("YYYY-MM-DD")).date_input();
 	</script> 
 </body>
</html>
