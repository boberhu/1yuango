<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
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
            <span class="fr">已有账号？<a id="hylinkLoginPage" class="blue" href="/login/index.html?forward=${forward }">立即登录</a></span>
        </div>

        <div class="g-contentCon clrfix">

            <div class="m-main clrfix">
                <h2 class="gray3">新用户注册</h2>
                <div class="register-form-con clrfix">
                    <ul>
                        <li>
                            <input id="txtUserName" type="text" maxlength="100" />
                            <b class="passport-icon user-name transparent-png"></b>
                            <em style="display: none;">手机号/邮箱地址</em>
                            <span class="orange" style="display: none;"></span>
                            <s class="passport-icon" style="display: none;"></s>
                        </li>
                        <li>
                            <input id="txtPwd" type="password" maxlength="20" />
                            <b class="passport-icon login-password transparent-png"></b>
                            <em style="display: none;">设置登录密码</em>
                            <span class="orange" style="display: none;"></span>
                            <s class="passport-icon" style="display: none;"></s>
                            <span id="pwdStrength" style="display: none;"></span>

                        </li>
                        <li>
                            <input id="txtConPwd" type="password" maxlength="20" />
                            <b class="passport-icon login-password transparent-png"></b>
                            <em style="display: none;">确认密码</em>
                            <span class="orange" style="display: none;"></span>
                            <s class="passport-icon" style="display: none;"></s>
                        </li>
                    </ul>
                    <ul class="j-tips-wrap" id="j-tips-wrap"></ul>
                </div>
                <div class="link-con clrfix">
                    <a id="btnAgreeBtn" href="javascript:;" class="z-agreeBtn">同意协议并注册</a>
                    <a id="btnAgreement" href="/help/agreement.html" target="_blank" class="z-agreement">《<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>服务协议》</a>
                </div>
            </div>
        </div>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/register2.js"></script>
    
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
