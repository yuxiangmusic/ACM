package notebook.gcd;

public class GCD {

	// add Math.abs when necessary
	public static long gcd_rec(long a, long b) {
		return b == 0 ? a : gcd_rec(b, a % b);
	}

	public static long gcd_itr(long a, long b) {
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
