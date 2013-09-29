<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.videos.title"/></jsp:attribute>
    <jsp:attribute name="menu">
        <t:menu menuItems="${menuItems}" showLogin="true"/>
    </jsp:attribute>

    <jsp:body>
        <c:forEach items="${videoList}" var="video">
            <iframe src="http://player.vimeo.com/video/${video.videoId}" width="800" height="450" frameborder="0"
                    webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>
            <br/>
        </c:forEach>
    </jsp:body>
</t:generic>
