package notebook.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination {

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
				int min = cur.length == 0 ? 0 : (cur[cur.length - 1] + 1);
				for (int i = min; i <= n - (k - cur.length); i++) {
					int[] next = Arrays.copyOf(cur, cur.length + 1);
					next[cur.length] = i;
					generate(next);
				}
			}
		}
	}

}
