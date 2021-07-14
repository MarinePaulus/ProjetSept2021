package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Artist;
import POJO.Configuration;
import POJO.Representation;
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
	    return false;
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
	    	if(s!=null) {
	    		// Récup Artists
	    		stmt = connection().prepareStatement("SELECT idSpec FROM SpecArt WHERE idSpec=?");
    			stmt.setInt(1, obj.getId());
    			res = stmt.executeQuery();
    			while(res.next()) {
    				Artist a = new Artist();
    				a.setId(res.getInt("idPer"));
    				s.addArtist(a.getOne());	
    	    	}
    			// Récup Representation
				stmt = connection().prepareStatement("SELECT idRepr FROM Representation WHERE idSpec=?");
    			stmt.setInt(1, obj.getId());
    			res = stmt.executeQuery();
    			while(res.next()) {
    				Representation r = new Representation();
    				r.setId(res.getInt("idRepr"));
    				s.addRepresentation(r.getOne());	
    	    	}
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Show : " + ex.getMessage()); return null; }
		return s;
	}

	@Override
	public ArrayList<Show> getList() {
		ResultSet res = null;
		ResultSet res2 = null;
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
	    	
	    		// Récup Artists
				PreparedStatement stmt2 = connection().prepareStatement("SELECT idSpec FROM SpecArt WHERE idSpec=?");
    			stmt2.setInt(1, s.getId());
    			res2 = stmt2.executeQuery();
    			while(res2.next()) {
    				Artist a = new Artist();
    				a.setId(res2.getInt("idPer"));
    				s.addArtist(a.getOne());	
    	    	}
    			// Récup Representation
				stmt2 = connection().prepareStatement("SELECT idRepr FROM Representation WHERE idSpec=?");
    			stmt2.setInt(1, s.getId());
    			res2 = stmt2.executeQuery();
    			while(res2.next()) {
    				Representation r = new Representation();
    				r.setId(res2.getInt("idRepr"));
    				s.addRepresentation(r.getOne());	
    	    	}
    			show.add(s);
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get all Show : " + ex.getMessage()); return null; }
		return show;
	}
}