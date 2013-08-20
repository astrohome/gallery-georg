<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">Register new user</jsp:attribute>
    <jsp:body>
        Check you email shortly. Click on the link there.

        ${code}

        <a href="/">Back to index</a>
    </jsp:body>
</t:generic>
