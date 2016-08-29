<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>触屏手机版</title>
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layout.css" />
	</head>
	
<body>
    <div class="tContent">
        <div class="tMain">
            <div class="tHead">
                <ul>
                    <li class="tLogo"> <a href="/" class="tHome"> <img alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/Images/new-logo.png" /> </a><img alt="" src="/Images/line.gif" class="line" /><a href="/#" title="触屏手机版" class="txt">触屏手机版</a></li>
                    <li class="tNav">
                        <a href="/" title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>首页" class="tReturn"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>首页</a>
                        <a href="/#" title="手机版" >手机版</a>
                        <a href="/#" title="触屏版" class="current">触屏版</a>
                        <a href="/#" title="微信关注">微信关注</a>
                </ul>
            </div>
            <div class="tPic">
                <img src="/Images/mob.png" />
            </div>
            <div class="tInfo">
                <div class="tTitle">功能特色</div>
                <ul>
                    <li class="w380"><dd><s class="a1"></s></dd><dt><span>随时随地，所有商品，一览无余！</span>即将揭晓、最新上架，及时查看，不错过任何一次机会！</dt></li>
                    <li class="w450"><dd><s class="a2"></s></dd><dt><span>是不是我中了？第一时间看到！</span>中奖信息，不用等待短信或邮件通知，第一时间查看揭晓结果！</dt></li>
                    <li class="w380"><dd><s class="a3"></s></dd><dt><span>中奖了？随时登录填写地址！</span>中奖了第一时间填上地址，更快速完成操作，即可更快的收到商品哦！</dt></li>
                    <li class="w450"><dd><s class="a4"></s></dd><dt><span>又有人晒单了，看看都是谁吧！</span>有人晒单，谁又回复了我的晒单？ 随时掌握晒单动态！</dt></li>
                </ul>
            </div>
        </div>
    </div>    
	</body>
</html>
