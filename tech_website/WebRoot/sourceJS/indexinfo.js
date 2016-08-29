
var CBLFun = null;
var PagePOPLoginOK = null;
var ShareUrl, ShareTitle;
$(function () {
	var M = "http://skin.1ypg.com";
	var G = "http://img.1ypg.com";
	var C = $("#hidPostID").val();
	var D = $("#hidIsLogin").val() == "1";
	var L = $("#DCMainLeft");
	var B = $("#emReplyCount");
	var H = $("#commentMain");
	var K = function () {
		var N = $("#referralsLink");
		if (N.length > 0) {
			N.attr("href", N.attr("href").replace(".html", "-p-") + C + ".html");
		}
	};
	K();
	var J = function () {
		var N = "<dl class=\"sAltOK\"><dd>\u63d0\u4ea4\u6210\u529f!</dd></dl>";
		$.PageDialog(N, {W:160, H:50, close:false, autoClose:true, submit:function () {
		}});
	};
	var F = function (O) {
		var N = "<dl class=\"sAltFail\"><dd>" + O + "</dd></dl>";
		$.PageDialog(N, {W:185, H:50, close:false, autoClose:true, submit:function () {
		}});
	};
	var I = function () {
		var c = G + "/UserPost";
		var N = $("#hidAllPic").val();
		var U = N.split(",");
		var P = $("<img id=\"imgBig\" src=\"" + c + "/Big/" + U[0] + "\" onabort=\"this.src=this.src\" alt=\"\" />");
		$("#bigImgBox").append(P);
		var d = U.length;
		var Y = $("#ulMenu");
		for (var Z = 0; Z < d; Z++) {
			var b = U[Z];
			Y.append("<li><img src=\"" + c + "/Small/" + b + "\" onabort=\"this.src=this.src\"></li>");
		}
		var T = $("#onload");
		var e;
		Y.children("li").each(function (f) {
			$(this).bind("click", function () {
				T.show();
				P.attr("src", c + "/Big/" + U[f]).load(function () {
					T.hide();
				});
			});
		});
		var X = $("#arrowTop");
		var a = $("#arrowBottom");
		var O = function (f) {
			X.attr("class", f);
		};
		var V = function (f) {
			a.attr("class", f);
		};
		if (d <= 3) {
			O("arrow_bottom_click");
			V("arrow_bottom");
		} else {
			O("arrow_bottom_click");
			V("arrow_top_click");
			var S = 0;
			var Q = 114;
			var W = 0;
			var R = true;
			X.bind("click", function () {
				if (R) {
					if (S > 0) {
						W += Q;
						R = false;
						Y.stop(true, false).animate({"top":W + "px"}, 1000, function () {
							S--;
							V("arrow_top_click");
							if (S == 0) {
								O("arrow_bottom_click");
							}
							R = true;
						});
					}
				}
			});
			a.bind("click", function () {
				if (R) {
					if (S < (d - 3)) {
						W -= Q;
						R = false;
						Y.stop(true, false).animate({"top":W + "px"}, 1000, function () {
							S++;
							O("arrow_top");
							if (S == (d - 3)) {
								V("arrow_bottom");
							}
							R = true;
						});
					}
				}
			});
		}
	};
	var A = function () {
		var N = function () {
			var S = $("#emHits");
			var R = S.html();
			S.parent().hover(function () {
				$(this).html("+1");
			}, function () {
				$(this).html("<em id=\"emHits\">" + R + "</em>&nbsp;\u4eba\u7fa1\u6155\u5ac9\u5992\u6068");
			}).bind("click", function () {
				if (!isLoaded) {
					return false;
				}
				var Y = $("#divMoodTS");
				var X = function () {
					R = parseInt(R) + 1;
					Y.attr("class", "tsed").html("\u4eb2\uff0c\u7fa1\u6155\u6210\u529f\u54e6\uff01").show().fadeOut(3000, function () {
						Y.hide();
					});
				};
				var T = function () {
					Y.attr("class", "ts").html("\u60a8\u5df2\u7ecf\u7fa1\u6155\u8fc7\u4e86\uff01").show().fadeOut(3000, function () {
						Y.hide();
					});
				};
				var W = "postHits" + C;
				var U = $.cookie(W);
				if (U != "1") {
					var V = new JQAjax("/DataServer/InsertPostHits");
					V.OnSuccess(function (Z) {
						if (Z.d == "True") {
							X();
						} else {
							T();
						}
						$.cookie(W, "1", {expires:1, path:"/"});
					});
					V.OnSend("{'postid':" + C + "}", "json", true);
				} else {
					T();
				}
			});
		};
		var O = function (S) {
			var R = S.parent().parent().next("div").find("div:first").find("span");
			S.bind("keyup", function (W) {
				var U = (window.event) ? event.keyCode : W.keyCode;
				var T = $(this);
				var V = T.val();
				if (U == 13) {
					if (V.indexOf("\n\n\n") != -1) {
						V = V.replace("\n\n\n", "\n\n");
						T.val(V);
					} else {
						S.css("height", (S.height() + 20) + "px");
					}
				}
				if (V.length <= 150) {
					R.html(150 - V.length);
				} else {
					R.html("0");
					T.val(V.substr(0, 150));
				}
			}).bind("keydown", function (U) {
				if (!window.event) {
					var T = U.keyCode;
					var T = String.fromCharCode(T).toLowerCase();
					if (U.ctrlKey && T == "v") {
						U.preventDefault();
						U.stopPropagation();
					}
				}
			});
		};
		var Q = function (R, U, S) {
			var T = "/API/ValidImg.html";
			var V = function () {
				var Z = getobj("btnCodeSubmit");
				var Y = getobj("btnSetImg");
				var b = getobj("PDZshowimg");
				var W = function () {
					b.attr("src", T + "?rnd=" + GetRandomNum(1, 10000));
					return false;
				};
				Y.bind("click", W);
				var X = getobj("PDShowErr");
				if (U == 0) {
					X.html("\u9a8c\u8bc1\u7801\u9519\u8bef\uff01").show();
				} else {
					if (U == 1) {
						X.html("\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801\uff01").show();
					}
				}
				var a = getobj("PDTxtCode");
				Z.unbind("click").bind("click", function () {
					var c = a.val();
					if (c == "") {
						X.show().html("\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801\uff01");
					} else {
						S(c);
					}
				});
			};
			$.PageDialog("<div class=\"auth_box\" ><ul><li><input id=\"PDTxtCode\" class=\"text_yzm\" type=\"text\" onpaste=\"return false;\" maxlength=\"6\" /><div id=\"PDShowErr\" class=\"yzwrong\">\u9a8c\u8bc1\u7801\u9519\u8bef</div></li><li><img id=\"PDZshowimg\"  src=\"" + T + "?rnd=" + GetRandomNum(1, 10000) + "\" /><a id=\"btnSetImg\" href=\"javascript:gotoClick();\"><u>\u770b\u4e0d\u6e05\uff0c\u6362\u4e00\u4e2a</u></a></li><li><input id=\"btnCodeSubmit\" type=\"button\" value=\"\u786e \u5b9a\" class=\"ok\" /></li></ul></div>", {W:260, H:160, oL:-188, oT:-150, obj:R, title:"\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801", ready:V});
		};
		var P = function () {
			var S = new popLogin();
			var R = function () {
				var c = G + "/UserFace/";
				var U = function (d) {
					var e = false;
					if (d.length > 0 && d.css("display") == "block") {
						e = true;
					}
					return e;
				};
				var b = function () {
					return "<div class=\"qcomment_box_reply_form\"><div class=\"qcomment_reply_form_textbox\"><textarea name=\"replyTA\"" + (D ? "" : " class=\"hiden\"") + " oncontextmenu=\"return false;\" onpaste=\"return false\"></textarea>" + (D ? "" : "<div name=\"replyLogin\" class=\"reply_login\">\u8bf7\u60a8<a href=\"javascript:gotoClick()\" name=\"replyLoginBtn\">\u767b\u5f55</a>\u6216<a href=\"http://passport.1ypg.com/Register.html?forward=rego\">\u6ce8\u518c</a>\u540e\u518d\u56de\u590d</div>") + "</div><div class=\"qcomment_reply_form_closebt\"><a href=\"javascript:gotoclick();\" class=\"closereply\" title=\"\u5173\u95ed\"><span>\u5173\u95ed</span></a></div></div><div class=\"qcomment_box_reply_send\"><div class=\"qcomment_reply_send_left\">\u60a8\u8fd8\u53ef\u4ee5\u8f93\u5165<span>150</span>\u4e2a\u5b57\uff01</div><div class=\"qcomment_reply_send_right\"><input type=\"button\" value=\"\u63d0\u4ea4\u56de\u590d\" class=\"reply_" + (D ? "" : "un") + "botton\" /></div></div>";
				};
				var a = function (d) {
					return "<div class=\"qcomment_reply_list\"><div class=\"qcomment_reply_list_left\"><img src=\"" + c + d.face + "\" alt=\"\" /></div><div name=\"replyBoxEX\" class=\"qcomment_reply_list_right\"><div class=\"qcomment_reply_list_r_top\"><em>" + d.floor + "\u697c</em><span>" + d.username + "</span>" + (parseInt(d.reFloor) > 0 ? (" \u5bf9 " + d.reFloor + "\u697c") : "") + " \u8bf4\uff1a</div><div class=\"qcomment_reply_list_r_m\">" + d.content + "</div><div class=\"qcomment_reply_list_r_b\"><span><a href=\"javascript:gotoclick()\" name=\"" + (parseInt(d.isDel) == 1 ? "signDelete\">\u5220\u9664" : "signReply\">\u56de\u590d") + "</a></span>" + d.time + "</div></div><div class=\"qhackbox\"></div>";
				};
				var X = function (h, d, f, g, j, e) {
					var i = function () {
						if (g.find("div[name='ReplyTextAreaEX']").length == 0) {
							var o = "<div name=\"ReplyTextAreaEX\" class=\"qcomment_reply_module\">" + b() + "</div>";
							var m = $(o);
							m.find("a.closereply").bind("click", function () {
								m.remove();
							});
							var n = m.find("textarea[name='replyTA']");
							O(n);
							var k = m.find("input.reply_botton");
							k.bind("click", function () {
								if (!isLoaded) {
									return false;
								}
								var p = n.val().trim();
								if (p.length < 3) {
									F("\u56de\u590d\u5185\u5bb9\u4e0d\u80fd\u5c11\u4e8e3\u4e2a\u5b57!");
									return false;
								}
								var q = {"postid":C, "originalContent":p, "code":"", "refReplyId":d, "refFloor":f};
								var r = new JQAjax("/DataServer/InsertPostReply");
								r.OnSuccess(function (t) {
									if (t.d.Code == 0) {
										J();
										j.next("input").val("0");
										j.children("span").html(parseInt(j.children("span").html()) + 1);
										m.remove();
										isLoaded = true;
										W(j, d, e);
									} else {
										if (t.d.Code == 2) {
											D = false;
											PagePOPLoginOK = function () {
												D = true;
												r.OnSend($.toJSON(q), "json", true);
												S.hide();
											};
											S.show();
										} else {
											if (t.d.Code == 3) {
												var s = function (u) {
													q.code = u;
													r.OnSend($.toJSON(q), "json", true);
												};
												Q(k, t.d.Num, s);
											} else {
												if (t.d.Code == 4) {
													F("\u56de\u590d\u5185\u5bb9\u4e0d\u80fd\u5c11\u4e8e3\u4e2a\u5b57!");
												} else {
													if (t.d.Code == 5) {
														F("\u4e0d\u80fd\u5bf9\u81ea\u5df2\u56de\u590d!");
													} else {
														F("\u63d0\u4ea4\u5931\u8d25!");
													}
												}
											}
										}
									}
								});
								r.OnSend($.toJSON(q), "json", true);
							});
							var l = g.find("div[name='replyBoxEX']");
							l.append(m);
						}
					};
					if (D) {
						i();
					} else {
						D = false;
						PagePOPLoginOK = function () {
							D = true;
							S.hide();
							i();
						};
						S.show();
					}
				};
				var Y = function (f, d) {
					if (confirm("\u786e\u5b9a\u8981\u5220\u9664\u5417?")) {
						if (!isLoaded) {
							return false;
						}
						var e = new JQAjax("/DataServer/DeletePostReply");
						e.OnSuccess(function (g) {
							if (g.d.Code == 0) {
								isLoaded = true;
								d();
							} else {
								F("\u5220\u9664\u5931\u8d25!");
							}
						});
						e.OnSend("{'replyId':" + f + "}", "json", true);
					}
				};
				var W = function (h, d, e) {
					var f = h.next("input");
					if (U(e) && f.val() == "1") {
						e.hide();
					} else {
						if (f.val() == "0") {
							var g = new JQAjax("/DataServer/GetPostReplyInfo");
							g.OnSuccess(function (k) {
								var l = e.find("div[name='ReplyList']");
								if ($(k).find("Code").text() == 1) {
									var i = $("<div class=\"qcomment_reply_more\"><a href=\"ReplyDetail-" + C + "-" + d + ".html\">\u67e5\u770b\u672c\u8bc4\u8bba\u6240\u6709\u56de\u590d&gt;&gt;</a></div>");
									var j = h.children("span");
									l.empty();
									$(k).find("PostReplyListTop5").each(function () {
										var n = $(this).find("replyID").text();
										var p = $(this).find("floorID").text();
										var o = {"username":$(this).find("replyUserName").text(), "face":$(this).find("userPhoto").text(), "floor":p, "reFloor":$(this).find("replyRefFloor").text(), "isDel":$(this).find("isDel").text(), "time":$(this).find("replyTime").text(), "content":$(this).find("replyContent").text()};
										var m = $(a(o));
										m.find("a[name='signReply']").bind("click", function () {
											X($(this), d, p, m, h, e);
										});
										m.find("a[name='signDelete']").bind("click", function () {
											var q = function () {
												f.val("0");
												var r = parseInt(j.html()) - 1;
												if (r == 4) {
													i.remove();
												}
												if (r == 0) {
													e.find("div[name='ReplyForm']").removeClass("grayline");
												}
												j.html(r);
												W(h, d, e);
											};
											Y(n, q);
										});
										l.append(m);
									});
									if (parseInt(j.html()) > 5) {
										l.append(i);
									}
								} else {
									l.empty();
								}
								f.val("1");
								e.show();
							});
							g.OnSend({"replyId":d}, "xml", true);
						} else {
							e.show();
						}
					}
				};
				var Z = function (k, e, g) {
					var j = g.find("div[name='ReplyForm']");
					var i = parseInt(k.children("span").html());
					if (i > 0) {
						j.addClass("grayline");
					}
					var d = $(b());
					d.find("a.closereply").bind("click", function () {
						g.hide();
					});
					if (D) {
						var h = d.find("textarea[name='replyTA']");
						O(h);
						var f = d.find("input.reply_botton");
						f.bind("click", function () {
							if (!isLoaded) {
								return false;
							}
							var l = h.val().trim();
							if (l.length < 3) {
								F("\u56de\u590d\u5185\u5bb9\u4e0d\u80fd\u5c11\u4e8e3\u4e2a\u5b57!");
								return false;
							}
							var m = {"postid":C, "originalContent":l, "code":"", "refReplyId":e, "refFloor":0};
							var n = new JQAjax("/DataServer/InsertPostReply");
							n.OnSuccess(function (p) {
								if (p.d.Code == 0) {
									J();
									k.next("input").val("0");
									k.children("span").html(i + 1);
									isLoaded = true;
									Z(k, e, g);
								} else {
									if (p.d.Code == 2) {
										D = false;
										PagePOPLoginOK = function () {
											D = true;
											n.OnSend($.toJSON(m), "json", true);
											S.hide();
										};
										S.show();
									} else {
										if (p.d.Code == 3) {
											var o = function (q) {
												m.code = q;
												n.OnSend($.toJSON(m), "json", true);
											};
											Q(f, p.d.Num, o);
										} else {
											if (p.d.Code == 4) {
												F("\u56de\u590d\u5185\u5bb9\u4e0d\u80fd\u5c11\u4e8e3\u4e2a\u5b57!");
											} else {
												if (p.d.Code == 5) {
													F("\u4e0d\u80fd\u5bf9\u81ea\u5df2\u56de\u590d!");
												} else {
													F("\u63d0\u4ea4\u5931\u8d25!");
												}
											}
										}
									}
								}
							});
							n.OnSend($.toJSON(m), "json", true);
						});
					} else {
						d.find("a[name='replyLoginBtn']").bind("click", function () {
							var l = function () {
								g.hide();
								Z(k, e, g);
							};
							if (D) {
								l();
							} else {
								PagePOPLoginOK = function () {
									D = true;
									S.hide();
									L.find("a[name='replyLoginBtn']").click();
								};
								S.show();
							}
							return false;
						});
					}
					j.empty().append(d);
					W(k, e, g);
				};
				var V = function () {
					var g = getobj("commentItems");
					this.TotalCount = 0;
					this.PageMax = 10;
					this.CurrentIndex = 1;
					var f = {"FIdx":1, "EIdx":this.PageMax, "isCount":1, "postid":C};
					var d = new JQAjax("/DataServer/GetPostReplyGetByID");
					var e = function () {
						if (!isLoaded) {
							return false;
						}
						d.OnSuccess(function (i) {
							var j = parseInt($(i).find("Code").text());
							if (j == 0) {
								if (f.isCount == 1) {
									CBLFun.TotalCount = parseInt($(i).find("Count").text());
								}
								g.empty();
								if (CBLFun.TotalCount > 0) {
									$(i).find("PostReplyList").each(function () {
										var o = $(this).find("replyID").text();
										var k = $(this).find("replyCount").text();
										var r = "<div class=\"qcomment_content_box\"><div class=\"qcomment_content_box_usericon\"><img src=\"" + c + $(this).find("userPhoto").text() + "\" alt=\"\" /></div><div class=\"qcomment_box\"><div class=\"qcomment_box_top\"><span>" + $(this).find("replyUserName").text() + "</span>\u8bf4\uff1a</div><div class=\"qcomment_box_main\">" + $(this).find("replyContent").text().toHTML() + "</div><div class=\"qcomment_box_foot\"><ul><li class=\"time\">" + $(this).find("replyTime").text() + "</li><li><a class=\"signReplay\" href=\"javascript:gotoclick();\">\u56de\u590d(<span>" + k + "</span>)</a><input type=\"hidden\" value=\"0\" /></li><li class=\"hiden\"><a class=\"signOnoff\" href=\"javascript:gotoclick();\">\u5c55\u5f00</a><input type=\"hidden\" value=\"0\" /></li></ul></div><div name=\"Replybox\" class=\"qcomment_box_reply\"><div class=\"qcomment_box_reply_topbj\"></div><div name=\"ReplyForm\" class=\"qcomment_reply_module\"></div><div name=\"ReplyList\" class=\"qcomment_reply_list_module\"></div></div><div class=\"qhackbox\"></div><div class=\"qcomment_box_bottom\"></div></div></div>";
										var q = $(r);
										g.append(q);
										var n = q.find("div[name='Replybox']");
										var m = q.find("a.signReplay");
										m.bind("click", function () {
											Z($(this), o, n);
										});
										n.find("a.closereply").bind("click", function () {
											onOffReply(q);
										});
										var p = n.find("textarea");
										O(p);
										var l = n.find("input.reply_botton");
										l.bind("click", function () {
											if (!isLoaded) {
												return false;
											}
											var s = p.val().trim();
											if (s.length < 3) {
												F("\u56de\u590d\u5185\u5bb9\u4e0d\u80fd\u5c11\u4e8e3\u4e2a\u5b57!");
												return false;
											}
											var t = {"postid":C, "originalContent":s, "code":"", "refReplyId":cmtID, "refFloor":0};
											var u = new JQAjax("/DataServer/InsertPostReply");
											u.OnSuccess(function (w) {
												if (w.d.Code == 0) {
													J();
													m.next("input").val("0");
													m.children("span").html(parseInt(m.children("span").html()) + 1);
													isLoaded = true;
													W(m, o, q);
												} else {
													if (w.d.Code == 2) {
														D = false;
														PagePOPLoginOK = function () {
															D = true;
															u.OnSend($.toJSON(t), "json", true);
															S.hide();
														};
														S.show();
													} else {
														if (w.d.Code == 3) {
															var v = function (x) {
																t.code = x;
																u.OnSend($.toJSON(t), "json", true);
															};
															Q(l, w.d.Num, v);
														} else {
															if (w.d.Code == 4) {
																F("\u56de\u590d\u5185\u5bb9\u4e0d\u80fd\u5c11\u4e8e3\u4e2a\u5b57!");
															} else {
																if (w.d.Code == 5) {
																	F("\u4e0d\u80fd\u5bf9\u81ea\u5df2\u56de\u590d!");
																} else {
																	F("\u63d0\u4ea4\u5931\u8d25!");
																}
															}
														}
													}
												}
											});
											u.OnSend($.toJSON(t), "json", true);
										});
									});
									if (CBLFun.TotalCount > CBLFun.PageMax) {
										g.append("<div id=\"cmtPageNav\" class=\"page_nav\">" + GetAjaxPageNation(CBLFun.TotalCount, CBLFun.PageMax, 5, CBLFun.CurrentIndex, "CBLFun.gotoPageIndex") + "</div>");
									}
								}
							}
						});
						d.OnSend(f, "xml", true);
					};
					var h = function (i, j) {
						CBLFun.CurrentIndex = Math.floor(j / CBLFun.PageMax);
						f.FIdx = i;
						f.EIdx = j;
						if (CBLFun.CurrentIndex > 1) {
							f.isCount = 0;
						} else {
							f.isCount = 1;
						}
						e();
					};
					this.gotoPageIndex = function (i, j) {
						h(i, j);
					};
					this.initData = function () {
						h(1, CBLFun.PageMax);
					};
					this.TotalCountAdd = function (i) {
						CBLFun.TotalCount += i;
					};
				};
				CBLFun = new V();
				CBLFun.initData();
			};
			var T = function () {
				var V = $("#bottomComment");
				var W = function () {
					var Y = $("#replyContent", V);
					O(Y);
					var X = $("#btnSubmit", V);
					X.attr("class", "reply_botton");
					X.bind("click", function () {
						if (!isLoaded) {
							return false;
						}
						var Z = Y.val().trim();
						if (Z.length < 3) {
							F("\u8bc4\u8bba\u5185\u5bb9\u4e0d\u80fd\u5c11\u4e8e3\u4e2a\u5b57!");
							return false;
						}
						var a = {"postid":C, "originalContent":Z, "code":"", "refReplyId":0, "refFloor":0};
						var b = new JQAjax("/DataServer/InsertPostReply");
						b.OnSuccess(function (d) {
							if (d.d.Code == 0) {
								J();
								Y.val("");
								Y.keyup();
								Y.css("height", "40px");
								isLoaded = true;
								var e = parseInt(B.html()) + 1;
								B.html(e);
								if (e == 1) {
									H.show();
									R();
								} else {
									CBLFun.initData();
								}
							} else {
								if (d.d.Code == 2) {
									D = false;
									PagePOPLoginOK = function () {
										D = true;
										b.OnSend($.toJSON(a), "json", true);
										S.hide();
									};
									S.show();
								} else {
									if (d.d.Code == 3) {
										var c = function (f) {
											a.code = f;
											b.OnSend($.toJSON(a), "json", true);
										};
										Q(X, d.d.Num, c);
									} else {
										if (d.d.Code == 4) {
											F("\u8bc4\u8bba\u5185\u5bb9\u4e0d\u80fd\u5c11\u4e8e3\u4e2a\u5b57!");
										} else {
											F("\u63d0\u4ea4\u5931\u8d25!");
										}
									}
								}
							}
						});
						b.OnSend($.toJSON(a), "json", true);
					});
				};
				var U = $("#linklogin", V);
				if (U.length > 0) {
					U.bind("click", function () {
						var X = function () {
							$("#notLogin", V).hide();
							$("#replyContent", V).show();
							W();
						};
						if (D) {
							X();
						} else {
							PagePOPLoginOK = function () {
								D = true;
								S.hide();
								L.find("a[name='replyLoginBtn']").click();
							};
							S.show();
						}
						return false;
					});
				} else {
					W();
				}
			};
			isLoaded = true;
			N();
			T();
			if (parseInt(B.html()) > 0) {
				H.show();
				R();
			}
		};
		$.getScript(M + "/JS/popLogin.js?date=20120410", P);
	};
	$.getScript(M + "/JS/AjaxFun.js?date=20120309", A);
	I();
	var E = function () {
		ShareTitle = document.title;
		var Q = function () {
			$.getScript(M + "/JS/BDShare.js?date=20120428");
		};
		var P = $.cookie("uid");
		if (P != null && parseInt(P) > 0) {
			var O = function () {
				return escape("http://www.1ypg.com/api/referrals.ashx?rid=" + P + "&url=" + escape(location.href + "?s=share-u" + P + "p"));
			};
			var N = function (R) {
				if (R.code == 1 && R.data.urls[0].result) {
					ShareUrl = R.data.urls[0].url_short.replace("t.cn", "t.1ypg.com");
					Q();
				}
			};
			$.getJSON("https://api.weibo.com/2/short_url/shorten.json?source=1681459862&url_long=" + O() + "&callback=?", N);
		} else {
			ShareUrl = location.href + "?s=shared";
			Q();
		}
	};
	E();
});

