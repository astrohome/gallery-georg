<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
Welcome to admin part !
<table border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Modified</th>
        <th>Private?</th>
        <th>Watermarked?</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${listDirectories}" var="directory">

        <tr>
            <td>${directory.id}</td>
            <td>${directory.title}</td>
            <td>${directory.created}</td>
            <td>${directory.hidden}</td>
            <td>${directory.watermark}</td>
            <c:url var="editLink" value="/admin">
                <c:param name="title" value="${directory.title}"/>
            </c:url>
            <td><a href="<c:out value="${editLink}" escapeXml="true" />">Edit</a></td>
        </tr>

    </c:forEach>
</table>
</body>
</html>