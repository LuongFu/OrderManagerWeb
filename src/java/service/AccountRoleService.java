/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.AccountRoleDAO;
import dto.Response;
import model.*;

/**
 *
 * @author Fu
 */
public class AccountRoleService {

    AccountRoleDAO accountRoleDAO;

    public AccountRoleService() {
        accountRoleDAO = new AccountRoleDAO();
    }

//    public boolean isAdmin(String username){
//        Response<AccountRole> accountRole = accountRoleDAO.checkAccountRole(username);
//        return accountRole.getData().getRoleId() == 1;
//    }
    public boolean isAdmin(String username) {
        Response<AccountRole> accountRole = accountRoleDAO.checkAccountRole(username);
        if (accountRole.getData() == null) {
            return false;
        }
        return accountRole.getData().getRoleId() == 1;
    }

}
