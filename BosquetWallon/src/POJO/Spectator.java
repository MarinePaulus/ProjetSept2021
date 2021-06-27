package POJO;

@SuppressWarnings("serial")
public class Spectator extends Person {
	private String phoneNumber;
	private String gender;
	private String Birthdate;
	
	public Spectator() {
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
}
