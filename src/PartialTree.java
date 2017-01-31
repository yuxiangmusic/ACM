import static org.junit.Assert.assertEquals;

import java.util.Scanner;

/*
=====
2
3
2 1
4
5 1 4

 */

/**
 * <a href="https://icpcarchive.ecs.baylor.edu/external/71/7190.pdf">
 * 
 * Partial Tree</a>
 */
public class PartialTree {

	public static void test() {
		assertEquals(5, solve(3, 2, 1));
		assertEquals(19, solve(4, 5, 1, 4));
	}

	public static void main(String[] args) {
		test();

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			int n = sc.nextInt();
			int[] f = new int[n - 1];
			for (int i = 0; i < f.length; i++)
				f[i] = sc.nextInt();
			System.out.println(solve(n, f));
		}
		sc.close();
	}

	public static long solve(int n, int... f) {
		// each node must have at least 1 degree
		long[] dp = new long[n - 1]; // DP[i] := max coolness for (i + n) degree
		dp[0] = n * f[0]; // f(i) = f[i-1] off by 1
		for (int i = 1; i <= n - 2; i++)
			for (int j = 0; j <= i; j++) // replace one of 1 degree to (j + 1) degree
				dp[i] = Math.max(dp[i], dp[i - j] + f[j] - f[0]);
		return dp[n - 2];
	}

}
