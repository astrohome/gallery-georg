<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.activation-required.title"/></jsp:attribute>
    <jsp:body>
        <spring:message code="page.activation.info" arguments="${email}"/>

        <a href="/"><spring:message code="back-to-index"/> </a>
    </jsp:body>
</t:generic>
