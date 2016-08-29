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
  <input type="hidden" value="${resultCount }" id="resultCount"/>
  <input type="hidden" id="backUrl" name="backUrl" value=""/>
  <div class="bk10"></div>
<div class="table-list lr10">
	 <table width="100%" cellspacing="0">
     	<thead>
        		<tr>
					<th>ID</th>  
                    <th>主题</th>
                    <th>昵称</th>
                    <th>电话</th>
                    <th>E-mail</th>
                    <th>反馈信息</th>
                    <th>详情</th>
				</tr>
        </thead>
        <tbody>				
        <s:iterator value="suggestionList" var="suggestions" status="st">
        <tr>
         	<td align="center" width="5%">${suggestions.id }</td>
			<td align="center" width="5%">${suggestions.subject } </td>
	         <td align="center" width="10%">${suggestions.userName }</td>
	         <td align="center" width="10%">${suggestions.mobilePhone }</td>
	         <td align="center" width="10%">${suggestions.mail }</td>
	         <td align="center" class="open" style="overflow: hidden;float: left;height: 30px;text-align: left;">${suggestions.postText }</td>
	         <td align="center" width="5%"><a href="javascript:;" name="open">展开</a>&nbsp;&nbsp;&nbsp;<a href="javascript:;" ids="${suggestions.id }" name="delete">删除</a></td>
          </tr>
          </s:iterator>
           </tbody>
     </table>
</div>
		${pageString }
		
		<script type="text/javascript">
			$("[name=open]").toggle(
			  function () {
			   $(this).parent().parent().find(".open").css("float","none");
			   $(this).text("收起")
			  },
			  function () {
			    $(this).parent().parent().find(".open").css("float","left");
		     	$(this).text("展开")
			  }
			); 
			
			$("[name=delete]").click(function(){
				 if(confirm("确定要删除吗？"))
				   {
				   	var id = $(this).attr("ids");
		 			var burl = document.URL;
		 			window.location.href = "/admin_back/delSuggestion.action?id="+id+"&backUrl="+burl;  
				   }
			});
		</script>
  </body>
</html>
