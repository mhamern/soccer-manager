
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="a" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="Teams">
    <jsp:attribute name="body">
        <c:if test="${not empty authenticatedUser && authenticatedUser.isAdmin()}">
            <td><my:a href="/team/new" class="btn btn-success"><f:message key="create"/></my:a></td>
        </c:if>
        <table class="table table-striped">
            <thead>
            <tr>
                <th><f:message key="name"/></th>
                <th><f:message key="country"/></th>
                <th><f:message key="league"/></th>
                <th><f:message key="manager"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${teams}" var="team">
                <tr>
                    <td>${team.name}</td>
                    <td>${team.origin}</td>
                    <td>${team.league.name}</td>
                    <td>${team.manager.name}</td>
                    <td><my:a href="/team/view/${team.id}" class="btn btn-primary"><f:message key="view"/></my:a></td>
                    <c:if test="${not empty authenticatedUser && authenticatedUser.isAdmin()}">
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/team/delete/${team.id}">
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