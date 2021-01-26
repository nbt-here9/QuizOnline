/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.servlets;

import java.io.IOException;
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
import thunb.dao.QuestionDAO;
import thunb.utils.ConstantsKey;

/**
 *
 * @author Banh Bao
 */
@WebServlet(name = "DeleteQuestionServlet", urlPatterns = {"/DeleteQuestionServlet"})
public class DeleteQuestionServlet extends HttpServlet {

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

        String url = ConstantsKey.ADMIN_PAGE;
        boolean rs = false;
        try {
//            String txtSubject = request.getParameter("txtSubject");
//            String txtStatus = request.getParameter("txtStatus");
//            String txtSearchValue = request.getParameter("txtSearchValue");
            String txtQuestionID = request.getParameter("txtQuestionID");

            if (txtQuestionID != null && !txtQuestionID.trim().isEmpty()) {
                int quesID = Integer.parseInt(txtQuestionID);

                QuestionDAO dao = new QuestionDAO();
                rs = dao.updateStatusQuestion(quesID, false);
                if (rs) {
                    request.setAttribute("REMOVE_SUCCESS", "Delete Successfully! (Meaning update the status of the Question to Deactive)");
                }
            }

        } catch (SQLException ex) {
            log("DeleteQuestionServlet_SQLException:" + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleteQuestionServlet_NamingException:" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
