<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New team">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/team/create"
               modelAttribute="createTeam" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="origin" cssClass="col-sm-2 control-label">Origin</form:label>
            <div class="col-sm-10">
                <form:select path="origin" cssClass="form-control">
                    <c:forEach items="${countries}" var="c">
                        <form:option value="${c}">${c}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="origin" cssClass="error"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="stadium" cssClass="col-sm-2 control-label">Stadium</form:label>
            <div class="col-sm-10">
                <form:select path="stadium" cssClass="form-control">
                    <c:forEach items="${stadiums}" var="s">
                        <form:option value="${s}">${s}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="stadium" cssClass="error"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="leagueId" cssClass="col-sm-2 control-label">League</form:label>
            <div class="col-sm-10">
                <form:select path="leagueId" cssClass="form-control">
                    <c:forEach items="${leagues}" var="l">
                        <form:option value="${l.id}">${l.name}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="leagueId" cssClass="error"/>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Create team</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>