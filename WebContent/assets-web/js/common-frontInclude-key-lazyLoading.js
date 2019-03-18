/*! jQuery Migrate v1.2.1 | (c) 2005, 2013 jQuery Foundation, Inc. and other contributors | jquery.org/license */
var scrollMarkValue;

jQuery.migrateMute === void 0 && (jQuery.migrateMute = !0),
function(e, t, n) {
  function r(n) {
    var r = t.console;
    i[n] || (i[n] = !0, e.migrateWarnings.push(n), r && r.warn && !e.migrateMute && (r.warn("JQMIGRATE: " + n), e.migrateTrace && r.trace && r.trace()))
  }
  function a(t, a, i, o) {
    if (Object.defineProperty) try {
      return Object.defineProperty(t, a, {
        configurable: !0,
        enumerable: !0,
        get: function() {
          return r(o),
          i
        },
        set: function(e) {
          r(o),
          i = e
        }
      }),
      n
    } catch(s) {}
    e._definePropertyBroken = !0,
    t[a] = i
  }
  var i = {};
  e.migrateWarnings = [],
  !e.migrateMute && t.console && t.console.log && t.console.log("JQMIGRATE: Logging is active"),
  e.migrateTrace === n && (e.migrateTrace = !0),
  e.migrateReset = function() {
    i = {},
    e.migrateWarnings.length = 0
  },
  "BackCompat" === document.compatMode && r("jQuery is not compatible with Quirks Mode");
  var o = e("<input/>", {
    size: 1
  }).attr("size") && e.attrFn,
  s = e.attr,
  u = e.attrHooks.value && e.attrHooks.value.get ||
  function() {
    return null
  },
  c = e.attrHooks.value && e.attrHooks.value.set ||
  function() {
    return n
  },
  l = /^(?:input|button)$/i,
  d = /^[238]$/,
  p = /^(?:autofocus|autoplay|async|checked|controls|defer|disabled|hidden|loop|multiple|open|readonly|required|scoped|selected)$/i,
  f = /^(?:checked|selected)$/i;
  a(e, "attrFn", o || {},
  "jQuery.attrFn is deprecated"),
  e.attr = function(t, a, i, u) {
    var c = a.toLowerCase(),
    g = t && t.nodeType;
    return u && (4 > s.length && r("jQuery.fn.attr( props, pass ) is deprecated"), t && !d.test(g) && (o ? a in o: e.isFunction(e.fn[a]))) ? e(t)[a](i) : ("type" === a && i !== n && l.test(t.nodeName) && t.parentNode && r("Can't change the 'type' of an input or button in IE 6/7/8"), !e.attrHooks[c] && p.test(c) && (e.attrHooks[c] = {
      get: function(t, r) {
        var a, i = e.prop(t, r);
        return i === !0 || "boolean" != typeof i && (a = t.getAttributeNode(r)) && a.nodeValue !== !1 ? r.toLowerCase() : n
      },
      set: function(t, n, r) {
        var a;
        return n === !1 ? e.removeAttr(t, r) : (a = e.propFix[r] || r, a in t && (t[a] = !0), t.setAttribute(r, r.toLowerCase())),
        r
      }
    },
    f.test(c) && r("jQuery.fn.attr('" + c + "') may use property instead of attribute")), s.call(e, t, a, i))
  },
  e.attrHooks.value = {
    get: function(e, t) {
      var n = (e.nodeName || "").toLowerCase();
      return "button" === n ? u.apply(this, arguments) : ("input" !== n && "option" !== n && r("jQuery.fn.attr('value') no longer gets properties"), t in e ? e.value: null)
    },
    set: function(e, t) {
      var a = (e.nodeName || "").toLowerCase();
      return "button" === a ? c.apply(this, arguments) : ("input" !== a && "option" !== a && r("jQuery.fn.attr('value', val) no longer sets properties"), e.value = t, n)
    }
  };
  var g, h, v = e.fn.init,
  m = e.parseJSON,
  y = /^([^<]*)(<[\w\W]+>)([^>]*)$/;
  e.fn.init = function(t, n, a) {
    var i;
    return t && "string" == typeof t && !e.isPlainObject(n) && (i = y.exec(e.trim(t))) && i[0] && ("<" !== t.charAt(0) && r("$(html) HTML strings must start with '<' character"), i[3] && r("$(html) HTML text after last tag is ignored"), "#" === i[0].charAt(0) && (r("HTML string cannot start with a '#' character"), e.error("JQMIGRATE: Invalid selector string (XSS)")), n && n.context && (n = n.context), e.parseHTML) ? v.call(this, e.parseHTML(i[2], n, !0), n, a) : v.apply(this, arguments)
  },
  e.fn.init.prototype = e.fn,
  e.parseJSON = function(e) {
    return e || null === e ? m.apply(this, arguments) : (r("jQuery.parseJSON requires a valid JSON string"), null)
  },
  e.uaMatch = function(e) {
    e = e.toLowerCase();
    var t = /(chrome)[ \/]([\w.]+)/.exec(e) || /(webkit)[ \/]([\w.]+)/.exec(e) || /(opera)(?:.*version|)[ \/]([\w.]+)/.exec(e) || /(msie) ([\w.]+)/.exec(e) || 0 > e.indexOf("compatible") && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(e) || [];
    return {
      browser: t[1] || "",
      version: t[2] || "0"
    }
  },
  e.browser || (g = e.uaMatch(navigator.userAgent), h = {},
  g.browser && (h[g.browser] = !0, h.version = g.version), h.chrome ? h.webkit = !0 : h.webkit && (h.safari = !0), e.browser = h),
  a(e, "browser", e.browser, "jQuery.browser is deprecated"),
  e.sub = function() {
    function t(e, n) {
      return new t.fn.init(e, n)
    }
    e.extend(!0, t, this),
    t.superclass = this,
    t.fn = t.prototype = this(),
    t.fn.constructor = t,
    t.sub = this.sub,
    t.fn.init = function(r, a) {
      return a && a instanceof e && !(a instanceof t) && (a = t(a)),
      e.fn.init.call(this, r, a, n)
    },
    t.fn.init.prototype = t.fn;
    var n = t(document);
    return r("jQuery.sub() is deprecated"),
    t
  },
  e.ajaxSetup({
    converters: {
      "text json": e.parseJSON
    }
  });
  var b = e.fn.data;
  e.fn.data = function(t) {
    var a, i, o = this[0];
    return ! o || "events" !== t || 1 !== arguments.length || (a = e.data(o, t), i = e._data(o, t), a !== n && a !== i || i === n) ? b.apply(this, arguments) : (r("Use of jQuery.fn.data('events') is deprecated"), i)
  };
  var j = /\/(java|ecma)script/i,
  w = e.fn.andSelf || e.fn.addBack;
  e.fn.andSelf = function() {
    return r("jQuery.fn.andSelf() replaced by jQuery.fn.addBack()"),
    w.apply(this, arguments)
  },
  e.clean || (e.clean = function(t, a, i, o) {
    a = a || document,
    a = !a.nodeType && a[0] || a,
    a = a.ownerDocument || a,
    r("jQuery.clean() is deprecated");
    var s, u, c, l, d = [];
    if (e.merge(d, e.buildFragment(t, a).childNodes), i) for (c = function(e) {
      return ! e.type || j.test(e.type) ? o ? o.push(e.parentNode ? e.parentNode.removeChild(e) : e) : i.appendChild(e) : n
    },
    s = 0; null != (u = d[s]); s++) e.nodeName(u, "script") && c(u) || (i.appendChild(u), u.getElementsByTagName !== n && (l = e.grep(e.merge([], u.getElementsByTagName("script")), c), d.splice.apply(d, [s + 1, 0].concat(l)), s += l.length));
    return d
  });
  var Q = e.event.add,
  x = e.event.remove,
  k = e.event.trigger,
  N = e.fn.toggle,
  T = e.fn.live,
  M = e.fn.die,
  S = "ajaxStart|ajaxStop|ajaxSend|ajaxComplete|ajaxError|ajaxSuccess",
  C = RegExp("\\b(?:" + S + ")\\b"),
  H = /(?:^|\s)hover(\.\S+|)\b/,
  A = function(t) {
    return "string" != typeof t || e.event.special.hover ? t: (H.test(t) && r("'hover' pseudo-event is deprecated, use 'mouseenter mouseleave'"), t && t.replace(H, "mouseenter$1 mouseleave$1"))
  };
  e.event.props && "attrChange" !== e.event.props[0] && e.event.props.unshift("attrChange", "attrName", "relatedNode", "srcElement"),
  e.event.dispatch && a(e.event, "handle", e.event.dispatch, "jQuery.event.handle is undocumented and deprecated"),
  e.event.add = function(e, t, n, a, i) {
    e !== document && C.test(t) && r("AJAX events should be attached to document: " + t),
    Q.call(this, e, A(t || ""), n, a, i)
  },
  e.event.remove = function(e, t, n, r, a) {
    x.call(this, e, A(t) || "", n, r, a)
  },
  e.fn.error = function() {
    var e = Array.prototype.slice.call(arguments, 0);
    return r("jQuery.fn.error() is deprecated"),
    e.splice(0, 0, "error"),
    arguments.length ? this.bind.apply(this, e) : (this.triggerHandler.apply(this, e), this)
  },
  e.fn.toggle = function(t, n) {
    if (!e.isFunction(t) || !e.isFunction(n)) return N.apply(this, arguments);
    r("jQuery.fn.toggle(handler, handler...) is deprecated");
    var a = arguments,
    i = t.guid || e.guid++,
    o = 0,
    s = function(n) {
      var r = (e._data(this, "lastToggle" + t.guid) || 0) % o;
      return e._data(this, "lastToggle" + t.guid, r + 1),
      n.preventDefault(),
      a[r].apply(this, arguments) || !1
    };
    for (s.guid = i; a.length > o;) a[o++].guid = i;
    return this.click(s)
  },
  e.fn.live = function(t, n, a) {
    return r("jQuery.fn.live() is deprecated"),
    T ? T.apply(this, arguments) : (e(this.context).on(t, this.selector, n, a), this)
  },
  e.fn.die = function(t, n) {
    return r("jQuery.fn.die() is deprecated"),
    M ? M.apply(this, arguments) : (e(this.context).off(t, this.selector || "**", n), this)
  },
  e.event.trigger = function(e, t, n, a) {
    return n || C.test(e) || r("Global events are undocumented and deprecated"),
    k.call(this, e, t, n || document, a)
  },
  e.each(S.split("|"),
  function(t, n) {
    e.event.special[n] = {
      setup: function() {
        var t = this;
        return t !== document && (e.event.add(document, n + "." + e.guid,
        function() {
          e.event.trigger(n, null, t, !0)
        }), e._data(this, n, e.guid++)),
        !1
      },
      teardown: function() {
        return this !== document && e.event.remove(document, n + "." + e._data(this, n)),
        !1
      }
    }
  })
} (jQuery, window);
/*!
 * IE10 viewport hack for Surface/desktop Windows 8 bug
 * Copyright 2014 Twitter, Inc.
 * Licensed under the Creative Commons Attribution 3.0 Unported License. For
 * details, see http://creativecommons.org/licenses/by/3.0/.
 */
(function() {
  'use strict';
  if (navigator.userAgent.match(/IEMobile\/10\.0/)) {
    var msViewportStyle = document.createElement('style');
	msViewportStyle.appendChild(document.createTextNode('@-ms-viewport{width:auto!important}'));
	 document.querySelector('head').appendChild(msViewportStyle)
  }
})();
jQuery(document).ready(function($) {
  $('#custom-post-query-submit').on('click',
  function(e) {
    e.preventDefault();
    var filter = $('#filter-by-date').val();
    document.location.href = 'edit.php?post_type=destination&page=destination-settings&tab=master-pages&m=' + filter;
  });
});
jQuery(document).ready(function($) {
  $catSelect = $("select[name='category_id']");
  if ($catSelect.length) {
    $catSelect.change(function() {
      cat = $(this).val();
      $('.travel-dir-rating').hide();
      $('.travel-dir-' + cat).show();
    });
    $catSelect.trigger('change');
  }
});
jQuery(document).ready(function($) {
  var data = {};
  var opts = {};
  var elems = null;
  $.fn.raterater = function(options) {
    $.fn.raterater.defaults = {
      submitFunction: 'submitRating',
      allowChange: false,
      starWidth: 20,
      spaceWidth: 5,
      numStars: 5
    };
    opts = $.extend({},
    $.fn.raterater.defaults, options);
    opts.width = opts.numStars * (opts.starWidth + opts.spaceWidth);
    opts.starAspect = 0.9226;
    elems = this;
    var value = $('#rating-star').val();
    rateraterInit();
    rateraterInitializePositions();
    return this;
  }
  var isFront = $('input.rating-is-front').val();
  $('.ratebox').raterater({
    submitFunction: 'rateAlert',
    allowChange: true,
    starWidth: (isFront == 'false') ? 20 : 13,
    spaceWidth: 5,
    numStars: 5
  });
  function rateraterInit() {
    elems.each(function() {
      var $this = $(this);
      var id = dataId($this);
      if (!id) {
        throw "Error: Each raterater element needs a unique data-id attribute.";
      }
      var key = $(this).attr('data-id');
      var val = (isFront == 'false') ? $('#rating-' + key).val() : $(this).parent().find('input[name="rating-types_' + key + '"]').val();
      $(this).attr('data-rating', val);
      data[id] = {
        state: 'inactive',
      };
      if ($this.css('position') === 'static') $this.css('position', 'relative');
      $this.addClass('raterater-wrapper');
      $this.html('');
      $.each(['bg', 'hover', 'rating', 'outline', 'cover'],
      function() {
        $this.append(' <div class="raterater-layer raterater-' + this + '-layer"></div>');
      });
      var rating_class = (isFront == 'false') ? $(this).parent().next().find('.rate-class').val() : $(this).parent().find('.rate-class').val();

      var rating_color = (isFront == 'false') ? $(this).parent().next().find('.rate-color').val() : $(this).parent().find('.rate-color').val();
      for (var i = 0; i < opts.numStars; i++) {
        $this.children('.raterater-bg-layer').first().append('<i class="' + rating_class + '"></i>');
        $this.children('.raterater-outline-layer').first().append('<i class="' + rating_class + '"></i>');
        $this.children('.raterater-hover-layer').first().append('<i class="' + rating_class + '"></i>');
        $this.children('.raterater-rating-layer').first().append('<i class="' + rating_class + '"></i>');
      }
      $('.raterater-hover-layer i.' + rating_class.substring(3) + ', .raterater-rating-layer i.' + rating_class.substring(3)).css({
        'color': rating_color
      });
      $this.find('.raterater-cover-layer').hover(mouseEnter, mouseLeave);
      $this.find('.raterater-cover-layer').mousemove(hiliteStarsHover);
      $this.find('.raterater-cover-layer').click(rate);
    });
  }
  function rateraterInitializePositions() {
    elems.each(function() {
      var $this = $(this);
      var id = dataId($this);
      var width = opts.width + 'px';
      var height = Math.floor(opts.starWidth / opts.starAspect) + 'px';
      $this.css('width', width).css('height', height);
      $this.find('.raterater-layer').each(function() {
        $(this).css('width', width).css('height', height);
      });
      for (var i = 0; i < opts.numStars; i++) {
        $.each(['bg', 'hover', 'rating', 'outline'],
        function() {
          $this.children('.raterater-' + this + '-layer').first().children('i').eq(i).css('left', i * (opts.starWidth + opts.spaceWidth) + 'px').css('font-size', Math.floor(opts.starWidth / opts.starAspect) + 'px');
        });
      }
      var rating = parseFloat($this.attr('data-rating'));
      var whole = Math.floor(rating);
      var partial = rating - whole;
      hiliteStars($this.find('.raterater-rating-layer').first(), whole, partial);
    });
  }
  function rate(e) {
    var $this = $(e.target).parent();
    var id = dataId($this);
    var stars = data[id].whole_stars_hover + data[id].partial_star_hover;
    stars = Math.round(stars * 100) / 100;
    data[id].state = 'rated';
    $this.attr('data-rating', stars);
    $this.parent().next().find('.rate-manual-input').val(stars);
    $this.find('.raterater-hover-layer').addClass('rated');
    if (typeof window[opts.submitFunction] === 'function') window[opts.submitFunction](id, stars);
  }
  function calculateStars(x, id) {
    var whole_stars = Math.floor(x / (opts.starWidth + opts.spaceWidth));
    var partial_star = x - whole_stars * (opts.starWidth + opts.spaceWidth);
    if (partial_star > opts.starWidth) partial_star = opts.starWidth;
    partial_star /= opts.starWidth;
    data[id].whole_stars_hover = whole_stars;
    data[id].partial_star_hover = partial_star;
  }
  function hiliteStars($layer, whole, partial) {
    var id = dataId($layer.parent());
    for (var i = 0; i < whole; i++) {
      $layer.find('i').eq(i).css('width', opts.starWidth + 'px');
    }
    $layer.find('i').eq(whole).css('width', opts.starWidth * partial + 'px');
    for (var i = whole + 1; i < opts.numStars; i++) {
      $layer.find('i').eq(i).css('width', '0px');
    }
  }
  function hiliteStarsHover(e) {
    var id = dataId($(e.target).parent());
    if (data[id].state !== 'hover') {
      return;
    }
    var x = e.offsetX;
    if (x === undefined) {
      x = e.pageX - $(e.target).offset().left;
    }
    data[id].stars = calculateStars(x, id);
    var $layer = $(e.target).parent().children('.raterater-hover-layer').first();
    hiliteStars($layer, data[id].whole_stars_hover, data[id].partial_star_hover);
  }
  function mouseEnter(e) {
    if (isFront == 'true') return;
    var id = dataId($(e.target).parent());
    if (data[id].state === 'rated' && !opts.allowChange) {}
    data[id].state = 'hover';
    $(e.target).parent().children('.raterater-rating-layer').first().css('display', 'none');
    $(e.target).parent().children('.raterater-hover-layer').first().css('display', 'block');
  }
  function mouseLeave(e) {
    var id = dataId($(e.target).parent());
    if (data[id].state === 'rated') {
      return;
    }
    data[id].state = 'inactive';
    rateraterInitializePositions();
    $(e.target).parent().children('.raterater-hover-layer').first().css('display', 'none');
    $(e.target).parent().children('.raterater-rating-layer').first().css('display', 'block');
  }
  function dataId(e) {
    return $(e).attr('data-id');
  }
  $('.rate-manual-input').on('change',
  function(e) {
    e.preventDefault();
    $(this).parent().prev().find('.ratebox').attr('data-rating', $(this).val());
    rateraterInitializePositions();
  });
  $('.add_custom_contact').on('click',
  function(e) {
    e.preventDefault();
    var contacts_len = $('.details-contacts-last-number').val();
    var custom_contact = $('.contact-extra-template').clone(true);
    var new_contact = setCustomContactAttrs(custom_contact, contacts_len);
    $('.contact-extra-description').before(new_contact);
    $('.details-contacts-last-number').val(parseInt(contacts_len) + 1);
  });
  function setCustomContactAttrs(el, len) {
    el.find('.details-contacts-name').attr({
      id: 'contact_name[' + len + ']',
      name: 'contact_name[' + len + ']'
    });
    el.find('.details-contacts-value').attr({
      id: 'contact_value[' + len + ']',
      name: 'contact_value[' + len + ']'
    });
    el.removeClass('contact-extra-template');
    el.addClass('details-contacts');
    el.show();
    return el;
  }
  $('.remove_custom_other, .remove_custom_contact').on('click',
  function(e) {
    e.preventDefault();
    $(this).parent().parent().remove();
  });
  $('.add_custom_other').on('click',
  function(e) {
    e.preventDefault();
    var other_len = $('.details-other-last-number').val();
    var custom_other = $('.other-extra-template').clone(true);
    var new_other = setCustomOtherAttrs(custom_other, other_len);
    $('.other-extra-description').before(new_other);
    $('.details-other-last-number').val(parseInt(other_len) + 1);
  });
  function setCustomOtherAttrs(el, len) {
    el.find('.details-other-name').attr({
      id: 'other_name[' + len + ']',
      name: 'other_name[' + len + ']'
    });
    el.find('.details-other-value').attr({
      id: 'other_value[' + len + ']',
      name: 'other_value[' + len + ']'
    });
    el.removeClass('other-extra-template');
    el.addClass('details-other');
    el.show();
    return el;
  }
});
jQuery(document).ready(function($) {
  $(document).bind('shrink-menu-init',
  function(e, status) {
    var topBarHeight = $('.navbar-extra-top').outerHeight();
    scrollMark = Math.max(topBarHeight, 30);
    style = ".menu-shrink {top : -" + topBarHeight + "px !important;}";
	scrollMarkValue = scrollMark;
	$("<style></style>").attr('id', 'ShrinkMenu').data('scrollMark', scrollMark).appendTo("head");
//    if (!$('#ShrinkMenu').length) {
//      $("<style></style>").attr('id', 'ShrinkMenu').data('scrollMark', scrollMark).appendTo("head");
//    }
    $('#ShrinkMenu').html(style);
  });
  $(document).bind('shrink-menu',function(e, status) {
    scrollMark = scrollMarkValue;
	if ($(this).scrollTop() < scrollMark) {
	  $('#MainMenu').removeClass('scrolled menu-shrink');
	} else {
	  $('#MainMenu').addClass('scrolled menu-shrink');
		}
  });
 
  $(window).resize(function() {
    $(document).trigger('shrink-menu-init');
  });
  $(window).scroll(function() {
    $(document).trigger('shrink-menu');
  });
  if ($('#SubMenu').length) {
    $('#SubMenu').affix({
      offset: {
        top: function() {
          return $('#SubMenu').parent().offset().top - $('#navbar-main-container').outerHeight();
        },
      }
    }).css('top', $('#navbar-main-container').outerHeight());
    $(window).resize(function() {
      theTop = $('#SubMenu').parent().offset().top - $('#navbar-main-container').outerHeight();
      $('#SubMenu').data('bs.affix').options.offset = {
        top: theTop
      };
    });
  }
  if ($('.panel-heading').length) {
    $('.panel-heading').on('click',
    function(e) {
      if ($(this).parents('.panel').children('.panel-collapse').hasClass('in')) {
        e.preventDefault();
        e.stopPropagation();
      }
    });
  }
  $('[data-toggle="tooltip"]').tooltip({
    placement: function(tip, trigger) {
      return ($(trigger).parents('#SubMenu.affix').length) ? 'bottom': 'auto top';
    }
  });
  $('[data-toggle="popover"]').popover();
  $('.nav-previous > a, .nav-next > a').popover({
    html: true,
    placement: 'top',
    trigger: 'hover',
    delay: {
      "show": 500,
      "hide": 100
    },
    title: function() {
      return $(this).find('.meta-nav-title').html();
    },
    content: function() {
      var img = $('<img class="placeholder" width="600" height="800" style="visibility:hidden" >');
      img.attr('src', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAICAMAAADtGH4KAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDE0IDc5LjE1Njc5NywgMjAxNC8wOC8yMC0wOTo1MzowMiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjMxQjk3NTA3RjJGRDExRTRCNjk0Rjg0QjlEODkzNDkxIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjMxQjk3NTA4RjJGRDExRTRCNjk0Rjg0QjlEODkzNDkxIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MzFCOTc1MDVGMkZEMTFFNEI2OTRGODRCOUQ4OTM0OTEiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MzFCOTc1MDZGMkZEMTFFNEI2OTRGODRCOUQ4OTM0OTEiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz6NsDE8AAAABlBMVEXp6usAAAD3NurCAAAADklEQVR42mJgIA8ABBgAADgAAYdNUgcAAAAASUVORK5CYII=');
      return img;
    },
    template: '<div class="popover post-nav-popover" role="tooltip"><div class="arrow"></div><div class="popover-content"></div><h3 class="popover-title"></h3></div>'
  });
  $('.nav-previous > a, .nav-next > a').on('shown.bs.popover',
  function(e) {
    id = $(this).attr('aria-describedby');
    $('#' + id).css({
      'background-image': 'url(' + $(this).find('.meta-nav-img').text() + ')'
    });
  }) ;
  if ($('.featured-carousel').length) {
    $(".featured-carousel").owlCarousel({
      items: 1,
      loop: true,
      autoplay: true,
      autoplayHoverPause: true,
      autoplayTimeout: 3800,
      autoplaySpeed: 800,
      navSpeed: 500,
      dots: false,
      nav: true,
      navText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>']
    });
  }
  collapseSize = 1299;
  $('.navbar-nav a').click(function(e) {
    $this = $(e.target);
    href = $this.attr('href');
    if (href === undefined || !href.length || href === '#' || href === 'javascript:;') {
      href = false;
    }
    if ($this.hasClass('dropdown-toggle')) {
      if ($(window).width() > collapseSize) {
        if (href) {
          if (e.which !== 2) {
            window.location.href = href;
          }
        }
      } else if ($this.parent().hasClass('open') && href !== false) {
        $(document).trigger('collapse-menus');
        window.location.href = href;
      }
    } else {
      $(document).trigger('collapse-menus');
    }
  });
  $(document).on('show.bs.dropdown',
  function(obj) {
    if ($(window).width() <= collapseSize) {
      $(obj.target).parents('.show-on-hover').addClass('open');
    }
  });
  $('.navbar a:not(.dropdown-toggle)').click(function(e) {
    $this = $(e.target);
    href = $this.attr('href');
    if (href === undefined || !href.length || href === '#' || href === 'javascript:;') {
      href = false;
    }
    if ($(window).width() > collapseSize) {
      if (href) {
        if (e.which !== 2) {
          window.location.href = href;
        }
      }
    } else if ($this.parent().hasClass('open') && href !== false) {
      $(document).trigger('collapse-menus');
      window.location.href = href;
    }
  });
  $(document).on('collapse-menus',
  function() {
    $('.collapse.in').removeClass('in').children().removeClass('open');
  });
  $('.navbar-nav > li.show-on-hover').hover(function() {
    if ($(window).width() > collapseSize) {
      $(this).addClass('open');
    }
  },
  function() {
    if ($(window).width() > collapseSize) {
      $(this).removeClass('open');
    }
  });
  $scrollTop = $("a.scrollTop, .scrollTop a, a[href='#top']");
  $scrollTop.on("click",
  function(event) {
    event.preventDefault();
    $('html,body').stop().animate({
      scrollTop: 0
    },
    1000);
  });
  $(".entry-content, .video-container").fitVids();
  mapTransition = {
    'height': '100%',
    'transition': 'height .45s ease-out',
    '-webkit-transition': 'height .45s ease-out',
    '-moz-transition': 'height .45s ease-out'
  };
  jQuery('#HeaderMapToggle').click(function(e) {
    e.preventDefault();
    $(this).blur();
    $hero = jQuery('section.hero');
    $map = jQuery('#gmap_wrapper');
    mapZindex = $map.css('z-index');
    if (parseInt(mapZindex) < 0) {
      $map.css({
        'z-index': 0,
        'height': '0px'
      });
    } else {
      $map.css(mapTransition);
    }
    mapHeight = $map.css('height');
    if ($hero.hasClass('mapOn')) {
      $map.css({
        'height': '0px',
      });
      $hero.addClass('mapOff').removeClass('mapOn');
      $(this).parent('li').removeClass('open');
    } else {
      $map.css(mapTransition);
      $hero.addClass('mapOn').removeClass('mapOff');
      $(this).parent('li').addClass('open');
      setTimeout(function() {
        jQuery('html, body').animate({
          scrollTop: 0
        },
        500);
      },
      100);
    }
  });
  $heroOverlays = $(this).find('.bg-overlay').add($('#MainMenu'));
  $('.hero').hover(function() {
    if ($(this).hasClass('mapOn')) {
      $heroOverlays.stop(true).animate({
        opacity: 0
      },
      120,
      function() {
        $(this).css('z-index', '-1');
      });
    }
  },
  function() {
    if ($(this).hasClass('mapOn')) {
      $heroOverlays.stop(true).css('z-index', '1').animate({
        opacity: 1
      },
      400,
      function() {
        $('#MainMenu').css('z-index', '1030');
      });
    }
  });
  $(document).trigger('shrink-menu-init');
  $(document).trigger('shrink-menu');
});
/*!
 * Bootstrap v3.2.0 (http://getbootstrap.com)
 * Copyright 2011-2014 Twitter, Inc.
 * Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 */
if ("undefined" == typeof jQuery) throw new Error("Bootstrap's JavaScript requires jQuery"); +
function(a) {
  "use strict";
  function b() {
    var a = document.createElement("bootstrap"),
    b = {
      WebkitTransition: "webkitTransitionEnd",
      MozTransition: "transitionend",
      OTransition: "oTransitionEnd otransitionend",
      transition: "transitionend"
    };
    for (var c in b) if (void 0 !== a.style[c]) return {
      end: b[c]
    };
    return ! 1
  }
  a.fn.emulateTransitionEnd = function(b) {
    var c = !1,
    d = this;
    a(this).one("bsTransitionEnd",
    function() {
      c = !0
    });
    var e = function() {
      c || a(d).trigger(a.support.transition.end)
    };
    return setTimeout(e, b),
    this
  },
  a(function() {
    a.support.transition = b(),
    a.support.transition && (a.event.special.bsTransitionEnd = {
      bindType: a.support.transition.end,
      delegateType: a.support.transition.end,
      handle: function(b) {
        return a(b.target).is(this) ? b.handleObj.handler.apply(this, arguments) : void 0
      }
    })
  })
} (jQuery),
+
function(a) {
  "use strict";
  function b(b) {
    return this.each(function() {
      var c = a(this),
      e = c.data("bs.alert");
      e || c.data("bs.alert", e = new d(this)),
      "string" == typeof b && e[b].call(c)
    })
  }
  var c = '[data-dismiss="alert"]',
  d = function(b) {
    a(b).on("click", c, this.close)
  };
  d.VERSION = "3.2.0",
  d.prototype.close = function(b) {
    function c() {
      f.detach().trigger("closed.bs.alert").remove()
    }
    var d = a(this),
    e = d.attr("data-target");
    e || (e = d.attr("href"), e = e && e.replace(/.*(?=#[^\s]*$)/, ""));
    var f = a(e);
    b && b.preventDefault(),
    f.length || (f = d.hasClass("alert") ? d: d.parent()),
    f.trigger(b = a.Event("close.bs.alert")),
    b.isDefaultPrevented() || (f.removeClass("in"), a.support.transition && f.hasClass("fade") ? f.one("bsTransitionEnd", c).emulateTransitionEnd(150) : c())
  };
  var e = a.fn.alert;
  a.fn.alert = b,
  a.fn.alert.Constructor = d,
  a.fn.alert.noConflict = function() {
    return a.fn.alert = e,
    this
  },
  a(document).on("click.bs.alert.data-api", c, d.prototype.close)
} (jQuery),
+
function(a) {
  "use strict";
  function b(b) {
    return this.each(function() {
      var d = a(this),
      e = d.data("bs.button"),
      f = "object" == typeof b && b;
      e || d.data("bs.button", e = new c(this, f)),
      "toggle" == b ? e.toggle() : b && e.setState(b)
    })
  }
  var c = function(b, d) {
    this.$element = a(b),
    this.options = a.extend({},
    c.DEFAULTS, d),
    this.isLoading = !1
  };
  c.VERSION = "3.2.0",
  c.DEFAULTS = {
    loadingText: "loading..."
  },
  c.prototype.setState = function(b) {
    var c = "disabled",
    d = this.$element,
    e = d.is("input") ? "val": "html",
    f = d.data();
    b += "Text",
    null == f.resetText && d.data("resetText", d[e]()),
    d[e](null == f[b] ? this.options[b] : f[b]),
    setTimeout(a.proxy(function() {
      "loadingText" == b ? (this.isLoading = !0, d.addClass(c).attr(c, c)) : this.isLoading && (this.isLoading = !1, d.removeClass(c).removeAttr(c))
    },
    this), 0)
  },
  c.prototype.toggle = function() {
    var a = !0,
    b = this.$element.closest('[data-toggle="buttons"]');
    if (b.length) {
      var c = this.$element.find("input");
      "radio" == c.prop("type") && (c.prop("checked") && this.$element.hasClass("active") ? a = !1 : b.find(".active").removeClass("active")),
      a && c.prop("checked", !this.$element.hasClass("active")).trigger("change")
    }
    a && this.$element.toggleClass("active")
  };
  var d = a.fn.button;
  a.fn.button = b,
  a.fn.button.Constructor = c,
  a.fn.button.noConflict = function() {
    return a.fn.button = d,
    this
  },
  a(document).on("click.bs.button.data-api", '[data-toggle^="button"]',
  function(c) {
    var d = a(c.target);
    d.hasClass("btn") || (d = d.closest(".btn")),
    b.call(d, "toggle"),
    c.preventDefault()
  })
} (jQuery),
+
function(a) {
  "use strict";
  function b(b) {
    return this.each(function() {
      var d = a(this),
      e = d.data("bs.carousel"),
      f = a.extend({},
      c.DEFAULTS, d.data(), "object" == typeof b && b),
      g = "string" == typeof b ? b: f.slide;
      e || d.data("bs.carousel", e = new c(this, f)),
      "number" == typeof b ? e.to(b) : g ? e[g]() : f.interval && e.pause().cycle()
    })
  }
  var c = function(b, c) {
    this.$element = a(b).on("keydown.bs.carousel", a.proxy(this.keydown, this)),
    this.$indicators = this.$element.find(".carousel-indicators"),
    this.options = c,
    this.paused = this.sliding = this.interval = this.$active = this.$items = null,
    "hover" == this.options.pause && this.$element.on("mouseenter.bs.carousel", a.proxy(this.pause, this)).on("mouseleave.bs.carousel", a.proxy(this.cycle, this))
  };
  c.VERSION = "3.2.0",
  c.DEFAULTS = {
    interval: 5e3,
    pause: "hover",
    wrap: !0
  },
  c.prototype.keydown = function(a) {
    switch (a.which) {
    case 37:
      this.prev();
      break;
    case 39:
      this.next();
      break;
    default:
      return
    }
    a.preventDefault()
  },
  c.prototype.cycle = function(b) {
    return b || (this.paused = !1),
    this.interval && clearInterval(this.interval),
    this.options.interval && !this.paused && (this.interval = setInterval(a.proxy(this.next, this), this.options.interval)),
    this
  },
  c.prototype.getItemIndex = function(a) {
    return this.$items = a.parent().children(".item"),
    this.$items.index(a || this.$active)
  },
  c.prototype.to = function(b) {
    var c = this,
    d = this.getItemIndex(this.$active = this.$element.find(".item.active"));
    return b > this.$items.length - 1 || 0 > b ? void 0 : this.sliding ? this.$element.one("slid.bs.carousel",
    function() {
      c.to(b)
    }) : d == b ? this.pause().cycle() : this.slide(b > d ? "next": "prev", a(this.$items[b]))
  },
  c.prototype.pause = function(b) {
    return b || (this.paused = !0),
    this.$element.find(".next, .prev").length && a.support.transition && (this.$element.trigger(a.support.transition.end), this.cycle(!0)),
    this.interval = clearInterval(this.interval),
    this
  },
  c.prototype.next = function() {
    return this.sliding ? void 0 : this.slide("next")
  },
  c.prototype.prev = function() {
    return this.sliding ? void 0 : this.slide("prev")
  },
  c.prototype.slide = function(b, c) {
    var d = this.$element.find(".item.active"),
    e = c || d[b](),
    f = this.interval,
    g = "next" == b ? "left": "right",
    h = "next" == b ? "first": "last",
    i = this;
    if (!e.length) {
      if (!this.options.wrap) return;
      e = this.$element.find(".item")[h]()
    }
    if (e.hasClass("active")) return this.sliding = !1;
    var j = e[0],
    k = a.Event("slide.bs.carousel", {
      relatedTarget: j,
      direction: g
    });
    if (this.$element.trigger(k), !k.isDefaultPrevented()) {
      if (this.sliding = !0, f && this.pause(), this.$indicators.length) {
        this.$indicators.find(".active").removeClass("active");
        var l = a(this.$indicators.children()[this.getItemIndex(e)]);
        l && l.addClass("active")
      }
      var m = a.Event("slid.bs.carousel", {
        relatedTarget: j,
        direction: g
      });
      return a.support.transition && this.$element.hasClass("slide") ? (e.addClass(b), e[0].offsetWidth, d.addClass(g), e.addClass(g), d.one("bsTransitionEnd",
      function() {
        e.removeClass([b, g].join(" ")).addClass("active"),
        d.removeClass(["active", g].join(" ")),
        i.sliding = !1,
        setTimeout(function() {
          i.$element.trigger(m)
        },
        0)
      }).emulateTransitionEnd(1e3 * d.css("transition-duration").slice(0, -1))) : (d.removeClass("active"), e.addClass("active"), this.sliding = !1, this.$element.trigger(m)),
      f && this.cycle(),
      this
    }
  };
  var d = a.fn.carousel;
  a.fn.carousel = b,
  a.fn.carousel.Constructor = c,
  a.fn.carousel.noConflict = function() {
    return a.fn.carousel = d,
    this
  },
  a(document).on("click.bs.carousel.data-api", "[data-slide], [data-slide-to]",
  function(c) {
    var d, e = a(this),
    f = a(e.attr("data-target") || (d = e.attr("href")) && d.replace(/.*(?=#[^\s]+$)/, ""));
    if (f.hasClass("carousel")) {
      var g = a.extend({},
      f.data(), e.data()),
      h = e.attr("data-slide-to");
      h && (g.interval = !1),
      b.call(f, g),
      h && f.data("bs.carousel").to(h),
      c.preventDefault()
    }
  }),
  a(window).on("load",
  function() {
    a('[data-ride="carousel"]').each(function() {
      var c = a(this);
      b.call(c, c.data())
    })
  })
} (jQuery),
+
function(a) {
  "use strict";
  function b(b) {
    return this.each(function() {
      var d = a(this),
      e = d.data("bs.collapse"),
      f = a.extend({},
      c.DEFAULTS, d.data(), "object" == typeof b && b); ! e && f.toggle && "show" == b && (b = !b),
      e || d.data("bs.collapse", e = new c(this, f)),
      "string" == typeof b && e[b]()
    })
  }
  var c = function(b, d) {
    this.$element = a(b),
    this.options = a.extend({},
    c.DEFAULTS, d),
    this.transitioning = null,
    this.options.parent && (this.$parent = a(this.options.parent)),
    this.options.toggle && this.toggle()
  };
  c.VERSION = "3.2.0",
  c.DEFAULTS = {
    toggle: !0
  },
  c.prototype.dimension = function() {
    var a = this.$element.hasClass("width");
    return a ? "width": "height"
  },
  c.prototype.show = function() {
    if (!this.transitioning && !this.$element.hasClass("in")) {
      var c = a.Event("show.bs.collapse");
      if (this.$element.trigger(c), !c.isDefaultPrevented()) {
        var d = this.$parent && this.$parent.find("> .panel > .in");
        if (d && d.length) {
          var e = d.data("bs.collapse");
          if (e && e.transitioning) return;
          b.call(d, "hide"),
          e || d.data("bs.collapse", null)
        }
        var f = this.dimension();
        this.$element.removeClass("collapse").addClass("collapsing")[f](0),
        this.transitioning = 1;
        var g = function() {
          this.$element.removeClass("collapsing").addClass("collapse in")[f](""),
          this.transitioning = 0,
          this.$element.trigger("shown.bs.collapse")
        };
        if (!a.support.transition) return g.call(this);
        var h = a.camelCase(["scroll", f].join("-"));
        this.$element.one("bsTransitionEnd", a.proxy(g, this)).emulateTransitionEnd(350)[f](this.$element[0][h])
      }
    }
  },
  c.prototype.hide = function() {
    if (!this.transitioning && this.$element.hasClass("in")) {
      var b = a.Event("hide.bs.collapse");
      if (this.$element.trigger(b), !b.isDefaultPrevented()) {
        var c = this.dimension();
        this.$element[c](this.$element[c]())[0].offsetHeight,
        this.$element.addClass("collapsing").removeClass("collapse").removeClass("in"),
        this.transitioning = 1;
        var d = function() {
          this.transitioning = 0,
          this.$element.trigger("hidden.bs.collapse").removeClass("collapsing").addClass("collapse")
        };
        return a.support.transition ? void this.$element[c](0).one("bsTransitionEnd", a.proxy(d, this)).emulateTransitionEnd(350) : d.call(this)
      }
    }
  },
  c.prototype.toggle = function() {
    this[this.$element.hasClass("in") ? "hide": "show"]()
  };
  var d = a.fn.collapse;
  a.fn.collapse = b,
  a.fn.collapse.Constructor = c,
  a.fn.collapse.noConflict = function() {
    return a.fn.collapse = d,
    this
  },
  a(document).on("click.bs.collapse.data-api", '[data-toggle="collapse"]',
  function(c) {
    var d, e = a(this),
    f = e.attr("data-target") || c.preventDefault() || (d = e.attr("href")) && d.replace(/.*(?=#[^\s]+$)/, ""),
    g = a(f),
    h = g.data("bs.collapse"),
    i = h ? "toggle": e.data(),
    j = e.attr("data-parent"),
    k = j && a(j);
    h && h.transitioning || (k && k.find('[data-toggle="collapse"][data-parent="' + j + '"]').not(e).addClass("collapsed"), e[g.hasClass("in") ? "addClass": "removeClass"]("collapsed")),
    b.call(g, i)
  })
} (jQuery),
+
function(a) {
  "use strict";
  function b(b) {
    b && 3 === b.which || (a(e).remove(), a(f).each(function() {
      var d = c(a(this)),
      e = {
        relatedTarget: this
      };
      d.hasClass("open") && (d.trigger(b = a.Event("hide.bs.dropdown", e)), b.isDefaultPrevented() || d.removeClass("open").trigger("hidden.bs.dropdown", e))
    }))
  }
  function c(b) {
    var c = b.attr("data-target");
    c || (c = b.attr("href"), c = c && /#[A-Za-z]/.test(c) && c.replace(/.*(?=#[^\s]*$)/, ""));
    var d = c && a(c);
    return d && d.length ? d: b.parent()
  }
  function d(b) {
    return this.each(function() {
      var c = a(this),
      d = c.data("bs.dropdown");
      d || c.data("bs.dropdown", d = new g(this)),
      "string" == typeof b && d[b].call(c)
    })
  }
  var e = ".dropdown-backdrop",
  f = '[data-toggle="dropdown"]',
  g = function(b) {
    a(b).on("click.bs.dropdown", this.toggle)
  };
  g.VERSION = "3.2.0",
  g.prototype.toggle = function(d) {
    var e = a(this);
    if (!e.is(".disabled, :disabled")) {
      var f = c(e),
      g = f.hasClass("open");
      if (b(), !g) {
        "ontouchstart" in document.documentElement && !f.closest(".navbar-nav").length && a('<div class="dropdown-backdrop"/>').insertAfter(a(this)).on("click", b);
        var h = {
          relatedTarget: this
        };
        if (f.trigger(d = a.Event("show.bs.dropdown", h)), d.isDefaultPrevented()) return;
        e.trigger("focus"),
        f.toggleClass("open").trigger("shown.bs.dropdown", h)
      }
      return ! 1
    }
  },
  g.prototype.keydown = function(b) {
    if (/(38|40|27)/.test(b.keyCode)) {
      var d = a(this);
      if (b.preventDefault(), b.stopPropagation(), !d.is(".disabled, :disabled")) {
        var e = c(d),
        g = e.hasClass("open");
        if (!g || g && 27 == b.keyCode) return 27 == b.which && e.find(f).trigger("focus"),
        d.trigger("click");
        var h = " li:not(.divider):visible a",
        i = e.find('[role="menu"]' + h + ', [role="listbox"]' + h);
        if (i.length) {
          var j = i.index(i.filter(":focus"));
          38 == b.keyCode && j > 0 && j--,
          40 == b.keyCode && j < i.length - 1 && j++,
          ~j || (j = 0),
          i.eq(j).trigger("focus")
        }
      }
    }
  };
  var h = a.fn.dropdown;
  a.fn.dropdown = d,
  a.fn.dropdown.Constructor = g,
  a.fn.dropdown.noConflict = function() {
    return a.fn.dropdown = h,
    this
  },
  a(document).on("click.bs.dropdown.data-api", b).on("click.bs.dropdown.data-api", ".dropdown form",
  function(a) {
    a.stopPropagation()
  }).on("click.bs.dropdown.data-api", f, g.prototype.toggle).on("keydown.bs.dropdown.data-api", f + ', [role="menu"], [role="listbox"]', g.prototype.keydown)
} (jQuery),
+
function(a) {
  "use strict";
  function b(b, d) {
    return this.each(function() {
      var e = a(this),
      f = e.data("bs.modal"),
      g = a.extend({},
      c.DEFAULTS, e.data(), "object" == typeof b && b);
      f || e.data("bs.modal", f = new c(this, g)),
      "string" == typeof b ? f[b](d) : g.show && f.show(d)
    })
  }
  var c = function(b, c) {
    this.options = c,
    this.$body = a(document.body),
    this.$element = a(b),
    this.$backdrop = this.isShown = null,
    this.scrollbarWidth = 0,
    this.options.remote && this.$element.find(".modal-content").load(this.options.remote, a.proxy(function() {
      this.$element.trigger("loaded.bs.modal")
    },
    this))
  };
  c.VERSION = "3.2.0",
  c.DEFAULTS = {
    backdrop: !0,
    keyboard: !0,
    show: !0
  },
  c.prototype.toggle = function(a) {
    return this.isShown ? this.hide() : this.show(a)
  },
  c.prototype.show = function(b) {
    var c = this,
    d = a.Event("show.bs.modal", {
      relatedTarget: b
    });
    this.$element.trigger(d),
    this.isShown || d.isDefaultPrevented() || (this.isShown = !0, this.checkScrollbar(), this.$body.addClass("modal-open"), this.setScrollbar(), this.escape(), this.$element.on("click.dismiss.bs.modal", '[data-dismiss="modal"]', a.proxy(this.hide, this)), this.backdrop(function() {
      var d = a.support.transition && c.$element.hasClass("fade");
      c.$element.parent().length || c.$element.appendTo(c.$body),
      c.$element.show().scrollTop(0),
      d && c.$element[0].offsetWidth,
      c.$element.addClass("in").attr("aria-hidden", !1),
      c.enforceFocus();
      var e = a.Event("shown.bs.modal", {
        relatedTarget: b
      });
      d ? c.$element.find(".modal-dialog").one("bsTransitionEnd",
      function() {
        c.$element.trigger("focus").trigger(e)
      }).emulateTransitionEnd(300) : c.$element.trigger("focus").trigger(e)
    }))
  },
  c.prototype.hide = function(b) {
    b && b.preventDefault(),
    b = a.Event("hide.bs.modal"),
    this.$element.trigger(b),
    this.isShown && !b.isDefaultPrevented() && (this.isShown = !1, this.$body.removeClass("modal-open"), this.resetScrollbar(), this.escape(), a(document).off("focusin.bs.modal"), this.$element.removeClass("in").attr("aria-hidden", !0).off("click.dismiss.bs.modal"), a.support.transition && this.$element.hasClass("fade") ? this.$element.one("bsTransitionEnd", a.proxy(this.hideModal, this)).emulateTransitionEnd(300) : this.hideModal())
  },
  c.prototype.enforceFocus = function() {
    a(document).off("focusin.bs.modal").on("focusin.bs.modal", a.proxy(function(a) {
      this.$element[0] === a.target || this.$element.has(a.target).length || this.$element.trigger("focus")
    },
    this))
  },
  c.prototype.escape = function() {
    this.isShown && this.options.keyboard ? this.$element.on("keyup.dismiss.bs.modal", a.proxy(function(a) {
      27 == a.which && this.hide()
    },
    this)) : this.isShown || this.$element.off("keyup.dismiss.bs.modal")
  },
  c.prototype.hideModal = function() {
    var a = this;
    this.$element.hide(),
    this.backdrop(function() {
      a.$element.trigger("hidden.bs.modal")
    })
  },
  c.prototype.removeBackdrop = function() {
    this.$backdrop && this.$backdrop.remove(),
    this.$backdrop = null
  },
  c.prototype.backdrop = function(b) {
    var c = this,
    d = this.$element.hasClass("fade") ? "fade": "";
    if (this.isShown && this.options.backdrop) {
      var e = a.support.transition && d;
      if (this.$backdrop = a('<div class="modal-backdrop ' + d + '" />').appendTo(this.$body), this.$element.on("click.dismiss.bs.modal", a.proxy(function(a) {
        a.target === a.currentTarget && ("static" == this.options.backdrop ? this.$element[0].focus.call(this.$element[0]) : this.hide.call(this))
      },
      this)), e && this.$backdrop[0].offsetWidth, this.$backdrop.addClass("in"), !b) return;
      e ? this.$backdrop.one("bsTransitionEnd", b).emulateTransitionEnd(150) : b()
    } else if (!this.isShown && this.$backdrop) {
      this.$backdrop.removeClass("in");
      var f = function() {
        c.removeBackdrop(),
        b && b()
      };
      a.support.transition && this.$element.hasClass("fade") ? this.$backdrop.one("bsTransitionEnd", f).emulateTransitionEnd(150) : f()
    } else b && b()
  },
  c.prototype.checkScrollbar = function() {
    document.body.clientWidth >= window.innerWidth || (this.scrollbarWidth = this.scrollbarWidth || this.measureScrollbar())
  },
  c.prototype.setScrollbar = function() {
    var a = parseInt(this.$body.css("padding-right") || 0, 10);
    this.scrollbarWidth && this.$body.css("padding-right", a + this.scrollbarWidth)
  },
  c.prototype.resetScrollbar = function() {
    this.$body.css("padding-right", "")
  },
  c.prototype.measureScrollbar = function() {
    var a = document.createElement("div");
    a.className = "modal-scrollbar-measure",
    this.$body.append(a);
    var b = a.offsetWidth - a.clientWidth;
    return this.$body[0].removeChild(a),
    b
  };
  var d = a.fn.modal;
  a.fn.modal = b,
  a.fn.modal.Constructor = c,
  a.fn.modal.noConflict = function() {
    return a.fn.modal = d,
    this
  },
  a(document).on("click.bs.modal.data-api", '[data-toggle="modal"]',
  function(c) {
    var d = a(this),
    e = d.attr("href"),
    f = a(d.attr("data-target") || e && e.replace(/.*(?=#[^\s]+$)/, "")),
    g = f.data("bs.modal") ? "toggle": a.extend({
      remote: !/#/.test(e) && e
    },
    f.data(), d.data());
    d.is("a") && c.preventDefault(),
    f.one("show.bs.modal",
    function(a) {
      a.isDefaultPrevented() || f.one("hidden.bs.modal",
      function() {
        d.is(":visible") && d.trigger("focus")
      })
    }),
    b.call(f, g, this)
  })
} (jQuery),
+
function(a) {
  "use strict";
  function b(b) {
    return this.each(function() {
      var d = a(this),
      e = d.data("bs.tooltip"),
      f = "object" == typeof b && b; (e || "destroy" != b) && (e || d.data("bs.tooltip", e = new c(this, f)), "string" == typeof b && e[b]())
    })
  }
  var c = function(a, b) {
    this.type = this.options = this.enabled = this.timeout = this.hoverState = this.$element = null,
    this.init("tooltip", a, b)
  };
  c.VERSION = "3.2.0",
  c.DEFAULTS = {
    animation: !0,
    placement: "top",
    selector: !1,
    template: '<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>',
    trigger: "hover focus",
    title: "",
    delay: 0,
    html: !1,
    container: !1,
    viewport: {
      selector: "body",
      padding: 0
    }
  },
  c.prototype.init = function(b, c, d) {
    this.enabled = !0,
    this.type = b,
    this.$element = a(c),
    this.options = this.getOptions(d),
    this.$viewport = this.options.viewport && a(this.options.viewport.selector || this.options.viewport);
    for (var e = this.options.trigger.split(" "), f = e.length; f--;) {
      var g = e[f];
      if ("click" == g) this.$element.on("click." + this.type, this.options.selector, a.proxy(this.toggle, this));
      else if ("manual" != g) {
        var h = "hover" == g ? "mouseenter": "focusin",
        i = "hover" == g ? "mouseleave": "focusout";
        this.$element.on(h + "." + this.type, this.options.selector, a.proxy(this.enter, this)),
        this.$element.on(i + "." + this.type, this.options.selector, a.proxy(this.leave, this))
      }
    }
    this.options.selector ? this._options = a.extend({},
    this.options, {
      trigger: "manual",
      selector: ""
    }) : this.fixTitle()
  },
  c.prototype.getDefaults = function() {
    return c.DEFAULTS
  },
  c.prototype.getOptions = function(b) {
    return b = a.extend({},
    this.getDefaults(), this.$element.data(), b),
    b.delay && "number" == typeof b.delay && (b.delay = {
      show: b.delay,
      hide: b.delay
    }),
    b
  },
  c.prototype.getDelegateOptions = function() {
    var b = {},
    c = this.getDefaults();
    return this._options && a.each(this._options,
    function(a, d) {
      c[a] != d && (b[a] = d)
    }),
    b
  },
  c.prototype.enter = function(b) {
    var c = b instanceof this.constructor ? b: a(b.currentTarget).data("bs." + this.type);
    return c || (c = new this.constructor(b.currentTarget, this.getDelegateOptions()), a(b.currentTarget).data("bs." + this.type, c)),
    clearTimeout(c.timeout),
    c.hoverState = "in",
    c.options.delay && c.options.delay.show ? void(c.timeout = setTimeout(function() {
      "in" == c.hoverState && c.show()
    },
    c.options.delay.show)) : c.show()
  },
  c.prototype.leave = function(b) {
    var c = b instanceof this.constructor ? b: a(b.currentTarget).data("bs." + this.type);
    return c || (c = new this.constructor(b.currentTarget, this.getDelegateOptions()), a(b.currentTarget).data("bs." + this.type, c)),
    clearTimeout(c.timeout),
    c.hoverState = "out",
    c.options.delay && c.options.delay.hide ? void(c.timeout = setTimeout(function() {
      "out" == c.hoverState && c.hide()
    },
    c.options.delay.hide)) : c.hide()
  },
  c.prototype.show = function() {
    var b = a.Event("show.bs." + this.type);
    if (this.hasContent() && this.enabled) {
      this.$element.trigger(b);
      var c = a.contains(document.documentElement, this.$element[0]);
      if (b.isDefaultPrevented() || !c) return;
      var d = this,
      e = this.tip(),
      f = this.getUID(this.type);
      this.setContent(),
      e.attr("id", f),
      this.$element.attr("aria-describedby", f),
      this.options.animation && e.addClass("fade");
      var g = "function" == typeof this.options.placement ? this.options.placement.call(this, e[0], this.$element[0]) : this.options.placement,
      h = /\s?auto?\s?/i,
      i = h.test(g);
      i && (g = g.replace(h, "") || "top"),
      e.detach().css({
        top: 0,
        left: 0,
        display: "block"
      }).addClass(g).data("bs." + this.type, this),
      this.options.container ? e.appendTo(this.options.container) : e.insertAfter(this.$element);
      var j = this.getPosition(),
      k = e[0].offsetWidth,
      l = e[0].offsetHeight;
      if (i) {
        var m = g,
        n = this.$element.parent(),
        o = this.getPosition(n);
        g = "bottom" == g && j.top + j.height + l - o.scroll > o.height ? "top": "top" == g && j.top - o.scroll - l < 0 ? "bottom": "right" == g && j.right + k > o.width ? "left": "left" == g && j.left - k < o.left ? "right": g,
        e.removeClass(m).addClass(g)
      }
      var p = this.getCalculatedOffset(g, j, k, l);
      this.applyPlacement(p, g);
      var q = function() {
        d.$element.trigger("shown.bs." + d.type),
        d.hoverState = null
      };
      a.support.transition && this.$tip.hasClass("fade") ? e.one("bsTransitionEnd", q).emulateTransitionEnd(150) : q()
    }
  },
  c.prototype.applyPlacement = function(b, c) {
    var d = this.tip(),
    e = d[0].offsetWidth,
    f = d[0].offsetHeight,
    g = parseInt(d.css("margin-top"), 10),
    h = parseInt(d.css("margin-left"), 10);
    isNaN(g) && (g = 0),
    isNaN(h) && (h = 0),
    b.top = b.top + g,
    b.left = b.left + h,
    a.offset.setOffset(d[0], a.extend({
      using: function(a) {
        d.css({
          top: Math.round(a.top),
          left: Math.round(a.left)
        })
      }
    },
    b), 0),
    d.addClass("in");
    var i = d[0].offsetWidth,
    j = d[0].offsetHeight;
    "top" == c && j != f && (b.top = b.top + f - j);
    var k = this.getViewportAdjustedDelta(c, b, i, j);
    k.left ? b.left += k.left: b.top += k.top;
    var l = k.left ? 2 * k.left - e + i: 2 * k.top - f + j,
    m = k.left ? "left": "top",
    n = k.left ? "offsetWidth": "offsetHeight";
    d.offset(b),
    this.replaceArrow(l, d[0][n], m)
  },
  c.prototype.replaceArrow = function(a, b, c) {
    this.arrow().css(c, a ? 50 * (1 - a / b) + "%": "")
  },
  c.prototype.setContent = function() {
    var a = this.tip(),
    b = this.getTitle();
    a.find(".tooltip-inner")[this.options.html ? "html": "text"](b),
    a.removeClass("fade in top bottom left right")
  },
  c.prototype.hide = function() {
    function b() {
      "in" != c.hoverState && d.detach(),
      c.$element.trigger("hidden.bs." + c.type)
    }
    var c = this,
    d = this.tip(),
    e = a.Event("hide.bs." + this.type);
    return this.$element.removeAttr("aria-describedby"),
    this.$element.trigger(e),
    e.isDefaultPrevented() ? void 0 : (d.removeClass("in"), a.support.transition && this.$tip.hasClass("fade") ? d.one("bsTransitionEnd", b).emulateTransitionEnd(150) : b(), this.hoverState = null, this)
  },
  c.prototype.fixTitle = function() {
    var a = this.$element; (a.attr("title") || "string" != typeof a.attr("data-original-title")) && a.attr("data-original-title", a.attr("title") || "").attr("title", "")
  },
  c.prototype.hasContent = function() {
    return this.getTitle()
  },
  c.prototype.getPosition = function(b) {
    b = b || this.$element;
    var c = b[0],
    d = "BODY" == c.tagName;
    return a.extend({},
    "function" == typeof c.getBoundingClientRect ? c.getBoundingClientRect() : null, {
      scroll: d ? document.documentElement.scrollTop || document.body.scrollTop: b.scrollTop(),
      width: d ? a(window).width() : b.outerWidth(),
      height: d ? a(window).height() : b.outerHeight()
    },
    d ? {
      top: 0,
      left: 0
    }: b.offset())
  },
  c.prototype.getCalculatedOffset = function(a, b, c, d) {
    return "bottom" == a ? {
      top: b.top + b.height,
      left: b.left + b.width / 2 - c / 2
    }: "top" == a ? {
      top: b.top - d,
      left: b.left + b.width / 2 - c / 2
    }: "left" == a ? {
      top: b.top + b.height / 2 - d / 2,
      left: b.left - c
    }: {
      top: b.top + b.height / 2 - d / 2,
      left: b.left + b.width
    }
  },
  c.prototype.getViewportAdjustedDelta = function(a, b, c, d) {
    var e = {
      top: 0,
      left: 0
    };
    if (!this.$viewport) return e;
    var f = this.options.viewport && this.options.viewport.padding || 0,
    g = this.getPosition(this.$viewport);
    if (/right|left/.test(a)) {
      var h = b.top - f - g.scroll,
      i = b.top + f - g.scroll + d;
      h < g.top ? e.top = g.top - h: i > g.top + g.height && (e.top = g.top + g.height - i)
    } else {
      var j = b.left - f,
      k = b.left + f + c;
      j < g.left ? e.left = g.left - j: k > g.width && (e.left = g.left + g.width - k)
    }
    return e
  },
  c.prototype.getTitle = function() {
    var a, b = this.$element,
    c = this.options;
    return a = b.attr("data-original-title") || ("function" == typeof c.title ? c.title.call(b[0]) : c.title)
  },
  c.prototype.getUID = function(a) {
    do a += ~~ (1e6 * Math.random());
    while (document.getElementById(a));
    return a
  },
  c.prototype.tip = function() {
    return this.$tip = this.$tip || a(this.options.template)
  },
  c.prototype.arrow = function() {
    return this.$arrow = this.$arrow || this.tip().find(".tooltip-arrow")
  },
  c.prototype.validate = function() {
    this.$element[0].parentNode || (this.hide(), this.$element = null, this.options = null)
  },
  c.prototype.enable = function() {
    this.enabled = !0
  },
  c.prototype.disable = function() {
    this.enabled = !1
  },
  c.prototype.toggleEnabled = function() {
    this.enabled = !this.enabled
  },
  c.prototype.toggle = function(b) {
    var c = this;
    b && (c = a(b.currentTarget).data("bs." + this.type), c || (c = new this.constructor(b.currentTarget, this.getDelegateOptions()), a(b.currentTarget).data("bs." + this.type, c))),
    c.tip().hasClass("in") ? c.leave(c) : c.enter(c)
  },
  c.prototype.destroy = function() {
    clearTimeout(this.timeout),
    this.hide().$element.off("." + this.type).removeData("bs." + this.type)
  };
  var d = a.fn.tooltip;
  a.fn.tooltip = b,
  a.fn.tooltip.Constructor = c,
  a.fn.tooltip.noConflict = function() {
    return a.fn.tooltip = d,
    this
  }
} (jQuery),
+
function(a) {
  "use strict";
  function b(b) {
    return this.each(function() {
      var d = a(this),
      e = d.data("bs.popover"),
      f = "object" == typeof b && b; (e || "destroy" != b) && (e || d.data("bs.popover", e = new c(this, f)), "string" == typeof b && e[b]())
    })
  }
  var c = function(a, b) {
    this.init("popover", a, b)
  };
  if (!a.fn.tooltip) throw new Error("Popover requires tooltip.js");
  c.VERSION = "3.2.0",
  c.DEFAULTS = a.extend({},
  a.fn.tooltip.Constructor.DEFAULTS, {
    placement: "right",
    trigger: "click",
    content: "",
    template: '<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
  }),
  c.prototype = a.extend({},
  a.fn.tooltip.Constructor.prototype),
  c.prototype.constructor = c,
  c.prototype.getDefaults = function() {
    return c.DEFAULTS
  },
  c.prototype.setContent = function() {
    var a = this.tip(),
    b = this.getTitle(),
    c = this.getContent();
    a.find(".popover-title")[this.options.html ? "html": "text"](b),
    a.find(".popover-content").empty()[this.options.html ? "string" == typeof c ? "html": "append": "text"](c),
    a.removeClass("fade top bottom left right in"),
    a.find(".popover-title").html() || a.find(".popover-title").hide()
  },
  c.prototype.hasContent = function() {
    return this.getTitle() || this.getContent()
  },
  c.prototype.getContent = function() {
    var a = this.$element,
    b = this.options;
    return a.attr("data-content") || ("function" == typeof b.content ? b.content.call(a[0]) : b.content)
  },
  c.prototype.arrow = function() {
    return this.$arrow = this.$arrow || this.tip().find(".arrow")
  },
  c.prototype.tip = function() {
    return this.$tip || (this.$tip = a(this.options.template)),
    this.$tip
  };
  var d = a.fn.popover;
  a.fn.popover = b,
  a.fn.popover.Constructor = c,
  a.fn.popover.noConflict = function() {
    return a.fn.popover = d,
    this
  }
} (jQuery),
+
function(a) {
  "use strict";
  function b(c, d) {
    var e = a.proxy(this.process, this);
    this.$body = a("body"),
    this.$scrollElement = a(a(c).is("body") ? window: c),
    this.options = a.extend({},
    b.DEFAULTS, d),
    this.selector = (this.options.target || "") + " .nav li > a",
    this.offsets = [],
    this.targets = [],
    this.activeTarget = null,
    this.scrollHeight = 0,
    this.$scrollElement.on("scroll.bs.scrollspy", e),
    this.refresh(),
    this.process()
  }
  function c(c) {
    return this.each(function() {
      var d = a(this),
      e = d.data("bs.scrollspy"),
      f = "object" == typeof c && c;
      e || d.data("bs.scrollspy", e = new b(this, f)),
      "string" == typeof c && e[c]()
    })
  }
  b.VERSION = "3.2.0",
  b.DEFAULTS = {
    offset: 10
  },
  b.prototype.getScrollHeight = function() {
    return this.$scrollElement[0].scrollHeight || Math.max(this.$body[0].scrollHeight, document.documentElement.scrollHeight)
  },
  b.prototype.refresh = function() {
    var b = "offset",
    c = 0;
    a.isWindow(this.$scrollElement[0]) || (b = "position", c = this.$scrollElement.scrollTop()),
    this.offsets = [],
    this.targets = [],
    this.scrollHeight = this.getScrollHeight();
    var d = this;
    this.$body.find(this.selector).map(function() {
      var d = a(this),
      e = d.data("target") || d.attr("href"),
      f = /^#./.test(e) && a(e);
      return f && f.length && f.is(":visible") && [[f[b]().top + c, e]] || null
    }).sort(function(a, b) {
      return a[0] - b[0]
    }).each(function() {
      d.offsets.push(this[0]),
      d.targets.push(this[1])
    })
  },
  b.prototype.process = function() {
    var a, b = this.$scrollElement.scrollTop() + this.options.offset,
    c = this.getScrollHeight(),
    d = this.options.offset + c - this.$scrollElement.height(),
    e = this.offsets,
    f = this.targets,
    g = this.activeTarget;
    if (this.scrollHeight != c && this.refresh(), b >= d) return g != (a = f[f.length - 1]) && this.activate(a);
    if (g && b <= e[0]) return g != (a = f[0]) && this.activate(a);
    for (a = e.length; a--;) g != f[a] && b >= e[a] && (!e[a + 1] || b <= e[a + 1]) && this.activate(f[a])
  },
  b.prototype.activate = function(b) {
    this.activeTarget = b,
    a(this.selector).parentsUntil(this.options.target, ".active").removeClass("active");
    var c = this.selector + '[data-target="' + b + '"],' + this.selector + '[href="' + b + '"]',
    d = a(c).parents("li").addClass("active");
    d.parent(".dropdown-menu").length && (d = d.closest("li.dropdown").addClass("active")),
    d.trigger("activate.bs.scrollspy")
  };
  var d = a.fn.scrollspy;
  a.fn.scrollspy = c,
  a.fn.scrollspy.Constructor = b,
  a.fn.scrollspy.noConflict = function() {
    return a.fn.scrollspy = d,
    this
  },
  a(window).on("load.bs.scrollspy.data-api",
  function() {
    a('[data-spy="scroll"]').each(function() {
      var b = a(this);
      c.call(b, b.data())
    })
  })
} (jQuery),
+
function(a) {
  "use strict";
  function b(b) {
    return this.each(function() {
      var d = a(this),
      e = d.data("bs.tab");
      e || d.data("bs.tab", e = new c(this)),
      "string" == typeof b && e[b]()
    })
  }
  var c = function(b) {
    this.element = a(b)
  };
  c.VERSION = "3.2.0",
  c.prototype.show = function() {
    var b = this.element,
    c = b.closest("ul:not(.dropdown-menu)"),
    d = b.data("target");
    if (d || (d = b.attr("href"), d = d && d.replace(/.*(?=#[^\s]*$)/, "")), !b.parent("li").hasClass("active")) {
      var e = c.find(".active:last a")[0],
      f = a.Event("show.bs.tab", {
        relatedTarget: e
      });
      if (b.trigger(f), !f.isDefaultPrevented()) {
        var g = a(d);
        this.activate(b.closest("li"), c),
        this.activate(g, g.parent(),
        function() {
          b.trigger({
            type: "shown.bs.tab",
            relatedTarget: e
          })
        })
      }
    }
  },
  c.prototype.activate = function(b, c, d) {
    function e() {
      f.removeClass("active").find("> .dropdown-menu > .active").removeClass("active"),
      b.addClass("active"),
      g ? (b[0].offsetWidth, b.addClass("in")) : b.removeClass("fade"),
      b.parent(".dropdown-menu") && b.closest("li.dropdown").addClass("active"),
      d && d()
    }
    var f = c.find("> .active"),
    g = d && a.support.transition && f.hasClass("fade");
    g ? f.one("bsTransitionEnd", e).emulateTransitionEnd(150) : e(),
    f.removeClass("in")
  };
  var d = a.fn.tab;
  a.fn.tab = b,
  a.fn.tab.Constructor = c,
  a.fn.tab.noConflict = function() {
    return a.fn.tab = d,
    this
  },
  a(document).on("click.bs.tab.data-api", '[data-toggle="tab"], [data-toggle="pill"]',
  function(c) {
    c.preventDefault(),
    b.call(a(this), "show")
  })
} (jQuery),
+
function(a) {
  "use strict";
  function b(b) {
    return this.each(function() {
      var d = a(this),
      e = d.data("bs.affix"),
      f = "object" == typeof b && b;
      e || d.data("bs.affix", e = new c(this, f)),
      "string" == typeof b && e[b]()
    })
  }
  var c = function(b, d) {
    this.options = a.extend({},
    c.DEFAULTS, d),
    this.$target = a(this.options.target).on("scroll.bs.affix.data-api", a.proxy(this.checkPosition, this)).on("click.bs.affix.data-api", a.proxy(this.checkPositionWithEventLoop, this)),
    this.$element = a(b),
    this.affixed = this.unpin = this.pinnedOffset = null,
    this.checkPosition()
  };
  c.VERSION = "3.2.0",
  c.RESET = "affix affix-top affix-bottom",
  c.DEFAULTS = {
    offset: 0,
    target: window
  },
  c.prototype.getPinnedOffset = function() {
    if (this.pinnedOffset) return this.pinnedOffset;
    this.$element.removeClass(c.RESET).addClass("affix");
    var a = this.$target.scrollTop(),
    b = this.$element.offset();
    return this.pinnedOffset = b.top - a
  },
  c.prototype.checkPositionWithEventLoop = function() {
    setTimeout(a.proxy(this.checkPosition, this), 1)
  },
  c.prototype.checkPosition = function() {
    if (this.$element.is(":visible")) {
      var b = a(document).height(),
      d = this.$target.scrollTop(),
      e = this.$element.offset(),
      f = this.options.offset,
      g = f.top,
      h = f.bottom;
      "object" != typeof f && (h = g = f),
      "function" == typeof g && (g = f.top(this.$element)),
      "function" == typeof h && (h = f.bottom(this.$element));
      var i = null != this.unpin && d + this.unpin <= e.top ? !1 : null != h && e.top + this.$element.height() >= b - h ? "bottom": null != g && g >= d ? "top": !1;
      if (this.affixed !== i) {
        null != this.unpin && this.$element.css("top", "");
        var j = "affix" + (i ? "-" + i: ""),
        k = a.Event(j + ".bs.affix");
        this.$element.trigger(k),
        k.isDefaultPrevented() || (this.affixed = i, this.unpin = "bottom" == i ? this.getPinnedOffset() : null, this.$element.removeClass(c.RESET).addClass(j).trigger(a.Event(j.replace("affix", "affixed"))), "bottom" == i && this.$element.offset({
          top: b - this.$element.height() - h
        }))
      }
    }
  };
  var d = a.fn.affix;
  a.fn.affix = b,
  a.fn.affix.Constructor = c,
  a.fn.affix.noConflict = function() {
    return a.fn.affix = d,
    this
  },
  a(window).on("load",
  function() {
    a('[data-spy="affix"]').each(function() {
      var c = a(this),
      d = c.data();
      d.offset = d.offset || {},
      d.offsetBottom && (d.offset.bottom = d.offsetBottom),
      d.offsetTop && (d.offset.top = d.offsetTop),
      b.call(c, d)
    })
  })
} (jQuery); !
function(a, b, c, d) {
  function e(b, c) {
    this.settings = null,
    this.options = a.extend({},
    e.Defaults, c),
    this.$element = a(b),
    this.drag = a.extend({},
    m),
    this.state = a.extend({},
    n),
    this.e = a.extend({},
    o),
    this._plugins = {},
    this._supress = {},
    this._current = null,
    this._speed = null,
    this._coordinates = [],
    this._breakpoint = null,
    this._width = null,
    this._items = [],
    this._clones = [],
    this._mergers = [],
    this._invalidated = {},
    this._pipe = [],
    a.each(e.Plugins, a.proxy(function(a, b) {
      this._plugins[a[0].toLowerCase() + a.slice(1)] = new b(this)
    },
    this)),
    a.each(e.Pipe, a.proxy(function(b, c) {
      this._pipe.push({
        filter: c.filter,
        run: a.proxy(c.run, this)
      })
    },
    this)),
    this.setup(),
    this.initialize()
  }
  function f(a) {
    if (a.touches !== d) return {
      x: a.touches[0].pageX,
      y: a.touches[0].pageY
    };
    if (a.touches === d) {
      if (a.pageX !== d) return {
        x: a.pageX,
        y: a.pageY
      };
      if (a.pageX === d) return {
        x: a.clientX,
        y: a.clientY
      }
    }
  }
  function g(a) {
    var b, d, e = c.createElement("div"),
    f = a;
    for (b in f) if (d = f[b], "undefined" != typeof e.style[d]) return e = null,
    [d, b];
    return [!1]
  }
  function h() {
    return g(["transition", "WebkitTransition", "MozTransition", "OTransition"])[1]
  }
  function i() {
    return g(["transform", "WebkitTransform", "MozTransform", "OTransform", "msTransform"])[0]
  }
  function j() {
    return g(["perspective", "webkitPerspective", "MozPerspective", "OPerspective", "MsPerspective"])[0]
  }
  function k() {
    return "ontouchstart" in b || !!navigator.msMaxTouchPoints
  }
  function l() {
    return b.navigator.msPointerEnabled
  }
  var m, n, o;
  m = {
    start: 0,
    startX: 0,
    startY: 0,
    current: 0,
    currentX: 0,
    currentY: 0,
    offsetX: 0,
    offsetY: 0,
    distance: null,
    startTime: 0,
    endTime: 0,
    updatedX: 0,
    targetEl: null
  },
  n = {
    isTouch: !1,
    isScrolling: !1,
    isSwiping: !1,
    direction: !1,
    inMotion: !1
  },
  o = {
    _onDragStart: null,
    _onDragMove: null,
    _onDragEnd: null,
    _transitionEnd: null,
    _resizer: null,
    _responsiveCall: null,
    _goToLoop: null,
    _checkVisibile: null
  },
  e.Defaults = {
    items: 3,
    loop: !1,
    center: !1,
    mouseDrag: !0,
    touchDrag: !0,
    pullDrag: !0,
    freeDrag: !1,
    margin: 0,
    stagePadding: 0,
    merge: !1,
    mergeFit: !0,
    autoWidth: !1,
    startPosition: 0,
    rtl: !1,
    smartSpeed: 250,
    fluidSpeed: !1,
    dragEndSpeed: !1,
    responsive: {},
    responsiveRefreshRate: 200,
    responsiveBaseElement: b,
    responsiveClass: !1,
    fallbackEasing: "swing",
    info: !1,
    nestedItemSelector: !1,
    itemElement: "div",
    stageElement: "div",
    themeClass: "owl-theme",
    baseClass: "owl-carousel",
    itemClass: "owl-item",
    centerClass: "center",
    activeClass: "active"
  },
  e.Width = {
    Default: "default",
    Inner: "inner",
    Outer: "outer"
  },
  e.Plugins = {},
  e.Pipe = [{
    filter: ["width", "items", "settings"],
    run: function(a) {
      a.current = this._items && this._items[this.relative(this._current)]
    }
  },
  {
    filter: ["items", "settings"],
    run: function() {
      var a = this._clones,
      b = this.$stage.children(".cloned"); (b.length !== a.length || !this.settings.loop && a.length > 0) && (this.$stage.children(".cloned").remove(), this._clones = [])
    }
  },
  {
    filter: ["items", "settings"],
    run: function() {
      var a, b, c = this._clones,
      d = this._items,
      e = this.settings.loop ? c.length - Math.max(2 * this.settings.items, 4) : 0;
      for (a = 0, b = Math.abs(e / 2); b > a; a++) e > 0 ? (this.$stage.children().eq(d.length + c.length - 1).remove(), c.pop(), this.$stage.children().eq(0).remove(), c.pop()) : (c.push(c.length / 2), this.$stage.append(d[c[c.length - 1]].clone().addClass("cloned")), c.push(d.length - 1 - (c.length - 1) / 2), this.$stage.prepend(d[c[c.length - 1]].clone().addClass("cloned")))
    }
  },
  {
    filter: ["width", "items", "settings"],
    run: function() {
      var a, b, c, d = this.settings.rtl ? 1 : -1,
      e = (this.width() / this.settings.items).toFixed(3),
      f = 0;
      for (this._coordinates = [], b = 0, c = this._clones.length + this._items.length; c > b; b++) a = this._mergers[this.relative(b)],
      a = this.settings.mergeFit && Math.min(a, this.settings.items) || a,
      f += (this.settings.autoWidth ? this._items[this.relative(b)].width() + this.settings.margin: e * a) * d,
      this._coordinates.push(f)
    }
  },
  {
    filter: ["width", "items", "settings"],
    run: function() {
      var b, c, d = (this.width() / this.settings.items).toFixed(3),
      e = {
        width: Math.abs(this._coordinates[this._coordinates.length - 1]) + 2 * this.settings.stagePadding,
        "padding-left": this.settings.stagePadding || "",
        "padding-right": this.settings.stagePadding || ""
      };
      if (this.$stage.css(e), e = {
        width: this.settings.autoWidth ? "auto": d - this.settings.margin
      },
      e[this.settings.rtl ? "margin-left": "margin-right"] = this.settings.margin, !this.settings.autoWidth && a.grep(this._mergers,
      function(a) {
        return a > 1
      }).length > 0) for (b = 0, c = this._coordinates.length; c > b; b++) e.width = Math.abs(this._coordinates[b]) - Math.abs(this._coordinates[b - 1] || 0) - this.settings.margin,
      this.$stage.children().eq(b).css(e);
      else this.$stage.children().css(e)
    }
  },
  {
    filter: ["width", "items", "settings"],
    run: function(a) {
      a.current && this.reset(this.$stage.children().index(a.current))
    }
  },
  {
    filter: ["position"],
    run: function() {
      this.animate(this.coordinates(this._current))
    }
  },
  {
    filter: ["width", "position", "items", "settings"],
    run: function() {
      var a, b, c, d, e = this.settings.rtl ? 1 : -1,
      f = 2 * this.settings.stagePadding,
      g = this.coordinates(this.current()) + f,
      h = g + this.width() * e,
      i = [];
      for (c = 0, d = this._coordinates.length; d > c; c++) a = this._coordinates[c - 1] || 0,
      b = Math.abs(this._coordinates[c]) + f * e,
      (this.op(a, "<=", g) && this.op(a, ">", h) || this.op(b, "<", g) && this.op(b, ">", h)) && i.push(c);
      this.$stage.children("." + this.settings.activeClass).removeClass(this.settings.activeClass),
      this.$stage.children(":eq(" + i.join("), :eq(") + ")").addClass(this.settings.activeClass),
      this.settings.center && (this.$stage.children("." + this.settings.centerClass).removeClass(this.settings.centerClass), this.$stage.children().eq(this.current()).addClass(this.settings.centerClass))
    }
  }],
  e.prototype.initialize = function() {
    if (this.trigger("initialize"), this.$element.addClass(this.settings.baseClass).addClass(this.settings.themeClass).toggleClass("owl-rtl", this.settings.rtl), this.browserSupport(), this.settings.autoWidth && this.state.imagesLoaded !== !0) {
      var b, c, e;
      if (b = this.$element.find("img"), c = this.settings.nestedItemSelector ? "." + this.settings.nestedItemSelector: d, e = this.$element.children(c).width(), b.length && 0 >= e) return this.preloadAutoWidthImages(b),
      !1
    }
    this.$element.addClass("owl-loading"),
    this.$stage = a("<" + this.settings.stageElement + ' class="owl-stage"/>').wrap('<div class="owl-stage-outer">'),
    this.$element.append(this.$stage.parent()),
    this.replace(this.$element.children().not(this.$stage.parent())),
    this._width = this.$element.width(),
    this.refresh(),
    this.$element.removeClass("owl-loading").addClass("owl-loaded"),
    this.eventsCall(),
    this.internalEvents(),
    this.addTriggerableEvents(),
    this.trigger("initialized")
  },
  e.prototype.setup = function() {
    var b = this.viewport(),
    c = this.options.responsive,
    d = -1,
    e = null;
    c ? (a.each(c,
    function(a) {
      b >= a && a > d && (d = Number(a))
    }), e = a.extend({},
    this.options, c[d]), delete e.responsive, e.responsiveClass && this.$element.attr("class",
    function(a, b) {
      return b.replace(/\b owl-responsive-\S+/g, "")
    }).addClass("owl-responsive-" + d)) : e = a.extend({},
    this.options),
    (null === this.settings || this._breakpoint !== d) && (this.trigger("change", {
      property: {
        name: "settings",
        value: e
      }
    }), this._breakpoint = d, this.settings = e, this.invalidate("settings"), this.trigger("changed", {
      property: {
        name: "settings",
        value: this.settings
      }
    }))
  },
  e.prototype.optionsLogic = function() {
    this.$element.toggleClass("owl-center", this.settings.center),
    this.settings.loop && this._items.length < this.settings.items && (this.settings.loop = !1),
    this.settings.autoWidth && (this.settings.stagePadding = !1, this.settings.merge = !1)
  },
  e.prototype.prepare = function(b) {
    var c = this.trigger("prepare", {
      content: b
    });
    return c.data || (c.data = a("<" + this.settings.itemElement + "/>").addClass(this.settings.itemClass).append(b)),
    this.trigger("prepared", {
      content: c.data
    }),
    c.data
  },
  e.prototype.update = function() {
    for (var b = 0,
    c = this._pipe.length,
    d = a.proxy(function(a) {
      return this[a]
    },
    this._invalidated), e = {}; c > b;)(this._invalidated.all || a.grep(this._pipe[b].filter, d).length > 0) && this._pipe[b].run(e),
    b++;
    this._invalidated = {}
  },
  e.prototype.width = function(a) {
    switch (a = a || e.Width.Default) {
    case e.Width.Inner:
    case e.Width.Outer:
      return this._width;
    default:
      return this._width - 2 * this.settings.stagePadding + this.settings.margin
    }
  },
  e.prototype.refresh = function() {
    if (0 === this._items.length) return ! 1; (new Date).getTime();
    this.trigger("refresh"),
    this.setup(),
    this.optionsLogic(),
    this.$stage.addClass("owl-refresh"),
    this.update(),
    this.$stage.removeClass("owl-refresh"),
    this.state.orientation = b.orientation,
    this.watchVisibility(),
    this.trigger("refreshed")
  },
  e.prototype.eventsCall = function() {
    this.e._onDragStart = a.proxy(function(a) {
      this.onDragStart(a)
    },
    this),
    this.e._onDragMove = a.proxy(function(a) {
      this.onDragMove(a)
    },
    this),
    this.e._onDragEnd = a.proxy(function(a) {
      this.onDragEnd(a)
    },
    this),
    this.e._onResize = a.proxy(function(a) {
      this.onResize(a)
    },
    this),
    this.e._transitionEnd = a.proxy(function(a) {
      this.transitionEnd(a)
    },
    this),
    this.e._preventClick = a.proxy(function(a) {
      this.preventClick(a)
    },
    this)
  },
  e.prototype.onThrottledResize = function() {
    b.clearTimeout(this.resizeTimer),
    this.resizeTimer = b.setTimeout(this.e._onResize, this.settings.responsiveRefreshRate)
  },
  e.prototype.onResize = function() {
    return this._items.length ? this._width === this.$element.width() ? !1 : this.trigger("resize").isDefaultPrevented() ? !1 : (this._width = this.$element.width(), this.invalidate("width"), this.refresh(), void this.trigger("resized")) : !1
  },
  e.prototype.eventsRouter = function(a) {
    var b = a.type;
    "mousedown" === b || "touchstart" === b ? this.onDragStart(a) : "mousemove" === b || "touchmove" === b ? this.onDragMove(a) : "mouseup" === b || "touchend" === b ? this.onDragEnd(a) : "touchcancel" === b && this.onDragEnd(a)
  },
  e.prototype.internalEvents = function() {
    var c = (k(), l());
    this.settings.mouseDrag ? (this.$stage.on("mousedown", a.proxy(function(a) {
      this.eventsRouter(a)
    },
    this)), this.$stage.on("dragstart",
    function() {
      return ! 1
    }), this.$stage.get(0).onselectstart = function() {
      return ! 1
    }) : this.$element.addClass("owl-text-select-on"),
    this.settings.touchDrag && !c && this.$stage.on("touchstart touchcancel", a.proxy(function(a) {
      this.eventsRouter(a)
    },
    this)),
    this.transitionEndVendor && this.on(this.$stage.get(0), this.transitionEndVendor, this.e._transitionEnd, !1),
    this.settings.responsive !== !1 && this.on(b, "resize", a.proxy(this.onThrottledResize, this))
  },
  e.prototype.onDragStart = function(d) {
    var e, g, h, i;
    if (e = d.originalEvent || d || b.event, 3 === e.which || this.state.isTouch) return ! 1;
    if ("mousedown" === e.type && this.$stage.addClass("owl-grab"), this.trigger("drag"), this.drag.startTime = (new Date).getTime(), this.speed(0), this.state.isTouch = !0, this.state.isScrolling = !1, this.state.isSwiping = !1, this.drag.distance = 0, g = f(e).x, h = f(e).y, this.drag.offsetX = this.$stage.position().left, this.drag.offsetY = this.$stage.position().top, this.settings.rtl && (this.drag.offsetX = this.$stage.position().left + this.$stage.width() - this.width() + this.settings.margin), this.state.inMotion && this.support3d) i = this.getTransformProperty(),
    this.drag.offsetX = i,
    this.animate(i),
    this.state.inMotion = !0;
    else if (this.state.inMotion && !this.support3d) return this.state.inMotion = !1,
    !1;
    this.drag.startX = g - this.drag.offsetX,
    this.drag.startY = h - this.drag.offsetY,
    this.drag.start = g - this.drag.startX,
    this.drag.targetEl = e.target || e.srcElement,
    this.drag.updatedX = this.drag.start,
    ("IMG" === this.drag.targetEl.tagName || "A" === this.drag.targetEl.tagName) && (this.drag.targetEl.draggable = !1),
    a(c).on("mousemove.owl.dragEvents mouseup.owl.dragEvents touchmove.owl.dragEvents touchend.owl.dragEvents", a.proxy(function(a) {
      this.eventsRouter(a)
    },
    this))
  },
  e.prototype.onDragMove = function(a) {
    var c, e, g, h, i, j;
    this.state.isTouch && (this.state.isScrolling || (c = a.originalEvent || a || b.event, e = f(c).x, g = f(c).y, this.drag.currentX = e - this.drag.startX, this.drag.currentY = g - this.drag.startY, this.drag.distance = this.drag.currentX - this.drag.offsetX, this.drag.distance < 0 ? this.state.direction = this.settings.rtl ? "right": "left": this.drag.distance > 0 && (this.state.direction = this.settings.rtl ? "left": "right"), this.settings.loop ? this.op(this.drag.currentX, ">", this.coordinates(this.minimum())) && "right" === this.state.direction ? this.drag.currentX -= (this.settings.center && this.coordinates(0)) - this.coordinates(this._items.length) : this.op(this.drag.currentX, "<", this.coordinates(this.maximum())) && "left" === this.state.direction && (this.drag.currentX += (this.settings.center && this.coordinates(0)) - this.coordinates(this._items.length)) : (h = this.coordinates(this.settings.rtl ? this.maximum() : this.minimum()), i = this.coordinates(this.settings.rtl ? this.minimum() : this.maximum()), j = this.settings.pullDrag ? this.drag.distance / 5 : 0, this.drag.currentX = Math.max(Math.min(this.drag.currentX, h + j), i + j)), (this.drag.distance > 8 || this.drag.distance < -8) && (c.preventDefault !== d ? c.preventDefault() : c.returnValue = !1, this.state.isSwiping = !0), this.drag.updatedX = this.drag.currentX, (this.drag.currentY > 16 || this.drag.currentY < -16) && this.state.isSwiping === !1 && (this.state.isScrolling = !0, this.drag.updatedX = this.drag.start), this.animate(this.drag.updatedX)))
  },
  e.prototype.onDragEnd = function(b) {
    var d, e, f;
    if (this.state.isTouch) {
      if ("mouseup" === b.type && this.$stage.removeClass("owl-grab"), this.trigger("dragged"), this.drag.targetEl.removeAttribute("draggable"), this.state.isTouch = !1, this.state.isScrolling = !1, this.state.isSwiping = !1, 0 === this.drag.distance && this.state.inMotion !== !0) return this.state.inMotion = !1,
      !1;
      this.drag.endTime = (new Date).getTime(),
      d = this.drag.endTime - this.drag.startTime,
      e = Math.abs(this.drag.distance),
      (e > 3 || d > 300) && this.removeClick(this.drag.targetEl),
      f = this.closest(this.drag.updatedX),
      this.speed(this.settings.dragEndSpeed || this.settings.smartSpeed),
      this.current(f),
      this.invalidate("position"),
      this.update(),
      this.settings.pullDrag || this.drag.updatedX !== this.coordinates(f) || this.transitionEnd(),
      this.drag.distance = 0,
      a(c).off(".owl.dragEvents")
    }
  },
  e.prototype.removeClick = function(c) {
    this.drag.targetEl = c,
    a(c).on("click.preventClick", this.e._preventClick),
    b.setTimeout(function() {
      a(c).off("click.preventClick")
    },
    300)
  },
  e.prototype.preventClick = function(b) {
    b.preventDefault ? b.preventDefault() : b.returnValue = !1,
    b.stopPropagation && b.stopPropagation(),
    a(b.target).off("click.preventClick")
  },
  e.prototype.getTransformProperty = function() {
    var a, c;
    return a = b.getComputedStyle(this.$stage.get(0), null).getPropertyValue(this.vendorName + "transform"),
    a = a.replace(/matrix(3d)?\(|\)/g, "").split(","),
    c = 16 === a.length,
    c !== !0 ? a[4] : a[12]
  },
  e.prototype.closest = function(b) {
    var c = -1,
    d = 30,
    e = this.width(),
    f = this.coordinates();
    return this.settings.freeDrag || a.each(f, a.proxy(function(a, g) {
      return b > g - d && g + d > b ? c = a: this.op(b, "<", g) && this.op(b, ">", f[a + 1] || g - e) && (c = "left" === this.state.direction ? a + 1 : a),
      -1 === c
    },
    this)),
    this.settings.loop || (this.op(b, ">", f[this.minimum()]) ? c = b = this.minimum() : this.op(b, "<", f[this.maximum()]) && (c = b = this.maximum())),
    c
  },
  e.prototype.animate = function(b) {
    this.trigger("translate"),
    this.state.inMotion = this.speed() > 0,
    this.support3d ? this.$stage.css({
      transform: "translate3d(" + b + "px,0px, 0px)",
      transition: this.speed() / 1e3 + "s"
    }) : this.state.isTouch ? this.$stage.css({
      left: b + "px"
    }) : this.$stage.animate({
      left: b
    },
    this.speed() / 1e3, this.settings.fallbackEasing, a.proxy(function() {
      this.state.inMotion && this.transitionEnd()
    },
    this))
  },
  e.prototype.current = function(a) {
    if (a === d) return this._current;
    if (0 === this._items.length) return d;
    if (a = this.normalize(a), this._current !== a) {
      var b = this.trigger("change", {
        property: {
          name: "position",
          value: a
        }
      });
      b.data !== d && (a = this.normalize(b.data)),
      this._current = a,
      this.invalidate("position"),
      this.trigger("changed", {
        property: {
          name: "position",
          value: this._current
        }
      })
    }
    return this._current
  },
  e.prototype.invalidate = function(a) {
    this._invalidated[a] = !0
  },
  e.prototype.reset = function(a) {
    a = this.normalize(a),
    a !== d && (this._speed = 0, this._current = a, this.suppress(["translate", "translated"]), this.animate(this.coordinates(a)), this.release(["translate", "translated"]))
  },
  e.prototype.normalize = function(b, c) {
    var e = c ? this._items.length: this._items.length + this._clones.length;
    return ! a.isNumeric(b) || 1 > e ? d: b = this._clones.length ? (b % e + e) % e: Math.max(this.minimum(c), Math.min(this.maximum(c), b))
  },
  e.prototype.relative = function(a) {
    return a = this.normalize(a),
    a -= this._clones.length / 2,
    this.normalize(a, !0)
  },
  e.prototype.maximum = function(a) {
    var b, c, d, e = 0,
    f = this.settings;
    if (a) return this._items.length - 1;
    if (!f.loop && f.center) b = this._items.length - 1;
    else if (f.loop || f.center) if (f.loop || f.center) b = this._items.length + f.items;
    else {
      if (!f.autoWidth && !f.merge) throw "Can not detect maximum absolute position.";
      for (revert = f.rtl ? 1 : -1, c = this.$stage.width() - this.$element.width(); (d = this.coordinates(e)) && !(d * revert >= c);) b = ++e
    } else b = this._items.length - f.items;
    return b
  },
  e.prototype.minimum = function(a) {
    return a ? 0 : this._clones.length / 2
  },
  e.prototype.items = function(a) {
    return a === d ? this._items.slice() : (a = this.normalize(a, !0), this._items[a])
  },
  e.prototype.mergers = function(a) {
    return a === d ? this._mergers.slice() : (a = this.normalize(a, !0), this._mergers[a])
  },
  e.prototype.clones = function(b) {
    var c = this._clones.length / 2,
    e = c + this._items.length,
    f = function(a) {
      return a % 2 === 0 ? e + a / 2 : c - (a + 1) / 2
    };
    return b === d ? a.map(this._clones,
    function(a, b) {
      return f(b)
    }) : a.map(this._clones,
    function(a, c) {
      return a === b ? f(c) : null
    })
  },
  e.prototype.speed = function(a) {
    return a !== d && (this._speed = a),
    this._speed
  },
  e.prototype.coordinates = function(b) {
    var c = null;
    return b === d ? a.map(this._coordinates, a.proxy(function(a, b) {
      return this.coordinates(b)
    },
    this)) : (this.settings.center ? (c = this._coordinates[b], c += (this.width() - c + (this._coordinates[b - 1] || 0)) / 2 * (this.settings.rtl ? -1 : 1)) : c = this._coordinates[b - 1] || 0, c)
  },
  e.prototype.duration = function(a, b, c) {
    return Math.min(Math.max(Math.abs(b - a), 1), 6) * Math.abs(c || this.settings.smartSpeed)
  },
  e.prototype.to = function(c, d) {
    if (this.settings.loop) {
      var e = c - this.relative(this.current()),
      f = this.current(),
      g = this.current(),
      h = this.current() + e,
      i = 0 > g - h ? !0 : !1,
      j = this._clones.length + this._items.length;
      h < this.settings.items && i === !1 ? (f = g + this._items.length, this.reset(f)) : h >= j - this.settings.items && i === !0 && (f = g - this._items.length, this.reset(f)),
      b.clearTimeout(this.e._goToLoop),
      this.e._goToLoop = b.setTimeout(a.proxy(function() {
        this.speed(this.duration(this.current(), f + e, d)),
        this.current(f + e),
        this.update()
      },
      this), 30)
    } else this.speed(this.duration(this.current(), c, d)),
    this.current(c),
    this.update()
  },
  e.prototype.next = function(a) {
    a = a || !1,
    this.to(this.relative(this.current()) + 1, a)
  },
  e.prototype.prev = function(a) {
    a = a || !1,
    this.to(this.relative(this.current()) - 1, a)
  },
  e.prototype.transitionEnd = function(a) {
    return a !== d && (a.stopPropagation(), (a.target || a.srcElement || a.originalTarget) !== this.$stage.get(0)) ? !1 : (this.state.inMotion = !1, void this.trigger("translated"))
  },
  e.prototype.viewport = function() {
    var d;
    if (this.options.responsiveBaseElement !== b) d = a(this.options.responsiveBaseElement).width();
    else if (b.innerWidth) d = b.innerWidth;
    else {
      if (!c.documentElement || !c.documentElement.clientWidth) throw "Can not detect viewport width.";
      d = c.documentElement.clientWidth
    }
    return d
  },
  e.prototype.replace = function(b) {
    this.$stage.empty(),
    this._items = [],
    b && (b = b instanceof jQuery ? b: a(b)),
    this.settings.nestedItemSelector && (b = b.find("." + this.settings.nestedItemSelector)),
    b.filter(function() {
      return 1 === this.nodeType
    }).each(a.proxy(function(a, b) {
      b = this.prepare(b),
      this.$stage.append(b),
      this._items.push(b),
      this._mergers.push(1 * b.find("[data-merge]").andSelf("[data-merge]").attr("data-merge") || 1)
    },
    this)),
    this.reset(a.isNumeric(this.settings.startPosition) ? this.settings.startPosition: 0),
    this.invalidate("items")
  },
  e.prototype.add = function(a, b) {
    b = b === d ? this._items.length: this.normalize(b, !0),
    this.trigger("add", {
      content: a,
      position: b
    }),
    0 === this._items.length || b === this._items.length ? (this.$stage.append(a), this._items.push(a), this._mergers.push(1 * a.find("[data-merge]").andSelf("[data-merge]").attr("data-merge") || 1)) : (this._items[b].before(a), this._items.splice(b, 0, a), this._mergers.splice(b, 0, 1 * a.find("[data-merge]").andSelf("[data-merge]").attr("data-merge") || 1)),
    this.invalidate("items"),
    this.trigger("added", {
      content: a,
      position: b
    })
  },
  e.prototype.remove = function(a) {
    a = this.normalize(a, !0),
    a !== d && (this.trigger("remove", {
      content: this._items[a],
      position: a
    }), this._items[a].remove(), this._items.splice(a, 1), this._mergers.splice(a, 1), this.invalidate("items"), this.trigger("removed", {
      content: null,
      position: a
    }))
  },
  e.prototype.addTriggerableEvents = function() {
    var b = a.proxy(function(b, c) {
      return a.proxy(function(a) {
        a.relatedTarget !== this && (this.suppress([c]), b.apply(this, [].slice.call(arguments, 1)), this.release([c]))
      },
      this)
    },
    this);
    a.each({
      next: this.next,
      prev: this.prev,
      to: this.to,
      destroy: this.destroy,
      refresh: this.refresh,
      replace: this.replace,
      add: this.add,
      remove: this.remove
    },
    a.proxy(function(a, c) {
      this.$element.on(a + ".owl.carousel", b(c, a + ".owl.carousel"))
    },
    this))
  },
  e.prototype.watchVisibility = function() {
    function c(a) {
      return a.offsetWidth > 0 && a.offsetHeight > 0
    }
    function d() {
      c(this.$element.get(0)) && (this.$element.removeClass("owl-hidden"), this.refresh(), b.clearInterval(this.e._checkVisibile))
    }
    c(this.$element.get(0)) || (this.$element.addClass("owl-hidden"), b.clearInterval(this.e._checkVisibile), this.e._checkVisibile = b.setInterval(a.proxy(d, this), 500))
  },
  e.prototype.preloadAutoWidthImages = function(b) {
    var c, d, e, f;
    c = 0,
    d = this,
    b.each(function(g, h) {
      e = a(h),
      f = new Image,
      f.onload = function() {
        c++,
        e.attr("src", f.src),
        e.css("opacity", 1),
        c >= b.length && (d.state.imagesLoaded = !0, d.initialize())
      },
      f.src = e.attr("src") || e.attr("data-src") || e.attr("data-src-retina")
    })
  },
  e.prototype.destroy = function() {
    this.$element.hasClass(this.settings.themeClass) && this.$element.removeClass(this.settings.themeClass),
    this.settings.responsive !== !1 && a(b).off("resize.owl.carousel"),
    this.transitionEndVendor && this.off(this.$stage.get(0), this.transitionEndVendor, this.e._transitionEnd);
    for (var d in this._plugins) this._plugins[d].destroy(); (this.settings.mouseDrag || this.settings.touchDrag) && (this.$stage.off("mousedown touchstart touchcancel"), a(c).off(".owl.dragEvents"), this.$stage.get(0).onselectstart = function() {},
    this.$stage.off("dragstart",
    function() {
      return ! 1
    })),
    this.$element.off(".owl"),
    this.$stage.children(".cloned").remove(),
    this.e = null,
    this.$element.removeData("owlCarousel"),
    this.$stage.children().contents().unwrap(),
    this.$stage.children().unwrap(),
    this.$stage.unwrap()
  },
  e.prototype.op = function(a, b, c) {
    var d = this.settings.rtl;
    switch (b) {
    case "<":
      return d ? a > c: c > a;
    case ">":
      return d ? c > a: a > c;
    case ">=":
      return d ? c >= a: a >= c;
    case "<=":
      return d ? a >= c: c >= a
    }
  },
  e.prototype.on = function(a, b, c, d) {
    a.addEventListener ? a.addEventListener(b, c, d) : a.attachEvent && a.attachEvent("on" + b, c)
  },
  e.prototype.off = function(a, b, c, d) {
    a.removeEventListener ? a.removeEventListener(b, c, d) : a.detachEvent && a.detachEvent("on" + b, c)
  },
  e.prototype.trigger = function(b, c, d) {
    var e = {
      item: {
        count: this._items.length,
        index: this.current()
      }
    },
    f = a.camelCase(a.grep(["on", b, d],
    function(a) {
      return a
    }).join("-").toLowerCase()),
    g = a.Event([b, "owl", d || "carousel"].join(".").toLowerCase(), a.extend({
      relatedTarget: this
    },
    e, c));
    return this._supress[b] || (a.each(this._plugins,
    function(a, b) {
      b.onTrigger && b.onTrigger(g)
    }), this.$element.trigger(g), this.settings && "function" == typeof this.settings[f] && this.settings[f].apply(this, g)),
    g
  },
  e.prototype.suppress = function(b) {
    a.each(b, a.proxy(function(a, b) {
      this._supress[b] = !0
    },
    this))
  },
  e.prototype.release = function(b) {
    a.each(b, a.proxy(function(a, b) {
      delete this._supress[b]
    },
    this))
  },
  e.prototype.browserSupport = function() {
    if (this.support3d = j(), this.support3d) {
      this.transformVendor = i();
      var a = ["transitionend", "webkitTransitionEnd", "transitionend", "oTransitionEnd"];
      this.transitionEndVendor = a[h()],
      this.vendorName = this.transformVendor.replace(/Transform/i, ""),
      this.vendorName = "" !== this.vendorName ? "-" + this.vendorName.toLowerCase() + "-": ""
    }
    this.state.orientation = b.orientation
  },
  a.fn.owlCarousel = function(b) {
    return this.each(function() {
      a(this).data("owlCarousel") || a(this).data("owlCarousel", new e(this, b))
    })
  },
  a.fn.owlCarousel.Constructor = e
} (window.Zepto || window.jQuery, window, document),
function(a, b) {
  var c = function(b) {
    this._core = b,
    this._loaded = [],
    this._handlers = {
      "initialized.owl.carousel change.owl.carousel": a.proxy(function(b) {
        if (b.namespace && this._core.settings && this._core.settings.lazyLoad && (b.property && "position" == b.property.name || "initialized" == b.type)) for (var c = this._core.settings,
        d = c.center && Math.ceil(c.items / 2) || c.items, e = c.center && -1 * d || 0, f = (b.property && b.property.value || this._core.current()) + e, g = this._core.clones().length, h = a.proxy(function(a, b) {
          this.load(b)
        },
        this); e++<d;) this.load(g / 2 + this._core.relative(f)),
        g && a.each(this._core.clones(this._core.relative(f++)), h)
      },
      this)
    },
    this._core.options = a.extend({},
    c.Defaults, this._core.options),
    this._core.$element.on(this._handlers)
  };
  c.Defaults = {
    lazyLoad: !1
  },
  c.prototype.load = function(c) {
    var d = this._core.$stage.children().eq(c),
    e = d && d.find(".owl-lazy"); ! e || a.inArray(d.get(0), this._loaded) > -1 || (e.each(a.proxy(function(c, d) {
      var e, f = a(d),
      g = b.devicePixelRatio > 1 && f.attr("data-src-retina") || f.attr("data-src");
      this._core.trigger("load", {
        element: f,
        url: g
      },
      "lazy"),
      f.is("img") ? f.one("load.owl.lazy", a.proxy(function() {
        f.css("opacity", 1),
        this._core.trigger("loaded", {
          element: f,
          url: g
        },
        "lazy")
      },
      this)).attr("src", g) : (e = new Image, e.onload = a.proxy(function() {
        f.css({
          "background-image": "url(" + g + ")",
          opacity: "1"
        }),
        this._core.trigger("loaded", {
          element: f,
          url: g
        },
        "lazy")
      },
      this), e.src = g)
    },
    this)), this._loaded.push(d.get(0)))
  },
  c.prototype.destroy = function() {
    var a, b;
    for (a in this.handlers) this._core.$element.off(a, this.handlers[a]);
    for (b in Object.getOwnPropertyNames(this))"function" != typeof this[b] && (this[b] = null)
  },
  a.fn.owlCarousel.Constructor.Plugins.Lazy = c
} (window.Zepto || window.jQuery, window, document),
function(a) {
  var b = function(c) {
    this._core = c,
    this._handlers = {
      "initialized.owl.carousel": a.proxy(function() {
        this._core.settings.autoHeight && this.update()
      },
      this),
      "changed.owl.carousel": a.proxy(function(a) {
        this._core.settings.autoHeight && "position" == a.property.name && this.update()
      },
      this),
      "loaded.owl.lazy": a.proxy(function(a) {
        this._core.settings.autoHeight && a.element.closest("." + this._core.settings.itemClass) === this._core.$stage.children().eq(this._core.current()) && this.update()
      },
      this)
    },
    this._core.options = a.extend({},
    b.Defaults, this._core.options),
    this._core.$element.on(this._handlers)
  };
  b.Defaults = {
    autoHeight: !1,
    autoHeightClass: "owl-height"
  },
  b.prototype.update = function() {
    this._core.$stage.parent().height(this._core.$stage.children().eq(this._core.current()).height()).addClass(this._core.settings.autoHeightClass)
  },
  b.prototype.destroy = function() {
    var a, b;
    for (a in this._handlers) this._core.$element.off(a, this._handlers[a]);
    for (b in Object.getOwnPropertyNames(this))"function" != typeof this[b] && (this[b] = null)
  },
  a.fn.owlCarousel.Constructor.Plugins.AutoHeight = b
} (window.Zepto || window.jQuery, window, document),
function(a, b, c) {
  var d = function(b) {
    this._core = b,
    this._videos = {},
    this._playing = null,
    this._fullscreen = !1,
    this._handlers = {
      "resize.owl.carousel": a.proxy(function(a) {
        this._core.settings.video && !this.isInFullScreen() && a.preventDefault()
      },
      this),
      "refresh.owl.carousel changed.owl.carousel": a.proxy(function() {
        this._playing && this.stop()
      },
      this),
      "prepared.owl.carousel": a.proxy(function(b) {
        var c = a(b.content).find(".owl-video");
        c.length && (c.css("display", "none"), this.fetch(c, a(b.content)))
      },
      this)
    },
    this._core.options = a.extend({},
    d.Defaults, this._core.options),
    this._core.$element.on(this._handlers),
    this._core.$element.on("click.owl.video", ".owl-video-play-icon", a.proxy(function(a) {
      this.play(a)
    },
    this))
  };
  d.Defaults = {
    video: !1,
    videoHeight: !1,
    videoWidth: !1
  },
  d.prototype.fetch = function(a, b) {
    var c = a.attr("data-vimeo-id") ? "vimeo": "youtube",
    d = a.attr("data-vimeo-id") || a.attr("data-youtube-id"),
    e = a.attr("data-width") || this._core.settings.videoWidth,
    f = a.attr("data-height") || this._core.settings.videoHeight,
    g = a.attr("href");
    if (!g) throw new Error("Missing video URL.");
    if (d = g.match(/(http:|https:|)\/\/(player.|www.)?(vimeo\.com|youtu(be\.com|\.be|be\.googleapis\.com))\/(video\/|embed\/|watch\?v=|v\/)?([A-Za-z0-9._%-]*)(\&\S+)?/), d[3].indexOf("youtu") > -1) c = "youtube";
    else {
      if (! (d[3].indexOf("vimeo") > -1)) throw new Error("Video URL not supported.");
      c = "vimeo"
    }
    d = d[6],
    this._videos[g] = {
      type: c,
      id: d,
      width: e,
      height: f
    },
    b.attr("data-video", g),
    this.thumbnail(a, this._videos[g])
  },
  d.prototype.thumbnail = function(b, c) {
    var d, e, f, g = c.width && c.height ? 'style="width:' + c.width + "px;height:" + c.height + 'px;"': "",
    h = b.find("img"),
    i = "src",
    j = "",
    k = this._core.settings,
    l = function(a) {
      e = '<div class="owl-video-play-icon"></div>',
      d = k.lazyLoad ? '<div class="owl-video-tn ' + j + '" ' + i + '="' + a + '"></div>': '<div class="owl-video-tn" style="opacity:1;background-image:url(' + a + ')"></div>',
      b.after(d),
      b.after(e)
    };
    return b.wrap('<div class="owl-video-wrapper"' + g + "></div>"),
    this._core.settings.lazyLoad && (i = "data-src", j = "owl-lazy"),
    h.length ? (l(h.attr(i)), h.remove(), !1) : void("youtube" === c.type ? (f = "http://img.youtube.com/vi/" + c.id + "/hqdefault.jpg", l(f)) : "vimeo" === c.type && a.ajax({
      type: "GET",
      url: "http://vimeo.com/api/v2/video/" + c.id + ".json",
      jsonp: "callback",
      dataType: "jsonp",
      success: function(a) {
        f = a[0].thumbnail_large,
        l(f)
      }
    }))
  },
  d.prototype.stop = function() {
    this._core.trigger("stop", null, "video"),
    this._playing.find(".owl-video-frame").remove(),
    this._playing.removeClass("owl-video-playing"),
    this._playing = null
  },
  d.prototype.play = function(b) {
    this._core.trigger("play", null, "video"),
    this._playing && this.stop();
    var c, d, e = a(b.target || b.srcElement),
    f = e.closest("." + this._core.settings.itemClass),
    g = this._videos[f.attr("data-video")],
    h = g.width || "100%",
    i = g.height || this._core.$stage.height();
    "youtube" === g.type ? c = '<iframe width="' + h + '" height="' + i + '" src="http://www.youtube.com/embed/' + g.id + "?autoplay=1&v=" + g.id + '" frameborder="0" allowfullscreen></iframe>': "vimeo" === g.type && (c = '<iframe src="http://player.vimeo.com/video/' + g.id + '?autoplay=1" width="' + h + '" height="' + i + '" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>'),
    f.addClass("owl-video-playing"),
    this._playing = f,
    d = a('<div style="height:' + i + "px; width:" + h + 'px" class="owl-video-frame">' + c + "</div>"),
    e.after(d)
  },
  d.prototype.isInFullScreen = function() {
    var d = c.fullscreenElement || c.mozFullScreenElement || c.webkitFullscreenElement;
    return d && a(d).parent().hasClass("owl-video-frame") && (this._core.speed(0), this._fullscreen = !0),
    d && this._fullscreen && this._playing ? !1 : this._fullscreen ? (this._fullscreen = !1, !1) : this._playing && this._core.state.orientation !== b.orientation ? (this._core.state.orientation = b.orientation, !1) : !0
  },
  d.prototype.destroy = function() {
    var a, b;
    this._core.$element.off("click.owl.video");
    for (a in this._handlers) this._core.$element.off(a, this._handlers[a]);
    for (b in Object.getOwnPropertyNames(this))"function" != typeof this[b] && (this[b] = null)
  },
  a.fn.owlCarousel.Constructor.Plugins.Video = d
} (window.Zepto || window.jQuery, window, document),
function(a, b, c, d) {
  var e = function(b) {
    this.core = b,
    this.core.options = a.extend({},
    e.Defaults, this.core.options),
    this.swapping = !0,
    this.previous = d,
    this.next = d,
    this.handlers = {
      "change.owl.carousel": a.proxy(function(a) {
        "position" == a.property.name && (this.previous = this.core.current(), this.next = a.property.value)
      },
      this),
      "drag.owl.carousel dragged.owl.carousel translated.owl.carousel": a.proxy(function(a) {
        this.swapping = "translated" == a.type
      },
      this),
      "translate.owl.carousel": a.proxy(function() {
        this.swapping && (this.core.options.animateOut || this.core.options.animateIn) && this.swap()
      },
      this)
    },
    this.core.$element.on(this.handlers)
  };
  e.Defaults = {
    animateOut: !1,
    animateIn: !1
  },
  e.prototype.swap = function() {
    if (1 === this.core.settings.items && this.core.support3d) {
      this.core.speed(0);
      var b, c = a.proxy(this.clear, this),
      d = this.core.$stage.children().eq(this.previous),
      e = this.core.$stage.children().eq(this.next),
      f = this.core.settings.animateIn,
      g = this.core.settings.animateOut;
      this.core.current() !== this.previous && (g && (b = this.core.coordinates(this.previous) - this.core.coordinates(this.next), d.css({
        left: b + "px"
      }).addClass("animated owl-animated-out").addClass(g).one("webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend", c)), f && e.addClass("animated owl-animated-in").addClass(f).one("webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend", c))
    }
  },
  e.prototype.clear = function(b) {
    a(b.target).css({
      left: ""
    }).removeClass("animated owl-animated-out owl-animated-in").removeClass(this.core.settings.animateIn).removeClass(this.core.settings.animateOut),
    this.core.transitionEnd()
  },
  e.prototype.destroy = function() {
    var a, b;
    for (a in this.handlers) this.core.$element.off(a, this.handlers[a]);
    for (b in Object.getOwnPropertyNames(this))"function" != typeof this[b] && (this[b] = null)
  },
  a.fn.owlCarousel.Constructor.Plugins.Animate = e
} (window.Zepto || window.jQuery, window, document),
function(a, b, c) {
  var d = function(b) {
    this.core = b,
    this.core.options = a.extend({},
    d.Defaults, this.core.options),
    this.handlers = {
      "translated.owl.carousel refreshed.owl.carousel": a.proxy(function() {
        this.autoplay()
      },
      this),
      "play.owl.autoplay": a.proxy(function(a, b, c) {
        this.play(b, c)
      },
      this),
      "stop.owl.autoplay": a.proxy(function() {
        this.stop()
      },
      this),
      "mouseover.owl.autoplay": a.proxy(function() {
        this.core.settings.autoplayHoverPause && this.pause()
      },
      this),
      "mouseleave.owl.autoplay": a.proxy(function() {
        this.core.settings.autoplayHoverPause && this.autoplay()
      },
      this)
    },
    this.core.$element.on(this.handlers)
  };
  d.Defaults = {
    autoplay: !1,
    autoplayTimeout: 5e3,
    autoplayHoverPause: !1,
    autoplaySpeed: !1
  },
  d.prototype.autoplay = function() {
    this.core.settings.autoplay && !this.core.state.videoPlay ? (b.clearInterval(this.interval), this.interval = b.setInterval(a.proxy(function() {
      this.play()
    },
    this), this.core.settings.autoplayTimeout)) : b.clearInterval(this.interval)
  },
  d.prototype.play = function() {
    return c.hidden === !0 || this.core.state.isTouch || this.core.state.isScrolling || this.core.state.isSwiping || this.core.state.inMotion ? void 0 : this.core.settings.autoplay === !1 ? void b.clearInterval(this.interval) : void this.core.next(this.core.settings.autoplaySpeed)
  },
  d.prototype.stop = function() {
    b.clearInterval(this.interval)
  },
  d.prototype.pause = function() {
    b.clearInterval(this.interval)
  },
  d.prototype.destroy = function() {
    var a, c;
    b.clearInterval(this.interval);
    for (a in this.handlers) this.core.$element.off(a, this.handlers[a]);
    for (c in Object.getOwnPropertyNames(this))"function" != typeof this[c] && (this[c] = null)
  },
  a.fn.owlCarousel.Constructor.Plugins.autoplay = d
} (window.Zepto || window.jQuery, window, document),
function(a) {
  "use strict";
  var b = function(c) {
    this._core = c,
    this._initialized = !1,
    this._pages = [],
    this._controls = {},
    this._templates = [],
    this.$element = this._core.$element,
    this._overrides = {
      next: this._core.next,
      prev: this._core.prev,
      to: this._core.to
    },
    this._handlers = {
      "prepared.owl.carousel": a.proxy(function(b) {
        this._core.settings.dotsData && this._templates.push(a(b.content).find("[data-dot]").andSelf("[data-dot]").attr("data-dot"))
      },
      this),
      "add.owl.carousel": a.proxy(function(b) {
        this._core.settings.dotsData && this._templates.splice(b.position, 0, a(b.content).find("[data-dot]").andSelf("[data-dot]").attr("data-dot"))
      },
      this),
      "remove.owl.carousel prepared.owl.carousel": a.proxy(function(a) {
        this._core.settings.dotsData && this._templates.splice(a.position, 1)
      },
      this),
      "change.owl.carousel": a.proxy(function(a) {
        if ("position" == a.property.name && !this._core.state.revert && !this._core.settings.loop && this._core.settings.navRewind) {
          var b = this._core.current(),
          c = this._core.maximum(),
          d = this._core.minimum();
          a.data = a.property.value > c ? b >= c ? d: c: a.property.value < d ? c: a.property.value
        }
      },
      this),
      "changed.owl.carousel": a.proxy(function(a) {
        "position" == a.property.name && this.draw()
      },
      this),
      "refreshed.owl.carousel": a.proxy(function() {
        this._initialized || (this.initialize(), this._initialized = !0),
        this._core.trigger("refresh", null, "navigation"),
        this.update(),
        this.draw(),
        this._core.trigger("refreshed", null, "navigation")
      },
      this)
    },
    this._core.options = a.extend({},
    b.Defaults, this._core.options),
    this.$element.on(this._handlers)
  };
  b.Defaults = {
    nav: !1,
    navRewind: !0,
    navText: ["prev", "next"],
    navSpeed: !1,
    navElement: "div",
    navContainer: !1,
    navContainerClass: "owl-nav",
    navClass: ["owl-prev", "owl-next"],
    slideBy: 1,
    dotClass: "owl-dot",
    dotsClass: "owl-dots",
    dots: !0,
    dotsEach: !1,
    dotData: !1,
    dotsSpeed: !1,
    dotsContainer: !1,
    controlsClass: "owl-controls"
  },
  b.prototype.initialize = function() {
    var b, c, d = this._core.settings;
    d.dotsData || (this._templates = [a("<div>").addClass(d.dotClass).append(a("<span>")).prop("outerHTML")]),
    d.navContainer && d.dotsContainer || (this._controls.$container = a("<div>").addClass(d.controlsClass).appendTo(this.$element)),
    this._controls.$indicators = d.dotsContainer ? a(d.dotsContainer) : a("<div>").hide().addClass(d.dotsClass).appendTo(this._controls.$container),
    this._controls.$indicators.on("click", "div", a.proxy(function(b) {
      var c = a(b.target).parent().is(this._controls.$indicators) ? a(b.target).index() : a(b.target).parent().index();
      b.preventDefault(),
      this.to(c, d.dotsSpeed)
    },
    this)),
    b = d.navContainer ? a(d.navContainer) : a("<div>").addClass(d.navContainerClass).prependTo(this._controls.$container),
    this._controls.$next = a("<" + d.navElement + ">"),
    this._controls.$previous = this._controls.$next.clone(),
    this._controls.$previous.addClass(d.navClass[0]).html(d.navText[0]).hide().prependTo(b).on("click", a.proxy(function() {
      this.prev(d.navSpeed)
    },
    this)),
    this._controls.$next.addClass(d.navClass[1]).html(d.navText[1]).hide().appendTo(b).on("click", a.proxy(function() {
      this.next(d.navSpeed)
    },
    this));
    for (c in this._overrides) this._core[c] = a.proxy(this[c], this)
  },
  b.prototype.destroy = function() {
    var a, b, c, d;
    for (a in this._handlers) this.$element.off(a, this._handlers[a]);
    for (b in this._controls) this._controls[b].remove();
    for (d in this.overides) this._core[d] = this._overrides[d];
    for (c in Object.getOwnPropertyNames(this))"function" != typeof this[c] && (this[c] = null)
  },
  b.prototype.update = function() {
    var a, b, c, d = this._core.settings,
    e = this._core.clones().length / 2,
    f = e + this._core.items().length,
    g = d.center || d.autoWidth || d.dotData ? 1 : d.dotsEach || d.items;
    if ("page" !== d.slideBy && (d.slideBy = Math.min(d.slideBy, d.items)), d.dots || "page" == d.slideBy) for (this._pages = [], a = e, b = 0, c = 0; f > a; a++)(b >= g || 0 === b) && (this._pages.push({
      start: a - e,
      end: a - e + g - 1
    }), b = 0, ++c),
    b += this._core.mergers(this._core.relative(a))
  },
  b.prototype.draw = function() {
    var b, c, d = "",
    e = this._core.settings,
    f = (this._core.$stage.children(), this._core.relative(this._core.current()));
    if (!e.nav || e.loop || e.navRewind || (this._controls.$previous.toggleClass("disabled", 0 >= f), this._controls.$next.toggleClass("disabled", f >= this._core.maximum())), this._controls.$previous.toggle(e.nav), this._controls.$next.toggle(e.nav), e.dots) {
      if (b = this._pages.length - this._controls.$indicators.children().length, e.dotData && 0 !== b) {
        for (c = 0; c < this._controls.$indicators.children().length; c++) d += this._templates[this._core.relative(c)];
        this._controls.$indicators.html(d)
      } else b > 0 ? (d = new Array(b + 1).join(this._templates[0]), this._controls.$indicators.append(d)) : 0 > b && this._controls.$indicators.children().slice(b).remove();
      this._controls.$indicators.find(".active").removeClass("active"),
      this._controls.$indicators.children().eq(a.inArray(this.current(), this._pages)).addClass("active")
    }
    this._controls.$indicators.toggle(e.dots)
  },
  b.prototype.onTrigger = function(b) {
    var c = this._core.settings;
    b.page = {
      index: a.inArray(this.current(), this._pages),
      count: this._pages.length,
      size: c && (c.center || c.autoWidth || c.dotData ? 1 : c.dotsEach || c.items)
    }
  },
  b.prototype.current = function() {
    var b = this._core.relative(this._core.current());
    return a.grep(this._pages,
    function(a) {
      return a.start <= b && a.end >= b
    }).pop()
  },
  b.prototype.getPosition = function(b) {
    var c, d, e = this._core.settings;
    return "page" == e.slideBy ? (c = a.inArray(this.current(), this._pages), d = this._pages.length, b ? ++c: --c, c = this._pages[(c % d + d) % d].start) : (c = this._core.relative(this._core.current()), d = this._core.items().length, b ? c += e.slideBy: c -= e.slideBy),
    c
  },
  b.prototype.next = function(b) {
    a.proxy(this._overrides.to, this._core)(this.getPosition(!0), b)
  },
  b.prototype.prev = function(b) {
    a.proxy(this._overrides.to, this._core)(this.getPosition(!1), b)
  },
  b.prototype.to = function(b, c, d) {
    var e;
    d ? a.proxy(this._overrides.to, this._core)(b, c) : (e = this._pages.length, a.proxy(this._overrides.to, this._core)(this._pages[(b % e + e) % e].start, c))
  },
  a.fn.owlCarousel.Constructor.Plugins.Navigation = b
} (window.Zepto || window.jQuery, window, document),
function(a, b) {
  "use strict";
  var c = function(d) {
    this._core = d,
    this._hashes = {},
    this.$element = this._core.$element,
    this._handlers = {
      "initialized.owl.carousel": a.proxy(function() {
        "URLHash" == this._core.settings.startPosition && a(b).trigger("hashchange.owl.navigation")
      },
      this),
      "prepared.owl.carousel": a.proxy(function(b) {
        var c = a(b.content).find("[data-hash]").andSelf("[data-hash]").attr("data-hash");
        this._hashes[c] = b.content
      },
      this)
    },
    this._core.options = a.extend({},
    c.Defaults, this._core.options),
    this.$element.on(this._handlers),
    a(b).on("hashchange.owl.navigation", a.proxy(function() {
      var a = b.location.hash.substring(1),
      c = this._core.$stage.children(),
      d = this._hashes[a] && c.index(this._hashes[a]) || 0;
      return a ? void this._core.to(d, !1, !0) : !1
    },
    this))
  };
  c.Defaults = {
    URLhashListener: !1
  },
  c.prototype.destroy = function() {
    var c, d;
    a(b).off("hashchange.owl.navigation");
    for (c in this._handlers) this._core.$element.off(c, this._handlers[c]);
    for (d in Object.getOwnPropertyNames(this))"function" != typeof this[d] && (this[d] = null)
  },
  a.fn.owlCarousel.Constructor.Plugins.Hash = c
} (window.Zepto || window.jQuery, window, document);



jQuery.fn.customInput = function(){
				$(this).each(function(i){
					if($(this).is('[type=checkbox],[type=radio]')){
						var input = $(this);
						//get the associated label using the input's id
						var label = $('label[for='+input.attr('id')+']');
						//get type,for classname suffix
						var inputType = (input.is('[type=checkbox]')) ? 'checkbox' : 'radio';
						//wrap the input + label in a div
						$('<div class="custom-'+ inputType +'"></div>').insertBefore(input).append(input,label);
						//find all inputs in this set using the shared name attribute
						var allInputs = $('input[name='+input.attr('name')+']');
						//necessary for browsers that don't support the :hover pseudo class on labels
						label.hover(function(){
							$(this).addClass('hover');
							if(inputType == 'checkbox' && input.is(':checked')) {
								$(this).addClass('checkedHover');
							}
						},function(){
							$(this).removeClass('hover checkedHover');
						});
						
						//bind custom event, trigger it, bind click,focus,blur events
						input.bind('updateState',function(){
							if(input.is(':checked')){
								if(input.is(':radio')){
									allInputs.each(function(){
										$('label[for='+$(this).attr('id')+']').removeClass('checked');
									});
								};
								label.addClass('checked');
							} else {
								label.removeClass('checked checkedHover checkedFocus');
							}
						})
						.trigger('updateState')
						.click(function(){
							$(this).trigger('updateState');
						})
						.focus(function(){
							label.addClass('focus');
							if(inputType == 'checkbox' && input.is(':checked')) {
								$(this).addClass('checkedFocus');
							}
						})
										
					}
				});
			}

//Depends on jsbn.js and rng.js

//Version 1.1: support utf-8 encoding in pkcs1pad2

//convert a (hex) string to a bignum object
function parseBigInt(str,r) {
return new BigInteger(str,r);
}

function linebrk(s,n) {
var ret = "";
var i = 0;
while(i + n < s.length) {
 ret += s.substring(i,i+n) + "\n";
 i += n;
}
return ret + s.substring(i,s.length);
}

function byte2Hex(b) {
if(b < 0x10)
 return "0" + b.toString(16);
else
 return b.toString(16);
}

//PKCS#1 (type 2, random) pad input string s to n bytes, and return a bigint
function pkcs1pad2(s,n) {
if(n < s.length + 11) { // TODO: fix for utf-8
 alert("Message too long for RSA");
 return null;
}
var ba = new Array();
var i = s.length - 1;
while(i >= 0 && n > 0) {
 var c = s.charCodeAt(i--);
 if(c < 128) { // encode using utf-8
   ba[--n] = c;
 }
 else if((c > 127) && (c < 2048)) {
   ba[--n] = (c & 63) | 128;
   ba[--n] = (c >> 6) | 192;
 }
 else {
   ba[--n] = (c & 63) | 128;
   ba[--n] = ((c >> 6) & 63) | 128;
   ba[--n] = (c >> 12) | 224;
 }
}
ba[--n] = 0;
var rng = new SecureRandom();
var x = new Array();
while(n > 2) { // random non-zero pad
 x[0] = 0;
 while(x[0] == 0) rng.nextBytes(x);
 ba[--n] = x[0];
}
ba[--n] = 2;
ba[--n] = 0;
return new BigInteger(ba);
}

//"empty" RSA key constructor
function RSAKey() {
this.n = null;
this.e = 0;
this.d = null;
this.p = null;
this.q = null;
this.dmp1 = null;
this.dmq1 = null;
this.coeff = null;
}

//Set the public key fields N and e from hex strings
function RSASetPublic(N,E) {
if(N != null && E != null && N.length > 0 && E.length > 0) {
 this.n = parseBigInt(N,16);
 this.e = parseInt(E,16);
}
else
 alert("Invalid RSA public key");
}

//Perform raw public operation on "x": return x^e (mod n)
function RSADoPublic(x) {
return x.modPowInt(this.e, this.n);
}

//Return the PKCS#1 RSA encryption of "text" as an even-length hex string
function RSAEncrypt(text) {
var m = pkcs1pad2(text,(this.n.bitLength()+7)>>3);
if(m == null) return null;
var c = this.doPublic(m);
if(c == null) return null;
var h = c.toString(16);
if((h.length & 1) == 0) return h; else return "0" + h;
}

//Return the PKCS#1 RSA encryption of "text" as a Base64-encoded string
//function RSAEncryptB64(text) {
//var h = this.encrypt(text);
//if(h) return hex2b64(h); else return null;
//}

//protected
RSAKey.prototype.doPublic = RSADoPublic;

//public
RSAKey.prototype.setPublic = RSASetPublic;
RSAKey.prototype.encrypt = RSAEncrypt;
//RSAKey.prototype.encrypt_b64 = RSAEncryptB64;

var b64map="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
var b64pad="=";

function hex2b64(h) {
var i;
var c;
var ret = "";
for(i = 0; i+3 <= h.length; i+=3) {
 c = parseInt(h.substring(i,i+3),16);
 ret += b64map.charAt(c >> 6) + b64map.charAt(c & 63);
}
if(i+1 == h.length) {
 c = parseInt(h.substring(i,i+1),16);
 ret += b64map.charAt(c << 2);
}
else if(i+2 == h.length) {
 c = parseInt(h.substring(i,i+2),16);
 ret += b64map.charAt(c >> 2) + b64map.charAt((c & 3) << 4);
}
while((ret.length & 3) > 0) ret += b64pad;
return ret;
}

//convert a base64 string to hex
function b64tohex(s) {
var ret = ""
var i;
var k = 0; // b64 state, 0-3
var slop;
for(i = 0; i < s.length; ++i) {
 if(s.charAt(i) == b64pad) break;
 v = b64map.indexOf(s.charAt(i));
 if(v < 0) continue;
 if(k == 0) {
   ret += int2char(v >> 2);
   slop = v & 3;
   k = 1;
 }
 else if(k == 1) {
   ret += int2char((slop << 2) | (v >> 4));
   slop = v & 0xf;
   k = 2;
 }
 else if(k == 2) {
   ret += int2char(slop);
   ret += int2char(v >> 2);
   slop = v & 3;
   k = 3;
 }
 else {
   ret += int2char((slop << 2) | (v >> 4));
   ret += int2char(v & 0xf);
   k = 0;
 }
}
if(k == 1)
 ret += int2char(slop << 2);
return ret;
}

//convert a base64 string to a byte/number array
function b64toBA(s) {
//piggyback on b64tohex for now, optimize later
var h = b64tohex(s);
var i;
var a = new Array();
for(i = 0; 2*i < h.length; ++i) {
 a[i] = parseInt(h.substring(2*i,2*i+2),16);
}
return a;
}

//Copyright (c) 2005  Tom Wu
//All Rights Reserved.
//See "LICENSE" for details.

//Basic JavaScript BN library - subset useful for RSA encryption.

//Bits per digit
var dbits;

//JavaScript engine analysis
var canary = 0xdeadbeefcafe;
var j_lm = ((canary&0xffffff)==0xefcafe);

//(public) Constructor
function BigInteger(a,b,c) {
if(a != null)
if("number" == typeof a) this.fromNumber(a,b,c);
else if(b == null && "string" != typeof a) this.fromString(a,256);
else this.fromString(a,b);
}

//return new, unset BigInteger
function nbi() { return new BigInteger(null); }

//am: Compute w_j += (x*this_i), propagate carries,
//c is initial carry, returns final carry.
//c < 3*dvalue, x < 2*dvalue, this_i < dvalue
//We need to select the fastest one that works in this environment.

//am1: use a single mult and divide to get the high bits,
//max digit bits should be 26 because
//max internal value = 2*dvalue^2-2*dvalue (< 2^53)
function am1(i,x,w,j,c,n) {
while(--n >= 0) {
var v = x*this[i++]+w[j]+c;
c = Math.floor(v/0x4000000);
w[j++] = v&0x3ffffff;
}
return c;
}
//am2 avoids a big mult-and-extract completely.
//Max digit bits should be <= 30 because we do bitwise ops
//on values up to 2*hdvalue^2-hdvalue-1 (< 2^31)
function am2(i,x,w,j,c,n) {
var xl = x&0x7fff, xh = x>>15;
while(--n >= 0) {
var l = this[i]&0x7fff;
var h = this[i++]>>15;
var m = xh*l+h*xl;
l = xl*l+((m&0x7fff)<<15)+w[j]+(c&0x3fffffff);
c = (l>>>30)+(m>>>15)+xh*h+(c>>>30);
w[j++] = l&0x3fffffff;
}
return c;
}
//Alternately, set max digit bits to 28 since some
//browsers slow down when dealing with 32-bit numbers.
function am3(i,x,w,j,c,n) {
var xl = x&0x3fff, xh = x>>14;
while(--n >= 0) {
var l = this[i]&0x3fff;
var h = this[i++]>>14;
var m = xh*l+h*xl;
l = xl*l+((m&0x3fff)<<14)+w[j]+c;
c = (l>>28)+(m>>14)+xh*h;
w[j++] = l&0xfffffff;
}
return c;
}
if(j_lm && (navigator.appName == "Microsoft Internet Explorer")) {
BigInteger.prototype.am = am2;
dbits = 30;
}
else if(j_lm && (navigator.appName != "Netscape")) {
BigInteger.prototype.am = am1;
dbits = 26;
}
else { // Mozilla/Netscape seems to prefer am3
BigInteger.prototype.am = am3;
dbits = 28;
}

BigInteger.prototype.DB = dbits;
BigInteger.prototype.DM = ((1<<dbits)-1);
BigInteger.prototype.DV = (1<<dbits);

var BI_FP = 52;
BigInteger.prototype.FV = Math.pow(2,BI_FP);
BigInteger.prototype.F1 = BI_FP-dbits;
BigInteger.prototype.F2 = 2*dbits-BI_FP;

//Digit conversions
var BI_RM = "0123456789abcdefghijklmnopqrstuvwxyz";
var BI_RC = new Array();
var rr,vv;
rr = "0".charCodeAt(0);
for(vv = 0; vv <= 9; ++vv) BI_RC[rr++] = vv;
rr = "a".charCodeAt(0);
for(vv = 10; vv < 36; ++vv) BI_RC[rr++] = vv;
rr = "A".charCodeAt(0);
for(vv = 10; vv < 36; ++vv) BI_RC[rr++] = vv;

function int2char(n) { return BI_RM.charAt(n); }
function intAt(s,i) {
var c = BI_RC[s.charCodeAt(i)];
return (c==null)?-1:c;
}

//(protected) copy this to r
function bnpCopyTo(r) {
for(var i = this.t-1; i >= 0; --i) r[i] = this[i];
r.t = this.t;
r.s = this.s;
}

//(protected) set from integer value x, -DV <= x < DV
function bnpFromInt(x) {
this.t = 1;
this.s = (x<0)?-1:0;
if(x > 0) this[0] = x;
else if(x < -1) this[0] = x+DV;
else this.t = 0;
}

//return bigint initialized to value
function nbv(i) { var r = nbi(); r.fromInt(i); return r; }

//(protected) set from string and radix
function bnpFromString(s,b) {
var k;
if(b == 16) k = 4;
else if(b == 8) k = 3;
else if(b == 256) k = 8; // byte array
else if(b == 2) k = 1;
else if(b == 32) k = 5;
else if(b == 4) k = 2;
else { this.fromRadix(s,b); return; }
this.t = 0;
this.s = 0;
var i = s.length, mi = false, sh = 0;
while(--i >= 0) {
var x = (k==8)?s[i]&0xff:intAt(s,i);
if(x < 0) {
if(s.charAt(i) == "-") mi = true;
continue;
}
mi = false;
if(sh == 0)
this[this.t++] = x;
else if(sh+k > this.DB) {
this[this.t-1] |= (x&((1<<(this.DB-sh))-1))<<sh;
this[this.t++] = (x>>(this.DB-sh));
}
else
this[this.t-1] |= x<<sh;
sh += k;
if(sh >= this.DB) sh -= this.DB;
}
if(k == 8 && (s[0]&0x80) != 0) {
this.s = -1;
if(sh > 0) this[this.t-1] |= ((1<<(this.DB-sh))-1)<<sh;
}
this.clamp();
if(mi) BigInteger.ZERO.subTo(this,this);
}

//(protected) clamp off excess high words
function bnpClamp() {
var c = this.s&this.DM;
while(this.t > 0 && this[this.t-1] == c) --this.t;
}

//(public) return string representation in given radix
function bnToString(b) {
if(this.s < 0) return "-"+this.negate().toString(b);
var k;
if(b == 16) k = 4;
else if(b == 8) k = 3;
else if(b == 2) k = 1;
else if(b == 32) k = 5;
else if(b == 4) k = 2;
else return this.toRadix(b);
var km = (1<<k)-1, d, m = false, r = "", i = this.t;
var p = this.DB-(i*this.DB)%k;
if(i-- > 0) {
if(p < this.DB && (d = this[i]>>p) > 0) { m = true; r = int2char(d); }
while(i >= 0) {
if(p < k) {
  d = (this[i]&((1<<p)-1))<<(k-p);
  d |= this[--i]>>(p+=this.DB-k);
}
else {
  d = (this[i]>>(p-=k))&km;
  if(p <= 0) { p += this.DB; --i; }
}
if(d > 0) m = true;
if(m) r += int2char(d);
}
}
return m?r:"0";
}

//(public) -this
function bnNegate() { var r = nbi(); BigInteger.ZERO.subTo(this,r); return r; }

//(public) |this|
function bnAbs() { return (this.s<0)?this.negate():this; }

//(public) return + if this > a, - if this < a, 0 if equal
function bnCompareTo(a) {
var r = this.s-a.s;
if(r != 0) return r;
var i = this.t;
r = i-a.t;
if(r != 0) return (this.s<0)?-r:r;
while(--i >= 0) if((r=this[i]-a[i]) != 0) return r;
return 0;
}

//returns bit length of the integer x
function nbits(x) {
var r = 1, t;
if((t=x>>>16) != 0) { x = t; r += 16; }
if((t=x>>8) != 0) { x = t; r += 8; }
if((t=x>>4) != 0) { x = t; r += 4; }
if((t=x>>2) != 0) { x = t; r += 2; }
if((t=x>>1) != 0) { x = t; r += 1; }
return r;
}

//(public) return the number of bits in "this"
function bnBitLength() {
if(this.t <= 0) return 0;
return this.DB*(this.t-1)+nbits(this[this.t-1]^(this.s&this.DM));
}

//(protected) r = this << n*DB
function bnpDLShiftTo(n,r) {
var i;
for(i = this.t-1; i >= 0; --i) r[i+n] = this[i];
for(i = n-1; i >= 0; --i) r[i] = 0;
r.t = this.t+n;
r.s = this.s;
}

//(protected) r = this >> n*DB
function bnpDRShiftTo(n,r) {
for(var i = n; i < this.t; ++i) r[i-n] = this[i];
r.t = Math.max(this.t-n,0);
r.s = this.s;
}

//(protected) r = this << n
function bnpLShiftTo(n,r) {
var bs = n%this.DB;
var cbs = this.DB-bs;
var bm = (1<<cbs)-1;
var ds = Math.floor(n/this.DB), c = (this.s<<bs)&this.DM, i;
for(i = this.t-1; i >= 0; --i) {
r[i+ds+1] = (this[i]>>cbs)|c;
c = (this[i]&bm)<<bs;
}
for(i = ds-1; i >= 0; --i) r[i] = 0;
r[ds] = c;
r.t = this.t+ds+1;
r.s = this.s;
r.clamp();
}

//(protected) r = this >> n
function bnpRShiftTo(n,r) {
r.s = this.s;
var ds = Math.floor(n/this.DB);
if(ds >= this.t) { r.t = 0; return; }
var bs = n%this.DB;
var cbs = this.DB-bs;
var bm = (1<<bs)-1;
r[0] = this[ds]>>bs;
for(var i = ds+1; i < this.t; ++i) {
r[i-ds-1] |= (this[i]&bm)<<cbs;
r[i-ds] = this[i]>>bs;
}
if(bs > 0) r[this.t-ds-1] |= (this.s&bm)<<cbs;
r.t = this.t-ds;
r.clamp();
}

//(protected) r = this - a
function bnpSubTo(a,r) {
var i = 0, c = 0, m = Math.min(a.t,this.t);
while(i < m) {
c += this[i]-a[i];
r[i++] = c&this.DM;
c >>= this.DB;
}
if(a.t < this.t) {
c -= a.s;
while(i < this.t) {
c += this[i];
r[i++] = c&this.DM;
c >>= this.DB;
}
c += this.s;
}
else {
c += this.s;
while(i < a.t) {
c -= a[i];
r[i++] = c&this.DM;
c >>= this.DB;
}
c -= a.s;
}
r.s = (c<0)?-1:0;
if(c < -1) r[i++] = this.DV+c;
else if(c > 0) r[i++] = c;
r.t = i;
r.clamp();
}

//(protected) r = this * a, r != this,a (HAC 14.12)
//"this" should be the larger one if appropriate.
function bnpMultiplyTo(a,r) {
var x = this.abs(), y = a.abs();
var i = x.t;
r.t = i+y.t;
while(--i >= 0) r[i] = 0;
for(i = 0; i < y.t; ++i) r[i+x.t] = x.am(0,y[i],r,i,0,x.t);
r.s = 0;
r.clamp();
if(this.s != a.s) BigInteger.ZERO.subTo(r,r);
}

//(protected) r = this^2, r != this (HAC 14.16)
function bnpSquareTo(r) {
var x = this.abs();
var i = r.t = 2*x.t;
while(--i >= 0) r[i] = 0;
for(i = 0; i < x.t-1; ++i) {
var c = x.am(i,x[i],r,2*i,0,1);
if((r[i+x.t]+=x.am(i+1,2*x[i],r,2*i+1,c,x.t-i-1)) >= x.DV) {
r[i+x.t] -= x.DV;
r[i+x.t+1] = 1;
}
}
if(r.t > 0) r[r.t-1] += x.am(i,x[i],r,2*i,0,1);
r.s = 0;
r.clamp();
}

//(protected) divide this by m, quotient and remainder to q, r (HAC 14.20)
//r != q, this != m.  q or r may be null.
function bnpDivRemTo(m,q,r) {
var pm = m.abs();
if(pm.t <= 0) return;
var pt = this.abs();
if(pt.t < pm.t) {
if(q != null) q.fromInt(0);
if(r != null) this.copyTo(r);
return;
}
if(r == null) r = nbi();
var y = nbi(), ts = this.s, ms = m.s;
var nsh = this.DB-nbits(pm[pm.t-1]);	// normalize modulus
if(nsh > 0) { pm.lShiftTo(nsh,y); pt.lShiftTo(nsh,r); }
else { pm.copyTo(y); pt.copyTo(r); }
var ys = y.t;
var y0 = y[ys-1];
if(y0 == 0) return;
var yt = y0*(1<<this.F1)+((ys>1)?y[ys-2]>>this.F2:0);
var d1 = this.FV/yt, d2 = (1<<this.F1)/yt, e = 1<<this.F2;
var i = r.t, j = i-ys, t = (q==null)?nbi():q;
y.dlShiftTo(j,t);
if(r.compareTo(t) >= 0) {
r[r.t++] = 1;
r.subTo(t,r);
}
BigInteger.ONE.dlShiftTo(ys,t);
t.subTo(y,y);	// "negative" y so we can replace sub with am later
while(y.t < ys) y[y.t++] = 0;
while(--j >= 0) {
// Estimate quotient digit
var qd = (r[--i]==y0)?this.DM:Math.floor(r[i]*d1+(r[i-1]+e)*d2);
if((r[i]+=y.am(0,qd,r,j,0,ys)) < qd) {	// Try it out
y.dlShiftTo(j,t);
r.subTo(t,r);
while(r[i] < --qd) r.subTo(t,r);
}
}
if(q != null) {
r.drShiftTo(ys,q);
if(ts != ms) BigInteger.ZERO.subTo(q,q);
}
r.t = ys;
r.clamp();
if(nsh > 0) r.rShiftTo(nsh,r);	// Denormalize remainder
if(ts < 0) BigInteger.ZERO.subTo(r,r);
}

//(public) this mod a
function bnMod(a) {
var r = nbi();
this.abs().divRemTo(a,null,r);
if(this.s < 0 && r.compareTo(BigInteger.ZERO) > 0) a.subTo(r,r);
return r;
}

//Modular reduction using "classic" algorithm
function Classic(m) { this.m = m; }
function cConvert(x) {
if(x.s < 0 || x.compareTo(this.m) >= 0) return x.mod(this.m);
else return x;
}
function cRevert(x) { return x; }
function cReduce(x) { x.divRemTo(this.m,null,x); }
function cMulTo(x,y,r) { x.multiplyTo(y,r); this.reduce(r); }
function cSqrTo(x,r) { x.squareTo(r); this.reduce(r); }

Classic.prototype.convert = cConvert;
Classic.prototype.revert = cRevert;
Classic.prototype.reduce = cReduce;
Classic.prototype.mulTo = cMulTo;
Classic.prototype.sqrTo = cSqrTo;

//(protected) return "-1/this % 2^DB"; useful for Mont. reduction
//justification:
//   xy == 1 (mod m)
//   xy =  1+km
//xy(2-xy) = (1+km)(1-km)
//x[y(2-xy)] = 1-k^2m^2
//x[y(2-xy)] == 1 (mod m^2)
//if y is 1/x mod m, then y(2-xy) is 1/x mod m^2
//should reduce x and y(2-xy) by m^2 at each step to keep size bounded.
//JS multiply "overflows" differently from C/C++, so care is needed here.
function bnpInvDigit() {
if(this.t < 1) return 0;
var x = this[0];
if((x&1) == 0) return 0;
var y = x&3;		// y == 1/x mod 2^2
y = (y*(2-(x&0xf)*y))&0xf;	// y == 1/x mod 2^4
y = (y*(2-(x&0xff)*y))&0xff;	// y == 1/x mod 2^8
y = (y*(2-(((x&0xffff)*y)&0xffff)))&0xffff;	// y == 1/x mod 2^16
//last step - calculate inverse mod DV directly;
//assumes 16 < DB <= 32 and assumes ability to handle 48-bit ints
y = (y*(2-x*y%this.DV))%this.DV;		// y == 1/x mod 2^dbits
//we really want the negative inverse, and -DV < y < DV
return (y>0)?this.DV-y:-y;
}

//Montgomery reduction
function Montgomery(m) {
this.m = m;
this.mp = m.invDigit();
this.mpl = this.mp&0x7fff;
this.mph = this.mp>>15;
this.um = (1<<(m.DB-15))-1;
this.mt2 = 2*m.t;
}

//xR mod m
function montConvert(x) {
var r = nbi();
x.abs().dlShiftTo(this.m.t,r);
r.divRemTo(this.m,null,r);
if(x.s < 0 && r.compareTo(BigInteger.ZERO) > 0) this.m.subTo(r,r);
return r;
}

//x/R mod m
function montRevert(x) {
var r = nbi();
x.copyTo(r);
this.reduce(r);
return r;
}

//x = x/R mod m (HAC 14.32)
function montReduce(x) {
while(x.t <= this.mt2)	// pad x so am has enough room later
x[x.t++] = 0;
for(var i = 0; i < this.m.t; ++i) {
// faster way of calculating u0 = x[i]*mp mod DV
var j = x[i]&0x7fff;
var u0 = (j*this.mpl+(((j*this.mph+(x[i]>>15)*this.mpl)&this.um)<<15))&x.DM;
// use am to combine the multiply-shift-add into one call
j = i+this.m.t;
x[j] += this.m.am(0,u0,x,i,0,this.m.t);
// propagate carry
while(x[j] >= x.DV) { x[j] -= x.DV; x[++j]++; }
}
x.clamp();
x.drShiftTo(this.m.t,x);
if(x.compareTo(this.m) >= 0) x.subTo(this.m,x);
}

//r = "x^2/R mod m"; x != r
function montSqrTo(x,r) { x.squareTo(r); this.reduce(r); }

//r = "xy/R mod m"; x,y != r
function montMulTo(x,y,r) { x.multiplyTo(y,r); this.reduce(r); }

Montgomery.prototype.convert = montConvert;
Montgomery.prototype.revert = montRevert;
Montgomery.prototype.reduce = montReduce;
Montgomery.prototype.mulTo = montMulTo;
Montgomery.prototype.sqrTo = montSqrTo;

//(protected) true iff this is even
function bnpIsEven() { return ((this.t>0)?(this[0]&1):this.s) == 0; }

//(protected) this^e, e < 2^32, doing sqr and mul with "r" (HAC 14.79)
function bnpExp(e,z) {
if(e > 0xffffffff || e < 1) return BigInteger.ONE;
var r = nbi(), r2 = nbi(), g = z.convert(this), i = nbits(e)-1;
g.copyTo(r);
while(--i >= 0) {
z.sqrTo(r,r2);
if((e&(1<<i)) > 0) z.mulTo(r2,g,r);
else { var t = r; r = r2; r2 = t; }
}
return z.revert(r);
}

//(public) this^e % m, 0 <= e < 2^32
function bnModPowInt(e,m) {
var z;
if(e < 256 || m.isEven()) z = new Classic(m); else z = new Montgomery(m);
return this.exp(e,z);
}

//protected
BigInteger.prototype.copyTo = bnpCopyTo;
BigInteger.prototype.fromInt = bnpFromInt;
BigInteger.prototype.fromString = bnpFromString;
BigInteger.prototype.clamp = bnpClamp;
BigInteger.prototype.dlShiftTo = bnpDLShiftTo;
BigInteger.prototype.drShiftTo = bnpDRShiftTo;
BigInteger.prototype.lShiftTo = bnpLShiftTo;
BigInteger.prototype.rShiftTo = bnpRShiftTo;
BigInteger.prototype.subTo = bnpSubTo;
BigInteger.prototype.multiplyTo = bnpMultiplyTo;
BigInteger.prototype.squareTo = bnpSquareTo;
BigInteger.prototype.divRemTo = bnpDivRemTo;
BigInteger.prototype.invDigit = bnpInvDigit;
BigInteger.prototype.isEven = bnpIsEven;
BigInteger.prototype.exp = bnpExp;

//public
BigInteger.prototype.toString = bnToString;
BigInteger.prototype.negate = bnNegate;
BigInteger.prototype.abs = bnAbs;
BigInteger.prototype.compareTo = bnCompareTo;
BigInteger.prototype.bitLength = bnBitLength;
BigInteger.prototype.mod = bnMod;
BigInteger.prototype.modPowInt = bnModPowInt;

//"constants"
BigInteger.ZERO = nbv(0);
BigInteger.ONE = nbv(1);

//prng4.js - uses Arcfour as a PRNG

function Arcfour() {
this.i = 0;
this.j = 0;
this.S = new Array();
}

//Initialize arcfour context from key, an array of ints, each from [0..255]
function ARC4init(key) {
var i, j, t;
for(i = 0; i < 256; ++i)
 this.S[i] = i;
j = 0;
for(i = 0; i < 256; ++i) {
 j = (j + this.S[i] + key[i % key.length]) & 255;
 t = this.S[i];
 this.S[i] = this.S[j];
 this.S[j] = t;
}
this.i = 0;
this.j = 0;
}

function ARC4next() {
var t;
this.i = (this.i + 1) & 255;
this.j = (this.j + this.S[this.i]) & 255;
t = this.S[this.i];
this.S[this.i] = this.S[this.j];
this.S[this.j] = t;
return this.S[(t + this.S[this.i]) & 255];
}

Arcfour.prototype.init = ARC4init;
Arcfour.prototype.next = ARC4next;

//Plug in your RNG constructor here
function prng_newstate() {
return new Arcfour();
}

//Pool size must be a multiple of 4 and greater than 32.
//An array of bytes the size of the pool will be passed to init()
var rng_psize = 256;

//Random number generator - requires a PRNG backend, e.g. prng4.js

//For best results, put code like
//<body onClick='rng_seed_time();' onKeyPress='rng_seed_time();'>
//in your main HTML document.

var rng_state;
var rng_pool;
var rng_pptr;

//Mix in a 32-bit integer into the pool
function rng_seed_int(x) {
rng_pool[rng_pptr++] ^= x & 255;
rng_pool[rng_pptr++] ^= (x >> 8) & 255;
rng_pool[rng_pptr++] ^= (x >> 16) & 255;
rng_pool[rng_pptr++] ^= (x >> 24) & 255;
if(rng_pptr >= rng_psize) rng_pptr -= rng_psize;
}

//Mix in the current time (w/milliseconds) into the pool
function rng_seed_time() {
rng_seed_int(new Date().getTime());
}

//Initialize the pool with junk if needed.
if(rng_pool == null) {
rng_pool = new Array();
rng_pptr = 0;
var t;
if(navigator.appName == "Netscape" && navigator.appVersion < "5" && window.crypto) {
// Extract entropy (256 bits) from NS4 RNG if available
var z = window.crypto.random(32);
for(t = 0; t < z.length; ++t)
rng_pool[rng_pptr++] = z.charCodeAt(t) & 255;
}  
while(rng_pptr < rng_psize) {  // extract some randomness from Math.random()
t = Math.floor(65536 * Math.random());
rng_pool[rng_pptr++] = t >>> 8;
rng_pool[rng_pptr++] = t & 255;
}
rng_pptr = 0;
rng_seed_time();
//rng_seed_int(window.screenX);
//rng_seed_int(window.screenY);
}

function rng_get_byte() {
if(rng_state == null) {
rng_seed_time();
rng_state = prng_newstate();
rng_state.init(rng_pool);
for(rng_pptr = 0; rng_pptr < rng_pool.length; ++rng_pptr)
rng_pool[rng_pptr] = 0;
rng_pptr = 0;
//rng_pool = null;
}
//TODO: allow reseeding after first request
return rng_state.next();
}

function rng_get_bytes(ba) {
var i;
for(i = 0; i < ba.length; ++i) ba[i] = rng_get_byte();
}

function SecureRandom() {}

SecureRandom.prototype.nextBytes = rng_get_bytes;

/*
* Summary: lyz.delayLoading 1.0
* Author: ����־
* Date: 2011
* Emial: c_sharp@live.cn
*/
jQuery.fn.extend({delayLoading:function(a){function g(d){var b,c;if(a.container===undefined||a.container===window){b=$(window).scrollTop();c=$(window).height()+$(window).scrollTop()}else{b=$(a.container).offset().top;c=$(a.container).offset().top+$(a.container).height()}return d.offset().top+d.height()+a.beforehand>=b&&c>=d.offset().top-a.beforehand}function h(d){var b,c;if(a.container===undefined||a.container===window){b=$(window).scrollLeft();c=$(window).width()+$(window).scrollLeft()}else{b=$(a.container).offset().left;
c=$(a.container).offset().left+$(a.container).width()}return d.offset().left+d.width()+a.beforehand>=b&&c>=d.offset().left-a.beforehand}function f(){e.filter("img["+a.imgSrcAttr+"]").each(function(d,b){if($(b).attr(a.imgSrcAttr)!==undefined&&$(b).attr(a.imgSrcAttr)!==null&&$(b).attr(a.imgSrcAttr)!==""&&g($(b))&&h($(b))){var c=new Image;c.onload=function(){$(b).attr("src",c.src);a.duration!==0&&$(b).hide().fadeIn(a.duration);$(b).removeAttr(a.imgSrcAttr);a.success($(b))};c.onerror=function(){$(b).attr("src",
a.errorImg);$(b).removeAttr(a.imgSrcAttr);a.error($(b))};c.src=$(b).attr(a.imgSrcAttr)}})}a=jQuery.extend({defaultImg:"",errorImg:"",imgSrcAttr:"originalSrc",beforehand:0,event:"scroll",duration:"normal",container:window,success:function(){},error:function(){}},a||{});if(a.errorImg===undefined||a.errorImg===null||a.errorImg==="")a.errorImg=a.defaultImg;var e=$(this);if(e.attr("src")===undefined||e.attr("src")===null||e.attr("src")==="")e.attr("src",a.defaultImg);f();$(a.container).bind(a.event,function(){f()})}});


var local = window.location;
var contextPath = local.pathname.split("/")[1];
var basePath = local.protocol + "//" + local.host+"/intertrips/"+contextPath;

var HISTORY_RECORED_SIZE = 10;
var HISTORY_RECORED_EXPIRES = 3600 * 24 * 7;

var getMember = function(){
   var memberJson = getCookie("loginMember");
   var member = null;
   if(memberJson != '' && memberJson != null){
       member = JSON.parse(memberJson);
   }
   return member; 
}

var logout = function(){
	$.get(basePath + "/logout.do",function(result){
		if(result == 'success'){
			$("#menu-item-312").find("a").attr("href","javascript:goProfile();").html("<i class=\"fa fa-sign-in\"></i> Sign in </a>");
			$("#logoutLi").hide();
		}
	});
}

//点击去用户中心
var goProfile = function(){
   var member = getMember();
   if(member != null && member.account != '' && member.account != null){
	   var cValue = $("#flagselect").attr('actTry');
	   window.location.href = basePath + "/member/profile/user_orders.htm?cValue="+cValue;
   }else{
   	   $(".theme-login").click();
   	   $("#menu-item-312").find("a").attr("href","javascript:goProfile();").html("<i class=\"fa fa-sign-in\"></i> Sign in </a>");
	   $("#logoutLi").hide();
   }
}

//点击登陆或用户中心（手机）
var goProfileMobile = function(){
   var member = getMember();
   if(member != null && member.account != '' && member.account != null){
	var cValue = $("#flagselect").attr('actTry');
	   window.location.href = basePath + "/member/profile/user_orders.htm?cValue="+cValue;
   }else{
   	   $(".theme-login-mobile").click();
   }
}
var member = null;
jQuery(document).ready(function($) {
   if(member == null){
  	    member = getMember();
   }
   if(member != null && member.account != '' && member.account != null){
	   $("#menu-item-312").html("<a href=\"javascript:goProfile();\" title=\"UserCenter\"><i class=\"fa fa-user\"></i>"+member.account+"</a>");
	   $("#logoutLi").show();
   }

	/**
 //币种切换
 $(".nationalflag").mouseenter(function(){
     $(this).find(".brand_op").show();
 });
 $(".nationalflag").mouseleave(function(){
     $(this).find(".brand_op").hide();
 });
	**/
	 //显示币种
	 var cc = getCookie("cc");
//	 var sshe ='${costCode!}';
//	 alert(sshe);
	 if(cc){
		 //显示cookie币种
		 if(cc=='CNY'){
			 $(".currency-selector").val("CNY");
		 }else if (cc=='CAD') {
			 $(".currency-selector").val("CAD");
		 }else if (cc=='AUD') {
			 $(".currency-selector").val("AUD");
		 }else if (cc=='USD') {
			 $(".currency-selector").val("USD");
		 }else if(cc=="EUR"){
			 $(".currency-selector").val("EUR");
		 }
	 }else {
		//显示销售中心默认币种
		 $(".currency-selector").val("USD");
	 }
	 
	 //头部服务热线
	 $("#phone_lists").hover(function(){
       $(this).find(".tel_list").slideDown("fast");
		  $(this).find(".arrows").removeClass("off").addClass("active");
  },function(){
	      $(this).find(".tel_list").slideUp("fast",function(){
			     $(this).stop(true,true);
		   });
		  $(this).find(".arrows").removeClass("active").addClass("off");	
	 });
	 
	 // 登录
		$('.theme-login').click(function(){
			$('.theme-popover-mask').fadeIn(300);
			$('.theme-popover-register').slideUp(300);//隐藏注册弹出窗
			$('.theme-popover-password').slideUp(300);
			$('.theme-popover').slideDown(400);//显示登陆弹出窗
			//$('#loginCode').find(".loginCode").remove().end().append("<img src=\""+basePath+"/captcha.do?d='+new Date().getTime()\" title=\"\" onclick=\"this.src='"+basePath+"/captcha.do?d='+new Date().getTime()\" class=\"yzm-img loginCode\"><div class=\"clear loginCode\"></div>");
			//原压缩版代码 下
			//$("#loginCode").find(".loginCode").remove().end().append('<img src="'+basePath+'/captcha.do?d=\'+new Date().getTime()" title="" onclick="this.src=\''+basePath+'/captcha.do?d=\'+new Date().getTime()" class="yzm-img loginCode"><div class="clear loginCode"></div>')
		});
		$('.theme-close').click(function(){
			$('.theme-popover-mask').fadeOut(300);
			$('.theme-popover').slideUp(400);
			$('.theme-popover-register').slideUp(300);
			$('.theme-popover-password').slideUp(300);
		});
		
		// 注册
		$('.theme-register').click(function(){
			$('.theme-popover-mask').fadeIn(300);
			$('.theme-popover').slideUp(300);
			$('.theme-popover-register').slideDown(400);
			$('#regestCode').find(".regestCode").remove().end().append("<img src=\""+basePath+"/captcha.do?d='+new Date().getTime()\" title=\"\" onclick=\"this.src='"+basePath+"/captcha.do?d='+new Date().getTime()\" class=\"yzm-img regestCode\"><div class=\"clear regestCode\"></div>");
		});
		
		// 密码找回
		$('.theme-password').click(function(){
			$('.theme-popover-mask').fadeIn(300);
			$('.theme-popover').slideUp(300);
			$('.theme-popover-register').slideUp(300);
			$('.theme-popover-password').slideDown(400);
		});
		
		//手机登陆
	    $(".theme-login-mobile").click(function(){
			$('.theme-popover-mask-mobile').fadeIn(300);
			$('.theme-popover-register-mobile').slideUp(300);//隐藏注册弹出窗
			$('.theme-popover-password-mobile').slideUp(300);
			$('.theme-popover-mobile').slideDown(400);//显示登陆弹出窗
			$('#loginCode-mobile').find(".loginCode").remove().end().append("<img src=\""+basePath+"/captcha.do?d='+new Date().getTime()\" title=\"\" onclick=\"this.src='"+basePath+"/captcha.do?d='+new Date().getTime()\" class=\"yzm-img loginCode\"><div class=\"clear loginCode\"></div>");			   
			//;$("#loginCode-mobile").find(".loginCode").remove().end().append('<img src="'+basePath+'/captcha.do?d=\'+new Date().getTime()" title="" onclick="this.src=\''+basePath+'/captcha.do?d=\'+new Date().getTime()" class="yzm-img loginCode"><div class="clear loginCode"></div>')
		});	
		$('.theme-close-mobile').click(function(){
			$('.theme-popover-mask-mobile').fadeOut(300);
			$('.theme-popover-mobile').slideUp(400);
			$('.theme-popover-register-mobile').slideUp(300);
			$('.theme-popover-password-mobile').slideUp(300);
		});
	    
		// 手机注册
		$('.theme-register-mobile').click(function(){
			$('.theme-popover-mask-mobile').fadeIn(300);
			$('.theme-popover-mobile').slideUp(300);
			$('.theme-popover-register-mobile').slideDown(400);
			$('#regestCode-mobile').find(".regestCode").remove().end().append("<img src=\""+basePath+"/captcha.do?d='+new Date().getTime()\" title=\"\" onclick=\"this.src='"+basePath+"/captcha.do?d='+new Date().getTime()\" class=\"yzm-img regestCode\"><div class=\"clear regestCode\"></div>");
		});
		
		// 手机密码找回
		$('.theme-password-mobile').click(function(){
			$('.theme-popover-mask-mobile').fadeIn(300);
			$('.theme-popover-mobile').slideUp(300);
			$('.theme-popover-register-mobile').slideUp(300);
			$('.theme-popover-password-mobile').slideDown(400);
		});
		
 $("#shoppingCartButton").click(function(){
 	window.location.href = basePath + "/shopping_cart.htm";		
 });
 
 $("#password").focus(function(){
 	$("#theme-login-error").html("");
 });
 
 $("#password_mobile").focus(function(){
	$("#theme-login-error-mobile").html("");
 });
 
 //登录提交
 $("form#loginForm").validate({
 	
     rules: {	
         account: {
             required: true,
             email: true,
             remote: {
                 type: "post",
                 url: basePath + "/register/isExist.do",
                 cache:false,
                 data: {
                     account: function() {
                         return $("#account").val();
                     }
                 },
             }
         },
         
         password: {
             required: true,
         }
		/*
         captcha: {
             required: true,
             remote: {
                 type: "post",
                 url: basePath + "/register/isCaptcha.do",
                 cache:false,
                 data: {
                     captcha: function() {
                         return $("#captcha").val();
                     }
                 },
             }
         }
		 
		 ,captcha:{required:true,remote:{type:"post",url:basePath+"/register/isCaptcha.do",cache:false,data:{captcha:function(){return $("#captcha").val()}},}}
		 */
     },
     messages: {
         account: {
             required: "Enter your email address",
             email: "Invalid email format",
             remote: "Email unregistered",
         },

         password: {
             required: "Enter password",
         },
		/*
         captcha: {
             required: "Enter verification code",
             remote: "Wrong verification code"
         },
		 captcha:{required:"Enter verification code",remote:"Wrong verification code"},
		 */
     },

     submitHandler: function(form) {
         $.ajax({
             url: basePath + "/common/public_key.do",
             type: "GET",
             dataType: "json",
             cache: false,
             beforeSend: function() {
                 $('#sub-login').attr("disabled", true);
                 $("#loading_hint").show();
                 $("#hint").text("Logging in...");
             },
             success: function(data) {
                 var rsaKey = new RSAKey();
                 rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
                 var enPassword = hex2b64(rsaKey.encrypt($("#password").val()));
                 $.ajax({
                     url: $("#loginForm").attr("action"),
                     type: "POST",
                     data: {
                         account: $("#account").val(),
                         enPassword: enPassword,
                     },
                     dataType: "json",
                     cache: false,
                     complete: function(message) {
                         $("#loading_hint").hide();
                         $('#sub-login').attr("disabled", false);
                         var result = eval("(" + message.responseText + ")");
                         if (result.success) {
                            // location.href = basePath + "/";
                        	 location.href = basePath + "/member/profile/user_orders.htm";
                            //location.reload();
                         } else {
                             $("#theme-login-error").html(result.message);
                         }
                     }
                 });
             }
         });
     }
 });

 //注册表单验证
 $("form#registerForm").validate({
     rules: {
         account_register: {
             required: true,
             email: true,
             remote: {
                 type: "post",
                 url: basePath + "/register/isExist.do",
                 cache:false,
                 data: {
                     account: function() {
                         return $("#account_register").val();
                     }
                 },
                 dataType: "html",
                 dataFilter: function(data, type) {
                     if (data == "true") {
                         return false;
                     } else {
                         return true;
                     }
                 }
             }
         },
         password_register: {
             required: true,
             minlength: 6,
             maxlength: 20
         },
         confirm_password_register: {
             required: true,
             equalTo: "#password_register"
         }
		 /*
         captcha_register: {
             required: true,
             remote: {
                 type: "post",
                 url: basePath + "/register/isCaptcha.do",
                 cache:false,
                 data: {
                     captcha: function() {
                         return $("#captcha_register").val();
                     }
                 },
             }
         }
		 ,captcha_register:{required:true,remote:{type:"post",url:basePath+"/register/isCaptcha.do",cache:false,data:{captcha:function(){return $("#captcha_register").val()}},}}
		 */
     },
     messages: {
         account_register: {
             required: "Enter your email address",
             email: "Invalid email format",
             remote: "User name already exists"
         },
         password_register: {
             required: "Enter password",
             minlength: "Your Password's length should be between 6-20 characters",
             maxlength: "Your Password's length should be between 6-20 characters"
         },
         confirm_password_register: {
             required: "Enter confirm password",
             equalTo: " The two passwords you typed do not match"
         },
		 /*
         captcha_register: {
             required: "Enter verification code",
             remote: "Wrong verification code"
         },
		 captcha_register:{required:"Enter verification code",remote:"Wrong verification code"},
		 */
     },
     submitHandler: function(form) {
         $.ajax({
             url: basePath + "/common/public_key.do",
             type: "GET",
             dataType: "json",
             cache: false,
             beforeSend: function() {
                 $('#sub-register').attr("disabled", true);
                 $("#loading_hint").show();
                 $("#hint").text("Being registered...");
             },
             success: function(data) {
                 var rsaKey = new RSAKey();
                 rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
                 var enPassword = hex2b64(rsaKey.encrypt($("#password_register").val()));
                 $.ajax({
                     url: $("#registerForm").attr("action"),
                     type: "POST",
                     data: {
                         account: $("#account_register").val(),
                         enPassword: enPassword,
                     },
                     dataType: "json",
                     cache: false,
                     complete: function(message) {
                         $("#loading_hint").hide();
                         $('#sub-register').attr("disabled", false);
                         var result = eval("(" + message.responseText + ")");
                         if (result.success) {
                             $('.theme-popover-mask').hide();
                             $('.theme-popover-register').slideUp(300); //隐藏注册弹出窗
                            // $('#theme-popover-password').slideDown(400); //提示信息
                            alertWarn('Signing in...');
                         	setTimeout(function(){
                         	   //location.reload();
                         	  location.href = basePath + "/member/profile/user_orders.htm";
                         	},2000);
                         } else {
                         	 $("#theme-register-error").html(result.message);
                         }
                     }
                 });
             }
         });
     }
 });

 //密码修改
 $("form#resetForm").validate({
     rules: {
         account_reset: {
             required: true,
             email: true,
             remote: {
                 type: "post",
                 url: basePath + "/register/isExist.do",
                 cache:false,
                 data: {
                     account: function() {
                         return $("#account_reset").val();
                     }
                 },
                 dataType: "html",
                 dataFilter: function(data, type) {
                     if (data == "true") {
                         return true;
                     } else {
                         return false;
                     }
                 }
             }
         }
     },

     messages: {
         account_reset: {
             required: "Enter your email address",
             email: "Invalid email format",
             remote: "Email unregistered"
         },
     },
     
     submitHandler: function(form) {
     	$.ajax({
             url: $("#resetForm").attr("action"),
             type: "POST",
             data: {
                 account: $("#account_reset").val(),
             },
             dataType: "json",
             cache: false,
             beforeSend: function() {
                 $('#sub-reset-password').attr("disabled", true);
                 $("#loading_hint").show();
                 $("#hint").text("正在发送...");
             },
             complete: function(message) {
             	 var result = eval("(" + message.responseText + ")");
                  if (result.success) {
                 	$("#loading_hint").hide();
                  	$("#loading_hint").show();
                  	$("#hint").text("发送成功...");
                  	setTimeout(function(){
                  		 $("#loading_hint").hide();
                 	    },2000);
					 } else {  
						$("#loading_hint").hide();
						$("#theme-password-error").html(result.message);
		             }   
                 $('#sub-reset-password').attr("disabled", false);
             }
         });
     }
 });

	 //手机登录提交
	 $("form#mobileLoginForm").validate({
	 	
	     rules: {
	         account: {
	             required: true,
	             email: true,
	             remote: {
	                 type: "post",
	                 url: basePath + "/register/isExist.do",
	                 cache:false,
	                 data: {
	                     account: function() {
	                         return $("#account_mobile").val();
	                     }
	                 },
	             }
	         },
	         
	         password: {
	             required: true,
	         }
			/*
	         captcha: {
	             required: true,
	             remote: {
	                 type: "post",
	                 url: basePath + "/register/isCaptcha.do",
	                 cache:false,
	                 data: {
	                     captcha: function() {
	                         return $("#captcha_mobile").val();
	                     }
	                 },
	             }
	         }
			 ,captcha:{required:true,remote:{type:"post",url:basePath+"/register/isCaptcha.do",cache:false,data:{captcha:function(){return $("#captcha_mobile").val()}},}}
			 */
	     },
	     messages: {
	         account: {
	             required: "Enter your email address",
	             email: "Invalid email format",
	             remote: "Email unregistered",
	         },

	         password: {
	             required: "Enter password",
	         },
			/*
	         captcha: {
	             required: "Enter verification code",
	             remote: "Wrong verification code"
	         },
			 captcha:{required:"Enter verification code",remote:"Wrong verification code"},
			 */
	     },

	     submitHandler: function(form) {
	         $.ajax({
	             url: basePath + "/common/public_key.do",
	             type: "GET",
	             dataType: "json",
	             cache: false,
	             beforeSend: function() {
	                 $('#sub-login-mobile').attr("disabled", true);
	                 $("#loading_hint").show();
	                 $("#hint").text("Logging in...");
	             },
	             success: function(data) {
	                 var rsaKey = new RSAKey();
	                 rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
	                 var enPassword = hex2b64(rsaKey.encrypt($("#password_mobile").val()));
	                 $.ajax({
	                     url: $("#mobileLoginForm").attr("action"),
	                     type: "POST",
	                     data: {
	                         account: $("#account_mobile").val(),
	                         enPassword: enPassword,
	                     },
	                     dataType: "json",
	                     cache: false,
	                     complete: function(message) {
	                         $("#loading_hint").hide();
	                         $('#sub-login-mobile').attr("disabled", false);
	                         var result = eval("(" + message.responseText + ")");
	                         if (result.success) {
	                            // location.href = basePath + "/";
	                        	 location.href = basePath + "/member/profile/user_orders.htm";
	                            //location.reload();
	                         } else {
	                             $("#theme-login-error-mobile").html(result.message);
	                         }
	                     }
	                 });
	             }
	         });
	     }
	 });

	 //手机注册表单验证
	 $("form#mobileRegisterForm").validate({
	     rules: {
	         account_register: {
	             required: true,
	             email: true,
	             remote: {
	                 type: "post",
	                 url: basePath + "/register/isExist.do",
	                 cache:false,
	                 data: {
	                     account: function() {
	                         return $("#account_register_mobile").val();
	                     }
	                 },
	                 dataType: "html",
	                 dataFilter: function(data, type) {
	                     if (data == "true") {
	                         return false;
	                     } else {
	                         return true;
	                     }
	                 }
	             }
	         },
	         password_register: {
	             required: true,
	             minlength: 6,
	             maxlength: 20
	         },
	         confirm_password_register: {
	             required: true,
	             equalTo: "#password_register_mobile"
	         }
			 /*
	         captcha_register: {
	             required: true,
	             remote: {
	                 type: "post",
	                 url: basePath + "/register/isCaptcha.do",
	                 cache:false,
	                 data: {
	                     captcha: function() {
	                         return $("#captcha_register_mobile").val();
	                     }
	                 },
	             }
	         }
			 ,captcha_register:{required:true,remote:{type:"post",url:basePath+"/register/isCaptcha.do",cache:false,data:{captcha:function(){return $("#captcha_register_mobile").val()}},}}
			 */
	     },
	     messages: {
	         account_register: {
	             required: "Enter your email address",
	             email: "Invalid email format",
	             remote: "User name already exists"
	         },
	         password_register: {
	             required: "Enter password",
	             minlength: "Your Password's length should be between 6-20 characters",
	             maxlength: "Your Password's length should be between 6-20 characters"
	         },
	         confirm_password_register: {
	             required: "Enter confirm password",
	             equalTo: " The two passwords you typed do not match"
	         },
			 /*
	         captcha_register: {
	             required: "Enter verification code",
	             remote: "Wrong verification code"
	         },
			 captcha_register:{required:"Enter verification code",remote:"Wrong verification code"},
			 */
	     },
	     submitHandler: function(form) {
	         $.ajax({
	             url: basePath + "/common/public_key.do",
	             type: "GET",
	             dataType: "json",
	             cache: false,
	             beforeSend: function() {
	                 $('#sub-register').attr("disabled", true);
	                 $("#loading_hint").show();
	                 $("#hint").text("Being registered...");
	             },
	             success: function(data) {
	                 var rsaKey = new RSAKey();
	                 rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
	                 var enPassword = hex2b64(rsaKey.encrypt($("#password_register_mobile").val()));
	                 $.ajax({
	                     url: $("#mobileRegisterForm").attr("action"),
	                     type: "POST",
	                     data: {
	                         account: $("#account_register_mobile").val(),
	                         enPassword: enPassword,
	                     },
	                     dataType: "json",
	                     cache: false,
	                     complete: function(message) {
	                         $("#loading_hint").hide();
	                         $('#sub-register').attr("disabled", false);
	                         var result = eval("(" + message.responseText + ")");
	                         if (result.success) {
	                             $('.theme-popover-mask-mobile').hide();
	                             $('.theme-popover-register-mobile').slideUp(300); //隐藏注册弹出窗
	                          //   $('#theme-popover-password-mobile').slideDown(400); //提示信息
  	                            alertWarn('Signing in...');
                         	    setTimeout(function(){
                         	    	location.href = basePath + "/member/profile/user_orders.htm";
	                         	   //location.reload();
	                         	},2000);
	                         } else {
	                         	 $("#theme-register-error-mobile").html(result.message);
	                         }
	                     }
	                 });
	             }
	         });
	     }
	 });

	 //手机密码修改
	 $("form#resetForm").validate({
	     rules: {
	         account_reset: {
	             required: true,
	             email: true,
	             remote: {
	                 type: "post",
	                 url: basePath + "/register/isExist.do",
	                 cache:false,
	                 data: {
	                     account: function() {
	                         return $("#account_reset").val();
	                     }
	                 },
	                 dataType: "html",
	                 dataFilter: function(data, type) {
	                     if (data == "true") {
	                         return true;
	                     } else {
	                         return false;
	                     }
	                 }
	             }
	         }
	     },

	     messages: {
	         account_reset: {
	             required: "Enter your email address",
	             email: "Invalid email format",
	             remote: "Email unregistered"
	         },
	     },
	     
	     submitHandler: function(form) {
	     	$.ajax({
	             url: $("#resetForm").attr("action"),
	             type: "POST",
	             data: {
	                 account: $("#account_reset").val(),
	             },
	             dataType: "json",
	             cache: false,
	             beforeSend: function() {
	                 $('#sub-reset-password').attr("disabled", true);
	                 $("#loading_hint").show();
	                 $("#hint").text("正在发送...");
	             },
	             complete: function(message) {
	             	 var result = eval("(" + message.responseText + ")");
	                  if (result.success) {
	                 	$("#loading_hint").hide();
	                  	$("#loading_hint").show();
	                  	$("#hint").text("发送成功...");
	                  	setTimeout(function(){
	                  		 $("#loading_hint").hide();
	                 	    },2000);
						 } else {  
							$("#loading_hint").hide();
							$("#theme-password-error").html(result.message);
			             }   
	                 $('#sub-reset-password').attr("disabled", false);
	             }
	         });
	     }
	 }); 
 
 
 $(document).ajaxComplete(function(event, request, settings) {
		var loginStatus = request.getResponseHeader("loginStatus");
		if (loginStatus == "accessDenied") {
			$.redirectLogin(location.href, "Please login");
		}
	});
 
 // 跳转登录
	$.redirectLogin = function (redirectUrl, message) {
		var href = basePath + "/login.htm";
		if (redirectUrl != null) {
			href += "?redirectUrl=" + encodeURIComponent(redirectUrl);
		}
		if (message != null) {
			alert(message);
			setTimeout(function() {
				location.href = href;
			}, 1000);
		} else {
			location.href = href;
		}
	};
});

// 登录页再次激活
function againActivate () {
	var account = $("#account").val();
	window.location.href= basePath + "/register/user_activate.htm?account=" + account;
	/**$.ajax({
     url: basePath + "/register/again_activate.do",
     type: "post",
     data: {account: $("#account").val()},
     dataType: "json",
     cache: false,
     beforeSend: function() {
         $('#sub-login').attr("disabled", true);
         $("#loading_hint").show();
         $("#hint").text("请稍等...");
     },
     success: function(data) {
     	$("#loading_hint").hide();
         $('#sub-login').attr("disabled", false);
         if (data.success) {
             $('.theme-popover-mask').fadeIn(300);
             $('.theme-popover').slideUp(300); //隐藏登录弹出窗
             $('.theme-popover-success').slideDown(400); //显示注册成功窗口
             $("#email-success-account").text(data.message);
         } else {
         	alert(data.message);
         }
     }
 });*/
}

//注册成功页再次激活
function activate () {
	var account = $("#email-success-account").text();
	window.location.href= basePath + "/register/user_activate.htm?account=" + account;
	/**
	$.ajax({
     url: basePath + "/register/again_activate.do",
     type: "post",
     data: {account: $("#email-success-account").text()},
     dataType: "json",
     cache: false,
     beforeSend: function() {
         $("#loading_hint").show();
         $("#hint").text("请稍等...");
     },
     success: function(data) {
     	$("#loading_hint").hide();
         if (data.success) {
         	alert("激活邮件发送成功。");
             $("#email-success-account").text(data.message);
         } else {
             alert(data.message);
         }
     }
 });
  */
}

//cookie添加币种code
function setCurrency(currency) {
	   addCookie("cc", currency, {path:"/", expires:3600});
	   var uusd = currency.substring(0, 2).toLowerCase();
	   var url = window.location.href;
		   if(url.indexOf("cn/")){
			   url = url.replace("cn/",uusd+"/");
		   }else if(url.indexOf("us/")){
			   url = url.replace("us/",uusd+"/");
		   }else if(url.indexOf("ca/")){
			   url = url.replace("ca/",uusd+"/");
		   }else if(url.indexOf("au/")){
			   url = url.replace("au/",uusd+"/");
		   }else if(url.indexOf("eu/")){
			   url = url.replace("eu/",uusd+"/");
		   }
	  
	   window.location=basePath+"/"+uusd;
	}


//添加Cookie
function addCookie(name, value, options) {
 if (arguments.length > 1 && name != null) {
     if (options == null) {
         options = {};
     }
     if (value == null) {
         options.expires = -1;
     }
     if (typeof options.expires == "number") {
         var time = options.expires;
         var expires = options.expires = new Date();
         expires.setTime(expires.getTime() + time * 1000);
     }
     document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path=" + options.path: "") + (options.domain ? "; domain=" + options.domain: ""),
     (options.secure ? "; secure": "");
 }
}

//获取Cookie
function getCookie(name) {
 if (name != null) {
     var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
     return value ? decodeURIComponent(value[1]) : null;
 }
}

//移除Cookie
function removeCookie(name, options) {
 addCookie(name, null, options);
}

//验证码
function checkCapacha(captcha) {
	var flag = false;
	$.ajax({
     url:  basePath + "/register/isCaptcha.do",
     type: "post",
     data: {captcha: captcha},
     dataType: "json",
     async: false,
     success: function(data) {
     	if(data){
     		flag = true;
     	}
     }
	});
	return flag;
}

//记录浏览历史
var recordVisitedHistory = function(tourname,mprice,firstImageUrl){
	var code = getCookie("cc");
	var historyRecordCookieName = 'visitedHistory_' + code;
	var currentPageUrl = window.location.href;
	
	var visitedHistoryJson = getCookie(historyRecordCookieName);
	var historyRecordArray;
	if(visitedHistoryJson != null && visitedHistoryJson != ''){
		historyRecordArray = JSON.parse(visitedHistoryJson);
	}else{
		historyRecordArray = new Array();
	}
	for(var i=0; i<historyRecordArray.length; i++){
		if(historyRecordArray[i].url == currentPageUrl){
			historyRecordArray.splice(i,1);
		}
	}
	historyRecordArray.unshift(new HistoryRecord(tourname,code,mprice,firstImageUrl,currentPageUrl));
	if(historyRecordArray.length > HISTORY_RECORED_SIZE){
		historyRecordArray =  historyRecordArray.slice(0, HISTORY_RECORED_SIZE);
	}
	visitedHistoryJson = JSON.stringify(historyRecordArray);
	addCookie(historyRecordCookieName, visitedHistoryJson, {path:"/", expires:HISTORY_RECORED_EXPIRES}); 
};

//删除历史记录
var removeVisitedHistory = function(i){
	var code = getCookie("cc");
	var historyRecordCookieName = 'visitedHistory_' + code;
	var visitedHistoryJson = getCookie(historyRecordCookieName);
	var historyRecordArray = JSON.parse(visitedHistoryJson);
	historyRecordArray.splice(i,1);
	visitedHistoryJson = JSON.stringify(historyRecordArray);
	addCookie(historyRecordCookieName, visitedHistoryJson, {path:"/", expires:HISTORY_RECORED_EXPIRES}); 
};

//获得历史记录
var getVisitedHistory = function(){
	var code = getCookie("cc");
	var historyRecordCookieName = 'visitedHistory_' + code;
	var visitedHistoryJson = getCookie(historyRecordCookieName);
	var historyRecordArray;
	if(visitedHistoryJson != null && visitedHistoryJson != ''){
		historyRecordArray = JSON.parse(visitedHistoryJson);
	}else{
		historyRecordArray = new Array();
	}
	return historyRecordArray;
};

//历史记录对象
function HistoryRecord(tourname,code,mprice,firstImageUrl,url){
	//线路名称
	this.tourname = tourname;
	//销售中心的币种
	this.code = code;
	//最低价格
	this.mprice = mprice;
	//首张图片的url
	this.firstImageUrl = firstImageUrl;
	//线路详情页的url
	this.url = url;
};