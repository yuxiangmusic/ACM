package notebook.gcd;

import notebook.Notebook;

public class GCD {

	@Notebook
	public static long gcd_rec(long a, long b) {
		// add Math.abs when necessary
		return b == 0 ? a : gcd_rec(b, a % b);
	}

	@Notebook
	public static long gcd_itr(long a, long b) {
		// add Math.abs when necessary
		long r;
		while (b != 0) {
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

	public static void main(String[] args) {
		int a = 15, b = 25;

		System.out.println(gcd_itr(a, b));
		System.out.println(gcd_itr(-a, -b));
		System.out.println(gcd_itr(a, -b));
		System.out.println(gcd_itr(-a, b));

		System.out.println(gcd_rec(a, b));
		System.out.println(gcd_rec(-a, -b));
		System.out.println(gcd_rec(a, -b));
		System.out.println(gcd_rec(-a, b));
	}

}
