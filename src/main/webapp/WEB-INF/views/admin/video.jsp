<%@ include file="../include/init.jsp" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="bottom">
        <script>
            var i = $('table tr').length - 1;
            $("#addVideo").click(function () {
                $("table tr").eq(1).clone().find("input").each(function () {
                    $(this).attr('value', '');
                    $(this).attr('name', $(this).attr('name').replace('0', i));
                }).end().appendTo("table");
                i = $('table tr').length;
            });

            $('table').on('click', '.removeVideo', function () {
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
        <t:menu type="admin" menuItems="${menuItems}" showLogin="false"/>

        <div class="row">
            <div class="span7">
                <c:if test="${success}">
                    <div class="alert alert-success">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <h4>Успех!</h4>
                        Successfully saved ${count} formats!
                    </div>
                </c:if>
                <form:form action="/editvideo" modelAttribute="videoListContainer" method="post" id="videoListForm">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Video code</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="videoListContainer">
                        <c:forEach items="${videoListContainer.list}" varStatus="i" var="video">
                            <tr class="person">
                                <td>
                                    <form:input readonly="true" path="list[${i.index}].id" value="${video.id}"/>
                                </td>
                                <td>
                                    <form:input path="list[${i.index}].videoId" required="true"/>
                                </td>
                                <td><a href="#" class="btn btn-danger removeVideo">
                                    <div class="icon-remove"></div>
                                    Delete video</a></td>
                            </tr>
                        </c:forEach>
                        <c:if test="${videoListContainer.list.size() == 0}">
                            <tr class="person">
                                <td>
                                    <form:input path="list[0].id" disabled="true"/>
                                </td>
                                <td>
                                    <form:input path="list[0].videoId" required="true"/>
                                </td>
                                <td><a href="#" class="btn btn-danger removeVideo">
                                    <div class="icon-remove"></div>
                                    Delete video</a></td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                    <input type="submit" class="btn" value="Save" id="submit"/>&nbsp;&nbsp;
                    <a href="#" class="btn btn-success" id="addVideo">
                        <div class="icon-plus"></div>
                        Add video</a>&nbsp;&nbsp;
                </form:form>
            </div>
        </div>

    </jsp:body>
</t:generic>
