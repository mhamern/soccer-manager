
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="a" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Teams">
    <jsp:attribute name="body">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Country</th>
                <th>League</th>
                <th>Manager</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${teams}" var="team">
                <tr>
                    <td>${team.name}</td>
                    <td>${team.origin}</td>
                    <td>${team.league.name}</td>
                    <td>${team.manager.name}</td>
                    <td><my:a href ="/team/view/${team.id}" class="btn btn-primary">View details</my:a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>