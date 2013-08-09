<%-- 
    Document   : Register
    Created on : Aug 9, 2013, 2:09:02 PM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User registration</title>
    </head>
    <body>
        <form method="POST" action='RegisterServlet' name="register"><input
                type="hidden" name="action" value="insert" />
            <p><b>Add New User</b></p>
            <table>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="username" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" name="password" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Register" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
