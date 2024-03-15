<%-- 
    Document   : login
    Created on : Mar 11, 2024, 3:29:44 PM
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
        <form action="LoginController" method="POST">
            User ID: <input type="text" name="username" required /> 
            <br>
            Password: <input type="password" name="password" required />
            <br>
            <p style="background-color:red;color:white;display:inline-block">${noti}</p>
            <input name="action" type="submit" value="Login" /> 
        </form>
    </body>
</html>
