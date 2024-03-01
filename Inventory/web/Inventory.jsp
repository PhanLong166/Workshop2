<%-- 
    Document   : Inventory
    Created on : Feb 29, 2024, 4:45:32 PM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="dto.Product"%>
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
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <h1>Welcome to Inventory View, <%= loginUser.getFullname()%></h1>
        <a href="UserController?action=Logout" class="logout">Logout</a>
        <form action="UserController">
            <input type="text" placeholder="Search by product..." name="search" value="<%=search%>"/>
            <input type="submit" name="action" value="Search"/>
        </form>
        <br>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Product> productList = (List) request.getAttribute("PRODUCT_LIST");
                    if (productList != null) {
                        for (Product p : productList) {

                %>
            <form action="AdminController" method="POST">
                <tr>
                    <td><%= p.getID()%></td>
                    <td><%= p.getName()%></td>
                    <td><%= p.getType()%></td>
                    <td><%= p.getQuantity()%></td>
                    <td><%= p.getPrice()%></td>
                    <td>
                        <span style="color: red"><%= p.getQuantity() < 10 ? "Low inventory" : ""%></span>
                    </td>
                </tr>
            </form>
            <%
                    }
                }
            %>
        </tbody>
    </table>
    </body>
</html>
