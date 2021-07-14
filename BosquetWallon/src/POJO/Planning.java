package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import DAO.Dao;
import DAO.PlanningDAO;

@SuppressWarnings("serial")
public class Planning implements Serializable {
	private int id;
	private Calendar beginDate;
	private Calendar endDate;
	private ArrayList<Show> showList = new ArrayList<Show>();
	private Dao<Planning> dao = new PlanningDAO();

	public Planning() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(beginDate);
		this.beginDate = c;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(endDate);
		this.endDate = c;
	}
	public ArrayList<Show> getShowList() {
		return showList;
	}
	public void setShowList(ArrayList<Show> showList) {
		this.showList = showList;
	}
	public void addShow(Show show){
		this.showList.add(show);
	}
	public void removeShow(Show show){
		this.showList.remove(show);
	}

	@Override
	public String toString() {
		return "Planning [id=" + id + ", beginDate=" + beginDate.getTime() + ", EndDate=" + endDate.getTime() + "]";
	}
	
	public boolean create() {
		return dao.create(this);
	}
	public ArrayList<Planning> getAll() {
		return dao.getList();
	}
	public Planning getOne() {
		return dao.get(this);
	}
	public Planning getOneNoID() {
		return ((PlanningDAO) dao).getNoID(this);
	}
}
