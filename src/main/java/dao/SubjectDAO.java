/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import dto.SubjectDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author axyres
 */
public class SubjectDAO {
    
    private DataSource dataSource;
    
    public SubjectDAO() throws NamingException {
        Context context = new InitialContext();
        dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/QuizOnline");
    }
    
    public SubjectDTO getSubjectFromQuestionID(String questionID) throws SQLException {
        SubjectDTO result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            if (conn != null) {
                String sql = "SELECT Subjects.subjectID, subjectName, numberQuestion, timeQuiz, Subjects.status " +
                             "FROM Questions INNER JOIN Subjects ON Questions.subjectID = Subjects.subjectID " +
                             "WHERE Questions.questionID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, questionID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String subjectID =rs.getString("subjectID");
                    String subjectName=rs.getString("subjectName");
                    int numberQuestion=rs.getInt("numberQuestion");
                    int timeQuiz=rs.getInt("timeQuiz");
                    boolean status=rs.getBoolean("status");
                    result= new SubjectDTO(subjectID, subjectName, numberQuestion, timeQuiz, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    public List<SubjectDTO> getAllSubject() throws SQLException {
        List<SubjectDTO> result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            if (conn != null) {
                String sql = "SELECT subjectID, subjectName, numberQuestion, timeQuiz, status " +
                             "FROM Subjects ";
                stm = conn.prepareStatement(sql);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String subjectID =rs.getString("subjectID");
                    String subjectName=rs.getString("subjectName");
                    int numberQuestion=rs.getInt("numberQuestion");
                    int timeQuiz=rs.getInt("timeQuiz");
                    boolean status=rs.getBoolean("status");
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    if(status==true) {
                        result.add(new SubjectDTO(subjectID, subjectName, numberQuestion, timeQuiz, status));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    public SubjectDTO getSubjectFromSubjectID(String subjectID) throws SQLException {
        SubjectDTO result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            if (conn != null) {
                String sql = "SELECT S.subjectID,subjectName,numberQuestion,timeQuiz,S.status " +
                             "FROM Subjects S " +
                             "where S.subjectID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, subjectID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String subjectName=rs.getString("subjectName");
                    int numberQuestion=rs.getInt("numberQuestion");
                    int timeQuiz=rs.getInt("timeQuiz");
                    boolean status=rs.getBoolean("status");
                    result= new SubjectDTO(subjectID, subjectName, numberQuestion, timeQuiz, status);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving answers from SubjectID: " + subjectID, e);
        }
        return result;
    }
}
