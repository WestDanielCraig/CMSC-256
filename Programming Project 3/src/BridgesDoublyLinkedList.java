import java.io.IOException;
import bridges.base.DLelement;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

/**
 * BridgesDoublyLinkedList creates a list with a double link using the bridges third party class
 *   after items are added to the list it will then visualize the list to the bridges website
 *   where it can be viewed. This class performs different actions on the list depending on how
 *   you call the methods. 
 *   
 * @author Daniel C. West
 * @version 1.0
 * @since 2018-11-3
 * @param <E> Generic type for use of different variables.
 */

public class BridgesDoublyLinkedList<E> implements MyListInterface<E> {
	private DLelement<E> first;
	private DLelement<E> last;
	private int numElements;

	public static void main(String[] args) {

		printHeading();

		Bridges bridges = new Bridges(3, "dweststi", "671266808684"); // login to bridges account to see visual image

		BridgesDoublyLinkedList<String> testDoubleLink = new BridgesDoublyLinkedList<>();
		try {
			testDoubleLink.add("Daniel");
			testDoubleLink.add("Kimberly");
			testDoubleLink.add("Owen");
			testDoubleLink.add("Carson");
			testDoubleLink.add("Ronan");
			testDoubleLink.add("Bruin");
			testDoubleLink.add("Herb");
			testDoubleLink.add("Cait");
			testDoubleLink.add("Maddox");
			testDoubleLink.add("Tobias");
			testDoubleLink.add("Ruby");
			testDoubleLink.add("Ping");
			testDoubleLink.add("Chris");
			testDoubleLink.add("Matt");
			testDoubleLink.add("Cheryl");
			testDoubleLink.add("Gary");
			testDoubleLink.add("Buster");
			testDoubleLink.add("Stripes");
			testDoubleLink.add("Janis");
			testDoubleLink.add("Peter");

			bridges.setDataStructure(testDoubleLink.getFirst());
			bridges.visualize();

			// Testing .getLength() method, should return 20 items
			System.out.println("The length of this list is " + testDoubleLink.getLength() + " items.\n");

			System.out.println(testDoubleLink.toString());

			// Should add "Tony" in the 3 position on the list
			System.out.println("Add \"Tony\" to position 3");
			testDoubleLink.add("First Add", 3, "Tony");

			System.out.println(testDoubleLink.toString());

			// Should remove "Owen" from position 4 on the list
			System.out.println("Remove \"Owen\" from position 4");
			testDoubleLink.remove(4);

			System.out.println(testDoubleLink.toString());

			// Should remove first case of "Cait" and return true
			System.out.println("Was Cait on the list and removed? " + testDoubleLink.remove("Cait") + "\n");

			// Should remove first case of "Amber" but there is no Amber so it should return
			// false
			System.out.println("Was Amber on the list and removed? " + testDoubleLink.remove("Amber") + "\n");

			System.out.println(testDoubleLink.toString());

			// Testing indexOf method should return -1 because Cait was removed
			System.out.println(
					"Counting from the beginning of the list \"Cait\" should not be on the list, -1 means she is not: "
							+ testDoubleLink.indexOf("Cait"));

			// Testing indexOf method should return position of 1
			System.out.println(
					"Counting from the beginning of the list \"Daniel\" should be on the list and should have an index of 1: "
							+ testDoubleLink.indexOf("Daniel"));

			// Add "Daniel" to the end of the list so it now has an index of 1 from the
			// beginning and 20
			testDoubleLink.add("Daniel");

			// Getting last index of "Daniel", should be 20 because "Daniel" is at the end
			// and beginning
			System.out.println("Counting from the end of the list \"Daniel\" should have an index of 20: "
					+ testDoubleLink.lastIndexOf("Daniel"));

			// Getting last index of "Ping", should return 11
			System.out.println("Counting from the end of the list \"Ping\" should have an index of 11: "
					+ testDoubleLink.lastIndexOf("Ping"));

			// Getting last index of "Cait" should return -1 because she is not on the list
			System.out.println(
					"Counting from the end of the list \"Cait\" should have an index of -1 b/c she is not on the list: "
							+ testDoubleLink.lastIndexOf("Cait"));

			// Testing replace method, should replace "Ping" with "Sammy"
			System.out.println("\"Ping\" will now be replaced with \"Sammy\" " + testDoubleLink.replace(11, "Sammy"));
			System.out.println(testDoubleLink.toString());

			// Using get Entry method to get the entry at position number 5
			System.out.println("Get entry at position 5, should be \"Ronan\": " + testDoubleLink.getEntry(5));

			// Getting the last value on the list with the get last method, should be
			// "Daniel"
			System.out.println(
					"Get last entry in the list, should be \"Daniel\": " + testDoubleLink.getLast().getValue());

			// Getting the first value on the list with the get first method, should also be
			// "Daniel"
			System.out.println(
					"Get first entry in the list, should be \"Daniel\": " + testDoubleLink.getFirst().getValue());

			// Printing the list to an array
			System.out.println("This will now print the list to an array: ");

			Object[] listArry = testDoubleLink.toArray();

			for (Object ele : listArry) {
				System.out.print(ele + ", ");
			}
			System.out.println();

			// Testing contains method for values
			System.out.println("We will now check for the entry \"Cheryl\": " + testDoubleLink.contains("Cheryl"));
			System.out.println("We will now check for the entry \"Ford\": " + testDoubleLink.contains("Ford"));

			// Testing isEmpty method, should return false
			System.out.println("Check to see if the list is empty or not: " + testDoubleLink.isEmpty());
			System.out.println("What is the length of the list?: " + testDoubleLink.getLength());
			System.out.println("What node is this?: " + testDoubleLink.getNodeAt(3).getValue());

			// Testing clear method
			System.out.println("The list will now be cleared");
			testDoubleLink.clear();
			System.out.println(testDoubleLink.toString());

			// Testing isEmpty method, should return true
			System.out.println("Check to see if the list is empty or not: " + testDoubleLink.isEmpty());

		} catch (IOException | RateLimitException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

	}

	public BridgesDoublyLinkedList() {
		initializeDataFields();
	}

	  /** Adds a new entry to the end of this list.
    Entries currently in the list are unaffected.
    The list's size is increased by 1.
    @param element  The object to be added as a new entry. 
*/
	public void add(E element) {
		DLelement<E> newNode = new DLelement<E>(element, null, last);
		if (isEmpty()) {
			first = newNode;
		} else {
			last.setNext(newNode);
		}
		last = newNode;
		numElements++;
	}

	  /** Adds a new entry at a specified position within this list.
    Entries originally at and above the specified position
    are at the next higher position within the list.
    The list's size is increased by 1.
    @param name		  A string name that shows up on bridges visualization
    @param index       An integer that specifies the desired
                           position of the new entry.
    @param element     The object to be added as a new entry.
    @throws  IndexOutOfBoundsException if either
             newPosition < 1 or newPosition > getLength() + 1. 
*/
	public void add(String name, int index, E element) {
		if ((index >= 1) && (index <= numElements + 1)) {
			DLelement<E> newNode = new DLelement<E>(name, element);
			if (isEmpty()) {
				first = newNode;
				last = newNode;
			} else if (index == 1) {
				newNode.setNext(first);
				first.setPrev(newNode);
				first = newNode;
			} else if (index == numElements + 1) {
				last.setNext(newNode);
				newNode.setPrev(last);
				last = newNode;
			} else {
				DLelement<E> nodeBefore = getNodeAt(index - 1);
				DLelement<E> nodeAfter = nodeBefore.getNext();
				newNode.setNext(nodeAfter);
				nodeAfter.setPrev(newNode);
				nodeBefore.setNext(newNode);
				newNode.setPrev(nodeBefore);
			}
			numElements++;
		} else {
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
		}
	}

	  /** Removes the entry at a given position from this list.
    Entries originally at positions higher than the given
    position are at the next lower position within the list,
    and the list's size is decreased by 1.
    @param index  An integer that indicates the position of
                          the entry to be removed.
    @return  A reference to the removed entry.
    @throws  IndexOutOfBoundsException if either 
             givenPosition < 1 or givenPosition > getLength(). 
*/
	public E remove(int index) {
		E result = null;
		if ((index >= 1) && (index <= numElements)) {
			if (index == 1) {
				result = first.getValue();
				first = first.getNext();

				if (first == null) {
					last = null;
				} else {
					first.setPrev(null);
				}
			} else {
				DLelement<E> nodeBefore = getNodeAt(index - 1);
				DLelement<E> nodeToRemove = nodeBefore.getNext();
				DLelement<E> nodeAfter = nodeToRemove.getNext();

				if (nodeAfter == null) {
					nodeBefore.setNext(nodeAfter);
					last.setNext(null);
				} else {
					nodeBefore.setNext(nodeAfter);
					nodeAfter.setPrev(nodeBefore);
				}

				result = nodeToRemove.getValue();
			}
			numElements--;
		}

		else {
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
		}
		return result;
	}

	  /** Removes the given element from this list, if it is present in the list.
    Entries originally at positions higher than the given
    position are at the next lower position within the list,
    and the list's size is decreased by 1.
    @param element  The element to be removed.
    @return  True if the element was in the list and was removed, 
             false otherwise.
*/
	public boolean remove(E element) {
		if (indexOf(element) != -1) {
			remove(indexOf(element));
			return true;
		} else {
			return false;
		}
	}

	  /** Removes all elements from this list. 
	   */
	public void clear() {
		initializeDataFields();
	}

	  /** Replaces the entry at a given position in this list.
    @param givenPosition  An integer that indicates the position of
                          the entry to be replaced.
    @param newEntry  The object that will replace the entry at the
                     position givenPosition.
    @return  The original entry that was replaced.
    @throws  IndexOutOfBoundsException if either
             givenPosition < 1 or givenPosition > getLength(). */
	public E replace(int givenPosition, E newEntry) {
		if ((givenPosition >= 1) && (givenPosition <= numElements)) {
			DLelement<E> desiredNode = getNodeAt(givenPosition);
			E originalEntry = desiredNode.getValue();
			desiredNode.setValue(newEntry);
			return originalEntry;
		} else {
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
		}
	}

	  /** Retrieves the entry at a given position in this list.
    @param givenPosition  An integer that indicates the position of
                          the desired entry.
    @return  A reference to the indicated entry.
    @throws  IndexOutOfBoundsException if either
             givenPosition < 1 or givenPosition > getLength(). 
*/
	public E getEntry(int givenPosition) {
		if ((givenPosition >= 1) && (givenPosition <= numElements)) {
			assert !isEmpty();
			return getNodeAt(givenPosition).getValue();
		} else {
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
		}
	}

	  /** Retrieves the last entry in this list.
    @return  A reference to the indicated entry 
      or null if the list is empty.
 */
	public DLelement<E> getLast() {
		if (last != null) {
			DLelement<E> lastElement = last;
			return lastElement;
		} else {
			return null;
		}
	}

	  /** Retrieves the first entry in this list.
    @return  A reference to the indicated entry 
      or null if the list is empty.
   */
	public DLelement<E> getFirst() {
		if (first != null) {
			DLelement<E> firstElement = first;
			return firstElement;
		} else {
			return null;
		}
	}

	  /** Retrieves all entries that are in this list in the order in which
    they occur in the list.
    @return  A newly allocated array of all the entries in the list.
             If the list is empty, the returned array is empty. 
*/
	public E[] toArray() {
		@SuppressWarnings("unchecked")
		E[] result = (E[]) new Object[numElements];

		int index = 0;
		DLelement<E> currentNode = first;
		while ((index < numElements) && (currentNode != null)) {
			result[index] = currentNode.getValue();
			currentNode = currentNode.getNext();
			index++;
		}
		return result;
	}

	  /** Sees whether this list contains a given entry.
    @param anEntry  The object that is the desired entry.
    @return  True if the list contains anEntry, or false if not. 
*/
	public boolean contains(E anEntry) {
		boolean foundIt = false;
		DLelement<E> currentNode = first;

		while (!foundIt && (currentNode != null)) {
			if (anEntry.equals(currentNode.getValue())) {
				foundIt = true;
			} else {
				currentNode = currentNode.getNext();
			}
		}
		return foundIt;
	}

	  /** Gets the length of this list.
    @return  The integer number of entries currently in the list. 
*/
	public int getLength() {
		return numElements;
	}

	  /** Determines whether this list is empty.
    @return  True if the list is empty, or false if not. 
*/
	public boolean isEmpty() {
		return (first == null) && (last == null);
	}

	  /** Locates the index of the given element by searching from the front 
    of the list.
  @return  The first index where the given element occurs in this list, 
          or -1 if the element is not in this list.
*/
	public int indexOf(E element) {
		int index = 1;
		DLelement<E> current = first;

		while (current != null && !current.getValue().equals(element)) {
			current = current.getNext();
			index++;
		}
		if (current == null) {
			return -1;
		} else {
			return index;
		}
	}

	  /** Locates the index of the given element by searching from the back 
    of the list.
    @return  The last index where the given element occurs in this list, 
          or -1 if the element is not in this list.
*/
	public int lastIndexOf(E element) {
		int index = getLength();
		DLelement<E> current = last;

		while (current != null && !current.getValue().equals(element)) {
			current = current.getPrev();
			index--;
		}
		if (current == null) {
			return -1;
		} else if (current != null && index <= numElements) {
			return index;
		} else {
			throw new IllegalArgumentException("Index of element is outside the list range");
		}
	}

	  /** Returns a reference to the node at a given position.
    Precondition: The chain is not empty;
                  1 <= givenPosition <= numberOfEntries. 
  * @param givenPosition
  * @return The DLelement<E> at the given index position
  * @throws IllegalArgumentException if index is outside of
  *      the range of this list.
  */
	public DLelement<E> getNodeAt(int givenPosition) {
		assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numElements);
		DLelement<E> currentNode = first;

		for (int counter = 1; counter < givenPosition; counter++) {
			currentNode = currentNode.getNext();
		}

		assert currentNode != null;

		return currentNode;
	}

	  /** @return a String representation of this list. 
*/ 
	public String toString() {
		String result = "";
		DLelement<E> currentNode = first;
		System.out.println("Here is the current list: ");
		while (currentNode != null) {
			result = result + "<" + currentNode.getValue() + "> ";
			currentNode = currentNode.getNext();
		}

		return result + "\n";
	}

	/** Initialize the constructor or empty the list */
	private void initializeDataFields() {
		first = null;
		last = null;
		numElements = 0;
	}

	/**
	 * Method to print out authors name, program name, program number, and date
	 */
	private static void printHeading() {
		System.out.println();
		System.out.println("Daniel C. West"); // Name of Program Author
		System.out.println("CMSC 256 Fall 2018"); // Class, Section, and Semester
		System.out.println("Programming Project 3"); // Program Number
		System.out.println("Bridges Doubly Linked List"); // Program Name
		System.out.println(); // Line created for console display appeal
	}

}
