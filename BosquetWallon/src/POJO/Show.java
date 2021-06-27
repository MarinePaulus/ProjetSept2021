package POJO;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Show implements Serializable {
	private int id;
	private String title;
	private String description;
	private String ticketPerPerson;
	
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

	@Override
	public String toString() {
		return "Show [id=" + id + ", title=" + title + ", description=" + description + ", ticketPerPerson="
				+ ticketPerPerson + "]";
	}
}
