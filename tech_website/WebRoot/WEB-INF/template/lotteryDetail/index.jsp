<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>${latestlottery.productName }${latestlottery.productTitle }_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/lotterydetail.css" />
  </head>
<body id="loadingPicBlock" rf="2">
    <!--开奖列表开始-->
    <input name="hidGoodsID" type="hidden" id="hidGoodsID" value="${latestlottery.productId }" />
    <input name="hidCodeID" type="hidden" id="hidCodeID" value="${latestlottery.spellbuyProductId }" />
    <input name="hidIsShowCalc" type="hidden" id="hidIsShowCalc" value="${latestlottery.sscNumber }" />
    <input name="hidShowCalcTime" type="hidden" id="hidShowCalcTime" value="2015-07-04 11:18:18.888"/>
    <input name="hidRaffleTime" type="hidden" id="hidRaffleTime" value="${startDate }" />
    <input name="hidBaseNum" type="hidden" id="hidBaseNum" value="${latestlottery.dateSum }" />
    <input name="hidCodeQuantity" type="hidden" id="hidCodeQuantity" value="${latestlottery.productPrice }" />
    <input name="hidYuShu" type="hidden" id="hidYuShu" value="${latestlottery.randomNumber-10000001}" />
    <input name="hidCodeRno" type="hidden" id="hidCodeRno" value="${latestlottery.randomNumber}" />
    <input name="hidLatestlotteryUserId" type="hidden" id="hidLatestlotteryUserId" value="${latestlottery.userId}" />
    <input type="hidden" id="resultCount" value="${resultCount }"/>
	<input type="hidden" id="pageCount" value="${pageCount }"/>
	 <c:choose>
	    <c:when test="${productJSON.productId != null}">
   	<input name="hidMostPeriod" type="hidden" id="hidMostPeriod" value="${fn:length(productPeriodList)+1}" />
	    </c:when>
	    <c:otherwise>
   	<input name="hidMostPeriod" type="hidden" id="hidMostPeriod" value="${fn:length(productPeriodList)}" />
	    </c:otherwise>
    </c:choose>
    <!--当前位置-->
    <div class="Current_nav">
        当前位置：<a href="/">首页</a> <span>&gt;</span> <a href="/list/hot20.html">所有商品</a> <span>&gt;</span> 揭晓详情 
    </div>
    <div class="ng-wrap">
         <div id="ng_pt_line" class="ng-pt-line"></div>
        <div id="divMain" class="ng-main-wrapper">
            <!--期数-->
            <div class="ng-total-nav">
                <ul class="ng-sort ng-result clearfix">
                <c:choose>
                	<c:when test="${productJSON.productId != null}">
                		<li class="now "><a title="第${productJSON.productPeriod }期进行中" class="orange" href="/products/${productJSON.productId }.html">第${productJSON.productPeriod }期进行中<span class="dotting"></span></a><div style="display:" class="result-line"><div style="width:${fn:replace( fn:substring((productJSON.currentBuyCount/productJSON.productPrice)*100, 0, 2),'.','' )}%;" title="已完成${fn:replace( fn:substring((productJSON.currentBuyCount/productJSON.productPrice)*100, 0, 2),'.','' )}%" class="on-line"></div></div></li>
	                	<c:choose>
                		<c:when test="${(productJSON.productPeriod-latestlottery.productPeriod)>8}">
                			<li id="li_more1"><a href="javascript:;">......</a></li>
   			                	<c:forEach items="${productPeriodList}" var="entry" varStatus="i" begin="0" end="6">
							    	<c:choose>
							    		<c:when test="${entry.key==latestlottery.productPeriod}">
							    			<li class="current "><a title="第${latestlottery.productPeriod }期" href="javascript:;">第${latestlottery.productPeriod }期</a></li>
							    		</c:when>
							    		<c:otherwise>
							    			<c:choose>
										    	<c:when test="${entry.key==1}">
												     <li class="last"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${entry.value}.html" title="第${entry.key }期">第${entry.key }期</a></li>
										    	</c:when>
										    	<c:otherwise>
										    		<li class=""><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${entry.value}.html" title="第${entry.key }期">第${entry.key }期</a></li>
										    	</c:otherwise>
										    </c:choose>
							    		</c:otherwise>
							    	</c:choose>
								</c:forEach> 
			                   <c:if test="${fn:length(productPeriodList)>6}">
								     <li id="li_more"><a href="javascript:;" title="更多">更多<i>+</i></a></li>
						    	</c:if>
                		</c:when>
                		<c:otherwise>
   			                	<c:forEach items="${productPeriodList}" var="entry" varStatus="i" begin="0" end="7">
							    	<c:choose>
							    		<c:when test="${entry.key==latestlottery.productPeriod}">
							    			<li class="current "><a title="第${latestlottery.productPeriod }期" href="javascript:;">第${latestlottery.productPeriod }期</a></li>
							    		</c:when>
							    		<c:otherwise>
							    			<c:choose>
										    	<c:when test="${entry.key==1}">
												     <li class="last"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${entry.value}.html" title="第${entry.key }期">第${entry.key }期</a></li>
										    	</c:when>
										    	<c:otherwise>
										    		<li class=""><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${entry.value}.html" title="第${entry.key }期">第${entry.key }期</a></li>
										    	</c:otherwise>
										    </c:choose>
							    		</c:otherwise>
							    	</c:choose>
								</c:forEach> 
			                   <c:if test="${fn:length(productPeriodList)>7}">
								     <li id="li_more"><a href="javascript:;" title="更多">更多<i>+</i></a></li>
						    	</c:if>
                		</c:otherwise>
                	</c:choose>
                	</c:when>
                	<c:otherwise>
                		<c:forEach items="${productPeriodList}" var="entry" varStatus="i" begin="0" end="8">
					    	<c:choose>
					    		<c:when test="${entry.key==latestlottery.productPeriod}">
					    			<c:choose>
								    	<c:when test="${entry.key==fn:length(productPeriodList)}">
								    			<li class="current now"><a title="第${latestlottery.productPeriod }期" href="javascript:;">第${latestlottery.productPeriod }期</a></li>
								    	</c:when>
								    	<c:otherwise>
								    		<li class="current"><a title="第${latestlottery.productPeriod }期" href="javascript:;">第${latestlottery.productPeriod }期</a></li>
								    	</c:otherwise>
								    </c:choose>
					    		</c:when>
					    		<c:otherwise>
					    			<c:choose>
								    	<c:when test="${entry.key==fn:length(productPeriodList)}">
										     <li class="now"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${entry.value}.html" title="第${entry.key }期">第${entry.key }期</a></li>
								    	</c:when>
								    	<c:otherwise>
								    		<li class=""><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${entry.value}.html" title="第${entry.key }期">第${entry.key }期</a></li>
								    	</c:otherwise>
								    </c:choose>
					    		</c:otherwise>
					    	</c:choose>
						</c:forEach> 
	                   <c:if test="${fn:length(productPeriodList)>8}">
						     <li id="li_more"><a href="javascript:;" title="更多">更多<i>+</i></a></li>
				    	</c:if>
                	</c:otherwise>
                </c:choose>
                	
                </ul>
            </div>
            <!--商品揭晓-->
            <div id="div_ngresult" class="ng-result-wrapper clearfix  ">
                <!--图片展示-->
                <div class="ng-result-img">
                	<c:choose>
                   		<c:when test="${productJSON.productId!=null}">
                   			<div class="result-img-wrapper">
		                            <a href="/product/${latestlottery.productId }.html" title="${latestlottery.productName }">
		                           	<c:if test="${fn:length(productimageList)!=0}">
				                    	<s:iterator value="productimageList" var="productimages" begin="0" end="0">
				                    		 <img width="242" height="242" alt="${latestlottery.productName }" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }${productimages.imageType }" />
				                    	</s:iterator>
			                    	</c:if>
		                            </a>
		                    </div>
		                    <a href="/product/${latestlottery.productId }.html" class="result-more">查看商品详情</a>
                   		</c:when>
                   		<c:otherwise>
                   			<div class="result-img-wrapper">
		                            <a href="javascript:;" title="${latestlottery.productName }">
		                           	<c:if test="${fn:length(productimageList)!=0}">
				                    	<s:iterator value="productimageList" var="productimages" begin="0" end="0">
				                    		 <img width="242" height="242" alt="${latestlottery.productName }" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }${productimages.imageType }" />
				                    	</s:iterator>
			                    	</c:if>
		                            </a>
		                    </div>
		                    <a href="javascript:;" class="result-more">该商品已经下架</a>
                   		</c:otherwise>
                   	</c:choose>
                    
                </div>
                <!--揭晓结果-->
                <div class="ng-result-detail">
                    <div class="result-con-wrapper">
                        <h2 class="title"><span class="num">(第${latestlottery.productPeriod }期)</span>${latestlottery.productName }</h2>
                        <p class="price">
                            价值：￥${latestlottery.productPrice }.00
                        </p>
                        
                        <div class="result-main">
                            <div class="result-con-info">
                                <p class="r-name">
                                    <span><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${latestlottery.userId }.html" target="_blank"  title="${latestlottery.userName }">${latestlottery.userName }</a></span>(<em>${latestlottery.location }</em>)
                                </p>
                                <p>本期参与：<span class="r-num">${latestlottery.buyNumberCount }</span>人次<a id="a_luckynum" href="javascript:;" class="r-look">点击查看</a></p>
                                <p>揭晓时间：<span>${latestlottery.announcedTime }</span></p>
                                <p><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>时间：<span>${latestlottery.buyTime }</span></p>
                                <div class="result-head-pic">
                                    <div class="rh-wrap"><img width="110" height="110" src="${latestlottery.userFace }"/></div><a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${latestlottery.userId }.html" target="_blank" title="${latestlottery.userName }" class="ng-result-head transparent-png"><span class="name">获得者</span></a>
                                </div>
                            </div>
                            <div class="result-con-code">
                                <p class="code-name">— 幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码 —</p>
                                <span class="code-num">${latestlottery.randomNumber }</span>
                            </div>
                        </div>
                        <div class="result-how">
                            <h6>如何计算?</h6>
                            <p>1、取该商品最后购买时间前网站所有商品的最后100条购买时间记录；</p>
                            <p>2、按时、分、秒、毫秒排列取值之和，除以该商品总参与人次后取余数；</p>
                            <p>3、余数加上10000001 即为“幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码”；</p>
                            <p>4、余数是指整数除法中被除数未被除尽部分， 如7÷3 = 2 ......1，1就是余数。</p>
                        </div>
                    </div>
                </div>
                <!--查看分期-->
                <div class="ng-result-select">
                    <ul class="r-select">
                    	<c:choose>
             				<c:when test="${latestlottery.productPeriod==1}">
             					<c:forEach items="${productPeriodList}" var="entry" varStatus="i">
                   					<c:if test="${(latestlottery.productPeriod+1)==entry.key}">
						    			<li><a href="/products/${entry.value}.html"><i class="ng-result-bg up transparent-png"></i><span></span></a></li>
						    		</c:if>
								</c:forEach> 
               					<li><a class="end" href="javascript:;"><i class="ng-result-bg down transparent-png"></i><span></span></a></li>
             				</c:when>
               				<c:otherwise>
               					<c:choose>
               						<c:when test="${latestlottery.productPeriod==fn:length(productPeriodList)}">
               							<c:choose>
                							<c:when test="${productJSON.productId != null}">
                								<li><a href="/products/${productJSON.productId}.html"><i class="ng-result-bg up transparent-png"></i><span></span></a></li>
                							</c:when>
                							<c:otherwise>
                								<li><a class="end" href="javascript:;"><i class="ng-result-bg up transparent-png"></i><span></span></a></li>
                							</c:otherwise>
               							</c:choose>
               							<c:forEach items="${productPeriodList}" var="entry" varStatus="i">
		                   					<c:if test="${(latestlottery.productPeriod-1)==entry.key}">
								    			<li><a href="/products/${entry.value}.html"><i class="ng-result-bg down transparent-png"></i><span></span></a></li>
								    		</c:if>
										</c:forEach> 
               						</c:when>
               						<c:otherwise>
               							<c:forEach items="${productPeriodList}" var="entry" varStatus="i">
		                   					<c:if test="${(latestlottery.productPeriod+1)==entry.key}">
								    			<li><a href="/products/${entry.value}.html"><i class="ng-result-bg up transparent-png"></i><span></span></a></li>
								    		</c:if>
										</c:forEach>
										<c:forEach items="${productPeriodList}" var="entry" varStatus="i">
		                   					<c:if test="${(latestlottery.productPeriod-1)==entry.key}">
								    			<li><a href="/products/${entry.value}.html"><i class="ng-result-bg down transparent-png"></i><span></span></a></li>
								    		</c:if>
										</c:forEach> 
               						</c:otherwise>
               					</c:choose>
              				</c:otherwise>
              			</c:choose>
                    </ul>
                </div>
                <!--清除浮动-->
                <div class="clear"></div>
            </div>
        </div>

        <!--3块导航-->
        <div class="ng-main clearfix">
            <div class="ng-data-wrapper">
                <div class="ng-data-m">
                    <ul id="ul_menu" class="ng-data-nav clearfix">
                        <li class="current"><a href="javascript:;">计算详情</a></li>
                        <li><a href="javascript:;">参与记录</a></li>
                        <li><a href="javascript:;">商品晒单</a></li>
                    </ul>
                    <div id="midNavLine" class="m-line"></div>
                </div>
                <!--计算详情-->
                <div id="div_evaldata" class="ng-data-inner">
                    <div class="ng-b-wait"></div>
                </div>
                <!--所有参与记录-->
                <div id="div_allrecord" class="ng-all-record" style="display: none;">
                    <div class="record">
                        <ul class="record-list">
                            <div class="ng-b-wait"></div>
                        </ul>
                    </div>
                         <div class="pages" name="bitem">
						<ul class="pageULEx" id="pagination"></ul>
						</div>
                </div>
                <!--晒单-->
                <div id="div_postlist" class="ng-share-goods" style="display: none;">
                    <div class="ng-share-wrapper">
                        <div class="ng-b-wait"></div>
                    </div>
                    <div class="pages" name="bitem">
					<ul class="pageULEx2" id="pagination2"></ul>
					</div>
                </div>
            </div>
        </div>
    </div>
    <!--开奖列表结束-->
    <script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagination.js"></script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/lotterydetail.js"></script>
</body>
</html>