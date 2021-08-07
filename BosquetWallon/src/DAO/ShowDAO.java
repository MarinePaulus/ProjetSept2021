package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import POJO.Configuration;
import POJO.Show;

public class ShowDAO extends Dao<Show>{

	@Override
	public boolean create(Show obj) {
		PreparedStatement stmt = null;
		try {
			//Crating PreparedStatement object
			stmt = connection().prepareStatement("insert into Spectacle (titre, description, nbPlaceSpec, idConf) values (?,?,?,?)");
            //Setting values for Each Parameter
			stmt.setString(1, obj.getTitle());
			stmt.setString(2, obj.getDescription());
			stmt.setString(3, obj.getTicketPerPerson());
			stmt.setInt(4, obj.getConfig().getId());
            //Executing Query
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access create Show : " + ex.getMessage()); return false; }
	}
	
	@Override
	public boolean delete(Show obj) {
		PreparedStatement stmt = null;
		try {
			//Using SQL DELETE query
			stmt=connection().prepareStatement("delete from Spectacle where idSpec=?");
			stmt.setInt(1, obj.getId());
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access delete Show : " + ex.getMessage()); return false; }
	}
	
	@Override
	public boolean update(Show obj) {
		PreparedStatement stmt = null;
		try {
			stmt=connection().prepareStatement("update Spectacle set titre=?, description=?, nbPlaceSpec=?, idConf=? where idSpec=?");
			stmt.setString(1, obj.getTitle());
			stmt.setString(2, obj.getDescription());
			stmt.setString(3, obj.getTicketPerPerson());
			stmt.setInt(4, obj.getConfig().getId());
			stmt.setInt(5, obj.getId());
            //Executing Query
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access update Show : " + ex.getMessage()); return false; }
	}

	@Override
	public Show get(Show obj) {
		ResultSet res = null;
		Show s = null;
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Spectacle where idSpec=?");
			//Setting values for Each Parameter
			stmt.setInt(1, obj.getId());
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		s = new Show();
	    		s.setId(res.getInt("idSpec"));
    			s.setTitle(res.getString("titre"));
    			s.setDescription(res.getString("description"));
    			s.setTicketPerPerson(res.getString("nbPlaceSpec"));
    			Configuration c = new Configuration();
				c.setId(res.getInt("idConf"));
				c = c.getOne();
				s.setConfig(c);
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Show : " + ex.getMessage()); return null; }
		return s;
	}

	@Override
	public ArrayList<Show> getList() {
		ResultSet res = null;
		ArrayList<Show> show = new ArrayList<Show>();
		try {
			//Using SQL SELECT Query
            PreparedStatement stmt = connection().prepareStatement("select * from Spectacle");
            //Creaing Java ResultSet object
            res = stmt.executeQuery();
	    	while(res.next()) {
	    		Show s = new Show();
	    		s.setId(res.getInt("idSpec"));
    			s.setTitle(res.getString("titre"));
    			s.setDescription(res.getString("description"));
    			s.setTicketPerPerson(res.getString("nbPlaceSpec"));
    			Configuration c = new Configuration();
				c.setId(res.getInt("idConf"));
				c = c.getOne();
				s.setConfig(c);
    			show.add(s);
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get all Show : " + ex.getMessage()); return null; }
		return show;
	}
	
	public Show getNoID(Show obj) {
		ResultSet res = null;
		Show s = null;
		try {
			PreparedStatement stmt = connection().prepareStatement("SELECT * FROM Spectacle WHERE idSpec=(SELECT max(idSpec) FROM Spectacle);");
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		s = new Show();
	    		s.setId(res.getInt("idSpec"));
    			s.setTitle(res.getString("titre"));
    			s.setDescription(res.getString("description"));
    			s.setTicketPerPerson(res.getString("nbPlaceSpec"));
    			Configuration c = new Configuration();
				c.setId(res.getInt("idConf"));
				c = c.getOne();
				s.setConfig(c);
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Show : " + ex.getMessage()); return null; }
		return s;
	}
}