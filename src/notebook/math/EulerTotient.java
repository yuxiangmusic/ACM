package notebook.math;

import notebook.Notebook;

public class EulerTotient {

	/**
	 * @return number of positive integers < n that are relatively prime to n
	 */
	@Notebook
	public static long phi(long n) {
		long r = n;
		for (long p = 2; p * p <= n; p++) {
			if (n % p == 0) {
				while (n % p == 0)
					n /= p;
				r -= r / p;
			}
		}
		if (n > 1)
			r -= r / n;
		return r;
	}

	public static void main(String[] args) {
		long n;
		for (n = 1; n <= 100; n++)
			System.out.println("phi(" + n + ") = " + phi(n));
	}

}
