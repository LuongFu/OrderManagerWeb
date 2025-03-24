package controller;

import dal.PaymentDAO;
import model.Payment;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        String paymentMethod = request.getParameter("paymentMethod");

        // Tạo đối tượng Payment từ thông tin nhận được
        Payment payment = new Payment(orderId, amount, paymentMethod, new java.util.Date());

        // Gọi PaymentDAO để thêm thanh toán vào cơ sở dữ liệu
        PaymentDAO paymentDAO = new PaymentDAO();
        boolean isPaymentSuccessful = paymentDAO.addPayment(payment);

        // Điều hướng tới trang payment-result.jsp với kết quả thanh toán
        request.setAttribute("paymentStatus", isPaymentSuccessful ? "success" : "failure");
        request.setAttribute("paymentMessage", isPaymentSuccessful ? "Thanh toán thành công!" : "Thanh toán không thành công. Vui lòng thử lại.");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("payment-result.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        // Lấy thông tin thanh toán từ DAO
        PaymentDAO paymentDAO = new PaymentDAO();
        Payment payment = paymentDAO.getPaymentByOrderId(orderId);

        if (payment != null) {
            request.setAttribute("payment", payment);
            request.setAttribute("paymentStatus", "success");
            request.setAttribute("paymentMessage", "Thông tin thanh toán đã được tìm thấy.");
        } else {
            request.setAttribute("paymentStatus", "failure");
            request.setAttribute("paymentMessage", "Không tìm thấy thông tin thanh toán.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("payment-result.jsp");
        dispatcher.forward(request, response);
    }
}
