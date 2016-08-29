<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>官方微信</title>
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layout.css" />
	</head>
	
<body>
    <div class="tContent">
        <div class="tMain">
            <div class="tHead">
                <ul>
                    <li class="tLogo"> <a href="/" class="tHome"> <img alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/Images/new-logo.png" /> </a><img alt="" src="/Images/line.gif" class="line" /><a href="/#" title="官方微信" class="txt">官方微信</a></li>
                    <li class="tNav">
                        <a href="/" title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>首页" class="tReturn"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>首页</a>
                        <a href="/#" title="手机版">手机版</a>
						<a href="/#" title="桌面版">触屏版</a>
                        <a href="/#" title="微信关注" class="current">微信关注</a>
                    </li>
                </ul>
            </div>
         </div>
         <div class="mPic2">
         	<div class="mPic">
                <ul>
                    <li class="pic1"></li>
                    <li class="pic2"></li>
                    <li class="pic3">没有安装微信？ <a href="http://weixin.qq.com/" title="点击这里下载安装" target="_blank">点击这里下载安装>></a></li>
                </ul>
            </div>
         </div>
         <div class="tMain">
            <div class="mMode">
                <div class="tTitle">关注方式</div>
                <ul>
                    <li>
                        <dd><img src="/Images/pic_23.jpg" /></dd>
                        <dd class="mt60"><img src="/Images/pic_24.jpg" /></dd>
                    </li>
                    <li>
                        <dd class="mt60"><img src="/Images/pic_25.jpg" /></dd>
                        <dd><img src="/Images/pic_26.jpg" /></dd>
                    </li>
                    <li>
                        <dd><img src="/Images/pic_27.jpg" /></dd>
                        <dd class="mt60"><img src="/Images/pic_28.jpg" /></dd>
                    </li>
                </ul>
            </div>
          </div>
        </div>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery123.js"></script>
	</body>
</html>
