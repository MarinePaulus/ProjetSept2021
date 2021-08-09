package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Planning;
import POJO.Show;

public class PlanningDAO  extends Dao<Planning>{

	@Override
	public boolean create(Planning obj) {
		PreparedStatement stmt = null;
		try {
			//Crating PreparedStatement object
			stmt = connection().prepareStatement("insert into Planning (dateDR, dateFR) values (?,?)");
            //Setting values for Each Parameter
			ZonedDateTime zdtD = ZonedDateTime.ofInstant ( obj.getBeginDate().getTime().toInstant() , ZoneId.of ( "Europe/Paris" ) );
			ZonedDateTime zdtF = ZonedDateTime.ofInstant ( obj.getEndDate().getTime().toInstant() , ZoneId.of ( "Europe/Paris" ) );
			LocalDate localDate = zdtD.toLocalDate();
			java.sql.Date sqlDate = java.sql.Date.valueOf( localDate );
			stmt.setDate(1, sqlDate);
			localDate = zdtF.toLocalDate();
			sqlDate = java.sql.Date.valueOf( localDate );
			stmt.setDate(2,sqlDate);
            //Executing Query
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access create Planning : " + ex.getMessage()); return false; }
	}
	
	@Override
	public boolean delete(Planning obj) {
		return false;
	}
	
	@Override
	public boolean update(Planning obj) {
		return false;
	}

	@Override
	public Planning get(Planning obj) {
		ResultSet res = null;
		Planning p = new Planning();
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Planning where idPlan=?");
			//Setting values for Each Parameter
			stmt.setInt(1,obj.getId());
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		p.setId(res.getInt("idPlan"));
	    		p.setBeginDate(res.getDate("dateDR"));
	    		p.setEndDate(res.getDate("dateFR"));
	    	}
	    	if(p!=null) {
	    		// Récup Show
	    		stmt = connection().prepareStatement("SELECT idSpec FROM Spectacle WHERE idPlan=?");
    			stmt.setInt(1, obj.getId());
    			res = stmt.executeQuery();
    			while(res.next()) {
    				Show s = new Show();
    				s.setId(res.getInt("idSpec"));
    				p.addShow(s.getOne());	
    	    	}
	    	}
	    	
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Planning : " + ex.getMessage()); return null; }
		return p;
	}

	@Override
	public ArrayList<Planning> getList() {
		ResultSet res = null;
		ResultSet res2 = null;
		ArrayList<Planning> plan = new ArrayList<Planning>();
		try {
			//Using SQL SELECT Query
            PreparedStatement stmt = connection().prepareStatement("select * from Planning");
            //Creaing Java ResultSet object
            res = stmt.executeQuery();
	    	while(res.next()) {
	    		Planning p = new Planning();
	    		p.setId(res.getInt("idPlan"));
	    		p.setBeginDate(res.getDate("dateDR"));
	    		p.setEndDate(res.getDate("dateFR"));
	    		// Récup Representation
	    		PreparedStatement stmt2 = connection().prepareStatement("SELECT idSpec FROM Spectacle WHERE idPlan=?");
    			stmt2.setInt(1, p.getId());
    			res2 = stmt2.executeQuery();
    			while(res2.next()) {
    				Show s = new Show();
    				s.setId(res2.getInt("idSpec"));
    				p.addShow(s.getOne());	
    	    	}	    		
	    		plan.add(p);
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get all Plannings : " + ex.getMessage()); return null; }
		return plan;
	}
	
	public Planning getNoID(Planning obj) {
		ResultSet res = null;
		Planning p = new Planning();
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Planning where dateDR=? and dateFR=?");
			//Setting values for Each Parameter
			ZonedDateTime zdtD = ZonedDateTime.ofInstant ( obj.getBeginDate().getTime().toInstant() , ZoneId.of ( "Europe/Paris" ) );
			ZonedDateTime zdtF = ZonedDateTime.ofInstant ( obj.getEndDate().getTime().toInstant() , ZoneId.of ( "Europe/Paris" ) );
			LocalDate localDate = zdtD.toLocalDate();
			Date sqlDate = Date.valueOf( localDate );
			stmt.setDate(1, sqlDate);
			localDate = zdtF.toLocalDate();
			sqlDate = Date.valueOf( localDate );
			stmt.setDate(2,sqlDate);
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		p.setId(res.getInt("idPlan"));
	    		p.setBeginDate(res.getDate("dateDR"));
	    		p.setEndDate(res.getDate("dateFR"));
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Planning : " + ex.getMessage()); return null; }
		return p;
	}

	public boolean recShows(Planning obj) {
		PreparedStatement stmt = null;
		try {
			for(Show show :obj.getShowList()) {
				stmt=connection().prepareStatement("update Spectacle set idPlan=? where idSpec=?");
				stmt.setInt(1, obj.getId());
				stmt.setInt(2, show.getId());
	            //Executing Query
				stmt.executeUpdate();
			}
			return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access update Show : " + ex.getMessage()); return false; }
	}
}
