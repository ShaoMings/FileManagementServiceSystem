getShareRecord();


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


function getShareRecord() {
    let loadIndex = layer.load();
    $.post('/share/record', function (res) {
        if (res.code === 200) {
            let data = res.data;
            // console.log(data)
            let container = $('#share-container');
            if (data.length > 0) {
                $.each(data, function (index, value) {
                    let html = '<div class="share-file-item">\n' +
                        '            <div class="file-title" data-username="' + value.sharerUsername + '" data-id="' + value.shareId + '" data-read="' + value.read + '"  ' +
                        'data-name="' + value.fileName + '" data-path="' + value.filePath + '">' + getFileTypeIcon(value.fileName) + '&nbsp;' + value.fileName + '</div>\n' +
                        '            <div class="file-sharer">分享人&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;' + value.sharer + '</div>\n' +
                        '            <div class="file-size">文件大小&nbsp;: ' + value.size + '</div>\n' +
                        '            <div class="file-share-time">分享时间&nbsp;: ' + value.time + '</div>\n' +
                        '            <div class="file-data" style="margin-bottom: 28px;">\n' +
                        '                <span class="file-download">\n' +
                        '                    <i class="fa fa-arrow-down" aria-hidden="true"></i>\n' +
                        '                    <span class="file-download-count">&nbsp;&nbsp;' + value.download + '</span>\n' +
                        '                </span>\n' +
                        '                <span class="file-read">\n' +
                        '                    <i class="fa fa-eye" aria-hidden="true"></i>\n' +
                        '                    <span class="file-read-count">&nbsp;&nbsp;' + value.read + '</span>\n' +
                        '                </span>\n' +
                        '            </div>\n' +
                        '            <div class="option">\n' +
                        (Number(role) < 3 ? '<button class="delete-btn"  data-id="' + value.shareId + '" data-role="'+value.sharerRole+'">删除分享</button>' : '') +
                        '                <button class="download-btn" data-username="' + value.sharerUsername + '" data-id="' + value.shareId + '" ' +
                        'data-download="' + value.download + '" data-remote="' + value.remote + '" data-token="' + value.token + '" data-name="' + value.fileName + '" data-path="' + value.filePath + '">文件下载</button>' +
                        '            </div>\n' +
                        '        </div>'
                    container.append(html);
                });
            } else {
                let html = '<div style="margin: 0 auto;text-align: center;font-size: 22px;font-weight: 500;color: rgba(0, 0, 0, .85);">还没有人文件分享,快来分享你的文件吧!</div>';
                container.append(html);
            }

            layer.close(loadIndex);
        }
    })
}

/*监听删除按钮*/
$("#share-container").on('click','.delete-btn',function () {
    let item = $(this).parent().parent();
    let shareId = $(this).data("id");
    let sharerRole = $(this).data("role");
    if (Number(role) !== 1 && sharerRole === 1){
        layer.msg("你没有权限删除该记录!");
        return;
    }

    $.ajax({
        url:"/share/delRecord",
        method:"post",
        data:{"shareId":shareId},
        success:function (res) {
            if (res.code === 200){
                layer.msg("删除成功!");
                item.remove();
            }else {
                layer.msg(res.msg);
            }
        }
    });
})

/*监听下载按钮*/
$("#share-container").on("click", ".download-btn", function () {
    let name = $(this).data("name");
    let path = $(this).data("path");
    let remote = $(this).data("remote");
    let token = $(this).data("token");
    let username = $(this).data("username");
    let obj = $(this);
    let shareId = $(this).data("id");
    let downloadCount = $(this).data("download");
    let download = $(this).parent().parent().find('.file-download-count');
    $.ajax({
        url: "/share/download",
        method: "post",
        data: {"shareId": shareId, "newCount": (downloadCount + 1)},
        success: function (res) {
            if (res.code === 200) {
                obj.data('download', downloadCount + 1);
                download.html('&nbsp;&nbsp;' + (downloadCount + 1));
            } else {
                layer.msg(res.msg);
            }
        }
    });
    layer.msg("已提交下载请求!");
    if (remote === 0){
        let url = "/file/downloadFile";
        let form = $("<form></form>").attr("action", url).attr("method", "post");
        form.append($("<input/>").attr("type", "hidden").attr("name", "path").attr("value", path));
        form.append($("<input/>").attr("type", "hidden").attr("name", "name").attr("value", name));
        form.append($("<input/>").attr("type", "hidden").attr("name", "username").attr("value", username));
        form.appendTo('body').submit().remove();
    }else {
        let url = "/repo/gite/open/download";
        let form = $("<form></form>").attr("action", url).attr("method", "post");
        form.append($("<input/>").attr("type", "hidden").attr("name", "filePath").attr("value", path));
        form.append($("<input/>").attr("type", "hidden").attr("name", "token").attr("value", token));
        form.appendTo('body').submit().remove();
    }

})

// 文件预览
$("#share-container").on("click", ".file-title", function () {
    let name = $(this).data("name");
    let path = $(this).data("path");
    let obj = $(this);
    let shareId = $(this).data("id");
    let readCount = $(this).data("read");
    let read = $(this).parent().find('.file-read-count');
    let username = $(this).data("username");
    let token;
    let canReadFlag = true;
    let address = "http://1.15.221.117:8085/group1";
    $.ajax({
        url: "/peers/address",
        async: false,
        success: function (res) {
            address = res;
        }
    });
    let source
    let group = address.substring(address.lastIndexOf("/")+1)
    if(path.indexOf(username+"/"+group)<0){
        source = address + "/" + username + "/" + (path === "" ? name : path.indexOf(name)<0? (path+"/" + name):path);
    }else {
       source = address.substring(0,address.lastIndexOf("/")) + (path === "" ? name : path.indexOf(name)<0? (path+"/" + name):path);
    }

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
    let loadIndex = layer.load();
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
        layer.close(loadIndex);
        layer.photos({
            photos: img,
            anim: 5,
            shade: 0.3
        });
    } else if (kit.getFileType(suffix) === "song") {
        //音乐
        layer.close(loadIndex);
        layer.open({
            type: 1,
            shadeClose: true,
            area: ['400px', '120px'],
            title: name,
            shade: 0.3,
            content: '<audio src="' + source + '" autoplay controls style="width: 350px;display: block;margin: 10px auto auto;">您的浏览器不支持 audio 标签。</audio>'
        });
    } else if (kit.getFileType(suffix) === "video") {
        $.getScript("/static/js/easyplayer.min.js", function () {
            //视频
            layer.close(loadIndex);
            layer.open({
                type: 1,
                shadeClose: true,
                area: ['400px', '271x'],
                offset: ['35%', '35%'],
                title: name,
                shade: 0.3,
                content: '<easy-player video-url="' + source + '" decodeWasm="true" style="width: 400px;height: 226px;position: fixed"></easy-player>'
            });
        });
    } else if (kit.getFileType(suffix) === "txt") {
        // 文本
        let resObj = $.ajax({url: source, async: false});
        layer.close(loadIndex);
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
        layer.close(loadIndex);
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
        layer.close(loadIndex);
        layer.open({
            type: 1,
            title: '文件内容',
            skin: 'layui-layer-rim', //加上边框
            area: ['90%', '90vh'], //宽高
            content: '<div id="show-area" class="clearfix" style="width: 100%;height: 100%;overflow: auto;background-color: #FCF6E5;">' + context + '</div>'
        })
    } else if (kit.getFileType(suffix) === "pdf") {
        let viewer_url = source + ((source.indexOf("auth_token")>-1?"":("?auth_token=" + token))+"&download=0");
        layer.close(loadIndex);
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