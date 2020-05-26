import java.util.ArrayList;
import java.util.List;

//EmptySet - an integer set that is empty
//Eric McCreath 2018


public class EmptySet extends IntSet {

	@Override
	Integer max() {
		return null;
	}

	@Override
	boolean isIn(int value) {
		return false;
	}
 
	@Override
	IntSet insert(int value) {
		return new NonEmptySet(value); 
	}

	@Override
	IntSet delete(int value) {
		return this;
	}

	@Override
	boolean isEmpty() {
		return true;
	}

	@Override
	List<Integer> elements() {
		return new ArrayList<Integer>();
	}
}
