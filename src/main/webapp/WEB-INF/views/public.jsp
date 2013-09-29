<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.welcome.intro"/> </jsp:attribute>

    <jsp:attribute name="menu">
        <t:menu menuItems="${menuItems}"/>
    </jsp:attribute>
    <jsp:attribute name="header">
       <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/datepicker.css" />"/>
    </jsp:attribute>

    <jsp:attribute name="bottom">
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>

        <script type="text/javascript">
            var date = new Date();
            var valDate = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
            $('.date').attr('data-date', valDate);

            $('#dp3').datepicker().on('changeDate', function (ev) {
                $('.sort').removeClass('hidden');
                var id = DPGlobal.formatDate(ev.date, DPGlobal.parseFormat('dd-mm-yyyy'), true);
                $('.sort:not(#' + id + ')').addClass('hidden');
            });

            $('.clean').click(function () {
                var date = new Date();
                var valDate = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
                $('#dp3').datepicker('setValue', valDate);
                $('.datevalue').val('');
                $('.sort').removeClass('hidden');
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <a href="/admin?page=gal">admin</a> <br/>


        <t:gallerylist list="${list}" showFilter="true"/>
    </jsp:body>
</t:generic>
