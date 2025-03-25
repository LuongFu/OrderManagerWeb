package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dal.*;
import model.*;

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            Account auth = (Account) request.getSession().getAttribute("auth");

            if (auth != null) {
                String productId = request.getParameter("id");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                if (productQuantity <= 0) {
                    productQuantity = 1;
                }

                // Tạo đối tượng đơn hàng
                Order orderModel = new Order();
                orderModel.setAccountId(auth.getUserId());
                orderModel.setItemId(Integer.parseInt(productId));
                orderModel.setQuantity(productQuantity);
                orderModel.setDate(formatter.format(date));
                orderModel.setStatus("Completed");

                // Lưu đơn hàng vào cơ sở dữ liệu
                OrderDAO orderDAO = new OrderDAO();
                boolean result = orderDAO.insertOrder(orderModel);
                if (result) {
                    // Xóa sản phẩm khỏi giỏ hàng
                    ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                    if (cart_list != null) {
                        for (Cart c : cart_list) {
                            if (c.getItemId() == Integer.parseInt(productId)) {
                                cart_list.remove(cart_list.indexOf(c));
                                break;
                            }
                        }
                    }
                    response.sendRedirect("orders.jsp");
                } else {
                    out.println("Order failed.");
                }
            } else {
                response.sendRedirect("login.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Account auth = (Account) request.getSession().getAttribute("auth");

        if (auth != null) {
            // Lấy danh sách đơn hàng của người dùng
            OrderDAO orderDAO = new OrderDAO();
            List<Order> orders = orderDAO.userOrders(auth.getUserId());
            
            // Chuyển dữ liệu vào JSP
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("orders.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}

//@WebServlet("/order-now")
//public class OrderNowServlet extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        
//        try (PrintWriter out = response.getWriter()) {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = new Date();
//
//            Account auth = (Account) request.getSession().getAttribute("auth");
//
//            if (auth != null) {
//                String productId = request.getParameter("id");
//                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
//                if (productQuantity <= 0) {
//                    productQuantity = 1;
//                }
//
//                // Tạo đối tượng đơn hàng
//                Order orderModel = new Order();
//                orderModel.setAccountId(auth.getUserId());
//                orderModel.setItemId(Integer.parseInt(productId));
//                orderModel.setQuantity(productQuantity);
//                orderModel.setDate(formatter.format(date));
//                orderModel.setStatus("Completed");
//
//                // Lưu đơn hàng vào cơ sở dữ liệu
//                OrderDAO orderDAO = new OrderDAO();
//                boolean result = orderDAO.insertOrder(orderModel);
//                if (result) {
//                    // Xóa sản phẩm khỏi giỏ hàng
//                    ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
//                    if (cart_list != null) {
//                        for (Cart c : cart_list) {
//                            if (c.getItemId() == Integer.parseInt(productId)) {
//                                cart_list.remove(cart_list.indexOf(c));
//                                break;
//                            }
//                        }
//                    }
//                    response.sendRedirect("orders.jsp");
//                } else {
//                    out.println("Order failed.");
//                }
//            } else {
//                response.sendRedirect("login.jsp");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//        Account auth = (Account) request.getSession().getAttribute("auth");
//
//        if (auth != null) {
//            // Lấy danh sách đơn hàng của người dùng
//            OrderDAO orderDAO = new OrderDAO();
//            List<Order> orders = orderDAO.userOrders(auth.getUserId());
//            
//            // Chuyển dữ liệu vào JSP
//            request.setAttribute("orders", orders);
//            request.getRequestDispatcher("orders.jsp").forward(request, response);
//        } else {
//            response.sendRedirect("login.jsp");
//        }
//    }


    
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        
//        try (PrintWriter out = response.getWriter()) {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = new Date();
//
//            Account auth = (Account) request.getSession().getAttribute("auth");
//
//            if (auth != null) {
//                String productId = request.getParameter("id");
//                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
//                if (productQuantity <= 0) {
//                    productQuantity = 1;
//                }
//                Item item = new Item(); // debug
//                Order orderModel = new Order();
//                orderModel.setAccountId(auth.getUserId());
//                orderModel.setItemId(Integer.parseInt(productId));
//                orderModel.setQuantity(productQuantity);
//                orderModel.setDate(formatter.format(date));
//                orderModel.setStatus("Completed");
////                double totalAmount = item.getPrice() * orderModel.getQuantity();  // Tính tổng tiền cho đơn hàng
////                orderModel.setTotalAmount(totalAmount);
//                OrderDAO orderDAO = new OrderDAO();
//                boolean result = orderDAO.insertOrder(orderModel);
//                if (result) {
//                    ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
//                    if (cart_list != null) {
//                        for (Cart c : cart_list) {
//                            if (c.getItemId() == Integer.parseInt(productId)) {
//                                cart_list.remove(cart_list.indexOf(c));
//                                break;
//                            }
//                        }
//                    }
//                    response.sendRedirect("orders.jsp");
//                } else {
//                    out.println("order failed");
//                }
//            } else {
//                response.sendRedirect("login.jsp");
//            }
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }


//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    response.setContentType("text/html;charset=UTF-8");
//
//    Account auth = (Account) request.getSession().getAttribute("auth");
//
//    if (auth != null) {
//        int userId = auth.getUserId();
//        
//        // Lấy danh sách đơn hàng của người dùng
//        OrderDAO orderDAO = new OrderDAO();
//        List<Order> orders = orderDAO.getOrdersByUserId(userId);
//        
//        // Chuyển dữ liệu vào JSP
//        request.setAttribute("orders", orders);
//        request.getRequestDispatcher("orders.jsp").forward(request, response);
//    } else {
//        response.sendRedirect("login.jsp");
//    }
//}

//}
