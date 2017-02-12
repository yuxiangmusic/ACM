import java.util.Scanner;

/*
https://ncpc16.kattis.com/problems/stockbroker
=====
6
100
200
100
150
125
300

 */
public class DaydreamingStockbroker {

	private static void assertEquals(long expected, long actual) {
		if (expected != actual)
			throw new AssertionError();
	}

	private static void test() {
		assertEquals(650, solve(100, 200, 100, 150, 125, 300));
	}

	public static void main(String[] args) {
		test();
		Scanner sc = new Scanner(System.in);
		int d = sc.nextInt();
		int[] arr = new int[d];
		for (int i = 0; i < d; i++)
			arr[i] = sc.nextInt();
		System.out.println(solve(arr));
		sc.close();
	}

	public static long solve(int... arr) {
		long money = 100;
		for (int i = 0; i < arr.length - 1; i++) {
			long buyprice = arr[i], sellprice = arr[i + 1];
			if (buyprice < sellprice) {
				long shares = Math.min(money / buyprice, 100000);
				money += shares * (sellprice - buyprice);
			}
		}
		return money;
	}

}
