getStatus();

let form;
let switchPeersId = 0;
layui.use('form', function() {
    form = layui.form;
    form.on('select(peers)', function(data){
        switchPeersId = data.value;
    });
})

//文件上传
$('#fileUpload').click(function () {
    layer.open({
        type: 2,
        skin: 'layui-layer-rim', //加上边框
        title: '文件上传',
        shadeClose: true,
        shade: 0.3,
        area: ['90%', '90vh'],
        content: "/file/upload"
    });
})
//文件列表
$('#fileList').click(function () {
    window.parent.toPage("/file");
})

//切换集群
$('#switchPeers').click(function () {
    $.post('/status/getAllPeers', function(result){
        let html = '<form class="layui-form" action="">'+
            '<div class="layui-form-item">'+
            '<div class="layui-input-block" style="margin: 0;padding: 15px;">'+
            '<select name="peers" lay-filter="peers">'+
            '<option value="0"></option>';
        for(let i=0;i<result.data.length;i++){
            html+='<option value="'+result.data[i].id+'">'+result.data[i].name+'</option>';
        }
        html+='</select></div></div></form>';
        layer.open({
            type: 1,
            content: html,
            btn: ['确定', '取消'],
            title: '选择集群',
            area: ['400px', '160px'],
            shadeClose: true,
            maxmin: true,
            yes: function(index, layero){
                if(switchPeersId === 0){
                    layer.msg("请选择要切换的集群");
                }else{
                    $.post('/status/switchPeers',{"id":switchPeersId}, function(result){
                        if(result.code === 200){
                            layer.close(index);
                            layer.msg("切换成功", {icon: 6});
                            setTimeout(function(){
                                window.location.reload();
                            }, 1000);
                        }else{
                            layer.msg(result.msg);
                        }
                    })
                }
            }
        });
        form.render();
    });
})

//修正统计信息
$('#repair_stat').click(function () {
    layer.confirm('确定要修正统计信息吗?该操作会修正最近30天的统计数据(耗时较长)', {icon: 3, title:'提示'}, function(index){
        layer.close(index);
        index = layer.load();
        $.post("/status/repair_stat",function (data) {
            layer.close(index);
            layer.msg(data.msg);
            setTimeout(function(){
                window.location.reload();
            }, 1000);
        })
    });
})

//删除空目录
$('#remove_empty_dir').click(function () {
    layer.confirm('确定要删除空目录吗?该操作耗时较长,请勿重复操作', {icon: 3, title:'提示'}, function(index){
        layer.close(index);
        index = layer.load();
        $.post("/status/remove_empty_dir",function (data) {
            layer.close(index);
            layer.msg(data.msg);
        })
    });
})

//备份元数据
$('#backup').click(function () {
    layer.confirm('确定要备份元数据吗?该操作将会备份最近30天的数据,耗时较长', {icon: 3, title:'提示'}, function(index){
        layer.close(index);
        index = layer.load();
        $.post("/status/backup",function (data) {
            layer.close(index);
            layer.msg(data.msg);
        })
    });
})

//同步失败修复
$('#repair').click(function () {
    layer.confirm('确定进行同步失败修复吗?该操作将会修复同步失败数据,耗时较长', {icon: 3, title:'提示'}, function(index){
        layer.close(index);
        index = layer.load();
        $.post("/status/repair",function (data) {
            layer.close(index);
            layer.msg(data.msg);
        })
    });
})

/**
 * 获取文件统计/状态信息
 */
function getStatus() {
    $.post('/status/getStatus', function (data) {
        let option;
        if (data.code === 200) {
            $("#totalFileCount").text(data.data.totalFileCount);
            $("#totalFileSize").text(data.data.totalFileSize);
            $("#peerFileCount").text(data.data.peerFileCount);
            $("#peerTotalSize").text(data.data.peerTotal);
            $("#diskTotalSize").text(data.data.diskTotalSize);
            $("#diskFreeSize").text(data.data.diskFreeSize);
            $("#peerUsedSize").text(data.data.peerUsed);
            $("#peerLeftSize").text(data.data.peerFree);
            let myChart = echarts.init(document.getElementById('main'));
            myChart.setOption(
                option = {
                    title: {
                        text: '文件统计(30天)',
                        textStyle: {
                            fontSize: '18'
                        }
                    },
                    color: ['#445e75', '#45a7ca', '#98d5ef'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        },
                        formatter: '{a}:{c}MB<br>{a1}:{c1}'
                    },
                    legend: {
                        data: ['文件大小', '文件数量'],
                        top: '20'
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        top: '15%',
                        bottom: '11%',
                        containLabel: true
                    },
                    calculable: true,
                    xAxis: [{
                        type: 'category',
                        data: data.data.dayNumList
                    }],
                    yAxis: [{
                        type: 'value',
                        nameLocation: 'middle',
                        nameGap: 30,
                        nameTextStyle: {
                            fontWeight: 'bold',
                            fontSize: '14',
                        }
                    }],
                    dataZoom: [{
                        textStyle: {
                            color: '#8392A5'
                        },
                        start: 0,
                        end: 100,
                        handleSize: '100%',
                        dataBackground: {
                            areaStyle: {
                                color: '#8392A5'
                            },
                            lineStyle: {
                                opacity: 0.8,
                                color: '#8392A5'
                            }
                        },
                        handleStyle: {
                            color: '#fff',
                            shadowBlur: 3,
                            shadowColor: 'rgba(0, 0, 0, 0.6)',
                            shadowOffsetX: 2,
                            shadowOffsetY: 2
                        }
                    }, {
                        type: 'inside'
                    }],
                    series: [{
                        name: '文件大小',
                        type: 'bar',
                        data: data.data.dayFileSizeList,
                        markPoint: {
                            data: [{
                                type: 'max',
                                name: '最大值'
                            }, {
                                type: 'min',
                                name: '最小值'
                            }]
                        },
                    }, {
                        name: '文件数量',
                        type: 'bar',
                        data: data.data.dayFileCountList,
                        markPoint: {
                            data: [{
                                type: 'max',
                                name: '最大值'
                            }, {
                                type: 'min',
                                name: '最小值'
                            }]
                        },
                    }]
                }
            );
        } else {
            layer.msg(data.msg);
        }
    })
}