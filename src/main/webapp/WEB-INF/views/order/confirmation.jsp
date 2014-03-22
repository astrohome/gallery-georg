<%@include file="../include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.order.info"/></jsp:attribute>
    <jsp:attribute name="bottom">
        <script type="text/javascript">
            simpleCart.empty();
            function del_cookie(name) {
                document.cookie = name +
                        '=; expires=Thu, 01-Jan-70 00:00:01 GMT;';
            }

            $(function () {
                del_cookie("simpleCart");
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><spring:message code="page.order.info"/></h2>
        <br/>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th class="text-center"><spring:message code="page.order.gallery"/></th>
                <th class="text-center"><spring:message code="page.order.photo"/></th>
                <th class="text-center"><spring:message code="page.order.format"/></th>
                <th class="text-center"><spring:message code="page.order.type"/></th>
                <th class="text-center"><spring:message code="page.order.quantity"/></th>
                <th class="text-center"><spring:message code="page.order.price"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${container.list}">
                <tr>
                    <td class="text-center">${item.gallery.title}</td>
                    <td class="text-center">${item.image}</td>
                    <td class="text-center">${item.price.format.format}</td>
                    <td class="text-center">${item.price.paperType.paperType}</td>
                    <td class="text-center">${item.quantity}</td>
                    <td class="text-center">${item.price.price * item.quantity}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <p>
            <spring:message code="payment.info"/>
            <br/>
            <c:forEach var="item" varStatus="i" items="${methods}">
                <b>${i}:</b> ${item.text}<br/>
            </c:forEach>
        </p>

        <spring:message code="page.order.confirmation-text"/>
        <form:form method="post" action="/confirmOrder">
            <input type="submit" class="btn btn-primary"
                   value="<spring:message code="page.order.confirmation-button" />"/>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form:form>

        <a href="javascript:history.back()"><spring:message code="page.order.refusing-button"/></a>
    </jsp:body>
</t:generic>
