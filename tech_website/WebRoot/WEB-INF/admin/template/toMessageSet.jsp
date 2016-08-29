<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.tech.util.ApplicationListenerImpl"%>
<%@page import="com.tech.pojo.Admin"%>
<%@page import="com.tech.util.Struts2Utils"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<title>1元夺宝_管理中心</title>
	<link href="/admin_css/global.css"  rel="stylesheet" type="text/css"/>
	<link href="/admin_css/admin_style.css"  rel="stylesheet" type="text/css"/>
	<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
	<style>
.on{color:#fff;background:#0c0;padding:3px 6px;}
.off{color:#f00;background:#eee;padding:3px 6px;}
.msgs{float:left; overflow:hidden; width:100%;}
table.meg_table{border:10px solid #d5dfe8; width:650px; height:350px; float:left; display:inline-block;padding:10px;}
table.meg_table tr { display:block;width:460px;}
</style>
  </head>
  
  <body>
<div class="header-title lr10"><b>系统短信设置</b><span class="lr10"> | </span><b><a onclick="mobile_check();" href="javascript:;">测试短信发送</a></b></div>
<div class="bk10"></div>
<div class="table_form lr10 msgs">

<form id="myform" method="post" action="/admin_back/doSeoSet.action">
<table width="100%" class="meg_table">
	<tbody><tr>
    	<td width="100px;"><b>上海创明</b><em> </em></td> 
   		<td class="mobile_on_off" key="chuangming"><span class="on"></span></td>
    </tr>    
    <tr>
    	<td width="100px;">短信接口帐号：</td> 
   		<td><input type="text" value="${sysConfigure.messagePartner }" class="input-text wid300" name="sysConfigure.messagePartner" /></td>
    </tr>
    <tr>
    	<td width="100px;">短信接口密钥：</td> 
   		<td>
   		<%
		if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
		%>
   		<input type="password" value="${sysConfigure.messageKey }" class="input-text wid300" name="sysConfigure.messageKey" />
   		<%
		}else{
		%>
		<input type="password" value="密钥已加密" class="input-text wid300" name="sysConfigure.messageKey" />
		<%
		}
		%>
   		</td>
    </tr>
    <tr>
    	<td width="100px;">短信通道编号：</td> 
   		<td><input type="text" value="${sysConfigure.messageChannel }" class="input-text wid300" onkeyup="value=value.replace(/\D/g,'')" name="sysConfigure.messageChannel" /></td>
    </tr>
     <tr>
    	<td width="100px;">短信签名编号：</td> 
   		<td><input type="text" value="${sysConfigure.messageSign }" class="input-text wid300" onkeyup="value=value.replace(/\D/g,'')" name="sysConfigure.messageSign" /></td>
    </tr>
	 <tr>
	 <td width="100px;">短信信息：</td>
	 <td>
     <span><b style="color:#0c0">短信功能正常</b></span> &nbsp;&nbsp;&nbsp;&nbsp;<b style="color:#f60">${message }</b>
     </td>
     </tr>
     
    <tr>
    	<td width="100px;"><input type="hidden" value="chuangming" name="sysConfigure.smsType"/></td> 
   		<td> <input type="submit" class="button" name="dosubmit" value=" 提交并启用该接口 "/></td>
    </tr>
     <tr>
    	<td width="100px;" style="border:0px;"></td> 
   		<td style="border:0px;color: red;">注意：以上参数必须填写，不能填写空值保存。</td>
     </tr> 
    </tbody></table> 
</form>

<form id="myform2" method="post" action="/admin_back/doSeoSet.action">
<table width="100%" class="meg_table" style="margin-top:5px;">
	<tbody><tr>
    	<td width="100px;"><b>短信宝</b><em> </em></td> 
   		<td class="mobile_on_off" key="smsbao"><span class="on"></span></td>
    </tr>    
    <tr>
    	<td width="100px;">短信接口用户名：</td> 
   		<td><input type="text" value="${sysConfigure.smsbaoUsername }" class="input-text wid300" name="sysConfigure.smsbaoUsername" /></td>
    </tr>
    <tr>
    	<td width="100px;">短信接口密码：</td> 
   		<td>
   		<%
		if(((Admin)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
		%>
   		<input type="password" value="${sysConfigure.smsbaoPass }" class="input-text wid300" name="sysConfigure.smsbaoPass" />
   		<%
		}else{
		%>
		<input type="password" value="密钥已加密" class="input-text wid300" name="sysConfigure.smsbaoPass" />
		<%
		}
		%>
   		</td>
    </tr>
	 <tr>
	 <td width="100px;">短信信息：</td>
	 <td>
     <span><b style="color:#0c0">短信功能正常</b></span> &nbsp;&nbsp;&nbsp;&nbsp;<b style="color:#f60">${message2 }</b>
     </td>
     </tr>
     
    <tr>
    	<td width="100px;"><input type="hidden" value="smsbao" name="sysConfigure.smsType"/></td> 
   		<td> <input type="submit" class="button" name="dosubmit" value=" 提交并启用该接口 "/></td>
    </tr>
     <tr>
    	<td width="100px;" style="border:0px;"></td> 
   		<td style="border:0px;color: red;">注意：以上参数必须填写，不能填写空值保存。</td>
     </tr> 
    </tbody></table> 
    </form>
</div>
<div class="bk10"></div>
<div class="header-title lr10">
	<b>短信模板配置</b><span style=" padding-left:30px;"></span>
</div>
<div class="bk10"></div>
<div class="table_form lr10">
<form id="myform_tpl" method="post" action="/admin_back/doSeoSet.action">
<table width="100%" class="lr10">
    <tbody><tr>
    	<td width="150">用户短信验证模板：</td> 
   		<td><textarea class="input-text" style=" height:50px; width:450px" name="sysConfigure.verifyMsgTpl">${sysConfigure.verifyMsgTpl }</textarea>
        <font color="red">{000000}</font> 为发送验证码！请不要修改,请不要超过75个字,超过按照2条短信发送</td>
    </tr>
    <tr>
    	<td width="150">用户获奖通知模板：</td> 
   		<td><textarea class="input-text" style=" height:50px; width:450px" name="sysConfigure.lotteryMsgTpl">${sysConfigure.lotteryMsgTpl }</textarea>
        </td>
    </tr>
     <tr>
   	<td width="100"></td>
  		<td> <input type="submit" class="button" name="dosubmit" value=" 提交 " /></td>
  	 </tr>
</tbody></table>
</form>
</div>

<style>
#paywindow{position:absolute;z-index:999; display:none}
#paywindow_b{width:372px;height:442px;background:#2a8aba; filter:alpha(opacity=60);opacity: 0.6;position:absolute;left:0px;top:0px; display:block}
#paywindow_c{width:360px;height:430px;background:#fff;display:block;position:absolute;left:6px;top:6px;}
.p_win_title{ line-height:40px;height:40px;background:#f8f8f8;}
.p_win_title b{float:left}
.p_win_title a{float:right;padding:0px 10px;color:#f60}
.p_win_content h1{font-size:25px;font-weight:bold;}
.p_win_but,.p_win_mes,.p_win_ctitle,.p_win_text{ margin:20px 20px;}
.p_win_ctitle{overflow:hidden;}
.p_win_x_b{float:left; width:73px;height:68px;background-repeat:no-repeat;}
.p_win_x_t{ font-size:18px; font-weight:bold;font-family: "Helvetica Neue",\5FAE\8F6F\96C5\9ED1,Tohoma;color:#f00; text-align:center}
.p_win_but{ height:40px; line-height:40px;}
.p_win_but a{ padding:8px 15px; background:#f60; color:#fff;border:1px solid #f50; margin:0px 15px;font-family: "Helvetica Neue",\5FAE\8F6F\96C5\9ED1,Tohoma; font-size:15px; }
.p_win_but a:hover{ background:#f50}
.p_win_text a{ font-size:13px; color:#f60}
.pay_window_quit:hover{ color:#f00;}
</style>
<div id="paywindow" style="left: 670px; top: 248.5px; display: none;">
	<div id="paywindow_b"></div>
	<div id="paywindow_c">
		<div class="p_win_title"><a class="pay_window_quit" href="javascript:;">[关闭]</a><b></b></div>
		<div class="p_win_content">
			<div class="p_win_ctitle">			
				<li class="p_win_x_t">系统短信测试</li>
			</div>
			<div class="p_win_mes">
            	 手机号码：<input type="text" value="" style="width:200px;" class="input-text" id="ceshi_haoma" />    		
            </div>
            <div class="p_win_mes">             	    	
                 	测试内容：<textarea style="width:200px; height:150px;" id="ceshi_con"></textarea>
                 	<div class="bk10"></div>
    			 	<input type="button" id="ceshi_form" class="button" value="测试短信功能与内容" />              
            </div>	
		</div>
	</div>
</div>
<script>
$(function(){
	var width = ($(window).width()-372)/2;
	var height = ($(window).height()-442)/2;
	$("#paywindow").css("left",width);
	$("#paywindow").css("top",height);
		
	$(".pay_window_quit").click(function(){
		$("#paywindow").hide();								 
	});

	var mobile = '${sysConfigure.smsType }';
	
	$("td.mobile_on_off").each(function(i){
		if($(this).attr("key") == mobile){
			$(this).html("<span class=\"on\">开启中...<span>");
		}else{
			$(this).html("<span class=\"off\">关闭中...<span>");
		}
	});
	
});
</script>
<script type="text/javascript">
function mobile_check(){
	$("#paywindow").show();
	return true;
}

$("#ceshi_form").click(function(){
		$.ajaxSetup({
			async : false
		});	
		var ceshi_haoma=document.getElementById('ceshi_haoma').value;
		var ceshi_con=document.getElementById('ceshi_con').value;	
		if(ceshi_haoma==""){
			alert("手机号码不能为空!");
			return false;
		}
		if(ceshi_con == ''){
			alert("内容不能为空!");
			return false;
		}
		$.post("/admin_back/testMessage.action",{"id":ceshi_haoma,"message":ceshi_con,"ceshi_submit":true},function(data){
			//data = jQuery.parseJSON(data);	
			if(data!=null){
				window.parent.message(data,1,2);
			}else{
				window.parent.message(data,1,2);
			}
		});			
});   
		 
/*
	设置TABLE样式
*/
$(function(){
	/* $(".meg_table").each(function(i){		
			if(i%2!=0){
				$(this).css("border-left","none");				
			}
			if(i==0 || i==1){
				$(this).css("border-top","10px solid #d5dfe8");
			}
	 }); */
});
</script>
 </body>
</html>
