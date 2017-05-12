package notebook.gcd;

import notebook.Notebook;

@Notebook
public class ExtendedEuclid {
	/**
	 * @return GCD(a, b) = ax + by
	 */
	public static ExtendedEuclid gcd(long a, long b) {
		ExtendedEuclid ans = new ExtendedEuclid();
		if (b == 0) {
			ans.gcd = a;
			ans.a = a;
			ans.b = b;
			ans.x = 1;
			ans.y = 0;
			return ans;
		}
		ans = gcd(b, a % b);
		long x = ans.x, y = ans.y, q = a / b;

		ans.gcd = ans.gcd;
		ans.a = a;
		ans.b = b;
		ans.x = y;
		ans.y = x - y * q;

		return ans;
	}

	public long gcd, a, x, b, y;

	@Override
	public String toString() {
		return String.format("%d = %d * %d + %d * %d", gcd, a, x, b, y);
	}

}
