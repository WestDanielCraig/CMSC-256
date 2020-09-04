
public interface QueueInterface<E> {

	public void enqueue(E newEntry);
	
	public E dequeue();
	
	public E getFront();
	
	public boolean isEmpty();
	
	public void clear();
}
