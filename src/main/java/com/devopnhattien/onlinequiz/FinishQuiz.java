/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.devopnhattien.onlinequiz;

import dao.*;
import dto.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nhatt
 */
public class FinishQuiz extends HttpServlet {

    private final static String SUCCESS = "result.jsp";
    private final static String ERROR = "quiz.jsp";

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
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            AccountDTO loginUser = (AccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser != null && loginUser.getRoleID().equals("2")) {
                String answerSelectedID = request.getParameter("radioAnswerSelected");             
                QuizDTO quiz = (QuizDTO) session.getAttribute("QUIZ");
                SubjectDTO subjectCurrent = (SubjectDTO) session.getAttribute("SUBJECT");
                
                QuizDAO quizDAO = new QuizDAO();
                QuizDetailDAO detailDAO = new QuizDetailDAO();
                AnswerDAO answerDAO=new AnswerDAO();
                
                if(quiz!=null && subjectCurrent!=null) {//luu dap an da selected
                    String needSaveQuestionID = request.getParameter("needToSaveQuestion");
                    if (answerSelectedID != null) {
                        if (answerDAO.checkAnswerCorrect(Integer.parseInt(answerSelectedID)) == true) {
                            detailDAO.updateQuizDetail(quiz.getQuizID(), needSaveQuestionID, Integer.parseInt(answerSelectedID), true);
                        } else {
                            detailDAO.updateQuizDetail(quiz.getQuizID(), needSaveQuestionID, Integer.parseInt(answerSelectedID), false);
                        }
                        int index = -1;
                        for (QuestionDTO questionDTO : quiz.getListQuestion()) {
                            if (needSaveQuestionID.equals(questionDTO.getQuestionID())) {
                                index = quiz.getListQuestion().indexOf(questionDTO);
                            }
                        }
                        quiz.getListAnswerSelected()[index] = Integer.parseInt(answerSelectedID);
                    }
                    
                    float totalMarkUpdate=detailDAO.getTotalMark(quiz.getQuizID());
                    quiz.setTotalMark(totalMarkUpdate);
                    quizDAO.updateTotalMark(quiz.getQuizID(), totalMarkUpdate);
                    
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    Date date = new Date();
                    String strCurrentDate = formatter.format(date);
                    quizDAO.updateFinishTime(quiz.getQuizID(), strCurrentDate);
                    
                    request.setAttribute("RESULT_MESSAGE", "You finished quiz successful, you score: "+quiz.getTotalMark()+"/10, question correct: "+quiz.getTotalMark()*subjectCurrent.getNumberQuestion()/10+"/"+subjectCurrent.getNumberQuestion());
                    request.setAttribute("SCORE", quiz.getTotalMark());
                    request.setAttribute("ANSWER_CORRECT", (int)quiz.getTotalMark()*subjectCurrent.getNumberQuestion()/10);
                    request.setAttribute("DATE_SUBMIT", strCurrentDate);
                    request.setAttribute("SUBJECT", subjectCurrent.getSubjectName());
                    
                    session.setAttribute("CURRENT_QUESTION", null);
                    session.setAttribute("LIST_CURRENT_ANSWER", null);
                    session.setAttribute("SUBJECT", null);
                    session.setAttribute("QUIZ", null);
 
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at FinishQuizServlet " + e.toString());
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
