package dk.is12b.ctrLayer;

import java.util.ArrayList;

import dk.is12b.dbLayer.DBHerb;
import dk.is12b.dbLayer.DBPigment;
import dk.is12b.modelLayer.Herb;
import dk.is12b.modelLayer.Pigment;

public class HerbCtr {

private DBHerb dbHerb;
	
	public HerbCtr(){
		dbHerb = new DBHerb();
	}
	
	public Herb createHerb(String name){
		Herb h = new Herb(name);
		dbHerb.insertHerb(h);
		return h;
	}
	
	public ArrayList<Herb> getAllHerbs(){
		return dbHerb.getAllHerbs();
	}
	
	public void updateHerb(Herb h, String name){
		dbHerb.updateHerb(h, name);
	}
	
	public void deleteHerb(Herb h){
		dbHerb.deleteHerb(h);
	}

}
