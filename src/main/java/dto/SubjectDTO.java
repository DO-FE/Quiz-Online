/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dto;

/**
 *
 * @author axyres
 */
public class SubjectDTO implements java.io.Serializable{
    private String subjectID ;
    private String subjectName ;
    private int numberQuestion ;
    private int timeQuiz ;
    private boolean status;

    public SubjectDTO() {
    }

    public SubjectDTO(String subjectID, String subjectName, int numberQuestion, int timeQuiz, boolean status) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.numberQuestion = numberQuestion;
        this.timeQuiz = timeQuiz;
        this.status = status;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public int getTimeQuiz() {
        return timeQuiz;
    }

    public void setTimeQuiz(int timeQuiz) {
        this.timeQuiz = timeQuiz;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
