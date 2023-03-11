/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dto;

/**
 *
 * @author axyres
 */
public class QuestionDTO implements java.io.Serializable {
    private String questionID ;
    private String question_content ;
    private String createDate ;
    private String subjectID ;
    private boolean status;

    public QuestionDTO() {
    }

    public QuestionDTO(String questionID, String question_content, String createDate, String subjectID, boolean status) {
        this.questionID = questionID;
        this.question_content = question_content;
        this.createDate = createDate;
        this.subjectID = subjectID;
        this.status = status;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
