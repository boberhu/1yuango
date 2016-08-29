<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="template_footer" />
    <title>购物车_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/cartlist.css" />
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/payjcb.css" />
     <style>
.ui-dialog {
	height: auto;
}
.ui-fm-item{
	height:120px;
}
#tips{
	display:none;
}
.ui-button-waiting,.ui-button-waiting:hover{
	background: #989898;
}

.d-top {
    background: rgba(0, 0, 0, 0) url("/Images/bank_logo.png") no-repeat scroll 618px -500px;
    border-bottom: 2px solid #eee;
    color: #000;
    font-size: 14px;
    font-weight: bold;
    height: 20px;
    overflow: hidden;
    padding: 12px;
    position: relative;
}


</style>
  </head>
  
  <body>
   <div class="ui-dialog">
	<div class="d-top" style=""><span class="title">财付通付款</span><a id="closeLayer" class="close closeLayer" href="javascript:;">×</a></div>
	<div class="d-cnt">
		<div class="order-header fn-clear">
			<div class="order-spending alignr pr20">
				<span class="icon-spending">
					<h3 class="ft-12">订单金额（元）</h3>
					<span id="spending" class="ft-red ft-18 fm-yahei fb mr10">${moneyCount }.00</span>
					<span></span>
				</span>
			</div>
			<ul class="order-detail">
				<li><span>您正在使用“财付通”进行交易</span></li>
				<li><strong class="ft-14">商品名称：<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></strong></li>
				<li>交易时间：${currTime }</li>
			</ul>
		</div>
		<div class="ptb40">
				<input name="optEmail" id="optEmail"  value="<%=ApplicationListenerImpl.sysConfigureJson.getAliPayUser()%>"  type="hidden" />
				<input name="payAmount" id="payAmount" value="${moneyCount }" type="hidden" />
				<input name="title" id="title" value="${out_trade_no }" type="hidden" />
				<input name="memo" id="memo" value="请不要修改付款金额和说明内容，以免影响系统订单处理。" type="hidden" />
			<form name="payForm" action="https://www.tenpay.com/v2/account/pay/paymore_cft.shtml?data=<%=ApplicationListenerImpl.sysConfigureJson.getTenPayUser()%>%26${moneyCount }%26${out_trade_no }&validate=${id }" method="post"  target="_blank" id="msgPayForm" class="msg-pay-form">
				<div class="ui-fm-item">
					<div class="item-div">
						<strong>支付过程中请不要修改[付款金额]和[付款说明]，以免造成订单处理失败。</strong><br />
						如有问题请联系<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>客服，客服电话：<%=ApplicationListenerImpl.sysConfigureJson.getServiceTel()%> 客服QQ：<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq()%><br />
						<span id="tips">如果付款失败，请点击返回重新发起付款。付款完成后请稍作等待，系统处理订单后将自动关闭该页面。</span>
					</div>
				</div>

				<div class="item-txt">
					<a class="ui-button-mred J-formSubmit J-forRisk" id="submit">继续付款</a>
					<a class="ui-button-mwhite J-formSubmit J-forRisk" id="cancel" style="margin-left:20px;">返回</a>
				</div>
			</form>
		</div>
	</div>
</div>
<input type="hidden" id="payName" name="payName" value="${payName }" />
<div class="divcover" style="z-index: 998; width: 100%; height: 100%;"></div>
<!--	<script>document.payForm.submit();</script>-->

<script>

$(function(){

    var posTimmer;
	var $win = $(window);
	var $dialog = $('.ui-dialog');
	$win.resize(function(){
        posTimmer && clearTimeout(posTimmer);
		posTimmer = setTimeout(function(){
			var winHeight = $win.height();
			var height = $dialog.height();
			if (height >= winHeight) {
				$dialog.css('margin-top', '0px');
			} else {
				$dialog.css('margin-top', (winHeight - height) / 2);
			}
			$dialog.css('visibility', 'visible');
        }, 300);
	}).trigger('resize');

	var $submit = $('#submit');
	$submit.click(function(){
		$submit.text('正在等待付款，请不要刷新该页面...').addClass('ui-button-waiting');
		$submit.unbind('click');
		$('#tips').show().css('color','red');

		setInterval(function(){
		  $.ajax({
			url:"/alipay/noKeyRechargeReturn.html",
			type: "post",
			data: {tradeNo:$("#title").val(),payName:$("#payName").val()},
			success: function(d){
				if(d == "success" ){
					$submit.text('付款成功');
					setTimeout(function(){
						if ( 0 ) {
							location.replace("/user/UserBalance.html");
						} else {
							if (window.opener) {
								window.opener.location.href = "/user/UserBalance.html";
								window.close();
							} else {
								location.replace("/user/UserBalance.html");
							}
						}
					},1000);
				}else if(d=="buySuccess"){
					location.replace("/mycart/shopok.html?id="+$("#title").val());
				}
			}
		  });
		},3000);
		$('#msgPayForm').submit();
	});

	$('#closeLayer,#cancel').click(function(){
		if ( 0 ) {
			history.go(-1);
		} else {
			if (window.opener) {
				window.close();
			} else {
				location.replace("/user/UserBalance.html");
			}
		}
	});
});


</script>
</body>
</html>
