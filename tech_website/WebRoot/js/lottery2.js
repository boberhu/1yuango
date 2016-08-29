var resetLotteryListStyle = null;
var bindLotteryItemHover = null;
$(document)
		.ready(
				function() {
					var d = $skin;
					var c = function() {
						var u = $("#divLoading");
						var t = $("#divLottery");
						var q = $("#lotteryCount");
						var f = $("#divMsg");
						var x = $("#divPage");
						var b = $("#hidCurSortID").val();
						if (b == "" || b == "index") {
							$("#lottMenu > li").eq(0).attr("class", "current")
						} else {
							if (b == "1001") {
								$("#lottMenu > li").eq(1).attr("class",
										"current")
							} else {
								if (b == "1002") {
									$("#lottMenu > li").eq(2).attr("class",
											"current")
								} else {
									if (b == "1003") {
										$("#lottMenu > li").eq(3).attr("class",
												"current")
									} else {
										if (b == "1019") {
											$("#lottMenu > li").eq(4).attr(
													"class", "current")
										} else {
											if (b == "1005") {
												$("#lottMenu > li").eq(5).attr(
														"class", "current")
											} else {
												if (b == "1006") {
													$("#lottMenu > li").eq(6)
															.attr("class",
																	"current")
												} else {
													if (b == "1022") {
														$("#lottMenu > li").eq(
																7).attr(
																"class",
																"current")
													} else {
														if (b == "1021") {
															$("#lottMenu > li")
																	.eq(8)
																	.attr(
																			"class",
																			"current")
														} else {
															if (b == "1009") {
																$(
																		"#lottMenu > li")
																		.eq(9)
																		.attr(
																				"class",
																				"current")
															} else {
																if (b == "1007") {
																	$(
																			"#lottMenu > li")
																			.eq(
																					10)
																			.attr(
																					"class",
																					"current")
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						var l = 1175;
						if (/(msie\s|trident.*rv:)([\w.]+)/
								.test(navigator.userAgent.toLowerCase())
								&& parseInt($.browser.version) < 9) {
							l = 1190
						}
						var s = 72;
						bindLotteryItemHover = function(e) {
							e.hover(function() {
								var g = parseInt(t.children("div").index(
										$(this)[0])) + 1;
								if (g % 3 == 0) {
									$(this).addClass(
											"m-lottery-hover m-lottery-hover2")
								} else {
									$(this).addClass("m-lottery-hover")
								}
							}, function() {
								var g = parseInt(t.children("div").index(
										$(this)[0])) + 1;
								if (g % 3 == 0) {
									$(this).removeClass(
											"m-lottery-hover m-lottery-hover2")
								} else {
									$(this).removeClass("m-lottery-hover")
								}
							})
						};
						var v = function() {
							t.children('div[type="isRaff"]').each(function() {
								bindLotteryItemHover($(this))
							})
						};
						v();
						$(window).resize(function() {
							resetLotteryListStyle()
						});
						var w = function() {
							if (/(msie\s|trident.*rv:)([\w.]+)/
									.test(navigator.userAgent.toLowerCase())
									&& parseInt($.browser.version) == 6) {
								var e = $(window).width() < l;
								if (e) {
									t.find('img[type="userPhoto"]').each(
											function() {
												$(this).css({
													width : "40px",
													height : "40px"
												})
											})
								} else {
									t.find('img[type="userPhoto"]').each(
											function() {
												$(this).css({
													width : "60px",
													height : "60px"
												})
											})
								}
							}
						};
						resetLotteryListStyle = function() {
							var i = $(window).width() < l;
							var g = t.children("div.m-lottery-anning");
							var j = g.length;
							var e = function() {
								if (j == 1) {
									g
											.eq(0)
											.attr("class",
													"m-lottery-list m-lottery-anning m-anning-wh326 m-lottery-bor-rb")
								} else {
									if (j == 2) {
										g
												.eq(0)
												.attr("class",
														"m-lottery-list m-lottery-anning");
										g
												.eq(1)
												.attr("class",
														"m-lottery-list m-lottery-anning m-lottery-bor-rb")
									} else {
										if (j >= 3) {
											g
													.eq(0)
													.attr("class",
															"m-lottery-list m-lottery-anning");
											g
													.eq(1)
													.attr("class",
															"m-lottery-list m-lottery-anning m-anning-wh328");
											g
													.eq(2)
													.attr("class",
															"m-lottery-list m-lottery-anning m-anning-wh329 m-lottery-bor-rb")
										}
									}
								}
								var k = parseInt(j / 3) * 3;
								if (j > 3 && k > 3) {
									g
											.each(function(m, n) {
												var o = m + 1;
												if (o > 3 && o <= k) {
													if ((o - 1) % 3 == 0) {
														if (o == j) {
															$(this)
																	.attr(
																			"class",
																			"m-anning-wh326")
														} else {
															$(this)
																	.attr(
																			"class",
																			"")
														}
													} else {
														if ((o - 2) % 3 == 0) {
															$(this)
																	.attr(
																			"class",
																			"m-anning-wh328")
														} else {
															$(this)
																	.attr(
																			"class",
																			"m-anning-wh329 m-lottery-bor-rb")
														}
													}
													$(this)
															.addClass(
																	"m-lottery-list m-lottery-anning m-anning-height");
													if (o == j) {
														$(this)
																.addClass(
																		"m-lottery-bor-rb")
													}
												}
											})
								}
								if (j > 3 && j > k) {
									if ((j - 1) % 3 == 0) {
										g
												.eq(j - 1)
												.attr(
														"class",
														"m-lottery-list m-lottery-anning m-anning-wh326 m-anning-height m-lottery-bor-rb")
									} else {
										g
												.eq(j - 2)
												.attr("class",
														"m-lottery-list m-lottery-anning m-anning-height");
										g
												.eq(j - 1)
												.attr("class",
														"m-lottery-list m-lottery-anning m-anning-height m-lottery-bor-rb")
									}
								}
							};
							var h = function() {
								if (j == 1) {
									g
											.eq(0)
											.attr("class",
													"m-lottery-list m-lottery-anning m-anning-wh393 m-lottery-bor-rb")
								} else {
									if (j == 2) {
										g
												.eq(0)
												.attr("class",
														"m-lottery-list m-lottery-anning");
										g
												.eq(1)
												.attr("class",
														"m-lottery-list m-lottery-anning m-lottery-bor-rb")
									} else {
										if (j >= 3) {
											g
													.eq(0)
													.attr("class",
															"m-lottery-list m-lottery-anning");
											g
													.eq(1)
													.attr("class",
															"m-lottery-list m-lottery-anning m-anning-wh395");
											g
													.eq(2)
													.attr("class",
															"m-lottery-list m-lottery-anning m-anning-wh395 m-lottery-bor-rb")
										}
									}
								}
								var k = parseInt(j / 3) * 3;
								if (j > 3 && k > 3) {
									g
											.each(function(m, n) {
												var o = m + 1;
												if (o > 3 && o <= k) {
													if ((o - 1) % 3 == 0) {
														if (o == j) {
															$(this)
																	.attr(
																			"class",
																			"m-anning-wh393")
														} else {
															$(this)
																	.attr(
																			"class",
																			"")
														}
													} else {
														if ((o - 2) % 3 == 0) {
															$(this)
																	.attr(
																			"class",
																			"m-anning-wh395")
														} else {
															if (o % 3 == 0) {
																$(this)
																		.attr(
																				"class",
																				"m-anning-wh395 m-lottery-bor-rb")
															}
														}
													}
													$(this)
															.addClass(
																	"m-lottery-list m-lottery-anning m-anning-height");
													if (o == j) {
														$(this)
																.addClass(
																		"m-lottery-bor-rb")
													}
												}
											})
								}
								if (j > 3 && j > k) {
									if ((j - 1) % 3 == 0) {
										g
												.eq(j - 1)
												.attr(
														"class",
														"m-lottery-list m-lottery-anning m-anning-wh393 m-anning-height m-lottery-bor-rb")
									} else {
										g
												.eq(j - 2)
												.attr("class",
														"m-lottery-list m-lottery-anning m-anning-height");
										g
												.eq(j - 1)
												.attr("class",
														"m-lottery-list m-lottery-anning m-anning-height m-lottery-bor-rb")
									}
								}
							};
							if (i) {
							} else {
								h()
							}
							t
									.children('div[type="isRaff"]')
									.each(
											function() {
												var k = parseInt(t.children(
														"div")
														.index($(this)[0])) + 1;
												if (i) {
													if (k % 3 == 0) {
														$(this)
																.attr("class",
																		"m-lottery-list m-lottery-special")
													} else {
														$(this)
																.attr("class",
																		"m-lottery-list")
													}
												} else {
													if (k % 3 == 0) {
														$(this)
																.attr("class",
																		"m-lottery-list m-lottery-special")
													} else {
														$(this)
																.attr("class",
																		"m-lottery-list")
													}
												}
											})
						};
						var a = ",";
						var r = function() {
							var g = false;
							var e = 0;
							var j = function(k) {
								return "/products/" + k + ".html"
							};
							var h = (function(k) {
								if (k != null) {
									e = k;
									var m = function(B) {
										var p = B.length > 24 ? 24 : B.length;
										for (var C = B.length - 1; C >= (B.length - p); C--) {
											if (a.indexOf(","
													+ B[C].lotteryProductId
													+ ",") < 0) {
												a += B[C].lotteryProductId
														+ ",";
												var o = '<div class="m-lottery-list m-lottery-anning"><ul><li class="f-lott-comm"><a href="'
														+ j(B[C].lotteryProductId)
														+ '" target="_blank" title="'
														+ B[C].lotteryProductName
														+ '"><img src="'
														+ $img
														+ B[C].lotteryProductImg
														+ '"></a></li><li class="f-lott-detailed"><dl><dt><a href="'
														+ j(B[C].lotteryProductId)
														+ '" target="_blank" title="(\u7b2c'
														+ B[C].lotteryProductPeriod
														+ "\u671f)"
														+ B[C].lotteryProductName
														+ '">(\u7b2c'
														+ B[C].lotteryProductPeriod
														+ "\u671f)"
														+ B[C].lotteryProductName
														+ "</a></dt><dd>\u5546\u54c1\u4ef7\u503c\uff1a\uffe5"
														+ B[C].lotteryProductPrice
														+ '</dd><dd class="z-ymy">\u5df2\u6ee1\u5458</dd><dd class="z-jx-time"><p>\u63ed\u6653\u5012\u8ba1\u65f6</p><cite><span class="minute">00</span><em>:</em><span class="second">00</span><em>:</em><span><i class="millisecond">0</i><i class="last">0</i></span></cite></dd></dl></li></ul><b></b></div>';
												var n = $(o);
												t.children("div:last").remove();
												t.prepend(n);
												n
														.StartTimeOut(
																B[C].lotteryProductId,
																parseInt(B[C].lotteryProductEndDate))
											}
										}
										resetLotteryListStyle();
										t.children("div:gt(" + (s - 1) + ")")
												.remove()
									};
									if (g) {
										m(k)
									} else {
										$.getScript(d + "/js/lotterytime2.js",
												function() {
													g = true;
													m(k)
												})
									}
								}
								setTimeout(i, 5000)
							});
							var i = function() {
								/*return;*/
								$
										.ajax({
											url : "/lottery/lotteryproductutilList.action",
											type : "GET",
											data : "json="
													+ new Date().getTime(),
											success : function(k) {
												if (k != null) {
													h(k)
												}
											}
										})
							};
							i()
						};
						r()
					};
					c();
					$(".scrollLoading").scrollLoading()
				});
