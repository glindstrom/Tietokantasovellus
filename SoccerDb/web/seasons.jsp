<%-- 
    Document   : seasons
    Created on : Aug 19, 2013, 11:38:28 AM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seasons</title>
    </head>
    <body>
        <h1>All Seasons</h1>
        <table border=1>
            <thead>
                <tr>
                    <th>Season</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${seasons}" var = "season">
                    <tr>
                        <td><c:out value="${season.name}" /></td>
                        <td><a href="SeasonServlet?action=delete&id=<c:out value="${season.id}" />">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="SeasonServlet?action=addseason">Add season</a></p>
        <p><a href="SeasonServlet?action=menu">Menu</a></p>
    </body>
</html>

