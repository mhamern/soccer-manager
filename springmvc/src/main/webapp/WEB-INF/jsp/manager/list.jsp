
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="a" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="Managers">
    <jsp:attribute name="body">
        <c:if test="${not empty authenticatedUser && authenticatedUser.isAdmin()}">
            <td><my:a href="/manager/new" class="btn btn-success"><f:message key="create"/></my:a></td>
        </c:if>
        <table class="table table-striped">
            <thead>
            <tr>
                <th><f:message key="name"/></th>
                <th><f:message key="nationality"/></th>
                <th><f:message key="team"/></th>
                <c:if test="${not empty authenticatedUser && authenticatedUser.isAdmin()}">
                    <th><f:message key="email"/></th>
                </c:if>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${managers}" var="manager">
                <tr>

                    <c:if test="${not manager.admin}">
                        <td>${manager.name}</td>
                        <td>${manager.nationality}</td>
                        <td> - </td>
                        <c:if test="${not empty authenticatedUser && authenticatedUser.isAdmin()}">
                            <td>${manager.email}</td>
                        </c:if>

                        <td><my:a href="/manager/view/${manager.id}" class="btn btn-primary"><f:message key="view"/></my:a></td>
                        <c:if test="${not empty authenticatedUser && authenticatedUser.isAdmin()}">
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/manager/delete/${manager.id}">
                                    <button type="submit" class="btn btn-danger">
                                        <f:message key="delete"></f:message>
                                    </button>
                                </form>
                            </td>
                        </c:if>
                    </c:if>

                </tr>
            </c:forEach>
            </tbody>
        </table>

        <c:if test="${not empty authenticatedUser && authenticatedUser.isAdmin()}">

            <h2><f:message key="navigation.admins"/></h2>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><f:message key="name"/></th>
                    <th><f:message key="nationality"/></th>
                    <th><f:message key="email"/></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${admins}" var="admin">
                    <td>${admin.name}</td>
                    <td>${admin.nationality}</td>
                    <td>${admin.email}</td>
                    <td><my:a href="/manager/view/${admin.id}" class="btn btn-primary"><f:message key="view"/></my:a></td>

                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/manager/delete/${manager.id}">
                            <button type="submit" class="btn btn-danger">
                                <f:message key="delete"></f:message>
                            </button>
                        </form>
                    </td>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </jsp:attribute>
</my:pagetemplate>