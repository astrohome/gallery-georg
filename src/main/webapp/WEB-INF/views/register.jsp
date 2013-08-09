<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">Welcome</jsp:attribute>
    <jsp:body>
        <form:form action="register_user.html" method="POST" modelAttribute="user">

        </form:form>
    </jsp:body>
</t:generic>
