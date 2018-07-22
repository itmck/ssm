<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>商品列表-后台管理系统-Admin 1.0</title>
    <meta name="Description" content="基于layUI数据表格操作"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/weadmin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui/css/layui.css" media="all">
    <%--<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/itemlist.js"--%>
    <%--charset="utf-8"></script>--%>

    <!--<script type="text/javascript" src="../../static/js/admin.js"></script>-->
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .layui-form-switch {
            width: 55px;
        }

        .layui-form-switch em {
            width: 40px;
        }

        .layui-form-onswitch i {
            left: 45px;
        }

        body {
            overflow-y: scroll;
        }
    </style>
</head>

<body>
<div class="weadmin-nav">
			<span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">商品管理</a>
        <a>
          <cite>商品列表</cite></a>
      </span>
    <a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">&#x1002;</i></a>
</div>
<div class="weadmin-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 we-search" onsubmit="return false;">
            商品搜索：
            <div class="layui-inline">
                <input type="text" id="title" name="title" placeholder="请输入商品名称关键字" autocomplete="off"
                       class="layui-input">
            </div>
            <button class="layui-btn" lay-submit="" lay-filter="search" data-type="reload"><i class="layui-icon">&#xe615;</i>
            </button>
        </form>
    </div>
    <div class="weadmin-block demoTable">
        <button class="layui-btn layui-btn-danger" data-type="getCheckData"><i class="layui-icon">&#xe640;</i>批量删除
        </button>
        <button class="layui-btn" onclick="WeAdminShow('添加商品','./add',600,500)"><i class="layui-icon">&#xe61f;</i>添加
        </button>
    </div>

    <%--这个是动态的表格--%>
    <table class="layui-hide" id="articleList"></table>


    <script type="text/html" id="operateTpl">
        <a title="编辑" onclick="WeAdminEdit('编辑','./edit', 2, 600, 400)" href="javascript:;">
            <i class="layui-icon">&#xe642;</i>
        </a>
        <a title="删除" href="javascript:;">
            <i class="layui-icon">&#xe640;</i>
        </a>
    </script>
    <%--<script type="text/html" id="shelfTpl">--%>
    <%--<form class="layui-form">--%>
    <%--<input type="checkbox" name="itemstatus" lay-filter="itemstatus" lay-skin="switch" lay-text="正常|下架" {{1==d.status?'checked':''}}/>--%>
    <%--</form>--%>
    <%--</script>--%>
    <script type="text/html" id="myTpl">
        <div class="layui-form">
            <input type="checkbox" name="itemStatus" id="itemStatus" lay-skin="switch" lay-text="正常|下架"
                   {{1==d.status?'checked':''}}/>
        </div>
    </script>

</div>
<script type="text/javascript">

    layui.extend({
        admin: '{/}../../static/js/admin'
    });
    layui.use(['table', 'admin', 'jquery', 'layer'], function () {

        var table = layui.table,
            $ = layui.jquery,
            layer = layui.layer;
        table.render({
            //表格属性
            //要渲染的容器
            page: true,
            elem: '#articleList',
            //异步请求
            url: '../../items',
            //列
            cols: [[
                //field title 列属性
                {type: 'checkbox'},
                {field: 'id', title: '商品编号'},
                {field: 'title', title: '商品名称'},
                {field: 'sellPoint', title: '商品卖点'},
                {field: 'catName', title: '分类名称'},
                {field: 'statusName', title: '商品状态'},
            ]],

            //这是前台修改商品状态的方式,由于在后台已经写了 ,在此注释掉即可
            // done:function(res,curr,count){
            //     console.log($("[data-field='status']").children());
            //     $("[data-field='status']").children().each(function(){
            //         if($(this).text() == '1'){
            //             $(this).text('正常');
            //         }
            //         if($(this).text() == '2'){
            //             $(this).text('下架');
            //         }
            //         if($(this).text() == '3'){
            //             $(this).text('删除');
            //         }
            //     });
            //
            // }
        });

        //搜索操作 先获取搜索按钮事件
        $('.we-search .layui-btn').on('click',function () {

             var type = $(this).data('type');
            // console.log(type);
            active[type]?active[type].call(this):'';

        });

        //批量删除的点击事件
        $(".demoTable .layui-btn-danger ").click(function () {

            //获取按钮的data-type属性
            var type = $(this).data('type');     //getCheckData
            // var type2 =$(this).attr('data-type');   // 方式2:getCheckData
            //  console.log(type2);
            //三目运算符,如果active[type]为真时,那么久调用它,负责什么都不做
            active[type] ? active[type].call(this) : '';

        });
        var active = {

            reload:function(){

               // alert(5);
                //通过id选择器获取搜索框中的内容
                //val() 单选按钮 复选按钮 文本框
                //text() 文本
                //html() 标签
                var title = $('#title').val();
                //判断内容是否为空
                if($.trim(title).length>0){
                    //文本框输入了内容,表格需要重新加载.另外发送一个item请求
                    table.reload('articleList',{

                        page:{curr:1},
                        //第一个title作为表单数据传出去的key
                        //第二个参数就是js定义的变量(就是我们获取的文本框值)
                        where:{title:title}

                    });

                }else{

                    layer.msg('亲,您未输入任何东西',{icom:1});
                }



            },

            getCheckData: function () {

                //判断是否有选中行
                //获取选中的行
                var data = table.checkStatus('articleList').data;
                //判断长度
                if (data.length > 0) {

                    //console.log(data);
                   // 确认框,至少选中一行数据
                   // 创建一个数组存放选中行的id
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        //遍历选中行,将其id存入数组中
                        ids.push(data[i].id);
                    }
                    //将数组中的id异步提交给后台
                    $.post(
                        '../../item/batch', //url
                        {'ids[]': ids},
                        function (data) {

                            //至少删除一条记录
                            if (data > 0) {
                                //停留在原来界面并刷新
                                $('.layui-laypage-btn').click();
                                layer.msg("恭喜您,删除成功", {icon: 1});
                            }
                        }
                    )


                } else {

                    layer.msg('请至少选中一行数据!!!!', {icon: 2});

                }


            }
        }

    });

</script>
</body>
