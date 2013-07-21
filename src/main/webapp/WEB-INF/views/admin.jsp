<%@ page import="java.net.URLEncoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />"/>
</head>
<body>
Welcome to admin part !

<c:if test="${success}">
    <div class="success" style="clear: both;">Successfully saved!</div>
</c:if>
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
            <c:set var="title" value="${directory.title}"/>
            <c:url var="editLink" value="/admin">
                <c:param name="title" value='<%=URLEncoder.encode((String)pageContext.getAttribute("title"))%>'/>
            </c:url>

            <td><a href="<c:out value="${editLink}" escapeXml="false" />">Edit</a></td>
        </tr>

    </c:forEach>
</table>
</body>
</html>