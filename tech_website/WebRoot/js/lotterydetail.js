var CBLFun = null;
var CBLRecordFun = null;
var CBLPageFun = null;
var CBLResultFun = null;
var PagePOPLoginOK = null;
$(function() {
	var e = $www;
	var d = function() {
		var L = $("#hidCodeID").val();
		var B = $("#hidGoodsID").val();
		var c = $("#hidLatestlotteryUserId").val();
		var w = $("#hidIsShowCalc").val();
		var K = function(k) {
			if (k && k.stopPropagation) {
				k.stopPropagation()
			} else {
				window.event.cancelBubble = true
			}
		};
		var D = function(k) {
			k = k || window.event;
			if (k && k.preventDefault) {
				k.preventDefault()
			} else {
				window.event.returnValue = false
			}
		};
		var F = function(k, l) {
			$.PageDialog('<div class="mAltFail"><s></s>' + k + "</div>", {
				W : 255,
				H : 60,
				close : false,
				autoClose : true,
				submit : function() {
					if (l) {
						location.reload()
					}
				}
			})
		};
		var E = function(k, l) {
			$.PageDialog('<div class="mAltOK"><s></s>' + k + "</div>", {
				W : 175,
				H : 60,
				close : false,
				autoClose : true,
				submit : function() {
					if (l) {
						location.reload()
					}
				}
			})
		};
		var v = $("#divMain");
		var y;
		var H = false;
		var x = function() {
			$
					.getScript(
							e + "/js/jquery.jscrollpane.js?date=20150513",
							function() {
								if ($("#li_more").length > 0) {
									$("#li_more,#li_more1")
											.bind(
													"click",
													function(l) {
														K(l);
														if ($("#ng_result_frame").length > 0) {
															$(
																	"#ng_result_frame")
																	.hide();
															H = false
														}
														var k = v.height();
														var q = 0;
														var p = function() {
															this.TotalCount = 0;
															this.PageMax = 100;
															this.CurrentIndex = 1;
															this.PageCount = 0;
															var r = false;
															var s = {
																FIdx : 1,
																EIdx : this.PageMax,
																IsCount : 1,
																goodsID : B,
																codeID : L
															};
															var t = function() {
																var u = function() {
																	var O = "";
																	O += "id="
																			+ s.goodsID;
																	O += "&codeID="
																			+ s.codeID;
																	O += "&pageNo="
																			+ s.FIdx;
																	O += "&pageSize="
																			+ s.EIdx;
																	O += "&IsCount="
																			+ s.IsCount;
																	return O
																};
																var N = function(
																		T) {
																	if (r) {
																		return
																	}
																	r = true;
																	if (T.length > 0) {
																		if (s.isCount == 1) {
																			s.isCount = 0;
																			CBLFun.TotalCount = T[0].spellbuyCount;
																			CBLFun.PageCount = parseInt(CBLFun.TotalCount
																					/ CBLFun.PageMax);
																			if (CBLFun.TotalCount
																					% CBLFun.PageMax > 0) {
																				CBLFun.PageCount++
																			}
																			if (CBLFun.PageCount < 1) {
																				CBLFun.PageCount = 1
																			}
																		}
																		if (CBLFun.TotalCount > 0) {
																			var S = "";
																			var U = T;
																			for (var P = 0; P < U.length; P++) {
																				var V = U[P];
																				if (V.status == 0) {
																					if (V.spellbuyProductId == L) {
																						S += '<li class="current"><a href="/products/'
																								+ V.spellbuyProductId
																								+ '.html">\u7b2c'
																								+ V.productPeriod
																								+ '\u671f<b>\u8fdb\u884c\u4e2d<span class="dotting"></span></b></a></li>'
																					} else {
																						S += '<li><a class="orange" href="/products/'
																								+ V.spellbuyProductId
																								+ '.html">\u7b2c'
																								+ V.productPeriod
																								+ '\u671f<b>\u8fdb\u884c\u4e2d<span class="dotting"></span></b></a></li>'
																					}
																				} else {
																					if (V.spellbuyProductId == L) {
																						S += '<li class="current"><a href="/products/'
																								+ V.spellbuyProductId
																								+ '.html">\u7b2c'
																								+ V.productPeriod
																								+ "\u671f</a></li>"
																					} else {
																						S += '<li><a href="/products/'
																								+ V.spellbuyProductId
																								+ '.html">\u7b2c'
																								+ V.productPeriod
																								+ "\u671f</a></li>"
																					}
																				}
																			}
																			if (CBLFun.CurrentIndex == 1) {
																				y
																						.html('<ul class="ng-pt-inner clearfix">'
																								+ S
																								+ "</ul>")
																			} else {
																				y
																						.find(
																								"ul")
																						.append(
																								S)
																			}
																			if (CBLFun.TotalCount > q) {
																				var Q = $("#ng_bg_top");
																				var R = $(
																						"#ng_bg_bottom")
																						.show();
																				var W = y
																						.jScrollPane({
																							verticalDragMinHeight : 15
																						});
																				W
																						.unbind(
																								"scroll")
																						.bind(
																								"scroll",
																								function(
																										O) {
																									var Y = parseInt($(
																											".jspTrack",
																											y)
																											.css(
																													"height"));
																									var Z = parseInt($(
																											".jspDrag",
																											y)
																											.css(
																													"height"));
																									var X = parseInt($(
																											".jspDrag",
																											y)
																											.css(
																													"top"));
																									if (Z
																											+ X < Y) {
																										Q
																												.show();
																										R
																												.show()
																									} else {
																										if (Z
																												+ X >= (Y - 10)) {
																											Q
																													.show();
																											R
																													.hide();
																											CBLFun
																													.gotoPageIndex()
																										}
																									}
																									if (X == 0) {
																										Q
																												.hide();
																										R
																												.show()
																									}
																								});
																				W
																						.unbind(
																								"mousewheel")
																						.bind(
																								"mousewheel",
																								function(
																										X,
																										O) {
																									var Z = O > 0 ? "Up"
																											: "Down";
																									var Y = W
																											.scrollTop();
																									var aa = W[0].scrollHeight;
																									var ab = W
																											.height();
																									if (Y
																											+ ab >= aa
																											&& O < 0) {
																										D(X)
																									} else {
																										if (Y == 0
																												&& O > 0) {
																											D(X)
																										}
																									}
																								})
																			}
																		}
																		r = false
																	}
																};
																$
																		.get(
																				"/products/getGoodsPeriodPage.action",
																				u(),
																				function(
																						O) {
																					N(O)
																				})
															};
															this.gotoPageIndex = function() {
																if (CBLFun.CurrentIndex >= CBLFun.PageCount) {
																	return
																}
																CBLFun.CurrentIndex++;
																s.FIdx += 1;
																if (s.EIdx > CBLFun.TotalCount) {
																	s.EIdx = CBLFun.TotalCount
																}
																if (CBLFun.CurrentIndex > 1) {
																	s.isCount = 0
																} else {
																	s.isCount = 1
																}
																t()
															};
															this.initData = function() {
																s.FIdx = 1;
																s.EIdx = CBLFun.PageMax;
																s.isCount = 1;
																t()
															}
														};
														var m = function() {
															var r = '<div class="ng-show-past"><div class="ng-main clearfix"><div class="ng-period"><div class="ng-period-inner"><div class="select-wrap"><div class="input-wrapper"> <div class="label">\u76f4\u8fbe\u7b2c</div><div class="inp"><input id="txtPeriod" maxlength="7" value="\u8bf7\u8f93\u5165"></div><div class="unit">\u671f</div>  <a id="btnGo" href="javascript:;" class="fly ng-result-bg transparent-png"></a> </div></div><div id="div_ngpt" class="ng-pt" ><div class="ng-b-wait"></div></div><div id="ng_bg_top" class="ng-pt-bg ng-pt-top" style="display:none;"></div><div id="ng_bg_bottom" class="ng-pt-bg" style="display:none;"></div><a id="a_close" href="javascript:;" title="\u5173\u95ed" class="ng-frame-close ng-result-bg transparent-png">\u5173\u95ed</a></div></div></div></div>';
															return r
														};
														var o = function(s) {
															y = $("#div_ngpt",
																	s);
															var r = $(
																	"#a_close",
																	s);
															var t = $("#btnGo",
																	s);
															var N = $(
																	"#txtPeriod",
																	s);
															N
																	.bind(
																			"keyup",
																			function() {
																				var O = /^[1-9]\d*/
																						.exec(N
																								.val());
																				N
																						.val((O == null || O == undefined) ? ""
																								: O)
																			})
																	.keydown(
																			function(
																					O) {
																				var P = window.event ? event.keyCode
																						: O.keyCode;
																				if (P == 13) {
																					t
																							.trigger("click")
																				}
																			})
																	.bind(
																			"focus",
																			function() {
																				if ($(
																						this)
																						.val() == "\u8bf7\u8f93\u5165") {
																					$(
																							this)
																							.val(
																									"")
																				}
																				$(
																						this)
																						.css(
																								"color",
																								"#666666");
																				$(
																						this)
																						.parent()
																						.parent()
																						.addClass(
																								"current")
																			})
																	.bind(
																			"blur",
																			function() {
																				if ($(
																						this)
																						.val() == "") {
																					$(
																							this)
																							.val(
																									"\u8bf7\u8f93\u5165");
																					$(
																							this)
																							.css(
																									"color",
																									"#bbbbbb")
																				}
																				$(
																						this)
																						.parent()
																						.parent()
																						.removeClass(
																								"current")
																			});
															var u = function() {
																s
																		.animate(
																				{
																					marginTop : -1
																							* k
																							+ "px"
																				},
																				{
																					queue : false,
																					duration : 400,
																					complete : function() {
																						N
																								.val("\u8bf7\u8f93\u5165");
																						N
																								.css(
																										"color",
																										"#bbbbbb")
																					}
																				});
																return false
															};
															r.click(function() {
																u()
															});
															v
																	.bind(
																			"click",
																			function(
																					O) {
																				K(O)
																			});
															$("body").bind(
																	"click",
																	function() {
																		u()
																	});
															t
																	.bind(
																			"click",
																			function() {
																				if (N
																						.val() == "\u8bf7\u8f93\u5165") {
																					return
																				}
																				var P = parseInt(N
																						.val());
																				var O = parseInt($(
																						"#hidMostPeriod")
																						.val());
																				if (isNaN(P)) {
																					N
																							.val("");
																					return
																				}
																				if (P > 0
																						&& P <= O) {
																					var Q = function(
																							R) {
																						if (R.length > 0) {
																							if (R[0].spStatus == 0) {
																								location
																										.replace("/products/"
																												+ R[0].spellbuyProductId
																												+ ".html")
																							} else {
																								if (R[0].spStatus == 2) {
																									location
																											.replace("/products/"
																													+ R[0].spellbuyProductId
																													+ ".html")
																								} else {
																									location
																											.replace("/lotteryDetail/"
																													+ R[0].spellbuyProductId
																													+ ".html")
																								}
																							}
																						}
																					};
																					$
																							.get(
																									"/products/getGoodsPeriodInfo.action",
																									{
																										id : B,
																										startDate : P
																									},
																									function(
																											R) {
																										Q(R)
																									})
																				} else {
																					N
																							.val("")
																				}
																			});
															q = Math
																	.floor(y
																			.height() / 47) * 7;
															CBLFun = new p();
															CBLFun.initData()
														};
														var n = function() {
															var r = v
																	.children("div.ng-show-past");
															if (r.length == 0) {
																v.prepend(m());
																r = v
																		.children("div.ng-show-past");
																r
																		.css(
																				{
																					"margin-top" : -1
																							* k
																							+ "px",
																					height : k
																							+ "px"
																				})
																		.animate(
																				{
																					marginTop : 0
																				},
																				400,
																				function() {
																					o(r)
																				})
															} else {
																r
																		.animate(
																				{
																					marginTop : 0
																				},
																				400,
																				function() {
																				})
															}
														};
														n()
													})
								}
							})
		};
		$.getScript(e + "/js/jquery.mousewheel.js?date=20150513", x);
		$("#a_luckynum")
				.bind(
						"click",
						function(n) {
							if (H) {
								return
							}
							H = true;
							K(n);
							var r = $("#ng_result_frame");
							if (r.length > 0) {
								r.show();
								return
							}
							var u = parseInt($(this).parent().find(".r-num")
									.html());
							var t = function() {
								var P = '<div style="display:none;" id="ng_result_frame" class="ng-result-frame">';
								P += '<div class="ng-frame-con">';
								P += '<div class="ng-frame-wrap">';
								P += '<a id="a_close" href="javascript:;" title="\u5173\u95ed" class="ng-frame-close ng-result-bg transparent-png">\u5173\u95ed</a>';
								P += '<h3 class="name">\u5e78\u8fd0\u83b7\u5f97\u8005\u672c\u671f\u603b\u5171\u53c2\u4e0e<span class="num">'
										+ u + "</span>\u4eba\u6b21</h3>";
								P += '<div class="ng-frame-code">';
								P += '<div class="ng-s-wait"></div>';
								P += "</div>";
								P += '<div class="transparent-png filter-bg"></div>';
								P += "</div>";
								P += "</div>";
								P += "</div>";
								return P
							};
							r = $(t());
							$("body").append(r);
							var k = $(this);
							var m = k.parent().offset().top - 124, p = k
									.parent().offset().left - 159;
							if (w == 2) {
								m = m - 50
							}
							r.css({
								left : p,
								top : m,
								position : "absolute"
							}).show();
							var l = 800;
							var s = [];
							var q = 0;
							var N = function() {
								if (s.length > 0) {
									if (q == (s.length - 1)) {
										return
									}
									q++;
									var R = $("div.jspPane", r).html();
									var P = s[q];
									var Q = P.substring(0, 3);
									if (Q == "<dl") {
										R += s[q]
									} else {
										R = R.substring(0, R.length - 5) + s[q]
									}
									o(R)
								}
							};
							var o = function(Q) {
								var R = $("div.ng-frame-code", r);
								if (q > 0) {
									$("div.jspPane", r).html(Q)
								} else {
									R.html(Q)
								}
								var P = function() {
									r.hide();
									H = false
								};
								r.find("a.ng-frame-close").click(function() {
									K(n);
									P()
								});
								$("body").bind("click", function() {
									P()
								});
								r.click(function(U) {
									K(U)
								});
								var S = $("div.transparent-png", r);
								var T = R.jScrollPane({
									verticalDragMinHeight : 15
								});
								T.unbind("scroll").bind(
										"scroll",
										function() {
											var V = parseInt($(".jspTrack", r)
													.css("height"));
											var W = parseInt($(".jspDrag", r)
													.css("height"));
											var U = parseInt($(".jspDrag", r)
													.css("top"));
											if (W + U < V) {
												S.show()
											} else {
												if (W + U >= (V - 10)) {
													S.hide();
													N()
												}
											}
											if (U == 0) {
												S.show()
											}
										});
								T.unbind("mousewheel").bind("mousewheel",
										function(V, U) {
											var X = U > 0 ? "Up" : "Down";
											var W = T.scrollTop();
											var Y = T[0].scrollHeight;
											var Z = T.height();
											if (W + Z >= Y && U < 0) {
												D(V)
											} else {
												if (W == 0 && U > 0) {
													D(V)
												}
											}
										})
							};
							var O = function() {
								var P = function(T) {
									if (T.length > 0) {
										var ab = T;
										var W = ab.length;
										if (W > 0) {
											var ac = "";
											var aa = 0;
											var X = parseInt($("#hidCodeRno")
													.val());
											for (var Y = 0; Y < W; Y++) {
												var U = ab[Y];
												ac += '<dl class="f-dl clearfix">';
												ac += "<dt>" + U.buyDate
														+ "</dt>";
												var V = U.randomNumbers
														.split(",");
												for (var Z = 0; Z < V.length; Z++) {
													aa++;
													if (X == parseInt(V[Z])) {
														ac += '<dd class="orange">'
																+ V[Z]
																+ "</dd>"
													} else {
														ac += "<dd>" + V[Z]
																+ "</dd>"
													}
													if (u > l) {
														if (aa % l == 0
																&& aa < u) {
															ac += "#"
														}
													}
												}
												ac += "</dl>"
											}
											if (u > l) {
												s = ac.split("#");
												if (s.length > 0) {
													o(s[q] + "</dl>")
												}
											} else {
												o(ac)
											}
										} else {
											F(
													"\u83b7\u53d6\u6570\u636e\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\uff01",
													function() {
														location.reload()
													})
										}
									} else {
										F(
												"\u83b7\u53d6\u6570\u636e\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\uff01",
												function() {
													location.reload()
												})
									}
								};
								$
										.get(
												"/lotteryDetail/getUserBuyGoodsCodeInfo.action",
												{
													id : L,
													userId : c
												}, function(Q) {
													P(Q)
												})
							};
							O()
						});
		var I = $("#ul_menu").offset().top;
		var G = function() {
			var o = $("#hidBaseNum").val();
			var r = $("#hidCodeQuantity").val();
			var n = $("#hidYuShu").val();
			var m = parseInt(n) + parseInt("10000001");
			var p = parseInt($("#hidCodeRno").val());
			var l = $("#div_evaldata");
			var q = true;
			var t = function(u) {
				l.html(u)
			};
			var s = false;
			var k = function() {
				if (s) {
					return
				}
				$
						.get(
								"/lotteryDetail/getLotteryRecords.action",
								{
									id : L
								},
								function(u) {
									if (u.length > 0) {
										var Q = w == "" ? "\u622a\u6b62\u8be5\u5546\u54c1\u6700\u540e\u8d2d\u4e70\u65f6\u95f4"
												: "\u622a\u6b62\u63ed\u6653\u65f6\u95f4";
										var S = '<div class="ng-data-info">';
										S += '<div class="ng-data-head">';
										S += '<span class="time">' + $shortName
												+ "\u65f6\u95f4</span>";
										S += '<span class="data">\u8f6c\u6362\u6570\u636e</span>';
										S += '<span class="user">\u4f1a\u5458</span>';
										S += '<span class="num">\u53c2\u4e0e\u4eba\u6b21</span>';
										S += '<span class="product">\u5546\u54c1\u540d\u79f0</span>';
										S += "</div>";
										S += "</div>";
										S += '<div class="ng-data-detail">';
										S += '<div class="ng-data-step">';
										S += '<p class="title">'
												+ Q
												+ "\u3010"
												+ $("#hidRaffleTime").val()
												+ "\u3011\u7f51\u7ad9\u6240\u6709\u5546\u54c1\u7684\u6700\u540e100\u6761\u8d2d\u4e70\u65f6\u95f4(\u65f6\u3001\u5206\u3001\u79d2\u3001\u6beb\u79d2)\u8bb0\u5f55</p>";
										S += '<div class="step">';
										S += '<ul class="step-inner clearfix">';
										S += '<li class="s-r1"><p>\u8ba1\u7b97\u7ed3\u679c</p></li>';
										S += '<li class="s-t">=</li>';
										S += '<li class="s-t">(</li>';
										S += '<li class="s-r2">';
										S += "<p>" + o + "</p>";
										S += "<span>\u4ee5\u4e0b100\u6761\u65f6\u95f4\u53d6\u503c\u4e4b\u548c</span>";
										S += "</li>";
										S += '<li id="li_mod" class="s-t mod"><i>%</i><span class="txt">(\u53d6\u4f59)</span></li>';
										S += '<li class="s-r3">';
										S += "<p>" + r + "</p>";
										S += "<span>\u603b\u9700\u53c2\u4e0e\u4eba\u6b21</span>";
										S += "</li>";
										S += '<li class="s-t">)</li>';
										S += '<li class="s-t">+</li>';
										S += '<li class="s-r4">';
										S += "<p>10000001</p>";
										S += "<span>\u56fa\u5b9a\u6570\u503c</span>";
										S += "</li>";
										S += '<li class="s-t">=</li>';
										S += '<li class="s-r5">';
										S += "<p>" + p + "</p>";
										S += "<span>\u6700\u7ec8\u8ba1\u7b97\u7ed3\u679c</span>";
										S += "</li>";
										S += "</ul>";
										S += '<div class="ng-result-bg equals transparent-png"></div>';
										if (m != p) {
											S += '<div class="ng-result-tt">\u8ba1\u7b97\u7ed3\u679c<span>'
													+ m
													+ "</span>\u672a\u88ab\u8d2d\u4e70\uff0c\u53d6\u5dee\u503c\u6700\u5c0f\u7684\u60ca\u559c\u7801<span>"
													+ p
													+ "</span>\u4e3a\u5e78\u8fd0\u60ca\u559c\u7801</div>"
										}
										S += "</div>";
										S += '<div id="div_whatmod" class="ng-mod-wrapper" style="display:none;">';
										S += '<div class="ng-mod-inner">';
										S += "<p>\u4f59\u6570\u662f\u6307\u6574\u6570\u9664\u6cd5\u4e2d\u88ab\u9664\u6570\u672a\u88ab\u9664\u5c3d\u90e8\u5206,<br>\u59827\xf73 = 2 ......1\uff0c1\u5c31\u662f\u4f59\u6570\u3002</p>";
										S += "</div>";
										S += '<i class="s"><i class="s"></i></i>';
										S += "</div>";
										S += "</div>";
										S += '<div class="ng-table-wrapper">';
										S += '<div id="div_nginner" class="ng-table-inner">';
										S += '<ul class="ng-table-ul clearfix">';
										var T = u;
										var O = u.length;
										for (var R = 0; R < O; R++) {
											if (R == 0) {
												S += '<li class="first">'
											} else {
												S += "<li>"
											}
											S += '<span class="time"><b>'
													+ T[R].buyDate.replace(" ",
															"</b>") + "</span>";
											S += '<span class="code">'
													+ T[R].dateSum + "</span>";
											S += '<span class="name"><a href="/u/'
													+ T[R].userId
													+ '.html" target="_blank">'
													+ T[R].userName
													+ "</a></span>";
											S += '<span class="num">'
													+ T[R].buyCount
													+ "\u4eba\u6b21</span>";
											S += '<span class="pro"><a href="/products/'
													+ T[R].spellbuyProductId
													+ '.html" target="_blank">\uff08\u7b2c'
													+ T[R].productPeriod
													+ "\u4e91\uff09"
													+ T[R].productName
													+ "</a></span>";
											S += "</li>"
										}
										S += "</ul>";
										S += '<div class="ng-table-bg01 transparent-png"></div>';
										S += '<div class="ng-table-bg02 transparent-png"></div>';
										S += '<div class="ng-table-bg03 ng-result-bg transparent-png"></div>';
										S += "</div>";
										S += '<div id="div_showmore" class="ng-see-more">';
										S += "<span>\u5c55\u5f00\u5168\u90e8100\u6761\u6570\u636e<b><s></s></b></span>";
										S += "</div>";
										S += "</div>";
										S += "</div>";
										S += '<ul class="ng-table-ul ng-tc-ul">';
										var U = u;
										for (var R = 100; R < U.length; R++) {
											if (R == 0) {
												S += '<li class="first">'
											} else {
												S += "<li>"
											}
											S += '<span class="time"><b>'
													+ U[R].buyDate.replace(" ",
															"</b>") + "</span>";
											S += '<span class="code"></span>';
											S += '<span class="name"><a href="/u/'
													+ U[R].userId
													+ '.html" target="_blank">'
													+ U[R].userName
													+ "</a></span>";
											S += '<span class="num">'
													+ U[R].buyCount
													+ "\u4eba\u6b21</span>";
											S += '<span class="pro"><a href="/products/'
													+ U[R].spellbuyProductId
													+ '.html" target="_blank">\uff08\u7b2c'
													+ U[R].productPeriod
													+ "\u4e91\uff09"
													+ U[R].productName
													+ "</a></span>";
											S += "</li>"
										}
										S += "</ul>";
										t(S);
										var V = $("#div_nginner");
										$("#div_showmore")
												.bind(
														"click",
														function() {
															var N = $(this);
															if (parseInt(V
																	.height()) < 4004) {
																V
																		.animate(
																				{
																					height : "4004px"
																				},
																				800,
																				function() {
																					N
																							.addClass(
																									"up")
																							.html(
																									"<span>\u6536\u8d77<b><s></s></b></span>")
																				})
															} else {
																V
																		.height("322px");
																N
																		.removeClass(
																				"up")
																		.html(
																				"<span>\u5c55\u5f00\u5168\u90e8100\u6761\u6570\u636e<b><s></s></b></span>");
																$("body,html")
																		.animate(
																				{
																					scrollTop : I
																				},
																				0)
															}
														});
										var P = $("#div_whatmod");
										P.hover(function() {
											q = false
										}, function() {
											q = true;
											setTimeout(function() {
												if (q) {
													P.fadeOut()
												}
											}, 600)
										});
										$("#li_mod").hover(function() {
											q = false;
											P.show()
										}, function() {
											q = true;
											setTimeout(function() {
												if (q) {
													P.fadeOut()
												}
											}, 600)
										});
										s = true
									} else {
										l
												.html('<div class="null-retips-wrapper"><div class="gth-icon transparent-png"></div><span>\u8ba1\u7b97\u7ed3\u679c\u83b7\u53d6\u5931\u8d25\uff0c\u8bf7\u5237\u65b0\u91cd\u8bd5\uff01</span></div>')
									}
								})
			};
			this.initData = function() {
				if (w != "") {
					t('<div class="NoConMsg"><span>\u5bf9\u4e0d\u8d77\uff0c\u8be5\u5546\u54c1\u7684\u63ed\u6653\u65f6\u95f4\u65e9\u4e8e'
							+ $("#hidShowCalcTime").val()
							+ "\uff0c\u6ca1\u6709\u91c7\u7528\u5f53\u524d\u7684\u63ed\u6653\u673a\u5236\u63ed\u6653\uff01</span><br /><br /><span>\u8be5\u5546\u54c1\u63ed\u6653\u673a\u5236\u4e3a\uff1a<br /><br />1\u3001\u53d6\u8be5\u5546\u54c1\u6700\u540e\u8d2d\u4e70\u65f6\u95f4\u524d\u7f51\u7ad9\u6240\u6709\u5546\u54c1\u7684\u6700\u540e50\u6761\u8d2d\u4e70\u65f6\u95f4\u8bb0\u5f55\uff1b<br /> 2\u3001\u53d6\u6700\u8fd1\u4e0b\u4e00\u671f\u4e2d\u56fd\u798f\u5229\u5f69\u7968\u201c\u65f6\u65f6\u5f69\u201d\u7684\u5f00\u5956\u7ed3\u679c\uff08\u4e00\u4e2a\u4e94\u4f4d\u6570\u503cB\uff09;<br /> 3\u3001\uff08\u6570\u503cA+\u6570\u503cB\uff09\u9664\u4ee5\u8be5\u5546\u54c1\u603b\u53c2\u4e0e\u4eba\u6b21\u540e\u53d6\u4f59\u6570 + \u539f\u59cb\u6570 10000001</span><br /><br /><span>\u5f00\u5956\u6570\u636e\uff1a "
							+ o
							+ " (50\u6761\u8d2d\u4e70\u65f6\u95f4\u603b\u548c) + "
							+ w
							+ " (\u6700\u8fd1\u4e00\u671f\u65f6\u65f6\u5f69\u5f00\u5956\u53f7) % "
							+ r
							+ " (\u603b\u9700\u53c2\u4e0e\u4eba\u6b21) = "
							+ n
							+ " (\u4f59\u6570) + 10000001 (\u539f\u59cb\u6570) = "
							+ p
							+ " (\u6700\u7ec8\u8ba1\u7b97\u7ed3\u679c)</span></div>")
				} else {
					k()
				}
			}
		};
		var A = new Object();
		$.fn.showUserBuyRNO = function() {
			var k = function() {
				$("#div_allrecord").find('a[name="showCode"]').each(function() {
					$(this).attr("isShow", "0")
				})
			};
			$(this)
					.click(
							function(m) {
								K(m);
								var N = $("#divRnoShow");
								var P = $(this);
								if (P.attr("isShow") == "1") {
									N.hide();
									P.attr("isShow", "0");
									return
								}
								k();
								P.attr("isShow", "1");
								var R = P.offset().top, p = P.offset().left;
								var r = P.parent().prevAll("span.name").html();
								r = r.replace("<i ", "<b ");
								r = r.replace("</i>", "</b>");
								var u = P.attr("buyid");
								var l = P.attr("buynum");
								var q = '<div class="yungou-code-inner"><div class="yt">'
										+ r
										+ "\u672c\u6b21\u53c2\u4e0e<span>"
										+ l
										+ '</span>\u4eba\u6b21</div><div class="code-info"><div class="code-ul-wrapper"><ul class="code-ul"><div class="ng-s-wait"></div></ul></div></div><a href="javascript:;" class="close-btn ng-result-bg transparent-png" title="\u5173\u95ed"></a><i class="j"><b class="j"></b></i></div>';
								var Q = 800;
								var t = [];
								var s = 0;
								var o = "";
								var O = function() {
									if (t.length > 0) {
										if (s == (t.length - 1)) {
											return
										}
										s++;
										var S = N.find("ul.code-ul").html();
										S += t[s];
										n(S)
									}
								};
								var n = function(S) {
									N.find("ul.code-ul").html(S);
									N.find("a.close-btn").click(function() {
										$(this).parent().parent().hide();
										P.attr("isShow", "0");
										return false
									});
									$("body").click(function() {
										N.hide();
										k()
									});
									var T = $("div.code-ul-wrapper", N)
											.jScrollPane({
												verticalDragMinHeight : 15
											});
									T.unbind("scroll").bind(
											"scroll",
											function() {
												var V = parseInt($(".jspTrack",
														N).css("height"));
												var W = parseInt($(".jspDrag",
														N).css("height"));
												var U = parseInt($(".jspDrag",
														N).css("top"));
												if (W + U >= (V - 10)) {
													O()
												}
											});
									T.unbind("mousewheel").bind("mousewheel",
											function(V, U) {
												var X = U > 0 ? "Up" : "Down";
												var W = T.scrollTop();
												var Y = T[0].scrollHeight;
												var Z = T.height();
												if (W + Z >= Y && U < 0) {
													D(V)
												} else {
													if (W == 0 && U > 0) {
														D(V)
													}
												}
											})
								};
								if (N.length == 0) {
									N = $('<div id="divRnoShow" class="yungou-code-wrapper"></div>');
									$("body").append(N);
									N.click(function(S) {
										K(S)
									})
								}
								R = R - 96;
								p = p - 363;
								p = p - 10 - l.length * 8;
								N.html(q).css({
									left : p,
									top : R
								}).show();
								if (A["userBuyRnoArr" + u] != null) {
									t = A["userBuyRnoArr" + u];
									if (t.length > 0) {
										n(t[s])
									}
								} else {
									if (A["userBuyRnoStr" + u] != null) {
										o = A["userBuyRnoStr" + u];
										n(o)
									} else {
										$
												.ajax({
													url : "/lotteryDetail/getUserBuyCodeByBuyid.action",
													data : "spellbuyrecordId="
															+ u + "&id=" + L,
													success : function(S) {
														if (S != "false") {
															var T = S;
															o = "";
															s = 0;
															var U = 0;
															for (var V = 0; V < T.length; V++) {
																U++;
																o += "<li>"
																		+ T[V]
																		+ "</li>";
																if (parseInt(l) > Q) {
																	if (U % Q == 0
																			&& U < parseInt(l)) {
																		o += "#"
																	}
																}
															}
															if (parseInt(l) > Q) {
																t = o
																		.split("#");
																if (t.length > 0) {
																	n(t[s]);
																	A["userBuyRnoArr"
																			+ u] = t
																}
															} else {
																n(o);
																A["userBuyRnoStr"
																		+ u] = o
															}
														}
													},
													error : function() {
														F("\u62b1\u6b49\uff0c\u67e5\u8be2\u5931\u8d25")
													}
												})
									}
								}
							})
		};
		var C = function() {
			var m = $("#div_allrecord");
			var k = new Object();
			var l = function() {
				var o = $("#resultCount").val();
				var n = 0;
				$("#pagination").pagination(o, {
					current_page : n,
					prev_text : "\u4e0a\u4e00\u9875",
					next_text : "\u4e0b\u4e00\u9875",
					num_display_entries : 5,
					num_edge_entries : 1,
					link_to : "",
					prev_show_always : false,
					next_show_always : false,
					items_per_page : 20,
					callback : p
				});
				function p(q) {
					var s = m.children("div.record");
					s.next("div.g-pagination").remove();
					s.find("ul").html('<div class="ng-b-wait"></div>');
					if (k["page" + q] != null) {
						s.find("ul").html(k["page" + q]);
						m.find("li").hover(function() {
							$(this).addClass("current")
						}, function() {
							$(this).removeClass("current")
						});
						m.find('a[name="showCode"]').showUserBuyRNO()
					} else {
						var r = "/products/ajaxPage.action";
						$
								.ajax({
									url : r,
									data : "id=" + L + "&pageNo=" + q,
									beforeSend : a,
									success : function(P) {
										if (P.length > 0) {
											var t = P;
											var O = '<li class="record-menu"><span class="time">\u65f6\u95f4</span><span class="name">\u4f1a\u5458</span><span class="people">\u53c2\u4e0e\u4eba\u6b21</span><span class="ip">IP</span><span class="form">\u6765\u6e90</span></li>';
											for (var N = 0; N < t.length; N++) {
												var u = t[N];
												O += "<li>";
												O += '<span class="time">'
														+ u.buyDate + "</span>";
												O += '<span class="name"><span class="w"><a href="/u/'
														+ u.userId
														+ '.html" target="_blank"><i class="head-s-img"><img src="'
														+ u.userFace
														+ '" width="22" height="22"></i>'
														+ u.userName
														+ "</a></span></span>";
												O += '<span class="people">'
														+ u.buyCount
														+ "<a name='showCode' buyid='"
														+ u.buyId
														+ "' buynum='"
														+ u.buyCount
														+ '\' href="javascript:;">\u67e5\u770b'
														+ $shortName
														+ "\u7801</a></span>";
												O += '<span class="ip">'
														+ u.ip_location + " IP:"
														+ u.ip_address
														+ "</span>";
												if (u.buySource == "1") {
													O += '<span class="form"><a href="/#" target="_blank">\u624b\u673a\u89e6\u5c4f\u7248<i class="f-icon mobile"></i></a></span>'
												} else {
													if (u.buySource == "2") {
														O += '<span class="form"><a href="/#" target="_blank">Android\u5ba2\u6237\u7aef<i class="f-icon andriod"></i></a></span>'
													} else {
														if (u.buySource == "3") {
															O += '<span class="form"><a href="/#" target="_blank">iOS\u5ba2\u6237\u7aef<i class="f-icon ios"></i></a></span>'
														} else {
															if (u.buySource == "4") {
																O += '<span class="form"><a href="/#" target="_blank">\u5fae\u4fe1\u516c\u4f17\u5e73\u53f0<i class="f-icon wx"></i></a></span>'
															} else {
																O += '<span class="form"><a href="javascript:;">PC\u7535\u8111<i class="f-icon pc"></i></a></span>'
															}
														}
													}
												}
												O += "</li>"
											}
											s.find("ul").html(O);
											s.find("li").hover(function() {
												$(this).addClass("current")
											}, function() {
												$(this).removeClass("current")
											});
											s.find('a[name="showCode"]')
													.showUserBuyRNO();
											k["page" + q] = s.find("ul").html()
										} else {
											m
													.html('<div class="null-retips-wrapper"><div class="gth-icon transparent-png"></div><span>\u53c2\u4e0e\u8bb0\u5f55\u83b7\u53d6\u5931\u8d25\uff0c\u8bf7\u5237\u65b0\u91cd\u8bd5\uff01</span></div>')
										}
									}
								})
					}
				}
			};
			this.initData = function() {
				l()
			}
		};
		function a() {
			$("#div_allrecord").children("div.record").find("ul").html(
					'<div class="ng-b-wait"></div>')
		}
		function b() {
			$("#div_postlist").find("div.ng-share-wrapper").html(
					'<div class="ng-b-wait"></div>')
		}
		var J = function() {
			var p = $("#div_postlist");
			var o = new Object();
			var n = $("#pageCount").val();
			var m = 0;
			var l = function(O) {
				var s = "postHits";
				var P = f(s);
				if (P == null || P == "") {
					P = ","
				}
				var q = $(O);
				q.find("a[type='showCard']").each(function() {
					$(this).ShowUserCard()
				});
				var r = function() {
					$("body").find("div.pic-hover").removeClass("current")
							.hide();
					$("body").find("div.ng-show-bigpic").remove();
					N = false;
					t = -1
				};
				var Q = q.find("ul");
				var N = false;
				var t = -1;
				var u = true;
				Q
						.children("li")
						.each(
								function(R) {
									$(this)
											.hover(
													function(S) {
														K(S);
														u = false;
														var T = $(this);
														Q
																.find(
																		"div.pic-hover")
																.removeClass(
																		"current")
																.hide();
														if (t != R) {
															N = false
														} else {
															$(this)
																	.find(
																			"div.pic-hover")
																	.addClass(
																			"current")
																	.show();
															N = true;
															return
														}
														var U = T
																.find(
																		"div.pic-hover")
																.show();
														U
																.unbind("click")
																.bind(
																		"click",
																		function(
																				W) {
																			K(W);
																			t = R;
																			$(
																					"body")
																					.find(
																							"div.ng-show-bigpic")
																					.remove();
																			var X = $('<div class="ng-show-bigpic" style="display:none;"><img width="309px"  src="'
																					+ $img
																					+ T
																							.attr("pic")
																					+ '"></div>');
																			$(
																					"body")
																					.append(
																							X);
																			X
																					.hover(
																							function() {
																								u = false
																							},
																							function() {
																								u = true;
																								setTimeout(
																										function() {
																											if (u) {
																												r()
																											}
																										},
																										800)
																							})
																					.bind(
																							"click",
																							function(
																									Z) {
																								K(Z)
																							});
																			var Y = T
																					.offset().left
																					- (X
																							.width() - T
																							.width())
																					/ 2;
																			var V = T
																					.offset().top
																					+ T
																							.height()
																					+ 10;
																			if (N) {
																				N = false;
																				$(
																						this)
																						.removeClass(
																								"current");
																				X
																						.hide();
																				t = -1
																			} else {
																				N = true;
																				$(
																						this)
																						.addClass(
																								"current");
																				X
																						.css(
																								{
																									left : +Y,
																									top : +V
																								})
																						.show()
																			}
																		})
													}, function() {
													})
								});
				Q.hover(function() {
				}, function() {
					u = true;
					setTimeout(function() {
						if (u) {
							r()
						}
					}, 800)
				});
				$("body").bind("click", function() {
					r()
				});
				q
						.find("a.xianmu")
						.each(
								function() {
									var S = $(this);
									var V = parseInt(S.attr("num"));
									var R = S.attr("postid");
									var U = function() {
										var W = $('<b class="has-heart">\u5df2\u7fa1\u6155</b>');
										S.append(W);
										W.fadeTo(2000, 0, function() {
											W.remove()
										})
									};
									var T = function() {
										if (R <= 0) {
											return
										}
										$
												.ajax({
													url : "/shareShow/upShareInfo.action",
													data : "shareId=" + R,
													success : function(X) {
														if (X == "true") {
															P = P + R + ",";
															i(s, P, 1);
															W()
														}
													}
												});
										var W = function() {
											S.addClass("xianmu-click");
											var Y = S.children("img");
											var ac = Y.width();
											var ab = Y.height();
											var Z = Y.parent().offset().left + 14;
											var X = Y.parent().offset().top;
											Y.hide();
											var aa = $(
													'<img style="display: none" src='
															+ Y.attr("src")
															+ ">").prependTo(
													"body");
											aa.css({
												position : "absolute",
												left : Z + "px",
												top : X + "px",
												width : ac,
												height : ab,
												"z-index" : 9999
											}).show();
											aa.animate({
												width : ac * 2,
												height : ab * 2,
												left : Z - ac / 2,
												top : X - ab / 2,
												opacity : 0
											}, 700, function() {
												aa.remove();
												S.children("em").html(V + 1);
												S.addClass("xianmu-past")
											})
										}
									};
									if (P.indexOf("," + R + ",") >= 0) {
										S.bind("click", function() {
											U()
										}).addClass("xianmu-past")
									} else {
										S.bind("click", function() {
											if (P.indexOf("," + R + ",") >= 0) {
												U();
												return
											}
											T()
										})
									}
								});
				return q
			};
			var k = function() {
				$("#pagination2").pagination(n, {
					current_page : m,
					prev_text : "\u4e0a\u4e00\u9875",
					next_text : "\u4e0b\u4e00\u9875",
					num_display_entries : 5,
					num_edge_entries : 1,
					link_to : "",
					prev_show_always : false,
					next_show_always : false,
					items_per_page : 20,
					callback : q
				});
				function q(r) {
					if (o["page" + r] != null) {
						p.find("div.ng-share-wrapper").empty().append(
								l(o["page" + r]));
						loadImgFun()
					} else {
						$
								.ajax({
									url : "/shareShow/productInfoShareListByProductId.action",
									data : "id=" + B + "&pageNo=" + r,
									beforeSend : b,
									success : function(R) {
										if (R.length > 0) {
											p
													.find(
															"div.ng-share-wrapper")
													.before(
															'<div class="total">\u672c\u5546\u54c1\u5df2\u6709<span class="gray6">'
																	+ n
																	+ "</span>\u4f4d\u5e78\u8fd0\u7528\u6237\u6652\u5355 </div>");
											var t = R;
											var P = "";
											for (var u = 0; u < t.length; u++) {
												P += '<div class="ng-share-detail clearfix">';
												P += '<div class="ng-share-pic">';
												P += '<a href="/u/'
														+ t[u].userId
														+ '.html" type="showCard" uweb="'
														+ t[u].userId
														+ '"  target="_blank">';
												P += '<img src="'
														+ t[u].userFace
														+ '" alt="" width="80" height="80" /></a>';
												P += "</div>";
												P += '<div class="ng-share-con">';
												P += '<div class="name-line">';
												P += '<a href="/u/'
														+ t[u].userId
														+ '.html" target="_blank" rel="nofollow" class="u-name" >'
														+ t[u].userName
														+ "</a>";
												P += '<span class="u-time">'
														+ t[u].shareDate
														+ "</span>";
												P += "</div>";
												P += '<div class="u-data">';
												P += '<span class="u-num">\u7b2c<span>'
														+ t[u].productPeriod
														+ "</span>\u4e91\u6652\u5355</span>";
												P += '<a href="/shareShow/'
														+ t[u].uid
														+ '.html" class="u-show" target="_blank">'
														+ t[u].shareTitle
														+ "</a>";
												P += "</div>";
												P += '<div class="share-info">';
												P += '<p><a href="/shareShow/'
														+ t[u].uid
														+ '.html"  target="_blank">'
														+ t[u].shareContent
																.substring(0,
																		150)
														+ (t[u].shareContent.length > 150 ? "..."
																: "")
														+ "</a></p>";
												P += "</div>";
												P += '<div class="pic-list-wrap">';
												P += '<ul class="pic-list clearfix">';
												var Q = t[u].shareimageList;
												var N = Q.length > 5 ? 5
														: Q.length;
												for (var O = 0; O < N; O++) {
													P += "<li pic=/UserPost/Big/"
															+ Q[O].images + ">";
													P += "<span>";
													P += '<img width="71px" height="71px" src="/Images/loading71.gif" src2="'
															+ $img
															+ "/UserPost/Small/"
															+ Q[O].images
															+ '" /></span>';
													P += '<div class="pic-hover transparent-png" style="display:none;"></div>';
													P += "</li>"
												}
												P += "</ul>";
												P += '<div class="talk-wrap">';
												P += '<a href="javascript:;" num=\''
														+ t[u].upCount
														+ "' postid='"
														+ t[u].uid
														+ '\' class="xianmu">';
												P += '<i class="transparent-png"></i>';
												P += "\u7fa1\u6155(<em>"
														+ t[u].upCount
														+ "</em>)";
												P += '<img class="transparent-png" src="/Images/xin.png">';
												P += "</a>";
												P += '<a rel="nofollow" href="/shareShow/'
														+ t[u].uid
														+ '.html" target="_blank" class="pinglun">';
												P += '<i class="transparent-png"></i>';
												P += "\u8bc4\u8bba(<em>"
														+ t[u].replyCount
														+ "</em>)";
												P += "</a>";
												P += "</div>";
												P += "</div>";
												P += "</div>";
												P += '<div class="clear"></div>';
												P += "</div>"
											}
											var s = l(P);
											p.find("div.ng-share-wrapper")
													.empty().append(s);
											loadImgFun();
											o["page" + r] = p.find(
													"div.ng-share-wrapper")
													.html()
										} else {
											p
													.html('<div class="null-retips-wrapper"><div class="gth-icon transparent-png"></div><span>\u6682\u65e0\u6652\u5355\u8bb0\u5f55\u54e6\uff01</span></div>')
										}
									}
								})
					}
				}
			};
			this.initData = function() {
				k()
			}
		};
		CBLResultFun = new G();
		CBLResultFun.initData();
		CBLRecordFun = new C();
		CBLPageFun = new J();
		var z = [ 384, 559, 732 ];
		var M = $("#midNavLine");
		$("#ul_menu").children("li").each(function(k) {
			$(this).bind("click", function() {
				$(this).addClass("current").siblings().removeClass("current");
				M.animate({
					left : z[k]
				}, {
					queue : false,
					duration : 400,
					complete : function() {
						_IsAminate = false
					}
				});
				if (k == 0) {
					$("#div_evaldata").show();
					$("#div_allrecord").hide();
					$("#div_postlist").hide()
				} else {
					if (k == 1) {
						$("#div_evaldata").hide();
						$("#div_allrecord").show();
						$("#div_postlist").hide();
						CBLRecordFun.initData()
					} else {
						if (k == 2) {
							$("#div_evaldata").hide();
							$("#div_allrecord").hide();
							$("#div_postlist").show();
							CBLPageFun.initData()
						}
					}
				}
			})
		})
	};
	var j = function() {
		var a = function() {
			var b = function() {
				d()
			};
			$.getScript(e + "/js/userinfofun.js?date=20150214", b)
		};
		$.getScript(e + "/js/ajaxfun.js?date=20150213", a)
	};
	$.getScript(e + "/js/poplogin.js?date=20150213", j);
	function h(a, b) {
		document.cookie = a + "=" + escape(b) + ";id=1ypg;path=/;domain="
				+ $domain
	}
	function i(b, k, a) {
		var c = a;
		var l = new Date();
		l.setTime(l.getTime() + c * 24 * 60 * 60 * 1000);
		document.cookie = b + "=" + escape(k) + ";id=1ypg;path=/;expires="
				+ l.toGMTString() + ";domain=" + $domain
	}
	function f(b) {
		var a = document.cookie
				.match(new RegExp("(^| )" + b + "=([^;]*)(;|$)"));
		if (a != null) {
			return unescape(a[2])
		}
		return null
	}
	function g(a) {
		var c = new Date();
		c.setTime(c.getTime() - 1);
		var b = f(a);
		if (b != null) {
			document.cookie = a + "=" + b + ";id=1ypg;path=/;expires="
					+ c.toGMTString() + ";domain=" + $domain
		}
	}
});
