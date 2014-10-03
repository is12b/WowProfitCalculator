package dk.is12b.dbLayer;

import java.io.File;
import java.sql.*;

public class DBConnection {
	private Connection c;
	private static DBConnection instance;
	
	private DBConnection(){
		boolean exist = true;
		File f = new File("/WoWProfitCalculator.db");
		if(!f.exists()){
			exist = false;
		}
		c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:WoWProfitCalculator.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	    
	    if(exist){
	    	createTables();
	    }
	}
	
	public static DBConnection getInstance(){
		if(instance == null){
			instance = new DBConnection();
		}
		
		return instance;
	}
	
	public Connection getConnection(){
		return c;
	}

	private void createTables(){
		try {
		      Statement stmt = c.createStatement();
		      String pigmentQuery = "CREATE TABLE PIGMENT " +
		                   "(ID INT PRIMARY KEY     NOT NULL," +
		                   " NAME           TEXT    NOT NULL, " + 
		                   " CHANCETO       INT     NOT NULL, " + 
		                   " CHANCEOFF      INT     NOT NULL, " + 
		                   " MIN            INT     NOT NULL, " +
		                   " PERCENT        INT     NOT NULL)"; 
		      
		      String herbQuery = "CREATE TABLE PIGMENT " +
	                   "(ID INT PRIMARY KEY     NOT NULL," +
	                   " NAME           TEXT    NOT NULL, " + 
	                   " PIGMENTS       TEXT    NOT NULL)"; 
		      stmt.executeUpdate(pigmentQuery);
		      stmt.executeUpdate(herbQuery);
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    }
	}
}
