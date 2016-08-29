$(document)
		.ready(
				function() {
					var d = $skin;
					var c = function() {
						var a = function(e) {
							var f = /^[0-9a-z]{6,}$/;
							return f.test(e)
						};
						var A = function(e, f) {
							$.PageDialog('<div class="mAltFail"><s></s>' + e
									+ "</div>", {
								W : 300,
								H : 60,
								close : false,
								autoClose : true,
								submit : function() {
									if (f) {
										f()
									}
								}
							})
						};
						var E = function(e, f) {
							$.PageDialog('<div class="mAltOK"><s></s>' + e
									+ "</div>", {
								W : 300,
								H : 60,
								close : false,
								autoClose : true,
								submit : function() {
									if (f) {
										f()
									}
								}
							})
						};
						var C = $("#btnSubmit");
						var D = $("#hidAccount").val();
						var x = $("#txtCode");
						var G = $("#checkSN").val();
						var y = $("#hidType").val() == "1" ? true : false;
						var H = function(e) {
							x.parent().addClass("enter-focus error-text");
							x.siblings(".orange").html(
									'<i class="passport-icon transparent-png"></i>'
											+ e).show();
							if (x.val() == "") {
								x.siblings("em").hide()
							}
							F = false
						};
						var z = function() {
							x.parent().removeClass("error-text");
							x.siblings(".orange").hide()
						};
						var b = false;
						var B = function() {
							var e = $("#btnSend");
							e.unbind("click").parent().addClass(
									"countdown-time");
							var f = 120;
							var g = function() {
								if (f < 2) {
									b = false;
									e.bind("click", u).html(
											"\u91cd\u65b0\u53d1\u9001")
											.parent().removeClass(
													"countdown-time");
									return
								} else {
									f--;
									e.html("\u91cd\u65b0\u53d1\u9001(" + f
											+ ")")
								}
								setTimeout(g, 1000)
							};
							g()
						};
						var u = function() {
							if (b) {
								return
							}
							b = true;
							if (y) {
								$
										.ajax({
											url : "/register/SendRegisterMail.html",
											data : "mail=" + D + "&isVerify="
													+ G,
											success : function(e) {
												if (e == "2") {
													E("\u9a8c\u8bc1\u7801\u5df2\u53d1\u9001\u81f3\u60a8\u90ae\u7bb1\uff0c\u8bf7\u6ce8\u610f\u67e5\u6536\uff01");
													B()
												} else {
													if (e == "3") {
														A("\u5bf9\u4e0d\u8d77\uff0c\u8bf7\u4e0d\u8981\u9891\u7e41\u83b7\u53d6\u9a8c\u8bc1\u7801\uff01")
													} else {
														A("\u9a8c\u8bc1\u7801\u53d1\u9001\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\uff01")
													}
												}
											},
											error : function() {
												A("\u9a8c\u8bc1\u7801\u53d1\u9001\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\uff01")
											}
										})
							} else {
								$
										.ajax({
											url : "/register/regSendMes.html",
											data : "phone=" + D + "&isVerify="
													+ G,
											success : function(e) {
												if (e == "0") {
													E("\u9a8c\u8bc1\u7801\u5df2\u53d1\u9001\uff0c\u8bf7\u6ce8\u610f\u67e5\u6536\uff01");
													B()
												} else {
													if (e == "2") {
														A("\u5bf9\u4e0d\u8d77\uff0c\u8bf7\u4e0d\u8981\u9891\u7e41\u83b7\u53d6\u9a8c\u8bc1\u7801\uff01")
													} else {
														A("\u9a8c\u8bc1\u7801\u53d1\u9001\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\uff01")
													}
												}
											}
										})
							}
						};
						u();
						var F = false;
						var I = function() {
							if (F) {
								return
							}
							F = true;
							C
									.addClass("letter-noSpac")
									.html(
											"\u6b63\u5728\u9a8c\u8bc1\uff0c\u8bf7\u7a0d\u540e...");
							$
									.post(
											"/register/saveregsiter.html",
											{
												key : x.val().trim(),
												isVerify : G
											},
											function(e) {
												if (e == "0") {
													location
															.replace("/register/registerok.html?isVerify="
																	+ G);
													return
												} else {
													if (e == "3") {
														H('\u8d26\u53f7\u5df2\u6ce8\u518c\uff0c\u8bf7\u66f4\u6362\u6216<a href="/login/index.html?account='
																+ escape(v)
																+ '" class="blue">\u7acb\u5373\u767b\u5f55</a>')
													} else {
														H("\u9a8c\u8bc1\u7801\u8f93\u5165\u4e0d\u6b63\u786e\u6216\u5df2\u5931\u6548")
													}
												}
												C.removeClass("letter-noSpac")
														.html("\u63d0\u4ea4")
											})
						};
						var t = function() {
							var e = x.val().trim();
							if (e == "") {
								H("\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801\uff01")
							} else {
								if (a(e)) {
									I()
								} else {
									H("\u9a8c\u8bc1\u7801\u8f93\u5165\u4e0d\u6b63\u786e")
								}
							}
						};
						var w = function() {
							if (G != "") {
								B();
								C.bind("click", t);
								x
										.focus(
												function() {
													$(this).parent().addClass(
															"enter-focus")
												})
										.blur(
												function() {
													$(this)
															.parent()
															.removeClass(
																	"enter-focus");
													if ($(this).val() == "") {
														$(this).siblings("em")
																.show();
														$(this).css("color",
																"#bbbbbb");
														z()
													}
												}).keydown(function() {
											$(this).siblings("em").hide();
											$(this).css("color", "#333333")
										});
								if (x.val() == "") {
									x.siblings("em").bind("click", function() {
										x.focus()
									}).show()
								}
							} else {
								location.reload()
							}
						};
						w();
						$("div.security-code-con").keydown(function(e) {
							var f = (window.event) ? event.keyCode : e.keyCode;
							if (f == 32) {
								return false
							} else {
								if (f == 13) {
									C.trigger("click")
								}
							}
							return true
						})
					};
					$.getScript(d + "/js/ajaxfun.js?date=20130123", c)
				});
