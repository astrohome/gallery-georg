<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.min.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />"/>
</head>
<body>

<script>
    $(document).ready(function () {

        $("#generate").click(function () {
                    $("#password").val($.password(6));
                }
        );

        $("#hidden1").change(function () {
                    if ($(this).is(":checked")) {
                        $("#password_generation").removeClass('hidden');
                        $("#password").val(null);
                    }
                    else {
                        $("#password_generation").addClass('hidden');
                    }
                }
        );
    });

    $.extend({
        password: function (length, special) {
            var iteration = 0;
            var password = "";
            var randomNumber;
            if (special == undefined) {
                var special = false;
            }
            while (iteration < length) {
                randomNumber = (Math.floor((Math.random() * 100)) % 94) + 33;
                if (!special) {
                    if ((randomNumber >= 33) && (randomNumber <= 47)) {
                        continue;
                    }
                    if ((randomNumber >= 58) && (randomNumber <= 64)) {
                        continue;
                    }
                    if ((randomNumber >= 91) && (randomNumber <= 96)) {
                        continue;
                    }
                    if ((randomNumber >= 123) && (randomNumber <= 126)) {
                        continue;
                    }
                }
                iteration++;
                password += String.fromCharCode(randomNumber);
            }
            return password;
        }
    });
</script>
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
        <tr id="password_generation" class="hidden">
            <td><form:label path="password">Password</form:label></td>
            <td><form:input path="password"/> <input type="button" id="generate" value="Generate new"></td>
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