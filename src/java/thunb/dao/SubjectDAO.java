/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thunb.dto.SubjectDTO;
import thunb.utils.DBHelpers;

/**
 *
 * @author Banh Bao
 */
public class SubjectDAO implements Serializable {

    private static Connection cn;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public SubjectDAO() {
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

    private List<SubjectDTO> listSubject = null;

    public List<SubjectDTO> getListSubject() {
        return listSubject;
    }

    public boolean getAllSubject()
            throws SQLException, NamingException {
        boolean result = false;
        try {
            cn = DBHelpers.makeConnection();
            if (cn != null) {
                String sql = "SELECT subjectID, subjectName, numOfQues, timeOfQuiz FROM Subject";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    if (this.listSubject == null) {
                        this.listSubject = new ArrayList<>();
                    }
                    String subjectID = rs.getString("subjectID");
                    String subjectName = rs.getString("subjectName");
                    int numOfQues = rs.getInt("numOfQues");
                    int timeOfQuiz = rs.getInt("timeOfQuiz");

                    SubjectDTO dto = new SubjectDTO(subjectID, subjectName, numOfQues, timeOfQuiz);
                    this.listSubject.add(dto);
                    result = true;
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public String getSubjectIDFromName(String subjectName) 
            throws NamingException, SQLException{
        try{
            cn = DBHelpers.makeConnection();
            if(cn!=null){
                String sql = "SELECT subjectID FROM Subject WHERE subjectName = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, subjectName);
                rs = pst.executeQuery();
                if(rs.next()){
                    return rs.getString("subjectID");
                }
            }
        }finally{
            closeConnection();
        }
        return null;
    }

}
