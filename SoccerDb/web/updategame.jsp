<%-- 
    Document   : updategame
    Created on : Aug 27, 2013, 6:28:58 PM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Games</title>
    </head>
    <body>
        <form action ='GameServlet' method="POST">
            <h1>Update Score</h1>
            <table border=1>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>League</th>
                        <th>Season</th> 
                        <th>Home Team</th>
                        <th>Away Team</th>
                        <th>Home Score</th>
                        <th>Away Score</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><c:out value="${game.dateAsString}" /></td>
                        <td><c:out value="${game.leagueName}" /></td>
                        <td><c:out value="${game.seasonName}" /></td>
                        <td><c:out value="${game.homeTeamName}" /></td>
                        <td><c:out value="${game.awayTeamName}" /></td>
                        <td><input autofocus type="number" name="homeScore" min="0" required value ="<c:out value="${game.homeScoreAsString}" />"/></td>
                        <td><input type="number" name="awayScore" min="0" required  value ="<c:out value="${game.awayScoreAsString}" />"/></td>
                    </tr>
                </tbody>
            </table>
            <p> <input type="submit" value="Update" />
                <input type="hidden" name="action" value="updateScore" /> </p>
        </form>
    </body>
</html>
