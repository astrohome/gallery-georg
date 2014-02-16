<%@ include file="../include/init.jsp" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="bottom">
        <script type="text/javascript">
            var i = $('table tr').length - 1;
            $("#addPaperType").click(function () {
                $("table tr").eq(1).clone().find("select, input").each(function () {
                    $(this).attr('value', '');
                    $(this).attr('name', $(this).attr('name').replace('0', i));
                    $(this).attr('id', $(this).attr('id').replace('0', i));
                }).end().appendTo("table");
                i = $('table tr').length - 1;
            });

            $('table').on('click', '.removePrice', function () {
                $(this).closest('tr').nextAll('tr').find('select').each(function () {
                    var name = $(this).attr('name');
                    var m = name.match("\\[(.*?)\\]");
                    $(this).attr('name', name.replace(m[1], m[1] - 1));
                });
                $(this).closest('tr').remove();

                i = $('table tr').length - 1;
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <t:menu type="admin" menuItems="${menuItems}" showLogin="false"/>

        <div class="row">
            <div class="span9">
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
                            <th>Format</th>
                            <th>Type</th>
                            <th>Price</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="priceListContainer">
                        <c:forEach items="${priceListContainer.list}" varStatus="i" var="price">
                            <tr>

                                <th>
                                    <form:select path="list[${i.index}].format" required="true">
                                        <form:options items="${formats}" itemValue="id" itemLabel="format"/>
                                    </form:select>
                                </th>
                                <th>
                                    <form:select path="list[${i.index}].paperType" required="true">
                                        <form:options items="${paperTypes}" itemValue="id" itemLabel="paperType"/>
                                    </form:select>
                                </th>
                                <th>
                                    <form:input path="list[${i.index}].price" required="true"/>
                                </th>
                                <th>
                                    <a href="#" class="btn btn-danger removePrice">
                                        <div class="icon-remove"></div>
                                        Delete price</a>
                                </th>
                            </tr>
                        </c:forEach>
                        <c:if test="${priceListContainer.list.size() == 0}">
                            <tr>

                                <th>
                                    <form:select path="list[0].format.id" required="true">
                                        <form:options items="${formats}" itemValue="id" itemLabel="format"/>
                                    </form:select>
                                </th>
                                <th>
                                    <form:select path="list[0].paperType.id" required="true">
                                        <form:options items="${paperTypes}" itemValue="id" itemLabel="paperType"/>
                                    </form:select>
                                </th>
                                <th>
                                    <form:input path="list[0].price" required="true"/>
                                </th>
                                <th>
                                    <a href="#" class="btn btn-danger removePrice">
                                        <div class="icon-remove"></div>
                                        Delete price</a>
                                </th>
                            </tr>
                        </c:if>
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
