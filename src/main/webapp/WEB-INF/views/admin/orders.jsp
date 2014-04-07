<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../include/init.jsp" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:body>
        <t:menu type="admin" menuItems="${menuItems}" showLogin="false"/>
    </jsp:body>
</t:generic>