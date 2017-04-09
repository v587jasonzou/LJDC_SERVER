<%--
  Created by IntelliJ IDEA.
  User: Jason
  Date: 2016/11/9 0009
  Time: 下午 6:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
welcome
<%
    Object res = session.getAttribute("123");
    out.println(res);

    out.println(request.getAttribute("123"));

//    不存在的属性输出的是"属性名"
//    out.println(request.getAttribute("123123"));//输出"123123"
//    out.println(request.getAttribute("123123") == null ? "null" : "not null");//not null

%>

</body>
</html>
