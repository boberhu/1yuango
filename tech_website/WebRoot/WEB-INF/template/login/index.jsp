<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
   <meta name="decorator" content="template_footer" />
    <title>会员登录_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/login.css" />
    <script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="<%=ApplicationListenerImpl.sysConfigureJson.getQqAppId() %>" data-redirecturi="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl() %>/qc_callback.html" charset="utf-8"></script>
  </head>
  <body>
         <!--顶部-->
<!--头部-->
    
  	 <!--login 开始-->
     <div class="wrapper">
        <div class="g-logo-top g-logo-width">
      <a rel="nofollow" href="/" class="transparent-png fl"><img src="/Images/new-logo.png"></a>
      <span class="fr"><a href="/">返回首页</a></span>
  </div>
      <input name="hidLoginForward" type="hidden" id="hidLoginForward" value="${forward }" />
        <input name="hidQQAppID" type="hidden" id="hidQQAppID" value="<%=ApplicationListenerImpl.sysConfigureJson.getQqAppId() %>" />
        <input name="hidWxAppID" type="hidden" id="hidWxAppID" value="<%=ApplicationListenerImpl.sysConfigureJson.getWxAppID() %>" />
        <input name="hidAccount" type="hidden" id="hidAccount" value="${account }"/>
        <div class="g-login-con clrfix" id="g_login">
            <div class="m-login-screen clrfix">
                <div id="loadingPicBlock" class="screen-left fl"></div>
                <div class="screen-right fr clrfix">
                    <div class="login-panel" id="LoginForm">
                        <dl>
                            <dt>
                                <em class="fl">登录</em>
                                 <a id="hylinkRegisterPage" tabindex="7" class="fr orange" href="/register/index.html?forward=${forward }">免费注册<i class="passport-icon"></i></a>
                            </dt>
                            <dd>
                                <div class="register-form-con clrfix">
                                    <ul>
                                        <li>
                                            <input id="username" type="text" maxlength="100" tabindex="1" autocomplete="off" />
                                            <b class="passport-icon user-name transparent-png"></b>
                                            <em style="display: none;">手机号</em>
                                        </li>
                                        <li>
                                            <input id="pwd" type="password" maxlength="20" tabindex="2" value="" />
                                            <b class="passport-icon login-password transparent-png"></b>
                                            <em style="display: none;">密码</em>
                                        </li>
                                    </ul>
                                </div>
                            </dd>
                            <dd class="text-alignR">
                                <a id="hylinkGetPassPage" tabindex="3" class="gray9" href="/getbackpwd/index.html?forward=rego">忘记密码？</a>
                            </dd>
                            <dd class="error-message orange" style="display: none;" id="dd_error_msg"></dd>
                        </dl>
                        <p><a id="btnSubmitLogin" href="javascript:;" class="z-agreeBtn" tabindex="4">登录</a></p>
                        <div class="other-login">
                            <span>使用其它方式快捷登录：</span>
                              <%
						    if(ApplicationListenerImpl.sysConfigureJson.getQqAppStatus()==0){
						    %>
						    	 <span id="qq_login_btn"></span> 
								<!-- <a id="qq_login_btn" href="javascript:;" class="qq-icon" tabindex="5"><b class="passport-icon transparent-png"></b></a> -->
		                    <%
						    }
						    %>
						    <%
						    if(ApplicationListenerImpl.sysConfigureJson.getWxAppStatus()==0){
						    %>
								<a id="wx_login_btn" href="javascript:;" class="wx-icon" tabindex="6"><b class="passport-icon transparent-png"></b></a>
                           <%
						    }
						    %>
                        </div>
                        <ul id="j-tips-wrap" class="j-tips-wrap j-login-page">
                        </ul>
                    </div>
                    <div class="wx-login clrfix" id="wxLogin" style="display: none;">
                        <h2 class="gray3">微信登录</h2>
                        <p id="p_code"></p>
                        <a id="a_return" href="javascript:;"></a>
                    </div>
                </div>
            </div>
        </div>
    <!--login 结束-->
<script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/login.js"></script>
</div>
  </body>
</html>
