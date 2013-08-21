<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">Welcome</jsp:attribute>

    <jsp:attribute name="menu"><t:menu></t:menu></jsp:attribute>

    <jsp:body>
        <a href="/admin">admin</a> <br/>

        <sec:authorize access="not isAuthenticated()">
            <a href="/login.html">Login</a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a href="<c:url value="j_spring_security_logout" />"> Logout</a>
        </sec:authorize>
        <t:gallerylist list="${list}"/>
    </jsp:body>
</t:generic>
