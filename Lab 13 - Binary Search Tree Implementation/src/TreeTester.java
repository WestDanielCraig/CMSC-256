import java.util.Iterator;
import TreePackage.BinarySearchTree;

/**
   This program tests the binary search tree class.
*/
public class TreeTester { 
   public static void main(String[] args) {  
      BinarySearchTree<String> tree = new BinarySearchTree<String>();
      tree.add("Megan");
      tree.add("Kathy");
      tree.add("Sean");
      tree.add("Brett");
      tree.add("Lance");
      tree.add("Pat");
      tree.add("Whitney");
      tree.add("Chad");
      tree.add("Maria");
      tree.add("Reba");
      tree.add("Zak");
      tree.add("Britany");
      tree.add("Doug");
      tree.add("Derek");
      tree.add("Dan");
      tree.add("Dirk");
   
      tree.remove("Pat");   // Removing element with one child
      tree.remove("Sean");  // Removing element with two child
      tree.remove("Kathy"); // Removing element with two children
      tree.remove("Megan"); // Removing root
      
      Iterator<String> iter = tree.getInorderIterator();
      while(iter.hasNext()) {
    	  System.out.println(iter.next());
      }
   }
}

