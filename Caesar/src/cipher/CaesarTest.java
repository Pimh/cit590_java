package cipher;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CaesarTest {
	
	Caesar caesar; 

	@Before
	public void setUp() throws Exception {
		caesar = new Caesar(); 
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testShiftLetter() {
		assertEquals('C', caesar.shiftLetter(2,'A'));
		assertEquals('A', caesar.shiftLetter(2,'Y'));
		assertEquals('Z', caesar.shiftLetter(2,'X'));
		assertEquals('c', caesar.shiftLetter(2,'a'));
		assertEquals('b', caesar.shiftLetter(2,'z'));
	}
	
	@Test
	public void testDeShiftLetter() {
		assertEquals('A', caesar.deShiftLetter(2,'C'));
		assertEquals('Y', caesar.deShiftLetter(2,'A'));
		assertEquals('X', caesar.deShiftLetter(2,'Z'));
		assertEquals('a', caesar.deShiftLetter(2,'c'));
		assertEquals('z', caesar.deShiftLetter(2,'b'));
	}
	
	@Test
	public void testShiftString() {
		String msg = "Jcrra Vjcpmuikxkpi! :)"; 
		assertEquals("Happy Thanksgiving! :)", caesar.shiftString(2, msg));
	}
	
	@Test
	public void testEncipher() {
		String msg = "Happy Thanksgiving! :)"; 
		assertEquals("Jcrra Vjcpmuikxkpi! :)", caesar.encipher(2, msg));
	}
	
	@Test
	public void testDecipher() {
		String msg = "This thanksgiving meal is delicious!";
		String encText = caesar.encipher(25, msg);
		assertEquals(msg, caesar.decipher(encText));
		
		String msg2 = "OMG, the homework is due tomorrow :(";
		String encText2 = caesar.encipher(10, msg2);
		assertEquals(msg2, caesar.decipher(encText2));
	}
	
	@Test
	public void testIsWord() {
		Set<String> dictionary = caesar.createDictionary();
		assertTrue(caesar.isWord("Delicious", dictionary));
		assertFalse(caesar.isWord("xyabc", dictionary));
	}

}
