/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dto;

/**
 *
 * @author axyres
 */
public class AnswerDTO implements java.io.Serializable {
    private String answerID ;
    private String answer_content ;
    private boolean answer_correct ;
    private String questionID ;
    private boolean status;

    public AnswerDTO() {
    }

    public AnswerDTO(String answerID, String answer_content, boolean answer_correct, String questionID, boolean status) {
        this.answerID = answerID;
        this.answer_content = answer_content;
        this.answer_correct = answer_correct;
        this.questionID = questionID;
        this.status = status;
    }

    public String getAnswerID() {
        return answerID;
    }

    public void setAnswerID(String answerID) {
        this.answerID = answerID;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public boolean isAnswer_correct() {
        return answer_correct;
    }

    public void setAnswer_correct(boolean answer_correct) {
        this.answer_correct = answer_correct;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
