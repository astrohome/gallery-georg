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
                    { attr: "name", label: "Название"},
                    { view: "currency", attr: "price", label: "Цена"},
                    { attr: "quantity", label: "Шт."},
                    { view: "currency", attr: "total", label: "Сумма" },
                    { attr: "size", label: "Формат" },
                    { view: "remove", text: "Удалить", label: false}
                ]
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <t:singlegallery listImages="${listImages}"/>

        <span class="simpleCart_quantity"></span> фотографий - <span class="simpleCart_total"></span>

        <div class="simpleCart_items">
        </div>
        <a href="#" class="btn btn-small" onclick="simpleCart.empty();"><i class="icon-remove"></i> <spring:message
                code="clear"/> </a>
        <a href="javascript:;" class="btn btn-small simpleCart_checkout"><spring:message code="checkout"/></a>
    </jsp:body>
</t:generic>