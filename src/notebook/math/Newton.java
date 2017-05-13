package notebook.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import notebook.Notebook;

public class Newton {

	public static final double DELTA = 1e-10;

	@Test
	public void test() {
		for (int i = 0; i < 1000; i++) {
			assertEquals(Math.sqrt(i), sqrt(i), Newton.DELTA);
		}
	}

	/**
	 * f(x) = x^2 - n = 0, f'(x) = 2x <br>
	 * Iterating x = x - f/f' = x - (x^2 - n) / 2x until x does not change
	 */
	@Notebook
	public static double sqrt(double n) {
		double x = n, next;
		while (true) {
			next = x - (x * x - n) / (2 * x);
			if (Double.isNaN(next) || Math.abs(next - x) < DELTA)
				break;
			x = next;
		}
		return x;
	}

}
