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
let count = 0;
layui.use(['upload', 'element'], function () {
    let $ = layui.jquery, upload = layui.upload, element = layui.element;
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
        choose: function (obj) {
            //将每次选择的文件追加到文件队列
            let files = this.files = obj.pushFile();
            //读取本地文件
            demoListView.html(" ");
            for (let key in files) {
                count++;
                let tr = $(['<tr id="upload-' + key + '">',
                    '<td>' + files[key].name + '</td>',
                    '<td>' + (files[key].size / 1014).toFixed(1) + 'kb</td>',
                    '<td>准备就绪...</td>',
                    '<td>' +
                    '<div file="' + files[key].name + '" class="layui-progress" data-count="'+key+'" lay-showPercent="true" lay-filter="progressBar' + count + '">' +
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
            ;
        }, before: function (obj) {
            let scene = $("#scene").val();
            let path = $("#path").val();
            let showUrl = $("#showUrl").val();
            // 传递参数  场景  文件上传路径  服务地址
            this.data = {'scene': scene, 'path': path, 'showUrl': showUrl};
        }, done: function (res, index, upload) {
            //上传成功
            if (res.code === 200) {
                let tr = demoListView.find('tr#upload-' + index), tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).children(".layui-progress").children(".layui-progress-bar").attr("lay-percent", "100%");
                //清空操作
                tds.eq(4).html('');
                tds.eq(4).html('<a class="layui-btn layui-btn-xs" target="_blank" onclick="showUrl(\'' + res.data.url + '\');">查看链接</a>');
                //删除文件队列已经上传成功的文件
                return delete this.files[index];
            } else {
                let tr = demoListView.find('tr#upload-' + index), tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                //显示重传
                tds.eq(4).find('.more-file-reload').removeClass('layui-hide');
            }
            this.error(index, upload);
        }, error: function (index, upload) {
            let tr = demoListView.find('tr#upload-' + index), tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
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