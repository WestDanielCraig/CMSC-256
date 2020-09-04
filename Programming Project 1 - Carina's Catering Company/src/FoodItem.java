
//Food item class to construct food item objects to help out in the menu array and used for the menu construction
public class FoodItem {
	private String foodName;
	private FoodCatagory foodCatagory;
	private String designationType;

	//FoodItem default constructor
	public FoodItem() {
		foodName = "";
		foodCatagory = FoodCatagory.SWEETTREAT;
		designationType = "";
	}
    //FoodItem method
	public FoodItem(String foodName, FoodCatagory foodCatagory, String designationType) {
		this.foodName = foodName;
		this.foodCatagory = foodCatagory;
		this.designationType = designationType;
	}

	/**
	 * @return the foodName
	 */
	public String getFoodName() {
		return foodName;
	}

	/**
	 * @param foodName
	 *            the foodName to set
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	/**
	 * @return the foodCatagory
	 */
	public FoodCatagory getFoodCatagory() {
		return foodCatagory;
	}

	/**
	 * @param foodCatagory
	 *            the foodCatagory to set
	 */
	public void setFoodCatagory(FoodCatagory foodCatagory) {
		this.foodCatagory = foodCatagory;
	}

	/**
	 * @return the designationType
	 */
	public String getDesignationType() {
		return designationType;
	}

	/**
	 * @param designationType
	 *            the designationType to set
	 */
	public void setDesignationType(String designationType) {
		this.designationType = designationType;
	}

	//toString method which would have been used to help build the other toString method inside the Menu Class
	public String toString() {
		String output = "";

		if (designationType.equals("none")) {
			output = foodName;
			return output;
		} else {
			output = foodName + " (" + designationType + ")";
			return output;
		}
	}

	//Enum method to hold variables, also gave me a lot of trouble
	public enum FoodCatagory {
		SAVORY, SWEETPIE, SWEETTREAT;
		
		/*private final String name;
		
		FoodCatagory(final String name) {
			this.name = name;
		}
		
		public String toString() {
			return name;*/
		//}		
	}
}
