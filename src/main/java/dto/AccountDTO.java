/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dto;

/**
 *
 * @author axyres
 */
public class AccountDTO {
    
    private String email;
    private String password;
    private String fullname;
    private int phone;
    private String roleID;
    private boolean status;

    public AccountDTO() {
    }

    public AccountDTO(String email, String password, String fullname,int phone, String roleID, boolean status) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.roleID = roleID;
        this.status = status;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    
    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
