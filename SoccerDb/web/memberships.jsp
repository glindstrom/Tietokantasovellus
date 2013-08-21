<%-- 
    Document   : memberships
    Created on : Aug 19, 2013, 2:44:18 PM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Memberships</title>
    </head>
    <body>
        <h1>All Memberships</h1>
        <table border=1>
            <thead>
                <tr>
                    <th>League</th>
                    <th>Season</th> 
                    <th>Team</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${memberships}" var = "membership">
                    <tr>
                        <td><c:out value="${membership.leagueName}" /></td>
                        <td><c:out value="${membership.seasonName}" /></td>
                        <td><c:out value="${membership.teamName}" /></td>
                        <td><a href="MembershipServlet?action=delete&id=<c:out value="${membership.id}" />">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="MembershipServlet?action=addmembership">Add membership</a></p>
        <p><a href="MembershipServlet?action=menu">Menu</a></p>
    </body>
</html>
