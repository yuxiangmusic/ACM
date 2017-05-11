package notebook.array;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import notebook.Notebook;

public class LongestIncreasingSubsequence {

	/**
	 * O(n*log(n)) DP solution
	 */
	@Notebook
	public static int[] getLIS_log(int... arr) {
		if (arr.length == 0)
			return new int[0];
		int len = 0, dp[] = new int[arr.length];
		int p[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int l = 0, h = len - 1;
			while (l <= h) {
				int mid = (l + h) >>> 1;
				if (arr[dp[mid]] == arr[i]) {
					l = mid;
					break;
				} else if (arr[dp[mid]] < arr[i]) {
					l = mid + 1;
				} else {
					h = mid - 1;
				}
			}
			dp[l] = i;
			if (len == l)
				len++;
			if (l > 0)
				p[i] = dp[l - 1];
		}
		int lis[] = new int[len], arrIndex = dp[len - 1];
		for (int i = len - 1; i >= 0; i--) {
			lis[i] = arr[arrIndex]; // or we can return indexes
			arrIndex = p[arrIndex];
		}
		return lis;
	}

	/**
	 * classic DP solution O(n^2)
	 */
	@Notebook
	public static int[] getLIS_quadratic(int... arr) {
		if (arr.length == 0)
			return new int[0];
		int l[] = new int[arr.length]; // l[i] := length of LIS ending at i
		int p[] = new int[arr.length]; // p[i] := predecessor of i
		int maxIndex = 0; // index of max LIS
		for (int i = 0; i < arr.length; i++) {
			l[i] = 1;
			p[i] = -1;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && l[i] < l[j] + 1) {
					l[i] = l[j] + 1;
					p[i] = j;
				}
			}
			if (l[i] > l[maxIndex])
				maxIndex = i;
		}
		int lis[] = new int[l[maxIndex]], arrIndex = maxIndex;
		for (int i = lis.length - 1; i >= 0; i--) {
			lis[i] = arr[arrIndex]; // or we can return indexes
			arrIndex = p[arrIndex];
		}
		return lis;
	}

	/**
	 * @return length of LIS
	 */
	@Notebook
	public static int lenLIS_log(int... arr) {
		// DP[i] := smallest tail of LIS of length (i + 1)
		int[] dp = new int[arr.length];
		int len = 0; // effective length of DP
		for (int n : arr) {
			int i = Arrays.binarySearch(dp, 0, len, n);
			if (i < 0)
				i = ~i;
			dp[i] = n;
			if (i == len)
				len++;
		}
		return len;
	}

	/**
	 * @return length of LIS
	 */
	@Notebook
	public static int lenLIS_quadratic(int... arr) {
		if (arr.length == 0)
			return 0;
		// DP[i] := length of LIS ending at i
		int dp[] = new int[arr.length];
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++)
				if (arr[j] < arr[i])
					dp[i] = Math.max(dp[i], 1 + dp[j]);
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	/**
	 * For testing
	 */
	public int lengthOfLIS(int[] nums) {
		int ans_log = lenLIS_log(nums);
		int ans_quadratic = lenLIS_quadratic(nums);

		assertTrue(ans_log == ans_quadratic);

		return ans_log;
	}

	@Test
	public void test() {
		int[] arr = ArrayUtil.getRandomArray(1000);
		{
			long begin = System.nanoTime();
			int[] lis = getLIS_log(arr);
			System.out.println(Arrays.toString(lis));
			System.out.println("Runtime: " + (System.nanoTime() - begin));
		}
		{
			long begin = System.nanoTime();
			int[] lis = getLIS_quadratic(arr);
			System.out.println(Arrays.toString(lis));
			System.out.println("Runtime: " + (System.nanoTime() - begin));
		}
		{
			long begin = System.nanoTime();
			System.out.println("LIS length = " + lenLIS_log(arr));
			System.out.println("Runtime: " + (System.nanoTime() - begin));
		}
		{
			long begin = System.nanoTime();
			System.out.println("LIS length = " + lenLIS_quadratic(arr));
			System.out.println("Runtime: " + (System.nanoTime() - begin));
		}
	}

	@Test
	public void test_3() {
		int[] arr = { 4, 10, 4, 3, 8, 9 };
		assertEquals(3, lengthOfLIS(arr));
	}

	@Test
	public void test_4() {
		int[] arr = { 10, 9, 2, 5, 3, 7, 101, 18 };
		assertEquals(4, lengthOfLIS(arr));
	}

	@Test
	public void test_empty() {
		int[] arr = {};
		assertEquals(0, lengthOfLIS(arr));
	}
}
