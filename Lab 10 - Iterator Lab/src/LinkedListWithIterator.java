
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListWithIterator<T> implements ListWithIteratorInterface<T> {
   private Node firstNode;
   private int  numberOfEntries;;

   public LinkedListWithIterator()  {
      initializeDataFields();
   } 

	public void clear()	{
      initializeDataFields();
	} 
 
	public void add(T newEntry)	{
		Node newNode = new Node(newEntry);

		if (isEmpty())
			firstNode = newNode;
		else  {                               // Add to end of non-empty list			
         Node lastNode = getNodeAt(numberOfEntries);
			lastNode.setNextNode(newNode);         // Make last node reference new node
		} 
		
		numberOfEntries++;
	}  

   public void add(int newPosition, T newEntry) 	{
 		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))	{
			Node newNode = new Node(newEntry);
         
			if (newPosition == 1)   {              // Case 1
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else	{					// Case 2: list is not empty and newPosition > 1
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			} 
         
			numberOfEntries++;
		}
      else
         throw new IndexOutOfBoundsException("Illegal position given to add operation.");
   }

	public T remove(int givenPosition)	{
      T result = null;                          // Return value
      
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))   {
         assert !isEmpty();
         
         if (givenPosition == 1)  {              // Case 1: remove first entry
            result = firstNode.getData();       // Save entry to be removed
            firstNode = firstNode.getNextNode();
         }
         else   {                                // Case 2: not first entry
            Node nodeBefore = getNodeAt(givenPosition - 1);
            Node nodeToRemove = nodeBefore.getNextNode();
            Node nodeAfter = nodeToRemove.getNextNode();
            nodeBefore.setNextNode(nodeAfter);
            result = nodeToRemove.getData();    // Save entry to be removed
         } 
         
         numberOfEntries--;
         return result;                         // Return removed entry
      }
      else
         throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	} 

	public T replace(int givenPosition, T newEntry)	{
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))  {
      	assert !isEmpty();
         
			Node desiredNode = getNodeAt(givenPosition);
         T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
         return originalEntry;
      }
		else
         throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
   } 
   
   public T getEntry(int givenPosition)  {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
         return getNodeAt(givenPosition).getData();
     	}
      else
         throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
   } 
   
   public T[] toArray() {
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries];
      
      int index = 0;
      Node currentNode = firstNode;
      while ((index < numberOfEntries) && (currentNode != null))   {
         result[index] = currentNode.getData();
         currentNode = currentNode.getNextNode();
         index++;
      } 
      
      return result;
   } 

	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		
		while (!found && (currentNode != null))	{
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} 
		
		return found;
	} 

   public int getLength()  {
      return numberOfEntries;
   } 

   public boolean isEmpty()  {
      boolean result;

      if (numberOfEntries == 0) // Or getLength() == 0
      {
         assert firstNode == null;
         result = true;
      }
      else
      {
         assert firstNode != null;
         result = false;
      } 

      return result;
   } 
   
   public Iterator<T> iterator() {
	   return new IteratorForLinkedList();
   } 

	public Iterator<T> getIterator() {
	   return iterator();
	} 
   
   // Initializes the class's data fields to indicate an empty list.
   private void initializeDataFields()  {
		firstNode = null;
		numberOfEntries = 0;
   } 
	
	// Returns a reference to the node at a given position.
   // Precondition: List is not empty;
   //               1 <= givenPosition <= numberOfEntries.
	private Node getNodeAt(int givenPosition)	{
		assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		
      // Traverse the chain to locate the desired node (skipped
      // if givenPosition is 1)
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();
		
		assert currentNode != null;
      
		return currentNode;
	} 
	
    public String toString() {
        String result = "{ ";
        Node currentNode = firstNode;
        while (currentNode != null)   {
        	result = result + "<" + currentNode.getData() + "> ";
           currentNode = currentNode.getNextNode();
        } 
        
        result = result + "}";

        return result;
    }
//==================================

	private class IteratorForLinkedList implements Iterator<T>	{
      private Node nextNode;  // Node containing next entry in iteration

		private IteratorForLinkedList()	{
			nextNode = firstNode;
		} 
		
		public boolean hasNext()	{
			return nextNode != null;
		} 

		public T next()	{
			if (hasNext())	{
				Node returnNode = nextNode;        // Get next node
				nextNode = nextNode.getNextNode(); // Advance iterator
				
				return returnNode.getData();       // Return next entry in iteration
			}
			else
				throw new NoSuchElementException("Illegal call to next(); " +
		                                       "iterator is after end of list.");
		} 

		public void remove()	{
			throw new UnsupportedOperationException("remove() is not " +
		                                           "supported by this iterator");
		} 
	} 

	//==================================

	private class Node	{
      private T    data; // Entry in list
      private Node next; // Link to next node
      
      private Node(T dataPortion)   {
         data = dataPortion;
         next = null;
      } 
      
      private Node(T dataPortion, Node nextNode)  {
         data = dataPortion;
         next = nextNode;
      } 
      
      private T getData()  {
         return data;
      } 
      
      private void setData(T newData)   {
         data = newData;
      } 
      
      private Node getNextNode()  {
         return next;
      }
      
      private void setNextNode(Node nextNode)   {
         next = nextNode;
      } 
	} 
} 


