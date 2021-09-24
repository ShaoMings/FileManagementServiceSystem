let xhrOnProgress = function (fun) {
    xhrOnProgress.onprogress = fun; //绑定监听
    //使用闭包实现监听绑
    return function () {
        //通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
        let xhr = $.ajaxSettings.xhr();
        //判断监听函数是否为函数
        if (typeof xhrOnProgress.onprogress !== 'function')
            return xhr;
        //如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
        if (xhrOnProgress.onprogress && xhr.upload) {
            xhr.upload.onprogress = xhrOnProgress.onprogress;
        }
        return xhr;
    }
}



$("#bigFile").on("click", function () {
    layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        title: '断点续传',
        shadeClose: true,
        shade: 0.3,
        area: ['50%', '90vh'],
        content: '<div id="app">\n' +
            '    <div id="drag-drop-area"></div>\n' +
            '</div>',
        success: function (obj, index) {
            let user;
            $.ajax({
                url:"/getUser",
                method:"get",
                async:false,
                success:function (obj) {
                    user = JSON.parse(obj);
                }
            })
            let upload = Uppy.Core().use(Uppy.Dashboard, {
                inline: true,
                target: '#drag-drop-area',
                showProgressDetails:true,
                fileManagerSelectionType: 'both'
            }).use(Uppy.Tus, {
                // endpoint: 'http://192.168.0.106:8080/group1/big/upload/'
                endpoint: 'http://10.60.1.79:8080/group1/big/upload/'
                // endpoint: 'http://1.15.221.117:8085/group1/big/upload/'
            })
            upload.on('complete', (result) => {
                let obj = result.successful[0];
                let filepath = obj.meta.path + "/"+obj.meta.name;
                $.ajax({
                    url:"/file/saveBigFileInfo",
                    method: "post",
                    data:{"filepath":filepath}
                });
            });
            upload.setMeta({
                auth_token: '9ee60e59-cb0f-4578-aaba-29b9fc2919ca',
                path: user.username+$("#path").val(),
                callback_url:'http://10.60.2.0:8081/callback/bigFileInfo'
                // callback_url:'http://1.15.221.117:8081/callback/bigFileInfo'
                // callback_url:'http://192.168.0.106:8081/callback/bigFileInfo'
            });
        }
    });
})



// 文件夹压缩
function generateZipFile(
    zipName, files,
    options = {type: "blob", compression: "DEFLATE",compressionOptions: {
            level: 9
        }}
) {
    return new Promise((resolve, reject) => {
        const zip = new JSZip();
        for (let i = 0; i < files.length; i++) { // 添加目录中包含的文件
            let fileName = files[i].name;
            if (fileName.replace(".", "").length < 1) {
                continue;
            }
            zip.file(files[i].webkitRelativePath, files[i]);
        }
        zip.generateAsync(options).then(function (blob) { // 生成zip文件
            const zipFile = new File([blob], zipName, {
                type: "application/zip",
            });
            resolve(zipFile);
        });
    });
}

function ran() {
    let num = new Date().getTime();
    return num.toString();
}

let count = 0;
let uploadFileList = {}
let tmp;
layui.use(['upload', 'element'], function () {
    let $ = layui.jquery, upload = layui.upload, element = layui.element;

    $('#dirList').on('click',function (event) {
        tmp = uploadListIns;
    })
    let dirLoadIndex;
    $('#dirList').change(async function () {
        let folder = this.files;
        if (folder === null || folder === undefined){
            return;
        }
        dirLoadIndex = layer.load();
        let zipFileName = folder[0].webkitRelativePath.split("/")[0] + ".zip";
        let zipFileList = [ran()+'-dir',await generateZipFile(zipFileName, folder)];
        tmp.saveObj.setFile(zipFileList[0],zipFileList[1]);
        choose(zipFileList);
    });
    $.get('/status/getUserStatus', function (res) {
        let data = res.data;
        if (res.code === 200) {
            layer.msg("当前剩余存储空间: "+data.left+",请注意上传文件大小!");
        } else {
            layer.msg(data.msg);
        }
    });
    //多文件上传
    let demoListView = $('#moreFileList'), uploadListIns = upload.render({
        elem: '#fileList',
        url: '/file/upload/moreFileUpload',
        accept: 'file',
        multiple: true,
        auto: false,
        bindAction: '#fileListAction',
        xhr: xhrOnProgress,
        progress: function (value, obj) {
            //上传进度回调 value进度值
            $("#moreFileList").find('.layui-progress ').each(function () {
                if ($(this).attr("file") === obj.name) {
                    let progressBarName = $(this).attr("lay-filter");
                    let index = $(this).data('count');
                    let tr = demoListView.find('tr#upload-' + index), tds = tr.children();
                    let percent = ((value.loaded / value.total) * 100).toFixed(2);
                    if (percent === 100.00) {
                        percent = 100;
                    } else if (percent > 0.00 && percent < 100.00) {
                        tds.eq(2).html('<span style="color: #FFB800;">上传中...</span>');
                    }
                    element.progress(progressBarName, percent + '%');
                }
            })
        },
        choose: window.choose = function (obj) {
            //将每次选择的文件追加到文件队列
            let files = {}
            if (typeof (obj.pushFile) === 'function') {
                uploadFileList =  obj.pushFile();
            } else {
                if (tmp !==undefined){
                    if (typeof (tmp.saveObj.setFile) === 'function') {
                        if (obj[0].endsWith("dir") && !obj[1].name.endsWith("@dir.zip")){
                            let dirFlagName = obj[1].name.substring(0,obj[1].name.lastIndexOf(".")) + "@dir.zip";
                            obj[1] = new File([obj[1]],dirFlagName,{type:obj[1].type});
                        }
                        tmp.saveObj.setFile(obj[0],obj[1]);
                        uploadFileList[obj[0]] = obj[1];
                        layer.close(dirLoadIndex);
                    }
                }
            }
            //读取本地文件
            demoListView.html(" ");
            files = this.files = uploadFileList;
            for (let key in files) {
                count++;
                let name;
                if (files[key].name.endsWith("@dir.zip")){
                    name = files[key].name.substring(0,files[key].name.lastIndexOf("@"));
                }else {
                    name =files[key].name;
                }
                let tr = $(['<tr id="upload-' + key + '">',
                    '<td>' + name + '</td>',
                    '<td>' + (files[key].size / 1014).toFixed(1) + 'kb</td>',
                    '<td>准备就绪...</td>',
                    '<td>' +
                    '<div file="' + files[key].name + '" class="layui-progress" data-count="' + key + '" lay-showPercent="true" lay-filter="progressBar' + count + '">' +
                    '<div class="layui-progress-bar layui-bg-green" lay-percent="0%"></div>' +
                    '</div>' +
                    '</td>',
                    '<td>',
                    '<button class="layui-btn layui-btn-xs more-file-reload layui-hide">重传</button>',
                    '<button class="layui-btn layui-btn-xs layui-btn-danger more-file-delete">删除</button>',
                    '</td>',
                    '</tr>'].join(''));
                //单个重传
                tr.find('.more-file-reload').on('click', function () {
                    obj.upload(key, files[key]);
                });
                //删除
                tr.find('.more-file-delete').on('click', function () {
                    delete files[key];
                    tr.remove();
                    uploadListIns.config.elem.next()[0].value = '';
                });
                demoListView.append(tr);
                element.render('progress');
            }
        },
        before: function (obj) {
            let scene = $("#scene").val();
            let path = $("#path").val();
            let showUrl = $("#showUrl").val();
            // 传递参数  场景  文件上传路径  服务地址
            this.data = {'scene': scene, 'path': path===""?"":path, 'showUrl': showUrl};
        },
        done: function (res, index, upload) {
            //上传成功
            if (res.code === 200) {
                let tr = demoListView.find('tr#upload-' + index), tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).children(".layui-progress").children(".layui-progress-bar").attr("lay-percent", "100%");
                //清空操作
                tds.eq(4).html('');
                tds.eq(4).html('<a class="layui-btn layui-btn-xs" target="_blank" onclick="showUrl(\'' + res.data.url + '\');">查看链接</a>');
                //删除文件队列已经上传成功的文件
                this.files = uploadFileList;
                return delete this.files[index] && delete uploadFileList[index];
            } else {
                let tr = demoListView.find('tr#upload-' + index), tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;"> '+res.msg +' </span>');
                //显示重传
                tds.eq(4).find('.more-file-reload').removeClass('layui-hide');
            }
            this.error(res,index, upload);
        },
        error: function (res,index, upload) {
            let tr = demoListView.find('tr#upload-' + index), tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">'+res.msg +'</span>');
            //显示重传
            tds.eq(4).find('.more-file-reload').removeClass('layui-hide');
        }
    });
})


function showUrl(url) {
    layer.confirm('点击访问: <br><a href="' + url + '" target="_blank" title="点击访问" class="showUrl-href">' + url + '</a>', {
        btn: ['确定'], title: '查看链接'
    }, function (index, layero) {
        layer.close(index);
    });
}