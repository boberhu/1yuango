<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ResourceBundle url = ResourceBundle.getBundle("config");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<meta name="decorator" content="admin_main" />
	<title>1元夺宝_管理中心</title>
	<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
  </head>
  
  <body name="sysInfo">
  <div class="page-head">
		<h2>系统概括</h2>
	  <div class="cl-mcont">
	 	<div class="stats_bar">
					<div class="butpro butstyle flat">
						<a href="/admin_back/userListAll.action"><div class="sub"><h2>会员总数</h2><span id="total_clientes">载入中</span></div></a>
					</div>
					<div class="butpro butstyle flat">
						<div class="sub"><h2>购买总数</h2><span id="buyCount">载入中</span></div>
					</div>
					<div class="butpro butstyle flat">
						<a href="/admin_back/index.action?id=hot20"><div class="sub"><h2>在售商品数</h2><span id="productCount">载入中</span></div></a>
					</div>	
					<div class="butpro butstyle flat">
						<a href="/admin_back/latestList.action"><div class="sub"><h2>揭晓商品</h2><span id="lotteryCount">载入中</span></div></a>
					</div>	
					<div class="butpro butstyle flat">
						<a href="/admin_back/shareList.action?typeId=hot20"><div class="sub"><h2>晒单总数</h2><span id="shareCount">载入中</span></div></a>
					</div>
		</div>
	</div>
</div>

<div class="cl-mcont">
	<div class="row">
		<div class="col-sm-6 col-md-6">
			<div class="block-flat">
				<div class="header">
					<h3>系统信息</h3>
				</div>
				<div class="content">
					<h6>网站名称：<%=url.getString("saitName")%></h6>
					<h6>网站域名：<%=url.getString("www")%></h6>
					<h6>网站关键词：<%=url.getString("keyword")%></h6>
					<h6>网站描述：<%=url.getString("description")%></h6>
					<h6>系统邮箱：<%=url.getString("mailName")%></h6>
					<h6>系统邮箱密码：<%=url.getString("mailPwd")%></h6>
					<h6>网站备案编号：<%=url.getString("icp")%></h6>
					<h6>财付通商户号：<%=url.getString("tenpayPartner")%></h6>
					<h6>财付通初始密钥：<%=url.getString("tenpayKey")%></h6>
					<h6>支付宝商户号：<%=url.getString("alipayPartner")%></h6>
					<h6>支付宝初始密钥：<%=url.getString("alipayKey")%></h6>
					<h6>支付宝帐户：<%=url.getString("alipayMail")%></h6>
					<div class="spacer">&nbsp;</div>
				</div>
			</div>
		
		</div>
		
		<div class="col-sm-6 col-md-6">
			<div class="block-flat">
				<div class="header">							
					<h3>\u7248\u6743\u4fe1\u606f</h3>
				</div>
				<div class="content">
					<h4>\u6b22\u8fce\u4f7f\u75281\u5143\u62cd\u8d2d\u6e90\u7801\uff0c\u672c\u6e90\u7801\u4e3a java \u8bed\u8a00\u7f16\u5199\uff0c\u62e5\u6709\u6700\u597d\u7684\u5b89\u5168\u6027\u80fd\uff0c\u7a0b\u5e8f\u53ef\u90e8\u7f72\u96c6\u7fa4\u670d\u52a1\uff0c\u4e0d\u6015\u4eba\u6c14\u7206\u68da\uff0c\u8be5\u7a0b\u5e8f\u4e0d\u90a6\u5b9a\u57df\u540d\uff0c\u4e0d\u505a\u4efb\u4f55\u9650\u5236\uff0c\u53ef\u6c38\u4e45\u4f7f\u7528\u66f4\u65b0\uff0c\u60a8\u6709\u4efb\u4f55\u597d\u7684\u610f\u89c1\u53ef\u4e0e\u4f5c\u8005\u8054\u7cfb\uff0c\u6211\u4eec\u5171\u540c\u4e0d\u65ad\u5b8c\u5584\u7cfb\u7edf\uff0c\u7a0b\u5e8f\u63d0\u4f9b\u6c38\u4e45\u5347\u7ea7\u670d\u52a1\u3002\u6211\u4eec\u53ef\u5b9a\u5236\u5f00\u53d1\u529f\u80fd\uff0c\u5982\u679c\u4f60\u6709\u9700\u8981\u8bf7\u8054\u7cfb\u6211\u4eec\u3002\uff08\u6309\u529f\u80fd\u590d\u6742\u6210\u5ea6\u6536\u8d39\uff09
					</h4>
					<p></p>
					<p class="text-right">1\u5143\u62cd\u8d2d</p>
					<h4>www.egouos.com</h4>
					<p class="lead">\u611f\u8c22\u60a8\u7684\u652f\u6301\uff0c\u6211\u4eec\u4f1a\u7528\u5fc3\u505a\u7684\u66f4\u597d\uff01</p>
					<p class="text-right">\u8d2d\u4e70\u7a0b\u5e8f\u8bf7\u8054\u7cfbQQ\uff1a 2825670967</p>
				</div>
			</div>
		</div>			
	</div>
</div>
<script type="text/javascript">
   $.ajax({
   		url:"/admin_back/numberCount.action",
   		success:function(data){
   			if(data!=null){
   				data = data.split("_");
   				$("#total_clientes").text(data[0]);
   				$("#buyCount").text("￥"+data[1]);
   				$("#productCount").text(data[2]);
   				$("#lotteryCount").text(data[3]);
   				$("#shareCount").text(data[4]);
   			}
   		},
   		error:function(){
   		}
   });
</script>
  </body>
</html>
