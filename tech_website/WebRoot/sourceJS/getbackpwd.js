GetBackPwd = {
	loginload:function () {
		function isDigit(B) {
			var A = /^[0-9]{6}$/;
			if (!A.exec(B)) {
				return false;
			}
			return true;
		}
		function ckEmail(A) {
			var B = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			if (B.test(A)) {
				return true;
			}
			return false;
		}
		function ckPasswd(B) {
			var A = /^[^ \f\n\r\t\v]{6,20}$/;
			if (!A.exec(B)) {
				return false;
			}
			return true;
		}
		function ckMobile(B) {
			var A = /^1\d{10}$/;
			if (!A.exec(B)) {
				return false;
			} else {
				return true;
			}
		}
		function showErrMsg(A, B) {
			$.PageDialog("<h2 class='error'>" + A + "</h2>", {W:320, H:80, submit:function () {
				B.focus();
			}});
		}
		
		var m = $("#mobileReg");
		var e = $("#emailReg");
		var uM = $("#userMobile");
		var rM = $("#registerMail");
		var lB = $("#luserMobile");
		var lM = $("#lregisterMail");
		var SN = $("#mobileSN");
		var lSN = $("#lmobileSN");
		uM.focus();
		var isReg = true;
		m.click(function(){
			m.attr("class", "password_tel");
			e.attr("class", "password_email");
			$("#liMobile").attr("class","");
			$("#liSendSN").attr("class","");
			$("#liMobile").show();
			$("#liEmail").hide();
			$("#liSendSN").show();
			rM.val("");
			uM.attr("class","tel_text");
			uM.focus();
			lB.removeClass("wrong").html("\u8BF7\u8F93\u5165\u6CE8\u518C\u65F6\u6240\u586B\u5199\u7684\u624B\u673A\u53F7\u7801");
			lB.removeClass("ok").html("\u8BF7\u8F93\u5165\u6CE8\u518C\u65F6\u6240\u586B\u5199\u7684\u624B\u673A\u53F7\u7801");
			lSN.removeClass("wrong").html("");
		});
		e.click(function(){
			m.attr("class", "password_tel_click");
			e.attr("class", "password_email_click");
			$("#liEmail").attr("class","");
			$("#liSendSN").attr("class","");
			$("#liMobile").hide();
			$("#liEmail").show();
			$("#liSendSN").hide();
			uM.val("");
			rM.attr("class","tel_text");
			rM.focus();
			lM.removeClass("wrong").html("\u8BF7\u8F93\u5165\u6CE8\u518C\u65F6\u6240\u586B\u5199\u7684\u90AE\u7BB1");
			lM.removeClass("ok").html("\u8BF7\u8F93\u5165\u6CE8\u518C\u65F6\u6240\u586B\u5199\u7684\u90AE\u7BB1");
		});
		
		var h = null;
		var g = 10;
		var fa = function () {
				g = g - 1;
				$("#btnSendSN").val("(" + g + ")\u518d\u6b21\u53d1\u9001").attr("disabled", true);
				if (g <= 0) {
					$("#btnSendSN").val("\u70b9\u51fb\u518d\u6b21\u53d1\u9001").attr("disabled", false);
					if (h != null) {
						clearInterval(h);
						g = 10;
						return false;
					}
				}
			};
		function loadingReg(){
			$("#registerSubmit").remove();
	        $("#registerSubmitStatus").show();
	        $("#registerSubmitStatus > p").css({"cursor":"wait"});
		}
		function loadingIsPhone(){
			lB.removeClass("wrong").html("\u6B63\u5728\u68C0\u67E5\u624B\u673A\u53F7\u662F\u5426\u53EF\u7528\!");
			lB.removeClass("ok").html("\u6B63\u5728\u68C0\u67E5\u624B\u673A\u53F7\u662F\u5426\u53EF\u7528\!");
			$("#liMobile").attr("class","init");
		}
		function loadingIsMail(){
			lM.removeClass("wrong").html("\u6B63\u5728\u68C0\u67E5\u90AE\u7BB1\u662F\u5426\u53EF\u7528\!");
			lM.removeClass("ok").html("\u6B63\u5728\u68C0\u67E5\u90AE\u7BB1\u662F\u5426\u53EF\u7528\!");
			$("#liEmail").attr("class","init");
		}
			
		var newObj = "";
		uM.blur(function (){
	        var obj = uM.val();
	        reg=/^1\d{10}$/;
			if(obj==""){
			 	lB.attr("class","wrong").html("\u624B\u673A\u53F7\u7801\u4E0D\u80FD\u4E3A\u7A7A\!");
			 	uM.attr("class","tel_text_click");
			 	$("#liMobile").attr("class","init");
			 	isReg = false;
			 	return;
			}else if(!reg.test(obj)){
	            lB.attr("class","wrong").html("\u8BF7\u8F93\u5165\u6B63\u786E\u624B\u673A\u53F7\u7801\!");
	            uM.attr("class","tel_text_click");
	            $("#liMobile").attr("class","init");
	            isReg = false;
	            return;
	        }else{
	        	if(newObj!=obj){
	        		$.ajax({
		 				url:"/register/isExists.html?userName="+obj,
		 				type:"post",
		 				data:"String",
		 				beforeSend:loadingIsPhone,
		 				success:function(data){
		 					if(data=="false"){
		 						lB.attr("class","ok").html("");
		            			uM.attr("class","tel_text");
		            			$("#liMobile").attr("class","");
		            			newObj = obj;
		            			isReg = true;
		 					}else{
		 						lB.attr("class","wrong").html("\u60A8\u8F93\u5165\u7684\u624B\u673A\u53F7\u7801\u8FD8\u672A\u6CE8\u518C\!");
		           				uM.attr("class","tel_text_click");
		           				$("#liMobile").attr("class","init");
		           				newObj = obj;
		           				isReg = false;
		 					}
		 				},
		 				error:function(){
		 					alert("\u8FDE\u63A5\u670D\u52A1\u5668\u5931\u8D25\uFF0C\u8BF7\u7A0D\u540E\u518D\u8BD5\uFF01");
		 					window.location.href="/index.html";
		 				}
		 			});
	        	}
	            
	        }   
		 }); 
		 rM.blur(function (){
		        var obj = rM.val();
		        reg=/^\w{3,}@\w+(\.\w+)+$/;
				if(obj==""){
				 	lM.attr("class","wrong").html("\u90AE\u7BB1\u4E0D\u80FD\u4E3A\u7A7A\!");
				 	rM.attr("class","tel_text_click");
				 	$("#liEmail").attr("class","init");
				 	isReg = false;
				 	return;
				}else if(!reg.test(obj)){   
		            lM.attr("class","wrong").html("\u8BF7\u8F93\u5165\u6B63\u786E\u90AE\u7BB1\u5730\u5740\!");
		            rM.attr("class","tel_text_click");
		            $("#liEmail").attr("class","init");
		            isReg = false;
		            return;
		        }else{
		        	if(newObj!=obj){
			        	$.ajax({
			 				url:"/register/isExists.html?userName="+obj,
			 				type:"post",
			 				data:"String",
			 				beforeSend:loadingIsMail,
			 				success:function(data){
			 					if(data=="false"){
			 						lM.attr("class","ok").html("");
						            rM.attr("class","tel_text");
						            $("#liEmail").attr("class","");
						            newObj = obj;
						            isReg = true;
			 					}else{
			 						lM.attr("class","wrong").html("\u60A8\u8F93\u5165\u7684\u90AE\u7BB1\u8FD8\u672A\u6CE8\u518C\!");
						            rM.attr("class","tel_text_click");
						            $("#liEmail").attr("class","init");
						            newObj = obj;
						            isReg = false;
			 					}
			 				},
			 				error:function(){
			 					alert("\u8FDE\u63A5\u670D\u52A1\u5668\u5931\u8D25\uFF0C\u8BF7\u7A0D\u540E\u518D\u8BD5\uFF01");
		 						window.location.href="/index.html";
			 				}
			 			});
		 			}
		        }   
		 }); 
		 SN.blur(function (){
		        var obj = SN.val();
		        reg=/^[0-9]{6}$/;
				if(obj==""){
				 	lSN.attr("class","wrong").html("\u8BF7\u8F93\u5165\u624B\u673A\u4E0A\u7684\u9A8C\u8BC1\u7801\!");
				 	SN.attr("class","verification");
				 	$("#liSendSN").attr("class","init");
				 	isReg = false;
				 	return;
				}else if(!reg.test(obj)){   
		            lSN.attr("class","wrong").html("\u9A8C\u8BC1\u7801\u4E0D\u8DB36\u4F4D\!");
		            SN.attr("class","verification");
		            $("#liSendSN").attr("class","init");
		            isReg = false;
		            return;
		        }else{
		        	lSN.attr("class","ok").html("");
		            SN.attr("class","verification");
		            $("#liSendSN").attr("class","");
		            isReg = true;
		        }   
		 }); 
		
		$("#btnSendSN").live("click",function () {
			fa();
			h = setInterval(fa, 1000);
		});
		
	}
};
jQuery(function ($) {
	GetBackPwd.loginload();
});