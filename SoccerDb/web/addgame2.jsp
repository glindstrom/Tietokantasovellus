<%-- 
    Document   : addgame2
    Created on : Aug 21, 2013, 12:19:08 AM
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
            <p><b>League:</b> <c:out value="${league.name}" /> <b>Season:</b> <c:out value="${season.name}" /></p>
            <table>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Home Team</th>
                        <th>Away Team</th>
                        <th>Home score</th>
                        <th>Away score</th>                     
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="text" name="date" placeholder="YYYY-MM-DD" autofocus required /></td>
                        <td><select name="homeTeamId">
                                <c:forEach items="${teams}" var = "homeTeam">
                                    <option value="${homeTeam.id}"><c:out value="${homeTeam.name}" /></option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><select name ="awayTeamId">
                                <c:forEach items="${teams}" var = "awayTeam">
                                    <option value="${awayTeam.id}"><c:out value="${awayTeam.name}" /></option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><input type="text" name="homeScore" /></td>
                        <td><input type="text" name="awayScore" /></td>
                    </tr>
                </tbody>
            </table>

            <p> <input type="submit" value="Add" /> </p>
        </form>
    </body>
</html>
