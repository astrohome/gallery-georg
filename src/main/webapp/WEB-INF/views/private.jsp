<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.private.title"/></jsp:attribute>
    <jsp:attribute name="menu">
        <t:menu/>
    </jsp:attribute>

    <jsp:body>
        <sec:authorize access="isAuthenticated()">
            <form:form action="private" cssClass="navbar-form pull-left" method="POST">
                <input type="text" class="span2" name="code"/>
                <input type="submit" class="btn" value="<spring:message code="submit" />"/>
            </form:form>
        </sec:authorize>
        <t:gallerylist list="${list}" showFilter="false"/>
    </jsp:body>
</t:generic>
