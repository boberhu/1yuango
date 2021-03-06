jQuery.fn.pagination = function(a, b) {
	b = jQuery.extend({
		items_per_page : 10,
		num_display_entries : 10,
		current_page : 0,
		num_edge_entries : 0,
		link_to : "#",
		prev_text : "Prev",
		next_text : "Next",
		ellipse_text : "...",
		prev_show_always : true,
		next_show_always : true,
		callback : function() {
			return false
		}
	}, b || {});
	return this.each(function() {
		function f() {
			return Math.ceil(a / b.items_per_page)
		}
		function h() {
			var k = Math.ceil(b.num_display_entries / 2);
			var l = f();
			var j = l - b.num_display_entries;
			var m = g > k ? Math.max(Math.min(g - k, j), 0) : 0;
			var i = g > k ? Math.min(g + k, l) : Math.min(
					b.num_display_entries, l);
			return [ m, i ]
		}
		function e(j, i) {
			g = j;
			c();
			var k = b.callback(j, d);
			if (!k) {
				if (i.stopPropagation) {
					i.stopPropagation()
				} else {
					i.cancelBubble = true
				}
			}
			return k
		}
		function c() {
			d.empty();
			var k = h();
			var o = f();
			var p = function(i) {
				return function(q) {
					return e(i, q)
				}
			};
			var n = function(i, q) {
				i = i < 0 ? 0 : (i < o ? i : o - 1);
				q = jQuery.extend({
					text : i + 1,
					classes : ""
				}, q || {});
				if (i == g) {
					var r = jQuery("<li class='curr_page'>" + (q.text)
							+ "</li>")
				} else {
					var r = jQuery("<li><a>" + (q.text) + "</a></li>").bind(
							"click", p(i));
					r.find("a").attr("href", "javascript:;")
				}
				if (q.classes) {
					r.addClass(q.classes)
				}
				d.append(r)
			};
			if (b.prev_text && (g > 0 || b.prev_show_always)) {
				n(g - 1, {
					text : b.prev_text,
					classes : "prev_page"
				})
			}
			if (k[0] > 0 && b.num_edge_entries > 0) {
				var j = Math.min(b.num_edge_entries, k[0]);
				for (var l = 0; l < j; l++) {
					n(l)
				}
				if (b.num_edge_entries < k[0] && b.ellipse_text) {
					jQuery("<li>" + b.ellipse_text + "</li>").appendTo(d)
				}
			}
			for (var l = k[0]; l < k[1]; l++) {
				n(l)
			}
			if (k[1] < o && b.num_edge_entries > 0) {
				if (o - b.num_edge_entries > k[1] && b.ellipse_text) {
					jQuery("<li>" + b.ellipse_text + "</li>").appendTo(d)
				}
				var m = Math.max(o - b.num_edge_entries, k[1]);
				for (var l = m; l < o; l++) {
					n(l)
				}
			}
			if (b.next_text && (g < o - 1 || b.next_show_always)) {
				n(g + 1, {
					text : b.next_text,
					classes : "next_page"
				})
			}
		}
		var g = b.current_page;
		a = (!a || a < 0) ? 1 : a;
		b.items_per_page = (!b.items_per_page || b.items_per_page < 0) ? 1
				: b.items_per_page;
		var d = jQuery(this);
		this.selectPage = function(i) {
			e(i)
		};
		this.prevPage = function() {
			if (g > 0) {
				e(g - 1);
				return true
			} else {
				return false
			}
		};
		this.nextPage = function() {
			if (g < f() - 1) {
				e(g + 1);
				return true
			} else {
				return false
			}
		};
		c();
		b.callback(g, this)
	})
};
