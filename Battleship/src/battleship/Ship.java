package battleship;

public abstract class Ship {
	
	int bowRow;
	int bowColumn;
	int length; 
	boolean horizontal;
	boolean [] hit = new boolean[4]; 
	
	public abstract int getLength();
	
	/** 
	 * Gets the row number of the ship bow.
	 * @return The row number of the ship bow.
	 */
	public int getBowRow(){return this.bowRow;}
	
	/**
	 * Gets the column number of the ship bow.
	 * @return The column number of the ship bow.
	 */
	public int getBowColumn(){return this.bowColumn;}
	
	/**
	 * Identifies the orientation of the ship.
	 * @return true if the ship is horizontal, and false otherwise.
	 */
	public boolean isHorizontal(){return this.horizontal;}
	
	/**
	 * Sets the row number of the ship bow.
	 * @param row The row number of the ship bow.
	 */
	public void setBowRow(int row){this.bowRow = row;}
	
	/**
	 * Sets the column number of the ship bow.
	 * @param column The column number of the ship bow.
	 */
	public void setBowColumn(int column){this.bowColumn = column;}
	
	/**
	 * Sets the orientation of the ship.
	 * @param horizontal The orientation of the ship.
	 */
	public void setHorizontal(boolean horizontal){this.horizontal = horizontal;}
	
	public abstract String getShipType();
	
	/**
	 * Determines if it is legal to place the ship at the given location.
	 * @param row The row number of the ship bow at which the ship will be placed.
	 * @param column The column number of the ship bow at which the ship will be placed.
	 * @param horizontal The orientation of the ship to be placed.
	 * @param ocean The ocean in which the ship is placed.
	 * @return true if it is legal to place the ship at this location, and false otherwise.
	 */
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){
		if (horizontal) {
			for (int i = column; i < column + this.length; i++) {
				if (!isInBound(row, i)) return false;
				else {if (!isAdjacentToOtherShips(row, i, ocean)) return false;}
			}
			return true;
		} else {
			for (int i = row; i < row + this.length; i++) {
				if (!isInBound(i, column)) return false;
				else {if (!isAdjacentToOtherShips(i, column, ocean)) return false;}
			}
			return true;
		}
		
	}
	
	/**
	 * Determine if the given location is within the ocean boundary
	 * @param row The row number of the location
	 * @param column The column number of the location
	 * @return true if the location is within the boundary, and false otherwise.
	 */
	public boolean isInBound(int row, int column){
		if ((row >=0 ) && (row <= 9) && (column >= 0) && (column <= 9)) return true;
		else return false; 
	}
	
	/**
	 * Determines if there is a ship immediately adjacent to the current location.
	 * @param row The row number of the current location.
	 * @param column The column number of the current location.
	 * @param ocean The ocean to which the current location is referred.
	 * @return true if there is any ship adjacent to the current location, and false
	 * otherwise. 
	 */
	private boolean isAdjacentToOtherShips(int row, int column, Ocean ocean){
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = column - 1; j <= column +1; j++) {
				if (isInBound(i, j)) {
					if (!isCurrentSpotEmpty(i, j, ocean)) return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Determines if the current location is empty.
	 * @param row The row number of the current location.
	 * @param column The column number of the current location.
	 * @param ocean The ocean to which the current location is referred.
	 * @return true if the current location is empty, and false otherwise.
	 */
	private boolean isCurrentSpotEmpty(int row, int column, Ocean ocean){
		Ship oceanContent = ocean.getShipArray()[row][column];
		if (oceanContent instanceof EmptySea) return true;
		else return false;
	}
	
	/**
	 * Puts the ship the given location.
	 * @param row The row number of the ship bow.
	 * @param column The column number of the ship bow.
	 * @param horizontal The orientation of the ship.
	 * @param ocean The ocean in which the ship is placed.
	 */
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean){
		if (okToPlaceShipAt(row, column, horizontal, ocean)) {
			setBowRow(row);
			setBowColumn(column); 
			setHorizontal(horizontal);

			// Assign a reference to the ship in the ocean
			if (horizontal) {
				for (int i = column; i < column + this.length; i++) {
					ocean.ships[row][i] = this; 
				}
			} else {
				for (int i = row; i < row + this.length; i++) {
					ocean.ships[i][column] = this; 
				}
			}	
		}
	}
	
	/**
	 * Fires a shot at a given location.
	 * @param row The row number of the location being shot.
	 * @param column The column number of the location being shot.
	 * @return true if there is a ship afloat at the given location, and false otherwise.
	 */
	public boolean shootAt(int row, int column) {
		// Does this ship occupy this row & column?
		if (!isSunk()) {
			if (isHorizontal()) this.hit[column - getBowColumn()] = true;
			else this.hit[row - getBowRow()] = true;
			return true;
		} else return false; 
	}
	
	/**
	 * Determines if the ship has been sunk.
	 * @return true if the ship has been sunk, and false otherwise.
	 */
	public boolean isSunk() {
		int nHit = 0;
		for(int i = 0; i < this.hit.length; i++) {
			if (this.hit[i]) nHit++;
		}
		
		if (nHit == this.length) return true;
		else return false;
	}
	
	/** 
	 * Returns a string corresponding to the status of the ship
	 * @return "S" if the ship is shot, but still afloat, and "x" if the ship
	 * is sunk. 
	 */
	@Override
	public String toString(){
		if (isSunk()) return "x";
		else return "S";
	}
}
