<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<my:pagetemplate>
    <jsp:attribute name="body">

       <form:form method="post" action="${pageContext.request.contextPath}/auth/login/"
                  modelAttribute="userLogin" cssClass="form-horizontal">
       <div class="form-group ${mail_error?'has-error':''}">
           <form:label path="mail" cssClass="col-sm-2 control-label"><f:message key="login.mail"/>:</form:label>
           <div class="col-sm-10">
               <form:input path="mail" cssClass="form-control"/>
               <form:errors path="mail" cssClass="help-block"/>
           </div>
       </div>
        <div class="form-group ${password_error?'has-error':''}">
            <form:label path="password" cssClass="col-sm-2 control-label"><f:message key="login.password"/>:</form:label>
            <div class="col-sm-10">
                <form:password path="password" cssClass="form-control"/>
                <form:errors path="password" cssClass="help-block"/>
            </div>
        </div>
        <c:if test="${not empty alert_warning}">
            <p class="text-warning"><c:out value="${alert_warning}"></c:out></p>
        </c:if>
        <button class="btn btn-primary" type="submit">Login</button>
    </form:form>

    </jsp:attribute>
</my:pagetemplate>