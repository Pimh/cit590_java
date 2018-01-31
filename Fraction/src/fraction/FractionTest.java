package fraction;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FractionTest {
	Fraction fraction1;
	Fraction fraction2;
	Fraction fraction3;
	Fraction fraction4;
	
	@Before
	public void setUp() throws Exception {
		fraction1 = new Fraction(8, -12);
		fraction2 = new Fraction(3);
		fraction3 = new Fraction("-3");
		fraction4 = new Fraction(" 8 / -12 ");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAdd() {
		Fraction calSum = fraction1.add(fraction2); 
		Fraction expectedSum = new Fraction(7,3);
		assertEquals(expectedSum, calSum);
	}
	
	@Test
	public void testSubtract() {
		Fraction calSum = fraction1.subtract(fraction2); 
		Fraction expectedSum = new Fraction(11,-3);
		assertEquals(expectedSum, calSum);
	}
	
	@Test
	public void testMultiply() {
		Fraction calSum = fraction1.multiply(fraction3); 
		Fraction expectedSum = new Fraction(2);
		assertEquals(expectedSum, calSum);
	}
	
	@Test
	public void testDivide() {
		Fraction calSum = fraction1.divide(fraction3); 
		Fraction expectedSum = new Fraction(2,9);
		assertEquals(expectedSum, calSum);
	}
	
	@Test(expected=ArithmeticException.class)
	public void testDivideByZero() {
		new Fraction(" 8 / 0");
	}
	
	@Test
	public void testAbs() {
		Fraction calSum = fraction1.abs(); 
		Fraction expectedSum = new Fraction(2,3);
		assertEquals(expectedSum, calSum);
		
		Fraction calSum2 = fraction2.abs(); 
		Fraction expectedSum2 = new Fraction(3,1);
		assertEquals(expectedSum2, calSum2);
	}
	
	@Test
	public void testNegate() {
		Fraction calSum = fraction1.negate(); 
		Fraction expectedSum = new Fraction(2,3);
		assertEquals(expectedSum, calSum);
		
		Fraction calSum2 = fraction2.negate(); 
		Fraction expectedSum2 = new Fraction(3,-1);
		assertEquals(expectedSum2, calSum2);
	}
	
	public void testInverse() {
		Fraction calSum = fraction1.inverse(); 
		Fraction expectedSum = new Fraction(3,-2);
		assertEquals(expectedSum, calSum);
		
		Fraction calSum2 = fraction2.inverse(); 
		Fraction expectedSum2 = new Fraction(1,3);
		assertEquals(expectedSum2, calSum2);
	}
	
	@Test
	public void testEquals() {
		Fraction f = new Fraction(-2, 3);
		assertEquals(fraction1, fraction4); 
		assertEquals(fraction1, f);
		assertNotEquals(fraction1, fraction2);
		assertNotEquals(fraction2, 3);
	}
	
	@Test
	public void testCompareTo() {
		assertTrue(fraction1.compareTo(fraction2) < 0);
		assertTrue(fraction1.compareTo(fraction3) > 0);
		assertTrue(fraction1.compareTo(fraction4) == 0);
		assertTrue(fraction1.compareTo(1) < 0);
	}
	
	@Test(expected=ClassCastException.class)
	public void testCompareToIncorrectClass() {
		fraction1.compareTo("2/3"); 
	}
	
	@Test
	public void testToString() {
		String fracStr1 = fraction1.toString();
		assertTrue("-2/3".compareTo(fracStr1) == 0);
		String fracStr2 = fraction2.toString();
		assertTrue("3".compareTo(fracStr2) == 0);
		String fracStr4 = fraction4.toString();
		assertTrue("-2/3".compareTo(fracStr4) == 0);
		assertFalse("-2/3".compareTo(fracStr2) == 0);
	}

}
