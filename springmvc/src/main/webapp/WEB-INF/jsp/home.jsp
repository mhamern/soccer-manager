<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<my:pagetemplate>
    <jsp:attribute name="body">
        <div class="jumbotron">
            <h1>Welcome to Soccer Manager!</h1>
            <c:if test="${not empty authenticatedUser}">
                <h4>You are logged in as <c:out value="${authenticatedUser.name}"/>.</h4>
                <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/matches/all"
                        role="button">Start playing now!</a></p>
            </c:if>
            <c:if test="${empty authenticatedUser}">
                <h4>Login to play.</h4>
                <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/auth/login"
                  role="button">Login</a></p>
            </c:if>
        </div>
    </jsp:attribute>
</my:pagetemplate>