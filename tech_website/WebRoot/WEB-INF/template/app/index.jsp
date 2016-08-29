<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>手机版</title>
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
                        触屏版请用手机访问wx.java.egouos.com
                    </p>
                    <div class="download_btn">
                        <a title="立即下载" href="http://java.egouos.com/egouos1.0.apk"></a>
                    </div>
                </div>
            </div>
        </div>

        <div class="app_content" style="height: 3435px;">
            <div class="part-box" id="divBox" style="margin-top: -687px;">

                <div class="part bgcolor1" id="part1" style="height: 687px;">
                    <div class="tHeader" id="divHead">
                        <div class="tHead">
                            <ul>
                                <li class="tLogo">
                                    <a class="tHome" href="http://www.egouos.com/">
                                        <img src="http://java.egouos.com/App/images/logo.gif" alt="" />
                                    </a>
                                    <img class="line" src="http://java.egouos.com/App/images/line.gif" alt="" />
                                    <a class="txt" title="手机版" href="http://java.egouos.com/#">手机版</a></li>
                                <li class="tNav">
                                    <a class="tReturn" title="1元夺宝首页" href="http://www.egouos.com/">易购 1元夺宝首页</a>
                                    <a class="current" title="手机版" href="http://java.egouos.com/#">手机版</a>
                                    <a title="微信关注" href="http://java.egouos.com/#">微信关注</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="phone-wrapper">
                        <div style="display: none;" class="stars1"></div>
                        <div style="display: none;" class="stars2"></div>
                        <div style="display: none;" class="stars3"></div>
                        <div style="display: none;" class="stars4"></div>
                        <div style="display: none;" class="stars5"></div>
                        <div style="display: none;" class="stars6"></div>
                        <div style="display: none;" class="stars7"></div>
                        <div class="part1_phone"></div>
                        <div style="top: -404px; opacity: 0; display: none;" class="part1_search"></div>
                        <div style="top: -177px; opacity: 0; display: none;" class="part1_card"></div>
                        <div style="top: -239px; opacity: 0; display: none;" class="part1_wx"></div>
                        <div style="bottom: -35px; opacity: 0; display: none;" class="part1_circle1"></div>
                        <div style="bottom: -88px; opacity: 0; display: none;" class="part1_circle2"></div>
                        <div style="bottom: -150px; opacity: 0; display: none;" class="part1_pic1"></div>
                        <div style="bottom: -176px; opacity: 0; display: none;" class="part1_pic2"></div>
                    </div>
                </div>

                <div class="part bgcolor2" id="part2" style="height: 687px;">
                    <div class="phone-wrapper">
                        <div class="part2_land"></div>
                        <div style="left: 354px; opacity: 1;" class="part2_packs"></div>
                        <div style="top: 5px; opacity: 1;" class="part2_phone"></div>
                        <div style="" class="part2_shape"></div>
                        <div style="left: -105px; opacity: 1;" class="part2_people"></div>
                    </div>
                </div>

                <div class="part bgcolor3" id="part3" style="height: 687px;">
                    <div class="phone-wrapper">
                        <div class="part3_phone"></div>
                        <div style="display: none;" class="part3_balloons"></div>
                        <div style="display: none;" class="part3_people"></div>
                        <div style="display: none;" class="part3_search"></div>
                    </div>
                </div>

                <div class="part bgcolor4" id="part4" style="height: 687px;">
                    <div class="phone-wrapper">
                        <div class="part4_phone"></div>
                        <div style="display: none;" class="part4_clouds"></div>
                        <div style="display: none;" class="part4_card"></div>
                    </div>
                </div>

                <div class="part bgcolor5" id="part5" style="height: 687px;">
                    <div class="phone-wrapper">
                        <div style="display: none;" class="part5_clouds"></div>
                        <div style="display: none;" class="sun"></div>
                        <div class="part5_land"></div>
                        <div style="display: none;" class="part5_phone"></div>
                        <div style="display: none;" class="part5_people"></div>
                    </div>
                </div>

            </div>

        </div>

        <div class="round_current" id="divPage" style="top: 288.5px;">
            <ul>
                <li class=""><a href="javascript:;"></a></li>
                <li class="active"><a href="javascript:;"></a></li>
                <li class=""><a href="javascript:;"></a></li>
                <li class=""><a href="javascript:;"></a></li>
                <li class=""><a href="javascript:;"></a></li>
            </ul>
        </div>

        <div class="return_top" id="divReturnTop" style="display: block;">
            <a href="javascript:;"></a>
        </div>

    </div>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/mobile.js"></script>
	</body>
</html>
