<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>${productInfo.productName }${productInfo.productTitle }_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/goodsdetail.css" />
  </head>
<body id="loadingPicBlock" rf="2">
<input name="hidGoodsID" type="hidden" id="hidGoodsID" value="${productInfo.spellbuyProductId  }" />
<input name="hidCodeID" type="hidden" id="hidCodeID" value="${productInfo.spellbuyProductId  }" />
<input name="hidLogined" type="hidden" id="hidLogined" value="0" />
<input name="hidIsEnd" type="hidden" id="hidIsEnd" value="0" />
<input name="hidIsStart" type="hidden" id="hidIsStart" value="${productInfo.status }" />
<input type="hidden" id="productId" value="${productInfo.productId  }"/>
<input type="hidden" id="resultCount" value="${resultCount }"/>
<input type="hidden" id="pageCount" value="${pageCount }"/>
<input type="hidden" id="userId" value="${userId }"/>
 <div class="g-width Current_nav">
    <a href="/">首页</a> <span>&gt;</span>  商品详情 </div>
   <!-- 商品图片 -->
<div class="show_content">
        <!-- 商品期数 -->
	    <c:choose>
	    	<c:when test="${fn:length(productPeriodList)>27}">
			    <div class="show_Period" id="divPeriodList" style="height: 99px;">
	    		<div class="period_Open"><a href="javascript:;" id="btnOpenPeriod" class="gray02">展开<i></i></a></div>
	    	</c:when>
	    	<c:otherwise>
	    		<div class="show_Period" id="divPeriodList">
	    	</c:otherwise>
	    </c:choose>
		<ul class="Period_list">
	    <c:forEach items="${productPeriodList}" var="entry" varStatus="i">
	    	<c:choose>
	    		<c:when test="${entry.key==productInfo.productPeriod}">
		    		<li><b class="period_ArrowCur">第${productInfo.productPeriod }期</b></li>
	    		</c:when>
	    		<c:otherwise>
	    			<c:choose>
			    		<c:when test="${i.index==0 }">
					    	<li><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${entry.value}.html"><b class="period_Ongoing">第${entry.key }期<i></i></b></a></li>
			    		</c:when>
			    		<c:otherwise>
			    			<li><a class="gray02" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${entry.value}.html">第${entry.key }期</a></li>
			    		</c:otherwise>
			    	</c:choose>
	    		</c:otherwise>
	    	</c:choose>
    		<c:if test="${(i.index+1)%10==0 }">
		    	</ul>
		    	<ul class="Period_list">
    		</c:if>
		</c:forEach> 
	    </div>
	    <!-- 商品信息 -->
	    <div class="Pro_Details">
		    <h1><span>(第${productInfo.productPeriod }期)</span>${productInfo.productName } <span class="red">${productInfo.productTitle }</span></h1>
		    <div class="Pro_Detleft">
		        <div class="detail-itemsummary-imageviewer">
                    <div class="middlePicRemark" id="middlePicRemark"></div>
                    <div class="middlePicBox" id="middlePicBox" style="position: relative;">
                    <span id="BigViewImage" style="outline-style: none; cursor: crosshair; display: block; position: relative; height: 396px; width: 396px;">
                    <div id="onload" style="width: 200px;height:50px;position: absolute;text-align: center;padding-left: 100px;padding-top: 200px;font-size: 14px;display: none;">正在努力加载图片...</div>
                    <c:if test="${fn:length(productimageList)!=0}">
                    	<s:iterator value="productimageList" var="productimages" begin="0" end="0">
                    		<img id="imgGoodsPic" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }${productimages.imageType }" style="width:396px; height:396px;" alt="" rel="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }${productimages.imageType }" class="jqzoom"/>
                    	</s:iterator>
                   	</c:if>
                    </span></div>
                    <div class="smallPicList">
                        <div class="hidden" style="display: none;"></div>
                        <div class="jcarousel-clip">
                            <ul id="mycarousel" style="width: 324px; left: 0px; display: block;">
                                <s:iterator value="productimageList" var="productimages">
                                	<li><img width="48" height="48" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }_small${productimages.imageType }" big="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }${productimages.imageType }" /></li>
                                </s:iterator>
                                <div class="hackbox"></div>
                            </ul>
                        </div>
                        <div class="hidden" style="display: none;"></div>
                    </div>
                </div>
			    <div class="Pro_GetPrize">
				    <c:if test="${latestlottery!=null}">
				    	 <h2>上期获得者</h2>
					    <div class="GetPrize">
						    <dl>
							    <dt><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${latestlottery.userId }.html" rel="nofollow"><img width="80" height="80" src="${latestlottery.userFace }" alt="" /></a></dt>
							    <dd class="gray02">
								    <p>恭喜 <a class="blue" target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${latestlottery.userId }.html">${latestlottery.userName }</a> (${latestlottery.location }) 获得了本商品</p>
								    <p>揭晓时间：${latestlottery.announcedTime }</p>
								    <p><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>时间：${latestlottery.buyTime }</p>
								    <p>幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码：<em class="orange Fb">${latestlottery.randomNumber }</em></p>
							    </dd>
						    </dl>
					    </div>
				    </c:if>
			    </div>
		    </div>
		    <div class="Pro_Detright">
			        <p class="Det_money">价值：<span class="rmbgray">${productInfo.productPrice }.00</span></p>
		        <div class="Announced_Frame" id="divLotteryTimer">
				    <div class="Announced_FrameTin">揭晓倒计时</div>
				    <div class="Announced_FrameCode">
					    <div class="Announced_FrameClock">
					    <img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Announced_Clock.gif" />
					    </div>
					    <ul class="Announced_FrameClockMar">
						    <li class="Code_0" id="liMinute1">0<b></b></li>
						    <li class="Code_0" id="liMinute2">0<b></b></li>
						    <li class="Code_Point">:<b></b></li>
						    <li class="Code_0" id="liSecond1">0<b></b></li>
						    <li class="Code_0" id="liSecond2">0<b></b></li>
						    <li class="Code_Point">:<b></b></li>
						    <li class="Code_0" id="liMilliSecond1">0<b></b></li>
						    <li class="Code_0" id="last">0<b></b></li>
					    </ul>
				    </div>
				    <div class="Announced_FrameGet">
					    <p class="Announced_FrameLanguage">
					    	<img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/1.gif" id="imgFunny" />
					    </p>
				    </div>
				    <div class="Announced_FrameBm"></div>
			    </div>
			    <div style="display: none;" class="Announced_Frame" id="divLotteryTiming">
					    <div class="Announced_FrameTin">正在计算</div>
					    <div class="Announced_FrameCal">
						    <p><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Announced_6.png" /></p>
						    <span><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Announced_4.gif" /></span>
					    </div>
				    <div class="Announced_FrameBm"></div>
			    </div>
			        <div class="Security">
				        <ul>
					        <li><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/genuinetwo.html"><i></i>100%公平公正</a></li>
					        <li><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/genuine.html"><s></s>100%正品保证</a></li>
					        <li><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/deliveryFees.html"><b></b>全国免费配送</a></li>
				        </ul>
				        <div class="Det_Share">
				            <!-- JiaThis Button BEGIN -->
                            <div class="jiathis_style">
                                <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt" target="_blank"><img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/jiathis.gif" border="0" /></a>
                            </div>
                            <!-- JiaThis Button END -->
                        </div>
			        </div>
			        <div class="Pro_Record">
				        <ul class="Record_tit" id="ulRecordTab">
					        <li class="NewestRec Record_titCur">最新<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</li>
					        <li class="MytRec">我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</li>
					        <li class="Explain orange">什么是<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>？</li>
				        </ul>
				        <div class="Newest_Con">
				        	<c:choose>
				        		<c:when test="${fn:length(ParticipateJSONList)!=0}">
				        			<ul>
							            <s:iterator var="ParticipateJSON" value="ParticipateJSONList">
							            	<li><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ParticipateJSON.userId }.html" rel="nofollow"><img width="20" height="20" border="0" alt="" src="${ParticipateJSON.userFace }" /></a><a class="blue" target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ParticipateJSON.userId }.html">${ParticipateJSON.userName }</a>(${ParticipateJSON.ip_location }) ${ParticipateJSON.buyDate } <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>了<em class="Fb gray01">${ParticipateJSON.buyCount }</em>人次</li>
							            </s:iterator>
					                </ul>
							        <p style=""><a class="gray01" href="javascript:;" id="btnUserBuyMore">查看更多</a></p>
				        		</c:when>
				        		<c:otherwise>
					             	<p>暂无<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录哦!</p>
				        		</c:otherwise>
				        	</c:choose>
				        </div>
					    <div style="display:none;" class="My_Record">
					        <div class="My_RecordReg">
					        	<b class="gray01">看不到？是不是没登录或是没注册？ 登录后看看</b>
							    <a class="My_Signbut" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/login/index.html?forward=rego">登录</a><a class="My_Regbut" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/register/index.html?forward=rego">注册</a>
						    </div>
						</div>
					    <div style="display:none;" class="Newest_Con">
					        <p class="MsgIntro"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>是指只需1元就有机会买到想要的商品。即每件商品被平分成若干“等份”出售，每份1元，当一件商品所有“等份”售出后，根据<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>规则产生一名幸运者，该幸运者即可获得此商品。</p>
					        <p class="MsgIntro1"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>以“快乐<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>，惊喜无限”为宗旨，力求打造一个100%公平公正、100%正品保障、寄娱乐与购物一体化的新型购物网站。<a target="_blank" class="blue" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/index.html">查看详情&gt;&gt;</a></p>
					    </div>
			        </div>
			    </div>
		    </div>
    <!-- 商品图片结束 -->
    <!-- 商品图片结束 -->
    <div class="ProductTabNav">
	    <div class="DetailsT_Tit" id="divProductNav">
	        <div class="DetailsT_TitP">
		        <ul>
			        <li class="Product_DetT DetailsTCur"><span class="DetailsTCur">计算结果</span></li>
			        <li class="All_RecordT" id="liUserBuyAll"><span class="">所有参与记录</span></li>
			        <li class="Single_ConT"><span class="">晒单</span></li>
		        </ul>
		    </div>
	    </div>
	</div>
	<!-- 商品详细介绍 -->
	<div class="Product_Content" id="divContent" style="display: block;">
		<div style="" class="ygRecord m-detail-main-rule">
			<p style="color: #999;font-family: 'Microsoft Yahei';font-size: 24px;font-weight: bold;line-height: 34px;padding: 15px;">我们有严谨的幸运号码计算规则，保证公平公正公开</p>
			<ul class="txt">
                <li>
                    <span class="title">为什么会进行倒计时？</span>
                    <p>为保证公平公正公开，正等待一小段时间，取最近一期中国福利彩票“时时彩”的开奖结果。 <a style="font-family:simsun" target="_blank" href="http://caipiao.163.com/award/cqssc/">开奖查询&gt;&gt;</a></p>
                </li>
                <li>
                    <span class="title">计算公式</span>
                    <div class="formula">
                        <div class="box red-box"><b class="txt-red">本期幸运号码</b></div><div class="operator">=(</div><div class="box gray-box"><b class="txt-red">${DateSUM}</b><br />
                        50个时间求和
                        </div><div title="相加" class="operator">+</div><div class="box gray-box"><b class="txt-red">?</b><br />“时时彩”开奖号码
                        </div><div title="取余" class="operator">)%</div><div class="box"><b class="txt-red">${productInfo.productPrice }</b><br />该奖品总需人次</div><div title="相加" class="operator">+</div><div class="box"><b class="txt-red">10000001</b><br />原始数</div>
                    </div>
                </li>
            </ul>
            <div class="yghelp">
                1、取该商品最后购买时间前网站所有商品的最后50条购买时间记录
                <br/>
                2、每个时间记录按时、分、秒、毫秒依次排列取数值，如20:15:25.362则为201525362）将这50个时间的数值进行求和（得出数值A）
                <br/>
                3、为保证公平公正公开，系统还会等待一小段时间，取最近下一期中国福利彩票“时时彩”的开奖结果（一个五位数值B）；
                <br/>
                4、（数值A+数值B）除以该商品总参与人次后取余数 + 原始数 10000001，得到最终幸运号码，拥有该幸运号码者，直接获得该商品。
                <br />
                	注：最后一个号码分配时间距离“时时彩”最近下一期开奖大于24小时，默认“时时彩”开奖结果为最后一期开奖号码。
            </div>
            <ul class="Record_title">
                <li class="time"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>时间</li>
                <li class="nem">会员账号</li>
                <li class="name">商品名称</li>
                <li class="much"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>人次</li>
            </ul>
		 <div class="RecordOnehundred">
		 <h4>截止该商品最后购买时间【${startDate }】最后50条全站购买时间记录</h4>
		 <div class="FloatBox"></div>
		 <s:iterator value="lotteryDetailJSONList" var="lotteryDetailJSONs">
			 <ul class="Record_content"><li class="time"><b>${lotteryDetailJSONs.buyDate }</b>${lotteryDetailJSONs.buyTime }</li><li class="timeVal">${lotteryDetailJSONs.dateSum }</li><li class="nem"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${lotteryDetailJSONs.userId }.html" target="_blank" class="gray02">${lotteryDetailJSONs.userName }</a></li>
			 <li class="name"><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${lotteryDetailJSONs.productId }.html" class="gray02">（第${lotteryDetailJSONs.productPeriod }期）${lotteryDetailJSONs.productName }</a></li><li class="much">${lotteryDetailJSONs.buyCount }人次</li></ul>
		 </s:iterator>
		 <div class="ResultBox"><h2>计算结果</h2><p class="num4">求和：<span class="Fb">${DateSUM}</span>(上面50条<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录时间取值相加之和)<br />
		 第 <span class="Fb">?</span>  期 “时时彩”开奖号码：<span class="Fb"><em>?</em></span> <a href="http://caipiao.163.com/award/cqssc/" target="_blank">开奖查询>></a><br />
		 取余：<span class="Fb">(${DateSUM} + ?) % ${productInfo.productPrice }</span> (本商品总需参与人次) = <span class="Fb">?</span> <br/>
		 结果：<span class="Fb">?</span>(余数)+ <span class="Fb">10000001</span> = <span class="Fb"><em>?</em></span><br />
<!--		 取余：<span class="Fb">${DateSUM}</span>(100条时间记录之和)<span class="Fb"> % ${latestlottery.productPrice}</span>(本商品总需参与人次)<span class="Fb"> = ${latestlottery.randomNumber-10000001}</span>(余数)<br />-->
<!--		 结果：<span class="Fb">${latestlottery.randomNumber-10000001}</span>(余数)<span class="Fb"> + 10000001 = <em>${latestlottery.randomNumber }</em></span></p>-->
			<b>最终结果：等待揭晓</b></div>
		 </div>
	</div>
	</div>
	<!-- 商品详细介绍结束 -->
	<!-- 所有参与记录 -->
	<div class="AllRecord_Content" id="divBuyRecord" style="display: none;">
	    <div class="goods_loding" style="display: none;"><img border="0" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/goods_loading.gif" alt="" />正在加载......</div>
	<div id="userByListDIV">
	</div>
	<div class="pages" name="bitem">
	<ul class="pageULEx" id="pagination">
	</ul></div></div>
	<!-- 所有参与记录结束 -->
	<!-- 晒单开始 -->
	<div id="divPost" class="Single_Content">
		<div class="NoConMsg" style="display:none;"><span>暂无晒单记录~！</span></div>
	    <div class="goods_loding" style="display:none;"><img border="0" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/goods_loading.gif"/>正在加载......</div>
	    <div id="shareByListDIV"></div>
	    <div class="pages" name="bitem">
		<ul class="pageULEx2" id="pagination2"></ul>
		</div>
    </div>
    <!-- 晒单结束 -->
	
	<!-- 人气推荐 -->
	<div class="list_goodsTj" id="divGoodsRec">
    <div class="list_goodsTjTit">人气推荐</div>
    <div class="list_goodsTjCon" id="divRecommend">
	    <div class="Roll_Left"><a href="javascript:;" name="left" class="Roll_enabled">&lt;</a></div>
	    <div class="Roll_Con">
	        <ul id="ulRecommend" style="width: 1820px; left: 0px;">
	        </ul>
	    </div>
	    <div class="Roll_Right"><a href="javascript:;" name="right" class="Roll_enabled">&gt;</a></div>
    </div>
</div>
	<!-- 人气推荐结束 -->
    <script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagination.js"></script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/goodsdetail.js"></script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.imagezoom.js"></script>
</body>
</html>