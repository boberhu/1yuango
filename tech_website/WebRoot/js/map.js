var getOrderNextPageFun = null;
var RUFun = null;
var RGFun = null;
$(document)
		.ready(
				function() {
					function U(d, a, c, b) {
						$.getJSON(d + "/JPDataMap?action=" + a + "&" + c
								+ "&fun=?", b)
					}
					var ai = $(".MapInfoCon");
					var an = ai.find("div.searchContent").html();
					var ap = $("#MapInfoTab");
					var V = $("#liNav").html();
					var ac = $("#divMore");
					var aq = $("#pageDialogBG");
					var aa = $("#keywords");
					var al = $("#keyword");
					var ak = "mapCity";
					var Y = null;
					var ae = null;
					var S;
					var M = null;
					var Z = false;
					var W = null;
					var T = 1;
					var O;
					var L;
					var ar = "getAreastatByLevel";
					var R;
					var aj = false;
					var Q = true;
					var X = false;
					var ab = false;
					var am = true;
					var K = true;
					var af = 0;
					var ao = true;
					var N = "http://api.1yyg.com";
					var ad = {
						action : "getAreastatByLevel",
						zoom : "5",
						lng1 : "116.404",
						lat1 : "39.915",
						lng2 : "116.404",
						lat2 : "39.915",
						level : "1",
						keywords : "",
						querytype : "0",
						fidx : 1,
						eidx : 3,
						iscount : 1
					};
					var J = parseInt($("div.header").css("height"))
							+ parseInt($("div.copyright").css("height")) + 34;
					var ag = function() {
						var a = $(window).width();
						if (a < 1000) {
							$("#mainBox").css("width", "1000px")
						} else {
							$("#mainBox").css("width", "100%")
						}
						_Height = $(window).height() - J;
						if (_Height > 100) {
							if (_Height < 560) {
								_Height = 560
							}
							$("#allmap").css("height", _Height + "px");
							ap.css("top", (_Height - 80) / 2 + "px");
							ai.css("height", _Height + "px").find(
									"div.shadowRight s").css({
								height : _Height + "px"
							})
						}
					};
					ag();
					var ah = function() {
						var d = null;
						var a = null;
						var r = function(H) {
							d = H;
							setTimeout(function() {
								d = null
							}, 60000)
						};
						var q = function(H) {
							a = H;
							setTimeout(function() {
								a = null
							}, 60000)
						};
						Y = ae = new BMap.Point(1, 1);
						var u = new BMap.Map("allmap", {
							minZoom : 5,
							enableMapClick : false
						});
						function k() {
							var at = $.cookie(ak);
							if (at == null || at == "") {
								O = new BMap.Point(116.4035, 39.915);
								u.centerAndZoom(O, 5)
							} else {
								var I = at.toString().split(",");
								O = new BMap.Point(I[0], I[1]);
								u.centerAndZoom(O, 12)
							}
							var H = new BMap.LocalCity();
							H.get(G);
							R = u.getCenter();
							u.addControl(new BMap.NavigationControl());
							u.addControl(new BMap.ScaleControl());
							u.addControl(new BMap.OverviewMapControl());
							u.enableScrollWheelZoom(true);
							u
									.addEventListener(
											"tilesloaded",
											function() {
												if (ao) {
													var aE = {
														position : aH,
														offset : new BMap.Size(
																-85, -23)
													};
													var aD = new BMap.Label(
															'<span id="1yyglogo" style="color:#22AAFF; margin:0 3;">\u4e00\u5143\u62cd\u8d2d\u7a0b\u5e8f\u9500\u552e\u4e2d\u5fc3</span>',
															aE);
													aD
															.setStyle({
																color : "#666",
																fontSize : "12px",
																height : "20px",
																lineHeight : "20px",
																fontFamily : "\u5b8b\u4f53",
																borderRadius : "3px",
																border : "1px solid #FFA366",
																padding : "0 5px",
																background : "#FFFFE3"
															});
													aD.setZIndex(10000);
													var aF = new BMap.Icon(
															"/Images/logomarker.png",
															new BMap.Size(23,
																	36),
															{
																anchor : new BMap.Size(
																		12, 22)
															});
													var aH = new BMap.Point(
															116.537427,
															39.92369);
													var aG = new BMap.Marker(
															aH,
															{
																icon : aF,
																offset : new BMap.Size(
																		3, -12)
															});
													aG.disableMassClear();
													aG.setZIndex(10000);
													u.addOverlay(aG);
													aG
															.addEventListener(
																	"mouseover",
																	function() {
																		this
																				.setLabel(aD)
																	});
													aG
															.addEventListener(
																	"mouseout",
																	function() {
																		if (this
																				.getLabel() != null) {
																			this
																					.getLabel()
																					.remove()
																		}
																	});
													var aB = '<div class="m_boxCon"><div class="m_title"><b class="gray6">\u4e00\u5143\u62cd\u8d2d\u7a0b\u5e8f\u9500\u552e\u4e2d\u5fc3</b></div><div class="blockCon"><ul><li class="company"><span><a target="_blank" href="http://www.591jx.net" title="\u5173\u4e8e\u62cd\u8d2d"><img src="http://www.591jx.net/Images/new-logo.png"/></a></span><dl><dt><p>\u5730 \u5740\uff1a\u5317\u4eac\u5e02\u671d\u9633\u533a\u9510\u57ce\u56fd\u9645A\u5ea79\u5c42</p></dt><dd>\u9500\u552e\u70ed\u7ebf\uff1a<em>400-000-6907</em></dd><dd>\u90ae \u7bb1\uff1a<em>service@1ypg.com</em></dd></dl></li></ul></div></div>';
													var aC = new BMap.InfoWindow(
															aB,
															{
																enableMessage : false
															});
													aC.setWidth(330);
													aG
															.addEventListener(
																	"click",
																	function() {
																		this
																				.openInfoWindow(aC)
																	});
													u.addEventListener(
															"zoomend", y);
													u.addEventListener(
															"moveend", j);
													ao = false;
													y()
												}
											})
						}
						k();
						function G(I) {
							var H = new BMap.Geocoder();
							H.getPoint(I.name, function(au) {
								if (au) {
									$.cookie(ak, au.lng + "," + au.lat, {
										expires : 7,
										path : "/"
									});
									if (O.lng != au.lng || O.lat != au.lat) {
										u.centerAndZoom(au, 12)
									}
								}
							}, "")
						}
						var h = $("#helpIcon");
						var D = $("#helpDesc");
						h.hover(function() {
							$(this).addClass("hover");
							D.show()
						}, function() {
							$(this).removeClass("hover");
							D.hide()
						});
						ap.bind("click", function() {
							g(true)
						});
						var g = function(H) {
							if (ap.attr("class") == "expand"
									&& ap.css("display") == "block") {
								if (H) {
									ai.animate({
										right : "-265px"
									}, {
										duration : 800,
										complete : function() {
											ap.addClass("expand2")
										}
									})
								}
							} else {
								if (T != 1) {
									ai.animate({
										right : "0"
									}, {
										duration : 800,
										complete : function() {
											ap.removeClass("expand2")
										}
									})
								}
							}
							return true
						};
						var E = false;
						var y = function() {
							var H = u.getZoom();
							if (!am) {
								af = H;
								return
							}
							if (H >= 15 && aj) {
								aj = false;
								E = true;
								ad.level = 4
							} else {
								if (H == 14) {
									E = true;
									ad.level = 4
								} else {
									if (H == 12 || H == 13) {
										E = true;
										ad.level = 3
									} else {
										if ((af > 11 || af < 7) && 7 <= H
												&& H <= 11) {
											E = true;
											ad.level = 2
										} else {
											if (af > 6 && 3 <= H && H <= 6) {
												E = true;
												ad.level = 1
											}
										}
									}
								}
							}
							af = H;
							if (E) {
								E = false;
								if (H >= 14 && Q) {
									ad.action = "getDetailPointByAreaid"
								} else {
									ad.action = ar
								}
								am = true;
								K = false;
								e()
							} else {
								am = true;
								K = true
							}
						};
						var i = false;
						var j = function() {
							if (!K) {
								return
							}
							var H = u.getCenter();
							var at = Math.abs(H.lng - R.lng);
							var av = Math.abs(H.lat - R.lat);
							var I = u.getZoom();
							if (I == 18) {
								if (at > 0.001 || av > 0.001) {
									ad.level = 4;
									i = true
								}
							} else {
								if (I == 17) {
									if (at > 0.003 || av > 0.003) {
										ad.level = 4;
										i = true
									}
								} else {
									if (I == 16) {
										if (at > 0.006 || av > 0.006) {
											ad.level = 4;
											i = true
										}
									} else {
										if (I >= 14) {
											if (at > 0.015 || av > 0.015) {
												ad.level = 4;
												i = true
											}
										} else {
											if (I == 13) {
												if (at > 0.02 || av > 0.02) {
													ad.level = 3;
													i = true
												}
											} else {
												if (I == 12) {
													if (at > 0.08 || av > 0.08) {
														ad.level = 3;
														i = true
													}
												}
											}
										}
									}
								}
							}
							if (i) {
								_MoveCount = 0;
								i = false;
								if (I >= 14 && Q) {
									ad.action = "getDetailPointByAreaid"
								} else {
									ad.action = ar
								}
								aj = true;
								am = false;
								K = true;
								R = H;
								e()
							} else {
								am = true;
								K = true
							}
						};
						var e = function() {
							var aD = u.getBounds();
							var aA = aD.getSouthWest();
							var H = aD.getNorthEast();
							ad.lng1 = aA.lng;
							ad.lat1 = aA.lat;
							ad.lng2 = H.lng;
							ad.lat2 = H.lat;
							var aB = H.lng + "" + H.lat;
							reqPointSign = aB;
							var aC = ad.level;
							var I = T;
							u.clearOverlays();
							var az = function(au) {
								if (au.code == 0 && aB == reqPointSign) {
									if (aC == 1 && I == 1) {
										r(au)
									} else {
										if (aC == 2 && I == 1) {
											q(au)
										}
									}
									result = au.result;
									var av = 0;
									$
											.each(
													result,
													function(ay) {
														if (aB == reqPointSign) {
															var aw = this.areastat;
															var aF = new BMap.Point(
																	this.longitude,
																	this.latitude);
															var ax = new BMap.Point(
																	this.orderlongitude,
																	this.orderlatitude);
															if (this.isstat == undefined) {
																av = 0
															} else {
																if (this.isstat == 1
																		&& aw == 1) {
																	aF = ax
																}
																av = this.isstat
															}
															o(aF, ax, aw, av,
																	ay)
														}
													})
								} else {
									if (au.code == 1) {
										if (Z) {
											Z = false;
											setTimeout(function() {
												u.panTo(W)
											}, 100)
										}
									}
								}
								K = true;
								am = true
							};
							var at = null;
							if (aC == 1 && I == 1 && d != null) {
								at = d
							} else {
								if (aC == 2 && I == 1 && a != null) {
									at = a
								}
							}
							if (at == null) {
								U(N, ad.action, "level="
										+ (ad.level == 1 ? 2 : ad.level)
										+ "&lng1=" + ad.lng1 + "&lat1="
										+ ad.lat1 + "&lng2=" + ad.lng2
										+ "&lat2=" + ad.lat2 + "&keywords="
										+ ad.keywords + "&querytype="
										+ ad.querytype + "&fidx=" + ad.fidx
										+ "&eidx=" + ad.eidx + "&iscount="
										+ ad.iscount, az)
							} else {
								az(at)
							}
						};
						var o = function(aK, aP, aQ, aN, aM) {
							if (aQ == 1 || ad.level == 4 || (aQ > 1 && aN == 0)) {
								var aO = {
									position : aK,
									offset : new BMap.Size(-30, -23)
								};
								var aL = new BMap.Label(
										'\u786e\u8ba4\u5730\u5740<span style="color:#ca2a44; margin:0 3px;">'
												+ aQ + "</span>\u4e2a", aO);
								aL.setStyle({
									color : "#666",
									fontSize : "12px",
									height : "20px",
									lineHeight : "20px",
									fontFamily : "\u5b8b\u4f53",
									borderRadius : "3px",
									border : "1px solid #22aaff",
									padding : "0 5px",
									background : "#D9F0F9"
								});
								aL.setZIndex(9999);
								var aH = new BMap.Icon("/Images/marker.png",
										new BMap.Size(27, 30), {
											anchor : new BMap.Size(13, 15)
										});
								var aw = new BMap.Marker(aK, {
									icon : aH,
									offset : new BMap.Size(3, -12)
								});
								aw.setZIndex(0);
								u.addOverlay(aw);
								if (u.getZoom() >= 14 || X) {
									aP = aK
								}
								var H = '<div class="win_loading"><img src="/Images/goods_loading2.gif" />\u6b63\u5728\u52aa\u529b\u52a0\u8f7d...</div>';
								var aG = new BMap.InfoWindow(H, {
									enableMessage : false
								});
								aG.setWidth(300);
								var aJ = new BMap.Icon(
										"/Images/markerover.png",
										new BMap.Size(29, 33), {
											anchor : new BMap.Size(14, 16)
										});
								aw.addEventListener("mouseover", function() {
									this.setZIndex(50);
									if (!aG.isOpen()) {
										this.setLabel(aL);
										this.setIcon(aJ)
									}
								});
								aw.addEventListener("mouseout", function() {
									this.setZIndex(0);
									if (this.getLabel() != null) {
										this.getLabel().remove()
									}
									if (!aG.isOpen()) {
										this.setIcon(aH)
									}
								});
								aG.addEventListener("open", function() {
									if (M != null) {
										clearTimeout(M);
										M = null
									} else {
										K = false;
										am = false;
										M = setTimeout(function() {
											K = true;
											am = true
										}, 1000)
									}
									aw.setIcon(aJ)
								});
								aG.addEventListener("close", function() {
									aw.setIcon(aH)
								});
								aw
										.addEventListener(
												"click",
												function() {
													ad.iscount = 1;
													ad.fidx = 1;
													ad.eidx = 3;
													this.openInfoWindow(aG);
													if (Y.lng == aP.lng
															&& Y.lat == aP.lat) {
														aG.setContent(S);
														z(aP, aw);
														if (M != null) {
															clearTimeout(M);
															M = null
														}
														K = false;
														am = false;
														M = setTimeout(
																function() {
																	K = true;
																	am = true
																}, 1000);
														return
													}
													Y = aP;
													U(
															N,
															"getOrderDetailByPoint",
															"lng="
																	+ aP.lng
																	+ "&lat="
																	+ aP.lat
																	+ "&keywords="
																	+ ad.keywords
																	+ "&querytype="
																	+ ad.querytype
																	+ "&fidx="
																	+ ad.fidx
																	+ "&eidx="
																	+ ad.eidx
																	+ "&iscount="
																	+ ad.iscount,
															function(av) {
																if (av.code == 0) {
																	var at = av.result;
																	var ay = av.count;
																	L = at[0].useraddress;
																	H = '<div class="m_boxCon"><div class="m_title gray6"><b>'
																			+ L
																			+ '</b></div><div id="markerBox" class="m_info '
																			+ ((T == 1 || T == 2) ? "un_blockCon"
																					: "blockCon")
																			+ '"><ul>';
																	for (var au = 0; au < ay; au++) {
																		if (au > 1) {
																			break
																		}
																		if (T == 1
																				|| T == 2) {
																			H += '<li><p class="myLogo"><a href="/u/'
																					+ at[au].userweb
																					+ '" target="_blank"><img src="http://faceimg.1yyg.com/UserFace/'
																					+ at[au].userphoto
																					+ '" /><s></s></a></p><dl><dt class="pro_name"><a href="http://www.1yyg.com/lottery/'
																					+ at[au].ordercodeid
																					+ '.html" target="_blank" title="'
																					+ at[au].codegoodssname
																					+ '">(\u7b2c'
																					+ at[au].codeperiod
																					+ "\u671f)"
																					+ at[au].codegoodssname
																					+ '</a></dt><dd>\u83b7\u5f97\u8005\uff1a<a href="http://u.1yyg.com/'
																					+ at[au].userweb
																					+ '" class="blue" target="_blank" title="'
																					+ at[au].username
																					+ '">'
																					+ at[au].username
																					+ "</a></dd><dd>\u60ca\u559c\uff1a<em>"
																					+ at[au].buyNum
																					+ "</em>\u4eba\u6b21</dd></dl></li>"
																		} else {
																			H += '<li><a href="/lottery/'
																					+ at[au].ordercodeid
																					+ '.html" target="_blank"><p><img src="http://goodsimg.1yyg.com/GoodsPic/pic-200-200/'
																					+ at[au].goodspic
																					+ '"></p></a><dl><dt class="pro_name"><a href="http://www.1yyg.com/lottery/'
																					+ at[au].ordercodeid
																					+ '.html" target="_blank" title="'
																					+ at[au].codegoodssname
																					+ '">(\u7b2c'
																					+ at[au].codeperiod
																					+ "\u671f)"
																					+ at[au].codegoodssname
																					+ "</a></dt><dd>\u5546\u54c1\u4ef7\u503c\uff1a\uffe5"
																					+ at[au].codequantity
																					+ "</dd><dd>\u60ca\u559c\uff1a<em>"
																					+ at[au].buyNum
																					+ "</em>\u4eba\u6b21</dd></dl></li>"
																		}
																	}
																	H += "</ul></div>";
																	if (ay > 2) {
																		H += '<div class="m_more gray9">\u6b64\u4f4d\u7f6e\u5df2\u786e\u8ba4\u5730\u5740<em class="orange">'
																				+ ay
																				+ '</em>\u4e2a\uff0c<a id="showMore" href="javascript:void(0);" class="gray9">\u67e5\u770b\u66f4\u591a>></a></div>'
																	}
																	H += "</div>"
																}
																S = H;
																aG
																		.setContent(H);
																if (M != null) {
																	clearTimeout(M);
																	M = null
																}
																K = false;
																am = false;
																M = setTimeout(
																		function() {
																			K = true;
																			am = true
																		}, 1000);
																$("#markerBox")
																		.find(
																				"li")
																		.hover(
																				function() {
																					$(
																							this)
																							.addClass(
																									"hover")
																				},
																				function() {
																					$(
																							this)
																							.removeClass(
																									"hover")
																				});
																z(aP, aw)
															})
												})
							} else {
								var ax = aQ;
								mouseoverTxt = aP.lng + "," + aP.lat;
								var I = new n(aK, ax, mouseoverTxt, aM);
								var aI = F(aK) || ad.level <= 2;
								if (aI) {
									u.addOverlay(I)
								} else {
									u.removeOverlay(I)
								}
							}
						};
						var l = 0;
						getOrderNextPageFun = function(I) {
							var H = ac;
							ad.fidx = (I - 1) * 9 + 1;
							ad.eidx = I * 9;
							$("#loading").show();
							H.find(".m_info").children("ul").empty();
							U(
									N,
									"getOrderDetailByPoint",
									"lng=" + ae.lng + "&lat=" + ae.lat
											+ "&keywords=" + ad.keywords
											+ "&querytype=" + ad.querytype
											+ "&fidx=" + ad.fidx + "&eidx="
											+ ad.eidx + "&iscount="
											+ ad.iscount,
									function(aC) {
										if (aC.code == 0) {
											$("#loading").hide();
											var aE = aC.result;
											if (ad.iscount == 1) {
												l = aC.count;
												ad.iscount = 0
											}
											if (l <= 4) {
												ac.css("height", "250px")
											} else {
												if (l > 6) {
													ac
															.css("width",
																	"882px")
															.css(
																	"height",
																	l > 9 ? 400
																			: 360 + "px")
												}
											}
											var aB = ($(window).width() - ac
													.width()) / 2;
											var aA = ($(window).height() - ac
													.height()) / 2;
											ac.css({
												left : aB + "px",
												top : aA + "px"
											});
											var aF = "";
											for (var aG = 0; aG < aE.length; aG++) {
												if (T == 1 || T == 2) {
													aF += '<li><p><a href="http://u.1yyg.com/'
															+ aE[aG].userweb
															+ '" target="_blank"><img src="http://faceimg.1yyg.com/UserFace/'
															+ aE[aG].userphoto
															+ '" /><s></s></a></p><dl><dt class="pro_name"><a href="http://www.1yyg.com/lottery/'
															+ aE[aG].ordercodeid
															+ '.html" target="_blank" title="'
															+ aE[aG].codegoodssname
															+ '">(\u7b2c'
															+ aE[aG].codeperiod
															+ "\u671f)"
															+ aE[aG].codegoodssname
															+ '</a></dt><dd>\u83b7\u5f97\u8005\uff1a<a href="http://u.1yyg.com/'
															+ aE[aG].userweb
															+ '" class="blue" target="_blank" title="'
															+ aE[aG].username
															+ '">'
															+ aE[aG].username
															+ "</a></dd><dd>\u60ca\u559c\uff1a<em>"
															+ aE[aG].buyNum
															+ "</em>\u4eba\u6b21</dd></dl></li>"
												} else {
													aF += '<li><a href="http://www.1yyg.com/lottery/'
															+ aE[aG].ordercodeid
															+ '.html" target="_blank"><p><img src="http://goodsimg.1yyg.com/GoodsPic/pic-200-200/'
															+ aE[aG].goodspic
															+ '"></p></a><dl><dt class="pro_name"><a href="http://www.1yyg.com/lottery/'
															+ aE[aG].ordercodeid
															+ '.html" target="_blank" title="'
															+ aE[aG].codegoodssname
															+ '">(\u7b2c'
															+ aE[aG].codeperiod
															+ "\u671f)"
															+ aE[aG].codegoodssname
															+ "</a></dt><dd>\u5546\u54c1\u4ef7\u503c\uff1a\uffe5"
															+ aE[aG].codequantity
															+ "</dd><dd>\u60ca\u559c\uff1a<em>"
															+ aE[aG].buyNum
															+ "</em>\u4eba\u6b21</dd></dl></li>"
												}
											}
											$("#mTitle").html(L);
											$("#orderCount").html(l);
											if (T == 3) {
												H.find(".m_info").removeClass(
														"un_blockCon")
														.addClass("blockCon")
											} else {
												H.find(".m_info").removeClass(
														"blockCon").addClass(
														"un_blockCon")
											}
											H.find(".m_info").children("ul")
													.html(aF);
											H
													.find(".m_info ul")
													.children("li")
													.hover(
															function() {
																$(this)
																		.addClass(
																				"hover")
															},
															function() {
																$(this)
																		.removeClass(
																				"hover")
															});
											if (l > 9) {
												var aD = p(l, 9, I, 5,
														"getOrderNextPageFun");
												H.children(".m_page").remove();
												H.append(aD)
											} else {
												H.children(".m_info").next(
														"div.m_page").remove()
											}
										}
									})
						};
						var z = function(H, I) {
							$("#showMore").unbind("click")
									.bind(
											"click",
											function() {
												I.closeInfoWindow();
												var aw = $(window).width();
												var ax = $(window).height();
												var ay = $(document.body)
														.height() + 12;
												aq.css({
													width : aw < 1000 ? 1000
															: aw,
													height : ax > ay ? ax : ay
												});
												aq.show();
												if (ae.lng == H.lng
														&& ae.lat == H.lat) {
													ac.show()
												} else {
													ac.css("width", "585px")
															.css("height",
																	"360px");
													$("#loading").show();
													ac.show();
													ae = H;
													ad.iscount = 1;
													getOrderNextPageFun(1)
												}
											});
							$("#moreClose").bind("click", function() {
								ac.hide();
								aq.hide();
								ad.iscount = 0
							})
						};
						function n(at, H, I, av) {
							this._point = at;
							this._text = H;
							this._overText = I;
							this._zIndex = av
						}
						n.prototype = new BMap.Overlay();
						n.prototype.initialize = function(ay) {
							this._map = ay;
							var ax = this._div = document.createElement("div");
							ax.style.position = "absolute";
							ax.style.zIndex = BMap.Overlay
									.getZIndex(this._point.lat);
							ax.setAttribute("lng", this._point.lng);
							ax.setAttribute("lat", this._point.lat);
							var I = this._overText.split(",");
							ax.setAttribute("ordlng", I[0]);
							ax.setAttribute("ordlat", I[1]);
							ax.setAttribute("zIndex", this._zIndex);
							ax.style.backgroundColor = "#EE5D5B";
							ax.style.border = "1px solid #BC3B3A";
							ax.style.borderRadius = "5px";
							ax.style.padding = "2px 8px";
							ax.style.height = "18px";
							var at = this._text.toString().length * 7;
							ax.style.width = at + "px";
							ax.style.fontSize = "12px",
									ax.style.lineHeight = "18px";
							ax.style.fontWeight = "bold";
							ax.style.fontFamily = "arial";
							ax.style.color = "white";
							ax.style.whiteSpace = "nowrap";
							ax.style.MozUserSelect = "none";
							ax.style.zIndex = this._zIndex;
							ax.appendChild(document.createTextNode(this._text));
							var H = this;
							var az = this._arrow = document
									.createElement("div");
							az.style.background = "url(/Images/label.png) no-repeat";
							az.style.position = "absolute";
							az.style.width = "11px";
							az.style.height = "10px";
							az.style.top = "22px";
							az.style.left = parseInt((at + 6) / 2) + "px";
							az.style.overflow = "hidden";
							ax.appendChild(az);
							ax.onmouseover = function() {
								this.style.backgroundColor = "#22aaff";
								this.style.borderColor = "#22aaff";
								this.style.cursor = "pointer";
								this.style.zIndex = "9988";
								az.style.backgroundPosition = "0px -20px"
							};
							ax.onmouseout = function() {
								this.style.backgroundColor = "#EE5D5B";
								this.style.borderColor = "#BC3B3A";
								this.style.zIndex = this.getAttribute("zIndex");
								az.style.backgroundPosition = "0px 0px"
							};
							ax.onclick = function() {
								var aB = new BMap.Point(this
										.getAttribute("lng"), this
										.getAttribute("lat"));
								var aw = ay.getZoom();
								var au = false;
								var av;
								if (aw >= 14) {
									av = 16;
									au = true
								} else {
									if (aw >= 12) {
										av = 14;
										au = true
									} else {
										if (aw >= 7) {
											av = 12;
											au = true
										} else {
											if (aw >= 5) {
												av = 7;
												au = true
											}
										}
									}
								}
								if (au) {
									ay.centerAndZoom(aB, av);
									if (aw >= 7) {
										Z = true;
										W = new BMap.Point(this
												.getAttribute("ordlng"), this
												.getAttribute("ordlat"))
									}
								}
							};
							ay.getPanes().labelPane.appendChild(ax);
							return ax
						};
						n.prototype.draw = function() {
							var H = this._map;
							var I = H.pointToOverlayPixel(this._point);
							this._div.style.left = I.x - 2
									- parseInt(this._arrow.style.left) + "px";
							this._div.style.top = I.y - 32 + "px"
						};
						var b = function(H) {
							u.clearOverlays();
							for (var I = 0; I < H.length; I++) {
								var at = F(H[I].getPosition());
								if (at == true) {
									u.addOverlay(H[I])
								} else {
									u.removeOverlay(H[I])
								}
							}
						};
						var F = function(I) {
							var H = u.getBounds();
							var at = H.getSouthWest();
							var av = H.getNorthEast();
							return ((I.lng >= at.lng && I.lng <= av.lng) && (I.lat >= at.lat && I.lat <= av.lat))
						};
						var C = function() {
							ai.find("div.m_sort").find("a").each(
									function() {
										$(this).unbind("click").bind(
												"click",
												function() {
													if (ab) {
														return
													}
													$(this).addClass("hover")
															.siblings()
															.removeClass(
																	"hover");
													var I = $(this).attr(
															"sortid");
													var H = $(this).attr(
															"title");
													RGFun.changeSortFun(I, H)
												})
									})
						};
						C();
						var f;
						getGoodsListBySortIDFun = function() {
							f = $("div.m_searchTo");
							var at = 0;
							var ay = 4;
							var ax = 1;
							var az = {
								action : "getListBySortID",
								keyword : "",
								sortID : 0,
								sortName : "",
								FIdx : 1,
								EIdx : ay,
								isCount : 1
							};
							var H = function() {
								return "keyword=" + az.keyword + "&sortID="
										+ az.sortID + "&FIdx=" + az.FIdx
										+ "&EIdx=" + az.EIdx + "&isCount="
										+ az.isCount
							};
							var I = function() {
								if (ab) {
									return
								}
								ab = true;
								ai.find("div.m_page").remove();
								var au = f.children(".m_loading");
								au.css("display", "").siblings().css("display",
										"none");
								f.children("span").show();
								ai.find("span.gray6").remove();
								U(
										N,
										az.action,
										H(),
										function(aC) {
											if (aC.code == 0) {
												if (az.isCount == 1) {
													at = aC.count;
													az.isCount = 0
												}
												var aw = aC.result;
												au.css("display", "none")
														.siblings().css(
																"display", "");
												if (az.sortID == 0) {
													f
															.children("span")
															.show()
															.html(
																	'\u5171\u627e\u5230<em class="orange">'
																			+ at
																			+ '</em>\u4e2a\u6709\u5173\u201c<b class="orange">'
																			+ az.sortName
																			+ "</b>\u201d\u7684\u5546\u54c1")
												} else {
													f
															.children("span")
															.show()
															.html(
																	'\u5171\u627e\u5230<em class="orange">'
																			+ at
																			+ '</em>\u4e2a\u5206\u7c7b\u4e3a\u201c<b class="orange">'
																			+ az.sortName
																			+ "</b>\u201d\u7684\u5546\u54c1")
												}
												var av = "";
												$
														.each(
																aw,
																function() {
																	av += "<li goodsID="
																			+ this.goodsID
																			+ '><a href="javascript:void(0);"><img src="http://goodsimg.1yyg.com/GoodsPic/pic-200-200/'
																			+ this.goodsPic
																			+ '" /><dl><dt>'
																			+ this.goodsSName
																			+ '</dt> <dd class="gray9">\u5df2\u786e\u8ba4\u5730\u5740\uff1a<em class="blue">'
																			+ this.orderTrueTotal
																			+ "</em></dd></dl></a></li>"
																});
												f.find("ul").html(av);
												f
														.find("li")
														.each(
																function() {
																	$(this)
																			.bind(
																					"click",
																					function() {
																						$(
																								this)
																								.addClass(
																										"hover")
																								.siblings()
																								.removeClass(
																										"hover");
																						var aA = $(
																								this)
																								.attr(
																										"goodsID");
																						v(aA)
																					})
																});
												if (at > ay) {
													var aD = p(at, ay, ax, 3,
															"RGFun.getNextPageFun");
													ai.append(aD);
													ai.find("div.m_page").css(
															"width", "256px")
												} else {
													ai.find("div.m_page")
															.remove()
												}
											} else {
												if (az.sortID == 0) {
													f
															.html('<span class="gray6"><i></i>\u672a\u627e\u5230\u6709\u5173\u201c<b class="orange">'
																	+ az.sortName
																	+ "</b>\u201d\u7684\u5546\u54c1</span><ul></ul>")
												} else {
													f
															.html('<span class="gray6"><i></i>\u672a\u627e\u5230\u5206\u7c7b\u201c<b class="orange">'
																	+ az.sortName
																	+ "</b>\u201d\u7684\u5546\u54c1</span><ul></ul>")
												}
											}
											ab = false
										})
							};
							this.changeSortFun = function(av, au) {
								az.action = "getListBySortID";
								az.keyword = "";
								az.sortID = av;
								at = 0;
								ax = 1;
								az.sortName = au;
								az.FIdx = 1;
								az.EIdx = ay;
								az.isCount = 1;
								aa.val("");
								I()
							};
							this.changeSearch = function(au) {
								az.action = "getGoodsByKeyword";
								az.keyword = au;
								az.sortID = 0;
								az.sortName = aa.val();
								az.FIdx = 1;
								az.EIdx = ay;
								ax = 1;
								az.isCount = 1;
								ai.find("div.m_sort").find("a").removeClass(
										"hover");
								I()
							};
							this.getNextPageFun = function(au) {
								ax = au;
								az.FIdx = (ax - 1) * ay + 1;
								az.EIdx = ax * ay;
								az.isCount = ax > 0 ? 0 : 1;
								I()
							}
						};
						RGFun = new getGoodsListBySortIDFun();
						var v = function(H) {
							ad.keywords = H;
							ad.level = 1;
							ad.querytype = 1;
							Q = false;
							X = false;
							ar = "getAreaGoodsByGoodsid";
							if (u.getZoom() != 5) {
								u.centerAndZoom(
										new BMap.Point(116.404, 39.915), 5)
							} else {
								ad.action = ar;
								e()
							}
						};
						var m = function(H) {
							H
									.unbind("focus")
									.bind("focus", function() {
										$(this).css("color", "#666")
									})
									.keydown(
											function(I) {
												if (13 == ((window.event) ? event.keyCode
														: I.keyCode)) {
													x();
													g(false)
												}
											})
						};
						m(aa);
						m(al);
						var w = new BMap.Autocomplete({
							input : "keyword",
							location : u
						});
						al.focus();
						var A = function(H) {
							function I() {
								_MoveCount = 6;
								Q = true;
								X = false;
								ad.querytype = 0;
								ar = "getAreastatByLevel";
								e()
							}
							var at = new BMap.LocalSearch(u, {
								renderOptions : {
									map : u
								},
								onMarkersSet : function(aw) {
									if (aw != null && aw.length > 0) {
										for (var ax = 0; ax < aw.length; ax++) {
											aw[ax].marker.remove()
										}
									}
								},
								onSearchComplete : I
							});
							at.search(H)
						};
						var B;
						var c = function() {
							B = $("div.name_search");
							var az = 4;
							var at = 0;
							var ax = 1;
							var H = {
								action : "",
								type : 0,
								keyword : "",
								FIdx : 1,
								EIdx : az,
								isCount : 1,
								lng1 : "116.404",
								lat1 : "39.915",
								lng2 : "116.404",
								lat2 : "39.915"
							};
							var I = function() {
								return "keyword="
										+ encodeURIComponent(H.keyword.replace(
												/(\/)/g, "")) + "&FIdx="
										+ H.FIdx + "&EIdx=" + H.EIdx
										+ "&isCount=" + H.isCount + "&lng1="
										+ H.lng1 + "&lat1=" + H.lat1 + "&lng2="
										+ H.lng2 + "&lat2=" + H.lat2
							};
							var ay = function() {
								if (ab) {
									return
								}
								ab = true;
								B.children(".m_loading").show();
								ai.children("div.m_page").remove();
								U(
										N,
										H.action,
										I(),
										function(aB) {
											var av = "";
											if (aB.code == 0) {
												if (H.isCount == 1) {
													at = aB.count;
													H.isCount = 0
												}
												var aw = aB.result;
												$
														.each(
																aw,
																function() {
																	av += "<li userid="
																			+ this.orderuserid
																			+ ">";
																	av += '<p><a href="http://u.1yyg.com/'
																			+ this.userweb
																			+ '" target="_blank" class="blue"><img src="http://faceimg.1yyg.com/UserFace/'
																			+ this.userphoto
																			+ '" /><s></s></a></p><dl class="gray9"><dt><a href="http://u.1yyg.com/'
																			+ this.userweb
																			+ '" target="_blank" class="blue" title="'
																			+ this.username
																			+ '">'
																			+ this.username
																			+ '</a></dt><dd class="class-icon'
																			+ this.gradeico
																			+ '"><s></s>'
																			+ this.grade
																			+ "</dd>";
																	if (this.orderTotal > 0) {
																		av += '<dd>\u5171\u83b7\u5f97\u5546\u54c1\uff1a<em class="blue">'
																				+ this.orderTotal
																				+ '</em>\u4e2a</dd><dd>\u5df2\u786e\u8ba4\u5730\u5740\uff1a<em class="blue">'
																				+ this.orderTrueTotal
																				+ "</em>\u4e2a</dd>"
																	} else {
																		av += "<dd>\u8fd8\u672a\u83b7\u5f97\u5546\u54c1</dd>"
																	}
																	av += "</dl></li>"
																});
												B.children(".m_loading").hide();
												B.children("ul").html(av);
												ab = false;
												B
														.find("li")
														.each(
																function() {
																	var aA = $(this);
																	if (at == 1) {
																		setTimeout(
																				function() {
																					var aD = aA
																							.attr("userid");
																					s(aD);
																					aA
																							.addClass("hover")
																				},
																				800)
																	} else {
																		aA
																				.bind(
																						"click",
																						function() {
																							aA
																									.hover(
																											function() {
																												aA
																														.addClass("hover")
																											},
																											function() {
																												aA
																														.removeClass("hover")
																											});
																							var aD = aA
																									.attr("userid");
																							s(aD);
																							aA
																									.addClass(
																											"hover")
																									.siblings()
																									.removeClass(
																											"hover")
																						})
																	}
																});
												if (at > az) {
													var au = p(at, az, ax, 3,
															"RUFun.getNextPageFun");
													B.append(au)
												} else {
													B.next("div.m_page")
															.remove()
												}
											} else {
												av = '<h4 class="gray6">\u641c\u7d22\u7ed3\u679c</h4>';
												av += '<h5 class="gray6"><i></i>\u672a\u627e\u5230\u76f8\u5173\u7ed3\u679c</h5>';
												B.html(av);
												ab = false
											}
										})
							};
							this.searchUsersFun = function(au) {
								H.type = 0;
								H.action = "getUserByKeywords";
								H.keyword = au;
								H.FIdx = 1;
								H.EIdx = az;
								H.isCount = 1;
								ax = 1;
								ay()
							};
							this.searchAddressFun = function() {
								var av = u.getBounds();
								var au = av.getSouthWest();
								var aw = av.getNorthEast();
								H.lng1 = au.lng;
								H.lat1 = au.lat;
								H.lng2 = aw.lng;
								H.lat2 = aw.lat;
								H.type = 1;
								H.action = "getUserInfoByLonLat";
								H.FIdx = 1;
								H.EIdx = az;
								H.isCount = 1;
								ax = 1;
								ay()
							};
							this.getNextPageFun = function(au) {
								ax = au;
								H.FIdx = (ax - 1) * az + 1;
								H.EIdx = ax * az;
								H.isCount = ax > 1 ? 0 : 1;
								ay()
							}
						};
						RUFun = new c();
						var s = function(H) {
							ad.keywords = H;
							ad.level = 1;
							ad.querytype = 2;
							Q = false;
							X = true;
							ar = "getOrdersByUserID";
							if (u.getZoom() != 5) {
								u.centerAndZoom(
										new BMap.Point(116.404, 39.915), 5)
							} else {
								ad.action = ar;
								e()
							}
						};
						$(".m_search .m_type")
								.children("a")
								.each(
										function(H) {
											$(this)
													.unbind("click")
													.bind(
															"click",
															function() {
																ac.hide();
																var I = $("#liNav");
																T = H + 1;
																$(this)
																		.addClass(
																				"hover")
																		.siblings()
																		.removeClass(
																				"hover");
																if (T == 1) {
																	al
																			.show()
																			.focus()
																			.val(
																					"");
																	aa.hide();
																	ai
																			.css(
																					"right",
																					"-265px");
																	ap.hide();
																	I.html(V)
																} else {
																	aa
																			.show()
																			.focus()
																			.val(
																					"");
																	al.hide();
																	if (ap
																			.css("display") == "none") {
																		ai
																				.css(
																						"right",
																						"-265px");
																		ap
																				.addClass(
																						"expand2")
																				.show()
																	}
																	if (T == 2) {
																		I
																				.html('<a href="javascript:void(0);" title="\u667a\u80fd\u624b\u673a">\u667a\u80fd\u624b\u673a</a><a href="javascript:void(0);" title="3G\u624b\u673a">3G\u624b\u673a</a><a href="javascript:void(0);" title="\u5b9d\u9a6c">\u5b9d\u9a6c</a><a href="javascript:void(0);" title="\u5355\u53cd">\u5355\u53cd</a>')
																	} else {
																		I
																				.html("")
																	}
																}
																t()
															})
										});
						var t = function() {
							$("#liNav").children("a").each(
									function() {
										$(this).unbind("click").bind(
												"click",
												function() {
													var H = $(this).attr(
															"title");
													if (T == 1) {
														w.setInputValue(H)
													} else {
														aa.val(H)
													}
													g(false);
													x()
												})
									})
						};
						t();
						$(".orangeBtn").bind("click", function() {
							ab = false;
							u.clearOverlays();
							g(false);
							x()
						});
						var x = function() {
							ac.hide();
							var H = aa.val();
							ai
									.find("div.searchContent")
									.html(
											'<div class="name_search"><h4 class="gray6">\u641c\u7d22\u7ed3\u679c</h4><div class="m_loading" style="display: none;">\u6b63\u5728\u52aa\u529b\u52a0\u8f7d\u4e2d...</div><ul></ul></div>');
							B = $("div.name_search");
							switch (T) {
							case 1:
								H = al.val();
								if (H == "") {
									al.focus()
								}
								A(H);
								break;
							case 2:
								ai.find("div.searchContent").html(an);
								f = $("div.m_searchTo");
								C();
								if (H == "") {
									f
											.append('<span class="gray6"><i></i>\u67e5\u8be2\u5546\u54c1\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a</span>');
									ai.find("div.m_page").remove();
									aa.focus();
									return
								}
								RGFun.changeSearch(H);
								break;
							case 3:
								if (H == "") {
									B
											.html('<h4 class="gray6">\u641c\u7d22\u7ed3\u679c</h4><h5 class="gray6"><i></i>\u67e5\u8be2\u6635\u79f0\u4e0d\u80fd\u4e3a\u7a7a</h5>');
									ai.find("div.m_page").remove();
									aa.focus();
									return
								}
								RUFun.searchUsersFun(H);
								break
							}
						};
						$(window).resize(function() {
							ag();
							if (aq.css("display") == "block") {
								var at = $(window).width();
								var ax = $(window).height();
								var H = $(document.body).height() + 12;
								aq.css({
									width : at < 1000 ? 1000 : at,
									height : ax > H ? ax : H
								});
								var aw = ($(window).width() - ac.width()) / 2;
								var I = ($(window).height() - ac.height()) / 2;
								ac.css({
									left : aw + "px",
									top : I + "px"
								})
							}
						});
						var p = function(I, aw, aG, aD, aF) {
							var aC = 1, H = 1, ax = 1;
							var aE = "";
							aC = parseInt(I / aw);
							if (I % aw > 0) {
								aC++
							}
							if (aC < 1) {
								aC = 1
							}
							aE += '<div class="m_page">';
							if (I > 0) {
								H = 1;
								while (H + aD < aC + 1 && H + aD < aG + 2) {
									H += aD - 2
								}
								ax = H + aD - 1;
								if (ax > aC) {
									ax = aC
								}
								if (aG == 1) {
									aE += '<a href="javascript:;" title="\u4e0a\u4e00\u9875" class="m_page_pre m_nopage_pre"></a>'
								} else {
									aE += '<a title="\u4e0a\u4e00\u9875" class="m_page_pre" href="javascript:'
											+ aF + "(" + (aG - 1) + ')"></a>'
								}
								if (H > 1) {
									aE += '<a href="javascript:' + aF
											+ '(1)" class="m_page_num">1</a>';
									aE += aG > 3 ? "<span>...</span>" : ""
								}
								for (var aB = H; aB <= ax; aB++) {
									if (aB != aG) {
										aE += '<a href="javascript:' + aF + "("
												+ aB + ')" class="m_page_num">'
												+ aB + "</a>"
									} else {
										aE += '<a href="javascript:;" class="m_page_current">'
												+ aB + "</a>"
									}
								}
								if (ax < aC) {
									aE += aG < aC - 2 ? "<span>...</span>" : "";
									aE += '<a href="javascript:' + aF + "("
											+ aC + ')" class="m_page_num">'
											+ aC + "</a>"
								}
								if (aG < aC) {
									aE += '<a title="\u4e0b\u4e00\u9875" class="m_page_next" href="javascript:'
											+ aF + "(" + (aG + 1) + ')"></a>'
								} else {
									aE += '<a title="\u4e0b\u4e00\u9875" class="m_page_pre m_nopage_next"></a>'
								}
							}
							aE += "</div>";
							return aE
						}
					};
					var P = function() {
						function a() {
							var b = new Date();
							return b.getFullYear().toString().substring(2, 4)
									+ "." + (b.getMonth() + 1) + "."
									+ b.getDate()
						}
						$
								.getScript(
										"http://api.map.baidu.com/getscript?v=2.0&ak=a9RbDTEvcc6AGiORrcOsn9GX&services=&t="
												+ a(), ah)
					};
					$.getScript("/js/comm.js", P)
				});
