<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="template_footer" />
    <title>会员注册_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
	<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/register.css" />
	<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/qqinfo.css" />
	<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/sslcomm.css" />
  </head>
  
  <body>
  <div class="sHeadCon">
     <div class="sHead">
         <a class="fl" href="/" rel="nofollow">
             <img width="105px;" height="63px" title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>" alt="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>" src="<%=ApplicationListenerImpl.sysConfigureJson.getSaitLogo()%>" />
         </a>
         <a title="返回首页" class="fr" href="/" rel="nofollow">返回首页</a>
     </div>
 </div>
 	 <input name="hidTokenInfo" id="hidTokenInfo" value="${user.attribute22 }" type="hidden" />
  	<div class="login_layout">
	<div class="login_title">
		<span>已经是会员？<a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/login/index.html?forward=<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>" class="blue Fb" id="hylinkLoginPage">登录</a></span>
	</div>
	<div class="login_qqcon">
		    <div class="login_qqConInput">
			    <div class="login_qq_tips_txt">
				    <h2>亲爱的微信用户:</h2>
				    <p>欢迎光临<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>，由于您还未验证手机或邮箱，如果您获得了商品我们无法联系到您，请您及时验证！</p>
			    </div>
			    <div class="login-qq-img">
				    <img border="0" id="userPhoto" src="${user.faceImg }" />
			    </div>
			    <dl>
				    <dd><input type="text" class="login_qqinput_text" name="txtUserAccount" id="userAccount" style="color: rgb(102, 102, 102);"></dd>
				    <dd></dd>
			    </dl>
			    <div class="login_Membut">
				    <a class="Mem_qq_orangebut" href="javascript:;" id="btnSubmit">立即验证</a><a class="Mem_qq_graybut" href="javascript:;" id="btnSkip">放弃验证</a>
				    <input type="hidden" value="/" id="hidRegisterForward" name="hidRegisterForward" />
			    </div>
			    <div class="login_Agreement"><input type="checkbox" checked="checked" id="cbAgr"><label for="cbAgr">我已阅读并同意<a target="_blank" class="blue" href="/help/agreement.html"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>用户服务协议</a></label></div>
		    </div>
	    </div>
    </div>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/qqinfo.js"></script>
  </body>
</html>
