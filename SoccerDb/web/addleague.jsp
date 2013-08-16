<%-- 
    Document   : addleague
    Created on : Aug 16, 2013, 1:51:18 PM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add League</title>
    </head>
    <body>
        <form method="POST" action='LeagueServlet' name="add"><input
                type="hidden" name="action" value="add" />
            <p><b>Add New League</b></p>
            <table>
                <tr>
                    <td>League name</td>
                    <td><input type="text" name="name" autofocus required /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
