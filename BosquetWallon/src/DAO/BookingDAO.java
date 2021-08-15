package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Booking;
import POJO.Organizer;
import POJO.Planning;

public class BookingDAO extends Dao<Booking>{

	@Override
	public boolean create(Booking obj) {
		PreparedStatement stmt = null;
		try {
			//Crating PreparedStatement object
			stmt = connection().prepareStatement("insert into Reservation (acompte, solde, statut, prix, idPlan) values (?,?,?,?,?)");
            //Setting values for Each Parameter
			stmt.setDouble(1, obj.getDeposit());
			stmt.setDouble(2,obj.getBalance());
			stmt.setString(3, obj.getStatus());
			stmt.setDouble(4, obj.getPrice());
			stmt.setInt(5,  obj.getPlanning().getId());
            //Executing Query
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access create Booking : " + ex.getMessage()); return false; }
	}
	
	@Override
	public boolean delete(Booking obj) {
		return false;
	}
	
	@Override
	public boolean update(Booking obj) {
		PreparedStatement stmt = null;
		try {
			stmt=connection().prepareStatement("update Reservation set statut=? where idRes=?");
			stmt.setString(1, obj.getStatus());
			stmt.setInt(2, obj.getId());
            //Executing Query
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access update Booking : " + ex.getMessage()); return false; }
	}

	@Override
	public Booking get(Booking obj) {
		ResultSet res = null;
		Booking b = new Booking();
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Reservation where idRes=?");
			stmt.setInt(1, obj.getId());
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		b.setId(res.getInt("idRes"));
	    		b.setDeposit(res.getFloat("acompte"));
				b.setBalance(res.getFloat("solde"));
				b.setStatus(res.getString("statut"));
				b.setPrice(res.getFloat("prix"));
				Planning p = new Planning();
				p.setId(res.getInt("idPlan"));
				b.setPlanning(p.getOne());
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Booking : " + ex.getMessage()); return null; }
		return b;
	}

	@Override
	public ArrayList<Booking> getList() {
		ResultSet res = null;
		ArrayList<Booking> reser = new ArrayList<Booking>();
		try {
			//Using SQL SELECT Query
            PreparedStatement stmt = connection().prepareStatement("select * from Reservation");
            //Creaing Java ResultSet object
            res = stmt.executeQuery();
	    	while(res.next()) {
	    		Booking b = new Booking();
	    		b.setId(res.getInt("idRes"));
	    		b.setDeposit(res.getFloat("acompte"));
				b.setBalance(res.getFloat("solde"));
				b.setStatus(res.getString("statut"));
				b.setPrice(res.getFloat("prix"));
				Planning p = new Planning();
				p.setId(res.getInt("idPlan"));
				b.setPlanning(p.getOne());
				reser.add(b);		
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get all Bookings : " + ex.getMessage()); return null; }
		return reser;
	}

	public Booking getNoID(Booking obj) {
		ResultSet res = null;
		Booking b = new Booking();
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Reservation where idPlan=?");
			//Setting values for Each Parameter
			stmt.setInt(1, obj.getPlanning().getId());
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		b.setId(res.getInt("idRes"));
	    		b.setDeposit(res.getFloat("acompte"));
				b.setBalance(res.getFloat("solde"));
				b.setStatus(res.getString("statut"));
				b.setPrice(res.getFloat("prix"));
				Planning p = new Planning();
				p.setId(res.getInt("idPlan"));
				b.setPlanning(p.getOne());
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Booking : " + ex.getMessage()); return null; }
		return b;
	}
	
	public boolean updateBooking(Booking obj, Organizer org) {
		PreparedStatement stmt = null;
		try {
			stmt=connection().prepareStatement("update Reservation set idPer=? where idRes=?");
			stmt.setInt(1, org.getId());
			stmt.setInt(2, obj.getId());
            //Executing Query
			stmt.executeUpdate();
			return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access update Booking : " + ex.getMessage()); return false; }
	}
}
