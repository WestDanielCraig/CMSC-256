
public class QueueClient {

	public static void main(String[] args) {
		CircularQueue<String> q = new CircularQueue<>(4);

		q.enqueue("Red");
		q.enqueue("Yellow");
		q.enqueue("Green");
		q.enqueue("Blue");
		q.enqueue("Purple");
		q.enqueue("Orange");
		q.enqueue("White");
		q.dequeue();
		q.dequeue();
		q.enqueue("Grey");

		while (!q.isEmpty()) {
			System.out.println(q.dequeue());
		}
	}
}
