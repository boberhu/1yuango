<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>我的晒单_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></title>
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutframe.css?data=20131121" />
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/postsingleadd.css?data=20131121" />
  </head>
  
<body class="f-width-change">
  	<div class="main-content clearfix">
        
<div class="left">
	<div class="head">
	    <a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${user.userId }.html">
	    <img width="160" height="160" border="0" runat="server" alt="" src="${user.faceImg }" id="imgUserPhoto" /></a>
	</div>
	<div class="head-but">
		<a class="blue" href="/user/UserPhoto.html">修改头像</a>
		<a class="blue fr" href="/user/MemberModify.html">编辑资料</a>
	</div>
	<div class="sidebar-nav">
<p class="sid-line"></p>
<h2 class=""><a href="index.html"><b></b>我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></a></h2><p class="sid-line"></p>
<h3 url="/user/UserBuyList.html" haschild="1" class="sid-icon02 "><a href="javascript:;"><b></b>我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <s title="收起"></s></a></h3>
<ul><li class=""><a href="/user/UserBuyList.html"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</a></li>
<li class=""><a href="/user/OrderList.html">获得的商品</a></li>
<li class="sid-cur"><a href="/user/PostSingleList.html">晒单</a></li></ul>
<!--<p class="sid-line"></p><h3 url="/user/JoinGroup.html" haschild="1" class="sid-icon03 "><a href="javascript:;"><b></b>圈子管理 <s title="收起"></s></a></h3>-->
<!--<ul><li class=""><a href="/user/JoinGroup.html">加入的圈子</a></li>-->
<!--<li class=""><a href="/user/Topic.html">圈子话题</a></li></ul>-->
<p class="sid-line"></p><h3 url="/user/InvitedList.html" haschild="1" class="sid-icon04 "><a href="javascript:;"><b></b>邀请管理 <s title="收起"></s></a></h3>
<ul><li class=""><a href="/user/InvitedList.html">邀请好友</a></li>
<li class=""><a href="/user/CommissionQuery.html">佣金明细</a></li>
<li class=""><a href="/user/ApplyMention.html">提现申请</a></li>
<li class=""><a href="/user/MentionList.html">提现记录</a></li></ul>
<p class="sid-line"></p><h3 url="/user/UserBalance.html" haschild="1" class="sid-icon05 "><a href="javascript:;"><b></b>账户管理 <s title="收起"></s></a></h3>
<ul><li class=""><a href="/user/UserBalance.html">账户明细</a></li>
<li class=""><a href="/user/UserRecharge.html">账户充值</a></li>
<li class=""><a href="/user/userCardRecharge.html">实卡充值</a></li>
</ul>
<!--<p class="sid-line"></p><h3 url="/user/MyFriends.html" haschild="1" class="sid-icon06 "><a href="javascript:;"><b></b>我的好友 <s title="收起"></s></a></h3>-->
<!--<ul><li class=""><a href="/user/MyFriends.html">我的好友</a></li>-->
<!--<li class=""><a href="/user/SearchFriends.html">查找好友</a></li>-->
<!--<li class=""><a href="/user/FriendsApply.html">好友请求</a></li></ul>-->
<p class="sid-line"></p><h3 url="" haschild="0" class="sid-icon07 "><a href="/user/MemberPoints.html"><b></b>我的积分</a></h3>

<!--<p class="sid-line"></p><h3 url="" haschild="0" class="sid-icon08 "><a href="/user/UserMessage.html"><b></b>消息管理</a></h3>-->

<p class="sid-line"></p><h3 url="" haschild="0" class="sid-icon09 "><a href="/user/MemberModify.html"><b></b>个人设置</a></h3>


		<p class="sid-line"></p>
	</div>
	<div class="sid-service">
		<p><a class="service-btn" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq() %>&site=qq&menu=yes" id="btnLeftQQ"><s></s>在线客服</a></p>
		<span>客服热线(免长途费)</span>
		<b class="tel"><%=ApplicationListenerImpl.sysConfigureJson.getServiceTel() %></b>
	</div>
</div>
<script type="text/javascript">

    var _NavState = [true, true, true, true, true];
    
    $("div.sidebar-nav").find("h3").each(function(i,v){
        var _This = $(this);
        var _HasClild = _This.attr("hasChild")=="1";
        
        var _SObj = _This.find("s");
        _This.click(function(e){
            if(_HasClild){
                var _State = _NavState[i];
                
                /* 一级栏目更改样式 */
                if(_State){
                    _This.addClass("sid-iconcur");
                    _SObj.attr("title","展开");
                }
                else {
                    _This.removeClass("sid-iconcur");
                    _SObj.attr("title","收起");
                }
                
                /* 二级栏目显示或隐藏 */
                _This.next("ul").children().each(function(){
                    if(_State){
                        $(this).hide(50);
                    }
                    else {
                        $(this).show(50);
                    }
                });
                _NavState[i] = !_State;
            }
        });
    });
    
</script>
<div class="R-content">
            <div class="member-t">
                <h2>晒单-编辑晒单</h2>
            </div>
            <c:choose>
            	<c:when test="${empty shareinfo}">
            		<div class="myshare">该分享不属于您的，无法编辑....</div>
            	</c:when>
            	<c:otherwise>
            		<div class="myshare">
		                <div class="text_myshare">
		                    <div style="display: none;">
		                        <iframe src="about:blank" scrolling="no" name="upFrame" id="upFrame"></iframe>
		                    </div>
		                    <ul class="upload">
		                        <li><span class="titl">主&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</span><input name="postTitle" type="text" id="postTitle" class="title_click" value="${shareinfo.shareTitle }" /></li>
		                        <li><span class="content"><%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>感言：</span>
		                        <textarea name="postContent" id="postContent" class="textarea_click" rows="4" cols="30">
		                        ${shareinfo.shareContent }
		                        </textarea></li>
		                        <li><span class="pic">上传图片：</span>
		                            <div class="uppic">
		                                <form id="pageForm" name="pageForm" method="post" action="/user/PostSingleAddUpLoadImg.html" target="upFrame" enctype="multipart/form-data">
		                                <div class="upImgLoading hidden"><s></s>上传中</div>
		                                <div class="localBtn">
		                                    <div id="upImgBtn" class="btn"></div>
		                                    <div id="FileControl" class="btnFile">
		                                        <input type="file" style="cursor: pointer;" hidefocus="true" name="myFile" class="file" title="" accept="image/*" id="fuploadFile" />
		                                    </div>
		                                </div>
		                                </form>
		                                <p>&nbsp;&nbsp;&nbsp;&nbsp; 您最少可以上传3张，最多上传10张照片，必须为实物拍摄商品图，单张图片不得超过5M。</p>
		                                <input name="hidPicAll" type="hidden" id="hidPicAll" value="${hidPicUrl }" />
		                                <input name="postID" type="hidden" id="postID" value="${shareinfo.sproductId }" /><br />
		                                <br /><div class="pic" id="ulPicBox"></div>
		                            </div>
		                        </li>
		                        
		                        <li class="value">
		                            <input id="but_ok" class="bluebut" type="button" value="确认编辑"/></li>
		                    </ul>
		                </div>
		                <ul class="text">
		                    <li class="tit">晒单说明:</li>
		                    <li>1、晒单内容越详细，获得的积分奖励就越多（限时商品除外）；</li>
		                    <li>2、您的文字描述应不少于100字，高清的商品实拍照不少于3张；</li>
		                    <li>3、请避免晒单内容中文字与图片的大量重复；</li>
		                    <li>4、为提高晒单的真实性，您可以展示真实有效的快递单号；</li>
		                    <li>5、建议晒出您与商品的合照，您的手机短信、邮件通知及夺宝网上的交易详情页面截图等；</li>
		                    <li>6、注意保持晒单内容与获得商品的关联性，请勿使用其他网站的图片，请勿违反国家相关规定，否则我们有权删除并冻结账号，且保留追究其法律责任的权利</li>
		                    <li>7、如果晒单内容非常新颖，那么您有可能会获得更高的奖励！</li>
		                </ul>
		            </div>
            	</c:otherwise>
            </c:choose>
        </div>
    </div>
      
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/postsingleedit.js?data=20131121"></script>
    <div class="clear_process"></div>
    <link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css" />
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js"></script>
    <div class="pageDialogBG" id="pageDialogBG"></div>
	<div class="pageDialogBorder" id="pageDialogBorder"></div>
	<div class="pageDialog" id="pageDialog">
		<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
		<div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
	</div>
</body>
</html>
