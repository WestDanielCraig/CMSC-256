import java.util.*;

/**
 * This class will take two arguments from the command line and put the first
 * argument into a stack as a postfix expression. The second argument will be
 * divided and then used to hold values for the variables in the postfix
 * expression.
 * 
 * @author Daniel C. West
 * @version 1.0
 * @since 2018-09-25
 */
public class InfixExpressionEvaluator {

	/**
	 * This is the main method that will take in the two arguments from the command
	 * line
	 * 
	 * @param args
	 *            Takes in two arguments from the command line
	 */
	public static void main(String[] args) {

		printHeading(); // Prints author name, project name, date, and project number

		try {
			// Test to make sure arguments are passed
			if (args.length == 0) {
				System.out.println("No arguments to echo, please review your arguments and try again");
				System.exit(1);
			} else if (args.length != 2) {
				System.out.println(
						"Please make sure you pass two arguments to the command line. The second argument must be in quotations \" \" ");
				System.exit(1);
			}

			System.out.println("Checking equation for proper parenthesis balance:");
			if (BalanceChecker.checkBalance(args[0]))
				System.out.println(args[0] + " is balanced. \n");
			else {
				System.out.println(args[0] + " is not balanced, please check your equation. \n");
				System.exit(1);
			}

			System.out.println("Converting to Postfix expression:");
			System.out.println(infixToPostfix(args[0]) + "\n");

			System.out.println("Replacing variables with values from command line argument:");
			System.out.println(variableReplacement(infixToPostfix(args[0]), valueSplitter(args[1])) + "\n");

			System.out.println("Checking that all the postfix expression variables has values associated with them:");
			variableValueChecker(infixToPostfix(args[0]), valueSplitter(args[1]));
			System.out.println();

			System.out.println("Evaluating expression with variable values:");
			System.out
					.println(postfixToEvaluation(variableReplacement(infixToPostfix(args[0]), valueSplitter(args[1]))));

		} catch (InputMismatchException e) {
			System.out.println(
					"Some variables do not have a value associated with it, or variable value is null, please check your arguments");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(
					"Some of your variable values may not have been initialized in your second argument, please check your arguments");
		}

	}

	/**
	 * This class checks for proper balance of parentheses in an equation.
	 * 
	 * @author Daniel C. West
	 * @version 1.0
	 */
	public static class BalanceChecker {

		/**
		 * @param line
		 *            Takes in a string to check for balance
		 * @return This will return true or false depending on if the equation is
		 *         balanced
		 */
		public static boolean checkBalance(String line) {

			MyStack<Character> openDelimiterStack = new MyStack<>();

			int characterCount = line.length();
			boolean isBalanced = true;
			int index = 0;
			char nextCharacter = ' ';

			while (isBalanced && (index < characterCount)) {
				nextCharacter = line.charAt(index);
				switch (nextCharacter) {
				case '(':
				case '[':
				case '{':
					openDelimiterStack.push(nextCharacter);
					;
					break;
				case ')':
				case ']':
				case '}':
					if (openDelimiterStack.isEmpty())
						isBalanced = false;
					else {
						char openDelimiter = openDelimiterStack.pop();
						isBalanced = isPaired(openDelimiter, nextCharacter); // Sends to method isPaired to check that
																				// where are two paired opposite
					}
					break;
				default:
					break;
				}
				index++;
			}

			if (!openDelimiterStack.isEmpty())
				isBalanced = false;
			return isBalanced;
		}

		/**
		 * Method that checks for opened and closed parentheses, brackets, or curly
		 * brackets
		 * 
		 * @param open
		 *            Takes in the open operator
		 * @param close
		 *            Takes in the close operator
		 * @return Will return true or false depending on if they match up properly
		 */
		private static boolean isPaired(char open, char close) {
			return (open == '(' && close == ')') || (open == '[' && close == ']') || (open == '{' && close == '}');
		}
	}

	/**
	 * Takes in a string that is a infix expression and turns it into a postfix
	 * expression
	 * 
	 * @param line
	 *            Takes in a String to change from an infix expression to postfix
	 *            expression
	 * @return returns another String that is in the postfix form
	 */
	public static String infixToPostfix(String line) {

		String result = new String("");

		MyStack<Character> stack = new MyStack<>();

		for (int i = 0; i < line.length(); ++i) {
			char c = line.charAt(i);

			if (Character.isLetterOrDigit(c))
				result += c;
			else if (c == '(')
				stack.push(c);
			else if (c == ')') {
				while (!stack.isEmpty() && stack.peek() != '(')
					result += stack.pop();

				if (!stack.isEmpty() && stack.peek() != '(')
					return "Invalid Expression";
				else
					stack.pop();
			} else {
				while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek()))
					result += stack.pop();
				stack.push(c);
			}
		}

		while (!stack.isEmpty())
			result += stack.pop();

		return result;
	}

	/**
	 * Method takes in an operator and associates a value with it, the higher the
	 * value the higher the precedence
	 * 
	 * @param ch
	 *            takes in a character to check for precedence
	 * @return will return a value depending on how high the precedence is
	 */
	public static int precedence(char ch) {

		switch (ch) {
		case '+':
		case '-':
			return 1;

		case '*':
		case '/':
			return 2;

		case '^':
			return 3;
		}
		return -1;
	}

	/**
	 * This method will split the second argument string from the command line and
	 * put the value into an array of strings, the array will always have be size
	 * six each index corresponds with a variable letter, index 0 = a, index 1 = b,
	 * and so on to the letter f. This will also check to make sure there are only
	 * variables a-f and no further.
	 * 
	 * @param line2
	 *            takes in a string
	 * @return will return an array of strings will values in them
	 */
	public static String[] valueSplitter(String line2) {

		Scanner in = new Scanner(line2);
		String[] varNums = new String[6];

		while (in.hasNextLine()) {

			String line = in.nextLine();
			String[] firstSplit = line.split("\\s");

			int countA = 0;
			int countB = 0;
			int countC = 0;
			int countD = 0;
			int countE = 0;
			int countF = 0;

			for (int i = 0; i < firstSplit.length; i++) {
				String[] secondSplit = firstSplit[i].split("=");

				switch (secondSplit[0]) {
				case "a":
				case "A":
					if (countA == 0) {
						varNums[0] = secondSplit[1];
						countA++;
						break;
					} else {
						System.out.println("There is a duplicate value for variable a, please check your arguments");
						System.exit(1);
					}
				case "b":
				case "B":
					if (countB == 0) {
						varNums[1] = secondSplit[1];
						countB++;
						break;
					} else {
						System.out.println("There is a duplicate value for variable b, please check your arguments");
						System.exit(1);
					}
				case "c":
				case "C":
					if (countC == 0) {
						varNums[2] = secondSplit[1];
						countC++;
						break;
					} else {
						System.out.println("There is a duplicate value for variable c, please check your arguments");
						System.exit(1);
					}
				case "d":
				case "D":
					if (countD == 0) {
						varNums[3] = secondSplit[1];
						countD++;
						break;
					} else {
						System.out.println("There is a duplicate value for variable d, please check your arguments");
						System.exit(1);
					}
				case "e":
				case "E":
					if (countE == 0) {
						varNums[4] = secondSplit[1];
						countE++;
						break;
					} else {
						System.out.println("There is a duplicate value for variable e, please check your arguments");
						System.exit(1);
					}
				case "f":
				case "F":
					if (countF == 0) {
						varNums[5] = secondSplit[1];
						countF++;
						break;
					} else {
						System.out.println("There is a duplicate value for variable f, please check your arguments");
						System.exit(1);
					}
				default:
					System.out.println("Variable references should be a-f, please check your agruments");
					System.exit(1);
				}
			}
		}
		in.close();
		return varNums;
	}

	/**
	 * This method replaces each instance of a letter variable with a string of
	 * digits from an array of strings.
	 * 
	 * @param postfix
	 *            takes in a postfix string expression
	 * @param values
	 *            takes in an array of strings that are made up of digits
	 * @return will return a new string expression where the variables are now
	 *         replaced with string digits
	 */
	public static String variableReplacement(String postfix, String[] values) {

		String result;

		if (values.length != 6) {
			throw new ArrayIndexOutOfBoundsException(); // May throw exception if a variable did not have a value
														// associated with it in command line argument
		}

		result = postfix.replaceAll("a", values[0] + " ");
		result = result.replaceAll("A", values[0] + " ");
		result = result.replaceAll("b", values[1] + " ");
		result = result.replaceAll("B", values[1] + " ");
		result = result.replaceAll("c", values[2] + " ");
		result = result.replaceAll("C", values[2] + " ");
		result = result.replaceAll("d", values[3] + " ");
		result = result.replaceAll("D", values[3] + " ");
		result = result.replaceAll("e", values[4] + " ");
		result = result.replaceAll("E", values[4] + " ");
		result = result.replaceAll("f", values[5] + " ");
		result = result.replaceAll("F", values[5] + " ");

		return result;

	}

	/**
	 * This method checks to make sure each variable has a value associated with it
	 * and not a null value from the array if it has a null value attached to it
	 * then the command line argument variable did not have a value associated with
	 * it.
	 * 
	 * @param postfix
	 *            takes in the postfix expression string
	 * @param values
	 *            takes in the array of strings
	 * @return will return true if each variable that is used has a value associated
	 *         with it
	 */
	public static boolean variableValueChecker(String postfix, String[] values) {

		boolean result = true;

		for (int i = 0; i < postfix.length(); i++) {
			if (postfix.charAt(i) == 'a' && values[0] == null) {
				throw new InputMismatchException();
			} else if (postfix.charAt(i) == 'A' && values[0] == null) {
				throw new InputMismatchException();
			} else if (postfix.charAt(i) == 'b' && values[1] == null) {
				throw new InputMismatchException();
			} else if (postfix.charAt(i) == 'B' && values[1] == null) {
				throw new InputMismatchException();
			} else if (postfix.charAt(i) == 'c' && values[2] == null) {
				throw new InputMismatchException();
			} else if (postfix.charAt(i) == 'C' && values[2] == null) {
				throw new InputMismatchException();
			} else if (postfix.charAt(i) == 'd' && values[3] == null) {
				throw new InputMismatchException();
			} else if (postfix.charAt(i) == 'D' && values[3] == null) {
				throw new InputMismatchException();
			} else if (postfix.charAt(i) == 'e' && values[4] == null) {
				throw new InputMismatchException();
			} else if (postfix.charAt(i) == 'E' && values[4] == null) {
				throw new InputMismatchException();
			} else if (postfix.charAt(i) == 'f' && values[5] == null) {
				throw new InputMismatchException();
			} else if (postfix.charAt(i) == 'F' && values[5] == null) {
				throw new InputMismatchException();
			}
		}
		System.out.println("All variables are matched with a value.");
		return result;
	}

	/**
	 * This method takes in the expression that has string vlues associated with it
	 * and spits out a numeric answer
	 * 
	 * @param line
	 *            takes in postfix expression with values associated with the
	 *            variables
	 * @return will return the answer as a double
	 */
	public static double postfixToEvaluation(String line) {

		MyStack<Integer> stack = new MyStack<>();

		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);

			if (c == ' ')
				continue;
			else if (Character.isDigit(c)) {
				int n = 0;

				while (Character.isDigit(c)) {
					n = n * 10 + (int) (c - '0');
					i++;
					c = line.charAt(i);
				}
				i--;

				stack.push(n);
			} else {
				int value1 = stack.pop();
				int value2 = stack.pop();

				switch (c) {
				case '+':
					stack.push(value2 + value1);
					break;
				case '-':
					stack.push(value2 - value1);
					break;
				case '/':
					if (value1 == 0) {
						System.out.println(
								"Because you've divided by zero, countless mathematicians on the fourth floor of Harris Hall are crying and the world exploded.");
						System.exit(0);
					}
					stack.push(value2 / value1);
					break;
				case '*':
					stack.push(value2 * value1);
					break;
				case '^':
					stack.push((int) Math.pow(value2, value1));
					break;
				}
			}
		}
		return stack.pop();
	}

	/**
	 * Method to show author name, program name, program number, and date
	 */
	private static void printHeading() {
		System.out.println();
		System.out.println("Daniel C. West"); // Name of Program Author
		System.out.println("CMSC 256 Fall 2018"); // Class, Section, and Semester
		System.out.println("Programming Project 2"); // Program Number
		System.out.println("Stacks"); // Program Name
		System.out.println(); // Line created for console display appeal
	}
}
