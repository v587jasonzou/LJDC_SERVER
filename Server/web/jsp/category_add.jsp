<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>LJDC_CMS-后台管理中心</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../css/global.css">
    <script type="text/javascript" src="../layui/layui.js"></script>
</head>
<body>
<div class="layui-tab-brief main-tab-container">
    <ul class="layui-tab-title main-tab-title">
        <a href="/web/libs">
            <li>词库列表</li>
        </a>
        <a href="/jsp/category_add.jsp">
            <li class="layui-this">添加词库</li>
        </a>
        <div class="main-tab-item">词库管理</div>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <form class="layui-form" action="/web/libupload.action" method="post" enctype="multipart/form-data">
                <div class="layui-tab layui-tab-card">
                    <ul class="layui-tab-title">
                        <li class="layui-this">基本选项</li>
                    </ul>
                    <div class="layui-tab-content">
                        <div class="layui-tab-item layui-show">
                            <div class="layui-form-item">
                                <label class="layui-form-label">词库名称</label>
                                <div class="layui-input-inline input-custom-width">
                                    <%
                                        String libName = request.getParameter("modifyLib");
                                        if (libName != null) {
                                            libName = new String(libName.getBytes("iso8859-1"), "utf-8");
                                        } else {
                                            libName = "";
                                        }
                                    %>
                                    <input type="text" name="libname" value="<%=libName%>" lay-verify="required"
                                           autocomplete="off"
                                           placeholder="请输入词库名称" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">词库文件</label>
                                <div class="layui-input-inline input-custom-width">
                                    <input type="file" name="file">
                                </div>
                                <div class="layui-form-mid layui-word-aux"></div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="cate_add">立即提交</button>
                            </div>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    layui.use(['element', 'form', 'upload'], function () {
        var element = layui.element()
                , form = layui.form()
                , jq = layui.jquery;
        //文件上传
//        layui.upload({
//            url: ''
//            , elem: '#image'
//            , before: function (input) {
//                loading = layer.load(2, {
//                    shade: [0.2, '#000'] //0.2透明度的白色背景
//                });
//            }
//            , success: function (res) {
//                layer.close(loading);
//                jq('input[name=file_url]').val(res.path);
//                layer.msg(res.msg, {icon: 1, time: 1000});
//            }
//        });

        /* layui.upload({
         url: '/web/libupload.action' //上传接口
         ,before: function(input){
         //返回的参数item，即为当前的input DOM对象
         console.log('文件上传中');
         }
         ,success: function(res){
         console.log('上传完毕');
         }
         });  */


        //监听提交
//        form.on('submit(cate_add)', function (data) {
//            loading = layer.load(2, {
//                shade: [0.2, '#000'] //0.2透明度的白色背景
//            });
//            var param = data.field;
//            jq.post('/web/libupload.action', param, function (data) {
//                if (data.code == 200) {
//                    layer.close(loading);
//                    layer.msg(data.msg, {icon: 1, time: 1000}, function () {
//                        location.reload();//do something
//                    });
//                } else {
//                    layer.close(loading);
//                    layer.msg(data.msg, {icon: 2, anim: 6, time: 1000});
//                }
//            });
//            return false;
//        });

    })
</script>
</body>
</html>

