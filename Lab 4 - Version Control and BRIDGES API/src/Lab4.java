import java.util.Arrays;
import java.util.Collections;

public class Lab4 {

	public static void main(String args[]) {

		Integer[] array1 = { 37, 14, 78, 90 };
		Integer[] array2 = { 1, 2, 3, 4 };
		Integer[] array3 = { 0, 0, 0, 1 };
		Integer[] array4 = { 3, 4, 5, 6 };
		Integer[] array5 = { 1, 2, 3, 4 };

		try {
			Integer[] x1 = sorting(array1);
			Integer[] x2 = sorting(array2);
			Integer[] x3 = sorting(array3);
			Integer[] x4 = sorting(array4);
			Integer[] x5 = sorting(array5);

			for (int ele : x1) {
				System.out.print(ele + " ");
			}
			System.out.println();

			for (int ele : x2) {
				System.out.print(ele + " ");
			}
			System.out.println();

			for (int ele : x3) {
				System.out.print(ele + " ");
			}
			System.out.println();

			for (int ele : x4) {
				System.out.print(ele + " ");
			}
			System.out.println();

			for (int ele : x5) {
				System.out.print(ele + " ");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	public static Integer[] sorting(Integer[] arrIn) {

		Integer[] newArry = Arrays.copyOf(arrIn, arrIn.length);

		if (arrIn.length == 0) {
			throw new NullPointerException("Array does not hold any values");

		} else {
			Arrays.sort(newArry, Collections.reverseOrder());
		}
		return newArry;

	}

}
