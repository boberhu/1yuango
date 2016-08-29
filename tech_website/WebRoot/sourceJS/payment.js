$(function () {
	var G = parseInt($("#hidCountMoney").val());
	var J = parseInt($("#hidBalance").val());
	var B = 0;
	if (isNaN(G)) {
		alert("\u8d2d\u7269\u8f66\u4e2d\u6682\u65f6\u6ca1\u6709\u5546\u54c1\!\u9a6c\u4e0a\u53bb\u62cd\u8d2d\u5427 >>");
		location.replace($www);
	}
	var E = "";
	var H = $("#liPayAlt1");
	var F = $("#liPayAlt2");
	var C = "<p class=\"cz\">\u60a8\u7684\u8d26\u6237\u4f59\u989d\u4e0d\u8db3\u4ee5\u652f\u4ed8\u672c\u6b21\u8ba2\u5355<a href=\""+$passport+"/user/UserBalance.html\" target=\"_blank\"><u>\u8d26\u6237\u5145\u503c</u></a></p>";
	if (J > 0 && J >= G) {
		B = 1;
		E = "<input id=\"ckBalance\" type=\"checkbox\" name=\"xz\" class=\"xz\" /><label for=\"ckBalance\">\u4f7f\u7528\u8d26\u6237\u4f59\u989d\u652f\u4ed8\uff1a\uffe5<span class=\"pay\">" + G + ".00</label></span>";
	} else {
		if (J > 0) {
			B = 2;
			H.append(C);
			E = "<input id=\"ckBalance\" type=\"checkbox\" name=\"xz\" class=\"xz\" /><label for=\"ckBalance\">\u4f7f\u7528\u8d26\u6237\u4f59\u989d\u652f\u4ed8\uff1a\uffe5<span class=\"pay\">" + J + ".00</span>\uff0c\u5269\u4f59\uff1a\uffe5<span class=\"oth\">" + (G - J) + ".00</span>\u53ef\u4ee5\u9009\u62e9\u4ee5\u4e0b\u652f\u4ed8\u65b9\u5f0f\u5b8c\u6210\u652f\u4ed8</label>";
		} else {
			H.append(C);
			E = "<span class='s'></span>\u60a8\u7684\u4f59\u989d\u4e0d\u8db3,\u8bf7\u9009\u62e9\u4ee5\u4e0b\u652f\u4ed8\u65b9\u5f0f\u5b8c\u6210\u652f\u4ed8";
		}
	}
	F.append(E);
	var A = $("#submit_ok");
	var I = "";
	var K = function () {
		var content = "<div id=\"payAltBox\"><div class=\"payment_ts\"><p><span></span>付款完成之前请不要关闭此窗口</p><p class=\"bottom\">完成付款后跟据您的情况进行以下操作</p><ul><li>付款成功：</li><li><a href=\""+$passport+"/user/ConsumeList.html\">查看消费记录&gt;&gt;</a></li><li class=\"title\">付款失败：</li><li><a id=\"btnReSelect\" href=\"javascript:gotoClick();\">返回重新选择>></a></li></ul></div></div>";
		pageDialog.PageDialog(content,"支付提醒",405,180,"true","false");
	};
	$("#btnReSelect").live("click", function () {
		$("#pageDialog").hide();
		$("#pageDialogBG").hide();
		$("#pageDialogBorder").hide();
	});
	var D = function (L) {
		if (I == "") {
			return false;
		}
		if (/^\d{4}$/.test(I)) {
			$("#hidPayName").val("Tenpay");
			$("#hidPayBank").val(I);
		} else {
			$("#hidPayName").val(I);
			$("#hidPayBank").val("0");
		}
		$("#hidUseBalance").val(L); 
		K();
	};
	$("#ckBalance").click(function () {
		if (B == 1) {
			$("#other").toggle();
		} else {
			if (B == 2) {
				if ($(this).attr("checked")) {
					$("#yue").html((G - J) + ".00");
				} else {
					$("#yue").html(G + ".00");
				}
			}
		}
	});
	A.bind("click", function () {
		I = "";
		$("input[name=account]").each(function () {
			if ($(this).attr("checked")) {
				I = $(this).val();
			}
		});
		if (I == "") {
			alert("\u8bf7\u9009\u62e9\u652f\u4ed8\u65b9\u5f0f\uff01");
			return false;
		}
		var money = $("#yue").html();
		money = parseInt(money);
		$("#moneyCount").val(money);
		if (B > 0) {
			if ($("#ckBalance").attr("checked")) {
				if (J >= G) {
					if (confirm("\u60a8\u786e\u5b9a\u4f7f\u7528\u8d26\u6237\u4f59\u989d\u652f\u4ed8\u5417?")) {
						$.ajax({
							url:"/mycart/goPay.html?moneyCount="+money,
							type:"post",
							data:"String",
							success:function(data){
								if(data=="success"){
									pageDialog.PageDialog("<dl class=\"sAltOK\"><dd>支付成功!</dd></dl>","",160,50,"true","true");
									window.setInterval("location.replace('"+$passport+"/user/ConsumeList.html');",2000);
								}else{
									alert("购物车中有商品已经满员，请选择该商品的下一期夺宝！");
									location.replace($www+"/mycart/index.html");
								}
							}
						});
					}
					return false;
				} else {
					D(J);
				}
			} else {
				D(0);
			}
		} else {
			D(0);
		}
		return true;
	});
});

