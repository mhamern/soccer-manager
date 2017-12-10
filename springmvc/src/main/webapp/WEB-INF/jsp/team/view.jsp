<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="a" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="${team.name}">
    <jsp:attribute name="body">
        <table class="table">
        <tbody>
            <tr>
                <td><b><f:message key="name"/>:</b> ${team.name}</td>
            </tr>
            <tr>
                <td><b><f:message key="country"/>:</b> ${team.origin}</td>
            </tr>
            <tr>
                <td><b><f:message key="league"/>:</b> ${team.league.name}</td>
            </tr>
            <tr>
                <td><b><f:message key="manager"/>:</b> ${team.manager.name}</td>
            </tr>
            </tbody>
        </table>
        <h2><f:message key="navigation.players"/></h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <td><b><f:message key="name"/></b></td>
                    <td><b><f:message key="position"/></b></td>
                    <td><b><f:message key="nationality"/></b></td>
                    <td><b><f:message key="number"/></b></td>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${players}" var="player">
                <tr>
                    <td>${player.name}</td>
                    <td>${player.position}</td>
                    <td>${player.nationality}</td>
                    <td>#${player.number}</td>
                    <td><my:a href ="/player/view/${player.id}" class="btn btn-primary"><f:message key="view"/></my:a></td>
                    <c:if test="${not empty authenticatedUser && authenticatedUser.isAdmin()}">
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/player/delete/${player.id}">
                                <button type="submit" class="btn btn-danger">
                                    <f:message key="delete"></f:message>
                                </button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <h2><f:message key="navigation.matches"/></h2>
        <table class="table">
            <thead>
            <tr>
                <td><b><f:message key="homeTeam"/></b></td>
                <td><b><f:message key="awayTeam"/></b></td>
                <td><b><f:message key="date"/></b></td>
                <td><b><f:message key="stadium"/></b></td>
                <td><b><f:message key="result"/></b></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${matches}" var="match">
                <c:choose>
                    <c:when test="${(match.homeTeam == team && match.homeTeamGoals > match.awayTeamGoals)
                    || (match.awayTeam == team && match.awayTeamGoals > match.homeTeamGoals)}">
                        <tr class="success">
                    </c:when>
                    <c:when test="${(match.homeTeam == team && match.homeTeamGoals < match.awayTeamGoals)
                    || (match.awayTeam == team && match.awayTeamGoals < match.homeTeamGoals)}">
                        <tr class="danger">
                    </c:when>
                    <c:when test="${not match.finished}">
                        <tr>
                    </c:when>
                    <c:otherwise>
                        <tr class="active">
                    </c:otherwise>
                </c:choose>

                    <td>${match.homeTeam.name}</td>
                    <td>${match.awayTeam.name}</td>
                    <td>${match.date}</td>
                    <td>${match.homeTeam.stadium}</td>
                    <td>${match.homeTeamGoals}-${match.awayTeamGoals}</td>
                    <c:if test="${not empty authenticatedUser && not authenticatedUser.isAdmin() && not match.finished}">
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/match/play/${match.id}">
                            <button type="submit" class="btn btn-primary">
                                <f:message key="play"></f:message>
                            </button>
                        </form>
                    </td>

                    </c:if>
                    <c:if test="${not empty authenticatedUser && authenticatedUser.isAdmin()}">
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/match/delete/${match.id}">
                                <button type="submit" class="btn btn-danger">
                                    <f:message key="delete"></f:message>
                                </button>
                            </form>
                            </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>