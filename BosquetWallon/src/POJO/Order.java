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
	public boolean createTickets(){
		boolean ret = false;
		for(int i = 0;i<ticketList.size();i++) {
			Ticket ticket = ticketList.get(i);
			switch(i){
				case 0:
					ticket.setNumPlace();
					break;
				default :
					int numPrec = ticketList.get(i-1).getNumPlace();
					ticket.setNumPlace(numPrec+1);
					break;
			}
			if(ticket.create()) {
				ticket = ticket.getOneNoID();
				if(((OrderDAO) dao).addTicket(this, ticket)){
					ret =  true;
				} else ret =  false;
			} else ret =  false;
		}
		return ret;
	}
	public void removeTickets(double cat){
		this.ticketList.removeIf(l->l.getPrice()==cat);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", paymentMethod=" + paymentMethod + ", deliveryMethod=" + deliveryMethod
				+ ", total=" + total + "]";
	}
	
	public boolean create() {
		return dao.create(this);
	}
	public Order getOne() {
		return dao.get(this);
	}
	public Order getOneNoID() {
		return ((OrderDAO) dao).getNoID(this);
	}

	public void calculTotal() {
		int total = 5;
		for(Ticket t : getTicketList()) {
			total+= t.getPrice();
		}
		if(deliveryMethod.equals("Envoi sécurisé")) {
			total+=10;
		}
		this.total=total;
	}
}
