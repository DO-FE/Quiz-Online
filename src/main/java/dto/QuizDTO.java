/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dto;

import java.util.List;

/**
 *
 * @author axyres
 */
public class QuizDTO {
    private String quizID;
    private String email;
    private String subjectID;
    private float totalMark;
    private String finishedQuizTime;
    private List<QuestionDTO>  listQuestion;
    private int[] listAnswerSelected;

    public QuizDTO() {
    }

    public QuizDTO(String quizID, String email, String subjectID, float totalMark, String finishedQuizTime, List<QuestionDTO> listQuestion, int[] listAnswerSelected) {
        this.quizID = quizID;
        this.email = email;
        this.subjectID = subjectID;
        this.totalMark = totalMark;
        this.finishedQuizTime = finishedQuizTime;
        this.listQuestion = listQuestion;
        this.listAnswerSelected = listAnswerSelected;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public float getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(float totalMark) {
        this.totalMark = totalMark;
    }

    public String getFinishedQuizTime() {
        return finishedQuizTime;
    }

    public void setFinishedQuizTime(String finishedQuizTime) {
        this.finishedQuizTime = finishedQuizTime;
    }

    public List<QuestionDTO> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<QuestionDTO> listQuestion) {
        this.listQuestion = listQuestion;
    }

    public int[] getListAnswerSelected() {
        return listAnswerSelected;
    }

    public void setListAnswerSelected(int[] listAnswerSelected) {
        this.listAnswerSelected = listAnswerSelected;
    }
    
    
}
