import java.io.IOException;
import java.util.EmptyStackException;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;
import bridges.base.SLelement;

/**
 * This classes uses the bridges library to create a stack of nodes and then use the bridges visualizer to 
 *  make a visual image of the nodes connected together. This will also test pop, push, and is empty methods etc.
 * 
 * @author Daniel C. West
 * @version 1.0
 * @param <E> generic variable
 */
public class MyBridgesStack<E> implements StackInterface<E> {

	/**
	 * private SLelement variable to hold the topnode position
	 */
	private SLelement<E> topNode;

	/**
	 * Main method to test methods in the class as well all use the visualizer in the bridges library
	 * 
	 * @param args Not used
	 */
	public static void main(String[] args) {

		printHeading();

		Bridges bridges = new Bridges(0, "dweststi", "671266808684"); //login to bridges account to see visual image

		MyBridgesStack<Integer> testStack = new MyBridgesStack<>();

		testStack.push(256);
		testStack.push(1);
		testStack.push(9);
		testStack.push(20);
		testStack.push(2018);
		System.out.println("Pushed 256, 1, 9, 20, and 2018");

		System.out.println("Top of stack");
		testStack.display(testStack);
		System.out.println("Bottom of stack");

		bridges.setDataStructure(testStack.returnTopNode());
		try {
			bridges.visualize();
		} catch (IOException | RateLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();

		testStack.pop();
		testStack.pop();
		System.out.println("Called pop twice: ");

		System.out.println("Top of stack");
		testStack.display(testStack);
		System.out.println("Bottom of stack");
		System.out.println();

		System.out.println("A call to peek returns " + testStack.peek());

		System.out.println("Top of stack");
		testStack.display(testStack);
		System.out.println("Bottom of stack");
		System.out.println();

		testStack.pop();
		testStack.pop();
		testStack.pop();
		System.out.println("Called pop three times: ");

		testStack.display(testStack);
		testStack.isEmpty(testStack);
		System.out.println();
	}

	/**
	 * Default constructor
	 */
	public MyBridgesStack() {
		topNode = null;
	}

	/* (non-Javadoc)
	 * @see StackInterface#push(java.lang.Object)
	 */
	@Override
	public void push(E newEntry) {
		SLelement<E> newNode = new SLelement<>(newEntry, topNode);
		topNode = newNode;
	} // End push

	/* (non-Javadoc)
	 * @see StackInterface#pop()
	 */
	@Override
	public E pop() {
		E top = peek(); // Might throw EmptyStackException
		assert topNode != null;
		topNode = topNode.getNext();
		return top;
	} // End pop

	/* (non-Javadoc)
	 * @see StackInterface#peek()
	 */
	@Override
	public E peek() {
		if (isEmpty())
			throw new EmptyStackException();
		else
			return topNode.getValue();
	} // End peek

	/* (non-Javadoc)
	 * @see StackInterface#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return topNode == null;
	} // End isEmpty

	/**
	 * Method checks to see if a stack is empty or not
	 * 
	 * @param s takes a stack 
	 */
	public void isEmpty(MyBridgesStack<E> s) {
		if (s.isEmpty())
			System.out.println("The stack is empty");
		else
			System.out.println("The stack is not empty");
	}

	/* (non-Javadoc)
	 * @see StackInterface#clear()
	 */
	@Override
	public void clear() {
		topNode = null;
	} // End clear

	/**
	 * Method displays stacks contents from the top down
	 * 
	 * @param s takes in a stack
	 */
	public void display(MyBridgesStack<E> s) {
		if (s.isEmpty()) {
			return;
		} else {
			E item = s.pop();
			System.out.println(item);
			display(s);
			s.push(item);
		}
	}

	/**
	 * Method to return the topNode so it can be used in the bridges visualizer
	 * 
	 * @return topNode of stack
	 */
	public SLelement<E> returnTopNode() {
		return topNode;
	}

	/**
	 * Method to print out authors name, program name, program number, and date
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
