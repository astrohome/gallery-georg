<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">
        ${galleryTitle}
    </jsp:attribute>
    <jsp:attribute name="header">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/prettyPhoto.css" />"/>
    </jsp:attribute>
    <jsp:attribute name="menu">
        <t:menu/>
    </jsp:attribute>
    <jsp:attribute name="bottom">
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.prettyPhoto.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/simpleCart.js"/>"></script>

        <script type="text/javascript">
            jQuery(document).ready(function () {
                jQuery("[rel^='lightbox']").prettyPhoto({
                    social_tools: ''
                });

                jQuery("[rel^='thumbnail']").one('load',function () {
                    if (!this.complete || typeof this.naturalWidth == "undefined" || this.naturalWidth == 0) {
                        alert('broken image!');
                    } else {
                        $(this).siblings('.loading').remove();
                        $(this).removeClass("hidden")
                    }
                }).each(function () {
                    if (this.complete) $(this).load();
                });

                jQuery(".item_price").on('change', function () {
                    //alert();
                    var value = $("option:selected", this).attr('format');
                    $(this).parent().parent().find('.item_size').val(value);

                    value = $("option:selected", this).attr('format-id');
                    $(this).parent().parent().find('.item_sizeid').val(value);

                    value = $("option:selected", this).attr('paper-id');
                    $(this).parent().parent().find('.item_paperTypeId').val(value);
                });

                jQuery(".fileDownload").click(function () {

                    var preparingFileModal = $("#preparing-file-modal");

                    preparingFileModal.dialog({ modal: true });

                    jQuery.fileDownload($(this).attr('url'), {
                        successCallback: function () {
                            preparingFileModal.dialog('close');
                        },
                        failCallback: function () {
                            preparingFileModal.dialog('close');
                            $("#error-modal").dialog({ modal: true });
                        }
                    });
                    return false; //this is critical to stop the click event which will trigger a normal file download!
                });
            });
        </script>

        <script>
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            simpleCart({
                checkout: {
                    type: "SendForm",
                    url: "putOrder.html",
                    method: "POST",
                    success: "orderSuccess.html",
                    cancel: "orderCancel.html"
                },
                cartStyle: 'table',
                currency: "UAH",
                cartColumns: [
                    { attr: "name", label: "<spring:message code="page.order.photo"/>"},
                    { view: "currency", attr: "price", label: "<spring:message code="page.order.price"/>"},
                    { attr: "quantity", label: "<spring:message code="page.order.quantity"/>"},
                    { view: "currency", attr: "total", label: "<spring:message code="summ"/>" },
                    { attr: "size", label: "<spring:message code="page.order.format"/>" },
                    { view: "remove", text: "<spring:message code="delete"/>", label: false}
                ]
            });
        </script>

        <c:choose>
            <c:when test="${gallery.hidden}">
                <c:url value="/code" var="url">
                    <c:param name="code" value="${param.code}"/>
                </c:url>
            </c:when>
            <c:otherwise>
                <c:url value="/view" var="url">
                    <c:param name="id" value="${param.id}"/>
                </c:url>
            </c:otherwise>
        </c:choose>

                <script type='text/javascript'>
                    var options = {
                        alignment: "center",
                        numberOfPages: 10,
                        bootstrapMajorVersion: 2,
                        onPageClicked: function (e, originalEvent, type, page) {
                            window.location.href = '${url}&page=' + page;
                        },
                        currentPage: ${currentPage},
                        totalPages: ${pages}
                    }
                    jQuery('#paginationContainer').bootstrapPaginator(options);
                </script>
    </jsp:attribute>
    <jsp:body>
        <t:singlegallery listImages="${listImages}"/>

        <div id="paginationContainer"></div>

        <sec:authorize access="isAuthenticated()">
            <div class="simpleCart_items"></div>
            <a href="#" class="btn btn-small" onclick="simpleCart.empty();"><i class="icon-remove"></i> <spring:message
                    code="clear"/> </a>
            <a href="javascript:;" class="btn btn-small simpleCart_checkout"><spring:message code="checkout"/></a> <br/>
            <span class="simpleCart_quantity"></span> <spring:message code="page.gallery.photos"/> - <span
                class="simpleCart_total"></span>
        </sec:authorize>
    </jsp:body>
</t:generic>