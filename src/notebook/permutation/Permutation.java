package notebook.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Permutation {

	public static void main(String[] args) {
		Generator g = new Generator(6, 3);
		for (int[] c : g.all)
			System.out.println(Arrays.toString(c));
	}

	static class Generator {
		final List<int[]> all = new ArrayList<>();
		final int n, k;

		Generator(int n, int k) {
			this.n = n;
			this.k = k;

			generate(new int[] {});
		}

		void generate(int[] cur) {
			if (cur.length == k) {
				all.add(cur);
			} else {
				HashSet<Integer> rest = new HashSet<>();
				for (int i = 0; i < n; i++)
					rest.add(i);
				for (int i : cur)
					rest.remove(i);

				for (int r : rest) {
					int[] next = Arrays.copyOf(cur, cur.length + 1);
					next[cur.length] = r;
					generate(next);
				}
			}
		}
	}

}
