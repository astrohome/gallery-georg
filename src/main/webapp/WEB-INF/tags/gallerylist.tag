<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Gallery List" pageEncoding="UTF-8" %>
<%@attribute name="list" required="true" type="java.util.SortedMap" %>

<br/>Gallery list : <br/>

<c:forEach items="${list}" var="item">
    <b>${item.key}</b> <br/>
    <c:forEach items="${item.value}" var="gallery">
        <a href="?id=${gallery.id}">${gallery.title}</a> <br/>
    </c:forEach>
</c:forEach>