getParentFile()
/*初始化layui*/
let element;
layui.use(['element'], function () {
    element = layui.element;
});
/*监听创建文件夹按钮点击*/
$('#mkdir').click(function () {
    let dir_path = $('#dir-path').data('path');
    let file_path = $('#file-path').data('path');
    let current_path = "files/";
    if (dir_path !== undefined) {
        current_path += dir_path;
    }
    if (file_path !== undefined) {
        current_path += file_path;
    }
    if (dir_path === undefined && file_path === undefined) {
        current_path = "files" +  $('#path-side').children("a:last-child").data("path");
    }
    layer.prompt({title: '输入文件夹名称', formType: 0}, function (name, index) {
        $.post('/file/mkdir', {"path": current_path + "/" + name}, function (res) {
            if (res.code === 200) {
                openDir(current_path.replace("files/",""));
                layer.msg("创建成功");
            } else {
                layer.msg(res.msg);
            }
        });
        layer.close(index);
    });
})

/* 监听文件检索框 */
$('#search').on('input', function (e) {
    let keywords = e.delegateTarget.value;
    if (keywords === "" || keywords === undefined) {
        getParentFile();
    } else {
        let index = layer.load();
        $.post('/file/getSearchFiles', {"keywords": keywords}, function (res) {
            if (res.code === 200) {
                let data = res;
                template.helper('iconHandler', function (name, isDir) {
                    let icon;
                    if (isDir === true) {
                        icon = "file";
                    } else {
                        let index = name.lastIndexOf(".");
                        let length = name.length;
                        let suffix = name.substring(index + 1, length).toLowerCase();
                        icon = kit.getIconName(suffix);
                    }
                    return icon;
                });
                let html = template('file-list', data);
                $("#file-result").html(html);
                layer.close(index);
            } else {
                layer.close(index);
                layer.msg("系统异常");
            }
        });
    }
})


/*获取所有一级目录及文件*/
function getParentFile() {
    let index = layer.load();
    $.post('/file/getParentFile', function (result) {
        if (result.code === 200) {
            let data = result;
            template.helper('iconHandler', function (name, isDir) {
                let icon;
                if (isDir === true) {
                    icon = "file";
                } else {
                    let index = name.lastIndexOf(".");
                    let length = name.length;
                    let suffix = name.substring(index + 1, length).toLowerCase();
                    icon = kit.getIconName(suffix);
                }
                return icon;
            });
            let html = template('file-list', data);
            $("#file-result").html(html);
            $("#path-side").html('<a class="path-side-btn" data-path=""><cite>全部文件</cite></a>');
            layer.close(index);
        } else {
            layer.close(index);
            layer.msg("系统异常");
        }
    });
}

/*文件夹点击事件*/
$("#file-result").on("click", ".resultDir", function () {
    let dirName = $(this).data("name");
    let dirPath = $(this).data("path");
    openDir(dirPath + "/" + dirName);
});

/*监听文件导航*/
$("#path-side").on("click", ".path-side-btn", function () {
    let dir = $(this).data("path");
    openDir(dir);
})

//打开文件夹
function openDir(dir) {
    let index = layer.load();
    let suff = dir.substring(0, 1);
    if (suff === "/") {
        dir = dir.substring(1);
    }
    $.post('/file/getDirFile', {"dir": dir}, function (result) {
        if (result.code === 200) {
            let data = result;
            template.helper('iconHandler', function (name, isDir) {
                let icon;
                if (isDir === true) {
                    icon = "file";
                } else {
                    let index = name.lastIndexOf(".");
                    let length = name.length;
                    let suffix = name.substring(index + 1, length).toLowerCase();
                    icon = kit.getIconName(suffix);
                }
                return icon;
            });
            let html = template('file-list', data);
            $("#file-result").html(html);
            setPathSide("/" + dir);
            layer.close(index);
        } else {
            layer.close(index);
            layer.msg("系统异常");
        }
    });
}

//设置文件导航
function setPathSide(dir) {
    let arr = dir.split('/');
    let html = '<a class="path-side-btn" data-path="">全部文件</a>';
    let path = "";
    for (let i = 0; i < arr.length; i++) {
        if (arr[i] !== "") {
            html += '<a class="path-side-btn" data-path="' + (path + "/" + arr[i]) + '">' + arr[i] + '</a>';
            path += "/" + arr[i];
        }
    }
    $("#path-side").html(html);
    element.init();
}

/*监听下载按钮*/
$("#file-result").on("click", ".download-btn", function () {
    let name = $(this).data("name");
    let path = $(this).data("path");
    let url = "/file/downloadFile";
    let form = $("<form></form>").attr("action", url).attr("method", "post");
    form.append($("<input></input>").attr("type", "hidden").attr("name", "path").attr("value", path));
    form.append($("<input></input>").attr("type", "hidden").attr("name", "name").attr("value", name));
    form.appendTo('body').submit().remove();
})

/*监听上传按钮*/
$("#file-result").on("click", ".upload-file-btn", function () {
    let name = $(this).data("name");
    let path = $(this).data("path");
    layer.open({
        type: 2,
        skin: 'layui-layer-rim', //加上边框
        title: '文件上传',
        shadeClose: true,
        shade: 0.3,
        area: ['90%', '90vh'],
        content: "/file/upload",
        success: function (obj, index) {
            let body = layer.getChildFrame('body', index);
            // 获取上传页面的元素进行初始化渲染
            body.contents().find("#path").val(path + "/" + name);
        }
    });
})


/*监听详情按钮*/
$("#file-result").on("click", ".details-btn", function () {
    let md5 = $(this).data("md5");
    $.post('/file/details', {"md5": md5}, function (result) {
        if (undefined === result.data.name) {
            console.log("name = null");
        }
        let html = '<div class="file-details-box">' +
            '<ul>' +
            '<li><span>名称:&nbsp;</span>' + result.data.name + '</li>' +
            '<li><span>路径:&nbsp;</span>' + result.data.path + '</li>' +
            '<li><span>url:&nbsp;</span>' + result.data.url + '</li>' +
            '<li><span>MD5:&nbsp;</span>' + result.data.md5 + '</li>' +
            '<li><span>场景:&nbsp;</span>' + result.data.scene + '</li>' +
            '<li><span>大小:&nbsp;</span>' + result.data.size + '</li>' +
            '<li><span>日期:&nbsp;</span>' + result.data.timeStamp + '</li>' +
            '</ul>' +
            '</div>';
        layer.open({
            type: 1,
            title: '文件信息',
            shadeClose: true,
            shade: 0.3,
            area: ['500px', '400px'],
            content: html
        });
    })
})

/*监听文件夹删除按钮*/
$("#file-result").on("click", ".delete-dir-btn", function () {
    // layer.msg("该功能未开发完成!");
    let name = $(this).data("name");
    let path = $(this).data("path");
    let $this = $(this);
    layer.confirm('确定要删除该文件夹吗?', {icon: 3, title: '提示'}, function (index) {
        $.post('/file/deleteDir', {"path": path + "/" + name}, function (result) {
            // console.log(result);
            if (result.code === 200) {
                $this.parent().parent().remove();
                let len = $(".file-list-file-box").length;
                if (len === 0) {
                    $("#file-result").html('<div class="file-list-file-box"><div class="no-file-tip" value="">暂无文件</div></div>');
                }
                layer.msg("删除成功");
            } else {
                layer.msg(result.msg);
            }
        })
        layer.close(index);
    });
})

/*监听文件删除按钮*/
$("#file-result").on("click", ".delete-file-btn", function () {
    let name = $(this).data("name");
    let md5 = $(this).data("md5");
    let $this = $(this);
    layer.confirm('确定要删除' + name + '吗?', {icon: 3, title: '提示'}, function (index) {
        $.post('/file/deleteFile', {"md5": md5}, function (result) {
            if (result.code === 200) {
                $this.parent().parent().remove();
                let len = $(".file-list-file-box").length;
                if (len === 0) {
                    $("#file-result").html('<div class="file-list-file-box"><div class="no-file-tip" value="">暂无文件</div></div>');
                }
                layer.msg("删除成功");
            } else {
                layer.msg(result.msg);
            }
        })
        layer.close(index);
    });
})


// 文件预览
$("#file-result").on("click", ".resultFile", function () {
    let name = $(this).data("name");
    let path = $(this).data("path");
    let peer = $(this).data("peer");
    let source = peer + "/" + path + "/" + name;
    let index = name.lastIndexOf(".");
    let length = name.length;
    let suffix = name.substring(index + 1, length).toLowerCase();
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
    } else if (kit.getFileType(suffix) === "md") {
        // MarkDown文件预览
        let resObj = $.ajax({url: source, async: false});
        let converter = new showdown.Converter();
        let context = converter.makeHtml(resObj.responseText);
        // console.log(context);
        layer.open({
            type: 1,
            title: '文件内容',
            skin: 'layui-layer-rim', //加上边框
            area: ['90%', '90vh'], //宽高
            content: '<div id="show-area" class="clearfix" style="width: 100%;height: 100%;overflow: auto;background-color: #FCF6E5;">' + context + '</div>'
        })
    } else {
        layer.msg("该文件格式暂不支持预览");
    }

})

//图片弹出鼠标滚动缩放
$(document).on("mousewheel DOMMouseScroll", ".layui-layer-phimg img", function (e) {
    let delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) || // chrome & ie
        (e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1)); // firefox
    let imagep = $(".layui-layer-phimg").parent().parent();
    let image = $(".layui-layer-phimg").parent();
    let h = image.height();
    let w = image.width();
    if (delta > 0) {
        if (h < (window.innerHeight)) {
            h = h * 1.05;
            w = w * 1.05;
        }
    } else if (delta < 0) {
        if (h > 100) {
            h = h * 0.95;
            w = w * 0.95;
        }
    }
    imagep.css("top", (window.innerHeight - h) / 2);
    imagep.css("left", (window.innerWidth - w) / 2);
    image.height(h);
    image.width(w);
    imagep.height(h);
    imagep.width(w);
});