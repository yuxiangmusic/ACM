import static org.junit.Assert.assertEquals;

import java.util.Scanner;

/*
=====
abababab
bcbb

 */

/**
 * <a href="http://www.spoj.com/problems/LCS0/">
 * 
 * Longest Common Subsequence</a>
 */
// XXX judged but failed
public class LongestCommonSubsequence {
	public static void test() {
		assertEquals(3, solve("abababab", "bcbb"));
	}

	public static void main(String[] args) {
		test();

		Scanner sc = new Scanner(System.in);
		System.out.println(solve(sc.next(), sc.next()));
		sc.close();
	}

	public static int solve(String s, String t) {
		// DP[i][j] := LCS(s.substring(i), t.substring(j))
		int dp[][] = new int[s.length() + 1][t.length() + 1];
		for (int i = s.length() - 1; i >= 0; i--) {
			for (int j = t.length() - 1; j >= 0; j--) {
				if (s.charAt(i) == t.charAt(j))
					dp[i][j] = dp[i + 1][j + 1] + 1;
				else
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
			}
		}
		return dp[0][0];
	}
}
