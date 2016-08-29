
var CBLFun = null;
$(document).ready(function () {
	var A = "http://skin.1ypg.com";
	var C = function () {
		var D = function () {
			var J = 10;
			var F = 1;
			var P = 0;
			var T = $(".ui-select-selected");
			var M = $(".ui-select-list");
			var U = $("#txtMisTime");
			var E = $("#txtMaxTime");
			var H = 1;
			var G = 0;
			var L = ["\u6240\u6709\u8bb0\u5f55", "\u5f53\u5929", "\u6700\u8fd1\u4e00\u5468", "\u6700\u8fd1\u4e00\u4e2a\u6708", "\u6700\u8fd1\u4e09\u4e2a\u6708", "\u9009\u62e9\u65f6\u95f4\u6bb5"];
			T.mouseover(function () {
				M.show().parent().hover(function () {
					M.show();
				}, function () {
					M.hide();
				});
			});
			$(".ui-select-list li").each(function (Y, X) {
				$(this).bind("click", function () {
					H = 1;
					G = Y;
					T.html(L[Y] + "<i></i>");
					O.region = Y;
					$(this).parent().hide();
					if (Y == 5) {
						N();
					} else {
						U.val("");
						E.val("");
						$(".ui-input").hide();
						$("#butTimeSelect").hide();
						I(1, J);
					}
				});
			});
			var N = function () {
				$(".ui-input").show();
				$("#butTimeSelect").show();
				var X = new Date();
				U.val(X.DateAdd("m", -1).Format("YYYY-MM-DD")).date_input();
				E.val(X.Format("YYYY-MM-DD")).date_input();
				var Y = function () {
					var Z = U.val();
					var a = E.val();
					if (!IsValidDate(Z)) {
						alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u5f00\u59cb\u65e5\u671f!");
						return;
					}
					if (!IsValidDate(a)) {
						alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7ed3\u675f\u65e5\u671f!");
						return;
					}
					if (ConvertDate(a) < ConvertDate(Z)) {
						alert("\u7ed3\u675f\u65e5\u671f\u4e0d\u80fd\u5c0f\u4e8e\u5f00\u59cb\u65e5\u671f!");
						return;
					}
					G = 5;
					O.region = 5;
					I(1, J);
				};
				$("#butTimeSelect").unbind("click").bind("click", Y);
			};
			var K = $("#BrokerageList");
			var O = {"FIdx":(H - 1) * J + 1, "EIdx":H * J, "region":parseInt(G), "beginTime":"", "endTime":"", "isCount":1};
			var Q = new JQAjax("/DataServer/GetMemberCenterCommissionList");
			var S = "<ul class='User_title_bg'><li class='User'>\u7528\u6237</li><li class='time'>\u65f6\u95f4</li><li class='Commodity'>\u63cf\u8ff0</li><li class='money'>\u60ca\u559c\u91d1\u989d(\u5143)</li><li class='Commission'>\u4f63\u91d1(\u5143)</li></ul>";
			var R = function () {
				if (!isLoaded) {
					return false;
				}
				Q.OnSuccess(function (X) {
					eval("X=" + X.d);
					$("#divNoRecord").html("");
					if (X.errorCode == 1) {
						$("#divNoRecord").html("\u67e5\u65e0\u4f63\u91d1\u660e\u7ec6\u8bb0\u5f55\uff0c\u8bf7\u66f4\u6539\u67e5\u8be2\u533a\u95f4\uff01");
					} else {
						if (X.errorCode == 0) {
							if (O.isCount) {
								P = X.totalCount;
							}
							var a = X.listItems;
							for (var Z = 0; Z < a.length; Z++) {
								var Y = "<ul class=\"content\">";
								Y += "<li class=\"User\">" + a[Z].userName + "</li>";
								Y += "<li class=\"time\">" + a[Z].buyTime + "</li>";
								if (a[Z].period != "" && a[Z].codeID != "") {
									Y += "<li class=\"Commodity\"><span class=\"orange\">" + a[Z].period + "</span>";
									Y += "<a target=\"_blank\" href=\"http://www.1ypg.com/product/" + a[Z].codeID + ".html\" class=\"blue\">" + a[Z].descript.reAjaxStr() + "</a></li>";
								} else {
									Y += "<li class=\"Commodity\">" + a[Z].descript + "</li>";
								}
								Y += "<li class=\"money\">" + a[Z].buyMoney + "</li>";
								Y += "<li class=\"Commission orange\">" + a[Z].brokerage + "</li>";
								Y += "</ul>";
								K.append(Y);
							}
							if (P > J) {
								K.append("<div class='page_nav'>" + GetAjaxPageNation(P, J, 5, F, "CBLFun.gotoPageIndex") + "</div>");
							}
						} else {
							if (X.errorCode == 10) {
								location.reload();
							}
						}
					}
				});
				Q.OnSend($.toJSON(O), "json", true);
			};
			var V = function (X, Y) {
				F = Math.floor(Y / J);
				O.FIdx = X;
				O.EIdx = Y;
				K.html(S);
				R();
			};
			this.gotoPageIndex = function (X, Y) {
				V(X, Y);
			};
			var I = function (X, Y) {
				O.beginTime = U.val();
				O.endTime = E.val();
				V(X, Y);
			};
			var W = function () {
				I((H - 1) * J + 1, H * J);
				return;
			};
			this.initData = function () {
				W();
			};
		};
		CBLFun = new D();
		isLoaded = true;
		CBLFun.initData();
	};
	var B = function () {
		var E = function (F) {
			$.getScript(A + "/js/date.js", F);
		};
		var D = function () {
			$.getScript(A + "/js/ajaxfun.js", C);
		};
		$.getScript(A + "/js/dateinput.js", function () {
			E(D);
		});
	};
	$.loadcss(A + "/css/dateinput.css", B);
});

