package notebook.prime;

import java.util.ArrayList;

public class PrimeUtil {

	public static void main(String[] args) {
		Generator g = new Generator(100);
		System.out.println(g.primeList);
	}

	static class Generator {
		final ArrayList<Long> primeList = new ArrayList<>();

		Generator(long n) {
			for (long i = 2; i < n; i++)
				if (isPrime(i))
					primeList.add(i);
		}

		boolean isPrime(long n) {
			for (long p : primeList) {
				if (p * p > n)
					break;
				if (n % p == 0)
					return false;
			}
			return true;
		}
	}
}
