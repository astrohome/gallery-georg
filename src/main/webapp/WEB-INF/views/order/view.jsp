<%@include file="../include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.order.info"/></jsp:attribute>
    <jsp:body>
        <spring:message code="page.order.info"/>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th><spring:message code="page.order.gallery"/></th>
                <th><spring:message code="page.order.photo"/></th>
                <th><spring:message code="page.order.format"/></th>
                <th><spring:message code="page.order.type"/></th>
                <th><spring:message code="page.order.quantity"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${items}">
                <tr>
                    <th>${item.gallery}</th>
                    <th>${item.image}</th>
                    <th>${item.format.format}</th>
                    <th>${item.paperType.paperType}</th>
                    <th>${item.quantity}</th>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <spring:message code="payment.info"/>
        <c:forEach var="item" varStatus="i" items="${methods}">
            ${i}: ${item.text}<br/>
        </c:forEach>
    </jsp:body>
</t:generic>
