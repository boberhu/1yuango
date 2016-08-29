<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>忘记密码_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></title>
	<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/register.css" />
  </head>
  
  <body>
  	<div class="login_layout">    
	    <div class="login_title">
		    <h2>密码忘了？不要着急，通过以下方式即可帮您顺利的找回来！</h2>
	    </div>
	    <div class="login_Content">
            <div class="login_CMobile_Complete">
			    <p><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>已向您的手机 <span class="orange">${mail}</span> 免费发送了一条验证短信，请查看您的手机短信！</p>
				<dl>
					<dt>请输入手机短信收到的验证码：</dt>
					<dd><input type="text" value="" class="login_CMobile_Code" id="mobileCode" /></dd>
					<dd></dd>
				</dl>
			    <a class="login_Email_but" href="javascript:;" id="btnSubmitVerify">提交验证</a>
			    <input type="hidden" value="${mail}" id="hidRegMobile" name="hidRegMobile" /><input type="hidden" value="${rnd }" id="hidRegSN" name="hidRegSN" /><input type="hidden" value="${key }" id="checkSN" name="checkSN" />
		    </div>
		    <div class="login_Explain">
			    <h2>没收到验证短信？</h2>
			    <p>1.请查看手机的垃圾短信，信息有可能被误认为是垃圾信息。</p>
			    <p>2.如果在2分钟后仍未收到验证短信，请点击 <a class="login_SendoutbutClick" href="javascript:;" id="retrySend">重新发送</a> </p>
		    </div>
            
	    </div>
    </div>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/findmobilecheck.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css" />
		<script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js"></script>
	    <div class="pageDialogBG" id="pageDialogBG"></div>
	    <div class="pageDialogBorder" id="pageDialogBorder"></div>
	    <div class="pageDialog" id="pageDialog">
	    	<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
	    	<div class="pageDialogMain" id="pageDialogMain"> </div>
	    </div>
  </body>
</html>
