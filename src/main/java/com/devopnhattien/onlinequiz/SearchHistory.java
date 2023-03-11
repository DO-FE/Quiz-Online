/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.devopnhattien.onlinequiz;

import dao.QuizDAO;
import dto.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nhatt
 */
//@WebServlet(name = "SearchHistory", urlPatterns = {"/SearchHistory"})
public class SearchHistory extends HttpServlet {

    private final static String SUCCESS = "history.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            HttpSession session = request.getSession();
            AccountDTO loginUser = (AccountDTO) session.getAttribute("LOGIN_USER");
            String subjectID = request.getParameter("cmbSubject");
            
            QuizDAO quizDAO = new QuizDAO();
            int count = quizDAO.getCountQuizOfStudent(loginUser.getEmail(),subjectID);
            int totalPage=0;
            if (count % 5 != 0) {
                totalPage = (count / 5) + 1;
            } else {
                totalPage = (count / 5);
            }
            request.setAttribute("TOTAL_PAGE", totalPage);
            String currentPage = request.getParameter("txtCurrentPage");
            int page = 1;
            if (currentPage != null) {
                page = Integer.parseInt(currentPage);
            }
            
            if (loginUser != null && loginUser.getRoleID().equals("2")) {
                if (subjectID != null) {
                    List<String> history = quizDAO.getHistory(loginUser.getEmail(), subjectID,page);
                    if (history != null && history.size()>0) {
                        url = SUCCESS;
                        request.setAttribute("LIST_HISTORY", history);
                    } else {
                        request.setAttribute("MESSAGE", "Not found");
                    }
                }
            }
        } catch (Exception e) {
            log("Error at SearchHistoryServlet " + e.toString());
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
