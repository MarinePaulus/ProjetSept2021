package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Order;
import POJO.Spectator;

public class SpectatorDAO extends Dao<Spectator>{

	@Override
	public boolean create(Spectator obj) {
		PreparedStatement stmt = null;
		try {
			//Crating PreparedStatement object
			stmt = connection().prepareStatement("insert into Personne (nom, prenom, adresse, mail, mdp, role, genre, telephone, naissance) values (?,?,?,?,?,?,?,?,?)");
            //Setting values for Each Parameter
			stmt.setString(1, obj.getLastname());
			stmt.setString(2, obj.getFirstname());
			stmt.setString(3, obj.getAdress());
			stmt.setString(4, obj.getEmailAddress());
			stmt.setString(5, obj.getPassword());
			stmt.setString(6, "Spectateur");
			stmt.setString(7, obj.getGender());
			stmt.setString(8, obj.getPhoneNumber());
			stmt.setString(9, obj.getBirthdate());
            //Executing Query
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access create Spectator : " + ex.getMessage()); return false; }
	}
	
	@Override
	public boolean delete(Spectator obj) {
	    return false;
	}
	
	@Override
	public boolean update(Spectator obj) {
	    return false;
	}

	@Override
	public Spectator get(Spectator obj) {
		ResultSet res = null;
		Spectator p = null;
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Personne where idPer=? and mdp=?");
			//Setting values for Each Parameter
			stmt.setInt(1, obj.getId());
			stmt.setString(2, obj.getPassword());
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		p = new Spectator();
	    		p.setId(res.getInt("idPer"));
    			p.setLastname(res.getString("nom"));
    			p.setFirstname(res.getString("prenom"));
    			p.setAdress(res.getString("adresse"));
    			p.setEmailAddress(res.getString("mail"));
    			p.setPassword(res.getString("mdp"));
    			p.setRole(res.getString("role"));
    			p.setGender(res.getString("genre"));
    			p.setPhoneNumber(res.getString("telephone"));
    			p.setBirthdate(res.getString("naissance"));
	    	}
	    	if(p!= null) {
	    		// Récup Orders
    			stmt = connection().prepareStatement("SELECT idCmd FROM Commande WHERE idPer=?");
    			stmt.setInt(1, obj.getId());
    			res = stmt.executeQuery();
    			while(res.next()) {
    				Order o = new Order();
    				o.setId(res.getInt("idCmd"));
    				p.addOrder(o.getOne());	
    	    	}
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Spectator : " + ex.getMessage()); return null; }
		return p;
	}

	@Override
	public ArrayList<Spectator> getList() {
		return null;
	}
	
	public boolean addOrder(Spectator obj, Order order) {
		PreparedStatement stmt = null;
		try {
			stmt=connection().prepareStatement("update Commande set idPer=? where idCmd=?");
			stmt.setInt(1, obj.getId());
			stmt.setInt(2, order.getId());
            //Executing Query
			stmt.executeUpdate();
			return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access update Order : " + ex.getMessage()); return false; }
	}
}
