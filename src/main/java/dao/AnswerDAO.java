/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import dto.AnswerDTO;
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
public class AnswerDAO {
    private DataSource dataSource;
    
    public AnswerDAO() throws NamingException {
        Context context = new InitialContext();
        dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/QuizOnline");
    }
    public List<AnswerDTO> getAnswersFromQuestionID(String questionID) throws SQLException {
        List<AnswerDTO> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            if (conn != null) {
                String sql = "SELECT answerID, answer_content, questionID, answer_correct, status " +
                             "FROM Answers " +
                             "WHERE questionID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, questionID); 
                rs = stm.executeQuery();
                while (rs.next()) {
                    
                    String answerID =rs.getString("answerID");
                    String answer_content=rs.getString("answer_content");
                    boolean answer_correct=rs.getBoolean("answer_correct");
                    boolean status=rs.getBoolean("status");
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new AnswerDTO(answerID, answer_content, answer_correct, questionID, status));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving answers from questionID: " + questionID, e);
        }
        return result;
    }
    
    public void update(AnswerDTO answerDTO) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn = dataSource.getConnection();
            if(conn!=null){
                String sql="UPDATE Answers " +
                           "SET answer_content = ?, answer_correct = ? " +
                           "WHERE answerID = ? ";
                stm=conn.prepareStatement(sql);           
                stm.setString(1, answerDTO.getAnswer_content());             
                stm.setBoolean(2, answerDTO.isAnswer_correct());
                stm.setString(3,answerDTO.getAnswerID());
                stm.executeUpdate();          
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    
    public void create(AnswerDTO answer) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn = dataSource.getConnection();
            if(conn!=null){
                String sql="INSERT Answers(answer_content, answer_correct, questionID, status) " 
                        + "VALUES ( ?, ?, ?, 1)";
                stm=conn.prepareStatement(sql);
                stm.setString(1,answer.getAnswer_content());
                stm.setBoolean(2,answer.isAnswer_correct());
                stm.setString(3,answer.getQuestionID());      
                stm.executeUpdate();          
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    
    public boolean checkAnswerCorrect(int answerID) throws SQLException{
        boolean result=false;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = dataSource.getConnection();
            if(conn!=null){
                String sql="SELECT answerID " +
                           "FROM Answers " +
                           "WHERE answerID = ? AND answer_correct = '1' ";
                stm=conn.prepareStatement(sql);
                stm.setInt(1,answerID);
                rs=stm.executeQuery();
                if(rs.next()){
                    result=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    
    public static boolean bruteforce(ArrayList input) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                if (input.get(i).equals(input.get(j)) && i != j) {
                    return false;
                }
            }
        }
        return true;
    }
}
