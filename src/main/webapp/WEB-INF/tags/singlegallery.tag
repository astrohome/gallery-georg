<%@tag description="List images code" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="listImages" required="true" type="java.util.List" %>

<!-- modal-gallery is the modal dialog used for the image gallery -->
<div id="modal-gallery" class="modal modal-gallery hide fade" tabindex="-1">
    <div class="modal-header">
        <a class="close" data-dismiss="modal">&times;</a>

        <h3 class="modal-title"></h3>
    </div>
    <div class="modal-body">
        <div class="modal-image"></div>
    </div>
    <div class="modal-footer">
        <a class="btn btn-info modal-prev"><i class="icon-arrow-left icon-white"></i> Previous</a>
        <a class="btn btn-primary modal-next">Next <i class="icon-arrow-right icon-white"></i></a>
        <a class="btn btn-success modal-play modal-slideshow" data-slideshow="5000"><i class="icon-play icon-white"></i>
            Slideshow</a>
    </div>
</div>

<div id="gallery" data-toggle="modal-gallery" data-target="#modal-gallery">
    <c:forEach items="${listImages}" var="image">
        <a href="/getImage/${gallery.encodedTitle}/${image}" data-gallery="gallery">
            <img src="/getThumb/${gallery.encodedTitle}/${image}" height="150px"/>
        </a>
    </c:forEach>
</div>