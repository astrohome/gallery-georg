<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: felix
  Date: 7/21/13
  Time: 12:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<a href="/">Back</a> <br/>
<c:forEach items="${listImages}" var="image">
    <img src="/getImage/${gallery.encodedTitle}/${image}" width="150"/>
</c:forEach>
</body>
</html>