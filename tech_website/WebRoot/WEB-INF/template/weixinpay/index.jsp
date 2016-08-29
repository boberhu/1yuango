<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="template_footer" />
    <title>购物车_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/weixinpay.css" />
    <script language="javascript" type="text/javascript">
        $(function () { var e = $("#qr_box"); var c = $("#guide"); c.css({ left: "50%", opacity: 0 }); e.hover(function () { c.css("display", "block").stop().animate({ marginLeft: "+156px", opacity: 1 }, 900, "swing", function () { c.animate({ marginLeft: "+143px" }, 300) }) }, function () { c.stop().animate({ marginLeft: "-101px", opacity: 0 }, "400", "swing", function () { c.hide() }) }); 
        var d = "${out_trade_no}", b = 0, toh = null, bto = 3000, next = true;
        toh = setTimeout(poll, bto);
        function poll(){
          b++;
          $.ajax({
            url:"/weixinpay/poll.action?out_trade_no="+d,
            type: "GET",
            dataType: "json",
            success: function(res){
              // res: {ps, tt}
              var rurl;
              switch(res.ps){
                case 0:
                  break;
                case 1:
                case 3:
                  next = false;
                  if(res.tt == 1){
                    rurl = "/user/UserBalance.action";
                  }else if(res.ps == 3){
                    return;
                  }else{
                    rurl = "/mycart/shopok.action?id="+d;
                  }
                  location.href = rurl;
                  break;
                case 2:
                default:
                  next = false;
                  return;
              }
            },
            complete: function(){
              var delta;
              if(next){
                delta = parseInt(b/20) * 1000;
                toh = setTimeout(poll, bto+delta);
              }else{
                if(toh != null){
                   clearTimeout(toh);
                   toh = null;
                }
              }
            }
          });
        }
        });
    </script>
  </head>
  
  <body>
 	<div class="wx_header">
        <div class="wx_logo">
            <img src="/Images/wxlogo_pay.png" alt="微信支付标志" title="微信支付"></div>
    </div>
    <div class="weixin">
        <div class="weixin2">
            <b class="wx_box_corner left pngFix"></b><b class="wx_box_corner right pngFix"></b>
            <div class="wx_box pngFix">
                <div class="wx_box_area">
                    <div class="pay_box qr_default">
                        <div class="area_bd">
                        <span id="qr_box" class="wx_img_wrapper">
                        <s:if test="%{orderError == null}">
                            <img style="border-width:0px;" alt="二维码" src="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/uploadImages/${out_trade_no }.png" class="qrcode" id="payQRImg" />
                            <img id="guide" alt="" src="/Images/wxwebpay_guide.png" class="guide pngFix" style="left: 50%; opacity: 0; display: none; margin-left: -101px;" />
                        </s:if>
                        <s:else>
                        	<p>
                        		<span style="color:red;font-weight:bold">消息提示：</span><span>
                        		<s:property value="orderError"/></span>
                        	</p>
                        </s:else>
                        </span>
                        <s:if test="%{orderError == null}">
                            <div class="msg_default_box"><i class="icon_wx pngFix"></i>
                                <p>
                                	请使用微信扫描<br />
                                                                                                            二维码以完成支付
                                </p>
                            </div>
                        </s:if>
                            <div class="msg_box"><i class="icon_wx pngFix"></i>
                                <p><strong>扫描成功</strong>请在手机确认支付</p>
                            </div>
                        </div>
                    </div>
                </div>
                <s:if test="%{orderError == null}">
                <div class="wx_hd">
                    <div class="wx_hd_img icon_wx"></div>
                </div>
                <div class="wx_money"><span>￥</span>${moneyCount }.00</div>
                <!--支付订单号-->
                <div class="wx_pay">
                    <p><span class="wx_left">支付订单号</span><span class="wx_right">${out_trade_no}</span></p>
                </div>
                </s:if>
                <div class="wx_kf">
                    <div class="wx_kf_img icon_wx"></div>
                    <div class="wx_kf_wz">
                        <p>客服</p>
                        <p><%=ApplicationListenerImpl.sysConfigureJson.getServiceTel()%></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css" />
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/cartlist.js"></script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js"></script>
    <div class="pageDialogBG" id="pageDialogBG"></div>
	<div class="pageDialogBorder" id="pageDialogBorder"></div>
	<div class="pageDialog" id="pageDialog">
		<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
		<div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
	</div>
</body>
</html>
