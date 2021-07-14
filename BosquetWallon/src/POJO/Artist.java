package POJO;

import java.util.ArrayList;

import DAO.ArtistDAO;
import DAO.Dao;

@SuppressWarnings("serial")
public class Artist extends Person {
	private String showname;
	private Dao<Artist> dao = new ArtistDAO();
	
	public Artist() {
		super();
	}

	public String getShowname() {
		return showname;
	}
	public void setShowname(String showname) {
		this.showname = showname;
	}

	public String toString() {
		return super.toString() + " showname=" + showname;
	}
	
	public boolean create() {
		return dao.create(this);
	}
	public ArrayList<Artist> getAll() {
		return dao.getList();
	}
	public Artist getOne() {
		return dao.get(this);
	}
}
