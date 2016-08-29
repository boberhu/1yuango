<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.Struts2Utils"%>
<%@page import="com.tech.pojo.Admin"%>
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
<div class="header-title lr10"><b>管理员管理</b></div>
<div class="bk10"></div>
<div class="table-list lr10">
  <table width="100%" cellspacing="0">
    <thead>
		<tr>
		<th width="10%">管理员ID</th>
		<th width="10%" align="left">用户名</th>
		<!-- <th width="10%" align="left">所属角色</th> -->
		<th width="15%" align="left">最后登录IP所在地</th>
		<th width="10%" align="left">最后登录时间</th>
		<th width="10%">状态</th>
		<th width="15%">管理操作</th>
		</tr>
    </thead>
  	<tbody>
  	<s:iterator var="admin" value="adminList">
        <tr>
        <td align="center">${admin.id }</td>
        <td >${admin.userName }</td>
        <!-- <td>超级管理员</td> -->
		<td >${admin.user.oldIpAddress }</td>
        <td >${admin.user.oldDate }</td>
        <td align="center">
        	<c:choose>
				<c:when test="${admin.disabled}"><span style="color:red;">禁用</span></c:when>
				<c:otherwise><span style="color:green;">正常</span></c:otherwise>
			</c:choose>
        </td>
        <td align="center">
        <a href="/admin_back/toEditAdmin.action?id=${admin.id}">修改</a>
        <span class="span_fenge lr5">|</span>
                <font color="#cccccc">删除</font>
                </td>
        </tr>
  	</s:iterator>
  	</tbody>
</table>
</div>
 </body>
</html>
