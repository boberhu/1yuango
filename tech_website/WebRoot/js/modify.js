$(function() {
	var a = function() {
		var an = function(d) {
			var b = /^1\d{10}$/;
			return b.test(d)
		};
		var aw = function(d) {
			var b = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			return b.test(d)
		};
		var R = function(d) {
			var b = /^(0[0-9]{2,3}\-)+([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
			return b.test(d)
		};
		var ad = function(b) {
			if (b.length > 20) {
				return false
			}
			var d = /^[\w\u4e00-\u9fa5\-]{2,20}$/;
			return d.test(b)
		};
		function W(b, d) {
			document.cookie = b + "=" + escape(d) + ";id=1ypg;path=/;domain="
					+ $domain
		}
		var ak = true;
		var ag = $("#txtName");
		var Q = $("#txtPhone");
		var ac = $("#txtQQ");
		var V = $("#txtSignature");
		var T = $("#userId").val();
		var ae = true;
		var S = {
			oldUserName : $("#hidOldName").val(),
			userName : "",
			userPhone : "",
			mobilePhone : "",
			userMail : "",
			userSex : 0,
			userBirthDay : "",
			userCons : 0,
			userLiveArea : 0,
			liveAreaName : "",
			userBirthArea : 0,
			birthAreaName : "",
			userQQ : "",
			userMonthIncome : "",
			userSign : "",
			rnd : 0
		};
		var Y = function(b, d) {
			ae = false;
			b.removeClass("txt").addClass("orangeBor");
			b.nextAll("span").eq(0).attr("class", "orange").html("<s></s>" + d)
		};
		var M = function(b, d) {
			b.removeClass("orangeBor").addClass("txt");
			b.nextAll("span").eq(0).attr("class", "").html(
					(d == undefined ? "" : d))
		};
		var av = function(b) {
			ae = false;
			if (b == 0) {
				$("#errmsgArea").attr("class", "orange").html(
						"<s></s>\u8bf7\u9009\u62e9\u6240\u5728\u5e02\u533a")
						.show()
			} else {
				$("#errmsgArea2").attr("class", "orange").html(
						"<s></s>\u8bf7\u9009\u62e9\u6240\u5728\u5e02\u533a")
						.show()
			}
		};
		var af = function(d) {
			this.DataTextField = null;
			this.DataValueField = null;
			this.DataSource = null;
			this.OnItemDataBinding = null;
			this.OnSelectedIndexChanged = null;
			var b = d;
			this.DataBind = function() {
				var f = $("#" + b);
				f.empty();
				if (this.DataSource == null) {
					return
				}
				var g = this.DataSource.length;
				if (this.OnSelectedIndexChanged != null) {
					f.bind("change", this.OnSelectedIndexChanged)
				}
				var e = null;
				for (var h = 0; h < g; h++) {
					e = this.DataSource[h];
					if (this.OnItemDataBinding != null) {
						this.OnItemDataBinding(e)
					}
					f.get(0).options.add(new Option(e[this.DataTextField],
							e[this.DataValueField]))
				}
			};
			this.appendFirstOption = function(f, e) {
				$("#" + b).get(0).options.add(new Option(f, e), 0)
			};
			this.show = function() {
				$("#" + b).show()
			};
			this.hide = function() {
				$("#" + b).hide()
			};
			this.getValue = function() {
				return $("#" + b).val()
			};
			this.setValue = function(e) {
				$("#" + b).val(e)
			};
			this.clear = function() {
				$("#" + b).empty()
			};
			this.getText = function() {
				return $("#" + b).get(0).options[$("#" + b).get(0).selectedIndex].text
			};
			this.getSelectedIndex = function() {
				return $("#" + b).get(0).selectedIndex
			}
		};
		var ao = function(f) {
			var d = null;
			var e = null;
			if (f == 0) {
				d = new af("sel_areaA");
				e = new af("sel_areaB")
			} else {
				d = new af("sel_areaA2");
				e = new af("sel_areaB2")
			}
			var g = new Object();
			d.DataTextField = e.DataTextField = "name";
			d.DataValueField = e.DataValueField = "id";
			var h = function(k, j) {
				var i = {
					name : "\u8bf7\u9009\u62e9\u3000\u3000",
					id : 0
				};
				if (j == 0) {
					k.DataSource = [ i ];
					k.DataBind();
					return
				}
				var l = g[j];
				if (l != null) {
					k.DataSource = l.regions;
					k.DataBind();
					return
				}
			};
			var b = function() {
				var i = d.getValue();
				h(e, i)
			};
			d.OnSelectedIndexChanged = b;
			e.OnSelectedIndexChanged = function() {
				var i = e.getValue();
				if (i == "0") {
					av(f)
				} else {
					if (f == 0) {
						$("#errmsgArea").hide()
					} else {
						$("#errmsgArea2").hide()
					}
				}
			};
			this.setValue = function(i, j, k) {
				h(d, 1);
				d.setValue(i);
				if (i != 0) {
					h(e, i)
				} else {
					h(e, 0)
				}
				if (j != 0) {
					e.setValue(j)
				}
				return
			};
			this.getValue = function() {
				var i = d.getValue();
				var j = e.getValue();
				if (i != "0" && j == "0") {
					av(f);
					return null
				}
				return {
					areaAID : i,
					areaBID : j
				}
			};
			this.getText = function() {
				var i = d.getValue();
				var j = e.getValue();
				if (i != "0" && j == "0") {
					av(f);
					return null
				} else {
					return d.getText() + e.getText()
				}
			}
		};
		var ap = new ao(0);
		ap.setValue($("#hidSelareaA").val(), $("#hidSelareaB").val());
		var au = new ao(1);
		au.setValue($("#hidSelareaA2").val(), $("#hidSelareaB2").val());
		var al = $("#sltYear");
		var aq = $("#sltMouth");
		var N = $("#sltDay");
		al.bind("change", function() {
			ab(al, aq, N)
		});
		aq.bind("change", function() {
			ab(al, aq, N)
		});
		var ar = null;
		var O = function() {
			ar = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
			var d = new Date().getFullYear();
			var b = new Date().getMonth() + 1;
			var f = new Date().getDate();
			var g = $("#hidBirthday").val().split("-");
			for (var h = d; h > d - 60; h--) {
				if (h == g[0]) {
					al.append("<option value='" + h + "' selected>" + h
							+ "</option>")
				} else {
					al.append("<option value='" + h + "'>" + h + "</option>")
				}
			}
			for (var h = 1; h <= 12; h++) {
				if (h == g[1]) {
					aq.append("<option value='" + h + "' selected>" + h
							+ "</option>")
				} else {
					aq.append("<option value='" + h + "'>" + h + "</option>")
				}
			}
			var e = ar[b - 1];
			if (b == 2 && ah(al.val())) {
				e++
			}
			for (var h = 1; h <= e; h++) {
				if (h == g[2]) {
					N.append("<option value='" + h + "' selected>" + h
							+ "</option>")
				} else {
					N.append("<option value='" + h + "'>" + h + "</option>")
				}
			}
		};
		var ab = function(g, f) {
			var d = g.val();
			var b = f.val();
			var e = ar[b - 1];
			if (b == 2 && ah(d)) {
				e++
			}
			aa(e)
		};
		var aa = function(d) {
			L();
			for (var b = 1; b <= d; b++) {
				N.append("<option value='" + b + "'>" + b + "</option>")
			}
		};
		var L = function() {
			N.find("option").remove()
		};
		var ah = function(b) {
			return (b % 4 == 0 || (b % 100 == 0 && b % 400 == 0))
		};
		if (!al.attr("disabled")) {
		} else {
			var X = $("#hidBirthday").val().split("-");
			$("#sltYear").append(
					"<option value='" + X[0] + "' selected>" + X[0]
							+ "</option>");
			$("#sltMouth").append(
					"<option value='" + X[1] + "' selected>" + X[1]
							+ "</option>");
			$("#sltDay").append(
					"<option value='" + X[2] + "' selected>" + X[2]
							+ "</option>");
			var aj = $("#hidUpdateTime").val().split("-");
			var am = (parseInt(aj[0]) + 1);
			var ai = am
					+ "\u5e74"
					+ aj[1]
					+ "\u6708"
					+ aj[2]
					+ "\u65e5\u540e\u624d\u80fd\u518d\u6b21\u7f16\u8f91\u60a8\u7684\u751f\u65e5";
			$("#BirthdayMsg").html(ai).show()
		}
		var c = function() {
			var d = [ "\u767d\u7f8a\u5ea7", "\u91d1\u725b\u5ea7",
					"\u53cc\u5b50\u5ea7", "\u5de8\u87f9\u5ea7",
					"\u72ee\u5b50\u5ea7", "\u5904\u5973\u5ea7",
					"\u5929\u67b0\u5ea7", "\u5929\u874e\u5ea7",
					"\u5c04\u624b\u5ea7", "\u6469\u7faf\u5ea7",
					"\u6c34\u74f6\u5ea7", "\u53cc\u9c7c\u5ea7" ];
			var b = $("#selCons");
			b.empty();
			var f = $("#hideCons").val();
			for (var e = 1; e < 13; e++) {
				if (e == f) {
					b.append("<option value=" + e + " selected>" + d[e - 1]
							+ "</option>")
				} else {
					b.append("<option value=" + e + ">" + d[e - 1]
							+ "</option>")
				}
			}
		};
		c();
		var P = "\u6635\u79f0\u957f\u5ea6\u4e3a2-20\u4e2a\u5b57\u7b26\uff0c\u7531\u6c49\u5b57\u3001\u5b57\u6bcd\u3001\u6570\u5b57\u3001\u201c_-\u201d\u5b57\u7b26\u7ec4\u6210";
		ag
				.blur(function() {
					var b = ag.val();
					if (b == "") {
						Y(ag, "\u8bf7\u8f93\u5165\u6635\u79f0")
					} else {
						if (!ad(b)) {
							Y(ag, P, 300)
						} else {
							if (b != S.oldUserName) {
								$
										.ajax({
											url : "/user/isUserNameExists.html",
											type : "post",
											data : "id="
													+ encodeURIComponent(b)
													+ "&userId=" + T,
											success : function(d) {
												if (d == "true") {
													M(ag, "")
												} else {
													Y(ag,
															"\u8be5\u6635\u79f0\u5df2\u88ab\u4f7f\u7528\uff0c\u6362\u4e00\u4e2a\u5427")
												}
											}
										})
							} else {
								M(ag, P)
							}
						}
					}
				});
		Q
				.blur(function() {
					var b = Q.val();
					if (b != "" && (!R(b) && !an(b))) {
						Y(Q,
								"\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7535\u8bdd\u53f7\u7801")
					} else {
						M(Q)
					}
				});
		ac.blur(function() {
			var b = ac.val();
			var d = /^[0-9]{5,12}$/;
			if (b != "" && !d.test(b)) {
				Y(ac, "QQ\u4e3a5-12\u4f4d\u6570\u5b57")
			} else {
				M(ac)
			}
		});
		V
				.focus(
						function() {
							if (V.val() == "\u8ba9\u522b\u4eba\u770b\u5230\u4e0d\u4e00\u6837\u7684\u4f60\uff01") {
								V.val("").removeClass("gray03").addClass(
										"gray01")
							}
						})
				.blur(
						function() {
							if (V.val() == "") {
								V
										.val(
												"\u8ba9\u522b\u4eba\u770b\u5230\u4e0d\u4e00\u6837\u7684\u4f60\uff01")
										.removeClass("gray01").addClass(
												"gray03")
							}
						}).bind("keyup", function(d) {
					var b = (window.event) ? event.keyCode : d.keyCode;
					var e = V.val();
					if (b == 13) {
						e = e.replace("\n", "");
						V.val(e)
					}
					if (e.length > 200) {
						V.val(e.substr(0, 200))
					}
				}).bind("keydown", function(d) {
					if (!window.event) {
						var b = d.keyCode;
						var b = String.fromCharCode(b).toLowerCase();
						if (d.ctrlKey && b == "v") {
							d.preventDefault();
							d.stopPropagation()
						}
					}
				});
		if (V.val() != "\u8ba9\u522b\u4eba\u770b\u5230\u4e0d\u4e00\u6837\u7684\u4f60\uff01") {
			V.removeClass("gray03").addClass("gray01")
		}
		function at() {
			ak = false;
			$("#butSaveSubmit").attr("disabled", true)
		}
		var U = function() {
			if (!ak) {
				return
			}
			var b = '{"userName":"' + S.userName + '","mobilePhone":"'
					+ S.mobilePhone + '","qq":"' + S.userQQ + '","userSign":"'
					+ S.userSign + '"}';
			alert(b);
			$
					.ajax({
						url : "/user/updateUser.action",
						type:"post", 
						data : "id=" + T + "&userJSON=" + encodeURIComponent(b),
						beforeSend : at,
						contentType: "application/x-www-form-urlencoded; charset=utf-8", 
						success : function(d) {
							if (d != "false") {
								ak = true;
								$("#butSaveSubmit").attr("disabled", false);
								$
										.PageDialog(
												'<dl class="sAltOK"><dd>\u64cd\u4f5c\u6210\u529f\uff01</dd></dl>',
												{
													W : 160,
													H : 50,
													autoClose : true
												});
								if (d.userId != null && d.userId != "") {
									W("userId", d.userId);
									W("face", d.faceImg)
								}
								if (d.userName != null && d.userName != "") {
									W("userName", d.userName)
								} else {
									if (d.mail != null && d.mail != "") {
										W("userName", d.mail)
									} else {
										if (d.phone != null && d.phone != "") {
											W("userName", d.phone)
										}
									}
								}
								setTimeout(function() {
									location.replace(location.href)
								}, 1500)
							} else {
								$
										.PageDialog(
												'<dl class="sAltFail"><dd>\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\uff01</dd></dl>',
												{
													W : 160,
													H : 50,
													autoClose : true
												});
								setTimeout(function() {
									location.replace(location.href)
								}, 1500)
							}
						},
						error : function() {
							alert("\u7f51\u7edc\u9519\u8bef\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\uff01");
							location.reload()
						}
					})
		};
		var Z = function() {
			if (!ak) {
				return
			}
			ae = true;
			var e = ag.val();
			if (e == "") {
				Y(ag, "\u8bf7\u8f93\u5165\u6635\u79f0")
			} else {
				if (!ad(e)) {
					Y(ag, P, 300)
				}
			}
			S.userName = e;
			var f = Q.val();
			if (f != "" && (!R(f) && !an(f))) {
				Y(Q,
						"\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7535\u8bdd\u53f7\u7801")
			}
			S.mobilePhone = f;
			if ($("#rdoBoy").attr("checked") == true) {
				S.userSex = 2
			} else {
				if ($("#rdoGirl").attr("checked") == true) {
					S.userSex = 1
				} else {
					if ($("#rdoSecrit").attr("checked") == true) {
						S.userSex = 3
					}
				}
			}
			var b = ac.val();
			var d = /^[0-9]{5,12}$/;
			if (b != "" && !d.test(b)) {
				Y(ac, "QQ\u4e3a5-12\u4f4d\u6570\u5b57")
			}
			S.userQQ = b;
			S.userMonthIncome = $("#sltMonthIncome").val();
			if (V.val() != "\u8ba9\u522b\u4eba\u770b\u5230\u4e0d\u4e00\u6837\u7684\u4f60\uff01") {
				S.userSign = V.val()
			}
			if (ae) {
				U()
			}
		};
		$("#butSaveSubmit").bind("click", function() {
			Z()
		});
		ak = true
	};
	a()
});
