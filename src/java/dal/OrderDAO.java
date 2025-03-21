package dal;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DBContext {
    
    private final String ORDER_INSERT = "INSERT INTO [Order] (accountId, itemId, quantity, date) VALUES(?,?,?,?)";
    private final String ACCOUNT_ORDER = "SELECT * FROM Order WHERE accountId=? ORDER BY Order.orderId DESC";
    private final String CANCEL_ORDER = "DELETE FROM Order WHERE orderId=?";
    
   
    public boolean insertOrder(Order model) {
        boolean result = false;
        try {
            PreparedStatement ps = c.prepareStatement(ORDER_INSERT);
            ps.setInt(1, model.getAccountId());
            ps.setInt(2, model.getItemId());  
            ps.setInt(3, model.getQuantity());
            ps.setString(4,  model.getDate());
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
                order.setPrice(item.getPrice()*rs.getInt("quantity"));
                order.setQuantity(rs.getInt("quantity"));
                order.setDate(rs.getString("date"));
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
}
