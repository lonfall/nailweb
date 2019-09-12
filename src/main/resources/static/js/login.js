function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    $.ajax({
        async: true,
        type: "post",
        url: "/login?username=" + username + "&password=" + password,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({}),
        dataType: "json",
        success: function (data) {
            console.log(data);
            if (data.code == 200 && data.data) {
                console.log("token:", data.data);

                toHome(data.data);
            } else {
                $("#error-content").html(data.msg ? data.msg : "登录验证失败！");
                $("#error-alert").modal("open");
            }
        },
        error: function (data) {
            console.log(data);
            $("#error-content").html("登录验证失败！");
            $("#error-alert").modal("open");
        }
    })
}

//获取项目根路径
function getRootPath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPaht = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}

//发送POST请求跳转到指定页面
function httpPost(URL, PARAMS) {
    var temp = document.createElement("form");
    temp.action = URL;
    temp.method = "post";
    temp.style.display = "none";
    for (var x in PARAMS) {
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = PARAMS[x];
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}

function toHome(token) {
    // request.setRequestHeader("token", token);
    // setCookie("token", token);
    window.location.href = getRootPath() + "/index";
}

//浏览器端设置cookie，value必须是一个字符串，如果存入对象可以存一个json字符串
function setCookie(name, value) {
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + value + ";expires=" + exp.toGMTString();
}

//浏览器端获取cookie
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return arr[2];
    else
        return null;
}