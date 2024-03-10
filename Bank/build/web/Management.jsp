<%-- 
    Document   : Management
    Created on : Feb 29, 2024, 4:45:44 PM
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
        <title>Admin Page</title>
    </head>
    <body>
        <%
            User loginUser = (User) session.getAttribute("user");
        %>
        <h1>Welcome to Bank Management, <%= loginUser.getFullname()%></h1>
        <a href="MainController?action=Logout" class="logout">Logout</a>
        <br>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Full Name</th>
                    <th>Username</th>
                    <th>Balance</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    DAO dao = new DAO();
                    List<Account> accountList = dao.getAccountList();
                    List<User> userList = dao.getUserList();
                    if (accountList != null) {
                        for (Account a : accountList) {
                            for(User u : userList){
                                if(u.getUsername().equals(a.getUsername())){
                            
                %>
            <form action="MainController" method="POST">
                <tr>
                    <td><%= a.getId()%></td>
                    <td>
                        <input type="text" name="fullname" value="<%= u.getFullname()%>">
                    </td>
                    <td>
                        <input type="text" name="username" value="<%= u.getUsername()%>">
                    </td>
                    <td><%= a.getBalance()%></td>
                    <td>
                        <input type="submit" name="action" value="Update">
                        <input type="hidden" name="id" value="<%= a.getId()%>">
                    </td>
                    <td>
                        <a href="MainController?action=Delete&username=<%=a.getUsername()%>">Delete</a>
                    </td>
                </tr>
            </form>
            <%
                            }
                        }
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
    <h1>Add new account</h1>
    <form action="MainController" method="POST">
        ID: <input type="text" name="id"><br>
        Username: <input type="text" name="username"><br>
        Password: <input type="text" name="password"><br>
        Fullname: <input type="text" name="fullname"><br>
        Balance: <input type="number" name="balance"><br>
        <input type="submit" name="action" value="Save"/>
        <input type="hidden" name="role" value="1"/>
    </form>
    <%
        }
    %>
</body>
</html>
