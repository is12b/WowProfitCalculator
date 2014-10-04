package dk.is12b.dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dk.is12b.modelLayer.Herb;
import dk.is12b.modelLayer.Pigment;

public class DBHerb {

	public DBHerb(){
	}

	public void insertHerb(Herb h) {
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		try {
			int id = DBGetMax.getMaxId("SELECT MAX(ID) FROM HERB");
	        id = id + 1;
	        h.setId(id);
	        
	        String pigments = "";
	        ArrayList<Pigment> pigs = h.getPigments();
	        if(pigs.size() > 0){
		        for(Pigment p : pigs){
		        	pigments += p.getId() + ",";
		        }
		        pigments = pigments.substring(0, pigments.length()-1);
	        }
			stmt = c.createStatement();
			String sql = "INSERT INTO HERB (ID,NAME,PIGMENTS) " +
	                   "VALUES (" + h.getId() + ", '" + h.getName()  + "', '" + pigments + "' );"; 
			stmt.executeUpdate(sql);
			stmt.close();
			
			//System.out.println("Insert Herb: "  + id + ", '" + h.getName()  + "', " + pigments);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Herb> getAllHerbs() {
		ArrayList<Herb> herbs = new ArrayList<Herb>();
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		
		try {
			stmt = c.createStatement();
			String sql = "SELECT * FROM HERB";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				System.out.println("Rs.next ok");
				Herb h = new Herb(rs.getString("NAME"));
				h.setId(rs.getInt("ID"));
				String pigments = rs.getString("PIGMENTS");
				if(!pigments.isEmpty()){
					System.out.println("Pigments ok");
					String[] pigsArr = pigments.split(",");
					DBPigment dbPig = new DBPigment(); 
					for(String s : pigsArr){
						Pigment p = dbPig.getPigment(Integer.parseInt(s));
						h.addPigment(p);
					}
				}
				
				herbs.add(h);
				
			}
			
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return herbs;
	}

	public void updateHerb(Herb h, String name) {
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		String pigments = "";
        ArrayList<Pigment> pigs = h.getPigments();
        if(pigs.size() > 0){
	        for(Pigment p : pigs){
	        	pigments += p.getId() + ",";
	        }
	        pigments = pigments.substring(0, pigments.length()-1);
        }
		Statement stmt;
		try{
			stmt = c.createStatement();
			String sql = "UPDATE HERB "
					   + "SET NAME = '" + name + "', " 
					   + "PIGMENTS = '" + pigments + "' "
					   + "WHERE ID = " + h.getId() + ";";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void deleteHerb(Herb h, boolean removeAccociated) {
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		
		Statement stmt;
		
		try{
			stmt = c.createStatement();
			String sql = "DELETE FROM HERB WHERE ID = " + h.getId();
			stmt.executeUpdate(sql);
			stmt.close();
			if(removeAccociated){
				ArrayList<Pigment> pigs = h.getPigments();
		        if(pigs.size() > 0){
			        for(Pigment p : pigs){
			        	DBPigment dbPig = new DBPigment();
			        	dbPig.deletePigment(p);
			        }
		        }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Herb getHerb(int id) {
		DBConnection dbCon = DBConnection.getInstance();
		Connection c = dbCon.getConnection();
		Herb herb = null;
		
		Statement stmt;
		
		try {
			stmt = c.createStatement();
			String sql = "SELECT * FROM HERB " + 
						  "WHERE ID = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				System.out.println("Rs.next ok");
				herb = new Herb(rs.getString("NAME"));
				herb.setId(rs.getInt("ID"));
				String pigments = rs.getString("PIGMENTS");
				if(!pigments.isEmpty()){
					System.out.println("Pigments ok");
					String[] pigsArr = pigments.split(",");
					DBPigment dbPig = new DBPigment(); 
					for(String s : pigsArr){
						Pigment p = dbPig.getPigment(Integer.parseInt(s));
						herb.addPigment(p);
					}
				}
				
			}
			
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return herb;
	}

}
