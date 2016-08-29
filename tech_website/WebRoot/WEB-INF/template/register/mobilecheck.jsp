<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>会员注册_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
	<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/register.css" />
  </head>
  
  <body>
  	<div class="login_layout">
	    <div class="login_title">
		    <h2>新用户注册</h2>
		    <ul class="login_process">
			    <li><b>1</b>填写注册信息</li>
			    <li class="login_arrow"></li>
			    <li class="login_processCur"><b>2</b>验证邮箱/验证手机</li>
			    <li class="login_arrow"></li>
			    <li><b>3</b>完成注册</li>
		    </ul>
		    <span>已经是会员？<a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/login/index.html?forward=${forward }" class="blue Fb" id="hylinkLoginPage">登录</a></span>
	    </div>
	    <div class="login_Content">
	        <div class="login_CMobile_Complete">
			    <p><%=ApplicationListenerImpl.sysConfigureJson.getSaitName() %>已向您的手机 <span class="orange">${str}</span> 免费发送了一条验证短信，请查看您的手机短信！</p>
				<dl>
					<dt>请输入短信验证码：</dt>
					<dd><input type="text" value="" class="login_CMobile_Code" id="mobileCode" /></dd>
					<dd></dd>
				</dl>
			    <a class="login_Email_but" href="javascript:;" id="btnSubmitRegister">提交验证</a>
			    <input type="hidden" value="${str }" id="hidRegMobile" name="hidRegMobile" />
			    <input type="hidden" value="${isVerify }" id="checkSN" name="isVerify" />
		    </div>
		    <div class="login_Explain">
			    <h2>没收到验证短信？</h2>
			    <p>1.请查看手机的垃圾短信，信息有可能被误认为是垃圾信息。</p>
			    <p>2.如果在2分钟后仍未收到验证短信，请点击 <a class="login_SendoutbutClick" href="javascript:;" id="retrySend">重新发送</a> </p>
			    <p>3.如果手机号码不小心输错了或者想换个号码？请点击 <a href="register.html?forward=<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>" class="blue Fb" id="hylinkRegisterPageA">重新注册</a></p>
		    </div>
            
            
	    </div>
    </div>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/mobilecheck.js"></script>
  </body>
</html>
