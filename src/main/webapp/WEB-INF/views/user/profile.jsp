<%@include file="../include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.profile.title"/></jsp:attribute>
    <jsp:attribute name="menu">
        <t:menu menuItems="${menuItems}" showLogin="true"/>
    </jsp:attribute>
    <jsp:body>
        <h2><spring:message code="page.profile.your-orders"/></h2>
        <br/>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th><spring:message code="page.profile.order-id"/></th>
                <th><spring:message code="page.profile.status"/></th>
                <th><spring:message code="page.profile.quantity"/></th>
                <th><spring:message code="page.profile.summ"/></th>
                <th><spring:message code="page.profile.date"/></th>
            </tr>
            </thead>
            <tbody id="orderListContainer">
            <c:forEach items="${orderListContainer.list}" varStatus="i" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.status}</td>
                    <td>${order.items.size}</td>
                    <td>${order.summ}</td>
                    <td>${order.date}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </jsp:body>
</t:generic>