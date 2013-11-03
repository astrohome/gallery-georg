<%@include file="../include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">Обзор заказа</jsp:attribute>
    <jsp:body>
        Обзор заказа:
        <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th>Галерея</th>
            <th>Фотография</th>
            <th>Формат</th>
            <th>Тип бумаги</th>
            <th>Количество</th>
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
        <tbody>

        Вы можете оплатить следующими способами:
        <c:forEach var="item" varStatus="i" items="${methods}">
            ${i}: ${item.text}<br/>
        </c:forEach>
    </jsp:body>
</t:generic>
