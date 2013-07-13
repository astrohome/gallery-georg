<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: felix
  Date: 7/13/13
  Time: 7:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
Welcome to admin part !

<c:forEach items="${listDirectories}" var="directory">
    <a href="/admin?dir=${directory.name}">${directory.name}</a> <br/>
</c:forEach>

Create new gallery?
</body>
</html>