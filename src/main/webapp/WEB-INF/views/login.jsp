<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.login.title"/></jsp:attribute>

    <jsp:body>

        <c:if test="${param.error != null}">
        <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong><spring:message code="attention"/></strong> <spring:message code="login-failed"/><br/><!-- Caused :
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}                -->
            </div>
        </c:if>

        <form class="form-signin" name='f'
              action="<c:url value='/login' />"
              method='POST'>
            <legend><h3><spring:message code="please-sign-in"/></h3></legend>

            <div class="alert alert-success">
                <spring:message code="login-info"/>
            </div>
            <hr/>

            <input type="text" class="input-block-level" name="username"
                   placeholder="<spring:message code="email" />" required>
            <input type="password" class="input-block-level" name="password"
                   placeholder="<spring:message code="password" />" required>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> <spring:message code="remember-me"/>
            </label>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <button class="btn btn-large btn-primary" type="submit"><spring:message code="login"/></button>
            <a class="btn btn-large pull-right" href="/register_user.html"><spring:message code="register"/></a>
        </form>
    </jsp:body>
</t:generic>