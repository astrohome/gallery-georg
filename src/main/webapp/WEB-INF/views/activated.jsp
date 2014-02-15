<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.activated.title"/></jsp:attribute>
    <jsp:body>
        <p>

        <h1><spring:message code="page.activated.intro"/></h1></p>
        <p><spring:message code="page.activated.success"/></p>

        <p><a class="btn btn-info" href="/private"><i class="icon-key"></i> <spring:message code="enter-code"/></a></p>

        <a href="/" class="btn"><i class="icon-home"></i> <spring:message code="back-to-index"/></a><br/>
    </jsp:body>
</t:generic>
