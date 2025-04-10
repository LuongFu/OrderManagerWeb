package dal;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;

public class OrderDAO extends DBContext {
    
    private final String ORDER_INSERT = "INSERT INTO [Order] (accountId, itemId, quantity, date, Status) VALUES(?,?,?,?,?)";
    private final String ACCOUNT_ORDER = "SELECT * FROM [Order] WHERE accountId=? ORDER BY orderId DESC";
    private final String CANCEL_ORDER = "DELETE FROM Order WHERE orderId=?";
    private final String UPDATE_ORDER_STATUS = "UPDATE [Order] SET [Status] = ? WHERE orderId = ?";
   
    
    public boolean insertOrder(Order order) {
        boolean result = false;
        try {
            PreparedStatement ps = c.prepareStatement(ORDER_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getAccountId());
            ps.setInt(2, order.getItemId());
            ps.setInt(3, order.getQuantity());
            ps.setString(4, order.getDate());
            ps.setString(5, order.getStatus());
//            ps.setDouble(6, order.getTotalAmount());
            ps.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Order> userOrders(int userId) {
        List<Order> list = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement(ACCOUNT_ORDER);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                ItemDAO itemDAO = new ItemDAO();
                int itemId = rs.getInt("itemId");
                Item item = itemDAO.getItemById(itemId);
                order.setOrderId(rs.getInt("orderId"));
                order.setItemId(itemId);
                order.setNameItem(item.getNameItem());
                order.setPrice(item.getPrice() * rs.getInt("quantity"));
                order.setQuantity(rs.getInt("quantity"));
                order.setDate(rs.getString("date"));
                order.setStatus("Completed");
//                order.setTotalAmount(itemId);
                list.add(order);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void cancelOrder(int orderId) {
        try {
            PreparedStatement ps = c.prepareStatement(CANCEL_ORDER);
            ps.setInt(1, orderId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }

    public boolean updateOrderStatus(Order order) {
        try {
            PreparedStatement ps = c.prepareStatement(UPDATE_ORDER_STATUS);
            ps.setString(1, order.getStatus());
            ps.setInt(2, order.getOrderId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    // Trong OrderDAO.java
public List<Order> getOrdersByUserId(int userId) {
    List<Order> orders = new ArrayList<>();
    try {
        String query = "SELECT * FROM [Order] WHERE accountId = ? ORDER BY orderId DESC";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Order order = new Order();
            order.setOrderId(rs.getInt("orderId"));
            order.setAccountId(rs.getInt("accountId"));
            order.setItemId(rs.getInt("itemId"));
            order.setQuantity(rs.getInt("quantity"));
            order.setDate(rs.getString("date"));
            order.setStatus(rs.getString("status"));

            // Lấy thông tin về món ăn từ bảng Item
            ItemDAO itemDAO = new ItemDAO();
            Item item = itemDAO.getItemById(order.getItemId());
            order.setNameItem(item.getNameItem());
            order.setPrice(item.getPrice() * order.getQuantity()); // Tính tổng giá của sản phẩm
            orders.add(order);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return orders;
}

public double getTotalAmountForAllOrders() {
    double totalAmount = 0.0;
    try {
        String query = "SELECT SUM(i.price * o.quantity) AS totalAmount " +
                       "FROM [Order] o " +
                       "JOIN Item i ON o.itemId = i.idItem " +
                       "WHERE o.Status = 'Completed'";
        PreparedStatement ps = c.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            totalAmount = rs.getDouble("totalAmount");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return totalAmount;
}


}
