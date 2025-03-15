/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.MessageConstant;
import dto.Response;
import java.sql.*;
import model.AccountRole;

/**
 *
 * @author Fu
 */
public class AccountRoleDAO extends DBContext{
    
    private final String ACCOUNT_ROLE_INSERT = "INSERT INTO AccountRole (username, roleId) VALUES (?, ?)";
    private final String ACCOUNT_ROLE_CHECK_ROLE = "SELECT [username],[roleId] FROM [dbo].[AccountRole] where username = ?";
    
    public boolean addAccountRole(String username){
        try{
            PreparedStatement ps = c.prepareStatement(ACCOUNT_ROLE_INSERT);
            ps.setString(1, username);
            ps.setInt(2, 1); // dang ki role khach hang
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
//    public Response<AccountRole> checkAccountRole(String username){
//        try{
//            PreparedStatement ps = c.prepareStatement(ACCOUNT_ROLE_CHECK_ROLE);
//            ps.setString(1, username);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()){
//                return new Response<>(new AccountRole(username, rs.getInt("roleId")), true, MessageConstant.MESSAGE_SUCCESS);
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return new Response<>(null, false, MessageConstant.MESSAGE_FAILED);
//    }
    
    public Response<AccountRole> checkAccountRole(String username) {
    try {
        System.out.println("DEBUG: Checking role for username: " + username);
        PreparedStatement ps = c.prepareStatement(ACCOUNT_ROLE_CHECK_ROLE);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int roleId = rs.getInt("roleId");
            System.out.println("DEBUG: Role ID retrieved: " + roleId);
            return new Response<>(new AccountRole(username, roleId), true, MessageConstant.MESSAGE_SUCCESS);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println("DEBUG: Role retrieval failed for username: " + username);
    return new Response<>(null, false, MessageConstant.MESSAGE_FAILED);
}

}
