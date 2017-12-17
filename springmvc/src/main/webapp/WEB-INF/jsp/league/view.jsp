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
    </jsp:attribute>
</my:pagetemplate>