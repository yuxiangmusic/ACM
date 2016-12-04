
import java.util.ArrayList;
import java.util.Scanner;

/*
=====
100
9999
123456
1000000000
0

 */

/**
 * <a href="https://uva.onlinejudge.org/external/100/10049.pdf">
 * 
 * Self-Describing Sequence</a>
 */
public class SelfDescribingSequence {

	static ArrayList<Integer> list = new ArrayList<Integer>();

	public static void main(String[] args) {
		list.add(0);
		list.add(1); // f(n) = 1: 0 < n <= 1
		list.add(3); // f(n) = 2: 1 < n <= 3
		list.add(5); // f(n) = 3: 3 < n <= 5

		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			int n = scan.nextInt();
			if (n == 0)
				break;
			System.out.println(query(n));
		}
		scan.close();
	}

	static int query(int n) {
		// update list to make sure it contains n
		for (int i = list.size(); list.get(i - 1) < n; i++)
			list.add(list.get(i - 1) + binSearch(i));
		return binSearch(n);
	}

	// [2] < n <= [3] return 3
	static int binSearch(int n) {
		int l = 0, r = list.size() - 1;
		while (l <= r) {
			int m = (l + r) >>> 1;

			if ((m == 0 || list.get(m - 1) < n) && (n <= list.get(m)))
				return m;
			else if (n < list.get(m)) // IMPORTANT: use m
				r = m - 1;
			else
				l = m + 1;
		}
		return -1;
	}
}
