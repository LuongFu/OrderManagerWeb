/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import constant.MessageConstant;
import dal.*;
import dto.*;
import utils.*;
import model.Account;

/**
 *
 * @author Fu
 */
public class AccountService {

    AccountDAO accountDAO;
    AccountRoleDAO accountRoleDAO;
    
    public AccountService() {
        accountDAO = new AccountDAO();
        accountRoleDAO = new AccountRoleDAO();
    }

    public Response<Account> checkLogin(String username, String password) {
        Account account = accountDAO.getAccountByUsername(username);
        if (account != null && PasswordHasher.checkPassword(password, account.getPassword())) {
            return new Response<>(account, true, MessageConstant.LOGIN_SUCCESS);
        }
        else{
            return new Response<>(null, false, MessageConstant.LOGIN_FAILED);
        }
        
    }
    
    public boolean register(RegisterRequest request){
        String hashedPassword = PasswordHasher.hashPassword(request.getPassword());
        boolean isSuccessRegister = accountRoleDAO.addAccountRole(request.getUsername());
        if(isSuccessRegister){
            boolean roleSuccess = accountRoleDAO.addAccountRole(request.getUsername());
            if(roleSuccess){
                return true;
            }
        }
        return accountDAO.register(request.getUsername(), hashedPassword, request.getEmail(), request.getPhone());
    }
}
