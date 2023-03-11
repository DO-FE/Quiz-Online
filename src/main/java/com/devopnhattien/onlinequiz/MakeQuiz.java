/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.devopnhattien.onlinequiz;

import dto.*;
import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
public class MakeQuiz extends HttpServlet {
    private final static String ERROR = "error.jsp";
    private final static String FINISH = "FinishQuiz";
    private final static String SUCCESS = "quiz.jsp";

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
            String action = request.getParameter("btnAction");
            String currentQuestion = request.getParameter("txtCurrentQuestion");
            String answerSelectedID = request.getParameter("radioAnswerSelected");
            if (loginUser != null && loginUser.getRoleID().equals("2")) {
                if ("FinishQuiz".equals(action)) {
                    url = FINISH;
                } else {
                    QuizDTO quiz = (QuizDTO) session.getAttribute("QUIZ");
                    SubjectDTO subjectCurrent = (SubjectDTO) session.getAttribute("SUBJECT");
                    QuestionDAO questionDAO = new QuestionDAO();
                    QuizDAO quizDAO = new QuizDAO();
                    SubjectDAO subjectDAO = new SubjectDAO();
                    QuizDetailDAO detailDAO = new QuizDetailDAO();
                    AnswerDAO answerDAO = new AnswerDAO();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    if (quiz == null && subjectCurrent == null) {
                        String subjectID = request.getParameter("cmbSubject");
                        SubjectDTO subjectdto = subjectDAO.getSubjectFromSubjectID(subjectID);
                        currentQuestion = "0";
                        String quizID = "Q" + (quizDAO.getNoOfQuiz() + 1);                    
                        Date date = new Date();
                        date.setMinutes(date.getMinutes() + subjectdto.getTimeQuiz());
                        String strCurrentDate = formatter.format(date);
                        List<QuestionDTO> listQuestion = questionDAO.getListQuestionRandom(subjectdto);
                        int[] listAnswerSelected = new int[subjectdto.getNumberQuestion()];

                        quiz = new QuizDTO(quizID, loginUser.getEmail(), subjectID, 0, strCurrentDate, listQuestion, listAnswerSelected);
                        quizDAO.createQuiz(quiz);

                        int count = 0;
                        for (QuestionDTO questionDTO : quiz.getListQuestion()) {
                            if (answerDAO.checkAnswerCorrect(listAnswerSelected[count]) == true) {
                                detailDAO.createQuizDetail(quizID, questionDTO.getQuestionID(), listAnswerSelected[count], true);
                            } else {
                                detailDAO.createQuizDetail(quizID, questionDTO.getQuestionID(), listAnswerSelected[count], false);
                            }
                            count++;
                        }
                        QuestionDTO questionCurrent = quiz.getListQuestion().get(Integer.parseInt(currentQuestion));
                        List<AnswerDTO> answerCurrent = answerDAO.getAnswersFromQuestionID(questionCurrent.getQuestionID());
                        session.setAttribute("CURRENT_QUESTION", questionCurrent);
                        session.setAttribute("LIST_CURRENT_ANSWER", answerCurrent);
                        session.setAttribute("SUBJECT", subjectdto);
                        session.setAttribute("QUIZ", quiz);

                        url = SUCCESS;
                    } else {
                        String subjectID = request.getParameter("cmbSubject");
                        Date date = new Date();
                        String strCurrentDate = formatter.format(date);
                        AccountDTO user=(AccountDTO) session.getAttribute("LOGIN_USER");
                        SubjectDTO subjectdto=(SubjectDTO) session.getAttribute("SUBJECT");
                        if(subjectID!=null){
                            if(quizDAO.checkTimeFinish(strCurrentDate, user.getEmail()) && !subjectID.equals(subjectdto.getSubjectID())){
                                request.setAttribute("MESSAGE", "You are doing the exam ");
                            }
                        }                      
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

                        float totalMarkUpdate = detailDAO.getTotalMark(quiz.getQuizID());
                        quiz.setTotalMark(totalMarkUpdate);
                        quizDAO.updateTotalMark(quiz.getQuizID(), totalMarkUpdate);
                        QuestionDTO questionCurrent = quiz.getListQuestion().get(Integer.parseInt(currentQuestion) - 1);
                        List<AnswerDTO> answerCurrent = answerDAO.getAnswersFromQuestionID(questionCurrent.getQuestionID());
                        session.setAttribute("CURRENT_QUESTION", questionCurrent);
                        session.setAttribute("LIST_CURRENT_ANSWER", answerCurrent);
                        session.setAttribute("SUBJECT", subjectCurrent);
                        session.setAttribute("QUIZ", quiz);
                        url = SUCCESS;
                    }
                }
            }

        } catch (Exception e) {
            log("Error at MakeQuiz " + e.toString());
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
