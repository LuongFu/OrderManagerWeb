/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Fu
 */
@WebServlet(name = "LogoutController", urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            if(request.getSession().getAttribute("auth")!=null){
                request.getSession().removeAttribute("auth");
                response.sendRedirect(UrlConstant.LOGIN_URL);
            }else{
                response.sendRedirect(UrlConstant.HOME_URL);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
       
        
                                                        // tra ve NULL neu khong co session nao ton tai
                                                        // tra ve session dang ton tai
//        if(session != null){
//            session.invalidate(); // xoa session
//        }
        
//         response.sendRedirect(UrlConstant.LOGIN_URL);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


}
