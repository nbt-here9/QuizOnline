/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thunb.dao.QuestionDAO;
import thunb.dao.SubjectDAO;
import thunb.dto.AnswerOfQuesDTO;
import thunb.utils.ConstantsKey;
import thunb.utils.Utilities;

/**
 *
 * @author Banh Bao
 */
@WebServlet(name = "CreateQuestionServlet", urlPatterns = {"/CreateQuestionServlet"})
public class CreateQuestionServlet extends HttpServlet {

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

        String url = ConstantsKey.CREATE_PAGE;
        try {
            String txtSubject = request.getParameter("txtSubject");
            String txtStatus = request.getParameter("txtStatus");
            String txtQuestion = request.getParameter("txtQuestion");
            String txtAns1 = request.getParameter("txtAns1");
            String txtAns2 = request.getParameter("txtAns2");
            String txtAns3 = request.getParameter("txtAns3");
            String txtAns4 = request.getParameter("txtAns4");
            String rdAnsCorrect = request.getParameter("rdAnsCorrect");
            //
            boolean status = true;
            if (txtStatus != null && !txtStatus.trim().isEmpty()) {
                if (txtStatus.equals("Deactive")) {
                    status = false;
                }
            }
            
            int ansCorrect = Integer.parseInt(rdAnsCorrect);
            
            SubjectDAO subDAO = new SubjectDAO();
            String subjectID = subDAO.getSubjectIDFromName(txtSubject);
            //
            AnswerOfQuesDTO ansA = new AnswerOfQuesDTO(txtAns1, false);
            AnswerOfQuesDTO ansB = new AnswerOfQuesDTO(txtAns2, false);
            AnswerOfQuesDTO ansC = new AnswerOfQuesDTO(txtAns3, false);
            AnswerOfQuesDTO ansD = new AnswerOfQuesDTO(txtAns4, false);
           
            List<AnswerOfQuesDTO> listAns = new ArrayList<>();
            listAns.add(ansA);
            listAns.add(ansB);
            listAns.add(ansC);
            listAns.add(ansD);
            
            for (int i = 1; i < 5; i++) {
                if (ansCorrect == i) {
                    listAns.get(i-1).setIsTrue(true);
                }
            }
            //
            Timestamp createDate = Utilities.getCurrentTime();
            QuestionDAO dao = new QuestionDAO();
            boolean rs = dao.createQuestion(txtQuestion, createDate, subjectID, status, listAns);
            if (rs) {
                request.setAttribute("CREATE_SUCCESS", "OK");
                url = ConstantsKey.ADMIN_PAGE;
            } else {
                request.setAttribute("CREATE_FAIL", "FAIL");
            }
            
            
        
        } catch (SQLException ex) {
            log("CreateQuestionServlet_SQLException:" + ex.getMessage());
        } catch (NamingException ex) {
            log("CreateQuestionServlet_NamingException:" + ex.getMessage());
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
