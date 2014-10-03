import dk.is12b.modelLayer.Herb;
import dk.is12b.modelLayer.Pigment;


public class Test {

	public static void main(String[] args){
		new Test();
	}
	
	public Test(){
		Pigment p = new Pigment("Shadow Pigment", 2, 4, 2, 100);
		Pigment p1 = new Pigment("Misty Pigment", 3, 3, 0, 50);
		
		Herb h = new Herb("Fool's Cap");
		
		h.addPigment(p);
		h.addPigment(p1);
		
		System.out.println(h.getName());
		System.out.println("Shadow Pigment: " + h.getRes(60, "Shadow Pigment"));
		System.out.println("Misty Pigment: " + h.getRes(60, "Misty Pigment"));
		
		//System.out.println(p.getChanceRand());
	}
}
