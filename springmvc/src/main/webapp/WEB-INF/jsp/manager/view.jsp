<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="a" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="${manager.name}">
    <jsp:attribute name="body">
        <table class="table">
        <tbody>
            <tr>
                <td><b><f:message key="nationality"/>:</b> ${manager.nationality}</td>
            </tr>
            <tr>
                <td><b><f:message key="email"/>:</b> ${manager.email}</td>
            </tr>
            </tbody>
        </table>
        <c:if test="${not empty managersTeam}">
            <h2><f:message key="team"/></h2>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><f:message key="name"/></th>
                    <th><f:message key="country"/></th>
                    <th><f:message key="league"/></th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${managersTeam.name}</td>
                        <td>${managersTeam.origin}</td>
                        <td>${managersTeam.league.name}</td>
                        <td><my:a href="/team/view/${managersTeam.id}" class="btn btn-primary"><f:message key="view"/></my:a></td>
                    </tr>
                </tbody>
            </table>
        </c:if>
    </jsp:attribute>
</my:pagetemplate>