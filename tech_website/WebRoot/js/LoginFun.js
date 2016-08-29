$(function() {
	var t = "https://skin.1yyg.com";
	var o = $("#username");
	var h = $("#pwd");
	var p = $("#j-tips-wrap");
	var v = null;
	var n = null;
	var k = false;
	var b = /\w+(@{1})$/i, g = [ "qq.com", "vip.qq.com", "sina.com",
			"foxmail.com", "139.com", "sohu.com" ], y = "", x = 0;
	var j = function(B) {
		var A = function(E, F, C) {
			var D = new RegExp(F, "g");
			return E.replace(D, C)
		};
		var B = escape(B);
		B = A(B, "\\+", "%2B");
		B = A(B, "/", "%2F");
		return B
	};
	var c = function(B) {
		var A = /^1\d{10}$/;
		if (!A.exec(B)) {
			return false
		} else {
			return true
		}
	};
	var a = function(A) {
		var B = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if (B.test(A)) {
			return true
		}
		return false
	};
	var q = function(B) {
		var A = /^[\S]{6,20}$/;
		if (!A.exec(B)) {
			return false
		}
		return true
	};
	var z = function() {
		try {
			if ($.browser.msie && parseInt($.browser.version) == 6) {
				Base.getScript(t + "/JS/iepng.js?date=20150215", function() {
					if (EvPNG != null && EvPNG != undefined) {
						EvPNG.fix(".transparent-png")
					}
				})
			}
		} catch (A) {
		}
	};
	var l = function(A, B) {
		if (A) {
			$(A).parent().addClass("enter-focus")
		}
		$("#dd_error_msg").html(
				'<i class="passport-icon transparent-png"></i>' + B).show(5,
				function() {
					z()
				});
		$("#btnSubmitLogin").removeClass("letter-noSpac").html("登录");
		k = false
	};
	var e = function(A) {
		if (A) {
			$(A).parent().removeClass("enter-focus")
		}
		$("#dd_error_msg").hide()
	};
	var s = null;
	var w = null;
	w = setInterval(function() {
		if (o.val() != "") {
			if (!c(o.val()) && !a(o.val())) {
				o.val("");
				o.siblings("em").show();
				h.val("");
				h.siblings("em").show();
				h.blur()
			} else {
				o.siblings("em").hide()
			}
		}
		if (h.val() != "") {
			if (!q(h.val())) {
				h.val("");
				if (o.val() == "") {
					h.blur()
				}
				h.siblings("em").show()
			} else {
				h.siblings("em").hide()
			}
		}
	}, 200);
	var f = function() {
		if (w != null) {
			clearInterval(w);
			w = null;
			if (s != null) {
				clearTimeout(s);
				s = null
			}
		}
	};
	s = setTimeout(function() {
		f()
	}, 5000);
	o.focus(function() {
		$(this).parent().addClass("enter-focus")
	}).blur(function() {
		if (w != null) {
			return
		}
		$(this).parent().removeClass("enter-focus");
		if ($(this).val() == "") {
			$(this).siblings("em").show();
			$(this).css("color", "#bbbbbb");
			$("#dd_error_msg").hide();
			return
		}
		u.checkUserName(false)
	}).click(function(A) {
		f();
		A = A || window.event;
		A.stopPropagation();
		if (p.children().length > 0 && o.val().indexOf("@") !== -1) {
			p.show()
		}
	}).keydown(function() {
		$(this).css("color", "#333333");
		o.siblings("em").hide();
		$("#dd_error_msg").hide()
	});
	if (o.val() == "") {
		o.siblings("em").bind("click", function() {
			o.focus()
		}).show()
	}
	o.keyup(function(H) {
		H = H || window.event;
		H.stopPropagation();
		var B = this.value, F = 0, E = 0, G = g.length, A = H.keyCode, C = "";
		if (b.test(B)) {
			for (; F < G; F++) {
				y += "<li class='j-tips'>" + (B + g[F]) + "</li>"
			}
			p.show().empty().html(y);
			y = ""
		} else {
			if (B.indexOf("@") !== -1) {
				var I = B.indexOf("@"), D = B.slice(0, I + 1), K = B
						.slice(I + 1);
				for (; E < G; E++) {
					if (g[E].indexOf(K) !== -1) {
						C += "<li class='j-tips'>" + (D + g[E]) + "</li>"
					}
				}
				if (C !== "") {
					p.show().empty().html(C)
				} else {
					p.hide();
					x = 0
				}
			} else {
				x = 0;
				p.hide()
			}
		}
		if (p.css("display") === "block") {
			var J = p.find("li");
			switch (A) {
			case 13:
				if (x > 0) {
					o.val(J.eq(x - 1).text().trim()).blur();
					p.hide();
					h.focus();
					x = 0
				}
				break;
			case 40:
				x = x + 1 > J.length ? 1 : x + 1;
				J.removeClass("selected").eq(x - 1).addClass("selected");
				break;
			case 38:
				x = x - 1 < 1 ? J.length : x - 1;
				J.removeClass("selected").eq(x - 1).addClass("selected");
				break;
			default:
				break
			}
		}
	});
	p.click(function(B) {
		B = B || window.event;
		B.stopPropagation();
		var A = B.target || B.srcElement;
		if (A.tagName.toLowerCase() === "li") {
			o.val($(A).text().trim()).blur();
			h.focus()
		}
		$(this).hide()
	});
	$(window).click(function(B) {
		if (p.css("display") !== "block") {
			return
		}
		B = B || window.event;
		var A = B.target || B.srcElement, C = A.id.toLowerCase();
		if (C !== "j-tips-wrap" || C !== "username") {
			p.hide()
		}
	});
	h.focus(function() {
		$(this).parent().addClass("enter-focus");
		p.hide()
	}).blur(function() {
		if (w != null) {
			return
		}
		$(this).parent().removeClass("enter-focus");
		if ($(this).val() == "") {
			$(this).siblings("em").show();
			$(this).css("color", "#bbbbbb");
			$("#dd_error_msg").hide();
			return
		}
		u.checkPwd(false)
	}).keydown(function() {
		$(this).css("color", "#333333");
		$(this).siblings("em").hide();
		$("#dd_error_msg").hide()
	});
	if (h.val() == "") {
		h.siblings("em").bind("click", function() {
			h.focus()
		}).show()
	}
	var r = function() {
		this.checkUserName = function(A) {
			v = o.val().trim();
			if (v == "") {
				if (A) {
					o.focus()
				}
				l(o, "请输入手机号或邮箱地址");
				return false
			} else {
				if (!c(v) && !a(v)) {
					if (A) {
						o.focus()
					}
					l(o, "请输入正确的手机号或邮箱地址");
					return false
				}
			}
			e(o);
			return true
		};
		this.checkPwd = function(A) {
			n = h.val().trim();
			if (n == "") {
				if (A) {
					h.focus()
				}
				l(h, "请输入登录密码");
				return false
			} else {
				if (!q(n)) {
					if (A) {
						h.focus()
					}
					l(h, "登录密码为6-20长度的字符");
					return false
				}
			}
			e(h);
			return true
		}
	};
	var u = new r();
	var m = function() {
		var A = $("#hidAccount").val();
		if (A != "" && (c(A) || a(A))) {
			o.siblings("em").hide();
			o.val(A);
			if (h.val() == "") {
				h.focus()
			}
		} else {
			var C = $.cookie("_uName");
			if (C != null && (c(C) || a(C))) {
				o.siblings("em").hide();
				o.val(C);
				if (h.val() == "") {
					h.focus()
				}
			}
		}
		if (h.val() != "") {
			h.siblings("em").hide()
		}
		var B = function() {
			f();
			if (k) {
				return
			}
			k = true;
			if (u.checkUserName(true) && u.checkPwd(true)) {
				$("#btnSubmitLogin").addClass("letter-noSpac").html("正在登录...");
				var D = function(E) {
					var F = E.state;
					if (F == 0) {
						$.cookie("_uName", v, {
							domain : "1yyg.com",
							expires : 365
						});
						location.replace($("#hidLoginForward").val())
					} else {
						if (F == 1 && E.num == -1) {
							l(h, "登录密码错误，请重新输入")
						} else {
							if (F == 1 && E.num == -2) {
								l(o, "此账号不存在，请重新输入")
							} else {
								if (F == 1 && E.num == -3) {
									l(o, "此账号已被冻结，请与客服联系！")
								} else {
									if (F == 1 && E.num == -4) {
										l(o, "此账号未激活，请与客服联系！")
									} else {
										if (F == 1 && E.num == -5) {
											l(h, "密码被系统锁定，请点击“忘记密码”重新设置！")
										} else {
											if (F == 3) {
												l(null, "失败次数超限，被冻结5分钟！")
											}
										}
									}
								}
							}
						}
					}
				};
				GetJPData("", "userlogin", "name=" + v + "&pwd=" + j(n), D)
			}
		};
		$("#LoginForm").keydown(function(E) {
			f();
			var D = (window.event) ? event.keyCode : E.keyCode;
			if (D == 32) {
				return false
			} else {
				if (D == 13) {
					B()
				}
			}
			return true
		}).bind("click", function() {
			f()
		});
		$("#btnSubmitLogin").bind("click", function() {
			B()
		});
		GetJPData("https://passport.1yyg.com", "getadbysortid", "ID=9",
				function(G) {
					var J = "";
					if (G.state == 0) {
						var F = G.listItems;
						if (F.length > 0) {
							var E = parseInt($.cookie("loginad"));
							var H = null;
							if (!isNaN(E) && E < (F.length - 1)) {
								H = F[E + 1];
								$.cookie("loginad", E + 1, {
									expires : 365
								})
							} else {
								H = F[0];
								$.cookie("loginad", 0, {
									expires : 365
								})
							}
							if (H != null && H.type == 0 && H.src != "") {
								var D = "";
								var I = H.alt.reAjaxStr();
								if (I.indexOf("#") > -1) {
									D = H.alt.reAjaxStr()
											+ " url("
											+ H.src.replace("http://img",
													"https://safeimg")
											+ ") top center no-repeat;"
								} else {
									D = "url("
											+ H.src.replace("http://img",
													"https://safeimg")
											+ ") top center no-repeat;"
								}
								$("#g_login").attr("style", "background:" + D);
								if (H.url != "") {
									$("#loadingPicBlock").html(
											'<a href="' + H.url
													+ '" target="_blank"></a>')
								}
							}
						}
					}
				});
		if (o.val() == "") {
			o.focus()
		}
	};
	$("#qq_login_btn")
			.click(
					function() {
						$.cookie("qFromUrl", $("#hidLoginForward").val());
						window
								.open("http://openapi.qzone.qq.com/oauth/show?which=ConfirmPage&response_type=code&client_id="
										+ $("#hidQQAppID").val()
										+ "&redirect_uri="
										+ escape("https://passport.1yyg.com/qcback.html")
										+ "&state=qq&scope=get_user_info&display=pc")
					});
	var d = false;
	var i = false;
	$("#wx_login_btn")
			.click(
					function() {
						$.cookie("qFromUrl", $("#hidLoginForward").val());
						if ($.browser.msie && parseInt($.browser.version) == 6) {
							window
									.open("https://open.weixin.qq.com/connect/qrconnect?appid="
											+ $("#hidWxAppID").val()
											+ "&redirect_uri="
											+ escape("https://passport.1yyg.com/qcback.html")
											+ "&response_type=code&scope=snsapi_login&state=wx#wechat_redirect")
						} else {
							var A = $("#wxLogin");
							var F = $("#LoginForm");
							var C = $("#a_return", A);
							var E = function() {
								if (i) {
									return
								}
								i = true;
								A.show();
								A.animate({
									right : 0
								}, {
									queue : false,
									duration : 500,
									complete : function() {
										C.unbind("click").bind("click",
												function() {
													D()
												})
									}
								})
							};
							var D = function() {
								if (!i) {
									return
								}
								i = false;
								A.animate({
									right : -390
								}, {
									queue : false,
									duration : 500,
									complete : function() {
									}
								})
							};
							var B = function() {
								if ($("#p_code").html().length == 0) {
									var G = new WxLogin(
											{
												id : "p_code",
												appid : $("#hidWxAppID").val(),
												scope : "snsapi_login",
												redirect_uri : escape("https://passport.1yyg.com/qcback.html"),
												state : "wx",
												style : "",
												href : "https://skin.1yyg.com/Passport/css/layout.css?date=0617"
											})
								}
								E()
							};
							if (d) {
								B()
							} else {
								Base
										.getScript(
												"https://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js",
												function() {
													B();
													d = true
												})
							}
						}
					});
	Base.getScript(t + "/JS/AjaxFun.js?date=20130123", m)
});