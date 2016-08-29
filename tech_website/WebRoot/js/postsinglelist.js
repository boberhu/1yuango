var CBLFun = null;
$(document)
		.ready(
				function() {
					var e = $skin;
					var f = $("#resultCount").val();
					var d = 0;
					var g = $("#userId").val();
					var c = function() {
						var a = false;
						$("div.subMenu")
								.find("a")
								.each(
										function(l, m) {
											$(this)
													.click(
															function() {
																if (l == 0) {
																	$(this)
																			.attr(
																					"class",
																					"current")
																			.siblings()
																			.attr(
																					"class",
																					"");
																	$(
																			"#PostList0")
																			.show();
																	$(
																			"#PostList1")
																			.hide();
																	$(
																			"#divPageNav0")
																			.show();
																	$(
																			"#divPageNav1")
																			.hide()
																} else {
																	$(this)
																			.attr(
																					"class",
																					"current")
																			.siblings()
																			.attr(
																					"class",
																					"current2");
																	$(
																			"#PostList0")
																			.hide();
																	$(
																			"#PostList1")
																			.show();
																	$(
																			"#divPageNav0")
																			.hide();
																	$(
																			"#divPageNav1")
																			.show();
																	if (!a) {
																		j()
																	}
																}
															})
										});
						$("#pagination").pagination(f, {
							current_page : d,
							prev_text : "\u4e0a\u4e00\u9875",
							next_text : "\u4e0b\u4e00\u9875",
							num_display_entries : 5,
							num_edge_entries : 1,
							link_to : "",
							prev_show_always : false,
							next_show_always : false,
							items_per_page : 5,
							callback : b
						});
						function b(l) {
							var m = "/user/PostSingleListAjaxPage.action?pageNo="
									+ l + "&userId=" + g;
							$(".pageUL").prepend(
									'<li class="total_page" id="pageLoading"><img src="'
											+ $img
											+ '/Images/loding.gif" /></li>');
							$
									.ajax({
										url : m,
										type : "get",
										beforeSend : k,
										success : function(n) {
											$("#PostList0").empty();
											if (f > 0) {
												$(
														'<ul class="listTitle"><li class="single-img">\u6652\u5355\u56fe\u7247</li><li class="single-xx-has">\u6652\u5355\u4fe1\u606f</li><li class="sdzt">\u6652\u5355\u72b6\u6001</li><li class="single-Control">\u64cd\u4f5c</li></ul>')
														.appendTo("#PostList0");
												$(".pageULEx").show();
												$(".pageULEx")
														.prepend(
																'<li class="total_page">\u9875\u6b21<i>'
																		+ (l + 1)
																		+ "/"
																		+ Math
																				.ceil(f / 5)
																		+ "</i>&nbsp;&nbsp;\u5171<i>"
																		+ f
																		+ "</i>\u6761\u8bb0\u5f55</li>");
												h(n)
											} else {
												$(
														'<ul class="listTitle"><li class="single-img">\u6652\u5355\u56fe\u7247</li><li class="single-xx-has">\u6652\u5355\u4fe1\u606f</li><li class="sdzt">\u6652\u5355\u72b6\u6001</li><li class="single-Control">\u64cd\u4f5c</li></ul>')
														.appendTo("#PostList0");
												$(
														'<div class="tips-con"><i></i>\u6682\u65e0\u8bb0\u5f55\uff01</div>')
														.appendTo("#PostList0");
												$(".pageULEx").hide()
											}
										}
									})
						}
						function k() {
							$("#pageLoading").show()
						}
						var j = function() {
							$
									.ajax({
										url : "/user/PostSingleListAjaxPageResultCount.action?pageNo="
												+ d
												+ "&userId="
												+ g
												+ "&postAllPic=-1&hidPicUrl=4",
										type : "get",
										success : function(l) {
											f = parseInt(l);
											$("#pagination")
													.pagination(
															f,
															{
																current_page : d,
																prev_text : "\u4e0a\u4e00\u9875",
																next_text : "\u4e0b\u4e00\u9875",
																num_display_entries : 5,
																num_edge_entries : 1,
																link_to : "",
																prev_show_always : false,
																next_show_always : false,
																items_per_page : 5,
																callback : i
															})
										}
									});
							a = true
						};
						function i(l) {
							var m = "/user/OrderListAjaxPage.action?pageNo="
									+ l + "&userId=" + g
									+ "&postAllPic=&hidPicUrl=4";
							$(".pageUL").prepend(
									'<li class="total_page" id="pageLoading"><img src="'
											+ $img
											+ '/Images/loding.gif" /></li>');
							$
									.ajax({
										url : m,
										type : "get",
										beforeSend : k,
										success : function(p) {
											$("#PostList1").empty();
											if (f > 0) {
												$(
														'<ul class="single-tit listTitle"><li class="single-img">\u5546\u54c1\u56fe\u7247</li><li class="single-xx-not">\u5546\u54c1\u4fe1\u606f</li><li class="single-Control">\u64cd\u4f5c</li></ul>')
														.appendTo("#PostList1");
												$(".pageULEx").show();
												$(".pageULEx")
														.prepend(
																'<li class="total_page">\u9875\u6b21<i>'
																		+ (l + 1)
																		+ "/"
																		+ Math
																				.ceil(f / 5)
																		+ "</i>&nbsp;&nbsp;\u5171<i>"
																		+ f
																		+ "</i>\u6761\u8bb0\u5f55</li>");
												for (var o = 0; o < p.length; o++) {
													var n = "";
													n += '<ul class="sdzt"><li class="single-img"><a href="'
															+ $www
															+ "/products/"
															+ p[o].productId
															+ '.html" class="pic" target="_blank" title=""><img src="'
															+ $img
															+ p[o].productImg
															+ '"></a></li><li class="single-xx-has"><a class="blue" href="'
															+ $www
															+ "/products/"
															+ p[o].productId
															+ '.html" target="_blank">(\u7b2c'
															+ p[o].productPeriod
															+ "\u671f)"
															+ p[o].productName
															+ '</a><p class="buy-money">\u4ef7\u503c\uff1a<span class="money"><i class="rmb"></i>'
															+ p[o].productPrice
															+ '.00</span></p><p class="buy-code">\u5e78\u8fd0'
															+ $shortName
															+ "\u7801\uff1a"
															+ p[o].winId
															+ '</p><p class="gray02">\u63ed\u6653\u65f6\u95f4\uff1a'
															+ p[o].winDate
															+ '</p></li><li class="sdzt"><span class="orange">';
													if (p[o].buyStatus == 1) {
														n += '\u6536\u8d27\u5730\u5740\u672a\u786e\u8ba4<br /><a class="blue" href="/user/OrderDetail-'
																+ p[o].productId
																+ '.html">\u7acb\u5373\u786e\u8ba4\u5730\u5740</a>'
													} else {
														if (p[o].buyStatus == 2) {
															n += "\u7b49\u5f85\u53d1\u8d27"
														} else {
															if (p[o].buyStatus == 3) {
																n += "\u7b49\u5f85\u6536\u8d27"
															} else {
																if (p[o].buyStatus == 4) {
																	n += "\u5df2\u786e\u8ba4\u6536\u8d27";
																	if (p[o].shareStatus == -1) {
																		n += "<br />\u6682\u672a\u6652\u5355"
																	} else {
																		if (p[o].shareStatus == 0) {
																			n += "<br />\u7b49\u5f85\u5ba1\u6838"
																		} else {
																			if (p[o].shareStatus == 1) {
																				n += "<br />\u672a\u5ba1\u6838\u901a\u8fc7"
																			} else {
																				if (p[o].shareStatus == 2) {
																					n += "<br />\u5ba1\u6838\u901a\u8fc7"
																				}
																			}
																		}
																	}
																} else {
																	if (p[o].buyStatus == 10) {
																		n += "\u4ea4\u6613\u5b8c\u6210";
																		if (p[o].shareStatus == -1) {
																			n += "<br />\u6682\u672a\u6652\u5355"
																		} else {
																			if (p[o].shareStatus == 0) {
																				n += "<br />\u6652\u5355\u7b49\u5f85\u5ba1\u6838"
																			} else {
																				if (p[o].shareStatus == 1) {
																					n += "<br />\u6652\u5355\u672a\u5ba1\u6838\u901a\u8fc7"
																				} else {
																					if (p[o].shareStatus == 2) {
																						n += "<br />\u6652\u5355\u5ba1\u6838\u901a\u8fc7"
																					}
																				}
																			}
																		}
																	} else {
																		if (p[o].buyStatus == 11) {
																			n += "\u4ea4\u6613\u53d6\u6d88"
																		}
																	}
																}
															}
														}
													}
													n += "</span></li>";
													n += '<li class="single-Control"><a title="\u8be6\u60c5" class="blue" href="'
															+ $www
															+ "/user/OrderDetail-"
															+ p[o].productId
															+ '.html">\u4ea4\u6613\u8be6\u60c5</a>';
													if (p[o].buyStatus == 4) {
														n += '<a class="blue" href="'
																+ $www
																+ "/user/PostSingleAdd-"
																+ p[o].productId
																+ '.html">\u7acb\u5373\u6652\u5355</a>'
													}
													n += "</li></ul>";
													$(n).appendTo("#PostList1")
												}
											} else {
												$(
														'<ul class="single-tit listTitle"><li class="single-img">\u5546\u54c1\u56fe\u7247</li><li class="single-xx-not">\u5546\u54c1\u4fe1\u606f</li><li class="single-Control">\u64cd\u4f5c</li></ul>')
														.appendTo("#PostList1");
												$(
														'<div class="tips-con"><i></i>\u6682\u65e0\u8bb0\u5f55\uff01</div>')
														.appendTo("#PostList1");
												$(".pageULEx").hide()
											}
										}
									})
						}
						var h = function(u) {
							var l = u;
							var m = "";
							for (var n = 0; n < l.length; n++) {
								if (l[n].shareStatus < 2) {
									m = '<a href="/user/PostSingleDetail-'
											+ l[n].shareId + '.html"'
								} else {
									m = '<a href="/shareShow/' + l[n].shareId
											+ '.html" target="_blank"'
								}
								var o = '<ul><li class="single-img">'
										+ m
										+ '><img alt="" src="'
										+ $img
										+ "/UserPost/220/"
										+ l[n].productImg
										+ '"></a></li><li class="single-xx-has"><p>'
										+ m + l[n].shareId + ' class="blue">'
										+ l[n].productTitle
										+ '</a> <span class="gray02">'
										+ l[n].buyTime + "</span></p>"
										+ l[n].productName + "</li>";
								if (l[n].shareStatus == 0) {
									o += '<li class="sdzt"><p class="gray02">\u5ba1\u6838\u4e2d...</p></li>';
									o += '<li class="single-Control"><a href="/user/PostSingleDetail-'
											+ l[n].shareId
											+ '.html" class="blue" title="\u8be6\u60c5">\u8be6\u60c5</a>'
								} else {
									if (l[n].shareStatus == 1) {
										o += '<li class="sdzt"><p class="red">\u5ba1\u6838\u672a\u901a\u8fc7</p>\u8bf7\u91cd\u65b0\u4fee\u6539\u6652\u5355\u5185\u5bb9</li>';
										o += '<li class="single-Control"><a href="/user/PostSingleEdit-'
												+ l[n].shareId
												+ '.html" class="blue" title="\u7f16\u8f91">\u7f16\u8f91</a>'
									} else {
										if (l[n].shareStatus == 2) {
											o += '<li class="sdzt"><p class="green">\u5ba1\u6838\u901a\u8fc7</p></li>';
											o += '<li class="single-Control"><a href="/shareShow/'
													+ l[n].shareId
													+ '.html" target="_blank" class="blue" title="\u8be6\u60c5">\u8be6\u60c5</a>'
										}
									}
								}
								o += "</li></ul>";
								$("#PostList0").append(o)
							}
						}
					};
					c()
				});
