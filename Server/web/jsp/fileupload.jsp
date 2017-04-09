<%--
  Created by IntelliJ IDEA.
  User: Jason
  Date: 2017/4/5
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
    <script type="text/javascript" src="script/jquery-3.2.0.min.js"></script>
    <script type="text/javascript">

        $(function () {
            $("#button").click(function () {
                var html = $("<input type='file' name='file'>");
                var button = $("<input type='button' name='button' value='删除'><br>");

                $("#body div").append(html).append(button);

                button.click(function () {
                    html.remove();
                    button.remove();
                })
            })
        })

    </script>
</head>
<body>
<form action="/web/upload.action" method="post" enctype="multipart/form-data">

    username: <input type="text" name="username"><br>
    file: <input type="file" name="file"><br>
    file: <input type="file" name="file"><br>
    <input type="button" value="添加" id="button"><br>

    <input type="submit" value="submit">

</form>
</body>
</html>
