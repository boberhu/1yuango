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
  <div class="bk10"></div>
  <div id="con-tab" class="table-list con-tab lr10">
	  <div class="header" align="center">					
	     <h3>分类操作</h3>
	   </div>
   <div class="bk10"></div>
<div class="con-tabv null" name="con-tabv" style="display: block;">
<form action="/admin_back/addProductType.action" method="post" class="form-horizontal">
<input type="hidden" name="producttype.typeId" value="${producttype.typeId }"/>
 <table width="100%" class="table_form">
 <tbody>
 <tr>
	<td align="right" style="width:120px"><font color="red">*</font>分类名称：</td>
	<td>
    <input type="text" name="producttype.typeName" value="${producttype.typeName }" class="input-text wid200 bg" />
    </td>
</tr>

<tr>
	<td align="right" style="width:120px"><font color="red">*</font>父分类：</td>
	<td>
	<s:if test="tId!=null">
		<select name="producttype.ftypeId" class="form-control">
			<option value="">请选择</option>
			<s:iterator value="productTypeList" var="productTypes">
				<c:choose>
					<c:when test="${tId==productTypes.typeId}">
						<option selected="selected" value="${productTypes.typeId }">${productTypes.typeName }</option>
					</c:when>
					<c:otherwise>
						<option value="${productTypes.typeId }">${productTypes.typeName }</option>
					</c:otherwise>
				</c:choose>
			</s:iterator>
		</select>
	</s:if>
	<s:else>
	   <select name="producttype.ftypeId" class="form-control">
			<option value="">请选择</option>
			<s:iterator value="productTypeList" var="productTypes">
				<c:choose>
					<c:when test="${producttype.ftypeId==productTypes.typeId}">
						<option selected="selected" value="${productTypes.typeId }">${productTypes.typeName }</option>
					</c:when>
					<c:otherwise>
						<option value="${productTypes.typeId }">${productTypes.typeName }</option>
					</c:otherwise>
				</c:choose>
			</s:iterator>
		</select>
	</s:else>
    </td>
</tr>

<!--<tr>-->
<!--	<td align="right" style="width:120px">子分类ID：</td>-->
<!--	<td>-->
<!--    <input type="text" name="producttype.stypeId" value="${producttype.stypeId }" onkeyup="value=value.replace(/[^\w]/ig,'')" class="input-text wid200 bg" />-->
<!--    </td>-->
<!--</tr>-->
</tbody>
</table>
<div class="bk10"></div>
<input type="submit" value="提交" id="submit" class="button" />
<input type="button" value="返回" class="button" onclick="history.go(-1)" />
</form>
</div>
</div>
	<script type="text/javascript">
 		$("#submit").click(function(){
 			if($("[name='producttype.typeName']").val()==""){
 				alert("请输入分类名称！");
 				return false;	
 			}
 			$("form:first").submit(); 
 		});
 	</script> 
  </body>
</html>
