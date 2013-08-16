<%-- 
    Document   : welcome
    Created on : Aug 12, 2013, 6:32:57 PM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Football Database</title>
    </head>
    <body>
        <h1>MENU</h1>
        <a href="LeagueServlet?action=listLeagues">Manage leagues</a>
        <a href="TeamServlet?action=listTeams">Manage teams</a>
        <a href="SeasonServlet?action=listSeasons">Manage seasons</a>
        <a href="MembershipServlet?action=listMemberships">Manage memberships</a>
        <a href="GameServlet?action=">Manage games</a>
    </body>
</html>
