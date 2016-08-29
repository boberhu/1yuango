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
  
  <div class="table-list lr10">
 <table width="100%" cellspacing="0">
    <thead>
            <tr>
            <th width="100px;">分类ID</th>
            <th align="center">分类名称</th>
            <th width="100px;" align="center">父分类</th>
			<th align="center">管理操作</th>
            </tr>
    </thead>
    <tbody>
    <s:iterator value="productTypeList" var="productType" status="st">
		<tr align="center">
		<td align="center">${productType.typeId }</td>
		<td align="center">${productType.typeName }</td>
		<td align="center">
		<s:iterator value="productTypeList" var="productTypes">
			<c:if test="${productType.ftypeId==productTypes.typeId}">
				${productTypes.typeName }
			</c:if>
		</s:iterator>
		</td>
		<td class="center ">
		<a href="/admin_back/toAddProductType.action?id=${productType.typeId  }"><input type="submit" class="button" value="修改" /></a>
		<a name="toDelete" ids="${productType.typeId }" href="javascript:;"><input type="button" class="button" value="删除" /></a>
		<a href="/admin_back/toAddProductType.action?tId=${productType.typeId  }"><input type="submit" class="button" value="添加子分类" /></a>
		</td>
	</tr>
	</s:iterator>
    </tbody>
  </table>
</div>

<script language="javascript" type="text/javascript">
$("[name='toDelete']").click(function(){
	   if(confirm("确定要删除该分类吗？"))
	   {
	   	var id = $(this).attr("ids");
		var burl = document.URL;
		window.location.href = "/admin_back/deleteProductType.action?id="+id+"&backUrl="+burl;  
	   }
	
});
</script>
  </body>
</html>
