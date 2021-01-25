/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Banh Bao
 */
public class QuestionDTO implements Serializable {
    private int quesID;
    private String quesContent;
    private Timestamp createDate;
    private String subjectID;
    private boolean status;

    public QuestionDTO() {
    }

    
    public QuestionDTO(int quesID, String quesContent, Timestamp createDate, String subjectID, boolean status) {
        this.quesID = quesID;
        this.quesContent = quesContent;
        this.createDate = createDate;
        this.subjectID = subjectID;
        this.status = status;
    }

    /**
     * @return the quesID
     */
    public int getQuesID() {
        return quesID;
    }

    /**
     * @param quesID the quesID to set
     */
    public void setQuesID(int quesID) {
        this.quesID = quesID;
    }

    /**
     * @return the quesContent
     */
    public String getQuesContent() {
        return quesContent;
    }

    /**
     * @param quesContent the quesContent to set
     */
    public void setQuesContent(String quesContent) {
        this.quesContent = quesContent;
    }

    /**
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the subjectID
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * @param subjectID the subjectID to set
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
