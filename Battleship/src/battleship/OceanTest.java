package battleship;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OceanTest {
	Ocean ocean;
	Ocean ocean2;
	
	@Before
	public void setUp() throws Exception {
		ocean = new Ocean();
		ocean2 = new Ocean();
		Ship destroyer = new Destroyer();
		destroyer.placeShipAt( 2, 0, true, ocean2);
		Ship cruiser = new Cruiser();
		cruiser.placeShipAt(7, 3, false, ocean2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPlaceAllShipsRandomly() {
		ocean.placeAllShipsRandomly();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Ship ship = ocean.ships[i][j];
				
				// Check if the placements are legal
				for (int row = i - 1; row <= i + 1; row++) {
					for (int column = j - 1; column <= j; column++) {
						if (ship.isInBound(i, j)) {
							assertTrue(ship.equals(ocean.ships[i][j]) 
									|| (ocean.ships[i][j] instanceof EmptySea));
						}
					}
				}
				
				
			}
		}
		
	}
	
	@Test
	public void testIsOccupied() {
		assertTrue(ocean2.isOccupied(2, 0));
		assertTrue(ocean2.isOccupied(2, 1));
		assertFalse(ocean2.isOccupied(2, 4));
	}
	
	@Test
	public void testShootAt() {
		assertFalse(ocean2.shootAt(5, 0));
		assertEquals(ocean2.hitCount,0);
		assertTrue(ocean2.shootAt(2, 0));
		assertEquals(ocean2.hitCount,1);
		assertFalse(ocean2.ships[2][0].isSunk());
		assertTrue(ocean2.shootAt(2, 0));
		assertEquals(ocean2.hitCount,2);
		assertTrue(ocean2.shootAt(2, 1));
		assertEquals(ocean2.hitCount,3);
		assertTrue(ocean2.ships[2][0].hit[0]);
		assertTrue(ocean2.ships[2][1].hit[1]);
		assertTrue(ocean2.ships[2][0].isSunk());
		assertFalse(ocean2.shootAt(2, 0));
		assertEquals(ocean2.shotsFired,5);
	}
	
	@Test
	public void testGetShotFired() {
		assertEquals(0, ocean2.getShotsFired());
		ocean2.shootAt(2, 0);
		assertEquals(1, ocean2.getShotsFired());
	}
	
	@Test
	public void testGetHitCount() {
		assertFalse(ocean2.shootAt(5, 0));
		assertEquals(ocean2.getHitCount(),0);
		assertTrue(ocean2.shootAt(2, 0));
		assertEquals(ocean2.getHitCount(),1);
	}
	
	@Test
	public void testIsGameOver() {
		ocean2.shootAt(2, 0);
		ocean2.shootAt(2, 1);
		assertFalse(ocean2.isGameOver());
		ocean2.shootAt(7, 3);
		ocean2.shootAt(8, 3);
		ocean2.shootAt(9, 3);
		assertTrue(ocean2.isGameOver());
	}
	
	@Test
	public void testGetShipArray() {
		Ship[][] ships = ocean2.getShipArray();
		assertTrue(ships[0][0] instanceof EmptySea); 
		assertTrue(ships[2][1] instanceof Destroyer); 
		assertTrue(ships[8][3] instanceof Cruiser); 
	}
}
