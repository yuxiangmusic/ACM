import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/*
https://ncpc16.kattis.com/problems/cardhand
=====
4
2h Th 8c Qh

=====
7
9d As 2s Qd 2c Jd 8h

=====
4
2h 3h 9c 8c

 */
public class CardHandSorting {

	public static void assertEquals(int expected, int actual) {
		if (expected != actual)
			throw new AssertionError(actual);
	}

	public static void test() {
		assertEquals(1, solve("2h", "Th", "8c", "Qh"));
		assertEquals(2, solve("9d", "As", "2s", "Qd", "2c", "Jd", "8h"));
		assertEquals(0, solve("2h", "3h", "9c", "8c"));
	}

	public static void main(String[] args) {
		test();
		Scanner sc = new Scanner(System.in);
		String[] arr = new String[sc.nextInt()];
		for (int i = 0; i < arr.length; i++)
			arr[i] = sc.next();
		System.out.println(solve(arr));
		sc.close();
	}

	static final String suit = "shdc", rank = "23456789TJQKA";

	public static int solve(String... arr) {
		int max = Integer.MIN_VALUE;
		for (List<Integer> p : permutations(4, 4)) {
			for (int i = 0; i < 16; i++) {
				final int order = i;
				int lenLIS = lenLIS(arr, (a, b) -> getValue(a, p, order) - getValue(b, p, order));
				max = Math.max(max, lenLIS);
			}
		}
		return arr.length - max;
	}

	private static int getValue(String card, List<Integer> p, int order) {
		int suitIndex = p.get(suit.indexOf(card.charAt(1)));
		int rankValue = rank.indexOf(card.charAt(0));
		if (((order >>> suitIndex) & 1) == 1)
			rankValue = rank.length() - 1 - rankValue;
		return suitIndex * rank.length() + rankValue;
	}

	/**
	 * @see notebook.array.LIS
	 */
	private static int lenLIS(String[] arr, Comparator<String> comp) {
		if (arr.length == 0)
			return 0;
		int l[] = new int[arr.length]; // l[i] := length of LIS ending at i
		int max = 0; // index of max LIS
		for (int i = 0; i < arr.length; i++) {
			l[i] = 1;
			for (int j = 0; j < i; j++)
				if (comp.compare(arr[j], arr[i]) < 0 && l[i] < l[j] + 1)
					l[i] = l[j] + 1;
			if (l[i] > max)
				max = l[i];
		}
		return max;
	}

	/**
	 * @see notebook.permutation.Permutations
	 */
	private static List<List<Integer>> permutations(int n, int k) {
		Queue<List<Integer>> q = new LinkedList<>(Arrays.asList(new ArrayList<>()));
		Queue<List<Integer>> r = new LinkedList<>(Arrays.asList(new ArrayList<>()));
		for (int i = 0; i < n; i++)
			r.peek().add(i);
		while (q.peek().size() < k) {
			List<Integer> qq = q.poll();
			List<Integer> rr = r.poll();
			for (int i = 0; i < rr.size(); i++) {
				List<Integer> copyqq = new ArrayList<>(qq);
				List<Integer> copyrr = new ArrayList<>(rr);
				copyqq.add(copyrr.remove(i));
				q.offer(copyqq);
				r.offer(copyrr);
			}
		}
		return new ArrayList<>(q);
	}
}
