package POJO;

import java.util.ArrayList;

import DAO.Dao;
import DAO.SpectatorDAO;

@SuppressWarnings("serial")
public class Spectator extends Person {
	private String gender;
	private String phoneNumber;
	private String Birthdate;
	private Dao<Spectator> dao = new SpectatorDAO();
	private ArrayList<Order> orderList = new ArrayList<Order>();
	
	public Spectator() {
		super();
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		//crypt();
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthdate() {
		return Birthdate;
	}
	public void setBirthdate(String birthdate) {
		Birthdate = birthdate;
	}
	public ArrayList<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}
	public void addOrder(Order order){
		this.orderList.add(order);
	}
	public boolean createOrder(Order order){
		if(order.create()) {
			order = order.getOneNoID();
			if(((SpectatorDAO) dao).addOrder(this, order)){
				this.orderList.add(order);
				return true;
			} else return false;
		} else return false;
	}

	public String toString() {
		return super.toString() + " gender=" + gender + ", phoneNumber=" + phoneNumber + ", Birthdate=" + Birthdate;
	}

	public boolean create() {
		return dao.create(this);
	}
	public Spectator getOne() {
		return dao.get(this);
	}
}
