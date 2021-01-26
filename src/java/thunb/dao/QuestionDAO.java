/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thunb.dto.AnswerOfQuesDTO;
import thunb.dto.QuestionDTO;
import thunb.utils.DBHelpers;

/**
 *
 * @author Banh Bao
 */
public class QuestionDAO {

    private static Connection cn;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public QuestionDAO() {
        this.cn = null;
        this.pst = null;
        this.rs = null;
    }

    private static void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (pst != null) {
            pst.close();
        }
        if (cn != null) {
            cn.close();
        }
    }

    public boolean createQuestion(String quesContent,
            Timestamp createDate, String subjectID, boolean status, List<AnswerOfQuesDTO> listAns)
            throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelpers.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                //Insert into Question
                String sql1 = "INSERT INTO Question ( quesContent ,createDate "
                        + ",subjectID ,status ) VALUES  (?,?,?,?)";

                pst = cn.prepareStatement(sql1);
                pst.setNString(1, quesContent);
                pst.setTimestamp(2, createDate);
                pst.setString(3, subjectID);
                pst.setBoolean(4, status);

                boolean check = pst.executeUpdate() > 0;

                //Select quesID then insert into AnswerOfQues
                if (check) {
                    String sql2 = "SELECT MAX(quesID) AS quesID FROM Question";
                    pst = cn.prepareStatement(sql2);

                    rs = pst.executeQuery();

                    if (rs.next()) {
                        int quesID = rs.getInt("quesID");

                        AnswerOfQuesDAO dao = new AnswerOfQuesDAO();
                        if (dao.insertAnswerOfQuestion(cn, quesID, listAns)) {
                            cn.commit();
                            return true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            if (cn != null) {
                cn.rollback();
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return false;
    }

    private List<QuestionDTO> listQues;

    public List<QuestionDTO> getListQues() {
        return listQues;
    }

    public int searchQuestion(String subjectID, String searchValue, boolean status)
            throws SQLException, NamingException {
        int result = 0;
        try {
            cn = DBHelpers.makeConnection();
            if (cn != null) {
                String sql = "SELECT quesID, quesContent, createDate, subjectID, status FROM Question "
                        + "WHERE  subjectID LIKE ? AND status = ? AND quesContent LIKE ? "
                        + "ORDER BY quesContent ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + subjectID + "%");
                pst.setBoolean(2, status);
                pst.setNString(3, "%" + searchValue + "%");

                rs = pst.executeQuery();

                while (rs.next()) {
                    if (this.listQues == null) {
                        this.listQues = new ArrayList<>();
                    }

                    int quesID = rs.getInt("quesID");
                    String quesContent = rs.getNString("quesContent");
                    Timestamp createDate = rs.getTimestamp("createDate");
                    subjectID = rs.getString("subjectID");
                    boolean statusQues = rs.getBoolean("status");

                    QuestionDTO dto = new QuestionDTO(quesID, quesContent, createDate, subjectID, statusQues);
                    this.listQues.add(dto);
                    result += 1;
                }

            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public QuestionDTO getSelectedQuestion(int quesID)
            throws SQLException, NamingException {
        QuestionDTO dto = null;
        try {
            cn = DBHelpers.makeConnection();
            if (cn != null) {
                String sql = "SELECT quesID, quesContent, createDate, subjectID, status FROM Question "
                        + "WHERE  quesID = ? ";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, quesID);

                rs = pst.executeQuery();

                while (rs.next()) {
                    String quesContent = rs.getNString("quesContent");
                    Timestamp createDate = rs.getTimestamp("createDate");
                    String subjectID = rs.getString("subjectID");
                    boolean statusQues = rs.getBoolean("status");
                    dto = new QuestionDTO(quesID, quesContent, createDate, subjectID, statusQues);
                }

            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    
    public boolean updateQuestion(int quesID, String quesContent,
            Timestamp createDate, String subjectID, boolean status, List<AnswerOfQuesDTO> listAns)
            throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBHelpers.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                //Update question
                String sql1 = "UPDATE Question SET quesContent = ?, createDate = ?, "
                        + "subjectID = ?, STATUS = ? WHERE quesID = ?";

                pst = cn.prepareStatement(sql1);
                pst.setNString(1, quesContent);
                pst.setTimestamp(2, createDate);
                pst.setString(3, subjectID);
                pst.setBoolean(4, status);
                pst.setInt(5, quesID);

                boolean check = pst.executeUpdate() > 0;

                //Update into AnswerOfQues
                if (check) {

                        AnswerOfQuesDAO dao = new AnswerOfQuesDAO();
                        if (dao.updateAnswerOfQuestion(cn, quesID, listAns)) {
                            cn.commit();
                            return true;
                        }
                    
                }
            }
        } catch (SQLException e) {
            if (cn != null) {
                System.out.println("AAAAA: " + e.getMessage());
                cn.rollback();
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return false;
    }
    
    public boolean updateStatusQuestion(int quesID, boolean status) throws SQLException, NamingException {
        try {
            cn = DBHelpers.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Question SET STATUS = ? WHERE quesID = ? ";
                pst = cn.prepareStatement(sql);

                pst.setBoolean(1, status);
                pst.setInt(2, quesID);
                int result = pst.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
