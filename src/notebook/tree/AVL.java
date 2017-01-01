package notebook.tree;

import java.util.Arrays;

import util.ArrayUtil;

// TODO need test
public class AVL<E> {

	public static void main(String[] args) {
		AVL<Integer> tree = new AVL<>();
	
		int[] arr = ArrayUtil.getRandArray(10);
		System.out.println(Arrays.toString(arr));
	
		for (int i : arr)
			tree.add(i);
	
		System.out.println(tree);
	}

	class Node {
		E data;
		int h = 1;
		Node l, p, r;

		Node(E data) {
			this.data = data;
		}
	}

	Node root;

	boolean add(E data) {
		Node z = new Node(data);
		if (root == null)
			root = z;
		else {
			Node y = root;
			while (true) {
				int comp = compare(data, y.data);
				if (comp == 0) {
					return false;
				} else if (comp < 0) {
					if (y.l == null)
						break;
					else
						y = y.l;
				} else {
					if (y.r == null)
						break;
					else
						y = y.r;
				}
			}
			link(y, z);
			updateHeight(y);

			// AVL
			Node x = y.p;
			while (x != null && Math.abs(balanceFactor(x)) <= 1) {
				z = y;
				y = x;
				x = x.p;
			}
			if (Math.abs(balanceFactor(x)) > 1) {
				Node w = x.p;

				// \ shape: L
				if (x.r == y && y.r == z) {
					rotateL(y);
					link(w, y);
				}
				// / shape: R
				if (x.l == y && y.l == z) {
					rotateR(y);
					link(w, y);
				}
				// < shape: LR
				if (x.l == y && y.r == z) {
					rotateL(z);
					linkL(x, z);
					rotateR(z);
					link(w, z);
				}
				// > shape: RL
				if (x.r == y && y.l == z) {
					rotateR(z);
					linkR(x, z);
					rotateL(z);
					link(w, z);
				}

				updateHeight(x);
				updateHeight(y);
				updateHeight(z);
			}
		}
		return true;
	}

	int balanceFactor(Node n) {
		if (n == null)
			return 0;
		return height(n.l) - height(n.r);
	}

	@SuppressWarnings("unchecked")
	int compare(E a, E b) {
		return ((Comparable<? super E>) a).compareTo(b);
	}

	int height(Node n) {
		return n == null ? 0 : n.h;
	}

	boolean isLeaf(Node n) {
		return n.l == null && n.r == null;
	}

	void link(Node parent, Node child) {
		if (parent == child)
			throw new IllegalArgumentException("parent == child");

		if (child != null)
			child.p = parent;

		if (parent == null) {
			root = child;
			return;
		}
		int comp = compare(child.data, parent.data);
		if (comp == 0)
			throw new IllegalArgumentException();

		else if (comp < 0)
			linkL(parent, child);
		else
			linkR(parent, child);
	}

	void linkL(Node parent, Node child) {
		if (parent != null)
			parent.l = child;
		if (child != null)
			child.p = parent;
	}

	void linkR(Node parent, Node child) {
		if (parent != null)
			parent.r = child;
		if (child != null)
			child.p = parent;
	}

	void rotateL(Node y) {
		Node x = y.p;
		if (y != x.r)
			throw new IllegalStateException("y is not a right child: " + y.data);
		linkR(x, y.l);
		linkL(y, x);
	}

	void rotateR(Node y) {
		Node x = y.p;
		if (y != x.l)
			throw new IllegalStateException("y is not a left child: " + y.data);
		linkL(x, y.r);
		linkR(y, x);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toStringRec(sb, root, 0);
		return sb.toString();
	}

	void toStringRec(StringBuilder sb, Node n, int depth) {
		for (int i = 0; i < depth; i++)
			sb.append(" ");
		sb.append(n == null ? "*" : n.data);
		sb.append(" h=" + height(n) + " b=" + balanceFactor(n));
		sb.append(System.lineSeparator());

		if (n != null && (n.l != null || n.r != null)) {
			toStringRec(sb, n.l, depth + 1);
			toStringRec(sb, n.r, depth + 1);
		}
	}

	void updateHeight(Node n) {
		if (n == null)
			return;
		int h = Math.max(height(n.l), height(n.r)) + 1;

		n.h = h;
		updateHeight(n.p);
	}

}
