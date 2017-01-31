import static org.junit.Assert.assertEquals;

import java.util.Scanner;
/*
=====
1
4 1

 */

/**
 * <a href="http://www.spoj.com/problems/PERMUT1/">
 * 
 * Permutations</a>
 */
public class Permutations {
	public static void test() {
		assertEquals(3, solve(4, 1));
	}

	public static void main(String[] args) {
		test();

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++)
			System.out.println(solve(sc.nextInt(), sc.nextInt()));
		sc.close();
	}

	// DP[n][k] := number of n-permutations with k inversions
	// +-----+---------------+
	// | n\k | 0 1 2 3 4 5 6 |
	// +-----+---------------+
	// | 0 - | 0 0 0 0 0 0 0 |
	// | 1 - | 1 0 0 0 0 0 0 |
	// | 2 - | 1 1 0 0 0 0 0 |
	// | 3 - | 1 2 2 1 0 0 0 |
	// | 4 - | 1 3 5 6 5 3 1 |
	// +-----+---------------+
	public static int solve(int n, int k) {
		int dp[][] = new int[n + 1][k + 1];
		for (int i = 1; i <= n; i++)
			dp[i][0] = 1;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= Math.min(k, i * (i - 1) / 2); j++)
				dp[i][j] += dp[i][j - 1] + dp[i - 1][j] - (j < i ? 0 : dp[i - 1][j - i]);
		return dp[n][k];
	}
}
