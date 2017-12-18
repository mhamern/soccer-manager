<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="New manager">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/manager/create"
               modelAttribute="createManager" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label"><f:message key="name"/></form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${mail_error?'has-error':''}">
            <form:label path="email" cssClass="col-sm-2 control-label"><f:message key="email"/></form:label>
            <div class="col-sm-10">
                <form:input path="email" cssClass="form-control"/>
                <form:errors path="email" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="nationality" cssClass="col-sm-2 control-label"><f:message key="nationality"/></form:label>
            <div class="col-sm-10">
                <form:select path="nationality" cssClass="form-control">
                    <c:forEach items="${countries}" var="c">
                        <form:option value="${c}">${c}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="nationality" cssClass="error"/>
            </div>
        </div>

        <button class="btn btn-primary" type="submit"><f:message key="createManager"/></button>
    </form:form>
</jsp:attribute>
</my:pagetemplate>
