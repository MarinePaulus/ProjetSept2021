package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import DAO.Dao;
import DAO.ReprCatDAO;
import DAO.RepresentationDAO;

@SuppressWarnings("serial")
public class Representation implements Serializable {
	private int id;
	private Calendar date;
	private String beginHour;
	private String endHour;
	private Show show;
	private Dao<Representation> dao = new RepresentationDAO();
	private ReprCatDAO dao2 = new ReprCatDAO();
	
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
	public Representation getOneNoID() {
		return ((RepresentationDAO) dao).getNoID(this);
	}
	public ArrayList<Representation> getAll() {
		return dao.getList();
	}
	
	public boolean recCatPlace() {
		return dao2.create(this);
	}
	public boolean updateCatPlace() {
		return dao2.update(this);
	}
	public void getCatPlace() {
		ArrayList<Category> cats = new ArrayList<Category>();
		cats = dao2.getList(this);
		for(int i = 0;i<cats.size();i++) {
			show.getConfig().getCategoryList().get(i).setAvailableTickets(cats.get(i).getAvailableTickets());
		}
	}
	
	public boolean verifyAvailable(Planning pla) {
		boolean debOK = true; // Heure debut valide
		boolean finOK = true; // Heure fin valide
		
		int hDebut = Integer.parseInt(beginHour.substring(0, beginHour.indexOf(":")));
		int mDebut = Integer.parseInt(beginHour.substring(beginHour.indexOf(":")+1, beginHour.length()));
		int hFin = Integer.parseInt(endHour.substring(0, endHour.indexOf(":")));
		int mFin = Integer.parseInt(endHour.substring(endHour.indexOf(":")+1, endHour.length()));
		
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		
	// Vérifier si les heures de représentations ne soNt pas hors réservation
		boolean horsReserv = false;
		if(date.compareTo(pla.getBeginDate())==0) {
			if(hDebut>=12 && hFin>=12 && mFin>=0) horsReserv = true;
		} else if(date.compareTo(pla.getEndDate())==0) {
			if(hDebut<12 && hFin<=12 && mFin>0) horsReserv = true;
		} else horsReserv=true;
	
		if(horsReserv){
			// Vérifier si les heures ne sont pas en conflit avec une autre représentation
			for(Representation rused :show.getRepresentationList()) {
				// Si le représentations sont le même jour
				if(date.compareTo(rused.getDate())==0) {
					int rused_hDebut = Integer.parseInt(rused.getBeginHour().substring(0, rused.getBeginHour().indexOf(":")));
					int rused_mDebut = Integer.parseInt(rused.getBeginHour().substring(rused.getBeginHour().indexOf(":")+1, rused.getBeginHour().length()));
					int rused_hFin = Integer.parseInt(rused.getEndHour().substring(0, rused.getEndHour().indexOf(":")));
					int rused_mFin = Integer.parseInt(rused.getEndHour().substring(rused.getEndHour().indexOf(":")+1, rused.getEndHour().length()));
					
					boolean sensOK = false;
					if(hDebut<=hFin) {
						if(hDebut==hFin) { 
							if(mDebut<mFin) sensOK = true;
						} else sensOK = true;
					}

					if(sensOK) {
						if(hDebut <= rused_hDebut || hDebut >= rused_hFin) {
					        if(hDebut == rused_hDebut) {
					            if(mDebut < rused_mDebut) debOK = true;
					            else debOK = false; 
					        }
					        if(debOK) {
					        	if(hDebut == rused_hFin) {
						            if(mDebut > rused_mFin) debOK = true;
						            else debOK = false;
						        }
					        }
					    } else debOK = false;
						
					    if(hFin <= rused_hDebut || hFin >= rused_hFin) {
					        if(hFin == rused_hDebut) {
					            if(mFin < rused_mDebut) finOK = true; 
					            else finOK = false; 
					        }
					    	if(finOK) {
					    		if(hFin == rused_hFin) {
						            if(mFin > rused_mFin) finOK = true;
						            else finOK = false;
						        }
					    	}
					    } else finOK = false;
					} else return false;
				}
			}
			return (debOK&&finOK);
		} else return false;
	}
}
