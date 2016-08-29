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
	第${spellbuyproduct.productPeriod }期
	</div>
	<div class="bk10"></div>
	
	<div class="table-list lr10">
	<table width="100%" cellspacing="0">
	<thead>
	<tr>
                 <th width="15%">购买时间</th>
                 <th width="10%">用户ID</th>  
                 <th width="10%">会员名称</th>            
                 <th width="10%">购买数量</th>     
                 <th width="10%">购买IP</th>     
                 <th width="10%">IP来源</th>    
                 <th width="10%">购买类型</th>   
	</tr>
     </thead>
	<tbody>
	<s:iterator value="orderJSONList" var="order">
		<tr>
		<td align="center">${order.buyDate }</td>
		<td align="center">${order.userId }</td>
		<td align="center">${order.userName }</td>
		<td align="center">${order.buyCount }</td>
		<td align="center">${order.ip_address }</td>
		<td align="center">${order.ip_location }</td>
		<td align="center">
			<c:choose>
				<c:when test="${order.buySource == 1}">
				 	<span style="color:Red;">机器人购买</span>
		    	</c:when>
				<c:when test="${order.buySource == 2}">
				 	会员购买
		    	</c:when>
		    </c:choose>
		</td>
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
