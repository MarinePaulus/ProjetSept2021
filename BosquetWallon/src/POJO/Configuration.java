package POJO;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Configuration implements Serializable {
	private int id;
	private String type;
	private String description;
	
	public Configuration() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Configuration [id=" + id + ", type=" + type + ", description=" + description + "]";
	}
	
	
	
}
