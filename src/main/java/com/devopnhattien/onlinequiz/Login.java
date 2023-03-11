package com.devopnhattien.onlinequiz;

import dao.AccountDAO;
import dto.AccountDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.taglibs.standard.tag.el.core.OutTag;

/**
 *
 * @author axyres
 */
public class Login extends HttpServlet{
    private static final String ADMIN_LOGIN = "Admin";   
    private static final String USER_LOGIN = "Home";   
    private static final String ERROR = "login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            
            AccountDAO accDao = new AccountDAO();
            AccountDTO acc = accDao.checkLogin(email, password);
            
            HttpSession session=request.getSession();
            if (acc==null) {
                session.setAttribute("LOGIN_INVALID", "Email or Password is incorrect. Try Again");
            }else {
                if(acc.getRoleID().equals("1")){
                    url = ADMIN_LOGIN;
                }else if(acc.getRoleID().equals("2")){     
                    url = USER_LOGIN;
                }
                session.setAttribute("LOGIN_USER", acc);
            }
        } catch (Exception e) {
            log("Error at Login: " + e.toString());
        } finally {
            response.sendRedirect(url);
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
