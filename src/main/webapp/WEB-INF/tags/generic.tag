<%@tag description="Generic page" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="menu" fragment="true" %>
<%@attribute name="bottom" fragment="true" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>
        <jsp:invoke fragment="title"/>
    </title>

    <%@include file="../views/include/head_system.jsp" %>
    <jsp:invoke fragment="header"/>
</head>
<body>

<jsp:invoke fragment="menu"/>

<div class="container" id="main-container">
    <jsp:doBody/>
</div>

<%@include file="../views/include/bottom_system.jsp" %>
<jsp:invoke fragment="bottom"/>

</body>
</html>