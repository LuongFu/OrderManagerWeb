package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dal.OrderDAO;
import jakarta.servlet.http.HttpSession;
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
                    int result = oDao.insertOrder(order);
                    if (result >= 0) {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account auth = (Account) session.getAttribute("auth");

        if (auth == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");
        if (cartList == null || cartList.isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        double totalBill = 0;
        OrderDAO orderDAO = new OrderDAO();
        for (Cart cart : cartList) {
            totalBill += cart.getPrice() * cart.getQuantity();
            Order order = new Order();
            order.setAccountId(auth.getUserId());
            order.setItemId(cart.getItemId());
            order.setQuantity(cart.getQuantity());
            order.setTotalAmount(cart.getPrice() * cart.getQuantity());
            orderDAO.insertOrder(order);
        }

        response.sendRedirect("vnpayajax?userId=" + auth.getUserId() + "&totalBill=" + totalBill);

    }

}
