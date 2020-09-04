/** 
  Determines if three doubles can be sides of triangle. 
*/
public class Triangle  {

   /** Length of side 1. */
   private double sideA;

   /** Length of side 2. */
   private double sideB;

   /** Length of side 3. */
   private double sideC;

   /**
    * Creates a Triangle.
    *
    * @param aIn length of side 1.
    * @param bIn length of side 2.
    * @param cIn length of side 3.
    */
   public Triangle(double aIn, double bIn, double cIn) {
      sideA = aIn;
      sideB = bIn;
      sideC = cIn;

      if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
         throw new IllegalArgumentException("Sides: " + sideA + " " + sideB
            + " " + sideC
            + " -- each must be greater than zero.");
      }
      if ((sideA >= sideB + sideC) || (sideB >= sideA + sideC) || (sideC >= sideA + sideB)) {
         throw new IllegalArgumentException("Sides: "
            + sideA + " " + sideB + " " + sideC
            + " -- not a triangle.");
      }
   }

   /**
    * Classifies a triangle based on the lengths of the three sides.
    *
    * @return the triangle classification.
    */
   public String classify() {
      String result = "not a triangle";

      if ((sideA == sideB) && (sideB == sideC)) {
         result = "equilateral";
      }
      else if ((sideA != sideB) && (sideA != sideC) && (sideB != sideC)) {
         result = "scalene";
      }
      else {
         result = "isosceles";
      }
   
      return result;
   }
  
   public static void main(String[] args) {
	   int counter = 1;
	   try
	   {
			Triangle test1 = new Triangle(3.0, 4.0, 5.0);
			System.out.println("Test #" + counter + " Sides: 3.0, 4.0, 5.0 Result: " + test1.classify());
	   }
	   catch(IllegalArgumentException e)
	   {
		   e.printStackTrace();
	   }
	   counter++;
	   try
	   {
			Triangle test2 = new Triangle(3.0, 3.0, 3.0);
			System.out.println("Test #" + counter + " Sides: 3.0, 3.0, 3.0 Result: " + test2.classify());
	   }
	   catch(IllegalArgumentException e)
	   {
		   e.printStackTrace();
	   }
	   counter++;
	   try
	   {
			Triangle test3 = new Triangle(3.0, 3.0, 5.0);
			System.out.println("Test #" + counter + " Sides: 3.0, 3.0, 5.0 Result: " + test3.classify());
	   }
	   catch(IllegalArgumentException e)
	   {
		   e.printStackTrace();
	   }
	   counter++;
	   try
	   {
			Triangle test4 = new Triangle(0, 0, 0);
			System.out.println("Test #" + counter + " Sides: 0, 0, 0 Result: " + test4.classify());
	   }
	   catch(IllegalArgumentException e)
	   {
		   e.printStackTrace();
	   }
	   counter++;
	   try
	   {
			Triangle test5 = new Triangle(0, 4.0, 5.0);
			System.out.println("Test #" + counter + " Sides: 0, 4.0, 5.0 Result: " + test5.classify());
	   }
	   catch(IllegalArgumentException e)
	   {
		   e.printStackTrace();
	   }
	   counter++;
	   try
	   {
			Triangle test6 = new Triangle(3.0, 4.0, 0);
			System.out.println("Test #" + counter + " Sides: 3.0, 4.0, 0 Result: " + test6.classify());
	   }
	   catch(IllegalArgumentException e)
	   {
		   e.printStackTrace();
	   }
	   counter++;
	   try
	   {
			Triangle test7 = new Triangle(3.0, 0, 5.0);
			System.out.println("Test #" + counter + " Sides: 3.0, 0, 5.0 Result: " + test7.classify());
	   }
	   catch(IllegalArgumentException e)
	   {
		   e.printStackTrace();
	   }
	   counter++;
	   try
	   {
			Triangle test8 = new Triangle(1000.0, 4.0, 5.0);
			System.out.println("Test #" + counter + " Sides: 1000.0, 4.0, 5.0 Result: " + test8.classify());
	   }
	   catch(IllegalArgumentException e)
	   {
		   e.printStackTrace();
	   }
	   counter++;
	   try
	   {
			Triangle test9 = new Triangle(3.0, 4000.0, 5.0);
			System.out.println("Test #" + counter + " Sides: 3.0, 4000.0, 5.0 Result: " + test9.classify());
	   }
	   catch(IllegalArgumentException e)
	   {
		   e.printStackTrace();
	   }
	   counter++;
	   try
	   {
			Triangle test10 = new Triangle(3.0, 4.0, 5000.0);
			System.out.println("Test #" + counter + " Sides: 3.0, 4.0, 5000.0 Result: " + test10.classify());
	   }
	   catch(IllegalArgumentException e)
	   {
		   e.printStackTrace();
	   }
	   counter++;
   }
}
