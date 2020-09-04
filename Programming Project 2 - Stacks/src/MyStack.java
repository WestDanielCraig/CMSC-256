
import java.util.*;

/**
 * MyStack class creates a stack using nodes from a private Node class. This class also
 * 	has a main method that tests the classes functions by performing a sequence of 
 *  push and pops. This class also has a display method that will print out the stack
 *  from the top of the stack to the bottom of the stack.
 * 
 * @author Daniel C. West
 * @version 1.0
 * @since 2018-09-25
 * @param <E> Generic type for use of different variables.
 */

public final class MyStack<E> implements StackInterface<E> {
	/**
	 * private variable to hold the top of the stack node.
	 */
	private Node topNode;

	/**
	 * This is the main method which will perform a series of push, pops and displays
	 *  to manipulate the stack and display the different methods. 
	 * @param args Unused.
	 */
	public static void main(String[] args) {

		printHeading(); //Prints Authors name, class, date, and project.
		
		MyStack<Integer> testStack = new MyStack<>(); //Creates stack to manipulate

		try {
			testStack.push(256);
			testStack.push(1);
			testStack.push(9);
			testStack.push(20);
			testStack.push(2018);
			System.out.println("Pushed 256, 1, 9, 20, and 2018");

			System.out.println("Top of stack");
			testStack.display(testStack);
			System.out.println("Bottom of stack");

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
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Private class to use nodes to link stack nodes together.
	 * 
	 * @author Daniel C. West
	 */
	private class Node {

		/**
		 * Variable to hold node data.
		 */
		private E data;
		/**
		 * Variable to point to the next node in the chain.
		 */
		private Node next;

		/**
		 * Default constructor for Node class
		 * 
		 * @param dataPortion Generic variable to hold different values
		 */
		private Node(E dataPortion) {
			this(dataPortion, null);
		}

		/**
		 * Parameterized constructor for Node class
		 * 
		 * @param dataPortion Generic variable to hold different values
		 * @param nextNode Variable to point to next node in the chain
		 */
		private Node(E dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		/**
		 * Getdata method to get data from node
		 * 
		 * @return returns data from node
		 */
		private E getData() {
			return data;
		}

		/**
		 * Sets data in node
		 * 
		 * @param newData
		 */
		private void setData(E newData) {
			data = newData;
		}

		/**
		 * Gets the next node in the chain
		 * 
		 * @return
		 */
		private Node getNextNode() {
			return next;
		}

		/**
		 * Sets the next node in the chain
		 * 
		 * @param nextNode
		 */
		private void setNextNode(Node nextNode) {
			next = nextNode;
		}
	} // End private Node class

	/**
	 * MyStack default constructor, sets top node to null
	 */
	public MyStack() {
		topNode = null;
	}

	/* (non-Javadoc)
	 * @see StackInterface#push(java.lang.Object)
	 */
	@Override
	public void push(E newEntry) {
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
	} // End push

	/* (non-Javadoc)
	 * @see StackInterface#pop()
	 */
	@Override
	public E pop() {
		E top = peek(); // Might throw EmptyStackException
		assert topNode != null;
		topNode = topNode.getNextNode();
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
			return topNode.getData();
	} // End peek

	/* (non-Javadoc)
	 * @see StackInterface#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return topNode == null;
	} // End isEmpty

	/**
	 * Method to check if a stack is empty by passing the stack to the method.
	 * 
	 * @param s MyStack<E> variable is passed to method and check to see if there is
	 *  anyting in the stack.
	 */
	public void isEmpty(MyStack<E> s) {
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
	 * This method will display a stack contents
	 * 
	 * @param s Takes a stack and displays its contents
	 */
	public void display(MyStack<E> s) {
		if (s.isEmpty()) {
			return;
		} else {
			E item = s.pop();
			System.out.println(item);
			display(s); //Recursive method to go through everything in the stack
			s.push(item);
		}
	}

	/**
	 * Method to display author, class, project number, and project name
	 */
	private static void printHeading() {
		System.out.println();
		System.out.println("Daniel C. West"); // Name of Program Author
		System.out.println("CMSC 256 Fall 2018"); // Class, Section, and Semester
		System.out.println("Programming Project 2"); // Program Number
		System.out.println("Stacks"); // Program Name
		System.out.println(); // Line created for console display appeal
	} //End printHeading
	
} //End MyStack
