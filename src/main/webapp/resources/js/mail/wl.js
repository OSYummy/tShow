(function() {
    if (!window.WL) {
        var Wg = "download"
            , h = "interface_method"
            , He = "WL.Internal.jsonp."
            , Cd = 2000
            , ee = "body"
            , r = "callback"
            , Tb = "code"
            , bb = "element"
            , ae = "error"
            , sh = "error_description"
            , Md = "logging"
            , yh = "tracing"
            , Mb = "message"
            , R = "method"
            , cg = "file_input"
            , qh = "stream_input"
            , Kb = "file_name"
            , th = "file_output"
            , L = "overwrite"
            , z = "path"
            , Ag = "pretty"
            , Bg = "result"
            , Ah = "status"
            , Kf = "return_ssl_resources"
            , wh = "success"
            , Bh = "error"
            , pd = "suppress_redirects"
            , Xc = "suppress_response_codes"
            , O = "x_http_live_library"
            , be = 0
            , qd = 1
            , d = "access_token"
            , Yg = "appstate"
            , Yb = "authentication_token"
            , q = "client_id"
            , dc = "display"
            , hh = "code"
            , l = "error"
            , E = "error_description"
            , fb = "expires"
            , eb = "expires_in"
            , ch = "locale"
            , x = "redirect_uri"
            , kb = "response_type"
            , s = "request_ts"
            , he = "resource_id"
            , f = "scope"
            , we = "session"
            , Pb = "secure_cookie"
            , V = "state"
            , e = "status"
            , Sb = [d, Yb, f, eb, fb]
            , F = "connected"
            , Fg = "notConnected"
            , T = "unchecked"
            , wb = "unknown"
            , cc = "expiring"
            , Gh = "expired"
            , Eh = "live-sdk-upload"
            , zh = "live-sdk-download"
            , gh = "appId"
            , Tg = "channelUrl"
            , ue = "wl_auth"
            , je = "wl_upload"
            , ne = "page"
            , Ic = "touch"
            , ac = "none"
            , Vd = "none"
            , Qb = "auth.login"
            , Ac = "auth.logout"
            , X = "auth.sessionChange"
            , nc = "auth.statusChange"
            , ze = "wl.log"
            , Hb = "access_denied"
            , uf = "connection_failed"
            , kg = "invalid_cookie"
            , Lf = "invalid_request"
            , lb = "request_canceled"
            , K = "request_failed"
            , Ub = "timed_out"
            , xh = "unknown_user"
            , vh = "user_canceled"
            , kf = "METHOD: Failed to get the required user permission to perform this operation."
            , lf = "The request could not be completed due to browser issues."
            , id = "The request could not be completed due to browser limitations."
            , Nb = "METHOD: The operation has been canceled."
            , ed = "The 'wl_auth' cookie is not valid."
            , Zc = "The 'wl_auth' cookie has been modified incorrectly. Ensure that the redirect URI only modifies sub-keys for values received from the OAuth endpoint."
            , Le = "The 'wl_auth' cookie has multiple values. Ensure that the redirect URI specifies a cookie domain and path when setting cookies."
            , Cf = "METHOD: The input property 'PARAM' does not reference a valid DOM element."
            , Sf = "METHOD: An exception was received for EVENT. Detail: MESSAGE"
            , Df = "METHOD: The WL object must be initialized with WL.init() prior to invoking this method."
            , md = "A connection to the server could not be established."
            , oh = "The user could not be identified."
            , vf = "The pending login request has been canceled."
            , Pe = "Logging out the user is not supported in current session because the user is logged in with a Microsoft account on this computer. To logout, the user may quit the app or log out from the computer."
            , jd = "METHOD: The input value for parameter/property 'PARAM' is not valid."
            , mf = "METHOD: The input parameter/property 'PARAM' must be included."
            , Re = "METHOD: The type of the provided value for the input parameter/property 'PARAM' is not valid."
            , gc = "METHOD: There is a pending METHOD request, the current call will be ignored."
            , Je = gc.replace(/METHOD/g, "WL.login")
            , Ee = gc.replace(/METHOD/g, "WL.fileDialog")
            , mh = gc.replace(/METHOD/g, "WL.upload")
            , Qe = "METHOD: The input property 'redirect_uri' is required if the value of the 'response_type' property is 'code'."
            , lh = "WL.init: The redirect_uri value should be the same as the value of 'Redirect Domain' of your registered app. It must begin with 'http://' or 'https://'."
            , nh = "METHOD: The api call is not supported on this platform."
            , kh = "WL.init: The response_type value 'code' is not supported on this platform."
            , lg = "METHOD: The input property 'redirect_uri' must use https: to match the scheme of the current page."
            , nf = "The auth request is timed out.", Ef = "The popup is closed without receiving consent."
            , Bc = 0
            , ef = 1
            , kd = 2
            , mg = 3
            , Rb = "GET"
            , Xd = "POST"
            , Kg = "PUT"
            , og = "DELETE"
            , Ch = "COPY"
            , Dh = "MOVE"
            , yf = 30000
            , jh = "METHOD"
            , o = "onSuccess"
            , p = "onError"
            , I = "onProgress"
            , Zb = "redirect_type"
            , Kd = "auth"
            , yd = "upload"
            , pg = "code"
            , hb = "token"
            , bc = "https:"
            , Nc = "http:"
            , pe = "wl.signin"
            , Qg = "wl.skydrive"
            , Pf = "wl.skydrive_update"
            , xg = /\s|,/
            , qb = "boolean"
            , Rc = "dom"
            , j = "function"
            , ve = "number"
            , b = "string"
            , vb = "object"
            , zc = "string_or_array"
            , Vb = "undefined"
            , Vg = "name"
            , cb = "element"
            , Wb = "brand"
            , Lc = "type"
            , Db = "sign_in_text"
            , Bb = "sign_out_text"
            , D = "theme"
            , Fd = "onloggedin"
            , Ad = "onloggedout"
            , mb = "onerror"
            , qg = "messenger"
            , Eg = "hotmail"
            , yg = "skydrive"
            , Cc = "windows"
            , zd = "windowslive"
            , Kc = "none"
            , Pc = "signin"
            , qc = Pc
            , Bd = "login"
            , ud = "connect"
            , xd = "custom"
            , Fb = "blue"
            , Eb = "white"
            , uh = "dark"
            , rh = "light"
            , de = "id"
            , db = "auth_server"
            , Q = "apiservice_uri"
            , S = "skydrive_uri"
            , G = "sdk_root"
            , ec = "wl_trace";

        window.WL = {
            getSession: function() {
                try {
                    return a.getSession()
                } catch (b) {
                    t(b.message)
                }
            }
            ,getLoginStatus: function() {
                try {
                    return a.getLoginStatus({callback: Id(arguments, j, 2),internal: false}, Id(arguments, qb, 2))
                } catch (d) {
                    return J("WL.getLoginStatus", d)
                }
            }
            ,logout: function(b) {
                try {
                    Xb(b, N, "WL.logout");
                    return a.logout({callback: b})
                } catch (c) {
                    return J("WL.logout", c)
                }
            }
            ,canLogout: function() {
                return a.canLogout()
            },
            api: function() {
                try {
                    var c = Nf(arguments);
                    n(c, [{name: z,type: b,optional: false}, {name: R,type: b,optional: true}, N], "WL.api");
                    return a.api(c)
                } catch (f) {
                    return J("WL.api", f)
                }
            }
        };

        var ie = [Qb, Ac, X, nc, ze];

        WL.Event = {
            subscribe: function(d, a) {
                try {
                    Xb([d, a], [{name: "event",type: b,allowedValues: ie,caseSensitive: true,optional: false}, bf], "WL.Event.subscribe");
                    c.subscribe(d, a)
                } catch (e) {
                    t(e.message)
                }
            }
            ,unsubscribe: function(d, a) {
                try {
                    Xb([d, a], [{name: "event",type: b,allowedValues: ie,caseSensitive: true,optional: false}, N], "WL.Event.unsubscribe");
                    c.unsubscribe(d, a)
                } catch (e) {
                    t(e.message)
                }
            }
        };

        WL.Internal = {};

        var c = {
                subscribe: function(a, b) {
                    i("Subscribe " + a);
                    var d = c.getHandlers(a);
                    d.push(b)
                }
                ,unsubscribe: function(d, f) {
                    i("Unsubscribe " + d);
                    var b = c.getHandlers(d), e = [];
                    if (f != null) {
                        var g = false;
                        for (var a = 0; a < b.length; a++)
                            if (g || b[a] != f)
                                e.push(b[a]);
                            else
                                g = true
                    }
                    c._eHandlers[d] = e
                }
                ,getHandlers: function(b) {
                    if (!c._eHandlers)
                        c._eHandlers = {};
                    var a = c._eHandlers[b];
                    if (a == null)
                        c._eHandlers[b] = a = [];
                    return a
                }
                ,notify: function(d, e) {
                    i("Notify " + d);
                    var b = c.getHandlers(d);
                    for (var a = 0; a < b.length; a++)
                        b[a](e)
                }
            }
            , a = {
                _status: be,
                _statusRequests: [],
                _rpsAuth: false
            };

        a.appInit = function(c) {
            if (a._status == qd) {
                var e = a._session.getNormalStatus();
                return Z("WL.init", true, c.callback, e)
            }
            var b = WL[G];
            if (b) {
                if (b.charAt(b.length - 1) !== "/")
                    b += "/";
                a[G] = b
            }
            var d = c[Md];
            if (d === false)
                a._logEnabled = d;
            if (a.testInit)
                a.testInit(c);
            a._status = qd;
            return rf(c)
        };

        a.onloadInit = function() {
            Ng();
            Pg()
        };

        function y(b) {
            if (a._status === be)
                throw new Error(Df.replace("METHOD", b))
        }

        function Oc() {
            return WL.Internal.tApp || a
        }

        a.api = function(a) {
            y("WL.api");
            var c = a[ee];
            if (c) {
                a = M(Gc(c), a);
                delete a[ee]
            }
            var b = a[R];
            a[R] = (b != null ? U(b) : Rb).toUpperCase();
            return (new xe(a)).execute()
        };

        var Uf = function() {
                var b = a.api.lastId, c;
                b = b === undefined ? 1 : b + 1;
                c = "WLAPI_REQ_" + b + "_" + (new Date).getTime();
                a.api.lastId = b;
                return c
            }
            , xe = function(b) {
                var c = this;
                c._properties = b;
                c._completed = false;
                c._id = Uf();
                b[Ag] = false;
                b[Kf] = a._isHttps;
                b[O] = a[O];
                var d = b[z];
                c._url = Wd() + (d.charAt(0) === "/" ? d.substring(1) : d);
                c._promise = new m("WL.api", null, null)
            };

        xe.prototype = {
            execute: function() {
                ug(this);
                return this._promise
            }
            ,onCompleted: function(a) {
                if (this._completed)
                    return;
                this._completed = true;
                C(this._properties.callback, a, true);
                if (a[l])
                    this._promise[p](a);
                else
                    this._promise[o](a)
            }
        };

        function yc(e, c, a, d) {
            a = a ? U(a) : "";
            var b = a !== "" ? Dc(a) : null;
            if (b === null) {
                b = {};
                if (c / 100 !== 2)
                    b[ae] = rg(c, d)
            }
            e.onCompleted(b)
        }

        function rg(c, b) {
            var a = {};
            a[Tb] = K;
            a[Mb] = b || md;
            return a
        }

        function rc() {
            var c = null;
            if (!a._rpsAuth) {
                var b = Oc()._session.getStatus();
                if (b.status === cc || b.status === F)
                    c = b.session[d]
            }
            return c
        }

        function Gc(i) {
            var c = {};
            for (var b in i) {
                var a = i[b], j = typeof a;
                if (a instanceof Array)
                    for (var d = 0; d < a.length; d++) {
                        var f = a[d], l = typeof f;
                        if (j == vb && !(a instanceof Date)) {
                            var h = Gc(f);
                            for (var e in h)
                                c[b + "." + d + "." + e] = h[e]
                        } else
                            c[b + "." + d] = f
                    }
                else if (j == vb && !(a instanceof Date)) {
                    var k = Gc(a);
                    for (var g in k)
                        c[b + "." + g] = k[g]
                } else
                    c[b] = a
            }
            return c
        }

        function Zf(c) {
            if (!fh())
                return false;
            var b = Sd(c), a = new XMLHttpRequest;
            a.open(b.method, b.url, true);
            var d = c._properties[R];
            if (b.method != Rb)
                a.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            a.onreadystatechange = function() {
                if (a.readyState == 4)
                    yc(c, a.status, a.responseText)
            };
            a.send(b.body);
            return true
        }

        function Sd(e) {
            var a = Od(e._properties, null, [r, z, R]), f = e._properties[R], g = gb(e._url, {"ts": (new Date).getTime()}), h = rc(), c, b;
            a[pd] = "true";
            a[Xc] = "true";
            if (h != null)
                a[d] = h;
            if (f === Rb || f === og) {
                c = null;
                b = Rb;
                g += "&" + w(a)
            } else {
                c = w(a);
                b = Xd
            }
            g += "&method=" + f;
            return {url: g,method: b,body: c}
        }

        a.download = function(a) {
            Ze(a);
            y("WL.download");
            return (new Pd(a)).execute()
        };

        function rd(b, f) {
            var c = f || {}, g = Wd();
            if (!ke(b))
                b = g + (b.charAt(0) === "/" ? b.substring(1) : b);
            var e = rc();
            if (e)
                c[d] = e;
            c[O] = a[O];
            return gb(b, c)
        }

        var Yc = "notStarted", sd = "ready", fc = "downloadCompleted", Tc = "downloadFailed", dd = "canceled", Ye = "completed";
        function Pd(a) {
            this._properties = a;
            this._status = Yc
        }

        Pd.prototype = {execute: function() {
            this._promise = new m("WL.download", this, null);
            this._process();
            return this._promise
        },cancel: function() {
            this._status = dd;
            if (this._cancel)
                try {
                    this._cancel()
                } catch (a) {
                }
            else {
                this._result = k(lb, Nb.replace("METHOD", "WL.download"));
                this._process()
            }
        },downloadComplete: function(b, c) {
            var a = this;
            a._result = c;
            a._status = b ? fc : Tc;
            a._process()
        },downloadProgress: function(a) {
            this._promise[I](a)
        },_process: function() {
            switch (this._status) {
                case Yc:
                    this._start();
                    break;
                case sd:
                    this._download();
                    break;
                case fc:
                case Tc:
                case dd:
                    this._complete()
            }
        },_start: function() {
            var b = this;
            a.getLoginStatus({internal: true,callback: function() {
                b._status = sd;
                b._process()
            }})
        },_download: function() {
            var a = this;
            Ig(a)
        },_complete: function() {
            var a = this, c = a._result, d = a._status === fc ? o : p;
            a._status = Ye;
            var b = a._properties[r];
            if (b)
                b(c);
            a._promise[d](c)
        }};
        a.login = function(b, e, d) {
            y("WL.login");
            dg(b);
            if (!ng(e))
                return Z("WL.login", false, null, k(K, Je));
            var c = a._session.tryGetResponse(b.normalizedScope);
            if (c != null)
                return Z("WL.login", true, b.callback, c);
            a._pendingLogin = jg(b, Ff, d);
            return a._pendingLogin.execute()
        };
        function Ff(c, b) {
            a._pendingLogin = null;
            var d = b[l];
            if (d)
                yb("WL.login: " + b[E]);
            else
                C(c.callback, b, true)
        }
        function Dd(b) {
            var a = b || "";
            if (a instanceof Array)
                a = a.join(" ");
            return U(a)
        }
        a.getSession = function() {
            y("WL.getSession");
            return a._session.getStatus()[we]
        };
        a.getLoginStatus = function(b, f) {
            y("WL.getLoginStatus");
            b = b || {};
            if (!f) {
                var d = a._session.tryGetResponse();
                if (d)
                    return Z("WL.getLoginStatus", true, b.callback, d)
            }
            i("wl_app:getLoginStatus");
            var e = a._statusRequests, c = null;
            if (!a._pendingStatusRequest) {
                c = jf(b, gf);
                a._pendingStatusRequest = c
            }
            e.push(b);
            if (c != null)
                c.execute();
            return a._pendingStatusRequest._promise
        };
        function gf(h, b) {
            var f = a._statusRequests;
            a._pendingStatusRequest = null;
            i("wl_app:onGetLoginStatusCompleted");
            var d = b[l], e = false;
            while (f.length > 0) {
                var c = f.shift(), g = M(b);
                if (!d || c.internal)
                    C(c.callback, g, true);
                if (!c.internal)
                    e = true
            }
            if (d)
                if (e && d !== Ub)
                    yb("WL.getLoginStatus: " + b[E]);
                else
                    i("wl_app-onGetLoginStatusCompleted: " + b[E])
        }
        a.logout = function(f) {
            var b = "WL.logout";
            y(b);
            var d = new m(b, null, null), c = function(c) {
                tb(function() {
                    var e, g = o;
                    if (c) {
                        t(c.message);
                        g = p;
                        e = Ab(b, b, c)
                    } else
                        e = a._session.getNormalStatus();
                    C(f.callback, e, false);
                    d[g](e)
                })
            }, e = function() {
                var b = a._session;
                if (b.isSignedIn())
                    if (a.canLogout()) {
                        b.updateStatus(wb);
                        vg(c)
                    } else
                        c(new Error(Pe));
                else
                    c()
            };
            if (a._pendingStatusRequest != null)
                a.getLoginStatus({internal: true,callback: e}, false);
            else
                e();
            return d
        };
        a.upload = function(a) {
            var c = a[h];
            y(c);
            n(a, [{name: z,type: b,optional: false}, N], c);
            Me(a);
            zf(a);
            return (new Ec(a)).execute()
        };
        function zf(a) {
            var b = a[cg], c = a[Kb];
            if (b)
                a[Kb] = c || b.name
        }
        function Se(a, h, i) {
            var c = a.indexOf("?"), e = c !== -1, g = "";
            if (e) {
                g = a.substring(c + 1);
                a = a.substring(0, c)
            }
            var f = typeof h !== Vb, j = a.charAt(a.length - 1) === "/";
            if (f && !j)
                a += "/";
            var b = a, d = {};
            if (f)
                b += encodeURIComponent(h);
            if (i === "rename")
                d[L] = "choosenewname";
            else
                d[L] = i;
            if (e)
                b = Nd(b, g);
            return lc(b, d)
        }
        function bh(a) {
            return /^(file|\/file)/.test(a.toLowerCase())
        }
        function lc(b, a) {
            a = a || {};
            a[Xc] = "true";
            return rd(b, a)
        }
        function Me(a) {
            if (L in a) {
                var c = a[h], d = a[L], f = typeof d, i = f === qb, e = f === b;
                if (!(i || e))
                    throw P(L, c);
                if (e) {
                    var g = /^(true|false|rename)$/i.test(d);
                    if (!g)
                        throw Y(L, c)
                }
            } else
                a[L] = false
        }
        var gd = 0, ld = 1, kc = 2, ic = 3, zb = 4, nd = 5, qf = 6;
        function Ec(a) {
            this._props = a;
            this._status = gd
        }
        Ec.prototype = {execute: function() {
            var a = this;
            a._strategy = a._getStrategy(a._props);
            a._promise = new m(a._props[h], a, null);
            a._process();
            return a._promise
        },cancel: function() {
            var a = this;
            a._status = nd;
            if (a._cancel)
                try {
                    a._cancel()
                } catch (c) {
                }
            else {
                var b = Nb.replace(jh, a._props[h]);
                a._result = k(lb, b);
                a._process()
            }
        },uploadProgress: function(a) {
            this._promise[I](a)
        },uploadComplete: function(b, c) {
            var a = this;
            a._result = c;
            a._status = b ? ic : zb;
            a._process()
        },onErr: function(c) {
            var a = this._props[h] + ":" + c, b = k(K, a);
            this.uploadComplete(false, b)
        },onResp: function(a) {
            a = a ? U(a) : "";
            var b = a !== "" ? Dc(a) : null;
            b = b || {};
            this.uploadComplete(b.error == null, b)
        },setFileName: function(a) {
            this._props[Kb] = a
        },_process: function() {
            switch (this._status) {
                case gd:
                    this._start();
                    break;
                case ld:
                    this._getUploadPath();
                    break;
                case kc:
                    this._upload();
                    break;
                case ic:
                case zb:
                case nd:
                    this._complete()
            }
        },_start: function() {
            var a = this;
            Oc().getLoginStatus({internal: true,callback: function() {
                a._status = ld;
                a._process()
            }})
        },_getUploadPath: function() {
            var a = this, c = a._props, b = c[z];
            if (ke(b)) {
                a._uploadPath = lc(b);
                a._status = kc;
                a._process();
                return
            }
            Oc().api({path: b}).then(function(h) {
                var d = h.upload_location;
                if (d) {
                    var e = Dg(b);
                    d = Nd(d, e);
                    var f = c[Kb], g = c[L];
                    if (bh(b))
                        a._uploadPath = lc(d);
                    else
                        a._uploadPath = Se(d, f, g);
                    a._status = kc
                } else {
                    a._result = k(K, "WL.upload: Failed to get upload_location of the resource.");
                    a._status = zb
                }
                a._process()
            }, function(b) {
                a._result = b;
                a._status = zb;
                a._process()
            })
        },_upload: function() {
            this._strategy.upload(this._uploadPath)
        },_complete: function() {
            var a = this, c = a._result, d = a._status === ic ? o : p;
            a._status = qf;
            var b = a._props[r];
            if (b)
                b(c);
            a._promise[d](c)
        }};
        var Yf = new RegExp("^\\w+\\.\\w+:\\w+\\|\\w+:([\\d\\w]+\\!\\d+)$");
        function U(a) {
            return a.replace(/^\s+|\s+$/g, "")
        }
        function fd(a, b) {
            if (a && b)
                return a.toLowerCase() === b.toLowerCase();
            return a === b
        }
        function Ed(a) {
            return a == null || a === ""
        }

        function M(b, d) {
            var c = d || {};
            if (b != null)
                for (var a in b)
                    c[a] = b[a];
            return c
        }

        function Od(e, d, b) {
            var c = M(e, d);
            for (var a = 0; a < b.length; a++)
                delete c[b[a]];
            return c
        }
        function Sg(b, c) {
            var a;
            for (a = 0; a < b.length; a++)
                if (b[a] === c)
                    return true;
            return false
        }
        function ye(a) {
            return Array.prototype.slice.call(a)
        }
        function g(b, a) {
            return function() {
                if (typeof a === j)
                    return a.apply(b, arguments)
            }
        }
        function Be(a, e) {
            a = "[WL]" + a;
            var b = window.console;
            if (b && b.log)
                switch (e) {
                    case "warning":
                        b.warn(a);
                        break;
                    case "error":
                        b.error(a);
                        break;
                    default:
                        b.log(a)
                }
            var d = window.opera;
            if (d)
                d.postError(a);
            var c = window.debugService;
            if (c)
                c.trace(a)
        }
        function ke(a) {
            return a.indexOf("https://") === 0 || a.indexOf("http://") === 0
        }
        function i(b) {
            if (a._traceEnabled)
                Be(b)
        }
        function yb(b, d) {
            if (a._logEnabled || a._traceEnabled)
                Be(b, d);
            c.notify(ze, b)
        }
        if (window.WL && WL.Internal) {
            WL.Internal.trace = i;
            WL.Internal.log = yb
        }
        function t(a) {
            yb(a, "error")
        }
        function Hd(b, c) {
            var a = Gb("iframe");
            a.id = c;
            a.src = b;
            document.body.appendChild(a);
            return a
        }
        function Gb(b) {
            var a = document.createElement(b);
            a.style.position = "absolute";
            a.style.top = "-1000px";
            a.style.width = "300px";
            a.style.height = "300px";
            a.style.visibility = "hidden";
            return a
        }
        function pc() {
            var a = null;
            while (a == null) {
                a = "wl_" + Math.floor(Math.random() * 1024 * 1024);
                if (ob(a) != null)
                    a = null
            }
            return a
        }
        function ob(a) {
            return document.getElementById(a)
        }
        function Rg(a, b) {
            if (a)
                if (a.innerText)
                    a.innerText = b;
                else {
                    var c = document.createTextNode(b);
                    a.innerHTML = "";
                    a.appendChild(c)
                }
        }
        function Dg(b) {
            var a = b.indexOf("?");
            if (a === -1)
                return "";
            var c = b.indexOf("#", a + 1);
            if (c !== -1)
                return b.substring(a + 1, c);
            return b.substring(a + 1)
        }
        function Nd(a, b) {
            if (typeof b === Vb || b === null || b === "")
                return a;
            var c = a.indexOf("?");
            if (c === -1)
                return a + "?" + b;
            if (a.charAt(a.length - 1) !== "&")
                a += "&";
            return a + b
        }
        function Ce(a) {
            M(Qc(a), this)
        }
        Ce.prototype = {toString: function() {
            var a = this, b = (a.scheme != "" ? a.scheme + "//" : "") + a.host + (a.port != "" ? ":" + a.port : "") + a.path;
            return b
        },resolve: function() {
            var a = this;
            if (a.scheme == "") {
                var b = window.location.port, c = window.location.host;
                a.scheme = window.location.protocol;
                a.host = c.split(":")[0];
                a.port = b != null ? b : "";
                if (a.path.charAt(0) != "/")
                    a.path = fg(a.host, window.location.href, a.path)
            }
        }};
        function Qc(c) {
            var e = c.indexOf(bc) == 0 ? bc : c.indexOf(Nc) == 0 ? Nc : "", h = "", i = "", f;
            if (e != "")
                var b = c.substring(e.length + 2), a = b.indexOf("/"), g = a > 0 ? b.substring(0, a) : b, d = g.split(":"), h = d[0], i = d.length > 1 ? d[1] : "", f = a > 0 ? b.substring(a) : "";
            else
                f = c;
            return {scheme: e,host: h,port: i,path: f}
        }
        function Ug(a) {
            return Qc(a.toLowerCase()).host
        }
        function fg(e, b, h) {
            var d = function(a, c) {
                charIdx = b.indexOf(c);
                a = charIdx > 0 ? a.substring(0, charIdx) : a;
                return a
            };
            b = d(d(b, "?"), "#");
            var f = b.indexOf(e), a = b.substring(f + e.length), g = a.indexOf("/"), c = a.lastIndexOf("/");
            a = c >= 0 ? a.substring(g, c) : a;
            return a + "/" + h
        }
        function Xg(a) {
            var b = a.indexOf("?");
            if (b > 0)
                a = a.substring(0, b);
            b = a.indexOf("#");
            if (b > 0)
                a = a.substring(0, b);
            return a
        }
        function C(a, b, d, c) {
            if (a != null) {
                if (c)
                    b[V] = c;
                if (d)
                    a(b);
                else
                    tb(function() {
                        a(b)
                    })
            }
        }
        function Dc(a) {
            if (window.JSON)
                return JSON.parse(a);
            else
                return eval("(" + a + ")")
        }
        function Rd() {
            return Math.floor((new Date).getTime() / 1000)
        }
        function ih(b, c) {
            var d = b.length;
            for (var a = 0; a < d; a++)
                c(b[a])
        }
        function Gg(c, b) {
            var a = {};
            a[l] = c;
            a[E] = b;
            return a
        }
        function k(d, c) {
            var a = {}, b = {};
            a[Tb] = d, a[Mb] = c;
            b[ae] = a;
            return b
        }
        function Ab(a, b, c) {
            return k(K, Sf.replace("METHOD", a).replace("EVENT", b).replace("MESSAGE", c.message))
        }
        function Jf(b) {
            var a = b.split(".");
            return a[0] + "." + a[1]
        }
        function tb(a, b) {
            if (window.wlUnitTests)
                wlUnitTests.delayInvoke(a);
            else
                window.setTimeout(a, b || 1)
        }
        function Ng() {
            var b = Og(navigator.userAgent, document.documentMode), c = a[O];
            a._browser = b;
            a[O] = c.replace("DEVICE", b.device)
        }
        function Og(a, e) {
            a = a.toLowerCase();
            var c = "other", b = {"firefox": /firefox/.test(a),"firefox1.5": /firefox\/1\.5/.test(a),"firefox2": /firefox\/2/.test(a),"firefox3": /firefox\/3/.test(a),"firefox4": /firefox\/4/.test(a),"ie": (/msie/.test(a) || /trident/.test(a)) && !/opera/.test(a),"ie6": false,"ie7": false,"ie8": false,"ie9": false,"ie10": false,"ie11": false,"opera": /opera/.test(a),"webkit": /webkit/.test(a),"chrome": /chrome/.test(a),"mobile": /mobile/.test(a) || /phone/.test(a)};
            if (b["ie"]) {
                var d = 0;
                if (e)
                    d = e;
                else if (/msie 7/.test(a))
                    d = 7;
                d = Math.min(11, Math.max(d, 6));
                c = "ie" + d;
                b[c] = true
            } else if (b.firefox)
                c = "firefox";
            else if (b.chrome)
                c = "chrome";
            else if (b.webkit)
                c = "webkit";
            else if (b.opera)
                c = "opera";
            if (b.mobile)
                c += "mobile";
            b.device = c;
            return b
        }
        function Cb(e, c) {
            var f = c != null ? c : {};
            if (e != null) {
                var d = e.split("&");
                for (var b = 0; b < d.length; b++) {
                    var a = d[b].split("=");
                    if (a.length == 2)
                        f[decodeURIComponent(a[0])] = decodeURIComponent(a[1])
                }
            }
            return f
        }
        function w(b) {
            var a = "";
            if (b != null)
                for (var c in b) {
                    var d = a.length == 0 ? "" : "&", e = b[c];
                    a += d + encodeURIComponent(c) + "=" + encodeURIComponent(gg(e))
                }
            return a
        }
        function gg(a) {
            if (a instanceof Date) {
                var b = function(a, b) {
                    switch (b) {
                        case 2:
                            return a < 10 ? "0" + a : a;
                        case 3:
                            return (a < 10 ? "00" : a < 100 ? "0" : "") + a
                    }
                };
                return a.getUTCFullYear() + "-" + b(a.getUTCMonth() + 1, 2) + "-" + b(a.getUTCDate(), 2) + "T" + b(a.getUTCHours(), 2) + ":" + b(a.getUTCMinutes(), 2) + ":" + b(a.getUTCSeconds(), 2) + "." + b(a.getUTCMilliseconds(), 3) + "Z"
            }
            return "" + a
        }
        function wg(b) {
            var d = b.indexOf("?") + 1, c = b.indexOf("#") + 1, a = {};
            if (d > 0) {
                var e = c > d ? c - 1 : b.length;
                a = Cb(b.substring(d, e), a)
            }
            if (c > 0)
                a = Cb(b.substring(c), a);
            return a
        }
        function gb(a, b) {
            return a + (a.indexOf("?") < 0 ? "?" : "&") + w(b)
        }
        function Of(a) {
            switch (typeof a) {
                case qb:
                    return a;
                case ve:
                    return !!a;
                case b:
                    return a.toLowerCase() === "true";
                default:
                    return false
            }
        }
        var N = {name: r,type: j,optional: true}, bf = {name: r,type: j,optional: false};
        function Xb(a, c, d) {
            if (a instanceof Array)
                for (var b = 0; b < a.length; b++)
                    le(a[b], c[b], d);
            else
                le(a, c, d)
        }
        function le(c, a, b) {
            Td(c, a, b);
            if (a.type === "properties")
                n(c, a.properties, b)
        }
        function Td(f, e, c) {
            var d = e.name, a = typeof f, g = e.type;
            if (a === "undefined" || f == null)
                if (e.optional)
                    return;
                else
                    throw mc(d, c);
            switch (g) {
                case "string":
                    if (a !== b)
                        throw P(d, c);
                    if (!e.optional && U(f) === "")
                        throw mc(d, c);
                    break;
                case "properties":
                    if (a != vb)
                        throw P(d, c);
                    break;
                case "function":
                    if (a != j)
                        throw P(a, c);
                    break;
                case "dom":
                    if (a == b) {
                        if (ob(f) == null)
                            throw new Error(Cf.replace("METHOD", c).replace("PARAM", d))
                    } else if (a != vb)
                        throw P(d, c);
                    break;
                case "string_or_array":
                    if (a !== b && !(f instanceof Array))
                        throw P(a, c);
                    break;
                default:
                    if (a !== g)
                        throw P(d, c)
            }
            if (e.allowedValues != null)
                ag(f, e.allowedValues, e.caseSensitive, d, c)
        }
        function n(g, c, f) {
            var d = g || {};
            for (var b = 0; b < c.length; b++) {
                var a = c[b], e = d[a.name] || d[a.altName];
                Td(e, a, f)
            }
        }
        function ag(d, a, e, f, h) {
            var g = typeof a[0] === b;
            for (var c = 0; c < a.length; c++)
                if (g && !e) {
                    if (d.toLowerCase() === a[c].toLowerCase())
                        return
                } else if (d === a[c])
                    return;
            throw Y(f, h)
        }
        function P(a, b) {
            return new Error(Re.replace("METHOD", b).replace("PARAM", a))
        }
        function mc(a, b) {
            return new Error(mf.replace("METHOD", b).replace("PARAM", a))
        }
        function Y(c, d, a) {
            var b = jd.replace("METHOD", d).replace("PARAM", c);
            if (typeof a !== Vb)
                b += " " + a;
            return new Error(b)
        }
        function Id(b, d, c) {
            if (b)
                for (var a = 0; a < c && a < b.length; a++)
                    if (d === typeof b[a])
                        return b[a];
            return undefined
        }
        function ab(i, g) {
            var e = ye(i), a = null, b = null;
            for (var d = 0; d < e.length; d++) {
                var c = e[d], f = typeof c;
                if (f === vb && a === null)
                    a = M(c);
                else if (f === j && b === null)
                    b = c
            }
            a = a || {};
            if (b)
                a.callback = b;
            a[h] = g;
            return a
        }
        function Nf(e) {
            var a = ye(e), d = null, c = null;
            if (typeof a[0] === b) {
                d = a.shift();
                if (typeof a[0] === b)
                    c = a.shift()
            }
            normalizedArgs = ab(a);
            if (d !== null) {
                normalizedArgs[z] = d;
                if (c != null)
                    normalizedArgs[R] = c
            }
            return normalizedArgs
        }
        function J(a, b) {
            var c = Ab(a, a, b);
            t(b.message);
            return Z(a, false, null, c)
        }
        var m = function(b, c, a) {
            this._name = b;
            this._op = c;
            this._uplinkPromise = a;
            this._isCompleted = false;
            this._listeners = []
        };
        m.prototype = {then: function(d, e, c) {
            var b = new m(null, null, this), a = {};
            a[o] = d;
            a[p] = e;
            a[I] = c;
            a.chainedPromise = b;
            this._listeners.push(a);
            return b
        },cancel: function() {
            if (this._isCompleted)
                return;
            if (this._uplinkPromise && !this._uplinkPromise._isCompleted)
                this._uplinkPromise.cancel();
            else {
                var a = this._op ? this._op.cancel : null;
                if (typeof a === j)
                    this._op.cancel();
                else
                    this.onError(k(lb, Nb.replace("METHOD", this._getName())))
            }
        },_getName: function() {
            if (this._name)
                return this._name;
            if (this._op && typeof this._op._getName === j)
                return this._op._getName();
            if (this._uplinkPromise)
                return this._uplinkPromise._getName();
            return ""
        },_onEvent: function(b, a) {
            if (this._isCompleted)
                return;
            this._isCompleted = a !== I;
            this._notify(b, a)
        },_notify: function(b, a) {
            var c = this;
            ih(this._listeners, function(g) {
                var h = g[a], d = g.chainedPromise, f = a !== I;
                if (h)
                    try {
                        var e = h.apply(g, b);
                        if (f && e && e.then) {
                            d._op = e;
                            e.then(function(a) {
                                d[o](a)
                            }, function(a) {
                                d[p](a)
                            }, function(a) {
                                d[I](a)
                            })
                        }
                    } catch (i) {
                        if (f)
                            d.onError(Ab(c._getName(), a, i))
                    }
                else if (f)
                    d[a].apply(d, b)
            })
        }};
        m.prototype[o] = function() {
            this._onEvent(arguments, o)
        };
        m.prototype[p] = function() {
            this._onEvent(arguments, p)
        };
        m.prototype[I] = function() {
            this._onEvent(arguments, I)
        };
        function Z(e, d, b, f) {
            var a = new m(e, null, null), c = d ? o : p;
            if (typeof b === j)
                a.then(function(a) {
                    b(a)
                });
            tb(function() {
                a[c](f)
            });
            return a
        }
        var Sb = [d, Yb, f, eb, fb, s, l, E], Zd = "refresh_type", bg = "app", vc = "ms", wc = "response_method", od = "url", cd = "cookie", Jb = "onanalytics", Gd = "login", hd = "loginstatus", sf = "file_dialog", Ib = "auth.response", v = "mode", W = "open", bd = "save", jc = "read", hc = "readwrite", Wc = "resourceType";
        FILEDIALOG_PARAM_RESOURCETYPE_FILE = "file";
        FILEDIALOG_PARAM_RESOURCETYPE_FOLDER = "folder";
        FILEDIALOG_PARAM_SELECT = "select", (FILEDIALOG_PARAM_SELECT_SINGLE = "single", (FILEDIALOG_PARAM_SELECT_MULTI = "multi", (FILEDIALOG_PARAM_PERMISSION = "permission", (FILEDIALOG_PARAM_PERMISSION_ONETIME = "onetime", (FILEDIALOG_PARAM_LIGHTBOX = "lightbox", (FILEDIALOG_PARAM_LIGHTBOX_GREY = "grey", (FILEDIALOG_PARAM_LIGHTBOX_TRANSPARENT = "transparent", (FILEDIALOG_PARAM_LIGHTBOX_WHITE = "white", (FILEDIALOG_PARAM_LOADING_TIMEOUT = "loading_timeout", FILEDIALOG_PARAM_ONSELECTED = "onselected")))))))));
        var Mf = "auth", cf = "rps", We = "oauth", df = "v", Fe = "1", Ge = "2", wf = "domain", of = "livesdk", Tf = "mkt", Vc = "pickerscript", Ve = "onPickerComplete", Te = "updateToken", Lb = "WL.fileDialog", ah = 27, Ob = "skydrivepicker", af = "Loading SkyDrive picker is timed out.";
        WL.init = function(d) {
            try {
                var c = M(d);
                Xb(c, {name: "properties",type: "properties",optional: false,properties: [{name: q,altName: gh,type: b,optional: false}, {name: f,type: zc,optional: true}, {name: x,altName: Tg,type: b,optional: true}, {name: kb,type: b,allowedValues: [pg, hb],optional: true}, {name: Zd,type: b,allowedValues: [bg, vc],optional: true}, {name: Md,type: qb,optional: true}, {name: e,type: qb,optional: true}]}, "WL.init");
                if (!c[x] && c[kb] === hh)
                    throw new Error(Qe.replace("METHOD", "WL.init"));
                if (c[e] == null)
                    c[e] = true;
                return a.appInit(c)
            } catch (g) {
                return J("WL.init", g)
            }
        };
        WL.login = function() {
            try {
                var c = ab(arguments);
                n(c, [{name: f,type: zc,optional: true}, {name: x,type: b,optional: true}, {name: V,type: b,optional: true}, N], "WL.login");
                return a.login(c)
            } catch (g) {
                return J("WL.login", g)
            }
        };
        WL.download = function() {
            try {
                var b = "WL.download", c = ab(arguments, b);
                return a.download(c)
            } catch (f) {
                return J(b, f)
            }
        };
        WL.upload = function() {
            try {
                var b = "WL.upload", c = ab(arguments, b);
                return a.upload(c)
            } catch (f) {
                return J(b, f)
            }
        };
        WL.ui = function() {
            try {
                var c = ab(arguments);
                n(c, [{name: Vg,type: b,allowedValues: [Pc, Ob],optional: false}, N], "WL.ui");
                a.ui(c)
            } catch (f) {
                td(c, f)
            }
        };
        WL.fileDialog = function() {
            try {
                var b = Lb, c = ab(arguments, b);
                vd(c, b);
                return a.fileDialog(c)
            } catch (f) {
                return J(b, f)
            }
        };
        function vd(d, c) {
            n(d, [{name: v,type: b,allowedValues: [W, bd, jc, hc],optional: false}, {name: Wc,type: b,allowedValues: [FILEDIALOG_PARAM_RESOURCETYPE_FILE, FILEDIALOG_PARAM_RESOURCETYPE_FOLDER],optional: true}, {name: FILEDIALOG_PARAM_SELECT,type: b,allowedValues: [FILEDIALOG_PARAM_SELECT_SINGLE, FILEDIALOG_PARAM_SELECT_MULTI],optional: true}, {name: FILEDIALOG_PARAM_LIGHTBOX,type: b,allowedValues: [FILEDIALOG_PARAM_LIGHTBOX_GREY, FILEDIALOG_PARAM_LIGHTBOX_TRANSPARENT, FILEDIALOG_PARAM_LIGHTBOX_WHITE],optional: true}, {name: FILEDIALOG_PARAM_LOADING_TIMEOUT,type: ve,optional: true}, {name: FILEDIALOG_PARAM_PERMISSION,type: b,allowedValues: [FILEDIALOG_PARAM_PERMISSION_ONETIME],optional: true}, N], c);
            if (c !== Lb)
                n(d, [{name: D,allowedValues: [Fb, Eb],type: b,optional: true}, {name: FILEDIALOG_PARAM_ONSELECTED,type: j,optional: false}, {name: mb,type: j,optional: true}], c);
            if (!Fc.isSupported() || !window.JSON || a._browser.mobile)
                throw new Error(id)
        }
        function Xf() {
            a._urlParams = wg(window.location.href);
            a._pageState = Cb(a._urlParams[V])
        }
        function Ld() {
            var b = new sb(ue);
            b.load();
            var c = a._urlParams, f = a._pageState, i = true, m = f[s];
            if (m)
                if (m != b.get(s))
                    b.set(s, f[s]);
                else
                    i = false;
            var j = c[d] != null, n = b.get(d) != null || j, o = n ? F : wb, p = Rd();
            if (f[wc] === od) {
                for (var h = 0; h < Sb.length; h++) {
                    var g = Sb[h];
                    if (c[g])
                        b.set(g, c[g])
                }
                if (j) {
                    b.set(fb, p + parseInt(c[eb]));
                    b.remove(l);
                    b.remove(E)
                } else if (!n)
                    if (c[l] === Hb)
                        o = Fg
            } else if (i) {
                var k = tf(b);
                if (k) {
                    b.set(l, kg);
                    b.set(E, k)
                }
            } else
                return;
            b.set(e, o);
            b.save()
        }
        function Cg() {
            var b = a._pageState, e = b[Zb];
            if (e === yd) {
                var h = b[de], g = a._urlParams[Bg];
                Vf(h, g);
                return
            }
            var c = b[dc], f = b[Pb] === "true";
            a._logEnabled = true;
            a._traceEnabled = b[ec] || a._urlParams[ec];
            a._secureCookie = f;
            Bf();
            if (c === ne || c === Ic) {
                Ld();
                if (c === Ic && a._browser.ie)
                    document.location = b[x];
                else
                    window.close()
            } else if (c === ac)
                Ld();
            else {
                ib(Lg);
                var d = window.wlAsyncInit;
                if (d && typeof d === j)
                    d.call()
            }
        }
        function Wf(a, b) {
            if (!a)
                a = Xg(window.location.href);
            return Ie(a, window.location.hostname, b)
        }
        function Ie(f, e, d) {
            var b = new Ce(f);
            b.resolve();
            var e = e.split(":")[0].toLowerCase(), c = b.host.toLowerCase();
            a._domain = a._domain || c;
            if (a._isHttps && b.scheme == Nc)
                throw new Error(lg.replace("METHOD", d));
            return b.toString()
        }
        function tf(a) {
            var i = a.get(d) != null, g = a.get(l) != null, h = a.get(f) != null, c = a.get(eb) != null, e = a.get(q) != null, b = null;
            if (!(i && h && c) && !g) {
                t(ed);
                b = ed
            }
            if (!e) {
                t(Zc);
                b = Zc
            }
            return b
        }
        function Pg() {
            Cd = a._browser.ie ? 2000 : 4000;
            Xf();
            Cg()
        }
        function Vf(c, b) {
            var a = new sb(je);
            a.load();
            a.set(c, b);
            a.save()
        }
        function rf(b) {
            a._authScope = Dd(b[f]);
            a._secureCookie = Of(b[Pb]);
            a._redirect_uri = Wf(b[x], "WL.init");
            a._response_type = (b[kb] || hb).toLowerCase();
            a._appId = b[q];
            a._refreshType = (b[Zd] || vc).toLowerCase();
            var i = new re(b[q], ue);
            a._session = i;
            var d = i.getNormalStatus(), j = d[e], g, h = "WL.init";
            if (j == F) {
                c.notify(X, d);
                c.notify(nc, d);
                c.notify(Qb, d);
                g = Z(h, true, b.callback, d)
            } else if (b[e]) {
                g = new m(h, null, null);
                a.getLoginStatus({internal: true,callback: function(a) {
                    var b = !!a.error ? p : o;
                    g[b](a)
                }}, true)
            }
            return g
        }
        function ng() {
            var b = a._pendingLogin;
            if (b != null) {
                b.cancel();
                a._pendingLogin = null
            }
            return true
        }
        function dg(c) {
            var b = Dd(c[f]);
            if (b === "")
                b = a._authScope;
            if (!b || b === "")
                throw mc(f, "WL.login");
            c.normalizedScope = b
        }
        function jg(c, b, a) {
            return new Mc(Gd, c, b, a)
        }
        function jf(b, a) {
            return new Mc(hd, b, a)
        }
        a.ensurePermission = function(d, e, f, b) {
            var c = k(Hb, kf.replace("METHOD", f));
            a.login({scope: d}).then(function(f) {
                if (f.session[eb] < e)
                    a.getLoginStatus({internal: true}, true).then(function() {
                        a.login({scope: d}).then(function(a) {
                            b(a)
                        }, function() {
                            b(c)
                        })
                    }, function() {
                        b(c)
                    });
                else
                    b(f)
            }, function() {
                b(c)
            })
        };
        a.canLogout = function() {
            return true
        };
        function vg(d) {
            Ud();
            var b = Gb("iframe"), c = Qd(), e = "/oauth20_logout.srf?ts=";
            b.src = "//" + c + e + (new Date).getTime();
            document.body.appendChild(b);
            a.logoutFrame = b;
            window.setTimeout(function() {
                Ud();
                d()
            }, 30000)
        }
        function Ud() {
            if (a.logoutFrame != null) {
                document.body.removeChild(a.logoutFrame);
                a.logoutFrame = null
            }
        }
        function td(c, b) {
            t(b.message);
            var a = c[mb];
            if (a)
                tb(function() {
                    error = Ab("WL.ui", "WL.ui", b), a(error)
                })
        }
        function fe() {
            return a[G]
        }
        function oe() {
            return fe() + "images"
        }
        var Jc = function(c) {
            var a = this;
            a._properties = c;
            var b = g(a, a.init);
            ib(b)
        };
        Jc.prototype = {init: function() {
            var d = this, e = d._properties;
            if (d._inited === true)
                return;
            d._inited = true;
            try {
                d.validate();
                var f = e[cb], h = e[Lc], m = e[r], j = e[Db], i = e[Bb];
                Xe(e);
                f = typeof f === b ? ob(e[cb]) : f;
                d._element = f;
                h = h != null ? h : qc;
                if (h == qc) {
                    j = u.signIn;
                    i = u.signOut
                } else if (h == Bd) {
                    j = u.login;
                    i = u.logout
                } else if (h == ud) {
                    j = u.connect;
                    i = u.signOut
                }
                d[Db] = j;
                d[Bb] = i;
                qe(f, Af(e));
                var k = a._session.isSignedIn(), l = k ? i : j;
                d.updateUI(l, k);
                Ne(d, f.childNodes[0]);
                c.subscribe(Qb, g(d, d.onLoggedIn));
                c.subscribe(Ac, g(d, d.onLoggedOut));
                a.getLoginStatus({internal: true});
                delete e[r];
                C(m, e, false)
            } catch (n) {
                td(e, n)
            }
        },validate: function() {
            var a = this._properties;
            n(a, [{name: cb,type: Rc,optional: false}, {name: Lc,allowedValues: [qc, Bd, ud, xd],type: b,optional: true}, {name: f,type: zc,optional: true}, {name: V,type: b,optional: true}, {name: Fd,type: j,optional: true}, {name: Ad,type: j,optional: true}, {name: mb,type: j,optional: true}], "WL.ui-signin");
            De(a);
            var c = a[Lc];
            if (c == xd)
                n(a, [{name: Db,type: b,optional: false}, {name: Bb,type: b,optional: false}], "WL.ui-signin")
        },fireEvent: function(b, c) {
            var a = this._properties[b];
            if (a)
                a(c)
        },onClick: function() {
            var b = this;
            if (b._element.childNodes.length == 0) {
                Uc(b);
                return false
            }
            if (a._session.isSignedIn()) {
                if (a.canLogout())
                    a.logout({})
            } else
                a.login(b._properties, true).then(function() {
                }, function(a) {
                    b.fireEvent(mb, a)
                });
            return false
        },onLoggedIn: function(a) {
            this.updateUI(this[Bb], true);
            this.fireEvent(Fd, a)
        },onLoggedOut: function(a) {
            this.updateUI(this[Db], false);
            this.fireEvent(Ad, a)
        }};
        function Xe(b) {
            if (a._authScope && a._authScope !== "")
                b[f] = a._authScope;
            if (!b[f])
                b[f] = pe
        }
        function Ke(d, a, c) {
            a._handlers = a._handlers || {};
            var b = g(a, c);
            a._handlers[d] = b;
            return b
        }
        function Ue(b, a) {
            return a._handlers[b]
        }
        a.ui = function(a) {
            y("WL.ui");
            switch (a.name) {
                case Pc:
                    new Jc(a);
                    break;
                case Ob:
                    new wd(a)
            }
        };
        function De(a) {
            n(a, [{name: D,allowedValues: [Fb, Eb],type: b,optional: true}, {name: Wb,allowedValues: [qg, Eg, yg, Cc, zd, Kc],type: b,optional: true}], "WL.ui-signin");
            a[D] = a[D] || Fb;
            a[Wb] = a[Wb] || Cc
        }
        function Af(f) {
            var d = f[Wb], e = f[D], g = a._locale, b = g.indexOf("ar") > -1 || g.indexOf("he") > -1 ? "RTL" : "LTR", h = "cursor:pointer;background-color:transparent;border:solid 0px;display:inline-block;overflow:hidden;white-space:nowrap;padding:0px;width:auto;", c = "margin:0px;padding:0px;border-width:0px;vertical-align:middle;background-attachment:scroll;display:inline-block;white-space:nowrap;", k = uc(d, b, e, "left") + c, i = uc(d, b, e, "center") + c, j = uc(d, b, e, "right") + c;
            return '<button style="' + h + '"><span style="' + k + '"></span><span style="' + i + '"><span style="' + ff(b) + '"></span></span><span style="' + j + '"></span></button>'
        }
        function ff(g) {
            var b = a._browser, j = b.ie6 || b.ie7, k = b.ie8 || b.ie9, f = b.chrome || b.safari ? "padding:2px 3px;margin:0px;" : "padding:1px 3px;margin:0px;", h = "font-family: Segoe UI, Verdana, Tahoma, Helvetica, Arial, sans-serif;", e = "direction:" + g.toLowerCase() + ";", d = "text-decoration:none;color:#3975a0;display:inline-block;", c = "150";
            switch (a._locale) {
                case "ar-ploc-sa":
                    if (!j)
                        c = "170";
                    break;
                case "te":
                case "ja-ploc-jp":
                    if (b.ie)
                        c = "190"
            }
            var i = "height:18px;font-size:9pt;font-weight:bold;line-height:" + c + "%;";
            return f + e + d + h + i
        }
        function uc(a, b, i, g) {
            a = a == Cc ? zd : a;
            var h = a + "_" + b + "_" + i, e, d, c, f = "background: url({imgpath}/signincontrol/{image}.png) scroll {repeat} 0px {vpos}; height: 22px; width: {width};";
            switch (g) {
                case "left":
                    e = a === Kc || b === "RTL" ? "3px" : "25px";
                    d = b === "LTR" ? "0px" : "-44px";
                    c = "no-repeat";
                    break;
                case "center":
                    e = "auto";
                    d = "-22px";
                    c = "repeat-x";
                    break;
                case "right":
                    e = a === Kc || b === "LTR" ? "3px" : "25px";
                    d = b === "LTR" ? "-44px" : "0px";
                    c = "no-repeat"
            }
            return f.replace("{imgpath}", oe()).replace("{image}", h).replace("{vpos}", d).replace("{width}", e).replace("{repeat}", c)
        }
        Jc.prototype.updateUI = function(d) {
            if (this._element.childNodes.length == 0) {
                Uc(this);
                return
            }
            if (d != this._text) {
                var c = a._browser, b = this._element.childNodes[0], e = b.childNodes[1];
                Rg(e.childNodes[0], d);
                this._text = d;
                if (c.ie6 || c.ie7) {
                    e.style.width = "auto";
                    b.style.width = "auto";
                    var h = b.childNodes[0].clientWidth, f = b.childNodes[1].clientWidth, g = b.childNodes[2].clientWidth, i = h + f + g;
                    b.style.width = i + "px";
                    if (c.ie6) {
                        b.childNodes[0].style.width = h + "px";
                        b.childNodes[1].style.width = f + "px";
                        b.childNodes[2].style.width = g + "px"
                    }
                }
            }
        };
        function Ne(a, b) {
            a._button = b;
            A(b, "click", Ke("click", a, a.onClick))
        }
        function Uc(a) {
            var b = a._button;
            if (b) {
                B(b, "click", Ue("click", a));
                delete a._button
            }
        }
        var wd = function(b) {
            vd(b);
            var a = this;
            a._props = b;
            ib(g(a, a.init))
        };
        wd.prototype = {init: function() {
            var a = this;
            if (a._inited === true)
                return;
            a._inited = true;
            try {
                var c = a._props, d = c[cb], e = c[r];
                c[h] = "WL.ui-" + Ob;
                a.validate();
                c[D] = c[D] || Eb;
                d = typeof d === b ? ob(c[cb]) : d;
                a._element = d;
                var f = Oe(c);
                qe(d, f);
                hf(d, g(a, a.onClick), g(a, a.onRender));
                a.onRender(false, false);
                C(e, c, false)
            } catch (i) {
                t(i.message)
            }
        },validate: function() {
            var a = this._props;
            n(a, [{name: cb,type: Rc,optional: false}], a[h])
        },onClick: function() {
            try {
                return a.fileDialog(this._props)
            } catch (b) {
                t(b.message)
            }
            return false
        },onRender: function() {
            var k = this._props, f = k[D] === Eb, h = a._locale, e = h.indexOf("ar") > -1 || h.indexOf("he") > -1, j = e ? "RTL" : "LTR", b = this._element.childNodes[0], i = b.childNodes[0], c = b.childNodes[1], d = "#094AB2", g = "#ffffff";
            b.style.direction = j;
            b.style.backgroundColor = f ? g : d;
            b.style.border = "solid 1px";
            b.style.borderColor = d;
            b.style.height = "20px";
            b.style.paddingLeft = "4px";
            b.style.paddingRight = "4px";
            b.style.textAlign = "center";
            b.style.cursor = "pointer";
            i.style.verticalAlign = "middle";
            i.style.height = "16px";
            c.style.fontFamily = "'Segoe UI', 'Segoe UI Web Regular', 'Helvetica Neue', 'BBAlpha Sans', 'S60 Sans', Arial, 'sans-serif'";
            c.style.fontSize = "12px";
            c.style.fontWeight = "bold";
            c.style.color = f ? d : g;
            c.style.textAlign = "center";
            c.style.verticalAlign = "middle";
            c.style.marginLeft = e ? "0px" : "2px";
            c.style.marginRight = e ? "2px" : "0px"
        }};
        function Oe(b) {
            var k = b[v], j = b[D], a = k === W, e = a ? u.skyDriveOpenPickerButtonText : u.skyDriveSavePickerButtonText, c = a ? u.skyDriveOpenPickerButtonTooltip : u.skyDriveSavePickerButtonTooltip, f = a ? "skydriveopenpickerbutton" : "skydrivesavepickerbutton", i = j === Fb ? "SkyDriveIcon_white.png" : "SkyDriveIcon_blue.png", h = "<img alt='' src='" + oe() + "/SkyDrivePicker/" + i + "'>", g = "<span>" + e + "</span>", d = "<button id='" + f + "' title='" + c + "'>" + h + g + "</button>";
            return d
        }
        function hf(c, l, k) {
            var a = c.childNodes[0];
            if (l) {
                var f = function(b) {
                    if (c.childNodes.length == 0) {
                        B(a, "click", f);
                        return
                    }
                    l(b)
                };
                A(a, "click", f)
            }
            if (k) {
                var d = false, e = false, g = function(a) {
                    e = true;
                    b(a)
                }, h = function(a) {
                    e = false;
                    b(a)
                }, i = function(a) {
                    d = true;
                    b(a)
                }, j = function(a) {
                    d = false;
                    b(a)
                }, b = function() {
                    if (c.childNodes.length == 0) {
                        B(a, "mouseenter", g);
                        B(a, "mouseleave", h);
                        B(document, "mousedown", i);
                        B(document, "mouseup", j);
                        return
                    }
                    k(d, e)
                };
                A(a, "mouseenter", g);
                A(a, "mouseleave", h);
                A(document, "mousedown", i);
                A(document, "mouseup", j)
            }
        }
        function dh(c) {
            var a = document.cookie;
            c += "=";
            var b = a.indexOf(c);
            if (b >= 0) {
                b += c.length;
                var d = a.indexOf(";", b);
                if (d < 0)
                    d = a.length;
                else {
                    postCookie = a.substring(d);
                    if (postCookie.indexOf(c) >= 0)
                        throw new Error(Le)
                }
                var e = a.substring(b, d);
                return e
            }
            return ""
        }
        function Ae(g, d, f, e) {
            d = d ? d : "";
            var c = g + "=" + d + "; path=/";
            if (f && f != "localhost")
                c += "; domain=" + encodeURIComponent(f);
            if (e != null) {
                var b = new Date(0);
                if (e > 0) {
                    b = new Date;
                    b.setTime(b.getTime() + e * 1000)
                }
                c += ";expires=" + b.toUTCString()
            }
            if (a._isHttps && a._secureCookie)
                c += ";secure";
            document.cookie = c
        }
        var sb = function(a, b) {
            this._cookieName = a;
            this._states = b || {};
            this._listeners = [];
            this._cookieWatcher = null
        };
        sb.prototype = {getStates: function() {
            return this._states
        },get: function(a) {
            return this._states[a]
        },set: function(b, a) {
            this._states[b] = a
        },remove: function(a) {
            if (this._states[a])
                delete this._states[a]
        },load: function() {
            try {
                var a = dh(this._cookieName);
                if (this._rawValue != a) {
                    i("Cookie changed: " + this._cookieName + "=" + a);
                    this._rawValue = a;
                    this._states = Cb(a);
                    for (var b = 0; b < this._listeners.length; b++)
                        this._listeners[b]()
                }
            } catch (c) {
                t(c.message);
                this.stopMonitor()
            }
        },flush: function(a) {
            this._states = a;
            this.save()
        },populate: function(a) {
            M(a, this._states);
            this.save()
        },save: function() {
            Ae(this._cookieName, w(this._states), ce(), null)
        },clear: function() {
            this._states = {};
            Ae(this._cookieName, "", ce(), 0)
        },addCookieChanged: function(a) {
            this._listeners.push(a);
            this.startMonitor()
        },startMonitor: function() {
            this._refreshInterval = 300;
            if (!this._cookieWatcher) {
                i("Started monitoring cookie: " + this._cookieName);
                this._cookieWatcher = window.setInterval(g(this, this.load), this._refreshInterval)
            }
        },stopMonitor: function() {
            if (this._cookieWatcher) {
                i("Stopped monitoring cookie: " + this._cookieName);
                window.clearInterval(this._cookieWatcher);
                this._cookieWatcher = null
            }
        },isBeingMonitored: function() {
            return this._cookieWatcher !== null
        }};
        function ce() {
            var b = a._domain || window.location.hostname.split(":")[0];
            return b
        }
        var Mc = function(d, b, c) {
            var a = this;
            a._method = d;
            a._completed = false;
            a._requestFired = false;
            a._properties = b;
            a._callback = c;
            a._authMonitor = g(a, a._onAuthChanged);
            a.execute = a._method == Gd ? a._login : a._getLoginStatus
        };
        Mc.prototype = {cancel: function() {
            this._onComplete({error: lb,error_description: vf})
        },_login: function() {
            var b = this;
            b._requestTs = (new Date).getTime();
            var e = a._browser.mobile, d = e && a._browser.ie, h = e ? Ic : ne, f = me(h, b._properties.normalizedScope, xc(b._properties), b._requestTs, d, b._properties.state);
            if (d)
                document.location = f;
            else {
                b._popup = eh(f);
                i("AuthRequest-login: popup is opened. " + b._popup);
                b._popupWatcher = window.setInterval(g(b, b._checkPopup), 3000);
                c.subscribe(Ib, b._authMonitor)
            }
            b._promise = new m("WL.login", null, null);
            return b._promise
        },_getLoginStatus: function() {
            eg(g(this, this._fireStatusRequest));
            this._timeout = window.setTimeout(g(this, this._onTimedOut), yf);
            this._promise = new m("WL.getLoginStatus", null, null);
            return this._promise
        },_fireStatusRequest: function() {
            var b = this;
            if (!b._requestFired) {
                b._requestTs = (new Date).getTime();
                var d = a._refreshType === vc ? me(ac, a._authScope, xc(), b._requestTs, false) : ig(xc(), a._authScope, b._requestTs);
                b._statusFrame = Hd(d);
                c.subscribe(Ib, b._authMonitor);
                b._requestFired = true
            }
        },_onAuthChanged: function() {
            var b = a._session.tryGetResponse(this._properties.normalizedScope, this._requestTs, this._properties.external_consent);
            if (b != null)
                this._onComplete(this._normalizeResp(b))
        },_normalizeResp: function(b) {
            if (this._method === hd && b.error === Hb)
                return a._session.getNormalStatus();
            return b
        },_onTimedOut: function() {
            this._onComplete({error: Ub,error_description: nf})
        },_checkPopup: function() {
            try {
                if (this._popup === null)
                    this._onComplete({error: Hb,error_description: Ef});
                else if (this._popup.closed)
                    this._popup = null
            } catch (a) {
                i("AuthRequest-checkPopup-error: " + a)
            }
        },_onComplete: function(a) {
            if (!this._completed) {
                this._completed = true;
                this._dispose();
                this._callback(this._properties, a);
                if (a[l])
                    this._promise[p](a);
                else
                    this._promise[o](a)
            }
        },_dispose: function() {
            i("AuthRequest: dispose " + this._method);
            if (this._timeout) {
                window.clearTimeout(this._timeout);
                this._timeout = null
            }
            if (this._statusFrame != null) {
                document.body.removeChild(this._statusFrame);
                this._statusFrame = null
            }
            if (this._popupWatcher) {
                window.clearInterval(this._popupWatcher);
                this._popupWatcher = null
            }
            c.unsubscribe(Ib, this._authMonitor)
        }};
        function eh(p) {
            var c = 525, b = 525, e, d;
            if (a._browser.ie) {
                var k = window.screenLeft, l = window.screenTop, f = document.documentElement, i = 30;
                d = k + (f.clientWidth - c) / 2;
                e = l + (f.clientHeight - b) / 2 - i
            } else {
                var n = window.screenX, o = window.screenY, j = window.outerWidth, h = window.outerHeight;
                d = n + (j - c) / 2;
                e = o + (h - b) / 2
            }
            var m = ["width=" + c, "height=" + b, "top=" + e, "left=" + d, "status=no", "resizable=yes", "toolbar=no", "menubar=no", "scrollbars=yes"], g = window.open(p, "oauth", m.join(","));
            g.focus();
            return g
        }
        function ig(d, i, g) {
            var b = {};
            b[Zb] = Kd;
            b[s] = g;
            b[Pb] = a._secureCookie;
            b[dc] = ac;
            b[wc] = cd;
            if (a.trace)
                b[ec] = true;
            var j = w(b), c = {};
            c[q] = a._session.get(q);
            c[kb] = hb;
            c[f] = i;
            c[V] = j;
            var h = d.indexOf("?") > 0, e = h ? "&" : "?", k = d + e + w(c);
            return k
        }
        function me(d, m, i, k, l, g) {
            var b = {};
            b[Zb] = Kd;
            b[dc] = d;
            b[s] = k;
            if (l)
                b[x] = window.location.href;
            if (a.trace)
                b[ec] = true;
            if (g)
                b[Yg] = g;
            var e = d === ac ? hb : a._response_type, h = e === hb ? od : cd;
            b[wc] = h;
            b[Pb] = a._secureCookie;
            var n = w(b), c = {};
            c[q] = a._session.get(q);
            c[dc] = d;
            c[ch] = a._locale;
            c[x] = i;
            c[kb] = e;
            c[f] = m;
            c[V] = n;
            var j = Qd(), o = "https://" + j + "/oauth20_authorize.srf?" + w(c);
            return o
        }
        function xc(c) {
            var b = c != null ? c[x] : null;
            return b != null && b != "" ? b : a._redirect_uri
        }
        var re = function(b, a) {
            this._state = {};
            this._state[q] = b;
            this._state[e] = T;
            this._cookieName = a;
            this.init()
        };
        re.prototype = {get: function(a) {
            return this._state[a]
        },save: function() {
            if (this._stateDirty) {
                this._cookie.flush(this._state);
                this._stateDirty = false
            }
        },init: function() {
            var a = new sb(this._cookieName);
            a.load();
            this._cookie = a;
            if (a.get(q) != this._state[q]) {
                a.clear();
                a.flush(this._state)
            } else
                this._state = a.getStates();
            var b = this._state[e];
            this._statusChecked = b !== wb && b !== T;
            this._cookie.addCookieChanged(g(this, this.onCookieChanged))
        },refresh: function() {
            a.getLoginStatus({internal: true}, true);
            this._refresh = undefined
        },scheduleRefresh: function() {
            this.cancelRefresh();
            var a = (this.tokenExpiresIn() - 600) * 1000;
            if (a > 0)
                this._refresh = window.setTimeout(g(this, this.refresh), a)
        },cancelRefresh: function() {
            if (this._refresh) {
                window.clearTimeout(this._refresh);
                this._refresh = undefined
            }
        },updateStatus: function(a) {
            var b = this._state[e], f = this._state[d];
            if (b != a) {
                this._state[e] = a;
                this._stateDirty = true;
                this.onStatusChanged(b, a);
                this.save();
                if (f != this._state[d])
                    c.notify(X, this.getNormalStatus())
            }
        },onStatusChanged: function(b, a) {
            i("AuthSession: Auth status changed: " + b + "=>" + a);
            if (b != a) {
                var g = b == F, d = a == F;
                if (!d) {
                    for (var e = 0; e < Sb.length; e++) {
                        var f = Sb[e];
                        if (this._state[f])
                            delete this._state[f]
                    }
                    this._stateDirty = true;
                    this.save()
                }
                if (oc(b) != oc(a))
                    c.notify(nc, this.getNormalStatus());
                if (d != g)
                    if (d)
                        c.notify(Qb, this.getNormalStatus());
                    else
                        c.notify(Ac, this.getNormalStatus())
            }
        },isSignedIn: function() {
            return this._state[e] === F
        },getNormalStatus: function() {
            var a = this.getStatus();
            a[e] = oc(a[e]);
            return a
        },tokenExpiresIn: function() {
            var a = this._state, c = a[e], b = parseInt(a[fb]);
            if (c === F)
                return b - Rd();
            return -1
        },onCookieChanged: function() {
            var b = this._state, a = this._cookie.getStates();
            this._state = a;
            i("AuthSession: cookie changed. Has token: " + (a[d] != null));
            this._statusChecked = a[e] !== T;
            if (b[d] != a[d] || b[l] != a[l] || b[s] != a[s]) {
                c.notify(Ib);
                delete a[l];
                delete a[E];
                this._stateDirty = true
            }
            if (b[e] != a[e])
                this.onStatusChanged(b[e], a[e]);
            if (b[d] != a[d]) {
                c.notify(X, this.getNormalStatus());
                if (a[d])
                    this.scheduleRefresh();
                else
                    this.cancelRefresh()
            }
            this.save()
        },getStatus: function() {
            var b = null, c = this._state[e], j = null;
            if (c === F) {
                var h = this.tokenExpiresIn();
                if (h <= 10) {
                    c = this._statusChecked ? wb : T;
                    this.updateStatus(c);
                    window.setTimeout(function() {
                        a.getLoginStatus({internal: true}, true)
                    }, 30000)
                } else {
                    if (h < 60)
                        c = cc;
                    b = {};
                    b[d] = this._state[d];
                    b[Yb] = this._state[Yb];
                    var i = this._state[f].split(" ");
                    b[f] = [];
                    for (var g = 0; g < i.length; g++) {
                        var k = Yf.exec(i[g]);
                        if (k)
                            j = k[1].split("_").join(".");
                        else
                            b[f].push(i[g])
                    }
                    this._state[f] = b[f].join(" ");
                    b[eb] = h;
                    b[fb] = this._state[fb]
                }
            } else if (!this._statusChecked)
                c = T;
            return {status: c,session: b,resource_id: j}
        },tryGetResponse: function(b, d, j) {
            i("AuthSession.tryGetResponse: requestTs = " + d + " scope = " + b);
            var a = this.getStatus(), h = a[e], n = a[he];
            session = a[we];
            if (h == T || h == cc)
                return null;
            if (d === undefined)
                if (b)
                    return session && Yd(session[f], b) ? a : null;
                else
                    return a;
            var g = this._state, k = parseInt(g[s]), c = g[l], m = g[E];
            if (k >= d) {
                if (session && (!c && j || Yd(session[f], b)))
                    return a;
                if (c)
                    return Gg(c, m);
                if (!b)
                    return a
            }
            return null
        }};
        function Yd(e, a) {
            if (a == null || U(a) == "")
                return true;
            var c = a.split(xg);
            for (var b = 0; b < c.length; b++) {
                var d = U(c[b]);
                if (d != "" && !Sg(e, d))
                    return false
            }
            return true
        }
        function oc(a) {
            return a === T ? wb : a === cc ? F : a
        }
        function ug(a) {
            if (Hf(a))
                return;
            if (Zf(a))
                return;
            if (Gf(a))
                return;
            var b = {};
            b[Tb] = K;
            b[Mb] = id;
            a.onCompleted(b)
        }
        function fh() {
            return window.XMLHttpRequest && (new XMLHttpRequest).withCredentials !== undefined
        }
        function Qd() {
            return a[db]
        }
        function Wd() {
            return a[Q]
        }
        function Ze(a) {
            n(a, [{name: z,type: b,optional: false}], a[h])
        }
        function Ig(a) {
            var b = a._properties, c = b[z];
            If(c, a)
        }
        var If = function() {
            var a = null, b = 1;
            return function(c) {
                var d = {};
                d[Wg] = b;
                c = rd(c, d);
                if (a === null) {
                    var e = pc();
                    a = Hd(c, e)
                } else
                    a.src = c
            }
        }();
        a.jsonp = {};
        WL.Internal.jsonp = a.jsonp;
        function Hf(c) {
            var i = document.getElementsByTagName("HEAD")[0], b = document.createElement("SCRIPT"), e = Od(c._properties, e, [r, z]), f = c._id, g = rc();
            if (g != null)
                e[d] = g;
            e[r] = He + f;
            e[pd] = "true";
            var h = gb(c._url, e);
            if (h.length > Cd)
                return false;
            c.scriptTag = b;
            a.jsonp[f] = function(a) {
                sc(f, b);
                c.onCompleted(a)
            };
            hg(b, c);
            b.setAttribute("async", "async");
            b.type = "text/javascript";
            b.src = h;
            i.appendChild(b);
            window.setTimeout(function() {
                if (c._completed)
                    return;
                sc(f, b)
            }, 30000);
            return true
        }
        function hg(b, c) {
            if (a._browser.ie && b.attachEvent)
                b.attachEvent("onreadystatechange", function(a) {
                    Hc(a, c)
                });
            else {
                b.readyState = "complete";
                b.addEventListener("load", function(a) {
                    Hc(a, c)
                }, false);
                b.addEventListener("error", function(a) {
                    Hc(a, c)
                }, false)
            }
        }
        function Hc(d, c) {
            if (c._completed)
                return;
            var b = d.srcElement || d.currentTarget;
            if (!b.readyState)
                b = d.currentTarget;
            if (b.readyState != "complete" && b.readyState != "loaded")
                return;
            var f = c._id;
            failure = d.type == "error" || a.jsonp[f] != null;
            if (failure) {
                sc(f, c.scriptTag);
                var e = {};
                e[Tb] = uf;
                e[Mb] = md;
                c.onCompleted({error: e})
            }
        }
        function sc(b, c) {
            delete a.jsonp[b];
            document.getElementsByTagName("HEAD")[0].removeChild(c)
        }
        function Gf(b) {
            Zg();
            if (a._browser.flashVersion < 9)
                return false;
            a.xdrFlash.send(b);
            return true
        }
        a.xdrFlash = {_id: "",_status: Bc,_flashObject: null,_requests: {},_pending: [],init: function() {
            if (this._status != Bc)
                return;
            this._status = ef;
            var c = Gb("div");
            c.id = "wl_xdr_container";
            document.body.appendChild(c);
            this._id = pc();
            var b = xf, d = a[G] + "XDR.swf";
            b = b.replace(/{url}/g, d);
            b = b.replace(/{id}/g, this._id);
            b = b.replace(/{variables}/g, "domain=" + document.domain);
            c.innerHTML = b
        },onReady: function(b) {
            if (b) {
                if (a._browser.firefox)
                    this._flashObject = document.embeds[this._id];
                else
                    this._flashObject = ob(this._id);
                this._status = kd
            } else
                this._status = mg;
            while (this._pending.length > 0)
                this.send(this._pending.shift())
        },onRequestCompleted: function(b, e, c, f) {
            var d = a.xdrFlash._requests[b];
            delete a.xdrFlash._requests[b];
            yc(d, e, Hg(c), f)
        },send: function(a) {
            if (this._status < kd) {
                this._pending.push(a);
                if (this._status == Bc)
                    ib(g(this, this.init));
                return
            }
            if (this._flashObject != null) {
                this._requests[a._id] = a;
                var b = Sd(a);
                this.invoke("send", [a._id, b.url, b.method, b.body])
            } else
                yc(a, 0, null, lf)
        },invoke: function(d, a) {
            a = a || [];
            var c = window.__flash__argumentsToXML(a, 0), e = '<invoke name="' + d + '" returntype="javascript">' + c + "</invoke>", b = this._flashObject.CallFunction(e);
            if (b == null)
                return null;
            return eval(b)
        }};
        WL.Internal.xdrFlash = a.xdrFlash;
        function Hg(a) {
            return a ? a.replace(/\r/g, " ").replace(/\n/g, " ") : a
        }
        var xf = "<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' codebase='https://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0' width='300' height='300' id='{id}' name='{id}' type='application/x-shockwave-flash' data='{url}'>" + "<param name='movie' value='{url}'></param>" + "<param name='allowScriptAccess' value='always'></param>" + "<param name='FlashVars' value='{variables}'></param>" + "<embed play='true' menu='false' swLiveConnect='true' allowScriptAccess='always' type='application/x-shockwave-flash' FlashVars='{variables}' src='{url}' width='300' height='300' name='{id}'></embed>" + "</object>", tc = null;
        (function() {
            a.fileDialog = function(b) {
                y(b[h]);
                if (a._pendingPickerOp != null)
                    throw new Error(Ee);
                return (new tc(b)).execute()
            };
            var i = 0, b = 1, f = 2, e = 3;
            tc = function(c) {
                var b = this;
                b._props = c;
                b._startTs = (new Date).getTime();
                c[FILEDIALOG_PARAM_LIGHTBOX] = c[FILEDIALOG_PARAM_LIGHTBOX] || FILEDIALOG_PARAM_LIGHTBOX_WHITE;
                c[FILEDIALOG_PARAM_SELECT] = c[FILEDIALOG_PARAM_SELECT] || FILEDIALOG_PARAM_SELECT_SINGLE;
                b._state = i;
                b._authDelegate = g(b, b._onAuthResp);
                a._pendingPickerOp = b
            };
            tc.prototype = {execute: function() {
                var a = this, b = new m(a._props[h], a, null);
                a._promise = b;
                a._process();
                return b
            },cancel: function(a) {
                var b = this, c = b._props;
                if (b._state === e)
                    return;
                if (!a)
                    a = k(lb, Nb.replace("METHOD", this._props[h]));
                b._onComplete(a)
            },_process: function() {
                var a = this;
                switch (a._state) {
                    case i:
                        a._validateAuth();
                        break;
                    case b:
                        a._initPicker();
                        break;
                    case f:
                        a._complete()
                }
            },_changeState: function(c, b) {
                var a = this;
                if (a._state !== e && a._state !== c) {
                    a._state = c;
                    if (b)
                        a._result = b;
                    a._process()
                }
            },_onComplete: function(a) {
                this._changeState(f, a)
            },_validateAuth: function() {
                var c = this, e = false;
                if (a._rpsAuth)
                    c._changeState(b);
                else {
                    var d = "";
                    switch (c._props[v]) {
                        case W:
                            d = Qg;
                            break;
                        case bd:
                            d = Pf;
                            break;
                        case jc:
                        case hc:
                            d = c._buildExternalScope();
                            e = true;
                            break;
                        default:
                            var i = jd.replace("METHOD", Lb).replace("PARAM", v);
                            c._onComplete(k(Lf, i));
                            return
                    }
                    var g = c._props[h], f = 650;
                    if (e)
                        a.login({scope: d,external_consent: true}).then(function(a) {
                            c._onExternalConsentComplete(a)
                        }, function(a) {
                            c._onComplete(a)
                        });
                    else {
                        d += " " + pe;
                        a.ensurePermission(d, f, g, c._authDelegate)
                    }
                }
            },_buildExternalScope: function() {
                var b = this, a = "onedrive_onetime.access:";
                switch (b._props[v]) {
                    case jc:
                        a += "read";
                        break;
                    case hc:
                        a += "readwrite"
                }
                switch (b._props[Wc]) {
                    case FILEDIALOG_PARAM_RESOURCETYPE_FILE:
                        a += "file|";
                        break;
                    case FILEDIALOG_PARAM_RESOURCETYPE_FOLDER:
                        a += "folder|";
                        break;
                    default:
                        a += "file|"
                }
                a += b._props[FILEDIALOG_PARAM_SELECT] === "single" ? "single" : "multi";
                return a
            },_onAuthResp: function(c) {
                var a = this;
                if (c.error) {
                    if (!a._channel)
                        a._onComplete(c)
                } else {
                    var e = c.session[d];
                    a._props[d] = e;
                    if (a._channel)
                        a._channel.send(Te, e);
                    else
                        a._changeState(b)
                }
            },_initPicker: function() {
                var b = this, d = b._props;
                n(d);
                b._channel = a.channel.registerOuterChannel(sf, Qc(d.url).host, d.frame.contentWindow, d.url, g(b, b._onMessage));
                c.subscribe(X, b._authDelegate);
                var e = function(a) {
                    if (a.keyCode === ah)
                        b.cancel()
                };
                d.keydownHandler = e;
                A(window, "keydown", e);
                b._initTimeout()
            },_initTimeout: function() {
                var a = this;
                timeoutSeconds = a._props[FILEDIALOG_PARAM_LOADING_TIMEOUT];
                if (timeoutSeconds && timeoutSeconds > 0)
                    a._timeout = window.setTimeout(g(a, a._onTimeout), timeoutSeconds * 1000)
            },_onTimeout: function() {
                var a = this, b = a._channel._connected;
                if (!b)
                    a.cancel(k(Ub, af));
                a._clearTimeout()
            },_clearTimeout: function() {
                var a = this;
                if (a._timeout) {
                    window.clearTimeout(a._timeout);
                    a._timeout = undefined
                }
            },_complete: function() {
                var b = this, c = b._result, d = c.error ? p : o;
                b._state = e;
                b._cleanup();
                b._normalizeResp();
                if (b._props[h] === Lb)
                    C(b._props[r], c, true);
                else if (c.data)
                    C(b._props[FILEDIALOG_PARAM_ONSELECTED], c, true);
                else
                    C(b._props[mb], c, true);
                b._promise[d](c);
                if (!a._rpsAuth)
                    tb(function() {
                        b._report()
                    })
            },_report: function() {
                var e = this._props, b = this._result, k = ((new Date).getTime() - this._startTs) / 1000, i = "none", c = 0, d = 0;
                if (b.data) {
                    if (b.data.folders != null)
                        c = b.data.folders.length;
                    if (b.data.files != null)
                        d = b.data.files.length
                }
                i = c > 0 && d > 0 ? "both" : c > 0 ? "folder" : d > 0 ? "file" : "none";
                var j = c + d, g = {client: Ob,api: e[h],mode: e[v],select: e[FILEDIALOG_PARAM_SELECT],result: b.error ? b.error.code : "success",duration: k,object_selected: i,selected_count: j}, f = a[Jb];
                a.api({path: "web_analytics",method: "POST",body: g});
                if (f)
                    f(g)
            },_onExternalConsentComplete: function(c) {
                var b = this;
                if (c.error) {
                    b._onComplete(c);
                    return
                }
                var d = c[he];
                if (d == null) {
                    b.complete(k(K, "Did not get expected resource id."));
                    return
                }
                a.api({path: d,method: "GET"}).then(function(a) {
                    b._onComplete(a)
                }, function(a) {
                    b._onComplete(a)
                })
            },_onMessage: function(a, b) {
                yb(a);
                switch (a) {
                    case Ve:
                        this._onComplete(b)
                }
            },_normalizeResp: function() {
                var e = this, g = e._props[v] === W, b = e._result.data, d = e._result.error, f = function(c) {
                    var b = c.upload_location;
                    if (b)
                        c.upload_location = b.replace("WL_APISERVICE_URL", a[Q])
                };
                if (b) {
                    if (b.folders)
                        for (var c = 0; c < b.folders.length; c++)
                            f(b.folders[c]);
                    if (b.files)
                        for (var c = 0; c < b.files.length; c++)
                            f(b.files[c])
                }
                if (d && d.message)
                    d.message = d.message.replace("METHOD", e._props[h])
            },_cleanup: function() {
                var d = this, b = d._props, g = d._channel, f = b.resizeHandler, e = b.keydownHandler;
                d._clearTimeout();
                c.unsubscribe(X, d._authDelegate);
                if (b.lightBox) {
                    b.frame.style.display = Vd;
                    b.lightBox.style.display = Vd;
                    document.body.removeChild(b.frame);
                    document.body.removeChild(b.lightBox);
                    delete b.lightBox;
                    delete b.frame
                }
                if (g) {
                    g.dispose();
                    delete d._channel
                }
                if (f)
                    B(window, "resize", f);
                if (e)
                    B(window, "keydown", e);
                delete a._pendingPickerOp
            }};
            function n(c) {
                var f = c[v] === W, e = f ? 800 : 500, d = f ? 600 : 450, g = c[FILEDIALOG_PARAM_LIGHTBOX], n = g === FILEDIALOG_PARAM_LIGHTBOX_TRANSPARENT, i = n ? 0 : 60, o = g === FILEDIALOG_PARAM_LIGHTBOX_WHITE ? "white" : "rgb(0,0,0)", k = i / 100, h = j(e, d), m = l(c), b = document.createElement("div");
                b.style.top = "0px";
                b.style.left = "0px";
                b.style.position = "fixed";
                b.style.width = "100%";
                b.style.height = "100%";
                b.style.display = "block";
                b.style.backgroundColor = o;
                b.style.opacity = k;
                b.style.MozOpacity = k;
                b.style.filter = "alpha(opacity=" + i + ")";
                b.style.zIndex = "2600000";
                var a = document.createElement("iframe");
                a.id = "picker" + (new Date).getTime();
                a.style.top = h.top;
                a.style.left = h.left;
                a.style.position = "fixed";
                a.style.width = e + "px";
                a.style.height = d + "px";
                a.style.display = "block";
                a.style.zIndex = "2600001";
                a.style.borderWidth = "1px";
                a.style.borderColor = "gray";
                a.style.maxHeight = "100%";
                a.style.maxWidth = "100%";
                a.src = m;
                a.setAttribute("sutra", "picker");
                document.body.appendChild(a);
                document.body.appendChild(b);
                c.resizeHandler = function() {
                    var b = j(e, d);
                    a.style.top = b.top;
                    a.style.left = b.left
                };
                A(window, "resize", c.resizeHandler);
                c.lightBox = b;
                c.frame = a;
                c.url = m
            }
            function l(e) {
                var f = e[v] === W, g = f ? Ge : Fe, b = {}, c = fe() + a[Vc];
                if (c.charAt(0) === "/")
                    c = bc + c;
                b[df] = g;
                b[Mf] = a._rpsAuth ? cf : We;
                b[wf] = window.location.hostname.toLowerCase();
                b[of] = c;
                b[q] = a._appId;
                b[s] = (new Date).getTime();
                b[Tf] = a._locale;
                if (!a._rpsAuth)
                    b[d] = e[d];
                if (f)
                    b[FILEDIALOG_PARAM_SELECT] = e[FILEDIALOG_PARAM_SELECT];
                return gb(a[S], b)
            }
            function j(f, e) {
                var b, c;
                if (a._browser.ie) {
                    var d = document.documentElement;
                    b = (d.clientWidth - f) / 2;
                    c = (d.clientHeight - e) / 2
                } else {
                    b = (window.innerWidth - f) / 2;
                    c = (window.innerHeight - e) / 2
                }
                b = b < 10 ? 10 : b;
                c = c < 10 ? 10 : c;
                return {left: b + "px",top: c + "px"}
            }
        })();
        var ge = 60 * 1000;
        Ec.prototype._getStrategy = function(e) {
            var g = this, c = e[h], a = e[bb], f = "file";
            n(e, [{name: bb,type: Rc,optional: false}], c);
            if (typeof a === b)
                a = document.getElementById(a);
            if (!(a instanceof HTMLInputElement) || a.type !== f)
                throw Y(bb, c, "It must be an HTMLInputElement with its type attribute set to " + '"file" (i.e., <input type="file" />).');
            if (a.value === "")
                throw Y(bb, c, "It did not contain a selected file.");
            if (a.files && window.FileReader) {
                if (a.files.length !== 1)
                    throw Y(bb, c, "It must contain one selected file.");
                var i = a.files[0];
                g.setFileName(i.name);
                return new zg(g, i)
            }
            var j = a.name !== "";
            if (!j)
                a.name = f;
            var d = null;
            if (a.form === null)
                d = "It must be a child of an HTMLFormElement.";
            else if (a.form.length !== 1)
                d = "It must be the only HTMLInputElement in its parent HTMLFormElement.";
            else if (a.name !== f)
                d = 'Its name attribute must be set to "file" ' + '(i.e., <input name="file" />).';
            if (d !== null)
                throw Y(bb, c, d);
            return new ad(g, a, c)
        };
        function ad(b, d, c) {
            var a = this;
            a._op = b;
            a._iMethod = c;
            a._element = d;
            a._uploadIFrame = null;
            a._uploadTimeoutId = null;
            b.setFileName(undefined)
        }
        ad.prototype = {getId: function() {
            var a = this;
            if (a._uploadIFrame === null)
                a._createUploadIFrame();
            return a._uploadIFrame.id
        },setUploadTimeout: function() {
            var a = this;
            a._uploadTimeoutId = window.setTimeout(function() {
                a.onTimeout()
            }, ge)
        },clearUploadTimeout: function() {
            var a = this;
            if (a._uploadTimeoutId === null)
                return;
            window.clearTimeout(a._uploadTimeoutId);
            a._uploadTimeoutId = null
        },onTimeout: function() {
            var a = this;
            a._uploadTimeoutId = null;
            var b = a._iMethod + ": did not receive a response in " + ge + " milliseconds.", c = k(Ub, b);
            a._op.uploadComplete(false, c)
        },onUploadComplete: function(a) {
            var b = this;
            b.clearUploadTimeout();
            b._removeUploadIFrame();
            a = Dc(a);
            var d = a.error, c = typeof d === Vb;
            b._op.uploadComplete(c, a)
        },upload: function(a) {
            this._multiPartFormUpload(a)
        },_getRequestUrl: function(d) {
            var b = {};
            b[x] = a._redirect_uri;
            var c = {};
            c[Zb] = yd;
            c[de] = this.getId();
            b[V] = w(c);
            return gb(d, b)
        },_createUploadIFrame: function() {
            var a = this;
            if (a._uploadIFrame !== null)
                return;
            a._uploadIFrame = Gb("iframe");
            a._uploadIFrame.name = a._uploadIFrame.id = pc();
            document.body.appendChild(a._uploadIFrame)
        },_multiPartFormUpload: function(c) {
            var a = this;
            a._createUploadIFrame();
            var b = a._getRequestUrl(c);
            a._submitForm(b);
            a.setUploadTimeout();
            jb.add(a);
            pf()
        },_removeUploadIFrame: function() {
            var a = this;
            if (a._uploadIFrame === null)
                return;
            a._uploadIFrame.parentNode.removeChild(a._uploadIFrame);
            a._uploadIFrame = null
        },_submitForm: function(c) {
            var b = this, a = b._element.form;
            a.target = b._uploadIFrame.name;
            a.method = Xd;
            a.enctype = "multipart/form-data";
            a.action = c;
            a.submit()
        }};
        function Sc() {
            this._pendingUploads = {}
        }
        Sc.prototype = {add: function(a) {
            var b = a.getId();
            this._pendingUploads[b] = a
        },hasPendingUploads: function() {
            for (var a in this._pendingUploads)
                return true;
            return false
        },isPending: function(a) {
            return a in this._pendingUploads
        },get: function(a) {
            return this._pendingUploads[a]
        },remove: function(a) {
            delete this._pendingUploads[a]
        }};
        var jb = new Sc, pf = function() {
            var b = false, a = new sb(je), c = function() {
                var d = a.getStates(), c = false;
                for (var b in d) {
                    if (!jb.isPending(b))
                        continue;
                    var f = d[b], e = jb.get(b);
                    jb.remove(b);
                    a.remove(b);
                    c = true;
                    e.onUploadComplete(f)
                }
                if (!jb.hasPendingUploads())
                    a.stopMonitor();
                if (c) {
                    a.save();
                    c = false
                }
            };
            return function() {
                if (a.isBeingMonitored())
                    return;
                if (b)
                    a.startMonitor();
                else {
                    a.addCookieChanged(c);
                    b = true
                }
            }
        }();
        function zg(a, b) {
            var c = this;
            c.upload = function(d) {
                var c = null;
                if (window.File && b instanceof window.File)
                    c = new FileReader;
                c.onerror = function(b) {
                    error = b.target.error;
                    a.onErr(error.name)
                };
                c.onload = function(e) {
                    var c = e.target.result, b = new XMLHttpRequest;
                    b.open(Kg, d, true);
                    b.onload = function(b) {
                        a.onResp(b.currentTarget.responseText)
                    };
                    b.onerror = function(b) {
                        a.onErr(b.currentTarget.statusText)
                    };
                    if (b.upload)
                        b.upload.onprogress = function(b) {
                            if (b.lengthComputable) {
                                var c = {bytesTransferred: b.loaded,totalBytes: b.total,progressPercentage: b.total === 0 ? 0 : b.loaded / b.total * 100};
                                a.uploadProgress(c)
                            }
                        };
                    a._cancel = g(b, b.abort);
                    b.send(c)
                };
                c.readAsArrayBuffer(b)
            }
        }
        function Bf() {
            a._isHttps = document.location.protocol.toLowerCase() == bc
        }
        function Zg() {
            if (a._browser.flash !== undefined)
                return;
            var b = 0;
            try {
                if (a._browser.ie) {
                    var k = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7"), f = k.GetVariable("$version"), i = f.split(" "), g = i[1], d = g.split(",");
                    b = d[0]
                } else if (navigator.plugins && navigator.plugins.length > 0)
                    if (navigator.plugins["Shockwave Flash 2.0"] || navigator.plugins["Shockwave Flash"]) {
                        var j = navigator.plugins["Shockwave Flash 2.0"] ? " 2.0" : "", e = navigator.plugins["Shockwave Flash" + j].description, h = e.split(" "), c = h[2].split(".");
                        b = c[0]
                    }
            } catch (l) {
            }
            a._browser.flashVersion = b;
            a._browser.flash = b >= 8
        }
        function Lg() {
            if (a._documentReady === undefined)
                a._documentReady = (new Date).getTime()
        }
        function eg(b) {
            ib(function() {
                if (a._browser.firefox && (!a._documentReady || (new Date).getTime() - a._documentReady < 1000))
                    window.setTimeout(b, 1000);
                else
                    b()
            })
        }
        function ib(b) {
            if (document.body)
                switch (document.readyState) {
                    case "complete":
                        b();
                        return;
                    case "loaded":
                        if (a._browser.webkit) {
                            b();
                            return
                        }
                        break;
                    case "interactive":
                    case undefined:
                        if (a._browser.firefox || a._browser.webkit) {
                            b();
                            return
                        }
                }
            if (document.addEventListener) {
                document.addEventListener("DOMContentLoaded", b, false);
                document.addEventListener("load", b, false)
            } else if (window.attachEvent)
                window.attachEvent("onload", b);
            if (a._browser.ie && document.attachEvent)
                document.attachEvent("onreadystatechange", function() {
                    if (document.readyState === "complete") {
                        document.detachEvent("onreadystatechange", arguments.callee);
                        b()
                    }
                })
        }
        function qe(b, a) {
            b.innerHTML = a
        }
        function A(a, b, c) {
            if (a.addEventListener)
                a.addEventListener(b, c, false);
            else if (a.attachEvent)
                a.attachEvent("on" + b, c)
        }
        function B(a, b, c) {
            if (a.removeEventListener)
                a.removeEventListener(b, c, false);
            else if (a.detachEvent)
                a.detachEvent("on" + b, c)
        }
        var Fc = {registerOuterChannel: function(e, b, c, d, a) {
            return H.registerChannel(e, b, c, d, a)
        },registerInnerChannel: function(c, b, a) {
            return H.registerChannel(c, b, null, null, a)
        },isSupported: function() {
            return H.isSupported()
        }}, H = {_channels: [],isSupported: function() {
            return window.postMessage != null
        },registerChannel: function(h, e, f, g, d) {
            var b = H, c = b._channels, a = null;
            if (b.isSupported()) {
                a = new Jd(h, e, f, g, d);
                if (c.length === 0)
                    A(window, "message", b._onMessage);
                c.push(a)
            }
            return a
        },unregisterChannel: function(d) {
            var c = H, a = c._channels;
            for (var b = 0; b < a.length; b++)
                if (a[b] == d) {
                    a.splice(b, 1);
                    break
                }
            if (a.length === 0)
                B(window, "message", c._onMessage)
        },_onMessage: function(a) {
            var d = H, a = a || window.event, c = Mg(a);
            if (c != null) {
                var b = d._findChannel(a, c);
                if (b != null)
                    switch (c.text) {
                        case te:
                            b._onConnectReq(a.source, a.origin);
                            break;
                        case se:
                            b._onConnectAck(a.source);
                            break;
                        default:
                            b._onMessage(c.text)
                    }
            }
        },_findChannel: function(g, f) {
            var d = H, c = d._channels, e = Ug(g.origin);
            for (var b = 0; b < c.length; b++) {
                var a = c[b];
                if (fd(a._name, f.name) && fd(a._allowedDomain, e))
                    return a
            }
            return null
        }}, te = "@ConnectReq", se = "@ConnectAck";
        function Jd(f, d, b, e, c) {
            var a = this;
            a._name = f;
            a._allowedDomain = d;
            a._msgHandler = c;
            if (b) {
                a._targetWindow = b;
                a._targetUrl = Jg(e);
                a._connect()
            }
        }
        Jd.prototype = {_disposed: false,_connected: false,_allowedDomain: null,_targetWindow: null,_targetUrl: null,_msgHandler: null,_connectSchedule: -1,_pendingQueue: [],_recvQueue: [],dispose: function() {
            var a = this;
            if (!a._disposed) {
                a._disposed = true;
                a._cancelConnect();
                H.unregisterChannel(a)
            }
        },send: function(c, d) {
            var a = this;
            if (a._disposed)
                return;
            var b = Rf(c, d);
            if (a._connected)
                a._post(b);
            else
                a._pendingQueue.push(b)
        },_connect: function() {
            var a = this;
            if (a._disposed || a._connected)
                return;
            var b = function() {
                a._post(te)
            };
            if (a._connectSchedule === -1) {
                a._connectSchedule = window.setInterval(b, 500);
                b()
            }
        },_post: function(b) {
            var a = this, c = tg(a._name, b);
            a._targetWindow.postMessage(c, a._targetUrl)
        },_onConnectReq: function(c, b) {
            var a = this;
            if (!a._connected) {
                a._connected = true;
                a._targetWindow = c;
                a._targetUrl = b;
                a._post(se);
                a._onConnected()
            }
        },_onConnectAck: function(b) {
            var a = this;
            if (!a._connected) {
                a._targetWindow = b;
                a._onConnected()
            }
        },_onConnected: function() {
            var a = this, c = a._pendingQueue;
            a._connected = true;
            for (var b = 0; b < c.length; b++)
                a._post(c[b]);
            a._pendingQueue = [];
            a._cancelConnect()
        },_cancelConnect: function() {
            var a = this;
            if (a._connectSchedule != -1) {
                window.clearInterval(a._connectSchedule);
                a._connectSchedule = -1
            }
        },_onMessage: function(b) {
            if (this._msgHandler) {
                var a = Qf(b);
                this._msgHandler(a.cmd, a.args)
            }
        }};
        function Jg(b) {
            var a = b.indexOf("://");
            if (a >= 0) {
                a = b.indexOf("/", a + 3);
                if (a >= 0)
                    b = b.substring(0, a)
            }
            return b
        }
        function Mg(a) {
            var b = null;
            if (!Ed(a.origin) && !Ed(a.data) && a.source != null)
                b = sg(a.data);
            return b
        }
        function tg(a, b) {
            return "<" + a + ">" + b
        }
        function sg(a) {
            var c = null;
            if (a.charAt(0) == "<") {
                var b = a.indexOf(">");
                if (b > 0) {
                    var d = a.substring(1, b), e = a.substr(b + 1);
                    c = {name: d,text: e}
                }
            }
            return c
        }
        function Rf(a, c) {
            var b = {cmd: a,args: c};
            return JSON.stringify(b)
        }
        function Qf(b) {
            var a = JSON.parse(b);
            return a
        }
        if (window.WL)
            a.channel = Fc;
        else
            window.WL = {channel: Fc};
        var u = {connect: "\u8fde\u63a5",signIn: "\u767b\u5f55",signOut: "\u6ce8\u9500",login: "\u767b\u5f55",logout: "\u6ce8\u9500",skyDriveOpenPickerButtonText: "\u4ece SkyDrive \u6253\u5f00",skyDriveOpenPickerButtonTooltip: "\u4ece SkyDrive \u6253\u5f00",skyDriveSavePickerButtonText: "\u4fdd\u5b58\u5230 SkyDrive",skyDriveSavePickerButtonTooltip: "\u4fdd\u5b58\u5230 SkyDrive"};
        a._locale = "zh-cn";
        a[O] = "Web/DEVICE_" + Jf("5.5.7914.4003");
        a.testInit = function(b) {
            if (b.env)
                a._settings.init(b.env);
            if (b.skydrive_uri)
                a._settings[S] = b.skydrive_uri;
            if (b[Jb])
                a[Jb] = b[Jb]
        };

        var pb = {};
        pb[db] = "login.live.com";
        pb[Q] = "https://apis.live.net/v5.0/";
        pb[S] = "https://onedrive.live.com/";
        pb[G] = "//js.live.net/v5.0/";

        var xb = {};
        xb[db] = "login.live.com";
        xb[Q] = "https://df.apis.live.net/v5.0/";
        xb[S] = "https://onedrive.live.com/";
        xb[G] = "//df-js.live.net/v5.0/";

        var ub = {};
        ub[db] = "login.live-int.com";
        ub[Q] = "https://apis.live-int.net/v5.0/";
        ub[S] = "https://onedrive.live-int.com/";
        ub[G] = "//js.live-int.net/v5.0/";

        var nb = {};
        nb[db] = "login.live-int.com";
        nb[Q] = "https://current.apis.live-tst.net/v5.0/";
        nb[S] = "https://onedrive.live-int.com/";
        nb[G] = "//current-js.live-int.net/v5.0/";

        var rb = {};
        rb[db] = "login.live-int.com";
        rb[Q] = "https://bvt.apis.live-tst.net/v5.0/";
        rb[S] = "https://onedrive-bvt.live-int.com/";
        rb[G] = "//bvt-js.live-int.net/v5.0/";

        a._settings = {
            PROD: pb,
            DF: xb,
            INT: ub,
            CURRENT: nb,
            BVT: rb,
            init: function(b) {
                b = b.toUpperCase();
                var c = this[b];
                if (c)
                    M(c, a)
            }
        };

        a._settings.init("PROD");

        a[Vc] = "wl.skydrivepicker.js";

        a.onloadInit()
    }
})();