<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate>
    <jsp:attribute name="body">
        <div class="jumbotron">
            <h1><f:message key="home.welcome"/></h1>
            <c:if test="${not empty authenticatedUser}">
                <h4><f:message key="home.loggedinname"/>  <c:out value="${authenticatedUser.name}"/>.</h4>
                <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/match/list"
                        role="button"><f:message key="home.loggedintext"/></a></p>
            </c:if>
            <c:if test="${empty authenticatedUser}">
                <h4><f:message key="home.notloggedintext"/></h4>
                <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/auth/login"
                  role="button"><f:message key="navigation.login"/></a></p>
            </c:if>
        </div>
    </jsp:attribute>
</my:pagetemplate>