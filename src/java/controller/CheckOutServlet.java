package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dal.OrderDAO;
import model.*;

@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            Account auth = (Account) request.getSession().getAttribute("auth");
            if (cart_list != null && auth != null) {
                for (Cart c : cart_list) {
                    Order order = new Order();
                    order.setItemId(c.getItemId());
                    order.setAccountId(auth.getUserId());
                    order.setQuantity(c.getQuantity());
                    order.setDate(formatter.format(date));

                    OrderDAO oDao = new OrderDAO();
                    boolean result = oDao.insertOrder(order);
                    if (!result) {
                        break;
                    }
                }
                cart_list.clear();
                response.sendRedirect(UrlConstant.ORDERED_URL);
            } else if (auth == null) {
                response.sendRedirect(UrlConstant.LOGIN_URL);
            } else {

                response.sendRedirect(UrlConstant.CART_URL);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
