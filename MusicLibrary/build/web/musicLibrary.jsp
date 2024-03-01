<%-- 
    Document   : musicLibrary
    Created on : Feb 24, 2024, 12:49:46 PM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="dto.Music"%>
<%@page import="dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Music Library</title>
    </head>

    <%
        User loginUser = (User) session.getAttribute("user");

        String search = request.getParameter("search");
        if (search == null) {
            search = "";
        }
    %>
    <h1>Welcome to Music Library, <%= loginUser.getFullname()%></h1>
    <a href="UserController?action=Logout" class="logout">Logout</a>
    <form action="UserController">
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
            </tr>
        </thead>
        <tbody>
            <%
                List<Music> musicList = (List) request.getAttribute("LIST_MUSIC");
                if (musicList != null) {
                    for (Music m : musicList) {

            %>
            <form action="UserController" method="POST">
                <tr>
                    <td><%= m.getId()%></td>
                    <td><%= m.getName()%></td>
                    <td><%= m.getArtist()%></td>
                    <td>
                        <audio id="myAudio" controls="">
                            <source src="<%= m.getSrc()%>" type="audio/mpeg"/>
                        </audio>
                    </td>
                </tr>
            </form>
            <%
                    }
                }
            %>
        </tbody>
    </table>
    <br>
    <form action="UserController" method="GET">
        <button type="submit" name="action" value="Random">Random Song</button>
    </form>
    <%
        Music randomSong = (Music) request.getAttribute("randomSong");
        if (randomSong != null) {
    %>
    <h1>Random Song</h1>
    <h2>${randomSong.getName()}</h2>
    <p>${randomSong.getArtist()}</p>
    <audio controls autoplay>
        <source src="${randomSong.getSrc()}" type="audio/mpeg">
    </audio>
    <%
        }
    %>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var currentAudio = null;

            // Lắng nghe sự kiện khi một thẻ audio được click để phát
            document.querySelectorAll("audio").forEach(function (audio) {
                audio.addEventListener("play", function (event) {
                    var clickedAudio = event.target;
                    // Kiểm tra nếu đã có một thẻ audio khác đang phát
                    if (currentAudio && currentAudio !== clickedAudio) {
                        currentAudio.pause(); // Dừng thẻ audio đang phát
                    }
                    currentAudio = clickedAudio; // Đặt thẻ audio hiện tại là thẻ audio được click
                });
            });
        });
    </script>
</html>

