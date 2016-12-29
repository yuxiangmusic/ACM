package notebook.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Subsets {

	public static void main(String[] args) {
		System.out.println(subsets(new int[] { 1, 2, 3, 4 }));
		System.out.println(subsets(new int[] { 1, 1, 2, 2 }));
		System.out.println(subsetsWithDup(new int[] { 1, 1, 2, 2 }));
	}

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		list.add(new ArrayList<>());
		for (int i = 0, pow = 1; i < nums.length; i++, pow *= 2) {
			for (int j = 0; j < pow; j++) {
				List<Integer> l = new ArrayList<>(list.get(j));
				l.add(nums[i]);
				list.add(l);
			}
		}
		return list;
	}

	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		Set<List<Integer>> set = new LinkedHashSet<>();
		set.add(new ArrayList<>());
		for (int i = 0; i < nums.length; i++) {
			for (List<Integer> l : new ArrayList<>(set)) {
				List<Integer> copy = new ArrayList<>(l);
				copy.add(nums[i]);
				set.add(copy);
			}
		}
		return new ArrayList<>(set);
	}

}
