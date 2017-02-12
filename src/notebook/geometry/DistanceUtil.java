package notebook.geometry;

import static org.junit.Assert.*;

import org.junit.Test;

public class DistanceUtil {
	static final double DELTA = 1e-10;

	@Test
	public void test() {
		assertEquals(Math.sqrt(2) / 2, distanceOriginToLine(new int[][] { { 0, 1 }, { 1, 0 } }), DELTA);
	}

	public static double distanceOriginToLineSegment(int[][] l) {
		// TODO
		return 0;
	}

	public static double distanceOriginToLine(int[][] l) {
		int x1 = l[0][0], y1 = l[0][1], x2 = l[1][0], y2 = l[1][1];
		double top = Math.abs(x2 * y1 - y2 * x1);
		double bot = Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
		return top / bot;
	}

	public static double distancePointToLine(int[] p, int[][] l) {
		int x0 = p[0], y0 = p[1], x1 = l[0][0], y1 = l[0][1], x2 = l[1][0], y2 = l[1][1];
		double top = Math.abs((y2 - y1) * x0 - (x2 - x1) * y0 + x2 * y1 - y2 * x1);
		double bot = Math.hypot(y2 - y1, x2 - x1);
		return top / bot;
	}
}
