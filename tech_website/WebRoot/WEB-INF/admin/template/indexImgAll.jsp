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
	    <a href="/admin_back/indexImgAll.action">幻灯片管理</a><span class="span_fenge lr5">|</span><a href="/admin_back/toAddIndexImg.action">添加幻灯版</a><span class="span_fenge lr5">|</span>
	</div>
  <div class="bk10"></div>
<div class="table-list lr10">
<!--start-->
<table width="100%" cellspacing="0">
  <thead>
	<tr>
	<th width="5%">id</th>
	<th width="50%" align="center">幻灯图片</th>
	<th width="10%" align="center">状态</th>
	<th width="10%" align="center">操作</th>
	</tr>
  </thead>
<tbody>
<s:iterator value="indexImgList" var="indexImgs">
<tr>
	<td align="center">${indexImgs.id }</td>
	<td align="center"><a target="_black" href="${indexImgs.proUrl }"><img height="50px" src="/${indexImgs.proImg }" /></a></td>
	<td align="center">
	<c:if test="${indexImgs.status==0 }">
		<font color="#0c0">启用</font>
	</c:if>
	<c:if test="${indexImgs.status==1 }">
		<font color="#f00">禁用</font>
	</c:if>
	</td>
	<td align="center">
		<a href="/admin_back/toAddIndexImg.action?id=${indexImgs.id }">修改</a>
		<a href="javascript:;" name="delImg" ids="${indexImgs.id }">删除</a>
	</td>	
</tr>
</s:iterator>
</tbody>
</table>
	<div class="btn_paixu"></div>
</div>
		<script type="text/javascript">
			$("[name='delImg']").click(function(){
 			   if(confirm("确定要删除吗？"))
			   {
			   	var id = $(this).attr("ids");
	 			window.location.href = "/admin_back/delIndexImg.action?id="+id;  
			   }
 			});
	 	</script>
  </body>
</html>
