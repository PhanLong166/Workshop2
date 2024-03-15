/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
    
    private static final String LOGIN_PAGE = "Login.jsp";
    private static final String ADMIN_PAGE = "Management.jsp";
    private static final String VIEW_PAGE = "Library.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        if (action == null) {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }

        if (action.equalsIgnoreCase("Login")) {
            String username = request.getParameter("username");
            int password = Integer.parseInt(request.getParameter("password"));

            DAO dao = new DAO();
            User user = dao.checkLogin(username, password);

            if (user == null) {
                request.setAttribute("noti", "Incorrect username or password");
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                if (user.getRole() == 0) {
                    request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
                } else {
                    request.getRequestDispatcher(VIEW_PAGE).forward(request, response);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
