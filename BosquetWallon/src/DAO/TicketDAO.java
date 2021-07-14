package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Representation;
import POJO.Ticket;

public class TicketDAO extends Dao<Ticket>{

	@Override
	public boolean create(Ticket obj) {
		PreparedStatement stmt = null;
		try {
			//Crating PreparedStatement object
			stmt = connection().prepareStatement("insert into Place (numPlace, prix, idRep) values (?,?,?)");
            //Setting values for Each Parameter
			stmt.setString(1, obj.getNumPlace());
			stmt.setDouble(2,obj.getPrice());
			stmt.setInt(3, obj.getRepresentation().getId());
            //Executing Query
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access create Ticket : " + ex.getMessage()); return false; }
	}
	
	@Override
	public boolean delete(Ticket obj) {
		return false;
	}
	
	@Override
	public boolean update(Ticket obj) {
		return false;
	}

	@Override
	public Ticket get(Ticket obj) {
		ResultSet res = null;
		Ticket t = new Ticket();
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Place where idPlace=?");
			stmt.setInt(1, obj.getId());
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		t.setId(res.getInt("idPlace"));
	    		t.setNumPlace(res.getString("numPlace"));
				t.setPrice(res.getFloat("prix"));
				Representation re = new Representation();
				re.setId(res.getInt("idRep"));
				re = re.getOne();
				t.setRepresentation(re);
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Ticket : " + ex.getMessage()); return null; }
		return t;
	}

	@Override
	public ArrayList<Ticket> getList() {
		return null;
	}

}
