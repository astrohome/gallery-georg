<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<form:form method="POST" action="saveGallery.html" modelAttribute="gallery">

    <table>
        <tr>
            <td><form:label path="id">Id</form:label></td>
            <td><form:input path="id" readonly="true"/></td>
        </tr>
        <tr>
            <td><form:label path="title">Title</form:label></td>
            <td><form:input path="title" readonly="true"/></td>
        </tr>
        <tr>
            <td><form:label path="created">Modified</form:label></td>
            <td><form:input path="created" readonly="true"/></td>
        </tr>
        <tr>
            <td><form:label path="hidden">Private?</form:label></td>
            <td><form:checkbox path="hidden"/></td>
        </tr>
        <tr>
            <td><form:label path="watermark">Watermark?</form:label></td>
            <td><form:checkbox path="watermark"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Save"/>
            </td>
        </tr>
    </table>

</form:form>
</body>
</html>