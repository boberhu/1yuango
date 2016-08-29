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
	    <a href="/admin_back/indexImgAll.action">幻灯片管理</a><span class="span_fenge lr5">|</span><a href="/admin_back/toAddIndexImg.action">添加幻灯版</a><span class="span_fenge lr5">|</span>
	</div>
  <div class="bk10"></div>
  <div class="table_form lr10" style="">
	<form action="/admin_back/doAddIndexImg.action" method="post" enctype="multipart/form-data">
 		<c:if test="${indexImg.id!=null}">
 			<input type="hidden" name="indexImg.id" value="${indexImg.id }"/>
 		</c:if>
	<table width="100%" cellspacing="0" cellpadding="0" style="">
		<tbody style="">
		<tr>
			<td align="right"><font color="red"></font>幻灯标题：</td>
			<td><input type="text" class="input-text wid400 bg" name="indexImg.title" value="${indexImg.title }"/>
            </td>
		</tr>
       <tr>
			<td align="right"><font color="red">*</font>幻灯链接：</td>
			<td><input type="text" class="input-text wid400 bg" name="indexImg.proUrl" value="${indexImg.proUrl }"/> 如：http://www.egouos.com/products/1688.html
            </td>
		</tr>
		<tr>
			<td align="right">是否启用：</td>
			<td>
			<s:if test="indexImg.id!=null">
				<c:if test="${indexImg.status==0}">
					<input type="radio" checked="checked" value="0" name="indexImg.status" />启用
					<input type="radio" value="1" name="indexImg.status" />禁用
				</c:if>
				<c:if test="${indexImg.status==1}">
					<input type="radio" value="0" name="indexImg.status" />启用
					<input type="radio" checked="checked" value="1" name="indexImg.status" />禁用
				</c:if>
			</s:if>
			<s:else >
				<input type="radio" checked="checked" value="0" name="indexImg.status" />启用
				<input type="radio" value="1" name="indexImg.status" />禁用
			</s:else>
			</td>
		</tr>
		<tr >
			<td align="right"><font color="red">*</font>图片：</td>
			<td >
           		<c:if test="${indexImg.proImg!=null}">
					<img style="border:1px solid #eee; padding:1px; width:50px; height:50px;" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${indexImg.proImg }"/>
					<input type="hidden" name="indexImg.proImg" value="${indexImg.proImg }"/>
					<input type="text" class="input-text wid400" value="${indexImg.proImg }" name="thumb" id="imagetext" />
		 		</c:if>
		 	<input name="myFile" class="form-control" value="上传图片" id="myFile" accept="image/*" type="file" />
		 	尺寸：740*333 像素
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
 	<script type="text/javascript">
 		$("#submit").click(function(){
 			if($("[name='news.title']").val()==""){
 				alert("请输入新闻标题！");
 				return false;	
 			}
 			$("form:first").submit(); 
 		});
 	</script> 
  </body>
</html>
