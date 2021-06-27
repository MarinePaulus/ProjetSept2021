package POJO;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Category implements Serializable {
	private int id;
	private String type;
	private double price;
	private int availableTickets;
	private int maximumTickets;
	
	public Category() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getAvailableTickets() {
		return availableTickets;
	}
	public void setAvailableTickets(int availableTickets) {
		this.availableTickets = availableTickets;
	}
	public int getMaximumTickets() {
		return maximumTickets;
	}
	public void setMaximumTickets(int maximumTickets) {
		this.maximumTickets = maximumTickets;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", type=" + type + ", price=" + price + ", availableTickets=" + availableTickets
				+ ", maximumTickets=" + maximumTickets + "]";
	}
}
