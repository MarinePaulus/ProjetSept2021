package POJO;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Booking implements Serializable {
	private int id;
	private double deposit;
	private double balance;
	private String status;
	private double price;
	
	public Booking() { 
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", deposit=" + deposit + ", balance=" + balance + ", status=" + status + ", price="
				+ price + "]";
	}
}
