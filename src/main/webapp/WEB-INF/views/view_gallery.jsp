<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">
        ${galleryTitle}
    </jsp:attribute>
    <jsp:attribute name="header">
            <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/prettyPhoto.css" />"/>
    </jsp:attribute>
    <jsp:attribute name="bottom">
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.prettyPhoto.js"/>"></script>

        <script type="text/javascript">
            jQuery(document).ready(function () {
                jQuery("[rel^='lightbox']").prettyPhoto({
                    social_tools: ''
                });

                jQuery("[rel^='thumbnail").one('load',function () {
                    if (!this.complete || typeof this.naturalWidth == "undefined" || this.naturalWidth == 0) {
                        alert('broken image!');
                    } else {
                        $(this).siblings('.loading').remove();
                        $(this).removeClass("hidden")
                    }
                }).each(function () {
                            if (this.complete) $(this).load();
                        });
                ;
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <a href="/">Back</a> <br/>
        <t:singlegallery listImages="${listImages}"/>
    </jsp:body>
</t:generic>