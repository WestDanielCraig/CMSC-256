import java.util.Arrays;

public final class ArrayBasedStack<T> implements StackInterface<T> {
	private int topIndex;
	private boolean initialized = false;
	private T[] data;
	private int topOfStack;
	private static final int INITIAL_CAPACITY = 5;

	public ArrayBasedStack() {
		this(INITIAL_CAPACITY);
	}

	public ArrayBasedStack(int initialCapacity) {

		checkCapacity(initialCapacity);

		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[INITIAL_CAPACITY];
		data = temp;
		topIndex = -1;
		initialized = true;
	}

	private void checkCapacity(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Array initial size error");
	}

	private void ensureCapacity() {
		if (topIndex == data.length - 1) {
			int newLength = 2 * data.length;
			checkCapacity(newLength);
			data = Arrays.copyOf(data, newLength);
		}
	}

	public void push(T newEntry) {
		// checkInitialization();
		ensureCapacity();
		data[topIndex + 1] = newEntry;
		topIndex++;
	}

	public boolean isEmpty() {
		return topIndex < 0;
	}

	public T pop() {
		// checkInitialization();
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		else {
			T top = data[topIndex];
			data[topIndex] = null;
			topIndex--;
			return top;
		}
	}
	
	public T peek () {
		//checkInitialzation();
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		else {
			return data[topIndex];
		}
	}
	
	public void clear() {
		while (topIndex > -1) {
			data[topIndex] = null;
			topIndex--;
		}
	}
	

}
