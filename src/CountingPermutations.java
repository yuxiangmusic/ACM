import static org.junit.Assert.assertEquals;

import java.util.Scanner;

/*
=====
3
3 1
2 1
3 2

=====
1
200 300

=====
1
2000 3000

 */

/**
 * <a href="http://www.spoj.com/problems/BDOI16C/">
 * 
 * Counting Permutations</a>
 */
public class CountingPermutations {

	public static void test() {
		assertEquals(5, solve(3, 1));
		assertEquals(1, solve(2, 1));
		assertEquals(3, solve(3, 2));
	}

	public static void main(String[] args) {
		test();

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			long sol = solve(sc.nextInt(), sc.nextInt());
			System.out.printf("Case %d: %d\n", t, sol);
		}
		sc.close();
	}

	static final int mod = 10007;

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
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= Math.min(k, i * (i - 1) / 2); j++) {
				dp[i][j] += mod + dp[i][j - 1] + dp[i - 1][j] - (j < i ? 0 : dp[i - 1][j - i]);
				dp[i][j] %= mod;
			}
		}
		int total = 0;
		for (int j = 0; j < k; j++) {
			total += dp[n][j];
			total %= mod;
		}
		return (factorial(n) - total + mod) % mod;
	}

	static int factorial(int n) {
		int f = 1;
		while (n > 0) {
			f *= n--;
			f %= mod;
		}
		return f;
	}

}
