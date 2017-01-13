package notebook.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Newton {

	public static final double DELTA = 1e-10;

	@Test
	public void test() {
		for (int i = 0; i < 1000; i++) {
			assertEquals(Math.sqrt(i), Newton.sqrt(i), Newton.DELTA);
		}
	}

	// f(x) = x^2 - n = 0
	// x = x - f/f' = x - (x^2 - n) / 2x
	public static double sqrt(double c) {
		double x = c, next;
		while (true) {
			next = x - (x - c / x) / 2;
			if (isNaN(next) || Math.abs(next - x) < DELTA)
				break;
			x = next;
		}
		return x;
	}

	public static boolean isNaN(double x) {
		return x != x;
	}

}
