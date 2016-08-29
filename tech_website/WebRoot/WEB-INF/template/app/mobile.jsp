<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>手机版</title>
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layout.css" />
	</head>
	
	<body style="overflow-y: hidden;">
	<div class="tContent">

        <div class="code-box">
            <div class="code-wrapper">
                <div class="part1_text" id="divTip"><h3>简约视觉  更加快捷</h3><h4>全新视觉设计，操作更加快捷！</h4></div>
                <div class="codeCon">
                    <div class="code"></div>
                    <p>
                        <span>手机扫描二维码下载</span>
                        触屏版请用手机访问weixin.<%=ApplicationListenerImpl.sysConfigureJson.getDomain()%>
                    </p>
                    <div class="download_btn">
                        <a title="立即下载" href="/egouos1.0.apk"></a>
                    </div>
                </div>
            </div>
        </div>

        <div class="app_content">
            <div id="divBox" class="part-box">

                <div id="part1" class="part bgcolor1">
                    <div id="divHead" class="tHeader">
                        <div class="tHead">
                            <ul>
                                <li class="tLogo"> <a href="/" class="tHome"> <img alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/Images/new-logo.png" /> </a><img alt="" src="/Images/line.gif" class="line" /><a href="/#" title="手机版" class="txt">手机版</a></li>
                                <li class="tNav">
                                    <a href="/" title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>" class="tReturn"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>首页</a>
                                    <a href="/#" title="手机版" class="current">手机版</a>
                                    <a href="/#" title="桌面版">触屏版</a>
                                    <a href="/#" title="微信关注">微信关注</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="phone-wrapper">
                        <div class="stars1" style="display: none;"></div>
                        <div class="stars2" style="display: none;"></div>
                        <div class="stars3" style="display: none;"></div>
                        <div class="stars4" style="display: none;"></div>
                        <div class="stars5" style="display: none;"></div>
                        <div class="stars6" style="display: none;"></div>
                        <div class="stars7" style="display: none;"></div>
                        <div class="part1_phone"></div>
                        <div class="part1_search" style="display: none;"></div>
                        <div class="part1_card" style="display: none;"></div>
                        <div class="part1_wx" style="display: none;"></div>
                        <div class="part1_circle1" style="display: none;"></div>
                        <div class="part1_circle2" style="display: none;"></div>
                        <div class="part1_pic1" style="display: none;"></div>
                        <div class="part1_pic2" style="display: none;"></div>
                    </div>
                </div>

                <div id="part2" class="part bgcolor2" >
                    <div class="phone-wrapper">
                        <div class="part2_land"></div>
                        <div class="part2_packs" style="display: none;"></div>
                        <div class="part2_phone" style="display: none;"></div>
                        <div class="part2_shape" style="display: none;"></div>
                        <div class="part2_people" style="display: none;"></div>
                    </div>
                </div>

                <div id="part3" class="part bgcolor3">
                    <div class="phone-wrapper">
                        <div class="part3_phone"></div>
                        <div class="part3_balloons" style="display: none;"></div>
                        <div class="part3_people" style="display: none;"></div>
                        <div class="part3_search" style="display: none;"></div>
                    </div>
                </div>

                <div id="part4" class="part bgcolor4">
                    <div class="phone-wrapper">
                        <div class="part4_phone"></div>
                        <div class="part4_clouds" style="display: none;"></div>
                        <div class="part4_card" style="display: none;"></div>
                    </div>
                </div>

                <div id="part5" class="part bgcolor5">
                    <div class="phone-wrapper">
                        <div class="part5_clouds" style="display: none;"></div>
                        <div class="sun" style="display: none;"></div>
                        <div class="part5_land"></div>
                        <div class="part5_phone" style="display: none;"></div>
                        <div class="part5_people" style="display: none;"></div>
                    </div>
                </div>

            </div>

        </div>

        <div id="divPage" class="round_current">
            <ul>
                <li class="active"><a href="javascript:;"></a></li>
                <li><a href="javascript:;"></a></li>
                <li><a href="javascript:;"></a></li>
                <li><a href="javascript:;"></a></li>
                <li><a href="javascript:;"></a></li>
            </ul>
        </div>

        <div id="divReturnTop" class="return_top">
            <a href="javascript:;"></a>
        </div>

    </div>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery123.js"></script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/mobile.js"></script>
	</body>
</html>
