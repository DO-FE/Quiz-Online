/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.devopnhattien.onlinequiz;

import dao.*;
import dto.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nhatt
 */
public class Search extends HttpServlet {
    private final static String SUCCESS="admin.jsp";
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
            String question_content = request.getParameter("txtQuestionContent");
            String status = "";
            String subjectID = request.getParameter("cmbSubject");
            String currentPage = request.getParameter("txtCurrentPage");
            
            if (currentPage == null) {
                currentPage = "1";
            }
            
            SubjectDAO subjectDao = new SubjectDAO();
            QuestionDAO questionDao=new QuestionDAO();
            AnswerDAO answerDao=new AnswerDAO();
            int count=questionDao.getNoOfAdminSearchResult(question_content, status, subjectID);
            
            int totalPage=0;
            if(count % 20!=0){
                totalPage = count/20 +1;
            }else{
                totalPage = count/20;
            }
            request.setAttribute("TOTAL_PAGE", totalPage);
            List<QuestionDTO> listQuestion = questionDao.getListQuestion(question_content, status, subjectID, Integer.parseInt(currentPage));
            if (!listQuestion.isEmpty()) {
                List<AnswerDTO> listAnswer = new ArrayList<>();
                List<SubjectDTO> listSubject = new ArrayList<>();
                for (QuestionDTO questionDTO : listQuestion) {
                    SubjectDTO subjectDTO = subjectDao.getSubjectFromQuestionID(questionDTO.getQuestionID());
                    if (subjectDTO != null && isDuplicateSubject(listSubject, subjectDTO)) {
                        listSubject.add(subjectDTO);
                    }
                    List<AnswerDTO> answerList = answerDao.getAnswersFromQuestionID(questionDTO.getQuestionID());
                    listAnswer.addAll(answerList);
                }
                request.setAttribute("LIST_QUESTION", listQuestion);
                request.setAttribute("LIST_ANSWER", listAnswer);
                request.setAttribute("LIST_SUBJECT", listSubject);

                url = SUCCESS;
            }else{
                request.setAttribute("MESSAGE", "Not found");
            }    
        } catch (Exception e) {
            log("Error at SearchServlet "+e.toString());
        }
        finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    
    private boolean isDuplicateSubject(List<SubjectDTO> subjectList, SubjectDTO subjectDTO) {
        for (SubjectDTO existingSubjectDTO : subjectList) {
            if (existingSubjectDTO.getSubjectID().equals(subjectDTO.getSubjectID())) {
                return false;
            }
        }
        return true;
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
