<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">Register new user</jsp:attribute>
    <jsp:body>
        <form:form action="register_user.html" method="POST" cssClass="form-signin" modelAttribute="user">
            <fieldset>
                <legend>User registration</legend>
                <label>Name</label>
                <form:input path="firstName"/>
                <label>Last name</label>
                <form:input path="lastName"/>
                <label>Email (will be login for site)</label>
                <form:input path="login"/>
                <label>Password</label>
                <form:input path="password"/>
                <br/>
                <button type="submit" class="btn">Отправить</button>
            </fieldset>
        </form:form>
    </jsp:body>
</t:generic>
