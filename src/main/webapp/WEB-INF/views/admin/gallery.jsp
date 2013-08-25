<%@ include file="../include/init.jsp" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">
        Admin part
    </jsp:attribute>
    <jsp:body>
        <t:menu type="admin" menuItems="${menuItems}"/>

        <div class="row">
            <div class="span9">
                <c:if test="${success}">
                    <div class="alert alert-success">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <h4>Успех!</h4>
                        Successfully saved!
                    </div>
                </c:if>

                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="text-center">ID</th>
                        <th class="text-center"><i class="icon-quote-left"></i> Title</th>
                        <th class="text-center"><i class="icon-calendar"></i> Modified</th>
                        <th class="text-center">Private?</th>
                        <th class="text-center">Watermarked?</th>
                        <th class="text-center">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listDirectories}" var="directory">

                        <c:choose>
                            <c:when test="${directory.id != null}">
                                <tr class="success">
                            </c:when>
                            <c:otherwise>
                                <tr>
                            </c:otherwise>
                        </c:choose>
                        <td class="text-center">${directory.id}</td>
                        <td class="text-center">${directory.title}</td>
                        <td class="text-center">${directory.createdText}</td>
                        <td class="text-center">
                            <i
                                    <c:choose>
                                        <c:when test="${directory.hidden}">
                                            class="icon-check"
                                        </c:when>
                                        <c:otherwise>
                                            class="icon-check-empty"
                                        </c:otherwise>
                                    </c:choose>
                                    ></i>
                        </td>
                        <td class="text-center">
                            <i
                                    <c:choose>
                                        <c:when test="${directory.watermark}">
                                            class="icon-check"
                                        </c:when>
                                        <c:otherwise>
                                            class="icon-check-empty"
                                        </c:otherwise>
                                    </c:choose>
                                    ></i>
                        </td>
                        <c:url var="editLink" value="/admin">
                            <c:param name="title" value="${directory.encodedTitle}"/>
                        </c:url>

                        <td class="text-center">
                            <div class="btn-group">
                                <a href="<c:out value="${editLink}" escapeXml="false" />"
                                   class="btn btn-toolbar">
                                    <div class="icon-pencil"></div>
                                    Редактировать</a>
                                <button class="btn dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="/?id=${directory.id}" target="_blank"><i class="icon-eye-open"></i>
                                            View</a>
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
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:body>
</t:generic>