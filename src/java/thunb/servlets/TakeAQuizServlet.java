/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thunb.dao.AnswerOfQuesDAO;
import thunb.dao.QuestionDAO;
import thunb.dto.AnswerOfQuesDTO;
import thunb.dto.QuestionDTO;
import thunb.question.QuestionObject;
import thunb.utils.ConstantsKey;
import thunb.utils.Utilities;

/**
 *
 * @author Banh Bao
 */
@WebServlet(name = "TakeAQuizServlet", urlPatterns = {"/TakeAQuizServlet"})
public class TakeAQuizServlet extends HttpServlet {

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

        String url = ConstantsKey.DASHBOARD_PAGE;
        try {
            String txtSubjectID = request.getParameter("txtSubjectID");
            String txtSubjectName = request.getParameter("txtSubjectName");
            String txtNumOfQues = request.getParameter("txtNumOfQues");
            String txtTimeOfQues = request.getParameter("txtTimeOfQues");

            if (txtSubjectID != null && !txtSubjectID.isEmpty()
                    && txtSubjectName != null && !txtSubjectName.isEmpty()
                    && txtNumOfQues != null && !txtNumOfQues.isEmpty()
                    && txtTimeOfQues != null && !txtTimeOfQues.isEmpty()) {

                int numOfQues = Integer.parseInt(txtNumOfQues);
                int timeOfQuiz = Integer.parseInt(txtTimeOfQues);

                QuestionDAO dao = new QuestionDAO();
                int rs = dao.searchQuestion(txtSubjectID, "", true);
                if (rs > 0) {
                    //Get All Ques
                    List<QuestionDTO> listQues = dao.getListQues();
                    int totalNumOfQuiz = listQues.size();
                    if (totalNumOfQuiz > 0 && totalNumOfQuiz >= numOfQues) {
                        //Random N ques
                        ArrayList<Integer> listQuesRandom = Utilities.randomQuestion(numOfQues, totalNumOfQuiz);

                        List<QuestionDTO> listQuesForQuiz = new ArrayList<>();
                        for (int i = 0; i < numOfQues; i++) {

                            int indexOfQuiz = listQuesRandom.get(i);
                            QuestionDTO dto = listQues.get(indexOfQuiz);
                            listQuesForQuiz.add(dto);
                        }

                        if (listQuesForQuiz.size() == numOfQues) {

                            QuestionObject quesObj = new QuestionObject();

                            for (QuestionDTO ques : listQuesForQuiz) {
                                AnswerOfQuesDAO ansDAO = new AnswerOfQuesDAO();
                                int rsAns = ansDAO.searchAnsByQuestionID(ques.getQuesID());
                                if (rsAns == 4) {
                                    List<AnswerOfQuesDTO> listAns = ansDAO.getListAns();
                                    quesObj.getQues().put(ques.getQuesContent(), listAns);
                                }
                            }

                            HttpSession session = request.getSession(false);
                            session.setAttribute("SUBJECT_NAME", txtSubjectName);
                            session.setAttribute("TIME", timeOfQuiz);
                            session.setAttribute("LIST_QUES_FOR_QUIZ", quesObj);
                            session.setAttribute("NUM_OF_QUES", numOfQues);

                            url = ConstantsKey.TAKE_A_QUIZ_PAGE;
                        }
                    }

                }

            }

        } catch (SQLException ex) {
            log("TakeAQuizServlet_SQLException:" + ex.getMessage());
        } catch (NamingException ex) {
            log("TakeAQuizServlet_NamingException:" + ex.getMessage());
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
