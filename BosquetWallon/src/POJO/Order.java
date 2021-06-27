package POJO;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Order implements Serializable {
	private int id;
	private String paymentMethod;
	private String deliveryMethod;
	private double total;
	
	public Order() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", paymentMethod=" + paymentMethod + ", deliveryMethod=" + deliveryMethod
				+ ", total=" + total + "]";
	}
}
