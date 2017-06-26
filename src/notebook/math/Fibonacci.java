package notebook.math;

import org.junit.Test;

import notebook.Notebook;

public class Fibonacci {

	static final double SQRT5 = Math.sqrt(5), GOLDEN = (1 + SQRT5) / 2;

	public static long fib(int n) {
		long dp[] = new long[n + 1];
		if (1 <= n)
			dp[1] = 1;
		for (int i = 2; i <= n; i++)
			dp[i] = dp[i - 1] + dp[i - 2];
		return dp[n];
	}

	/**
	 * Calculates correct result up to n = 70
	 */
	@Notebook
	public static long fib_math(int n) {
		// SQRT5 = Math.sqrt(5)
		// GOLDEN = (1 + SQRT5) / 2;
		return (long) Math.round(Math.pow(GOLDEN, n) / SQRT5);
	}

	@Test
	public void test() {
		int i = 0;
		for (; fib(i) < Integer.MAX_VALUE; i++)
			System.out.println("[" + i + "] " + fib(i) + " " + fib_math(i));

		System.out.println("==== " + Integer.MAX_VALUE + " max int");

		for (; fib(i) == fib_math(i); i++)
			System.out.println("[" + i + "] " + fib(i) + " " + fib_math(i));

		System.out.println("==== " + "bad math begins");

		for (; fib(i) >= 0; i++)
			System.out.println("[" + i + "] " + fib(i) + " " + fib_math(i));

		System.out.println("==== " + Long.MAX_VALUE + " max long");
	}

}
