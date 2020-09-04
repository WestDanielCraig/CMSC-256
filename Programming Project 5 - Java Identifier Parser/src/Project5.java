import AVLTreePackage.AVLTree;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;
import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

/*
 * Project5 first creates a an AVL tree from a keyword list .txt file then
 * 	reads in a .java source code file and picks out all the java keywords
 * 	that were from the tree that was just made and also gets rid of all the 
 * 	comments and string literals in the source code leaving only the valid
 * 	identifiers and displays them and how many times they are used. It also 
 * 	picks out all the invalid identifiers and displays them too.
 * 
 * @author Daniel C. West
 * @version 1.0
 * @since 2018-12-03
 */
public class Project5 {

	public static void main(String[] args) {

		printHeading(); // Prints out class information

		Bridges bridges = new Bridges(3, "dweststi", "671266808684"); // login to bridges account to see visual image
		AVLTree<String> keywordTree = new AVLTree<>();
		Scanner scan = new Scanner(System.in); // Scanner for user input
		String fileName1 = null; // Variable to hold args[0]
		String fileName2 = null; // Variable to hold args[1]

		// Checks to see if arguments were passed, if not they are added by the user
		if (args.length < 2 || args.length > 2) {
			System.out.println("Sorry but you my be missing a file or have too many, please input 2 file names: ");
			System.out.print("File 1: ");
			fileName1 = scan.nextLine();
			System.out.print("\nFile 2: ");
			fileName2 = scan.nextLine();
		} else {
			fileName1 = args[0];
			fileName2 = args[1];
		}
		scan.close(); // Close scanner for reading user input

		File keywordFile = new File(fileName1);
		try {
			Scanner in = new Scanner(keywordFile); // Scanner for keyword file

			// Loop to add in all the elements to the AVL tree
			while (in.hasNextLine()) {
				keywordTree.add(in.nextLine().trim());
			}

			bridges.setDataStructure(keywordTree.getRootNode());
			bridges.visualize();

			in.close(); // Close scanner for reading keyword file

		} catch (IOException | RateLimitException e) {
			e.printStackTrace();
		}

		File sourceCodeFile = new File(fileName2); // Creating a file to hold valid identifiers
		ArrayList<String> invalidIdentifers = new ArrayList<String>(); // Array list to hold invalid identifiers

		try {
			Scanner lineIn = new Scanner(sourceCodeFile); // Scanner for java source code file
			PrintWriter out = new PrintWriter("ParsedSourceCode.txt"); // Print writer to print valid identifiers to
																		// .txt file

			// Loop to go line by line in the java source code and find invalid and valid
			// identifiers
			while (lineIn.hasNextLine()) {
				String lineScanned = lineIn.nextLine().trim();

				// These statements remove all comments and string literals from the java source
				// code
				String newLine = lineScanned.replaceAll("\".*?\"", "");
				String newLine2 = newLine.replaceAll("\'.*?\'", "");
				String newLine3 = newLine2.replaceAll("//.*", "");

				// This statement removed all block comments from the source code
				if (newLine3.startsWith("/*") && !newLine3.endsWith("*/")) {
					String commentLine = lineIn.nextLine();
					while (!commentLine.endsWith("*/")) {
						commentLine = lineIn.nextLine();
					}
					newLine3 = commentLine;
				}

				Scanner lineSegment = new Scanner(newLine3); // Scanner for each line from source code
				Pattern pat = Pattern.compile("[^a-zA-Z_$0-9]+"); // Patters to split up identifiers
				lineSegment.useDelimiter(pat);

				// Loop to go through each line and split it up to find valid and invalid
				// identifiers
				while (lineSegment.useDelimiter(pat).hasNext()) {
					if (lineSegment.useDelimiter(pat).hasNextInt()) {
						lineSegment.next().trim();
						continue;
					}
					String segmentScanned = lineSegment.next().trim();

					// If statements to see if the string is a keyword
					if (keywordTree.contains(segmentScanned)) {
						continue;

						// Statement to see if the string is an invalid identifier
					} else if (!isValidJavaIdentifier(segmentScanned)) {
						invalidIdentifers.add(segmentScanned);
					} else {
						out.println(segmentScanned);
					}
				}
				lineSegment.close(); //Closes a scanner to scan each element in the line
			}
			out.close(); //Closes print writer 
			lineIn.close(); //Closes scanner for line in

			FrequencyCounter wordCounter = new FrequencyCounter(); 
			Scanner data = new Scanner(new File("ParsedSourceCode.txt"));

			wordCounter.readFile(data);
			System.out.println("The following tokens are not Java Keywords in the file, " + sourceCodeFile + ":");
			wordCounter.display();

			if (invalidIdentifers.isEmpty()) {
				System.out.println("There are no invalid identifiers in the file.");
			} else {
				System.out.println("The following are invalid identifiers in the file:");
				System.out.println(invalidIdentifers.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * isValidJavaIdentifier checks a string to see if it is a valid identifier or not
	 * 
	 * @return s a string variable to be checks for validation
	 * @return returns a true or false
	 */
	public final static boolean isValidJavaIdentifier(String s) {
		// an empty or null string cannot be a valid identifier
		if (s == null || s.length() == 0) {
			return false;
		}
		char[] c = s.toCharArray();
		if (!Character.isJavaIdentifierStart(c[0])) {
			return false;
		}
		for (int i = 1; i < c.length; i++) {
			if (!Character.isJavaIdentifierPart(c[i])) {
				return false;
			}
		}
		return true;
	}

	//Print heading for authors class information
	private static void printHeading() {
		System.out.println();
		System.out.println("Daniel C. West"); // Name of Program Author
		System.out.println("CMSC 256 Fall 2018"); // Class, Section, and Semester
		System.out.println("Programming Project 5"); // Program Number
		System.out.println("Java Identifier Parser"); // Program Name
		System.out.println(); // Line created for console display appeal
	}

}
