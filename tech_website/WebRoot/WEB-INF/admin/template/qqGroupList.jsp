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
                    <th>QQ群名称</th>
                    <th>QQ群号码</th>
                    <th>QQ群类型</th>
                    <th>所属省/市/区</th>
                    <th>操作</th>
				</tr>
        </thead>
        <tbody>				
        <s:iterator value="qqgroupList" var="qqgroups">
        <tr>
			<td align="center" width="10%">${qqgroups.groupName } </td>
	         <td align="center" width="10%">${qqgroups.groupNo }</td>
	         <td align="center" width="10%">
	         <c:choose>
		         <c:when test="${qqgroups.groupProvince==null}">
		         	直属群
		         </c:when>
		         <c:otherwise>
		         	地方群
		         </c:otherwise>
	         </c:choose>
	         </td>
	         <td align="center" width="15%">
	         	<c:choose>
		         <c:when test="${qqgroups.groupProvince==null}">
		         	
		         </c:when>
		         <c:otherwise>
		         	${qqgroups.groupShowName } 
		         </c:otherwise>
	         </c:choose>
	         </td>
	         <td align="center" width="5%"><a href="javascript:;" qid="${qqgroups.qqid }" name="open">删除</a></td>
          </tr>
          </s:iterator>
           </tbody>
     </table>
</div>
		<script type="text/javascript">
			$("[name=open]").click(function(){
				if (confirm("您确定删除吗?")) {
					location.replace("/admin_back/delQqGroup.action?id="+$(this).attr("qid"));
				}
			});
		</script> 
  </body>
</html>
