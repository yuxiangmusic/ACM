package notebook.geometry;

import static notebook.geometry.PointUtil.*;
import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Test;

import notebook.Notebook;

public class GrahamScan {

	@Notebook
	public static Point[] runGrahamScan(Point... points) {
		if (points.length <= 1)
			return points;
		// reference point
		Point bottomLeft = points[0];
		for (Point p : points)
			if (p.y < bottomLeft.y || p.y == bottomLeft.y && p.x < bottomLeft.x)
				bottomLeft = p;
		final Point r = bottomLeft;
		// sort by polar
		Arrays.sort(points, (p, q) -> ccw(p, r, q) == 0 ? distsq(p, r) - distsq(q, r) : ccw(p, r, q));

		Stack<Point> stack = new Stack<>();
		int i = 0;
		stack.push(points[i++]);
		stack.push(points[i++]);
		for (; i < points.length; i++) {
			if (ccw(points[0], points[1], points[i]) == 0) {
				stack.pop(); // skip mid point on the same line
				stack.push(points[i]);
			} else {
				break;
			}
		}
		for (; i < points.length; i++) {
			Point top = stack.pop();
			while (ccw(stack.peek(), top, points[i]) <= 0)
				top = stack.pop();
			stack.push(top);
			stack.push(points[i]);
		}
		return stack.stream().toArray(Point[]::new);
	}

	@Test
	public void test() {
		Point[] points = { //
				new Point(0, 0), //
				new Point(1, 1), //
				new Point(2, 2), //
				new Point(3, 3), //
				new Point(2, 1), //
				new Point(1, 2), //
		};
		Point[] expected = { //
				new Point(0, 0), //
				new Point(2, 1), //
				new Point(3, 3), //
				new Point(1, 2), //
		};
		Point[] actual = runGrahamScan(points);

		assertArrayEquals(expected, actual);
	}

	@Test
	public void test_collinear() {
		Point[] points = { //
				new Point(0, 0), //
				new Point(0, 1), //
				new Point(0, 2), //
				new Point(1, 1), //
				new Point(2, 0), //
				new Point(1, 0), //
		};
		Point[] expected = { //
				new Point(0, 0), //
				new Point(2, 0), //
				new Point(0, 2), //
		};
		Point[] actual = runGrahamScan(points);

		assertArrayEquals(expected, actual);
	}

	@Test
	public void test_line() {
		Point[] points = { //
				new Point(0, 0), //
				new Point(1, 1), //
				new Point(2, 2), //
				new Point(3, 3), //
		};
		Point[] expected = { //
				new Point(0, 0), //
				new Point(3, 3), //
		};
		Point[] actual = runGrahamScan(points);

		assertArrayEquals(expected, actual);
	}

}
