package notebook.combination;

import java.util.ArrayList;
import java.util.List;

public class Combination {

	public static void main(String[] args) {
		Generator g = new Generator(6, 3);
		for (List<Integer> c : g.all)
			System.out.println(c);
	}

	static class Generator {
		final List<List<Integer>> all = new ArrayList<>();
		final int n, k;

		Generator(int n, int k) {
			this.n = n;
			this.k = k;

			generate(new ArrayList<>());
		}

		void generate(List<Integer> cur) {
			if (cur.size() == k) {
				all.add(cur);
			} else {
				int min = cur.isEmpty() ? 0 : (cur.get(cur.size() - 1) + 1);
				for (int i = min; i <= n - (k - cur.size()); i++) {
					List<Integer> copy = new ArrayList<>(cur);
					copy.add(i);
					generate(copy);
				}
			}
		}
	}

}
