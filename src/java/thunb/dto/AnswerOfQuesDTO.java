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
public class AnswerOfQuesDTO implements Serializable {
    private int ansID;
    private  String ansContent;
    private int quesID;
    private boolean isTrue;

    public AnswerOfQuesDTO() {
    }

    public AnswerOfQuesDTO(String ansContent, boolean isTrue) {
        this.ansContent = ansContent;
        this.isTrue = isTrue;
    }
    
    public AnswerOfQuesDTO(int ansID, String ansContent, int quesID, boolean isTrue) {
        this.ansID = ansID;
        this.ansContent = ansContent;
        this.quesID = quesID;
        this.isTrue = isTrue;
    }

    /**
     * @return the ansID
     */
    public int getAnsID() {
        return ansID;
    }

    /**
     * @param ansID the ansID to set
     */
    public void setAnsID(int ansID) {
        this.ansID = ansID;
    }

    /**
     * @return the ansContent
     */
    public String getAnsContent() {
        return ansContent;
    }

    /**
     * @param ansContent the ansContent to set
     */
    public void setAnsContent(String ansContent) {
        this.ansContent = ansContent;
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
     * @return the isTrue
     */
    public boolean isIsTrue() {
        return isTrue;
    }

    /**
     * @param isTrue the isTrue to set
     */
    public void setIsTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }
    
    
}
