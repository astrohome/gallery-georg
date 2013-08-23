<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">Welcome</jsp:attribute>

    <jsp:attribute name="menu"><t:menu/></jsp:attribute>
    <jsp:attribute name="header">
       <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/datepicker.css" />"/>
    </jsp:attribute>

    <jsp:attribute name="bottom">
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>

        <script type="text/javascript">
            $('#dp3').datepicker().on('changeDate', function (ev) {
                $('.sort').removeClass('hidden');
                var id = DPGlobal.formatDate(ev.date, DPGlobal.parseFormat('dd-mm-yyyy'), true);
                $('.sort:not(#' + id + ')').addClass('hidden');
            });

            $('.clean').click(function () {
                $('.datevalue').val('');
                $('.sort').removeClass('hidden');
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <a href="/admin">admin</a> <br/>

        <sec:authorize access="not isAuthenticated()">
            <a href="/login.html">Login</a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a href="<c:url value="j_spring_security_logout" />"> Logout</a>
        </sec:authorize>
        <t:gallerylist list="${list}"/>
    </jsp:body>
</t:generic>
