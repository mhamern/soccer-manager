<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="a" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="${league.name}">
    <jsp:attribute name="body">
        <table class="table">
        <tbody>
            <tr>
                <td><b>Name:</b> ${league.name}</td>
            </tr>
            <tr>
                <td><b>Country:</b> ${league.country}</td>
            </tr>
            </tbody>
        </table>
        <h2>Players</h2>
        <table class="table">
            <thead>
                <tr>
                    <td><b>Name</b></td>
                    <td><b>Country</b></td>
                </tr>
            </thead>
        </table>

    </jsp:attribute>
</my:pagetemplate>