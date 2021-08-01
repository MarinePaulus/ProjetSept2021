package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import DAO.Dao;
import DAO.BookingDAO;

@SuppressWarnings("serial")
public class Booking implements Serializable {
	private int id;
	private double deposit = 4000;
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
		if(planning.create()) {
			planning = planning.getOneNoID();
			return dao.create(this);
		} else return false;
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
	public Booking getOneNoID() {
		return ((BookingDAO) dao).getNoID(this);
	}
	
	public void calculPrice() {
		price = 0;
        Calendar d = Calendar.getInstance();
        d.setTime(planning.getBeginDate().getTime());
		while(d.compareTo(planning.getEndDate())<0) {
			switch(d.get(Calendar.DAY_OF_WEEK)) {
				case Calendar.FRIDAY :
					price += 4500;
					break;
				case Calendar.SATURDAY :
					price += 4500;
					break;
				default :
					price += 3000;
			}
			d.add(Calendar.DATE, 1);
//			System.out.println(price);
	     }
	}
	
	public void calculBalance() {
		balance=deposit+price;
	}
}
