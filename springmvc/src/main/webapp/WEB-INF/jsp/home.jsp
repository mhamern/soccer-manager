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
            <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/matches/all"
                  role="button">Start playing now!</a></p>
        </div>
    </jsp:attribute>
</my:pagetemplate>