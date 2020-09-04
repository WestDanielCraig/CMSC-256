/*
*	Programmer #1:
*	Programmer #2:
*/

public class RecursiveMethods {
	/*
	 * @returns a String of character, ch. The length is determined by the second
	 * parameter, length.
	 */
	public static String buildStringOfCharacters(char ch, int length) {
		String str = "";
		if (length == 0)
			return "";
		else
			str = buildStringOfCharacters(ch, length - 1) + String.valueOf(ch);
		return str;

	}

	/*
	 * returns an int array that has the elements in reverse order of the parameter
	 * array, nums. Process this recursively beginning with the last element.
	 */
	public static int[] reverseNumArray(int[] nums, int firstIndex, int backIndex) {
		if (firstIndex < backIndex) {
			int temp = nums[firstIndex];
			nums[firstIndex] = nums[backIndex];
			nums[backIndex] = temp;
			reverseNumArray(nums, firstIndex + 1, backIndex - 1);
		}
		return nums;
	}

	/*
	 * returns true if the int array parameter is sorted from smallest to largest,
	 * false otherwise. Process this recursively beginning with the first element.
	 */
	public static boolean isSmallestToLargest(int[] values, int firstIndex) {
		if (firstIndex == values.length - 1)
			return true;
		if (values[firstIndex + 1] < values[firstIndex])
			return false;
		return isSmallestToLargest(values, firstIndex + 1);
	}

	/*
	 * @returns true if the parameter String, str is a palindrome false otherwise
	 */
	public static boolean isPalindrome(String str, int begin, int end) {
		if (begin == end)
			return true;
		if (str.charAt(begin) != str.charAt(end))
			return false;
		if (begin < end + 1)
			return isPalindrome(str, begin + 1, end - 1);
		return true;
	}
}
