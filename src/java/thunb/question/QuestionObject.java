/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.question;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import thunb.dto.AnswerOfQuesDTO;

/**
 *
 * @author Banh Bao
 */
public class QuestionObject implements Serializable {

    private Map<String, List<AnswerOfQuesDTO>> ques;

    public QuestionObject() {
        this.ques = new HashMap<>();
    }

    public Map<String, List<AnswerOfQuesDTO>> getQues() {
        return ques;
    }

    public void addAnswerToQuestion(String ques, List<AnswerOfQuesDTO> ans) {
        if (this.ques == null) {
            this.ques = new HashMap<>();
        } 
        this.ques.put(ques, ans);
    }

}
