package notebook.tree;

import notebook.Notebook;

@Notebook
public class SegmentTree {

	private static final class Node {
		private final int begin, end;
		private final Node left, right;
		private long sum;

		private Node(Node left, Node right, int begin, int end, long sum) {
			this.left = left;
			this.right = right;
			this.begin = begin;
			this.end = end;
			this.sum = sum;
		}
	}

	private final Node root;

	public SegmentTree(int... arr) {
		root = buildTree(arr, 0, arr.length);
	}

	private Node buildTree(int[] arr, int b, int e) {
		if (arr.length == 0)
			return null;
		if (b + 1 == e)
			return new Node(null, null, b, e, arr[b]);
		int m = (b + e) >>> 1;
		Node l = buildTree(arr, b, m);
		Node r = buildTree(arr, m, e);
		return new Node(l, r, b, e, l.sum + r.sum);
	}

	/**
	 * @param begin
	 *            index inclusive
	 * @param end
	 *            index exclusive
	 * @return sum in [begin, end)
	 */
	public long sum(int begin, int end) {
		return sumRec(root, begin, end);
	}

	private long sumRec(Node root, int begin, int end) {
		if (root == null || root.end < begin || end < root.begin)
			return 0;
		if (begin <= root.begin && root.end <= end)
			return root.sum;
		return sumRec(root.left, begin, end) + sumRec(root.right, begin, end);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toStringAsTree(sb, root, 0);
		return sb.toString();
	}

	private void toStringAsTree(StringBuilder sb, Node root, int depth) {
		if (root == null)
			return;
		for (int i = 0; i < depth; i++)
			sb.append(' ');
		String nodeInfo = "[" + root.begin + ", " + root.end + ") sum = " + root.sum;
		sb.append(nodeInfo).append('\n');
		toStringAsTree(sb, root.left, depth + 1);
		toStringAsTree(sb, root.right, depth + 1);
	}

	public void update(int i, int value) {
		updateRec(root, i, value);
	}

	private void updateRec(Node root, int i, int value) {
		if (root.begin == i && i + 1 == root.end)
			root.sum = value;
		else if (root.begin <= i && i < root.end) {
			updateRec(root.left, i, value);
			updateRec(root.right, i, value);
			root.sum = root.left.sum + root.right.sum;
		}
	}

}