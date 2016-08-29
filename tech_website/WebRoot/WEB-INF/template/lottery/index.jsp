<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>最新揭晓_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
	<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/lottery.css" />
  </head>
  
  <body  class="lottery"  id="loadingPicBlock">
  <input name="hidCurSortID" type="hidden" id="hidCurSortID" value="${id }" />
   <!--内容部分-->
    <div class="g-main-con clrfix">
        <div class="w1190">
            <div class="g-lottery-part1 clrfix">
                <div class="m-lottery-title">
                    <div class="fl">
                        <em>最新揭晓</em>
                        <%-- <i></i>
                        <a href="/map.html" target="_blank" title="进入地图模式">
                            <img alt="" src="/Images/map_mode.jpg"/></a> --%>
                    </div>
                    <div class="fr">
                        <span class="gray9">截至目前共揭晓商品<b class="orange" id="lotteryCount">${resultCount }</b>件</span>
                    </div>
                </div>
                <div class="m-lott-menu">
                    <ul id="lottMenu">
                    	<li class=""><a href="/lottery/index.html" title="全部">全部</a></li>
                    	<s:iterator value="protypeList" var="protype">
                    	<li class=""><a href="/lottery/${protype.typeId}.html" title="${protype.typeName}">${protype.typeName}</a></li>
                    	</s:iterator>
                    </ul>
                </div>
            </div>
            
            <!--加载-->
            <div id="divLoading" class="g-loading-2014" style="display: none;"><i></i></div>

            <!--提示-->
            <div id="divMsg" class="g-lott-noRecord" style="display: none;"></div>

            <!--数据列表-->
            <div id="divLottery" class="g-lottery-part2 clrfix">
<!--            <div class="m-lottery-list m-lottery-anning m-anning-wh393 m-lottery-bor-rb">-->
<!--            <ul><li class="f-lott-comm"><a title="小米（MIUI）红米1S 4G手机 移动版 金属灰" target="_blank" href="/product/285987.html"><img src="http://goodsimg.1yyg.com/goodspic/pic-200-200/20140814181751323.jpg"></a></li>-->
<!--            <li class="f-lott-detailed"><dl><dt><a title="(第4712期)小米（MIUI）红米1S 4G手机 移动版 金属灰" target="_blank" href="/product/285987.html">(第4712期)小米（MIUI）红米1S 4G手机 移动版 金属灰</a></dt><dd>商品价值：￥699.00</dd><dd class="z-ymy">已满员</dd><dd class="z-jx-time"><p>揭晓倒计时</p><cite><span class="minute">02</span><em>:</em><span class="second">35</span><em>:</em><span><i class="millisecond">9</i><i class="last">4</i></span></cite></dd></dl></li></ul><b></b></div>-->
			<s:iterator value="latestlotteryList" var="latestlotterys" status="i">
				<c:choose>
					<c:when test="${i.index+1 ==3 ||i.index+1 ==6||i.index+1==9||i.index+1==12||i.index+1==15||i.index+1==18||i.index+1==21||i.index+1==24}">
						<div class="m-lottery-list m-lottery-special" type="isRaff">
			            <ul><li class="f-lott-comm"><a title="${latestlotterys.productTitle }" target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${latestlotterys.spellbuyProductId }.html"><img alt="${latestlotterys.productName }" class="scrollLoading" data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${latestlotterys.productImg}" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/pixel.gif" /></a></li>
			            <li class="f-lott-detailed"><div class="u-user-info"><p class="fl"><a title="${latestlotterys.buyUser }" target="_blank" href="/u/${latestlotterys.userId }.html"><img border="0" class="scrollLoading" data-url="${latestlotterys.userFace }"  src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/pixel.gif" type="userPhoto"/><s></s></a></p><dl class="fl"><dt><em>获得者：</em><span><a title="${latestlotterys.buyUser }" target="_blank" href="/u/${latestlotterys.userId }.html">${latestlotterys.buyUser }</a></span></dt><dd class="z-lott-lz">来自：${latestlotterys.location }</dd><dd>幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码：<strong class="orange">${latestlotterys.randomNumber }</strong></dd><dd>本期<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>：<i class="orange">${latestlotterys.buyNumberCount }</i>人次</dd></dl></div>
			            <div class="u-comm-info"><dl><dt><a title="(第${latestlotterys.productPeriod }期)${latestlotterys.productName }" target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${latestlotterys.spellbuyProductId }.html">(第${latestlotterys.productPeriod }期)${latestlotterys.productName }</a></dt><dd>商品价值：￥${latestlotterys.productPrice }.00</dd><dd>揭晓时间：${latestlotterys.announcedTime }</dd><dd class="z-lott-btn"><span><a title="查看详情" target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${latestlotterys.spellbuyProductId }.html">查看详情</a></span>
			            <c:if test="${latestlotterys.shareStatus==2}">
			            	<p><em>已晒单</em><a title="查看" target="_blank" href="/shareShow/${latestlotterys.shareId }.html">查看</a><cite><i></i></cite></p>
			            </c:if>
			            </dd></dl></div></li></ul></div>
					</c:when>
					<c:otherwise>
						<div class="m-lottery-list" type="isRaff">
			            <ul><li class="f-lott-comm"><a title="${latestlotterys.productTitle }" target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${latestlotterys.spellbuyProductId }.html"><img alt="${latestlotterys.productName }" class="scrollLoading" data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${latestlotterys.productImg}" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/pixel.gif" /></a></li>
			            <li class="f-lott-detailed"><div class="u-user-info"><p class="fl"><a title="${latestlotterys.buyUser }" target="_blank" href="/u/${latestlotterys.userId }.html"><img border="0" class="scrollLoading" data-url="${latestlotterys.userFace }"  src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/pixel.gif" type="userPhoto"/><s></s></a></p><dl class="fl"><dt><em>获得者：</em><span><a title="${latestlotterys.buyUser }" target="_blank" href="/u/${latestlotterys.userId }.html">${latestlotterys.buyUser }</a></span></dt><dd class="z-lott-lz">来自：${latestlotterys.location }</dd><dd><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码：<strong class="orange">${latestlotterys.randomNumber }</strong></dd><dd>本期<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>：<i class="orange">${latestlotterys.buyNumberCount }</i>人次</dd></dl></div>
			            <div class="u-comm-info"><dl><dt><a title="(第${latestlotterys.productPeriod }期)${latestlotterys.productName }" target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${latestlotterys.spellbuyProductId }.html">(第${latestlotterys.productPeriod }期)${latestlotterys.productName }</a></dt><dd>商品价值：￥${latestlotterys.productPrice }.00</dd><dd>揭晓时间：${latestlotterys.announcedTime }</dd><dd class="z-lott-btn"><span><a title="查看详情" target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${latestlotterys.spellbuyProductId }.html">查看详情</a></span>
			            <c:if test="${latestlotterys.shareStatus==2}">
			            	<p><em>已晒单</em><a title="查看" target="_blank" href="/shareShow/${latestlotterys.shareId }.html">查看</a><cite><i></i></cite></p>
			            </c:if>
			            </dd></dl></div></li></ul></div>
					</c:otherwise>
				</c:choose>
				
				
			</s:iterator>
<!--            <div class="m-lottery-list m-lottery-special" type="isRaff">-->
<!--            <ul><li class="f-lott-comm"><a title="三只松鼠 碧根果 210g/包 奶油味" target="_blank" href="http://www.1yyg.com/lottery/286536.html"><img src="http://goodsimg.1yyg.com/goodspic/pic-200-200/20141114150837262.jpg"></a></li>-->
<!--            <li class="f-lott-detailed"><div class="u-user-info"><p class="fl"><a title="13380****16" target="_blank" href="http://u.1yyg.com/1007168364"><img src="http://faceimg.1yyg.com/UserFace/00000000000000000.jpg" type="userPhoto"><s></s></a></p><dl class="fl"><dt><em>获得者：</em><span><a title="13380****16" target="_blank" href="http://u.1yyg.com/1007168364">13380****16</a></span></dt><dd class="z-lott-lz">来自：广东省东莞市</dd><dd>幸运夺宝码：<strong class="orange">10000015</strong></dd><dd>本期夺宝：<i class="orange">5</i>人次</dd></dl></div><div class="u-comm-info"><dl><dt><a title="(第2680期)三只松鼠 碧根果 210g/包 奶油味" target="_blank" href="http://www.1yyg.com/lottery/286536.html">(第2680期)三只松鼠 碧根果 210g/包 奶油味</a></dt><dd>商品价值：￥25.00</dd><dd>揭晓时间：刚刚</dd><dd class="z-lott-btn"><span><a title="查看详情" target="_blank" href="http://www.1yyg.com/lottery/286536.html">查看详情</a></span></dd></dl></div></li></ul></div>-->
            
            </div>

            <!--翻页-->
            <div id="divPage" class="g-pagination clrfix" style="display:none;"></div>
			<br /><br />
			${pageString }
        </div>

    </div>

    <!--开奖列表结束-->
    <!--底部-->
	<script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/lottery2.js"></script>
	<script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.scrollloading-min.js"></script>
	<script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/lotterytime2.js"></script>
  </body>
</html>
