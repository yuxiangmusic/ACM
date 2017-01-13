package notebook.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
	public TreeNode left, right;
	public int val;

	public TreeNode(int x) {
		val = x;
	}

	public TreeNode(Integer... arr) {
		val = arr[0];
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(this);
		for (int i = 1; i < arr.length; i++) {
			TreeNode p = q.poll();
			if (arr[i] == null)
				p.left = null;
			else
				q.offer(p.left = new TreeNode(arr[i]));
			if (++i >= arr.length)
				break;
			if (arr[i] == null)
				p.right = null;
			else
				q.offer(p.right = new TreeNode(arr[i]));
		}
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

	public String toStringAsTree() {
		return toStringAsTree(this, 0);
	}

	public String toStringAsList() {
		StringBuilder sb = new StringBuilder();
		for (TreeNode cur = this; cur != null; cur = cur.right)
			sb.append(cur == this ? cur : "->" + cur);
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof TreeNode && isSameTree(this, (TreeNode) o);
	}

	public static boolean isSameTree(TreeNode p, TreeNode q) {
		return p == q || p != null && q != null && p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	private String toStringAsTree(TreeNode root, int depth) {
		String s = "";
		for (int i = 0; i < depth; i++)
			s += " ";
		s += root + "\n";
		if (root == null)
			return s;
		if (root.left != null || root.right != null) {
			s += toStringAsTree(root.left, depth + 1);
			s += toStringAsTree(root.right, depth + 1);
		}
		return s;
	}
}
