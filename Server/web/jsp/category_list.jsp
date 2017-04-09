<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ljdc.pojo.Libs" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>LJDC_CMS-后台管理中心</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../css/global.css">
    <script type="text/javascript" src="../layui/layui.js"></script>
</head>
<body>
<div class="layui-tab layui-tab-brief main-tab-container">
    <ul class="layui-tab-title main-tab-title">
        <a href="/web/libs">
            <li class="layui-this">词库列表</li>
        </a>
        <a href="/jsp/category_add.jsp">
            <li>添加词库</li>
        </a>
        <div class="main-tab-item">词库管理</div>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <form class="layui-form">

                <table class="list-table">
                    <thead>
                    <tr>
                        <!--<th style="width:40px">排序</th>-->
                        <th>ID</th>
                        <th>词库名称</th>
                        <th>词汇量</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="libList">
                    <%
                        List<Libs> libs = null;
                        Object data = request.getAttribute("libs");
                        if(!data.toString().equals("libs")){
                            libs = (List<Libs>) data;
                            System.out.println(libs.size());
                        }
                    %>
                    <%--创建表格行--%>
                    <%
                        for (Libs lib:libs) {
                     %>
                        <tr id="<%=lib.getLibsId()%>" >
                            <td><%=lib.getLibsId()%></td>
                            <td>
                                <%=lib.getLibName()%>
                            </td>
                            <td><%=lib.getTotalNum()%></td>
                            <td style="text-align: center;">
                                <a href="../jsp/category_add.jsp?modifyLib=<%=lib.getLibName()%>" class="layui-btn layui-btn-small" title="编辑"><i
                                        class="layui-icon"></i></a>
                                <a class="layui-btn layui-btn-small layui-btn-danger del_btn" category-id=<%=lib.getLibsId()%> title="删除"
                                   category-name=<%=lib.getLibName()%>><i class="layui-icon"></i></a>
                            </td>
                         </tr>
                    <%
                        }
                    %>
                    </tbody>
                    <thead>
                    <tr>
                        <th colspan="5">
                            <!--<button class="layui-btn layui-btn-small" lay-submit="" lay-filter="sort">排序</button>-->
                        </th>
                    </tr>
                    </thead>
                </table>

            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['element', 'layer', 'form'], function () {
        var element = layui.element()
                , jq = layui.jquery
                , form = layui.form()
                , laypage = layui.laypage;

        //图片预览
        jq('.list-table td .thumb').hover(function () {
            jq(this).append('<img class="thumb-show" src="' + jq(this).attr('thumb') + '" >');
        }, function () {
            jq(this).find('img').remove();
        });
        //链接预览
        jq('.list-table td .link').hover(function () {
            var link = jq(this).attr('href');
            layer.tips(link, this, {
                tips: [2, '#009688'],
                time: false
            });
        }, function () {
            layer.closeAll('tips');
        });

        //监听提交
        form.on('submit(sort)', function (data) {
            loading = layer.load(2, {
                shade: [0.2, '#000'] //0.2透明度的白色背景
            });
            var param = data.field;
            jq.post('', param, function (data) {
                if (data.code == 200) {
                    layer.close(loading);
                    layer.msg(data.msg, {icon: 1, time: 1000}, function () {
                        location.reload();//do something
                    });
                } else {
                    layer.close(loading);
                    layer.msg(data.msg, {icon: 2, anim: 6, time: 1000});
                }
            });
            return false;
        });

        //ajax删除 CODE BY JASONZOU
        jq('.del_btn').click(function () {
            var name = jq(this).attr('category-name');
            var id = jq(this).attr('category-id');
            layer.confirm('确定删除【' + name + '】?', function (index) {
                jq.post('/web/dellib', {'id': id},function (data) {
                    layer.close(index);
                    location.reload();//重新请求该页面
                });
            });
        });

    })
</script>
</body>
</html>