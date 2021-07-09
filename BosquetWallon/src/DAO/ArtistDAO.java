package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Artist;

public class ArtistDAO extends Dao<Artist>{
	@Override
	public boolean create(Artist obj) {
		PreparedStatement stmt = null;
		try {
			//Crating PreparedStatement object
			stmt = connection().prepareStatement("insert into Personne (nom, prenom, adresse, mail, role, nomScene) values (?,?,?,?,?,?)");
            //Setting values for Each Parameter
			stmt.setString(1, obj.getLastname());
			stmt.setString(2, obj.getFirstname());
			stmt.setString(3, obj.getAdress());
			stmt.setString(4, obj.getEmailAddress());
			stmt.setString(5, "Artiste");
			stmt.setString(6, obj.getShowname());
            //Executing Query
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access create Artiste : " + ex.getMessage()); return false; }
	}
	
	@Override
	public boolean delete(Artist obj) {
		return false; 
	}
	
	@Override
	public boolean update(Artist obj) {
		 return false;
	}

	@Override
	public Artist get(Artist obj) {
		ResultSet res = null;
		Artist p = null;
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Personne where idPer=?");
			//Setting values for Each Parameter
			stmt.setInt(1, obj.getId());
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		p = new Artist();
	    		p.setId(res.getInt("idPer"));
    			p.setLastname(res.getString("nom"));
    			p.setFirstname(res.getString("prenom"));
    			p.setAdress(res.getString("adresse"));
    			p.setEmailAddress(res.getString("mail"));
    			p.setRole(res.getString("role"));
    			p.setShowname(res.getString("nomScene"));
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Artist : " + ex.getMessage()); return null; }
		return p;
	}

	@Override
	public ArrayList<Artist> getList() {
		ResultSet res = null;
		ArrayList<Artist> pers = new ArrayList<Artist>();
		try {
			//Using SQL SELECT Query
            PreparedStatement stmt = connection().prepareStatement("select * from Personne where role=?");
            //Setting values for Each Parameter
			stmt.setString(1, "Artiste");
            //Creaing Java ResultSet object
            res = stmt.executeQuery();
	    	while(res.next()) {
	    		Artist p = new Artist();
	    		p.setId(res.getInt("idPer"));
    			p.setLastname(res.getString("nom"));
    			p.setFirstname(res.getString("prenom"));
    			p.setAdress(res.getString("adresse"));
    			p.setEmailAddress(res.getString("mail"));
    			p.setRole(res.getString("role"));
    			p.setShowname(res.getString("nomScene"));
	    		pers.add(p);
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get all Artist : " + ex.getMessage()); return null; }
		return pers;
	}
}
