<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>管理中心</title>
	<link href="/admin_css/global.css"  rel="stylesheet" type="text/css"/>
	<link href="/admin_css/admin_style.css"  rel="stylesheet" type="text/css"/>
	<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
  </head>
  
  <body>
  <div class="header lr10">
	<a href="/admin_back/productList.action">商品列表</a><span class="span_fenge lr5">|</span><a href="/admin_back/toAddProduct.action">添加商品</a><span class="span_fenge lr5">|</span><a href="/admin_back/productTypeList.action">商品分类</a><span class="span_fenge lr5">|</span><a href="/admin_back/index.action?id=hot20">在售商品管理</a><span class="span_fenge lr5">|</span></div>
	<div class="bk10"></div>
  <input type="hidden" value="${resultCount }" id="resultCount"/>
  <input type="hidden" id="backUrl" name="backUrl" value=""/>
  
  <div class="header-data lr10">
<select name="products.productType" id="typeId" class="form-control" style="width:180px;">
<option value="">请选择</option>
	<s:iterator value="productTypeList" var="productTypes">
		<c:choose>
			<c:when test="${products.productType==productTypes.typeId }">
				<option selected="selected" value="${productTypes.typeId }">${productTypes.typeName }</option>
			</c:when>
			<c:otherwise>
				<option value="${productTypes.typeId }">${productTypes.typeName }</option>
			</c:otherwise>
		</c:choose>
	</s:iterator>
</select>
商品名称：<input type="text" value="" name="product.productName" id="keyword" class="input-text wid200"/>
<input type="submit" value="搜索" id="search"  class="button" />
机器人次：<input type="text" value="${robots}" id="robots" maxlength="10" size="12"/>
<input type="submit" value="启动" id="startup"  class="button" />
<input type="submit" value="关闭" id="shutdown" class="button" />
</div>
<div class="bk10"></div>

<div class="table-list lr10">
    <table width="100%" cellspacing="0">
     	<thead>
        		<tr>
                    <th width="5%">商品ID</th>        
                    <th width="25%">商品名称</th>    
                    <th width="5%">商品价格</th>             
                    <th width="5%">商品图片</th>
                    <th width="5%">商品类型</th>
                    <th width="5%">所属品牌</th>
                    <th width="20%">商品操作</th>
				</tr>
        </thead>
        <tbody>				
<s:iterator value="productList" var="products">
	 		<tr>
	 			<td align="center">${products.productId } </td>
	 			<td align="left"><a href="/product/${products.productId }.html" target="_blank">${products.productName }</a> </td>
	 			<td align="center">${products.productPrice } </td>
	 			<td align="center"><img width="50px;" height="50px;" src="${products.headImage } "/></td>
	 			<td align="center">
	 				<s:iterator value="productTypeList" var="productTypes">
	 					<c:if test="${products.productType ==productTypes.typeId}">
	 					${productTypes.typeName }
	 					</c:if>
	 				</s:iterator>
	 			</td>
	 			<td align="center">
	 				<s:iterator value="productBrandList" var="productTypes">
	 					<c:if test="${products.productBrand ==productTypes.typeId}">
	 					${productTypes.typeName }
	 					</c:if>
	 				</s:iterator>
	 			</td>
	 			<td align="center">
	 				<a name="toUpdate" ids="${products.productId }" href="javascript:;">[编辑]</a> 
	 				<a name="toDelete" ids="${products.productId }" href="javascript:;">[删除]</a> 
	 				<a href="/admin_back/toAddProductImg.action?id=${products.productId }">[添加展示图片]</a>
	 			<c:if test="${products.status==0}">
	 				<a name="upSpellbuyproduct" id="${products.productId }" href="javascript:;">[上架]</a>
	 			</c:if> 
	 			<c:if test="${products.status==1}">
	 				<a name="downSpellbuyproduct" id="${products.productId }" href="javascript:;">[下架]</a>
	 			</c:if> 
	 			<c:if test="${products.status==2}">
	 				<a name="upSpellbuyproduct" id="${products.productId }" href="javascript:;">[已下架]</a>
	 			</c:if> 
	 			<c:if test="${products.attribute71=='hot'}">
	 				<a name="downHotProduct" pid="${products.productId }" href="javascript:;" style="color: red;">[取消机器人购买]</a>
	 			</c:if> 
	 			<c:if test="${products.attribute71!='hot'}">
	 				<a name="hotProduct" pid="${products.productId }" href="javascript:;">[设置机器人购买]</a>
	 			</c:if>
	 			</td>
	 		</tr>
		</s:iterator>
         </tbody>
     </table>     
</div>
		${pageString }
 	<script language="javascript" type="text/javascript">
 		// pzp 2015-09
 		$(function($){
 			var robots = $("#robots");
 			$("#startup").click(robotctrl);
 			$("#shutdown").click(robotctrl);
 			
 			function robotctrl(){
 				var id = this.id, url = null, val;
 				val = parseInt(robots.val());
 				switch(id){
 				case "startup":
 					if(isNaN(val)){
 						alert("机器人次请输入自然数");
 						return;
 					}
 					url = "robotStartup.action?robots="+val;
 					break;
 				case "shutdown":
 					url = "robotShutdown.action";
 					break;
 				}
 				if(url != null){
 					$.ajax({
 						url: url,
 						type: "POST",
 						dataType: "text",
 						cache: false,
 						success: function(res){
 							if("success" === res){
 								alert("startup"===id?"启动成功":"关闭成功");
 							}else if("test" === res){
 								alert("权限不足，请联系管理员！");
 							}
 						}
 					});
 				}
 				return false;
 			}
 		});
 		
 		$("#search").click(function(){
 			location.replace("/admin_back/productList.action?typeId="+$("#typeId").val()+"&keyword="+$("#keyword").val());
 		});
 		$("[name='toUpdate']").click(function(){
 			var id = $(this).attr("ids");
 			var burl = document.URL;
 			window.location.href = "/admin_back/toUpdate.action?id="+id+"&backUrl="+burl;  
 		});
 		$("[name='toDelete']").click(function(){
 			   if(confirm("确定要删除该商品吗？"))
			   {
			   	var id = $(this).attr("ids");
	 			var burl = document.URL;
	 			window.location.href = "/admin_back/deleteProduct.action?id="+id+"&backUrl="+burl;  
			   }
 			
 		});
 		$("[name='upSpellbuyproduct']").click(function(){
 			var id = $(this).attr("id");
 			$.ajax({
 				url:"/admin_back/upSpellbuyproduct.action",
 				type:"post",
 				data:"id="+id,
 				beforeSend:function(){
					$("#"+id).html('操作中');
				},
 				success:function(data){
 					if(data=="success"){
	 					$("#"+id).html("[已上架]").attr("name","");
 					}else if(data=="test"){
 						alert("权限不足，请联系管理员！");
 					}
 				}
 			});
 		});
 		$("[name='downSpellbuyproduct']").click(function(){
 			var id = $(this).attr("id");
 			$.ajax({
 				url:"/admin_back/downSpellbuyproduct.action",
 				type:"post",
 				data:"id="+id,
 				beforeSend:function(){
					$("#"+id).html('操作中');
				},
 				success:function(data){
 					if(data=="success"){
	 					$("#"+id).html("[已下架]").attr("name","");
 					}else if(data=="test"){
 						alert("权限不足，请联系管理员！");
 					}
 				}
 			});
 		});
 		$("[name='hotProduct']").live("click",function(){
 			var id = $(this).attr("pid");
 			$.ajax({
 				url:"/admin_back/hotProduct.action",
 				type:"post",
 				data:"id="+id,
 				beforeSend:function(){
					$("[pid='"+id+"']").html('操作中');
				},
 				success:function(data){
 					if(data=="success"){
	 					$("[pid='"+id+"']").html("[取消机器人购买]").attr("name","downHotProduct").css({"color":"#FF0000"});
 					}else if(data=="test"){
 						alert("权限不足，请联系管理员！");
 					}
 				}
 			});
 		});
 		$("[name='downHotProduct']").live("click",function(){
 			var id = $(this).attr("pid");
 			$.ajax({
 				url:"/admin_back/downHotProduct.action",
 				type:"post",
 				data:"id="+id,
 				beforeSend:function(){
					$("[pid='"+id+"']").html('操作中');
				},
 				success:function(data){
 					if(data=="success"){
	 					$("[pid='"+id+"']").html("[设置机器人购买]").attr("name","hotProduct").css({"color":"#3380FF"});
 					}else if(data=="test"){
 						alert("权限不足，请联系管理员！");
 					}
 				}
 			});
 		});
 	</script>
  </body>
</html>
