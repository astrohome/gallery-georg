<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.login.title"/></jsp:attribute>

    <jsp:body>

        <c:if test="${not empty error}">
            <div class="alert">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong><spring:message code="attention"/></strong> <spring:message code="login-failed"/><br/><!-- Caused :
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}                -->
            </div>
        </c:if>

        <form class="form-signin" name='f'
              action="<c:url value='/j_spring_security_check' />"
              method='POST'>
            <h2 class="form-signin-heading"><spring:message code="please-sign-in"/></h2>
            <input type="text" class="input-block-level" name="j_username"
                   placeholder="<spring:message code="email" />">
            <input type="password" class="input-block-level" name="j_password"
                   placeholder="<spring:message code="password" />">
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> <spring:message code="remember-me"/>
            </label>
            <button class="btn btn-large btn-primary" type="submit"><spring:message code="login"/></button>
            <a class="btn btn-large pull-right" href="/register_user.html"><spring:message code="register"/></a>
        </form>
    </jsp:body>
</t:generic>