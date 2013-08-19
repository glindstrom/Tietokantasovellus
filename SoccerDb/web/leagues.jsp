<%-- 
    Document   : leagues
    Created on : Aug 15, 2013, 7:43:31 PM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leagues</title>
    </head>
    <body>
        <h1>All Leagues</h1>
        <table border=1>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>League</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${leagues}" var = "league">
                    <tr>
                        <td><c:out value="${league.id}" /></td>
                        <td><c:out value="${league.name}" /></td>
                        <td><a href="LeagueServlet?action=delete&id=<c:out value="${league.id}" />">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="LeagueServlet?action=addleague">Add league</a></p>
        <p><a href="LeagueServlet?action=menu">Menu</a></p>
    </body>
</html>
