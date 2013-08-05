<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">Welcome</jsp:attribute>
    <jsp:body>
        <a href="/admin">admin</a> <br/>

        <a href="/private">Access by code</a> <br/>
        <t:gallerylist list="${list}"/>
    </jsp:body>
</t:generic>
