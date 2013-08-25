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
                <form:form action="/editpapertype" modelAttribute="paperTypeListContainer" method="post"
                           id="paperTypeListForm">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Format</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="paperTypeListContainer">
                        <c:forEach items="${paperTypeListContainer.list}" varStatus="i" var="format">
                            <tr class="person">
                                <td>
                                    <spring:bind path="list[${i.index}].id">
                                        <input type="text" disabled="true" name="${status.expression}"
                                               value="${status.value}"/>
                                    </spring:bind>
                                </td>
                                <td>
                                    <spring:bind path="list[${i.index}].paperType">
                                        <input type="text" name="${status.expression}" value="${status.value}"/>
                                    </spring:bind>
                                </td>
                                <td><a href="#" class="btn btn-danger removeFormat">
                                    <div class="icon-remove"></div>
                                    Delete paper type</a></td>
                            </tr>
                        </c:forEach>
                        <c:if test="${paperTypeListContainer.list.size() == 0}">
                            <tr class="person">
                                <td>
                                    <spring:bind path="list[0].id">
                                        <input type="text" disabled="true" name="${status.expression}"
                                               value="${status.value}"/>
                                    </spring:bind>
                                </td>
                                <td>
                                    <spring:bind path="list[0].paperType">
                                        <input type="text" name="${status.expression}" value="${status.value}"/>
                                    </spring:bind>
                                </td>
                                <td><a href="#" class="btn btn-danger removeFormat">
                                    <div class="icon-remove"></div>
                                    Delete paper type</a></td>
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