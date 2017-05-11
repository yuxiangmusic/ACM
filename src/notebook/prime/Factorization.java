package notebook.prime;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import notebook.Notebook;

public class Factorization {
	Random rand = new Random();

	@Test
	public void test() {
		long n = Math.abs(rand.nextLong());
		System.out.println("factors of " + n);

		for (long f : factorize(n)) {
			System.out.println(f);
			n /= f;
		}
		assertEquals(1, n);
	}

	@Notebook
	public static List<Long> factorize(long n) {
		List<Long> factors = new ArrayList<>();
		for (long f = 2; f * f <= n; f++) { // using (f < n) is slower
			while (n % f == 0) {
				factors.add(f);
				n /= f;
			}
		}
		if (n > 1)
			factors.add(n);
		return factors;
	}

}
