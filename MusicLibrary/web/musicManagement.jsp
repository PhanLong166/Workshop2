<%-- 
    Document   : musicManagement
    Created on : Feb 26, 2024, 9:55:08 AM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="dto.Music"%>
<%@page import="dto.Music"%>
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
        <h1>Welcome to Music Management, <%= loginUser.getFullname()%></h1>
        <a href="AdminController?action=Logout" class="logout">Logout</a>
        <form action="AdminController">
            <input type="text" placeholder="Search by name or artist..." name="search" value="<%=search%>"/>
            <input type="submit" name="action" value="Search"/>
        </form>
        <br>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Artist</th>
                    <th>Duration</th>
                    <th>Source</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Music> musicList = (List) request.getAttribute("LIST_MUSIC");
                    if (musicList != null) {
                        for (Music m : musicList) {

                %>
            <form action="AdminController" method="POST">
                <tr>
                    <td><%= m.getId()%></td>
                    <td>
                        <input type="text" name="name" value="<%= m.getName()%>">
                    </td>
                    <td>
                        <input type="text" name="artist" value="<%= m.getArtist()%>">
                    </td>
                    <td id="duration">0:00</td>
                    <td>
                        <input type="text" name="src" value="<%= m.getSrc()%>">
                    </td>
                    <td>
                        <input type="submit" name="action" value="Update">
                        <input type="hidden" name="id" value="<%= m.getId()%>">
                        <input type="hidden" name="search" value="<%= search%>">
                    </td>
                    <td>
                        <input type="hidden" name="mobileId" value="<%= m.getId()%>">
                        <a href="AdminController?search=<%=search%>&action=Delete&id=<%=m.getId()%>">Delete</a>
                    </td>
                <audio id="myAudio" controls hidden>
                    <source src="<%= m.getSrc()%>" type="audio/mpeg"/>
                </audio>
                </tr>
            </form>
            <%
                    }
                }
            %>
        </tbody>
    </table>
    <form action="musicManagement.jsp" method="POST">
        <input type="submit" name="status" value="Add"/>
    </form>
    <%
        String status = request.getParameter("status");
        if (status != null && status.equals("Add")) {
    %>
    <h1>Add new song</h1>
    <form action="AdminController" method="POST">
        Name: <input type="text" name="name"><br>
        Artist: <input type="text" name="artist"><br>
        Source: <input type="text" name="source"><br>
        <input type="submit" name="action" value="Save"/>
    </form>
    <%
        }
    %>
    <p>${noti}</p>
    <script>
        var audio = document.querySelectorAll('#myAudio');
        var durationDisplay = document.querySelectorAll('#duration');
        
        for(let i = 0; i < audio.length;i++){
            var mins = Math.floor(audio[i].duration / 60);
            var secs = Math.floor(audio[i].duration % 60);
            durationDisplay[i].textContent = mins + ":" + (secs < 10 ? "0" : "") + secs;
        }
        
        
        // Update duration display when audio is loadedmetadata
//        function formatTime(seconds) {
//            var totalMinutes = Math.floor(seconds / 60);
//            var totalSeconds = Math.floor(seconds % 60);
//            return totalMinutes + ":" + (totalSeconds < 10 ? "0" : "") + totalSeconds;
//        }
//        
//        function loadDurationTime(){
//            var audio = document.querySelectorAll('#myAudio');
//            var durationDisplay = document.querySelectorAll('#duration');
//            for(let i = 0; i < audio.length;i++){
//                durationDisplay[i].textContent = formatTime(audio[i].duration);
//            }
//        }
//        
//        loadDurationTime();
    </script>
</body>
</html>
