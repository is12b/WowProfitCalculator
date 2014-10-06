package dk.is12b.modelLayer;

import java.util.HashMap;

public class Ink {
	private int id;
	private String name;
	private HashMap<Integer, Integer> pigments;
	
	public Ink(String name){
		this.name = name;
		pigments = new HashMap<Integer, Integer>();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
