/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.errors;

/**
 *
 * @author Banh Bao
 */
public class CreateError {
    private String noSubject;
    private String noQuestion;
    private String notEnoughFourAnwsers;
    private String notSetCorrectAnswer;
    

    public CreateError() {
    }

    public CreateError(String noSubject, String noQuestion, String notEnoughFourAnwsers, String notSetCorrectAnswer) {
        this.noSubject = noSubject;
        this.noQuestion = noQuestion;
        this.notEnoughFourAnwsers = notEnoughFourAnwsers;
        this.notSetCorrectAnswer = notSetCorrectAnswer;
    }

   

    /**
     * @return the noSubject
     */
    public String getNoSubject() {
        return noSubject;
    }

    /**
     * @param noSubject the noSubject to set
     */
    public void setNoSubject(String noSubject) {
        this.noSubject = noSubject;
    }

    /**
     * @return the noQuestion
     */
    public String getNoQuestion() {
        return noQuestion;
    }

    /**
     * @param noQuestion the noQuestion to set
     */
    public void setNoQuestion(String noQuestion) {
        this.noQuestion = noQuestion;
    }

    /**
     * @return the notEnoughFourAnwsers
     */
    public String getNotEnoughFourAnwsers() {
        return notEnoughFourAnwsers;
    }

    /**
     * @param notEnoughFourAnwsers the notEnoughFourAnwsers to set
     */
    public void setNotEnoughFourAnwsers(String notEnoughFourAnwsers) {
        this.notEnoughFourAnwsers = notEnoughFourAnwsers;
    }

    /**
     * @return the notSetCorrectAnswer
     */
    public String getNotSetCorrectAnswer() {
        return notSetCorrectAnswer;
    }

    /**
     * @param notSetCorrectAnswer the notSetCorrectAnswer to set
     */
    public void setNotSetCorrectAnswer(String notSetCorrectAnswer) {
        this.notSetCorrectAnswer = notSetCorrectAnswer;
    }
    
    
    
}
