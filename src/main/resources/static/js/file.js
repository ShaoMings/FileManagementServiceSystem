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
    if (dir_path !== undefined && file_path === undefined) {
        current_path += dir_path;
    }
    if (file_path !== undefined && dir_path === undefined) {
        current_path += file_path;
    }
    if (dir_path === undefined && file_path === undefined) {
        current_path = "files" + $('#path-side').children("a:last-child").data("path");
    }
    if (dir_path !== undefined && file_path !== undefined) {
        current_path += dir_path;
    }

    layer.prompt({title: '输入文件夹名称', formType: 0}, function (name, index) {
        $.post('/file/mkdir', {"path": current_path + "/" + name}, function (res) {
            if (res.code === 200) {
                openDir(current_path.replace("files/", ""));
                layer.msg("创建成功");
            } else {
                layer.msg(res.msg);
            }
        });
        layer.close(index);
    });
});

//  从url导入文件监听
$('#import').click(function () {
    let dir_path = $('#dir-path').data('path');
    let file_path = $('#file-path').data('path');
    let current_path = "files/";
    if (dir_path !== undefined && file_path === undefined) {
        current_path += dir_path;
    }
    if (file_path !== undefined && dir_path === undefined) {
        current_path += file_path;
    }
    if (dir_path === undefined && file_path === undefined) {
        current_path = "files" + $('#path-side').children("a:last-child").data("path");
    }
    if (dir_path !== undefined && file_path !== undefined) {
        current_path += dir_path;
    }

    layer.prompt({
        title: '输入异源链接  (git链接或者下载链接)',
        formType: 0,
    }, function (url, index) {
        if (url === "" || url.replace(" ", "").length === 0) {
            layer.close(index);
            layer.msg("链接不能为空!");
        } else {
            layer.msg("后台正在为您导入中，请耐心稍等。(提示: 导入时长与仓库或文件大小有关)");
            let loadIndex = layer.load();
            $.post('/file/import', {"path": current_path, "link": url}, function (res) {
                if (res.code === 200) {
                    layer.close(loadIndex);
                    openDir(current_path.replace("files/", ""));
                    layer.msg("导入成功");
                } else {
                    layer.close(loadIndex);
                    layer.msg(res.msg);
                }
            });
            layer.close(index);
        }
    });
})

/*监听重命名操作*/
$('#file-result').on('click', '.rename-file-btn', function () {
    let dir_path = $('#dir-path').data('path');
    let file_path = $('#file-path').data('path');
    let md5 = $(this).data('md5');
    if (md5 === undefined) {
        md5 = "";
    }
    let current_path = "files/";
    if (dir_path !== undefined && file_path === undefined) {
        current_path += dir_path;
    }
    if (file_path !== undefined && dir_path === undefined) {
        current_path += file_path;
    }
    if (dir_path === undefined && file_path === undefined) {
        current_path = "files" + $('#path-side').children("a:last-child").data("path");
    }
    if (dir_path !== undefined && file_path !== undefined) {
        current_path += dir_path;
    }
    let oldName = $(this).data('name');
    layer.prompt({
        formType: 0,
        title: '输入新的名称',
        value: oldName
    }, function (name, index) {
        $(".layui-layer-input").val(oldName);
        $.post('/file/rename', {"path": current_path, "oldName": oldName, "newName": name, "md5": md5}, function (res) {
            if (res.code === 200) {
                openDir(current_path.replace("files/", ""));
                layer.msg("重命名成功");
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
});

 //公开文件监听
$('#file-result').on('click','.open-btn',function () {
    let obj = $(this);
    let path = $(this).data('path');
    let filename = $(this).data('name');
    let filesize = $(this).data('size');
    let open = $(this).data('open');

    if (open === 0){
        $.ajax({
            url:"/share/open",
            method: "post",
            data:{"filename":filename,"path":path,"size":filesize},
            success:function (res) {
                if (res.code === 200){
                    layer.msg("公开成功!");
                    obj.data("open",1);
                    obj.text("私有");
                }else {
                    layer.msg(res.msg);
                }
            }
        })
    }else {
        $.ajax({
            url:"/share/private",
            method: "post",
            data:{"filename":filename,"path":path},
            success:function (res) {
                if (res.code === 200){
                    layer.msg("私有成功!");
                    obj.data("open",0);
                    obj.text("公开");
                }else {
                    layer.msg(res.msg);
                }
            }
        })
    }

})

// 监听文件分享
$('#file-result').on('click', '.share-btn', function () {
    let path = $(this).data('path');
    let filename = $(this).data('name');
    let html = '<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">' +
        '  <legend style="font-size: 15px;margin-left: 9px">分享文件:' + filename + '</legend>' +
        '</fieldset>';
    let select = '<label class="layui-form-label" style="text-align: center;">分享有效期:</label>' +
        '<div class="layui-input-inline">\n' +
        '<select id="share-time">' +
        '<option value="1">1天</option>' +
        '<option value="7">7天</option>' +
        '<option value="30">30天</option>' +
        '<option value="99999">永久有效</option>' +
        '</select>' +
        '</div>';
    let confirm_btn = '<div class="layui-form-item"><div class="layui-input-block">\n' +
        '      <button type="button" class="layui-btn" id="share-btn" style="float: right;margin-right: 10px;margin-top: 150px;">创建链接</button>\n' +
        '    </div></div>'
    let form = layui.form;
    layer.open({
        type: 1,
        skin: 'layui-layer-demo', //样式类名
        title: false,
        closeBtn: 0,
        anim: 2,
        shadeClose: true, //开启遮罩关闭
        area: ['510px', '320px'],
        content: '<div class="share-file"><form class="layui-form" style="align-self: center;"><div class="layui-form-item">' + html + select + '</div>' + confirm_btn + '</form></div>',
        success: function (name, index) {
            form.render('select');
            $('#share-btn').on('click', function () {
                if (checkSystemRightTime()) {
                    let shareTime = $('#share-time option:selected').val();
                    let data = {"path": path, "filename": filename, "days": shareTime};
                    $.post("/file/share", data, function (res) {
                        if (res.code === 200) {
                            layer.close(index);
                            let loadIndex = layer.load();
                            let html = '<div class="file-details-box">' +
                                '<ul>' +
                                '<li><span>有效期至:&nbsp;</span>' + res.data.until + '</li>' +
                                '<li><span>访问链接:</span></br><a target="_blank" href=' + res.data.link  + '>' + res.data.link + '</a></li>' +
                                '<li><span>提取码:&nbsp;</span>' + res.data.check + '</li>' +
                                '<li><span>二维码:</span></br><div id="qrcode" style="width:150px; height:150px; margin:0 auto;"></div></li>' +
                                '</ul>';

                            layer.open({
                                type: 1,
                                skin: 'layui-layer-demo', //样式类名
                                title: false,
                                closeBtn: 0,
                                anim: 2,
                                shadeClose: true, //开启遮罩关闭
                                area: ['710px', '520px'],
                                content: html,
                                success: function () {
                                    let qrcode = new QRCode(document.getElementById("qrcode"), {
                                        width: 150,
                                        height: 150
                                    });
                                    layer.close(loadIndex);
                                    qrcode.makeCode(res.data.link);
                                }
                            })
                        }
                    })
                } else {
                    layer.msg("本地时间与网络标准时间相差过大,请检查!");
                }
            });
        }
    });
});

function checkSystemRightTime() {
    let networkTime;
    $.ajax({
        type: 'GET',
        dataType: 'json',
        async: false,
        url: 'https://quan.suning.com/getSysTime.do',
        success: function (data) {
            networkTime = data.sysTime2;
        }
    });
    Date.prototype.Format = function (fmt) {
        let o = {
            "M+": this.getMonth() + 1, // 月份
            "d+": this.getDate(), // 日
            "h+": this.getHours(), // 小时
            "m+": this.getMinutes(), // 分
            "s+": this.getSeconds(), // 秒
            "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
            "S": this.getMilliseconds() // 毫秒
        };
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (let k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
    let localTime = (new Date()).Format("yyyy-MM-dd hh:mm:ss");
    return Math.abs(Date.parse(networkTime) - Date.parse(localTime)) < 18000;
}


/*监听格式转换按钮 */
$('#file-result').on('click', '.converter-btn', function () {

    let dir_path = $('#dir-path').data('path');
    let file_path = $('#file-path').data('path');
    let current_path = "files/";
    if (dir_path !== undefined && file_path === undefined) {
        current_path += dir_path;
    }
    if (file_path !== undefined && dir_path === undefined) {
        current_path += file_path;
    }
    if (dir_path === undefined && file_path === undefined) {
        current_path = "files" + $('#path-side').children("a:last-child").data("path");
    }
    if (dir_path !== undefined && file_path !== undefined) {
        current_path += dir_path;
    }
    let oldName = $(this).data('name');
    let suffix = oldName.substring(oldName.lastIndexOf(".") + 1);
    let html;
    let select;
    // 支持格式转mp3
    let audio_types = ['m4a', 'wav'];
    let picture_type = ['jpg', 'png']
    // 支持格式转pdf
    let document_types = ['txt', 'ppt', 'pptx', 'docx', 'xlsx'];
    let form = layui.form;
    if (audio_types.indexOf(suffix) !== -1) {
        html = '<div class="layui-input-inline">\n' +
            '        <select id="before">\n' +
            '          <option value=' + suffix + '>' + suffix + '</option>\n' +
            '        </select>\n' +
            '</div>';
        select = '<label class="layui-form-label" style="text-align: center;">转换为</label>' +
            '<div class="layui-input-inline">\n' +
            '<select id="after">' +
            '<option value="mp3">mp3</option>' +
            '</select>' +
            '</div>';
    } else if (picture_type.indexOf(suffix) !== -1) {
        removeItem(picture_type, suffix);
        html = '<div class="layui-input-inline">\n' +
            '      <select id="before">\n' +
            '        <option value=' + suffix + '>' + suffix + '</option>\n' +
            '      </select>\n' +
            '    </div>';
        select = '<label class="layui-form-label" style="text-align: center;">转换为</label>' +
            '<div class="layui-input-inline">\n' +
            '<select class="sel" id="after">' +
            '<option value=' + picture_type[0] + '>' + picture_type[0] + '</option>' +
            '</select>' +
            '</div>';
    } else if (document_types.indexOf(suffix) !== -1) {
        html = '<div class="layui-input-inline">\n' +
            '        <select id="before">\n' +
            '          <option value=' + suffix + '>' + suffix + '</option>\n' +
            '        </select>\n' +
            '</div>';
        select = '<label class="layui-form-label" style="text-align: center;">转换为</label>' +
            '<div class="layui-input-inline">\n' +
            '<select id="after">' +
            '<option value="pdf">pdf</option>' +
            '</select>' +
            '</div>';
    } else {
        if (suffix === "mp3") {
            layer.msg("目前仅m4a,wav格式转mp3 !");
            return;
        }
        layer.msg("未知类型!");
        return;
    }
    let confirm_btn = '<div class="layui-form-item"><div class="layui-input-block">\n' +
        '      <button type="button" class="layui-btn" id="converter" style="float: right;margin-right: 10px;margin-top: 20px;">立即提交</button>\n' +
        '    </div></div>'
    layer.open({
        type: 1,
        skin: 'layui-layer-demo', //样式类名
        title: false,
        closeBtn: 0,
        anim: 2,
        shadeClose: true, //开启遮罩关闭
        area: ['510px', '200px'],
        content: '<div class="format"><form class="layui-form" style="align-self: center;"><div class="layui-form-item">' + html + select + '</div>' + confirm_btn + '</form></div>',
        success: function (name, index) {
            form.render('select');
            let before = $('#before option:selected').val();
            $('#before').on('change', function () {
                before = $('#before option:selected').val();
            })
            let after = $('#after option:selected').val();
            $('#after').on('change', function () {
                after = $('#after option:selected').val();
            })
            $('#converter').click(function () {
                layer.close(index);
                layer.msg("格式转换花费的时间较长,请耐心等待!");
                let load_index = layer.load();
                let path = current_path.substring(current_path.indexOf("/") + 1)

                // post: path filename src dest
                if (audio_types.indexOf(before) !== -1) {
                    $.post("/file/audioConverter", {
                        "path": path,
                        "filename": oldName,
                        "srcSuffix": before,
                        "destSuffix": after
                    }, function (res) {
                        if (res.code === 200) {
                            layer.close(load_index);
                            layer.msg("转换成功");
                            openDir(path)
                        }
                    });
                } else if (picture_type.indexOf(after) !== -1) {
                    $.post("/file/picConverter", {
                        "path": path,
                        "filename": oldName,
                        "srcSuffix": before,
                        "destSuffix": after
                    }, function (res) {
                        if (res.code === 200) {
                            layer.close(load_index);
                            layer.msg("转换成功");
                            openDir(path)
                        }
                    });
                } else if (document_types.indexOf(before) !== -1) {
                    $.post("/file/documentConverter", {
                        "path": path,
                        "filename": oldName,
                        "srcSuffix": before,
                        "destSuffix": after
                    }, function (res) {
                        if (res.code === 200) {
                            layer.close(load_index);
                            layer.msg("转换成功");
                            openDir(path)
                        }
                    });
                }

            });
        }

    });
})

function removeItem(arr, e) {
    arr.forEach(function (item, index, arr) {
        if (item === e) {
            arr.splice(index, 1);
        }
    });
}

/*获取所有一级目录及文件*/
function getParentFile() {
    let index = layer.load();
    $.post('/file/getParentFile', function (result) {
        if (result.code === 200) {
            let data = result;
            // console.log(data);
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
    // console.log(dir)
    let index = layer.load();
    let suff = dir.substring(0, 1);
    if (suff === "/") {
        dir = dir.substring(1);
    }
    if (dir === "files") {
        dir = "";
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



/*监听页面上传按钮*/
$("#upload").click(function () {
    let dir = $('#path-side').children("a:last-child").data("path");
    let path = "";
    if (dir !== "") {
        path = path + dir;
    } else {
        path = ""
    }
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
            body.contents().find("#path").val(path);
        },
        cancel: function () {
            openDir(path);
        }
    });
})


/*监听详情按钮*/
$("#file-result").on("click", ".details-btn", function () {
    let md5 = $(this).data("md5");
    let path = $(this).data("path");
    let name = $(this).data("name");
    $.post('/file/details', {"md5": md5}, function (result) {
        if (undefined === result.data.name) {
            let filePath;
            if (path === ""){
                filePath = name;
            }else {
                filePath = path+"/"+name;
            }
            $.ajax({
                url:"/file/getBigFileInfo",
                method:"post",
                data:{"filePath":filePath},
                async:false,
                success:function (res) {
                    if (res.code === 200){
                        result = res;
                    }else {
                        layer.msg(res.msg);
                    }
                }
            });
        }
        let html = '<div class="file-details-box">' +
            '<ul>' +
            '<li><span>名称:&nbsp;</span>' + result.data.name + '</li>' +
            '<li><span>MD5:&nbsp;</span>' + result.data.md5 + '</li>' +
            '<li><span>大小:&nbsp;</span>' + result.data.size + '</li>' +
            '<li><span>日期:&nbsp;</span>' + result.data.timeStamp + '</li>' +
            '</ul>' +
            '</div>';
        layer.open({
            type: 1,
            title: '文件信息',
            shadeClose: true,
            shade: 0.3,
            area: ['500px', '280px'],
            content: html
        });
    })
})

/*监听文件夹删除按钮*/
$("#file-result").on("click", ".delete-dir-btn", function () {
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
    let path = $(this).data("path");
    let $this = $(this);
    layer.confirm('确定要删除' + name + '吗?', {icon: 3, title: '提示'}, function (index) {
        $.post('/file/deleteFile', {"path": path + "/" + name}, function (result) {
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
    let username;
    let token;
    let address;
    $.ajax({
        url: "/peers/address",
        async: false,
        success: function (res) {
            address = res;
        }
    });

    $.ajax({
        url: "/user/username",
        async: false,
        success: function (res) {
            username = res;
        }
    })
    let source;
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
        data: {"filePath": username + "/"+(path === "" ? name : path + "/" + name)},
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
        $.getScript("/static/js/easyplayer.min.js",function () {
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
        })
    } else {
        layer.close(loadIndex);
        if (doc_types.indexOf(kit.getFileType(suffix)) !== -1) {
            layer.msg("该文档格式需转为PDF才能在线预览!");
            return;
        }
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