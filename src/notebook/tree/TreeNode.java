package notebook.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

	public static TreeNode add(TreeNode root, int val) {
		if (root == null) {
			return new TreeNode(val);
		} else if (val < root.val) {
			root.left = add(root.left, val);
		} else if (val > root.val) {
			root.right = add(root.right, val);
		} else {
			// do nothing
		}
		return root;
	}

	public static boolean isSameTree(TreeNode p, TreeNode q) {
		return p == q || p != null && q != null // null cases
				&& p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	public TreeNode left, right;

	public final int val;

	public TreeNode(int x) {
		val = x;
	}

	public TreeNode(Integer... arr) {
		val = arr[0];
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(this);
		for (int i = 1; i < arr.length; i++) {
			TreeNode p = queue.poll();
			if (arr[i] == null)
				p.left = null;
			else
				queue.offer(p.left = new TreeNode(arr[i]));
			if (++i >= arr.length)
				break;
			if (arr[i] == null)
				p.right = null;
			else
				queue.offer(p.right = new TreeNode(arr[i]));
		}
	}

	public void add(int val) {
		add(new TreeNode(val));
	}

	/**
	 * Adds a child node to this
	 * 
	 * @param node
	 */
	public void add(TreeNode node) {
		if (node.val == val) {
			// do nothing
		} else if (node.val < val) {
			if (left == null)
				left = node;
			else
				left.add(node);
		} else {
			if (right == null)
				right = node;
			else
				right.add(node);
		}
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof TreeNode && isSameTree(this, (TreeNode) o);
	}

	public TreeNode node(int val) {
		return node(this, val);
	}

	private TreeNode node(TreeNode root, int val) {
		if (root == null)
			return null;
		if (root.val == val)
			return root;
		TreeNode left = node(root.left, val);
		if (left != null)
			return left;
		TreeNode right = node(root.right, val);
		if (right != null)
			return right;
		return null;
	}

	@Override
	public String toString() {
		return String.valueOf(val);
	}

	public String toStringAsList() {
		StringBuilder sb = new StringBuilder();
		for (TreeNode cur = this; cur != null; cur = cur.right)
			sb.append(cur == this ? cur : "->" + cur);
		return sb.toString();
	}

	public String toStringAsTree() {
		StringBuilder sb = new StringBuilder();
		toStringAsTree(sb, this, 0);
		return sb.toString();
	}

	private void toStringAsTree(StringBuilder sb, TreeNode root, int depth) {
		for (int i = 0; i < depth; i++)
			sb.append(' ');
		sb.append(root).append('\n');
		if (root == null)
			return;
		if (root.left != null || root.right != null) {
			toStringAsTree(sb, root.left, depth + 1);
			toStringAsTree(sb, root.right, depth + 1);
		}
	}
}
