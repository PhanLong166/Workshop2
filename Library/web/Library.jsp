<%-- 
    Document   : Library
    Created on : Mar 11, 2024, 3:29:56 PM
    Author     : Admin
--%>

<%@page import="dto.User"%>
<%@page import="java.util.List"%>
<%@page import="dto.Library"%>
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
        %>
        <h1>Hello user: <%= loginUser.getFullname()%> </h1>
        <p style="background-color:red;color:white;display:inline-block">${message}</p>
        <form action="ClientController">
            Min price: <input type="number" min="0" name="minPrice" />
            Max price: <input type="number" min="0" name="maxPrice" />
            <input type="submit" value="search" name="action" /> 
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Book ID</th> 
                    <th>Book name</th> 
                    <th>Price</th>
                    <th>Author</th>
                    <th>Category</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    List<Library> list = (List) request.getAttribute("books");
                    if (list != null) {
                        for (Library i : list) {
                            count++;
                %>
            <form action="ClientController" method="POST">
                <tr>
                    <td><%= count%></td>
                    <td><%= i.getBookID()%></td>
                    <td><%= i.getBookName()%></td>
                    <td><%= i.getPrice()%></td>
                    <td><%= i.getAuthor()%></td>
                    <td><%= i.getCategory()%></td>
                    <td><%= i.getQuantity()%></td>
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
