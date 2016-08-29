<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="header-title lr10">
	<b>公益基金</b>
</div>
<div class="bk10"></div>
<div class="table_form lr10">	
<form id="myform" method="post" action="/admin_back/doSeoSet.action">

<table width="100%" class="lr10">
  <tbody>
  <tr>
    <td>公益基金率</td>
    <td><input type="text" value="0.01%" name="sysConfigure.qqAppId" class="input-text wid150" disabled="disabled" /></td>
  </tr>  
  <tr>
    <td>公益基金</td>
    <td><input type="text" value="${sysConfigure.gyjjNumber }" name="sysConfigure.gyjjNumber" class="input-text wid250" />公益基金默认为全站总购买数的百分之一。
	</td>
  </tr> 
	<tr>
    	<td width="100"></td> 
   		<td> <input type="submit" class="button" name="dosubmit" value=" 提交 " /></td>
    </tr>
</tbody></table>
</form>

</div><!--table-form end-->

<script>	
</script>

 </body>
</html>
