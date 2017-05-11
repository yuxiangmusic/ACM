package notebook.prime;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import notebook.Notebook;

public class PrimeUtil {

	@Test
	public void test() {
		int n = 1000;
		{
			long begin = System.nanoTime();
			primes_slow(n);
			System.out.println("slow cost: " + (System.nanoTime() - begin));
		}
		{
			long begin = System.nanoTime();
			primes(n);
			System.out.println("fast cost: " + (System.nanoTime() - begin));
		}
		assertEquals(primes(n), primes_slow(n));
	}

	@Notebook
	public static List<Integer> primes(int n) {
		List<Integer> primes = new ArrayList<>();
		boolean[] isComposite = new boolean[n];
		for (int i = 2; i < n; i++) {
			if (!isComposite[i]) {
				primes.add(i);
				for (int j = 2; i * j < n; j++)
					isComposite[i * j] = true;
			}
		}
		return primes;
	}

	@Deprecated
	public static List<Integer> primes_slow(int n) {
		List<Integer> primes = new ArrayList<>();
		for (int i = 2; i < n; i++) {
			boolean isPrime = true;
			for (int p : primes) {
				if (p * p > i)
					break;
				if (i % p == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime)
				primes.add(i);
		}
		return primes;
	}

}
