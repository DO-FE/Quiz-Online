/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import dto.QuestionDTO;
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
public class QuestionDAO {
    private DataSource dataSource;
    
    public QuestionDAO() throws NamingException {
        Context context = new InitialContext();
        dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/QuizOnline");
    }
    
    public int getNoOfAdminSearchResult(String question_content, String status, String subjectID) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            if (conn != null) {
                String sql = "";
                if (status.equals("")) {
                    sql = "SELECT COUNT(Questions.questionID) AS NoOfQuestion "
                            + "FROM Questions INNER JOIN Subjects ON Questions.subjectID = Subjects.subjectID "
                            + "WHERE question_content like ? and Questions.subjectID like ?  ";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, "%" + question_content + "%");
                    stm.setString(2, "%" + subjectID + "%");
                    rs = stm.executeQuery();
                    if (rs.next()) {
                        result = rs.getInt("NoOfQuestion");
                    }
                } else {
                    sql = "SELECT COUNT(Questions.questionID) AS NoOfQuestion "
                            + "FROM Questions INNER JOIN Subjects ON Questions.subjectID=Subjects.subjectID "
                            + "WHERE question_content like ? and Questions.status = ? and Questions.subjectID like ?  ";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, "%" + question_content + "%");
                    stm.setBoolean(2, Boolean.parseBoolean(status));
                    stm.setString(3, "%" + subjectID + "%");
                    rs = stm.executeQuery();
                    if (rs.next()) {
                        result = rs.getInt("NoOfQuestion");
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
    public List<QuestionDTO> getListQuestion(String question_content, String status, String subjectID, int currentPage) throws SQLException {
        List<QuestionDTO> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int offset = (currentPage - 1) * 20;
        try {
            conn = dataSource.getConnection();
            if (conn != null) {
                if (status.equals("")) {
                    String sql = "SELECT Questions.questionID, question_content, createDate, Questions.subjectID, Questions.status "
                            + "FROM Questions INNER JOIN Subjects ON Questions.subjectID = Subjects.subjectID "
                            + "WHERE question_content like ? and Questions.subjectID like ?  "
                            + "ORDER BY Subjects.subjectName, question_content "
                            + "OFFSET ? rows fetch next 20 "
                            + "ROWS only ";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, "%" + question_content + "%");
                    stm.setString(2, "%" + subjectID + "%");
                    stm.setInt(3, offset);
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        String questionID = rs.getString("questionID");
                        String question_content1 = rs.getString("question_content");
                        String createDate = rs.getString("createDate");
                        String subjectID1 = rs.getString("subjectID");
                        boolean status1 = rs.getBoolean("status");
                        if (result == null) {
                            result = new ArrayList<>();
                        }
                        result.add(new QuestionDTO(questionID, question_content1, createDate, subjectID1, status1));
                    }
                } else {
                    String sql = "SELECT Questions.questionID, question_content, createDate, Questions.subjectID, Questions.status "
                            + "FROM Questions INNER JOIN Subjects ON Questions.subjectID= Subjects.subjectID "
                            + "WHERE question_content like ? and Questions.status = ? and Questions.subjectID like ?  "
                            + "ORDER BY Subjects.subjectName, question_content "
                            + "OFFSET ? rows fetch next 20 "
                            + "ROWS only";
                    stm = conn.prepareStatement(sql);

                    stm.setString(1, "%" + question_content + "%");
                    stm.setInt(2, Integer.parseInt(status));
                    stm.setString(3, "%" + subjectID + "%");
                    stm.setInt(4, offset);
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        String questionID = rs.getString("questionID");
                        String question_content1 = rs.getString("question_content");
                        String createDate = rs.getString("createDate");
                        String subjectID1 = rs.getString("subjectID");
                        boolean status1 = rs.getBoolean("status");
                        if (result == null) {
                            result = new ArrayList<>();
                        }
                        result.add(new QuestionDTO(questionID, question_content1, createDate, subjectID1, status1));
                    }
                }

            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving answers from SubjectID: " + subjectID, e);
        }
        return result;
    }
    public QuestionDTO getQuestion(String questionID) throws SQLException {
        QuestionDTO result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            if (conn != null) {
                String sql = "SELECT questionID, question_content, createDate, subjectID, status "
                        + "FROM Questions "
                        + "WHERE questionID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, questionID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String question_content1 = rs.getString("question_content");
                    String createDate = rs.getString("createDate");
                    String subjectID = rs.getString("subjectID");
                    boolean status = rs.getBoolean("status");
                    result = new QuestionDTO(questionID, question_content1, createDate, subjectID, status);
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
    
    public void update(QuestionDTO questionDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = dataSource.getConnection();
            if (conn != null) {
                String sql = "UPDATE Questions "
                        + "SET question_content = ? ,subjectID = ? ,status = ?  "
                        + "WHERE questionID = ? ";
                stm = conn.prepareStatement(sql);

                stm.setString(1, questionDTO.getQuestion_content());
                stm.setString(2, questionDTO.getSubjectID());
                stm.setBoolean(3, questionDTO.getStatus());
                stm.setString(4, questionDTO.getQuestionID());

                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    public void delete(String questionID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = dataSource.getConnection();
            if (conn != null) {
                String sql = "UPDATE Questions "
                        + "SET status = 0 "
                        + "WHERE questionID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, questionID);
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    public boolean checkID(String questionID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            if (conn != null) {
                String sql = "SELECT questionID "
                        + "FROM Questions "
                        + "WHERE questionID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, questionID);

                rs = stm.executeQuery();
                if (rs.next()) {
                    result = true;
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
    public void create(QuestionDTO question) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = dataSource.getConnection();
            if (conn != null) {
                String sql = "INSERT Questions "
                        + "VALUES ( ?, ?, ?, ?, ?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, question.getQuestionID());
                stm.setString(2, question.getQuestion_content());
                stm.setString(3, question.getCreateDate());
                stm.setString(4, question.getSubjectID());
                stm.setBoolean(5, true);
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<QuestionDTO> getListQuestionRandom(SubjectDTO subject) throws SQLException {
        List<QuestionDTO> result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP " + subject.getNumberQuestion() + " Questions.questionID, question_content, createDate, Questions.subjectID, Questions.status "
                        + "FROM Questions INNER JOIN Subjects ON Questions.subjectID = Subjects.subjectID "
                        + "WHERE Questions.status = '1' and Questions.subjectID = ?  "
                        + "ORDER BY NEWID() ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, subject.getSubjectID());
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("questionID");
                    String question_content1 = rs.getString("question_content");
                    String createDate = rs.getString("createDate");
                    String subjectID = rs.getString("subjectID");
                    boolean status = rs.getBoolean("status");
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new QuestionDTO(questionID, question_content1, createDate, subjectID, status));
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
}
