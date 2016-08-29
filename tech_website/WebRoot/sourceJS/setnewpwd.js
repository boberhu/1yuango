SetNewPwd = {
	loginload:function () {
		function ckPasswd(B) {
			var A = /^[^ \f\n\r\t\v]{6,20}$/;
			if (!A.exec(B)) {
				return false;
			}
			return true;
		}
		function showErrMsg(A, B) {
			$.PageDialog("<h2 class='error'>" + A + "</h2>", {W:320, H:80, obj:B, oT:4, submit:function () {
				B.focus();
			}});
		}
		$(function () {
			var A = "";
			var B = "";
			var I = 0;
			var H = 0;
			var C = $("#MemberForm");
			var J = C.html();
			var L = function (O) {
				if (!O) {
					return;
				}
				var N = getTrimVal(O);
				var P = $("#l" + O);
				var M = "";
				switch (O) {
				  case "password":
					M = "\u5bc6\u7801\u75316-16\u4f4d\u5b57\u7b26\uff0c\u53ef\u7531\u82f1\u6587\u3001\u6570\u5b57\u53ca\u201c_\u201d\u3001\u201c-\u201d\u7ec4\u6210";
					break;
				  case "passwordAgain":
					M = "\u91cd\u590d\u60a8\u521a\u521a\u8f93\u5165\u7684\u5bc6\u7801";
					break;
				  default:
					M = "";
					break;
				}
				if (M != "") {
					P.attr("class", "").html(M);
				}
				$("#" + O).parent().attr("class", "init");
			};
			var D = function (P) {
				if (!P) {
					return;
				}
				var O = getTrimVal(P);
				var Q = $("#l" + P);
				var M = "Exegesis in_errorMsg";
				var N = "";
				switch (P) {
				  case "password":
					if (O == "") {
						M = "wrong";
						N = "\u8bf7\u8f93\u5165\u65b0\u5bc6\u7801\uff01";
						A = "";
						I = 0;
					} else {
						if (!ckPasswd(O)) {
							M = "wrong";
							N = "\u65b0\u5bc6\u7801\u8f93\u5165\u9519\u8bef,\u5bc6\u7801\u75316-16\u4f4d\u5b57\u7b26\uff0c\u53ef\u7531\u82f1\u6587\u3001\u6570\u5b57\u53ca\u201c_\u201d\u3001\u201c-\u201d\u7ec4\u6210\uff01";
							A = O;
							I = 1;
						} else {
							M = "ok";
							N = "\u65b0\u5bc6\u7801\u8f93\u5165\u6b63\u786e\uff01";
							A = O;
							I = 2;
						}
					}
					E(O);
					break;
				  case "passwordAgain":
					if (O == "") {
						M = "wrong";
						N = "\u8bf7\u8f93\u5165\u786e\u8ba4\u5bc6\u7801\uff01";
						B = "";
						H = 0;
					} else {
						if ((!ckPasswd(O)) || (O != A)) {
							M = "wrong";
							N = "\u786e\u8ba4\u5bc6\u7801\u4e0d\u6b63\u786e\uff0c\u91cd\u590d\u60a8\u521a\u521a\u8f93\u5165\u7684\u5bc6\u7801!";
							B = O;
							H = 1;
						} else {
							M = "ok";
							N = "\u786e\u8ba4\u5bc6\u7801\u8f93\u5165\u6b63\u786e\uff01";
							B = O;
							H = 2;
						}
					}
					break;
				  default:
					break;
				}
				if (N != "") {
					Q.attr("class", M).html(N);
				}
				$("#" + P).parent().attr("class", "");
			};
			var K = function () {
				if (!isLoaded) {
					return;
				}
//				var M = new JQAjax("/DataServer/SetUserPwd");
//				M.OnStart(function () {
//					isLoaded = false;
//					$("#registerSubmit").hide();
//					$("#registerSubmitStatus").show();
//					showLoadingStatus();
//				});
//				M.OnStop(function () {
//					isLoaded = true;
//					closeLoadingStatus();
//				});
//				M.OnSuccess(function (O) {
//					var P = O.d.Code;
//					if (P == 0) {
//						location.replace("GetBackOk.html");
//					} else {
//						$.PageDialog("<dl class=\"sAltFail\"><dd>\u8bbe\u7f6e\u65b0\u5bc6\u7801\u5931\u8d25\uff01</dd></dl>", {W:160, H:50, close:false, autoClose:true, submit:function () {
//							location.reload();
//						}});
//					}
//				});
//				var N = {"userName":$("#hidUserName").val(), "pwd":A, "key":$("#hidKey").val(), "sn":$("#hidSN").val(), "rnd":GetRandomNum(1, 10000)};
//				M.OnSend($.toJSON(N), "json", true);
			};
			var G = function () {
				if (!isLoaded) {
					return;
				}
				var N = $("#password");
				var O = $("#lpassword");
				if (I == 0) {
					N.focus();
					O.attr("class", "wrong").html("\u8bf7\u8f93\u5165\u65b0\u5bc6\u7801!");
					return false;
				}
				if (I == 1) {
					N.focus();
					O.attr("class", "wrong").html("\u65b0\u5bc6\u7801\u8f93\u5165\u9519\u8bef,\u5bc6\u7801\u75316-16\u4f4d\u5b57\u7b26\uff0c\u53ef\u7531\u82f1\u6587\u3001\u6570\u5b57\u53ca\u201c_\u201d\u3001\u201c-\u201d\u7ec4\u6210!");
					return false;
				}
				var P = $("#passwordAgain");
				var M = $("#lpasswordAgain");
				if (H == 0) {
					P.focus();
					M.attr("class", "wrong").html("\u8bf7\u8f93\u5165\u786e\u8ba4\u5bc6\u7801!");
					return false;
				}
				if (H == 1 || (P.val() != N.val())) {
					P.focus();
					M.attr("class", "wrong").html("\u4e24\u6b21\u5bc6\u7801\u4e0d\u4e00\u81f4\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165!");
					return false;
				}
				K();
			};
			var F = function () {
				C.html(J).ready(function () {
					A = "";
					B = "";
					I = false;
					H = false;
					$(":password").bind("focus", function () {
						L(this.id);
					}).bind("blur", function () {
						D(this.id);
					}).removeAttr("disabled").val("");
				});
				$("#password").focus();
			};
			var E = function (O) {
				$("p span").attr("class", "");
				if (I == 2) {
					var M = /^[A-Za-z0-9]+$/;
					var N = /[_a-zA-Z]/;
					if (!isNaN(O)) {
						$("#low").attr("class", "low");
					}
					if (N.test(O)) {
						$("#low").attr("class", "low");
						$("#middle").attr("class", "middle");
					}
					if (!(new RegExp(/^\w+$/ig).test(O))) {
						$("#low").attr("class", "low");
						$("#middle").attr("class", "middle");
						$("#height").attr("class", "high");
					}
				}
			};
			F();
			$("#btnSubmitRegister").bind("click", function () {
				G();
			});
			isLoaded = true;
		});
	}
};
jQuery(function ($) {
	SetNewPwd.loginload();
});