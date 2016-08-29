
function ckPasswd(B) {
	var A = /^[^ \f\n\r\t\v]{6,16}$/;
	if (!A.exec(B)) {
		return false;
	}
	return true;
}
$(function () {
	var C = ["\u539f\u59cb\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a!", "\u5bc6\u7801\u4e0d\u8db36\u4f4d!", "\u539f\u59cb\u5bc6\u7801\u9519\u8bef,\u8bf7\u91cd\u65b0\u8f93\u5165!"];
	var H = ["\u65b0\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a!", "\u5bc6\u7801\u4e0d\u8db36\u4f4d!", "\u65b0\u5bc6\u7801\u9519\u8bef,\u8bf7\u91cd\u65b0\u8f93\u5165!"];
	var B = ["\u786e\u8ba4\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a!", "\u4e24\u6b21\u5bc6\u7801\u8f93\u5165\u4e0d\u4e00\u81f4,\u8bf7\u91cd\u65b0\u8f93\u5165"];
	var O = "";
	var D = 1;
	var A = "";
	var K = "";
	var N = "";
	var U = 0;
	var R = 0;
	var W = 0;
	var V = 1;
	var I = false;
	var J = true;
	var Q = false;
	var userId = $("#userId").val();
//	var S = $("#MemberForm");
//	var G = S.html();
	var M = function (X, Y) {
		if (!Q) {
			X.focus();
		}
		X.nextAll("div").attr("class", "wrong").html(Y);
	};
	var T = function (X) {
		if (!X) {
			return;
		}
		Q = true;
		O = X;
	};
	var F = function (a) {
		if (!a) {
			return;
		}
		var Z = $("#"+a).val();
		var b = $("#l" + a);
		var X = "";
		var Y = "";
		Q = false;
		switch (a) {
		  case "OldPass":
			if (Z == "") {
				A = "";
				X = "wrong";
				Y = C[0];
				U = 0;
				I = false;
			} else {
				if (!ckPasswd(Z)) {
					X = "wrong";
					Y = C[1];
					A = Z;
					U = 1;
					_IsNameInit = 0;
					I = false;
				} else {
					X = "ok";
					Y = "&nbsp;";
					A = Z;
					U = 2;
					_IsNameInit = 0;
					I = true;
				}
			}
			break;
		  case "NewPass":
			if (Z == "" && N != "") {
				X = "wrong";
				Y = H[0];
				K = Z;
				R = 0;
				_IsNameInit = 0;
				I = false;
			} else {
				if (Z == "") {
					X = "wrong";
					Y = H[0];
					K = Z;
					R = 0;
					_IsNameInit = 0;
					I = false;
				} else {
					if (!ckPasswd(Z)) {
						X = "wrong";
						Y = H[1];
						K = Z;
						R = 1;
						_IsNameInit = 0;
						I = false;
					} else {
						if (N != "" && Z != N) {
							X = "wrong";
							Y = B[1];
							K = Z;
							W = 1;
							_IsNameInit = 0;
							O = "NewPassAgain";
							I = false;
							b.attr("class", "ok").html("");
							b = $("#lNewPassAgain");
						} else {
							if (N != "" && Z == N) {
								X = "ok";
								Y = "&nbsp;";
								K = Z;
								W = 2;
								_IsNameInit = 0;
								I = true;
								b.attr("class", "ok").html("");
								b = $("#lNewPassAgain");
							} else {
								X = "ok";
								Y = "&nbsp;";
								K = Z;
								R = 2;
								_IsNameInit = 0;
								I = true;
							}
						}
					}
				}
			}
			break;
		  case "NewPassAgain":
			if (Z == "") {
				X = "wrong";
				Y = B[0];
				W = 0;
				N = "";
				_IsNameInit = 0;
				I = false;
			} else {
				if (K == "") {
					X = "wrong";
					Y = H[0];
					N = Z;
					W = 0;
					_IsNameInit = 0;
					O = "NewPass";
					I = false;
					b = $("#lNewPass");
				} else {
					if (Z != K) {
						X = "wrong";
						Y = B[2];
						W = 1;
						N = Z;
						_IsNameInit = 0;
						I = false;
					} else {
						X = "ok";
						Y = "&nbsp;";
						N = Z;
						W = 2;
						_IsNameInit = 0;
						I = true;
					}
				}
			}
			break;
		  default:
			break;
		}
		if (Y != "") {
			b.attr("class", X).html(Y);
		}
		_IsNameInit = 1;
	};
	var E = function () {
		pageDialog.PageDialog("<dl class='sAltOK'><dd>\u64cd\u4f5c\u6210\u529f\uff01</dd></dl>","",160,50,"true","true");
		window.setInterval("location.replace(location.href);",2000);
	};
	var P = function () {
		if (!isLoaded) {
			return;
		}
		var pwd = $("#OldPass").val();
		$.ajax({
			url:"/user/updatePwd.action?userId="+userId+"&id="+pwd,
			type:"post",
			data:"string",
			success:function(data){
				if(data=="true"){
					E();
				}else{
					alert("操作失败，请稍后再试！");
				}
			}
		});
	};
	var L = function () {
		if (!isLoaded) {
			return;
		}
		J = true;
		var X = $("#OldPass");
		if (U == 0) {
			M(X, C[0]);
			J = false;
		}
		if (U == 1) {
			M(X, C[1]);
			J = false;
		}
		var Z = $("#NewPass");
		if (R == 0) {
			M(Z, H[0]);
			J = false;
		}
		if (R == 1) {
			M(Z, H[1]);
			J = false;
		}
		var Y = $("#NewPassAgain");
		if (W == 0) {
			M(Y, B[0]);
			J = false;
		}
		if (W == 1 || Y.val() != Z.val()) {
			M(Y, B[1]);
			J = false;
		}
		if (J == true) {
			P();
		}
	};
	$("input[type='password']").bind("focus", function () {
		T(this.id);
	}).bind("blur", function () {
		F(this.id);
	}).removeAttr("disabled");
	$("#btnSubmitSave").bind("click", function () {
		L();
	});
	$("input[type='password']").bind("keydown", function (Y) {
		var X = (window.event) ? event.keyCode : Y.keyCode;
		if (X == 32) {
			return false;
		} else {
			if (X == 13) {
				F(O);
				if (I) {
					if (O == "NewPassAgain") {
						$("#btnSubmitSave").click();
					} else {
						$(O).parent().nextAll("li").eq(0).find("input[type='password']").focus();
					}
				} else {
					$(O).focus();
				}
				return false;
			}
		}
		return true;
	});
	isLoaded = true;
});

