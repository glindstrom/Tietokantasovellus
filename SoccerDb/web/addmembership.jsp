<%-- 
    Document   : addmembership
    Created on : Aug 19, 2013, 3:10:27 PM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Membership</title>
    </head>
    <body>
        <form action ='MembershipServlet' method="POST">
            <p><b>Add New Membership</b></p>
            <p> Select league:
                <select name="leagueId">
                    <c:forEach items="${leagues}" var = "league">
                        <option value="${league.id}">${league.name}</option>
                    </c:forEach>
                </select>
            </p>
            <p> Select team:
                <select name="teamId">
                    <c:forEach items="${teams}" var = "team">
                        <option value="${team.id}">${team.name}</option>
                    </c:forEach>
                </select>
            </p>
            <p> Select season
                <select name="seasonId">
                    <c:forEach items="${seasons}" var = "season">
                        <option value="${season.id}">${season.name}</option>
                    </c:forEach>
                </select>
            </p>
            <p> <input type="submit" value="Add" /> </p>
        </form>
    </body>
</html>
