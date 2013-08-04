<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
</head>
<body>
<h1>Hello World!</h1>

<a href="/admin">admin</a> <br/>

<a href="/private">Access by code</a> <br/>

<p>This is the homepage!</p>

<c:forEach items="${list}" var="item">
    <b>${item.key}</b> <br/>
    <c:forEach items="${item.value}" var="gallery">
        <a href="?id=${gallery.id}">${gallery.title}</a> <br/>
    </c:forEach>
</c:forEach>
</body>
</html>
