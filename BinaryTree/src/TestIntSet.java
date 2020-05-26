import java.util.List;

// DemoIntSet - a program that does a basic run of the IntSet code.
// Eric McCreath

public class TestIntSet {

	public static void main(String[] args) {
		IntSet set = new EmptySet();
		System.out.println("Adding : 4, 12, 3, 5, 1, 7");
		set = set.insert(4);
		set = set.insert(12);
		set = set.insert(3);
		set = set.insert(5);
		set = set.insert(1);
		set = set.insert(7);
		System.out.println("The set contains : " + set.elements());
		System.out.println("Does the set contain 11 ? " + set.isIn(11));
		System.out.println("Does the set contain 7 ? " + set.isIn(7));
		System.out.println("Delete : 4");
		set = set.delete(4);
		System.out.println("The set contains : " + set.elements());
		System.out.println("Delete : 7");
		set = set.delete(7);
		System.out.println("The set contains : " + set.elements());
		System.out.println("The max number is : " + set.max());
	}
}
