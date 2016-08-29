$(document)
		.ready(
				function() {
					var a = $skin;
					var b = function() {
						$.fn.showSlide = function() {
							var l = $(this);
							var f = $indexImg;
							var k = f.length;
							if (k > 1) {
								var d = $('<a class="rslides_nav rslides1_nav pre" href="javascript:;">Previous</a>');
								var n = $('<a class="rslides_nav rslides1_nav next" href="javascript:;">Next</a>');
								l.parent().append(d);
								l.parent().append(n);
								var o = $('<ul class="rslides_tabs"></ul>');
								for (var h = 1; h <= k; h++) {
									o.append('<li><a href="javascript:;">' + h
											+ "</a></li>")
								}
								l.parent().append(o);
								var imgShow = [];
								for (var i = 0; i < k; i++) {
									imgShow
											.push('<a target="_blank" href="'
													+ f[i].proUrl
													+ '"><img width="710" height="300" alt="'
													+ f[i].title + '" src="'
													+ f[i].proImg + '"></a>')
								}
								var m = o.children("li");
								var j = 0;
								var e = 5000;
								var g = function() {
									l
											.stop(true, false)
											.animate(
													{
														opacity : 0.2
													},
													200,
													function() {
														l.empty().append(
																imgShow[j])
																.animate({
																	opacity : 1
																}, 200);
														m
																.eq(j)
																.addClass(
																		"rslides_here")
																.siblings()
																.removeClass(
																		"rslides_here");
														j++;
														if (j == k) {
															j = 0
														}
													})
								};
								var c = setInterval(g, e);
								m.hover(function() {
									if (c) {
										clearInterval(c)
									}
									j = m.index(this);
									g()
								}, function() {
									c = setInterval(g, e)
								});
								this.hover(function() {
									if (c) {
										clearInterval(c)
									}
								}, function() {
									c = setInterval(g, e)
								}).parent().hover(function() {
									d.show();
									n.show()
								}, function() {
									d.hide();
									n.hide()
								});
								d.hover(function() {
									if (c) {
										clearInterval(c)
									}
								}, function() {
									c = setInterval(g, e)
								}).click(function() {
									j = j - 2;
									if (j < 0) {
										j = j + k
									}
									g();
									return false
								});
								n.hover(function() {
									if (c) {
										clearInterval(c)
									}
								}, function() {
									c = setInterval(g, e)
								}).click(function() {
									g();
									return false
								});
								g()
							} else {
								if (k > 0) {
									l.html(l.next().html())
								}
							}
							l.parent().removeClass("ban_loading")
						};
						function SetCookie(name, value) {
							document.cookie = name + "=" + escape(value)
									+ ";id=1ypg;path=/;domain=" + $domain
						}
						function SetCookieByExpires(name, value, date) {
							var Days = date;
							var exp = new Date();
							exp.setTime(exp.getTime() + Days * 24 * 60 * 60
									* 1000);
							document.cookie = name + "=" + escape(value)
									+ ";id=1ypg;path=/;expires="
									+ exp.toGMTString() + ";domain=" + $domain
						}
						function getCookie(name) {
							var arr = document.cookie.match(new RegExp("(^| )"
									+ name + "=([^;]*)(;|$)"));
							if (arr != null) {
								return unescape(arr[2])
							}
							return null
						}
						function delCookie(name) {
							var exp = new Date();
							exp.setTime(exp.getTime() - 1);
							var cval = getCookie(name);
							if (cval != null) {
								document.cookie = name + "=" + cval
										+ ";id=1ypg;path=/;expires="
										+ exp.toGMTString() + ";domain="
										+ $domain
							}
						}
						var f = function(z) {
							if (z && z.stopPropagation) {
								z.stopPropagation()
							} else {
								window.event.cancelBubble = true
							}
						};
						var k = function(z) {
							$.PageDialog('<div class="mAltFail"><s></s>' + z
									+ "</div>", {
								W : 150,
								H : 60,
								close : false,
								autoClose : true,
								submit : function() {
									location.reload()
								}
							})
						};
						$.fn.addShopCart = function(D, B, C, F, A) {
							var z = "num=" + B + "&codeID=" + C;
							var E = $(this);
							$
									.ajax({
										url : "/list/isStatus.action",
										data : "id=" + C,
										success : function(data) {
											if (data == "false") {
												k("\u5546\u54c1\u5df2\u88ab\u62a2\u5149\u4e86\uff01");
												e()
											} else {
												_IsCartChanged = true;
												if (D == 1) {
													var count = parseInt(data);
													var codeid = C;
													var json = getCookie("products");
													if (json == null
															|| json == "") {
														var val = "[{'pId':"
																+ codeid
																+ ",'num':" + B
																+ "}]";
														SetCookieByExpires(
																"products",
																val, 1)
													} else {
														var flag = false;
														json = eval("(" + json
																+ ")");
														$
																.each(
																		json,
																		function(
																				n,
																				value) {
																			if (codeid == value.pId) {
																				value.num = (parseInt(value.num) + parseInt(B));
																				if ((parseInt(value.num) + parseInt(B)) > count) {
																					value.num = count
																				}
																				flag = true
																			}
																		});
														if (!flag) {
															json.push({
																pId : +codeid,
																num : B
															})
														}
														json = JSON
																.stringify(json);
														SetCookieByExpires(
																"products",
																json, 1)
													}
													showParabola(E, B, F, A)
												} else {
													location.href = "/mycart/index.html"
												}
											}
										}
									})
						};
						var w = function() {
							var A = false;
							var z = function() {
								var B = "getbysortid";
								$("#slideImg").showSlide()
							};
							if (A) {
								z()
							} else {
								A = true;
								z()
							}
						};
						w();
						var d = function() {
							var E = $("#div_guide");
							var G = $("#guide_pre");
							var B = $("#guide_next");
							var K = $("#ul_guide").children("li");
							var H = $("#what_1yyg");
							var J = 0;
							var C = false;
							var D = 0;
							var L = function() {
								E.show();
								if (K.length <= 1) {
									G.hide();
									B.hide()
								} else {
									if (J <= 0) {
										J = 0;
										G.hide();
										B.show()
									} else {
										if (J >= (K.length - 1)) {
											J = K.length - 1;
											G.show();
											B.hide()
										} else {
											G.show();
											B.show()
										}
									}
								}
								K.eq(J).hide().fadeToggle();
								K.eq(J).siblings().hide()
							};
							var M = function() {
								E.hide();
								G.hide();
								B.show();
								K.eq(J).show();
								K.eq(J).siblings().hide();
								P = 0;
								if (N != null) {
									clearInterval(N)
								}
							};
							var I = function() {
								J++;
								L()
							};
							B.bind("click", function() {
								I()
							});
							G.bind("click", function() {
								J--;
								L()
							});
							var F = function() {
								if (C) {
									return
								}
								C = true;
								var Q = E;
								Q.css({
									left : Q.width()
								}).show();
								Q.animate({
									left : 0
								}, {
									queue : false,
									duration : 500,
									complete : function() {
										if (K.length <= 1) {
											G.hide();
											B.hide()
										} else {
											G.hide();
											B.show()
										}
										N = setInterval(A, 1000);
										D = 1
									}
								})
							};
							var z = function() {
								if (!C) {
									return
								}
								C = false;
								var Q = E;
								Q.animate({
									left : Q.width()
								}, {
									queue : false,
									duration : 500,
									complete : function() {
										J = 0;
										M();
										D = 0
									}
								})
							};
							H.bind("click", function() {
								if (D == 0) {
									F()
								} else {
									z()
								}
							});
							$("#guide_close").bind("click", function() {
								z()
							});
							K.each(function(Q) {
								Q = Q + 1;
								if (Q > 1) {
									$(this).hide()
								}
								if (Q < K.length) {
									$(this).children("a").bind("click", I)
								} else {
									$(this).children("a").bind("click", z)
								}
							});
							var P = 0;
							var O = 60;
							var N = null;
							var A = function() {
								P++;
								if (P == O) {
									z()
								}
							};
							E.hover(function() {
								P = 0;
								if (N != null) {
									clearInterval(N)
								}
							}, function() {
								N = setInterval(A, 1000)
							})
						};
						d();
						var p = function() {
							var B = $("#ul_Lottery");
							var z = 20;
							var D = ",";
							var E = false;
							var G = 0;
							var A = null;
							var F = function() {
								/*return;*/
								$
										.ajax({
											url : "/lottery/lotteryproductutilList.html",
											data : "json="
													+ new Date().getTime(),
											success : function(data) {
												if (data != null) {
													H(data, B, z)
												}
												if (A == null) {
													A = setInterval(C, 2000)
												}
											}
										});
								var H = function(K, L, J) {
									var I = function(M, N, U) {
										var S = M.length > U ? U : M.length;
										for (var P = M.length - 1; P >= (M.length - S); P--) {
											var T = M[P];
											if (D.indexOf(","
													+ T.lotteryProductId + ",") < 0) {
												D += T.lotteryProductId + ",";
												var O = '<li id="'
														+ T.lotteryProductId
														+ '" class="current">';
												O += '<dl class="m-in-progress">';
												O += '<dt><a href="/products/'
														+ T.lotteryProductId
														+ '.html" target="_blank" title="'
														+ T.lotteryProductName
														+ '">';
												O += '<img alt="'
														+ T.lotteryProductName
														+ '" src="' + $img
														+ T.lotteryProductImg
														+ '" /></a></dt>';
												O += '<dd class="u-name"><a href="/products/'
														+ T.lotteryProductId
														+ '.html" title=" (\u7b2c'
														+ T.lotteryProductPeriod
														+ "\u671f)"
														+ T.lotteryProductName
														+ '" >(\u7b2c'
														+ T.lotteryProductPeriod
														+ "\u671f)"
														+ T.lotteryProductName
														+ "</a></dd>";
												O += '<dd class="gray">\u4ef7\u503c\uff1a\uffe5'
														+ formatFloat(T.lotteryProductPrice)
														+ "</dd>";
												O += '<dd class="u-time" id="dd_time"><em>\u63ed\u6653\u5012\u8ba1\u65f6</em><span><b>00</b> : <b>00</b> : <b><i>0</i><i>0</i></b></span></dd>';
												O += "</dl>";
												O += "<s></s>";
												O += "</li>";
												var R = $(O);
												if (B.find("li").length >= 5) {
													N.children("li:last")
															.remove()
												}
												N.prepend(R);
												var Q = N.children("li:last");
												if (Q.attr("class") == "current") {
													/*Q.addClass("current2")*/
												} else {
													Q.addClass("col5")
												}
												R.StartTimeOut(
																T.lotteryProductId,
																parseInt(T.lotteryProductEndDate))
											}
										}
									};
									if (E) {
										I(K, L, J)
									} else {
										$.getScript("/js/indexlotteryfun.js",
												function() {
													E = true;
													I(K, L, J)
												})
									}
								}
							};
							var C = function() {
								var H = B.find("dl.m-in-progress").length;
								if (H < 20) {
									z = 20 - H;
									setTimeout(F, 2000)
								}
							};
							F()
						};
						p();
						var t = function() {
							var B = $("#divHotGoodsList");
							var A = 8;
							$.ajax({
								url : "/getIndexHotProductList.action",
								data : "json=" + new Date().getTime(),
								success : function(data) {
									if (data != null) {
										z(data, B, A)
									}
								}
							});
							var z = function(I, E, R) {
								var G = I;
								E.empty();
								var H = $img;
								var N = G.length > R ? R : G.length;
								for (var J = 0; J < N; J++) {
									var D = G[J].productName;
									var L = parseInt(G[J].currentBuyCount);
									var P = parseInt(G[J].productPrice);
									var C = parseInt(P - L);
									var Q = formatFloat((L * 100) / P);
									Q = L > 0 && Q < 1 ? 1 : Q;
									var F = '<div class="g-hotL-list">';
									F += '<div class="g-hotL-con"><ul>';
									F += '<li class="g-hot-pic"><a href="/products/'
											+ G[J].spellbuyProductId
											+ '.html" target="_blank" title="'
											+ D + '">';
									F += '<img alt="' + D + '" src="' + H
											+ G[J].headImage + '" /></a></li>';
									F += '<li class="g-hot-name"><a href="/products/'
											+ G[J].spellbuyProductId
											+ '.html" target="_blank" title="'
											+ D + '">' + D + "</a></li>";
									F += '<li class="gray">\u4ef7\u503c\uff1a\uffe5'
											+ formatFloat(G[J].productPrice)
											+ "</li>";
									F += '<li class="g-progress"><dl class="m-progress">';
									F += '<dt title="\u5df2\u5b8c\u6210' + Q
											+ '%"><b style="width: ' + Q
											+ '%;"></b></dt>';
									F += "<dd>";
									F += '<span class="orange fl"><em>' + L
											+ "</em>\u5df2\u53c2\u4e0e</span>";
									F += '<span class="gray6 fl"><em>'
											+ P
											+ "</em>\u603b\u9700\u4eba\u6b21</span>";
									F += '<span class="blue fr"><em>' + C
											+ "</em>\u5269\u4f59</span>";
									F += "</dd></dl></li>";
									F += '<li><a href="/products/'
											+ G[J].spellbuyProductId
											+ '.html" class="u-imm" target="_blank" title="\u7acb\u5373'
											+ $saitName + '">\u7acb\u5373'
											+ $saitName + "</a></li>";
									F += "</ul></div></div>";
									var M = $(F);
									M.hover(function() {
										$(this).addClass("g-hotL-hover")
									}, function() {
										$(this).removeClass("g-hotL-hover")
									});
									E.append(M)
								}
								var O = E.children("div").length;
								for (var J = 0; J < (8 - O); J++) {
									E
											.append('<div class="g-hotL-list" '
													+ (J > 5 && K ? 'style="display:none;"'
															: "")
													+ '><div class="g-hotL-con"></div></div>')
								}
								$(window).resize(function() {
									if ($(window).width() <= 1170) {
										E.children("div:gt(5)").hide()
									} else {
										E.children("div").show()
									}
								})
							}
						};
						var u = function() {
							var H = $("#UserBuyNewList");
							var I = H.children("li").length;
							var G = $("#hidBuyID").val();
							var A = 0;
							var B = 14;
							var C = 7;
							var z = 0;
							var F = function() {
								$
										.ajax({
											url : "/getNowBuyProduct.action",
											data : "pageNo=" + 1 + "&pageSize="
													+ 14,
											success : function(data) {
												var M = data;
												var O = 7;
												var J = M.length > O ? O
														: M.length;
												for (var L = (J - 1); L >= 0; L--) {
													var K = "<li>";
													K += '<span class="fl"><a href="'
															+ $www
															+ "/u/"
															+ M[L].userId
															+ '.html" target="_blank" title="'
															+ M[L].buyer + '">';
													K += '<img alt="'
															+ M[L].buyer
															+ '"  width="40" height="40" src="'
															+ M[L].headImage
															+ '" /><i></i></a></span>';
													K += "<p>";
													K += '<a target="_blank" href="'
															+ $www
															+ "/u/"
															+ M[L].userId
															+ '.html" title="'
															+ M[L].buyer
															+ '" class="blue">'
															+ M[L].buyer
															+ "</a><br />";
													K += '<a target="_blank" href="/products/'
															+ M[L].spellbuyProductId
															+ '.html" target="_blank" title="'
															+ M[L].productName
															+ '" class="u-ongoing">'
															+ M[L].productName
															+ "</a>";
													K += "</p>";
													K += "</li>";
													if (I >= B) {
														H.children("li").eq(
																C + 1).remove();
														I--
													}
													H.append(K);
													I++;
													if (L == 0) {
														G = M[0].userId
													}
												}
											}
										})
							};
							F();
							var E = function() {
								if (z >= 5) {
									z = 0;
									F()
								} else {
									z++
								}
								var K = H.find("li:last");
								var J = 89;
								H.prepend(K).css("marginTop", "-" + J + "px");
								H.animate({
									marginTop : "0px"
								}, 800)
							};
							var D = setInterval(E, 2000);
							H.hover(function() {
								clearInterval(D);
								D = null
							}, function() {
								D = setInterval(E, 2000)
							})
						};
						var n = false;
						var e = function() {
							var B = $("#divSoonGoodsList");
							var C = 24;
							var D = function() {
								return "sortID=0&brandID=0&orderFlag=10&FIdx=1&EIdx="
										+ C + "&isCount=0"
							};
							$.ajax({
								url : "/getIndexSoonGoodsList.action",
								data : "json=" + new Date().getTime(),
								success : function(data) {
									if (data != null) {
										A(data, B, C)
									}
								}
							});
							var A = function(G, E, F) {
								if (n) {
									z(G, E, F)
								} else {
									$.getScript(a + "/js/parabola.js",
											function() {
												n = true;
												z(G, E, F)
											})
								}
							};
							var z = function(N, G, U) {
								var J = N;
								G.empty();
								var K = $img;
								var R = J.length > U ? U : J.length;
								for (var O = 0; O < R; O++) {
									var F = J[O].productName;
									var P = parseInt(J[O].currentBuyCount);
									var S = parseInt(J[O].productPrice);
									var E = parseInt(S - P);
									var T = formatFloat((P * 100) / S);
									T = P > 0 && T < 1 ? 1 : T;
									var L = J[O].productStyle;
									var M = L == "goods_xp" ? "\u65b0\u54c1"
											: L == "goods_tj" ? "\u63a8\u8350"
													: L == "goods_rq" ? "\u4eba\u6c14"
															: L == "goods_xg" ? "\u9650\u8d2d"
																	: "";
									var H = L == "goods_xp" ? "F_goods_xp transparent-png"
											: L == "goods_tj" ? "F_goods_tj transparent-png"
													: L == "goods_rq" ? "F_goods_rq transparent-png"
															: L == "goods_xg" ? "F_goods_xg transparent-png"
																	: "";
									var I = '<div class="soon-list-con" idx="'
											+ (O + 1) + '">';
									I += '<div class="soon-list"><ul>';
									I += '<li class="g-soon-pic"><a href="/products/'
											+ J[O].spellbuyProductId
											+ '.html" target="_blank" title="'
											+ F + '">';
									I += '<img id="img_' + J[O].productId
											+ '" alt="' + F + '" src="' + K
											+ J[O].headImage + '" /></a></li>';
									I += '<li class="soon-list-name"><a href="/products/'
											+ J[O].spellbuyProductId
											+ '.html" target="_blank" title="'
											+ F + '">' + F + "</a></li>";
									I += '<li class="gray">\u4ef7\u503c\uff1a\uffe5'
											+ formatFloat(J[O].productPrice)
											+ "</li>";
									I += '<li class="g-progress"><dl class="m-progress">';
									I += '<dt title="\u5df2\u5b8c\u6210' + T
											+ '%"><b style="width: ' + T
											+ '%;"></b></dt>';
									I += "<dd>";
									I += '<span class="orange fl"><em>' + P
											+ "</em>\u5df2\u53c2\u4e0e</span>";
									I += '<span class="gray6 fl"><em>'
											+ S
											+ "</em>\u603b\u9700\u4eba\u6b21</span>";
									I += '<span class="blue fr"><em>' + E
											+ "</em>\u5269\u4f59</span>";
									I += "</dd></dl></li>";
									I += "<li>";
									I += '<a href="/products/'
											+ J[O].spellbuyProductId
											+ '.html" target="_blank" title="\u7acb\u5373'
											+ $saitName
											+ '"  class="u-now">\u7acb\u5373'
											+ $saitName + "</a>";
									I += '<a href="javascript:;"  title="\u52a0\u5165\u5230\u8d2d\u7269\u8f66" codeid="'
											+ J[O].spellbuyProductId
											+ '" goodsid="'
											+ J[O].productId
											+ '" surplus="'
											+ E
											+ '" class="u-cart"><s></s></a>';
									I += "</li></ul>";
									if (M != "") {
										/*I += '<div class="f-callout"><span class="' + H + '"><i>' + M + '</i></span></div>'*/
										I += '<div class="f-callout"><span class="' + H + '"></span></div>'
									}
									I += "</div></div>";
									var Q = $(I);
									Q
											.hover(
													function() {
														var W = $(this).attr(
																"idx");
														var V = W % 4 == 0 ? "soon-list-hover soon-list-hover2"
																: "soon-list-hover";
														$(this).addClass(V)
													},
													function() {
														var W = $(this).attr(
																"idx");
														var V = W % 4 == 0 ? "soon-list-hover soon-list-hover2"
																: "soon-list-hover";
														$(this).removeClass(V)
													});
									Q
											.find("a.u-cart")
											.click(
													function(Y) {
														f(Y);
														var aa = $(this);
														var V = aa.attr("goodsid");
														var X = $("#img_" + V);
														var W = X
																.attr("src")
																.substring(
																		X
																				.attr(
																						"src")
																				.lastIndexOf(
																						"/productImg/"));
														var Z = aa
																.attr("surplus");
														aa
																.addShopCart(
																		1,
																		1,
																		V,
																		X,
																		function() {
																			_InsertIntoCart(
																					V,
																					W,
																					1,
																					Z,
																					F)
																		})
													});
									G.append(Q)
								}
							}
						};
						var i = function() {
							var A = 12;
							var z = $("#divNewGoodsList");
							$.ajax({
								url : "/getIndexNewProductList.action",
								data : "json=" + new Date().getTime(),
								success : function(data) {
									if (data != null) {
										B(data, z, A)
									}
								}
							});
							var B = function(H, D, M) {
								var F = H;
								D.empty();
								var G = $img;
								var K = F.length > M ? M : F.length;
								for (var I = 0; I < K; I++) {
									var C = F[I].productName;
									var E = '<div class="soon-list-con" idx="'
											+ (I + 1) + '">';
									E += '<div class="soon-list"><ul>';
									E += '<li class="g-soon-pic"><a href="/products/'
											+ F[I].spellbuyProductId
											+ '.html" target="_blank" title="'
											+ C + '">';
									E += '<img  alt="' + C + '" src="' + G
											+ F[I].headImage + '" /></a></li>';
									E += '<li class="soon-list-name"><a href="/products/'
											+ F[I].spellbuyProductId
											+ '.html" target="_blank" title="'
											+ C + '">' + C + "</a></li>";
									E += '<li class="gray">\u4ef7\u503c\uff1a\uffe5'
											+ formatFloat(F[I].productPrice)
											+ "</li>";
									E += "</ul></div></div>";
									var J = $(E);
									J
											.hover(
													function() {
														var O = $(this).attr(
																"idx");
														var N = O % 4 == 0 ? "soon-list-hover soon-list-hover2"
																: "soon-list-hover";
														$(this).addClass(N)
													},
													function() {
														var O = $(this).attr(
																"idx");
														var N = O % 4 == 0 ? "soon-list-hover soon-list-hover2"
																: "soon-list-hover";
														$(this).removeClass(N)
													});
									D.append(J)
								}
								var L = D.children("div").length;
								for (var I = 0; I < (8 - L); I++) {
									D
											.append('<div class="soon-list-con"><div class="soon-list"></div></div>')
								}
							}
						};
						var h = function() {
							var B = $("#divPostRec");
							var z = $("#ul_PostList");
							var A = $img;
							$
									.ajax({
										url : "/share/ajaxPage.action",
										data : "pageNo=0&pageSize=7&id=new20",
										success : function(data) {
											var K = data;
											var I = K.length;
											var D = "/shareShow/";
											var C = "/u/";
											B.empty();
											z.empty();
											if (I > 0) {
												var E = "<dl>";
												E += '<dt><a href="'
														+ D
														+ K[0].uid
														+ '.html" target="_blank" title="'
														+ K[0].shareTitle
														+ '">';
												E += '<img  src="' + A
														+ "/UserPost/Big/"
														+ K[0].shareImages
														+ '" /></a></dt>';
												E += '<dd class="u-user">';
												E += '<p class="u-head"><a href="'
														+ C
														+ K[0].userId
														+ '.html" target="_blank" title="'
														+ K[0].userName + '">';
												E += '<img alt="'
														+ K[0].shareTitle
														+ '" src="'
														+ K[0].userFace
														+ '" width="40" height="40" /><i></i></a></p>';
												E += '<p class="u-info">';
												E += '<span><a href="'
														+ C
														+ K[0].userId
														+ '.html" target="_blank" title="'
														+ K[0].userName + '">'
														+ K[0].userName
														+ "</a><em>"
														+ K[0].shareDate
														+ "</em></span>";
												E += '<cite><a href="'
														+ D
														+ K[0].uid
														+ '.html" target="_blank" title="'
														+ K[0].shareTitle
														+ '">'
														+ K[0].shareTitle
														+ "</a></cite>";
												E += "</p></dd>";
												E += '<dd class="m-summary"><cite>';
												E += '<a href="'
														+ D
														+ K[0].uid
														+ '.html" target="_blank">'
														+ K[0].shareContent
														+ "</a>";
												E += "</cite><b><s></s></b>";
												E += "</dd></dl>";
												var J = $(E);
												B.html(J);
												I = I >= 7 ? 7 : I;
												for (var H = 1; H < I; H++) {
													E = "";
													E += '<li><a href="'
															+ D
															+ K[H].uid
															+ '.html" target="_blank" title="'
															+ K[H].shareTitle
															+ '">';
													E += '<img alt="'
															+ K[H].shareTitle
															+ '" src="' + A
															+ "/UserPost/Big/"
															+ K[H].shareImages
															+ '" /><p title="'
															+ K[H].shareTitle
															+ '">'
															+ K[H].shareTitle
															+ "</p>";
													E += "</a></li>";
													var J = $(E);
													z.append(J)
												}
												var F = z.children("li").length;
												for (var H = 0; H < (6 - F); H++) {
													z.append("<li></li>")
												}
											}
										}
									})
						};
						var y = 0;
						var v = [ 0, 0, 0, 0 ];
						var r = $("#divHotGoodsList");
						var j = $("#divSoonGoodsList");
						var s = $("#divNewGoodsList");
						var x = $("#divPostRec");
						var m = r.offset().top - $(window).height();
						var g = 0;
						var o = 0;
						var q = 0;
						$(window).scroll(function() {
							l()
						});
						var l = function() {
							y = $(window).scrollTop();
							g = j.offset().top - $(window).height();
							o = s.offset().top - $(window).height();
							q = x.offset().top - $(window).height();
							if (y >= m) {
								if (v[0] == 0) {
									v[0] = 1;
									t();
									u()
								}
							}
							if (y >= g) {
								if (v[1] == 0) {
									v[1] = 1;
									e()
								}
							}
							if (y >= o) {
								if (v[2] == 0) {
									v[2] = 1;
									i()
								}
							}
							if (y >= q) {
								if (v[3] == 0) {
									v[3] = 1;
									h()
								}
							}
						};
						if ($(window).scrollTop() > m) {
							l()
						}
					};
					b()
				});
