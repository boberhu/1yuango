<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
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
<div class="header-title lr10"><b>SEO设置</b></div>
<div class="bk10"></div>
<div class="table-list lr10">
<!--start-->
<form method="post" action="/admin_back/doSeoSet.action" name="myform" enctype="multipart/form-data">

  <table width="100%" cellspacing="0">
  	<tbody>
    <tr>
		<td width="220" align="right">网站logo：</td>
		<td>
           	<input type="text" class="input-text wid200" name="sysConfigure.saitLogo" value="${sysConfigure.saitLogo }" />
           	<input name="myFile" class="button" value="上传图片" id="myFile" accept="image/*" type="file" /> 图片大小：122*74
        </td>
	</tr>
  	 <tr>
			<td width="220" align="right">网站名称：</td>
			<td><input type="text" class="input-text wid200" name="sysConfigure.saitName" value="${sysConfigure.saitName }" /> 如：1元惊喜</td>
	</tr>
	<tr>
			<td width="220" align="right">网站简称：</td>
			<td><input type="text" class="input-text wid200" name="sysConfigure.shortName" value="${sysConfigure.shortName }" /> 如：惊喜</td>
	</tr>
	<tr>
			<td width="220" align="right">网站标题：</td>
			<td><input type="text" class="input-text wid200" name="sysConfigure.saitTitle" value="${sysConfigure.saitTitle }" /> 如：1元惊喜 - 惊喜无限</td>
	</tr>
     <tr>
			<td width="220" align="right">网站关键字：</td>
			<td>
			<input type="text" class="input-text wid300" name="sysConfigure.keyword" value="${sysConfigure.keyword }" />如：1元惊喜,一元惊喜   （多个关键词请用,号分割）</td>
	</tr>
     <tr>
			<td width="220" align="right">网站描述：</td>
			<td><textarea style="height:80px" class="wid300" name="sysConfigure.description">${sysConfigure.description }</textarea>
            </td>
	</tr>	
     <tr>
			<td width="220" align="right">版权信息/统计代码：</td>
			<td><textarea style="height:80px" class="wid300" name="sysConfigure.icp">${sysConfigure.icp }</textarea>
            </td>
	</tr>	
    <tr>
        	<td width="220" align="right"></td>
            <td>
            <input type="submit" value=" 提交 " class="button" />
            <input type="button" value="返回" class="button"  onclick="history.go(-1)" />
            </td>
   </tr>
</tbody></table>
</form>
</div>
 </body>
</html>
