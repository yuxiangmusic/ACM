package notebook.tree;

import static notebook.array.ArrayUtil.ith;
import static notebook.geometry.PointUtil.distsq;

import java.util.Comparator;

import notebook.Notebook;
import notebook.geometry.Point;

@Notebook
public class TwoDTree {

	private static final class Node {
		private Node left, right;
		private final Point p;

		private final boolean y;

		private Node(Point p, boolean y) {
			this.p = p;
			this.y = y;
		}
	}

	private Node root;
	private Comparator<Point> xcomp = (p, q) -> p.x - q.x;
	private Comparator<Point> ycomp = (p, q) -> p.y - q.y;

	public TwoDTree(Point[] points) {
		buildTree(points);
	}

	private void buildTree(Point[] points) {
		root = buildTree(points, 0, points.length - 1, false);
	}

	private Node buildTree(Point[] points, int l, int h, boolean y) {
		if (l > h)
			return null;
		if (l == h)
			return new Node(points[l], y);
		int mid = (l + h) >>> 1;
		Point p = ith(points, l, h, mid, y ? ycomp : xcomp);
		Node node = new Node(p, y);
		node.left = buildTree(points, l, mid - 1, !y);
		node.right = buildTree(points, mid + 1, h, !y);
		return node;
	}

	public Point nearest(Point p) {
		Point nearest[] = { node(p).p };
		if (nearest[0].equals(p))
			return nearest[0];
		nearest(nearest, p, root);
		return nearest[0];
	}

	private void nearest(Point[] nearest, Point p, Node root) {
		if (root == null)
			return;
		if (distsq(root.p, p) < distsq(nearest[0], p))
			nearest[0] = root.p;

		// distance to hyper-plane
		int dist = root.y ? p.y - root.p.y : p.x - root.p.x;
		int dsq = dist * dist;
		// radius of hyper-sphere centered at p
		int rsq = distsq(nearest[0], p);

		if ((root.y ? ycomp : xcomp).compare(p, root.p) < 0) {
			nearest(nearest, p, root.left);
			if (rsq > dsq)
				nearest(nearest, p, root.right);
		} else {
			nearest(nearest, p, root.right);
			if (rsq > dsq)
				nearest(nearest, p, root.left);
		}
	}

	private Node node(Point p) {
		Node cur = root, pre = null;
		while (cur != null) {
			if (cur.p.equals(p))
				return cur;
			pre = cur;
			cur = (cur.y ? ycomp : xcomp).compare(p, cur.p) < 0 ? cur.left : cur.right;
		}
		return pre;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(sb, root, 0);
		return sb.toString();
	}

	private void toString(StringBuilder sb, Node n, int depth) {
		for (int i = 0; i < depth; i++)
			sb.append(' ');
		if (n == null) {
			sb.append(n).append('\n');
			return;
		}
		sb.append(n.p).append(' ').append(n.y ? 'y' : 'x').append('\n');
		if (n.left != null || n.right != null) {
			toString(sb, n.left, depth + 1);
			toString(sb, n.right, depth + 1);
		}
	}
}
