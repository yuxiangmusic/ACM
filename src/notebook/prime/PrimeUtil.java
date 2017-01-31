package notebook.prime;

import java.util.LinkedList;
import java.util.List;

public class PrimeUtil {

	public static void main(String[] args) {
		Generator g = new Generator(1500000);
		System.out.println(g.primeList);
	}

	static class Generator {
		final List<Long> primeList = new LinkedList<>();

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
