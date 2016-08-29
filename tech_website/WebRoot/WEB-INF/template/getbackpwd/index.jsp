<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="template_footer" />
    <title>忘记密码_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></title>
	<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/login.css" />
  </head>
  
  <body>
<div class="wrapper">

        <div class="g-logo-top">
            <a href="/" class="transparent-png fl">
                <img src="<%=ApplicationListenerImpl.sysConfigureJson.getSaitLogo()%>"></a>
            <span class="fr">想起来了？<a id="hylinkLoginPage" class="blue" href="/login/index.html?forward=${forward }">立即登录</a></span>
        </div>

        <div class="g-contentCon clrfix">

            <div class="m-main clrfix">
                <h2 class="gray3">找回密码</h2>
                <div class="register-form-con clrfix">
                    <ul style="_height:72px;">
                        <li>
                            <input id="txtUserName" type="text" maxlength="100" />
                            <b class="passport-icon user-name transparent-png"></b>
                            <em style="display:none;">请输入您的手机号/邮箱</em>
                            <span class="orange" style="display: none;"></span>
                            <s class="passport-icon" style="display: none;"></s>
                        </li>
                        <li>
                    	    <div class="z-code clrfix">
                        	    <input id="txtCode" type="text" maxlength="6" />
                                <b class="passport-icon verification-code transparent-png"></b>
                                <em style="display:none;">验证码</em>
                                <img src="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/getbackpwd/getRandomCode.html?rnd=<%=new Date().getTime() %>" alt="" id="imgCode" />
                                <a href="javascript:;" id="btnChangeCode">换一张</a>
                            </div>
                            <span class="orange"></span>
                        </li>
                    </ul>
                    <ul class="j-tips-wrap" id="j-tips-wrap"></ul>
                </div>
                <div class="link-con clrfix">
                    <a id="btnNext" href="javascript:;" class="z-agreeBtn">下一步</a>
                </div>
            </div>
        </div>

        <!--版权-->
     <link type="text/css" rel="stylesheet" href="/css/pagedialog.css?v=141125" />
    <script language="javascript" type="text/javascript" src="/js/pagedialog.js?v=141125"></script>
    <div class="pageDialogBG" id="pageDialogBG"></div>
    <div class="pageDialogBorder" id="pageDialogBorder"></div>
    <div class="pageDialog" id="pageDialog">
    	<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
    	<div class="pageDialogMain" id="pageDialogMain"> </div>
    </div>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/getbackpwd.js"></script>
  </body>
</html>
