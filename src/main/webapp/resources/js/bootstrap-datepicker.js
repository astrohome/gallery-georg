!function (e) {
    var t = function (t, n) {
        this.element = e(t);
        this.format = DPGlobal.parseFormat(n.format || this.element.data("date-format") || "mm/dd/yyyy");
        this.picker = e(DPGlobal.template).appendTo("body").on({click: e.proxy(this.click, this)});
        this.isInput = this.element.is("input");
        this.component = this.element.is(".date") ? this.element.find(".opencalendar") : false;
        if (this.isInput) {
            this.element.on({focus: e.proxy(this.show, this), keyup: e.proxy(this.update, this)})
        } else {
            if (this.component) {
                this.component.on("click", e.proxy(this.show, this))
            } else {
                this.element.on("click", e.proxy(this.show, this))
            }
        }
        this.minViewMode = n.minViewMode || this.element.data("date-minviewmode") || 0;
        if (typeof this.minViewMode === "string") {
            switch (this.minViewMode) {
                case"months":
                    this.minViewMode = 1;
                    break;
                case"years":
                    this.minViewMode = 2;
                    break;
                default:
                    this.minViewMode = 0;
                    break
            }
        }
        this.viewMode = n.viewMode || this.element.data("date-viewmode") || 0;
        if (typeof this.viewMode === "string") {
            switch (this.viewMode) {
                case"months":
                    this.viewMode = 1;
                    break;
                case"years":
                    this.viewMode = 2;
                    break;
                default:
                    this.viewMode = 0;
                    break
            }
        }
        this.startViewMode = this.viewMode;
        this.weekStart = n.weekStart || this.element.data("date-weekstart") || 0;
        this.weekEnd = this.weekStart === 0 ? 6 : this.weekStart - 1;
        this.onRender = n.onRender;
        this.fillDow();
        this.fillMonths();
        this.update();
        this.showMode()
    };
    t.prototype = {constructor: t, show: function (t) {
        this.picker.show();
        this.height = this.component ? this.component.outerHeight() : this.element.outerHeight();
        this.place();
        e(window).on("resize", e.proxy(this.place, this));
        if (t) {
            t.stopPropagation();
            t.preventDefault()
        }
        if (!this.isInput) {
        }
        var n = this;
        e(document).on("mousedown", function (t) {
            if (e(t.target).closest(".datepicker").length == 0) {
                n.hide()
            }
        });
        this.element.trigger({type: "show", date: this.date})
    }, hide: function () {
        this.picker.hide();
        e(window).off("resize", this.place);
        this.viewMode = this.startViewMode;
        this.showMode();
        if (!this.isInput) {
            e(document).off("mousedown", this.hide)
        }
        this.element.trigger({type: "hide", date: this.date})
    }, set: function () {
        var e = DPGlobal.formatDate(this.date, this.format, false);
        if (!this.isInput) {
            if (this.component) {
                this.element.find("input").prop("value", e)
            }
            this.element.data("date", e)
        } else {
            this.element.prop("value", e)
        }
    }, setValue: function (e) {
        if (typeof e === "string") {
            this.date = DPGlobal.parseDate(e, this.format)
        } else {
            this.date = new Date(e)
        }
        this.set();
        this.viewDate = new Date(this.date.getFullYear(), this.date.getMonth(), 1, 0, 0, 0, 0);
        this.fill()
    }, place: function () {
        var e = this.component ? this.component.offset() : this.element.offset();
        this.picker.css({top: e.top + this.height, left: e.left})
    }, update: function (e) {
        this.date = DPGlobal.parseDate(typeof e === "string" ? e : this.isInput ? this.element.prop("value") : this.element.data("date"), this.format);
        this.viewDate = new Date(this.date.getFullYear(), this.date.getMonth(), 1, 0, 0, 0, 0);
        this.fill()
    }, fillDow: function () {
        var e = this.weekStart;
        var t = "<tr>";
        while (e < this.weekStart + 7) {
            t += '<th class="dow">' + DPGlobal.dates.daysMin[e++ % 7] + "</th>"
        }
        t += "</tr>";
        this.picker.find(".datepicker-days thead").append(t)
    }, fillMonths: function () {
        var e = "";
        var t = 0;
        while (t < 12) {
            e += '<span class="month">' + DPGlobal.dates.monthsShort[t++] + "</span>"
        }
        this.picker.find(".datepicker-months td").append(e)
    }, fill: function () {
        var e = new Date(this.viewDate), t = e.getFullYear(), n = e.getMonth(), r = this.date.valueOf();
        this.picker.find(".datepicker-days th:eq(1)").text(DPGlobal.dates.months[n] + " " + t);
        var i = new Date(t, n - 1, 28, 0, 0, 0, 0), s = DPGlobal.getDaysInMonth(i.getFullYear(), i.getMonth());
        i.setDate(s);
        i.setDate(s - (i.getDay() - this.weekStart + 7) % 7);
        var o = new Date(i);
        o.setDate(o.getDate() + 42);
        o = o.valueOf();
        var u = [];
        var a, f, l;
        while (i.valueOf() < o) {
            if (i.getDay() === this.weekStart) {
                u.push("<tr>")
            }
            a = this.onRender(i);
            f = i.getFullYear();
            l = i.getMonth();
            if (l < n && f === t || f < t) {
                a += " old"
            } else if (l > n && f === t || f > t) {
                a += " new"
            }
            if (i.valueOf() === r) {
                a += " active"
            }
            u.push('<td class="day ' + a + '">' + i.getDate() + "</td>");
            if (i.getDay() === this.weekEnd) {
                u.push("</tr>")
            }
            i.setDate(i.getDate() + 1)
        }
        this.picker.find(".datepicker-days tbody").empty().append(u.join(""));
        var c = this.date.getFullYear();
        var h = this.picker.find(".datepicker-months").find("th:eq(1)").text(t).end().find("span").removeClass("active");
        if (c === t) {
            h.eq(this.date.getMonth()).addClass("active")
        }
        u = "";
        t = parseInt(t / 10, 10) * 10;
        var p = this.picker.find(".datepicker-years").find("th:eq(1)").text(t + "-" + (t + 9)).end().find("td");
        t -= 1;
        for (var d = -1; d < 11; d++) {
            u += '<span class="year' + (d === -1 || d === 10 ? " old" : "") + (c === t ? " active" : "") + '">' + t + "</span>";
            t += 1
        }
        p.html(u)
    }, click: function (t) {
        t.stopPropagation();
        t.preventDefault();
        var n = e(t.target).closest("span, td, th");
        if (n.length === 1) {
            switch (n[0].nodeName.toLowerCase()) {
                case"th":
                    switch (n[0].className) {
                        case"switch":
                            this.showMode(1);
                            break;
                        case"prev":
                        case"next":
                            this.viewDate["set" + DPGlobal.modes[this.viewMode].navFnc].call(this.viewDate, this.viewDate["get" + DPGlobal.modes[this.viewMode].navFnc].call(this.viewDate) + DPGlobal.modes[this.viewMode].navStep * (n[0].className === "prev" ? -1 : 1));
                            this.fill();
                            this.set();
                            break
                    }
                    break;
                case"span":
                    if (n.is(".month")) {
                        var r = n.parent().find("span").index(n);
                        this.viewDate.setMonth(r)
                    } else {
                        var i = parseInt(n.text(), 10) || 0;
                        this.viewDate.setFullYear(i)
                    }
                    if (this.viewMode !== 0) {
                        this.date = new Date(this.viewDate);
                        this.element.trigger({type: "changeDate", date: this.date, viewMode: DPGlobal.modes[this.viewMode].clsName})
                    }
                    this.showMode(-1);
                    this.fill();
                    this.set();
                    break;
                case"td":
                    if (n.is(".day") && !n.is(".disabled")) {
                        var s = parseInt(n.text(), 10) || 1;
                        var r = this.viewDate.getMonth();
                        if (n.is(".old")) {
                            r -= 1
                        } else if (n.is(".new")) {
                            r += 1
                        }
                        var i = this.viewDate.getFullYear();
                        this.date = new Date(i, r, s, 0, 0, 0, 0);
                        this.viewDate = new Date(i, r, Math.min(28, s), 0, 0, 0, 0);
                        this.fill();
                        this.set();
                        this.element.trigger({type: "changeDate", date: this.date, viewMode: DPGlobal.modes[this.viewMode].clsName})
                    }
                    break
            }
        }
    }, mousedown: function (e) {
        e.stopPropagation();
        e.preventDefault()
    }, showMode: function (e) {
        if (e) {
            this.viewMode = Math.max(this.minViewMode, Math.min(2, this.viewMode + e))
        }
        this.picker.find(">div").hide().filter(".datepicker-" + DPGlobal.modes[this.viewMode].clsName).show()
    }};
    e.fn.datepicker = function (n, r) {
        return this.each(function () {
            var i = e(this), s = i.data("datepicker"), o = typeof n === "object" && n;
            if (!s) {
                i.data("datepicker", s = new t(this, e.extend({}, e.fn.datepicker.defaults, o)))
            }
            if (typeof n === "string")s[n](r)
        })
    };
    e.fn.datepicker.defaults = {onRender: function (e) {
        return""
    }};
    e.fn.datepicker.Constructor = t
}(window.jQuery);
var DPGlobal = {modes: [
    {clsName: "days", navFnc: "Month", navStep: 1},
    {clsName: "months", navFnc: "FullYear", navStep: 1},
    {clsName: "years", navFnc: "FullYear", navStep: 10}
], dates: {days: ["Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"], daysShort: ["Вск", "Пнд", "Втр", "Срд", "Чтв", "Птн", "Суб", "Вск"], daysMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"], months: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"], monthsFormated: ["января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"], monthsShort: ["Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"], today: "Сегодня"}, isLeapYear: function (e) {
    return e % 4 === 0 && e % 100 !== 0 || e % 400 === 0
}, getDaysInMonth: function (e, t) {
    return[31, DPGlobal.isLeapYear(e) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][t]
}, parseFormat: function (e) {
    var t = e.match(/[.\/\-\s].*?/), n = e.split(/\W+/);
    if (!t || !n || n.length === 0) {
        throw new Error("Invalid date format.")
    }
    return{separator: t, parts: n}
}, parseDate: function (e, t) {
    var n = e.split(t.separator), e = new Date, r;
    e.setHours(0);
    e.setMinutes(0);
    e.setSeconds(0);
    e.setMilliseconds(0);
    if (n.length === t.parts.length) {
        var i = e.getFullYear(), s = e.getDate(), o = e.getMonth();
        for (var u = 0, a = t.parts.length; u < a; u++) {
            r = parseInt(n[u], 10) || 1;
            switch (t.parts[u]) {
                case"dd":
                case"d":
                    s = r;
                    e.setDate(r);
                    break;
                case"mm":
                case"m":
                    o = r - 1;
                    e.setMonth(r - 1);
                    break;
                case"yy":
                    i = 2e3 + r;
                    e.setFullYear(2e3 + r);
                    break;
                case"yyyy":
                    i = r;
                    e.setFullYear(r);
                    break
            }
        }
        e = new Date(i, o, s, 0, 0, 0)
    }
    return e
}, formatDate: function (e, t, n) {
    var r = {d: e.getDate(), m: e.getMonth() + 1, yy: e.getFullYear().toString().substring(2), yyyy: e.getFullYear()};
    r.dd = (r.d < 10 ? "0" : "") + r.d;
    if (n) {
        r.mm = (r.m < 10 ? "0" : "") + r.m
    } else {
        r.mm = DPGlobal.dates.monthsFormated[r.m - 1]
    }
    var e = [];
    for (var i = 0, s = t.parts.length; i < s; i++) {
        e.push(r[t.parts[i]])
    }
    return e.join(t.separator)
}, headTemplate: "<thead>" + "<tr>" + '<th class="prev">&lsaquo;</th>' + '<th colspan="5" class="switch"></th>' + '<th class="next">&rsaquo;</th>' + "</tr>" + "</thead>", contTemplate: '<tbody><tr><td colspan="7"></td></tr></tbody>'};
DPGlobal.template = '<div class="datepicker dropdown-menu">' + '<div class="datepicker-days">' + '<table class=" table-condensed">' + DPGlobal.headTemplate + "<tbody></tbody>" + "</table>" + "</div>" + '<div class="datepicker-months">' + '<table class="table-condensed">' + DPGlobal.headTemplate + DPGlobal.contTemplate + "</table>" + "</div>" + '<div class="datepicker-years">' + '<table class="table-condensed">' + DPGlobal.headTemplate + DPGlobal.contTemplate + "</table>" + "</div>" + "</div>"