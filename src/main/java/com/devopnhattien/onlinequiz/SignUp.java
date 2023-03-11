/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.devopnhattien.onlinequiz;

import dao.AccountDAO;
import dto.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author axyres
 */
public class SignUp extends HttpServlet{
    
    private static final String SIGNUP_PAGE = "signup.jsp";   
    private static final String LOGIN_PAGE = "login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SIGNUP_PAGE;
        AccountErrorDTO errorObject = new AccountErrorDTO("", "", "", "", "", "");
        try {
            String email = request.getParameter("txtEmail");
            String fullname = request.getParameter("txtFullname");
            String phone = request.getParameter("txtPhone");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            boolean check = true;
            if (email.isEmpty()) {
                check = false;
                errorObject.setEmailError("Email is not empty");
            }
            if (fullname.isEmpty()) {
                check = false;
                errorObject.setFullNameError("FullName is not empty ");
            }
            if (password.isEmpty()) {
                check = false;
                errorObject.setPasswordError("Password is not empty ");
            }
            if (!password.equals(confirm)) {
                check = false;
                errorObject.setConfirmError("Confirm password not same");
            }
            if (phone.isEmpty()) {
                check = false;
                errorObject.setPhoneError("Phone is not empty");
            }
            if (check == true) {
                HttpSession session=request.getSession();
                
                AccountDAO dao = new AccountDAO();
                AccountDTO acc = dao.createLogin(email, password, fullname, Integer.parseInt(phone));
                session.setAttribute("SIGNUP_SUCCESS", "Sign up successfully! Please login.");
                url = LOGIN_PAGE;
            } else {
                request.setAttribute("SIGNUP_ERROR", "Email is already taken. Please choose another email.");
            }
        } catch (Exception e) {
            log("Error at SignUp: " + e.toString());
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
