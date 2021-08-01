package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import DAO.Dao;
import DAO.TicketDAO;

@SuppressWarnings("serial")
public class Ticket implements Serializable {
	private int id;
	private String numPlace;
	private double price;
	private Representation representation;
	private Dao<Ticket> dao = new TicketDAO();
	
	public Ticket() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumPlace() {
		return numPlace;
	}
	public void setNumPlace(String numPlace) {
		this.numPlace = numPlace;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public Representation getRepresentation() {
		return representation;
	}

	public void setRepresentation(Representation representation) {
		this.representation = representation;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", numPlace=" + numPlace + ", price=" + price + "]";
	}
	
	public boolean create() {
		return dao.create(this);
	}
	public Ticket getOne() {
		return dao.get(this);
	}
	public ArrayList<Ticket> getAll(){
		ArrayList<Ticket> lstT = dao.getList();
		Stream<Ticket> strc = lstT.stream();
		lstT = (ArrayList<Ticket>) strc.filter(l->l.representation.getId()==this.representation.getId())
				.collect(Collectors.toList());
		return lstT;
	}
}
