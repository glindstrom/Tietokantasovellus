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
        <h2> Football database </h2>
        <form id="login" method="POST" action="LoginServlet">
            <h1>Log In</h1>
            <fieldset id="inputs">
                <input id="username" type="text" placeholder="Username" autofocus required>   
                <input id="password" type="password" placeholder="Password" required>
            </fieldset>
            <fieldset id="actions">
                <input type="submit" id="submit" value="Log in">
                <a href="/SoccerDb/register.jsp">Register</a>
            </fieldset>
        </form>
    </body>
</html>
