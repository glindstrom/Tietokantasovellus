<%-- 
    Document   : viewstandings
    Created on : Aug 21, 2013, 1:23:46 PM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Standings</title>
    </head>
    <body>
        <p><b>Standings</b></p>
        <p><b>League:</b> <c:out value="${league.name}" /> <b>Season:</b> <c:out value="${season.name}" /></p>
        <table border=1>
            <thead>
                <tr>
                    <th title="Rank">#</th>
                    <th title="Team">Team</th>
                    <th title="Games played">GP</th>
                    <th title="Wins">W</th>
                    <th title="Draws">D</th>
                    <th title="Losses">L</th>
                    <th title="Goals for">GF</th>
                    <th title="Goals against">GA</th>
                    <th title="Team points">PTS</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${standings}" var = "standing">
                    <tr>
                        <td><c:out value="${standing.rank}" /></td>
                        <td><c:out value="${standing.teamName}" /></td>
                        <td><c:out value="${standing.gamesPlayed}" /></td>
                        <td><c:out value="${standing.wins}" /></td>
                        <td><c:out value="${standing.draws}" /></td>
                        <td><c:out value="${standing.losses}" /></td>
                        <td><c:out value="${standing.goalsFor}" /></td>
                        <td><c:out value="${standing.goalsAgainst}" /></td>
                        <td><c:out value="${standing.teamPoints}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="GameServlet?action=menu">Menu</a></p>
    </body>
</html>
