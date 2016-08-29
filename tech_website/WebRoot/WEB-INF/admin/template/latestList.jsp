<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>管理中心</title>
	<link href="/admin_css/global.css"  rel="stylesheet" type="text/css"/>
	<link href="/admin_css/admin_style.css"  rel="stylesheet" type="text/css"/>
	<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
  </head>
  
  <body>
  <div class="bk10"></div>
  <input type="hidden" value="${typeId }" id="typeId"/>
  <div class="header-data lr10">
<select id="orderName" class="form-control" style="width:180px;">
<option value="">请选择排序</option>
<option value="time">按时间正序</option>
<option value="timeDesc">按时间倒序</option>
<option value="user">按获得者正序</option>
<option value="userDesc">按获得者倒序</option>
<option value="product">按商品正序</option>
<option value="productDesc">按商品倒序</option>
</select>
<!-- <input type="submit" id="query" value="查询"  class="button" /> -->
<script language="javascript" type="text/javascript">
	$("#orderName").change(function(){
		var typeId = $("#typeId").val();
		var typeName = $("#orderName").val();
		window.location.href = "latestList.action?typeName="+typeName+(typeId!=''?'&typeId='+typeId:'');
	});
	/* $("#query").click(function(){
		location.replace("/admin_back/orderList.action?userName="+$("#keyword").val());
	}); */
</script>
</div>
<div class="bk10"></div>
<div class="table-list lr10">
<table width="100%" cellspacing="0">
<thead>
<tr>
<th width="5%">订单号</th>
<th width="5%">商品期数</th>
<th width="15%">商品名称</th>
<th width="10%">揭晓时间</th>
<th width="5%">获得者</th>
<th width="5%">商品图片</th>
<th width="5%">交易状态</th>
<th width="5%">操作</th>
</tr>
</thead>
<tbody>
<s:iterator value="latestlotteryList" var="latestlotterys">
<tr>
	<td align="center">${latestlotterys.dingdanhao }</td>
	<td align="center">${latestlotterys.productPeriod }</td>
	<td  align="center">${latestlotterys.productName }</td>
	<td  align="center">${latestlotterys.announcedTime }</td>
	<td  align="center"><a href="/admin_back/userDetail.action?id=${latestlotterys.userId }" target="_blank">${latestlotterys.userName }</a></td>
	<td  align="center"><img width="50px;" height="50px;" src="${latestlotterys.productImg }"/></td>
	<td  align="center">
	<c:if test="${latestlotterys.status == 1}">
		未提交收获地址
	</c:if>
	<c:if test="${latestlotterys.status == 2}">
		<span style="color:red;">等待发货</span>
	</c:if>
	<c:if test="${latestlotterys.status == 3}">
		<span style="color:red;">等待收货</span>
	</c:if>
	<c:if test="${latestlotterys.status == 4}">
		<span style="color:red;">已确认收货</span>
		<c:if test="${latestlotterys.shareStatus == -1}">
			<span style="color:#999;">暂未晒单</span>
		</c:if>
	</c:if>
	<c:if test="${latestlotterys.status == 10}">
		<span style="color:red;">交易完成</span>
	</c:if>
	<c:if test="${latestlotterys.status == 11}">
		<span style="color:red;">交易取消</span>
	</c:if>
	</td>
	<td  align="center">
	<c:if test="${latestlotterys.status == 1}">
		<a href="javascript:alert('用户未提交收获地址');">[发货]</a>
	</c:if>
	<c:if test="${latestlotterys.status == 2}">
		<a href="/admin_back/toPostDeliver.action?id=${latestlotterys.spellbuyProductId }">[发货]</a>
	</c:if>
	<c:if test="${latestlotterys.status == 3}">
		<a href="/admin_back/latestDetail.action?id=${latestlotterys.spellbuyProductId }">[已发货]</a>
	</c:if>
	<c:if test="${latestlotterys.status == 4}">
		<a href="/admin_back/latestDetail.action?id=${latestlotterys.spellbuyProductId }">[已确认收货]</a>
	</c:if>
	<c:if test="${latestlotterys.status == 10}">
		<a href="/admin_back/latestDetail.action?id=${latestlotterys.spellbuyProductId }">[交易完成]</a>
	</c:if>
	<c:if test="${latestlotterys.status == 11}">
		<a href="/admin_back/latestDetail.action?id=${latestlotterys.spellbuyProductId }">[交易取消]</a>
	</c:if>
	</td>
</tr>
</s:iterator>
</tbody>
</table>
</div>
		${pageString }
  </body>
</html>
