
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
function ckMobile(B) {
	var A = /^1\d{10}$/;
	if (!A.exec(B)) {
		return false;
	} else {
		return true;
	}
}
function ckPhone(B) {
	var A = /^(0[0-9]{2,3}\-)+([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
	if (!A.test(B)) {
		return false;
	} else {
		return true;
	}
}
$(function () {
	var I = true;
	var userId = $("#userId").val();
	var M = $("#txtMobile");
	var MA = $("#txtMail");
	var N = $("#txtName");
	var NE = $("#divNameErr");
	var P = $("#txtPhone");
	var PE = $("#divPhoneErr");
	var q = $("#txtQQ");
	var qe = $("#divQQErr");
	var D = "";
	var T = 0;
	var K = "";
	var V = "";
	var H = "";
	var W = "";
	var J = "";
	var b = "";
	var S = $("#txtElse");
	var E = $("#ckElse");
	
	N.blur(function (){
        var obj = N.val();
		if(obj==""){
		 	N.focus().nextAll("div").eq(0).attr("class", "wrong").html("\u8bf7\u8f93\u5165\u6635\u79f0\uff01");
		 	I = false;
		}else{
       		$.ajax({
 				url:"/user/isUserNameExists.html?id="+obj+"&userId="+userId,
 				type:"post",
 				data:"String",
 				success:function(data){
 					if(data=="true"){
 						NE.attr("class","ok").html("&nbsp");
 						I = true;
 					}else{
 						NE.attr("class","wrong").html("\u8be5\u7528\u6237\u6635\u79f0\u5df2\u5b58\u5728\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165\uff01");
 						N.focus();
 					}
 				}
 			});
        }   
 	});
 	P.blur(function (){
 		J = P.val();
		if (J == "" || (!ckPhone(J) && !ckMobile(J))) {
			P.focus().nextAll("div").eq(0).attr("class", "wrong").html("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7535\u8bdd\u53f7\u7801\uff01");
			I = false;
		}else{
			PE.attr("class","ok").html("&nbsp");
			I = true;
		}
 	});
 	q.blur(function (){
 		D = q.val();
		if (isNaN(D)) {
			q.focus().nextAll("div").eq(0).attr("class", "wrong").html("\u8bf7\u8f93\u5165\u6b63\u786e\u7684QQ!");
			I = false;
		}else{
			qe.attr("class","ok").html("&nbsp");
			I = true;
		}
 	});
	E.bind("click", function () {
		if ($(this).attr("checked")) {
			S.attr("disabled", false).val("");
		} else {
			S.attr("disabled", true).val("");
		}
	});
	var R = function (e, f) {
		e.focus().nextAll("div").eq(0).attr("class", "wrong").html(f);
	};
	var U = function () {
		if (!I) {
			return;
		}
		$("#butSaveSubmit").hide();
		$("#SaveSubmitStatus").show();
		var userJSON = "{\"userName\":\""+N.val()+"\",\"mobilePhone\":\""+P.val()+"\",\"qq\":\""+q.val()+"\",\"mail\":\""+MA.val()+"\",\"phone\":\""+M.val()+"\"}";
		var url = "/user/updateUser.action?id="+userId+"&userJSON="+userJSON;
		$.ajax({
			url:url,
			type:"post",
			data:"json",
			success:function(data){
				if(data=="true"){
					pageDialog.PageDialog("<dl class='sAltOK'><dd>\u64cd\u4f5c\u6210\u529f\uff01</dd></dl>","",160,50,"true","true");
					window.setInterval("location.replace(location.href);",2000);
				}else{
					alert("操作失败，请稍后再试！");
				}
			}
		});
	};
	var Q = function () {
		if (N.val() == "") {
			R(N, "\u8bf7\u8f93\u5165\u6635\u79f0\uff01");
			return;
		}
		if (P.val() == "" || (!ckPhone(P.val()) && !ckMobile(P.val()))) {
			R(P, "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7535\u8bdd\u53f7\u7801\uff01");
			return;
		}
		if (isNaN(q.val())) {
			R(q, "\u8bf7\u8f93\u5165\u6b63\u786e\u7684QQ!");
			return;
		}
		if (E.attr("checked") == true) {
			if (h != "") {
				H = h;
			} else {
				R(S, "\u8bf7\u8f93\u5165\u5176\u4ed6\u5174\u8da3\u7231\u597d!");
				return false;
			}
		}
		U();
	};
	$("#butSaveSubmit").bind("click", function () {
		Q();
	});
	I = true;
});

