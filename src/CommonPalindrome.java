import static org.junit.Assert.assertEquals;

import java.util.Scanner;

/*
=====
3
cfcfaafc
efagfc
afbcdfca
bcadfcgyfka
palin
drome

 */

/**
 * <a href="https://uva.onlinejudge.org/external/124/12473.pdf">
 * 
 * Common Palindrome</a>
 */
public class CommonPalindrome {
	public static void test() {
		assertEquals(3, solve("cfcfaafc", "efagfc"));
		assertEquals(5, solve("afbcdfca", "bcadfcgyfka"));
		assertEquals(0, solve("palin", "drome"));
	}

	public static void main(String[] args) {
		test();

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			long sol = solve(sc.next(), sc.next());
			System.out.printf("Case %d: %d\n", t, sol);
		}
		sc.close();
	}

	public static int solve(String s, String t) {
		int m = s.length(), n = t.length();
		int[][][][] dp = new int[m + 1][m + 1][n + 1][n + 1];
		for (int a = 0; a <= m; a++) {
			for (int i = 0; i <= m - a; i++) {
				for (int b = 0; b <= n; b++) {
					for (int j = 0; j <= n - b; j++) {
						if (a == 0 || b == 0) {
							dp[i][i][j][j] = 0;
						} else if (a == 1 && b == 1) {
							dp[i][i + 1][j][j + 1] = s.charAt(i) == t.charAt(j) ? 1 : 0;
						} else if (a >= 2 && b >= 2 && s.charAt(i) == t.charAt(j) && s.charAt(i) == s.charAt(i + a - 1) && t.charAt(j) == t.charAt(j + b - 1)) {
							dp[i][i + a][j][j + b] = 2 + dp[i + 1][i + a - 1][j + 1][j + b - 1];
						} else {
							dp[i][i + a][j][j + b] = Math.max(dp[i][i + a][j][j + b], dp[i + 1][i + a][j][j + b]); // s\L
							dp[i][i + a][j][j + b] = Math.max(dp[i][i + a][j][j + b], dp[i][i + a - 1][j][j + b]); // s\R
							dp[i][i + a][j][j + b] = Math.max(dp[i][i + a][j][j + b], dp[i][i + a][j + 1][j + b]); // t\L
							dp[i][i + a][j][j + b] = Math.max(dp[i][i + a][j][j + b], dp[i][i + a][j][j + b - 1]); // t\R
						}
					}
				}
			}
		}
		return dp[0][m][0][n];
	}
}