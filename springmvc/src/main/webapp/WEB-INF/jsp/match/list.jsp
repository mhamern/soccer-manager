
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="a" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="Matches">
    <jsp:attribute name="body">
        <c:if test="${not empty authenticatedUser && authenticatedUser.isAdmin()}">
            <td><my:a href="/match/new" class="btn btn-success"><f:message key="create"/></my:a></td>
        </c:if>

        <table class="table table-striped">
            <thead>
            <tr>
                <th><f:message key="homeTeam"/></th>
                <th><f:message key="awayTeam"/></th>
                <th><f:message key="stadium"/></th>
                <th><f:message key="score"/></th>
                <th><f:message key="date"/></th>

            </tr>
            </thead>
            <tbody>

            <c:forEach items="${matches}" var="match">
                <tr>
                    <td>${match.homeTeam.name}</td>
                    <td>${match.awayTeam.name}</td>
                    <td>${match.stadium}</td>

                    <c:choose>
                    <c:when test="${match.isFinished() }">
                        <td>${match.getHomeTeamGoals()}:${match.getAwayTeamGoals()}</td>
                    </c:when>
                    <c:otherwise>
                        <td>-:-</td>
                    </c:otherwise>
                    </c:choose>

                    <td>${match.date}</td>

                    <td><my:a href="/match/view/${match.id}" class="btn btn-primary"><f:message key="view"/></my:a></td>
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