<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<title>1元夺宝_管理中心</title>
	<link href="${ctx }/admin_css/global.css"  rel="stylesheet" type="text/css"/>
	<link href="${ctx }/admin_css/admin_style.css"  rel="stylesheet" type="text/css"/>
	<script language="javascript" type="text/javascript" src="${ctx }/js/jquery-1.4.4-min.js"></script>
   <style>
.lanrentuku img{border:2px solid #FFF;}
</style>
  </head>
  
  <body>
<div class="header-title lr10"><b>基本设置</b></div>
<div class="bk10"></div>
<div class="table-list lr10">
<!--start-->
<form method="post" action="${ctx }/admin_back/doSeoSet.action" name="myform">

  <table width="100%" cellspacing="0">	
		<tbody><tr>
			<td width="220" align="right">网站字符集：</td>
			<td><input type="text" class="input-text" value="utf-8" name="charset" disabled="disabled"/></td>
		</tr>
		<tr>
			<td width="220" align="right">Gzip压缩：</td>
			<td>
				<input type="radio" checked="checked" value="1" name="gzip" />是
				<input type="radio"  value="0" name="gzip" disabled="disabled"/>否
				<span class="lr10">启用压缩可提高用户访问速度。</span>
			</td>
		</tr>	
		<tr>
			<td width="220" align="right">网站主域名：</td>
			<td><input type="text" class="input-text wid200" value="${sysConfigure.wwwUrl }" name="sysConfigure.wwwUrl" /> 如：http://www.egouos.com</td>
		</tr>
		<tr>
			<td width="220" align="right">微信端域名：</td>
			<td><input type="text" class="input-text wid200" value="${sysConfigure.weixinUrl }" name="sysConfigure.weixinUrl" /> 如：http://weixin.egouos.com</td>
		</tr>
		<tr>
			<td width="220" align="right">域名域：</td>
			<td><input type="text" class="input-text wid200" value="${sysConfigure.domain }" name="sysConfigure.domain" />如： egouos.com (不需要加http://www)</td>
		</tr>	
		<tr>
			<td width="220" align="right">JS、CSS域名：</td>
			<td><input type="text" class="input-text wid200" value="${sysConfigure.skinUrl }" name="sysConfigure.skinUrl" />如：http://skin.egouos.com (请先添加解析，如没有解析请填写网站主域名)</td>
		</tr>
		<tr>
			<td width="220" align="right">图片域名：</td>
			<td><input type="text" class="input-text wid200" value="${sysConfigure.imgUrl }" name="sysConfigure.imgUrl" />如：http://img.egouos.com (请先添加解析，如没有解析请填写网站主域名)</td>
		</tr>		
		<tr>
			<td width="220" align="right">客服QQ：</td>
			<td><input type="text" class="input-text" value="${sysConfigure.serviceQq }" name="sysConfigure.serviceQq" /></td>
		</tr>	
		<tr>
			<td width="220" align="right">客服电话:</td>
			<td><input type="text" class="input-text" value="${sysConfigure.serviceTel }" name="sysConfigure.serviceTel" />如：400-000-8888</td>
		</tr>		
		<tr>
		
		<%-- 
			<td width="220" align="right">模板:</td>
			<td>
<div class="lanrentuku">
 <input type="radio" value= "0" id= "111" name="sysConfigure.them" style="display:none"/> <img id="0" src="http://www.egouos.com/share/th1.jpg " onclick="myFun(this.id)"/> 
 <input type="radio" value= "1" id= "333" name="sysConfigure.them" style="display:none"/> <img id="1" src="http://www.egouos.com/share/th2.jpg" onclick="myFun(this.id)"/>
</div></td>
		</tr>	 --%>
        <tr>
        	<td width="220" align="right"></td>
            <td> 
            <input type="submit" value=" 提交 " class="button" />
            <input type="button" value="返回" class="button"  onclick="history.go(-1)" /></td>
		</tr>
</tbody></table>
</form>
</div>
 </body>
</html>
