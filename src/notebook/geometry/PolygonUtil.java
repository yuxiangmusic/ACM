package notebook.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Rectangle;

import org.junit.Test;

import notebook.Notebook;

public class PolygonUtil {

	static final double DELTA = 1e-10;

	/**
	 * @param p
	 *            points sorted in counter-clockwise order
	 * @return area of the polygon
	 */
	@Notebook
	public static double area(Point... p) {
		int a = 0, j = p.length - 1;
		for (int i = 0; i < p.length; j = i++)
			a += (p[i].x + p[j].x) * (p[i].y - p[j].y);
		return a / 2.0;
	}

	/**
	 * @see {@link java.awt.Polygon#getBoundingBox()}
	 */
	@Notebook
	public static Rectangle calculateBounds(int xpoints[], int ypoints[], int npoints) {
		int boundsMinX = Integer.MAX_VALUE;
		int boundsMinY = Integer.MAX_VALUE;
		int boundsMaxX = Integer.MIN_VALUE;
		int boundsMaxY = Integer.MIN_VALUE;

		for (int i = 0; i < npoints; i++) {
			int x = xpoints[i];
			boundsMinX = Math.min(boundsMinX, x);
			boundsMaxX = Math.max(boundsMaxX, x);
			int y = ypoints[i];
			boundsMinY = Math.min(boundsMinY, y);
			boundsMaxY = Math.max(boundsMaxY, y);
		}
		return new Rectangle(boundsMinX, boundsMinY, boundsMaxX - boundsMinX, boundsMaxY - boundsMinY);
	}

	/**
	 * @see {@link java.awt.Polygon#contains(double, double)}
	 */
	@Notebook
	public boolean contains(int[] xpoints, int[] ypoints, int npoints, double x, double y) {
		if (npoints <= 2 || !calculateBounds(xpoints, ypoints, npoints).contains(x, y)) {
			return false;
		}
		int hits = 0;
		int lastx = xpoints[npoints - 1];
		int lasty = ypoints[npoints - 1];
		int curx, cury;

		for (int i = 0; i < npoints; lastx = curx, lasty = cury, i++) {
			curx = xpoints[i];
			cury = ypoints[i];

			if (cury == lasty) {
				continue;
			}
			int leftx;
			if (curx < lastx) {
				if (x >= lastx) {
					continue;
				}
				leftx = curx;
			} else {
				if (x >= curx) {
					continue;
				}
				leftx = lastx;
			}
			double test1, test2;
			if (cury < lasty) {
				if (y < cury || y >= lasty) {
					continue;
				}
				if (x < leftx) {
					hits++;
					continue;
				}
				test1 = x - curx;
				test2 = y - cury;
			} else {
				if (y < lasty || y >= cury) {
					continue;
				}
				if (x < leftx) {
					hits++;
					continue;
				}
				test1 = x - lastx;
				test2 = y - lasty;
			}
			if (test1 < (test2 / (lasty - cury) * (lastx - curx))) {
				hits++;
			}
		}
		return ((hits & 1) != 0);
	}

	@Test
	public void test_area_concave() {
		assertEquals(1.0, area(new Point(0, 0), new Point(2, 1), new Point(1, 1), new Point(1, 2)), DELTA);
		assertEquals(1.0, area(new Point(0, 0), new Point(3, 2), new Point(1, 1), new Point(2, 3)), DELTA);
	}

	@Test
	public void test_area_convex() {
		assertEquals(0.5, area(new Point(0, 0), new Point(1, 0), new Point(0, 1)), DELTA);
		assertEquals(1.5, area(new Point(0, 0), new Point(2, 1), new Point(1, 2)), DELTA);
	}

	@Test
	public void test_contains() {
		assertTrue(contains(new int[] { 0, 3, 0 }, new int[] { 0, 0, 3 }, 3, 1, 1));
	}

}