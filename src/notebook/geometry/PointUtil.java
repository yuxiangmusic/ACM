package notebook.geometry;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import notebook.Notebook;

public class PointUtil {

	@Test
	public void test() {
		// counter-clockwise
		assertTrue(ccw(new Point(0, 1), new Point(0, 0), new Point(1, 0)) > 0);
		// clockwise
		assertTrue(ccw(new Point(1, 0), new Point(0, 0), new Point(0, 1)) < 0);
		// same line
		assertTrue(ccw(new Point(0, 0), new Point(0, 1), new Point(0, 2)) == 0);
	}

	/**
	 * @return positive if counter-clockwise, negative if clockwise, 0 otherwise
	 */
	@Notebook
	public static int ccw(Point a, Point b, Point c) {
		return a.x * b.y - a.y * b.x + b.x * c.y - b.y * c.x + c.x * a.y - c.y * a.x;
	}

	public static int distsq(Point p, Point q) {
		int dx = p.x - q.x;
		int dy = p.y - q.y;
		return dx * dx + dy * dy;
	}
}