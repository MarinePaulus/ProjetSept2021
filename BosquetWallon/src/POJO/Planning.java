package POJO;

import java.io.Serializable;
import java.util.Calendar;

@SuppressWarnings("serial")
public class Planning implements Serializable {
	private int id;
	private Calendar beginDate;
	private Calendar EndDate;
	
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
	public void setBeginDate(Calendar beginDate) {
		this.beginDate = beginDate;
	}
	public Calendar getEndDate() {
		return EndDate;
	}
	public void setEndDate(Calendar endDate) {
		EndDate = endDate;
	}

	@Override
	public String toString() {
		return "Planning [id=" + id + ", beginDate=" + beginDate + ", EndDate=" + EndDate + "]";
	}
}
