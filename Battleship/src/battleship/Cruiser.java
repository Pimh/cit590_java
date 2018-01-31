package battleship;

public class Cruiser extends Ship {
	
	public Cruiser() {
		this.length = 3;
		this.hit[0] = false; 
		this.hit[1] = false; 
		this.hit[2] = false;
	}
	
	/**
	 * Gets the length of the ship.
	 * @return the length of the Cruiser.
	 */
	@Override
	public int getLength() {
		return this.length;
	}
	
	/**
	 * Gets the type of the ship.
	 * @return "cruiser".
	 */
	@Override
	public String getShipType() {
		return "cruiser";
	}
}
