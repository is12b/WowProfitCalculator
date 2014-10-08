package dk.is12b.dbLayer;

import java.io.File;
import java.sql.*;

public class DBConnection {
	private Connection c;
	private static DBConnection instance;
	
	private DBConnection(){
		
		//boolean exist = false;
		//File f = new File("/WoWProfitCalculator.db");
		//if(!f.exists()){
		//	exist = false;
		//}
		
		c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:WoWProfitCalculator.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	    
	    //if(exist){
	    //	createHerbTable();
	    //	createPigmentTable();
	    //	createInkTable();
	    //	createInkPigmentTable();
	    //}
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

	private void createPigmentTable(){
		try {
		      Statement stmt = c.createStatement();
		      String pigmentQuery = "CREATE TABLE PIGMENT " +
		                   "(ID 		    INT     NOT NULL, " +
		                   " NAME           TEXT    NOT NULL, " + 
		                   " CHANCETO       INT     NOT NULL, " + 
		                   " CHANCEOFF      INT     NOT NULL, " + 
		                   " PERCENT        INT     NOT NULL, " + 
		                   " OWNER          INT, " + 
		                   " PRIMARY KEY(id, owner), " +
		                   " FOREIGN KEY(OWNER) REFERENCES HERB(ID))"; 
		      stmt.executeUpdate(pigmentQuery);
		      stmt.close();
		      System.out.println("PigmentTable Created");
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    }
	}
	
	private void createHerbTable(){
		try {
		      Statement stmt = c.createStatement();
		      String herbQuery = "CREATE TABLE HERB " +
	                   "(ID INT PRIMARY KEY     NOT NULL," +
	                   " NAME       TEXT    NOT NULL)"; 
		      stmt.executeUpdate(herbQuery);
		      stmt.close();
		      System.out.println("HerbTable Created");
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    }
	}
	
	private void createInkTable(){
		try {
		      Statement stmt = c.createStatement();
		      String herbQuery = "CREATE TABLE INK " +
	                   "(ID INT PRIMARY KEY     NOT NULL," +
	                   " NAME           TEXT    NOT NULL)";
		      stmt.executeUpdate(herbQuery);
		      stmt.close();
		      System.out.println("InkTable Created");
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    }
	}
	
	private void createInkPigmentTable(){
		try {
		      Statement stmt = c.createStatement();
		      String herbQuery = "CREATE TABLE COMPOSIT " +
	                   "(INKID INT     NOT NULL," +
	                   " PIGID INT     NOT NULL," + 
	                   " AMOUNT INT    NOT NULL," +
	                   " PRIMARY KEY(inkid, pigid)," +
	                   " FOREIGN KEY(INKID) REFERENCES INK(ID) ON DELETE CASCADE" +
	                   " FOREIGN KEY(PIGID) REFERENCES PIGMENT(ID) ON DELETE CASCADE )";
		      stmt.executeUpdate(herbQuery);
		      stmt.close();
		      System.out.println("InkPigmentTable Created");
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    }
	}
}
