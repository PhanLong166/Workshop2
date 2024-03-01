<%-- 
    Document   : Management
    Created on : Feb 29, 2024, 4:45:44 PM
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
        <title>Admin Page</title>
    </head>
    <body>
        <%
            User loginUser = (User) session.getAttribute("user");
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <h1>Welcome to Inventory Management, <%= loginUser.getFullname()%></h1>
        <a href="AdminController?action=Logout" class="logout">Logout</a>
        <form action="AdminController">
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
                    <td>
                        <input type="text" name="name" value="<%= p.getName()%>">
                    </td>
                    <td>
                        <input type="text" name="type" value="<%= p.getType()%>">
                    </td>
                    <td>
                        <input type="number" name="quantity" value="<%= p.getQuantity()%>">
                    </td>
                    <td>
                        <input type="number" name="price" value="<%= p.getPrice()%>">
                    </td>
                    <td>
                        <input type="submit" name="action" value="Update">
                        <input type="hidden" name="id" value="<%= p.getID()%>">
                        <input type="hidden" name="search" value="<%= search%>">
                    </td>
                    <td>
                        <input type="hidden" name="mobileId" value="<%= p.getID()%>">
                        <a href="AdminController?search=<%=search%>&action=Delete&id=<%=p.getID()%>">Delete</a>
                    </td>
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
    <form action="Management.jsp" method="POST">
        <input type="submit" name="status" value="Add"/>
    </form>
        <p>${noti}</p>
    <%
        String status = request.getParameter("status");
        if (status != null && status.equals("Add")) {
    %>
    <h1>Add new song</h1>
    <form action="AdminController" method="POST">
        ID: <input type="text" name="id"><br>
        Name: <input type="text" name="name"><br>
        Type: <input type="text" name="type"><br>
        Quantity: <input type="number" name="quantity"><br>
        Price: <input type="number" name="price"><br>
        <input type="submit" name="action" value="Save"/>
    </form>
    <%
        }
    %>
</body>
</html>
