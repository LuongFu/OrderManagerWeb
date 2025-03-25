/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;

/**
 *
 * @author Fu
 */
public class AccountDAO extends DBContext {

    List<Account> list = new ArrayList();
    public Account a;
    
    private final String GET_EMAIL = "Select * from Account where email = ?";
    private final String ADD_CASH = "UPDATE Account SET cash = cash + ? WHERE userId = ?";
    public List<Account> getAllAccount() {
        String sql = "SELECT * FROM Account";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a = new Account(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("phone"));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Account getAccountByEmail(String email){
        try{
            PreparedStatement ps = c.prepareStatement(GET_EMAIL);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("phone"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Account checkLogin(String username, String password) {
        try {
            String sql = "SELECT * FROM Account WHERE username = ? AND password = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkExistUsername(String username) {
        try {
            String sql = "SELECT * FROM Account WHERE username = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account getAccountByUsername(String username) {
        try {
            String sql = "SELECT * FROM Account WHERE username = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("phone"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean register(String username, String hashedPassword, String email, String phone) {

        try {
            String sql = "INSERT INTO Account VALUES (?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql);

            if (checkExistUsername(username)) {
                return false;
            }

            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ps.setString(3, email);
            ps.setString(4, phone);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Account getAccountById(int id){
        try{
            String sql = "SELECT * FROM Account WHERE userId = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(0, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Account(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("phone"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean addCashById(int userId, double amount) {
    boolean result = false;
    try {
        PreparedStatement ps = c.prepareStatement(ADD_CASH);
        ps.setDouble(1, amount);  // Set số tiền cần thêm vào tài khoản
        ps.setInt(2, userId);     // Set userId của tài khoản

        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            result = true;  // Cập nhật thành công
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

}
