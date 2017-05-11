package notebook.gcd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExtendedEuclidTest {

	@Test
	public void test() {
		long a = 1027;
		long b = 712;
		ExtendedEuclid ans = ExtendedEuclid.gcd(a, b);
		long gcd = ans.gcd;
		long x = ans.x;
		long y = ans.y;
		System.out.println(ans);
		assertEquals(gcd, a * x + b * y);
	}
}
