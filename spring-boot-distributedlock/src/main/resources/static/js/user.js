var $;
layui.use(['form', 'layer', 'jquery', 'table', 'laydate'], function () {
    //定义参数
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        table = layui.table,
        laydate = layui.laydate;

    //使用layui日期组件
    laydate.render({
        type: 'datetime',
        // lang: 'en',
        theme: 'grid',
        calendar: true,
        elem: '#startTime'
    });
    laydate.render({
        type: 'datetime',
        lang: 'en',
        // theme: 'grid',
        // calendar: true,
        elem: '#endTime'
    });

    //方法级渲染的重载 https://www.layui.com/doc/modules/table.html#reload
    //所获得的tableIns即为当前容器的实例
    var tableIns = table.render({
        //table的id
        elem: '#dataTable',
        //开启分页
        page: true,
        even: true,
        //数据接口
        url: s.rootUrl + '/user/listPage',
        //表头
        cols: [[{field: 'name', title: '姓名', width: '10%', sort: true, fixed: 'left', align: 'center'}
            , {field: 'sex', title: '性别', width: '10%', align: 'center'}
            , {field: 'age', title: '年龄', width: '10%', align: 'center'}
            , {field: 'mobile', title: '号码', width: '20%', align: 'center'}
            , {
                field: 'createTime',
                title: '创建时间',
                width: '20%',
                sort: true,
                align: 'center',
                templet: '<div>{{ Format(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>'
            }
            , {
                field: 'updateTime',
                title: '更新时间',
                width: '20%',
                sort: true,
                align: 'center',
                templet: '<div>{{ Format(d.updateTime,"yyyy-MM-dd hh:mm:ss")}}</div>'
            }
            , {title: '操作', width: '10%', align: 'center', templet: '#operationTemplet'}
        ]]
    });

    //搜索
    $("body").on("click", ".searchBtn", function () {
        var name = $.trim($("#name").val());
        var sex = $.trim($("#sex").val());
        var startTime = $.trim($("#startTime").val());
        var endTime = $.trim($("#endTime").val());
        tableIns.reload({
            //请求参数
            where: {
                name: name
                , sex: sex
                , startTime: startTime
                , endTime: endTime
            },//重新从第1页开始
            page: {
                curr: 1
            }
        });
    })

    //新增
    $("body").on("click", ".add", function () {
        layui.layer.open({
            title: "新增菜单信息",//标题
            type: 2,//基本层类型
            area: ['500px', '350px'], //宽高
            content: s.rootUrl + "/user/add" //内容
        });
    })

    //编辑
    $("body").on("click", ".edit", function () {
        var id = $(this).attr('data-id');
        layui.layer.open({
            title: "编辑菜单信息",//标题
            type: 2,//基本层类型
            area: ['500px', '350px'], //宽高
            content: s.rootUrl + "/user/edit/" + id //内容
        });
    })

    //提交
    form.on('submit(saveSubmit)', function (data) {
        $.ajax({
            type: "post",
            url: s.rootUrl + "/user/save",
            data: data.field,
            success:function (result) {
                if (result["code"] == s.successCode){
                    window.parent.location.reload();
                    layer.closeAll();
                }else{
                    layer.alert(result["msg"]);
                    return false;
                }
            }
        });
        return false;
    });

});

// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
//Format("2016-10-04 8:9:4.423","yyyy-MM-dd hh:mm:ss.S") ==> 2016-10-04 08:09:04.423
// Format("1507353913000","yyyy-M-d h:m:s.S")      ==> 2017-10-7 13:25:13.0
function Format(datetime, fmt) {
    if (parseInt(datetime) == datetime) {
        if (datetime.length == 10) {
            datetime = parseInt(datetime) * 1000;
        } else if (datetime.length == 13) {
            datetime = parseInt(datetime);
        }
    }
    datetime = new Date(datetime);
    var o = {
        "M+": datetime.getMonth() + 1,                 //月份
        "d+": datetime.getDate(),                    //日
        "h+": datetime.getHours(),                   //小时
        "m+": datetime.getMinutes(),                 //分
        "s+": datetime.getSeconds(),                 //秒
        "q+": Math.floor((datetime.getMonth() + 3) / 3), //季度
        "S": datetime.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}