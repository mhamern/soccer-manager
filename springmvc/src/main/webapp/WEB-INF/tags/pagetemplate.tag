<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width" initial-scale="1">
        <title>Soccer Manager Game</title>
        <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <c:if test="${not empty authenticatedUser && authenticatedUser.isAdmin()}">
                        <a class="navbar-brand" href="${pageContext.request.contextPath}">Soccer Manager Administration</a>
                    </c:if>
                    <c:if test="${empty authenticatedUser || !authenticatedUser.isAdmin()}">
                        <a class="navbar-brand" href="${pageContext.request.contextPath}">Soccer Manager</a>
                    </c:if>
                </div>
                <div id="navbar" class="collapse navbar-collapse">>
                    <ul class="nav navbar-nav">
                            <li><my:a href="/team/list/">Teams</my:a></li>
                            <li><my:a href="/player/list/">Players</my:a></li>
                            <li><my:a href="/league/list/">Leagues</my:a></li>
                            <li><my:a href="/match/list/">Matches</my:a></li>
                            <li><my:a href="/manager/list/">Managers</my:a></li>
                        <c:if test="${empty authenticatedUser}">
                            <li><my:a href="/auth/login">Login</my:a></li>
                        </c:if>
                        <c:if test="${not empty authenticatedUser}">
                        <li><my:a href="/auth/logout">Logout</my:a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <c:if test="${not empty title}">
                <div class="page-header">
                    <h1><c:out value="${title}"/></h1>
                </div>
            </c:if>
            <c:if test="${not empty authenticatedUser}">
                <div class="row">
                    <div class="col-xs-6 col-sm-8 col-md-9 col-lg-10"></div>
                    <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <c:out value="${authenticatedUser.name}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <jsp:invoke fragment="body"/>

            <footer class="footer">
                <p>&copy;&nbsp;<%=java.time.Year.now().toString()%>&nbsp;Martin Hamernik</p>
                <p>&copy;&nbsp;<%=java.time.Year.now().toString()%>&nbsp;Filip Lux</p>
                <p>&copy;&nbsp;<%=java.time.Year.now().toString()%>&nbsp;Iman Mehmandoust</p>
            </footer>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </body>
</html>