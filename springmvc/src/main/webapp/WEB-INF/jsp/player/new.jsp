<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="New player">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/player/create"
               modelAttribute="createPlayer" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label"><f:message key="name"/></form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
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
        <div class="form-group">
            <form:label path="teamId" cssClass="col-sm-2 control-label"><f:message key="team"/></form:label>
            <div class="col-sm-10">
                <form:select path="teamId" cssClass="form-control">
                    <c:forEach items="${teams}" var="t">
                        <form:option value="${t.id}">${t.name}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="teamId" cssClass="error"/>
            </div>
        </div>
        <div class="form-group ${birthDate_error?'has-error':''}">
            <form:label path="birthDate" cssClass="col-sm-2 control-label"><f:message key="date"/></form:label>
            <div class="col-sm-10">
                <form:input path="birthDate" placeholder="DD/MM/YYYY" cssClass="form-control"/>
                <form:errors path="birthDate" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="position" cssClass="col-sm-2 control-label"><f:message key="position"/></form:label>
            <div class="col-sm-10">
        <form:select path="position" cssClass="form-control">
            <c:forEach items="${positions}" var="p">
                <form:option value="${p}">${p}</form:option>
            </c:forEach>
        </form:select>
                <form:errors path="position" cssClass="error"/>
            </div>
        </div>
        <div class="form-group ${number_error?'has-error':''}">
            <form:label path="number" cssClass="col-sm-2 control-label"><f:message key="number"/></form:label>
            <div class="col-sm-10">
                <form:input path="number" cssClass="form-control"/>
                <form:errors path="number" cssClass="help-block"/>
            </div>
        </div>
        <h4><f:message key="playerStats"/></h4>
        <div class="form-group ${shooting_error?'has-error':''}">
            <form:label path="shooting" cssClass="col-sm-2 control-label"><f:message key="shooting"/></form:label>
            <div class="col-sm-10">
                <form:input path="shooting" cssClass="form-control"/>
                <form:errors path="shooting" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${passing_error?'has-error':''}">
            <form:label path="passing" cssClass="col-sm-2 control-label"><f:message key="passing"/></form:label>
            <div class="col-sm-10">
                <form:input path="passing" cssClass="form-control"/>
                <form:errors path="passing" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${speed_error?'has-error':''}">
            <form:label path="speed" cssClass="col-sm-2 control-label"><f:message key="speed"/></form:label>
            <div class="col-sm-10">
                <form:input path="speed" cssClass="form-control"/>
                <form:errors path="speed" cssClass="help-block"/>
            </div>
        </div>
                <div class="form-group ${defence_error?'has-error':''}">
                    <form:label path="defence" cssClass="col-sm-2 control-label"><f:message key="defence"/></form:label>
                    <div class="col-sm-10">
                        <form:input path="defence" cssClass="form-control"/>
                        <form:errors path="defence" cssClass="help-block"/>
                    </div>
                </div>
        <div class="form-group ${strength_error?'has-error':''}">
            <form:label path="strength" cssClass="col-sm-2 control-label"><f:message key="strength"/></form:label>
            <div class="col-sm-10">
                <form:input path="strength" cssClass="form-control"/>
                <form:errors path="strength" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${goalkeeping_error?'has-error':''}">
            <form:label path="goalkeeping" cssClass="col-sm-2 control-label"><f:message key="goalkeeping"/></form:label>
            <div class="col-sm-10">
                <form:input path="goalkeeping" cssClass="form-control"/>
                <form:errors path="goalkeeping" cssClass="help-block"/>
            </div>
        </div>
        <button class="btn btn-primary" type="submit"><f:message key="createPlayer"/></button>
    </form:form>
</jsp:attribute>
</my:pagetemplate>