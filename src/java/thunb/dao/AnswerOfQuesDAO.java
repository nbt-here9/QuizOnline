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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thunb.dto.AnswerOfQuesDTO;
import thunb.utils.DBHelpers;

/**
 *
 * @author Banh Bao
 */
public class AnswerOfQuesDAO {

    private static Connection cn;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public AnswerOfQuesDAO() {
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

    public boolean insertAnswerOfQuestion(Connection cn, int quesID, List<AnswerOfQuesDTO> list)
            throws SQLException, NamingException {
        PreparedStatement pst = null;

        try {
            if (cn != null) {
                String sql = "INSERT INTO AnswerOfQues ( quesID, ansContent, isTrue ) VALUES  (?,?,?)";

                pst = cn.prepareStatement(sql);
                if (list != null && !list.isEmpty()) {
                    for (AnswerOfQuesDTO ans : list) {
                        pst.setInt(1, quesID);
                        pst.setNString(2, ans.getAnsContent());
                        pst.setBoolean(3, ans.isIsTrue());
                        pst.executeUpdate();
                    }

                    return true;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
        }
        return false;
    }

    private List<AnswerOfQuesDTO> listAns;

    public List<AnswerOfQuesDTO> getListAns() {
        return listAns;
    }

    public int searchAnsByQuestionID(int quesID)
            throws SQLException, NamingException {
        int result = 0;
        try {
            cn = DBHelpers.makeConnection();
            if (cn != null) {
                String sql = "SELECT ansID, ansContent, isTrue "
                        + "FROM AnswerOfQues WHERE quesID = ? ";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, quesID);
                
                rs = pst.executeQuery();

                while (rs.next()) {
                    if (this.listAns == null) {
                        this.listAns = new ArrayList<>();
                    }
                    int ansID = rs.getInt("ansID");
                    String ansContent = rs.getNString("ansContent");
                    boolean isTrue = rs.getBoolean("isTrue");

                    AnswerOfQuesDTO dto = new AnswerOfQuesDTO(ansID, ansContent, quesID, isTrue);
                    this.listAns.add(dto);
                    result += 1;
                }
            }
        } finally {
            closeConnection();
        }

        return result;
    }
}
