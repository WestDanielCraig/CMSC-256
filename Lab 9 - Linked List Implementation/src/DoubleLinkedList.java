
public class DoubleLinkedList<T> implements ListInterface<T> {
	private DoubleLinkedNode first; 
	private DoubleLinkedNode last;
	private int numElements;

	public DoubleLinkedList() {
		initializeDataFields();
	}

	@Override
	public void add (T newEntry) {
		DoubleLinkedNode newNode = new DoubleLinkedNode(last, newEntry, null);
		if (isEmpty()) {
			first = newNode;
		}
		else {
			last.setNextNode(newNode);
		}
		last = newNode;
		numElements++;
	}

	@Override
	public void add (int newPosition, T newEntry) {
		if ((newPosition >= 1) && (newPosition <= numElements + 1)) {
			DoubleLinkedNode newNode = new DoubleLinkedNode(newEntry);
			if (isEmpty()) {
				first = newNode;
				last = newNode;
			}
			else if (newPosition == 1) {
				newNode.setNextNode(first);
				first.setPreviousNode(newNode);
				first = newNode;
			}
			else if (newPosition == numElements + 1) {
				last.setNextNode(newNode);
				newNode.setPreviousNode(last);
				last = newNode;
			}
			else {
				DoubleLinkedNode nodeBefore = getNodeAt(newPosition - 1);
				DoubleLinkedNode nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeAfter.setPreviousNode(newNode);
				nodeBefore.setNextNode(newNode);
				newNode.setPreviousNode(nodeBefore);
			}
			numElements++;
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
		}
	}

	@Override
	public T remove (int givenPosition) {
		T result = null;
		if ((givenPosition >= 1) && (givenPosition <= numElements)) {
			if (givenPosition == 1) {
				result = first.getData();
				first = first.getNextNode();
				
				if (first == null) {
					last = null;
				}
				else {
					first.setPreviousNode(null);
				}
			}
			
			else {
				DoubleLinkedNode nodeBefore = getNodeAt(givenPosition - 1);
				DoubleLinkedNode nodeToRemove = nodeBefore.getNextNode();
				DoubleLinkedNode nodeAfter = nodeToRemove.getNextNode();
				
				if (nodeAfter == null) {
					nodeBefore.setNextNode(nodeAfter);
					last.setNextNode(null); 
				}
				else {
					nodeBefore.setNextNode(nodeAfter);
					nodeAfter.setPreviousNode(nodeBefore);
				}
				
				result = nodeToRemove.getData();
			}
			numElements--;
		}
		
		else {
			throw new IndexOutOfBoundsException ("Illegal position given to remove operation.");
		}
		return result;
	}

	@Override
	public void clear () {
		initializeDataFields();
	}

	@Override
	public T replace (int givenPosition, T newEntry) {
		if ((givenPosition >= 1) && (givenPosition <= numElements)) {
			DoubleLinkedNode desiredNode = getNodeAt(givenPosition);
			T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
			return originalEntry;
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
		}
	}

	@Override
	public T getEntry (int givenPosition) {
		if ((givenPosition >= 1) && (givenPosition <= numElements))	{
			assert !isEmpty();
			return getNodeAt(givenPosition).getData();
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numElements];

		int index = 0;
		DoubleLinkedNode currentNode = first;
		while ((index < numElements) && (currentNode != null)) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		}
		return result;
	}

	@Override
	public boolean contains (T anEntry) {
		boolean found = false;
		DoubleLinkedNode currentNode = first;

		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData())) {
				found = true;
			}
			else {
				currentNode = currentNode.getNextNode();
			}
		}
		return found;
	}

	@Override
	public int getLength() {
		return numElements;
	}

	@Override
	public boolean isEmpty() {
		return (first == null) && (last == null);
	}

	// Initializes the class's data fields to indicate an empty list.
	private void initializeDataFields()  {
		first = null;
		last = null;
		numElements = 0;
	} 

	protected DoubleLinkedNode getFirst() {
		return first;
	}

	protected DoubleLinkedNode getLast() {
		return last;
	}

	// Returns a reference to the node at a given position.
	// Precondition: The chain is not empty;
	//               1 <= givenPosition <= numberOfEntries.	
	protected DoubleLinkedNode getNodeAt(int givenPosition)	{
		assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numElements);
		DoubleLinkedNode currentNode = first;

		// Traverse the chain to locate the desired node
		// (skipped if givenPosition is 1)
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();

		assert currentNode != null;

		return currentNode;
	} 


	protected class DoubleLinkedNode{
		private T data;  	 
		private DoubleLinkedNode next;  	 // Link to next node
		private DoubleLinkedNode previous; // Link to previous node

		private DoubleLinkedNode(T dataPortion){
			data = dataPortion;
			next = null;	
			previous = null;	
		}
		private DoubleLinkedNode(DoubleLinkedNode previousNode, T dataPortion, DoubleLinkedNode nextNode){
			data = dataPortion;
			next = nextNode;	
			previous = previousNode;
		} 

		protected T getData(){
			return data;
		} 

		private void setData(T newData){
			data = newData;
		} 

		DoubleLinkedNode getNextNode(){
			return next;
		} 

		private void setNextNode(DoubleLinkedNode nextNode){
			next = nextNode;
		} 

		DoubleLinkedNode getPreviousNode(){
			return previous;
		}

		private void setPreviousNode(DoubleLinkedNode previousNode){
			previous = previousNode;
		}
	}
}
