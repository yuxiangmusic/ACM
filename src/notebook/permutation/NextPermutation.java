package notebook.permutation;

import static notebook.array.ArrayUtil.reverse;
import static notebook.array.ArrayUtil.swap;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import notebook.Notebook;

public class NextPermutation {

	@Test
	public void test_151() {
		int[] actual = new int[] { 1, 5, 1 };
		int[] expected = new int[] { 5, 1, 1 };
		nextPermutation(actual);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test_4202320() {
		int[] actual = new int[] { 4, 2, 0, 2, 3, 2, 0 };
		int[] expected = new int[] { 4, 2, 0, 3, 0, 2, 2 };
		nextPermutation(actual);
		assertArrayEquals(expected, actual);
	}

	@Notebook
	public void nextPermutation(int... nums) {
		int i = nums.length - 1;
		while (i > 0 && nums[i] <= nums[i - 1])
			i--;
		if (i == 0)
			reverse(nums, 0, nums.length - 1);
		else {
			int j = nums.length - 1;
			while (nums[j] <= nums[i - 1])
				j--;
			swap(nums, i - 1, j);
			reverse(nums, i, nums.length - 1);
		}
	}

}
