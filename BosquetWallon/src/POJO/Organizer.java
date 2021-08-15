package POJO;

import java.util.ArrayList;

import DAO.BookingDAO;
import DAO.Dao;
import DAO.OrganizerDAO;

@SuppressWarnings("serial")
public class Organizer extends Person {
	private String phoneNumber;
	private ArrayList<Booking> bookingList = new ArrayList<Booking>();
	private Dao<Organizer> dao = new OrganizerDAO();
	private Dao<Booking> daob = new BookingDAO();
	
	public Organizer() {
		super();
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		crypt();
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public ArrayList<Booking> getBookingList() {
		return bookingList;
	}
	
	public void addBooking(Booking booking){
		this.bookingList.add(booking);
	}

	public boolean createBooking(Booking booking){
		if(booking.create()) {
			booking = booking.getOneNoID();
			if(((BookingDAO) daob).updateBooking(booking, this)){
				this.bookingList.add(booking);
				return true;
			} else return false;
		} else return false;
	}
	
	public void removeBooking(Booking booking){
		this.bookingList.remove(booking);
	}

	public String toString() {
		return super.toString() + " phoneNumber=" + phoneNumber;
	}

	public boolean create() {
		return dao.create(this);
	}
	public Organizer getOne() {
		return dao.get(this);
	}
}
