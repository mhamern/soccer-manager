
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="a" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="Free agents">
    <jsp:attribute name="body">
        <c:if test="${not empty authenticatedUser && authenticatedUser.isAdmin()}">
            <td><my:a href="/player/new" class="btn btn-success"><f:message key="create"/></my:a></td>
        </c:if>
        <table class="table table-striped">
            <thead>
            <tr>
                <th><f:message key="name"/></th>
                <th><f:message key="nationality"/></th>
                <th><f:message key="born"/></th>
                <th><f:message key="position"/></th>
                <th><f:message key="number"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${freeagents}" var="player">
                <tr>
                    <td>${player.name}</td>
                    <td>${player.nationality}</td>
                    <td>${player.birthDate}</td>
                    <td>${player.position}</td>
                    <td>#${player.number}</td>
                    <td><my:a href="/player/view/${player.id}" class="btn btn-primary"><f:message key="view"/></my:a></td>
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
    </jsp:attribute>
</my:pagetemplate>