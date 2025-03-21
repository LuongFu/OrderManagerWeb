/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Fu
 */
public class Item {

    private int itemId;
    private String nameItem;
    private String image;
    private double price;
    private String description;
    private String createBy;

    public Item() {
    }

    // this is for insert
    public Item(String nameItem, double price, String description, String createBy) {
        this.nameItem = nameItem;
        this.price = price;
        this.description = description;
        this.createBy = createBy;
    }

    // this is for retrieving from DB
    public Item(int itemId, String nameItem, double price, String description, String createBy, String image) {
        this.itemId = itemId;
        this.image = image;
        this.nameItem = nameItem;
        this.price = price;
        this.description = description;
        this.createBy = createBy;
    }
    
    public Item(int itemId, String nameItem, double price, String description, String createBy) {
        this.itemId = itemId;
        this.nameItem = nameItem;
        this.price = price;
        this.description = description;
        this.createBy = createBy;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

}
