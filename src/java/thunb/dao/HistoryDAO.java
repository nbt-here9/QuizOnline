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
import thunb.dto.HistoryDTO;
import thunb.utils.DBHelpers;

/**
 *
 * @author Banh Bao
 */
public class HistoryDAO {

    private static Connection cn;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public HistoryDAO() {
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

    public boolean insertHistory(String userEmail, String subjectID, double score, Timestamp startTime, Timestamp endTime)
            throws SQLException, NamingException {
        boolean result = false;
        try {
            cn = DBHelpers.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO History ( userEmail ,subjectID ,"
                        + "score ,startTime ,endTime ) VALUES  (?,?,?,?,?)";
                pst = cn.prepareStatement(sql);

                pst.setString(1, userEmail);
                pst.setString(2, subjectID);
                pst.setDouble(3, score);
                pst.setTimestamp(4, startTime);
                pst.setTimestamp(5, endTime);

                result = pst.executeUpdate() > 0;

            }
        } finally {
            closeConnection();
        }
        return result;
    }

    private List<HistoryDTO> listHistory = null;

    public List<HistoryDTO> getListHistory() {
        return listHistory;
    }

    public boolean searchHistory(String userEmail, String subjectID)
            throws SQLException, NamingException {
        boolean result = false;
        try {
            cn = DBHelpers.makeConnection();
            if (cn != null) {
                String sql = "SELECT score, startTime, endTime "
                        + "FROM History WHERE userEmail = ? AND subjectID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userEmail);
                pst.setString(2, subjectID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (this.listHistory == null) {
                        this.listHistory = new ArrayList<>();
                    }
                    double score = rs.getFloat("score");
                    Timestamp startTime = rs.getTimestamp("startTime");
                    Timestamp endTime = rs.getTimestamp("endTime");
                    HistoryDTO dto = new HistoryDTO(userEmail, subjectID, score, startTime, endTime);
                    this.listHistory.add(dto);
                    result = true;
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
