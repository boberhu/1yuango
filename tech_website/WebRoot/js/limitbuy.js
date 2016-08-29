var CBLFun = null;
$(document)
		.ready(
				function() {
					var f = $www;
					var e = $skin;
					var d = function() {
						var b = false;
						var s = $("#ulLimitGoodsList");
						var t = $("#divLoading");
						var w = 1;
						var x = 0;
						var p = new Object();
						var q = $("#divLotteryList");
						var a = $("#ulLotteryList");
						var u = 0;
						var v = function() {
							var k = 0;
							var j = null, h = null;
							var g = function() {
							};
							var i = function() {
								if (k > 1) {
									a.animate({
										"margin-top" : "-20px"
									}, 800, function() {
										$(this).css("margin-top", "0")
												.children().eq(0).appendTo(
														$(this))
									})
								}
							};
							a.hover(function() {
								clearInterval(h);
								h = null
							}, function() {
								h = setInterval(i, 3000)
							});
							g()
						};
						var c = function() {
							return "pageNo=1&pageSize=50&isCount=" + w
						};
						var r = function() {
							var g = function() {
								t.show();
								$
										.get(
												"/limitBuy/getLimitGoodsPage.action",
												c(),
												function(i) {
													if (i.length > 0) {
														var j = function() {
															if (w == 1) {
																x = i;
																w = 0
															}
															t.hide();
															var l = i;
															var k = l.length;
															if (k > 0) {
																p.LimitGoodsData = l;
																h()
															}
														};
														if (b) {
															j()
														} else {
															$
																	.getScript(
																			"/js/goodscomm.js?date=150602",
																			function() {
																				b = true;
																				j()
																			})
														}
													} else {
														t
																.html("\u62b1\u6b49\uff0c\u52a0\u8f7d\u5546\u54c1\u5931\u8d25\uff0c\u8bf7\u5237\u65b0\u91cd\u8bd5\uff01")
													}
												})
							};
							var h = function() {
								var l = p.LimitGoodsData;
								if (l != null && l != undefined) {
									var F = "";
									var n = "";
									var o = 0;
									var m = 0;
									var i = 0;
									var j = 0;
									var E = 0;
									s.show();
									var k = 235;
									for (var C = 0; C < l.length; C++) {
										F = "";
										n = "(\u7b2c" + l[C].productPeriod
												+ "\u4e91)&nbsp;"
												+ l[C].productName;
										o = parseInt(l[C].currentBuyCount);
										m = parseInt(l[C].productPrice);
										i = parseInt(m - o);
										j = parseFloat(o) / m;
										E = parseInt(j * k);
										F += '<div class="productsCon" codeID="'
												+ l[C].spellbuyProductId
												+ '" goodsID="'
												+ l[C].productId
												+ '" limitBuy="'
												+ l[C].spellbuyLimit
												+ '"><div class="proList '
												+ ((C + 1) % 4 == 0 ? "special"
														: "")
												+ '"><ul><li class="list-pic"><a href="/products/'
												+ l[C].spellbuyProductId
												+ '.html" target="_blank" title="'
												+ n
												+ '"><img name="goodsImg" src="'
												+ $img
												+ l[C].headImage
												+ '" /></a></li><li class="list-name"><a href="/products/'
												+ l[C].spellbuyProductId
												+ '.html" target="_blank" title="'
												+ n
												+ '">'
												+ n
												+ '</a></li><li class="list-value gray6">\u4ef7\u503c\uff1a\uffe5'
												+ formatFloat(l[C].productPrice)
												+ "<span><i></i>\u9650\u8d2d<b>"
												+ l[C].spellbuyLimit
												+ '</b>\u4eba\u6b21</span></li><li class="g-progress"><dl class="m-progress"><dt><b style="width:'
												+ (o == 0 ? 0
														: (E == 0 ? 1 : E))
												+ 'px;"></b></dt><dd><span class="orange fl"><em>'
												+ o
												+ '</em>\u5df2\u53c2\u4e0e</span><span class="fl gray6"><em>'
												+ m
												+ '</em>\u603b\u9700\u4eba\u6b21</span><span class="blue fr"><em>'
												+ i
												+ '</em>\u5269\u4f59</span></dd></dl></li><li name="buyBox" class="list-btn" limitBuy="'
												+ l[C].spellbuyLimit
												+ '"><a href="javascript:;" title="\u7acb\u5373'
												+ $saitName
												+ '" class="u-imm">\u7acb\u5373'
												+ $saitName
												+ '</a><a href="javascript:;" class="u-carts" title="\u52a0\u5165\u5230\u8d2d\u7269\u8f66"></a></li></ul></div></div>';
										var D = $(F);
										s.append(D);
										D.GoodsItemFun()
									}
									s.show()
								}
								t.hide()
							};
							this.initPage = function() {
								g()
							}
						};
						CBLFun = new r();
						CBLFun.initPage();
						v()
					};
					$.getScript(e + "/js/ajaxfun.js?v=141103", d)
				});
