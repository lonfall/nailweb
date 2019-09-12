/**
 * Created by loneyfall on 2019/8/7.
 */

//获取项目根路径
function getRootPath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPaht = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}

$(function () {
    var content = $("#token").html();
    var cb = new ClipboardJS('#copy', {
        text: function () {
            return content;
        }
    });
    cb.on('success', function (e) {
        alert("复制成功");
    });

    cb.on('error', function (e) {
        console.log(e);
    });

    $("#open_swagger").click(function () {
        window.location.href = getRootPath() + "/swagger-ui.html";
    });
});
