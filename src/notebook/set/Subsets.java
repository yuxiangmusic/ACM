package notebook.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

	public static void main(String[] args) {
		System.out.println(subsets(new int[] { 0, 1, 2, 3 }));
		System.out.println(subsets(new int[] { 1, 1, 2, 2 }));
		System.out.println(subsetsWithDup(new int[] { 1, 1, 2, 2, 2 }));
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
		List<List<Integer>> list = new ArrayList<>();
		list.add(new ArrayList<>());
		for (int i = 0, begin = 0, size = list.size(); i < nums.length; i++, begin = size, size = list.size()) {
			for (int j = i > 0 && nums[i] == nums[i - 1] ? begin : 0; j < size; j++) {
				List<Integer> l = new ArrayList<>(list.get(j));
				l.add(nums[i]);
				list.add(l);
			}
		}
		return list;
	}

}
