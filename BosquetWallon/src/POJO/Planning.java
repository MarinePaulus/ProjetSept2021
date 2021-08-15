package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import DAO.Dao;
import DAO.PlanningDAO;
import DAO.ShowDAO;

@SuppressWarnings("serial")
public class Planning implements Serializable {
	private int id;
	private Calendar beginDate;
	private Calendar endDate;
	private ArrayList<Show> showList = new ArrayList<Show>();
	private Dao<Planning> dao = new PlanningDAO();
	private Dao<Show> daos = new ShowDAO();

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
	public void addShow(Show show){
		this.showList.add(show);
	}
	public void removeShow(Show show){
		this.showList.remove(show);
	}
	public boolean updateShows() {
		return ((ShowDAO) daos).updateShows(this);
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
	
	public int verifyAvailable(Calendar c) {
		// Liste des dates déjà prises
		ArrayList<Planning> usedp = new ArrayList<Planning>();
		usedp = this.getAll();

		// Vérifie si la date est déjà utilisée dans un planning existant
		Iterator<Planning> iter = usedp.iterator();
		while(iter.hasNext()) {
			Planning plaused = iter.next();
			Calendar deb = plaused.getBeginDate();
			Calendar fin = plaused.getEndDate();

			// Si la date choisie correspond à un jour déjà compris dans le planning
			// Si date est comprise entre debut et fin ou = debut
			if(c.compareTo(deb)==0||(c.after(deb)&&c.before(fin))) {
				return -1; // Si date est déjà utilisée
			} else if(c.compareTo(Calendar.getInstance())<0) {
				return 0; // Si la date est passée
			}
		}
		return 1;
	}
}
