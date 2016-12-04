package notebook.prime;

import java.util.ArrayList;

public class PrimeUtil {

	public static void main(String[] args) {
		Generator g = new Generator(100);
		System.out.println(g.primeList);
	}

	static class Generator {
		final ArrayList<Integer> primeList = new ArrayList<>();

		Generator(int n) {
			for (int i = 2; i < n; i++)
				if (isPrime(i))
					primeList.add(i);
		}

		boolean isPrime(int n) {
			for (int p : primeList) {
				if (p * p > n)
					break;
				if (n % p == 0)
					return false;
			}
			return true;
		}
	}

}
