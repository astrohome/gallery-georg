(function (e, t) {
    var n = /[<>&\r\n"']/gm;
    var r = {"<": "lt;", ">": "gt;", "&": "amp;", "\r": "#13;", "\n": "#10;", '"': "quot;", "'": "apos;"};
    e.extend({fileDownload: function (i, s) {
        function E() {
            if (document.cookie.indexOf(o.cookieName + "=" + o.cookieValue) != -1) {
                d.onSuccess(i);
                document.cookie = o.cookieName + "=; expires=" + (new Date(1e3)).toUTCString() + "; path=" + o.cookiePath;
                x(false);
                return
            }
            if (m || v) {
                try {
                    var t = m ? m.document : S(v);
                    if (t && t.body != null && t.body.innerHTML.length) {
                        var n = true;
                        if (y && y.length) {
                            var r = e(t.body).contents().first();
                            if (r.length && r[0] === y[0]) {
                                n = false
                            }
                        }
                        if (n) {
                            d.onFail(t.body.innerHTML, i);
                            x(true);
                            return
                        }
                    }
                } catch (s) {
                    d.onFail("", i);
                    x(true);
                    return
                }
            }
            setTimeout(E, o.checkInterval)
        }

        function S(e) {
            var t = e[0].contentWindow || e[0].contentDocument;
            if (t.document) {
                t = t.document
            }
            return t
        }

        function x(e) {
            setTimeout(function () {
                if (m) {
                    if (l) {
                        m.close()
                    }
                    if (f) {
                        m.focus();
                        if (e) {
                            m.close()
                        }
                    }
                }
            }, 0)
        }

        function T(e) {
            return e.replace(n, function (e) {
                return"&" + r[e]
            })
        }

        var o = e.extend({preparingMessageHtml: null, failMessageHtml: null, androidPostUnsupportedMessageHtml: "Unfortunately your Android browser doesn't support this type of file download. Please try again with a different browser.", dialogOptions: {modal: true}, prepareCallback: function (e) {
        }, successCallback: function (e) {
        }, failCallback: function (e, t) {
        }, httpMethod: "GET", data: null, checkInterval: 100, cookieName: "fileDownload", cookieValue: "true", cookiePath: "/", popupWindowTitle: "Initiating file download...", encodeHTMLEntities: true}, s);
        var u = new e.Deferred;
        var a = (navigator.userAgent || navigator.vendor || t.opera).toLowerCase();
        var f;
        var l;
        var c;
        if (/ip(ad|hone|od)/.test(a)) {
            f = true
        } else if (a.indexOf("android") !== -1) {
            l = true
        } else {
            c = /avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|playbook|silk|iemobile|iris|kindle|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(a) || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|e\-|e\/|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\-|2|g)|yas\-|your|zeto|zte\-/i.test(a.substr(0, 4))
        }
        var h = o.httpMethod.toUpperCase();
        if (l && h !== "GET") {
            if (e().dialog) {
                e("<div>").html(o.androidPostUnsupportedMessageHtml).dialog(o.dialogOptions)
            } else {
                alert(o.androidPostUnsupportedMessageHtml)
            }
            return u.reject()
        }
        var p = null;
        var d = {onPrepare: function (t) {
            if (o.preparingMessageHtml) {
                p = e("<div>").html(o.preparingMessageHtml).dialog(o.dialogOptions)
            } else if (o.prepareCallback) {
                o.prepareCallback(t)
            }
        }, onSuccess: function (e) {
            if (p) {
                p.dialog("close")
            }
            o.successCallback(e);
            u.resolve(e)
        }, onFail: function (t, n) {
            if (p) {
                p.dialog("close")
            }
            if (o.failMessageHtml) {
                e("<div>").html(o.failMessageHtml).dialog(o.dialogOptions)
            }
            o.failCallback(t, n);
            u.reject(t, n)
        }};
        d.onPrepare(i);
        if (o.data !== null && typeof o.data !== "string") {
            o.data = e.param(o.data)
        }
        var v, m, g, y;
        if (h === "GET") {
            if (o.data !== null) {
                var b = i.indexOf("?");
                if (b !== -1) {
                    if (i.substring(i.length - 1) !== "&") {
                        i = i + "&"
                    }
                } else {
                    i = i + "?"
                }
                i = i + o.data
            }
            if (f || l) {
                m = t.open(i);
                m.document.title = o.popupWindowTitle;
                t.focus()
            } else if (c) {
                t.location(i)
            } else {
                v = e("<iframe>").hide().prop("src", i).appendTo("body")
            }
        } else {
            var w = "";
            if (o.data !== null) {
                e.each(o.data.replace(/\+/g, " ").split("&"), function () {
                    var e = this.split("=");
                    var t = o.encodeHTMLEntities ? T(decodeURIComponent(e[0])) : decodeURIComponent(e[0]);
                    if (t) {
                        var n = o.encodeHTMLEntities ? T(decodeURIComponent(e[1])) : decodeURIComponent(e[1]);
                        w += '<input type="hidden" name="' + t + '" value="' + n + '" />'
                    }
                })
            }
            if (c) {
                y = e("<form>").appendTo("body");
                y.hide().prop("method", o.httpMethod).prop("action", i).html(w)
            } else {
                if (f) {
                    m = t.open("about:blank");
                    m.document.title = o.popupWindowTitle;
                    g = m.document;
                    t.focus()
                } else {
                    v = e("<iframe style='display: none' src='about:blank'></iframe>").appendTo("body");
                    g = S(v)
                }
                g.write("<html><head></head><body><form method='" + o.httpMethod + "' action='" + i + "'>" + w + "</form>" + o.popupWindowTitle + "</body></html>");
                y = e(g).find("form")
            }
            y.submit()
        }
        setTimeout(E, o.checkInterval);
        return u.promise()
    }})
})(jQuery, this)