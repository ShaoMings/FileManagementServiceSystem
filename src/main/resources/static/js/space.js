getUserStatus()

let icons = {
    "song": '<i class="fa fa-music" aria-hidden="true"></i>',
    "image": '<i class="fa fa-picture-o" aria-hidden="true"></i>',
    "zip": '<i class="fa fa-file-zip-o" aria-hidden="true"></i>',
    "txt": '<i class="fa fa-file-text-o" aria-hidden="true"></i>',
    "ppt": '<i class="fa fa-file-powerpoint-o" aria-hidden="true"></i>',
    "word": '<i class="fa fa-file-word-o" aria-hidden="true"></i>',
    "excel": '<i class="fa fa-file-excel-o" aria-hidden="true"></i>',
    "video": '<i class="fa fa-video-camera" aria-hidden="true"></i>',
    "md": '<i class="fa fa-file-text-o" aria-hidden="true"></i>',
    "pdf": '<i class="fa fa-file-pdf-o" aria-hidden="true"></i>',
    "code": '<i class="fa fa-file-code-o" aria-hidden="true"></i>',
    "other": '<i class="fa fa-question-circle" aria-hidden="true"></i>'
}


function getFileTypeIcon(name) {
    let index = name.lastIndexOf(".");
    let length = name.length;
    let suffix = name.substring(index + 1, length).toLowerCase();
    let type = kit.getFileType(suffix);
    return icons[type];
}

/**
 * 获取文件统计/状态信息
 */
function getUserStatus() {
    $.get('/status/getUserStatus', function (res) {
        let data = res.data;
        if (res.code === 200) {
            $("#userFileCount").text(data.count);
            $("#userFileSize").text(data.size);
            $("#userDiskTotalSpace").text(data.total);
            $("#userDiskLeftSpace").text(data.left);
        } else {
            layer.msg(data.msg);
        }
    });

    $.get('/share/userRecord', function (res) {
        let loadIndex = layer.load();
        let data = res.data;
        if (res.code === 200) {
            let obj = $('#recently');
            let noRecordHtml = '<div class="file-item-blank">\n' +
                '                            <div class="file-title-blank"></div>\n' +
                '                            <div class="file-size-blank"></div>\n' +
                '                            <div class="file-time-blank"></div>\n' +
                '                            <div class="file-md5-blank"></div>\n' +
                '                        </div>';
            if (data.length > 0) {
                $.each(res.data, function (index, value) {
                    let recordHtml = '<div class="file-item">\n' +
                        '                <div class="file-title" data-username="' + value.sharerUsername + '" data-id="' + value.shareId + '" data-read="' + value.read + '"  ' +
                        'data-name="' + value.fileName + '" data-path="' + value.filePath + '">' + getFileTypeIcon(value.fileName) + '&nbsp;' + value.fileName + '</div>\n' +
                        '                <div class="file-size">文件大小&nbsp;: ' + value.size + '</div>\n' +
                        '                <div class="file-time">上传时间&nbsp;: ' + value.time + '</div>\n' +
                        '                <div class="file-md5">文件md5&nbsp;: ' + value.fileMd5 + '</div>\n' +
                        '            </div>';
                    obj.append(recordHtml);
                    if (data.length < 4) {
                        for (let i = 0; i < 4 - data.length; i++) {
                            obj.append(noRecordHtml);
                        }
                    }
                });
            } else {
                for (let i = 0; i < 4; i++) {
                    obj.append(noRecordHtml);
                }
            }
            layer.close(loadIndex);
        } else {
            layer.msg(data.msg);
        }
    });
}


// 文件预览
$("#recently").on("click", ".file-title", function () {
    let name = $(this).data("name");
    let path = $(this).data("path");
    let obj = $(this);
    let shareId = $(this).data("id");
    let readCount = $(this).data("read");
    let read = $(this).parent().find('.file-read-count');
    let username = $(this).data("username");
    let token;
    let address;
    let canReadFlag = true;
    $.ajax({
        url: "/peers/address",
        async: false,
        success: function (res) {
            address = res;
        }
    });
    let source = address + "/" + username + "/" + (path === "" ? name : path + "/" + name);

    // 文件预览token
    $.ajax({
        url: "/preview/token",
        method: "post",
        data: {"filePath": username + "/" + (path === "" ? name : path + "/" + name)},
        async: false,
        success: function (res) {
            token = res;
            source = source + "?auth_token=" + token;
        }
    });
    let index = name.lastIndexOf(".");
    let length = name.length;
    let suffix = name.substring(index + 1, length).toLowerCase();
    let doc_types = ["word", "excel", "ppt"];
    //图片
    if (kit.getFileType(suffix) === "image") {
        let img = {
            "data": [
                {
                    "alt": name,
                    "src": encodeURI(source).replace("%20", " "),
                }
            ]
        }
        layer.photos({
            photos: img,
            anim: 5,
            shade: 0.3
        });
    } else if (kit.getFileType(suffix) === "song") {
        //音乐
        layer.open({
            type: 1,
            shadeClose: true,
            area: ['400px', '120px'],
            title: name,
            shade: 0.3,
            content: '<audio src="' + source + '" autoplay controls style="width: 350px;display: block;margin: 10px auto auto;">您的浏览器不支持 audio 标签。</audio>'
        });
    } else if (kit.getFileType(suffix) === "video") {
        //视频
        layer.open({
            type: 1,
            shadeClose: true,
            area: ['400px', '271x'],
            title: name,
            shade: 0.3,
            content: '<video src="' + source + '" autoplay controls style="width: 400px;height: 226px">您的浏览器不支持 video 标签。</video>'
        });
    } else if (kit.getFileType(suffix) === "txt") {
        // 文本
        let resObj = $.ajax({url: source, async: false});
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            title: '文件内容',
            shadeClose: true,
            shade: 0.3,
            area: ['500px', '400px'],
            content: resObj.responseText
        });
        // window.open(source + "?download=0");
    } else if (kit.getFileType(suffix) === "code") {
        let resObj = $.ajax({url: source, async: false});
        let converter = new showdown.Converter();
        let context = converter.makeHtml('```md\n' + resObj.responseText + '\n```');
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            title: '文件内容',
            shadeClose: true,
            shade: 0.3,
            area: ['90%', '90vh'],
            content: '<div id="show-area" class="clearfix" style="width: 100%;height: 100%;overflow: auto;background-color: #FCF6E5;">' + context + '</div>'
        });
    } else if (kit.getFileType(suffix) === "md") {
        // MarkDown文件预览
        let resObj = $.ajax({url: source, async: false});
        let converter = new showdown.Converter();
        let context = converter.makeHtml(resObj.responseText);
        layer.open({
            type: 1,
            title: '文件内容',
            skin: 'layui-layer-rim', //加上边框
            area: ['90%', '90vh'], //宽高
            content: '<div id="show-area" class="clearfix" style="width: 100%;height: 100%;overflow: auto;background-color: #FCF6E5;">' + context + '</div>'
        })
    } else if (kit.getFileType(suffix) === "pdf") {
        let viewer_url = source + "?auth_token=" + token + "&download=0";
        layer.open({
            type: 2,
            title: '文件内容',
            skin: 'layui-layer-rim', //加上边框
            area: ['90%', '90vh'], //宽高
            content: viewer_url
        });
    } else {
        if (doc_types.indexOf(kit.getFileType(suffix)) !== -1) {
            layer.msg("该文档格式需下载预览!");
            return;
        }
        layer.msg("该文件格式暂不支持预览");
        canReadFlag = false;
    }
    if (canReadFlag) {
        $.ajax({
            url: "/share/read",
            method: "post",
            data: {"shareId": shareId, "newCount": readCount + 1},
            success: function (res) {
                if (res.code === 200) {
                    obj.data("read", readCount + 1);
                    read.html('&nbsp;&nbsp;' + (readCount + 1));
                } else {
                    layer.msg(res.msg);
                }
            }
        });
    } else {
        canReadFlag = true;
    }
});