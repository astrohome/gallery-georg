<%@tag description="List images code" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="menuItems" type="java.util.SortedMap<java.lang.String, java.lang.String>" %>
<%@attribute name="type" %>

<c:choose>
    <c:when test="${type eq 'admin'}">
        <ul class="nav nav-tabs">
    </c:when>
    <c:otherwise>
        <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
        <div class="container">
        <a class="brand" href="/"><spring:message code="page.menu.public.logo"/></a>

        <div class="nav-collapse collapse">
        <ul class="nav">
    </c:otherwise>
</c:choose>
<c:forEach items="${menuItems}" var="item">
    <li
            <c:if test="${(requestScope['javax.servlet.forward.servlet_path'] eq item.key)
                                                    or (
                                                    (fn:length(requestScope['javax.servlet.forward.query_string']) > 0) and
                                                    fn:contains(requestScope['javax.servlet.forward.query_string'], fn:substringAfter(item.key, '?')))}">
                class="active"
            </c:if>
            >

        <a href="${item.key}"><spring:message code="${item.value}"/></a>
    </li>
</c:forEach>
</ul>
<c:if test="${type != 'admin'}">
    </div>
    </div>
    </div>
    </div>
</c:if>