import dk.is12b.ctrLayer.PigmentCtr;
import dk.is12b.dbLayer.DBConnection;
import dk.is12b.dbLayer.DBHerb;
import dk.is12b.modelLayer.Herb;
import dk.is12b.modelLayer.Pigment;


public class Test {

	public static void main(String[] args){
		new Test();
	}
	
	public Test(){
		DBHerb db = new DBHerb();
		
		for(Herb h : db.getAllHerbs()){
			System.out.println(h.getName());
		}
	}
}
