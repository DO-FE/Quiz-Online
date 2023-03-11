/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import dto.AccountDTO;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author axyres
 */
public class AccountDAO {
    
    private DataSource dataSource;
    
    public AccountDAO() throws NamingException {
        Context context = new InitialContext();
        dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/QuizOnline");
    }
    
    public AccountDTO checkLogin(String email, String password) throws SQLException, NoSuchAlgorithmException {
        AccountDTO acc = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
        
            if (conn != null) {
                String sql = "Select email, fullname, password, phone, RoleCode, status "
                        + "FROM Account INNER JOIN AccRole ON Account.account_id = AccRole.account_id "
                        + "WHERE email = ? AND password = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullname");
                    int phone = rs.getInt("phone");
                    String roleID = rs.getString("RoleCode");
                    boolean status = rs.getBoolean("status");
                    if(status==true){
                        acc = new AccountDTO(email, "password", fullName, phone, roleID, status);
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
        return acc;
    }
    
    public AccountDTO createLogin(String email, String password, String fullname, int phone) throws SQLException, NoSuchAlgorithmException {
        String sql = "SELECT email FROM Account WHERE email = ?";
        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Email already taken, return null
                return null;
            } else {
                // Email not taken, create new account and return it
                String mssql1 = "INSERT Account(account_id, email, password, fullname, phone, status) "
                        + "VALUES (?, ?, ?, ?, ?, ?) ";
                PreparedStatement Statement = connection.prepareStatement(mssql1);
                String accountId = "acc" + String.format("%03d", getNextAccountId());
                
                Statement.setString(1, accountId);
                Statement.setString(2, email);
                Statement.setString(3, password);
                Statement.setString(4, fullname);
                Statement.setInt(5, phone);
                Statement.setBoolean(6, true);
                Statement.executeUpdate();
                
                String mssql2 = "INSERT AccRole(account_id, RoleCode) VALUES(?, ?)";
                PreparedStatement statement2 = connection.prepareStatement(mssql2);
                statement2.setString(1, accountId);
                statement2.setString(2, "2"); // Change this to the default role code you want to insert
                statement2.executeUpdate();
                
                return new AccountDTO(email, password, fullname, phone, "2", true);
            }
        }
    }
    
    private int getNextAccountId() throws SQLException {
        int accountId = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            stm = conn.prepareStatement("SELECT NEXT VALUE FOR AccountSeq AS AccountNum");
            rs = stm.executeQuery();

            if (rs.next()) {
                accountId = rs.getInt("AccountNum");
            }
        } finally {
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }

        return accountId;
    }
    
    private static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {   
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    
    private static String toHexString(byte[] hash) 
    { 
        BigInteger number = new BigInteger(1, hash);  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
        return hexString.toString();  
    } 
}
