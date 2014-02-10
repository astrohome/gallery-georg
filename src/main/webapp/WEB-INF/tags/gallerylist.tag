<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag description="Gallery List" pageEncoding="UTF-8" %>
<%@attribute name="list" required="true" type="java.util.SortedMap" %>
<%@attribute name="showFilter" required="true" type="java.lang.Boolean" %>

<c:if test="${showFilter}">
    <div class="clearfix"></div>
    <spring:message key="page.public.date-search"/><br/>

    <div class="input-append date" id="dp3" data-date="12-02-2013" data-date-format="dd mm yyyy">
        <input class="datevalue span2" size="16" type="text" readonly="true">
        <span class="opencalendar add-on" title='<spring:message key="page.public.show-calendar"/>'><i
                class="icon-calendar"></i></span>
        <span class="clean add-on" title='<spring:message key="page.public.clear-calender"/>'><i
                class="icon-remove"></i></span>
    </div>
    <div class="clearfix"></div>
    <hr/>
</c:if>

<c:forEach items="${list}" var="item">
    <div id="${item.key.id}" class="sort">
        <div class="icon-calendar"></div>
        <b>${item.key.text}</b>
        <br/>
        <c:forEach items="${item.value}" var="gallery">
            <c:choose>
                <c:when test="${gallery.hidden}">
                    <a href="/private">
                        <div class="icon-lock"></div>
                            ${gallery.title}</a> <br/>
                </c:when>
                <c:otherwise>
                    <a href="/view?id=${gallery.id}">${gallery.title}</a> <br/>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <br/>
    </div>
</c:forEach>