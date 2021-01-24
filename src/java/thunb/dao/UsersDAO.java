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
import javax.naming.NamingException;
import thunb.dto.UsersDTO;
import thunb.utils.ConstantsKey;
import thunb.utils.DBHelpers;

/**
 *
 * @author Banh Bao
 */
public class UsersDAO implements Serializable {

    private static Connection cn;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public UsersDAO() {
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
    
    private UsersDTO loginUser;

    public UsersDTO getLoginUser() {
        return loginUser;
    }
    
    public boolean checkLogin(String email, String password) 
            throws SQLException, NamingException {
        try {
            cn = DBHelpers.makeConnection();
            if (cn != null) {
                String sql = "SELECT name,role,status FROM Users "
                        + "WHERE email=? AND password=?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String name = rs.getNString("name");
                    int role = rs.getInt("role");
                    String status = rs.getString("status");
                    this.loginUser = new UsersDTO(email, name, password, role, status);
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public boolean createNewAccount(String email,String name,String password) 
            throws SQLException, NamingException{
        boolean result = false;
        try{
            cn = DBHelpers.makeConnection();
            if(cn!=null){
                String sql = "INSERT INTO Users ( email, name, password, role, status ) "
                        + "VALUES  (?,?,?,?,?)";
                pst=cn.prepareStatement(sql);
                pst.setString(1,email);
                pst.setNString(2, name);
                pst.setNString(3, password);
                pst.setInt(4, ConstantsKey.USER_ROLE);
                pst.setString(5, "New");
                result = pst.executeUpdate() > 0;
               
            }
        }finally{
            closeConnection();
        }
        return result;
    }
}
