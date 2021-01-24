/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.errors;

import java.io.Serializable;

/**
 *
 * @author Banh Bao
 */
public class UsersError implements Serializable{
    private String emailExistedErr;
    private String emailLengthErr;
    private String passwordLengthErr;
    private String nameLengthErr;
    private String confirmNotMatchedErr;    

    public UsersError() {
    }

    public UsersError(String usernameExistedErr, String usernameLengthErr, String passwordLengthErr, String nameLengthErr, String confirmNotMatchedErr) {
        this.emailExistedErr = usernameExistedErr;
        this.emailLengthErr = usernameLengthErr;
        this.passwordLengthErr = passwordLengthErr;
        this.nameLengthErr = nameLengthErr;
        this.confirmNotMatchedErr = confirmNotMatchedErr;
    }

    /**
     * @return the emailExistedErr
     */
    public String getEmailExistedErr() {
        return emailExistedErr;
    }

    /**
     * @param emailExistedErr the emailExistedErr to set
     */
    public void setEmailExistedErr(String emailExistedErr) {
        this.emailExistedErr = emailExistedErr;
    }

    /**
     * @return the emailLengthErr
     */
    public String getEmailLengthErr() {
        return emailLengthErr;
    }

    /**
     * @param emailLengthErr the emailLengthErr to set
     */
    public void setEmailLengthErr(String emailLengthErr) {
        this.emailLengthErr = emailLengthErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the nameLengthErr
     */
    public String getNameLengthErr() {
        return nameLengthErr;
    }

    /**
     * @param nameLengthErr the nameLengthErr to set
     */
    public void setNameLengthErr(String nameLengthErr) {
        this.nameLengthErr = nameLengthErr;
    }

    /**
     * @return the confirmNotMatchedErr
     */
    public String getConfirmNotMatchedErr() {
        return confirmNotMatchedErr;
    }

    /**
     * @param confirmNotMatchedErr the confirmNotMatchedErr to set
     */
    public void setConfirmNotMatchedErr(String confirmNotMatchedErr) {
        this.confirmNotMatchedErr = confirmNotMatchedErr;
    }
    
    
}
