package DAO;

import java.sql.*;
import javax.swing.JOptionPane;


public class Singleton {
	    private static Singleton instance = null;
	    private Connection co;
	    
	    private Singleton() {
	        try {
	        	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
	        	 co = DriverManager.getConnection("jdbc:ucanaccess://./DatabaseBosquetWallon.accdb");//Establishing Connection
	        } 
	        catch(ClassNotFoundException ex){
	        	JOptionPane.showMessageDialog(null, "Classe de driver introuvable" + ex.getMessage());
				System.exit(0);
			}
			catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Erreur JDBC : " + ex.getMessage());
			}

	    }
	    
	    public static Singleton instanciate(){
	        if(instance == null) { instance = new Singleton(); }
	        return instance;
	    }
	    
	    public Connection getConnection() { return co; }
	    
	    protected void finalize() {
	        try {
	            co.close();
	        } catch (Exception err) { JOptionPane.showMessageDialog(null,err); }
	    }
}

