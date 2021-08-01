package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Manager;

public class ManagerDAO extends Dao<Manager>{

	@Override
	public boolean create(Manager obj) {
		return false;
//		PreparedStatement stmt = null;
//		try {
//			//Crating PreparedStatement object
//			stmt = connection().prepareStatement("insert into Personne (nom, prenom, adresse, mail, mdp, role, telephone) values (?,?,?,?,?,?,?)");
//            //Setting values for Each Parameter
//			stmt.setString(1, obj.getLastname());
//			stmt.setString(2, obj.getFirstname());
//			stmt.setString(3, obj.getAdress());
//			stmt.setString(4, obj.getEmailAddress());
//			stmt.setString(5, obj.getPassword());
//			stmt.setString(6, "Gestionnaire");
//			stmt.setString(7, obj.getPhoneNumber());
//            //Executing Query
//			stmt.executeUpdate();
//	    	return true;
//	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access create Manager : " + ex.getMessage()); return false; }
	}
	
	@Override
	public boolean delete(Manager obj) {
	    return false;
	}
	
	@Override
	public boolean update(Manager obj) {
	    return false;
	}

	@Override
	public Manager get(Manager obj) {
		ResultSet res = null;
		Manager p = null;
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Personne where idPer=? and mdp=?");
			//Setting values for Each Parameter
			stmt.setInt(1, obj.getId());
			stmt.setString(2, obj.getPassword());
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		p = new Manager();
	    		p.setId(res.getInt("idPer"));
    			p.setLastname(res.getString("nom"));
    			p.setFirstname(res.getString("prenom"));
    			p.setAdress(res.getString("adresse"));
    			p.setEmailAddress(res.getString("mail"));
    			p.setPassword(res.getString("mdp"));
    			p.setRole(res.getString("role"));
    			p.setPhoneNumber(res.getString("telephone"));
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Manager : " + ex.getMessage()); return null; }
		return p;
	}

	@Override
	public ArrayList<Manager> getList() {
		return null;
	}
}
