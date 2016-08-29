register={
	loginload:function(){
			var m = $("#mobileReg");
			var e = $("#emailReg");
			var uM = $("#userMobile");
			var rM = $("#registerMail");
			var lB = $("#luserMobile");
			var lM = $("#lregisterMail");
			var pwd = $("#password");
			var pwda = $("#passwordAgain");
			var lpwd = $("#lpassword");
			var lpwda = $("#lpasswordAgain");
			var isReg = true;
			var isExists = false;
			uM.focus();
			m.click(function(){
				m.attr("class","regsiter_tel");
				e.attr("class","regsiter_email");
				$("#liMobile").show();
				$("#liEmail").hide();
				rM.val("");
				uM.attr("class","tel_text");
				pwd.attr("class","tel_text");
				pwda.attr("class","tel_text");
				uM.focus();
				lB.removeClass("wrong").html("\u60A8\u53EF\u4EE5\u4F7F\u7528\u60A8\u7684\u624B\u673A\u6CE8\u518C1\u5143\u62CD\u8D2D\u3002\u5E76\u4E14\u662F\u6536\u5230\u4E2D\u5956\u4FE1\u606F\u7684\u91CD\u8981\u9014\u5F84\uFF01");
				lB.removeClass("ok").html("\u60A8\u53EF\u4EE5\u4F7F\u7528\u60A8\u7684\u624B\u673A\u6CE8\u518C1\u5143\u62CD\u8D2D\u3002\u5E76\u4E14\u662F\u6536\u5230\u4E2D\u5956\u4FE1\u606F\u7684\u91CD\u8981\u9014\u5F84\uFF01");
				lpwd.removeClass("wrong").html("\u5BC6\u7801\u75316-16\u4F4D\u5B57\u7B26\uFF0C\u53EF\u7531\u82F1\u6587\u3001\u6570\u5B57\u53CA\u201C_\u201D\u7EC4\u6210");
				lpwd.removeClass("ok").html("\u5BC6\u7801\u75316-16\u4F4D\u5B57\u7B26\uFF0C\u53EF\u7531\u82F1\u6587\u3001\u6570\u5B57\u53CA\u201C_\u201D\u7EC4\u6210");
				lpwda.removeClass("wrong").html("\u91CD\u590D\u60A8\u521A\u521A\u8F93\u5165\u7684\u5BC6\u7801");
				lpwda.removeClass("ok").html("\u91CD\u590D\u60A8\u521A\u521A\u8F93\u5165\u7684\u5BC6\u7801");
			});
			e.click(function(){
				m.attr("class","regsiter_tel_click");
				e.attr("class","regsiter_email_click");
				$("#liMobile").hide();
				$("#liEmail").show();
				$("#liSendSN").hide();
				uM.val("");
				rM.attr("class","tel_text");
				pwd.attr("class","tel_text");
				pwda.attr("class","tel_text");
				rM.focus();
				lM.removeClass("wrong").html("\u60A8\u53EF\u4EE5\u7528\u60A8\u7684\u90AE\u7BB1\u767B\u5F551\u5143\u62CD\u8D2D\uFF0C\u5E76\u4E14\u662F\u6536\u5230\u4E2D\u5956\u4FE1\u606F\u7684\u91CD\u8981\u9014\u5F84\uFF01");
				lM.removeClass("ok").html("\u60A8\u53EF\u4EE5\u7528\u60A8\u7684\u90AE\u7BB1\u767B\u5F551\u5143\u62CD\u8D2D\uFF0C\u5E76\u4E14\u662F\u6536\u5230\u4E2D\u5956\u4FE1\u606F\u7684\u91CD\u8981\u9014\u5F84\uFF01");
				lpwd.removeClass("wrong").html("\u5BC6\u7801\u75316-16\u4F4D\u5B57\u7B26\uFF0C\u53EF\u7531\u82F1\u6587\u3001\u6570\u5B57\u53CA\u201C_\u201D\u7EC4\u6210");
				lpwd.removeClass("ok").html("\u5BC6\u7801\u75316-16\u4F4D\u5B57\u7B26\uFF0C\u53EF\u7531\u82F1\u6587\u3001\u6570\u5B57\u53CA\u201C_\u201D\u7EC4\u6210");
				lpwda.removeClass("wrong").html("\u91CD\u590D\u60A8\u521A\u521A\u8F93\u5165\u7684\u5BC6\u7801");
				lpwda.removeClass("ok").html("\u91CD\u590D\u60A8\u521A\u521A\u8F93\u5165\u7684\u5BC6\u7801");
			});
			function loadingReg(){
				$("#registerSubmit").remove();
		        $("#registerSubmitStatus").show();
		        $("#registerSubmitStatus > p").css({"cursor":"wait"});
			}
			function loadingIsPhone(){
				lB.removeClass("wrong").html("\u6B63\u5728\u68C0\u67E5\u624B\u673A\u53F7\u662F\u5426\u53EF\u7528\!");
				lB.removeClass("ok").html("\u6B63\u5728\u68C0\u67E5\u624B\u673A\u53F7\u662F\u5426\u53EF\u7528\!");
			}
			function loadingIsMail(){
				lM.removeClass("wrong").html("\u6B63\u5728\u68C0\u67E5\u90AE\u7BB1\u662F\u5426\u53EF\u7528\!");
				lM.removeClass("ok").html("\u6B63\u5728\u68C0\u67E5\u90AE\u7BB1\u662F\u5426\u53EF\u7528\!");
			}
			uM.blur(function (){
		        var obj = uM.val();
		        reg=/^1\d{10}$/;
				if(obj==""){
				 	lB.attr("class","wrong").html("\u624B\u673A\u53F7\u7801\u4E0D\u80FD\u4E3A\u7A7A\!");
				 	uM.attr("class","tel_text_click");
				 	isReg = false;
				}else if(!reg.test(obj)){
		            lB.attr("class","wrong").html("\u8BF7\u8F93\u5165\u6B63\u786E\u624B\u673A\u53F7\u7801\!");
		            uM.attr("class","tel_text_click");
		            isReg = false;
		        }else{
	        		$.ajax({
		 				url:"/register/isExists.html?userName="+obj,
		 				type:"post",
		 				data:"String",
		 				beforeSend:loadingIsPhone,
		 				success:function(data){
		 					if(data=="true"){
		 						lB.attr("class","ok").html("");
		            			uM.attr("class","tel_text");
		            			isReg = true;
		            			isExists = true;
		 					}else{
		 						lB.attr("class","wrong").html("\u8BE5\u624B\u673A\u53F7\u7801\u5DF2\u88AB\u6CE8\u518C\uFF0C\u5982\u53F7\u7801\u65E0\u8BEF\u8BF7\u8054\u7CFB\u5BA2\u670D\!");
		           				uM.attr("class","tel_text_click");
		           				isReg = false;
		 					}
		 				},
		 				error:function(){
		 					alert("\u8FDE\u63A5\u670D\u52A1\u5668\u5931\u8D25\uFF0C\u8BF7\u7A0D\u540E\u518D\u8BD5\uFF01");
		 					window.location.href=$www;
		 				}
		 			});
		        }   
			 }); 
			 rM.blur(function (){
			        var obj = rM.val();
			        reg=/^\w{3,}@\w+(\.\w+)+$/;
					if(obj==""){
					 	lM.attr("class","wrong").html("\u90AE\u7BB1\u4E0D\u80FD\u4E3A\u7A7A\!");
					 	rM.attr("class","tel_text_click");
					 	isReg = false;
					}else if(!reg.test(obj)){   
			            lM.attr("class","wrong").html("\u8BF7\u8F93\u5165\u6B63\u786E\u90AE\u7BB1\u5730\u5740\!");
			            rM.attr("class","tel_text_click");
			            isReg = false;
			        }else{
			        	$.ajax({
			 				url:"/register/isExists.html?userName="+obj,
			 				type:"post",
			 				data:"String",
			 				beforeSend:loadingIsMail,
			 				success:function(data){
			 					if(data=="true"){
			 						lM.attr("class","ok").html("");
						            rM.attr("class","tel_text");
						            isReg = true;
						            isExists = true;
			 					}else{
			 						lM.attr("class","wrong").html("\u8BE5\u90AE\u7BB1\u5DF2\u88AB\u6CE8\u518C\u8FC7\u5594\uFF0C\u60A8\u53EF\u4EE5\u76F4\u63A5\u767B\u5F55\!");
						            rM.attr("class","tel_text_click");
						            isReg = false;
			 					}
			 				},
			 				error:function(){
			 					alert("\u8FDE\u63A5\u670D\u52A1\u5668\u5931\u8D25\uFF0C\u8BF7\u7A0D\u540E\u518D\u8BD5\uFF01");
		 						window.location.href=$www;
			 				}
			 			});
			        }   
			 }); 
			 pwd.blur(function (){
			        var obj = pwd.val();
			        reg=/^[a-zA-Z0-9_]{6,16}$/;
					if(obj==""){
					 	lpwd.attr("class","wrong").html("\u8BF7\u8F93\u5165\u767B\u5F55\u5BC6\u7801\!");
					 	pwd.attr("class","tel_text_click");
					 	isReg = false;
					}else if(!reg.test(obj)){
			            lpwd.attr("class","wrong").html("\u5BC6\u7801\u8F93\u5165\u9519\u8BEF\uFF0C\u5BC6\u7801\u75316-16\u4F4D\u5B57\u7B26\uFF0C\u53EF\u7531\u82F1\u6587\u3001\u6570\u5B57\u53CA\u201C_\u201D\u7EC4\u6210\!");
			            pwd.attr("class","tel_text_click");
			            isReg = false;
			        }else{
			            lpwd.attr("class","ok").html("");
			            pwd.attr("class","tel_text");
			            isReg = true;
			        }   
			 });
			 pwda.blur(function (){
			        var obj = pwda.val();
			        var password = pwd.val();
			        if(password==""){   
			            lpwda.attr("class","wrong").html("\u8BF7\u8F93\u5165\u786E\u8BA4\u5BC6\u7801\!");   
			            pwda.attr("class","tel_text_click");
			            isReg = false;
			        }else if(password!=obj){
			        	lpwda.attr("class","wrong").html("\u786E\u8BA4\u5BC6\u7801\u4E0D\u6B63\u786E\uFF0C\u91CD\u590D\u60A8\u521A\u521A\u8F93\u5165\u7684\u5BC6\u7801\!");
			        	pwda.attr("class","tel_text_click");
			        	isReg = false;
			        }else{
			            lpwda.attr("class","ok").html("");   
			            pwda.attr("class","tel_text");
			            isReg = true;
			        }
			 });
			
		 $("#btnSubmitRegister").click(function(){
		 		var uMobj = uM.val();
		        regB=/^1\d{10}$/;
		        var rMobj = rM.val();
		        regM=/^\w{3,}@\w+(\.\w+)+$/;
		        var pwdobj = pwd.val();
		        regP=/^[a-zA-Z0-9_]{6,16}$/;
		        var pwdaobj = pwda.val();
		        var password = pwd.val();
		 		if($('#liMobile').is(":visible")){
		 			if(uMobj==""){
					 	lB.attr("class","wrong").html("\u624B\u673A\u53F7\u7801\u4E0D\u80FD\u4E3A\u7A7A\!");
					 	uM.attr("class","tel_text_click");
					 	uM.focus();
					 	isReg = false;
					 	return false;
					}else if(!regB.test(uMobj)){   
			            lB.attr("class","wrong").html("\u8BF7\u8F93\u5165\u6B63\u786E\u624B\u673A\u53F7\u7801\!");
			            uM.attr("class","tel_text_click");
			            uM.focus();
			            isReg = false;
			            return false;
			        }else if(!isExists){
			        	lB.attr("class","wrong").html("\u8BE5\u624B\u673A\u53F7\u7801\u5DF2\u88AB\u6CE8\u518C\uFF0C\u5982\u53F7\u7801\u65E0\u8BEF\u8BF7\u8054\u7CFB\u5BA2\u670D\!");
           				uM.attr("class","tel_text_click");
           				uM.focus();
           				isReg = false;
			        }else if(pwdobj==""){
					 	lpwd.attr("class","wrong").html("\u8BF7\u8F93\u5165\u767B\u5F55\u5BC6\u7801\!");
					 	pwd.attr("class","tel_text_click");
					 	pwd.focus();
					 	isReg = false;
					 	return false;
					}else if(!regP.test(pwdobj)){
			            lpwd.attr("class","wrong").html("\u5BC6\u7801\u8F93\u5165\u9519\u8BEF\uFF0C\u5BC6\u7801\u75316-16\u4F4D\u5B57\u7B26\uFF0C\u53EF\u7531\u82F1\u6587\u3001\u6570\u5B57\u53CA\u201C_\u201D\u7EC4\u6210\!");
			            pwd.attr("class","tel_text_click");
			            pwd.focus();
			            isReg = false;
			            return false;
			        }else if(pwdaobj==""){   
			            lpwda.attr("class","wrong").html("\u8BF7\u8F93\u5165\u786E\u8BA4\u5BC6\u7801\!");   
			            pwda.attr("class","tel_text_click");
			            pwda.focus();
			            isReg = false;
			            return false;
			        }else if(password!=pwdaobj){
			        	lpwda.attr("class","wrong").html("\u786E\u8BA4\u5BC6\u7801\u4E0D\u6B63\u786E\uFF0C\u91CD\u590D\u60A8\u521A\u521A\u8F93\u5165\u7684\u5BC6\u7801\!");
			        	pwda.attr("class","tel_text_click");
			        	pwda.focus();
			        	isReg = false;
			        	return false;
			        }else{
			        	lB.attr("class","ok").html("");
			            uM.attr("class","tel_text");
			            lpwd.attr("class","ok").html("");
			            pwd.attr("class","tel_text");
			            lpwda.attr("class","ok").html("");   
			            pwda.attr("class","tel_text");
			            isReg = true;
			        }
		 		}else{
		 			if(rMobj==""){
					 	lM.attr("class","wrong").html("\u90AE\u7BB1\u4E0D\u80FD\u4E3A\u7A7A\!");
					 	rM.attr("class","tel_text_click");
					 	rM.focus();
					 	isReg = false;
					 	return false;
					}else if(!regM.test(rMobj)){   
			            lM.attr("class","wrong").html("\u8BF7\u8F93\u5165\u6B63\u786E\u90AE\u7BB1\u5730\u5740\!");
			            rM.attr("class","tel_text_click");
			            rM.focus();
			            isReg = false;
			            return false;
			        }else if(!isExists){
			        	lM.attr("class","wrong").html("\u8BE5\u90AE\u7BB1\u5DF2\u88AB\u6CE8\u518C\u8FC7\u5594\uFF0C\u60A8\u53EF\u4EE5\u76F4\u63A5\u767B\u5F55\!");
			            rM.attr("class","tel_text_click");
			            rM.focus();
			            isReg = false;
			        }else if(pwdobj==""){
					 	lpwd.attr("class","wrong").html("\u8BF7\u8F93\u5165\u767B\u5F55\u5BC6\u7801\!");
					 	pwd.attr("class","tel_text_click");
					 	pwd.focus();
					 	isReg = false;
					 	return false;
					}else if(!regP.test(pwdobj)){
			            lpwd.attr("class","wrong").html("\u5BC6\u7801\u8F93\u5165\u9519\u8BEF\uFF0C\u5BC6\u7801\u75316-16\u4F4D\u5B57\u7B26\uFF0C\u53EF\u7531\u82F1\u6587\u3001\u6570\u5B57\u53CA\u201C_\u201D\u7EC4\u6210\!");
			            pwd.attr("class","tel_text_click");
			            pwd.focus();
			            isReg = false;
			            return false;
			        }else if(pwdaobj==""){   
			            lpwda.attr("class","wrong").html("\u8BF7\u8F93\u5165\u786E\u8BA4\u5BC6\u7801\!");   
			            pwda.attr("class","tel_text_click");
			            pwda.focus();
			            isReg = false;
			            return false;
			        }else if(password!=pwdaobj){
			        	lpwda.attr("class","wrong").html("\u786E\u8BA4\u5BC6\u7801\u4E0D\u6B63\u786E\uFF0C\u91CD\u590D\u60A8\u521A\u521A\u8F93\u5165\u7684\u5BC6\u7801\!");
			        	pwda.attr("class","tel_text_click");
			        	pwda.focus();
			        	isReg = false;
			        	return false;
			        }else{
			        	lM.attr("class","ok").html("");
		           		rM.attr("class","tel_text");
			            lpwd.attr("class","ok").html("");
			            pwd.attr("class","tel_text");
			            lpwda.attr("class","ok").html("");
			            pwda.attr("class","tel_text");
			            isReg = true;
			        }
		 		}
		 		if(isReg){
		 			var phone = $("#userMobile").val();
		 			var mail = $("#registerMail").val();
		 			var userPwd = $("#password").val();
		 			$.ajax({
		 				url:"/register/regsiter.html?phone="+phone+"&mail="+mail+ "&userPwd="+userPwd,
		 				type:"post",
		 				data:"String",
		 				beforeSend:loadingReg,
		 				success:function(data){
		 					if(data!="false"){
		 						if(data.userId){
			 						index.SetCookieByExpires("userId",data.userId,1);
		 						}
		 						if(data.phone){
			 						index.SetCookieByExpires("phone",data.phone,1);
		 						}
		 						if(data.mail){
		 							index.SetCookieByExpires("mail",data.mail,1);
		 						}
		 						index.SetCookieByExpires("loginType",0,1);
		 						window.location.href=$www;
		 					}else{
		 						alert("\u6CE8\u518C\u5931\u8D25\uFF0C\u8BF7\u7A0D\u540E\u518D\u8BD5\!");
		 						window.location.href=$www;
		 					}
		 				},
		 				error:function(){
		 					alert("\u6CE8\u518C\u5931\u8D25\uFF0C\u8BF7\u7A0D\u540E\u518D\u8BD5\!");
		 					window.location.href=$www;
		 				}
		 			});
		 		}else{
		 			return false;
		 		}
		 });
		 
		 //新浪微博登录
		WB2.anyWhere(function(W){
		    W.widget.connectButton({
		        id: "wb_connect_btn",	
		        type:"3,2",
		        callback : {
		            login:function(o){	//登录后的回调函数
		            	$.ajax({
			 				url:"/register/authorizeIsExists.action?userName="+o.screen_name,
			 				type:"get",
			 				success:function(data){
			 					if(data=="true"){
			 						$.ajax({
									   type: "POST",
									   url:"/register/authorizeRegsiter.action",
									   data: "userName="+o.screen_name,
									   success: function(data){
									     index.SetCookieByExpires("userId",data.userId,1);
						            	 index.SetCookieByExpires("mail",data.userName,1);
						            	 index.SetCookieByExpires("loginType",1,1);
						            	 window.location.href=$www;
									   }
									}); 
			 					}else{
			 						index.SetCookieByExpires("userId",data.userId,1);
					            	index.SetCookieByExpires("mail",data.userName,1);
					            	index.SetCookieByExpires("loginType",1,1);
					            	window.location.href=$www;
			 					}
			 				}
			 			});
		            }
		        }
		    });
		});
		
		//腾讯QQ登录
		QC.Login({//按默认样式插入QQ登录按钮
			btnId:"qq_login_btn",	//插入按钮的节点id
			scope:"get_user_info,add_share",
			size: "B_M"
		}, function(reqData, opts){//登录成功
           	$.ajax({
				url:"/register/authorizeIsExists.action?userName="+reqData.nickname,
				type:"get",
				success:function(data){
					if(data=="true"){
						$.ajax({
						   type: "POST",
						   url:"/register/authorizeRegsiter.action",
						   data: "userName="+reqData.nickname,
						   success: function(data){
						     index.SetCookieByExpires("userId",data.userId,1);
			            	 index.SetCookieByExpires("mail",data.userName,1);
			            	 index.SetCookieByExpires("loginType",2,1);
			            	 window.location.href=$www;
						   }
						}); 
					}else{
						index.SetCookieByExpires("userId",data.userId,1);
		            	index.SetCookieByExpires("mail",data.userName,1);
		            	index.SetCookieByExpires("loginType",2,1);
		            	window.location.href=$www;
					}
				}
			});
		   }
		);
	}
};

jQuery(function($) {
	register.loginload();
});