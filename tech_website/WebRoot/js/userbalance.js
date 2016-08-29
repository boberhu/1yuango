var CBLFun = null;
$(function() {
	var a = $skin;
	var b = function() {
		var c = function() {
			var y = $("#resultCount").val();
			var C = 0;
			var d = $("#userId").val();
			var g = 15;
			var r = 1;
			var u = 0;
			var v = "";
			var A = 0;
			var i = {
				type : 0,
				FIdx : 1,
				EIdx : g,
				region : 0,
				beginTime : "",
				endTime : "",
				isCount : 1
			};
			var t = $("#divList0");
			var m = $("#divPageNav0");
			var j = $("#txtMisTime");
			var p = $("#txtMaxTime");
			$("div.type a").each(
					function(D, E) {
						$(this).bind(
								"click",
								function() {
									$(this).addClass("annal1").siblings()
											.removeClass("annal1");
									i.type = D;
									i.beginTime = "";
									i.endTime = "";
									t = $("#divList" + D);
									m = $("#divPageNav" + D);
									if (D == 0) {
										$("#divTimeSel").show();
										$("#divList0").show();
										$("#divPageNav0").show();
										$("#divList1").hide();
										$("#divPageNav1").hide();
										$("#divDetailInfo").hide();
										$("#divbuyDetail").hide()
									} else {
										$("#divTimeSel").show();
										$("#divList0").hide();
										$("#divPageNav0").hide();
										$("#divList1").show();
										$("#divPageNav1").show()
									}
									CBLFun.initData()
								})
					});
			$("div.record-tab a").each(
					function(D, E) {
						$(this).bind(
								"click",
								function() {
									$(this).addClass("record-cur").siblings()
											.removeClass("record-cur");
									i.region = D;
									i.beginTime = "";
									i.endTime = "";
									CBLFun.initData()
								})
					});
			var s = function() {
				var E = new Date();
				j.val(E.DateAdd("m", -1).Format("YYYY-MM-DD")).date_input();
				p.val(E.Format("YYYY-MM-DD")).date_input();
				var D = function() {
					var G = j.val();
					var F = p.val();
					if (!IsValidDate(G)) {
						alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u5f00\u59cb\u65e5\u671f!");
						return
					}
					if (!IsValidDate(F)) {
						alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7ed3\u675f\u65e5\u671f!");
						return
					}
					if (ConvertDate(F) < ConvertDate(G)) {
						alert("\u7ed3\u675f\u65e5\u671f\u4e0d\u80fd\u5c0f\u4e8e\u5f00\u59cb\u65e5\u671f!");
						return
					}
					$(".record-tab a").each(function() {
						$(this).removeClass("record-cur")
					});
					i.region = 5;
					i.beginTime = j.val();
					i.endTime = p.val();
					CBLFun.initData()
				};
				$("#btnSearch").unbind("click").bind("click", D)
			};
			s();
			var k = '<ul class="listTitle"><li class="leftTitle" style="width:130px;">\u5145\u503c\u65f6\u95f4</li><li class="price" style="padding-left: 5px;width: 120px;">\u8d44\u91d1\u6e20\u9053</li><li class="price" style="padding-left: 5px;width: 120px;">\u89c4\u683c(MB)</li><li class="price" style="padding-left: 5px;width: 130px;">\u83b7\u5f97'
					+ $shortName
					+ '\u5e01\uff08\u4e2a\uff09</li><li class="regard">\u5145\u503c\u91d1\u989d</li></ul>';
			var o = '<ul class="listTitle"><li class="leftTitle">'
					+ $shortName
					+ '\u65f6\u95f4</li><li class="price">'
					+ $shortName
					+ '\u91d1\u989d\uff08\u5143\uff09</li><li class="regard">\u64cd\u4f5c</li></ul>';
			var l = '<ul class="listTitle"><li class="leftTitle">' + $shortName
					+ "\u5546\u54c1</li><li>" + $shortName
					+ '\u4eba\u6b21</li><li class="regard">' + $shortName
					+ "\u91d1\u989d</li></ul>";
			$("#pagination").pagination(y, {
				current_page : C,
				prev_text : "\u4e0a\u4e00\u9875",
				next_text : "\u4e0b\u4e00\u9875",
				num_display_entries : 5,
				num_edge_entries : 1,
				link_to : "",
				prev_show_always : false,
				next_show_always : false,
				items_per_page : 10,
				callback : x
			});
			function x(D) {
				var E = "/user/ConsumeListByDeltaAjaxPage.action?pageNo=" + D
						+ "&pageSize=10&userId=" + d + "&selectTime="
						+ i.region + "&startDate=" + i.beginTime + "&endDate="
						+ i.endTime;
				$(".pageULEx").prepend(
						'<li class="total_page" id="pageLoading"><img src="'
								+ $img + '/Images/loding.gif" /></li>');
				$
						.ajax({
							url : E,
							type : "post",
							data : "json",
							beforeSend : e,
							success : function(G) {
								t.empty();
								if (y > 0) {
									$(k).appendTo(t);
									$(".pageULEx").show();
									$(".pageULEx")
											.prepend(
													'<li class="total_page">\u9875\u6b21<i>'
															+ (D + 1)
															+ "/"
															+ Math.ceil(y / 10)
															+ "</i>&nbsp;&nbsp;\u5171<i>"
															+ y
															+ "</i>\u6761\u8bb0\u5f55</li>");
									for (var F = 0; F < G.length; F++) {
										$(
												'<ul><li class="leftTitle fontAri" style="width:130px;">'
														+ G[F].date
														+ '</li><li class="price" style="padding-left: 5px;width: 120px;">'+G[F].interfaceType+'</li><li class="price" style="padding-left: 5px;width: 120px;">'
														+ G[F].money
														+ '</li><li class="price" style="padding-left: 5px;width: 130px;">'
														+ G[F].money
														+ '</li><li class="regard">\uffe5'
														+ G[F].money
														+ "</li></ul>")
												.appendTo(t)
									}
									$("#pageLoading").hide()
								} else {
									$(k).appendTo(t);
									$(
											'<div class="tips-con"><i></i>\u65e0\u76f8\u5e94\u7684\u8bb0\u5f55</div>')
											.appendTo(t);
									$(".pageULEx").hide()
								}
							}
						})
			}
			function f(D) {
				var E = "/user/ConsumeListByDeltaAjaxPage.action?pageNo=" + D
						+ "&pageSize=10&userId=" + d + "&startDate="
						+ i.beginTime + "&endDate=" + i.endTime;
				$(".pageULEx").prepend(
						'<li class="total_page" id="pageLoading"><img src="'
								+ $img + '/Images/loding.gif" /></li>');
				$
						.ajax({
							url : E,
							type : "post",
							data : "json",
							beforeSend : e,
							success : function(G) {
								t.empty();
								if (y > 0) {
									$(k).appendTo(t);
									$(".pageULEx").show();
									$(".pageULEx")
											.prepend(
													'<li class="total_page">\u9875\u6b21<i>'
															+ (D + 1)
															+ "/"
															+ Math.ceil(y / 10)
															+ "</i>&nbsp;&nbsp;\u5171<i>"
															+ y
															+ "</i>\u6761\u8bb0\u5f55</li>");
									for (var F = 0; F < G.length; F++) {
										$(
												'<ul><li class="leftTitle fontAri">'
														+ G[F].date
														+ '</li><li class="price">'+G[F].interfaceType+'</li><li class="regard">\uffe5'
														+ G[F].money
														+ "</li></ul>")
												.appendTo(t)
									}
									$("#pageLoading").hide()
								} else {
									$(k).appendTo(t);
									$(
											'<div class="tips-con"><i></i>\u65e0\u76f8\u5e94\u7684\u8bb0\u5f55</div>')
											.appendTo(t);
									$(".pageULEx").hide()
								}
							}
						})
			}
			function q(E) {
				var D = "/ConsumeDetail/ConsumeDetailAjaxPage.action?pageNo="
						+ E + "&id=" + v;
				$(".pageULEx").prepend(
						'<li class="total_page" id="pageLoading"><img src="'
								+ $img + '/Images/loding.gif" /></li>');
				$
						.ajax({
							url : D,
							type : "post",
							data : "json",
							beforeSend : e,
							success : function(I) {
								if (y > 0) {
									var G = '<dd class="gray01"></dd><dd class="btn"><a name="goBack" href="javascript:;" title="\u8fd4\u56de\u5217\u8868">\u8fd4\u56de\u5217\u8868</a><span></span></dd>';
									var F = $(G);
									$("#divDetailInfo").show().empty()
											.append(F);
									F.find("a[name='goBack']").click(
											function() {
												$("div.type a:eq(1)").click();
												$("#divDetailInfo").hide();
												$("#divbuyDetail").hide();
												$("#divTimeSel").show();
												$("#divList1").show();
												$("#divPageNav1").show()
											});
									var H = "";
									for (var J = 0; J < I.length; J++) {
										H += '<ul><li class="leftTitle"><a target="_blank" title="'
												+ I[J].productTitle
												+ '" href="'
												+ $www
												+ "/products/"
												+ I[J].productId
												+ '.html">(\u7b2c'
												+ I[J].productPeriod
												+ "\u671f)"
												+ I[J].productName.substring(0,
														40)
												+ "...</a></li><li>"
												+ I[J].buyCount
												+ '</li><li class="regard">\uffe5'
												+ I[J].buyMoney + "</li></ul>"
									}
									$("#divTimeSel").hide();
									426532;
									$("#divList1").hide();
									$("#divPageNav1").hide();
									$("#divbuyDetail").html(l + H).show();
									$("#pageLoading").hide()
								}
							}
						})
			}
			function n(E) {
				var D = "/ConsumeDetail/ConsumeDetailAjaxPage.action?pageNo="
						+ E + "&id=" + v;
				$(".pageULEx").prepend(
						'<li class="total_page" id="pageLoading"><img src="'
								+ $img + '/Images/loding.gif" /></li>');
				$
						.ajax({
							url : D,
							type : "post",
							data : "json",
							beforeSend : e,
							success : function(I) {
								if (y > 0) {
									var G = '<dd class="gray01"></dd><dd class="btn"><a name="goBack" href="javascript:;" title="\u8fd4\u56de\u5217\u8868">\u8fd4\u56de\u5217\u8868</a><span></span></dd>';
									var F = $(G);
									$("#divDetailInfo").show().empty()
											.append(F);
									F.find("a[name='goBack']").click(
											function() {
												$("#btnSearch").click();
												$("#divDetailInfo").hide();
												$("#divbuyDetail").hide();
												$("#divTimeSel").show();
												$("#divList1").show();
												$("#divPageNav1").show()
											});
									var H = "";
									for (var J = 0; J < I.length; J++) {
										H += '<ul><li class="leftTitle"><a target="_blank" title="'
												+ I[J].productTitle
												+ '" href="'
												+ $www
												+ "/products/"
												+ I[J].productId
												+ '.html">(\u7b2c'
												+ I[J].productPeriod
												+ "\u671f)"
												+ I[J].productName.substring(0,
														40)
												+ "...</a></li><li>"
												+ I[J].buyCount
												+ '</li><li class="regard">\uffe5'
												+ I[J].buyMoney + "</li></ul>"
									}
									$("#divTimeSel").hide();
									$("#divList1").hide();
									$("#divPageNav1").hide();
									$("#divbuyDetail").html(l + H).show();
									$("#pageLoading").hide()
								}
							}
						})
			}
			function B(D) {
				var E = "/user/ConsumeListAjaxPage.action?pageNo=" + D
						+ "&pageSize=10&userId=" + d + "&selectTime="
						+ i.region + "&startDate=" + i.beginTime + "&endDate="
						+ i.endTime;
				$(".pageULEx").prepend(
						'<li class="total_page" id="pageLoading"><img src="'
								+ $img + '/Images/loding.gif" /></li>');
				$
						.ajax({
							url : E,
							type : "post",
							data : "json",
							beforeSend : e,
							success : function(G) {
								t.empty();
								if (y > 0) {
									$(o).appendTo(t);
									$(".pageULEx").show();
									$(".pageULEx")
											.prepend(
													'<li class="total_page">\u9875\u6b21<i>'
															+ (D + 1)
															+ "/"
															+ Math.ceil(y / 10)
															+ "</i>&nbsp;&nbsp;\u5171<i>"
															+ y
															+ "</i>\u6761\u8bb0\u5f55</li>");
									for (var F = 0; F < G.length; F++) {
										$(
												'<ul><li class="leftTitle">'
														+ G[F].date
														+ '</li><li class="price">\uffe5'
														+ G[F].money
														+ '</li><li class="regard"><a title="\u8be6\u60c5" shopid="'
														+ G[F].outTradeNo
														+ '" href="javascript:;" name="showDetail">\u8be6\u60c5</a></li></ul>')
												.appendTo(t)
									}
									$("#pageLoading").hide();
									$("a[name=showDetail]")
											.click(
													function() {
														v = $(this).attr(
																"shopid");
														$
																.ajax({
																	url : "/ConsumeDetail/ConsumeDetailAjaxPageByCount.action?id="
																			+ v,
																	success : function(
																			H) {
																		y = parseInt(H);
																		$(
																				"#pagination")
																				.pagination(
																						y,
																						{
																							current_page : A,
																							prev_text : "\u4e0a\u4e00\u9875",
																							next_text : "\u4e0b\u4e00\u9875",
																							num_display_entries : 5,
																							num_edge_entries : 1,
																							link_to : "",
																							prev_show_always : false,
																							next_show_always : false,
																							items_per_page : 10,
																							callback : q
																						})
																	}
																})
													})
								} else {
									$(o).appendTo(t);
									$(
											'<div class="tips-con"><i></i>\u65e0\u76f8\u5e94\u7684\u8bb0\u5f55</div>')
											.appendTo(t);
									$(".pageULEx").hide()
								}
							}
						})
			}
			function z(D) {
				var E = "/user/ConsumeListAjaxPage.action?pageNo=" + D
						+ "&pageSize=10&userId=" + d + "&startDate="
						+ i.beginTime + "&endDate=" + i.endTime;
				$(".pageULEx").prepend(
						'<li class="total_page" id="pageLoading"><img src="'
								+ $img + '/Images/loding.gif" /></li>');
				$
						.ajax({
							url : E,
							type : "post",
							data : "json",
							beforeSend : e,
							success : function(G) {
								t.empty();
								if (y > 0) {
									$(o).appendTo(t);
									$(".pageULEx").show();
									$(".pageULEx")
											.prepend(
													'<li class="total_page">\u9875\u6b21<i>'
															+ (D + 1)
															+ "/"
															+ Math.ceil(y / 10)
															+ "</i>&nbsp;&nbsp;\u5171<i>"
															+ y
															+ "</i>\u6761\u8bb0\u5f55</li>");
									for (var F = 0; F < G.length; F++) {
										$(
												'<ul><li class="leftTitle">'
														+ G[F].date
														+ '</li><li class="price">\uffe5'
														+ G[F].money
														+ '</li><li class="regard"><a title="\u8be6\u60c5" shopid="'
														+ G[F].outTradeNo
														+ '" href="javascript:;" name="showDetail">\u8be6\u60c5</a></li></ul>')
												.appendTo(t)
									}
									$("#pageLoading").hide();
									$("a[name=showDetail]")
											.click(
													function() {
														v = $(this).attr(
																"shopid");
														$
																.ajax({
																	url : "/ConsumeDetail/ConsumeDetailAjaxPageByCount.action?id="
																			+ v,
																	success : function(
																			H) {
																		y = parseInt(H);
																		$(
																				"#pagination")
																				.pagination(
																						y,
																						{
																							current_page : A,
																							prev_text : "\u4e0a\u4e00\u9875",
																							next_text : "\u4e0b\u4e00\u9875",
																							num_display_entries : 5,
																							num_edge_entries : 1,
																							link_to : "",
																							prev_show_always : false,
																							next_show_always : false,
																							items_per_page : 10,
																							callback : n
																						})
																	}
																})
													})
								} else {
									$(o).appendTo(t);
									$(
											'<div class="tips-con"><i></i>\u65e0\u76f8\u5e94\u7684\u8bb0\u5f55</div>')
											.appendTo(t);
									$(".pageULEx").hide()
								}
							}
						})
			}
			function e() {
				$("#pageLoading").show()
			}
			var h = function() {
				if (!isLoaded) {
					return false
				}
				if (i.type == "0") {
					if (i.region == "5") {
						$
								.ajax({
									url : "/user/ConsumeListByDeltaAjaxPageResultCount.action?pageNo="
											+ C
											+ "&userId="
											+ d
											+ "&startDate="
											+ i.beginTime
											+ "&endDate=" + i.endTime,
									type : "get",
									data : "String",
									success : function(D) {
										y = parseInt(D);
										$("#pagination").pagination(y, {
											current_page : C,
											prev_text : "\u4e0a\u4e00\u9875",
											next_text : "\u4e0b\u4e00\u9875",
											num_display_entries : 5,
											num_edge_entries : 1,
											link_to : "",
											prev_show_always : false,
											next_show_always : false,
											items_per_page : 10,
											callback : f
										})
									}
								})
					} else {
						$
								.ajax({
									url : "/user/ConsumeListByDeltaAjaxPageResultCount.action?pageNo="
											+ C
											+ "&userId="
											+ d
											+ "&selectTime=" + i.region,
									type : "get",
									data : "String",
									success : function(D) {
										y = parseInt(D);
										$("#pagination").pagination(y, {
											current_page : C,
											prev_text : "\u4e0a\u4e00\u9875",
											next_text : "\u4e0b\u4e00\u9875",
											num_display_entries : 5,
											num_edge_entries : 1,
											link_to : "",
											prev_show_always : false,
											next_show_always : false,
											items_per_page : 10,
											callback : x
										})
									}
								})
					}
				} else {
					if (i.region == "5") {
						$
								.ajax({
									url : "/user/ConsumeListAjaxPageResultCount.action?pageNo="
											+ C
											+ "&userId="
											+ d
											+ "&startDate="
											+ i.beginTime
											+ "&endDate=" + i.endTime,
									type : "get",
									data : "String",
									success : function(D) {
										y = parseInt(D);
										$("#pagination").pagination(y, {
											current_page : C,
											prev_text : "\u4e0a\u4e00\u9875",
											next_text : "\u4e0b\u4e00\u9875",
											num_display_entries : 5,
											num_edge_entries : 1,
											link_to : "",
											prev_show_always : false,
											next_show_always : false,
											items_per_page : 10,
											callback : z
										})
									}
								})
					} else {
						$
								.ajax({
									url : "/user/ConsumeListAjaxPageResultCount.action?pageNo="
											+ C
											+ "&userId="
											+ d
											+ "&selectTime=" + i.region,
									type : "get",
									data : "String",
									success : function(D) {
										y = parseInt(D);
										$("#pagination").pagination(y, {
											current_page : C,
											prev_text : "\u4e0a\u4e00\u9875",
											next_text : "\u4e0b\u4e00\u9875",
											num_display_entries : 5,
											num_edge_entries : 1,
											link_to : "",
											prev_show_always : false,
											next_show_always : false,
											items_per_page : 10,
											callback : B
										})
									}
								})
					}
				}
			};
			var w = function(E, D) {
				r = Math.floor(D / g);
				i.FIdx = E;
				i.EIdx = D;
				if (r > 1) {
					i.isCount = 0
				} else {
					i.isCount = 1
				}
				if (i.type == 0) {
					t.html(k)
				} else {
					t.html(o)
				}
				m.html("").hide();
				h()
			};
			this.gotoPageIndex = function(E, D) {
				w(E, D)
			};
			this.initData = function() {
				w(1, g)
			}
		};
		CBLFun = new c();
		isLoaded = true;
		CBLFun.initData()
	};
	b()
});
