import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
https://ncpc16.kattis.com/problems/autocorrect
=====
5 5
austria
autocorrect
program
programming
computer
autocorrelation
programming
competition
zyx
austria

=====
5 3
yogurt
you
blessing
auto
correct
bless
you
autocorrect

 */
public class BlessYouAutocorrect {

	public static void assertArrayEquals(int[] expecteds, int[] actuals) {
		if (expecteds.length != actuals.length)
			throw new AssertionError(actuals);
		for (int i = 0; i < expecteds.length; i++)
			if (expecteds[i] != actuals[i])
				throw new AssertionError(Arrays.toString(actuals));
	}

	public static void test() {
		int[] expecteds, actuals;

		actuals = solve( //
				new String[] { "austria", "autocorrect", "program", "programming", "computer" }, //
				new String[] { "autocorrelation", "programming", "competition", "zyx", "austria" });
		expecteds = new int[] { 12, 4, 11, 3, 2 };
		assertArrayEquals(expecteds, actuals);

		actuals = solve( //
				new String[] { "yogurt", "you", "blessing", "auto", "correct" }, //
				new String[] { "bless", "you", "autocorrect" });
		expecteds = new int[] { 5, 3, 9 };
		assertArrayEquals(expecteds, actuals);
	}

	public static void main(String[] args) {
		test();
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt(), n = sc.nextInt();
		String[] dict = new String[m];
		for (int i = 0; i < m; i++)
			dict[i] = sc.next();
		String[] words = new String[n];
		for (int i = 0; i < n; i++)
			words[i] = sc.next();
		for (int ans : solve(dict, words))
			System.out.println(ans);
		sc.close();
	}

	public static int[] solve(String[] dict, String[] words) {
		Trie trie = new Trie();
		for (String word : dict)
			trie.add(word);
		trie.updateDistance();

		int[] sol = new int[words.length];
		for (int i = 0; i < words.length; i++) {
			TrieNode prefixNode = trie.prefix(words[i]);
			sol[i] = prefixNode.dist + words[i].length() - prefixNode.depth;
		}
		return sol;
	}

	static class Trie {
		TrieNode root = new TrieNode(0, null);

		public void add(String word) {
			TrieNode cur = root;
			for (char c : word.toCharArray()) {
				if (cur.children[c - 'a'] == null)
					cur.children[c - 'a'] = new TrieNode(cur.depth + 1, cur);
				cur = cur.children[c - 'a'];
			}
			TrieNode end = cur;
			cur = root;
			for (char c : word.toCharArray()) {
				cur = cur.children[c - 'a'];
				if (cur.children[26] == null)
					cur.children[26] = end;
			}
		}

		public TrieNode prefix(String s) {
			TrieNode cur = root;
			for (char c : s.toCharArray()) {
				if (cur.children[c - 'a'] == null)
					return cur;
				cur = cur.children[c - 'a'];
			}
			return cur;
		}

		public void updateDistance() {
			Queue<TrieNode> queue = new LinkedList<>();
			queue.offer(root);
			int dist = 0;
			while (!queue.isEmpty()) {
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					TrieNode node = queue.poll();
					// if (node.visited) continue;
					node.dist = Math.min(node.dist, dist);
					for (TrieNode child : node.children)
						if (child != null && dist < child.dist && child.depth != node.depth)
							queue.offer(child);
				}
				dist++;
			}
		}
	}

	static class TrieNode {
		int dist, depth; // storing word in TrieNode can cause MLE
		/**
		 * [0...25] := letters [26] := tab [27] := parent (back edge)
		 */
		TrieNode children[] = new TrieNode[28];

		TrieNode(int depth, TrieNode parent) {
			this.depth = dist = depth;
			children[27] = parent;
		}
	}

}