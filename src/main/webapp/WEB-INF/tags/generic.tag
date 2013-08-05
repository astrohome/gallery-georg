<%@tag description="Generic page" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="title" fragment="true" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <jsp:invoke fragment="title"/>
    </title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.min.js" />"></script>
    <jsp:invoke fragment="header"/>
</head>
<body>
<jsp:doBody/>
</body>
</html>