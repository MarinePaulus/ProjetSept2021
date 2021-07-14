package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Configuration;

public class ConfigurationDAO extends Dao<Configuration> {

	@Override
	public boolean create(Configuration obj) {
		return false;
	}
	
	@Override
	public boolean delete(Configuration obj) {
		return false;
	}
	
	@Override
	public boolean update(Configuration obj) {
		return false;
	}

	@Override
	public Configuration get(Configuration obj) {
		ResultSet res = null;
		Configuration c = new Configuration();
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Configuration where idConf=?");
			//Setting values for Each Parameter
			stmt.setInt(1, obj.getId());
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		System.out.println(res.toString()); 
	    		c.setId(res.getInt("idConf"));
    			c.setType(res.getString("type"));
    			c.setDescription(res.getString("description"));
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Configuration : " + ex.getMessage()); return null; }
		return c;
	}

	@Override
	public ArrayList<Configuration> getList() {
		ResultSet res = null;
		ArrayList<Configuration> conf = new ArrayList<Configuration>();
		try {
			//Using SQL SELECT Query
            PreparedStatement stmt = connection().prepareStatement("select * from Configuration");
            //Creaing Java ResultSet object
            res = stmt.executeQuery();
	    	while(res.next()) {
	    		Configuration c = new Configuration();
	    		c.setId(res.getInt("idConf"));
    			c.setType(res.getString("type"));
    			c.setDescription(res.getString("description"));
	    		conf.add(c);
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get all Configurations : " + ex.getMessage()); return null; }
		return conf;
	}

}
