package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import DAO.Dao;
import DAO.ShowArtDAO;
import DAO.ShowDAO;
import DAO.ShowCatDAO;

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
	private ShowCatDAO daoc = new ShowCatDAO();
	private ShowArtDAO daoa = new ShowArtDAO();
	
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
	public void setArtistList() {
		this.artistList = daoa.getList(this);
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
		for(Representation r :representationList) {
			r.setShow(this);
		}
}
	public boolean addArtist(Artist artist){
		if(daoa.create(this, artist)){
			this.artistList.add(artist);
			return true;
		} else return false;
		
	}
	public boolean removeArtist(Artist artist){
		if(daoa.delete(this, artist)) {
			Artist a = null;
			for(Artist x:artistList) {
				if(x.getId()==artist.getId()) {
					a=x;
				}
			}
			this.artistList.remove(a);
			return true;
		} else return false;
	}
	public boolean addRepresentation(Representation representation){
		if(representation.create()) {
			representation = representation.getOneNoID();
			representation.setShow(this);
			if(representation.recCatPlace()) {
				this.representationList.add(representation);
				return true;
			} else return false;
		} else return false;
	}
	public boolean removeRepresentation(Representation representation){
		if(representation.delete()) {
			Representation r = null;
			for(Representation x : representationList) {
				if(x.getId()==representation.getId()) {
					r=x;
				}
			}
			this.representationList.remove(r);
			return true;
		} else return false;
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
		return daoc.create(this);
	}
	public boolean delCatPrice() {
		return daoc.delete(this);
	}
	public void setCatPrice() {
		ArrayList<Category> cats = new ArrayList<Category>();
		cats = daoc.getList(this);
		for(int i = 0;i<cats.size();i++) {
			config.getCategoryList().get(i).setPrice(cats.get(i).getPrice());
		}
	}
	
	public boolean ArtistAvailable(Artist artist) {
		for(Artist x:artistList) {
			if(x.getEmailAddress().equalsIgnoreCase(artist.getEmailAddress())||x.getShowname().equalsIgnoreCase(artist.getShowname())) {
				return false;
			}
		}
		return true;
	}
}
