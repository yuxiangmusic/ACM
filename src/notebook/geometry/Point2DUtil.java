package notebook.geometry;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import notebook.Notebook;

public class Point2DUtil {

	@Test
	public void test() {
		// counter-clockwise
		assertTrue(ccw(new Point2D(0, 1), new Point2D(0, 0), new Point2D(1, 0)) > 0);
		// clockwise
		assertTrue(ccw(new Point2D(1, 0), new Point2D(0, 0), new Point2D(0, 1)) < 0);
		// same line
		assertTrue(ccw(new Point2D(0, 0), new Point2D(0, 1), new Point2D(0, 2)) == 0);
	}

	/**
	 * @return positive if counter-clockwise, negative if clockwise, 0 otherwise
	 */
	@Notebook
	public static int ccw(Point2D a, Point2D b, Point2D c) {
		return a.x * b.y - a.y * b.x + b.x * c.y - b.y * c.x + c.x * a.y - c.y * a.x;
	}

}