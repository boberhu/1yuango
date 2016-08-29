$(function () {
	$(".hov").hide();
	$(".chanelL").hover(function () {
		$(".hov").show();
	}, function () {
		$(".hov").hide();
	});
	$("#UpdatePho").hover(function () {
		$(this).attr("color", "#FFF");
	}, function () {
		$(this).attr("color", "#FF6600");
	});
	$(".scrollLoading").scrollLoading();
});

