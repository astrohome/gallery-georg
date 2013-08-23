<%@tag description="List images code" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="listImages" required="true" type="java.util.List" %>

<ul class="thumbnails">
    <c:forEach items="${listImages}" var="image">
        <li>
            <a class="thumbnail" rel="lightbox[group]" href="/getImage/${gallery.encodedTitle}/${image}">
                <img class="loading" src="<c:url value="/resources/img/loading.gif" />" height="150px"/>
                <img rel="thumbnail" class="hidden group1" src="/getThumb/${gallery.encodedTitle}/${image}"
                     title="${image}"/>
            </a>
        </li>
    </c:forEach>
</ul>