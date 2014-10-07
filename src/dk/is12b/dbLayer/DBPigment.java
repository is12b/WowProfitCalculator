package dk.is12b.dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dk.is12b.modelLayer.Herb;
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
			int ownerID = 0;
			if(p.getOwner() != null){
				ownerID = p.getOwner().getId();
			}
			System.out.println("Insert Pigment: " + id + ", '" + p.getName()  + "', " + p.getChanceTo() + ", " + p.getChanceOff() + ", " + p.getPercent() + ", " + ownerID);
			String sql = "INSERT INTO PIGMENT (ID,NAME,CHANCETO,CHANCEOFF,PERCENT,OWNER) " +
	                   "VALUES (" + id + ", '" + p.getName()  + "', " + p.getChanceTo() + ", " + p.getChanceOff() + ", " + p.getPercent() + ", " + ownerID + " );"; 
			stmt.executeUpdate(sql);
			stmt.close();
			
			
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
				Pigment p = new Pigment(rs.getString("NAME"), rs.getInt("CHANCETO"), rs.getInt("CHANCEOFF"), rs.getInt("PERCENT"));
				p.setId(rs.getInt("ID"));
				DBHerb dbh = new DBHerb();
				p.setOwner(dbh.getHerb(rs.getInt("OWNER")));
				pigments.add(p);
			}
			
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return pigments;
	}

	public void updatePigment(Pigment p, String name, int chanceTo, int chanceOff, int percent, Herb h, boolean updateHerb) {
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		Herb hl = h;
		
		if(!updateHerb){
			hl = p.getOwner();
		}
		
		Statement stmt;
		
		try{
			stmt = c.createStatement();
			String sql = "UPDATE PIGMENT "
					   + "SET NAME = '" + name + "', " 
					   + "CHANCETO = " + chanceTo + ", "
					   + "CHANCEOFF = " + chanceOff + ", "
					   + "PERCENT = " + percent + ", "
					   + "OWNER = " + hl.getId() + " "
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
				pigment = new Pigment(rs.getString("NAME"), rs.getInt("CHANCETO"), rs.getInt("CHANCEOFF"), rs.getInt("PERCENT"));
				pigment.setId(rs.getInt("ID"));
				DBHerb dbh = new DBHerb();
				pigment.setOwner(dbh.getHerb(rs.getInt("OWNER")));
			}
			
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return pigment;
	}

	public ArrayList<Pigment> getPigmentsByOwner(Herb h) {
		ArrayList<Pigment> pigment = new ArrayList<Pigment>();
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		
		try {
			stmt = c.createStatement();
			String sql = "SELECT * FROM PIGMENT WHERE OWNER = " + h.getId();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Pigment p = new Pigment(rs.getString("NAME"), rs.getInt("CHANCETO"), rs.getInt("CHANCEOFF"), rs.getInt("PERCENT"));
				p.setId(rs.getInt("ID"));
				p.setOwner(h);
				
				pigment.add(p);
			}
			
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return pigment;
	}

}
