<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">Activating new user</jsp:attribute>
    <jsp:body>
        <spring:message code="page.activation.success"/>

        <a class="btn" href="/private"></a>

        <a href="/"><spring:message code="page.activation.back-to-index"/></a>
    </jsp:body>
</t:generic>
