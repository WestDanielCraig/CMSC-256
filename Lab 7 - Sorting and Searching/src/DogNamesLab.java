import java.util.*;
import java.io.*;

public class DogNamesLab {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner in1 = new Scanner(System.in);
		String fileName = "/Users/danielwest/Desktop/CSMC 256 Eclipse Workspace/Lab7 Sorting and Searching/src/Dog_Names.csv";
		File inputFile = new File(fileName);
		Scanner in2 = new Scanner(inputFile);

		if (args[0].equals("1")) {

			ArrayList<Dog> dogArray = new ArrayList();

			while (in2.hasNextLine()) {

				Dog doggie = new Dog();
				String excelCells = in2.nextLine();

				String[] nameAndCount = excelCells.split(",");

				doggie.setDogName(nameAndCount[0]);
				doggie.setCount(nameAndCount[1]);

				dogArray.add(doggie);
			}

			System.out.println("Enter a dog's name?");
			String dogName = in1.next();

			for (Dog d : dogArray) {
				if (d.getDogName() != null && d.getDogName().contains(dogName)) {
					System.out.println(d.getCount());
					System.exit(0);
				}
			}
		}

		if (args[0].equals("2")) {

			ArrayList<Dog> dogArray = new ArrayList();

			while (in2.hasNextLine()) {

				Dog doggie = new Dog();
				String excelCells = in2.nextLine();

				String[] nameAndCount = excelCells.split(",");

				doggie.setDogName(nameAndCount[0]);
				doggie.setCount(nameAndCount[1]);

				dogArray.add(doggie);
			}
			System.out.println("These are the dogs in alphabetical order");
			Collections.sort(dogArray);

			for (Dog ele : dogArray) {
				System.out.println(ele);
			}
		}

		if (args[0].equals("3")) {
			ArrayList<Dog> dogArray = new ArrayList();

			while (in2.hasNextLine()) {

				Dog doggie = new Dog();
				String excelCells = in2.nextLine();

				String[] nameAndCount = excelCells.split(",");

				doggie.setDogName(nameAndCount[0]);
				doggie.setCount(nameAndCount[1]);

				dogArray.add(doggie);
			}

			Random rand = new Random();
			String playGameAnswer;
			int count = 0;
			int totalCount = 0;

			System.out.println("Would you like to play a game?");

			while (!in1.hasNext("[YN]")) { // Start while loop
				System.out.print("Please press Y or N: "); // Ask User Again for Y or N
				in1.next(); // Scans Next String
			} // End while loop
			playGameAnswer = in1.next();

			if (playGameAnswer.equals("Y")) {

				while (playGameAnswer.equals("Y")) {

					int num1 = rand.nextInt(dogArray.size());
					int num2 = rand.nextInt(dogArray.size());

					System.out.println("Which name is more popular for Anchorage dogs? (Type 1 or 2)");
					System.out.println(
							"1. " + dogArray.get(num1).getDogName() + "\t 2. " + dogArray.get(num2).getDogName());

					int selection = in1.nextInt();

					if (selection == 1 && Integer.parseInt(dogArray.get(num1).getCount()) > Integer
							.parseInt(dogArray.get(num2).getCount())) {
						count++;
						totalCount++;
						System.out.println("You guessed correctly " + count + " out of " + totalCount + " times.");
					} else if (selection == 2 && Integer.parseInt(dogArray.get(num1).getCount()) > Integer
							.parseInt(dogArray.get(num2).getCount())) {
						totalCount++;
						System.out.println("Nope, the more popular dog name is " + dogArray.get(num1).getDogName());
					} else if (selection == 1 && Integer.parseInt(dogArray.get(num1).getCount()) < Integer
							.parseInt(dogArray.get(num2).getCount())) {
						totalCount++;
						System.out.println("Nope, the more popular dog name is " + dogArray.get(num2).getDogName());
					} else if (selection == 2 && Integer.parseInt(dogArray.get(num1).getCount()) < Integer
							.parseInt(dogArray.get(num2).getCount())) {
						count++;
						totalCount++;
						System.out.println("You guessed correctly " + count + " out of " + totalCount + " times.");
					} else if (Integer.parseInt(dogArray.get(num1).getCount()) == Integer
							.parseInt(dogArray.get(num2).getCount())) {
						System.out.println("They are the same popularity");
					}
					System.out.println("Play again?");
					playGameAnswer = in1.next();

				}
			} else {
				System.out.println("Goodbye");
				System.exit(0);
			}
			System.out.println("You guessed " + count + " out of " + totalCount + " times.");
			System.out.println("Goodbye!");
		
		}
	}
}
