/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import dto.QuizDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author axyres
 */
public class QuizDAO {
    
    private DataSource dataSource;
    
    public QuizDAO() throws NamingException {
        Context context = new InitialContext();
        dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/QuizOnline");
    }
    public int getNoOfQuiz() throws SQLException{
        int result=0;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = dataSource.getConnection();
            if(conn!=null){
                String sql="SELECT Count(status) as NoOfQuiz " +
                           "From Quizs";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                if(rs.next()){
                    result=rs.getInt("NoOfQuiz");
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
    
    public void createQuiz(QuizDTO quiz) throws SQLException{
        Connection conn= null;
        PreparedStatement stm= null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            if(conn!=null){
                String email = quiz.getEmail();
                String accountIDSql = "SELECT account_id FROM Account WHERE email = ? ";
                stm = conn.prepareStatement(accountIDSql);
                stm.setString(1, email);
                rs = stm.executeQuery();

                if (rs.next()) {
                    String accountID = rs.getString("account_id");
                    // insert quiz into Quizs table
                    String sql="INSERT INTO Quizs (quizID, account_id, subjectID, totalMark, finishedQuizTime, status)" +
                               "values(?,?,?,?,?,1)";
                    stm=conn.prepareStatement(sql);
                    stm.setString(1, quiz.getQuizID());
                    stm.setString(2, accountID);
                    stm.setString(3, quiz.getSubjectID());
                    stm.setFloat(4, quiz.getTotalMark());
                    stm.setString(5, quiz.getFinishedQuizTime());
                    stm.executeUpdate();
                } else {
                    // handle case where no account with the given email exists
                    System.out.println("No account found with email: " + email);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    
    
    
    public void updateTotalMark(String quizID,float totalMark) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn = dataSource.getConnection();
            if(conn!=null){
                String sql="UPDATE Quizs " +
                           "SET totalMark = ? " +
                           "WHERE quizID = ? ";
                stm=conn.prepareStatement(sql);           
                stm.setFloat(1, totalMark);
                stm.setString(2, quizID);             
                stm.executeUpdate();          
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    
    public void updateFinishTime(String quizID,String finishTime) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn = dataSource.getConnection();
            if(conn!=null){
                String sql="UPDATE Quizs " +
                           "SET finishedQuizTime = ? " +
                           "WHERE quizID = ? ";
                stm=conn.prepareStatement(sql);           
                stm.setString(1, finishTime);
                stm.setString(2, quizID);             
                stm.executeUpdate();          
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    
    public QuizDTO getQuizNotFinish(String email, String strCurrentDate) throws SQLException{
        QuizDTO result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = dataSource.getConnection();
            if(conn!=null){
                String sql="SELECT quizID, subjectID, totalMark, finishedQuizTime " +
                           "FROM Quizs INNER JOIN Account ON Quizs.account_id = Account.account_id " +
                           "WHERE email = ? and finishedQuizTime > ? ";
                stm=conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, strCurrentDate);
                rs=stm.executeQuery();
                if(rs.next()){
                    String quizID=rs.getString("quizID");
                    String subjectID=rs.getString("subjectID");
                    float totalMark=rs.getFloat("totalMark");
                    String finishedQuizTime=rs.getString("finishedQuizTime");                 
                    result=new QuizDTO(quizID, email, subjectID, totalMark, finishedQuizTime, null, null);
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
    
    public List<String> getHistory(String email,String subjectID,int pageNum) throws SQLException{
        List<String> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = dataSource.getConnection();
            if(conn!=null){
                String sql="SELECT quizID, subjectID, totalMark, finishedQuizTime " +
                           "FROM Quizs INNER JOIN Account ON Quizs.account_id = Account.account_id " +
                           "WHERE Account.email = ? and subjectID like ? and finishedQuizTime < ? "
                        +   "ORDER BY finishedQuizTime " +
                            "OFFSET (? - 1)*5 rows fetch next 5 rows only ";
                stm=conn.prepareStatement(sql);
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date date = new Date();
                String strCurrentDate = formatter.format(date);
                stm.setString(1, email);
                stm.setString(2,"%"+subjectID+"%");
                stm.setString(3, strCurrentDate);
                stm.setInt(4, pageNum);
                result=new ArrayList();
                rs=stm.executeQuery();
                while(rs.next()){
                    String quizID=rs.getString("quizID");
                    String subjectID1=rs.getString("subjectID");
                    float totalMark=rs.getFloat("totalMark");
                    String finishedQuizTime=rs.getString("finishedQuizTime");                 
                    result.add(quizID+"_"+subjectID1+"_"+totalMark+"_"+finishedQuizTime);
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
    public int getCountQuizOfStudent(String email,String subjectID) throws SQLException {
        int result = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(quizID) as num " +
                            "FROM Quizs INNER JOIN Account ON Quizs.account_id = Account.account_id " +
                            "WHERE Account.email = ? and subjectID like ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, "%"+subjectID+"%");
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("num");
                    if (result >= 0) {
                        return result;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public boolean checkTimeFinish(String currentTime,String email) throws SQLException{
        boolean result=false;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = dataSource.getConnection();
            if(conn!=null){
                String sql="SELECT quizID " +
                        "FROM Quizs Q INNER JOIN Account ON Quizs.account_id = Account.account_id " +
                        "WHERE ? < finishedQuizTime and Account.email = ? ";
                stm=conn.prepareStatement(sql);
                stm.setString(1, currentTime);
                stm.setString(2,email);
                rs=stm.executeQuery();
                while(rs.next()){
                    return true;
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
}
