$(function() {
	var G = $skin;
	var K = $("#username");
	var R = $("#pwd");
	var J = $("#j-tips-wrap");
	var E = null;
	var L = null;
	var O = false;
	var B = $("#hidLoginForward").val();
	var X = /\w+(@{1})$/i, S = [ "qq.com", "sina.com", "163.com", "126.com",
			"139.com", "sohu.com" ], A = "", C = 0;
	var P = function(a) {
		var b = function(d, c, f) {
			var e = new RegExp(c, "g");
			return d.replace(e, f)
		};
		var a = escape(a);
		a = b(a, "\\+", "%2B");
		a = b(a, "/", "%2F");
		return a
	};
	var W = function(a) {
		var b = /^1\d{10}$/;
		if (!b.exec(a)) {
			return false
		} else {
			return true
		}
	};
	var Y = function(b) {
		var a = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if (a.test(b)) {
			return true
		}
		return false
	};
	var I = function(a) {
		var b = /^[\S]{6,20}$/;
		if (!b.exec(a)) {
			return false
		}
		return true
	};
	var z = function() {
		try {
			if (/(msie\s|trident.*rv:)([\w.]+)/.test(navigator.userAgent
					.toLowerCase())
					&& parseInt($.browser.version) == 6) {
				$.getScript(G + "/js/iepng.js?date=20150215", function() {
					if (EvPNG != null && EvPNG != undefined) {
						EvPNG.fix(".transparent-png")
					}
				})
			}
		} catch (a) {
		}
	};
	var N = function(b, a) {
		if (b) {
			$(b).parent().addClass("enter-focus")
		}
		$("#dd_error_msg").html(
				'<i class="passport-icon transparent-png"></i>' + a).show(5,
				function() {
					z()
				});
		$("#btnSubmitLogin").removeClass("letter-noSpac").html("\u767b\u5f55");
		O = false
	};
	var U = function(a) {
		if (a) {
			$(a).parent().removeClass("enter-focus")
		}
		$("#dd_error_msg").hide()
	};
	var D = setInterval(function() {
		if (K.val() != "") {
			if (!W(K.val()) && !Y(K.val())) {
				K.val("");
				K.siblings("em").show();
				K.blur();
				R.val("");
				R.siblings("em").show();
				R.blur()
			} else {
				K.siblings("em").hide()
			}
		}
		if (R.val() != "") {
			if (!I(R.val())) {
				R.val("");
				if (K.val() == "") {
					R.blur()
				}
				R.siblings("em").show()
			} else {
				R.siblings("em").hide()
			}
		}
	}, 200);
	var T = function() {
		if (D != null) {
			clearInterval(D);
			D = null
		}
	};
	setTimeout(function() {
		T()
	}, 5000);
	K.focus(function() {
		$(this).parent().addClass("enter-focus")
	}).blur(function() {
		if (D != null) {
			return
		}
		$(this).parent().removeClass("enter-focus");
		if ($(this).val() == "") {
			$(this).siblings("em").show();
			$(this).css("color", "#bbbbbb");
			$("#dd_error_msg").hide();
			return
		}
		F.checkUserName(false)
	}).click(function(a) {
		a = a || window.event;
		a.stopPropagation();
		if (J.children().length > 0 && K.val().indexOf("@") !== -1) {
			J.show()
		}
	}).keydown(function() {
		$(this).css("color", "#333333");
		K.siblings("em").hide();
		$("#dd_error_msg").hide()
	});
	if (K.val() == "") {
		K.siblings("em").bind("click", function() {
			K.focus()
		}).show()
	}
	K.keyup(function(i) {
		i = i || window.event;
		i.stopPropagation();
		var d = this.value, k = 0, a = 0, j = S.length, e = i.keyCode, c = "";
		if (X.test(d)) {
			for (; k < j; k++) {
				A += "<li class='j-tips'>" + (d + S[k]) + "</li>"
			}
			J.show().empty().html(A);
			A = ""
		} else {
			if (d.indexOf("@") !== -1) {
				var h = d.indexOf("@"), b = d.slice(0, h + 1), f = d
						.slice(h + 1);
				for (; a < j; a++) {
					if (S[a].indexOf(f) !== -1) {
						c += "<li class='j-tips'>" + (b + S[a]) + "</li>"
					}
				}
				if (c !== "") {
					J.show().empty().html(c)
				} else {
					J.hide();
					C = 0
				}
			} else {
				C = 0;
				J.hide()
			}
		}
		if (J.css("display") === "block") {
			var g = J.find("li");
			switch (e) {
			case 13:
				if (C > 0) {
					K.val(g.eq(C - 1).text().trim()).blur();
					J.hide();
					R.focus();
					C = 0
				}
				break;
			case 40:
				C = C + 1 > g.length ? 1 : C + 1;
				g.removeClass("selected").eq(C - 1).addClass("selected");
				break;
			case 38:
				C = C - 1 < 1 ? g.length : C - 1;
				g.removeClass("selected").eq(C - 1).addClass("selected");
				break;
			default:
				break
			}
		}
	});
	J.click(function(a) {
		a = a || window.event;
		a.stopPropagation();
		var b = a.target || a.srcElement;
		if (b.tagName.toLowerCase() === "li") {
			K.val($(b).text().trim()).blur();
			R.focus()
		}
		$(this).hide()
	});
	$(window).click(function(a) {
		if (J.css("display") !== "block") {
			return
		}
		a = a || window.event;
		var b = a.target || a.srcElement, c = b.id.toLowerCase();
		if (c !== "j-tips-wrap" || c !== "username") {
			J.hide()
		}
	});
	R.focus(function() {
		$(this).parent().addClass("enter-focus");
		J.hide()
	}).blur(function() {
		if (D != null) {
			return
		}
		$(this).parent().removeClass("enter-focus");
		if ($(this).val() == "") {
			$(this).siblings("em").show();
			$(this).css("color", "#bbbbbb");
			$("#dd_error_msg").hide();
			return
		}
		F.checkPwd(false)
	}).keydown(function() {
		$(this).css("color", "#333333");
		$(this).siblings("em").hide();
		$("#dd_error_msg").hide()
	});
	if (R.val() == "") {
		R.siblings("em").bind("click", function() {
			R.focus()
		}).show()
	}
	var H = function() {
		this.checkUserName = function(a) {
			E = K.val().trim();
			if (E == "") {
				if (a) {
					K.focus()
				}
				N(K,
						"\u8bf7\u8f93\u5165\u624b\u673a\u53f7\u6216\u90ae\u7bb1\u5730\u5740");
				return false
			} else {
				if (!W(E) && !Y(E)) {
					if (a) {
						K.focus()
					}
					N(
							K,
							"\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u624b\u673a\u53f7\u6216\u90ae\u7bb1\u5730\u5740");
					return false
				}
			}
			U(K);
			return true
		};
		this.checkPwd = function(a) {
			L = R.val().trim();
			if (L == "") {
				if (a) {
					R.focus()
				}
				N(R, "\u8bf7\u8f93\u5165\u767b\u5f55\u5bc6\u7801");
				return false
			} else {
				if (!I(L)) {
					if (a) {
						R.focus()
					}
					N(R,
							"\u767b\u5f55\u5bc6\u7801\u4e3a6-20\u957f\u5ea6\u7684\u5b57\u7b26");
					return false
				}
			}
			U(R);
			return true
		}
	};
	var F = new H();
	var M = function() {
		var e = $("#hidAccount").val();
		if (e != "" && (W(e) || Y(e))) {
			K.siblings("em").hide();
			K.val(e);
			if (R.val() == "") {
				R.focus()
			}
		} else {
			var c = getCookie("loginUser");
			if (c != null && (W(c) || Y(c))) {
				K.siblings("em").hide();
				K.val(c);
				if (R.val() == "") {
					R.focus()
				}
			}
		}
		if (R.val() != "") {
			R.siblings("em").hide()
		}
		var d = function() {
			T();
			if (O) {
				return
			}
			O = true;
			if (F.checkUserName(true) && F.checkPwd(true)) {
				$("#btnSubmitLogin").addClass("letter-noSpac").html(
						"\u6b63\u5728\u767b\u5f55...");
				var k = function(m) {
					var l = m;
					if (l == "userError") {
						N(K,
								"\u6b64\u8d26\u53f7\u4e0d\u5b58\u5728\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165")
					} else {
						if (l == "pwdError") {
							N(R,
									"\u767b\u5f55\u5bc6\u7801\u9519\u8bef\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165")
						} else {
							if (l == "check") {
								N(
										K,
										"\u6b64\u8d26\u53f7\u672a\u6fc0\u6d3b\uff0c\u8bf7\u4e0e\u5ba2\u670d\u8054\u7cfb\uff01")
							} else {
								if (l.userId) {
									SetCookie("userId", l.userId);
									SetCookie("face", l.faceImg)
								}
								if (l.userName) {
									SetCookie("userName", l.userName)
								} else {
									if (l.mail) {
										SetCookie("userName", l.mail)
									} else {
										if (l.phone) {
											SetCookie("userName", l.phone)
										}
									}
								}
								SetCookie("loginType", 0);
								if (B == "rego") {
									window.location.href = $www
								} else {
									if (B == "mycart") {
										window.location.href = $www
												+ "/mycart/index.html"
									} else {
										if (B == "myUser") {
											window.location.href = $www
													+ "/user/index.html"
										} else {
											if (B == "PostSingleList") {
												window.location.href = $www
														+ "/user/PostSingleList.html"
											} else {
												if (B == "auth") {
													window.location.href = $www
															+ "/referAuth.html"
												} else {
													if (B == "cartPay") {
														window.location.href = $www
																+ "/cartPay/index.html"
													} else {
														if (B == "userRecharge") {
															window.location.href = $www
																	+ "/user/UserRecharge.html"
														} else {
															location.replace(B)
														}
													}
												}
											}
										}
									}
								}
								SetCookieByExpires("loginUser", E, 365)
							}
						}
					}
				};
				$.post("/login/login.html", {
					userName : E,
					pwd : P(L)
				}, k)
			}
		};
		$("#LoginForm").keydown(function(k) {
			T();
			var l = (window.event) ? event.keyCode : k.keyCode;
			if (l == 32) {
				return false
			} else {
				if (l == 13) {
					d()
				}
			}
			return true
		});
		$("#btnSubmitLogin").bind("click", function() {
			d()
		});
		var i = {
			state : 0,
			listItems : [ {
				type : 0,
				src : "/Images/20150710114244511.jpg",
				url : "",
				alt : "#b390f4",
				width : 1190,
				height : 455,
				startTime : "2015-07-10 00:00:00",
				endTime : "2015-10-10 00:00:00"
			}, {
				type : 0,
				src : "/Images/20150710114222431.jpg",
				url : "",
				alt : "#5fd2a7",
				width : 1190,
				height : 455,
				startTime : "2015-07-10 00:00:00",
				endTime : "2015-10-10 00:00:00"
			}, {
				type : 0,
				src : "/Images/20150710114148673.jpg",
				url : "",
				alt : "#54b1ff",
				width : 1190,
				height : 455,
				startTime : "2015-07-10 00:00:00",
				endTime : "2015-10-10 00:00:00"
			} ]
		};
		var f = "";
		if (i.state == 0) {
			var j = i.listItems;
			if (j.length > 0) {
				var a = parseInt($.cookie("loginad"));
				var h = null;
				if (!isNaN(a) && a < (j.length - 1)) {
					h = j[a + 1];
					$.cookie("loginad", a + 1)
				} else {
					h = j[0];
					$.cookie("loginad", 0)
				}
				if (h != null && h.type == 0 && h.src != "") {
					var b = "";
					var g = h.alt;
					if (g.indexOf("#") > -1) {
						b = h.alt + " url(" + h.src + ") top center no-repeat;"
					} else {
						b = "url(" + h.src + ") top center no-repeat;"
					}
					$("#g_login").attr("style", "background:" + b);
					if (h.url != "") {
						$("#loadingPicBlock").html(
								'<a href="' + h.url + '" target="_blank"></a>')
					}
				}
			}
		}
		if (K.val() == "") {
			K.focus()
		}
	};
	QC
			.Login(
					{
						btnId : "qq_login_btn",
						scope : "get_user_info,add_share",
						size : "B_M"
					},
					function(a, b) {
						if (QC.Login.check()) {
							QC.Login
									.getMe(function(d, c) {
										$
												.ajax({
													url : "/register/authorizeIsExists.action",
													type : "POST",
													data : "openId="
															+ encodeURIComponent(d
																	.replace(
																			/(\/)/g,
																			"")),
													success : function(e) {
														if (e == "true") {
															$
																	.ajax({
																		type : "POST",
																		url : "/register/authorizeRegsiter.action",
																		data : "userName="
																				+ encodeURIComponent(a.nickname
																						.replace(
																								/(\/)/g,
																								""))
																				+ "&openId="
																				+ d
																				+ "&userFace="
																				+ a.figureurl_2,
																		success : function(
																				f) {
																			SetCookie(
																					"userId",
																					f.userId);
																			SetCookie(
																					"userName",
																					f.userName);
																			SetCookie(
																					"face",
																					f.faceImg);
																			SetCookie(
																					"loginType",
																					2);
																			window.location.href = "/register/qqUserInfoAuth.html"
																		}
																	})
														} else {
															SetCookie("userId",
																	e.userId);
															SetCookie(
																	"userName",
																	e.userName);
															SetCookie("face",
																	e.faceImg);
															SetCookie(
																	"loginType",
																	2);
															window.location.href = "/register/qqUserInfoAuth.html"
														}
													}
												})
									})
						}
					});
	var V = false;
	var Q = false;
	$("#wx_login_btn")
			.click(
					function() {
						SetCookie("qFromUrl", $("#hidLoginForward").val());
						if (/(msie\s|trident.*rv:)([\w.]+)/
								.test(navigator.userAgent.toLowerCase())
								&& parseInt($.browser.version) == 6) {
							window
									.open("https://open.weixin.qq.com/connect/qrconnect?appid="
											+ $("#hidWxAppID").val()
											+ "&redirect_uri="
											+ escape($www + "/wxback.html")
											+ "&response_type=code&scope=snsapi_login&state=wx#wechat_redirect")
						} else {
							var e = $("#wxLogin");
							var b = $("#LoginForm");
							var f = $("#a_return", e);
							var c = function() {
								if (Q) {
									return
								}
								Q = true;
								e.show();
								e.animate({
									right : 0
								}, {
									queue : false,
									duration : 500,
									complete : function() {
										f.unbind("click").bind("click",
												function() {
													d()
												})
									}
								})
							};
							var d = function() {
								if (!Q) {
									return
								}
								Q = false;
								e.animate({
									right : -390
								}, {
									queue : false,
									duration : 500,
									complete : function() {
									}
								})
							};
							var a = function() {
								if ($("#p_code").html().length == 0) {
									var g = new WxLogin({
										id : "p_code",
										appid : $("#hidWxAppID").val(),
										scope : "snsapi_login",
										redirect_uri : escape($www + "/wxback.html"),
										state : "wx",
										style : "",
										href : "https://skin.1yyg.com/Passport/css/layout.css?date=0617"
									})
								}
								c()
							};
							if (V) {
								a()
							} else {
								$
										.getScript(
												"https://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js",
												function() {
													a();
													V = true
												})
							}
						}
					});
	$.getScript(G + "/js/ajaxfun.js?date=20130123", M)
});
