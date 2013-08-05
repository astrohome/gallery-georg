<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">
        ${galleryTitle}
    </jsp:attribute>
    <jsp:body>
        <a href="/">Back</a> <br/>
        <t:singlegallery listImages="${listImages}"></t:singlegallery>
    </jsp:body>
</t:generic>