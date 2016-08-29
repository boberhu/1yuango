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
        <input name="hidAccount" type="hidden" id="hidAccount" value="${userName }" />
        <input name="hidType" type="hidden" id="hidType" value="${date }" />
        <input name="checkSN" type="hidden" id="checkSN" value="${isVerify }" />
        <input name="hidRegisterForward" type="hidden" id="hidRegisterForward" value="${forward }" />
        <div class="g-logo-top">
            <a href="/" class="transparent-png fl">
                <img src="<%=ApplicationListenerImpl.sysConfigureJson.getSaitLogo()%>" /></a>
            <span class="fr">已有账号？<a id="hylinkLoginPage" class="blue" href="/login/index.html?forward=${forward }">立即登录</a></span>
        </div>

        <div class="g-contentCon clrfix">

            <div class="m-main clrfix">
                <h2 class="gray3">验证信息</h2>
                <div class="security-code-con clrfix">
                    <dl>
                        <dt>验证码已发送至<b class="blue">${key }</b><a id="hylinkRegisterPageA" href="/register/index.html?forward=${forward }">更换</a></dt>
                        <dd class="code-form">
                            <input id="txtCode" type="text" maxlength="6" value=""/><a id="btnSend" href="javascript:;">重新发送</a>
                             <em style="display:none;">请输入6位验证码</em>
                            <span class="orange" style="display:none;"></span>
                        </dd>
                        <dd><a id="btnSubmit" href="javascript:;" class="z-agreeBtn">提交</a></dd>
                    </dl>
                </div>
            </div>
        </div>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/registercheck.js"></script>
    
    <link type="text/css" rel="stylesheet" href="/css/pagedialog.css?v=141125" />
    <script language="javascript" type="text/javascript" src="/js/pagedialog.js?v=141125"></script>
    <div class="pageDialogBG" id="pageDialogBG"></div>
    <div class="pageDialogBorder" id="pageDialogBorder"></div>
    <div class="pageDialog" id="pageDialog">
    	<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
    	<div class="pageDialogMain" id="pageDialogMain"> </div>
    </div>
    </div>
  </body>
</html>
