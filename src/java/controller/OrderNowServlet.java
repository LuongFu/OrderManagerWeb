package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dal.*;
import jakarta.servlet.http.HttpSession;
import model.*;

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            Account auth = (Account) request.getSession().getAttribute("auth");

            if (auth != null) {
                String productId = request.getParameter("id");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                if (productQuantity <= 0) {
                    productQuantity = 1;
                }
                Order orderModel = new Order();
                orderModel.setItemId(Integer.parseInt(productId));
                orderModel.setAccountId(auth.getUserId());
                orderModel.setQuantity(productQuantity);
                orderModel.setDate(formatter.format(date));

                OrderDAO orderDAO = new OrderDAO();
                int result = orderDAO.insertOrder(orderModel);
                if (result >= 0) {
                    ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                    if (cart_list != null) {
                        for (Cart c : cart_list) {
                            if (c.getItemId() == Integer.parseInt(productId)) {
                                cart_list.remove(cart_list.indexOf(c));
                                break;
                            }
                        }
                    }
                    response.sendRedirect(UrlConstant.ORDERED_URL);
                } else {
                    out.println("order failed");
                }
            } else {
                response.sendRedirect(UrlConstant.LOGIN_URL);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int itemId = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        Account auth = (Account) session.getAttribute("auth");

        if (auth == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        ItemDAO itemDAO = new ItemDAO();
        Item item = itemDAO.getItemById(itemId);

        double totalBill = item.getPrice() * quantity;

        Order order = new Order();
        order.setAccountId(auth.getUserId());
        order.setItemId(itemId);
        order.setQuantity(quantity);
        order.setTotalAmount(totalBill);

        OrderDAO orderDAO = new OrderDAO();
        int orderId = orderDAO.insertOrder(order);

        response.sendRedirect("vnpayajax?orderId=" + orderId + "&totalBill=" + totalBill);
    }

}
