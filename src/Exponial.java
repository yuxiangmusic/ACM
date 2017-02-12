import java.util.Scanner;

/*
https://ncpc16.kattis.com/problems/exponial
=====
2 42

=====
5 123456789

=====
94 265

 */
public class Exponial {

	private static void assertEquals(long expected, long actual) {
		if (expected != actual)
			throw new AssertionError(actual);
	}

	private static void test() {
		assertEquals(2, solve(2, 42));
		assertEquals(16317634, solve(5, 123456789));
		assertEquals(39, solve(94, 265));
	}

	public static void main(String[] args) {
		test();
		Scanner sc = new Scanner(System.in);
		System.out.println(solve(sc.nextLong(), sc.nextLong()));
		sc.close();
	}

	public static long solve(long n, long m) {
		if (m == 1)
			return 0;
		if (n <= 5) {
			long result = 1;
			for (long base = 2; base <= n; base++)
				result = exp(base, result, m);
			return result % m;
		} else {
			long phi = phi(m), z = solve(n - 1, phi);
			return exp(n, phi + z, m);
		}
	}

	private static long phi(long n) {
		long result = n;
		for (long p = 2; p * p <= n; ++p) {
			if (n % p == 0) {
				while (n % p == 0)
					n /= p;
				result -= result / p;
			}
		}
		if (n > 1)
			result -= result / n;
		return result;
	}

	private static long exp(long base, long e, long m) {
		if (e == 0)
			return 1 % m;
		long tmp = (exp(base, e >>> 1, m)) % m;
		if (e % 2 == 0) {
			return (tmp * tmp) % m;
		} else {
			return (tmp * ((tmp * base) % m)) % m;
		}
	}
}