package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import POJO.Artist;
import POJO.Manager;
import POJO.Organizer;
import POJO.Person;
import POJO.Spectator;

public class PersonDAO extends Dao<Person> {

	@Override
	public boolean create(Person obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Person obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Person obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Person get(Person obj) {
		ResultSet res = null;
		Person p = null;
		try {
			PreparedStatement stmt = connection().prepareStatement("select * from Personne where mail=?");
			//Setting values for Each Parameter
			stmt.setString(1, obj.getEmailAddress());
			//Creaing Java ResultSet object
			res = stmt.executeQuery();
	    	if(res.next()) {
	    		if(res.getString("role").equals("Organisateur")){
	    			p = new Organizer();
	    		}
	    		else if(res.getString("role").equals("Spectateur")){
					p = new Spectator();			
				}
	    		else if(res.getString("role").equals("Gestionnaire")){
					p = new Manager();
				}
	    		else{
					p = new Artist();
				}
	    		p.setId(res.getInt("idPer"));
	    	}
	    } catch (SQLException ex){JOptionPane.showMessageDialog(null,"Error Access get one Personne : " 
	    																+ ex.getMessage()); return null; }
		return p;
	}

	@Override
	public ArrayList<Person> getList() {
		// TODO Auto-generated method stub
		return null;
	}
}
