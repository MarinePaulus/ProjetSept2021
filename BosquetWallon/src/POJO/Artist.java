package POJO;

@SuppressWarnings("serial")
public class Artist extends Person {
	private String showname;
	
	public Artist() {
		super();
	}

	public String getShowname() {
		return showname;
	}
	public void setShowname(String showname) {
		this.showname = showname;
	}
}
