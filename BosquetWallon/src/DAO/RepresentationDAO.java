package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Representation;
import POJO.Show;

public class RepresentationDAO extends Dao<Representation>{

	@Override
	public boolean create(Representation obj) {
		PreparedStatement stmt = null;
		try {
			//Crating PreparedStatement object
			stmt = connection().prepareStatement("insert into Representation (dateR, heureD, heureF, idSpec) values (?,?,?,?)");
            //Setting values for Each Parameter
			ZonedDateTime zdt = ZonedDateTime.ofInstant ( obj.getDate().getTime().toInstant() , ZoneId.of ( "Europe/Paris" ) );
			LocalDate localDate = zdt.toLocalDate();
			java.sql.Date sqlDate = java.sql.Date.valueOf( localDate );
			stmt.setDate(1, sqlDate);
			stmt.setString(2, obj.getBeginHour());
			stmt.setString(3, obj.getEndHour());
			stmt.setInt(4, obj.getShow().getId());
            //Executing Query
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access create Representation : " + ex.getMessage()); return false; }
	}
	
	@Override
	public boolean delete(Representation obj) {
		PreparedStatement stmt = null;
		try {
			//Using SQL DELETE query
			stmt=connection().prepareStatement("delete from Representation where idRepr=?");
			stmt.setInt(1, obj.getId());
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access delete Representation : " + ex.getMessage()); return false; }
	}
	
	@Override
	public boolean update(Representation obj) {
	    return false;
	}

	@Override
	public Representation get(Representation obj) {
		ResultSet res = null;
		Representation r = null;
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Representation where idRepr=?");
			//Setting values for Each Parameter
			stmt.setInt(1, obj.getId());
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		r = new Representation();
	    		r.setId(res.getInt("idRepr"));
	    		r.setDate(res.getDate("dateR"));
    			r.setBeginHour(res.getString("heureD"));
    			r.setEndHour(res.getString("heureF"));
    			Show s = new Show();
				s.setId(res.getInt("idSpec"));
				r.setShow(s.getOne());
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Representation : " + ex.getMessage()); return null; }
		return r;
	}

	@Override
	public ArrayList<Representation> getList() {
		ResultSet res = null;
		ArrayList<Representation> repr = new ArrayList<Representation>();
		try {
			//Using SQL SELECT Query
            PreparedStatement stmt = connection().prepareStatement("select * from Representation");
            //Creaing Java ResultSet object
            res = stmt.executeQuery();
	    	while(res.next()) {
	    		Representation r = new Representation();
	    		r.setId(res.getInt("idRepr"));
	    		r.setDate(res.getDate("dateR"));
    			r.setBeginHour(res.getString("heureD"));
    			r.setEndHour(res.getString("heureF"));
    			Show s = new Show();
				s.setId(res.getInt("idSpec"));
				r.setShow(s.getOne());
    			repr.add(r);
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get all Representation : " + ex.getMessage()); return null; }
		return repr;
	}
	
	public Representation getNoID(Representation obj) {
		ResultSet res = null;
		Representation r = null;
		try {
			PreparedStatement stmt = connection().prepareStatement("SELECT * FROM Representation WHERE idRepr=(SELECT max(idRepr) FROM Representation);");
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		r = new Representation();
	    		r.setId(res.getInt("idRepr"));
	    		r.setDate(res.getDate("dateR"));
    			r.setBeginHour(res.getString("heureD"));
    			r.setEndHour(res.getString("heureF"));
    			Show s = new Show();
				s.setId(res.getInt("idSpec"));
				r.setShow(s.getOne());
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Representation : " + ex.getMessage()); return null; }
		return r;
	}
}