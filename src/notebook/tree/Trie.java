package notebook.tree;

public class Trie {

	final class TrieNode {
		char c;
		TrieNode[] children = new TrieNode[1 << 7]; // index 48-57:0-9, 65-90:A-Z, 97-122:a-z
		boolean isWord;

		public TrieNode(char c) {
			this.c = c;
		}
	}

	private final TrieNode root = new TrieNode('\0');

	public void insert(String word) {
		TrieNode cur = root;
		for (char c : word.toCharArray()) {
			if (cur.children[c] == null)
				cur.children[c] = new TrieNode(c);
			cur = cur.children[c];
		}
		cur.isWord = true;
	}

	private TrieNode node(String path) {
		TrieNode cur = root;
		for (char c : path.toCharArray()) {
			if (cur.children[c] == null)
				return null;
			cur = cur.children[c];
		}
		return cur;
	}

	public boolean search(String word) {
		TrieNode node = node(word);
		return node != null && node.isWord;
	}

	public boolean startsWith(String prefix) {
		return node(prefix) != null;
	}

}
