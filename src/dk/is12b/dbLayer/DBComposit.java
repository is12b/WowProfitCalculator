package dk.is12b.dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dk.is12b.ctrLayer.InkCtr;
import dk.is12b.modelLayer.Composit;
import dk.is12b.modelLayer.Ink;
import dk.is12b.modelLayer.Pigment;

public class DBComposit {

	public DBComposit(){
		
	}

	public void insertComposit(Ink i, Pigment p, int amount){
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		try{
			stmt = c.createStatement();
			
			String sql = "INSERT INTO COMPOSIT (INKID, PIGID, AMOUNT) " + 
					     "VALUES (" + i.getId() + ", '" + p.getId() + "', " + amount + ");";
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updateComposit(Ink i, Pigment p, int amount){
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		try{
			stmt = c.createStatement();
			String sql = "UPDATE Composit " 
					   + "SET AMOUNT = '" + amount + "' " 
					   + "WHERE INKID = " + i.getId() 
					   + "AND PIGID = " + p.getId() + ";";
			
			stmt.executeUpdate(sql);
			stmt.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public void deleteComposit(Ink i, Pigment p){
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		try{
			stmt = c.createStatement();
			String sql = "DELETE FROM Composit " 
					   + "WHERE INKID = " + i.getId() + " "
					   + "AND PIGID = " + p.getId() + ";";
			
			stmt.executeUpdate(sql);
			stmt.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public Composit getComposit(Ink i, Pigment p){
		Composit comp = null;
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		
		try{
			stmt = c.createStatement();
			String sql = "SELECT * FROM Composit "
					   + "WHERE INKID = " + i.getId() 
					   + "AND PIGID = " + p.getId() + ";";
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				comp = new Composit();
				comp.setAmount(rs.getInt("AMOUNT"));
				DBInk dbI = new DBInk();
				DBPigment dbPig = new DBPigment();
				comp.setInk(i);
				comp.setPigment(p);
			}
			
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return comp;
	}
	
	public ArrayList<Composit> getCompositsByPigment(Pigment p){
		ArrayList<Composit> comps = new ArrayList<Composit>();
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		
		try{
			stmt = c.createStatement();
			String sql = "SELECT * FROM COMPOSIT "
					   + "WHERE PIGID = " + p.getId();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Composit comp = new Composit();
				comp.setAmount(rs.getInt("AMOUNT"));
				InkCtr iCtr = new InkCtr();
				comp.setInk(iCtr.getInk(rs.getInt("INKID")));
				comp.setPigment(p);
				
				comps.add(comp);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return comps;
	}
}
