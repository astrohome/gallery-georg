<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">Register new user</jsp:attribute>
    <jsp:body>
        <spring:message code="page.activation.info"/>

        <a href="/"><spring:message code="page.activation.back-to-index"/> </a>
    </jsp:body>
</t:generic>
