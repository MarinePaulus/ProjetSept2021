package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Artist;
import POJO.Show;

public class ShowArtDAO {
	public Connection connection() { return Singleton.instanciate().getConnection(); }
	
	public boolean create(Show show, Artist art) {
		PreparedStatement stmt = null;
		try {
				stmt=connection().prepareStatement("insert into SpecArt (idSpec, idArt) values (?,?)");
				stmt.setInt(1, show.getId());
				stmt.setInt(2, art.getId());
	            //Executing Query
				stmt.executeUpdate();
			return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access create SpecArt : " + ex.getMessage()); return false; }
	}

	public boolean delete(Show show, Artist art) {
		PreparedStatement stmt = null;
		try {
			stmt=connection().prepareStatement("delete from SpecArt where idSpec=? and idArt=?");
			stmt.setInt(1, show.getId());
			stmt.setInt(2, art.getId());
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access delete SpecArt : " + ex.getMessage()); return false; }
	}

	public ArrayList<Artist> getList(Show obj) {
		ArrayList<Artist> arts = new ArrayList<Artist>();
		ResultSet res = null;
		try {
				PreparedStatement stmt = connection().prepareStatement("SELECT idArt FROM SpecArt WHERE idSpec=?");
    			stmt.setInt(1, obj.getId());
    			res = stmt.executeQuery();
    			while(res.next()) {
    				Artist a = new Artist();
    				a.setId(res.getInt("idArt"));
    				a = a.getOne();
    				arts.add(a);
    	    	}
			return arts;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get SpecArt : " + ex.getMessage()); return null; }
	}
	
	public boolean get(Show show, Artist art) {
		ResultSet res = null;
		try {
				PreparedStatement stmt = connection().prepareStatement("SELECT * FROM SpecArt WHERE idSpec=? and idArt=?");
				stmt.setInt(1, show.getId());
				stmt.setInt(2, art.getId());
    			res = stmt.executeQuery();
    			if(res.next()) {
    				return true;
    	    	}
			return false;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get SpecArt : " + ex.getMessage()); return false; }
	}
}
