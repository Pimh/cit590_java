package battleship;

public class Destroyer extends Ship {
	
	public Destroyer() {
		this.length = 2;
		this.hit[0] = false;
		this.hit[1] = false;
	}
	
	/**
	 * Gets the length of the ship.
	 * @return the length of the Destroyer.
	 */
	@Override
	public int getLength() {
		return this.length;
	}
	
	/**
	 * Gets the type of the ship.
	 * @return "destroyer".
	 */
	@Override
	public String getShipType() {
		return "destroyer";
	}
	
	
}
