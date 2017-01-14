package notebook.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

	public static void main(String[] args) {
		System.out.println(subsets(0, 1, 2, 3));
		System.out.println(subsets(0, 1, 2, 1));
		System.out.println(subsetsWithDup(0, 1, 2, 1));
	}

	public static List<List<Integer>> subsets(int... nums) {
		List<List<Integer>> list = new ArrayList<>();
		list.add(new ArrayList<>());
		for (int i = 0, size = list.size(); i < nums.length; i++, size = list.size()) {
			for (int j = 0; j < size; j++) {
				List<Integer> l = new ArrayList<>(list.get(j));
				l.add(nums[i]);
				list.add(l);
			}
		}
		return list;
	}

	public static List<List<Integer>> subsetsWithDup(int... nums) {
		Arrays.sort(nums);
		List<List<Integer>> subsets = new ArrayList<>();
		subsets.add(new ArrayList<>());
		for (int i = 0, presize = 0, cursize = subsets.size(); i < nums.length; i++, presize = cursize, cursize = subsets.size()) {
			for (int j = i > 0 && nums[i] == nums[i - 1] ? presize : 0; j < cursize; j++) {
				List<Integer> subset = new ArrayList<>(subsets.get(j));
				subset.add(nums[i]);
				subsets.add(subset);
			}
		}
		return subsets;
	}

}
