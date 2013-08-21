<%-- 
    Document   : games
    Created on : Aug 20, 2013, 5:30:39 PM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Games</title>
    </head>
    <body>
        <h1>All Games</h1>
        <table border=1>
            <thead>
                <tr>
                    <th>Date</th>
                    <th>League</th>
                    <th>Season</th> 
                    <th>Home Team</th>
                    <th>Away Team</th>
                    <th>Score</th>
                    <th>Edited by</th>
                    <th>Last edited</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${games}" var = "game">
                    <tr>
                        <td><c:out value="${game.dateAsString}" /></td>
                        <td><c:out value="${game.leagueName}" /></td>
                        <td><c:out value="${game.seasonName}" /></td>
                        <td><c:out value="${game.homeTeamName}" /></td>
                        <td><c:out value="${game.awayTeamName}" /></td>
                        <td><c:out value="${game.homeScore}" />:<c:out value="${game.awayScore}" /></td>
                        <td><c:out value="${game.editedByUsername}" /></td>
                        <td><c:out value="${game.timestamp}" /></td>
                        <td><a href="GameServlet?action=delete&id=<c:out value="${game.id}" />">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="GameServlet?action=addgame">Add game</a></p>
        <p><a href="GameServlet?action=menu">Menu</a></p>
    </body>
</html>
