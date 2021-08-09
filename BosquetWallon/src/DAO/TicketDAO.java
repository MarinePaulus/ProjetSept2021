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
			stmt = connection().prepareStatement("insert into Place (numPlace, prix, idRepr) values (?,?,?)");
            //Setting values for Each Parameter
			stmt.setInt(1, obj.getNumPlace());
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
	    		t.setNumPlace(res.getInt("numPlace"));
				t.setPrice(res.getFloat("prix"));
				Representation re = new Representation();
				re.setId(res.getInt("idRepr"));
				t.setRepresentation(re.getOne());
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Ticket : " + ex.getMessage()); return null; }
		return t;
	}

	@Override
	public ArrayList<Ticket> getList() {
		ResultSet res = null;
		ArrayList<Ticket> tic = new ArrayList<Ticket>();
		try {
			//Using SQL SELECT Query
            PreparedStatement stmt = connection().prepareStatement("select * from Place");
            //Creaing Java ResultSet object
            res = stmt.executeQuery();
	    	while(res.next()) {
	    		Ticket t = new Ticket();
	    		t.setId(res.getInt("idPlace"));
	    		t.setNumPlace(res.getInt("numPlace"));
				t.setPrice(res.getFloat("prix"));
				Representation re = new Representation();
				re.setId(res.getInt("idRepr"));
				t.setRepresentation(re.getOne());
    			tic.add(t);
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get all Artist : " + ex.getMessage()); return null; }
		return tic;
	}
	
	public Ticket getNoID(Ticket obj) {
		ResultSet res = null;
		Ticket t = new Ticket();
		try {
			PreparedStatement stmt = connection().prepareStatement("SELECT * FROM Place WHERE idPlace=(SELECT max(idPlace) FROM Place)");
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
			if(res.next()) {
	    		t.setId(res.getInt("idPlace"));
	    		t.setNumPlace(res.getInt("numPlace"));
				t.setPrice(res.getFloat("prix"));
				Representation re = new Representation();
				re.setId(res.getInt("idRepr"));
				t.setRepresentation(re.getOne());
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Place : " + ex.getMessage()); return null; }
		return t;
	}
}
