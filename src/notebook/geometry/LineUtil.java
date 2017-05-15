package notebook.geometry;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import notebook.Notebook;

/**
 * @author {@link java.awt.geom.Line2D}
 */
public class LineUtil {

	static final double DELTA = 1e-15;

	/**
	 * @see {@link java.awt.geom.Line2D#linesIntersect(double, double, double, double, double, double, double, double)}
	 */
	@Notebook
	public static boolean linesIntersect(double x1, double y1, double x2, double y2, double x3, double y3, double x4,
			double y4) {
		return ((relativeCCW(x1, y1, x2, y2, x3, y3) * relativeCCW(x1, y1, x2, y2, x4, y4) <= 0)
				&& (relativeCCW(x3, y3, x4, y4, x1, y1) * relativeCCW(x3, y3, x4, y4, x2, y2) <= 0));
	}

	/**
	 * @see {@link java.awt.geom.Line2D#ptLineDist(double, double)}
	 */
	@Notebook
	public static double ptLineDist(double x1, double y1, double x2, double y2, double px, double py) {
		return Math.sqrt(ptLineDistSq(x1, y1, x2, y2, px, py));
	}

	/**
	 * @see {@link java.awt.geom.Line2D#ptLineDistSq(double, double)}
	 */
	@Notebook
	public static double ptLineDistSq(double x1, double y1, double x2, double y2, double px, double py) {
		x2 -= x1;
		y2 -= y1;
		px -= x1;
		py -= y1;
		double dotprod = px * x2 + py * y2;
		double projlenSq = dotprod * dotprod / (x2 * x2 + y2 * y2);
		double lenSq = px * px + py * py - projlenSq;
		if (lenSq < 0) {
			lenSq = 0;
		}
		return lenSq;
	}

	/**
	 * @see {@link java.awt.geom.Line2D#ptSegDist(double, double)}
	 */
	@Notebook
	public static double ptSegDist(double x1, double y1, double x2, double y2, double px, double py) {
		return Math.sqrt(ptSegDistSq(x1, y1, x2, y2, px, py));
	}

	/**
	 * @see {@link java.awt.geom.Line2D#ptSegDistSq(double, double)}
	 */
	@Notebook
	public static double ptSegDistSq(double x1, double y1, double x2, double y2, double px, double py) {
		x2 -= x1;
		y2 -= y1;
		px -= x1;
		py -= y1;
		double dotprod = px * x2 + py * y2;
		double projlenSq;
		if (dotprod <= 0.0) {
			projlenSq = 0.0;
		} else {
			px = x2 - px;
			py = y2 - py;
			dotprod = px * x2 + py * y2;
			if (dotprod <= 0.0) {
				projlenSq = 0.0;
			} else {
				projlenSq = dotprod * dotprod / (x2 * x2 + y2 * y2);
			}
		}
		double lenSq = px * px + py * py - projlenSq;
		if (lenSq < 0) {
			lenSq = 0;
		}
		return lenSq;
	}

	/**
	 * @see {@link java.awt.geom.Line2D#relativeCCW(double, double, double, double, double, double)}
	 */
	@Notebook
	public static int relativeCCW(double x1, double y1, double x2, double y2, double px, double py) {
		x2 -= x1;
		y2 -= y1;
		px -= x1;
		py -= y1;
		double ccw = px * y2 - py * x2;
		if (ccw == 0.0) {
			ccw = px * x2 + py * y2;
			if (ccw > 0.0) {
				px -= x2;
				py -= y2;
				ccw = px * x2 + py * y2;
				if (ccw < 0.0) {
					ccw = 0.0;
				}
			}
		}
		return (ccw < 0.0) ? -1 : ((ccw > 0.0) ? 1 : 0);
	}

	@Test
	public void test_ptLineDist() {
		assertEquals(2.4, ptLineDist(3, 0, 0, 4, 0, 0), DELTA);
		assertEquals(Math.sqrt(2), ptLineDist(2, 0, 0, 2, 0, 0), DELTA);
	}

	@Test
	public void test_ptSegDist() {
		assertEquals(2.4, ptLineDist(3, 0, 0, 4, 0, 0), DELTA);
		assertEquals(Math.sqrt(2), ptLineDist(2, 0, 0, 2, 0, 0), DELTA);

		// not perpendicular
		assertEquals(Math.sqrt(2), ptSegDist(1, 1, 2, 2, 0, 0), DELTA);
	}
}
