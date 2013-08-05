<%@tag description="List images code" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="listImages" required="true" type="java.util.List" %>

<c:forEach items="${listImages}" var="image">
    <img src="/getImage/${gallery.encodedTitle}/${image}" width="150"/>
</c:forEach>