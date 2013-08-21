<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">Login</jsp:attribute>
    <jsp:body>

        <c:if test="${not empty error}">
            <div class="alert">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Предупреждение!</strong> Your login attempt was not successful, try again.<br/> Caused :
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:if>

        <form class="form-signin" name='f'
              action="<c:url value='/j_spring_security_check' />"
              method='POST'>
            <h2 class="form-signin-heading">Please sign in</h2>
            <input type="text" class="input-block-level" name="j_username" placeholder="Email address">
            <input type="password" class="input-block-level" name="j_password" placeholder="Password">
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> Remember me
            </label>
            <button class="btn btn-large btn-primary" type="submit">Sign in</button>
        </form>
    </jsp:body>
</t:generic>