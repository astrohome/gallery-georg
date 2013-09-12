<%@include file="../include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">Register new user</jsp:attribute>
    <jsp:body>
        <c:forEach var="item" items="${items}">
            ${item.id}   ${item.image}<br/>
        </c:forEach>
    </jsp:body>
</t:generic>
