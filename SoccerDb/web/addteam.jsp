<%-- 
    Document   : addteam
    Created on : Aug 16, 2013, 1:51:18 PM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Team</title>
    </head>
    <body>
        <form method="POST" action='TeamServlet' name="add"><input
                type="hidden" name="action" value="add" />
            <p><b>Add New Team</b></p>
            <table>
                <tr>
                    <td>Team name</td>
                    <td><input type="text" name="name" autofocus required /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Add" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
