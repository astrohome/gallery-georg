<%@include file="../include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.404.title"/></jsp:attribute>
    <jsp:body>
        <div class="hero-unit center">
            <h1><spring:message code="page.404.legend"/>
                <small><font face="Tahoma" color="red"><spring:message code="page.404.error"/></font></small>
            </h1>
            <br/>
            <spring:message code="page.404.description"/>

            <a href="/" class="btn btn-large btn-info"><i class="icon-home icon-white"></i> <spring:message
                    code="back-to-index"/></a>
        </div>
        <br/>
    </jsp:body>
</t:generic>