package notebook.permutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Permutation {

	public static void main(String[] args) {
		Generator g = new Generator(6, 3);
		for (List<Integer> p : g.all)
			System.out.println(p);
		System.out.println(g.all.size());
	}

	static class Generator {
		final List<List<Integer>> all = new ArrayList<>();
		final int n, k;

		Generator(int n, int k) {
			this.n = n;
			this.k = k;

			// generate(new ArrayList<Integer>());
			generate(new ArrayList<Integer>(), 0);
		}

		void generate(List<Integer> cur) {
			if (cur.size() == k) {
				all.add(cur);
			} else {
				HashSet<Integer> rest = new HashSet<>();
				for (int i = 0; i < n; i++)
					rest.add(i);
				for (int i : cur)
					rest.remove(i);
				for (int r : rest) {
					List<Integer> copy = new ArrayList<>(cur);
					copy.add(r);
					generate(copy);
				}
			}
		}

		void generate(List<Integer> cur, int begin) {
			if (cur.size() == k) {
				all.add(cur);
			} else {
				for (int i = begin; i <= n - (k - cur.size()); i++) {
					for (int j = cur.size(); j >= 0; j--) {
						List<Integer> copy = new ArrayList<>(cur);
						copy.add(j, i);
						generate(copy, i + 1);
					}
				}
			}
		}
	}

}
