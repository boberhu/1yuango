var mainHttp = $www;
(function() {
	if (window.self != window.top) {
		var c = mainHttp;
		if (typeof (window.location) == "object") {
			c = window.location.href
		}
		var d = $("<form name='toTopUrl' method='get' action='" + c
				+ "' target='_top'></form>");
		d.appendTo("body").ready(function() {
			d.submit()
		})
	}
})();
function SetCookie(a, b) {
	document.cookie = a + "=" + escape(b) + ";id=1ypg;path=/;domain=" + $domain
}
function SetCookieByExpires(b, d, a) {
	var c = a;
	var e = new Date();
	e.setTime(e.getTime() + c * 24 * 60 * 60 * 1000);
	document.cookie = b + "=" + escape(d) + ";id=1ypg;path=/;expires="
			+ e.toGMTString() + ";domain=" + $domain
}
function getCookie(b) {
	var a = document.cookie.match(new RegExp("(^| )" + b + "=([^;]*)(;|$)"));
	if (a != null) {
		return unescape(a[2])
	}
	return null
}
function delCookie(a) {
	var c = new Date();
	c.setTime(c.getTime() - 1);
	var b = getCookie(a);
	if (b != null) {
		document.cookie = a + "=" + b + ";id=1ypg;path=/;expires="
				+ c.toGMTString() + ";domain=" + $domain
	}
}
function GetJPData(g, h, f, e) {
	$.getJSON(g + "/JPData?action=" + h + (f != "" ? "&" : "") + f + "&fun=?",
			e)
}
var loadImgFun = function() {
	var n = "src2";
	var i = $("#loadingPicBlock");
	if (i.length > 0) {
		var p = i.find("img");
		var o = function() {
			return Math.max(document.documentElement.scrollTop,
					document.body.scrollTop)
		};
		var j = function() {
			return document.documentElement.clientHeight + o() + 100
		};
		var m = o();
		var l = m;
		var k = function() {
			p.each(function() {
				if ($(this).parent().offset().top <= j()) {
					var a = $(this).attr(n);
					if (a) {
						$(this).attr("src", a).removeAttr(n).show()
					}
				}
			})
		};
		$(window).bind("scroll", function() {
			l = o();
			if (l - m > 50) {
				m = l;
				k()
			}
		});
		k()
	}
};
var _IsCartChanged = true;
var _IsBuySubmiting = false;
var _InsertIntoCart = function() {
};
var _IeVersion = /(msie\s|trident.*rv:)([\w.]+)/.test(navigator.userAgent
		.toLowerCase())
		&& parseInt($.browser.version);
(function() {
	var ay = $www;
	var bs = $www;
	var bq = $www;
	var a2 = $skin;
	var aW = $www;
	var ba = function() {
		if (/(msie\s|trident.*rv:)([\w.]+)/.test(navigator.userAgent
				.toLowerCase())
				&& $(window).width() < 1190) {
			if (parseInt($.browser.version) < 9) {
				$("body").addClass("f-width-change")
			} else {
				$("body").removeClass("f-width-change")
			}
		} else {
			$("body").removeClass("f-width-change")
		}
	};
	if (/(msie\s|trident.*rv:)([\w.]+)/.test(navigator.userAgent.toLowerCase())
			&& parseInt($.browser.version) == 6) {
		$("body").css("font", '12px/150% Arial,Verdana,"\x05b8b\x04f53"')
	}
	ba();
	$(window).resize(ba);
	loadImgFun();
	var bf = $("#rightTool");
	var aK = $("#divRTool");
	var bH = $("body").attr("rf");
	var aD = !(bH == "1" || bH == "2");
	var a3 = function() {
		var c = false;
		var b = function() {
			if (c || $(window).scrollTop() > 0) {
				bf.children("div").height($(window).height());
				bf.fadeIn("slow")
			} else {
				if (!c) {
					bf.fadeOut("slow")
				}
			}
		};
		var a = function() {
			if ($(window).scrollTop() > 0) {
				aK.fadeIn("slow")
			} else {
				if (!c) {
					aK.fadeOut("slow")
				}
			}
		};
		var d = function() {
			if (bH == "2") {
				c = true;
				b()
			} else {
				if (bH == "1") {
					b()
				} else {
					a()
				}
			}
		};
		$(window).scroll(d).resize(function() {
			_IsCartChanged = true;
			d()
		});
		d()
	};
	var bG = $("#ulTopRight");
	var bE = $("#liTopUMsg");
	if (bG.length > 0) {
		var ax = getCookie("userId");
		if (ax != null) {
			var a9 = getCookie("face");
			var aw = getCookie("userName");
			bG
					.prepend('<li><div class="u-menu-hd u-menu-login"><a target="_blank" href="/u/'
							+ ax
							+ '.html" title="'
							+ aw
							+ '" class="blue"><img class="M-name-img" style="border: 0 none;float: left;height: 25px;margin: -3px 5px;width: 25px;" src="'
							+ a9
							+ '" />'
							+ aw
							+ '</a><a href="/logout/index.html" title="\u9000\u51fa">[\u9000\u51fa]</a></div></li><li class="f-gap"><s></s></li>')
		} else {
			bG
					.prepend('<li><div class="u-menu-hd"><a href="/login/index.html?forward=rego" title="\u767b\u5f55">\u767b\u5f55</a></div></li><li class="f-gap"><s></s></li><li><div class="u-menu-hd"><a href="/register/index.html?forward=rego" title="\u6ce8\u518c">\u6ce8\u518c</a></div></li><li class="f-gap"><s></s></li>');
			bE.attr("show", "-1").hide()
		}
	}
	var a8 = $("#ulHTotalBuy");
	var bd = $("#spFundTotal");
	var bu = 0;
	var aX = 2000;
	var a4 = function() {
		$
				.ajax({
					url : "/getAllBuyCount.html",
					type : "GET",
					success : function(f) {
						bd.html("\uffe5" + (f / 100).toFixed(2));
						if (bu != f) {
							if (bu == 0) {
								bu = f;
								var d = '<li class="nobor gray6">\u7d2f\u8ba1\u53c2\u4e0e</li>';
								for (var c = bu.length; c > 0; c--) {
									if (c == 3 || c == 6 || c == 9) {
										d += '<li class="nobor">,</li>'
									}
									d += '<li class="num"><cite><em>0</em></cite><i></i></li>'
								}
								d += '<li class="nobor gray6 u-secondary">\u4eba\u6b21<b><s></s></b></li>';
								a8.prepend(d);
								if (a8.find("em").length == 3
										|| a8.find("em").length == 6
										|| a8.find("em").length == 9) {
									a8.find("li.nobor").eq(1).remove()
								}
								a8.children("li.num").each(function() {
									var j = '<cite style="top:-243px;">';
									for (var h = 9; h >= 0; h--) {
										j += '<em t="' + h + '">' + h + "</em>"
									}
									j += "</cite><i></i>";
									$(this).html(j)
								});
								var a = f;
								var g = f.length;
								var i = a.split("");
								a8.find("cite").each(function(k, j) {
									var l = $(this);
									var h = parseInt(i[k]);
									l.animate({
										top : "-" + (27 * (9 - h)) + "px"
									}, {
										queue : false,
										duration : aX,
										complete : function() {
										}
									})
								})
							} else {
								var e = bu.toString().split("");
								var b = f.split("");
								bu = f;
								a8
										.find("cite")
										.each(
												function(k, p) {
													var j = 0;
													var m = parseInt(e[k]);
													if (e[k] <= b[k]) {
														j = parseInt(b[k])
																- parseInt(e[k])
													} else {
														j = 10
																+ parseInt(b[k])
																- parseInt(e[k])
													}
													if (j != 0) {
														var h = $(this)
																.children(
																		'em[t="'
																				+ m
																				+ '"]');
														var l = h.nextAll();
														for (var o = l.length - 1; o > -1; o--) {
															$(this).prepend(
																	$(l[o]))
														}
														var n = -(243 - j * 27);
														$(this)
																.css(
																		{
																			top : "-243px"
																		})
																.animate(
																		{
																			top : n
																		},
																		{
																			queue : false,
																			duration : aX,
																			complete : function() {
																			}
																		})
													}
												})
							}
						}
					}
				});
		setTimeout(a4, 5000)
	};
	if (a8.length > 0 || bd.length > 0) {
		a4()
	}
	var bA = function(b) {
		var d = "\u624b\u673a";
		var f = "\u8f93\u5165\u201c\u624b\u673a\u201d\u8bd5\u8bd5";
		var g = 50;
		var e = function() {
			b.unbind("blur").bind("focus", a);
			if (b.val() == "") {
				b.val(f).css({
					color : "#BBB",
					padding : "9px 130px 9px 5px",
					width : "145px"
				});
				b.next("span").css("zIndex", "10").show()
			}
		};
		var a = function() {
			b.unbind("focus").bind("blur", e).css({
				color : "#333",
				padding : "9px 0px 9px 5px",
				width : "275px"
			});
			if (b.val() == f) {
				b.val("").next("span").css("zIndex", "0").hide()
			}
		};
		var c = function() {
			var h = $.trim(b.val());
			if (h == f) {
				h = d
			}
			location.href = mainHttp + "/search/hot20/"
					+ encodeURIComponent(h.replace(/(\/)/g, "")) + ".html"
		};
		b.focus(a).keydown(function(h) {
			if (13 == ((window.event) ? event.keyCode : h.keyCode)) {
				c()
			}
		}).keyup(function() {
			var h = $(this).val().trim();
			if (h.length > g) {
				$(this).val(h.substring(0, g))
			}
		}).attr("placeholder", "").css("color", "#BBB").val(f);
		$("#btnHSearch").click(function() {
			c();
			return false
		})
	};
	var bj = $("#txtHSearch");
	if (bj.length > 0) {
		bA(bj)
	}
	var aF = $("#btnTFavorite");
	if (aF.length > 0) {
		var bF = function() {
			var a = $saitName;
			var c = $www;
			try {
				window.external.addFavorite(c, a)
			} catch (b) {
				try {
					window.sidebar.addPanel(a, c, "")
				} catch (b) {
					alert("\u62b1\u6b49\uff0c\u60a8\u6240\u4f7f\u7528\u7684\u6d4f\u89c8\u5668\u65e0\u6cd5\u5b8c\u6210\u6b64\u64cd\u4f5c\u3002\r\n\r\n\u52a0\u5165\u6536\u85cf\u5931\u8d25\uff0c\u8bf7\u4f7f\u7528Ctrl + D\u8fdb\u884c\u6dfb\u52a0\uff01")
				}
			}
		};
		aF.bind("click", function() {
			bF()
		})
	}
	var bI = function(a) {
		if (a && a.stopPropagation) {
			a.stopPropagation()
		} else {
			window.event.cancelBubble = true
		}
	};
	var a0 = function() {
		$("#liMobile").hover(function() {
			$(this).addClass("u-arr-hover")
		}, function() {
			$(this).removeClass("u-arr-hover")
		});
		$("#liMember").hover(function() {
			$(this).addClass("u-arr-hover")
		}, function() {
			$(this).removeClass("u-arr-hover")
		});
		bE.hover(function() {
			$(this).addClass("u-arr-hover")
		}, function() {
			$(this).removeClass("u-arr-hover")
		});
		var a = $("#divSortList");
		if ($("body.home").length == 0) {
			$("#divGoodsSort").hover(function() {
				a.show()
			}, function() {
				setTimeout(function() {
					if (!b) {
						a.hide()
					}
				}, 200)
			})
		}
		var b = false;
		a.children("dl").each(function() {
			$(this).hover(function(c) {
				b = true;
				bI(c);
				$(this).addClass("hover");
				if ($(this).next().length > 0) {
					$(this).append("<i></i>")
				}
			}, function(c) {
				b = false;
				$(this).removeClass("hover").children("i").remove()
			})
		})
	};
	var bz = $("#pServerTime");
	if (bz.length > 0) {
		var aM = bz.children("span");
		var aE = function(a) {
			var c = a.getHours();
			var d = a.getMinutes();
			var b = a.getSeconds();
			return (c > 9 ? c.toString() : "0" + c) + ":"
					+ (d > 9 ? d.toString() : "0" + d) + ":"
					+ (b > 9 ? b.toString() : "0" + b)
		};
		var bc = 0;
		var az = new Date();
		var a6 = az.getFullYear() + "-" + (az.getMonth() + 1) + "-"
				+ az.getDate() + " " + aE(az);
		$.ajax({
			url : "/getServerTime.action",
			data : "id=" + a6,
			success : function(b) {
				if (b != "") {
					bc = parseInt(b)
				}
				var a = function() {
					var d = new Date();
					d.setSeconds(d.getSeconds() + bc);
					var e = d.getHours();
					var f = d.getMinutes();
					var c = d.getSeconds();
					aM.eq(0).html(e > 9 ? e.toString() : "0" + e);
					aM.eq(1).html(f > 9 ? f.toString() : "0" + f);
					aM.eq(2).html(c > 9 ? c.toString() : "0" + c)
				};
				setInterval(a, 1000)
			}
		})
	}
	var aJ = $("#spHCart");
	var aB = function() {
		$.ajax({
			url : "/mycart/buyProductClick.html",
			success : function(c) {
				var b = 0;
				if (c != "") {
					b = c.productCount
				} else {
					b = 0
				}
				if (b > 0) {
					var a = b > 99 ? "..." : b;
					aJ.html("(" + a + ")");
					aS.html(a);
					if (b > 0) {
						aS.show()
					}
				} else {
					aJ.html("");
					aS.html("0").hide()
				}
			}
		})
	};
	aB();
	var aP = $("#ulRToolList");
	var aC = aP.find(".f-shopping-cart");
	var aS = aC.find("em");
	var bg = $("#divRTCartMain");
	var bn = $("#divRTLogin");
	if (aC.length > 0) {
		var bD = false;
		var aI = false;
		var bo = "";
		var by = 37;
		var aV = bg.find(".cartEmpty");
		if (_IeVersion === 6) {
			by = 36
		}
		var a5 = function() {
			if (bn.attr("isOpen") != "1") {
				aI = false;
				bo = setTimeout(function() {
					if (!aI) {
						bb(0)
					}
				}, 800)
			}
		};
		var aA = function() {
			bD = true;
			bg.miniCartTool();
			bg.hover(function() {
				clearTimeout(bo);
				aI = true;
				bg.stop().animate({
					right : by
				}, 400)
			}, a5)
		};
		aC.bind("mouseenter", function() {
			clearTimeout(bo);
			aI = true;
			bb(1, true);
			if (!bD) {
				$.getScript("/js/carttool.js?date=150626", aA)
			} else {
				bg.miniCartTool()
			}
		}).bind("mouseleave", a5);
		_InsertIntoCart = function() {
			aB()
		}
	}
	var bb = function(a, b) {
		if (a == 0) {
			bn.hide();
			bg.css("zIndex", "5").stop().animate({
				right : "-240px"
			}, {
				duration : b ? 300 : 400,
				queue : false,
				complete : function() {
					aC.removeClass("f-checked");
					bg.hide();
					bt.css("zIndex", "10")
				}
			})
		} else {
			bt.css("zIndex", "5").stop().animate({
				right : "-240px"
			}, {
				duration : b ? 300 : 400,
				queue : false,
				complete : function() {
					aN.removeClass("cart-hover").find("b").hide();
					bt.hide().attr("isShowed", "0")
				}
			});
			bg.css("zIndex", "10")
		}
	};
	var aN = aP.find(".f-attention");
	var bt = $("#divRTColect");
	if (aN.length > 0) {
		var bk = true;
		var bw = false;
		var aH = false;
		var aY = "";
		var aZ = 37;
		if (_IeVersion === 6) {
			aZ = 36
		}
		var a1 = function() {
			aH = false;
			if (bk) {
				aY = setTimeout(function() {
					if (!aH) {
						if ($("#fastLoginFrame").length == 0) {
							bb(1)
						}
					}
				}, 800)
			} else {
				aN.removeClass("cart-hover").find("b").hide();
				bt.hide().attr("isShowed", "0")
			}
		};
		var bC = false;
		var bh = function() {
			bw = true;
			bt.miniCollectTool(bC);
			bt.hover(function() {
				clearTimeout(aY);
				aH = true;
				aN.addClass("cart-hover").find("b").show();
				bt.stop().animate({
					right : aZ
				}, 400)
			}, a1)
		};
		aN.bind("mouseenter", function() {
			clearTimeout(aY);
			aH = true;
			bb(0, true);
			var a = function() {
				if (!bw) {
					$.getScript("/js/collecttool.js?date=150604", bh)
				} else {
					bt.miniCollectTool(bC)
				}
			};
			$.ajax({
				url : "/user/hasCollectGoods.action",
				success : function(b) {
					if (b == 0) {
						bC = true;
						a()
					} else {
						if (b == 10) {
							a()
						} else {
							bk = false
						}
					}
				}
			})
		}).bind("mouseleave", a1)
	}
	var bp = function() {
		var c = [ ay ];
		var a = true;
		for (var b = 0; b < c.length; b++) {
			if (location.href.toLowerCase().indexOf(c[b]) > -1) {
				a = false;
				break
			}
		}
		if (a) {
			aB()
		}
	};
	bp();
	var bi = function() {
		var a = getCookie("_topNotice1");
		var c = "-1px";
		if (a == null) {
			var b = $('<div class="banner-prompt clrfix" style="background-color:#ffffef"><div class="w1190"><span class="fl">尊敬的用户您好，本站为<a href="" target="_blank " style="color:#2af">点击这里</a> <a href="/news/3.html" target="_blank" class="ckxq">查看详情</a></span><span class="fr"><a href="javascript:;" class="close" title="关闭"></a></span></div></div>');
			b.find("a.close").click(function() {
				b.remove();
				SetCookie("_topNotice1", "1");
				return false
			});
			/*$("div.g-toolbar").before(b);*/
			c = "35px"
		}
	};
	var bB = function() {
		var b = bE.attr("show");
		if (b != "-1") {
			if (b == "1") {
				var c = function() {
					var j = $.cookie("_msgFApply");
					var d = $.cookie("_msgSys");
					var h = $.cookie("_msgFPriv");
					var g = 0;
					if (j && parseInt(j) > 0) {
						g += parseInt(j)
					}
					if (d && parseInt(d) > 0) {
						g += parseInt(d)
					}
					if (h && parseInt(h) > 0) {
						g += parseInt(h)
					}
					var e = "";
					var f = function(k, m, l) {
						return '<span><a href="'
								+ bq
								+ k
								+ '" title="'
								+ m
								+ '">'
								+ m
								+ (l && parseInt(l) > 0 ? "<em"
										+ (parseInt(l) > 99 ? ' class="three-digit">...'
												: ">" + l) + "</em>"
										: "") + "</a></span>"
					};
					var i = "";
					i += f("/FriendsApply.do", "\u597d\u53cb\u8bf7\u6c42", j);
					i += f("/UserMessage.do", "\u7cfb\u7edf\u6d88\u606f", d);
					i += f("/UserPrivMsg.do", "\u79c1\u4fe1", h);
					bE.children("div.u-select").html(i);
					if (g > 0) {
						if (bE.children("div.u-menu-hd").find("em").length == 0) {
							bE.children("div.u-menu-hd").append("<em></em>")
						}
					} else {
						bE.children("div.u-menu-hd").find("em").remove()
					}
				};
				var a = function() {
				};
				a()
			} else {
				setTimeout(bB, 1000)
			}
		}
	};
	var bx = function() {
		a3();
		a0();
		bi();
		bB();
	};
	$.getScript(a2 + "/js/comm.js?date=20120309", bx);
	if (aD) {
		aK.find("li").each(function() {
			$(this).hover(function() {
				$(this).addClass("cart-hover")
			}, function() {
				$(this).removeClass("cart-hover")
			})
		})
	} else {
		aP.children("li:gt(0)").each(function(a) {
			$(this).hover(function() {
				$(this).addClass("cart-hover")
			}, function() {
				if (a == 0 && $("#colect_shower").children("ul").length > 0) {
					return
				}
				$(this).removeClass("cart-hover")
			})
		})
	}
	var bv = "btnTopQQ";
	var aQ = "btnRigQQ";
	var bl = "btnRigQQ2";
	var aR = "btnBtmQQ";
	var bm = $("#" + bv);
	var aU = $("#" + aQ);
	var aG = $("#" + bl);
	var a7 = $("#" + aR);
	if (bm.length > 0 || aU.length > 0 || a7.length > 0) {
		var aO = function() {
			var a = "52013594"
		};
		var aT = new Date();
		var be = aT.getHours();
		var aL = aT.getMinutes();
		if ((be == 8 && aL > 30) || (be > 8 && be < 21)) {
			bm.removeClass("u-service-off").addClass("u-service-on");
			a7.removeClass("u-service-off").addClass("u-service-on");
			aU.attr("class", "u-customer-on");
			aG.attr("class", "u-customer-on")
		} else {
			bm.html("<i></i>\u79bb\u7ebf\u7559\u8a00").attr("title",
					"\u79bb\u7ebf\u7559\u8a00");
			a7.html("<i></i>\u79bb\u7ebf\u7559\u8a00").attr("title",
					"\u79bb\u7ebf\u7559\u8a00")
		}
		var br = false;
		var bJ = function() {
			if (br) {
				return false
			}
			br = true
		};
		bm.hover(bJ, null);
		aU.hover(function() {
			$(this).addClass("cart-hover");
			bJ()
		}, function() {
			$(this).removeClass("cart-hover")
		});
		a7.hover(bJ, null);
		if (aD) {
			aG.hover(function() {
				$(this).addClass("cart-hover");
				bJ()
			}, function() {
				$(this).removeClass("cart-hover")
			})
		}
	}
	if (aD) {
		aK.find("li.f-back-to").click(function() {
			$("body,html").animate({
				scrollTop : 0
			}, 0);
			return false
		})
	} else {
		aP.children("li.f-back-to").click(function() {
			$("body,html").animate({
				scrollTop : 0
			}, 0);
			return false
		})
	}
})();
