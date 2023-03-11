/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dto;

/**
 *
 * @author axyres
 */
public class QuestionErrorDTO {
    private String questionErrorID ;
    private String question_contentError ;
    private String createDateError ;
    private String subjectIDError ;
    private String statusError;
    private String answer_contentError;

    public QuestionErrorDTO() {
    }

    public QuestionErrorDTO(String questionErrorID, String question_contentError, String createDateError, String subjectIDError, String statusError, String answer_contentError) {
        this.questionErrorID = questionErrorID;
        this.question_contentError = question_contentError;
        this.createDateError = createDateError;
        this.subjectIDError = subjectIDError;
        this.statusError = statusError;
        this.answer_contentError = answer_contentError;
    }

    public String getQuestionErrorID() {
        return questionErrorID;
    }

    public void setQuestionErrorID(String questionErrorID) {
        this.questionErrorID = questionErrorID;
    }

    public String getQuestion_contentError() {
        return question_contentError;
    }

    public void setQuestion_contentError(String question_contentError) {
        this.question_contentError = question_contentError;
    }

    public String getCreateDateError() {
        return createDateError;
    }

    public void setCreateDateError(String createDateError) {
        this.createDateError = createDateError;
    }

    public String getSubjectIDError() {
        return subjectIDError;
    }

    public void setSubjectIDError(String subjectIDError) {
        this.subjectIDError = subjectIDError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

    public String getAnswer_contentError() {
        return answer_contentError;
    }

    public void setAnswer_contentError(String answer_contentError) {
        this.answer_contentError = answer_contentError;
    }
    
}
