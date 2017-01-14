package notebook.math;

import org.junit.Test;

public class Fibonacci {

	@Test
	public void test() {
		int i = 0;
		for (; fib_dp(i) < Integer.MAX_VALUE; i++)
			System.out.println("[" + i + "] " + fib_dp(i) + " " + fib_math(i));

		System.out.println("==== " + Integer.MAX_VALUE + " max int");

		for (; fib_dp(i) == fib_math(i); i++)
			System.out.println("[" + i + "] " + fib_dp(i) + " " + fib_math(i));

		System.out.println("==== " + "bad math begins");

		for (; fib_dp(i) >= 0; i++)
			System.out.println("[" + i + "] " + fib_dp(i) + " " + fib_math(i));

		System.out.println("==== " + Long.MAX_VALUE + " max long");
	}

	public static long fib_dp(int n) {
		long dp[] = new long[n + 1];
		if (1 <= n)
			dp[1] = 1;
		for (int i = 2; i <= n; i++)
			dp[i] = dp[i - 1] + dp[i - 2];
		return dp[n];
	}

	public static long fib_math(int n) {
		double sqrt5 = Math.sqrt(5), golden = (1 + sqrt5) / 2;
		return (long) Math.round(Math.pow(golden, n) / sqrt5);
	}

}
