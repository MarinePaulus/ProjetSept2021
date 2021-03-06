package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import DAO.ConfigurationDAO;
import DAO.Dao;

@SuppressWarnings("serial")
public class Configuration implements Serializable {
	private int id;
	private String type;
	private String description;
	private ArrayList<Category> categoryList = new ArrayList<Category>();
	private Dao<Configuration> dao = new ConfigurationDAO();
	
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
	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList() {
		if(categoryList.isEmpty()) {
			categoryList = new ArrayList<Category>();
			Category c = new Category();
		
			categoryList = c.getAll();	
			Stream<Category> strc = categoryList.stream();
			categoryList = (ArrayList<Category>) strc.filter(l->l.getConfig().getId()==this.getId())
				.collect(Collectors.toList());
		}
	}

	@Override
	public String toString() {
		return type + " : " + description;
	}
	
	public Configuration getOne() {
		return dao.get(this);
	}
	public ArrayList<Configuration> getAll() {
		return dao.getList();
	}
	
}
