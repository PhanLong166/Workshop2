<%-- 
    Document   : FlightView
    Created on : Mar 4, 2024, 1:47:53 PM
    Author     : Admin
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="dto.Flight"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="dao.DAO"%>
<%@page import="dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            User loginUser = (User) session.getAttribute("user");
        %>
        <h1>Welcome to FPT Airline, <%= loginUser.getFullname()%></h1>
        <a href="UserController?action=Logout">Logout</a>
        <br>
        <table border="1">
            <tr>
                <th>No</th>
                <th>Flight Number</th>
                <th>Destination</th>
                <th>Department Time</th>
                <th>Time</th>
                <th>Status</th>
            </tr>
            <%
                DAO dao = new DAO();
                int no = 1;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime now = LocalDateTime.now();
                String currently = now.format(formatter);
                List<Flight> flightList = dao.getFlightList("");
                for(Flight flight : flightList){
            %>
            <tr>
                <td><%= no++%></td>
                <td><%= flight.getFlightNumber()%></td>
                <td><%= flight.getDestination()%></td>
                <td><%= flight.getDepartureTime()%></td>
                <td style="color: <%= flight.isDelayed() == 1 ? "red" : "green" %>">
                    <%= flight.isDelayed() == 1? "Delay" : "On Time"%>
                </td>
                <%
                    LocalDateTime flightTime = LocalDateTime.parse(flight.getDepartureTime(), formatter);
                    LocalDateTime dateTime = LocalDateTime.parse(currently,formatter);
                    int result = dateTime.compareTo(flightTime);
                %>
                <td style="color: <%= result < 0 || flight.isDelayed() == 1 ? "red" : "green"%>">
                    <%= result < 0 || flight.isDelayed() == 1 ? "Standby" : "Departed"%>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
