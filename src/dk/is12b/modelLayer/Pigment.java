package dk.is12b.modelLayer;

import java.util.ArrayList;
import java.util.Random;

public class Pigment {
	private int id;
	private String name;
	private int chanceTo;
	private int chanceOff;
	private int min;
	private int percent;
	private double per;
	
	public Pigment(String name, int chanceTo, int chanceOff, int min, int percent){
		this.name = name;
		this.chanceTo = chanceTo;
		this.chanceOff = chanceOff;
		this.min = min;
		this.percent = percent;
		this.per = (percent / 100);
	}
	
	private int getChanceRand(){
		int ret = 0;
		if(getChancePercent()){
			Random rand = new Random();
			int randOf = rand.nextInt(chanceOff) + 1;
			if(chanceTo >= randOf){
				ret = randOf;
			}
		}
		return ret;
	}
	
	public int getAmount(){
		return min + getChanceRand();
	}
	
	private boolean getChancePercent(){
		boolean ret = false;
		
		double d = Math.random();
		if(percent == 1){
			ret = true;
		} else if (d < percent){
		    ret = true;
		}
		
		return ret;
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

	/**
	 * @return the chanceTo
	 */
	public int getChanceTo() {
		return chanceTo;
	}

	/**
	 * @param chanceTo the chanceTo to set
	 */
	public void setChanceTo(int chanceTo) {
		this.chanceTo = chanceTo;
	}

	/**
	 * @return the chanceOff
	 */
	public int getChanceOff() {
		return chanceOff;
	}

	/**
	 * @param chanceOff the chanceOff to set
	 */
	public void setChanceOff(int chanceOff) {
		this.chanceOff = chanceOff;
	}

	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
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
	 * @return the percent
	 */
	public int getPercent() {
		return percent;
	}

	/**
	 * @param percent the percent to set
	 */
	public void setPercent(int percent) {
		this.percent = percent;
	}	

	/**
	 * @return the per
	 */
	public double getPer() {
		return per;
	}

	/**
	 * @param per the per to set
	 */
	public void setPer(double per) {
		this.per = per;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

		
	
}
