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
import java.util.ArrayList;
import java.util.Date;
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
public class Home extends HttpServlet {
    
    private final static String USER = "home.jsp";
    private final static String QUIZ_CONTINUE = "quiz.jsp";
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
        String url = USER;
        try {
            HttpSession session = request.getSession();
            AccountDTO loginUser = (AccountDTO) session.getAttribute("LOGIN_USER");
            
            QuizDAO quizDAO = new QuizDAO();
            QuizDetailDAO detailDAO = new QuizDetailDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            AnswerDAO answerDAO = new AnswerDAO();
            QuestionDAO questionDAO = new QuestionDAO();
            
            if (loginUser != null && loginUser.getRoleID().equals("2")) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date date = new Date();
                String strCurrentDate = formatter.format(date);
                QuizDTO quiz = quizDAO.getQuizNotFinish(loginUser.getEmail(), strCurrentDate);
                if (quiz != null) {
                    SubjectDTO subjectdto = subjectDAO.getSubjectFromSubjectID(quiz.getSubjectID());
                    quiz.setListAnswerSelected(new int[subjectdto.getNumberQuestion()]);
                    quiz.setListQuestion(new ArrayList<>());
                    List<String> listTemp = detailDAO.getListAnswerSelectedAndQuestionID(quiz.getQuizID());
                    int i = 0;
                    for (String string : listTemp) {
                        String[] arr = string.split("_");
                        int answerSelected = Integer.parseInt(arr[0]);
                        String questionID = arr[1];
                        quiz.getListQuestion().add(questionDAO.getQuestion(questionID));
                        quiz.getListAnswerSelected()[i] = answerSelected;
                        i++;
                    }
                    QuestionDTO questionCurrent = quiz.getListQuestion().get(0);
                    List<AnswerDTO> answerCurrent = answerDAO.getAnswersFromQuestionID(questionCurrent.getQuestionID());
                    session.setAttribute("CURRENT_QUESTION", questionCurrent);
                    session.setAttribute("LIST_CURRENT_ANSWER", answerCurrent);
                    session.setAttribute("SUBJECT", subjectdto);
                    session.setAttribute("QUIZ", quiz);
                    url = QUIZ_CONTINUE;
                } else {
                    List<SubjectDTO> list = subjectDAO.getAllSubject();
                    session.setAttribute("LIST_ALL_SUBJECT", list);
                    url = USER;
                }
            }

        } catch (Exception e) {
            log("Error at Home: " + e.toString());
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
