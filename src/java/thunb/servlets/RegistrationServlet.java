/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.servlets;

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
import thunb.dao.UsersDAO;
import thunb.errors.UsersError;
import thunb.utils.ConstantsKey;
import thunb.utils.Utilities;

/**
 *
 * @author Banh Bao
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

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
        boolean rs = false;
        try {
            String txtEmail = request.getParameter("txtEmail");
            String txtName = request.getParameter("txtName");
            String txtPassword = request.getParameter("txtPassword");

            txtPassword = Utilities.encryptedPasswordByUsingSHA256(txtPassword);
            UsersDAO dao = new UsersDAO();
            rs = dao.createNewAccount(txtEmail, txtName, txtPassword);
        } catch (SQLException ex) {
            if (ex.getMessage().contains("duplicated")) {
                UsersError error = new UsersError();
                error.setEmailExistedErr("Email existed!");
                request.setAttribute("ERROR", error);
            }
            log("RegistrationServlet_SQLException:" + ex.getMessage());
        } catch (NamingException ex) {
            log("RegistrationServlet_NamingException:" + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
             log("RegistrationServlet_NoSuchAlgorithmException:" + ex.getMessage());
        } finally {
            if (rs) {
                RequestDispatcher rd = request.getRequestDispatcher(ConstantsKey.LOGIN_PAGE);
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(ConstantsKey.REGISTRATION_PAGE);
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
