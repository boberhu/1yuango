<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta name="decorator" content="index_template" />
	<title>晒单分享_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
	<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/sharedetail.css" />
	<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/simple.css" />
</head>
<body class="share">
    <input name="hidPostID" type="hidden" id="hidPostID" value="${id }" />
    <input name="hidIsLogin" type="hidden" id="hidIsLogin" value="${user.userId }" />
    <input name="hidCodeID" type="hidden" id="hidCodeID" value="${shareInfoPro.productId }" />
    <input name="hidUserFace" type="hidden" id="hidUserFace" value="${user.faceImg }" />
    <input name="hidUserWeb" type="hidden" id="hidUserWeb" value="${user.userId }" />
    <input name="resultCount" type="hidden" id="resultCount" value="${resultCount }" />
    <input name="hidOwTotalCount" type="hidden" id="hidOwTotalCount" value="920" />
    <input name="hidGoodImage" type="hidden" id="hidGoodImage" value="" />
    <!--内容部分-->
    <div class="g-main-con clrfix" id="loadingPicBlock">
        <div class="w1190">
            <div class="m-position">当前位置：<a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>" title="首页">首页</a><span class="f-tran f-tran-prev f-tran-next">&gt;</span><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/share/new20.html" title="晒单列表">晒单列表</a><span class="f-tran f-tran-prev f-tran-next">&gt;</span>晒单详情</div>
            <div class="g-detailCon clrfix">
                <!--左边部分-->
                <div class="m-detail-left clrfix">
                    <!--会员和商品信息宽屏-->
                    <div class="winners-info gray9 clrfix"><ul><li class="win-head"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${shareInfoPro.userId }.html" target="_blank"><img src="${shareInfoPro.winUserFace }"><s class="transparent-png"></s></a></li><li class="win-name"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${shareInfoPro.userId }.html" target="_blank">${shareInfoPro.winUserName }</a></li><li><i>本期<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>：</i><em class="orange f-tran-prev">${shareInfoPro.buyNumberCount}</em>人次</li><li><i>幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码：</i><span class="orange">${shareInfoPro.winRandomNumber }</span></li><li><i>揭晓时间：</i><cite>${shareInfoPro.announcedTime }</cite></li></ul><b class="point-out"><s></s></b></div><div class="next-period"><dl><dt><a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${shareInfoPro.spellbuyProductId }.html" target="_blank"><img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${shareInfoPro.productImg }"></a></dt><dd class="comm-name "><p><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${shareInfoPro.spellbuyProductId }.html" target="_blank">(第${shareInfoPro.productPeriod }期)${shareInfoPro.productName }</a></p></dd><dd class="gray9">商品价值：￥${shareInfoPro.productPrice }.00</dd>
                    <c:if test="${spellbuyproduct.spellbuyProductId!=null}">
                    	<dd class="have-in"><a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${spellbuyproduct.fkProductId }.html" target="_blank">第${spellbuyproduct.productPeriod }期正在进行…</a></dd>
                    </c:if>
                    </dl><span class="publicly-get transparent-png"></span></div>
                    <!--他的其他晒单-->
                        <div class="other-single">
                            <dl>
                                <dt class="gray3"><i>TA</i>的其他晒单</dt>
                            </dl>
                        </div>
                    
                </div>
                <!--中间部分-->
                <div class="m-detail-middle clrfix">
                    <!--会员和商品信息窄屏-->
                    <!--晒单内容-->
                    <div class="single-info clrfix">
                        <dl><dt class="gray3">${shareInfoPro.shareTitle }</dt><dd class="single-fufen"><cite>${shareInfoPro.shareDate }</cite><cite><em>${shareInfoPro.upCount }</em>人羡慕</cite><cite><em id="b_ReplayCount">${shareInfoPro.replyCount }</em>人评论</cite><span class="transparent-png"><b>${shareInfoPro.reward }</b></span></dd><dd class="gray6"><p>${shareInfoPro.shareContent }</p></dd><dd>
                        <s:iterator value="shareimageList" var="shareImages">
	                    <p><img class="scrollLoading" data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/Big/${shareImages.images }" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/goods_loading.gif" /></p>
	                    </s:iterator>
                    </dd></dl>
                    </div>
                    <!--回复总数-->
                    <div class="reply-total clrfix" >
                        <ul>
                            <li class="m-gxhf gray6 fl"><i></i>共<em class="orange" id="em_ReplayCount">${shareInfoPro.replyCount }</em>条评论</li>
                            <li class="m-share fr" id="li_share" style="display:;">
                                <p id="p_xianmu" class="u-xianmu">
                                    <a href="javascript:;">
                                        <i class="transparent-png"></i>
                                        <em>羡慕</em>
                                        <span class="gray9"><cite>${shareInfoPro.upCount }</cite><b><s></s></b>
                                        </span>
                                        <img class="transparent-png" src="/Images/xin.png" alt="" />
                                    </a>
                                </p>
                                <!-- Baidu Button BEGIN -->
                                <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare" ><span class="bds_more"><cite></cite>分享</span></div>
                               <!-- <script type="text/javascript" id="bdshare_js" ></script> 
                                <script type="text/javascript" id="bdshell_js"></script> 
                                <script type="text/javascript">
                                    try {
                                        document.getElementById('bdshell_js').src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date() / 3600000);
                                    } catch (e) { }
                                </script>-->
                                <!-- Baidu Button END -->
                            </li>
                        </ul>
                    </div>
                    <!--晒单评论开始-->
                    <!--输入框-->
                    <div id="bottomComment">
                       
                    </div>
                    <!--评论列表-->
                    <div id="commentList" class="comment-main clrfix" style="display:none;">

                    </div>
                    <!--晒单评论结束-->
                    <div class="pages"><ul id="pagination" class="pageULEx"></ul></div>
                </div>
                <!--右边-->
                <div class="m-detail-right clrfix">
                    <!--其他获得者-->
                    
                        <div class="other-get clrfix">
                            <dl id="dl_otherget">
                                <dt>
                                    <cite class="gray3 fl">其他获得者</cite>
                                    <span class="fr">
                                        <a rel="nofollow" id="otherleft" href="javascript:;"  class="other-left other-left-none"></a><a id="otherright" href="javascript:;" class="other-right"></a>
                                    </span>
                                </dt>
                            </dl>
                        </div>

                    
                    <!--最新晒单-->
                    <div class="new-single">
                        <h4 class="gray3">最新晒单</h4>
                        <ul>
                        <s:iterator value="ShareJSONList" var="ShareJSONs">
                        <li><cite><i></i><em><a  href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"  title="${ShareJSONs.shareTitle }">${ShareJSONs.shareTitle }</a></em></cite><a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html" ><span class="gray9">${ShareJSONs.shareContent }…</span></a><p>
                        <c:forEach items="${ShareJSONs.shareimageList}" var="shareimageLists" begin="0" end="2">
                        <a rel="nofollow" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html" ><img width="72px" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/Small/${shareimageLists.images }" border="0" alt="" /></a>
                        </c:forEach>
                        </p>
                        </li>
                        </s:iterator>
                        </ul>
                    </div>
                </div>
            </div>

        </div>
    </div>

  <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/postdetail.js"></script>
  <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagination.js"></script>
  <script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.scrollloading-min.js"></script>
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
