(function (e) {
    "use strict";
    var t = function (e, n, r) {
        var i = document.createElement("img"), s, o;
        i.onerror = n;
        i.onload = function () {
            if (o && !(r && r.noRevoke)) {
                t.revokeObjectURL(o)
            }
            if (n) {
                n(t.scale(i, r))
            }
        };
        if (t.isInstanceOf("Blob", e) || t.isInstanceOf("File", e)) {
            s = o = t.createObjectURL(e);
            i._type = e.type
        } else if (typeof e === "string") {
            s = e;
            if (r && r.crossOrigin) {
                i.crossOrigin = r.crossOrigin
            }
        } else {
            return false
        }
        if (s) {
            i.src = s;
            return i
        }
        return t.readFile(e, function (e) {
            var t = e.target;
            if (t && t.result) {
                i.src = t.result
            } else {
                if (n) {
                    n(e)
                }
            }
        })
    }, n = window.createObjectURL && window || window.URL && URL.revokeObjectURL && URL || window.webkitURL && webkitURL;
    t.isInstanceOf = function (e, t) {
        return Object.prototype.toString.call(t) === "[object " + e + "]"
    };
    t.transformCoordinates = function (e, t) {
        var n = e.getContext("2d"), r = e.width, i = e.height;
        if (t > 4) {
            e.width = i;
            e.height = r
        }
        switch (t) {
            case 2:
                n.translate(r, 0);
                n.scale(-1, 1);
                break;
            case 3:
                n.translate(r, i);
                n.rotate(Math.PI);
                break;
            case 4:
                n.translate(0, i);
                n.scale(1, -1);
                break;
            case 5:
                n.rotate(.5 * Math.PI);
                n.scale(1, -1);
                break;
            case 6:
                n.rotate(.5 * Math.PI);
                n.translate(0, -i);
                break;
            case 7:
                n.rotate(.5 * Math.PI);
                n.translate(r, -i);
                n.scale(-1, 1);
                break;
            case 8:
                n.rotate(-.5 * Math.PI);
                n.translate(-r, 0);
                break
        }
    };
    t.renderImageToCanvas = function (e, t, n, r, i, s, o, u, a, f) {
        e.getContext("2d").drawImage(t, n, r, i, s, o, u, a, f);
        return e
    };
    t.scale = function (e, n) {
        n = n || {};
        var r = document.createElement("canvas"), i = e.getContext || (n.canvas || n.crop || n.orientation) && r.getContext, s = e.width, o = e.height, u = s, a = o, f = 0, l = 0, c = 0, h = 0, p, d, v, m, g, y, b;
        if (i && n.orientation > 4) {
            p = n.maxHeight;
            d = n.maxWidth;
            v = n.minHeight;
            m = n.minWidth
        } else {
            p = n.maxWidth;
            d = n.maxHeight;
            v = n.minWidth;
            m = n.minHeight
        }
        if (i && p && d && n.crop) {
            g = p;
            y = d;
            if (s / o < p / d) {
                a = d * s / p;
                l = (o - a) / 2
            } else {
                u = p * o / d;
                f = (s - u) / 2
            }
        } else {
            g = s;
            y = o;
            b = Math.max((v || g) / g, (m || y) / y);
            if (b > 1) {
                g = Math.ceil(g * b);
                y = Math.ceil(y * b)
            }
            b = Math.min((p || g) / g, (d || y) / y);
            if (b < 1) {
                g = Math.ceil(g * b);
                y = Math.ceil(y * b)
            }
        }
        if (i) {
            r.width = g;
            r.height = y;
            t.transformCoordinates(r, n.orientation);
            return t.renderImageToCanvas(r, e, f, l, u, a, c, h, g, y)
        }
        e.width = g;
        e.height = y;
        return e
    };
    t.createObjectURL = function (e) {
        return n ? n.createObjectURL(e) : false
    };
    t.revokeObjectURL = function (e) {
        return n ? n.revokeObjectURL(e) : false
    };
    t.readFile = function (e, t, n) {
        if (window.FileReader) {
            var r = new FileReader;
            r.onload = r.onerror = t;
            n = n || "readAsDataURL";
            if (r[n]) {
                r[n](e);
                return r
            }
        }
        return false
    };
    if (typeof define === "function" && define.amd) {
        define(function () {
            return t
        })
    } else {
        e.loadImage = t
    }
})(this)