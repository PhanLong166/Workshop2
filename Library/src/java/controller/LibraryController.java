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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ha Chang
 */
public class LibraryController extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User session = (User) req.getSession().getAttribute("user");
        if (session == null) {
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        }
        
        String action = req.getParameter("action");
        if (action == null) {
            req.getRequestDispatcher("Management.jsp").forward(req, resp);
        }
        
        if (action != null) {
            if (action.equalsIgnoreCase("search")) {
                DAO dao = new DAO();
                String query = req.getParameter("query");
                List<Library> bookList;
                if (query == null || query.isEmpty()) {
                    bookList = dao.getAllBooks();
                } else {
                    bookList = dao.getAllByIdAndName(query, query);
                }
                req.setAttribute("bookList", bookList);
                req.getRequestDispatcher("Management.jsp").forward(req, resp);
            }

            if (action.equalsIgnoreCase("add")) {
                DAO dao = new DAO();
                String bookID = req.getParameter("bookID");
                String bookName = req.getParameter("bookName");
                String price = req.getParameter("price");
                String author = req.getParameter("author");
                String category = req.getParameter("category");
                String quantity = req.getParameter("quantity");

                boolean response = dao.createBook(new Library(bookID, bookName, Float.parseFloat(price), author, category, Integer.parseInt(quantity)));
                if (response == true) {
                    req.setAttribute("message", "Book created successfully.");
                } else {
                    req.setAttribute("message", "Failed to create book.");
                }
                req.getRequestDispatcher("Management.jsp").forward(req, resp);
            }

            if ("delete".equalsIgnoreCase(action)) {
                DAO dao = new DAO();
                String bookID = req.getParameter("bookID");
                boolean response = dao.deleteBook(bookID);
                if (response == true) {
                    req.setAttribute("message", "Delete book successfully.");
                } else {
                    req.setAttribute("message", "Failed to delete book.");
                }
                req.getRequestDispatcher("Management.jsp").forward(req, resp);
            }
            
            if ("update".equalsIgnoreCase(action)) {
                DAO dao = new DAO();
                String bookID = req.getParameter("bookID");
                String bookName = req.getParameter("bookName");
                String price = req.getParameter("price");
                String author = req.getParameter("author");
                String category = req.getParameter("category");
                String quantity = req.getParameter("quantity");

                
                boolean response = dao.updateBook(bookID, bookName, Float.parseFloat(price), author, category, Integer.parseInt(quantity));
                if (response == true) {
                    req.setAttribute("message", "Book saved successfully.");
                } else {
                    req.setAttribute("message", "Failed to saved book.");
                }
                req.getRequestDispatcher("Management.jsp").forward(req, resp);
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
