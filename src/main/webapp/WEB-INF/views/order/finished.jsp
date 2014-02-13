<%@include file="../include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"></jsp:attribute>
    <jsp:body>
        <h1><spring:message code="page.order.finished.thank-you"/></h1>

        <p><spring:message code="page.order.finished.order-details" arguments="${orderDetails}"/></p>
        <a class="btn btn-large"><i class="icon-home"></i> <spring:message code="page.activation.back-to-index"/></a>
    </jsp:body>
</t:generic>