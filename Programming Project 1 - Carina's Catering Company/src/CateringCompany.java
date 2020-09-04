import java.util.*;
import java.io.*;

//Catering Company class to hold main method
public class CateringCompany {

	public static void main(String[] args) {

		FoodItem menuItem = new FoodItem();
		Menu todaysMenu = new Menu();

		//Print heading for name and class
		printHeading();

		
		try {
			File inputFile = new File(args[0]);
			Scanner in = new Scanner(inputFile);

			for (int i = 1; in.hasNextLine(); i++) {

				String line = in.nextLine();
//				System.out.println(line);
				String[] menuData = line.split("\\s*:\\s*");

				if (menuData.length != 4) {
					System.out.println("File item " + i + " did not have valid components and will not be on the menu");
					continue;
				}
				if (menuData[0].length() == 0 || menuData[1].length() == 0 || menuData[2].length() == 0
						|| menuData[1].length() == 0) {
					System.out.println("File item " + i
							+ " had incomplete data please check your file, this item will not be on the menu");
					continue;
				}
				if (!menuData[3].equals("[Y]") || !menuData[3].equals("yes"))
					if (menuData[3].equalsIgnoreCase("n")) {
						System.out.println("File item " + i + " will not be on today's menu");
						continue;
					} else if (menuData[3].equalsIgnoreCase("No")) {
						System.out.println("File item " + i + " will not be on today's menu");
						continue;
					} /*
						 * else { System.out.println("File item " + i +
						 * " did not have valid components and will not be on the menu"); continue; }
						 */

				menuItem.setFoodName(menuData[0]);

				if (menuData[1].equals("savory")) {
					menuItem.setFoodCatagory(FoodItem.FoodCatagory.SAVORY);
				} else if (menuData[1].equals("sweet pie")) {
					menuItem.setFoodCatagory(FoodItem.FoodCatagory.SWEETPIE);
				} else if (menuData[1].equals("sweet treat")) {
					menuItem.setFoodCatagory(FoodItem.FoodCatagory.SWEETTREAT);
				} else {
					System.out.println("File item " + i + " did not have valid components and will not be on the menu");
					continue;
				}
				menuItem.setDesignationType(menuData[2]);
				
				System.out.println(menuItem.toString());
				todaysMenu.menuBuilder(menuItem);
				menuItem = new FoodItem();
			}
			todaysMenu.toString();


			in.close();
		} catch (FileNotFoundException e) {
		}

		/*
		 * if (args.length == 0) System.out.println("No arguments to echo"); else { for
		 * (int i = 0; i < args.length; i++) System.out.println(args[i] + "");
		 * System.out.println(); } System.out.println(Arrays.toString(args));
		 */
	}

	private static void printHeading() {
		System.out.println();
		System.out.println("Daniel C. West"); // Name of Program Author
		System.out.println("CMSC 256 Fall 2018"); // Class, Section, and Semester
		System.out.println("Programming Project 1"); // Program Number
		System.out.println("Carina's Catering Company"); // Program Name
		System.out.println(); // Line created for console display appeal
	}
}
