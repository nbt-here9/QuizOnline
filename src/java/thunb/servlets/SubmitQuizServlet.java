/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import thunb.dao.HistoryDAO;
import thunb.dto.AnswerOfQuesDTO;
import thunb.dto.UsersDTO;
import thunb.question.QuestionObject;
import thunb.utils.ConstantsKey;
import thunb.utils.Utilities;

/**
 *
 * @author Banh Bao
 */
@WebServlet(name = "SubmitQuizServlet", urlPatterns = {"/SubmitQuizServlet"})
public class SubmitQuizServlet extends HttpServlet {

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
            String ans;
            String rdAnswer;
            int ansCorrect;
            List<Integer> listAnswerOfUser = new ArrayList<>();
            List<Integer> listCorrectAnswer = new ArrayList<>();

            HttpSession session = request.getSession(false);
            if (session != null) {
                int numOfQues = (int) session.getAttribute("NUM_OF_QUES");
                for (int i = 0; i < numOfQues; i++) {
                    rdAnswer = "rdAnsCorrect";
                    rdAnswer += String.valueOf(i + 1);

                    ansCorrect = -1;

                    ans = request.getParameter(rdAnswer);
                    if (ans != null && !ans.trim().isEmpty()) {
                        ansCorrect = Integer.parseInt(ans);
                    }

                    listAnswerOfUser.add(ansCorrect);
                }
                //
                QuestionObject quesObj = (QuestionObject) session.getAttribute("LIST_QUES_FOR_QUIZ");
                if (quesObj != null) {
                    Map<String, List<AnswerOfQuesDTO>> sourceQuiz = quesObj.getQues();
                    for (Map.Entry<String, List<AnswerOfQuesDTO>> quiz : sourceQuiz.entrySet()) {
                        int i = 1;
                        for (AnswerOfQuesDTO dto : quiz.getValue()) {
                            if (dto.isIsTrue()) {
                                listCorrectAnswer.add(i);
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                    //
                    double numOfCorrectAnswer = 0;
                    for (int i = 0; i < numOfQues; i++) {
                        if (listAnswerOfUser.get(i) != null && listCorrectAnswer.get(i) != null) {
                            if (listAnswerOfUser.get(i) == listCorrectAnswer.get(i)) {
                                numOfCorrectAnswer += 1;
                            }
                        }
                    }
                    //
                    double totalScore = Math.round(numOfCorrectAnswer / numOfQues * 10 * 100) / 100.0d;
                    //
                    UsersDTO userLogin = (UsersDTO) session.getAttribute("LOGIN_USER");
                    String subjectID = (String) session.getAttribute("SUBJECT_ID");
                    Timestamp startTime = (Timestamp) session.getAttribute("START_TIME");
                    Timestamp endTime = Utilities.getCurrentTime();

                    HistoryDAO historyDAO = new HistoryDAO();
                    historyDAO.insertHistory(userLogin.getEmail(), subjectID, totalScore, startTime, endTime);
                    //
                    request.setAttribute("NUM_OF_CORRECT", (int) numOfCorrectAnswer);
                    request.setAttribute("SCORE", totalScore);
                    url = ConstantsKey.TAKE_A_QUIZ_PAGE;
                    //
                    session.removeAttribute("SUBJECT_ID");
                    session.removeAttribute("SUBJECT_NAME");
                    session.removeAttribute("TIME");
                    session.removeAttribute("LIST_QUES_FOR_QUIZ");
                    session.removeAttribute("START_TIME");
                }
            }

        } catch (SQLException ex) {
            log("SubmitQuizServlet_SQLException:" + ex.getMessage());
        } catch (NamingException ex) {
            log("SubmitQuizServlet_NamingException:" + ex.getMessage());
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
