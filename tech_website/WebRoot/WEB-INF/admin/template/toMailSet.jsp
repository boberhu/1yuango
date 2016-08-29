<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
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
<div class="header-title lr10"><b>系统邮箱设置</b></div>
<div class="bk10"></div>
<div class="table_form lr10">
<form  method="post" action="/admin_back/doSeoSet.action">

<table width="100%" class="lr10">
  <tbody><tr>
    <td width="100px;">邮件发送模式</td>
    <td>
     <input type="radio"  value="1" disabled="disabled" checked="checked"/> SMTP 函数发送    		     
	</td>
  </tr>
  <tr>
    <td>邮件服务器：</td>
    <td><input type="text" value="${sysConfigure.mailsmtp }" size="30" name="sysConfigure.mailsmtp" class="input-text"  /></td>
  </tr>  
  <tr>
    <td>系统邮箱：</td>
    <td><input type="text" value="${sysConfigure.mailName }" size="30" name="sysConfigure.mailName" class="input-text" /></td>
  </tr> 
    <tr>
	 <td>系统邮箱密码：</td>
	 <td>
	 <%
		if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
		%>
	 	<input type="password" value="${sysConfigure.mailPwd }" size="30" name="sysConfigure.mailPwd" class="input-text" />
	 <%
		}else{
		%>
		<input type="password" value="密码已经加密" size="30" name="sysConfigure.mailPwd" class="input-text" />
		<%
		}
		%>
	 </td>
  </tr>
  <tr>
    <td>发送人名称：</td>
    <td><input type="text" value="${sysConfigure.saitName }" size="30" disabled="disabled" class="input-text wid80" />默认为网站名称</td>
  </tr>
   <tr>
    <td>发送编码：</td>
    <td><input type="text" value="utf-8" name="big" class="input-text wid80" disabled="disabled" /></td>
  </tr> 
	<tr>
   	<td width="100"></td> 
  		<td><input type="submit" value=" 提交 " class="button" />
           <input type="button" value="返回" class="button"  onclick="history.go(-1)" /></td>
   </tr>
</tbody></table>
</form>
</div>
<div class="header-title lr10"><b>邮件发送系统</b></div>
<div class="bk10"></div>
<div class="table_form lr10">
<form id="myform" method="post" action="/admin_back/doTestMail.action">
<table width="100%" class="lr10">
<tbody>
  <tr>
	 <td width="100px;">收件人：</td>
	 <td>
     <input type="text" size="60" class="input-text" id="testMailName" name="userName" /> 多个收件人请用英文逗号(,)分割
     </td>
  </tr> 
  <tr>
  	<td>邮件主题：</td>
  	<td><input type="text" size="60" class="input-text" name="tId" id="testMailSubject" /></td>
  </tr>
  <tr>
  	<td>邮件内容：</td>
  	<td><textarea name="message" style=" height:350px; width:650px" class="input-text" id="testMialText"></textarea></td>
  </tr>
  <tr>
  	<td></td>
  	<td><input type="button" class="button" id="sendTestMail" value=" 发送邮件 " /></td>
  </tr>
</tbody>
</table>
</form>
</div>
<script type="text/javascript">
	var b = function (w) {
		var x = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		return x.test(w);
	};
	$("#sendTestMail").click(function(){
		var G = $("#testMailName").val();
		var S = $("#testMailSubject").val();
		var T = $("#testMialText").val();
		if (G==""){
			alert("请输入正确的邮箱地址！");
			return false;
		}else if(S==""){
			alert("请输入邮件主题！");
			return false;
		}else if(T==""){
			alert("请输入邮件内容！");
			return false;
		}else{
			$("#myform").submit();
		}
	});
	
</script>
 </body>
</html>
