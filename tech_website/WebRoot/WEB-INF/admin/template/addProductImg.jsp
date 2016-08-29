<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>管理中心</title>
		<link href="/admin_css/global.css"  rel="stylesheet" type="text/css"/>
		<link href="/admin_css/admin_style.css"  rel="stylesheet" type="text/css"/>
		<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
	</head>
	<body>
  <div class="bk10"></div>
  <div class="table_form lr10" style="">
  <form action="/admin_back/addProductImg.action" method="post" enctype="multipart/form-data" style="border-radius: 0px;" class="form-horizontal group-border-dashed">
	<input type="hidden" name="id" value="${id }"/>
  <table width="100%" cellspacing="0" cellpadding="0" style="">
	<tbody style="">
	<tr>
		<td align="left" width="150px;"><font color="red">*</font>选择图片：<br/>参考像素：800*800</td>
		<td>
		 <div class="col-sm-3" style="float: none;margin: 0;padding: 5px;">
           <input type="file" name="Filedata"  class="form-control" accept="image/*" />  
         </div>
		<input type="button" value="增加" class="button" id="addButton" />
         </td>
	</tr>
	<tr>
		<td align="left" width="150px;"><font color="red">*</font>已传图片：</td>
		<td>
		<s:iterator var="productimages" value="productimageList">
		<div align="center" style="float: left;text-align: center;">
			<img src="/productImg/imagezoom/${productimages.piProductId }/${productimages.image }_mid${productimages.imageType }"/>
			<br /><a ids="${productimages.productImageId }" name="delImg" href="javascript:;">删除</a>
		</div>
		</s:iterator>
         </td>
	</tr>
</tbody>
</table>
<div class="bk10"></div>
<input type="submit" value="提交" id="submit" class="button" />
<input type="button" value="返回" class="button" onclick="history.go(-1)" /> <font color="red">商品展示图片会缓存60分钟，在添加之前请勿访问该商品页面，如已经访问过60分钟后会显示最新上传图片。</font>
</form>
</div>	
		<script type="text/javascript">
			$("#addButton").click(function(){
				$(".col-sm-3:last").after("<div class=\"col-sm-3\" style=\"float: none;margin: 0;padding: 5px;\"><input type=\"file\" name=\"Filedata\"  class=\"form-control\" accept=\"image/*\" /></div>");
			});
			$("[name='delImg']").click(function(){
 			   if(confirm("确定要删除该图片吗？"))
			   {
			   	var id = $(this).attr("ids");
	 			window.location.href = "/admin_back/doDeleteProductImg.action?id="+id;  
			   }
 			});
	 	</script>
	</body>
</html>
