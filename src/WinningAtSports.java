import static org.junit.Assert.assertEquals;

import java.util.Scanner;

/*
=====
5
2-1
3-1
3-2
10-5
1000-500

 */

/**
 * <a href="http://www.spoj.com/FBH15R1/problems/FBH15R1C/">
 * 
 * Winning at Sports</a>
 */
// XXX not yet judged
public class WinningAtSports {
	public static void test() {
		assertEquals(1, stressfree(2, 1));
		assertEquals(2, stressfree(3, 1));
		assertEquals(2, stressfree(3, 2));
		assertEquals(1001, stressfree(10, 5));
		assertEquals(70047606, stressfree(1000, 500));

		assertEquals(1, stressful(2, 1));
		assertEquals(1, stressful(3, 1));
		assertEquals(2, stressful(3, 2));
		assertEquals(42, stressful(10, 5));
		assertEquals(591137401, stressful(1000, 500));
	}

	public static void main(String[] args) {
		test();

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			String scores[] = sc.next().split("-");
			int a = Integer.parseInt(scores[0]);
			int b = Integer.parseInt(scores[1]);
			System.out.printf("Case #%d: %d %d\n", t, stressfree(a, b), stressful(a, b));
		}
		sc.close();
	}

	static final int mod = 1000000007;

	public static int stressfree(int a, int b) {
		int[][] dp = new int[a + 1][b + 1]; // DP[i][j] := number of ways to achieve i-j victory
		for (int i = 1; i <= a; i++)
			dp[i][0] = 1;
		for (int i = 1; i <= a; i++)
			for (int j = 1; j < i && j <= b; j++)
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % mod;
		return dp[a][b];
	}

	public static int stressful(int a, int b) {
		int[][] dp = new int[b + 1][b + 1]; // DP[i][j] := number of ways to achieve i-j victory
		for (int j = 1; j <= b; j++)
			dp[0][j] = 1;
		for (int i = 1; i <= b; i++)
			for (int j = i; j <= b; j++)
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % mod;
		return dp[b][b]; // a-b is equivalent to b-b
	}
}
