<%-- 
    Document   : Management
    Created on : Mar 11, 2024, 3:31:23 PM
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
        <title>Admin Page</title>
    </head>
    <body>
        <%
            User loginUser = (User) session.getAttribute("user");
        %>
        <h1>Hello user: <%= loginUser.getFullname()%> </h1>
        <p style="background-color:red;color:white;display:inline-block">${message}</p>
        <form action="LibraryController">
            <input type="text" name="query" />
            <input type="submit" value="search" name="action" /> 
        </form>
        <form action="Management.jsp">
            <input type="submit" value="Add" name="action" /> 
        </form>
        <%
            String action = request.getParameter("action");
            if (action != null && action.equalsIgnoreCase("Add")) {
        %>
        <form action="LibraryController" method="POST">

            Book ID: <input type="text" name="bookID" required />
            Book Name: <input type="text" name="bookName" required />
            Price: <input type="number" name="price" required min="0" max="9999" />
            Author:<input type="text" name="author" required />
            Category:<input type="text" name="category" required />
            Quantity: <input type="number" name="quantity" min="0" max="100" required />
            <input type="submit" name="action" value="add" />
        </form>
        <% } %>
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
                    List<Library> list = (List) request.getAttribute("bookList");
                    if (list != null) {
                        for (Library i : list) {
                            count++;
                %>
            <form action="LibraryController" method="POST">
                <tr>
                    <td><%= count %></td>
                    <td><%= i.getBookID() %></td>
                    <td><input type="text" name="bookName" value="<%= i.getBookName() %>"></td>
                    <td><input type="text" min="0" max="9999" name="price" value="<%= i.getPrice()%>"></td>
                    <td><input type="text" name="author" value="<%= i.getAuthor()%>"></td>
                    <td><input type="text" name="category" value="<%= i.getCategory()%>"></td>
                    <td><input type="text" name="quantity" min="0" max="100" value="<%= i.getQuantity()%>"></td>
                    
                    <td>
                        <input type="hidden" name="bookID" value="<%= i.getBookID()%>">
                        <a href="LibraryController?action=delete&bookID=<%= i.getBookID()%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" name="action" value="update">
                        <input type="hidden" name="bookID" value="<%= i.getBookID()%>">
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
