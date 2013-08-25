<%@ include file="../include/init.jsp" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="bottom">
        <script>
            var i = $('table tr').length - 1;
            $("#addPaperType").click(function () {
                $("table tr").eq(1).clone().find("input").each(function () {
                    $(this).attr('value', '');
                    $(this).attr('name', $(this).attr('name').replace('0', i));
                }).end().appendTo("table");
                i = $('table tr').length;
            });

            $('table').on('click', '.removePaperType', function () {
                $(this).closest('tr').nextAll('tr').find('input').each(function () {
                    var name = $(this).attr('name');
                    var m = name.match("\\[(.*?)\\]");
                    $(this).attr('name', name.replace(m[1], m[1] - 1));
                });
                $(this).closest('tr').remove();

                i = $('table tr').length;
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <t:menu type="admin" menuItems="${menuItems}"/>

        <div class="row">
            <div class="span7">
                <c:if test="${success}">
                    <div class="alert alert-success">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <h4>Успех!</h4>
                        Successfully saved ${count} paper types!
                    </div>
                </c:if>
                <form:form action="/editprice" modelAttribute="priceListContainer" method="post"
                           id="paperTypeListForm">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Type</th>
                            <th>Format</th>
                            <th>Price</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="priceListContainer">
                        <c:forEach items="${priceListContainer.list}" varStatus="i" var="price">
                            <tr>

                                <th>
                                    <spring:bind path="list[${i.index}].format">

                                        <select name="<${status.expression}">

                                            <option value="NONE">---SELECT---</option>

                                            <c:forEach items="${formats }" var="format">

                                                <c:set var="selectMe" value=""/>

                                                <c:if test="${status.actualValue == format.format}">

                                                    <c:set var="selectMe" value="selected"/>

                                                </c:if>

                                                <option

                                                        value="${format.format }"

                                                    ${selectMe }

                                                        >

                                                        ${format.format }

                                                </option>

                                            </c:forEach>

                                        </select>

                                    </spring:bind>
                                </th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <input type="submit" class="btn" value="Save" id="submit"/>&nbsp;&nbsp;
                    <a href="#" class="btn btn-success" id="addPaperType">
                        <div class="icon-plus"></div>
                        Add paper type</a>&nbsp;&nbsp;
                </form:form>
            </div>
        </div>

    </jsp:body>
</t:generic>
