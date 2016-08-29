$(document)
		.ready(
				function() {
					var B = $skin;
					function w(a, b) {
						document.cookie = a + "=" + escape(b)
								+ ";id=1ypg;path=/;domain=" + $domain
					}
					function N(b) {
						var a = document.cookie.match(new RegExp("(^| )" + b
								+ "=([^;]*)(;|$)"));
						if (a != null) {
							return unescape(a[2])
						}
						return null
					}
					w("isCookie", "yes");
					var G = N("isCookie");
					if (G == null || G != "yes") {
						alert("\u6e29\u99a8\u63d0\u793a\uff1a\u60a8\u7684\u6d4f\u89c8\u5668\u5f53\u524d\u4e0d\u652f\u6301Cookies\u529f\u80fd\uff0c\u8bf7\u60a8\u542f\u7528\u6d4f\u89c8\u5668Cookie\u529f\u80fd\u6216\u66f4\u6362\u6d4f\u89c8\u5668\u3002");
						window.location.href = $www + "/help/openCookie.html";
						return
					}
					var S = function(b) {
						var a = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
						return a.test(b)
					};
					var C = function(a) {
						var b = /^[\S]{6,20}$/;
						return b.test(a)
					};
					var R = function(a) {
						var b = /^1\d{10}$/;
						return b.test(a)
					};
					var L = function(a) {
						var b = /^[a-zA-Z0-9]{3,6}$/;
						return b.test(a)
					};
					var J = function(a) {
						return '<p class="tips_txt">' + a + "</p>"
					};
					var H = function(a) {
						return '<p class="tips_txt_Wrong"><s></s>' + a + "</p>"
					};
					var T = '<p class="tips_txt_yes"><s></s></p>';
					var x = function(b, a) {
						return '<div class="Pas_tips_StrWeak"><p class="password_tips0'
								+ b
								+ '"><span><em></em></span><i>'
								+ (b == 3 ? "\u5f3a" : b == 2 ? "\u4e2d"
										: "\u5f31")
								+ "</i></p><b>"
								+ a
								+ "</b></div>"
					};
					var F = {
						userAccount : {
							normal : J("\u63a8\u8350\u4f7f\u7528\u624b\u673a\u53f7\u6ce8\u518c\uff0c\u662f\u6536\u5230\u83b7\u5f97\u5546\u54c1\u4fe1\u606f\u7684\u91cd\u8981\u9014\u5f84"),
							empty : H("\u8bf7\u8f93\u5165\u90ae\u7bb1\u5730\u5740\u6216\u624b\u673a\u53f7\u7801"),
							ishad : H("\u5df2\u88ab\u6ce8\u518c\uff0c\u6362\u4e00\u4e2a\u5427"),
							error : H("\u90ae\u7bb1\u5730\u5740\u6216\u624b\u673a\u53f7\u7801\u4e0d\u6b63\u786e"),
							ismany : H("\u6ce8\u518c\u5e10\u53f7\u8fc7\u4e8e\u9891\u7e41\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5"),
							ok : T
						},
						password : {
							normal : J("\u5bc6\u7801\u75316\uff5e20\u4f4d\u534a\u89d2\u5b57\u7b26\uff08\u5b57\u6bcd\u3001\u6570\u5b57\u3001\u7b26\u53f7\uff09\u7ec4\u6210\uff0c\u533a\u5206\u5927\u5c0f\u5199"),
							empty : H("\u5bc6\u7801\u957f\u5ea6\u4e0d\u6b63\u786e\uff0c\u5e94\u4e3a6\uff5e20\u4e2a\u4e0d\u542b\u7a7a\u683c\u7684\u5b57\u7b26"),
							error : H("\u5bc6\u7801\u957f\u5ea6\u4e0d\u6b63\u786e\uff0c\u5e94\u4e3a6\uff5e20\u4e2a\u4e0d\u542b\u7a7a\u683c\u7684\u5b57\u7b26"),
							ok1 : x(
									1,
									"\u5bc6\u7801\u592a\u5f31\u5566\uff0c\u8bd5\u8bd5\u6570\u5b57\u548c\u5b57\u6bcd\u7684\u7ec4\u5408\u5427\uff01"),
							ok2 : x(2,
									"\u590d\u6742\u5ea6\u8fd8\u884c\uff0c\u518d\u52a0\u5f3a\u4e00\u4e0b\uff1f"),
							ok3 : x(3,
									"\u5bc6\u7801\u5f3a\u5ea6\u5f88\u597d\uff0c\u8bf7\u7262\u8bb0\uff01")
						},
						passwordAgain : {
							normal : J("\u8bf7\u518d\u6b21\u8f93\u5165\u5bc6\u7801"),
							empty : H("\u8bf7\u518d\u6b21\u8f93\u5165\u5bc6\u7801"),
							error : H("\u4e24\u6b21\u8f93\u5165\u7684\u5bc6\u7801\u4e0d\u4e00\u81f4\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165"),
							ok : T
						},
						regSN : {
							normal : J("\u8bf7\u8f93\u5165\u56fe\u7247\u4e2d\u7684\u9a8c\u8bc1\u7801"),
							empty : H("\u8bf7\u8f93\u5165\u56fe\u7247\u4e2d\u7684\u9a8c\u8bc1\u7801"),
							error : H("\u9a8c\u8bc1\u7801\u4e0d\u6b63\u786e\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165"),
							ok : T
						}
					};
					var A = "";
					var P = "";
					var D = "";
					var O = "";
					var y = "";
					var z = 0;
					var E = 0;
					var K = 0;
					var Q = 2;
					var M = true;
					var I = function() {
						var c = $("#btnSubmitRegister");
						var b = $("#showVerifySN");
						var e = function(i) {
							if (!i) {
								return
							}
							$("#" + i).attr("class", "login_input_textCur")
									.parent().next().html(F[i].normal)
						};
						var h = function(l) {
							var i = $("#" + l);
							if (i.length == 0) {
								return
							}
							var m = i.val();
							var k = i.parent().next();
							var n = "";
							switch (l) {
							case "userAccount":
								if (m == "") {
									A = "";
									n = F[l].empty;
									z = 0
								} else {
									if (!(R(m) || S(m))) {
										n = F[l].error;
										A = m;
										z = 1
									} else {
										if (m != A) {
											A = m;
											k = null;
											a(m, l)
										} else {
											if (z == 2) {
												n = F[l].ishad
											} else {
												if (z == 3) {
													n = F[l].ok
												} else {
													if (z == 4) {
														n = F[l].ismany
													}
												}
											}
										}
									}
								}
								break;
							case "password":
								if (m == "") {
									n = F[l].empty;
									P = m;
									E = 0
								} else {
									if (!C(m)) {
										n = F[l].error;
										P = m;
										E = 1
									} else {
										var j = function(o) {
											var p = 0;
											if (o.match(/[a-z]/ig)) {
												p++
											}
											if (o.match(/[0-9]/ig)) {
												p++
											}
											if (o.match(/(.[^a-z0-9])/ig)) {
												p++
											}
											if (p == 3 && o.length < 8) {
												p--
											}
											return p
										};
										n = F[l]["ok" + j(m)];
										P = m;
										E = 2;
										if (D != "") {
											h("passwordAgain")
										}
									}
								}
								break;
							case "passwordAgain":
								if (m == "") {
									n = P == "" ? "" : F[l].empty;
									D = "";
									K = 0
								} else {
									if (m != P) {
										n = F[l].error;
										D = m;
										K = 1
									} else {
										if (!C(m)) {
											n = "";
											D = m;
											K = 1
										} else {
											n = F[l].ok;
											D = m;
											K = 2
										}
									}
								}
								break;
							case "regSN":
								if (m == "") {
									y = "";
									n = F[l].empty;
									Q = 0
								} else {
									if (!L(m)) {
										n = F[l].error;
										y = m;
										Q = 1
									} else {
										y = m;
										Q = 2
									}
								}
								break;
							default:
								break
							}
							if (k) {
								k.html(n)
							}
							i.attr("class", "login_input_text")
						};
						var d = function() {
							if (!isLoaded) {
								return
							}
							var j = null;
							$
									.ajax({
										url : "/register/regsiter.html",
										type : "post",
										data : "str=" + A + "&userPwd=" + P,
										beforeSend : i,
										success : function(l) {
											if (l == "true") {
												var k = escape($(
														"#hidRegisterForward")
														.val());
												if (R(A)) {
													location
															.replace("/register/mobilecheck.html?str="
																	+ escape(A)
																	+ "&forward="
																	+ k)
												} else {
													location
															.replace("/register/emailcheck.html?str="
																	+ escape(A)
																	+ "&forward="
																	+ k)
												}
											} else {
												alert("\u6CE8\u518C\u5931\u8D25\uFF0C\u8BF7\u7A0D\u540E\u518D\u8BD5!");
												window.location.href = $www
											}
											isLoaded = true
										},
										error : function() {
											c
													.html(
															"\u540c\u610f\u4ee5\u4e0b\u534f\u8bae\uff0c\u63d0\u4ea4")
													.attr("class",
															"Mem_orangebut");
											var k = "\u6d4b\u8bd5\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\uff01"
										}
									});
							function i() {
								isLoaded = false;
								c
										.html(
												"\u6b63\u5728\u63d0\u4ea4\uff0c\u8bf7\u7a0d\u540e")
										.attr("class", "login_Email_butClick")
							}
						};
						var a = function(j, l) {
							if (!isLoaded) {
								return
							}
							var i = $("#" + l);
							var k = i.parent().next();
							var m = N("_regnum");
							if (m != null && parseInt(m) >= 3) {
								z = 4;
								k.html(F[l].ismany);
								return
							}
							$
									.ajax({
										url : "/user/isCheckName.html",
										type : "post",
										data : "userName=" + j,
										beforeSend : n,
										success : function(o) {
											if (o == "true") {
												z = 3;
												k.html(F[l].ok)
											} else {
												k.html(F[l].ishad);
												z = 2
											}
											isLoaded = true;
											window.defaultStatus = "";
											c.bind("click", g)
										},
										error : function() {
											alert("\u8FDE\u63A5\u670D\u52A1\u5668\u5931\u8D25\uFF0C\u8BF7\u7A0D\u540E\u518D\u8BD5\uFF01");
											window.location.href = $www
										}
									});
							function n() {
								isLoaded = false;
								window.defaultStatus = "\u6b63\u5728\u68c0\u9a8c\u6570\u636e\uff0c\u8bf7\u7a0d\u540e...";
								c.unbind("click");
								k
										.html('<p class="tips_txt_loding"><i><img src="/Images/loding.gif" border=0 alt=""></i></p>')
							}
						};
						var g = function() {
							if (!isLoaded) {
								return
							}
							M = true;
							if (z != 3) {
								h("userAccount");
								M = false
							}
							if (E != 2) {
								h("password");
								M = false
							}
							if (K != 2) {
								h("passwordAgain");
								M = false
							}
							if (Q != 2) {
								h("regSN");
								M = false
							}
							if (M) {
								d()
							}
						};
						var f = function() {
							$("input[name*='txt']")
									.each(
											function() {
												$(this)
														.bind("focus",
																function() {
																	e(this.id)
																})
														.bind("blur",
																function() {
																	h(this.id)
																})
														.bind(
																"keydown",
																function(i) {
																	var j = (window.event) ? event.keyCode
																			: i.keyCode;
																	if (j == 32) {
																		return false
																	} else {
																		if (j == 13) {
																			this
																					.blur();
																			if (this.id == "passwordAgain") {
																				c
																						.click()
																			} else {
																				$(
																						"#"
																								+ this.id)
																						.parent()
																						.parent()
																						.next()
																						.find(
																								"input[name*='txt']")
																						.focus()
																			}
																			return false
																		}
																	}
																	return true
																}).attr(
																"disabled",
																false)
											});
							c.bind("click", g);
							$("#userAccount").focus()
						};
						f();
						isLoaded = true
					};
					I()
				});
