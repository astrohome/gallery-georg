<%@tag description="Generic page" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="title" fragment="true" %>

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fi">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
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