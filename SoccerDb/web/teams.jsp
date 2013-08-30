<%-- 
    Document   : teams
    Created on : Aug 15, 2013, 7:43:31 PM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teams</title>
    </head>
    <body>
        <h1>All Teams</h1>
        <p><a href="TeamServlet?action=menu">Menu</a></p>
        <p><a href="TeamServlet?action=addTeam">Add team</a></p>        
        <table border=1>
            <thead>
                <tr>
                    <th>Team</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${teams}" var = "team">
                    <tr>
                        <td><c:out value="${team.name}" /></td>
                        <td><a href="TeamServlet?action=delete&id=<c:out value="${team.id}" />">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>        
    </body>
</html>
