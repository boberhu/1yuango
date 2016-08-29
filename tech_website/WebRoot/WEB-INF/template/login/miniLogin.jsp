<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>会员登录_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/minilogin.css" />
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery-1.4.4-min.js"></script>
    <%
    if(ApplicationListenerImpl.sysConfigureJson.getQqAppStatus()==0){
    %>
    <script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="<%=ApplicationListenerImpl.sysConfigureJson.getQqAppId() %>" data-redirecturi="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl() %>/qc_callback.html" charset="utf-8"></script>
    <%
    }
    %>
  </head>
  
<body>
    <div id="divCartLogin" class="mini-login clrfix">
        <ul>
            <li><label>账号</label><input name="userName" type="text" class="input-text" value="手机号/邮箱" /></li>
            <li><label>密码</label><input name="userPwd" type="text" class="input-text" value="登录密码" /><input type="password" class="input-text" style="display: none;" /></li>
            <li class="cart-wrong"></li>
            <li class="cart-login-btn"><a href="javascript:;">登录</a></li>
            <li class="other-login">
                <span class="fl"><a href="/getbackpwd/index.html" target="_blank">忘记密码？</a></span>
                <span class="fr"><a id="btnQQLogin" href="javascript:;"><b></b>QQ登录</a><a href="/register/index.html?forward=rego" target="_blank" class="mfzc">免费注册</a></span>
            </li>
        </ul>
    </div>

<script type="text/javascript">
var $img = "<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>";
var $skin = "<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>";
var $www = "<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>";
var $domain = "<%=ApplicationListenerImpl.sysConfigureJson.getDomain()%>";
</script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/minilogin.js"></script>
</body>
</html>
