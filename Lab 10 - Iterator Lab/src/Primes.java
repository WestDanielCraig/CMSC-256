
import java.io.*;
import java.util.*;

/**
 * Primes is a program that will compute prime numbers using the Sieve of
 * Eratosthenes.
 */
public class Primes {

	public static void main(String args[]) {
		int max;
		System.out.println("Please enter the maximum value to test for primality");
		max = getInt("   It should be an integer value greater than or equal to 2.");

		ListWithIteratorInterface<Integer> candidates = new LinkedListWithIterator<>();

		System.out.println();

		for (int i = 2; i <= max; i++) {
			candidates.add(i);
		}

		System.out.println("Candidate values: " + candidates.toString() + "\n");

		ListWithIteratorInterface<Integer> primes = new ArrayListWithIterator<>();
		ListWithIteratorInterface<Integer> composites = new ArrayListWithIterator<>();

		while (!candidates.isEmpty()) {
			Integer currentPrime = candidates.remove(1);

			System.out.println("Current prime value: " + currentPrime);

			primes.add(currentPrime);

			getComposites(candidates, composites, currentPrime);

			System.out.println("New candidate values: " + candidates.toString());
			System.out.println("Prime value: " + primes.toString());
			System.out.println("Composite values: " + composites.toString() + "\n");

		}

		ListWithIteratorInterface<Integer> candidates2 = new LinkedListWithIterator<>();

		for (int i = 2; i <= max; i++) {
			candidates2.add(i);
		}

		ListWithIteratorInterface<Integer> primes2 = new ArrayListWithIterator<>();
		ListWithIteratorInterface<Integer> composites2 = new ArrayListWithIterator<>();

		Iterator<Integer> candidateValues = candidates2.getIterator();
		System.out.print("\nCandidates: ");
		while (candidateValues.hasNext()) {
			System.out.print(candidateValues.next() + " ");
		}

		while (!candidates2.isEmpty()) {
			Integer currentPrime = candidates2.remove(1);
			System.out.print("\n\nCurrent prime value: " + currentPrime);
			primes2.add(currentPrime);

			getComposites2(candidates2, composites2, currentPrime);

			candidateValues = candidates2.getIterator();
			System.out.print("\nNew Candidates: ");
			;
			while (candidateValues.hasNext()) {
				System.out.print(candidateValues.next() + " ");
			}

			Iterator<Integer> primeValues = primes2.getIterator();
			Iterator<Integer> compositeValues = composites2.getIterator();

			System.out.print("\nPrimes: ");
			while (primeValues.hasNext()) {
				System.out.print(primeValues.next() + " ");
			}

			System.out.print("\nComposites: ");
			while (compositeValues.hasNext()) {
				System.out.print(compositeValues.next() + " ");
			}

		}

	}

	/**
	 * getComposites - Remove the composite values from possibles list and put them
	 * in the composites list.
	 *
	 * @param candidates
	 *            A list of integers holding the possible values.
	 * @param composites
	 *            A list of integers holding the composite values.
	 * @param prime
	 *            An Integer that is prime.
	 */
	public static void getComposites(ListInterface<Integer> candidates, ListInterface<Integer> composites,
			Integer prime) {
		for (int i = 1; i <= candidates.getLength(); i++) {
			if (candidates.getEntry(i) % prime == 0) {
				composites.add(candidates.remove(i));
			}
		}
	}

	public static void getComposites2(ListWithIteratorInterface<Integer> candidates, ListInterface<Integer> composites,
			Integer prime) {
		Iterator<Integer> candidateValues = candidates.getIterator();
		int index = 1;
		while (candidateValues.hasNext()) {
			Integer aValue = candidateValues.next();
			if (aValue % prime == 0) {
				composites.add(aValue);
				candidates.remove(index);
				index--;
			}
			index++;
		}
	}

	/**
	 * Get an integer value.
	 *
	 * @return An integer.
	 */
	private static int getInt(String rangePrompt) {
		Scanner input;
		int result = 10; // Default value is 10
		try {
			input = new Scanner(System.in);
			System.out.println(rangePrompt);
			result = input.nextInt();
		} catch (NumberFormatException e) {
			System.out.println("Could not convert input to an integer");
			System.out.println(e.getMessage());
			System.out.println("Will use 10 as the default value");
		} catch (Exception e) {
			System.out.println("There was an error with System.in");
			System.out.println(e.getMessage());
			System.out.println("Will use 10 as the default value");
		}
		return result;
	}

}
