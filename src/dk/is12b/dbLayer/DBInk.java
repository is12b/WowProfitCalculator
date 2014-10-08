package dk.is12b.dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dk.is12b.modelLayer.Herb;
import dk.is12b.modelLayer.Ink;
import dk.is12b.modelLayer.Pigment;

public class DBInk {

	public DBInk(){
		
	}

	public void insertInk(Ink i){
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		try{
			int id = DBGetMax.getMaxId("SELECT MAX(ID) FROM INK");
			id = id + 1;
			i.setId(id);
			stmt = c.createStatement();
			
			String sql = "INSERT INTO INK (ID,NAME) " + 
					     "VALUES (" + id + ", '" + i.getName() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<Ink> getAllInk(){
		ArrayList<Ink> inks = new ArrayList<Ink>();
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		
		try{
			stmt = c.createStatement();
			String sql = "SELECT * FROM INK";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Ink i = new Ink(rs.getString("NAME"));
				i.setId(rs.getInt("ID"));
				
				inks.add(i);
			}
			
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return inks;
	}
	
	public void updateInk(Ink i){
		ArrayList<Ink> inks = new ArrayList<Ink>();
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		try{
			stmt = c.createStatement();
			String sql = "UPDATE INK " 
					   + "SET NAME = '" + i.getName() + "' " 
					   + "WHERE ID = " + i.getId() + ";";
			
			stmt.executeUpdate(sql);
			stmt.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public void deleteInk(Ink i){
		ArrayList<Ink> inks = new ArrayList<Ink>();
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		try{
			stmt = c.createStatement();
			String sql = "DELETE FROM INK WHERE ID = " + i.getId() + ";";
			
			stmt.executeUpdate(sql);
			stmt.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public Ink getInk(int id){
		Ink i = null;
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		
		try{
			stmt = c.createStatement();
			String sql = "SELECT * FROM INK WHERE ID = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				i = new Ink(rs.getString("NAME"));
				i.setId(rs.getInt("ID"));
			}
			
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return i;
	}

}
