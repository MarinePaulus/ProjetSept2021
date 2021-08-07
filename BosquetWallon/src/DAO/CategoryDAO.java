package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Category;
import POJO.Configuration;

public class CategoryDAO extends Dao<Category> {

	@Override
	public boolean create(Category obj) {
		return false;
	}
	
	@Override
	public boolean delete(Category obj) {
		return false;
	}
	
	@Override
	public boolean update(Category obj) {
		return false;
	}

	@Override
	public Category get(Category obj) {
		ResultSet res = null;
		Category c = new Category();
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Categorie where idCat=?");
			//Setting values for Each Parameter
			stmt.setInt(1, obj.getId());
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		c.setId(res.getInt("idCat"));
    			c.setType(res.getString("type"));
    			c.setMaximumTickets(res.getInt("nbPlaceMax"));
    			Configuration co = new Configuration();
    			co.setId(res.getInt("idConf"));
    			co = co.getOne();
    			c.setConfig(co);
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Category : " + ex.getMessage()); return null; }
		return c;
	}

	@Override
	public ArrayList<Category> getList() {
		ResultSet res = null;
		ArrayList<Category> cat = new ArrayList<Category>();
		try {
			//Using SQL SELECT Query
            PreparedStatement stmt = connection().prepareStatement("select * from Categorie");
            //Creaing Java ResultSet object
            res = stmt.executeQuery();
	    	while(res.next()) {
	    		Category c = new Category();
	    		c.setId(res.getInt("idCat"));
    			c.setType(res.getString("type"));
    			c.setMaximumTickets(res.getInt("nbPlaceMax"));
    			Configuration co = new Configuration();
    			co.setId(res.getInt("idConf"));
    			co = co.getOne();
    			c.setConfig(co);
	    		cat.add(c);
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get all Categorys : " + ex.getMessage()); return null; }
		return cat;
	}
}
