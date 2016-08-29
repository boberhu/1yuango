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
	<link rel="stylesheet" type="text/css" href="/css/dateinput.css" />
	<script type="text/javascript" src="/ueditor/editor_config.js"></script>
	<script type="text/javascript" src="/ueditor/editor_all.js"></script> 
	<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
  </head>
  
  <body>
  <div class="header lr10">
	    <a href="/admin_back/qqGroupByList.action">QQ群管理</a><span class="span_fenge lr5">|</span><a href="/admin_back/toQqGroup.action">添加QQ群</a><span class="span_fenge lr5">|</span>
	</div>
  <div class="bk10"></div>
  <div class="table_form lr10" style="">
	<form action="/admin_back/doQqGroup.action" method="post" enctype="multipart/form-data">
 		<c:if test="${qqgroup.qqid!=null}">
 			<input type="hidden" name="qqgroup.qqid" value="${qqgroup.qqid }"/>
 		</c:if>
	<table width="100%" cellspacing="0" cellpadding="0" style="">
		<tbody style="">
		<tr>
			<td align="right"><font color="red"></font>QQ群名称：</td>
			<td><input type="text" class="input-text wid200 bg" name="qqgroup.groupName" value="${qqgroup.groupName }"/>
            </td>
		</tr>
       <tr>
			<td align="right"><font color="red">*</font>QQ群号码：</td>
			<td><input type="text" class="input-text wid200 bg" name="qqgroup.groupNo" value="${qqgroup.groupNo }" onkeyup="value=value.replace(/\D/g,'')"/> 
            </td>
		</tr>
		<tr>
			<td align="right"><font color="red">*</font>QQ群类型：</td>
			<td>
			<input type="hidden"  name="qqgroup.groupShowName" value="${qqgroup.groupShowName }" id="qqShowName"/> 
			<select class="form-control" id="sel_qqtype" style="width:150px;">
				<option value="0" selected="selected">直属群</option>
				<option value="1">地方群</option>
				</select>
            </td>
		</tr>
		<tr style="display: none;" name="trShow">
			<td align="right">所属省份：</td>
			<td>
				<select name="qqgroup.groupProvince" class="form-control" id="sel_areaA" style="width:150px;">
				</select>
			</td>
		</tr>
		<tr style="display: none;" name="trShow">
			<td align="right">所属市：</td>
			<td>
				<select name="qqgroup.groupCity" class="form-control" id="sel_areaB" style="width:150px;">
				</select>
			</td>
		</tr>
		<tr style="display: none;" name="trShow">
			<td align="right">所属区：</td>
			<td>
				<select name="qqgroup.groupDistrict" class="form-control" id="sel_areaC" style="width:150px;">
				</select>
			</td>
		</tr>
        <tr height="60px">
			<td align="right"></td>
			<td>
			<input type="submit" value="提交" id="submit" class="button" />
			<input type="button" value="返回" class="button" onclick="history.go(-1)" />
			</td>
		</tr>
	</tbody></table>
	</form>
</div>
 	<script type="text/javascript" src="/js/adminqqgroup.js"></script> 
  </body>
</html>
