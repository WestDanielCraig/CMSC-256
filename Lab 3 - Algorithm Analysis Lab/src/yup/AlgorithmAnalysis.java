package yup;

import java.util.Calendar;

public class AlgorithmAnalysis {

	public static void main(String[] args) {
		// pass the value of n as a command line argument
		long n = Long.parseLong(args[0]);

		// counter for output
		int fragNum = 1;

		// get current time
		long startTime = System.nanoTime();
		// Fragment 1
		long sum = 0;
		// Fragment 2

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n * n; j++) {

				for (int k = 0; k < j; k++) {
					sum++;
				}
			}
					try {
						Thread.sleep(1);
					} catch (InterruptedException ie) {
						System.out.println("Unable to sleep.");
					}

				}
			
		
		// get stop time and calculate actual run time
		long endTime = System.nanoTime();
		long diff = endTime - startTime;
		System.out.println("Time to compute Fragment " + fragNum++ + " was " + diff + " nanoseconds.");

		// continue in the same way for the remaining code fragments

	}

}