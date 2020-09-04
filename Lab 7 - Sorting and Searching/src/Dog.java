import java.util.Comparator;

public class Dog implements Comparable<Dog> {

	private String dogName;
	private String count;

	public Dog() {
		dogName = "";
		count = "";
	}

	public Dog(String dogName, String count) {
		this.dogName = dogName;
		this.count = count;
	}

	public String getDogName() {
		return dogName;
	}

	public void setDogName(String dogName) {
		this.dogName = dogName;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String toString() {
		String output = dogName + count;
		return output;
	}

	@Override
	public int compareTo(Dog o) {
		return this.getDogName().compareTo(o.getDogName());
	}

	public static Comparator<Dog> nameComparator = new Comparator<Dog>() {
		@Override
		public int compare(Dog dog1, Dog dog2) {
			return dog1.getDogName().compareTo(dog2.getDogName());
		}
	};
}
