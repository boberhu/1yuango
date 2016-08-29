function mycarousel() {
	$.fn.jcarousel = function (M) {
		var E = {itemWidth:58, showNum:4, initIndex:1, initCallback:function () {
		}};
		M = M || {};
		$.extend(E, M);
		var D = this;
		var C = 0;
		var G = 0;
		var I = E.showNum * E.itemWidth;
		var H = new J(this.parent().prev());
		H.setDisabled();
		var F = new L(this.parent().next());
		F.setDisabled();
		var B = $("li", this).length;
		G = B * E.itemWidth;
		D.width(G).css("left", C + "px");
		if (E.initIndex > E.showNum) {
			C = (E.initIndex - E.showNum) * itemWidth;
			D.css("left", C + "px");
		}
		if (C < 0) {
			H.setEnabled();
		}
		if (I - C < G) {
			F.setEnabled();
		}
		D.show("fast", E.initCallback);
		function J(N) {
			this.btn = N;
			this.setDisabled = function () {
				this.btn.attr("class", "jcarousel-prev jcarousel-prev-disabled").unbind();
			};
			this.setEnabled = function () {
				this.btn.attr("class", "jcarousel-prev").unbind().bind("click", function () {
					if (C + E.itemWidth <= 0) {
						C += E.itemWidth;
						D.animate({left:C + "px"}, "fast", function () {
							K();
						});
					}
				});
			};
		}
		function L(N) {
			this.btn = N;
			this.setDisabled = function () {
				this.btn.attr("class", "jcarousel-next jcarousel-next-disabled").unbind();
			};
			this.setEnabled = function () {
				this.btn.attr("class", "jcarousel-next").unbind().bind("click", function () {
					if ((C - E.itemWidth + G) >= I) {
						C -= E.itemWidth;
						D.animate({left:C + "px"}, "fast", function () {
							K();
						});
					}
				});
			};
		}
		function K() {
			if (C < 0) {
				H.setEnabled();
			} else {
				H.setDisabled();
			}
			if (C + G > I) {
				F.setEnabled();
			} else {
				F.setDisabled();
			}
		}
	};
	$("#mycarousel").jcarousel();
}