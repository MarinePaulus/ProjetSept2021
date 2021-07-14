package POJO;

import java.io.Serializable;

import DAO.Dao;
import DAO.TicketDAO;

@SuppressWarnings("serial")
public class Ticket implements Serializable {
	private int id;
	private String numPlace;
	private double price;
	private Representation representation;
	private Dao<Ticket> dao = new TicketDAO();
	
	public Ticket() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumPlace() {
		return numPlace;
	}
	public void setNumPlace(String numPlace) {
		this.numPlace = numPlace;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public Representation getRepresentation() {
		return representation;
	}

	public void setRepresentation(Representation representation) {
		this.representation = representation;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", numPlace=" + numPlace + ", price=" + price + "]";
	}
	
	public boolean create() {
		return dao.create(this);
	}
	public Ticket getOne() {
		return dao.get(this);
	}
}
