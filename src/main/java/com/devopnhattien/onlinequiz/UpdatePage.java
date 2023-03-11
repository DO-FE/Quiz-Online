/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.devopnhattien.onlinequiz;

import dao.AnswerDAO;
import dao.QuestionDAO;
import dao.SubjectDAO;
import dto.AccountDTO;
import dto.AnswerDTO;
import dto.QuestionDTO;
import dto.SubjectDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nhatt
 */
public class UpdatePage extends HttpServlet {

    private final static String SUCCESS = "update.jsp";
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
            if (loginUser != null && loginUser.getRoleID().equals("1")) {
                String questionID = request.getParameter("txtQuestionID");
                QuestionDAO questionDao=new QuestionDAO();
                SubjectDAO subjectDao = new SubjectDAO();              
                AnswerDAO answerDao=new AnswerDAO();                
                QuestionDTO questiondto=questionDao.getQuestion(questionID);
                session.setAttribute("QUESTION_UPDATE", questiondto);               
                List<AnswerDTO> listAnswer = answerDao.getAnswersFromQuestionID(questionID);
                session.setAttribute("LIST_ANSWER_UPDATE", listAnswer);             
                List<SubjectDTO> listSubject = subjectDao.getAllSubject();
                if (listSubject != null) {
                    url = SUCCESS;
                    session.setAttribute("LIST_SUBJECT", listSubject);
                }
            }
        } catch (Exception e) {
            log("Error at UpdatePage" + e.toString());
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
