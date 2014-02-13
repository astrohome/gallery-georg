<%@include file="../include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.profile.title"/></jsp:attribute>
    <jsp:attribute name="menu">
        <t:menu menuItems="${menuItems}" showLogin="true"/>
    </jsp:attribute>

</t:generic>