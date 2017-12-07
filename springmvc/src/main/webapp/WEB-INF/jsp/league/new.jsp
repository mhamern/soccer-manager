<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New League">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/league/create"
               modelAttribute="createLeague" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="country" cssClass="col-sm-2 control-label">Country</form:label>
            <div class="col-sm-10">
                <form:select path="country" cssClass="form-control">
                    <c:forEach items="${countries}" var="c">
                        <form:option value="${c}">${c}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="country" cssClass="error"/>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Create League</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>