<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="a" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="${match.homeTeam.name} vs. ${match.awayTeam.name}">
    <jsp:attribute name="body">

        <table class="table">
            <tbody>
            <tr>
                <td><b><f:message key="homeTeam"/>:</b> ${match.homeTeam.name}</td>
            </tr>
            <tr>
                <td><b><f:message key="awayTeam"/>:</b> ${match.awayTeam.name}</td>
            </tr>
            <tr>
                <td><b><f:message key="date"/>:</b> ${match.date}</td>
            </tr>
            <tr>
                <td><b><f:message key="league"/>:</b> ${match.league.name}</td>
            </tr>
            <c:choose>
                <c:when test="${not match.finished && match.inPast}">
                    <td><my:a href="/match/play/${match.id}" class="btn btn-primary"><f:message key="play"/></my:a>
                        </td>
                </c:when>
                <c:when test="${not match.finished}">
                    <tr>
                        <td><my:a href="/match/play/${match.id}" class="btn btn-primary disabled"><f:message key="play"/></my:a>
                            <b>Match is not possible to play now.</b> </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <td><b><f:message key="score"/>:</b> ${match.getHomeTeamGoals()} : ${match.getAwayTeamGoals()}</td>
                </c:otherwise>
                </c:choose>
            </tbody>
        </table>



    </jsp:attribute>
</my:pagetemplate>