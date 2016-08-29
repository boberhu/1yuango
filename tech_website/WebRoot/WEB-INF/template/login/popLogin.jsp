<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>会员登录_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/logincart.css" />
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery-1.4.4-min.js"></script>
    
    <script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="<%=ApplicationListenerImpl.sysConfigureJson.getQqAppId() %>" data-redirecturi="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl() %>/qc_callback.html" charset="utf-8"></script>
    
  </head>
  
  <body>
    <div class="wrapper">
        <input name="hidLoginForward" type="hidden" id="hidLoginForward" value="${forward }" />
        <input name="hidQQAppID" type="hidden" id="hidQQAppID" value="<%=ApplicationListenerImpl.sysConfigureJson.getQqAppId() %>" />
        <input name="hidWxAppID" type="hidden" id="hidWxAppID" value="wxe61d43f2e02a5b10" />
        <div class="screen-right clrfix">
            <div class="login-panel" id="LoginForm">
                <dl>
                    <dt>
                        <em class="fl">登录</em>
                        <a id="hylinkRegisterPage" tabindex="7" class="fr orange" href="/register/index.html?forward=${forward }" target="_blank">免费注册<i class="passport-icon"></i></a>
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
                                    <input id="pwd" type="password" maxlength="20" tabindex="2" />
                                    <b class="passport-icon login-password transparent-png"></b>
                                    <em style="display: none;">密码</em>
                                </li>
                            </ul>
                        </div>
                    </dd>
                    <dd class="text-alignR">
                        <a id="hylinkGetPassPage" tabindex="3" class="gray9" href="/getbackpwd/index.html?forward=${forward }" target="_blank">忘记密码？</a></dd>
                    <dd class="error-message orange" style="display: none;" id="dd_error_msg"></dd>
                </dl>
                <p><a id="btnSubmitLogin" href="javascript:;" class="z-agreeBtn" tabindex="4">登录</a></p>
                <div class="other-login">
                    <span>使用其它方式快捷登录：</span>
                    <%
				    if(ApplicationListenerImpl.sysConfigureJson.getQqAppStatus()==0){
				    %>
				    	<span id="qq_login_btn"></span>
<!--                    <a id="qq_login_btn" href="javascript:;" class="qq-icon" tabindex="5"><b class="passport-icon transparent-png"></b></a>-->
                    <%
				    }
				    %>
<!--                    <a id="wx_login_btn" href="javascript:;" class="wx-icon" tabindex="6"><b class="passport-icon transparent-png"></b></a>-->
                </div>

                <!--下拉-->
                <ul class="j-tips-wrap" id="j-tips-wrap">
                </ul>

            </div>
            <div class="wx-login clrfix" id="wxLogin" style="display: ;">
                <h2 class="gray3">微信登录</h2>
                <p id="p_code"></p>
                <a id="a_return" href="javascript:;"></a>
            </div>
        </div>
    </div>
    <link type="text/css" rel="stylesheet" href="/css/pagedialog.css?v=141125" />
    <script language="javascript" type="text/javascript" src="/js/pagedialog.js?v=141125"></script>
    <div class="pageDialogBG" id="pageDialogBG"></div>
    <div class="pageDialogBorder" id="pageDialogBorder"></div>
    <div class="pageDialog" id="pageDialog">
    	<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
    	<div class="pageDialogMain" id="pageDialogMain"> </div>
    </div>
<script type="text/javascript">
var $img = "<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>";
var $skin = "<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>";
var $www = "<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>";
var $domain = "<%=ApplicationListenerImpl.sysConfigureJson.getDomain()%>";
</script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/spoplogin.js"></script>
</body>
</html>
