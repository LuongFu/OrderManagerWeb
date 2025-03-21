package model;

public class Order extends Item {

    private int orderId;
    private int accountId;
    private int quantity;
    private String date;

    public Order() {
    }

    public Order(int orderId, int accountId, int quantity, String date) {
//        super();
        this.orderId = orderId;
        this.accountId = accountId;
        this.quantity = quantity;
        this.date = date;
    }

    public Order(int uid, int quantity, String date) {
        super();
        this.accountId = uid;
        this.quantity = quantity;
        this.date = date;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
