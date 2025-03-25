/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Fu
 */
public class Account {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String cash;

    public Account(int userId, String username, String password, String email, String phone) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public Account(int userId, String username, String password, String email, String phone, String cash) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.cash = cash;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

   
    
}
