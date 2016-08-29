$(document).ready(

function() {
	var a = function() {
			var d = $("#recordList > ul:eq(0)").attr("rel");
			var b = d;
			var c = $("#recordList > ul:eq(0)").attr("class");
			$.ajax({
				url: "/getNewRecordAjax.html",
				data: "id=" + d,
				success: function(h) {
					if (h.length > 0) {
						if (b != h[0].productStyle) {
							var g = "";
							if (c == "Record_content") {
								for (var f = 0; f < h.length; f++) {
									if (f % 2 == 0) {
										g += '<ul class="Record_contents" rel="' + h[f].productStyle + '">';
										g += '<li class="time">' + h[f].buyDate + "</li>";
										g += '<li class="nem"><a href="' + $www + "/u/" + h[f].userId + '.html" target="_blank" class="blue">' + h[f].buyer + "</a></li>";
										g += '<li class="name"><a href="' + $www + "/products/" + h[f].productId + '.html" title="' + h[f].productTitle + '">（第' + h[f].productPeriod + "期）" + h[f].productName + "</a></li>";
										g += '<li class="much">' + h[f].buyCount + "人次</li>";
										g += "</ul>"
									} else {
										g += '<ul class="Record_content" rel="' + h[f].productStyle + '">';
										g += '<li class="time">' + h[f].buyDate + "</li>";
										g += '<li class="nem"><a href="' + $www + "/u/" + h[f].userId + '.html" target="_blank" class="blue">' + h[f].buyer + "</a></li>";
										g += '<li class="name"><a href="' + $www + "/products/" + h[f].productId + '.html" title="' + h[f].productTitle + '">（第' + h[f].productPeriod + "期）" + h[f].productName + "</a></li>";
										g += '<li class="much">' + h[f].buyCount + "人次</li>";
										g += "</ul>"
									}
								}
							} else {
								for (var f = 0; f < h.length; f++) {
									if (f % 2 == 0) {
										g += '<ul class="Record_content" rel="' + h[f].productStyle + '">';
										g += '<li class="time">' + h[f].buyDate + "</li>";
										g += '<li class="nem"><a href="' + $www + "/u/" + h[f].userId + '.html" target="_blank" class="blue">' + h[f].buyer + "</a></li>";
										g += '<li class="name"><a href="' + $www + "/products/" + h[f].productId + '.html" title="' + h[f].productTitle + '">（第' + h[f].productPeriod + "期）" + h[f].productName + "</a></li>";
										g += '<li class="much">' + h[f].buyCount + "人次</li>";
										g += "</ul>"
									} else {
										g += '<ul class="Record_contents" rel="' + h[f].productStyle + '">';
										g += '<li class="time">' + h[f].buyDate + "</li>";
										g += '<li class="nem"><a href="' + $www + "/u/" + h[f].userId + '.html" target="_blank" class="blue">' + h[f].buyer + "</a></li>";
										g += '<li class="name"><a href="' + $www + "/products/" + h[f].productId + '.html" title="' + h[f].productTitle + '">（第' + h[f].productPeriod + "期）" + h[f].productName + "</a></li>";
										g += '<li class="much">' + h[f].buyCount + "人次</li>";
										g += "</ul>"
									}
								}
							}
							$("#recordList").prepend(g);
							for (var e = 0; e < h.length; e++) {
								$("#recordList > ul:last").remove()
							}
						}
					}
				}
			});
			setTimeout(a, 50000)
		};
	a()
});