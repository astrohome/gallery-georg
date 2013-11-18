<%@tag description="Gallery pagination" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="url" type="java.lang.String" required="true" %>
<%@attribute name="pages" type="java.lang.Integer" required="true" %>

<c:forEach begin="1" end="${pages}" var="i">
    <a href="<c:out value='${url}"&page="${i}'/>">${i}</a>
</c:forEach>

<br/>

