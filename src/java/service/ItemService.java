/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.DBContext;
import dal.ItemDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Item;

/**
 *
 * @author Fu
 */
public class ItemService extends DBContext {

    private final String ITEM_SELECT_ALL = "SELECT * FROM Account";
    private final String ITEM_INSERT = "INSERT INTO Item (nameItem, price, description, createdBy, image) VALUES (?, ?, ?, ?, ?)";
    private final String ITEM_SELECT_BY_ID = "SELECT * FROM Item WHERE idItem = ?";
    private final String ITEM_UPDATE = "UPDATE Item SET nameItem = ?, price = ?, description = ?, createdBy = ?, image = ? WHERE idItem = ?";
    private final String ITEM_DELETE = "DELETE FROM Item WHERE idItem = ?";

    List<Item> itemlist = new ArrayList();
    public Item i;

    private ItemDAO itemDAO;

    public ItemService() {
        itemDAO = new ItemDAO();
    }

    // Method to add an item
    public boolean addItem(Item item) {
        return itemDAO.addItem(item);
    }

    // Method to get all items
    public List<Item> getAllItems() {
        return itemDAO.getAllItem();
    }

    // Method to get an item by ID
    public Item getItemById(int itemId) {
        return itemDAO.getItemById(itemId);
    }

    // Method to update an item
    public boolean updateItem(Item item) {
        return itemDAO.updateItem(item);
    }

    // Method to delete an item
    public boolean deleteItem(int itemId) {
        return itemDAO.deleteItem(itemId);
    }

}
