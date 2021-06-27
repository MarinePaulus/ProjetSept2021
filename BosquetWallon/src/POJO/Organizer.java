package POJO;

@SuppressWarnings("serial")
public class Organizer extends Person {
	private String phoneNumber;
	
	public Organizer() {
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
}
