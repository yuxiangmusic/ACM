package notebook.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import notebook.Notebook;

public class Subsets {

	public static void main(String[] args) {
		System.out.println(subsets(0, 1, 2, 3));
		System.out.println(subsets(0, 0, 1, 1));
		System.out.println(subsetsWithDup(0, 0, 1, 1));
	}

	@Notebook
	public static List<List<Integer>> subsets(int... nums) {
		List<List<Integer>> subsets = new ArrayList<>(Arrays.asList(new ArrayList<>()));
		for (int i = 0, size = subsets.size(); i < nums.length; i++, size = subsets.size()) {
			for (int j = 0; j < size; j++) {
				List<Integer> l = new ArrayList<>(subsets.get(j));
				l.add(nums[i]);
				subsets.add(l);
			}
		}
		return subsets;
	}

	/**
	 * The given set may contain duplicates. <br>
	 * The solution must not contain duplicates.
	 */
	@Notebook
	public static List<List<Integer>> subsetsWithDup(int... nums) {
		Arrays.sort(nums);
		List<List<Integer>> subsets = new ArrayList<>(Arrays.asList(new ArrayList<>()));
		for (int i = 0, presize = 0, cursize = subsets
				.size(); i < nums.length; i++, presize = cursize, cursize = subsets.size()) {
			for (int j = i == 0 || nums[i] != nums[i - 1] ? 0 : presize; j < cursize; j++) {
				List<Integer> subset = new ArrayList<>(subsets.get(j));
				subset.add(nums[i]);
				subsets.add(subset);
			}
		}
		return subsets;
	}

}
