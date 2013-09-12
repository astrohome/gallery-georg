<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">Private gallery</jsp:attribute>
    <jsp:attribute name="menu">
        <t:menu/>
    </jsp:attribute>
    <jsp:body>
        <sec:authorize access="isAuthenticated()">
            <form:form action="private.html" cssClass="navbar-form pull-left" method="POST">
                <input type="text" class="span2" name="code"/>
                <input type="submit" class="btn"/>
            </form:form>
        </sec:authorize>
        <t:gallerylist list="${list}" showFilter="false"/>
    </jsp:body>
</t:generic>
