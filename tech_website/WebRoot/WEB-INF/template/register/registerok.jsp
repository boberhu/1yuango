<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="template_footer" />
    <title>会员注册_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
	<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/login.css" />
  </head>
  
  <body>
     <div class="wrapper">
     	<input name="hidRegisterForward" type="hidden" id="hidRegisterForward" value="${forward }" />
        <div class="g-logo-top">
            <a href="/" class="transparent-png fl">
                <img src="<%=ApplicationListenerImpl.sysConfigureJson.getSaitLogo()%>" /></a>
            <span class="fr"><a href="/" class="gray9">返回首页</a></span>
        </div>

        <div class="g-contentCon clrfix">

            <div class="sign-success clrfix">

                <i class="passport-icon"></i>

                <span>恭喜您，注册成功！</span>

                <p>您现在可以通过<b class="blue">${userName }</b>进行登录和收取获得商品的信息</p>

                <a href="/" class="orange" id="a_go"><em>立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></em>（0秒）</a>
            </div>
        </div>
        <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/registerok.js"></script>
  </body>
</html>
