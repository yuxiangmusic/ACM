import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Scanner;

/*
=====
4
1
9
011
12345

 */

/**
 * Google Code Jam<br>
 * 
 * <a href="https://code.google.com/codejam/contest/32015/dashboard#s=p1">
 * 
 * Ugly Numbers</a>
 */
public class UglyNumbers {

	// sanity test
	public static void test() {
		assertEquals(0, solve("1"));
		assertEquals(1, solve("9"));
		assertEquals(6, solve("011"));
		assertEquals(64, solve("12345"));
	}

	public static void main(String[] args) {
		test();

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			long sol = solve(sc.next());
			System.out.printf("Case #%d: %d\n", t, sol);
		}
		sc.close();
	}

	public static long solve(String digits) {
		// return solve_rec(digits); // pass small input test
		return solve_dp(digits); // pass large input test
	}

	// pass small input test
	public static long solve_rec(String digits) {
		final long count[] = new long[] { 0 };
		solve_rec(0, digits, count);
		return count[0] / 2;
	}

	// pass small input test
	private static void solve_rec(long eval, String todo, final long count[]) {
		if (todo.isEmpty()) {
			if (isUgly(eval))
				count[0]++;
			return;
		}
		for (int i = 1; i <= todo.length(); i++) {
			long val = Long.parseLong(todo.substring(0, i));
			String nexttodo = todo.substring(i);
			solve_rec(eval + val, nexttodo, count);
			solve_rec(eval - val, nexttodo, count);
		}
	}

	// pass large input test
	public static long solve_dp(String digits) {
		// n is ugly iff (n % 210) is ugly
		final int mod = 2 * 3 * 5 * 7;

		// DP[i][j] := number of ways that digits.substring(i) evaluates to j
		long dp[][] = new long[digits.length() + 1][mod];
		dp[digits.length()][0] = 1;

		for (int i = digits.length() - 1; i >= 0; i--) {
			for (int j = 0; j < mod; j++) {
				for (int k = i + 1; k <= digits.length(); k++) {
					int val = (int) (new BigInteger(digits.substring(i, k)).mod(new BigInteger(String.valueOf(mod))).intValue());
					// use '+' sign: parse(substring(i, k)) + EVAL(substring(k)) = j
					dp[i][j] += dp[k][(j - val + mod) % mod];
					// use '-' sign: parse(substring(i, k)) - EVAL(substring(k)) = j
					dp[i][j] += dp[k][(val - j + mod) % mod];
				}
			}
		}

		long count = 0;
		for (int j = 0; j < mod; j++)
			if (isUgly(j))
				count += dp[0][j];
		return count / 2;
	}

	private static boolean isUgly(long n) {
		for (int d = 2; d <= 7; d++)
			if (n % d == 0)
				return true;
		return false;
	}
}