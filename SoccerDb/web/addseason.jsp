<%-- 
    Document   : addseason
    Created on : Aug 19, 2013, 12:25:00 PM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Season</title>
    </head>
    <body>
        <form method="POST" action='SeasonServlet' name="add"><input
                type="hidden" name="action" value="add" />
            <p><b>Add New Season</b></p>
            <table>
                <tr>
                    <td>Season name</td>
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
