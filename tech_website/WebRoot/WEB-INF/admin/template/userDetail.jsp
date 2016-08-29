<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.pojo.User"%>
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
  
<div class="table_form lr10">
<!--start-->
 <c:choose>
	<c:when test="${user.userId!=null}">
		<form action="/admin_back/doUpdateUser.action" method="post" enctype="multipart/form-data" style="border-radius: 0px;" class="form-horizontal group-border-dashed">
		<input type="hidden" name="user.userId" value="${user.userId }"/>
		<input type="hidden" name="backUrl" value="${backUrl }"/>
	</c:when>
	<c:otherwise>
		<form enctype="multipart/form-data" method="post" action="/admin_back/doAddUser.action" name="myform" enctype="multipart/form-data">
		<input type="hidden" name="backUrl" value="${backUrl }"/>
	</c:otherwise>
</c:choose>
<input type="hidden" name="user.userType" value="0"/>
  <table width="100%" cellspacing="0">
  	 <tbody><tr>
			<td width="120" align="right">昵称：</td>
			<td>${user.userName }</td>
		</tr>
		<tr>
			<td width="120" align="right">邮箱：</td>
			<td>${user.mail }</td>
		</tr>
		<tr>
			<td width="120" align="right">手机：</td>
			<td>${user.phone }</td>
		</tr>
		<tr>
			<td width="120" align="right">QQ：</td>
			<td>${user.qq }</td>
		</tr>
		<tr>
			<td width="120" align="right">用户图像：</td>
			<td><img src="${user.faceImg }" alt="" /></td>
		</tr>
		<tr>
			<td width="120" align="right">ip地址：</td>
			<td>${user.ipAddress }</td>
		</tr>
		<tr>
			<td width="120" align="right">ip归属地：</td>
			<td>${user.ipLocation }</td>
		</tr>
		<tr>
			<td width="120" align="right">上次登录IP所在地：</td>
			<td>${user.oldIpAddress }</td>
		</tr>
		<tr>
			<td width="120" align="right">上次登录时间：</td>
			<td>${user.oldDate }</td>
		</tr>
		<tr>
			<td width="120" align="right">本次登录时间：</td>
			<td>${user.newDate }</td>
		</tr>
		<tr>
			<td width="120" align="right">账户余额：</td>
			<td>${user.balance }元</td>
		</tr>
		<tr>
			<td width="120" align="right">经验值：</td>
			<td>${user.experience }</td>
		</tr>
		<tr>
			<td width="120" align="right">邀请人：</td>
			<td>${user.invite }(邀请人用户ID)</td>
		</tr>
		<tr>
			<td width="120" align="right">佣金：</td>
			<td>${user.commissionCount }</td>
		</tr>
		<tr>
			<td width="120" align="right">佣金余额：</td>
			<td>${user.commissionBalance }</td>
		</tr>	
		<tr>
			<td width="120" align="right">佣金提现：</td>
			<td>${user.commissionMention }</td>
		</tr>
		<tr>
			<td width="120" align="right">积分：</td>
			<td>${user.commissionPoints }</td>
		</tr>
		<tr>
			<td width="120" align="right">签名：</td>
			<td>${user.signature }</td>
		</tr>
		<tr>
        	<td width="120" align="right"></td>
            <td>
            <input type="button" value="返回" class="button"  onclick="history.go(-1)" />
            <input type="button" value="关闭" class="button"  onclick="window.close()" />
            </td>
		</tr>
</tbody></table>
</form>
</div>
  </body>
</html>
