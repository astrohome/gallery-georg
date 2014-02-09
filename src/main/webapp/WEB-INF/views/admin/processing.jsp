<%@ include file="../include/init.jsp" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="bottom">
        <script type="text/javascript">
            $(function () {

                $("#progressbar").progressbar({
                    value: 0
                });

                var total = 0;

                $.get("/getTotal", function (data) {
                    total = data;
                });

                var progresspump = setInterval(function () {
                    /* query the completion percentage from the server */
                    $.get("/getProgress", function (data) {
                        /* update the progress bar width */
                        $("#progressbar").progressbar({
                            value: data / total * 100
                        });
                        $(".progress-label").text("Изображение " + data + " из " + total);

                        /* test to see if the job has completed */
                        if (data / total * 100 > 99.999) {
                            clearInterval(progresspump);
                            $("#progressbar").html("Готово.");
                        }
                    })
                }, 2000);
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <div id="progressbar">
            <div class="progress-label">Идет обработка...</div>
        </div>
    </jsp:body>
</t:generic>