<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta name="decorator" content="index_template" />
		<title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>_帮助中心 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/qqgroup.css" />
			
	</head>

	<body>
		<div class="help-main">
			<div class="qqGroup">
				<div class="qqTitle">
					<span class="groupt"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %><b>QQ</b>群</span>
					<span>为打造更具公平、透明的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>平台，<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>特成立各地QQ交流群（可在选择框查找本地群），欢迎广大拍友加入。<br />
							另外，<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>正在招募各地群主加盟，也可自建群，具体待遇和要求请咨询QQ:<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq()%>
					</span>
				</div>

				<div class="qqList" id="listTopContents">
					<p>
						直属群
					</p>
					<ul>
					<s:iterator value="qqgroupList" var="qqgroups">
						<c:if test="${qqgroups.groupDistrict==null}">
							<li>
							<dt>
								<img border="0" width="45px;" height="45px;" src="/Images/qqgroup.jpg" />
							</dt>
							<dt>
								${qqgroups.groupName }
<!--								<em>已满</em>-->
							</dt>
							<dd>
								${qqgroups.groupNo }
							</dd>
						</li>
						</c:if>
					</s:iterator>
					</ul>
				</div>
				<div class="qsearch">
					<ul>
						<li>
							<label>
								筛选：
							</label>
						</li>
						<li>
							<dd>
								<span id="sltAreaAID">----请选择----</span><b></b>
							</dd>
							<div class="sel" id="itemAreaAID" style="height: 220px; display: none;">
							</div>
						</li>
						<li>
							<dd>
								<span id="sltAreaBID">----请选择----</span><b></b>
							</dd>
							<div class="sel" id="itemAreaBID" style="height: 22px; display: none;">
								<a no="0" href="javascript:;">----请选择----</a>
							</div>
						</li>
						<li>
							<dd>
								<span id="sltAreaCID">----请选择----</span><b></b>
							</dd>
							<div class="sel" id="itemAreaCID" style="height: 22px; display: none;">
								<a no="0" href="javascript:;">----请选择----</a>
							</div>
						</li>
					</ul>
					<span>地方群</span>
				</div>
				<div class="qqList" id="listContents">
					<ul>
<!--						<li>-->
<!--							<dt>-->
<!--								<img border="0" width="45px;" height="45px;" src="/Images/qqgroup.png" alt="一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>-铜川总群" />-->
<!--							</dt>-->
<!--							<dt>-->
<!--								一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>-铜川总群-->
<!--							</dt>-->
<!--							<dd>-->
<!--								-->
<!--							</dd>-->
<!--						</li>-->
					</ul>
				</div>
			</div>
		</div>
		<script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/qqgroup.js"></script>
	</body>
</html>
