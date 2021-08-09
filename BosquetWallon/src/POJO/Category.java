package POJO;

import java.io.Serializable;
import java.util.ArrayList;

import DAO.Dao;
import DAO.CategoryDAO;

@SuppressWarnings("serial")
public class Category implements Serializable {
	private int id;
	private String type;
	private double price;
	private int availableTickets;
	private int maximumTickets;
	private Configuration config;
	private Dao<Category> dao = new CategoryDAO();
	
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
	public void setAvailableTickets() {
		this.availableTickets = maximumTickets;
	}
	public int getMaximumTickets() {
		return maximumTickets;
	}
	public void setMaximumTickets(int maximumTickets) {
		this.maximumTickets = maximumTickets;
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	@Override
	public String toString() {
		return /*"Category [id=" + id + ", type=" + */type/* + ", price=" + price + ", availableTickets=" + availableTickets
				+ ", maximumTickets=" + maximumTickets + "]"*/;
	}
	
	public boolean create() {
		return dao.create(this);
	}
	public Category getOne() {
		return dao.get(this);
	}
	public ArrayList<Category> getAll() {
		ArrayList<Category> cats = dao.getList();
		for(Category c:cats) {
			c.setAvailableTickets();
		}
		return cats;
	}
}
