/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import dto.Library;
import dto.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ha Chang
 */
public class ClientController extends HttpServlet{
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User session = (User) req.getSession().getAttribute("user");
        if (session == null) {
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        }
        resp.setContentType("text/html;charset=UTF-8");

        
        String action = req.getParameter("action");
        if (action == null) {
            req.getRequestDispatcher("Library.jsp").forward(req, resp);
        }

        if ("search".equalsIgnoreCase(action)) {
            String minPrice = req.getParameter("minPrice");
            String maxPrice = req.getParameter("maxPrice"); 
            
            if (minPrice.equalsIgnoreCase("") || maxPrice.equalsIgnoreCase("")) {
                List<Library> books = new ArrayList<>();
                if (minPrice.equalsIgnoreCase("") && !maxPrice.equalsIgnoreCase("")) {
                    books.addAll(new DAO().getAllBooksWithCondition(-1, Float.parseFloat(maxPrice)));
                } else if (!minPrice.equalsIgnoreCase("") && maxPrice.equalsIgnoreCase("")) {
                    books.addAll(new DAO().getAllBooksWithCondition(Float.parseFloat(minPrice), -1));
                } else {
                    books.addAll(new DAO().getAllBooksWithCondition(-1, -1));
                }
                req.setAttribute("books", books);
                req.getRequestDispatcher("Library.jsp").forward(req, resp);
            } else {
                List<Library> books = new DAO().getAllBooksWithCondition(Float.parseFloat(minPrice), Float.parseFloat(maxPrice));
                req.setAttribute("books", books);
                req.getRequestDispatcher("Library.jsp").forward(req, resp);    
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
