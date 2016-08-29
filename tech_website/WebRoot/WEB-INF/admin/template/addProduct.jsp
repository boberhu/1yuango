<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName() %>_管理中心</title>
	<script type="text/javascript" src="/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="/ueditor/ueditor.all.js"></script> 
	<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
	<link href="/admin_css/global.css"  rel="stylesheet" type="text/css"/>
	<link href="/admin_css/admin_style.css"  rel="stylesheet" type="text/css"/>
<style type="text/css">
#foot{padding-bottom: 10px;position:fixed;_position:absolute;bottom:0px;_bottom:0px;_margin-top:expression(this.style.pixelHeight+document.documentElement.scrollTop)}
</style>
  </head>
  
  <body>
  <div class="header lr10"><a href="/admin_back/productList.action">商品管理</a><span class="span_fenge lr5">|</span><a href="/admin_back/toAddProduct.action">添加商品</a><span class="span_fenge lr5">|</span></div>
  <div class="bk10"></div>
  
  <div class="table_form lr10" style="">
 <c:choose>
	<c:when test="${product.productId!=null}">
		<form action="/admin_back/update.action" method="post" enctype="multipart/form-data" style="border-radius: 0px;" class="form-horizontal group-border-dashed">
		<input type="hidden" name="product.productId" value="${product.productId }"/>
		<input type="hidden" name="backUrl" value="${backUrl }"/>
		<input type="hidden" name="product.productRealPrice" value="${product.productRealPrice }"/>
	<input type="hidden" name="product.status" value="${product.status }"/>
	<input type="hidden" name="product.attribute71" value="${product.attribute71 }"/>
	</c:when>
	<c:otherwise>
		<form action="/admin_back/addProduct.action" method="post" enctype="multipart/form-data" style="border-radius: 0px;" class="form-horizontal group-border-dashed">
		<input type="hidden" name="product.productId" value=""/>
		<input type="hidden" name="backUrl" value="${backUrl }"/>
		<input type="hidden" name="product.productRealPrice" value="0"/>
		<input type="hidden" name="product.status" value="0"/>
	<input type="hidden" name="product.attribute71" value="1"/>
	</c:otherwise>
</c:choose>
	<table width="100%" cellspacing="0" cellpadding="0" style="">
		<tbody style="">
		 <tr>
			<td align="right" style="width:150px;min-width:130px;"><font color="red">*</font>商品名称：</td>
			<td>
            <input type="text" class="input-text wid400 bg" name="product.productName" value="${product.productName }" />
            </td>
		</tr>
		 <tr>
			<td align="right"><font color="red">*</font>商品标题：</td>
			<td>
            <input type="text" class="input-text wid400 bg" name="product.productTitle" value="${product.productTitle }" />
            </td>
		</tr>
		<tr>
			<td align="right"><font color="red">*</font>商品总价格：</td>
			<td><input type="text" class="input-text" style="width:65px; padding-left:0px; text-align:center" onkeyup="value=value.replace(/\D/g,'')" name="product.productPrice" value="${product.productPrice }" id="money" />元
<!--			<b>提示：</b>-->
<!--<font color="red">商品总价格请不要填写100，2300,5000这样的整数,整数价格计算出的夺宝码可能就为10000001</font>-->
			</td>
		</tr>
		<tr>
			<td align="right"><font color="red">*</font>商品类型：</td>
			<td>
             <select name="product.style" class="form-control">
				<option value="">请选择</option>
				<s:if test="product.style=='goods_xp'">
					<option selected="selected" value="goods_xp">新品</option>
					<option value="goods_tj">推荐</option>
					<option value="goods_rq">人气</option>
					<option value="goods_xs">限时</option>
				</s:if>
				<s:elseif test="product.style=='goods_tj'">
					<option selected="selected" value="goods_tj">推荐</option>
					<option value="goods_xp">新品</option>
					<option value="goods_rq">人气</option>
					<option value="goods_xs">限时</option>
				</s:elseif>
				<s:elseif test="product.style=='goods_rq'">
					<option selected="selected" value="goods_rq">人气</option>
					<option value="goods_tj">推荐</option>
					<option value="goods_xp">新品</option>
					<option value="goods_xs">限时</option>
				</s:elseif>
				<s:elseif test="product.style=='goods_xs'">
					<option selected="selected" value="goods_xs">限时</option>
					<option value="goods_rq">人气</option>
					<option value="goods_tj">推荐</option>
					<option value="goods_xp">新品</option>
				</s:elseif>
				<s:else>
					<option value="goods_xp">新品</option>
					<option value="goods_tj">推荐</option>
					<option value="goods_rq">人气</option>
					<option value="goods_xs">限时</option>
				</s:else>
			</select>
            </td>
		</tr>
        <tr>
			<td align="right"><font color="red">*</font>所属分类：</td>
			<td>
            	 <select name="product.productType" class="form-control">
					<option value="">请选择</option>
							<s:iterator value="productTypeList" var="productTypes">
								<c:choose>
									<c:when test="${product.productType==productTypes.typeId }">
										<option selected="selected" value="${productTypes.typeId }">${productTypes.typeName }</option>
									</c:when>
									<c:otherwise>
										<option value="${productTypes.typeId }">${productTypes.typeName }</option>
									</c:otherwise>
								</c:choose>
							</s:iterator>
					</select>
			</td>
		</tr>  
        <tr>
			<td align="right"><font color="red">*</font>所属品牌：</td>
			<td>
            	 <select name="product.productBrand" class="form-control">
					<option value="">请选择</option>
							<s:iterator value="productBrandList" var="productTypes">
								<c:choose>
									<c:when test="${product.productBrand==productTypes.typeId }">
										<option selected="selected" value="${productTypes.typeId }">${productTypes.typeName }</option>
									</c:when>
									<c:otherwise>
										<option value="${productTypes.typeId }">${productTypes.typeName }</option>
									</c:otherwise>
								</c:choose>
							</s:iterator>
					</select>
			</td>
		</tr>
		<tr>
			<td align="right"><font color="red">*</font>约定中奖者ID：</td>
			<td><input type="text" value="${product.winner }" name="product.winner" class="input-text" style="width:80px;padding-left:0px;text-align:center" onkeyup="value=value.replace(/\D/g,'')" name="yunjiage" /> <font color="red">(为空或0表示不指定)</font></td>
		</tr> 
		<tr>
			<td align="right"><font color="red">*</font>单次价格：</td>
			<td><input type="text" value="${product.singlePrice }" name="product.singlePrice" class="input-text" style="width:65px;padding-left:0px;text-align:center" onkeyup="value=value.replace(/\D/g,'')" name="yunjiage" />元 <font color="red">(默认请填写1 表示1元起拍)</font></td>
		</tr>
		<tr>
			<td align="right"><font color="red">*</font>限购人次：</td>
			<td><input type="text" value="${product.productLimit }" name="product.productLimit" class="input-text" style="width:65px;padding-left:0px;text-align:center" onkeyup="value=value.replace(/\D/g,'')" name="yunjiage" />元 <font color="red">(默认请填写0  表示不限购，否则填写限购人次。)</font></td>
		</tr>
        
        <tr>
         <td align="right"><font color="red">*</font>缩略图：</td>
        <td>
        	<c:if test="${product.headImage!=null}">
					<img style="border:1px solid #eee; padding:1px; width:50px; height:50px;" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/${product.headImage }"/>
					<input type="hidden" name="product.headImage" value="${product.headImage }"/>
					<input type="text" class="input-text wid300" value="${product.headImage }" name="thumb" id="imagetext" />
		 	</c:if>
		 	<input name="myFile" class="form-control" value="上传图片" id="myFile" accept="image/*" type="file" />
		 	尺寸：200*200 像素
        </td>
      </tr>
		<tr >
        	<td height="300" align="right"><font color="red">*</font>商品内容详情：</td>
			<td >
			 <textarea id="editor" name="product.productDetail" style="width:1024px;height:500px;">${product.productDetail }</textarea>
					<script type="text/javascript">
						UE.getEditor('editor');
					</script>
            </td>        
		</tr> 
	</tbody></table>

<div id="foot">
<input type="submit" value="提交" class="button" id="submit" />
<input type="button" value="返回" class="button"  onclick="history.go(-1)" />
</div>
</form>
</div>
  
 
 	<script type="text/javascript">
 		$("#submit").click(function(){
 			if($("[name='product.productName']").val()==""){
 				alert("请输入商品名称！");
 				return false;	
 			}
 			if($("[name='product.productPrice']").val()==""){
 				alert("请输入商品价格！");
 				return false;
 			}
 			if($("[name='product.productTitle']").val()==""){
 				alert("请输入商品标题！");
 				return false;
 			}
 			if($("[name='product.productType']").val()==""){
 				alert("请选择所属分类！");
 				return false;
 			}
 			if($("[name='product.singlePrice']").val()==""){
 				alert("请输入单次价格！");
 				return false;
 			}
 			if($("[name='product.productLimit']").val()==""){
 				alert("请输入限购人次！");
 				return false;
 			}
 			
 			if($("[name='product.productId']").val()==""){
	 			if($("[name='myFile']").val()==""){
	 				alert("请给该商品添一张主图片！");
	 				return false;
	 			}
 			}
 			$("form:first").submit(); 
 		});
 		$("[name=product.productType]").change(function(){
 			var id = $(this).val();
 			$.ajax({
 				url:"/admin_back/productBrandListByTypeId.action",
 				data:"typeId="+id,
 				success:function(d){
 					var b = $("[name=product.productBrand]");
 					b.empty();
 					var h = "<option value=\"\">请选择</option>";
 					for(var i=0;i<d.length;i++){
 						h +="<option value=\""+d[i].typeId+"\">"+d[i].typeName+"</option>";
 					}
 					b.append(h);
 				},
 				error:function(){
 					alert("获取品牌出错，请稍等再试！");
 				}
 			});
 		});
 	</script> 
 	
  </body>
</html>
