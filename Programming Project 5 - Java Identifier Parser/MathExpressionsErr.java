import java.util.Scanner;

// This is a comment line
public class MathExpressionsErr {
;
	public static void main(String[] args) {
		double 9invalid = 3.14;
               int $a = -10;
               int $b = +10;
               String alkdfj_sldkjf = "whatever";  //this is valid in Java
               boolean is$not = false;
               int one = 9;
               int two = 10;
               int sum = one+two;
               char thing = 'a';
             
		Scanner in = new Scanner(System.in);  // Scanner for keyboard input

		System.out.print("Enter the temperature in Celsius:  ");
		
		int celsiusTemp = in.nextInt();  
		/* these are comments
		 * that go on
		 * and on
		 */
		double fahrenheitTemp = (double)9/(double)5*(double)celsiusTemp + (double)32;
		
		System.out.print("\nYou entered a Celsius Temperature of " + celsiusTemp + " degrees.");
		System.out.print("\n\nThat is equivalent to " + fahrenheitTemp + " degrees Fahrenheit.");

	}

}
