package battleship;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShipTest {
	Ship cruiser;
	Ship destroyer;
	Ship battleship;
	Ship submarine;
	Ship emptysea;
	Ocean ocean;
	
	@Before
	public void setUp() throws Exception {
		ocean = new Ocean();
		cruiser = new Cruiser(); 
		cruiser.placeShipAt(7, 3, false, ocean);
		destroyer = new Destroyer();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetLength() {
		assertEquals(cruiser.getLength(), 3);
	}
	
	@Test
	public void testGetBowRow() {
		assertEquals(cruiser.getBowRow(), 7);
	}
	
	@Test
	public void testGetBowColumn() {
		assertEquals(cruiser.getBowColumn(), 3);
	}
	
	@Test
	public void testIsHorizontal() {
		assertFalse(cruiser.isHorizontal());
	}
	
	@Test
	public void testSetBowRow() {
		destroyer.setBowRow(0);
		assertEquals(0, destroyer.getBowRow());
	}
	
	@Test
	public void testSetBowColumn() {
		destroyer.setBowColumn(1);
		assertEquals(1, destroyer.getBowColumn());
	}
	
	@Test
	public void testSetHorizontal() {
		destroyer.setHorizontal(true);
		assertEquals(true, destroyer.isHorizontal());
	}
	
	@Test
	public void testOkToPlaceShipAt() {
		battleship = new Battleship();
		assertFalse(battleship.okToPlaceShipAt(3, 4, false, ocean));
		assertFalse(battleship.okToPlaceShipAt(6, 2, false, ocean));
		assertFalse(battleship.okToPlaceShipAt(2, 7, true, ocean));
		assertFalse(battleship.okToPlaceShipAt(7, 7, false, ocean));
		assertTrue(battleship.okToPlaceShipAt(7, 5, true, ocean));
	}
	
	@Test
	public void testPlaceShipAt() {
		battleship = new Battleship();
		battleship.placeShipAt(2, 4, true, ocean);
		assertTrue(ocean.getShipArray()[2][4] instanceof Battleship);
		assertEquals(ocean.getShipArray()[2][4], ocean.getShipArray()[2][5]);
		assertEquals(ocean.getShipArray()[2][4], ocean.getShipArray()[2][6]);
		assertEquals(ocean.getShipArray()[2][4], ocean.getShipArray()[2][7]);
		assertNotEquals(ocean.getShipArray()[2][4], ocean.getShipArray()[2][8]);
	}
	
	@Test
	public void testShootAt() {
		battleship = new Battleship();
		battleship.placeShipAt(2, 4, true, ocean);
		assertFalse(battleship.hit[1]);
		assertTrue(battleship.shootAt(2, 5));
		assertTrue(battleship.hit[1]);
		assertTrue(battleship.shootAt(2, 7));
		assertTrue(battleship.hit[3]);
		assertTrue(battleship.shootAt(2, 4));
		assertTrue(battleship.shootAt(2, 6));
		assertFalse(battleship.shootAt(2, 6));
		
		emptysea = new EmptySea();
		assertFalse(emptysea.shootAt(0, 0));
	}
	
	@Test
	public void testIsSunk() {
		battleship = new Battleship();
		battleship.placeShipAt(2, 4, true, ocean);
		battleship.shootAt(2, 4);
		battleship.shootAt(2, 5);
		assertFalse(battleship.isSunk());
		battleship.shootAt(2, 6);
		battleship.shootAt(2, 7);
		assertTrue(battleship.isSunk());
		
		submarine = new Submarine();
		submarine.placeShipAt(9, 7, true, ocean);
		assertFalse(submarine.isSunk());
		submarine.shootAt(9, 7);
		assertTrue(submarine.isSunk());
		
		ocean.shootAt(0,9);
		assertFalse(ocean.ships[0][9].isSunk());
	}
	
	@Test
	public void testToString() {
		battleship = new Battleship();
		battleship.placeShipAt(2, 4, true, ocean);
		battleship.shootAt(2, 4);
		assertEquals(battleship.toString(), "S");
		battleship.shootAt(2, 5);
		battleship.shootAt(2, 6);
		battleship.shootAt(2, 7);
		assertEquals(battleship.toString(), "x");
		
		assertEquals(ocean.ships[0][9].toString(), "-");
	}
	
	@Test
	public void testGetShipType() {
		battleship = new Battleship();
		submarine = new Submarine();
		emptysea = new EmptySea();
		assertEquals(battleship.getShipType(), "battleship");
		assertEquals(cruiser.getShipType(), "cruiser");
		assertEquals(destroyer.getShipType(), "destroyer");
		assertEquals(submarine.getShipType(), "submarine");
		assertEquals(emptysea.getShipType(), "empty sea");
	}
}
