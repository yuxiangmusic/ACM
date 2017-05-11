package notebook.bit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Subsets {

	@Test
	public void test() {
		System.out.println(subsets("A", "B", "C"));
	}

	/**
	 * This should only work for small set
	 * 
	 * @see notebook.set.Subsets
	 */
	public static List<List<Object>> subsets(Object... arr) {
		List<List<Object>> subsets = new ArrayList<>(1 << arr.length);
		for (int i = 0; i < 1 << arr.length; i++) {
			List<Object> subset = new ArrayList<>();
			for (int j = 0; j < arr.length; j++)
				if ((i >> j & 1) == 1)
					subset.add(arr[j]);
			subsets.add(subset);
		}
		return subsets;
	}

}
