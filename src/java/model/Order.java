package model;

public class Order extends Item{
	private int orderId;
	private int quantity;
	private String date;
	
	public Order() {
	}

    public Order(int orderId, int quantity, String date) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.date = date;
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
