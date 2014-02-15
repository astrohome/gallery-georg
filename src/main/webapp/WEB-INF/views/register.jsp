<%@include file="include/init.jsp" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic>
    <jsp:attribute name="title"><spring:message code="page.registration.title"/></jsp:attribute>
    <jsp:body>
        <form:form id="register-user" action="register_user.html" method="POST" cssClass="form-signin"
                   modelAttribute="user">
            <fieldset>
                <legend><spring:message code="page.registration.form"/></legend>

                <spring:message code="page.registration.your-name" var="your_name"/>
                <spring:message code="page.registration.your-surname" var="your_surname"/>
                <spring:message code="email" var="email"/>
                <spring:message code="password" var="password"/>


                <label for="firstName"><spring:message code="page.registration.name"/></label>
                <form:input id="firstName" path="firstName" placeholder="${your_name}" required="true"/><br/>
                <form:errors path="firstName" cssClass="alert-error"></form:errors>

                <label for="lastName"><spring:message code="page.registration.surname"/></label>
                <form:input id="lastName" path="lastName" placeholder="${your_surname}" required="true"/><br/>
                <form:errors path="lastName" cssClass="alert-error"></form:errors>

                <label for="login"><spring:message code="page.registration.email"/></label>
                <form:input type="email" id="login" path="login" placeholder="${email}" required="true"/><br/>
                <form:errors path="login" cssClass="alert-error"></form:errors>

                <label for="password"><spring:message code="page.registration.password"/></label>
                <form:input id="password" path="password" placeholder="${password}" required="true"
                            type="password"/><br/>
                <form:errors path="password" cssClass="alert-error"></form:errors>
                <br/>

                <input id="activationCode" name="activationCode" type="hidden" value="activationCode"/>

                <button type="submit" class="btn btn-primary"><spring:message code="submit"/></button>
            </fieldset>
        </form:form>
    </jsp:body>
</t:generic>
