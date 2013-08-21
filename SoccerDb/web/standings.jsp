<%-- 
    Document   : standings
    Created on : Aug 21, 2013, 12:11:43 PM
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
        <form action ='StandingsServlet' method="POST">
            <p><b>Add New Game</b></p>
            <p>Start by selecting the league and season</p>
            <p> Select league:
                <select name="leagueId">
                    <c:forEach items="${leagues}" var = "league">
                        <option value="${league.id}"><c:out value="${league.name}" /></option>
                    </c:forEach>
                </select>
            </p>
            <p> Select season
                <select name="seasonId">
                    <c:forEach items="${seasons}" var = "season">
                        <option value="${season.id}"><c:out value="${season.name}" /></option>
                    </c:forEach>
                </select>
            </p>
            <p> <input type="submit" value="Go" /> </p>
        </form>
    </body>
</html>
