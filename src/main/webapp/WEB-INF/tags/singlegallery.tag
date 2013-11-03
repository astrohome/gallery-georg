<%@tag description="List images code" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="listImages" required="true" type="java.util.List" %>

<c:choose>
    <c:when test="${gallery.hidden}">
        <c:set var="url" value="/private/download/${gallery.title}"/>
    </c:when>
    <c:otherwise>
        <c:if test="${not gallery.watermark}">
            <c:set var="url" value="/download/${gallery.title}"/>
        </c:if>
    </c:otherwise>
</c:choose>

<c:if test="${not empty url}">
    <div id="preparing-file-modal" title="<spring:message code="page.public.download.preparing"/>"
         style="display: none;">
        <spring:message code="page.public.download.pleasewait"/>

        <div class="progress progress-striped active">
            <div class="bar" style="width: 100%;"></div>
        </div>
    </div>

    <div id="error-modal" title="Ошибка" style="display: none;">
        <spring:message code="page.public.download.failed"/>
    </div>

    <a url="${url}" href="#" class="fileDownload btn btn-success"><i
            class="icon-download"></i> <spring:message code="download-all-zip"/></a>
    <br/>
    <br/>
</c:if>

<ul class="thumbnails">
    <c:forEach items="${listImages}" var="image">
        <li>
            <div class="simpleCart_shelfItem">
                <a class="thumbnail" rel="lightbox[group]" href="/getImage/${gallery.encodedTitle}/${image}">
                    <img class="loading" src="<c:url value="/resources/img/loading.gif" />" height="150px"/>
                    <img rel="thumbnail" class="hidden group1" src="/getThumb/${gallery.encodedTitle}/${image}"
                         title="${image}"/>
                </a>

                <div class="hidden item_name">${gallery.title}/${image}</div>
                <input type="hidden" value="1" class="item_Quantity"/><br>
                <input name="size" class="hidden item_size" value="${prices.get(0).format}"/>
                <input name="sizeid" class="hidden item_sizeid" value="${prices.get(0).format.id}"/>
                <input name="paperTypeId" class="hidden item_paperTypeId" value="${prices.get(0).paperType.id}"/>

                <div class="input-append">
                    <select name="price" class="item_price">
                        <c:forEach items="${prices}" var="price">
                            <option format="${price.format}" format-id="${price.format.id}"
                                    paper-id="${price.paperType.id}" value="${price.price}">${price}</option>
                        </c:forEach>
                    </select>
                    <a class="btn item_add" href="javascript:;" title="<spring:message code="page.public.addtocard"/>">
                        <i class="icon-shopping-cart"></i>
                    </a>
                </div>
            </div>
        </li>
    </c:forEach>
</ul>