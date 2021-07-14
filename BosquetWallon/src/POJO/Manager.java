package POJO;

import DAO.Dao;
import DAO.ManagerDAO;

@SuppressWarnings("serial")
public class Manager extends Person {
	private String phoneNumber;
	private Dao<Manager> dao = new ManagerDAO();
	
	public Manager() {
		super();
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String toString() {
		return super.toString() + " phoneNumber=" + phoneNumber;
	}
	
	public boolean create() {
		return dao.create(this);
	}
	public Manager getOne() {
		return dao.get(this);
	}
}
