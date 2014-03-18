<%@ include file="../include/init.jsp" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">
        Admin part
    </jsp:attribute>
    <jsp:body>
        <t:menu type="admin" menuItems="${menuItems}" showLogin="false"/>

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
                        <th class="text-center"><i class="icon-quote-left"></i> <spring:message
                                code="page.admin.title"/></th>
                        <th class="text-center"><i class="icon-calendar"></i> <spring:message
                                code="page.admin.modified"/></th>
                        <th class="text-center"><i class="icon-lock"></i> <spring:message code="page.admin.private"/>
                        </th>
                        <th class="text-center"><i class="icon-shield"></i> <spring:message
                                code="page.admin.watermark"/></th>
                        <th class="text-center"><spring:message code="page.admin.actions"/></th>
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
                                    <spring:message code="edit"/></a>
                                <button class="btn dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="/view?id=${directory.id}" target="_blank"><i class="icon-eye-open"></i>
                                            <spring:message code="view"/></a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a href="/deleteGallery?id=${directory.id}"><i class="icon-remove"></i>
                                            <spring:message
                                                    code="delete"/></a>
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