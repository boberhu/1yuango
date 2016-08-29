<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>${productInfo.productName }${productInfo.productTitle }_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/goodsdetail.css" />
  </head>
<body id="loadingPicBlock" rf="2">
 	<input name="hidGoodsID" type="hidden" id="hidGoodsID" value="${productInfo.productId  }" />
    <input name="hidCodeID" type="hidden" id="hidCodeID" value="${productInfo.spellbuyProductId  }" />
    <input name="hidTitle" type="hidden" id="hidTitle" value="${productInfo.productTitle  }" />
    <input name="hidGoodsTag" type="hidden" id="hidGoodsTag" value="10" />
    <input name="hidCodeState" type="hidden" id="hidCodeState" value="${productInfo.status }" />
    <input name="hidMostPeriod" type="hidden" id="hidMostPeriod" value="${productInfo.productPeriod  }" />
    <input name="hidIsEnd" type="hidden" id="hidIsEnd" value="0" />
    <input name="hidLimitBuy" type="hidden" id="hidLimitBuy" value="${productInfo.spellbuyLimit }" />
    <input type="hidden" id="resultCount" value="${resultCount }"/>
	<input type="hidden" id="pageCount" value="${pageCount }"/>
    <input type="hidden" id="userId" value="${userId }"/>
    <!--当前位置-->
    <div class="Current_nav">
        当前位置：<a href="/">首页</a> <span>&gt;</span> <a href="/list/hot20.html">所有商品</a> <span>&gt;</span> 商品详情 
    </div>
    <div class="ng-wrap">
        <div id="ng_pt_line" class="ng-pt-line"></div>
        <div id="divMain" class="ng-main-wrapper">
            <!--期数-->
            <div class="ng-total-nav">
                <ul class="ng-sort clearfix">
                	<c:forEach items="${productPeriodList}" var="entry" varStatus="i" begin="0" end="8">
				    	<c:choose>
				    		<c:when test="${entry.key==productInfo.productPeriod}">
					    		<li class="current"><a href="javascript:;" title="第${productInfo.productPeriod }期">第${productInfo.productPeriod }期<span class="dotting"></span></a></li>
				    		</c:when>
				    		<c:otherwise>
				    			<c:choose>
				    				<c:when test="${entry.key > productInfo.productPeriod}">
				    					<li class="now "><a title="第${entry.key }期进行中" class="orange" href="/products/${entry.value}.html">第${entry.key }期进行中<span class="dotting"></span></a></li>
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
				    		</c:otherwise>
				    	</c:choose>
					</c:forEach> 
                    <c:if test="${fn:length(productPeriodList)>8}">
					     <li id="li_more"><a href="javascript:;" title="更多">更多<i>+</i></a></li>
			    	</c:if>
                   
                </ul>
            </div>

            <!--商品信息 begin-->
            <div id="divMainInfo" class="ng-main clearfix">
                <!--图片展示-->
                <div class="ng-goods-img">
                    <div id="middlePicRemark" class="middlePicRemark"></div>
                    <div id="middlePicBox" class="ng-middle-box"  style="position: relative;">
                    <span id="BigViewImage" style="outline-style: none; cursor: crosshair; display: block; position: relative; height: 343px; width: 343px;">
                    <div id="onload" style="background-color: #fff;border: 1px solid #f66;display: none;font-size: 14px;height: 25px;margin-left: 100px;margin-top: 180px;position: absolute;text-align: center;width: 200px;">正在努力加载图片...</div>
                    <c:if test="${fn:length(productimageList)!=0}">
                    	<s:iterator value="productimageList" var="productimages" begin="0" end="0">
                    		<img id="imgGoodsPic" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }${productimages.imageType }" style="width:343px; height:343px;" alt="" rel="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }${productimages.imageType }" class="jqzoom"/>
                    	</s:iterator>
                   	</c:if>
                    </span>
                    <c:if test="${productInfo.spellbuyLimit>0}">
                   	<div class="ng-over-time F_goods_xg transparent-png"><span>限购</span></div>
                    </c:if>
                    </div>
                    <div class="ng-goods-piclist">
                        <div class="ng-goods-btn prev ng-box-bg" style="display: none;"></div>
                        <div class="ng-list-wrapper">
                            <ul id="mycarousel" class="ng-list-inner clearfix" style="width: 999px;">
                                <s:iterator value="productimageList" var="productimages">
                                	<li><img width="40" height="40" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }_small${productimages.imageType }" big="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }${productimages.imageType }" /></li>
                                </s:iterator>
                            </ul>
                        </div>
                        <div class="ng-goods-btn next ng-box-bg" style="display: none;"></div>
                    </div>
                    <div class="ng-share-out">
                        <a id="a_sc" href="javascript:;" class="sc"><i class="t-icon sc-icon ng-box-bg"></i>关注</a>
                        <div class="fx">
                            <div class="fx-txt">
                                <i class="t-icon fx-icon ng-box-bg"></i>分享
                            </div>
                            <div>
                                <div class="fx-out-inner">
                                    <div class="bdsharebuttonbox">
                                        <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
                                        <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
                                        <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
                                        <a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
                                        <a href="#" class="bds_sqq" data-cmd="sqq" title="分享到QQ好友"></a>
                                        <!--<a href="#" class="bds_more" data-cmd="more"></a>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--正在进行中-->

                <!--揭晓倒计时-->
                <div class="ng-goods-detail">
                        <h2 class="title"><span class="num">
                            (第${productInfo.productPeriod }期)</span>${productInfo.productName }</h2>
                        <p class="price">价值：￥${productInfo.productPrice }.00</p>
                        <!--倒计时模块-->
                        <div class="time-model clearfix">
                            <div class="time-view">
                                <div class="full-tips">已满员，揭晓结果即将公布</div>
                                <ul id="ul_lotterytime" class="for-begin clearfix">
                                    <li class="cur"><em id="liMinute1">0</em><em id="liMinute2">0</em></li>
                                    <li>:</li>
                                    <li class="cur"><em id="liSecond1">0</em><em id="liSecond2">0</em></li>
                                    <li>:</li>
                                    <li class="cur"><em id="liMilliSecond1">0</em><em id="last">0</em></li>
                                </ul>
                                <!--正在计算中-->
                                <div id="div_eveling" style="display: none;" class="jiexiao-end">
                                    <div class="line-wrapper u-progress">
                                        <span class="pgbar"><span class="pging"></span></span>
                                    </div>
                                </div>
                            </div>
                            <div class="message-view">
                                <img id="imgFunny" src="http://skin.1yyg.com/images/lotteryFun/n10.gif" border="0" alt="" />
                            </div>
                        </div>
                        <!--如何计算-->
                        <div class="method">
                            <p class="t">如何计算？</p>
                            <p>1、取该商品最后购买时间前网站所有商品的最后100条购买时间记录；</p>
                            <p>2、按时、分、秒、毫秒排列取值之和，除以该商品总参与人次后取余数；</p>
                            <p>3、余数加上10000001 即为“幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>码”；</p>
                            <p>4、余数是指整数除法中被除数未被除尽部分， 如7÷3 = 2 ......1，1就是余数 。</p>
                        </div>
                        <!--计算过程-->
                        <div class="process">
                            <p class="t">计算过程</p>
                            <div class="process-detail clearfix">
                                <span>(</span>
                                <div class="process-btn step01">
                                    <div class="tb-cell">
                                        <p class="o-num">?</p>
                                        <p>
                                            100条时间<br />
                                            取值之和
                                        </p>
                                    </div>
                                </div>
                                <span>%</span>
                                <div class="process-btn step02">
                                    <div class="tb-cell">
                                        <p class="o-num">
                                            ${productInfo.productPrice }
                                        </p>
                                        <p>
                                            总需参与人次
                                        </p>
                                    </div>
                                </div>
                                <span>) +</span>
                                <div class="process-btn step03">
                                    <div class="tb-cell">
                                        <p class="o-num">10000001</p>
                                        <p>固定数值</p>
                                    </div>
                                </div>
                                <span>=</span>
                                <div class="process-btn step04">
                                    <div class="tb-cell">
                                        <p class="o-num">?</p>
                                        <p>最终计算结果</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                <!--右侧：夺宝记录-->
                <div class="ng-goods-buy">
                    <!--进行中状态-->
                    
                        <div class="ng-buy-list">
                            <div id="div_buynav" class="ng-buy-nav">
                                <a href="javascript:;" title="最新<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录" class="ng-fl current">最新<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</a>
                                <a href="javascript:;" title="我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录" class="ng-fr">我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</a>
                                <div class="b-line"></div>
                            </div>
                            <div id="div_goodsrecord" class="list-wrap">
                                <div class="my-list">
                                <c:choose>
					        		<c:when test="${fn:length(ParticipateJSONList)!=0}">
					        			<ul id="UserBuyNewList" class="list">
								            <s:iterator var="ParticipateJSON" value="ParticipateJSONList">
								            	<li><a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ParticipateJSON.userId }.html" title="${ParticipateJSON.userName }" target="_blank" class="buy-name"><i class="head-s-pic"><img src="${ParticipateJSON.userFace }" width="22" height="22"></i>${ParticipateJSON.userName }</a><span class="buy-num">${ParticipateJSON.buyCount }</span>人次</li>
								            </s:iterator>
						                </ul>
					        		</c:when>
					        		<c:otherwise>
					        			<div class="null-tips-wrapper clearfix"><div class="gth-icon transparent-png"></div><div class="null-text"><p>还没有人参与？</p><p>梦想与您只有1元的距离！</p></div></div>
					        		</c:otherwise>
					        	</c:choose>
                                </div>
	                                <a href="javascript:;" style="display: none;" class="select-all">查看全部</a>
                            </div>

                            <div id="div_myrecord" class="list-wrap" style="display: none;">
                                <div class="my-record">
                                    <!--未登录-->
                                    <div class="no-login-wrapper">
                                        <div class="gth-icon transparent-png"></div>
                                        <p class="ng-see-mycord">请您<a id="a_login" href="javascript:;">登录</a>后查看<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录！</p>
                                    </div>
                                </div>
                                <a href="javascript:;" style="display: none;" target="_blank" class="select-all">查看全部</a>
                            </div>
                        </div>
                    

                    <!--结束状态-->
                    
                </div>
                <!--清除浮动-->
                <div class="clear"></div>
            </div>
        </div>

        <!--中间浮动导航-->
        <div id="div_menu" class="ng-goods-option">
            <ul id="ul_menu" class="options-menu">
                <li class="current"><span>商品详情</span><i class="bor"></i></li><li><span>所有参与记录</span><i class="bor"></i></li><li><span>晒单</span><i class="bor"></i></li>
            </ul>
        </div>

        <!--商品信息-->
        <div id="div_desc" class="ng-product-content">
            <div class="product-con">
				${productInfo.productDetail }
            </div>
        </div>

        <!--所有参与记录-->
        <div id="div_allrecord" class="ng-all-record" style="display: none;">
            <!--记录表-->
            <div class="record">
                <div class="rocord-header">所有参与记录</div>
                <ul class="record-list">
                    <div class="ng-b-wait"></div>
                </ul>
            </div>
            <div class="pages" name="bitem">
			<ul class="pageULEx" id="pagination"></ul>
			</div>
        </div>

        <!--晒单-->
        <div id="div_postlist" class="ng-share-goods">
            <div class="ng-share-header">
                <span class="title">商品晒单</span>
            </div>
            <div class="ng-share-wrapper">
                <!--晒单开始-->
                <div class="ng-b-wait"></div>
                <!--晒单结束-->
            </div>
             <div class="pages" name="bitem">
			<ul class="pageULEx2" id="pagination2"></ul>
			</div>
        </div>
    </div>

    <link type="text/css" rel="stylesheet" href="/css/pagedialog.css?v=141125" />
    <script language="javascript" type="text/javascript" src="/js/pagedialog.js?v=141125"></script>
    <div class="pageDialogBG" id="pageDialogBG"></div>
    <div class="pageDialogBorder" id="pageDialogBorder"></div>
    <div class="pageDialog" id="pageDialog">
    	<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
    	<div class="pageDialogMain" id="pageDialogMain"> </div>
    </div>
    <script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagination.js"></script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/goodsdetail.js"></script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.imagezoom.js"></script>
</body>
</html>