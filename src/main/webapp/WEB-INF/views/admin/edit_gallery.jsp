<%@include file="../include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title">
        Edit gallery ${gallery.title}
    </jsp:attribute>
    <jsp:attribute name="bottom">
        <script type="text/javascript">
            $(document).ready(function () {

                $("#generate").click(function () {
                            $("#password").val($.password(6));
                        }
                );

                $("#hidden1").change(function () {
                            if ($(this).is(":checked")) {
                                $("#password_generation").removeClass('hidden');
                                $("#password").val(null);
                            }
                            else {
                                $("#password_generation").addClass('hidden');
                            }
                        }
                );
            });

            $.extend({
                password: function (length, special) {
                    var iteration = 0;
                    var password = "";
                    var randomNumber;
                    if (special == undefined) {
                        var special = false;
                    }
                    while (iteration < length) {
                        randomNumber = (Math.floor((Math.random() * 100)) % 94) + 33;
                        if (!special) {
                            if ((randomNumber >= 33) && (randomNumber <= 47)) {
                                continue;
                            }
                            if ((randomNumber >= 58) && (randomNumber <= 64)) {
                                continue;
                            }
                            if ((randomNumber >= 91) && (randomNumber <= 96)) {
                                continue;
                            }
                            if ((randomNumber >= 123) && (randomNumber <= 126)) {
                                continue;
                            }
                        }
                        iteration++;
                        password += String.fromCharCode(randomNumber);
                    }
                    return password;
                }
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <form:form method="POST" cssClass="navbar-form pull-left" action="saveGallery.html" modelAttribute="gallery">

            <table>
                <tr>
                    <td><form:label path="id">Id</form:label></td>
                    <td><form:input path="id" readonly="true"/></td>
                </tr>
                <tr>
                    <td><form:label path="title">Title</form:label></td>
                    <td><form:input path="title" readonly="true"/></td>
                </tr>
                <tr>
                    <td><form:label path="created">Modified</form:label></td>
                    <td><form:input path="created" readonly="true"/></td>
                </tr>
                <tr>
                    <td><form:label path="hidden">Private?</form:label></td>
                    <td><form:checkbox path="hidden"/>
                        <c:if test="${not empty gallery.password}">
                            Password: <c:out value="${gallery.password}"/>
                        </c:if>
                    </td>
                </tr>
                <tr id="password_generation" class="hidden">
                    <td><form:label path="password">Password</form:label></td>
                    <td><form:input path="password"/><input type="button" id="generate" value="Generate new"></td>
                </tr>
                <tr>
                    <td><form:label path="watermark">Watermark?</form:label></td>
                    <td><form:checkbox path="watermark"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>

        </form:form>
    </jsp:body>
</t:generic>