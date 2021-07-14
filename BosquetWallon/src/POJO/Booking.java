package POJO;

import java.io.Serializable;
import java.util.ArrayList;

import DAO.Dao;
import DAO.BookingDAO;

@SuppressWarnings("serial")
public class Booking implements Serializable {
	private int id;
	private double deposit;
	private double balance;
	private String status;
	private double price;
	private Planning planning;
	private Dao<Booking> dao = new BookingDAO();

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

	public Planning getPlanning() {
		return planning;
	}

	public void setPlanning(Planning planning) {
		this.planning = planning;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", deposit=" + deposit + ", balance=" + balance + ", status=" + status + ", price="
				+ price + "]";
	}
	
	public boolean create() {
		return dao.create(this);
	}
	public boolean update() {
		return dao.update(this);
	}
	public ArrayList<Booking> getAll() {
		return dao.getList();
	}
	public Booking getOne() {
		return dao.get(this);
	}
}
