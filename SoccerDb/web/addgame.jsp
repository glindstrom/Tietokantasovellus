<%-- 
    Document   : addgame
    Created on : Aug 20, 2013, 8:53:54 PM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Game</title>
    </head>
    <body>        
        <form action ='GameServlet' method="POST">
            <p><b>Add New Game</b></p>
            <p>Start by selecting the league and season for the game</p>
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
            <p> <input type="submit" value="Continue" /><input
                type="hidden" name="step" value="first" /> </p>
        </form>
    </body>
</html>
