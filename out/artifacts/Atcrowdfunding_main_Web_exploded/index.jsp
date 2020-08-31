<%--
  Created by IntelliJ IDEA.
  User: 78749
  Date: 2020/8/28
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
index
<jsp:forward page="/${APP_PATH}/index.htm"></jsp:forward>
<a href="${pageContext.request.contextPath }/test.do">test</a>
</body>
</html>
