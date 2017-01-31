package notebook.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Combinations {

	public static void main(String[] args) {
		System.out.println(combinations(3, 2));
	}

	/**
	 * @return all k-combinations of [0...n-1]
	 */
	public static List<List<Integer>> combinations(int n, int k) {
		Queue<List<Integer>> q = new LinkedList<>(Arrays.asList(new ArrayList<>()));
		while (q.peek().size() < k) {
			List<Integer> l = q.poll();
			int last = l.isEmpty() ? -1 : l.get(l.size() - 1);
			for (int i = last + 1; i <= n - k + l.size(); i++) {
				List<Integer> copy = new ArrayList<>(l);
				copy.add(i);
				q.offer(copy);
			}
		}
		return new ArrayList<>(q);
	}

}
