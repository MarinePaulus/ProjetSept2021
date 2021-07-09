package POJO;

import DAO.Dao;
import DAO.SpectatorDAO;

@SuppressWarnings("serial")
public class Spectator extends Person {
	private String gender;
	private String phoneNumber;
	private String Birthdate;
	private Dao<Spectator> dao = new SpectatorDAO();
	
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
	
	public String toString() {
		return super.toString() + " gender=" + gender + ", phoneNumber=" + phoneNumber + ", Birthdate=" + Birthdate;
	}

	public Spectator getOne() {
		return dao.get(this);
	}
}
