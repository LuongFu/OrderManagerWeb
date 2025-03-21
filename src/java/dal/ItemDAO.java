package dal;

import model.Item;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO extends DBContext {

    private final String ITEM_SELECT_ALL = "SELECT * FROM Item";
    private final String ITEM_INSERT = "INSERT INTO Item (nameItem, price, description, createdBy) VALUES (?, ?, ?, ?)";
    private final String ITEM_SELECT_BY_ID = "SELECT * FROM Item WHERE idItem = ?";
    private final String ITEM_UPDATE = "UPDATE Item SET nameItem = ?, price = ?, description = ?, createdBy = ? WHERE idItem = ?";
    private final String ITEM_DELETE = "DELETE FROM Item WHERE idItem = ?";

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
                        rs.getString("createdBy"));
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
                        rs.getString("createdBy"));
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
            ps.setInt(5, item.getItemId());
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
}
