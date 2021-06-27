package POJO;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Ticket implements Serializable {
	private int id;
	private String firstname;
	private double price;
	
	public Ticket() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", firstname=" + firstname + ", price=" + price + "]";
	}
}
