package POJO;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Person implements Serializable {
	protected int id;
	protected String firstname;
	protected String lastname;
	protected String adress;
	protected String emailAddress;
	protected String password;
	private String role;
	
	public Person() { 
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
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", adress=" + adress
				+ ", emailAddress=" + emailAddress + ", password=" + password + ", role=" + role + "]";
	}

	private void crypt() {
		int crypt = password.concat("mdp").hashCode();
		this.password = crypt+"";
	}
}
