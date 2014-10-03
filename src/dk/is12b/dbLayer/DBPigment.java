package dk.is12b.dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dk.is12b.modelLayer.Pigment;

public class DBPigment {
	
	public DBPigment(){
		
	}

	public void insertPigment(Pigment p) {
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		try {
			int id = DBGetMax.getMaxId("SELECT MAX(ID) FROM PIGMENT");
	        id = id + 1;
	        p.setId(id);
			stmt = c.createStatement();
			String sql = "INSERT INTO PIGMENT (ID,NAME,CHANCETO,CHANCEOFF,MIN,PERCENT) " +
	                   "VALUES (" + id + ", '" + p.getName()  + "', " + p.getChanceTo() + ", " + p.getChanceOff() + ", " + p.getMin() + ", " + p.getPercent() + " );"; 
			stmt.executeUpdate(sql);
			stmt.close();
			
			System.out.println("Insert Pigment: " + id + ", '" + p.getName()  + "', " + p.getChanceTo() + ", " + p.getChanceOff() + ", " + p.getMin() + ", " + p.getPercent());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Pigment> getAllPigments() {
		ArrayList<Pigment> pigments = new ArrayList<Pigment>();
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		
		try {
			stmt = c.createStatement();
			String sql = "SELECT * FROM PIGMENT";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Pigment p = new Pigment(rs.getString("NAME"), rs.getInt("CHANCETO"), rs.getInt("CHANCEOFF"), rs.getInt("MIN"), rs.getInt("PERCENT"));
				p.setId(rs.getInt("ID"));
				pigments.add(p);
			}
			
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return pigments;
	}

	public void updatePigment(Pigment p, String name, int chanceTo, int chanceOff, int min, int percent) {
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		
		try{
			stmt = c.createStatement();
			String sql = "UPDATE PIGMENT "
					   + "SET NAME = '" + name + "', " 
					   + "CHANCETO = " + chanceTo + ", "
					   + "CHANCEOFF = " + chanceOff + ", "
					   + "MIN = " + min + ", "
					   + "PERCENT = " + percent + " "
					   + "WHERE ID = " + p.getId() + ";";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void deletePigment(Pigment p) {
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		
		try{
			stmt = c.createStatement();
			String sql = "DELETE FROM PIGMENT WHERE ID = " + p.getId();
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Pigment getPigment(int id) {
		Pigment pigment = null;
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		
		try {
			stmt = c.createStatement();
			String sql = "SELECT * FROM PIGMENT WHERE ID = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				pigment = new Pigment(rs.getString("NAME"), rs.getInt("CHANCETO"), rs.getInt("CHANCEOFF"), rs.getInt("MIN"), rs.getInt("PERCENT"));
				pigment.setId(rs.getInt("ID"));
			}
			
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return pigment;
	}

}
