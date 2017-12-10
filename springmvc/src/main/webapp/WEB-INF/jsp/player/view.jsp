<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="a" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="${player.name}">
    <jsp:attribute name="body">
        <c:if test="${not empty authenticatedUser && !authenticatedUser.isAdmin()
                && freeagents.contains(player)}">
            <my:a href="${player.id}/addtouserteam" class="btn btn-success">
                <f:message key="AssignPlayer"></f:message>
            </my:a>
        </c:if>
        <table class="table">
            <tbody>
            <tr>
                <td><b><f:message key="name"/>:</b> ${player.name}</td>
            </tr>
            <tr>
                <td><b><f:message key="nationality"/>:</b> ${player.nationality}</td>
            </tr>
            <tr>
                <c:choose>
                <c:when test="${not empty playersTeam}">
                    <td><b><f:message key="team"/>:</b> ${playersTeam.name}</td>
                </c:when>
                <c:otherwise>
                    <td><b><f:message key="team"/>:</b><f:message key="noTeam"></f:message></td>
                </c:otherwise>
                </c:choose>
            </tr>
            <tr>
                <td><b><f:message key="birthdate"/>:</b> ${player.birthDate}</td>
            </tr>
            <tr>
                <td><b><f:message key="position"/>:</b> ${player.position}</td>
            </tr>
            <tr>
                <td><b><f:message key="number"/>:</b> ${player.number}</td>
            </tr>
            <tr>
                <td><b><f:message key="shooting"/>:</b> ${player.shooting}</td>
            </tr>
            <tr>
                <td><b><f:message key="passing"/>:</b> ${player.passing}</td>
            </tr>
            <tr>
                <td><b><f:message key="speed"/>:</b> ${player.speed}</td>
            </tr>
            <tr>
                <td><b><f:message key="defence"/>:</b> ${player.defence}</td>
            </tr>
            <tr>
                <td><b><f:message key="strength"/>:</b> ${player.strength}</td>
            </tr>
            <tr>
                <td><b><f:message key="goalkeeping"/>:</b> ${player.goalkeeping}</td>
            </tr>
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>