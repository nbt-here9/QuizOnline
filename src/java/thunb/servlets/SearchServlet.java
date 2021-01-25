/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thunb.dao.AnswerOfQuesDAO;
import thunb.dao.QuestionDAO;
import thunb.dao.SubjectDAO;
import thunb.dto.AnswerOfQuesDTO;
import thunb.dto.QuestionDTO;
import thunb.question.QuestionObject;
import thunb.utils.ConstantsKey;

/**
 *
 * @author Banh Bao
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

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
        try {
            String txtSubject = request.getParameter("txtSubject");
            String txtStatus = request.getParameter("txtStatus");
            String txtSearchValue = request.getParameter("txtSearchValue");
            
           
            if (txtSubject != null || !txtSubject.trim().isEmpty()
                    || txtStatus != null || !txtStatus.trim().isEmpty()
                    || txtSearchValue != null || !txtSearchValue.trim().isEmpty()) {
                
                
                QuestionObject quesObj = null;

                String subjectID = "";
                if (!txtSubject.trim().isEmpty()) {
                    SubjectDAO subDAO = new SubjectDAO();
                    subjectID = subDAO.getSubjectIDFromName(txtSubject);
                }
                boolean status = true;
                if (!txtStatus.trim().isEmpty()) {
                    if (txtStatus.equals("Deactive")) {
                        status = false;
                    }
                }
                //

                System.out.println("|" + subjectID + "|");
                System.out.println("|" + txtSearchValue + "|");
                QuestionDAO dao = new QuestionDAO();
                int rs = dao.searchQuestion(subjectID, txtSearchValue, status);
                if (rs > 0) {
                    System.out.println("AAAAAAAAAAAA: " + rs);
                    quesObj = new QuestionObject();
                    
                     
                    
                    List<QuestionDTO> listQues = dao.getListQues();
                    System.out.println("BBBBBBB: " + listQues.size());
                    AnswerOfQuesDAO ansDAO = new AnswerOfQuesDAO();
                    for (QuestionDTO ques : listQues) {
                        
                        System.out.println(ques.getQuesContent());
                        
                        int rsAns = ansDAO.searchAnsByQuestionID(ques.getQuesID());
                        if (rsAns == 4) {
                            List<AnswerOfQuesDTO> listAns = ansDAO.getListAns();
                            quesObj.getQues().put(ques.getQuesContent(), listAns);
                        }
                    }

                }

                if (quesObj != null) {
                   
                    request.setAttribute("LIST_QUESTION", quesObj);
                }
            } else {
                request.setAttribute("NOTI", "Please input info before press Search button");
            }

        } catch (NamingException ex) {
            log("SearchServlet_NamingException:" + ex.getMessage());
        } catch (SQLException ex) {
            log("SearchServlet_SQLException:" + ex.getMessage());
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
