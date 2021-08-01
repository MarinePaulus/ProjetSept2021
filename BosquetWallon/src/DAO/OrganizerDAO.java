package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Booking;
import POJO.Organizer;

public class OrganizerDAO extends Dao<Organizer>{
	@Override
	public boolean create(Organizer obj) {
		PreparedStatement stmt = null;
		try {
			//Crating PreparedStatement object
			stmt = connection().prepareStatement("insert into Personne (nom, prenom, adresse, mail, mdp, role, telephone) values (?,?,?,?,?,?,?)");
            //Setting values for Each Parameter
			stmt.setString(1, obj.getLastname());
			stmt.setString(2, obj.getFirstname());
			stmt.setString(3, obj.getAdress());
			stmt.setString(4, obj.getEmailAddress());
			stmt.setString(5, obj.getPassword());
			stmt.setString(6, "Organisateur");
			stmt.setString(7, obj.getPhoneNumber());
            //Executing Query
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access create Manager : " + ex.getMessage()); return false; }
	}
	
	@Override
	public boolean delete(Organizer obj) {
	    return false;
	}
	
	@Override
	public boolean update(Organizer obj) {
	    return false;
	}

	@Override
	public Organizer get(Organizer obj) {
		ResultSet res = null;
		Organizer p = null;
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Personne where idPer=? and mdp=?");
			//Setting values for Each Parameter
			stmt.setInt(1, obj.getId());
			stmt.setString(2, obj.getPassword());
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		p = new Organizer();
	    		p.setId(res.getInt("idPer"));
    			p.setLastname(res.getString("nom"));
    			p.setFirstname(res.getString("prenom"));
    			p.setAdress(res.getString("adresse"));
    			p.setEmailAddress(res.getString("mail"));
    			p.setPassword(res.getString("mdp"));
    			p.setRole(res.getString("role"));
    			p.setPhoneNumber(res.getString("telephone"));
	    	}
	    	if(p!= null) {
	    		// Récup Bookings
    			stmt = connection().prepareStatement("SELECT idRes FROM Reservation WHERE idPer=?");
    			stmt.setInt(1, obj.getId());
    			res = stmt.executeQuery();
    			while(res.next()) {
    				Booking b = new Booking();
    				b.setId(res.getInt("idRes"));
    				p.addBooking(b.getOne());	
    	    	}
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Organizer : " + ex.getMessage()); return null; }
		return p;
	}

	@Override
	public ArrayList<Organizer> getList() {
		return null;
	}
	
	public boolean recBookings(Organizer obj) {
		PreparedStatement stmt = null;
		try {
			for(Booking book :obj.getBookingList()) {
				stmt=connection().prepareStatement("update Reservation set idPer=? where idRes=?");
				stmt.setInt(1, obj.getId());
				stmt.setInt(2, book.getId());
	            //Executing Query
				stmt.executeUpdate();
			}
			return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access update Booking : " + ex.getMessage()); return false; }
	}
}
