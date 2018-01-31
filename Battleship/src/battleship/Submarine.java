package battleship;

public class Submarine extends Ship {

	public Submarine() {
		this.length = 1;
		this.hit[0] = false;
	}
	
	/**
	 * Gets the length of the ship.
	 * @return the length of the Submarine.
	 */
	@Override
	public int getLength() {
		return this.length;
	}
	
	/**
	 * Gets the type of the ship.
	 * @return "submarine".
	 */
	@Override
	public String getShipType() {
		return "submarine"; 
	}
	
	
}
