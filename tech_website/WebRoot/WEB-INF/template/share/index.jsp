<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta name="decorator" content="index_template" />
		<title>晒单分享_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/sharelist.css" />
	</head>

	<body class="share">
	<div class="wrapper">
        <!--顶部-->
    <input type="hidden" id="topid" value="${id }" />
    <input name="hidOrderID" type="hidden" id="hidOrderID" value="10" />
    <input name="hidSortID" type="hidden" id="hidSortID" value="0" />
    <input name="hidTotalCount" type="hidden" id="hidTotalCount" value="50506" />
    <input name="hidPageSize" type="hidden" id="hidPageSize" value="40" />
    <!--内容部分-->
        <div class="g-main-con clrfix">
            <div class="w1190">
                <!--导航开始-->
                <div class="g-single-part1 clrfix">
                    <div class="m-single-title">
                        <em class="gray3">晒单分享</em>
                        <s></s>
                        <span class="gray9">共<i class="orange">${resultCount}</i>个幸运获得者晒单</span>
                    </div>
                    <div class="m-single-menu">
                        <ul class="fl">
                            <li id="new" class=""><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/share/new20.html" title="最新">最新</a></li>
                            <li id="elite" class=""><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/share/elite20.html" title="精华">精华</a></li>
                            <li id="comment" class=""><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/share/reply20.html" title="推荐">推荐</a></li>
                            <li id="popularity" class=""><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/share/hot20.html" title="人气">人气</a></li>
                        </ul>
                        <div id="div_category" class="category-feedback fr">
                            <p>
                                <a href="/share/${id}.html"><em>全部分类</em><s></s></a>
                            </p>
                            <div class="classification">
                                <a href="/share/${id}.html" class="hover">全部分类</a>
                                <s:iterator var="protype" value="protypeList">
                                <a href="/share/${id}/<s:property value='#protype.typeId'/>.html" class=""><s:property value='#protype.typeName'/></a>
                                </s:iterator>
                            </div>
                        </div>
                    </div>
                </div>
                <!--导航结束-->
                <!--列表开始-->
                <div id="loadingSinglePic" class="g-single-part2 clrfix">
                <s:iterator value="ShareJSONList" var="ShareJSONs" status="i">
                	<c:choose>
                		<c:when test="${i.index+1==1 || i.index+1==5 || i.index+1==9 || i.index+1==13 || i.index+1==17}">
                		<div class="m-single m-single-special"><div class="single-list"><ul><li class="sig-pic sig-pic-size"><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"><img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/Big/${ShareJSONs.shareImages }" /></a></li><li class="sig-title"><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"><b>${ShareJSONs.shareTitle }</b></a></li><li class="sig-text">${ShareJSONs.shareContent }</li><li class="gray9">${ShareJSONs.shareDate}</li><li class="sig-xmjdh"><span class="xmjdh_left"><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ShareJSONs.userId }.html"><cite class="fl"><img src="${ShareJSONs.userFace }" /><s class="transparent-png"></s></cite><em class="fl">${ShareJSONs.userName}</em></a></span><span class="xmjdh_right"><a href="javascript:;" class="xianmu" num="${ShareJSONs.upCount }" postid="${ShareJSONs.uid }"><i class="transparent-png"></i><em>${ShareJSONs.upCount }</em><img class="transparent-png" src="/Images/xin.png"></a><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html" class="pinglun"><i class="transparent-png"></i><em>${ShareJSONs.replyCount}</em></a></span></li></ul><b class="curr-arrow"><s></s></b></div></div>
                		</c:when>
                		<c:otherwise>
                		<div class="m-single"><div class="single-list"><ul><li class="sig-pic sig-pic-size"><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"><img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/Big/${ShareJSONs.shareImages }" /></a></li><li class="sig-title"><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"><b>${ShareJSONs.shareTitle }</b></a></li><li class="sig-text">${ShareJSONs.shareContent }</li><li class="gray9">${ShareJSONs.shareDate}</li><li class="sig-xmjdh"><span class="xmjdh_left"><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ShareJSONs.userId }.html"><cite class="fl"><img src="${ShareJSONs.userFace }" /><s class="transparent-png"></s></cite><em class="fl">${ShareJSONs.userName}</em></a></span><span class="xmjdh_right"><a href="javascript:;" class="xianmu" num="${ShareJSONs.upCount }" postid="${ShareJSONs.uid }"><i class="transparent-png"></i><em>${ShareJSONs.upCount }</em><img class="transparent-png" src="/Images/xin.png"></a><a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html" class="pinglun"><i class="transparent-png"></i><em>${ShareJSONs.replyCount}</em></a></span></li></ul><b class="curr-arrow"><s></s></b></div></div>
                		</c:otherwise>
                	</c:choose>
                
                </s:iterator>
                </div>
                <!--列表结束-->
                <!--翻页开始-->
                <div class="pages">
					${pageString }
				</div>
                <!--翻页结束-->
            </div>
        </div>
    
    <div class="pageDialogBG" id="pageDialogBG"></div>
    <div class="pageDialogBorder" id="pageDialogBorder"></div>
    <div class="pageDialog" id="pageDialog">
    	<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
    	<div class="pageDialogMain" id="pageDialogMain"> </div>
    </div>
        <!--底部-->
	<script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/postlist.js"></script>
	<script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.scrollloading-min.js"></script>
	</body>
</html>
