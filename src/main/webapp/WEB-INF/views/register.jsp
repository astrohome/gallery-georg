<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">Register new user</jsp:attribute>
    <jsp:body>
        <form:form action="register_user.html" method="POST" modelAttribute="user">
            <table>
                <tr>
                    <td><form:label path="firstName">Name</form:label></td>
                    <td><form:input path="firstName"/></td>
                </tr>
                <tr>
                    <td><form:label path="lastName">Surname</form:label></td>
                    <td><form:input path="lastName"/></td>
                </tr>
                <tr>
                    <td><form:label path="login">Email (will be login)</form:label></td>
                    <td><form:input path="login"/></td>
                </tr>
                <tr>
                    <td><form:label path="password">Password</form:label></td>
                    <td><form:input path="password"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Register"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </jsp:body>
</t:generic>
