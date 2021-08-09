package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import DAO.Dao;
import DAO.TicketDAO;

@SuppressWarnings("serial")
public class Ticket implements Serializable {
	private int id;
	private int numPlace;
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
	public int getNumPlace() {
		return numPlace;
	}
	public void setNumPlace() {
		ArrayList<Ticket> ts = this.getAll();
		if(ts.isEmpty()) {
			this.numPlace = 1;
		} else {
			int numPrec = ts.get(ts.size()-1).getNumPlace();
			this.numPlace = numPrec+1;
		}
	}
	public void setNumPlace(int numPlace) {
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
	public Ticket getOneNoID() {
		return ((TicketDAO) dao).getNoID(this);
	}
	public ArrayList<Ticket> getAll(){
		ArrayList<Ticket> lstT = dao.getList();
		Stream<Ticket> strc = lstT.stream();
		lstT = (ArrayList<Ticket>) strc.filter(l->l.representation.getId()==this.representation.getId()).sorted(Comparator.comparing(Ticket::getNumPlace))
				.collect(Collectors.toList());
		return lstT;
	}
}
