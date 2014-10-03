package dk.is12b.modelLayer;

import java.util.ArrayList;

public class Herb {
	private String name;
	private ArrayList<Pigment> pigments;
	
	public Herb(String name){
		this.name = name;
		pigments = new ArrayList<Pigment>();
	}
	
	public int getRes(int amount, String pigment){
		Pigment p = getPigment(pigment);
		int ret = -1;
		if(p != null){
			ret = 0;
			int i = amount / 5;
			int inc = 0;
			while(inc < i){
				ret += p.getAmount();
				
				inc++;
			}
		}
		
		return ret;
	}
	
	private Pigment getPigment(String pigment){
		int i = 0;
		Pigment p = null;
		boolean found = false;
		while(!found && i < pigments.size()){
			if(pigments.get(i).getName().equalsIgnoreCase(pigment)){
				p = pigments.get(i);
				found = true;
			}
			i++;
		}
		
		return p;		
	}
	
	public void addPigment(Pigment p){
		pigments.add(p);
	}
	
	public void removePigment(Pigment p){
		pigments.remove(p);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
