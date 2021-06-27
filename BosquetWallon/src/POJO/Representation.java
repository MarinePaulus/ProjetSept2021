package POJO;

import java.io.Serializable;
import java.util.Calendar;

@SuppressWarnings("serial")
public class Representation implements Serializable {
	private int id;
	private Calendar date;
	private String beginHour;
	private String endHour;
	
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
	public void setDate(Calendar date) {
		this.date = date;
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

	@Override
	public String toString() {
		return "Representation [id=" + id + ", date=" + date + ", beginHour=" + beginHour + ", endHour=" + endHour
				+ "]";
	}
}
