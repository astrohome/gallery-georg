<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">Register new user</jsp:attribute>
    <jsp:body>
        Check you email shortly. Click on the link there.

        <a href="http://90.29.203.213:8080/activate_user/${code}"/>Link in email</a>

        <a href="/">Back to index</a>
    </jsp:body>
</t:generic>
