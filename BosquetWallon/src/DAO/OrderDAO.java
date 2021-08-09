package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Order;
import POJO.Ticket;

public class OrderDAO extends Dao<Order>{

	@Override
	public boolean create(Order obj) {
		PreparedStatement stmt = null;
		try {
			//Crating PreparedStatement object
			stmt = connection().prepareStatement("insert into Commande (modePay, modeLivr, total) values (?,?,?)");
            //Setting values for Each Parameter
			stmt.setString(1, obj.getPaymentMethod());
			stmt.setString(2,obj.getDeliveryMethod());
			stmt.setDouble(3, obj.getTotal());
            //Executing Query
			stmt.executeUpdate();
	    	return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access create Order : " + ex.getMessage()); return false; }
	}
	
	@Override
	public boolean delete(Order obj) {
		return false;
	}
	
	@Override
	public boolean update(Order obj) {
		return false;
	}

	@Override
	public Order get(Order obj) {
		ResultSet res = null;
		Order o = new Order();
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Commande where idCmd=?");
			stmt.setInt(1, obj.getId());
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		o.setId(res.getInt("idCmd"));
	    		o.setPaymentMethod(res.getString("modePay"));
				o.setDeliveryMethod(res.getString("modeLivr"));
				o.setTotal(res.getFloat("total"));
				
				if(o!=null) {
		    		// Récup Orders
	    			stmt = connection().prepareStatement("SELECT idPlace FROM Place WHERE idCmd=?");
	    			stmt.setInt(1, obj.getId());
	    			res = stmt.executeQuery();
	    			while(res.next()) {
	    				Ticket t = new Ticket();
	    				t.setId(res.getInt("idPlace"));
	    				o.addTicket(t.getOne());	
	    	    	}
		    	}
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Order : " + ex.getMessage()); return null; }
		return o;
	}

	@Override
	public ArrayList<Order> getList() {
		return null;
	}
	
	public Order getNoID(Order obj) {
		ResultSet res = null;
		Order o = new Order();
		try {
			PreparedStatement stmt = connection().prepareStatement("SELECT * FROM Commande WHERE idCmd=(SELECT max(idCmd) FROM Commande)");
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		o.setId(res.getInt("idCmd"));
	    		o.setPaymentMethod(res.getString("modePay"));
				o.setDeliveryMethod(res.getString("modeLivr"));
				o.setTotal(res.getFloat("total"));
				
				if(o!=null) {
		    		// Récup Orders
	    			stmt = connection().prepareStatement("SELECT idPlace FROM Place WHERE idCmd=?");
	    			stmt.setInt(1, obj.getId());
	    			res = stmt.executeQuery();
	    			while(res.next()) {
	    				Ticket t = new Ticket();
	    				t.setId(res.getInt("idPlace"));
	    				o.addTicket(t.getOne());	
	    	    	}
		    	}
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Order : " + ex.getMessage()); return null; }
		return o;
	}

	public boolean addTicket(Order obj, Ticket ticket) {
		PreparedStatement stmt = null;
		try {
			stmt=connection().prepareStatement("update Place set idCmd=? where idPlace=?");
			stmt.setInt(1, obj.getId());
			stmt.setInt(2, ticket.getId());
            //Executing Query
			stmt.executeUpdate();
			return true;
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access update Ticket : " + ex.getMessage()); return false; }
	}
}
