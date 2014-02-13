<%@include file="../include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.order.info"/></jsp:attribute>
    <jsp:body>
        <spring:message code="page.order.info"/>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th class="text-center"><spring:message code="page.order.gallery"/></th>
                <th class="text-center"><spring:message code="page.order.photo"/></th>
                <th class="text-center"><spring:message code="page.order.format"/></th>
                <th class="text-center"><spring:message code="page.order.type"/></th>
                <th class="text-center"><spring:message code="page.order.quantity"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${container.list}">
                <tr>
                    <td class="text-center">${item.gallery}</td>
                    <td class="text-center">${item.image}</td>
                    <td class="text-center">${item.format.format}</td>
                    <td class="text-center">${item.paperType.paperType}</td>
                    <td class="text-center">${item.quantity}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <spring:message code="payment.info"/>
        <c:forEach var="item" varStatus="i" items="${methods}">
            <b>${i}:</b> ${item.text}<br/>
        </c:forEach>

        <spring:message code="page.order.confirmation"/>
        <form:form action="/confirmOrder" modelAttribute="container">
            <input type="submit" class="btn">
        </form:form>
    </jsp:body>
</t:generic>
