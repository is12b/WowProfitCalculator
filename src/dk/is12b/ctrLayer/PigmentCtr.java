package dk.is12b.ctrLayer;

import java.util.ArrayList;

import dk.is12b.dbLayer.DBPigment;
import dk.is12b.modelLayer.Pigment;

public class PigmentCtr {
	private DBPigment dbPigment;
	
	public PigmentCtr(){
		dbPigment = new DBPigment();
	}
	
	public Pigment createPigment(String name, int chanceTo, int chanceOff, int min, int percent){
		Pigment p = new Pigment(name, chanceTo, chanceOff, min, percent);
		dbPigment.insertPigment(p);
		return p;
	}
	
	public ArrayList<Pigment> getAllPigments(){
		return dbPigment.getAllPigments();
	}
	
	public void updatePigment(Pigment p, String name, int chanceTo, int chanceOff, int min, int percent){
		dbPigment.updatePigment(p, name, chanceTo, chanceOff, min, percent);
		p.setName(name);
		p.setChanceTo(chanceTo);
		p.setChanceOff(chanceOff);
		p.setMin(min);
		p.setPercent(percent);
	}
	
	public void deletePigment(Pigment p){
		dbPigment.deletePigment(p);
	}

}
