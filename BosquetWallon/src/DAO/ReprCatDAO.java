package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Category;
import POJO.Representation;

public class ReprCatDAO extends Dao<Representation>{
	@Override
	public boolean create(Representation obj) {
		PreparedStatement stmt = null;
		try {
			for(Category cat :obj.getShow().getConfig().getCategoryList()) {
				stmt=connection().prepareStatement("insert into ReprCat (idRepr, idCat, nbPlace) values (?,?,?)");
				stmt.setInt(1, obj.getId());
				stmt.setInt(2, cat.getId());
				stmt.setFloat(3, (float) cat.getMaximumTickets());
	            //Executing Query
				stmt.executeUpdate();
			}
			return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access create ReprCat : " + ex.getMessage()); return false; }
	}

	public boolean delete(Representation obj) {
		PreparedStatement stmt = null;
		try {
			stmt=connection().prepareStatement("delete from ReprCat where idRepr=?");
			stmt.setInt(1, obj.getId());
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access delete ReprCat : " + ex.getMessage()); return false; }
	}

	public boolean update(Representation obj) {
		PreparedStatement stmt = null;
		try {
			for(Category cat :obj.getShow().getConfig().getCategoryList()) {
				stmt=connection().prepareStatement("update ReprCat set nbPlace=? where idRepr=? and idCat=?");
				stmt.setInt(1, cat.getAvailableTickets());
				stmt.setInt(2, obj.getId());
				stmt.setInt(3, cat.getId());
	            //Executing Query
				stmt.executeUpdate();
			}
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access update ReprCat : " + ex.getMessage()); return false; }
	}

	public ArrayList<Category> getList(Representation obj) {
		ArrayList<Category> cats = new ArrayList<Category>();
		ResultSet res = null;
		try {
			for(Category cat :obj.getShow().getConfig().getCategoryList()) {
				PreparedStatement stmt=connection().prepareStatement("SELECT * FROM ReprCat WHERE idRepr=? and idCat=?");
				//Setting values for Each Parameter
				stmt.setInt(1, obj.getId());
				stmt.setInt(2, cat.getId());
				//Creaing Java ResultSet object
				res = stmt.executeQuery();
				if(res.next()) {
					cat.setAvailableTickets(res.getInt("nbPlace"));
					cats.add(cat);
		    	}
			}
			return cats;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get ReprCat : " + ex.getMessage()); return null; }
	}

	@Override
	public Representation get(Representation obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Representation> getList() {
		// TODO Auto-generated method stub
		return null;
	}
}
