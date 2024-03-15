<%-- 
    Document   : FlightManagement
    Created on : Mar 4, 2024, 1:47:23 PM
    Author     : Admin
--%>

<%@page import="dto.Flight"%>
<%@page import="java.util.List"%>
<%@page import="dao.DAO"%>
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
        <h1>Welcome to Airplane Management, <%= loginUser.getFullname()%></h1>
        <a href="AdminController?action=Logout">Logout</a>
        <br>
        <table border="1">
            <tr>
                <th>No</th>
                <th>Flight Number</th>
                <th>Destination</th>
                <th>Department Time</th>
                <th>isDelayed?</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%
                DAO dao = new DAO();
                int no = 1;
                List<Flight> flightList = dao.getFlightList("");
                for(Flight flight : flightList){
            %>
            <form action="AdminController" method="POST">
                <tr>
                    <td><%= no++%></td>
                    <td><%= flight.getFlightNumber()%></td>
                    <td>
                        <input type="text" name="destination" value="<%= flight.getDestination()%>">
                    </td>
                    <td>
                        <input type="text" name="departureTime" value="<%= flight.getDepartureTime()%>">
                    </td>
                    <td>
                        <input type="text" name="delayed" value="<%= flight.isDelayed()%>">
                    </td>
                    <td>
                        <input type="submit" name="action" value="Update">
                        <input type="hidden" name="flightNumber" value="<%= flight.getFlightNumber()%>">
                        <input type="hidden" name="search" value="">
                    </td>
                    <td>
                        <a href="AdminController?action=Delete&flightNumber=<%= flight.getFlightNumber()%>">Delete</a>
                    </td>
                </tr>
            </form>
            <%
                }
            %>
        </table>
        <br>
        <form action="FlightManagement.jsp">
            <input type="submit" name="action" value="Add">
        </form>
        <%
            String action = request.getParameter("action");
            if(action!= null && action.equalsIgnoreCase("Add")){
        %>
        <h1>Add new flight</h1>
        <form action="AdminController" method="POST">
            Flight Number: <input type="text" name="flightNumber">
            <span style="color: red">${errorFormat}</span><br>
            Destination: <input type="text" name="destination"><br>
            Departure Time: <input type="text" name="departureTime">
            <span style="color: red">${errorDatetime}</span><br>
            isDelayed? <input type="checkbox" name="delayed" value="true"><br>
            <input type="submit" name="action" value="Save">
        </form>
        <%
            }
        %>
        <p style="color: green">${noti}</p>
        <p style="color: red">${alert}</p>
    </body>
</html>
