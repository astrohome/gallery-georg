<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.registration.title"/></jsp:attribute>
    <jsp:attribute name="bottom">
        <script type="text/javascript" src="<c:url value="/resources/js/jqBootstrapValidation.js" />"></script>
    </jsp:attribute>
    <jsp:body>
        <form:form id="register-user" action="register_user.html" method="POST" cssClass="form-signin"
                   modelAttribute="user">
            <fieldset>
                <legend><spring:message code="page.registration.form"/></legend>

                <spring:message code="page.registration.your-name" var="your_name"/>
                <spring:message code="page.registration.your-surname" var="your_surname"/>
                <spring:message code="email" var="email"/>
                <spring:message code="password" var="password"/>

                <div class="form-control-group">
                    <label class="control-label" for="firstName"><spring:message code="page.registration.name"/></label>

                    <div class="controls">
                        <form:input id="firstName" path="firstName" placeholder="${your_name}"/>
                    </div>
                </div>

                <div class="form-control-group">
                    <label class="control-label" for="lastName"><spring:message
                            code="page.registration.surname"/></label>

                    <div class="controls">
                        <form:input id="lastName" path="lastName" placeholder="${your_surname}"/>
                    </div>
                </div>

                <div class="form-control-group">
                    <label class="control-label" for="login"><spring:message code="page.registration.email"/></label>

                    <div class="controls">
                        <input type="email" id="login" path="login" placeholder="${email}" required
                               data-validation-required-message="Это поле является обязательным."
                               data-validation-email-message="Тести"/>

                        <p class="help-block"></p>
                    </div>
                </div>

                <div class="form-control-group">
                    <label class="control-label" for="password"><spring:message
                            code="page.registration.password"/></label>

                    <div class="controls">
                        <form:input id="password" path="password" placeholder="${password}"/>
                    </div>
                </div>

                <br/>

                <button type="submit" class="btn btn-primary"><spring:message code="submit"/></button>
            </fieldset>
        </form:form>
    </jsp:body>
</t:generic>
