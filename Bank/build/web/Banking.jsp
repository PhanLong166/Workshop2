<%-- 
    Document   : Inventory
    Created on : Feb 29, 2024, 4:45:32 PM
    Author     : Admin
--%>

<%@page import="dao.DAO"%>
<%@page import="dto.Account"%>
<%@page import="java.util.List"%>
<%@page import="dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <%
            User loginUser = (User) session.getAttribute("user");
            DAO dao = new DAO();
            Account acc = dao.getAccount(loginUser.getUsername());
        %>
        <h1>Welcome to Banking, <%= loginUser.getFullname()%></h1>
        <a href="MainController?action=Logout" class="logout">Logout</a>
        <br>
        <h2>Account balance: <%= acc.getBalance()%></h2>
        <form action="Banking.jsp">
            <input type="submit" name="status" value="Deposit">
            <input type="submit" name="status" value="Withdraw">
        </form>
        <%
            String status = request.getParameter("status");
            if(status != null && status.equalsIgnoreCase("Deposit")){
        %>
        <h1>Deposit Money</h1>
        <form action="MainController" method="POST">
            <input type="number" name="deposit" min="0">
            <input type="submit" name="action" value="Deposit">
            <input type="hidden" name="id" value="<%= acc.getId()%>">
            <input type="hidden" name="balance" value="<%= acc.getBalance()%>">
        </form>
        <% 
            } else if (status != null && status.equalsIgnoreCase("Withdraw")){
        %>
        <h1>Withdraw Money</h1>
        <form action="MainController" method="POST">
            <input type="number" name="withdraw" min="0">
            <input type="submit" name="action" value="Withdraw">
            <input type="hidden" name="id" value="<%= acc.getId()%>">
            <input type="hidden" name="balance" value="<%= acc.getBalance()%>">
        </form>
        <%
            }
        %>
        <p style="color: red">${alert}</p>
    </body>
</html>
