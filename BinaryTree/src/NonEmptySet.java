import java.util.ArrayList;
import java.util.List;

//NonEmptySet - an integer set that is represented by a binary search tree.
// In this implementation once a binary tree is constructed it does not change.  
// Thus methods that modify a tree return the new modified version of the tree.  
//Eric McCreath 2018


public class NonEmptySet extends IntSet {

	int value;
	IntSet left, right;
	
	public NonEmptySet(int v) {
		value = v;
		left = right = new EmptySet();
	}

	public NonEmptySet(IntSet l, int v, IntSet r) {
		left = l;
		value = v;
		right = r;
	}

	// max - returns the maximum value of the set
	@Override
	Integer max() {
		// add your solution to max here

		// if right has elements
		if (!right.elements().isEmpty()){
			return right.max();
		} else {
			return value;
		}
	}

	
	// isIn - this methods determines if "v" "is in" the set. 
	@Override
	boolean isIn(int v) {
		// add your solution to isIn here

		if (value != v &&
				!left.isIn(v) &&
				!right.isIn(v)){
			return false;
		}
		return true;
	}

	@Override
	IntSet insert(int v) {
		if (v == value) return this;
		else if (v < value) return new NonEmptySet(left.insert(v), value, right);
		else return new NonEmptySet(left, value, right.insert(v));
	}


//	@Override
//	IntSet delete(int v) {
//		if (value == v) {
//			if (left.isEmpty()) {
//				// left is Empty
//				return right;
//
//			} else if (right.isEmpty()){
//				// right is Empty
//				return left;
//
//			} else {
//				int b = min(right);
//
//				return new NonEmptySet(left, b, right.delete(b));
//			}
//
//		} else if (left.isIn(v)) {
//			// in left branch
//		} else
//		return null;
//	}

	@Override
	IntSet delete(int v) {
		// add your solution to delete here

		// root
		// left as new root

		if (value == v) {

			if (left.isEmpty()) {
				//case 1: no children
				// don't know how to deal with this case
				return right;


			} else if (right.isEmpty()) {
				// case 2: only left
				return left;

			} else {
				// case 4
				// 1 - right min as new root
				int b = min(right);

				return new NonEmptySet(left, b, right.delete(b));
//				// 2 - insert right again
//				IntSet tmp = right;
//				right = new EmptySet();
//				for (Integer ele: tmp.elements()) {
//					if (ele != value){
//						right = right.insert(ele);
//					}
//				}
			}
		} else if (left.isIn(v)) {
			return new NonEmptySet(left.delete(v), value, right);

		} else if (right.isIn(v)) {
			return new NonEmptySet(left, value, right.delete(v));
		}

		return this;
	}


	@Override
	boolean isEmpty() {
		return false;
	}


	public Integer min(IntSet node){
		// if right has elements

		Integer tmp = Integer.MAX_VALUE;
		for (Integer ele: node.elements()){
			if (ele < tmp){
				tmp = ele;
			}
		}
		return tmp;

	}


	@Override
	List<Integer> elements() {
		ArrayList<Integer> res = new ArrayList<Integer>();
		res.addAll(left.elements());
		res.add(value);
		res.addAll(right.elements());
		return res;
	}

}
