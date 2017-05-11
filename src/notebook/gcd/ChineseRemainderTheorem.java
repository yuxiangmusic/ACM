package notebook.gcd;

import static org.junit.Assert.*;

import org.junit.Test;

import notebook.Notebook;

public class ChineseRemainderTheorem {
	/**
	 * @return n (MOD r*s) such that n == a (MOD r) and n == b (MOD s)
	 */
	@Notebook
	public static long getN(long r, long s, long a, long b) {
		ExtendedEuclid ans = ExtendedEuclid.gcd(r, s);

		if (Math.abs(ans.gcd) != 1)
			throw new IllegalArgumentException("gcd(r, s) must be 1");

		long n = b * r * ans.x + a * s * ans.y, rs = r * s;

		return (n % rs + rs) % rs; // least positive n (MOD r*s)
	}

	@Test
	public void test() {
		long r = 7, s = 12, a = 3, b = 5;
		// 17 = 3 (MOD 7)
		// 17 = 5 (MOD 12)
		assertEquals(17, getN(r, s, a, b));
	}
}
