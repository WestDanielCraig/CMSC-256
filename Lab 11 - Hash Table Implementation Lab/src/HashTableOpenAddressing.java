import java.util.Iterator;

public class HashTableOpenAddressing<K, V> implements DictionaryInterface<K, V> {
	private int numEntries;
	private static final int DEFAULT_CAPACITY = 5;
	private static final int MAX_CAPACITY = 10000;
	private TableEntry<K, V>[] table;
	private double loadFactor;
	private boolean initialized = false;
	private static final double DEFAULT_LOAD_FACTOR = 0.75;

	public HashTableOpenAddressing() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	public HashTableOpenAddressing(int initialCapacity, double loadFactorIn) {
		if (initialCapacity > 0 && loadFactorIn > 0) {
			checkCapacity(initialCapacity);
			numEntries = 0;

			int tableSize = getNextPrime(initialCapacity);
			loadFactor = loadFactorIn;
			@SuppressWarnings("unchecked")
			TableEntry<K, V>[] temp = (TableEntry<K, V>[]) new TableEntry[tableSize];
			table = temp;
			initialized = true;
		} else {
			throw new IllegalArgumentException(
					"Attempted to set initial capacity or load factor to be less than or equal to zero.");
		}

	}

	private void checkCapacity(int desiredCapacity) {
		if (desiredCapacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum");
	}

	// Throws an exception if this object is not initialized.
	private void checkInitialization() {
		if (!initialized) {
			throw new SecurityException("HashTableOpenAddressing object is not initialized properly.");
		}
	}

	// Returns a price integer that is >= the given integer.
	private int getNextPrime(int integer) {
		// if even, add 1 to make odd
		if (integer % 2 == 0) {
			integer++;
		}
		// test odd integers
		while (!isPrime(integer)) {
			integer = integer + 2;
		}
		return integer;
	}

	// Returns true if the given integer is prime.
	private boolean isPrime(int integer) {
		boolean result;
		boolean done = false;

		// 1 and even numbers are not prime
		if ((integer == 1) || (integer % 2 == 0)) {
			result = false;
		}
		// 2 and 3 are prime
		else if ((integer == 2) || (integer % 2 == 0)) {
			result = true;
		} else { // integer is odd and >= 5
			result = true; // assume prime
			for (int divisor = 3; !done && (divisor * divisor <= integer); divisor = divisor + 2) {
				if (integer % divisor == 0) {
					result = false; // divisible; not prime
					done = true;
				}
			}
		}
		return result;
	}

	@Override
	public V add(K keyIn, V valueIn) {
		checkInitialization();
		if (keyIn == null || valueIn == null) {
			throw new IllegalArgumentException("Null values cannot be passed into add operation");
		} else {
			V oldValue;

			int index = getHashIndex(keyIn);
			// index = linearProbe(index, keyIn);
			index = quadraticProbe(index, keyIn);

			if ((table[index] == null) || table[index].isRemoved()) {
				table[index] = new TableEntry<>(keyIn, valueIn);
				numEntries++;
				oldValue = null;
			} else {
				oldValue = table[index].getValue();
				table[index].setValue(valueIn);
			}
			if (numEntries > loadFactor * table.length) {
				enlargeHashTable();
			}
			return oldValue;
		}
	}

	private void enlargeHashTable() {
		TableEntry<K, V>[] oldTable = table;
		int capacity = getNextPrime(oldTable.length * 2);

		@SuppressWarnings("unchecked")
		TableEntry<K, V>[] temp = (TableEntry<K, V>[]) new TableEntry[capacity];
		table = temp;
		numEntries = 0;

		for (int i = 0; i < oldTable.length; i++) {
			if ((oldTable[i] != null) && oldTable[i].isIn()) {
				add(oldTable[i].getKey(), oldTable[i].getValue());
			}
		}
	}

	private int quadraticProbe(int index, K key) {
		boolean found = false;
		int i = 0;
		int temp = index;
		int removedStateIndex = -1;

		if (table[index] == null) {
			return index;
		}

		while (!found && (table[index] != null)) {
			if (table[index].isIn()) {
				if (key.equals(table[index].getKey())) {
					found = true;
				} else {
					index = (temp + i * i) % table.length;
					i++;
				}
			} else {
				if (removedStateIndex == -1) {
					removedStateIndex = index;
				}
				index = (temp + i * i) % table.length;
				i++;
			}
		}
		if (found || (removedStateIndex == -1)) {
			return index;
		} else {
			return removedStateIndex;
		}
	}

	@Override
	public V remove(K keyIn) {
		checkInitialization();
		V removedValue = null;
		int index = getHashIndex(keyIn);
		// index = linearProbe(index, keyIn);
		index = quadraticProbe(index, keyIn);

		if (index != -1) {
			removedValue = table[index].getValue();
			table[index].setToRemoved();
			numEntries--;
		}
		return removedValue;
	}

	@Override
	public V getValue(K keyIn) {
		checkInitialization();
		int index = getHashIndex(keyIn);
		// index = linearProbe(index, keyIn);
		index = quadraticProbe(index, keyIn);
		TableEntry<K, V> item = table[index];

		if ((item != null) && item.isIn()) {
			return item.getValue();
		} else {
			return null;
		}
	}

	@Override
	public boolean contains(K key) {
return getValue(key) != null;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<V> getValueIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		if (numEntries == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean isFull() {
		if (numEntries >= table.length)
			return true;
		else
			return false;
	}

	@Override
	public int getSize() {
		return numEntries;
	}

	@Override
	public void clear() {
		@SuppressWarnings("unchecked")
		TableEntry<K, V>[] temp = (TableEntry<K, V>[]) new TableEntry[table.length];
		table = temp;
		numEntries = 0;
	}

	public int getHashIndex(K key) {
		int hashIndex = Math.abs((key.hashCode() % 10) % table.length);
		return hashIndex;
	}

	private static class TableEntry<K, V> {
		private K key;
		private V value;
		private States state; // Indicates if entry is in the hash table

		private enum States {
			CURRENT, REMOVED
		} // Possible values of state

		public TableEntry(K key, V value) {
			this.key = key;
			this.value = value;
			state = States.CURRENT;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			return this.value = value;
		}

		private boolean isIn() {
			return state == States.CURRENT;
		}

		private boolean isRemoved() {
			return state == States.REMOVED;
		}

		private void setToRemoved() {
			state = States.REMOVED;
		}
	}

}
