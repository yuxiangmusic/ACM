package notebook.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import notebook.Notebook;

public class Permutations {

	public static void main(String[] args) {
		System.out.println(permutations(4, 2));
	}

	/**
	 * @return all k-permutations of [0...n-1]
	 */
	@Notebook
	public static List<List<Integer>> permutations(int n, int k) {
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
