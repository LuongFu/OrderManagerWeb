package dal;

import model.Item;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cart;

public class ItemDAO extends DBContext {

    private final String ITEM_SELECT_ALL = "SELECT * FROM Item";
    private final String ITEM_INSERT = "INSERT INTO Item (nameItem, price, description, createdBy, image) VALUES (?, ?, ?, ?, ?)";
    private final String ITEM_SELECT_BY_ID = "SELECT * FROM Item WHERE idItem = ?";
    private final String ITEM_UPDATE = "UPDATE Item SET nameItem = ?, price = ?, description = ?, createdBy = ?, image = ? WHERE idItem = ?";
    private final String ITEM_DELETE = "DELETE FROM Item WHERE idItem = ?";
    private final String ITEM_PRICE_TOTAL = "select price from Item where idItem=?";
    List<Item> itemList = new ArrayList<>();

    public List<Item> getAllItem() {
        try {
            PreparedStatement ps = c.prepareStatement(ITEM_SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Item i = new Item(rs.getInt("idItem"),
                        rs.getString("nameItem"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("createdBy"),
                        rs.getString("image")); // Gán giá trị image từ database
                itemList.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public Item getItemById(int itemId) {
        try {
            PreparedStatement ps = c.prepareStatement(ITEM_SELECT_BY_ID);
            ps.setInt(1, itemId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Item(rs.getInt("idItem"),
                        rs.getString("nameItem"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("createdBy"),
                        rs.getString("image")); // moi add
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addItem(Item item) {
        try {
            PreparedStatement ps = c.prepareStatement(ITEM_INSERT);
            ps.setString(1, item.getNameItem());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getDescription());
            ps.setString(4, item.getCreateBy());
            ps.setString(5, item.getImage()); // moi add
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateItem(Item item) {
        try {
            PreparedStatement ps = c.prepareStatement(ITEM_UPDATE);
            ps.setString(1, item.getNameItem());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getDescription());
            ps.setString(4, item.getCreateBy());
            ps.setString(5, item.getImage()); // moi add
            ps.setInt(6, item.getItemId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteItem(int itemId) {
        try {
            PreparedStatement ps = c.prepareStatement(ITEM_DELETE);
            ps.setInt(1, itemId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> book = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    PreparedStatement ps = c.prepareStatement(ITEM_SELECT_BY_ID);
                    ps.setInt(1, item.getItemId());
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Cart row = new Cart();
                        row.setItemId(rs.getInt("idItem"));
                        row.setNameItem(rs.getString("nameItem"));
                        row.setDescription(rs.getString("description"));
                        row.setImage(rs.getString("image")); // moi add
                        row.setPrice(rs.getDouble("price") * item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        book.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }

    public double getTotalCartPrice(ArrayList<Cart> cartList) {
        double sum = 0;
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    PreparedStatement ps = c.prepareStatement(ITEM_PRICE_TOTAL);
                    ps.setInt(1, item.getItemId());
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        sum += rs.getDouble("price") * item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }
}
