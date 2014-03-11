<%@include file="../include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.profile.title"/></jsp:attribute>
    <jsp:attribute name="menu">
        <t:menu menuItems="${menuItems}" showLogin="true"/>
    </jsp:attribute>
    <jsp:body>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>ID заказа</th>
                <th>Состояние</th>
                <th>Количество фотографий</th>
                <th>Сумма</th>
            </tr>
            </thead>
            <tbody id="orderListContainer">
            <c:forEach items="${orderListContainer.list}" varStatus="i" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.status}</td>
                    <td>${order.items.size}</td>
                    <td>сумма</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </jsp:body>
</t:generic>