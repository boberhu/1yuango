<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%@ include file="/root/url.jsp" %>
  <head>
    <meta name="decorator" content="index_template" />
    <title>${productInfo.productName }${productInfo.productTitle }_1元夺宝 一元夺宝 夺宝网</title>
    <link type="text/css" rel="stylesheet" href="<%=url.getString("skin")%>/css/goodsdetail.css" />
    
    <style type="text/css">
ul,li{padding:0; margin:0;}
li{list-style-type:none;}
img{vertical-align:top;border:0;}

.tb-pic a {
    display:table-cell;
    text-align:center;
    vertical-align:middle;
}
.tb-pic a img {
    vertical-align:middle;
}

.tb-pic a{
    *display:block;
	*font-family:Arial;
	*line-height:1;
}

.tb-thumb {
    margin:10px 0 0;
	overflow:hidden;
}

.tb-thumb li {
    background:none repeat scroll 0 0 transparent;
	float:left;
	height:42px;
	margin:0 6px 0 0;
	overflow:hidden;
	padding:1px;
}

.tb-s310, .tb-s310 a {
    height:250px;
    width:250px;
}

.tb-s310, .tb-s310 img {
    max-height:250px;
    max-width:250px;
}

.tb-s310 a{
    *font-size:271px;
}

.tb-s40 a{
    *font-size:35px;
}

.tb-s40, .tb-s40 a {
    height:40px;
	width:40px;
}

.tb-booth {
    border:1px solid #CDCDCD;
	position:relative;
	z-index:1;
}

.tb-thumb .tb-selected {
    background:none repeat scroll 0 0 #C30008;
	height:40px;
	padding:2px;
}

.tb-thumb .tb-selected div {
    background-color:#FFFFFF;
	border:medium none;
}

.tb-thumb li div {
    border:1px solid #CDCDCD;
}

div.zoomDiv {
    z-index:999;
	position: absolute;
	top:0px;
	left:0px;
	width:200px;
	height:200px;
	background: #ffffff;
	border:1px solid #CCCCCC;
	display:none;
	text-align: center;
	overflow: hidden;
}
div.zoomMask {
    position:absolute;
    background:url("<%=url.getString("img")%>/Images/mask.png") repeat scroll 0 0 transparent;
	cursor:move;
	z-index:1;
}

</style>
  </head>
<body>
 <div class="detail-page" id="loadingPicBlock">
        <div class="detail-itemsummary">
            <div class="detail-summary-head">
                <h1 class="detail-pro-title">
                    <span>(第 ${productInfo.productPeriod } 期)</span>${productInfo.productName }<span>${productInfo.productTitle }</span></h1>
                <div id="LookHistoryBout" class="detail-summary-historybuy">
                    <div class="dsh-none-click">
                        本商品往期夺宝</div>
                    <div id="historyBout" class="dsh-click" style="display: none;">
                    </div>
                </div>
                <div class="hackbox">
                </div>
            </div>
            <div class="detail-itemsummary-info">
                <div class="detail-itemsummary-imageviewer">
                    <div class="middlePicRemark" id="middlePicRemark">
                    </div>
                    <div class="tb-booth tb-pic tb-s310">
                    <a><img src="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/01_mid.jpg" alt="" rel="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/01.jpg" class="jqzoom"/></a>
                    </div>
                    <div class="smallPicList">
                    	 <div class="jcarousel-prev jcarousel-prev-disabled">
                        </div>
                        <div class="jcarousel-clip">
                            <ul id="thumblist" style="width: 348px; left: 0px; display: block;">
                              <li>
								 <div class="tb-pic tb-s40">
									<a href="javascript:;"><img src="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/01_small.jpg" mid="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/01_mid.jpg" big="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/01.jpg" /></a>
								</div>
							  </li>
							  <li>
								 <div class="tb-pic tb-s40">
									<a href="javascript:;"><img src="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/02_small.jpg" mid="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/02_mid.jpg" big="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/02.jpg" /></a>
								</div>
							  </li>
							  <li>
								 <div class="tb-pic tb-s40">
									<a href="javascript:;"><img src="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/03_small.jpg" mid="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/03_mid.jpg" big="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/03.jpg" /></a>
								</div>
							  </li>
							  <li>
								 <div class="tb-pic tb-s40">
									<a href="javascript:;"><img src="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/04_small.jpg" mid="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/04_mid.jpg" big="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/04.jpg" /></a>
								</div>
							  </li>
							  <li>
								 <div class="tb-pic tb-s40">
									<a href="javascript:;"><img src="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/05_small.jpg" mid="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/05_mid.jpg" big="<%=url.getString("img")%>/productImg/imagezoom/${productInfo.spellbuyProductId  }/05.jpg" /></a>
								</div>
							  </li>
                            <div class="hackbox"></div>
                            </ul>
                        </div>
                        <div class="jcarousel-next"></div>
                    </div>
                    <div class="bdshare_t bds_tools get-codes-bdshare" id="bdshare">
                        <div style="float: left; line-height: 30px;">
                            分享到：</div>
                         <a class="bds_qzone"></a><a class="bds_tsina"></a><a class="bds_tqq"></a><a class="bds_renren"></a><a class="bds_t163"></a><a class="bds_kaixin001"></a><a class="bds_taobao"></a><span class="bds_more">更多</span>
                    </div>
                     <script type="text/javascript" id="bdshare_js" data="type=tools" ></script>
						<script type="text/javascript" id="bdshell_js"></script>
						<script type="text/javascript">
							document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + new Date().getHours();
						</script>
                </div>
                <div class="detail-pro-property">
                    <ul>
                        <li class="barcode"><span>商品编码：</span><label id="codeid">${productInfo.spellbuyProductId }</label></li>
                        <li class="price"><span>市场参考价：</span><span class="rmb">${productInfo.productPrice }.00</span></li>
                        <li class="has-attended">已有<span>${productInfo.spellbuyCount }</span>人次参与</li>
                        <li class="needall-attended">总需<span id="people">${productInfo.productPrice }</span>人次参与</li>
                        <c:choose>
                        	<c:when test="${productInfo.status==0}">
                        		<li class="progress-bar"><em>进度</em><p title="已完成${fn:replace( fn:substring((productInfo.spellbuyCount/productInfo.productPrice)*100, 0, 2),'.','' )} %"><span style="width:${fn:replace( fn:substring((productInfo.spellbuyCount/productInfo.productPrice)*100, 0, 2),'.','' )}%"></span></p></li>
                        		<li class="buybtn"><a class="btn" id="hylinkGoShop"><span>立即夺宝</span></a></li>
                        	</c:when>
                        	<c:otherwise>
                        		<li id="liLotteryed">
                                <div class="publish">
                                    <ul class="win">
                                        <li class="title">恭喜<span>1380***5746</span>获得该商品！幸运夺宝码为：</li>
                                        <li class="timebg"><span class="pdo">1</span><span>0</span><span>0</span><span class="pdf">0</span><span class="pd">0</span><span>0</span><span>0</span><span>9</span></li>
                                        <li class="count"><a class="look" href="<%=url.getString("server")%>/lotteryDetail/${productInfo.spellbuyProductId }.html#calculate">查看详细计算结果</a><a class="how" target="_blank" href="<%=url.getString("help")%>/help/paigouRule.html">幸运夺宝码计算方法</a></li>
                                    </ul>
                                </div>
                            	</li>
                        	</c:otherwise>
                        </c:choose>
                    </ul>
                    <input type="hidden" id="productId" value="${productInfo.spellbuyProductId  }"/>
                </div>
                <div class="detail-itemsummary-participate">
                    <h3>
                        最新参与记录</h3>
                            <ul>
                            <s:iterator var="ParticipateJSON" value="ParticipateJSONList">
	                            <li><span>${ParticipateJSON.userName}</span>刚购买了<em>${ParticipateJSON.buyCount }</em>个夺宝编号</li>
                            </s:iterator>
                            </ul>
                    		<ul>
                        <li class="more"><a href="javascript:gotoClick();" id="linkLookBuyInfo">查看所有夺宝记录</a></li>
                    </ul>
                </div>
            </div>
            <div class="hackbox">
            </div>
        </div>
        <div class="hackbox">
        </div>
        <div class="detail-info" id="con">
            <div class="detail-info-menu">
                <ul>
                    <li class="menu-on" id="id_boxmenu_1"><a href="javascript:void(0)" onclick="javascript:showDetailBox(1);">商品详情</a> </li>
                    <li id="id_boxmenu_2" class=""><a href="javascript:void(0)" onclick="javascript:showDetailBox(2);">所有参与记录</a> </li>
                    <li id="id_boxmenu_3" class=""><a href="javascript:void(0)" onclick="javascript:showDetailBox(3);">夺宝规则</a> </li>
                </ul>
            </div>
            <div class="detail-info-main">
                <div id="id_boxcontents_1" class="pro-main">
                ${productInfo.productDetail }
                </div>
                <div id="id_boxcontents_2" class="pro-mainH">
                <input type="hidden" id="resultCount" value="${resultCount }"/>
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
				<thead>
				<tr><th width="130" class="t-id">会员账号</th><th class="t-ip">IP地址</th><th width="90" class="t-numb">夺宝人次</th><th width="150" class="t-time">夺宝时间</th></tr>
				</thead>
				<tbody id="ParticipateTbody">
				</tbody>
				<tfoot>
				<tr>
				<td colspan="4" class="page-nave">
				<div class="page_nav">
				<ul class="pageUL" id="pagination">
				</ul>
				</div></td></tr></tfoot></table></div>
                <div id="id_boxcontents_3" class="pro-mainH">
                    <div class="what">
                        <div class="what-title">什么是1元夺宝</div>
                        <div class="kuang">
                            <b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
                            <div class="xboxcontent">
                                1元夺宝是指只需1元就有机会买到想要的商品。即每件商品被平分成若干“等份”出售，每份1元，当一件商品所有“等份”售出后，根据夺宝规则产生一名幸运者，该幸运者即可获得此商品。<br/>
                                1元夺宝（www.1ypg.com）是一种全新的互动购物体验方式，是时尚、潮流的风向标，能满足个性、年轻消费者的购物需求，由深圳市中利保投资管理有限公司注入巨资打造的新型购物网。<br/>
                                1元夺宝以“快乐夺宝，惊喜无限”为宗旨，力求打造一个100%公平公正、100%正品保障、寄娱乐与购物一体化的新型购物网站。 随着互联网的发展及网购消费模式的多样化，1元夺宝势必成为中国电子商务网站中最具活力的生力军及网民最爱的购物网，必将迅速崛起，成为互联网新时代的佼佼者！
                            </div>
                            <b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
                        </div>
                        <div class="what-title">1元夺宝规则</div>
                        <div class="kuang">
                            <b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
                            <div class="xboxcontent">
                                <p>
                                    1、每件商品参考市场价平分成相应“等份”，每份1元，1元对应1个夺宝码（1份=1元=1个夺宝码）。<br/>
                                    2、同一件商品可以购买多次或一次购买多份。<br/>
                                    3、系统在该商品所有“等份”售出后计算出“幸运夺宝码 ”，拥有“幸运夺宝码”者即可获得此商品。<br/>
                                    4、所有商品全国免费配送（港澳台地区除外）。<br/>
                                    5、商品获得者揭晓时间：该商品最后一条夺宝记录时间加上10分钟。<br/>
                                    6、幸运夺宝码计算方法：<br/>
                                    &nbsp;&nbsp;&nbsp;&nbsp;1)  截止揭晓时间最后100条全站夺宝时间记录。<br/>
                                    &nbsp;&nbsp;&nbsp;&nbsp;2)  每个时间记录按时、分、秒、毫秒依次排列取数值。(如：2012-03-20 21:49:18.25 则取值为 21491825)<br/>
                                    &nbsp;&nbsp;&nbsp;&nbsp;3)  将这100个数值之和除以该商品总需参与人次后取余数，余数加上10,000,001即为“幸运夺宝码”。
                                    </p>                                
                            </div>
                            <b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
                        </div>
                        <div class="what-title">1元夺宝流程</div>
                        <div class="kuang">
                            <b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
                            <div class="xboxcontent">
                                <div class="liucheng_pic">
                                    <ul class="yg-flow">
                                        <li class="select">选择您喜欢的商品</li>
                                        <li class="pay">支付1元获得1个夺宝编号</li>
                                        <li class="lotterys">达到指定参与人次，揭晓商品获得者</li>
                                        <li class="share">成功晒单，奖励10元</li>
                                    </ul>
                                </div>
                                <p>
                                    <strong></strong>
                                    <br/>
                                </p>
                                <p>
                                    <strong>1、挑选商品</strong><br/>
                                    分类浏览或直接搜索商品，点击“立即1元夺宝”。</p>
                                <p>
                                    <strong>2、支付1元</strong><br/>
                                    选择在线支付方式，支付1元获得一个“夺宝码”。同一件商品可购买多次或一次购买多份，购买的“夺宝码”越多，获得商品的几率越大。</p>
                                <p>
                                    <strong>3、揭晓获得者</strong><br/>
                                    当一件商品所有“等份”售出后，1元夺宝根据“夺宝规则”公布夺宝结果，并且会通过手机短信或邮件通知商品获得者。<br/>
                                    注：<br/>
                                    &nbsp;&nbsp;&nbsp;&nbsp;1、商品揭晓后您可登录“我的1元夺宝”查询详情，未获得商品的用户不会收到短信或邮件通知；<br/>
                                    &nbsp;&nbsp;&nbsp;&nbsp;2、商品揭晓后，请及时登录“我的1元夺宝”完善个人资料，以便我们能够准确无误地为您配送商品。</p>
                                <p>
                                    <strong>4、晒单分享</strong><br/>
                                    晒出您收到商品的实物照片和您的靓照，说出您的夺宝心得，让大家一起分享您的快乐。<br/>
                                    通过审核的晒单可获得10元现金奖励。</p>
                            </div>
                            <b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="detail-info">
            <div class="willstart">
                <h3>
                    <span>即将满员的商品</span></h3>
                <div class="willstart-list">
                </div>
                <div class="hackbox">
                </div>
            </div>
        </div>
        <div class="detail-info">
            <div class="share-list">
                <h3>
                    <span>最新晒单</span></h3>
                <div class="share-list-wrap">
   			 <ul></ul>
	    <div class="hackbox"></div>
                </div>
            </div>
        </div>
    </div>
    <link type="text/css" rel="stylesheet" href="<%=url.getString("img")%>/css/pageDialog.css" />
    <script type="text/javascript" src="<%=url.getString("img")%>/js/pagination.js"></script>
    <script language="javascript" type="text/javascript" src="<%=url.getString("img")%>/js/goodsdetail.js"></script>
    <script language="javascript" type="text/javascript" src="<%=url.getString("img")%>/js/jquery.imagezoom.js"></script>
    <script language="javascript" type="text/javascript" src="<%=url.getString("img")%>/js/pagedialog.js"></script>
    <div class="pageDialogBG" id="pageDialogBG"></div>
	<div class="pageDialogBorder" id="pageDialogBorder"></div>
	<div class="pageDialog" id="pageDialog">
		<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
		<div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
	</div>
</body>
</html>
