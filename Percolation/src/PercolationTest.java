import static org.junit.Assert.*;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PercolationTest {
	
	Percolation percolation;
	
	@Before
	public void setUp() {
		percolation = new Percolation();
	}
	
	@Test
	public void testGround() {
		int n = 100;
		double p = 0.5; 
		int[][] ground = percolation.ground(n, p);
		
		// test if ground only contains 0 or 1
		for (int i = 0; i < ground.length; i++) {
			for (int j = 0; j < ground.length; j++) {
				assertTrue((ground[i][j] == 0) || (ground[i][j] == 1));
			}
		}
		
		// test if number of 1s is close to the probability p	
		double sum = 0;
		for (int[] elem : ground) {
			sum = sum + IntStream.of(elem).sum();
		}
		double tot = n*n;
		double calP = sum/tot;		
		assertEquals(p, calP, 0.02);
	}
	
	@Test
	public void testSeep() {
		int[][] ground = {{0, 0, 1, 1, 0}, {1, 0, 1, 1, 1}, {0, 0, 0, 0, 1},
				{1, 1, 1, 0, 1}, {0, 0, 1, 0, 0}};
		
		// Test seep at row 0 
		percolation.seep(ground, 0);
		int[] expected = {2, 2, 1, 1, 2};
		assertArrayEquals(expected, ground[0]);
		
		// Test seep at row n 
		percolation.seep(ground, 1);
		percolation.seep(ground, 2);
		int[] expectedRow0 = {2, 2, 1, 1, 2};
		int[] expectedRow1 = {1, 2, 1, 1, 1};
		int[] expectedRow2 = {2, 2, 2, 2, 1};
		assertArrayEquals(expectedRow0, ground[0]);
		assertArrayEquals(expectedRow1, ground[1]);
		assertArrayEquals(expectedRow2, ground[2]);
	}
	
	@Test
	public void testSeppThroughGround() {
		int[][] ground = {{0, 0, 1, 1, 0}, {1, 0, 1, 1, 1}, {0, 0, 0, 0, 1},
				{1, 1, 1, 0, 1}, {0, 0, 1, 0, 0}};
		int[][] expectedGround = {{2, 2, 1, 1, 2}, {1, 2, 1, 1, 1}, 
				{2, 2, 2, 2, 1}, {1, 1, 1, 2, 1}, {0, 0, 1, 2, 2}};
		percolation.seepThroughGround(ground);
		assertArrayEquals(expectedGround, ground);
	}
	
	@Test
	public void testPercolate() {
		int[][] ground = {{0, 0, 1, 1, 0}, {1, 0, 1, 1, 1}, {0, 0, 0, 0, 1},
				{1, 1, 1, 0, 1}, {0, 0, 1, 0, 0}};
		assertTrue(percolation.percolate(ground));		
		
		int[][] ground2 = {{0, 0, 1, 1, 0}, {1, 0, 1, 1, 1}, {0, 0, 0, 0, 1},
				{1, 1, 1, 0, 1}, {0, 0, 1, 1, 0}};
		assertFalse(percolation.percolate(ground2));	
	}
	
	@After
	public void tearDown() { }

}
