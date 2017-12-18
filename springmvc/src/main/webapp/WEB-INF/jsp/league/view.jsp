<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="a" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="${league.name}">
    <jsp:attribute name="body">
        <table class="table">
        <tbody>
            <tr>
                <td><b><f:message key="name"/>:</b> ${league.name}</td>
            </tr>
            <tr>
                <td><b><f:message key="country"/>:</b> ${league.country}</td>
            </tr>
            </tbody>
        </table>
        <h2><f:message key="leagueTable"/></h2>
         <table class="table table-striped">
             <thead>
             <tr>
                 <th><f:message key="name"/></th>
                 <th><f:message key="points"/></th>
                 <th><f:message key="goalsScored"/></th>
                 <th><f:message key="goalsConceded"/></th>
             </tr>
             </thead>
             <tbody>
             <c:forEach items="${leagueTable}" var="team">
                <tr>
                    <td>${team.name}</td>
                    <td>${team.points}</td>
                    <td>${team.goalsScored}</td>
                    <td>${team.goalsConceded}</td>
                </tr>
            </c:forEach>
             </tbody>
         </table>
        <h2><f:message key="navigation.matches"/></h2>
        <table class="table table-striped">
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
                <tr>
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