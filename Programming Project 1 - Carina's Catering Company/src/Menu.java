import java.text.SimpleDateFormat;
import java.util.*;

import java.io.*;

//Menu class with private variables
public class Menu {
	private ArrayList<FoodItem> savoryItems;
	private ArrayList<FoodItem> sweetPieItems;
	private ArrayList<FoodItem> sweetTreatItems;

	//Format and variable to hold date
	String pattern = "M/dd/yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String date = simpleDateFormat.format(new Date());

	//constructor for Menu class
	public Menu() {
		savoryItems = new ArrayList<FoodItem>();
		sweetPieItems = new ArrayList<FoodItem>();
		sweetTreatItems = new ArrayList<FoodItem>();
	}

	//Method the build the array lists, but I think this is where I was going wrong
	public void menuBuilder(FoodItem menuItem) {
		System.out.println(menuItem.getFoodCatagory().toString());
		if (menuItem.getFoodCatagory().equals(FoodItem.FoodCatagory.SAVORY)) {
			savoryItems.add(menuItem);
		} else if (menuItem.getFoodCatagory().equals(FoodItem.FoodCatagory.SWEETPIE)) {
			sweetPieItems.add(menuItem);
		} else if (menuItem.getFoodCatagory().equals(FoodItem.FoodCatagory.SWEETTREAT)) {
			sweetTreatItems.add(menuItem);
		}
		return;
	}

	/**
	 * @return the savoryItems
	 */
	public ArrayList<FoodItem> getSavoryItems() {
		return savoryItems;
	}

	/**
	 * @param savoryItems
	 *            the savoryItems to set
	 */
	public void setSavoryItems(ArrayList<FoodItem> savoryItems) {
		this.savoryItems = savoryItems;
	}

	/**
	 * @return the sweetPieItems
	 */
	public ArrayList<FoodItem> getSweetPieItems() {
		return sweetPieItems;
	}

	/**
	 * @param sweetPieItems
	 *            the sweetPieItems to set
	 */
	public void setSweetPieItems(ArrayList<FoodItem> sweetPieItems) {
		this.sweetPieItems = sweetPieItems;
	}

	/**
	 * @return the sweetTreatItems
	 */
	public ArrayList<FoodItem> getSweetTreatItems() {
		return sweetTreatItems;
	}

	/**
	 * @param sweetTreatItems
	 *            the sweetTreatItems to set
	 */
	public void setSweetTreatItems(ArrayList<FoodItem> sweetTreatItems) {
		this.sweetTreatItems = sweetTreatItems;
	}

	//toString method to construct the menu, but there is also where I had difficulties, my arrays were not correct and it was messing everything up
	public String toString() {
		String output = "";
		try {
			PrintWriter out = new PrintWriter("Menu4Today.txt");

			out.println("Here is what we are starting with today, " + date + "\n");
			out.printf("Savory Hand Pies");
			out.printf("Sweet Pie by the Slice");
			out.println("Other Sweet Treats");

			for (int i = 0; i < savoryItems.size(); i++) {
				out.println(savoryItems.get(i).toString());
				System.out.println(savoryItems.get(i).toString());
			}
			out.close();

			// out.print(output);
		} catch (FileNotFoundException e) {

		}
		return output;

	}

}
