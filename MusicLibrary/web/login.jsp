<%-- 
    Document   : index
    Created on : Feb 24, 2024, 11:27:01 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Music Library - Login</h1>
        <form action="LoginController" method="POST">
            Username: <input name="username" /> <br>
            Password: <input type="password" name="password" /><br>
            <p>${noti}</p>
            <input name="action" type="submit" value="Login"/>
        </form>
    </body>
</html>
