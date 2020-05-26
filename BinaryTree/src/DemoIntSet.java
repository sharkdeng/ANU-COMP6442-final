import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// DemoIntSet - a program that does a basic run of the IntSet code.
// Eric McCreath

public class DemoIntSet {

	public static void main(String[] args) {
		
		int maxmarks = 0;
		int isinmarks = 0;
		int delmarks = 0;
		
		// max test
		IntSet set = new EmptySet();
		set = set.insert(10);
		if (set.max() == 10) maxmarks++;
		set = set.insert(2);
		if (set.max() == 10) maxmarks++;
		set = set.insert(13);
		if (set.max() == 13) maxmarks++;
		set = set.insert(21);
		if (set.max() == 21) maxmarks++;
		set = set.insert(18);
		if (set.max() == 21) maxmarks++;
		set = set.insert(3);
		if (set.max() == 21) maxmarks++;
		set = set.insert(100);
		if (set.max() == 100) maxmarks++;
		
		
		// isIn tests
		set = new EmptySet();
		set = set.insert(10);
		if (set.isIn(10) && !set.isIn(100)) isinmarks++;
		set = set.insert(2);
		set = set.insert(13);
		set = set.insert(21);
		set = set.insert(18);
		set = set.insert(3);
		
		if (set.isIn(10) && !set.isIn(100)) isinmarks++;
		if (set.isIn(2) && !set.isIn(4)) isinmarks++;
		if (set.isIn(21) && !set.isIn(22)) isinmarks++;
		if (set.isIn(18) && !set.isIn(1)) isinmarks++;
		if (set.isIn(13) && !set.isIn(14)) isinmarks++;
		if (set.isIn(3) && !set.isIn(4)) isinmarks++;
		
		
		set = new EmptySet();
		set = set.insert(10);
		set = set.insert(2);
		set = set.insert(13);
		set = set.insert(21);
		set = set.insert(18);
		set = set.insert(3);
		
		set = set.delete(3);
		if (showsort(set).equals("2,10,13,18,21,")) delmarks++;
		set = set.delete(10);
		if (showsort(set).equals("2,13,18,21,")) delmarks++;
		set = set.delete(13);
		if (showsort(set).equals("2,18,21,")) delmarks++;
		set = set.delete(21);
		if (showsort(set).equals("2,18,")) delmarks++;
		set = set.delete(2);
		if (showsort(set).equals("18,")) delmarks++;
		set = set.delete(18);
		if (showsort(set).equals("")) delmarks++;
		
		System.out.println(maxmarks + " " + isinmarks + " " + delmarks);
		
		
	}

	private static String showsort(IntSet s) {
		List<Integer> e = s.elements();
		Collections.sort(e);
		return show(e);
	}
	
	private static String show(List<Integer> sort) {
		String res = "";
		for (Integer i : sort) res += i + ",";
		return res;
	}
}
