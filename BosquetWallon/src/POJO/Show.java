package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import DAO.Dao;
import DAO.ShowDAO;

@SuppressWarnings("serial")
public class Show implements Serializable {
	private int id;
	private String title;
	private String description;
	private String ticketPerPerson;
	private Configuration config;
	private ArrayList<Artist> artistList = new ArrayList<Artist>();
	private ArrayList<Representation> representationList = new ArrayList<Representation>();
	private Dao<Show> dao = new ShowDAO();
	
	public Show() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTicketPerPerson() {
		return ticketPerPerson;
	}
	public void setTicketPerPerson(String ticketPerPerson) {
		this.ticketPerPerson = ticketPerPerson;
	}
	public Configuration getConfig() {
		return config;
	}
	public void setConfig(Configuration config) {
		this.config = config;
	}
	public ArrayList<Artist> getArtistList() {
		return artistList;
	}
	public void setArtistList(ArrayList<Artist> artistList) {
		this.artistList = artistList;
	}
	public ArrayList<Representation> getRepresentationList() {
		return representationList;
	}
	public void setRepresentationList() {
		representationList = new ArrayList<Representation>();
		Representation c = new Representation();
	
		representationList = c.getAll();	
		Stream<Representation> strc = representationList.stream();
		representationList = (ArrayList<Representation>) strc.filter(l->l.getShow().getId()==this.getId())
				.collect(Collectors.toList());
}
	public void addArtist(Artist artist){
		this.artistList.add(artist);
	}
	public void removeArtist(Artist artist){
		this.artistList.remove(artist);
	}
	public void addRepresentation(Representation representation){
		this.representationList.add(representation);
	}
	public void removeRepresentation(Representation representation){
		this.representationList.remove(representation);
	}

	@Override
	public String toString() {
		return "Show [id=" + id + ", title=" + title + ", description=" + description + ", ticketPerPerson="
				+ ticketPerPerson + " " + config.toString() + "]";
	}
	
	public boolean create() {
		return dao.create(this);
	}
	public boolean update() {
		return dao.update(this);
	}
	public boolean delete() {
		return dao.delete(this);
	}
	public Show getOne() {
		return dao.get(this);
	}
	public Show getOneNoID() {
		return ((ShowDAO) dao).getNoID(this);
	}
	public ArrayList<Show> getAll() {
		return dao.getList();
	}
	
	public boolean recCatPrice() {
		return ((ShowDAO) dao).recCatPrice(this);
	}
	public boolean delCatPrice() {
		return ((ShowDAO) dao).delCatPrice(this);
	}
	public void getCatPrice() {
		ArrayList<Category> cats = new ArrayList<Category>();
		cats = ((ShowDAO) dao).getCatPrice(this);
		for(int i = 0;i<cats.size();i++) {
			config.getCategoryList().get(i).setPrice(cats.get(i).getPrice());
		}
	}
}
