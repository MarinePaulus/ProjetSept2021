package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Category;
import POJO.Show;

public class ShowCatDAO {
	public Connection connection() { return Singleton.instanciate().getConnection(); }

	public boolean create(Show obj) {
		PreparedStatement stmt = null;
		try {
			for(Category cat :obj.getConfig().getCategoryList()) {
				stmt=connection().prepareStatement("insert into SpecCat (idSpec, idCat, prix) values (?,?,?)");
				stmt.setInt(1, obj.getId());
				stmt.setInt(2, cat.getId());
				stmt.setFloat(3, (float) cat.getPrice());
	            //Executing Query
				stmt.executeUpdate();
			}
			return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access create SpecCat : " + ex.getMessage()); return false; }
	}

	public boolean delete(Show obj) {
		PreparedStatement stmt = null;
		try {
			stmt=connection().prepareStatement("delete from SpecCat where idSpec=?");
			stmt.setInt(1, obj.getId());
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access delete SpecCat : " + ex.getMessage()); return false; }
	}

	public ArrayList<Category> getList(Show obj) {
		ArrayList<Category> cats = new ArrayList<Category>();
		ResultSet res = null;
		try {
			for(Category cat :obj.getConfig().getCategoryList()) {
				PreparedStatement stmt=connection().prepareStatement("SELECT * FROM SpecCat WHERE idSpec=? and idCat=?");
				//Setting values for Each Parameter
				stmt.setInt(1, obj.getId());
				stmt.setInt(2, cat.getId());
				//Creaing Java ResultSet object
				res = stmt.executeQuery();
				if(res.next()) {
					cat.setPrice(res.getFloat("prix"));
					cats.add(cat);
		    	}
			}
			return cats;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get SpecCat : " + ex.getMessage()); return null; }
	}
}
