<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<a href="javascript:;">角色列表</a><span class="span_fenge lr5">|</span>
	<a href="/admin_back/toAddRole.action">添加角色</a>
</div>
<div class="bk10"></div>
<div class="table-list lr10">
<table width="100%" cellspacing="0">
<thead>
<tr>
<th width="10%">ID</th>
<th width="25%">角色名称</th>
<th width="45%">角色描述</th>
<th width="20%">操作</th>
</tr>
</thead>
<tbody>
<s:iterator value="roleList" var="role" status="st">
<tr >
	<td align="left" style="padding-left: 5px;font-size: 14px;">${role.id }</td>
	<td align="center">${role.name }</td>
	<td align="center">${role.description }</td>
	<td align="center">
        <a href="/admin_back/toEditRole.action?id=${role.id}">修改</a>
        <span class="span_fenge lr5">|</span>
                <font color="#cccccc">删除</font>
	</td>
</tr>
</s:iterator>
</tbody>
</table>
</div>
 	${pageString }
  </body>
</html>
