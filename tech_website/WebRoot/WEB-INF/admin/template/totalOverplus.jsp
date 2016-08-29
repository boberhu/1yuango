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
<link href="/admin_css/global.css" rel="stylesheet" type="text/css" />
<link href="/admin_css/admin_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/css/dateinput.css" />
<script type="text/javascript" src="/ueditor/editor_config.js"></script>
<script type="text/javascript" src="/ueditor/editor_all.js"></script>
<script language="javascript" type="text/javascript"
	src="/js/jquery-1.4.4-min.js"></script>

</head>

<body>
	<div class="header lr10">
		<a href="/admin_back/totalOverplus.action">剩余财富统计</a><span
			class="span_fenge lr5">
	</div>
	<div class="bk10"></div>

	<div class="table_form lr10">
		<table width="100%" cellspacing="0">
			<tbody>
				<tr>
					<td width="120" align="right">剩余余额：</td>
					<td>${balance }元</td>
				</tr>
				<tr>
					<td width="120" align="right">剩余积分：</td>
					<td>${integral }元</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
	<script language="javascript" type="text/javascript"
		src="/js/date.js?data=20131121"></script>
	<script language="javascript" type="text/javascript"
		src="/js/dateinput.js?data=20131121"></script>
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
