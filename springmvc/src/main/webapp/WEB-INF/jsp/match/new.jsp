<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="New match">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/match/create"
               modelAttribute="createMatch" cssClass="form-horizontal">


        <div class="form-group">
            <form:label path="leagueId" cssClass="col-sm-2 control-label"><f:message key="league"/></form:label>
            <div class="col-sm-10">
        <form:select path="leagueId" cssClass="form-control">
            <c:forEach items="${leagues}" var="l">
                <form:option value="${l.id}">${l.name}</form:option>
            </c:forEach>
        </form:select>
                <form:errors path="leagueId" cssClass="error"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="homeTeamId" cssClass="col-sm-2 control-label"><f:message key="homeTeam"/></form:label>
            <div class="col-sm-10">
                <form:select path="homeTeamId" cssClass="form-control">
                    <c:forEach items="${teams}" var="t">
                        <form:option value="${t.id}">${t.name}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="homeTeamId" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group" ${team_error?'has-error':''}>
            <form:label path="awayTeamId" cssClass="col-sm-2 control-label"><f:message key="awayTeam"/></form:label>
            <div class="col-sm-10">
                <form:select path="awayTeamId" cssClass="form-control">
                    <c:forEach items="${teams}" var="t">
                        <form:option value="${t.id}">${t.name}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="awayTeamId" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group" ${stadium_error?'has-error':''}>
            <form:label path="stadium" cssClass="col-sm-2 control-label"><f:message key="stadium"/></form:label>
            <div class="col-sm-10">
                <form:select path="stadium" cssClass="form-control">
                    <c:forEach items="${stadiums}" var="st">
                        <form:option value="${st}">${st}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="stadium" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${date_error?'has-error':''}">
            <form:label path="date" cssClass="col-sm-2 control-label"><f:message key="date"/></form:label>
            <div class="col-sm-10">
                <form:input path="date" placeholder="DD-MM-YYYY" cssClass="form-control"/>
                <form:errors path="date" cssClass="help-block"/>
            </div>
        </div>


        <button class="btn btn-primary" type="submit"><f:message key="createMatch"/></button>
    </form:form>
</jsp:attribute>
</my:pagetemplate>
