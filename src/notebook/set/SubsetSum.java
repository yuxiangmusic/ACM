package notebook.set;

import static org.junit.Assert.*;

import org.junit.Test;

import notebook.Notebook;

public class SubsetSum {

	@Test
	public void test() {
		assertEquals(4, subsetSum(new int[] { 1, 2, 3, 4, 5, 6 }, 7));
	}

	/**
	 * @return number of subsets whose sum is k
	 */
	@Notebook
	public int subsetSum(int[] nums, int k) {
		int[] dp = new int[k + 1];
		dp[0] = 1;
		for (int n : nums)
			for (int i = k; i >= n; i--)
				dp[i] += dp[i - n];
		return dp[k];
	}

}
