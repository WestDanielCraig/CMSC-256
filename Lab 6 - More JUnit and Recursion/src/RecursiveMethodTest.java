import static org.junit.Assert.*;

import org.junit.Test;

/*
*	Programmer #1:
*	Programmer #2:
*/
public class RecursiveMethodTest {

	@Test
	public final void testBuildStringOfCharacters1() {
		assertEquals(null, "@@@", RecursiveMethods.buildStringOfCharacters('@', 3));
	}

	@Test
	public final void testBuildStringOfCharacters2() {
		assertEquals(null, "???????", RecursiveMethods.buildStringOfCharacters('?', 7));
	}

	@Test
	public final void testBuildStringOfCharacters3() {
		assertEquals(null, "", RecursiveMethods.buildStringOfCharacters('%', 0));
	}

	@Test
	public final void testReverseNumArray1() {
		int[] input = { 1, 2, 3 };
		int[] expectedOutput = { 3, 2, 1 };
		int[] actualOutput = RecursiveMethods.reverseNumArray(input, 0, input.length - 1);
		assertArrayEquals(expectedOutput, actualOutput);
	}

	@Test
	public final void testReverseNumArray2() {
		int[] input = { 1, 2, 2 };
		int[] expectedOutput = { 2, 2, 1 };
		int[] actualOutput = RecursiveMethods.reverseNumArray(input, 0, input.length - 1);
		assertArrayEquals(expectedOutput, actualOutput);
	}

	@Test
	public final void testReverseNumArray3() {
		int[] input = { 1, 0, 1 };
		int[] expectedOutput = { 1, 0, 1 };
		int[] actualOutput = RecursiveMethods.reverseNumArray(input, 0, input.length - 1);
		assertArrayEquals(expectedOutput, actualOutput);
	}

	@Test
	public final void testIsSmallestToLargest1() {
		int[] input = { 1, 2, 3 };
		assertTrue(RecursiveMethods.isSmallestToLargest(input, 0));
	}

	@Test
	public final void testIsSmallestToLargest2() {
		int[] input = { 1, 2, 2 };
		assertTrue(RecursiveMethods.isSmallestToLargest(input, 0));
	}

	@Test
	public final void testIsSmallestToLargest3() {
		int[] input = { 1, 0, 1 };
		assertFalse(RecursiveMethods.isSmallestToLargest(input, 0));
	}

	@Test
	public final void testIsSmallestToLargest4() {
		int[] input = { 3, 3, 3 };
		assertTrue(RecursiveMethods.isSmallestToLargest(input, 0));
	}

	@Test
	public final void testIsPalindrome1() {
		String str = "madam";
		assertTrue(RecursiveMethods.isPalindrome(str, 0, str.length() - 1));
	}

	@Test
	public final void testIsPalindrome2() {
		String str = "naan";
		assertTrue(RecursiveMethods.isPalindrome(str, 0, str.length() - 1));
	}

	@Test
	public final void testIsPalindrome3() {
		String str = "nope";
		assertFalse(RecursiveMethods.isPalindrome(str, 0, str.length() - 1));
	}

	@Test
	public final void testIsPalindrome4() {
		String str = "a";
		assertTrue(RecursiveMethods.isPalindrome(str, 0, str.length() - 1));
	}

	@Test
	public final void testIsPalindrome5() {
		String str = "apple";
		assertFalse(RecursiveMethods.isPalindrome(str, 0, str.length() - 1));
	}
}
