/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thunb.dao.HistoryDAO;
import thunb.dao.SubjectDAO;
import thunb.dto.HistoryDTO;
import thunb.dto.UsersDTO;
import thunb.utils.ConstantsKey;

/**
 *
 * @author Banh Bao
 */
@WebServlet(name = "ViewHistoryServlet", urlPatterns = {"/ViewHistoryServlet"})
public class ViewHistoryServlet extends HttpServlet {

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

        String url = ConstantsKey.HISTORY_PAGE;
        try {
            String txtSubject = request.getParameter("txtSubject");

            if (txtSubject != null && !txtSubject.trim().isEmpty()) {
                SubjectDAO dao = new SubjectDAO();
                String subjectID = dao.getSubjectIDFromName(txtSubject);

                HttpSession session = request.getSession(false);
                if (session != null) {
                    UsersDTO userLogin = (UsersDTO) session.getAttribute("LOGIN_USER");
                    HistoryDAO historyDAO = new HistoryDAO();
                    boolean rs = historyDAO.searchHistory(userLogin.getEmail(), subjectID);
                    List<HistoryDTO> listHistory = historyDAO.getListHistory();
                    request.setAttribute("LIST_HISTORY", listHistory);
                    
                }

            }

        } catch (NamingException ex) {
            log("ViewHistoryServlet_NamingException:" + ex.getMessage());
        } catch (SQLException ex) {
            log("ViewHistoryServlet_SQLException:" + ex.getMessage());
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
