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
            <div class="success" style="clear: both;">Successfully saved!</div>
        </c:if>
        <table class="simple-little-table">
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
                        <tr class="created">
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

                <td><a href="<c:out value="${editLink}" escapeXml="false" />">Edit</a></td>
                </tr>

            </c:forEach>
        </table>
    </jsp:body>
</t:generic>