import java.util.*;

public class StackTest {

	public static void main(String[] args) {
		int n = 50;
		
		Stack<Integer> stack = new Stack<Integer>();
		while (n > 0) {
			stack.push(n % 2);
			n = (n / 2);
			System.out.println(stack.peek());
		}
		
		while (!stack.isEmpty()) {
			System.out.print(stack.pop());
		}
	}

}
