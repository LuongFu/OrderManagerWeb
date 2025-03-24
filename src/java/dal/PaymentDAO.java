package dal;

import model.Payment;
import java.sql.*;
import java.util.*;

public class PaymentDAO extends DBContext {

    private final String PAYMENT_INSERT = "INSERT INTO Payment (orderId, amount, paymentMethod, paymentDate) VALUES (?, ?, ?, ?)";
    private final String PAYMENT_SELECT_BY_ORDER_ID = "SELECT * FROM Payment WHERE orderId = ?";
    private final String PAYMENT_UPDATE = "UPDATE Payment SET amount = ?, paymentMethod = ?, paymentDate = ? WHERE paymentId = ?";
    private final String PAYMENT_SELECT_ALL = "SELECT * FROM Payment";

    public boolean addPayment(Payment payment) {
        try {
            PreparedStatement ps = c.prepareStatement(PAYMENT_INSERT);
            ps.setInt(1, payment.getOrderId());
            ps.setDouble(2, payment.getAmount());
            ps.setString(3, payment.getPaymentMethod());
            ps.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Payment getPaymentByOrderId(int orderId) {
        try {
            PreparedStatement ps = c.prepareStatement(PAYMENT_SELECT_BY_ORDER_ID);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Payment(rs.getInt("paymentId"),
                                   rs.getInt("orderId"),
                                   rs.getDouble("amount"),
                                   rs.getString("paymentMethod"),
                                   rs.getDate("paymentDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePayment(Payment payment) {
        try {
            PreparedStatement ps = c.prepareStatement(PAYMENT_UPDATE);
            ps.setDouble(1, payment.getAmount());
            ps.setString(2, payment.getPaymentMethod());
            ps.setDate(3, new java.sql.Date(payment.getPaymentDate().getTime()));
            ps.setInt(4, payment.getPaymentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement(PAYMENT_SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment(rs.getInt("paymentId"),
                                              rs.getInt("orderId"),
                                              rs.getDouble("amount"),
                                              rs.getString("paymentMethod"),
                                              rs.getDate("paymentDate"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
}
