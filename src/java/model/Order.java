package model;

public class Order extends Item {

    private int orderId;
    private int accountId;
    private int quantity;
    private String date;
    private String status;
    private double totalAmount;
    
    public Order() {
    }

    public Order(int orderId, int accountId, int quantity, String date, String status, double totalAmount) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.quantity = quantity;
        this.date = date;
        this.status = status;
        this.totalAmount = totalAmount;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
