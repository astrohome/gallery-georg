<%@tag description="List images code" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="menuItems" type="java.util.LinkedHashMap<java.lang.String, java.lang.String>" %>
<%@attribute name="type" type="java.lang.String" %>
<%@attribute name="showLogin" type="java.lang.Boolean" %>

<c:choose>
    <c:when test="${type eq 'admin'}">
        <ul class="nav nav-tabs">
        <li>
            <a href="/">На главную</a>
        </li>
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

<ul class="nav pull-right">
    <li class="divider-vertical"></li>
    <c:if test="${showLogin}">
        <sec:authorize access="not isAuthenticated()">
            <li class="btn-primary">
                <a href="/login" style="color: white;"><spring:message code="login"/></a>
            </li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <p class="nav navbar-text" style="color: #ffffff;"><spring:message code="logged-in-as"/></p>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" style="color: #ffffff;" data-toggle="dropdown"><sec:authentication
                        property="principal.username"/> <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">
                            My profile
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="<c:url value="j_spring_security_logout" />"><spring:message code="logout"/></a>
                    </li>
                </ul>
            </li>
        </sec:authorize>
    </c:if>
</ul>
<c:if test="${type != 'admin'}">
    </div>
    </div>
    </div>
    </div>
</c:if>