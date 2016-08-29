<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.pojo.Admin"%>
<%@page import="com.tech.util.Struts2Utils"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	ResourceBundle url = ResourceBundle.getBundle("config");
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>1元夺宝_管理中心</title>
		<link href="/admin_css/global.css"  rel="stylesheet" type="text/css"/>
		<link href="/admin_css/admin_style.css"  rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="/css/dateinput.css" />
		<script type="text/javascript" src="/ueditor/editor_config.js"></script>
		<script type="text/javascript" src="/ueditor/editor_all.js"></script>
		<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
		
	</head>

	<body>
	<div class="header lr10"><a href="/admin_back/totalRecharge.action">充值消费统计</a><span class="span_fenge lr5"></div>
	<div class="bk10"></div>

	<div class="header-data lr10">
		<form action="/admin_back/totalRecharge.action" type="post">
		 查询时间：
		<input type="text" class="input-text wid100" id="startDate" name="startDate"  />
		-<input type="text" class="input-text wid100" id="endDate" name="endDate"  />
		<input type="submit" value="搜索" id="search"  class="button" />
		</form>
	</div>
	<div class="bk10">
	</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0">
			<tbody>
				<tr>
					<td width="120" align="right">
						充值总额：
					</td>
					<td>
						${recharge }元
					</td>
				</tr>
				<tr>
					<td width="120" align="right">
						消费总额：
					</td>
					<td>
						${buy }元
					</td>
				</tr>
				<tr>
					<td width="120" align="right">
						支付内容：
					</td>
					<td>
						余额支付：${pay }元 &nbsp;&nbsp;&nbsp;&nbsp;积分支付：${payIntegral }积分
					</td>
				</tr>
				<tr>
					<td width="120" align="right">
						消费获得积分：
					</td>
					<td>
						${addIntegral }积分
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
	<script language="javascript" type="text/javascript" src="/js/date.js?data=20131121"></script>
    	<script language="javascript" type="text/javascript" src="/js/dateinput.js?data=20131121"></script>
    	<script type="text/javascript">
	 		var a = new Date();
	 		var X = $("#startDate");
	 		var Y = $("#endDate");

	 		<c:choose>
		 		<c:when test="${startDate!=null}">
			 		var start = new Date(Date.parse("${startDate}".replace(/-/g, "/")));
			 		X.val(start.Format("YYYY-MM-DD")).date_input();
		 		</c:when>
		 		<c:otherwise>
		 			X.val(a.Format("YYYY-MM-DD")).date_input();
		 		</c:otherwise>
		 	</c:choose>

	 		<c:choose>
		 		<c:when test="${endDate!=null}">
		 			var end = new Date(Date.parse("${endDate}".replace(/-/g, "/")));
			 		Y.val(end.Format("YYYY-MM-DD")).date_input();
		 		</c:when>
		 		<c:otherwise>
		 			Y.val(a.Format("YYYY-MM-DD")).date_input();
		 		</c:otherwise>
		 	</c:choose>
	 	</script> 
	</body>
</html>
