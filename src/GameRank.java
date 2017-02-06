import java.util.Scanner;
import java.util.stream.IntStream;

/*
https://ncpc16.kattis.com/problems/gamerank
=====
WW

=====
WWW

=====
WWWW

=====
WLWLWLWL

=====
WWWWWWWWWLLWW

=====
WWWWWWWWWLWWL

 */
public class GameRank {

	private static void assertEquals(int expected, int actual) {
		if (expected != actual)
			throw new AssertionError(actual);
	}

	private static void test() {
		assertEquals(25, solve("WW"));
		assertEquals(24, solve("WWW"));
		assertEquals(23, solve("WWWW"));
		assertEquals(24, solve("WLWLWLWL"));
		assertEquals(19, solve("WWWWWWWWWLLWW"));
		assertEquals(18, solve("WWWWWWWWWLWWL"));
	}

	public static void main(String[] args) {
		test();
		Scanner sc = new Scanner(System.in);
		int sol = solve(sc.next());
		System.out.println(sol == 0 ? "Legend" : sol);
		sc.close();
	}

	static final int starsPerRank[] = IntStream.range(0, 26).map(rank -> Math.min(5, 6 - (rank - 1) / 5)).toArray();

	public static int solve(String s) {
		int rank = 25, stars = 0, cw = 0;
		for (char c : s.toCharArray()) {
			if (c == 'W') {
				stars++;
				if (++cw >= 3 && rank >= 6)
					stars++; // bonus
				if (stars > starsPerRank[rank]) {
					stars -= starsPerRank[rank];
					rank--;
				}
			} else {
				cw = 0;
				if (rank <= 20) {
					if (stars > 0 || rank < 20)
						stars--;
					if (stars < 0)
						stars += starsPerRank[++rank];
				}
			}
			if (rank == 0)
				return rank;
		}
		return rank;
	}

}
