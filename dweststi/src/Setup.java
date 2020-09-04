import bridges.connect.Bridges;
import bridges.base.Array;
import bridges.base.Element;

public class Setup {

	public static void main(String[] args) throws Exception {

		/* Initialize a Bridges connection with your credentials */
		/* TODO: plug your own BRIDGES credentials */
		Bridges bridges = new Bridges(0, "dweststi", "531252251753");

		/* Set an assignment title */
		bridges.setTitle("Daniel West");

		/* Set up the array dimensions, allocate an Array of Elements */
		/* TODO: Make an array of size 10 */
		int arraySize = 10;
		Array<Integer> arr = new Array<Integer>(arraySize);

		int count = 0;

		for (int i = 0; i < arraySize; i++) {

			/* Populate the array with integers */
			/* TODO: Make the array store square numbers */
			arr.getElement(i).setValue(count*count);
			/* set the value as a Label */
			arr.getElement(i).setLabel(String.valueOf(count*count));
			

			

			count++;
		}
		arr.getElement(0).getVisualizer().setColor("gold");
		arr.getElement(1).getVisualizer().setColor("aquamarine");
		arr.getElement(2).getVisualizer().setColor("khaki");
		arr.getElement(3).getVisualizer().setColor("bisque");
		arr.getElement(4).getVisualizer().setColor("cyan");
		arr.getElement(5).getVisualizer().setColor("orange");
		arr.getElement(6).getVisualizer().setColor("maroon");
		arr.getElement(7).getVisualizer().setColor("olive");
		arr.getElement(8).getVisualizer().setColor("chartreuse");
		arr.getElement(9).getVisualizer().setColor("red");
		
		/* Tell BRIDGES which data structure to visualize */
		bridges.setDataStructure(arr);		
		
		/* Visualize the Array */
		bridges.visualize();

	}
}
