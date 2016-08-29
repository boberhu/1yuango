<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<title>1元夺宝_管理中心</title>
	<link href="/admin_css/global.css"  rel="stylesheet" type="text/css"/>
	<link href="/admin_css/admin_style.css"  rel="stylesheet" type="text/css"/>
	<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
  </head>
  
  <body>
  <div class="header lr10">
	商品名称：${product.productName }
	</div>
	<div class="bk10"></div>
	
	<div class="table-list lr10">
	<table width="100%" cellspacing="0">
	<thead>
	<tr>
             	<th width="5%">期数ID</th>
                 <th width="5%">期数</th>        
                 <th width="10%">开始时间</th>     
                 <th width="10%">结束时间</th>   
                 <th width="5%">价格</th>             
                 <th width="5%">当前购买数</th>
                 <th width="5%">当前状态</th>
                 <th width="10%">操作</th>
	</tr>
     </thead>
	<tbody>
	<s:iterator value="productPeriodList" var="period">
		<tr>
		<td align="center">${period.spellbuyProductId }</td>
		<td align="center">${period.productPeriod }</td>
		<td align="center">${period.spellbuyStartDate }</td>
		<td align="center">${period.spellbuyEndDate }</td>
		<td align="center">${product.productPrice }</td>
		<td align="center">${period.spellbuyCount }</td>
		<td align="center">
			<c:choose>
				<c:when test="${period.spStatus == 0}">
				 	进行中
		    	</c:when>
				<c:when test="${period.spStatus == 2}">
				 	揭晓中
		    	</c:when>
				<c:when test="${period.spStatus == 1}">
				 	已结束
		    	</c:when> 
		    </c:choose>
		</td>
		<td align="center"><a href="/admin_back/periodOrderList.action?id=${period.spellbuyProductId }">[交易记录]</a></td>
	</tr>
	</s:iterator>
	</tbody>
	</table>
	${pageString }
	</div>
  
 	 	<script language="javascript" type="text/javascript">
 		$("#select").change(function(){
 			location.replace("/admin_back/index.action?id="+$("#select").val());
 		});
 		$("[name=indexTop]").click(function(){
 			var id = $(this).attr("pid");
 			$.ajax({
 				url:"/admin_back/indexTop.action?id="+id,
 				success:function(data){
 					if(data=="success"){
 						alert("操作成功");
 					}else if(data=="test"){
 						alert("权限不足，请联系管理员！");
 					}
 				},
 				error:function(){
 					alert("网络错误，请稍后再试！");
 				}
 			});
 		});
 	</script>
  </body>
</html>
