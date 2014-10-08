package dk.is12b.modelLayer;

public class Composit {
	private int amount;
	private Ink ink;
	private Pigment pigment;
	
	public Composit(){
		
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the ink
	 */
	public Ink getInk() {
		return ink;
	}

	/**
	 * @param ink the ink to set
	 */
	public void setInk(Ink ink) {
		this.ink = ink;
	}

	/**
	 * @return the pigment
	 */
	public Pigment getPigment() {
		return pigment;
	}

	/**
	 * @param pigment the pigment to set
	 */
	public void setPigment(Pigment pigment) {
		this.pigment = pigment;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return amount + " x " + ink.getName();
	}

	
	

}
