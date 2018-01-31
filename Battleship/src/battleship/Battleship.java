package battleship;

public class Battleship extends Ship {
	
	public Battleship() {
		this.length = 4;
		boolean[] hitArray = {false, false, false, false};
		this.hit = hitArray;
	}
	
	/**
	 * Gets the length of the ship.
	 * @return the length of the Battleship.
	 */
	@Override
	public int getLength() {
		return this.length;
	}
	
	/**
	 * Gets the ship type.
	 * @return "battleship"
	 */
	@Override
	public String getShipType() {
		return "battleship"; 
	}
	
}
