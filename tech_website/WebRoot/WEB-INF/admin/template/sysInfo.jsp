<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.pojo.Admin"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ResourceBundle url = ResourceBundle.getBundle("config");
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
	<title>1元夺宝_管理中心</title>
	<link href="/admin_css/global.css"  rel="stylesheet" type="text/css"/>
<!--	<link href="/admin_css/style.css"  rel="stylesheet" type="text/css"/>-->
<!--	<script type="text/javascript" src="/ueditor/editor_config.js"></script>-->
<!--	<script type="text/javascript" src="/ueditor/editor_all.js"></script> -->
<!--	<script language="javascript" type="text/javascript" src="/admin_js/jquery.modalEffects.js"></script>-->
<style>
	 body{ background-color:#fefeff; font:12px/1.5 arial,宋体b8b\4f53,sans-serif;}
	.width30{ width:25%; font-size:12px; border-radius:5px 2px 20px 2px;  }
	
	.title{ font-size:15px; font-weight:bold; color:#444; line-height:30px; border-bottom:1px solid #ccc;}
	.div-news{ height:50px; background-color:#fff}
	.div-user span{ display:block; font-size:12px; font: 12px/1.5 arial,宋体b8b\4f53,sans-serif; line-height:20px; color:#999}
	.div-user{ background-color:#fff; padding:20px;width:30%;float:left;  border-bottom:1px solid #eee }
	.div-button{ float:left;background-color:#fff; float:left; padding:20px; margin:0 10px; width:55%;border-radius:5px 5px 5px 5px;}
	.div-button ul li{ float:left; margin:0px 25px;}
	.div-button li a{  cursor:pointer; text-decoration:none}
	.div-button li span{ display:block; width:60px; text-align:center; line-height:32px;} 
	
	.div-system{background-color:#fff; float:left; padding:20px; margin:0 10px;border-right:1px solid #eee}
	.div-webinfo{background-color:#fff; float:left; padding:20px; margin:0 10px; width:27%;border-right:1px solid #eee }
	.div-about{background-color:#fff; float:left; padding:20px; margin:0 10px; overflow:hidden}
	 li{font:12px/1.5 arial,宋体b8b\4f53,sans-serif;}
	.div-system ul li{height:30px; line-height:30px;color:#333;border-bottom:1px dotted #ddd;overflow: hidden;}
	.div-system ul li i{width:90px;height:30px; line-height:30px; display:inline-block; color:#666;}
	.div-about ul li{height:30px; line-height:30px;color:#333;border-bottom:1px dotted #ddd;}
	.div-about ul li i{width:90px;height:30px; line-height:30px; display:inline-block; color:#666;}
	
	.div-webinfo ul li{height:30px; line-height:30px;color:#333;border-bottom:1px dotted #ddd;}
	.div-webinfo ul li i{width:90px;height:30px; line-height:30px; display:inline-block; color:#666;}
	
	.CMS_message{background-color: #eef3f7;border: 1px solid #d5dfe8; height:20px; padding:5px 0px; overflow:hidden}
	.CMS_message li{ text-indent:50px; height:25px; line-height:25px; color:#09c;font-size:12px; font-weight:bold;}
	
</style>
  </head>
  
<body>
<div class="bk30"></div>
<div class="div-user lr10">
	<h2>管理账户: <font color="#4c95b6"><%= ((Admin)request.getSession().getAttribute("admin")).getUserName() %></font></h2>
    <span>管理员角色: 超级管理员</span>
    <span>上次登录时间: <%= ((Admin)request.getSession().getAttribute("admin")).getUser().getOldDate() %></span>
    <span>上次登录IP:<%= ((Admin)request.getSession().getAttribute("admin")).getUser().getOldIpAddress() %></span>
</h1></div>
<div class="div-button">
<div class="bk15"></div>
	<ul >
    	<li><a style="color:#09c;" href="/admin_back/toAddNews.action"><img src="/admin_img/news-logo.png" /><span>添加新闻</span></a></li>
        <li><a style="color:#09c;" href="/admin_back/toAddProduct.action"><img src="/admin_img/commodity-logo.png" /><span>添加商品</span></a></li>
        <li><a style="color:#09c;" href="/admin_back/userListAll.action"><img src="/admin_img/user-logo.png" /><span>会员管理</span></a></li>
        <li><a style="color:#09c;" href="/admin_back/toBasicSet.action"><img src="/admin_img/system-logo.png" /><span>系统设置</span></a></li>
        <li><a style="color:#09c;" href="http://help.egouos.com/"><img src="/admin_img/service-logo.png" /><span>帮助中心</span></a></li>

    </ul>
</div>


<div class="bk10"></div>
<div id="roll" class="lr10 CMS_message">
<ul>
<li>温馨提示：操作后台请使用谷歌浏览器或者火狐浏览器!</li>
</ul>
</div>
<div class="bk10"></div>

<div style="overflow:hidden">
<!------------>
    <div class="div-system width30">
        <div class="title">系统信息</div>
        	<div class="bk10"></div>
            <ul>        
                <li><i>网站名称: </i>${sysConfigure.saitName }</li>
                <li><i>网站标题: </i>${sysConfigure.saitTitle }</li>
                <li><i>网站域名: </i>${sysConfigure.wwwUrl }</li>
                <li><i>微信端域名: </i>${sysConfigure.weixinUrl }</li>
                <li title="${sysConfigure.keyword }"><i>网站关键词: </i>${sysConfigure.keyword }</li>
                <li title="${sysConfigure.description }"><i>网站描述: </i>${sysConfigure.description }</li>
                <li><i>系统邮箱: </i>${sysConfigure.mailName }</li>
                <li><i>ICP备案号: </i><font color="#1194be">${sysConfigure.icp }</font></li>
                <li><i>客服电话: </i><font color="#1194be">${sysConfigure.serviceTel }</font></li>
                <li><i>客服QQ: </i><font color="#1194be">${sysConfigure.serviceQq }</font></li>
      
            </ul>      
    </div>
	    <div class="div-webinfo width30">
        <div class="title">网站信息统计</div>
        <div class="bk10"></div>
        <ul>
           <li><i>会员总数:</i><span id="total_clientes">载入中</span></li>
           <li><i>购买总数:</i><span id="buyCount">载入中</span></li>
           <li><i>在售商品数:</i><span id="productCount">载入中</span></li>
           <li><i>揭晓商品:</i><span id="lotteryCount">载入中</span></li>
           <li><i>晒单总数:</i><span id="shareCount">载入中</span></li>
        </ul>
    </div>
    
    <div class="div-about width30" id="sysInfo">
        <div class="title">关于我们</div>
        <div class="bk10"></div>
        <ul>
        	<li><i>程序版本:</i>V1.0.0<font color="#f60">【高级版】</font><script language="javascript" type="hidden" src="http://js.users.51.la/18798727.js"></script></li>
			<li><i>更新时间:</i>2016.03.06</li>
            <li><i>程序开发:</i>易购OS</li>
            <li><i>版权所有:</i>易购OS</li>
            <li><i>官方网站:</i><a style="color:blue" target="_black" href="http://www.egouos.com">http://www.egouos.com</a></li>    
            <li><i>客户帮助中心:</i><a style="color:blue" target="_black" href="http://help.egouos.com">http://help.egouos.com</a></li>       
        </ul>
    </div>
<!------------>
</div>
<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
<script language="javascript" type="text/javascript" src="/js/sysinfo.js"></script>
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
