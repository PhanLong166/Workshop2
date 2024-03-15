/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import dto.Flight;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class SaveController extends HttpServlet {

    private static final String ERROR = "SearchController";
    private static final String SUCCESS = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String flightNumber = request.getParameter("flightNumber");
            String destination = request.getParameter("destination");
            String departureTime = request.getParameter("departureTime");
            String delayed = request.getParameter("delayed");
            
            boolean check = true;
//            if (!flightNumber.matches("^[A-Z]{3}\\d{3}$")) {
//                check = false;
//                request.setAttribute("errorFormat", "Wrong format 3 characters and 3 number. Ex: ABC123");
//            }
//
//            if (!departureTime.matches("\\d{4}\\-\\d{2}\\-\\d{2} \\d{2}\\:\\d{2}\\")) {
//                check = false;
//                request.setAttribute("errorDatetime", "Wrong format(yyyy-MM-dd HH:mm)");
//            }
            
            int isDelayed = 0;
            if(delayed != null && delayed.equals("true")){
                isDelayed = 1;
            }
            
            if (check) {
                Flight flight = new Flight(flightNumber, destination, departureTime, isDelayed);
                DAO dao = new DAO();
                boolean checkAdd = dao.addFlight(flight);
                if (checkAdd) {
                    request.setAttribute("noti", "Insert successfully");
                    url = SUCCESS;
                } else {
                    request.setAttribute("alert", "Save failed");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
