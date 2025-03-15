/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author Fu
 */
public class Item {
    private int itemId;
    private String nameItem;
    private BigDecimal price;
    private String description;
    private Integer createBy;
    
    // this is for insert
    public Item(String nameItem, BigDecimal price, String description, Integer createBy) {
        this.nameItem = nameItem;
        this.price = price;
        this.description = description;
        this.createBy = createBy;
    }

    // this is for retrieving from DB
    public Item(int itemId, String nameItem, BigDecimal price, String description, Integer createBy) {
        this.itemId = itemId;
        this.nameItem = nameItem;
        this.price = price;
        this.description = description;
        this.createBy = createBy;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }
    
}
