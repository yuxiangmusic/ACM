package util;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int x) {
		val = x;
	}

	public TreeNode(int... arr) {
		val = arr[0];
		TreeNode[] nodes = new TreeNode[arr.length];
		nodes[0] = this;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == 0)
				nodes[i] = null;
			else {
				nodes[i] = new TreeNode(arr[i]);
				int pi = (i - 1) / 2;
				if ((i & 1) != 0)
					nodes[pi].left = nodes[i];
				else
					nodes[pi].right = nodes[i];
			}
		}
	}

	@Override
	public String toString() {
		return String.valueOf(val);
	}

	public static TreeNode node(TreeNode root, int val) {
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

	public static String toString(TreeNode root, int depth) {
		String s = "";
		for (int i = 0; i < depth; i++)
			s += " ";
		s += root + "\n";
		if (root == null)
			return s;
		s += toString(root.left, depth + 1);
		s += toString(root.right, depth + 1);
		return s;
	}
}
