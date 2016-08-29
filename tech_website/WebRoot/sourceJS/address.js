$(document).ready(function () {
	var userId = $("#userId").val();
	$("#btnAddnewAddr > a").click(function(){
		$(".shipping_address").hide();
		$("#div_ajax_canvas").show();
	});
	$("#addAddressColse").click(function(){
		$(".shipping_address").show();
		$("#div_ajax_canvas").hide();
	});
	$("#addAddressButton").click(function(){
		var address = $(".address > input").val();
		var zip_code = $(".zip_code > input").val();
		var name = $(".name > input").val();
		var tel = $(".tel > input").val();
		var id = $(".do > input").val();
		if(address=="" || zip_code=="" || name=="" || tel==""){
			alert("请填写完整信息!");
			return false;
		}else{
			var url = "/user/addAddress.action?address="+address+"&zip_code="+zip_code+"&name="+name+"&tel="+tel+"&userId="+userId+"&status="+1+"&id="+id;
			url = encodeURI(url);
			$.ajax({
				url:url,
				type:"post",
				data:"String",
				success:function(data){
					if(data=="true"){
						pageDialog.PageDialog("<dl class='sAltOK'><dd>\u64cd\u4f5c\u6210\u529f\uff01</dd></dl>","",160,50,"true","true");
						window.setInterval("location.replace(location.href);",2000);
					}
				},
				error:function(){
					alert("操作失败，请稍后再试！");
				}
			});
		}
	});
	$("a[name=AddressDelete]").click(function(){
		if (confirm("\u786e\u5b9a\u8981\u5220\u9664\u5417?")) {
			var id = $(this).attr("id");
			$.ajax({
				url:"/user/delAddress.action?id="+id,
				type:"post",
				data:"String",
				success:function(data){
					if(data=="true"){
						pageDialog.PageDialog("<dl class='sAltOK'><dd>\u64cd\u4f5c\u6210\u529f\uff01</dd></dl>","",160,50,"true","true");
						window.setInterval("location.replace(location.href);",2000);
					}else{
						alert("删除失败，请稍后再试！");
					}
				}
			});
		}
	});
	$(".default > a").click(function(){
		var id = $(this).attr("id");
		var url = "/user/addAddress.action?id="+id+"&userId="+userId;
		$.ajax({
			url:url,
			type:"post",
			data:"String",
			success:function(data){
				if(data=="true"){
					pageDialog.PageDialog("<dl class='sAltOK'><dd>\u8bbe\u7f6e\u6210\u529f\uff01</dd></dl>","",160,50,"true","true");
					window.setInterval("location.replace(location.href);",2000);
				}
			},
			error:function(){
				alert("操作失败，请稍后再试！");
			}
		});
	});
	$("ul > li > a[name=updateAddress]").click(function(){
		var address = $(this).parent().siblings("li").eq(0).text(); 
		var zip_code = $(this).parent().siblings("li").eq(1).text(); 
		var name = $(this).parent().siblings("li").eq(2).text(); 
		var tel = $(this).parent().siblings("li").eq(3).text(); 
		var id = $(this).attr("id");
		$(".HackBox > ul > li:eq(0) > input").val(address);
		$(".HackBox > ul > li:eq(1) > input").val(zip_code);
		$(".HackBox > ul > li:eq(2) > input").val(name);
		$(".HackBox > ul > li:eq(3) > input").val(tel);
		$(".HackBox > ul > li:eq(5) > input").val(id);
		$(".shipping_address").hide();
		$("#div_ajax_canvas").show();
	});
});

