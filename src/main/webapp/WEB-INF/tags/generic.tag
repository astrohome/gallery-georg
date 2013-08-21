<%@tag description="Generic page" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="menu" fragment="true" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <title>
        <jsp:invoke fragment="title"/>
    </title>
    <style>


    </style>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />"/>
    <jsp:invoke fragment="header"/>
</head>
<body>
<jsp:invoke fragment="menu"/>

<div class="container">
    <jsp:doBody/>
</div>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>