package dk.is12b.ctrLayer;

import java.util.ArrayList;

import dk.is12b.dbLayer.DBPigment;
import dk.is12b.modelLayer.Pigment;

public class PigmentCtr {
	private DBPigment dbPigment;
	
	public PigmentCtr(){
		dbPigment = new DBPigment();
	}
	
	public void createPigment(String name, int chanceTo, int chanceOff, int min, double percent){
		Pigment p = new Pigment(name, chanceTo, chanceOff, min, percent);
		dbPigment.insertPigment(p);
	}
	
	public ArrayList<Pigment> getAllPigments(){
		return dbPigment.getAllPigments();
	}
	
	public void updatePigment(Pigment p){
		dbPigment.updatePigment(p);
	}
	
	public void deletePigment(Pigment p){
		dbPigment.deletePigment(p);
	}

}
