<%-- 
    Document   : login
    Created on : Aug 12, 2013, 12:54:24 PM
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Football database</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>

    <body>
        <h1> Football database </h1>
        <form id="login" method="POST" action="LoginServlet">
            <h2>Log In</h2>
            <fieldset id="inputs">
                <input name="username" type="text" placeholder="Username" autofocus required>   
                <input name="password" type="password" placeholder="Password" required>
            </fieldset>
            <fieldset id="actions">
                <input type="submit" id="submit" value="Log in">
                <a href="/SoccerDb/register.jsp">Register</a>
            </fieldset>
            ${message}
        </form>
    </body>
</html>
