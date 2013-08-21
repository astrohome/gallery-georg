<%@tag description="List images code" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="listImages" required="true" type="java.util.List" %>

<div class="row-fluid">
    <ul class="thumbnails">
        <c:forEach items="${listImages}" var="image">
            <li class="span2">
                <a href="#" class="thumbnail">
                    <img src="/getImage/${gallery.encodedTitle}/${image}"/>
                </a>
            </li>
        </c:forEach>
    </ul>
</div>