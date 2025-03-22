package dal;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;

public class OrderDAO extends DBContext {
    
    private final String ORDER_INSERT = "INSERT INTO [Order] (accountId, itemId, quantity, date, TotalAmount) VALUES(?,?,?,?,?)";
    private final String ACCOUNT_ORDER = "SELECT * FROM Order WHERE accountId=? ORDER BY Order.orderId DESC";
    private final String CANCEL_ORDER = "DELETE FROM Order WHERE orderId=?";
    private final String UPDATE_ORDER_STATUS = "UPDATE [Order] SET [Status] = ? WHERE orderId = ?";
   
    public int insertOrder(Order order) {
        
        try {
            PreparedStatement ps = c.prepareStatement(ORDER_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getAccountId());
            ps.setInt(2, order.getItemId());  
            ps.setInt(3, order.getQuantity());
            ps.setString(4,  order.getDate());
            ps.setDouble(5, order.getTotalAmount());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating payment failed, no rows affected.");
            }
            ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating payment failed, no ID obtained.");
                }
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
        
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
                order.setTotalAmount(itemId);
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
    
    public boolean updateOrderStatus (Order order){
        try{
            PreparedStatement ps = c.prepareStatement(UPDATE_ORDER_STATUS);
            ps.setString(1, order.getStatus());
            ps.setInt(2, order.getOrderId());
            return ps.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }
}
