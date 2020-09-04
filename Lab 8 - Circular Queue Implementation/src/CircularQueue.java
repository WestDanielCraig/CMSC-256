import java.util.NoSuchElementException;

public final class CircularQueue<E> implements QueueInterface<E> {

	public static void main(String[] args) {
		CircularQueue<String> q = new CircularQueue<String>(3);
		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		System.out.println(q.dequeue());
		q.enqueue("d");
		q.enqueue("e");
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println("Empty? " + q.isEmpty());
	}

	private E[] queue;

	private int front = 0;
	private int rear = 0;
	private static final int DEFAULT_CAPACITY = 5;

	public CircularQueue() {
		this(DEFAULT_CAPACITY);
	}

	public CircularQueue(int capacity) {
		if (capacity > 0) {
			@SuppressWarnings("unchecked")
			E[] temp = (E[]) new Object[capacity + 1];
			queue = temp;
			front = 0;
			rear = 0;
		} else
			throw new IllegalArgumentException("Invalid capacity");
	}

	@Override
	public void enqueue(E newElement) {

		if (isFull()) {
			this.expand();
		}
		rear = (rear + 1) % queue.length;
		queue[rear] = newElement;
	}
	
	public int size() {
		return (queue.length - front + rear) % queue.length;
	}

	@Override
	public E dequeue() {

		if (isEmpty())
			throw new NoSuchElementException("Queue is empty");
		else {
			front = (front + 1) % queue.length;
			E otherFront = queue[front];
			queue[front] = null;
			return otherFront;
		}
	}

	@Override
	public E getFront() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return front == rear;
	}

	private boolean isFull() {
		return ((rear + 1) % queue.length) == front;
	}

	private void expand() {
		E[] oldQueue = queue;
		int oldSize = oldQueue.length;
		int newSize = 2 * oldSize;

		@SuppressWarnings("unchecked")
		E[] newQueue = (E[]) new Object[newSize];

		int newIndex = 1;
		while (!this.isEmpty()) {
			newQueue[newIndex] = this.dequeue();
			newIndex++;
		}
		queue = newQueue;
		front = 0;
		rear = newIndex - 1;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}
