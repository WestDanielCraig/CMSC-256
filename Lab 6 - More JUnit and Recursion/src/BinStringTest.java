import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinStringTest {

	@Test
	public void testConvert() {

	}

	@Test
	public void testSum() {
		int expected = 99;
		assertEquals(expected, BinString.sum("12"));
	}
	
	@Test
	public void testSum1() {
		int expected = 49;
		assertEquals(expected, BinString.sum("1"));
	}

	@Test
	public void testDecimalToBinary() {
		String expected = "10000";
		assertEquals(expected, BinString.decimalToBinary(16));
	}

	@Test
	public void testDecimalToBinary1() {
		String expected = "1111111111111111";
		assertEquals(expected, BinString.decimalToBinary(65535));
	}

	@Test
	public void testBinaryToDecimal() {
		int expected = 0;
		assertEquals(expected, BinString.binaryToDecimal("0000000000000"));
	}

	@Test
	public void testBinaryToDecimal1() {
		int expected = 15;
		assertEquals(expected, BinString.binaryToDecimal("0000000000001111"));
	}

}
