var theAddressNum = 0;
var listItems = null;
var addresslistDiv = null;
var divConsignee = null;
var userId = $("#userId").val();
$(document).ready(function() {
	var d = $skin;
	var c = function() {
		addresslistDiv = $("#addressListDiv");
		divConsignee = $("#div_consignee");
		isLoaded = true;
		getAddressListData();
		$("#btnAddnewAddr").click(function() {
			checkIsAddnewAddress(this)
		})
	};
	c()
});
function checkIsAddnewAddress(b) {
	if (theAddressNum < 4) {
		$(b).hide();
		addressAddnew()
	} else {
		$
				.PageDialog(
						'<dl class="sAltFail"><dd>\u5bf9\u4e0d\u8d77\uff0c\u6700\u591a\u53ea\u80fd\u5f55\u51654\u4e2a\u6536\u8d27\u5730\u5740\uff0c\u8bf7\u5220\u9664\u5176\u4e2d\u7684\u67d0\u4e2a\u6536\u8d27\u5730\u5740\uff01</dd></dl>',
						{
							W : 430,
							H : 50
						})
	}
}
function getTrimVal(b) {
	return $("#" + b).val()
}
var ListHeadHtml = '<ul class="listTitle tdTitle"><li class="pad">\u8be6\u7ec6\u5730\u5740</li><li class="wid55">\u90ae\u653f\u7f16\u7801</li><li class="wid70">\u6536\u8d27\u4eba</li><li class="wid110">\u7535\u8bdd\u53f7\u7801</li><li class="wid80">&nbsp;</li><li class="wid70">\u64cd\u4f5c</li></ul>';
function getAddressListData() {
	if (!isLoaded) {
		return
	}
	$
			.ajax({
				url : "/user/getAddressList.html",
				data : "userId=" + userId,
				success : function(e) {
					var f = true;
					var a = "";
					if (e.length > 0) {
						for (var c = 0; c < e.length; c++) {
							a += '<ul class="'
									+ (e[c].status == 1 ? "liBg" : "") + '">';
							a += '<li class="pad">' + e[c].province + ","
									+ e[c].city + "," + e[c].district + ","
									+ e[c].address + "</li>";
							a += '<li class="wid55">' + e[c].zipCode + "</li>";
							a += '<li class="wid70">' + e[c].consignee
									+ "</li>";
							a += '<li class="wid110">' + e[c].phone + "</li>";
							if (e[c].status == 1) {
								a += '<li class="wid80 orange">\u9ed8\u8ba4\u5730\u5740</li>'
							} else {
								a += '<li class="wid80 lightBlue"><a class="blue" href="javascript:;" onclick="javascript:addressSetDefault('
										+ e[c].id
										+ ');">\u8bbe\u4e3a\u9ed8\u8ba4\u5730\u5740</a></li>\n'
							}
							a += '<li class="wid70">'
									+ (e[c].status == 1 ? ""
											: ' <a href="javascript:;" onclick="javascript:addressDelete('
													+ e[c].id
													+ ');" title="\u5220\u9664">\u5220\u9664</a>')
									+ "</li>";
							a += "</ul>"
						}
						if (f) {
							theAddressNum = e.length;
							$("#btnAddnewAddr").show();
							addresslistDiv.html(ListHeadHtml + a);
							addresslistDiv.show()
						} else {
							addresslistDiv.empty();
							theAddressNum = 0;
							isLoaded = true;
							addressAddnew()
						}
					}
				}
			})
}
function StringBuilder(b) {
	this.s = new Array(b);
	this.append = function(a) {
		this.s.push(a);
		return this
	};
	this.toString = function() {
		return this.s.join("")
	};
	this.clear = function() {
		this.s = new Array()
	};
	this.appendFormat = function() {
		var q = {
			object : function(g, d) {
				return g[1][d]
			},
			string : function(g, d) {
				return g[d - 0 + 1]
			}
		};
		var r = arguments.length;
		if (r == 0) {
			return this
		}
		var f = arguments[0];
		if (r == 1) {
			return this.append(f)
		}
		var a = arguments[1];
		if (a == null) {
			a = ""
		}
		var i, e, c, n, k = q[typeof (a)];
		for (i = 0; i < f.length;) {
			c = f.charAt(i);
			if (c == "{") {
				e = f.indexOf("}", i);
				n = f.substring(i + 1, e);
				this.s.push(k(arguments, n));
				i = e + 1;
				continue
			}
			this.s.push(c);
			i++
		}
		return this
	}
}
function JSPanel(c) {
	this.Template = null;
	this.DataSource = null;
	var d = function(f, a) {
		var b = new StringBuilder();
		b.appendFormat(f, a);
		return b.toString()
	};
	this.DataBind = function() {
		$("#" + c).html(d(this.Template, this.DataSource))
	}
}
function requireFieldValid(e, f, d) {
	return getTrimVal(e) == "" ? $("#" + f).attr("class", "orange").html(
			" <s></s> " + d) : $("#" + f).attr("class", "red").html(" *")
}
function regularExpressionValid(f, g, h, j) {
	var i = new RegExp(g, "g");
	if (i.test(getTrimVal(f))) {
		$("#" + h).attr("class", "red").html(" *");
		return true
	} else {
		$("#" + h).attr("class", "orange").html(j);
		return false
	}
}
var SHIPNAME_REGULAR_EXPRESSION = "^[\\w\\u4e00-\\u9fa5]{1,20}$";
var SHIP_MB_REGULAR_EXPRESSION = "^1+[0-9]{10}$";
var SHIP_TEL_REGULAR_EXPRESSION = "^(0[0-9]{2,3}-)+([2-9][0-9]{6,7})+(-[0-9]{1,4})?$";
var SHIP_ZIP_REGULAR_EXPRESSION = "^(\\d{6})?$";
var MSG_SHIPADDRESS_FORMAT_ERROR = " 必填";
var MSG_SHIPNAME_EMPTY = " \u5fc5\u586b";
var SHIP_MB_FORMAT_ERROR = " <s></s>请正确输入手机号码";
var SHIP_TEL_FORMAT_ERROR = " <s></s>请正确输入电话号码(区号、号码必填，请用'-隔开')";
var CONSIGNEE_COM_TEMPLATE = '<table border="0" cellpadding="0" cellspacing="0"><tr><td><label>所在地区：</label></td><td><select id="sel_areaA" class="szxq"><option value="0"></option></select><select id="sel_areaB"><option value="0"></option></select><select id="sel_areaC"><option value="0"></option></select><em id="region_id_msg_valid" class="red">*</em></td></tr><tr><td><label>乡镇 / 街道地址：</label></td><td><input type="text" class="street" maxlength="50" id="txt_ship_address" value="{shipAddress}"><em id="ship_address_valid_msg" class="red"> *</em> <span class="gray02">(不需要重复填写省/市/区)</span></td></tr><tr><td><label>邮政编码：</label></td><td><input type="text" maxlength="6" id="txt_ship_zip" class="inputTxt" value="{shipZip}"/> <font><a href="http://alexa.ip138.com/post/Search.aspx" class="blue" target="_blank">邮编查询</a></font><em class="red" id="ship_zip_valid_msg"></em></td></tr><tr><td><label>收货人：</label></td><td><input type="text" maxlength="20" id="txt_ship_name" class="inputTxt" value="{shipName}"/><em class="red" id="ship_name_valid_msg"> *</em></td></tr><tr><td><label>手机号码：</label></td><td><input type="text" id="txt_ship_mb" value="{shipMobile}" class="inputTxt" maxlength="11"><em id="ship_mb_valid_msg" class="red">  *</em></td></tr>';
var CONSIGNEE_EDITABLE_TEMPLATE = "<dl>\u4fee\u6539\u6536\u8d27\u5730\u5740</dl>"
		+ CONSIGNEE_COM_TEMPLATE
		+ '<tr><td>&nbsp;</td><td><input type="button" id="btn_consignee_save" class="orangebut" value="\u4fdd\u5b58" title="\u4fdd\u5b58"> <input type="button" class="cancelBtn" id="btn_consignee_cancle" value="\u53d6\u6d88" title="\u53d6\u6d88"></td></tr></table>';
var CONSIGNEE_ADDNEW_TEMPLATE = "<dl>\u6dfb\u52a0\u6536\u8d27\u5730\u5740</dl>"
		+ CONSIGNEE_COM_TEMPLATE
		+ '<tr><td>&nbsp;</td><td><input type="button" class="orangebut" id="btn_consignee_save" value="\u4fdd\u5b58" title="\u4fdd\u5b58"> <input type="button" class="cancelBtn" value="\u53d6\u6d88" id="btn_consignee_cancle" title="\u53d6\u6d88"></td></tr></table>';
function Region() {
	var c = $("#sel_areaA");
	var e = $("#sel_areaB");
	var i = $("#sel_areaC");
	e.live("change", function() {
		j()
	});
	i.live("change", function() {
		$("#txt_ship_zip").val("")
	});
	c.live("change", function() {
		b()
	});
	var b = function() {
		var a = $("#sel_areaA").val();
		$
				.ajax({
					url : "/user/getCityList.html",
					data : "id=" + a,
					success : function(g) {
						var f = '<option value="0">\u3000\u3000\u3000\u3000\u3000</option>';
						for (var d = 0; d < g.length; d++) {
							f += '<option value="' + g[d][0] + '">' + g[d][1]
									+ "</option>"
						}
						$("#sel_areaB").html(f);
						$("#sel_areaC")
								.html(
										'<option value="0">\u3000\u3000\u3000\u3000\u3000</option>')
					},
					error : function() {
						alert("\u83b7\u53d6\u5730\u533a\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\uff01")
					}
				})
	};
	var j = function() {
		var d = $("#sel_areaB").val();
		var a = $("#sel_areaB option:selected").text();
		$
				.ajax({
					url : "/user/getDistrictList.html",
					data : "id=" + d,
					success : function(h) {
						var g = '<option value="0">\u3000\u3000\u3000\u3000\u3000</option>';
						if (h == "") {
							g += '<option value="' + f + '">' + a + "</option>"
						} else {
							for (var f = 0; f < h.length; f++) {
								g += '<option value="' + h[f][0] + '">'
										+ h[f][1] + "</option>"
							}
						}
						$("#sel_areaC").html(g)
					},
					error : function() {
						alert("\u83b7\u53d6\u5730\u533a\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\uff01")
					}
				})
	};
	this.setValue = function(a, d, f) {
		$
				.ajax({
					url : "/user/getProvinceList.html",
					success : function(k) {
						var h = '<option value="0">\u3000\u3000\u3000\u3000\u3000</option>';
						for (var g = 0; g < k.length; g++) {
							h += '<option value="' + k[g][0] + '">' + k[g][1]
									+ "</option>"
						}
						$("#sel_areaA").html(h)
					},
					error : function() {
						alert("\u83b7\u53d6\u5730\u533a\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\uff01")
					}
				})
	};
	this.getValue = function() {
		var a = $("#sel_areaA").val();
		var d = $("#sel_areaB").val();
		var f = $("#sel_areaC").val();
		if (a == "0" || d == "0" || f == "0") {
			$("#region_id_msg_valid")
					.attr("class", "orange")
					.html(
							" <s></s> \u8bf7\u9009\u62e9\u6709\u6548\u7684\u7701\u5e02\u533a");
			return false
		} else {
			$("#region_id_msg_valid").attr("class", "red").html(" *")
		}
		return {
			areaAID : a,
			areaBID : d,
			areaCID : f
		}
	}
}
var clearNoNum = function(b) {
	b.value = b.value.replace((/[^\d-]/g), "");
	b.value = b.value.replace((/^\-/g), "");
	b.value = b.value.replace((/\-{2,}/g), "-");
	b.value = b.value.replace("-", "$#$").replace((/\-/g), "").replace("$#$",
			"-")
};
var bindTxtFun = function(b) {
	b.bind("keyup", function(a) {
		var d = b.val();
		if (!(a.keyCode > 48 && a.keyCode <= 57)) {
			b.val(d.replace(/\D/g, ""))
		}
	}).bind("dragenter", function() {
		return false
	}).bind("onpaste", function() {
		return !clipboardData.getData("text").match(/\D/)
	})
};
function Consignee(w) {
	var v = 0;
	var t = null;
	var z = null;
	var q = null;
	var p = new JSPanel(w);
	var o = new Region();
	var u = function() {
		var c = o.getValue();
		var a = x();
		if (!c || !a) {
			return
		}
		var b = new Object();
		b.contactID = v;
		b.areaCID = c.areaCID;
		b.shipAddress = getTrimVal("txt_ship_address");
		b.shipZip = getTrimVal("txt_ship_zip");
		b.shipName = getTrimVal("txt_ship_name");
		b.shipMobile = getTrimVal("txt_ship_mb");
		z(b)
	};
	this.setDataSource = function(a) {
		t = a;
		v = t.contactID;
		p.DataSource = a
	};
	this.bindTemplateAddnew = function() {
		p.Template = CONSIGNEE_ADDNEW_TEMPLATE
	};
	this.bindTemplateModify = function() {
		p.Template = CONSIGNEE_EDITABLE_TEMPLATE
	};
	this.showEditable = function() {
		p.DataBind();
		o.setValue(t.areaAID, t.areaBID, t.areaCID);
		bindTxtFun($("#txt_ship_zip"));
		$("#txt_ship_tel").bind("keyup", function() {
			clearNoNum(this)
		}).bind("dragenter", function() {
			return false
		}).bind("onpaste", function() {
			return !clipboardData.getData("text").match(/\D/)
		});
		bindTxtFun($("#txt_ship_mb"));
		$("#btn_consignee_save").bind("click", u);
		$("#btn_consignee_cancle").bind("click", B)
	};
	var B = function() {
		var a = null;
		q(a)
	};
	this.setConsigneeSave = function(a) {
		z = a
	};
	this.setConsigneeCancle = function(a) {
		q = a
	};
	var y = function() {
		return requireFieldValid("txt_ship_name", "ship_name_valid_msg",
				MSG_SHIPNAME_EMPTY)
	};
	var A = function() {
		var b = getTrimVal("txt_ship_mb");
		if (b == "") {
			$("#ship_mb_valid_msg").attr("class", "orange").html(
					" <s></s>\u5fc5\u586b");
			return false
		}
		var a = true;
		if (b != "") {
			a = regularExpressionValid("txt_ship_mb",
					SHIP_MB_REGULAR_EXPRESSION, "ship_mb_valid_msg",
					SHIP_MB_FORMAT_ERROR)
		}
		return a
	};
	var s = function() {
		return requireFieldValid("txt_ship_address", "ship_address_valid_msg",
				MSG_SHIPADDRESS_FORMAT_ERROR)
	};
	var r = function() {
		var a = getTrimVal("txt_ship_zip");
		if (!regularExpressionValid("txt_ship_zip",
				SHIP_ZIP_REGULAR_EXPRESSION, "ship_zip_valid_msg",
				" <s></s> \u8bf7\u6b63\u786e\u586b\u5199\u90ae\u7f16")) {
			return false
		}
		$("#ship_zip_valid_msg").empty();
		return true
	};
	var x = function() {
		return s() && r() && y() && A()
	};
	this.setHignLight = function() {
		$("#btn_consignee_save").attr("class", "y_btn")
	};
	this.setDisabled = function() {
		$("#btn_consignee_save").attr("disabled", "disabled").css("cursor",
				"wait")
	};
	this.unDisabled = function() {
		$("#btn_consignee_save").removeAttr("disabled")
				.css("cursor", "pointer")
	}
}
function oString(c) {
	var b = [];
	for ( var a in c) {
		c[a] = typeof c[a] == "string" ? '"' + c[a] + '"'
				: (typeof c[a] == "object" ? oString(c[a]) : c[a]);
		b.push(a + ":" + c[a])
	}
	return "{" + b.join(",") + "}"
}
function saveShipAddressFun(d, c) {
	var e = {
		userId : userId,
		province : $("#sel_areaA > option:checked").text(),
		city : $("#sel_areaB > option:checked").text(),
		district : $("#sel_areaC > option:checked").text(),
		address : $("#txt_ship_address").val(),
		zipCode : $("#txt_ship_zip").val() == "" ? 0 : $("#txt_ship_zip").val(),
		consignee : $("#txt_ship_name").val(),
		phone : $("#txt_ship_mb").val()
	};
	e = oString(e);
	$
			.ajax({
				url : "/user/addAddress.html",
				data : "userJSON=" + encodeURIComponent(e),
				success : function(a) {
					if (a == "success") {
						$
								.PageDialog(
										'<dl class="sAltOK"><dd>\u64cd\u4f5c\u6210\u529f\uff01</dd></dl>',
										{
											W : 160,
											H : 50,
											autoClose : true
										});
						c = null;
						divConsignee.empty().hide();
						isLoaded = true;
						getAddressListData();
						addresslistDiv.show();
						$("#btnAddnewAddr").show()
					} else {
						if (a == "sizeError") {
							$
									.PageDialog(
											'<dl class="sAltFail"><dd>\u5bf9\u4e0d\u8d77\uff0c\u6700\u591a\u53ea\u80fd\u5f55\u51654\u4e2a\u6536\u8d27\u5730\u5740\uff0c\u8bf7\u5220\u9664\u5176\u4e2d\u7684\u67d0\u4e2a\u6536\u8d27\u5730\u5740\uff01</dd></dl>',
											{
												W : 430,
												H : 50
											})
						}
					}
				},
				error : function() {
					alert("\u7f51\u7edc\u9519\u8bef\uff0c\u8bf7\u4e0e\u7ba1\u7406\u5458\u8054\u7cfb\uff01");
					location.reload()
				}
			})
}
function cancleShipAddressFun() {
	divConsignee.empty().hide();
	addresslistDiv.show();
	$("#btnAddnewAddr").show()
}
function addressAddnew() {
	if (!isLoaded) {
		return
	}
	var f = $("#btn_consignee_save");
	if (f.length > 0) {
		f.attr("class", "y_btn").focus();
		return
	}
	var e = new Consignee("div_consignee");
	e.setConsigneeSave(function(a) {
		saveShipAddressFun(a, e)
	});
	e.setConsigneeCancle(function(a) {
		e = null;
		cancleShipAddressFun()
	});
	var d = {
		contactID : 0,
		areaAID : 0,
		areaBID : 0,
		areaCID : 0,
		shipAddress : "",
		shipZip : "",
		shipName : "",
		shipMobile : "",
		shipTel : ""
	};
	e.setDataSource(d);
	e.bindTemplateAddnew();
	e.showEditable();
	if (theAddressNum == 0) {
		$("#btn_consignee_cancle").hide()
	}
	divConsignee.show()
}
function addressModify(f) {
	if (!isLoaded) {
		return
	}
	var e = new Consignee("div_consignee");
	e.setConsigneeSave(function(a) {
		saveShipAddressFun(a, e)
	});
	e.setConsigneeCancle(function(a) {
		e = null;
		cancleShipAddressFun()
	});
	var d = null;
	if (d != null) {
		$("#btnAddnewAddr").hide();
		e.setDataSource(d);
		e.bindTemplateModify();
		e.showEditable();
		divConsignee.show()
	} else {
		divConsignee.hide();
		return
	}
}
function addressSetDefault(a) {
	if (!isLoaded) {
		return
	}
	$
			.ajax({
				url : "/user/setDefaultAddress.html",
				data : "userId=" + userId + "&id=" + a,
				success : function(b) {
					if (b == "success") {
						$
								.PageDialog(
										'<dl class="sAltOK"><dd>\u64cd\u4f5c\u6210\u529f\uff01</dd></dl>',
										{
											W : 160,
											H : 50,
											autoClose : true
										});
						divConsignee.empty().hide();
						isLoaded = true;
						getAddressListData();
						addresslistDiv.show();
						$("#btnAddnewAddr").show()
					}
				},
				error : function() {
					alert("\u64cd\u4f5c\u5931\u8d25\uff01\u8bf7\u7a0d\u540e\u518d\u8bd5\uff01")
				}
			})
}
function addressDelete(c) {
	if (!isLoaded) {
		return
	}
	if (theAddressNum < 2) {
		alert("\u5bf9\u4e0d\u8d77\uff0c\u60a8\u81f3\u5c11\u8981\u4fdd\u7559\u4e00\u4e2a\u6536\u8d27\u5730\u5740")
	} else {
		var d = function() {
			$
					.ajax({
						url : "/user/delAddress.html",
						data : "id=" + c,
						success : function(a) {
							if (a == "success") {
								$
										.PageDialog(
												'<dl class="sAltOK"><dd>\u64cd\u4f5c\u6210\u529f\uff01</dd></dl>',
												{
													W : 160,
													H : 50,
													autoClose : true
												});
								isLoaded = true;
								getAddressListData();
								addresslistDiv.show()
							}
						},
						error : function() {
							alert("\u64cd\u4f5c\u5931\u8d25\uff01\u8bf7\u7a0d\u540e\u518d\u8bd5\uff01")
						}
					})
		};
		$.PageDialog
				.showConfirm(
						"\u60a8\u786e\u5b9a\u8981\u5220\u9664\u6b64\u914d\u9001\u5730\u5740\u5417\uff1f",
						d)
	}
	return false
};
