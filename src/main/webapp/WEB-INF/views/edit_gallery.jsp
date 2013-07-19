<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
            <td><form:label path="hidden">Public?</form:label></td>
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