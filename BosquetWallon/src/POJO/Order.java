package POJO;

import java.io.Serializable;
import java.util.ArrayList;

import DAO.OrderDAO;
import DAO.Dao;

@SuppressWarnings("serial")
public class Order implements Serializable {
	private int id;
	private String paymentMethod;
	private String deliveryMethod;
	private double total;
	private ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
	private Dao<Order> dao = new OrderDAO();
	
	public Order() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public ArrayList<Ticket> getTicketList() {
		return ticketList;
	}
	public void setTicketList(ArrayList<Ticket> ticketList) {
		this.ticketList = ticketList;
	}
	public void addTicket(Ticket ticket){
		this.ticketList.add(ticket);
	}
	public void removeTicket(Ticket ticket){
		this.ticketList.remove(ticket);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", paymentMethod=" + paymentMethod + ", deliveryMethod=" + deliveryMethod
				+ ", total=" + total + "]";
	}
	
	public boolean create() {
		return dao.create(this);
	}
	public boolean update() {
		return dao.update(this);
	}
	public ArrayList<Order> getAll() {
		return dao.getList();
	}
	public Order getOne() {
		return dao.get(this);
	}
}
