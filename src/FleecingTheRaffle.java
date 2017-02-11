import java.util.Scanner;

/*
https://ncpc16.kattis.com/problems/raffle
=====
3 2

=====
23 5

 */
public class FleecingTheRaffle {

	private static void assertEquals(double expected, double actual) {
		if (Math.abs(expected - actual) > 1e-6)
			throw new AssertionError(actual);
	}

	private static void test() {
		assertEquals(0.6, solve(3, 2));
		assertEquals(0.45049857550, solve(23, 5));
	}

	public static void main(String[] args) {
		test();
		Scanner sc = new Scanner(System.in);
		System.out.println(solve(sc.nextInt(), sc.nextInt()));
		sc.close();
	}

	public static double solve(int n, int p) {
		return solve_linear(n, p);
	}

	public static double solve_linear(int n, int p) {
		int x = n / (p - 1); // probability is maximized when x = n / (p - 1)
		// return x * choose(n, p - 1) / choose(n + x, p); // XXX accumulate more inaccuracy
		double ans = (double) (x * p) / (n + 1);
		for (int i = 2; i <= x; i++)
			ans *= (double) (n - p + i) / (n + i);
		return ans;
	}

	public static double solve_constant(int n, int p) {
		// TODO constant solution
		return 0;
	}

}
