/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.dto;

import java.io.Serializable;

/**
 *
 * @author Banh Bao
 */
public class SubjectDTO implements Serializable {
    private String subjectID;
    private String subjectName;
    private int numOfQues;
    private int timeOfQues;

    public SubjectDTO() {
    }

    public SubjectDTO(String subjectID, String subjectName, int numOfQues, int timeOfQues) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.numOfQues = numOfQues;
        this.timeOfQues = timeOfQues;
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
     * @return the subjectName
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * @param subjectName the subjectName to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * @return the numOfQues
     */
    public int getNumOfQues() {
        return numOfQues;
    }

    /**
     * @param numOfQues the numOfQues to set
     */
    public void setNumOfQues(int numOfQues) {
        this.numOfQues = numOfQues;
    }

    /**
     * @return the timeOfQues
     */
    public int getTimeOfQues() {
        return timeOfQues;
    }

    /**
     * @param timeOfQues the timeOfQues to set
     */
    public void setTimeOfQues(int timeOfQues) {
        this.timeOfQues = timeOfQues;
    }
    
    
}
