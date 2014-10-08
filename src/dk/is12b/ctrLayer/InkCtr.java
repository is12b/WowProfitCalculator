package dk.is12b.ctrLayer;

import java.util.ArrayList;

import dk.is12b.dbLayer.DBComposit;
import dk.is12b.dbLayer.DBInk;
import dk.is12b.modelLayer.Ink;

public class InkCtr {
	DBInk dbInk;
	
	public InkCtr(){
		dbInk = new DBInk();
	}
	
	public Ink insertInk(String name){
		Ink i = new Ink(name);
		dbInk.insertInk(i);
		
		return i;
	}
	
	public ArrayList<Ink> getAllInks(){
		return dbInk.getAllInk();
	}
	
	public void updateInk(String name, Ink i){
		i.setName(name);
		dbInk.updateInk(i);
	}
	
	public void deleteInk(Ink i){
		dbInk.deleteInk(i);
	}
	
	public Ink getInk(int id){
		return dbInk.getInk(id);
	}

}
