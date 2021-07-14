package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import DAO.Dao;
import DAO.RepresentationDAO;

@SuppressWarnings("serial")
public class Representation implements Serializable {
	private int id;
	private Calendar date;
	private String beginHour;
	private String endHour;
	private Show show;
	private Dao<Representation> dao = new RepresentationDAO();
	
	public Representation() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		this.date = c;
	}
	public String getBeginHour() {
		return beginHour;
	}
	public void setBeginHour(String beginHour) {
		this.beginHour = beginHour;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}

	@Override
	public String toString() {
		return "Representation [id=" + id + ", date=" + date.getTime() + ", beginHour=" + beginHour + ", endHour=" + endHour
				+ "]";
	}
	
	public boolean create() {
		return dao.create(this);
	}
	public boolean delete() {
		return dao.delete(this);
	}
	public Representation getOne() {
		return dao.get(this);
	}
	public ArrayList<Representation> getAll() {
		return dao.getList();
	}
}
