/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import constant.MessageConstant;
import dto.Response;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import service.AccountRoleService;
import service.AccountService;

/**
 *
 * @author Fu
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        System.out.println("DEBUG: Attempting login with username: " + username + " and password: " + password);

        AccountService accountService = new AccountService();
        AccountRoleService accountRoleService = new AccountRoleService();

        Response<Account> accountResponse = accountService.checkLogin(username, password);
        if (accountResponse.isSuccess()) {

            // Create session after successful login
            HttpSession session = request.getSession();
            session.setAttribute("session_login", username);

            // Check for admin role
            boolean isAdmin = accountRoleService.isAdmin(username);
            session.setAttribute("isAdmin", isAdmin); // Store role in session for future checks

            // Handle rememberMe functionality with cookies if needed
            if (rememberMe != null) {
                Cookie usernameCookie = new Cookie("COOKIE_USERNAME", username);
                Cookie passwordCookie = new Cookie("COOKIE_PASSWORD", password);
                usernameCookie.setMaxAge(60 * 60 * 24); // 1 day
                passwordCookie.setMaxAge(60 * 60 * 24); // 1 day
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }

            // Redirect based on user role
            if (isAdmin) {
                response.sendRedirect(UrlConstant.ADMIN_URL); // Redirect to admin page
            } else {
                response.sendRedirect(UrlConstant.WELCOME_URL); // Redirect to user dashboard
            }
            // count login
            Integer loginCount = (Integer) session.getServletContext().getAttribute("loginCount");
            if (loginCount == null) {
                loginCount = 0;
            }
            session.getServletContext().setAttribute("loginCount", ++loginCount);

        } else {
            // Login failed
            System.out.println("DEBUG: Login failed for username: " + username);
            request.setAttribute("error", MessageConstant.LOGIN_FAILED);
            request.getRequestDispatcher(UrlConstant.LOGIN_URL).forward(request, response);
        }
    }

}
