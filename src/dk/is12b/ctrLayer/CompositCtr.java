package dk.is12b.ctrLayer;

import java.util.ArrayList;

import dk.is12b.dbLayer.DBComposit;
import dk.is12b.modelLayer.Composit;
import dk.is12b.modelLayer.Ink;
import dk.is12b.modelLayer.Pigment;

public class CompositCtr {
	DBComposit dbComp;
	
	public CompositCtr(){
		dbComp = new DBComposit();
	}
	
	public void insertComposit(Ink i, Pigment p, int amount){
		dbComp.insertComposit(i, p, amount);
	}
	
	public void updateComposit(Ink i, Pigment p, int amount){
		dbComp.updateComposit(i, p, amount);
	}
	
	public void deleteComposit(Ink i, Pigment p){
		dbComp.deleteComposit(i, p);
	}
	
	public Composit getComposit(Ink i, Pigment p){
		return dbComp.getComposit(i, p);
	}
	
	public ArrayList<Composit> getCompositListByPigment(Pigment p){
		return dbComp.getCompositsByPigment(p);
	}

}
