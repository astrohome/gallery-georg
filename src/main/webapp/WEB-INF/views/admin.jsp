<%@ include file="include/init.jsp" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">
        Admin part
    </jsp:attribute>
    <jsp:body>
        Welcome to admin part ! <br/>

        <a href="/">Go to home page</a>

        <br/>

        <c:if test="${success}">
            <div class="alert alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <h4>Успех!</h4>
                Successfully saved!
            </div>
        </c:if>

        <table class="table table-hover">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Modified</th>
                <th>Private?</th>
                <th>Watermarked?</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${listDirectories}" var="directory">

                <c:choose>
                    <c:when test="${directory.id != null}">
                        <tr class="success">
                    </c:when>
                    <c:otherwise>
                        <tr>
                    </c:otherwise>
                </c:choose>
                <td>${directory.id}</td>
                <td>${directory.title}</td>
                <td>${directory.created}</td>
                <td>${directory.hidden}</td>
                <td>${directory.watermark}</td>

                <c:url var="editLink" value="/admin">
                    <c:param name="title" value="${directory.encodedTitle}"/>
                </c:url>

                <td>
                    <div class="btn-group">
                        <a href="<c:out value="${editLink}" escapeXml="false" />"
                           class="btn btn-toolbar">Редактировать</a>
                        <button class="btn dropdown-toggle" data-toggle="dropdown">
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="/?id=${directory.id}">View</a>
                            </li>
                            <li class="divider"></li>
                            <li class="label-important">
                                <a href="/?delete=${directory.id}">Delete</a>
                            </li>
                        </ul>
                    </div>
                </td>
                </tr>

            </c:forEach>
        </table>
    </jsp:body>
</t:generic>