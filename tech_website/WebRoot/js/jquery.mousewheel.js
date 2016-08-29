/*! Copyright (c) 2011 Brandon Aaron (http://brandonaaron.net)
 * Licensed under the MIT License (LICENSE.txt).
 *
 * Thanks to: http://adomas.org/javascript-mouse-wheel/ for some pointers.
 * Thanks to: Mathias Bank(http://www.mathias-bank.de) for a scope bug fix.
 * Thanks to: Seamus Leahy for adding deltaX and deltaY
 *
 * Version: 3.0.6
 * 
 * Requires: 1.2.2+
 */
(function(c){function b(g){var q=g||window.event,e=[].slice.call(arguments,1),f=0,h=!0,o=0,p=0;return g=c.event.fix(q),g.type="mousewheel",q.wheelDelta&&(f=q.wheelDelta/120),q.detail&&(f=-q.detail/3),p=f,q.axis!==undefined&&q.axis===q.HORIZONTAL_AXIS&&(p=0,o=-1*f),q.wheelDeltaY!==undefined&&(p=q.wheelDeltaY/120),q.wheelDeltaX!==undefined&&(o=-1*q.wheelDeltaX/120),e.unshift(g,f,o,p),(c.event.dispatch||c.event.handle).apply(this,e)}var d=["DOMMouseScroll","mousewheel"];if(c.event.fixHooks){for(var a=d.length;a;){c.event.fixHooks[d[--a]]=c.event.mouseHooks}}c.event.special.mousewheel={setup:function(){if(this.addEventListener){for(var e=d.length;e;){this.addEventListener(d[--e],b,!1)}}else{this.onmousewheel=b}},teardown:function(){if(this.removeEventListener){for(var e=d.length;e;){this.removeEventListener(d[--e],b,!1)}}else{this.onmousewheel=null}}},c.fn.extend({mousewheel:function(e){return e?this.bind("mousewheel",e):this.trigger("mousewheel")},unmousewheel:function(e){return this.unbind("mousewheel",e)}})})(jQuery);
