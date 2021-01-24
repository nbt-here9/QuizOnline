/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.servlets;

//import com.sun.security.ntlm.Client;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thunb.dao.UsersDAO;
import thunb.dto.UsersDTO;
import thunb.utils.ConstantsKey;
import thunb.utils.Utilities;

/**
 *
 * @author Banh Bao
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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

        boolean success = false;
        String url = ConstantsKey.LOGIN_PAGE;

        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");

            if (email != null && password != null
                    && (!email.trim().isEmpty() || !password.trim().isEmpty())) {
                password = Utilities.encryptedPasswordByUsingSHA256(password);
                UsersDAO dao = new UsersDAO();
                boolean rs = dao.checkLogin(email, password);
                if (rs) {
                    UsersDTO loginUser = dao.getLoginUser();
                    HttpSession session = request.getSession(true);
                    session.setAttribute("LOGIN_USER", loginUser);

                    if (loginUser.getRole() == ConstantsKey.ADMIN_ROLE) {
                        url = ConstantsKey.ADMIN_PAGE;
                    } else if (loginUser.getRole() == ConstantsKey.USER_ROLE) {
                        url = ConstantsKey.DASHBOARD_PAGE;
                    }

                    success = true;
                }
            }

        } catch (SQLException ex) {
            log("LoginServlet_SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet_NamingException: " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log("LoginServlet_NoSuchAlgorithmException: " + ex.getMessage());
        } finally {
            if (success) {
                response.sendRedirect(url);
            } else {
                request.setAttribute("LOGIN_ERROR", "User not found!");
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
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
