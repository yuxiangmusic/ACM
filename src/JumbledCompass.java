import java.util.Scanner;

/*
https://ncpc16.kattis.com/problems/compass
=====
315
45

=====
180
270

=====
45
270

 */
public class JumbledCompass {

	private static void assertEquals(long expected, long actual) {
		if (expected != actual)
			throw new AssertionError();
	}

	private static void test() {
		assertEquals(90, solve(315, 45));
		assertEquals(90, solve(180, 270));
		assertEquals(-135, solve(45, 270));
	}

	public static void main(String[] args) {
		test();
		Scanner sc = new Scanner(System.in);
		System.out.println(solve(sc.nextInt(), sc.nextInt()));
		sc.close();
	}

	static int solve(int from, int to) {
		int sol[] = { to + 360 - from, to - from, to - 360 - from };
		int minIndex = 0;
		for (int i = 0; i < sol.length; i++)
			if (Math.abs(sol[i]) < Math.abs(sol[minIndex]))
				minIndex = i;
		return sol[minIndex] == -180 ? 180 : sol[minIndex];
	}

}
